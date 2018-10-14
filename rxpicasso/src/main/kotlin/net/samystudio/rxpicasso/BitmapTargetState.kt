@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso3.Picasso

sealed class BitmapTargetState {
    class PrepareLoad(val placeHolderDrawable: Drawable?) : BitmapTargetState()
    class BitmapLoaded(val bitmap: Bitmap, val from: Picasso.LoadedFrom) : BitmapTargetState()
    class BitmapFailed(val e: Exception, val errorDrawable: Drawable?) : BitmapTargetState()
}