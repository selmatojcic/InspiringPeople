package com.example.inspiringpeople.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "inspiringPeople")
data class InspiringPerson(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "date") var date: String,
    @ColumnInfo(name = "details") var details: String,
    @ColumnInfo(name = "imageUrl") var imageUrl: String,
    @ColumnInfo(name = "firstQuote") val firstQuote: String,
    @ColumnInfo(name = "secondQuote") val secondQuote: String
) : Serializable