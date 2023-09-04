package com.compfest.mediscanapp.ui.onboarding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.ActivityOnboardingBinding


class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding

    val onBoardingFragment = OnboardingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        onBoarding()
    }

    private fun onBoarding(){
        fragmentDisplay(onBoardingFragment)
    }

    private fun fragmentDisplay(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.frame, fragment)
        commit()
    }
}