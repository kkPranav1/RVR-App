package com.matrimony.rvrmatrimony.uicode.onboarding

import com.matrimony.rvrmatrimony.data.remote.ApiResponse
import com.matrimony.rvrmatrimony.data.remote.CarApiService
import com.matrimony.rvrmatrimony.data.remote.CarResponse
import com.matrimony.rvrmatrimony.data.room.TermConditionEntity
import com.matrimony.rvrmatrimony.data.room.TermsConditionRoomDao
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OnBoardingRepository @Inject constructor(
    private val termsConditionDao: TermsConditionRoomDao,
    private val carApiService: CarApiService
) {

    // I haven't removed this function in case of a future requirement for image icons
    fun getMatrimonyAccountCreationSpinnerOptionsD(): List<SpinnerItemPojo> {
        return listOf(
            SpinnerItemPojo("Myself - Male", 0),
            SpinnerItemPojo("Myself - Female", 0),
            SpinnerItemPojo("Son", 0),
            SpinnerItemPojo("Daughter", 0),
            SpinnerItemPojo("Sister", 0),
            SpinnerItemPojo("Brother", 0),
            SpinnerItemPojo("Friend - Male", 0),
            SpinnerItemPojo("Friend - Female", 0)
        )
    }

    fun getMatrimonyAccountCreationSpinnerOptions(): List<SpinnerItemPojo> {
        return AllConstants.ProfileCreationType.entries.map {
            SpinnerItemPojo(it.profileFor, 0)
        }
    }

    fun getReligionSpinnerOptions(): List<SpinnerItemPojo> {
        return listOf(
            SpinnerItemPojo("Hindu", 0),
            SpinnerItemPojo("Muslim", 0),
            SpinnerItemPojo("Christian", 0),
            SpinnerItemPojo("Sikh", 0),
            SpinnerItemPojo("Jain", 0),
            SpinnerItemPojo("Parsi", 0),
            SpinnerItemPojo("Buddhist", 0)
        )
    }

    suspend fun getTermsAndConditionsList2(): List<TermConditionEntity> {
        return termsConditionDao.getAllTermsConditions()
    }

    fun getTermsAndConditionsList(): List<TermsDataModel> {
        return listOf(
            TermsDataModel(
                "1. Introduction",
                "Welcome to RVRLuxury Matrimony. By accessing or using our services, you agree to be bound by these Terms and Conditions. Please read them carefully."
            ),
            TermsDataModel(
                "2. Service Description",
                "RVRLuxury Matrimony provides a premium matchmaking service designed to facilitate meaningful connections for individuals seeking serious relationships."
            ),
            TermsDataModel(
                "3. User Eligibility",
                "Users must be at least 18 years old to use our services.\n" +
                        "Users must cooperate by providing accurate and complete information during registration and throughout their use of the service."
            ),

            TermsDataModel(
                "4. Registration and Account Security",
                "To access our services, users must register and create an account. You are solely responsible for maintaining the confidentiality of your login credentials and any activities conducted under your account. Should you detect any unauthorized use, it is imperative to notify us immediately."
            ),
            TermsDataModel(
                "5. User Responsibilities",
                "Users are expected to use our services in a respectful, lawful manner, adhering to all applicable laws and regulations. Misrepresentation of identity or intent is strictly prohibited. Users must also uphold the privacy and confidentiality of other members on the platform."
            ),
            TermsDataModel(
                "6. Service Fees",
                "Information regarding subscription fees or one-time payments for accessing premium features will be clearly provided on our website. Unless otherwise explicitly stated, all fees are non-refundable."
            ),
            TermsDataModel(
                "7. Content and Communication",
                "Users are responsible for all content they post, including profiles, messages, and any other form of communication. We reserve the right to monitor and, if necessary, remove any content that violates these terms or is deemed inappropriate at our discretion."
            ),
            TermsDataModel(
                "8. Privacy Policy",
                "Your privacy is of utmost importance to us. Please refer to our Privacy Policy for details on how we collect, use, and safeguard your personal information. By using our services, you consent to the practices outlined in the Privacy Policy."
            ),
            TermsDataModel(
                "9. Termination of Service",
                "We reserve the right to suspend or terminate user accounts at our discretion, including but not limited to cases of suspected fraud, misuse, or violations of these Terms and Conditions."
            ),
            TermsDataModel(
                "10. Limitation of Liability",
                "RVRLuxury Matrimony shall not be held responsible for any direct, indirect, incidental, or consequential damages resulting from the use or inability to use our services. We do not warrant the accuracy or reliability of user profiles nor do we guarantee the outcome of any matches facilitated through our platform."
            ),
            TermsDataModel(
                "11. Amendments to Terms",
                "We may periodically update these Terms and Conditions. Users will be notified of any material changes, and continued use of the service following such updates will signify acceptance of the revised terms."
            ),
            TermsDataModel(
                "12. Governing Law",
                "These Terms and Conditions are governed by the laws of Andhra Pradesh, India. Any disputes arising out of these terms shall fall under the exclusive jurisdiction of the courts of Andhra Pradesh, India."
            ),
            TermsDataModel(
                "13. Contact Information",
                "For any inquiries or concerns regarding these Terms and Conditions, please reach out to us at [Contact Information]."
            )
        )
    }

    suspend fun getCarById(id: Int): Flow<ApiResponse<CarResponse>> = flow {
        emit(ApiResponse.Loading())
        try {
            val response = carApiService.getCarById(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ApiResponse.Success(it))
                } ?: emit(ApiResponse.Error("Response body is null"))
            } else {
                emit(ApiResponse.Error("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "An error occurred"))
        }
    }

}