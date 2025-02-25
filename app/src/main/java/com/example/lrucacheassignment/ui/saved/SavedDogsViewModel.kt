package com.example.lrucacheassignment.ui.saved

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lrucacheassignment.data.repository.DogRepository
import com.example.lrucacheassignment.ui.generate_dog.GenerateDogViewModel.GenerateDogUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedDogsViewModel @Inject constructor(
    private val dogRepository: DogRepository
) : ViewModel() {
    data class SavedDogsUiState(
        val isLoading: Boolean = true,
        val savedDogs: List<String>? = null,
        val errorMessage: String? = null
    )

    private val _savedDogsUiState = MutableStateFlow(SavedDogsUiState())
    val savedDogsUiState: StateFlow<SavedDogsUiState> get() = _savedDogsUiState.asStateFlow()

    fun clearCache() {
        viewModelScope.launch {
            dogRepository.clearCache()
            _savedDogsUiState.update {
                it.copy(savedDogs = emptyList())
            }
        }
    }

    fun getSavedDogs() {
        viewModelScope.launch(Dispatchers.IO) {
            _savedDogsUiState.update {
                it.copy(isLoading = true)
            }
            val cachedDogs = dogRepository.getAllCachedDogs()
            if (cachedDogs.isEmpty()) {
                val savedDogs = dogRepository.getAllSavedDogsFromDb()
                if (savedDogs.isNotEmpty()) {
                    launch {
                        dogRepository.saveAllDogsToCache(savedDogs.asReversed().map { it.imageUrl.orEmpty() })
                    }
                }
                _savedDogsUiState.update {
                    Log.d("SavedDogsViewModel", "dogs from db: $savedDogs")
                    it.copy(savedDogs = savedDogs.map { it.imageUrl.orEmpty() }, isLoading = false)
                }
            } else {
                Log.d("SavedDogsViewModel", "dogs from lru: $cachedDogs")
                _savedDogsUiState.update {
                    it.copy(savedDogs = cachedDogs, isLoading = false)
                }
            }
        }
    }
}