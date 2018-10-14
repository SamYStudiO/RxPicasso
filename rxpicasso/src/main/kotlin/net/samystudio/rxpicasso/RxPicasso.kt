@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.squareup.picasso3.BitmapTarget
import com.squareup.picasso3.Callback
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.RequestCreator
import io.reactivex.*

object RxPicasso {
    /**
     * [com.squareup.picasso3.RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        requestCreator: RequestCreator,
        imageView: ImageView
    ): Completable =
        Completable.create { emitter ->
            emitter.setCancellable { picasso.cancelRequest(imageView) }
            requestCreator.into(
                imageView,
                CompletableCallBack(emitter)
            )
        }

    /**
     * [com.squareup.picasso3.RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        requestCreator: RequestCreator,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ): Completable = Completable.create { emitter ->
        emitter.setCancellable { picasso.cancelRequest(remoteViews, viewId) }
        requestCreator.into(
            remoteViews,
            viewId,
            notificationId,
            notification,
            notificationTag,
            CompletableCallBack(emitter)
        )
    }

    /**
     * [com.squareup.picasso3.RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        requestCreator: RequestCreator,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ): Completable = Completable.create { emitter ->
        emitter.setCancellable { picasso.cancelRequest(remoteViews, viewId) }
        requestCreator.into(
            remoteViews,
            viewId,
            appWidgetIds,
            CompletableCallBack(emitter)
        )
    }

    /**
     * [com.squareup.picasso3.RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, requestCreator: RequestCreator): Single<Bitmap> =
        Single.create { emitter ->
            val bitmapTarget = SingleTarget(emitter)
            emitter.setCancellable { picasso.cancelRequest(bitmapTarget) }
            requestCreator.into(bitmapTarget)
        }

    /**
     * [com.squareup.picasso3.RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(
        picasso: Picasso,
        requestCreator: RequestCreator
    ): Observable<BitmapTargetState> =
        Observable.create { emitter ->
            val bitmapTarget = ObservableTarget(emitter)
            emitter.setCancellable { picasso.cancelRequest(bitmapTarget) }
            requestCreator.into(bitmapTarget)
        }

    /**
     * [tag] is only required if you want to cancel fetch when stream is disposed. It will override
     * any previously tag set from [com.squareup.picasso3.RequestCreator.tag]
     * !!! For now cancellation doesn't work https://github.com/square/picasso/issues/1205 !!!
     * [com.squareup.picasso3.RequestCreator.fetch]
     */
    @JvmStatic
    @JvmOverloads
    fun observeFetch(
        picasso: Picasso,
        requestCreator: RequestCreator,
        tag: Any? = null
    ): Completable =
        Completable.create { emitter ->
            tag?.let {
                requestCreator.tag(it)
                emitter.setCancellable { picasso.cancelTag(it) }
            }
            requestCreator.fetch(CompletableCallBack(emitter))
        }

    internal class CompletableCallBack(private val emitter: CompletableEmitter) : Callback {
        override fun onSuccess() {
            emitter.onComplete()
        }

        override fun onError(t: Throwable) {
            emitter.onError(t)
        }
    }

    internal class SingleTarget(private val emitter: SingleEmitter<Bitmap>) : BitmapTarget {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
            emitter.onError(e)
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            emitter.onSuccess(bitmap)
        }
    }

    internal class ObservableTarget(private val emitter: ObservableEmitter<BitmapTargetState>) :
        BitmapTarget {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            emitter.onNext(BitmapTargetState.PrepareLoad(placeHolderDrawable))
        }

        override fun onBitmapFailed(e: Exception, errorDrawable: Drawable?) {
            emitter.onNext(BitmapTargetState.BitmapFailed(e, errorDrawable))
            emitter.onError(e)
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            emitter.onNext(BitmapTargetState.BitmapLoaded(bitmap, from))
            emitter.onComplete()
        }
    }
}
