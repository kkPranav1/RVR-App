package com.matrimony.rvrmatrimony.uicode.onboarding

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseDialog
import com.matrimony.rvrmatrimony.databinding.DailogForgotPasswordBinding
import com.matrimony.rvrmatrimony.databinding.DialogRegisterstep1Binding
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.CommonUtils
import com.matrimony.rvrmatrimony.utils.UIUtils

class ForgotPasswordDialog(
    private val activityCtx: Activity
) : BaseDialog<DailogForgotPasswordBinding>(activityCtx, R.layout.dailog_forgot_password) {

    private lateinit var binding: DailogForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = requireBinding()
        initUi()
    }

    private fun initUi() {
        binding.btnSendLink.setOnClickListener {
            forgotPasswordMockApiCall()
        }

        binding.btnCancel.setOnClickListener {
            dismiss()
        }
    }

    private fun forgotPasswordMockApiCall() {
        // Api Call to send user the Password Recovery Link
        // Toast on success
        UIUtils.shortToast(activityCtx, AllConstants.PASSWORD_RECOVERY_NOTE)
    }


    override fun handleDialogUiDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(activityCtx, R.color.white)
        val black = ContextCompat.getColor(activityCtx, R.color.black)
        val grey = ContextCompat.getColor(activityCtx, R.color.grey)
        binding.apply {
            dialogRoundedBg.setBackgroundResource(R.drawable.rounded_background)
            tvTitle.setTextColor(black)
            tvSubtitle.setTextColor(black)
            etUsernameMobile.setTextColor(black)
            etUsernameMobile.setBackgroundResource(R.drawable.form_et_1)
            etUsernameMobile.setHintTextColor(grey)
            btnSendLink.setBackgroundResource(R.drawable.ca_button_selector_1)
            btnSendLink.setTextColor(white)
            btnCancel.setTextColor(black)
        }
    }

}
