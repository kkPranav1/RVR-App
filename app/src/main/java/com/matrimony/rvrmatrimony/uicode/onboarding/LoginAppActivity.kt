package com.matrimony.rvrmatrimony.uicode.onboarding

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityLoginAppBinding
import com.matrimony.rvrmatrimony.MainDashBoardActivity
import com.matrimony.rvrmatrimony.utils.AllConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginAppActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener,
    View.OnClickListener {

    private lateinit var binding: ActivityLoginAppBinding
    private lateinit var viewModel: LoginAppViewModel
    private var registerDialogRef: RegisterStep1Dialog? = null
    private var termsConditionsDialogRef: TermsConditionsDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this@LoginAppActivity, R.layout.activity_login_app)
        enableEdgeToEdge()
        initDayNightChangedListener(this@LoginAppActivity)
        viewModel = getViewModel<LoginAppViewModel>()
        initUi()
    }

    private fun initUi() {
        // splash screen animation before displaying main content
        val zoomOutAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_first)
        binding.splashLogoIV.startAnimation(zoomOutAnimation)
        // displaying main content after delay
        Handler(Looper.getMainLooper()).postDelayed({
            if (viewModel.isUserLoggedIn()) {
                // Navigation to MainDashBoardActivity if user is already logged in
                startActivity(Intent(this@LoginAppActivity, MainDashBoardActivity::class.java))
                finish()
            } else {
                binding.splashLogoIV.visibility = View.GONE
                binding.contentLayout.mainContentCL.visibility = View.VISIBLE
            }
        }, AllConstants.SCREEN_DELAY)
        // Setting clicklisteners to the buttons
        binding.contentLayout.loginButton.setOnClickListener(this::onClick)
        binding.contentLayout.createAccButton.setOnClickListener(this::onClick)
        // setting underline to the terms and conditions string
//        val tctext = getString(R.string.i_agree_to_the_tc)
//        val spannableString = SpannableString(tctext)
//        spannableString.setSpan(UnderlineSpan(), 0, tctext.length, 0)
//        binding.contentLayout.tcInfoTV.text = spannableString
        binding.contentLayout.tcInfoTV.apply {
            paintFlags = this.paintFlags or Paint.UNDERLINE_TEXT_FLAG
            setOnClickListener(this@LoginAppActivity::onClick)
        }
        binding.contentLayout.titleLogoIV.setOnClickListener(this::onClick)
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.titleLogoIV -> {
                    // Testing Purpose
                    // startActivity(Intent(this@LoginAppActivity, NotificationsActivity::class.java))
                    // startActivity(Intent(this@LoginAppActivity, SubscriptionActivity::class.java))
                    // startActivity(Intent(this@LoginAppActivity, MainDashBoardActivity::class.java))
                    startActivity(Intent(this@LoginAppActivity, TestScreenActivity::class.java))
                }

                R.id.loginButton -> {
                    // Open Login Activity
                    startActivity(Intent(this@LoginAppActivity, LoginAccountActivity::class.java))
                }

                R.id.createAccButton -> {
                    // Open Register User Dialog
                    registerDialogRef?.show() ?: run {
                        registerDialogRef = RegisterStep1Dialog(this@LoginAppActivity, viewModel.fetchProfileCreationOptions())
                        registerDialogRef!!.show()
                    }
                }

                R.id.tcInfoTV -> {
                    // TestDialog(this@LoginAppActivity).show(supportFragmentManager, "TestDialog")
                    termsConditionsDialogRef?.show(supportFragmentManager, "TCDialog") ?: run {
                        termsConditionsDialogRef = TermsConditionsDialog(this@LoginAppActivity, viewModel.fetchTermsAndConditions())
                        termsConditionsDialogRef!!.show(supportFragmentManager, "TCDialog")
                    }
                }
            }
        }
    }

    override fun handleDayNightChange(isDay: Boolean) {
        registerDialogRef?.handleDialogUiDayNightChange(isDay)
        termsConditionsDialogRef?.handleDayNightChange(isDay)
        // termsConditionsDialogRef?.dismiss()
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        val tcGrey = ContextCompat.getColor(this, R.color.grey)
        val gradientbg = ContextCompat.getDrawable(this, R.drawable.gradient_bg_1)
        val createAccountBtn = ContextCompat.getDrawable(this, R.drawable.ca_button_selector_1)
        val loginBtn = ContextCompat.getDrawable(this, R.drawable.login_button_selector)
        binding.apply {
            main.setBackgroundColor(white) // if (isDay) white else black
            contentLayout.apply {
                // mainContentCL.background = gradientbg
                mainContentCL.setBackgroundColor(white)
                tagTitleTV.setTextColor(black)
                regTitleTV.setTextColor(black)
                createAccButton.setTextColor(black)
                createAccButton.setBackgroundDrawable(createAccountBtn)
                tcInfoTV.setTextColor(tcGrey)
                memTitleTV.setTextColor(black)
                loginButton.setTextColor(white)
                loginButton.setBackgroundDrawable(loginBtn)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        registerDialogRef = null
        termsConditionsDialogRef = null
    }
}