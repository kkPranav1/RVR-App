package com.matrimony.rvrmatrimony.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TermsConditionRoomDao {
    @Query("SELECT * FROM terms")
    suspend fun getAllTermsConditions(): List<TermConditionEntity>

    @Delete
    suspend fun deleteTermCondition(termCondition: TermConditionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTermCondition(termCondition: TermConditionEntity)
}