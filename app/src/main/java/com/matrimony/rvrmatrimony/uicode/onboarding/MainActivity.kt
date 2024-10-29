package com.matrimony.rvrmatrimony.uicode.onboarding

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.matrimony.rvrmatrimony.R
import com.matrimony.rvrmatrimony.basemodule.BaseActivity
import com.matrimony.rvrmatrimony.databinding.ActivityMainBinding

class MainActivity : BaseActivity(), BaseActivity.OnDayNightChangedListener, View.OnClickListener   {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        enableEdgeToEdge()
        initDayNightChangedListener(this@MainActivity)
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//
//        // Set HomeFragment as the default fragment
//        bottomNavigationView.selectedItemId = R.id.navigation_home
//        loadFragment(HomeFragment())
//
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            var selectedFragment: Fragment? = null
//            when (item.itemId) {
//                R.id.navigation_home -> selectedFragment = HomeFragment()
//                R.id.navigation_matches -> selectedFragment = MatchesFragment()
//                R.id.navigation_plans -> selectedFragment = MyAccountFragment()
//                R.id.navigation_contact -> selectedFragment = AllChatsFragment()
//            }
//            if (selectedFragment != null) {
//                loadFragment(selectedFragment)
//            }
//            true
//        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
        }

    override fun handleDayNightChange(isDay: Boolean) {

    }

    override fun onClick(p0: View?) {

    }
}
