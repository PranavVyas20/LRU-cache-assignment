package com.example.lrucacheassignment.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "dogs")
data class DogResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @SerializedName("message")
    val imageUrl: String? = null,
    @SerializedName("status")
    val status: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
