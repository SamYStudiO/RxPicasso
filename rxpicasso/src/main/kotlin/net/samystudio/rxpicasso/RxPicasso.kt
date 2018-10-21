@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.net.Uri
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.squareup.picasso3.Picasso
import com.squareup.picasso3.RequestCreator
import java.io.File

object RxPicasso {
    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(picasso: Picasso, uri: Uri?, imageView: ImageView) =
        RequestIntoCompletable(picasso, picasso.load(uri), imageView)

    /**
     * [Picasso.load] (String)
     * [RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(picasso: Picasso, path: String?, imageView: ImageView) =
        RequestIntoCompletable(
            picasso,
            picasso.load(path),
            imageView
        )

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(picasso: Picasso, file: File?, imageView: ImageView) =
        RequestIntoCompletable(picasso, picasso.load(file), imageView)

    /**
     * [Picasso.load] (Int)
     * [RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(picasso: Picasso, @DrawableRes resourceId: Int, imageView: ImageView) =
        RequestIntoCompletable(picasso, picasso.load(resourceId), imageView)

    /**
     * [RequestCreator.into] (ImageView)
     */
    @JvmStatic
    fun observeInto(picasso: Picasso, requestCreator: RequestCreator, imageView: ImageView) =
        RequestIntoCompletable(picasso, requestCreator, imageView)

    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        uri: Uri?,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(uri),
        remoteViews,
        viewId,
        notificationId,
        notification,
        notificationTag
    )

    /**
     * [Picasso.load] (String)
     * [RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        path: String?,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(path),
        remoteViews,
        viewId,
        notificationId,
        notification,
        notificationTag
    )

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        file: File?,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(file),
        remoteViews,
        viewId,
        notificationId,
        notification,
        notificationTag
    )

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        @DrawableRes resourceId: Int,
        remoteViews: RemoteViews,
        @IdRes viewId: Int,
        notificationId: Int,
        notification: Notification,
        notificationTag: String
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(resourceId),
        remoteViews,
        viewId,
        notificationId,
        notification,
        notificationTag
    )


    /**
     * [Picasso.load] (Int)
     * [RequestCreator.into] (RemoteViews, Int, Int, Notification, String)
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
    ) = RequestIntoCompletable(
        picasso,
        requestCreator,
        remoteViews,
        viewId,
        notificationId,
        notification,
        notificationTag
    )

    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        uri: Uri?,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(uri),
        remoteViews,
        viewId,
        appWidgetIds
    )

    /**
     * [Picasso.load] (String)
     * [RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        path: String?,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(path),
        remoteViews,
        viewId,
        appWidgetIds
    )

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        file: File?,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(file),
        remoteViews,
        viewId,
        appWidgetIds
    )

    /**
     * [Picasso.load] (Int)
     * [RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        @DrawableRes resourceId: Int,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) = RequestIntoCompletable(
        picasso,
        picasso.load(resourceId),
        remoteViews,
        viewId,
        appWidgetIds
    )

    /**
     * [RequestCreator.into] (RemoteViews, Int, IntArray)
     */
    @JvmStatic
    fun observeInto(
        picasso: Picasso,
        requestCreator: RequestCreator,
        remoteViews: RemoteViews, @IdRes viewId: Int,
        appWidgetIds: IntArray
    ) = RequestIntoCompletable(
        picasso,
        requestCreator,
        remoteViews,
        viewId,
        appWidgetIds
    )

    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, uri: Uri?) =
        RequestIntoBitmapSingle(picasso, picasso.load(uri))

    /**
     * [Picasso.load] (String)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, path: String?) =
        RequestIntoBitmapSingle(picasso, picasso.load(path))

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, file: File?) =
        RequestIntoBitmapSingle(picasso, picasso.load(file))

    /**
     * [Picasso.load] (Int)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, @DrawableRes resourceId: Int) =
        RequestIntoBitmapSingle(picasso, picasso.load(resourceId))

    /**
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmap(picasso: Picasso, requestCreator: RequestCreator) =
        RequestIntoBitmapSingle(picasso, requestCreator)

    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(picasso: Picasso, uri: Uri?) =
        RequestIntoBitmapTargetObservable(picasso, picasso.load(uri))

    /**
     * [Picasso.load] (String)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(picasso: Picasso, path: String?) =
        RequestIntoBitmapTargetObservable(picasso, picasso.load(path))

    /**
     * [Picasso.load] (File)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(picasso: Picasso, file: File?) =
        RequestIntoBitmapTargetObservable(picasso, picasso.load(file))

    /**
     * [Picasso.load] (Int)
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(picasso: Picasso, @DrawableRes resourceId: Int) =
        RequestIntoBitmapTargetObservable(picasso, picasso.load(resourceId))

    /**
     * [RequestCreator.into] (BitmapTarget)
     */
    @JvmStatic
    fun observeIntoBitmapTarget(picasso: Picasso, requestCreator: RequestCreator) =
        RequestIntoBitmapTargetObservable(picasso, requestCreator)

    /**
     * [Picasso.load] (Uri)
     * [RequestCreator.fetch]
     */
    @JvmStatic
    fun observeFetch(picasso: Picasso, uri: Uri?) =
        RequestIntoCompletable(picasso, picasso.load(uri))

    /**
     * [Picasso.load] (String)
     * [RequestCreator.fetch]
     */
    @JvmStatic
    fun observeFetch(picasso: Picasso, path: String?) =
        RequestIntoCompletable(picasso, picasso.load(path))

    /**
     * [Picasso.load] (File)
     * [RequestCreator.fetch]
     */
    @JvmStatic
    fun observeFetch(picasso: Picasso, file: File?) =
        RequestIntoCompletable(picasso, picasso.load(file))

    /**
     * [Picasso.load] (Int)
     * [RequestCreator.fetch]
     */
    @JvmStatic
    fun observeFetch(picasso: Picasso, @DrawableRes resourceId: Int) =
        RequestIntoCompletable(picasso, picasso.load(resourceId))

    /**
     * [RequestCreator.fetch]
     */
    @JvmStatic
    fun observeFetch(picasso: Picasso, requestCreator: RequestCreator) =
        RequestIntoCompletable(picasso, requestCreator)
}
