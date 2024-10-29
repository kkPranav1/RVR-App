package com.matrimony.rvrmatrimony.uicode.myaccountandplans

import android.os.Bundle
import android.view.View
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseFragment
import com.matrimony.rvrmatrimony.databinding.FragmentMyAccountBinding

class MyAccountFragment() : BaseFragment<FragmentMyAccountBinding>() {

    companion object {

    }

    private lateinit var binding: FragmentMyAccountBinding
    override fun getLayoutId(): Int = R.layout.fragment_my_account

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun handleDayNightChange(isDay: Boolean) {
        TODO("Not yet implemented")
    }
}