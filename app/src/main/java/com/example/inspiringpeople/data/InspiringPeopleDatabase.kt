package com.example.inspiringpeople.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.inspiringpeople.model.InspiringPerson


@Database(entities = [InspiringPerson::class], version = 3)
abstract class InspiringPeopleDatabase : RoomDatabase() {

    abstract fun inspiringPersonDao(): InspiringPersonDao

    companion object {
        const val NAME = "inspiringPeopleDb"
    }
}