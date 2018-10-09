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
     * [com.squareup.picasso.RequestCreator.into] (ImageView, CallBack)
     */
    @JvmStatic
    fun observeRequestInto(requestCreator: RequestCreator, imageView: ImageView): Completable =
        Completable.create { emitter ->
            requestCreator.into(
                imageView,
                CompletableCallBack(emitter)
            )
        }

    /**
     * [com.squareup.picasso.RequestCreator.into] (RemoteViews, int, int, Notification, String, Callback)
     */
    @JvmStatic
    fun observeRequestInto(
        requestCreator: RequestCreator,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ): Completable = Completable.create { emitter ->
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
     * [com.squareup.picasso.RequestCreator.into] (RemoteViews, int, IntArray)
     */
    @JvmStatic
    fun observeRequestInto(
        requestCreator: RequestCreator,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ): Completable = Completable.create { emitter ->
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
    fun observeRequestIntoBitmap(requestCreator: RequestCreator): Single<Bitmap> =
        Single.create { emitter -> requestCreator.into(SingleTarget(emitter)) }

    /**
     * [com.squareup.picasso.RequestCreator.into] (Target)
     */
    @JvmStatic
    fun observeRequestIntoTarget(requestCreator: RequestCreator): Observable<TargetState> =
        Observable.create { emitter -> requestCreator.into(ObservableTarget(emitter)) }

    /**
     * [com.squareup.picasso.RequestCreator.fetch] (Callback)
     */
    @JvmStatic
    fun observeRequestFetch(requestCreator: RequestCreator): Completable =
        Completable.create { emitter -> requestCreator.fetch(CompletableCallBack(emitter)) }

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
