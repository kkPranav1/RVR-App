package com.matrimony.rvrmatrimony.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terms")
data class TermConditionEntity(
    @PrimaryKey
    @ColumnInfo(name = "TermTitle")
    val termTitle: String,

    @ColumnInfo(name = "TermDesc")
    val termDesc: String
)
