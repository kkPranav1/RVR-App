package com.matrimony.rvrmatrimony.uicode.onboarding

import com.matrimony.rvrmatrimony.basemodule.BaseViewModel
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import javax.inject.Inject

class RegisterStep2ViewModel @Inject constructor(
    private val repository: OnBoardingRepository
) : BaseViewModel() {

    fun fetchReligionOptions(): List<SpinnerItemPojo> {
        return repository.getReligionSpinnerOptions()
    }

}