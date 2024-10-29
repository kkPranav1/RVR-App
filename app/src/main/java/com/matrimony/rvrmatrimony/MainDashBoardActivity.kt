package com.matrimony.rvrmatrimony

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityMainDashBoardBinding
import com.matrimony.rvrmatrimony.uicode.home.HomeFragment
import com.matrimony.rvrmatrimony.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint
import io.ak1.OnBubbleClickListener

@AndroidEntryPoint
class MainDashBoardActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener {

    private lateinit var binding: ActivityMainDashBoardBinding
    private lateinit var viewModel: MainDashBoardViewModel
    private var mNavController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this@MainDashBoardActivity,
            R.layout.activity_main_dash_board
        )
        enableEdgeToEdge()
        viewModel = getViewModel<MainDashBoardViewModel>()
        initDayNightChangedListener(this@MainDashBoardActivity)
        binding.root.post {
            initUi()
        }
    }

    private fun initUi() {
        // supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, HomeFragment()).commitNow()
        mNavController = getNavController()
        mNavController?.let { nc ->
            binding.bottomBubbleBarNavigation.addBubbleListener { id ->
                when (id) {
                    R.id.navigation_home -> nc.navigate(R.id.homeFragment)
                    R.id.navigation_matches -> nc.navigate(R.id.matchesFragment)
                    R.id.navigation_all_chats -> nc.navigate(R.id.allChatsFragment)
                    R.id.navigation_my_account -> nc.navigate(R.id.myAccountFragment)
                }
            }
        } ?: run { UIUtils.shortToast(this@MainDashBoardActivity, "Please Try Again!!"); }
    }

    private fun getNavController(): NavController? {
        // Log.e("NavHostFragmentCheck", supportFragmentManager.findFragmentById(R.id.nav_host_fragment).toString())
        val nHF = (supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment)
        return nHF?.navController
    }



    override fun handleDayNightChange(isDay: Boolean) {
        val white = ContextCompat.getColor(this, R.color.white)
        val black = ContextCompat.getColor(this, R.color.black)
        binding.apply {
            mainCODL.setBackgroundColor(white) // if (isDay) white else black
        }
    }
}