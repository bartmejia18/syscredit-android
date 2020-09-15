package com.example.syscredit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syscredit.R
import com.example.syscredit.data.DataSource
import com.example.syscredit.data.model.Credito
import com.example.syscredit.domain.RepoImpl
import com.example.syscredit.ui.viewmodel.MainViewModel
import com.example.syscredit.ui.viewmodel.VMFactory
import com.example.syscredit.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment(), MainAdapter.ItemClickListener {

    private val viewModel by viewModels<MainViewModel>() { VMFactory(RepoImpl(DataSource())) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.fetchCustomerList.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    recycler_view.adapter = MainAdapter(requireContext(), result.data.result.registros, this)
                }
                is Resource.Failure -> {
                    progress_bar.visibility = View.GONE
                    Log.d("RESULTADO", result.exception.toString())
                    Toast.makeText(
                        requireContext(),
                        "HAY UN ERROR ${result.exception}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })

    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun click(credito: Credito) {
        val bundle = Bundle()
        bundle.putParcelable("credito", credito)
        findNavController().navigate(R.id.detailCustomer, bundle)
    }

}