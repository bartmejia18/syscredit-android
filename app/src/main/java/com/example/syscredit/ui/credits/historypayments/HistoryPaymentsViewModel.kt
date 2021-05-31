package com.example.syscredit.ui.credits.historypayments

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syscredit.data.model.Payment
import com.example.syscredit.data.repository.PaymentRepository
import com.example.syscredit.utils.NetworkHelper
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryPaymentsViewModel @Inject constructor(
    private val paymentRepository: PaymentRepository,
    private val networkHelper: NetworkHelper,
) : ViewModel() {
    private val _payments = MutableLiveData<Resource<List<Payment>>>()
    val payments: LiveData<Resource<List<Payment>>>
        get() = _payments

    fun getPayments(idCredit: Int) {
        viewModelScope.launch {
            _payments.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                paymentRepository.getPayments(idCredit).let {
                    if (it.isSuccessful) {
                        _payments.postValue(
                            Resource.success(
                                it.body()?.message,
                                it.body()?.records
                            )
                        )
                    } else {
                        _payments.postValue(Resource.error(it.message()))
                    }
                }
            } else {
                _payments.postValue(Resource.error("No tiene acceso a internet"))
            }
        }
    }
}