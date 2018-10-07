@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso.Picasso

sealed class TargetState {
    class PrepareLoad(val placeHolderDrawable: Drawable?) : TargetState()
    class BitmapLoaded(val bitmap: Bitmap, val from: Picasso.LoadedFrom) : TargetState()
    class BitmapFailed(val e: Exception, val errorDrawable: Drawable?) : TargetState()
}