package com.example.syscredit.ui.credits

import androidx.lifecycle.*
import com.example.syscredit.data.model.Credito
import com.example.syscredit.data.model.Records
import com.example.syscredit.data.repository.CreditsRepository
import com.example.syscredit.utils.NetworkHelper
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreditsViewModel @Inject constructor(
    private val creditsRepository: CreditsRepository,
    private val networkHelper: NetworkHelper
): ViewModel() {
    private val _credits = MutableLiveData<Resource<Records>>()
    val credits: LiveData<Resource<Records>>
        get() = _credits

    fun getCredits(idUser: Int) {
        viewModelScope.launch {
            _credits.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                creditsRepository.getListCredits(idUser).let {
                    if (it.isSuccessful) {
                        _credits.postValue(Resource.success(it.body()?.message, it.body()?.records))
                    } else {
                        _credits.postValue(Resource.error(it.message()))
                    }
                }
            } else {
                _credits.postValue(Resource.error("No tiene accesso a internet"))
            }
        }
    }
}