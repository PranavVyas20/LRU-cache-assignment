package com.example.lrucacheassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lrucacheassignment.domain.model.DogResponse

@Database(entities = [DogResponse::class], version = 5)
abstract class DogDatabase: RoomDatabase() {
    abstract fun dogDao(): DogDao
}