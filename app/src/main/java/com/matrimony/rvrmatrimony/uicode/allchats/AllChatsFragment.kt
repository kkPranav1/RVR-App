package com.matrimony.rvrmatrimony.uicode.allchats

import android.os.Bundle
import android.view.View
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseFragment
import com.matrimony.rvrmatrimony.databinding.FragmentAllChatsBinding

class AllChatsFragment() : BaseFragment<FragmentAllChatsBinding>() {

    companion object {

    }

    private lateinit var binding: FragmentAllChatsBinding
    override fun getLayoutId(): Int = R.layout.fragment_all_chats

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun handleDayNightChange(isDay: Boolean) {
        TODO("Not yet implemented")
    }
}