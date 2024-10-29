package com.matrimony.rvrmatrimony.uicode.myaccountandplans

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivitySubscriptionBinding

class SubscriptionActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener,
    View.OnClickListener {

    private lateinit var binding: ActivitySubscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@SubscriptionActivity,
            R.layout.activity_subscription
        )
        enableEdgeToEdge()
        initDayNightChangedListener(this@SubscriptionActivity)

    }

    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        val green = ContextCompat.getColor(this, R.color.green)
        val createAccountBtn = ContextCompat.getDrawable(this, R.drawable.ca_button_selector_1)

        binding.apply {
            main.setBackgroundColor(white)
            // Linear layouts
            mainLinearLayout.setBackgroundColor(white)
            silverLL.setBackgroundColor(white)
            silverPlanLL.setBackgroundColor(white)
            goldLL.setBackgroundColor(white)
            goldPlanLL.setBackgroundColor(white)
            platinumLL.setBackgroundColor(white)
            platinumPlanLL.setBackgroundColor(white)
            // Text views
            tvPricingHeader.setTextColor(black)
            tvPricingTitle.setTextColor(black)
            tvPricingSubtitle.setTextColor(black)
            silverTitle.setTextColor(black)
            silverSubTitle.setTextColor(black)
            silverPriceTv.setTextColor(black)
            silverPlansDetails.setTextColor(green)
            silverPlansDetails2.setTextColor(green)
            silverPlansDetails3.setTextColor(green)
            silverPlansDetails4.setTextColor(green)
            goldTitle.setTextColor(black)
            goldSubTitle.setTextColor(black)
            goldPriceTv.setTextColor(black)
            goldPlansDetails.setTextColor(green)
            goldPlansDetails1.setTextColor(green)
            goldPlansDetails2.setTextColor(green)
            goldPlansDetails3.setTextColor(green)
            platinumTitle.setTextColor(black)
            platinumSubTitle.setTextColor(black)
            platinumPriceTv.setTextColor(black)
            platinumPlansDetails.setTextColor(green)
            platinumPlansDetails1.setTextColor(green)
            platinumPlansDetails2.setTextColor(green)
            platinumPlansDetails3.setTextColor(green)

            // Buttons

            silverBtn.apply {
                setBackgroundDrawable(createAccountBtn)
                setTextColor(white)
            }
            goldBtn.apply {
                setBackgroundDrawable(createAccountBtn)
                setTextColor(white)
            }
            platinumBtn.apply {
                setBackgroundDrawable(createAccountBtn)
                setTextColor(white)
            }
        }
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when(it.id) {
                else -> {}
            }
        }
    }
}
