package com.matrimony.rvrmatrimony.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.matrimony.rvrmatrimony.data.AppCache
import com.matrimony.rvrmatrimony.data.room.AppDatabase
import com.matrimony.rvrmatrimony.data.room.TermsConditionRoomDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Provider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    // SQLite-related dependencies and Persistence related providers

    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return EncryptedSharedPreferences.create(
            "app-prefs",
            MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC), context.applicationContext,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Provides
    @Singleton
    fun provideAppCache(): AppCache = AppCache()

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        )
            .createFromAsset("tcbdf.db")
            .build()
    }

    @Provides
    @Singleton
    fun provideTermsConditionDao(database: AppDatabase): TermsConditionRoomDao {
        return database.termsConditionDao()
    }


}