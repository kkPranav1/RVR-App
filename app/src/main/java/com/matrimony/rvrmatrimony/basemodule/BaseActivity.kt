package com.matrimony.rvrmatrimony.basemodule

import android.content.res.Configuration
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.di.ViewModelFactory
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.CustomSpinnerAdapter
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import com.matrimony.rvrmatrimony.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseActivity : AppCompatActivity() {

    interface OnDayNightChangedListener {
        fun handleDayNightChange(isDay: Boolean)
    }

    private var onDayNightChangedListener: OnDayNightChangedListener? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    // Generic function to get the ViewModel instance
    protected inline fun <reified T : ViewModel> getViewModel(): T {
        return ViewModelProvider(this, viewModelFactory).get(T::class.java)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        Log.e(AllConstants.LOG_TAG_ACTIVITY, "onConfigurationChanged -- ${newConfig.orientation}")
        val nightModeFlag = newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK
        applyDayNight(nightModeFlag == Configuration.UI_MODE_NIGHT_NO)
        applyStatusBarColor(nightModeFlag)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(AllConstants.LOG_TAG_ACTIVITY, "onTouchEvent -- ${event?.action}")
        return super.onTouchEvent(event)
    }

    private fun applyDayNight(isDay: Boolean) {
        onDayNightChangedListener?.handleDayNightChange(isDay)
        Log.w(AllConstants.LOG_TAG_ACTIVITY, "DayNight Changed, isDay -- $isDay")
    }

    fun initDayNightChangedListener(listener: OnDayNightChangedListener) {
        this.onDayNightChangedListener = listener
    }

    @Suppress("DEPRECATION")
    private fun applyStatusBarColor(state: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (state == Configuration.UI_MODE_NIGHT_NO) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            window.statusBarColor = ContextCompat.getColor(this, R.color.black)
            window.decorView.systemUiVisibility = 0
        }
    }

    fun showSpinnerDropdown(
        anchorView: View,
        items: List<SpinnerItemPojo>,
        dropDownViewCount: Int,
        onItemSelected: (String) -> Unit
    ) {
        val recyclerView = RecyclerView(this)
        // recyclerView.isVerticalScrollBarEnabled = true
        recyclerView.layoutManager = LinearLayoutManager(anchorView.context)
        val popupWindow = PopupWindow(
            recyclerView,
            anchorView.width,
            UIUtils.calculateSpinnerPopUpHeight(anchorView, R.layout.item_simple_spinner, dropDownViewCount, recyclerView),
            true
        )
        recyclerView.adapter = CustomSpinnerAdapter(items) { selectedItem ->
            onItemSelected(selectedItem)
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(anchorView)
    }


}