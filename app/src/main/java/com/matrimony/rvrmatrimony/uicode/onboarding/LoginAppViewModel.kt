package com.matrimony.rvrmatrimony.uicode.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.matrimony.rvrmatrimony.basemodule.BaseViewModel
import com.matrimony.rvrmatrimony.data.room.TermConditionEntity
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginAppViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : BaseViewModel() {
    val VMTAG = LoginAppViewModel::class.java.simpleName

    // checking user login
    fun isUserLoggedIn(): Boolean {
        return super.getLoginUserType() != AllConstants.UserType.NOT_LOGGED_IN
    }

    fun gen4DigitOtp(): Int {
        return kotlin.random.Random.nextInt(1001, 9999)
    }

    fun fetchProfileCreationOptions(): List<SpinnerItemPojo> {
        return repository.getMatrimonyAccountCreationSpinnerOptions()
    }

    fun fetchTermsAndConditions(): List<TermsDataModel> {
        return repository.getTermsAndConditionsList()
    }

    private val _termsConditions = MutableLiveData<List<TermConditionEntity>>()
    val termsConditions: LiveData<List<TermConditionEntity>> = _termsConditions

    fun fetchTermsConditions() {
        viewModelScope.launch {
            try {
                val terms = repository.getTermsAndConditionsList2()
                _termsConditions.value = terms
            } catch (e: Exception) {
                // Handle error if needed
            }
        }
    }

}