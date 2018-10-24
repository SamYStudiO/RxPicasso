@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.VisibleForTesting
import com.squareup.picasso3.*
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.MainThreadDisposable

/**
 * A [Completable] that wrap [RequestCreator] to easily modify request directly from this stream.
 */
class RequestIntoCompletable internal constructor(
    private val picasso: Picasso,
    private val requestCreator: RequestCreator
) : Completable() {

    private var imageView: ImageView? = null
    private var remoteViews: RemoteViews? = null
    private var viewId: Int? = null
    private var notificationId: Int? = null
    private var notification: Notification? = null
    private var notificationTag: String? = null
    private var appWidgetIds: IntArray? = null
    private var tag: Any? = null

    internal constructor(
        picasso: Picasso,
        requestCreator: RequestCreator,
        imageView: ImageView
    ) : this(picasso, requestCreator) {
        this.imageView = imageView
    }

    internal constructor(
        picasso: Picasso,
        requestCreator: RequestCreator,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ) : this(picasso, requestCreator) {
        this.remoteViews = remoteViews
        this.viewId = viewId
        this.notificationId = notificationId
        this.notification = notification
        this.notificationTag = notificationTag
    }

    internal constructor(
        picasso: Picasso,
        requestCreator: RequestCreator,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) : this(picasso, requestCreator) {
        this.remoteViews = remoteViews
        this.viewId = viewId
        this.appWidgetIds = appWidgetIds
    }

    override fun subscribeActual(observer: CompletableObserver) {
        imageView?.let {
            val listener = Listener(observer, picasso, it)
            observer.onSubscribe(listener)
            requestCreator.into(it, listener)
        } ?: remoteViews?.let {
            val listener = Listener(observer, picasso, it, viewId!!)
            observer.onSubscribe(listener)
            if (appWidgetIds != null) requestCreator.into(
                it,
                viewId!!,
                notificationId!!
            ) else requestCreator.into(
                it,
                viewId!!,
                notificationId!!,
                notification!!,
                notificationTag!!,
                listener
            )
        } ?: run {
            val listener = Listener(observer, picasso, tag)
            observer.onSubscribe(listener)
            requestCreator.fetch(listener)
        }
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
    fun tag(tag: Any) = apply {
        this.tag = tag
        requestCreator.tag(tag)
    }

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
        private val observer: CompletableObserver,
        private val picasso: Picasso? = null
    ) : MainThreadDisposable(), Callback {

        private var imageView: ImageView? = null
        private var remoteViews: RemoteViews? = null
        private var viewId: Int? = null
        private var tag: Any? = null

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso,
            imageView: ImageView
        ) : this(observer, picasso) {
            this.imageView = imageView
        }

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso,
            remoteViews: RemoteViews,
            @DrawableRes viewId: Int
        ) : this(observer, picasso) {
            this.remoteViews = remoteViews
            this.viewId = viewId
        }

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso,
            tag: Any?
        ) : this(observer, picasso) {
            this.tag = tag
        }

        override fun onDispose() {
            imageView?.let { picasso?.cancelRequest(it) }
            remoteViews?.let { remoteViews ->
                viewId?.let { picasso?.cancelRequest(remoteViews, it) }
            }
            if (imageView == null && remoteViews == null)
                tag?.let { picasso?.cancelTag(it) }
        }

        override fun onSuccess() {
            observer.onComplete()
        }

        override fun onError(t: Throwable) {
            observer.onError(t)
        }
    }
}