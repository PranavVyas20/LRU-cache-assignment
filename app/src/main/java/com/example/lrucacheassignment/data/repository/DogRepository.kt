package com.example.lrucacheassignment.data.repository

import com.example.lrucacheassignment.data.local.DogDao
import com.example.lrucacheassignment.data.remote.DogApi
import com.example.lrucacheassignment.domain.model.DogResponse
import com.example.lrucacheassignment.util.DogImageCache
import com.example.lrucacheassignment.util.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


class DogRepository @Inject constructor(
    private val dogApi: DogApi,
    private val dogDao: DogDao,
    private val dogImageCache: DogImageCache
) : BaseDogRepository {
    suspend fun getDog(): Flow<NetworkResponse<DogResponse>> {
        return getFlowResult(
            call = { dogApi.getDog() },
            onResult = { dogResponse ->
                if (dogResponse.status == "success") {
                    dogResponse.imageUrl?.let {
                        coroutineScope {
                            launch(Dispatchers.IO) {
                                dogImageCache.putImage(it)
                            }
                            launch(Dispatchers.IO) {
                                dogDao.insertDog(dogResponse.copy(timestamp = System.currentTimeMillis()))
                            }
                        }
                    }
                }
            })
    }

    suspend fun getAllSavedDogsFromDb() = dogDao.getAllDogs()

    suspend fun getAllCachedDogs() = dogImageCache.getImages()

    suspend fun saveAllDogsToCache(urls: List<String>) {
        dogImageCache.putAllImages(urls)
    }

    suspend fun clearCache() {
        dogDao.deleteAllImages()
        dogImageCache.clearCache()
    }
}