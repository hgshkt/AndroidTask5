package com.hgshkt.androidtask5.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgshkt.androidtask5.data.mappers.ImageSizeType
import com.hgshkt.androidtask5.data.mappers.toDisplay
import com.hgshkt.androidtask5.data.repository.SuperHeroRepositoryImpl
import com.hgshkt.androidtask5.view.model.SuperHeroDisplay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _uiState = MutableLiveData<UIState>(UIState.EmptyList)
    val uiState: LiveData<UIState> = _uiState

    lateinit var repository: SuperHeroRepositoryImpl

    fun getSuperHeroes() {
        viewModelScope.launch {
            val list = repository.getSuperHeroes()
            val displayList = list.map {
                it.toDisplay(ImageSizeType.MD)
            }
            _uiState.postValue(UIState.FilledList(displayList))
        }
    }

    sealed class UIState {
        data object EmptyList : UIState()
        data class FilledList(val list: List<SuperHeroDisplay>) : UIState()
    }
}