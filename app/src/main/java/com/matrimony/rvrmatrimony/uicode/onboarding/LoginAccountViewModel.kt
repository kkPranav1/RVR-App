package com.matrimony.rvrmatrimony.uicode.onboarding

import com.matrimony.rvrmatrimony.basemodule.BaseViewModel
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import javax.inject.Inject

class LoginAccountViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : BaseViewModel() {
    val VMTAG = LoginAccountViewModel::class.java.simpleName

    fun fetchProfileCreationOptions(): List<SpinnerItemPojo> {
        return repository.getMatrimonyAccountCreationSpinnerOptions()
    }

}