package com.matrimony.rvrmatrimony.basemodule

import androidx.lifecycle.ViewModel
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.AppSharedPreferencesStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var sharedPreferencesManager: AppSharedPreferencesStore

    fun setLoginUserType(type: AllConstants.UserType) {
        sharedPreferencesManager.setString(AllConstants.LOGIN_USER_TYPE, type.s)
    }

    fun getLoginUserType(): AllConstants.UserType {
        val utString = sharedPreferencesManager.getString(AllConstants.LOGIN_USER_TYPE)
        return if (utString != AllConstants.emptyString) AllConstants.UserType.valueOf(utString) else AllConstants.UserType.NOT_LOGGED_IN
    }

    override fun onCleared() {
        super.onCleared()
    }
}
