package com.matrimony.rvrmatrimony.uicode.onboarding

import android.os.Parcelable
import com.matrimony.rvrmatrimony.utils.AllConstants
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileCreatePojo(
    var profileForPerson: AllConstants.ProfileCreationType = AllConstants.ProfileCreationType.MYSELF_MALE,
    var sexOfPerson: AllConstants.SexType = AllConstants.SexType.MALE,
    var firstNameOfPerson: String = "",
    var lastNameOfPerson: String = "",
    var personReligion: String = "",
    var personEnteredEmailOrPhoneForUsername: String = "rvrsupport@gmail.com",
    var isPersonEnteredPhoneForUsername: Boolean = false,
    var passwordSet: String = "",
    var sexPreference: AllConstants.SexType = AllConstants.SexType.FEMALE,
    var preferenceAgeFrom: Int = 18,
    var preferenceAgeTo: Int = 55,
) : Parcelable {
    init {
        // Strictly NO LGBTQ, Fuck Western Media
        if (sexOfPerson == AllConstants.SexType.MALE) {
            sexPreference = AllConstants.SexType.FEMALE
        } else if (sexOfPerson == AllConstants.SexType.FEMALE) {
            sexPreference = AllConstants.SexType.MALE
        }

        if(preferenceAgeFrom < 18)
            preferenceAgeFrom = 18
        if(preferenceAgeTo > 55)
            preferenceAgeTo = 55

    }
}
