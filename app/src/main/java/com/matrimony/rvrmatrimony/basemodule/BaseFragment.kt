package com.matrimony.rvrmatrimony.basemodule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.matrimony.rvrmatrimony.utils.AllConstants

abstract class BaseFragment<F : ViewDataBinding> : Fragment() {

    private var _binding: F? = null
    private val binding: F get() = _binding!!
    abstract fun getLayoutId(): Int
    abstract fun handleDayNightChange(isDay: Boolean)

    protected var savedUiTextStoreListFacility: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedUiTextStoreListFacility = if (savedInstanceState != null) {
            savedInstanceState.getStringArrayList(AllConstants.STRINGS_LIST_FACILITY)
        } else {
            ArrayList()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    protected fun getFragmentBinding(): F =
        _binding ?: throw IllegalStateException(AllConstants.NULL_BINDING_EXCEPTION_MESSAGE)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(
            AllConstants.STRINGS_LIST_FACILITY,
            savedUiTextStoreListFacility!!
        )
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}