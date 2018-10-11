@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, ImageView)
 */
fun Picasso.observeInto(requestCreator: RequestCreator, imageView: ImageView) =
    RxPicasso.observeInto(this, requestCreator, imageView)

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
 * [net.samystudio.rxpicasso.RxPicasso.observeInto] (Picasso, RequestCreator, RemoteViews, Int, IntArray)
 */
fun Picasso.observeInto(
    requestCreator: RequestCreator,
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeInto(this, requestCreator, remoteViews, viewId, appWidgetIds)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoBitmap]
 */
fun Picasso.observeIntoBitmap(requestCreator: RequestCreator) =
    RxPicasso.observeIntoBitmap(this, requestCreator)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeIntoTarget]
 */
fun Picasso.observeIntoTarget(requestCreator: RequestCreator) =
    RxPicasso.observeIntoTarget(this, requestCreator)

/**
 * [net.samystudio.rxpicasso.RxPicasso.observeFetch]
 */
fun Picasso.observeFetch(requestCreator: RequestCreator, tag: Any? = null) =
    RxPicasso.observeFetch(this, requestCreator, tag)