@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.squareup.picasso3.*
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.MainThreadDisposable

class RequestIntoCompletable private constructor(
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

    internal constructor(
        picasso: Picasso,
        requestCreator: RequestCreator,
        tag: Any? = null
    ) : this(picasso, requestCreator) {
        this.tag = tag
    }

    override fun subscribeActual(observer: CompletableObserver) {
        imageView?.let {
            val listener = Listener(observer, picasso, it)
            observer.onSubscribe(listener)
            requestCreator.into(it, listener)
        }
        remoteViews?.let {
            val listener = Listener(observer, picasso, it, viewId!!)
            observer.onSubscribe(listener)

            if (appWidgetIds != null) requestCreator.into(it, viewId!!, notificationId!!)
            else requestCreator.into(
                it,
                viewId!!,
                notificationId!!,
                notification!!,
                notificationTag!!,
                listener
            )
        }

        if (imageView == null && remoteViews == null) {
            val listener = Listener(observer, picasso, tag)
            observer.onSubscribe(listener)
            requestCreator.fetch(listener)
        }
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
    fun tag(tag: Any) = apply {
        this.tag = tag
        requestCreator.tag(tag)
    }

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

    internal class Listener private constructor(
        private val observer: CompletableObserver,
        private val picasso: Picasso?
    ) : MainThreadDisposable(), Callback {

        private var imageView: ImageView? = null
        private var remoteViews: RemoteViews? = null
        private var viewId: Int? = null
        private var tag: Any? = null

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso?,
            imageView: ImageView
        ) : this(observer, picasso) {
            this.imageView = imageView
        }

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso?,
            remoteViews: RemoteViews,
            @DrawableRes viewId: Int
        ) : this(observer, picasso) {
            this.remoteViews = remoteViews
            this.viewId = viewId
        }

        internal constructor(
            observer: CompletableObserver,
            picasso: Picasso?,
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