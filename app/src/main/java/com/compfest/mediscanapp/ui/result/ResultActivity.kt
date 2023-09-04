package com.compfest.mediscanapp.ui.result

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.viewModels
import com.compfest.mediscanapp.databinding.ActivityResultBinding
import com.compfest.mediscanapp.paging.ModelFactory
import com.compfest.mediscanapp.reduceFileImageUpload
import com.compfest.mediscanapp.rotateBitmap
import com.compfest.mediscanapp.ui.scancamera.HandWritinModel
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var factory: ModelFactory
    private val model: HandWritinModel by viewModels { factory }
    private var getMyFile: File? = null
    private lateinit var adapterUpload : ResultModelAdapter
    private var isBack: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnPredict.setOnClickListener { postModel() }
        factory = ModelFactory.getInstance(this)

        showImage()
//        binding.bu.setOnClickListener {
//            postStory()
//        }
    }

    private fun showImage() {
        getMyFile = intent.getSerializableExtra(PHOTO_RESULT_EXTRA) as File
        isBack = intent.getBooleanExtra(IS_CAMERA_BACK_EXTRA, true)

        val result = BitmapFactory.decodeFile((getMyFile as File).path)
        result.compress(Bitmap.CompressFormat.JPEG, 100, FileOutputStream(getMyFile))
        binding.previewImageView.setImageBitmap(result)
    }

    private fun postModel() {
        if (getMyFile != null) {
            val file = reduceFileImageUpload(getMyFile as File, isBackCamera = true)

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "file",
                file.name,
                requestImageFile
            )
            Log.d("upload", "response: ${file}")
//            showLoading(true)
            isPost(
                imageMultipart
            )
        } else {
            Toast.makeText(applicationContext, "Please Select Image", Toast.LENGTH_SHORT)
                .show()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun isPost(
        file: MultipartBody.Part,
    ) {
//        val rvModel = binding.rvDrug
        model.uploadStory(file)
        model.upload.observe(this@ResultActivity) { adapter ->
            val resultModel = ResultModelAdapter(adapter.predictions)
            val intent = Intent(this@ResultActivity, ActivityResult::class.java)
            Toast.makeText(applicationContext, "Uploading...", Toast.LENGTH_SHORT).show()
            startActivity(intent)
//            binding.rvDrug.adapter = resultModel
            runOnUiThread { binding.tvItemName1.text = "Your leaf is $resultModel" }
            runOnUiThread { binding.tvItemName2.text = "Your leaf is $resultModel" }
            runOnUiThread { binding.tvItemName3.text = "Your leaf is $resultModel" }
            runOnUiThread { binding.tvItemName4.text = "Your leaf is $resultModel" }
            runOnUiThread { binding.tvItemName5.text = "Your leaf is $resultModel" }

            finish()
        }
    }

//    private fun json(){
//
//
//// the json data you
//// got from the API
//        String jsonData = ModelDrug
//
//        Gson gson = new Gson();
//        try {
//            User[] users = gson.fromJson(jsonData, User[].class)
//        }
//        catch (JsonSyntaxException e) {
//            if (e.getMessage().equals(
//                    "Expected BEGIN_OBJECT but was BEGIN_ARRAY")) {
//                JsonArray jsonArray = new JsonParser()
//                    .parse(jsonData)
//                    .getAsJsonArray();
//                for (int i = 0; i < jsonArray.size(); i++) {
//                    User user = gson.fromJson(jsonArray.get(i),
//                    User.class);
//                    // do something with the user object
//                }
//            }
//            else {
//                // Handle other JSON parsing errors
//            }
//        }
//    }
    companion object {
        const val PHOTO_RESULT_EXTRA = "picture"
        const val IS_CAMERA_BACK_EXTRA = "isBackCamera"
    }
}