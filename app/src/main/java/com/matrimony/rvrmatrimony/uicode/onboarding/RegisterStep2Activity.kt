package com.matrimony.rvrmatrimony.uicode.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityRegisterStep2Binding
import com.matrimony.rvrmatrimony.MainDashBoardActivity
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.CommonUtils
import com.matrimony.rvrmatrimony.utils.UIUtils

class RegisterStep2Activity : BaseActivity(), BaseActivity.OnDayNightChangedListener {

    private lateinit var binding: ActivityRegisterStep2Binding
    private lateinit var viewModel: RegisterStep2ViewModel
    private var passedCreateProfileDTO: ProfileCreatePojo = ProfileCreatePojo()
    private var religionPicked: Boolean = false
    private var isPasswordVisible = false


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@RegisterStep2Activity,
            R.layout.activity_register_step2
        )
        // enableEdgeToEdge()
        initDayNightChangedListener(this@RegisterStep2Activity)
        viewModel = getViewModel<RegisterStep2ViewModel>()

        val intent = intent
        val passedDTO: ProfileCreatePojo? =
            intent.getParcelableExtra(AllConstants.CREATE_PROFILE_EXTRA)
        if (passedDTO == null) {
            UIUtils.shortToast(this@RegisterStep2Activity, "Technical Issue, Please Try again")
            this@RegisterStep2Activity.finish()
        }
        passedCreateProfileDTO = passedDTO!!
        initUi()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showPasswordToggle() {

        val etPassword: EditText = binding.passwordET

        etPassword.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[2].bounds.width())) {
                    if (isPasswordVisible) {
                        // Hide password
                        etPassword.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_off,
                            0
                        )
                    } else {
                        // Show password
                        etPassword.inputType = InputType.TYPE_CLASS_TEXT
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_eye_on,
                            0
                        )
                    }
                    isPasswordVisible = !isPasswordVisible
                    etPassword.setSelection(etPassword.text.length)
                    return@setOnTouchListener true
                }
            }
            false
        }

    }

    private fun initUi() {
        // Show Password based on eye icon click
        showPasswordToggle()
        // Setting up Pick Religion Spinner DropDown
        binding.religionSpinnerTv.setOnClickListener {
            showSpinnerDropdown(
                binding.religionSpinnerTv,
                viewModel.fetchReligionOptions(),
                3
            ) { selectedtxt ->
                religionPicked = true
                binding.religionSpinnerTv.text = selectedtxt
                passedCreateProfileDTO.personReligion = selectedtxt
            }
        }
        binding.startBrowsingBtn.setOnClickListener {
            if (validateFormAndSendRequest()) {
                val otpBottomSheet = VerifyOTPBottomSheetDialog(
                    passedCreateProfileDTO,
                    this@RegisterStep2Activity,
                    viewModel
                ) {
                    startActivity(
                        Intent(
                            this@RegisterStep2Activity,
                            MainDashBoardActivity::class.java
                        )
                    )
                    finish()
                }
                otpBottomSheet.show(supportFragmentManager, "OTP Verify BottomSheet")
            }
        }
    }

    private fun validateFormAndSendRequest(): Boolean {
        val fn = binding.firstNameET.text.toString().trim()
        val ln = binding.lastNameET.text.toString().trim()
        val emailOrMobileText = binding.mobileOrEmailET.text.toString().trim()
        val passwordText = binding.passwordET.text.toString().trim()

        if (fn == "" || ln == "" || emailOrMobileText == "" || passwordText == "" || !religionPicked) {
            UIUtils.shortToast(this, AllConstants.INCOMPLETE_FORM_MESSAGE)
            return false
        }
        val isEmail = CommonUtils.isValidEmailId(emailOrMobileText)
        val isMobile = CommonUtils.isValidPhoneNumber(emailOrMobileText)
        if (!isEmail && !isMobile) {
            UIUtils.shortToast(this, AllConstants.INVALID_EMAIL_PHONE)
            return false
        }
        passedCreateProfileDTO.personEnteredEmailOrPhoneForUsername = emailOrMobileText
        passedCreateProfileDTO.isPersonEnteredPhoneForUsername = !isEmail
        val enteredPassword = binding.passwordET.text.toString().trim()
        if (!CommonUtils.isValidPasswords(enteredPassword)) {
            UIUtils.shortToast(this, AllConstants.INVALID_PASSWORD_MESSAGE)
            return false
        }
        return mockUserCreationRemoteApiCall()
    }

    private fun mockUserCreationRemoteApiCall(): Boolean {
        // API CALL FOR USER CREATION
        return true
    }


    override fun onBackPressed() {
        // Create a confirmation dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Are you sure?")
        builder.setMessage("Do you really want to go back?")
        builder.setPositiveButton("OK") { _, _ ->
            // If OK is clicked, call super to go back
            super.onBackPressed()
        }
        builder.setNegativeButton("Cancel") { dialog, which ->
            // If Cancel is clicked, just dismiss the dialog
            dialog.dismiss()
        }
        // Show the dialog
        val dialog = builder.create()
        dialog.show()
    }


    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        val green = ContextCompat.getColor(this, R.color.main_green)
        binding.apply {
            rootLL.setBackgroundColor(white)
            pageTitleTv.setTextColor(black)
            tvUsername.setTextColor(green)
            firstNameET.setTextAppearance(
                this@RegisterStep2Activity,
                R.style.CustomTextInputEditText1
            )
            lastNameET.setTextAppearance(
                this@RegisterStep2Activity,
                R.style.CustomTextInputEditText1
            )
            tvReligionlabel.setTextColor(green)
            religionSpinnerTv.setTextColor(green)
            religionSpinnerTv.setBackgroundResource(R.drawable.form_et_1)
            tvUserMobileEmailLabel.setTextColor(green)
            mobileOrEmailET.setTextAppearance(
                this@RegisterStep2Activity,
                R.style.CustomTextInputEditText1
            )
            tvUserPasswordLabel.setTextColor(green)
            passwordET.setTextAppearance(
                this@RegisterStep2Activity,
                R.style.CustomTextInputEditText1
            )
//            startBrowsingBtnLL.setBackgroundResource(R.drawable.button_spl_bg)
//            startBrowsingBtn.setTextColor(black)
//            arrowIcon.apply {
//                foregroundTintList = ColorStateList(Array(1) { intArrayOf() }, intArrayOf(black))
//                foregroundTintMode = PorterDuff.Mode.SRC_IN
//            }
        }
    }
}
