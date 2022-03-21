package com.example.imagesearchbox.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.imagesearchbox.utils.Constants.DATABASE_NAME

@Entity(tableName = DATABASE_NAME)
data class MyBox(
    val url: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}