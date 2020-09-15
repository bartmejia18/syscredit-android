package com.example.syscredit.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.syscredit.R
import com.example.syscredit.base.BaseViewHolder
import com.example.syscredit.data.model.Credito
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_customer.view.*

class MainAdapter(
    private val context: Context,
    private val customerList: List<Credito>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(customerList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Credito>(itemView) {
        override fun bind(item: Credito, position: Int) {
            itemView.txt_name.text = item.nombreCompleto
            itemView.txt_address.text = item.cliente.direccion
            itemView.txt_number_phone.text = item.cliente.telefono
            itemView.setOnClickListener {
                itemClickListener.click(item)
            }
        }
    }

    interface ItemClickListener {
        fun click(credito: Credito)
    }
}