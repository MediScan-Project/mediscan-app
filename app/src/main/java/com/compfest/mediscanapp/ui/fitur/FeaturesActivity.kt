package com.compfest.mediscanapp.ui.fitur

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.compfest.mediscanapp.R
import com.compfest.mediscanapp.databinding.ActivityFeaturesBinding
import com.compfest.mediscanapp.ocr.MainActivity
import com.compfest.mediscanapp.ocr.MainScreen
import com.compfest.mediscanapp.ocr.themes.mediscanApp
import com.compfest.mediscanapp.ui.handwritingcamera.CameraXActivity
import com.compfest.mediscanapp.ui.scancamera.ScanCameraActivity
import java.io.File

class FeaturesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeaturesBinding
    private lateinit var currentPhotoPath: String
    private var getMyFile: File? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeaturesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        buttonNext()
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.buttonCameraCompose.setOnClickListener {
            val intent = Intent(this, OcrActivity::class.java)
            launcherIntentCameraX.launch(intent)
        }
        binding.buttonScan.setOnClickListener {
            startCameraScan()
        }
    }


    private fun startCameraX() {
        val intent = Intent(this, CameraXActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private fun startCameraScan() {
        val intent = Intent(this, ScanCameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

//    private fun startTakePhoto() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.resolveActivity(packageManager)
//        createCustomTempFile(application).also {
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this@StoryActivity,
//                "com.bangkit.submission1intermediate",
//                it
//            )
//            currentPhotoPath = it.absolutePath
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            launcherIntentCamera.launch(intent)
//        }
//    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULTT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
            getMyFile = myFile
//            val result = rotateBitmapBase(
//                BitmapFactory.decodeFile(myFile.path),
//                isBackCamera
//            )

//            binding.previewImageView.setImageBitmap(result)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

//    private fun startTakePhoto() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.resolveActivity(packageManager)
//        createCustomTempFile(application).also {
//            val photoURI: Uri = FileProvider.getUriForFile(
//                this@FeaturesActivity,
//                "com.compfest.mediscanapp",
//                it
//            )
//            currentPhotoPath = it.absolutePath
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//            launcherIntentCamera.launch(intent)
//        }
//    }
//
//    private val launcherIntentCamera = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) {
//        if (it.resultCode == RESULT_OK) {
//            val myFile = File(currentPhotoPath)
//            getMyFile = myFile
//
//            val result = BitmapFactory.decodeFile(myFile.path)
////            binding.previewImageView.setImageBitmap(result)
//        }
//    }



    companion object {
        const val CAMERA_X_RESULTT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
        private const val FILENAME_FORMAT = "dd-MMM-yyyy"
        private const val MAXIMAL_SIZE = 1000000
    }
}