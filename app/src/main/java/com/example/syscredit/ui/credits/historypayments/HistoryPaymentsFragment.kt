package com.example.syscredit.ui.credits.historypayments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syscredit.R
import com.example.syscredit.core.extensions.DEFAULT_ANIMATION_DURATION_TIME
import com.example.syscredit.core.extensions.fadeOut
import com.example.syscredit.core.extensions.hide
import com.example.syscredit.core.extensions.show
import com.example.syscredit.databinding.FragmentHistoryPaymentsBinding
import com.example.syscredit.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryPaymentsFragment: Fragment() {

    private val viewModel: HistoryPaymentsViewModel by viewModels()
    private lateinit var adapter: PaymentsAdapter
    private var _binding: FragmentHistoryPaymentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryPaymentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<Toolbar>(R.id.toolbar).title = requireArguments().getString("name")
        binding.totalAmountTextView.text = getString(R.string.label_total_payment, requireArguments().getString("total_paid"))
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL)
        )
        adapter = PaymentsAdapter(requireContext())
        binding.recyclerView.adapter = adapter

        with (viewModel) {
            getPayments(requireArguments().getInt("id_credit"))
            payments.observe(viewLifecycleOwner) { payments ->
                when (payments.status) {
                    Status.LOADING ->  {
                        binding.progressBar.show()
                        binding.noResultsTextView.hide()
                    }
                    Status.SUCCESS -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            when (payments.data?.size) {
                                0 -> {
                                    binding.noResultsTextView.show()
                                    binding.recyclerView.hide()
                                }
                                else -> {
                                    binding.noResultsTextView.hide()
                                    binding.recyclerView.show()
                                    adapter.setPaymentList(payments.data?: listOf())
                                }
                            }
                        }
                    })
                    Status.ERROR -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.noResultsTextView.show()
                            binding.recyclerView.hide()
                        }
                    })
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}