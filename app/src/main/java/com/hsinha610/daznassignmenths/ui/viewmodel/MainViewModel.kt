package com.hsinha610.daznassignmenths.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hsinha610.daznassignmenths.data.DataList
import com.hsinha610.daznassignmenths.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    private val _mld = MutableLiveData<UiState>(UiState.Loading)
    val ld: LiveData<UiState>
        get() = _mld

    fun getData() {
        viewModelScope.launch {
            _mld.value = UiState.Loading
            try {
                val data = repo.getData()
                _mld.value = UiState.Success(data)
            } catch (e: Exception) {
                _mld.value = UiState.Error(e)
            }
        }
    }

    private val _openDetailFragmentMLD = MutableLiveData<Int>(null)
    val openDetailFragmentLD: LiveData<Int>
        get() = _openDetailFragmentMLD
    fun openDetailFragment(itemIndex: Int) {
        _openDetailFragmentMLD.value = itemIndex
    }
}

sealed class UiState {
    object Loading : UiState()
    data class Success(val data: DataList?) : UiState()
    data class Error(val error: Exception) : UiState()
}