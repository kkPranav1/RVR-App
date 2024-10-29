package com.matrimony.rvrmatrimony.uicode.onboarding

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.data.remote.ApiResponse
import com.matrimony.rvrmatrimony.databinding.ActivitySplashScreenBinding
import com.matrimony.rvrmatrimony.utils.UIUtils
import kotlinx.coroutines.launch

class TestScreenActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener,
    View.OnClickListener {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var viewModel: TestScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)
        viewModel = getViewModel<TestScreenViewModel>()

        // Collect car data
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.carData.collect { response ->
                    when (response) {
                        is ApiResponse.Success -> {
                            // Handle success
                            val car = response.data.Car
                            // binding.splashLogoIV.visibility = View.GONE
                            binding.splashLogoIV.apply {
                                clearAnimation()
                                visibility = View.INVISIBLE
                            }
                            UIUtils.longToast(this@TestScreenActivity, car.toString())
                        }

                        is ApiResponse.Error -> {
                            // Handle error
                            UIUtils.shortToast(this@TestScreenActivity, response.message)
                        }

                        is ApiResponse.Loading -> {
                            // Show loading state
                            val zoomOutAnimation =
                                AnimationUtils.loadAnimation(
                                    this@TestScreenActivity,
                                    R.anim.splash_first
                                )
                            binding.splashLogoIV.startAnimation(zoomOutAnimation)
                        }
                    }
                }
            }
        }

        viewModel.getCarById()
    }

    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        binding.apply {
            mainCL.setBackgroundColor(white)
        }
    }

    override fun onClick(p0: View?) {

    }


}