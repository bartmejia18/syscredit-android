package com.example.syscredit.ui.credits.payregister

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.syscredit.R
import com.example.syscredit.core.extensions.*
import com.example.syscredit.databinding.FragmentDialogRegisterPayBinding
import com.example.syscredit.utils.Status
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PayRegisterFragment : DialogFragment() {

    private val viewModel: PayRegisterViewModel by viewModels()
    private var _binding: FragmentDialogRegisterPayBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDialogRegisterPayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        with(binding.appbarView.toolbar) {
            inflateMenu(R.menu.close)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.item_close -> {
                        dismiss()
                        true
                    }
                    else -> true
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            this.dismiss()
        }
        binding.sendButton.setOnClickListener {
            when {
                binding.amountTextInputLayout
                    .validator()
                    .nonEmpty()
                    .numberDecimals()
                    .addErrorCallback { error, field ->
                        field?.error = when (error) {
                            ValidationError.NON_EMPTY -> getString(R.string.message_error_field_required)
                            else -> "hay un error"
                        }
                        field?.editText?.requestFocus()
                    }
                    .addSuccessCallback {
                        it?.error = null
                    }
                    .validate() -> viewModel.payRegister(
                        idCredito = arguments?.getInt("id_credit")?: 0,
                        abono = binding.amountTextInputLayout.editText?.text.toString().toDouble()
                    )
            }
        }

        with(viewModel) {
            payment.observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.LOADING -> {
                        binding.sendButton.text = ""
                        binding.progressBar.show()
                    }
                    Status.SUCCESS -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            binding.sendButton.text = getString(R.string.action_send)
                            Toast.makeText(requireContext(), "Pago registrado exitosamente", Toast.LENGTH_LONG).show()
                            this@PayRegisterFragment.dismiss()
                            findNavController().popBackStack(R.id.detail_customer, true)
                        }
                    })
                    Status.ERROR -> binding.progressBar.fadeOut(DEFAULT_ANIMATION_DURATION_TIME, object : AnimatorListenerAdapter(){
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            binding.sendButton.text = getString(R.string.action_send)
                            MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
                                .setTitle(R.string.title_error)
                                .setMessage(it.message)
                                .setPositiveButton(R.string.action_accept) { dialog, _ -> dialog.dismiss() }
                                .create().show()
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