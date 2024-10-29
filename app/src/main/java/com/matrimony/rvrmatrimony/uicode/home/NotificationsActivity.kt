package com.matrimony.rvrmatrimony.uicode.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationsActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener {

    private lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_notifications)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        binding = DataBindingUtil.setContentView(
            this@NotificationsActivity,
            R.layout.activity_notifications
        )
        enableEdgeToEdge()
        applyComposeUi()
    }

    private fun applyComposeUi() {
        binding.notificationsComposeView.setContent {
            SimpleComposeUi(isDarkMode = isSystemInDarkTheme())
        }
    }

    @Composable
    fun SimpleComposeUi(modifier: Modifier = Modifier, isDarkMode: Boolean = false) {
        // Text(text = "Hello Compose", modifier = modifier.padding(16.dp), color = ContextCompat.getColor(this, R.color.green)) -> Gives error
        val textColor = if (isDarkMode) Color.White else Color.Green
        val backgroundColor = if (isDarkMode) Color.DarkGray else Color.LightGray
        Box(
            modifier = modifier.padding(16.dp).fillMaxWidth().background(backgroundColor)
        ) {
            Text(
                text = "Hello Compose",
                modifier = modifier.padding(16.dp).align(Alignment.Center),
                color = textColor,
                textAlign = TextAlign.Center
            )
        }   
    }

    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        val liteYellow = ContextCompat.getColor(this, R.color.lite_yellow)
        val green = ContextCompat.getColor(this, R.color.green)
        val themeGreen = ContextCompat.getColor(this, R.color.main_green)
        binding.apply {
            mainNotificationsCoordinatorLayout.setBackgroundColor(white)
            notificationsCollapsingToolbarLayout.apply {
                setCollapsedTitleTextColor(liteYellow)
                setExpandedTitleColor(white)
                setContentScrimColor(green)
                setBackgroundColor(themeGreen)
            }
        }
        applyComposeUi()
    }
}