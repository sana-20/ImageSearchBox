package com.example.imagesearchbox.repository

import androidx.annotation.WorkerThread
import com.example.imagesearchbox.model.MyBox
import com.example.imagesearchbox.db.MyBoxDao
import com.example.imagesearchbox.db.MyBoxDatabase
import kotlinx.coroutines.flow.Flow

class MyBoxRepository(database: MyBoxDatabase) {

    private val dao: MyBoxDao = database.myBoxDao()

    val allMyBox: Flow<List<MyBox>> = dao.getAllMyBoxData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(myBox: MyBox) {
        dao.insert(myBox)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }

    companion object {
        private lateinit var sInstance: MyBoxRepository

        fun getInstance(database: MyBoxDatabase): MyBoxRepository {
            if (!this::sInstance.isInitialized) {
                synchronized(MyBoxRepository::class.java) {
                    sInstance = MyBoxRepository(database)
                }
            }
            return sInstance
        }
    }

}
