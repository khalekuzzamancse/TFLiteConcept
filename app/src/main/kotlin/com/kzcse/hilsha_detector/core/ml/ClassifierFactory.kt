package com.kzcse.hilsha_detector.core.ml

import android.content.Context
import com.kzcse.hilsha_detector.core.ml.impl.ImageClassifierImpl

object ClassifierFactory {
    fun createImageClassifier(context: Context): ImageClassifier{
         return ImageClassifierImpl(context,modelPath = "model.tflite")
    }
}

