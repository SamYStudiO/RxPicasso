@file:Suppress("unused")

package net.samystudio.rxpicasso

import android.app.Notification
import android.support.annotation.IdRes
import android.widget.ImageView
import android.widget.RemoteViews
import com.squareup.picasso.RequestCreator

/**
 * [com.squareup.picasso.RequestCreator.into] (ImageView, CallBack)
 */
fun RequestCreator.observeInto(imageView: ImageView) = RxPicasso.requestInto(this, imageView)

/**
 * [com.squareup.picasso.RequestCreator.into] (RemoteViews, int, int, Notification, String, Callback)
 */
fun RequestCreator.observeInto(
    remoteViews: RemoteViews, @IdRes viewId: Int,
    notificationId: Int,
    notification: Notification,
    notificationTag: String
) = RxPicasso.requestInto(
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
) = RxPicasso.requestInto(this, remoteViews, viewId, appWidgetIds)

/**
 * [com.squareup.picasso.RequestCreator.into] (Target)
 */
fun RequestCreator.observeIntoBitmap() = RxPicasso.requestIntoBitmap(this)

/**
 * [com.squareup.picasso.RequestCreator.into] (Target)
 */
fun RequestCreator.observeIntoTarget() = RxPicasso.requestIntoTarget(this)

/**
 * [com.squareup.picasso.RequestCreator.fetch] (Callback)
 */
fun RequestCreator.observeFetch() = RxPicasso.requestFetch(this)
