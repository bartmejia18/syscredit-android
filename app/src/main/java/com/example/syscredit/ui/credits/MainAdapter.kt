package com.example.syscredit.ui.credits

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.model.Credito
import com.example.syscredit.databinding.ItemCustomerBinding
import java.util.*

class MainAdapter(
    private val customerList: List<Credito>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>(), Filterable {

    var customerFilterList = mutableListOf<Credito>()

    init {
        customerFilterList = customerList as MutableList<Credito>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        with (holder) {
            with (customerFilterList[position]) {
                binding.txtName.text = nombre_completo
                binding.txtAddress.text = cliente.direccion
                holder.itemView.setOnClickListener {
                    itemClickListener.click(this)
                }

                if (pago_hoy) {
                    binding.payTodayImageView.show()
                } else {
                    binding.payTodayImageView.hide()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return customerFilterList.size
    }

    inner class MainViewHolder (val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root)

    interface ItemClickListener {
        fun click(credito: Credito)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                customerFilterList = if (charSearch.isEmpty()) {
                    customerList as MutableList<Credito>
                } else {
                    val resultList = mutableListOf<Credito>()
                    for (row in customerList) {
                        if (row.nombre_completo.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
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