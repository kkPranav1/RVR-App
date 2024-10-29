package com.matrimony.rvrmatrimony.uicode.onboarding

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityTermsAndConditionsBinding
import com.matrimony.rvrmatrimony.utils.AllConstants

class TermsConditionsActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener {

    private lateinit var binding: ActivityTermsAndConditionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@TermsConditionsActivity,
            R.layout.activity_terms_and_conditions
        )
        initDayNightChangedListener(this@TermsConditionsActivity)
        binding.tvTermsConditions.text = getTermsAndConditionsText()
    }

    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        binding.apply {
            tcLinearLayout.setBackgroundColor(white)
            tvTitle.setTextColor(black)
            tvTermsConditions.setTextColor(black)
        }
    }

    private fun getTermsAndConditionsText(): String {
        return ""
    }


}