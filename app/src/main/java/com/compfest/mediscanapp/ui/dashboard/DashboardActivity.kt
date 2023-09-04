package com.compfest.mediscanapp.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.ActivityDashboardBinding
import com.compfest.mediscanapp.ui.article.ArticleFragment
import com.compfest.mediscanapp.ui.drug.DrugFragment
import com.compfest.mediscanapp.ui.fitur.FeaturesActivity
import com.compfest.mediscanapp.ui.fragment.DashboardFragment
import com.compfest.mediscanapp.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    private lateinit var bottomNavigation: BottomNavigationView

    val dashboardFragment = DashboardFragment()
    val drugFragment = DrugFragment()
    val articleFragment = ArticleFragment()
    val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(2).isEnabled = false
        setContentView(binding.root)
        fragmentDisplay(dashboardFragment)
        bottomNavigation()
        fab()
    }

    private fun bottomNavigation() {
        bottomNavigation = binding.bottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> fragmentDisplay(dashboardFragment)
                R.id.drug -> fragmentDisplay(drugFragment)
                R.id.article -> fragmentDisplay(articleFragment)
                R.id.profil -> fragmentDisplay(profileFragment)
            }
            true
        }
    }

    private fun fragmentDisplay(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frame_dashboard, fragment)
            commit()
        }

    fun fab(){
        binding.fabFeatures.setOnClickListener {
            val intent = Intent(this, FeaturesActivity::class.java)
            startActivity(intent)
        }
    }
}