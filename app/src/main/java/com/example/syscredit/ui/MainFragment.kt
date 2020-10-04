package com.example.syscredit.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Adapter
import android.widget.SearchView
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
    lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
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
        viewModel.fetchCustomerList.observe(viewLifecycleOwner, { result ->
            when (result) {
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    progress_bar.visibility = View.GONE
                    adapter = MainAdapter(requireContext(), result.data.result.registros, this)
                    recycler_view.adapter = adapter
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

}
