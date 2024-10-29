package com.matrimony.rvrmatrimony.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.recyclerview.widget.RecyclerView
import com.matrimony.rvrmatrimony.R
import io.ak1.BubbleTabBar

object UIUtils {

    fun shortToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun calculateSpinnerPopUpHeight(
        anchorView: View,
        itemLayoutId: Int,
        itemCountChoice: Int = 3,
        recyclerView: RecyclerView
    ): Int {
        // Inflate a single item view to measure its height
        val inflater = LayoutInflater.from(anchorView.context)
        val sampleItemView = inflater.inflate(itemLayoutId, recyclerView, false)
        // Measure the item view to get its height
        sampleItemView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        val singleItemHeight = sampleItemView.measuredHeight
        // Calculate and return the popup window height as itemCountChoice times the item height
        return itemCountChoice * singleItemHeight
    }

    fun setTextWithAnimation(textView: TextView, newText: String) {
        val fadeOut = AlphaAnimation(1.0f, 0.0f)
        fadeOut.duration = 250
        val fadeIn = AlphaAnimation(0.0f, 1.0f)
        fadeIn.duration = 250
        fadeOut.setAnimationListener(object : android.view.animation.Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {}
            override fun onAnimationEnd(p0: Animation?) {
                textView.text = newText
                textView.startAnimation(fadeIn)
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })
        textView.startAnimation(fadeOut)
    }

//    fun BubbleTabBar.onNavDestinationSelected(
//        itemId: Int,
//        navController: NavController
//    ): Boolean {
//        val builder = NavOptions.Builder()
//            .setLaunchSingleTop(true)
//        if (navController.currentDestination!!.parent!!.findNode(itemId) is ActivityNavigator.Destination) {
//            builder.setEnterAnim(R.anim.nav_default_enter_anim)
//                .setExitAnim(R.anim.nav_default_exit_anim)
//                .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
//                .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
//        } else {
//            builder.setEnterAnim(R.animator.nav_default_enter_anim)
//                .setExitAnim(R.animator.nav_default_exit_anim)
//                .setPopEnterAnim(R.animator.nav_default_pop_enter_anim)
//                .setPopExitAnim(R.animator.nav_default_pop_exit_anim)
//        }
//        //if (itemId == getChildAt(0).id) {
//        //builder.setPopUpTo(findStartDestination(navController.graph)!!.id, true)
//        // }
//        builder.setPopUpTo(itemId, true)
//        val options = builder.build()
//        return try {
//            //TODO provide proper API instead of using Exceptions as Control-Flow.
//            navController.navigate(itemId, null, options)
//            true
//        } catch (e: IllegalArgumentException) {
//            false
//        }
//    }
}