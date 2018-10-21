@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso3.Picasso

sealed class BitmapTargetState {
    /**
     * [com.squareup.picasso3.BitmapTarget.onPrepareLoad]
     */
    class PrepareLoad internal constructor(val placeHolderDrawable: Drawable?) : BitmapTargetState()

    /**
     * [com.squareup.picasso3.BitmapTarget.onBitmapLoaded]
     */
    class BitmapLoaded internal constructor(val bitmap: Bitmap, val from: Picasso.LoadedFrom) :
        BitmapTargetState()

    /**
     * [com.squareup.picasso3.BitmapTarget.onBitmapFailed]
     */
    class BitmapFailed internal constructor(val e: Exception, val errorDrawable: Drawable?) :
        BitmapTargetState()
}