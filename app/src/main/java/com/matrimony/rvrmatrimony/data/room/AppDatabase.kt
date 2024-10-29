package com.matrimony.rvrmatrimony.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TermConditionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun termsConditionDao(): TermsConditionRoomDao
}