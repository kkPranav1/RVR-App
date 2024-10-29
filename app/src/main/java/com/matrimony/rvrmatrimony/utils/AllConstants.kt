package com.matrimony.rvrmatrimony.utils

object AllConstants {
    const val LOG_TAG_ACTIVITY: String = "ACTIVITY"
    const val SCREEN_DELAY: Long = 1000
    const val emptyString = ""
    const val LOGIN_USER_TYPE = "usertype"

    const val RC_SIGN_IN: Int = 9001
    const val CREATE_PROFILE_EXTRA = "CREATE_PROFILE_DTO"
    const val INCOMPLETE_FORM_MESSAGE = "Please Provide all the necessary Details"
    const val INVALID_EMAIL_PHONE = "Please Enter a Valid Email Id or a Phone Number"
    const val INVALID_PASSWORD_MESSAGE =
        "A Valid Password has at least 1 digit, at least 1 lower case letter, at least 1 upper case letter, at least 1 special character and no white spaces with minimum of 8 characters"

    enum class SPDataType { // Shared Preferences Data Types
        StringType, BooleanType, IntType, FloatType, LongType
    }

    enum class UserType(val s: String) {
        NOT_LOGGED_IN("no"),
        FREE_MOBILE_CLIENT("free"),
        PAID_CUSTOM_CLIENT("custom"),
        PAID_SILVER_CLIENT("silver"),
        PAID_GOLD_CLIENT("gold"),
        PAID_PLATINUM_CLIENT("platinum")
    }

    enum class ProfileCreationType(val profileFor: String) {
        MYSELF_MALE("Myself - Male"),
        MYSELF_FEMALE("Myself - Female"),
        SON("Son"),
        DAUGHTER("Daughter"),
        SISTER("Sister"),
        BROTHER("Brother"),
        FRIEND_MALE("Friend - Male"),
        FRIEND_FEMALE("Friend - Female");

        companion object {
            // Function to get enum constant from string (Reverse Lookup)
            fun fromString(profileFor: String): ProfileCreationType? {
                return entries.find { it.profileFor == profileFor }
            }
        }
    }

    enum class SexType(val rep: Int) {
        MALE(0),
        FEMALE(1),
        TRANSGENDER(-1);

        companion object {
            // Function to get enum constant from boolean (Reverse Lookup)
            fun fromInt(sexNo: Int): SexType? {
                return SexType.entries.find { it.rep == sexNo }
            }
        }
    }

    const val PASSWORD_RECOVERY_NOTE =
        "Password Recovery Link has been sent to the entered Email ID/Mobile Number."
    const val ENTER_VALID_EMAIL_OR_PHONE =
        "Please enter a valid email address or a valid phone number"
    const val PLEASE_FILL_CREDENTIALS = "Please enter all the required credentials"

    const val STRINGS_LIST_FACILITY = "STRINGS_LIST_STORE_FACILITY"
    const val NULL_BINDING_EXCEPTION_MESSAGE = "Failed to get the associated layout Binding"
}