package com.compfest.mediscanapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.compfest.mediscanapp.ui.onboarding.OnboardingActivity
import com.compfest.mediscanapp.ui.dashboard.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
//        val intent = Intent(this, OnboardingActivity::class.java)
//        navigate(intent)
        auth = Firebase.auth
        val user = auth.currentUser
        updateUI(user)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            val intent = Intent(this, DashboardActivity::class.java)
            navigate(intent)
        }else {
            val intent = Intent(this, OnboardingActivity::class.java)
            navigate(intent)
        }
    }


    private fun navigate(intent: Intent) {
        val splashTimer: Long = 3000
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, splashTimer)
    }
}