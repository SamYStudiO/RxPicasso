@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.VisibleForTesting
import com.squareup.picasso3.*
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.MainThreadDisposable

class RequestIntoBitmapSingle internal constructor(
    private val picasso: Picasso,
    private val requestCreator: RequestCreator
) : Single<Bitmap>() {

    override fun subscribeActual(observer: SingleObserver<in Bitmap>) {
        val listener = Listener(observer, picasso)
        observer.onSubscribe(listener)
        requestCreator.into(listener)
    }

    /**
     * [com.squareup.picasso3.RequestCreator.noPlaceholder]
     */
    fun noPlaceholder() = apply { requestCreator.noPlaceholder() }

    /**
     * [com.squareup.picasso3.RequestCreator.placeholder] (Int)
     */
    fun placeholder(@DrawableRes placeholderResId: Int) =
        apply { requestCreator.placeholder(placeholderResId) }

    /**
     * [com.squareup.picasso3.RequestCreator.placeholder] (Drawable)
     */
    fun placeholder(placeholderDrawable: Drawable) =
        apply { requestCreator.placeholder(placeholderDrawable) }

    /**
     * [com.squareup.picasso3.RequestCreator.error] (Int)
     */
    fun error(@DrawableRes errorResId: Int) = apply { requestCreator.error(errorResId) }

    /**
     * [com.squareup.picasso3.RequestCreator.error] (Drawable)
     */
    fun error(errorDrawable: Drawable) = apply { requestCreator.error(errorDrawable) }

    /**
     * [com.squareup.picasso3.RequestCreator.tag]
     */
    fun tag(tag: Any) = apply { requestCreator.tag(tag) }

    /**
     * [com.squareup.picasso3.RequestCreator.fit]
     */
    fun fit() = apply { requestCreator.fit() }

    /**
     * [com.squareup.picasso3.RequestCreator.resizeDimen]
     */
    fun resizeDimen(targetWidthResId: Int, targetHeightResId: Int) =
        apply { requestCreator.resizeDimen(targetWidthResId, targetHeightResId) }

    /**
     * [com.squareup.picasso3.RequestCreator.resize]
     */
    fun resize(targetWidth: Int, targetHeight: Int) =
        apply { requestCreator.resize(targetWidth, targetHeight) }

    /**
     * [com.squareup.picasso3.RequestCreator.centerCrop]
     */
    fun centerCrop() = apply { requestCreator.centerCrop() }

    /**
     * [com.squareup.picasso3.RequestCreator.centerCrop] (Int)
     */
    fun centerCrop(alignGravity: Int) = apply { requestCreator.centerCrop(alignGravity) }

    /**
     * [com.squareup.picasso3.RequestCreator.centerInside]
     */
    fun centerInside() = apply { requestCreator.centerInside() }

    /**
     * [com.squareup.picasso3.RequestCreator.onlyScaleDown]
     */
    fun onlyScaleDown() = apply { requestCreator.onlyScaleDown() }

    /**
     * [com.squareup.picasso3.RequestCreator.rotate] (Float)
     */
    fun rotate(degrees: Float) = apply { requestCreator.rotate(degrees) }

    /**
     * [com.squareup.picasso3.RequestCreator.rotate] (Float, Float, Float)
     */
    fun rotate(degrees: Float, pivotX: Float, pivotY: Float) =
        apply { requestCreator.rotate(degrees, pivotX, pivotY) }

    /**
     * [com.squareup.picasso3.RequestCreator.config]
     */
    fun config(config: Bitmap.Config) = apply { requestCreator.config(config) }

    /**
     * [com.squareup.picasso3.RequestCreator.stableKey]
     */
    fun stableKey(stableKey: String) = apply { requestCreator.stableKey(stableKey) }

    /**
     * [com.squareup.picasso3.RequestCreator.priority]
     */
    fun priority(priority: Picasso.Priority) = apply { requestCreator.priority(priority) }

    /**
     * [com.squareup.picasso3.RequestCreator.transform] (Transformation)
     */
    fun transform(transformation: Transformation) =
        apply { requestCreator.transform(transformation) }

    /**
     * [com.squareup.picasso3.RequestCreator.transform] (List<Transformation>)
     */
    fun transform(transformations: List<Transformation>) =
        apply { requestCreator.transform(transformations) }

    /**
     * [com.squareup.picasso3.RequestCreator.memoryPolicy]
     */
    fun memoryPolicy(policy: MemoryPolicy, vararg additional: MemoryPolicy) =
        apply { requestCreator.memoryPolicy(policy, *additional) }

    /**
     * [com.squareup.picasso3.RequestCreator.networkPolicy]
     */
    fun networkPolicy(policy: NetworkPolicy, vararg additional: NetworkPolicy) =
        apply { requestCreator.networkPolicy(policy, *additional) }

    /**
     * [com.squareup.picasso3.RequestCreator.purgeable]
     */
    fun purgeable() = apply { requestCreator.purgeable() }

    /**
     * [com.squareup.picasso3.RequestCreator.noFade]
     */
    fun noFade() = apply { requestCreator.noFade() }

    @VisibleForTesting
    internal class Listener(
        private val observer: SingleObserver<in Bitmap>,
        private val picasso: Picasso? = null
    ) : MainThreadDisposable(), BitmapTarget {
        override fun onDispose() {
            picasso?.cancelRequest(this)
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
            observer.onError(e)
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            observer.onSuccess(bitmap)
        }
    }
}