package com.matrimony.rvrmatrimony.basemodule

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.databinding.DialogTermsConditionsBinding
import com.matrimony.rvrmatrimony.utils.AllConstants

abstract class BaseDialogFragment<D : ViewDataBinding>(
    private val ctx: Context,
    private val desiredUiWidth: Double = -1.0,
    private val desiredUiHeight: Double = -1.0
) : DialogFragment() {

    private var _binding: D? = null
    private val binding: D get() = _binding!!
    abstract fun getLayoutId(): Int
    abstract fun handleDayNightChange(isDay: Boolean)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    protected fun getDialogFragmentBinding(): D = _binding ?: throw IllegalStateException(AllConstants.NULL_BINDING_EXCEPTION_MESSAGE)

    override fun onStart() {
        super.onStart()
        // Set the soft input mode to adjust when keyboard appears
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        super.show(manager, tag)
        // setDim()
    }

    private fun setDim() {
        val width =
            if (desiredUiWidth != -1.0)
                (ctx.resources.displayMetrics.widthPixels * desiredUiWidth).toInt()
            else
                WindowManager.LayoutParams.MATCH_PARENT
        val height =
            if (desiredUiHeight != -1.0)
                (ctx.resources.displayMetrics.heightPixels * desiredUiHeight).toInt()
            else
                WindowManager.LayoutParams.MATCH_PARENT

        dialog?.window?.setLayout(width, height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}