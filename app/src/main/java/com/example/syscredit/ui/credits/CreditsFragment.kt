package com.example.syscredit.ui.credits

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syscredit.BuildConfig
import com.example.syscredit.R
import com.example.syscredit.core.extensions.DEFAULT_ANIMATION_DURATION_TIME
import com.example.syscredit.core.extensions.fadeOut
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.show
import com.example.syscredit.data.local.getFromSharedPreferences
import com.example.syscredit.data.local.sharedPreferences
import com.example.syscredit.data.model.Credito
import com.example.syscredit.databinding.FragmentCreditsBinding
import com.example.syscredit.utils.Status
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreditsFragment : Fragment(), MainAdapter.ItemClickListener {

    private val viewModel: CreditsViewModel by viewModels()
    lateinit var adapter: MainAdapter
    private var _binding: FragmentCreditsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreditsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCredits(activity?.getFromSharedPreferences("id", 0) ?: 0)
        }

        with(viewModel) {
            credits.observe(viewLifecycleOwner) { credits ->
                when (credits.status) {
                    Status.LOADING -> binding.progressBar.show()
                    Status.SUCCESS -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)

                            binding.swipeRefreshLayout.isRefreshing = false
                            if (credits.data != null) {
                                adapter = MainAdapter(
                                    credits.data.registros,
                                    this@CreditsFragment
                                )
                                binding.totalCobrarText.text = getString(
                                    R.string.label_total_cobrar,
                                    credits.data.total_cobrar
                                )
                                binding.totalCobradoText.text = getString(
                                    R.string.label_total_cobrado,
                                    credits.data.total_cobrado
                                )
                                binding.recyclerView.adapter = adapter
                            } else {
                                CoroutineScope(Dispatchers.Main + Job()).launch {
                                    requireContext().sharedPreferences {
                                        putInt("id", 0)
                                        putBoolean("logged_in", false)
                                    }
                                }
                                MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
                                    .setTitle(R.string.title_info)
                                    .setMessage(credits.message)
                                    .setPositiveButton(R.string.action_accept) { dialog, _ ->
                                        dialog.dismiss()
                                        binding.recyclerView.hide()
                                    }
                                    .create().show()
                            }
                        }
                    })
                    Status.ERROR -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.swipeRefreshLayout.isRefreshing = false
                            Toast.makeText(
                                requireContext(),
                                "HAY UN ERROR ${credits.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf(), this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter
    }

    override fun click(credito: Credito) {
        val bundle = Bundle()
        bundle.putParcelable("credit", credito)
        findNavController().navigate(R.id.detail_customer, bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        viewModel.getCredits(activity?.getFromSharedPreferences("id", 0) ?: 0)
    }

}
