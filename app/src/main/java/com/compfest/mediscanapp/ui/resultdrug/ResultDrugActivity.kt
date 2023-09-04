package com.compfest.mediscanapp.ui.resultdrug

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.ActivityDetailArticleBinding
import com.compfest.mediscanapp.databinding.ActivityResultDrugBinding

class ResultDrugActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultDrugBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDrugBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

//        val photo = intent.getStringExtra(PHOTO_EXTRA)
        val name = intent.getStringExtra(NAME_EXTRA)
        val description = intent.getStringExtra(DESCRIPTION_EXTRA)
        val dosis = intent.getStringExtra(DOSIS_EXTRA)
        val komposisi = intent.getStringExtra(KOMPOSISI_EXTRA)
        val aturan = intent.getStringExtra(ATURAN_EXTRA)
        val simpan = intent.getStringExtra(MANFAAT_EXTRA)
        val golongan = intent.getStringExtra(GOLONGAN_EXTRA)
        val resep = intent.getStringExtra(RESEP_EXTRA)

        button ()

//        Glide.with(this@ResultDrugActivity)
//            .load(photo)
//            .into(binding.imgItemPhoto)
        binding.namaObat.text = name
        binding.deskripsiObat.text = description
        binding.dosisObat.text = dosis
        binding.komposisiObat.text = komposisi
        binding.aturanObat.text = aturan
        binding.simpanObat.text = simpan
        binding.golonganObat.text = golongan
        binding.resep.text = resep
    }

    private fun button (){
        binding.buttonBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object{
        const val NAME_EXTRA = "NAME"
        const val DESCRIPTION_EXTRA = "DESCRIPTION"
        const val PHOTO_EXTRA = "PHOTO"
        const val RESEP_EXTRA = "RESEP"
        const val DOSIS_EXTRA = "DOSIS"
        const val KOMPOSISI_EXTRA = "KOMPOSISI"
        const val ATURAN_EXTRA = "ATURAN"
        const val MANFAAT_EXTRA = "MANFAAT"
        const val GOLONGAN_EXTRA = "GOLONGAN"

    }
}