package com.example.lrucacheassignment.ui.generate_dog

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lrucacheassignment.data.repository.DogRepository
import com.example.lrucacheassignment.util.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenerateDogViewModel @Inject constructor(private val dogRepository: DogRepository) :
    ViewModel() {

    data class GenerateDogUiState(
        val isLoading: Boolean = false,
        val imageUrl: String? = null,
        val errorMessage: String? = null
    )

    private val _GenerateDog_uiState = MutableStateFlow(GenerateDogUiState())
    val generateDogUiState: StateFlow<GenerateDogUiState> get() = _GenerateDog_uiState.asStateFlow()

    val TAG = "GenerateDogViewModel"
    fun getRandomDogImage() {
        viewModelScope.launch(Dispatchers.IO) {
            dogRepository.getDog().collect { response ->
                when (response) {
                    is NetworkResponse.Error -> {
                        Log.d(TAG, response.message)
                        _GenerateDog_uiState.update {
                            it.copy(isLoading = false, errorMessage = response.message)
                        }
                    }

                    is NetworkResponse.Loading -> {
                        Log.d(TAG, "Loading")
                        _GenerateDog_uiState.update {
                            it.copy(isLoading = true, errorMessage = null)
                        }
                    }

                    is NetworkResponse.Success -> {
                        _GenerateDog_uiState.update {
                            it.copy(
                                imageUrl = response.data.imageUrl,
                                isLoading = false,
                                errorMessage = null,
                            )
                        }
                        Log.d(TAG, response.data.toString())
                    }
                }
            }
        }
    }
}