package com.matrimony.rvrmatrimony.basemodule

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.matrimony.rvrmatrimony.utils.AllConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

abstract class BaseBottomSheetDialog<T : ViewDataBinding> : BottomSheetDialogFragment() {
    private var mViewDataBinding: T? = null
    protected val uiScope = CoroutineScope(Dispatchers.Main)

    abstract fun getLayoutId(): Int
    abstract fun handleDayNightChange(isDay: Boolean)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            dialog.behavior.apply {
                addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                    override fun onStateChanged(bottomSheet: View, newState: Int) {
                        if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            dialog.dismiss()
                        }
                    }

                    override fun onSlide(bottomSheet: View, slideOffset: Float) {
                        Log.w("Slide Offset", "" + slideOffset)
                        if (!slideOffset.isNaN()) {
                            dialog.window?.setDimAmount(0.5f)
                        }
                    }
                })
            }
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return mViewDataBinding!!.root
    }

    protected fun getDialogBinding(): T {
        return mViewDataBinding ?: throw IllegalStateException(AllConstants.NULL_BINDING_EXCEPTION_MESSAGE)
    }

    override fun onStart() {
        super.onStart()

        // Set the soft input mode to adjust when keyboard appears
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

}