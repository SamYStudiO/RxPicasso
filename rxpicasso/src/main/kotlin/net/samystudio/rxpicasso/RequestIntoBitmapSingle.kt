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

/**
 * A [Single] that wrap [RequestCreator] to easily modify request directly from this stream.
 */
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
     * [RequestCreator.noPlaceholder]
     */
    fun noPlaceholder() = apply { requestCreator.noPlaceholder() }

    /**
     * [RequestCreator.placeholder] (Int)
     */
    fun placeholder(@DrawableRes placeholderResId: Int) =
        apply { requestCreator.placeholder(placeholderResId) }

    /**
     * [RequestCreator.placeholder] (Drawable)
     */
    fun placeholder(placeholderDrawable: Drawable) =
        apply { requestCreator.placeholder(placeholderDrawable) }

    /**
     * [RequestCreator.error] (Int)
     */
    fun error(@DrawableRes errorResId: Int) = apply { requestCreator.error(errorResId) }

    /**
     * [RequestCreator.error] (Drawable)
     */
    fun error(errorDrawable: Drawable) = apply { requestCreator.error(errorDrawable) }

    /**
     * [RequestCreator.tag]
     */
    fun tag(tag: Any) = apply { requestCreator.tag(tag) }

    /**
     * [RequestCreator.fit]
     */
    fun fit() = apply { requestCreator.fit() }

    /**
     * [RequestCreator.resizeDimen]
     */
    fun resizeDimen(targetWidthResId: Int, targetHeightResId: Int) =
        apply { requestCreator.resizeDimen(targetWidthResId, targetHeightResId) }

    /**
     * [RequestCreator.resize]
     */
    fun resize(targetWidth: Int, targetHeight: Int) =
        apply { requestCreator.resize(targetWidth, targetHeight) }

    /**
     * [RequestCreator.centerCrop]
     */
    fun centerCrop() = apply { requestCreator.centerCrop() }

    /**
     * [RequestCreator.centerCrop] (Int)
     */
    fun centerCrop(alignGravity: Int) = apply { requestCreator.centerCrop(alignGravity) }

    /**
     * [RequestCreator.centerInside]
     */
    fun centerInside() = apply { requestCreator.centerInside() }

    /**
     * [RequestCreator.onlyScaleDown]
     */
    fun onlyScaleDown() = apply { requestCreator.onlyScaleDown() }

    /**
     * [RequestCreator.rotate] (Float)
     */
    fun rotate(degrees: Float) = apply { requestCreator.rotate(degrees) }

    /**
     * [RequestCreator.rotate] (Float, Float, Float)
     */
    fun rotate(degrees: Float, pivotX: Float, pivotY: Float) =
        apply { requestCreator.rotate(degrees, pivotX, pivotY) }

    /**
     * [RequestCreator.config]
     */
    fun config(config: Bitmap.Config) = apply { requestCreator.config(config) }

    /**
     * [RequestCreator.stableKey]
     */
    fun stableKey(stableKey: String) = apply { requestCreator.stableKey(stableKey) }

    /**
     * [RequestCreator.priority]
     */
    fun priority(priority: Picasso.Priority) = apply { requestCreator.priority(priority) }

    /**
     * [RequestCreator.transform] (Transformation)
     */
    fun transform(transformation: Transformation) =
        apply { requestCreator.transform(transformation) }

    /**
     * [RequestCreator.transform] (List<Transformation>)
     */
    fun transform(transformations: List<Transformation>) =
        apply { requestCreator.transform(transformations) }

    /**
     * [RequestCreator.memoryPolicy]
     */
    fun memoryPolicy(policy: MemoryPolicy, vararg additional: MemoryPolicy) =
        apply { requestCreator.memoryPolicy(policy, *additional) }

    /**
     * [RequestCreator.networkPolicy]
     */
    fun networkPolicy(policy: NetworkPolicy, vararg additional: NetworkPolicy) =
        apply { requestCreator.networkPolicy(policy, *additional) }

    /**
     * [RequestCreator.purgeable]
     */
    fun purgeable() = apply { requestCreator.purgeable() }

    /**
     * [RequestCreator.noFade]
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