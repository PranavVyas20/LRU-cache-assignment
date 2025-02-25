package com.example.lrucacheassignment.util

import android.util.LruCache
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DogImageCache @Inject constructor() {
    private val CACHE_SIZE = 20
    private val cache: LruCache<String, String> = LruCache(CACHE_SIZE)

    fun putImage(url: String) {
        cache.put(url, url)
    }

    fun putAllImages(urls: List<String>) {
        urls.forEach {
            cache.put(it, it)
        }
    }

    fun getImages(): List<String> {
        return cache.snapshot().values.toList().asReversed()
    }

    fun clearCache() {
        cache.evictAll()
    }
}
