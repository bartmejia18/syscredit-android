package com.example.syscredit.ui.credits.details

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.core.extensions.doPhoneCall
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.model.Credito
import com.example.syscredit.databinding.FragmentDetailCustomerBinding
import java.time.DayOfWeek
import java.time.LocalDate
import java.util.*

class DetailCustomer : Fragment() {

    private lateinit var credit: Credito
    private var _binding: FragmentDetailCustomerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            credit = it.getParcelable("credit")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCustomerBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).title = credit.nombre_completo

        with (binding) {
            addressTextView.text = credit.cliente.direccion
            phoneTextView.text = credit.cliente.telefono
            dpiTextView.text = credit.cliente.dpi

            /*when (credit.estado_morosidad) {
                "Excelente" -> binding.estadoMorosidad.setBackgroundResource(R.drawable.rectangle_green)
                "Bueno" -> binding.estadoMorosidad.setBackgroundResource(R.drawable.rectangle_orange)
                "Moroso" -> binding.estadoMorosidad.setBackgroundResource(R.drawable.rectangle_red)
                else -> {}
            }*/

            binding.estadoMorosidad.visibility = View.GONE

            totalDebtTextView.text = getString(R.string.label_quetzal, credit.deudatotal)
            balanceTextView.text = getString(R.string.label_quetzal, credit.saldo)
            feedPaidTextView.text = credit.cantidad_cuotas_pagadas.toString()
            remainingTextView.text = credit.cuotas_pendientes.toString()
            arrearsTextView.text = credit.cuotas_atrasadas.toString()
            dailyFeeTextView.text = getString(R.string.label_quetzal, credit.cuota_diaria)
            startDateTextView.text = credit.fecha_inicio
            endDateTextView.text = credit.fecha_fin
            amountPaidTextView.text = getString(R.string.label_quetzal, credit.monto_abonado.toString())
            showHistoryButton.setOnClickListener {
                findNavController().navigate(DetailCustomerDirections.actionDetailCustomerToHistoryPaymentsFragment(
                    idCredit = credit.id,
                    name = credit.nombre_completo,
                    totalPaid = credit.total_pagado?:"0"
                ))
            }

            val today = LocalDate.now().dayOfWeek

            if (credit.pago_hoy || (today == DayOfWeek.SUNDAY && credit.planes?.domingo == "1")) {
                payRegisterButton.hide()
            } else {
                payRegisterButton.show()
                payRegisterButton.setOnClickListener {
                    findNavController().navigate(
                        DetailCustomerDirections.actionDetailCustomerToPayRegisterFragment(
                            credit.id
                        )
                    )
                }
            }

            phoneButton.setOnClickListener {
                this@DetailCustomer.doPhoneCall(credit.cliente.telefono)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}