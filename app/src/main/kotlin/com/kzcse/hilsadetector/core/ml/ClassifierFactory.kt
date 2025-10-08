package com.kzcse.hilsadetector.core.ml

import android.content.Context
import com.kzcse.hilsadetector.core.ml.impl.ImageClassifierImpl

object ClassifierFactory {
    fun createImageClassifier(context: Context): ImageClassifier{
         return ImageClassifierImpl(context,modelPath = "model.tflite")
    }
}

