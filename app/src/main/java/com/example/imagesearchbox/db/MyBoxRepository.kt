package com.example.imagesearchbox.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MyBoxRepository(database: MyBoxDatabase) {

    private val dao: MyBoxDao = database.myBoxDao()

    val allMybox: Flow<List<MyBox>> = dao.getAllMyBoxData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(myBox: MyBox) {
        dao.insert(myBox)
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
