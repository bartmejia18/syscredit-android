package com.example.syscredit.ui.credits.historypayments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.syscredit.R
import com.example.syscredit.data.model.Payment
import com.example.syscredit.databinding.ItemHistoryPaymentBinding

class PaymentsAdapter(private val context: Context): RecyclerView.Adapter<PaymentsAdapter.PaymentViewHolder>() {

    private var paymentList = listOf<Payment>()

    fun setPaymentList(paymentList: List<Payment>){
        this.paymentList = paymentList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val binding =
            ItemHistoryPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PaymentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        with(holder) {
            with(paymentList[position]) {
                binding.payDateTextView.text = fecha_pago
                binding.amountPayTextView.text = context.getString(R.string.label_quetzal, abono.toString())
            }
        }
    }

    override fun getItemCount(): Int {
        return paymentList.size
    }

    inner class PaymentViewHolder(val binding: ItemHistoryPaymentBinding) : RecyclerView.ViewHolder(binding.root)
}