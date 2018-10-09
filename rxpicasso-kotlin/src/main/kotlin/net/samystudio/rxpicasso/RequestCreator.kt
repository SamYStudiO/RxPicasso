@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.widget.ImageView
import android.widget.RemoteViews
import androidx.annotation.IdRes
import com.squareup.picasso.RequestCreator

/**
 * [com.squareup.picasso.RequestCreator.into] (ImageView, CallBack)
 */
fun RequestCreator.observeInto(imageView: ImageView) = RxPicasso.observeRequestInto(this, imageView)

/**
 * [com.squareup.picasso.RequestCreator.into] (RemoteViews, int, int, Notification, String, Callback)
 */
fun RequestCreator.observeInto(
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.observeRequestInto(
    this,
    remoteViews,
    viewId,
    notificationId,
    notification,
    notificationTag
)

/**
 * [com.squareup.picasso.RequestCreator.into] (RemoteViews, int, IntArray)
 */
fun RequestCreator.observeInto(
    remoteViews: RemoteViews,
    @IdRes viewId: Int,
    appWidgetIds: IntArray
) = RxPicasso.observeRequestInto(this, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso.RequestCreator.into] (Target)
 */
fun RequestCreator.observeIntoBitmap() = RxPicasso.observeRequestIntoBitmap(this)

/**
 * [com.squareup.picasso.RequestCreator.into] (Target)
 */
fun RequestCreator.observeIntoTarget() = RxPicasso.observeRequestIntoTarget(this)

/**
 * [com.squareup.picasso.RequestCreator.fetch] (Callback)
 */
fun RequestCreator.observeFetch() = RxPicasso.observeRequestFetch(this)
