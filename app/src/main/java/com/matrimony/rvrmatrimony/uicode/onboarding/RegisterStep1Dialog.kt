package com.matrimony.rvrmatrimony.uicode.onboarding

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseDialog
import com.matrimony.rvrmatrimony.databinding.DialogRegisterstep1Binding
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import com.matrimony.rvrmatrimony.utils.UIUtils

class RegisterStep1Dialog(
    private val ctx: Activity,
    private val createOptions: List<SpinnerItemPojo>
) : BaseDialog<DialogRegisterstep1Binding>(ctx, R.layout.dialog_registerstep1, 0.95, -1.0) {

    private var binding: DialogRegisterstep1Binding? = null
    private var profileSexAndInterestSet: Boolean = false
    private val createNewProfileDto = ProfileCreatePojo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = requireBinding()
        binding?.let { bd ->
            bd.createForSpinnerTv.setOnClickListener {
                showSpinnerDropdown(bd.createForSpinnerTv, createOptions, 4) { selectedItemText ->
                    profileSexAndInterestSet = true
                    bd.createForSpinnerTv.text = selectedItemText
                    when (selectedItemText) {
                        "Myself - Male", "Friend - Male", "Brother", "Son" -> {
                            UIUtils.setTextWithAnimation(bd.tvInterestSwitch, "Women")
                            bd.radioMale.isChecked = true
                            bd.radioFemale.isChecked = false
                            // Strictly NO LGBTQ
                            createNewProfileDto.sexOfPerson = AllConstants.SexType.MALE
                            createNewProfileDto.sexPreference = AllConstants.SexType.FEMALE
                        }

                        else -> {
                            UIUtils.setTextWithAnimation(bd.tvInterestSwitch, "Men")
                            bd.radioMale.isChecked = false
                            bd.radioFemale.isChecked = true
                            // Strictly NO LGBTQ
                            createNewProfileDto.sexOfPerson = AllConstants.SexType.FEMALE
                            createNewProfileDto.sexPreference = AllConstants.SexType.MALE
                        }
                    }

                }
            }
            bd.proceedButton.setOnClickListener {
                if (profileSexAndInterestSet) {
                    if (validateAgesEntry()) {
                        // UIUtils.shortToast(ctx, "OK OK")
                        // Navigation To Step2
                        val intent = Intent(ctx, RegisterStep2Activity::class.java)
                        createNewProfileDto.profileForPerson =
                            AllConstants.ProfileCreationType.fromString(
                                bd.createForSpinnerTv.text.toString().trim()
                            )
                                ?: if (bd.radioMale.isChecked) AllConstants.ProfileCreationType.MYSELF_MALE else AllConstants.ProfileCreationType.MYSELF_FEMALE
                        createNewProfileDto.preferenceAgeFrom = bd.ageFromET.text.toString().toInt()
                        createNewProfileDto.preferenceAgeTo = bd.ageToET.text.toString().toInt()
                        intent.putExtra(AllConstants.CREATE_PROFILE_EXTRA, createNewProfileDto)
                        ctx.startActivity(intent)
                        dismiss()
                    }
                } else {
                    UIUtils.shortToast(
                        ctx,
                        "Please tell us for whom do we create Profile for properly"
                    )
                }
            }
        }

    }

    private fun validateAgesEntry(): Boolean {
        binding?.let { bd ->
            val from = bd.ageFromET.text.toString().trim()
            val to = bd.ageToET.text.toString().trim()
            if (to == "" || from == "") {
                UIUtils.shortToast(ctx, "Please enter valid Age Preferences")
                return false

            } else if (to.toInt() > from.toInt()) {
                return if (from.toInt() < 18 || to.toInt() > 60) false.also {
                    UIUtils.shortToast(
                        ctx,
                        "Invalid Age Preferences"
                    )
                } else true
            } else {
                UIUtils.shortToast(ctx, "Invalid Age Preferences")
                return false
            }
        }
        return false
    }

    override fun handleDialogUiDayNightChange(isDay: Boolean) {
        binding?.let { bd ->
            val white = ContextCompat.getColor(ctx, R.color.white)
            val black = ContextCompat.getColor(ctx, R.color.black)
            val orange = ContextCompat.getColor(ctx, R.color.yellow)
            val green = ContextCompat.getColor(ctx, R.color.green)
            val orangeWhite = ContextCompat.getColor(ctx, R.color.orange_white)
            val grey = ContextCompat.getColor(ctx, R.color.grey)
            val orangeBtn = ContextCompat.getDrawable(ctx, R.drawable.ca_button_selector_1)
            val formEt1 = ContextCompat.getDrawable(ctx, R.drawable.form_et_1)
            val lightModeDropDown = ContextCompat.getDrawable(ctx, R.drawable.ic_dropdown_s)
            val darkModeDropDown = ContextCompat.getDrawable(ctx, R.drawable.ic_dropdown_s_night)
            bd.apply {
                step1dialogCL.setBackgroundResource(R.drawable.rounded_background)
                whatTV.setTextColor(black)
                tvCreateFor.setTextColor(orange)
                createForSpinnerTv.setBackgroundDrawable(formEt1)
                createForSpinnerTv.setTextColor(black)
                createForSpinnerTv.setCompoundDrawablesWithIntrinsicBounds(
                    null,
                    null,
                    if (isDay) lightModeDropDown else darkModeDropDown,
                    null
                )
                tvSex.setTextColor(orange)
                radioMale.setTextColor(green)
                radioFemale.setTextColor(green)
                tvImInterestedIn.setTextColor(black)
                tvInterestSwitch.setTextColor(black)
                tvInterestSwitch.setBackgroundColor(orangeWhite)
                tvAged.setTextColor(black)
                tvAgeTo.setTextColor(black)
                ageFromET.apply {
                    setBackgroundDrawable(formEt1)
                    setTextColor(black)
                    setHintTextColor(grey)
                }
                ageToET.apply {
                    setBackgroundDrawable(formEt1)
                    setTextColor(black)
                    setHintTextColor(grey)
                }
                proceedButton.setBackgroundDrawable(orangeBtn)
            }
        }
    }

}