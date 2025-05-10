package com.kzcse.tfliteconcept.data

import android.content.Context
import android.graphics.Bitmap
import com.kzcse.tfliteconcept.domain.Constants
import com.kzcse.tfliteconcept.domain.CustomException
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.DataType
import java.nio.ByteBuffer
import java.nio.ByteOrder

class Classifier(context: Context) {

    private val interpreter: Interpreter

    init {
        val modelFile = FileUtil.loadMappedFile(context, "model.tflite")
        interpreter = Interpreter(modelFile)
    }

    fun classifyOrThrow(bitmap: Bitmap): String?{
        val width=bitmap.width
        val height=bitmap.height
        if (!Constants.isImageSizeMatched(bitmap)){
            throw  CustomException(
                message = "Image size should be ${Constants.EXPECTED_IMAGE_WIDTH}*${Constants.EXPECTED_IMAGE_HEIGHT}",
                debugMessage = "src=Classifier::classifyOrThrow, provided image size($width,$height)")
        }
        val mutableBitmap = convertToMutableBitmap(bitmap)
        val inputBuffer = convertBitmapToByteBuffer(mutableBitmap)

        // Correct TensorBuffer creation with DataType.FLOAT32
        val outputBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 5), DataType.FLOAT32)
        interpreter.run(inputBuffer, outputBuffer.buffer)

        // Get predicted class
        val outputArray = outputBuffer.floatArray
        val predictedIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1

        // Class labels
        val classLabels = arrayOf("Chondona", "Gurta", "Healthy ilish", "Jhatka ilish", "Others")

        return if (predictedIndex != -1) {
            classLabels[predictedIndex]
        } else {
            null
        }
    }

    /**
     * convert the bitmap to a software-configured bitmap
     */
    fun convertToMutableBitmap(bitmap: Bitmap): Bitmap {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }


    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val buffer = ByteBuffer.allocateDirect(4 * 224 * 224 * 3)
        buffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(224 * 224)
        bitmap.getPixels(pixels, 0, 224, 0, 0, 224, 224)

        for (pixel in pixels) {
            val r = (pixel shr 16) and 0xFF
            val g = (pixel shr 8) and 0xFF
            val b = pixel and 0xFF

            buffer.putFloat(r / 255.0f)
            buffer.putFloat(g / 255.0f)
            buffer.putFloat(b / 255.0f)
        }

        buffer.rewind()
        return buffer
    }
}

