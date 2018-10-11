@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import com.squareup.picasso.Target
import io.reactivex.*

object RxPicasso {
    /**
     * [com.squareup.picasso.RequestCreator.into] (ImageView)
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
     * [com.squareup.picasso.RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
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
     * [com.squareup.picasso.RequestCreator.into] (RemoteViews, Int, IntArray)
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
     * [com.squareup.picasso.RequestCreator.into] (Target)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, requestCreator: RequestCreator): Single<Bitmap> =
        Single.create { emitter ->
            val target = SingleTarget(emitter)
            emitter.setCancellable { picasso.cancelRequest(target) }
            requestCreator.into(target)
        }

    /**
     * [com.squareup.picasso.RequestCreator.into] (Target)
     */
    @JvmStatic
    fun observeIntoTarget(
        picasso: Picasso,
        requestCreator: RequestCreator
    ): Observable<TargetState> =
        Observable.create { emitter ->
            val target = ObservableTarget(emitter)
            emitter.setCancellable { picasso.cancelRequest(target) }
            requestCreator.into(target)
        }

    /**
     * [tag] is only required if you want to cancel fetch when stream is disposed. It will override
     * any previously tag set from [com.squareup.picasso.RequestCreator.tag]
     * !!! For now cancellation doesn't work https://github.com/square/picasso/issues/1205 !!!
     * [com.squareup.picasso.RequestCreator.fetch]
     */
    @JvmStatic
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

        override fun onError(e: Exception) {
            emitter.onError(e)
        }
    }

    internal class SingleTarget(private val emitter: SingleEmitter<Bitmap>) : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        }

        override fun onBitmapFailed(e: java.lang.Exception, errorDrawable: Drawable?) {
            emitter.onError(e)
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            emitter.onSuccess(bitmap)
        }
    }

    internal class ObservableTarget(private val emitter: ObservableEmitter<TargetState>) : Target {
        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            emitter.onNext(TargetState.PrepareLoad(placeHolderDrawable))
        }

        override fun onBitmapFailed(e: java.lang.Exception, errorDrawable: Drawable?) {
            emitter.onNext(TargetState.BitmapFailed(e, errorDrawable))
            emitter.onError(e)
        }

        override fun onBitmapLoaded(bitmap: Bitmap, from: Picasso.LoadedFrom) {
            emitter.onNext(TargetState.BitmapLoaded(bitmap, from))
            emitter.onComplete()
        }
    }
}
