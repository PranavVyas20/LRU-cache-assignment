package com.example.lrucacheassignment.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lrucacheassignment.domain.model.DogResponse

@Dao
interface DogDao {
    @Query("SELECT * FROM dogs ORDER BY timestamp DESC")
    suspend fun getAllDogs(): List<DogResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDog(dog: DogResponse)

    @Query("DELETE FROM dogs")
    suspend fun deleteAllImages()

}