package com.compfest.mediscanapp.mlkit

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class ImageAnalyzer(
    val onTextFound: (String) -> Unit,
    private val textRecognizer: TextRecognizer = TextRecognizer(onTextFound)
) :
    ImageAnalysis.Analyzer {
    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return

        textRecognizer.recognizeImageText(
            image = mediaImage,
            rotationDegrees = imageProxy.imageInfo.rotationDegrees,
            onResult = {
                imageProxy.close()
            }
        )

    }
}