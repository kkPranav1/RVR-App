package com.matrimony.rvrmatrimony.uicode.paymentgateways

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityGuidePaymentBinding
import com.matrimony.rvrmatrimony.databinding.ActivitySplashScreenBinding
import com.razorpay.Checkout

class GuidePaymentActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener,
    View.OnClickListener {

    private lateinit var binding: ActivityGuidePaymentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this@GuidePaymentActivity, R.layout.activity_guide_payment)

        /**
         * Payment Integration Pending (Not Implemented Fully)
         * Planned to Integrate the RazorPay SDK
         *
         * For more Details and to successfully implement the Razorpay Payment Gateway,
         * checkout the links below:
         *
         * To understand how payment gateways work
         * https://razorpay.com/docs/payments/payment-gateway/how-it-works/
         *
         * To understand how to implement the Razorpay Payment Gateway
         * https://razorpay.com/docs/payments/payment-gateway/android-integration/standard/
         *
         * */

        /*
        * To ensure faster loading of the Checkout form,
        * call this method as early as possible in your checkout flow
        * */
        Checkout.preload(applicationContext)
        val co = Checkout()
        // apart from setting it in AndroidManifest.xml, keyId can also be set
        // programmatically during runtime
        co.setKeyID("rzp_live_XXXXXXXXXXXXXX")



    }

    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        binding.apply {
            main.setBackgroundColor(white)
        }
    }

    override fun onClick(p0: View?) {

    }
}