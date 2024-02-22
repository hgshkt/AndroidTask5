package com.hgshkt.androidtask5.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hgshkt.androidtask5.data.mappers.ImageSizeType
import com.hgshkt.androidtask5.data.mappers.toDetail
import com.hgshkt.androidtask5.data.mappers.toDisplay
import com.hgshkt.androidtask5.data.repository.SuperHeroRepository
import com.hgshkt.androidtask5.view.fragments.details.model.SuperHeroDetail
import com.hgshkt.androidtask5.view.model.SuperHeroDisplay
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: SuperHeroRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState>(UIState.EmptyList)
    val uiState: LiveData<UIState> = _uiState

    var displayList: List<SuperHeroDisplay> = mutableListOf()
        private set
    var detailList: List<SuperHeroDetail> = mutableListOf()
        private set

    fun getSuperHeroes() {
        viewModelScope.launch {
            val response = repository.getSuperHeroes()

            if (response.isSuccessful) {
                val list = response.body!!

                displayList = list.map {
                    it.toDisplay(ImageSizeType.MD)
                }
                detailList = list.map {
                    it.toDetail(ImageSizeType.LG)
                }

                _uiState.postValue(UIState.FilledList(displayList))
            } else {
                _uiState.postValue(UIState.Error(response.errorMassage!!))
            }
        }
    }

    fun openDetailScreen(clickedSuperHero: SuperHeroDisplay) {
        val hero = detailList.find {
            it.name == clickedSuperHero.name
        }
        val state = UIState.DetailedScreen(hero!!)
        _uiState.postValue(state)
    }

    sealed class UIState {
        data object EmptyList : UIState()
        data class Error(val errorMessage: String) : UIState()
        data class FilledList(val list: List<SuperHeroDisplay>) : UIState()
        data class DetailedScreen(val superHero: SuperHeroDetail) : UIState()
    }
}