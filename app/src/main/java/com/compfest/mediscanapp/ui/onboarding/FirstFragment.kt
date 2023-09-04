package com.compfest.mediscanapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.compfest.mediscanapp.R


class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        val next = view.findViewById<ImageView>(R.id.button_next)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)

        next.setOnClickListener {
            viewPager?.currentItem = 1
        }
        return view
    }

}