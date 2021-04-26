package com.example.syscredit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.syscredit.R
import com.example.syscredit.data.model.Credito

class DetailCustomer : Fragment() {
    private lateinit var credit: Credito
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {
            credit = it.getParcelable("credito")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*address_text_view.text = credit.cliente.direccion
        phone_text_view.text = credit.cliente.telefono
        dpi_text_view.text = credit.cliente.dpi
        total_debt_text_view.text = "Q. ${credit.deudaTotal}"
        balance_text_view.text = "Q ${credit.saldo}"
        feed_paid_text_view.text = credit.cantidadCuotasPagadas.toString()
        remaining_text_view.text = credit.cuotasPendientes.toString()
        daily_fee_text_view.text = "Q ${credit.cuotaDiaria}"
        start_date_text_view.text = credit.fechaInicio
        end_date_text_view.text = credit.fechaFin
        amount_paid_text_view.text = "Q . ${credit.montoAbonado}"*/
    }
}