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

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(uri: Uri?, imageView: ImageView) =
    RxPicasso.observeInto(this, uri, imageView)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(string: String?, imageView: ImageView) =
    RxPicasso.observeInto(this, string, imageView)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(file: File?, imageView: ImageView) =
    RxPicasso.observeInto(this, file, imageView)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(@DrawableRes resourceId: Int, imageView: ImageView) =
    RxPicasso.observeInto(this, resourceId, imageView)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(requestCreator: RequestCreator, imageView: ImageView) =
    RxPicasso.observeInto(this, requestCreator, imageView)

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, Int, Notification, String, Callback)
 */
fun Picasso.observeInto(
    uri: Uri?,
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeInto(
    this,
    uri,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, Int, Notification, String, Callback)
 */
fun Picasso.observeInto(
    path: String?,
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeInto(
    this,
    path,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, Int, Notification, String, Callback)
 */
fun Picasso.observeInto(
    file: File?,
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeInto(
    this,
    file,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, Int, Notification, String, Callback)
 */
fun Picasso.observeInto(
    @DrawableRes resourceId: Int,
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeInto(
    this,
    resourceId,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, Int, Notification, String, Callback)
 */
fun Picasso.observeInto(
    requestCreator: RequestCreator,
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeInto(
    this,
    requestCreator,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    uri: Uri?,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, uri, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    path: String?,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, path, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    file: File?,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, file, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    @DrawableRes resourceId: Int,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, resourceId, remoteViews, viewId, appWidgetIds)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    requestCreator: RequestCreator,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, requestCreator, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(uri: Uri?) = RxPicasso.observeIntoBitmap(this, uri)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(path: String?) = RxPicasso.observeIntoBitmap(this, path)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(file: File?) = RxPicasso.observeIntoBitmap(this, file)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(@DrawableRes resourceId: Int) =
    RxPicasso.observeIntoBitmap(this, resourceId)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(requestCreator: RequestCreator) =
    RxPicasso.observeIntoBitmap(this, requestCreator)

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmapTarget]
 */
fun Picasso.observeIntoBitmapTarget(uri: Uri?) = RxPicasso.observeIntoBitmapTarget(this, uri)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmapTarget]
 */
fun Picasso.observeIntoBitmapTarget(path: String?) = RxPicasso.observeIntoBitmapTarget(this, path)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmapTarget]
 */
fun Picasso.observeIntoBitmapTarget(file: File?) = RxPicasso.observeIntoBitmapTarget(this, file)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmapTarget]
 */
fun Picasso.observeIntoBitmapTarget(@DrawableRes resourceId: Int) =
    RxPicasso.observeIntoBitmapTarget(this, resourceId)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmapTarget]
 */
fun Picasso.observeIntoBitmapTarget(requestCreator: RequestCreator) =
    RxPicasso.observeIntoBitmapTarget(this, requestCreator)

/**
 * [com.squareup.picasso3.Picasso.load] (Uri)
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(uri: Uri?) = RxPicasso.observeFetch(this, uri)

/**
 * [com.squareup.picasso3.Picasso.load] (String)
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(path: String?) = RxPicasso.observeFetch(this, path)

/**
 * [com.squareup.picasso3.Picasso.load] (File)
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(file: File?) = RxPicasso.observeFetch(this, file)

/**
 * [com.squareup.picasso3.Picasso.load] (Int)
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(@DrawableRes resourceId: Int) = RxPicasso.observeFetch(this, resourceId)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(requestCreator: RequestCreator) =
    RxPicasso.observeFetch(this, requestCreator)