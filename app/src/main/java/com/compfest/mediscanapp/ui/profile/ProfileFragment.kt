package com.compfest.mediscanapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.compfest.mediscanapp.databinding.FragmentProfileBinding
import com.compfest.mediscanapp.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view: View = binding.root
        mAuth = FirebaseAuth.getInstance()
        signOut()
        displayUser()
        return view
    }

    private fun displayUser() {
        val currentUser = mAuth.currentUser
        Glide.with(this).load(currentUser?.photoUrl).into(
            binding.photoProfile
        )
        binding.tvNamePhoto.text = currentUser?.displayName
    }

    private fun signOut() {

        binding.logOut.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

}