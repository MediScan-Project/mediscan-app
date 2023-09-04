package com.compfest.mediscanapp.ui.article

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        button ()
        val name = intent.getStringExtra(NAME_EXTRA)
        val description = intent.getStringExtra(DESCRIPTION_EXTRA)
        val link = intent.getStringExtra(LINK_EXTRA)
        val photo = intent.getStringExtra(PHOTO_EXTRA)


        Glide.with(this@DetailArticleActivity)
            .load(photo)
            .into(binding.imgItemPhoto)
        binding.titleArticle.text = name
        binding.content.text = description
        binding.tvItemLink.text = link
    }

    private fun button(){
        binding.buttonBackArticle.setOnClickListener {
            onBackPressed()
        }
    }

    companion object{
        const val NAME_EXTRA = "NAME"
        const val DESCRIPTION_EXTRA = "DESCRIPTION"
        const val PHOTO_EXTRA = "PHOTO"
        const val LINK_EXTRA = "LINK"

    }
}