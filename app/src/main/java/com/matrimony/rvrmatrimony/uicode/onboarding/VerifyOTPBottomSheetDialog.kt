package com.matrimony.rvrmatrimony.uicode.onboarding

import android.content.Context
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.chaos.view.PinView
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseBottomSheetDialog
import com.matrimony.rvrmatrimony.databinding.BottomsheetOtpverifyBinding
import com.matrimony.rvrmatrimony.utils.UIUtils

class VerifyOTPBottomSheetDialog(
    private val passedProfileCreatePojo: ProfileCreatePojo,
    private val parentActivity: Context, // RegisterStep2Activity
    private val parentVM: RegisterStep2ViewModel,
    private val onVerifiedSuccess: () -> Unit
) : BaseBottomSheetDialog<BottomsheetOtpverifyBinding>() {

    companion object {
        val BSTAG: String = VerifyOTPBottomSheetDialog::class.java.simpleName
    }

    override fun getLayoutId(): Int = R.layout.bottomsheet_otpverify

    private lateinit var binding: BottomsheetOtpverifyBinding
    private var enteredOtp: String = "1111"
    private val correctOtp: String = "1234"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // UI Code
        binding = getDialogBinding()

        binding.resendOtpTimerTextView.paintFlags =
            binding.resendOtpTimerTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        configureActivePinView(binding.otpMainPinView, 4u, validateButton = binding.validateButton)
        binding.validateButton.setOnClickListener {
            if (enteredOtp == correctOtp) {
                resetErrorStateInPinView(binding.otpMainPinView, binding.invalidOTPMsgTV)
                UIUtils.shortToast(requireContext(), "Registration Successful")
                onVerifiedSuccess.invoke()
            } else {
                setErrorStateInPinView(binding.otpMainPinView, binding.invalidOTPMsgTV)
            }
        }

    }

    private fun resetErrorStateInPinView(otpPinViewRef: PinView, errorTextView: TextView) {
        if (errorTextView.visibility == View.VISIBLE) {
            // otpPinViewRef.setBackgroundResource(R.drawable.custom_edittext)
            errorTextView.visibility = View.GONE
        }
    }

    private fun setErrorStateInPinView(otpPinViewRef: PinView, errorTextView: TextView) {
        if (errorTextView.visibility == View.GONE) {
            // otpPinViewRef.setBackgroundResource(R.drawable.custom_edittext_error)
            errorTextView.visibility = View.VISIBLE
        }
    }

    private fun configureActivePinView(
        pinViewRef: PinView,
        boxCount: UInt,
        validateButton: Button
    ) {
        pinViewRef.requestFocus()
        if (pinViewRef.hasFocus()) {
            if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                pinViewRef.windowInsetsController?.show(WindowInsets.Type.ime())
            } else {
                val inputMethodManager =
                    parentActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.toggleSoftInput(
                    InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }
        pinViewRef.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Activate validate Otp Button on PinView entry
                validateButton.isEnabled = s.toString().length.toUInt() == boxCount
                enteredOtp =
                    if (validateButton.isEnabled) pinViewRef.text.toString().trim() else ""
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun handleDayNightChange(isDay: Boolean) {

    }

}