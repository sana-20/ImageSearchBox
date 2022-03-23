package com.example.imagesearchbox.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imagesearchbox.model.MyBox
import com.example.imagesearchbox.utils.Constants.DATABASE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface MyBoxDao {

    @Query("SELECT * FROM $DATABASE_NAME ORDER BY id ASC")
    fun getAllMyBoxData(): Flow<List<MyBox>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(myBox: MyBox)

    @Query("DELETE FROM $DATABASE_NAME")
    suspend fun deleteAll()

}
