

package com.kzcse.tfliteconcept.domain

 class CustomException(override val message: String, val debugMessage: String) :
    Throwable(message = message, cause = Throwable(debugMessage)) {
    override fun toString(): String {
        return "CustomException: $message\nDebug Message: $debugMessage"
    }

}