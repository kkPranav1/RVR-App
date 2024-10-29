package com.matrimony.rvrmatrimony.basemodule

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.utils.AllConstants
import com.matrimony.rvrmatrimony.utils.CustomSpinnerAdapter
import com.matrimony.rvrmatrimony.utils.SpinnerItemPojo
import com.matrimony.rvrmatrimony.utils.UIUtils

abstract class BaseDialog<B : ViewDataBinding>(
    private val ctx: Context,
    private val layoutId: Int,
    private val desiredUiWidth: Double = 0.9,
    private val desiredUiHeight: Double = 0.75
) : Dialog(ctx) {

    abstract fun handleDialogUiDayNightChange(isDay: Boolean)

    private var binding: B? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(ctx),
            layoutId,
            null,
            false
        )
        setContentView(binding!!.root)
        this.setCancelable(true)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        binding = null // To Avoid memory leaks
    }

    // Safe way to get binding without null assertion
    protected fun requireBinding(): B =
        binding ?: throw IllegalStateException(AllConstants.NULL_BINDING_EXCEPTION_MESSAGE)


    override fun show() {
        super.show()
        val width =
            if (desiredUiWidth != -1.0)
                (ctx.resources.displayMetrics.widthPixels * desiredUiWidth).toInt()
            else
                WindowManager.LayoutParams.WRAP_CONTENT
        val height =
            if (desiredUiHeight != -1.0)
                (ctx.resources.displayMetrics.heightPixels * desiredUiHeight).toInt()
            else
                WindowManager.LayoutParams.WRAP_CONTENT
        window?.setLayout(width, height)
    }

    fun showSpinnerDropdown(
        anchorView: View,
        items: List<SpinnerItemPojo>,
        dropDownViewCount: Int,
        onItemSelected: (String) -> Unit
    ) {
        val recyclerView = RecyclerView(anchorView.context)
        // recyclerView.isVerticalScrollBarEnabled = true
        recyclerView.layoutManager = LinearLayoutManager(anchorView.context)
        val popupWindow = PopupWindow(
            recyclerView,
            anchorView.width,
            UIUtils.calculateSpinnerPopUpHeight(
                anchorView,
                R.layout.item_simple_spinner,
                dropDownViewCount,
                recyclerView
            ),
            true
        )
        recyclerView.adapter = CustomSpinnerAdapter(items) { selectedItem ->
            onItemSelected(selectedItem)
            popupWindow.dismiss()
        }
        popupWindow.showAsDropDown(anchorView)
    }

}