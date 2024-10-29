package com.matrimony.rvrmatrimony.uicode.matches

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseFragment
import com.matrimony.rvrmatrimony.databinding.FragmentAllChatsBinding
import com.matrimony.rvrmatrimony.databinding.FragmentMatchesBinding

class MatchesFragment() : BaseFragment<FragmentMatchesBinding>() {

    companion object {

    }

    private lateinit var binding: FragmentMatchesBinding
    override fun getLayoutId(): Int = R.layout.fragment_matches

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun handleDayNightChange(isDay: Boolean) {
        TODO("Not yet implemented")
    }
}