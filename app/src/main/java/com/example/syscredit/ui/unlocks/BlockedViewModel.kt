package com.example.syscredit.ui.unlocks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.syscredit.data.model.Blocked
import com.example.syscredit.data.repository.BlockedRepository
import com.example.syscredit.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BlockedViewModel @Inject constructor(
    private val blockedRepository: BlockedRepository
) : ViewModel() {
    private val _blockedList = MutableLiveData<Resource<List<Blocked>>>()
    val blockedList: LiveData<Resource<List<Blocked>>>
        get() = _blockedList

    fun getBlockedList(branchId: Int) {
        viewModelScope.launch {
            _blockedList.postValue(Resource.loading(null))
            blockedRepository.getCustomerBlocked(branchId).let {
                if (it.isSuccessful) {
                    _blockedList.postValue(Resource.success(
                        it.body()?.message,
                        it.body()?.records
                    ))
                } else {
                    _blockedList.postValue(Resource.error(it.message(), null))
                }
            }
        }
    }
}