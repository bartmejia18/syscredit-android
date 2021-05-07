package com.example.syscredit.ui.credits.payregister

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syscredit.data.repository.PaymentRepository
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PayRegisterViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository
): ViewModel() {
    private val _payment = MutableLiveData<Resource<Any>>()
    val payment: LiveData<Resource<Any>>
        get() = _payment

    fun payRegister(idCredito: Int, abono: Double) {
        viewModelScope.launch {
            _payment.postValue(Resource.loading())
            paymentRepository.payment(idCredito, abono).let {
                if (it.isSuccessful) {
                    _payment.postValue(Resource.success(Any()))
                } else {
                    _payment.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }
}