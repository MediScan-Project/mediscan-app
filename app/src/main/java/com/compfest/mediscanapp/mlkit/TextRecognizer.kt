package com.compfest.mediscanapp.mlkit

import android.media.Image
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions

class TextRecognizer(val onTextFound: (String) -> Unit) {

    fun recognizeImageText(image: Image, rotationDegrees: Int, onResult: (Boolean) -> Unit) {

        val inputImage = InputImage.fromMediaImage(image, rotationDegrees)

        TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)
            .process(inputImage)
            .addOnSuccessListener { recognizedText ->
                recognizedText.getAllTextFromImage()
                onResult(true)
            }
            .addOnFailureListener { error ->
                error.printStackTrace()
                onResult(false)
            }
    }

    private fun Text.getAllTextFromImage(){
        if (this.textBlocks.isNotEmpty()){
            onTextFound(this.text)
        }
    }
}