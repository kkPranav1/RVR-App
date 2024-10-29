package com.matrimony.rvrmatrimony.uicode.onboarding

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Paint
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityLoginAccountBinding
import com.matrimony.rvrmatrimony.MainDashBoardActivity
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.CommonUtils
import com.matrimony.rvrmatrimony.utils.UIUtils

class LoginAccountActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener,
    View.OnClickListener {

    private lateinit var binding: ActivityLoginAccountBinding
    private lateinit var viewModel: LoginAccountViewModel
    private lateinit var googleSignInClient: GoogleSignInClient
    private var registerDialogRef: RegisterStep1Dialog? = null
    private var forgotPasswordDialogRef: ForgotPasswordDialog? = null
    private var isPasswordVisible = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_account)
        enableEdgeToEdge()
        viewModel = getViewModel<LoginAccountViewModel>()
        initDayNightChangedListener(this@LoginAccountActivity)
        initUi()
        initGoogleSignOn()
        // showForgotPasswordDialog()

    }

    @SuppressLint("ClickableViewAccessibility")
    private fun showPasswordToggle() {
        val etPassword: EditText = binding.etPassword

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
                        etPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_on, 0)
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
        // Apply underline programmatically
        binding.tvForgotPassword.paintFlags =
            binding.tvForgotPassword.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvRegister.paintFlags = binding.tvRegister.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        // setting onClickListeners
        binding.apply {
            tvRegister.setOnClickListener(this@LoginAccountActivity::onClick)
            btnBack.setOnClickListener(this@LoginAccountActivity::onClick)
            btnLoginSignIn.setOnClickListener(this@LoginAccountActivity::onClick)
            btnGoogleSignIn.setOnClickListener(this@LoginAccountActivity::onClick)
            tvForgotPassword.setOnClickListener(this@LoginAccountActivity::onClick)
        }
        // Show Password based on eye icon click
        showPasswordToggle()
    }

    private fun showForgotPasswordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dailog_forgot_password, null)

        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        val sendLinkButton: Button = dialogView.findViewById(R.id.btnSendLink)
        val cancelButton: Button = dialogView.findViewById(R.id.btnCancel)

        sendLinkButton.setOnClickListener {
            // Handle send link functionality here
            Toast.makeText(this, "Link sent", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.tvRegister -> {
                    registerDialogRef?.show() ?: run {
                        registerDialogRef = RegisterStep1Dialog(
                            this@LoginAccountActivity,
                            viewModel.fetchProfileCreationOptions()
                        )
                        registerDialogRef!!.show()
                    }
                }

                R.id.btnBack -> {
                    finish(); }

                R.id.btnGoogleSignIn -> {
                    startActivityForResult(
                        googleSignInClient.signInIntent,
                        AllConstants.RC_SIGN_IN
                    ); }

                R.id.tvForgotPassword -> {
                    // Forgot Password Dialog
                    forgotPasswordDialogRef?.show() ?: run {
                        forgotPasswordDialogRef = ForgotPasswordDialog(this@LoginAccountActivity)
                        forgotPasswordDialogRef!!.show()
                    }
                }

                R.id.btnLoginSignIn -> {
                    // Original Sign-in (Login using username and password)
                    validateAndSignIn()
                }
            }
        }
    }

    private fun validateAndSignIn() {
        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        if (username.isNotEmpty() && password.isNotEmpty()) {
            if (!(CommonUtils.isValidEmailId(username) || CommonUtils.isValidPhoneNumber(username))) {
                mockAuthenticationApiCall()
            } else {
                UIUtils.shortToast(
                    this@LoginAccountActivity,
                    AllConstants.ENTER_VALID_EMAIL_OR_PHONE
                )
            }
        } else {
            UIUtils.shortToast(
                this@LoginAccountActivity,
                AllConstants.PLEASE_FILL_CREDENTIALS
            )
        }

    }

    private fun mockAuthenticationApiCall() {
        // place for an authentication request
        // on Success navigate to app main dashboard
        startActivity(
            Intent(
                this@LoginAccountActivity,
                MainDashBoardActivity::class.java
            )
        )
    }

    private fun initGoogleSignOn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // Replace with your actual client ID
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this@LoginAccountActivity, gso)
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AllConstants.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "Google sign-in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                } else {
                    Log.w("GoogleSignIn", "signInWithCredential:failure ", task.exception)
                }
            }
    }


    override fun handleDayNightChange(isDay: Boolean) {
        registerDialogRef?.let { if (it.isShowing) it.handleDialogUiDayNightChange(isDay); }
        forgotPasswordDialogRef?.let { if (it.isShowing) it.handleDialogUiDayNightChange(isDay); }
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        val green = ContextCompat.getColor(this, R.color.green)
        val othGreen = ContextCompat.getColor(this, R.color.main_green)
        val tcGrey = ContextCompat.getColor(this, R.color.grey)
        val createAccountBtn = ContextCompat.getDrawable(this, R.drawable.ca_button_selector_1)
        val roundBg = ContextCompat.getDrawable(this, R.drawable.rounded_background)
        binding.apply {
            mainLinearLayout.setBackgroundColor(white)
            btnBack.apply {
                foregroundTintList = ColorStateList(Array(1) { intArrayOf() }, intArrayOf(black))
                foregroundTintMode = PorterDuff.Mode.SRC_IN
            }
            title.setTextColor(black)
            tvUsernameLabel.setTextColor(green)
            etUsername.apply {
                setTextColor(othGreen)
                setBackgroundResource(R.drawable.form_et_1)
                setHintTextColor(tcGrey)
            }
            tvPasswordLabel.setTextColor(green)
            etPassword.apply {
                setTextColor(othGreen)
                setBackgroundResource(R.drawable.form_et_1)
                setHintTextColor(tcGrey)
            }
            tvForgotPassword.setTextColor(green)
            btnLoginSignIn.apply {
                setBackgroundDrawable(createAccountBtn)
                setTextColor(white)
            }
            signinTV.setTextColor(green)
            btnGoogleSignIn.setTextColor(green)
            verifiedMail.setTextColor(tcGrey)
            tvRegister.setTextColor(green)
            btnGoogleSignIn.apply {
                setBackgroundDrawable(roundBg)
                backgroundTintList = ColorStateList(Array(1) { intArrayOf() }, intArrayOf(white))
                backgroundTintMode = PorterDuff.Mode.SRC_IN
                setTextColor(green)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        registerDialogRef = null
        forgotPasswordDialogRef = null
    }


}

