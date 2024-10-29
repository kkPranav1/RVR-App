package com.matrimony.rvrmatrimony.uicode.home

import android.os.Bundle
import android.view.View
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseFragment
import com.matrimony.rvrmatrimony.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {

    }

    private lateinit var binding: FragmentHomeBinding
    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun handleDayNightChange(isDay: Boolean) {
        TODO("Not yet implemented")
    }

}