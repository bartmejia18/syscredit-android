package com.example.syscredit.ui.credits.credit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.core.extensions.doPhoneCall
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.model.Credito
import com.example.syscredit.databinding.FragmentDetailCustomerBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).title = credit.nombre_completo

        with (binding) {
            addressTextView.text = credit.cliente.direccion
            phoneTextView.text = credit.cliente.telefono
            dpiTextView.text = credit.cliente.dpi
            totalDebtTextView.text = getString(R.string.label_quetzal, credit.deudatotal)
            balanceTextView.text = getString(R.string.label_quetzal, credit.saldo)
            feedPaidTextView.text = credit.cantidad_cuotas_pagadas.toString()
            remainingTextView.text = credit.cuotas_pendientes.toString()
            dailyFeeTextView.text = getString(R.string.label_quetzal, credit.cuota_diaria)
            startDateTextView.text = credit.fecha_inicio
            endDateTextView.text = credit.fecha_fin
            amountPaidTextView.text = getString(R.string.label_quetzal, credit.monto_abonado.toString())
            showHistoryButton.setOnClickListener {
                findNavController().navigate(DetailCustomerDirections.actionDetailCustomerToHistoryPaymentsFragment(
                    idCredit = credit.id,
                    name = credit.nombre_completo,
                    totalPaid = credit.total_pagado
                ))
            }
            if (!credit.pago_hoy) {
                payRegisterButton.show()
                payRegisterButton.setOnClickListener {
                    findNavController().navigate(DetailCustomerDirections.actionDetailCustomerToPayRegisterFragment(credit.id))
                }
            } else {
                payRegisterButton.hide()
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