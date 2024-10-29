package com.matrimony.rvrmatrimony.utils

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppSharedPreferencesStore @Inject constructor(private val prefs: SharedPreferences) {

    private fun setData(
        prefType: AllConstants.SPDataType,
        prefKeyEntry: String,
        prefValueEntry: Any
    ) {
        val editor = prefs.edit()
        when (prefType) {
            AllConstants.SPDataType.StringType -> editor.putString(
                prefKeyEntry,
                prefValueEntry as String
            )

            AllConstants.SPDataType.BooleanType -> editor.putBoolean(
                prefKeyEntry,
                prefValueEntry as Boolean
            )

            AllConstants.SPDataType.IntType -> editor.putInt(prefKeyEntry, prefValueEntry as Int)
            AllConstants.SPDataType.FloatType -> editor.putFloat(
                prefKeyEntry,
                prefValueEntry as Float
            )

            AllConstants.SPDataType.LongType -> editor.putLong(prefKeyEntry, prefValueEntry as Long)
        }
        editor.apply()
    }

    private fun getData(
        prefType: AllConstants.SPDataType,
        prefKeyEntry: String,
        defaultValue: Any
    ): Any {
        return when (prefType) {
            AllConstants.SPDataType.StringType -> prefs.getString(
                prefKeyEntry,
                defaultValue as String
            ) ?: defaultValue

            AllConstants.SPDataType.BooleanType -> prefs.getBoolean(
                prefKeyEntry,
                defaultValue as Boolean
            ) ?: defaultValue

            AllConstants.SPDataType.IntType -> prefs.getInt(prefKeyEntry, defaultValue as Int)
                ?: defaultValue

            AllConstants.SPDataType.FloatType -> prefs.getFloat(prefKeyEntry, defaultValue as Float)
                ?: defaultValue

            AllConstants.SPDataType.LongType -> prefs.getLong(prefKeyEntry, defaultValue as Long)
                ?: defaultValue
        }
    }

    fun setString(prefKeyEntry: String, prefValueEntry: String) {
        setData(AllConstants.SPDataType.StringType, prefKeyEntry, prefValueEntry)
    }

    fun getString(prefKeyEntry: String): String {
        return getData(
            AllConstants.SPDataType.StringType,
            prefKeyEntry,
            AllConstants.emptyString
        ) as String
    }

    fun setInt(prefKeyEntry: String, prefValueEntry: Int) {
        setData(AllConstants.SPDataType.IntType, prefKeyEntry, prefValueEntry)
    }

    fun getInt(prefKeyEntry: String): Int {
        return getData(AllConstants.SPDataType.IntType, prefKeyEntry, 0) as Int
    }

    fun setLong(prefKeyEntry: String, prefValueEntry: Long) {
        setData(AllConstants.SPDataType.LongType, prefKeyEntry, prefValueEntry)
    }

    fun getLong(prefKeyEntry: String): Long {
        return getData(AllConstants.SPDataType.LongType, prefKeyEntry, 0L) as Long
    }

    fun setFloat(prefKeyEntry: String, prefValueEntry: Float) {
        setData(AllConstants.SPDataType.FloatType, prefKeyEntry, prefValueEntry)
    }

    fun getFloat(prefKeyEntry: String): Float {
        return getData(AllConstants.SPDataType.FloatType, prefKeyEntry, 0.0) as Float
    }

    fun setBoolean(prefKeyEntry: String, prefValueEntry: Boolean) {
        setData(AllConstants.SPDataType.BooleanType, prefKeyEntry, prefValueEntry)
    }

    fun getBoolean(prefKeyEntry: String, defaultValue: Boolean): Boolean {
        return getData(AllConstants.SPDataType.BooleanType, prefKeyEntry, defaultValue) as Boolean
    }


}