package com.kzcse.tfliteconcept.ui.u

import android.graphics.Bitmap
import com.kzcse.tfliteconcept.ui.drawer.Destination
import com.kzcse.tfliteconcept.ui.drawer.NavigationDrawerController

class MainViewModel  {
    companion object{
        var processImage:Bitmap?=null
    }
    val controller = NavigationDrawerController()
    fun openDrawer() = controller.openDrawer()
    /** Set the bitmap before navigation, passing bitmap via navigation is
     complex that is why doing this ...**/

    fun select(destination: Destination) = controller.select(destination)

}