package com.example.syscredit.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.syscredit.R
import com.example.syscredit.base.BaseViewHolder
import com.example.syscredit.data.model.Credito
import java.util.*

class MainAdapter(
    private val context: Context,
    private val customerList: List<Credito>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>(),
    Filterable {

    var customerFilterList = mutableListOf<Credito>()

    init {
        customerFilterList = customerList as MutableList<Credito>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(customerFilterList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return customerFilterList.size
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Credito>(itemView) {
        override fun bind(item: Credito, position: Int) {
            /*itemView.txt_name.text = item.nombreCompleto
            itemView.txt_address.text = item.cliente.direccion
            itemView.setOnClickListener {
                itemClickListener.click(item)
            }

            if (item.pagoHoy) {
                itemView.pay_today_image_view.visibility = View.VISIBLE
            } else {
                itemView.pay_today_image_view.visibility = View.GONE
            }*/
        }
    }

    interface ItemClickListener {
        fun click(credito: Credito)
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                customerFilterList = if (charSearch.isEmpty()) {
                    customerList as MutableList<Credito>
                } else {
                    val resultList = mutableListOf<Credito>()
                    for (row in customerList) {
                        if (row.nombreCompleto.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = customerFilterList
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                customerFilterList = p1?.values as MutableList<Credito>
                notifyDataSetChanged()
            }

        }
    }
}