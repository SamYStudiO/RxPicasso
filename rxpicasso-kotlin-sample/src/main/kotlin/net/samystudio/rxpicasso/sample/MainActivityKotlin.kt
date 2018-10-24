@file:SuppressLint("CheckResult")

package net.samystudio.rxpicasso.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso3.Picasso
import net.samystudio.rxpicasso.*

class MainActivityKotlin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val picasso = Picasso.Builder(this).build()
        val path =
            "https://avatars0.githubusercontent.com/u/3678076?s=400&u=6d8288bcdd22f9ce85e3c12d49bfeaac54a035f9&v=4"
        val imageView = ImageView(this)
        setContentView(imageView)

        // load image into an imageView
        picasso
            .observeInto(path, imageView)
            .subscribe({
                // image loaded
            }, {
                it.printStackTrace()
            })

        // load image into an imageView building request directly from stream
        picasso
            .observeInto(path, imageView)
            .resize(500, 500)
            .rotate(180f)
            .subscribe({
                // image loaded
            }, {
                it.printStackTrace()
            })

        // load image into remote view (notification)
        /*picasso
            .observeInto(picasso.load(path), remoteViews, viewId, notficationId, notification, notificationTag)
            .subscribe({
                // image loaded
            }, {
                it.printStackTrace()
            })

        // load image into remote view (notification)
        picasso
            .observeInto(picasso.load(path), remoteViews, viewId, appWidgetIds)
            .subscribe({
                // image loaded
            }, {
                it.printStackTrace()
            })*/

        // load image into a bitmap
        picasso
            .observeIntoBitmap(path)
            .subscribe({ bitmap ->
                // bitmap loaded
            }, {
                it.printStackTrace()
            })

        // load image into a Picasso Target
        picasso
            .observeIntoBitmapTarget(path)
            .subscribe({ state: BitmapTargetState ->
                when (state) {
                    is BitmapTargetState.PrepareLoad -> {
                        // do something with state.placeHolderDrawable
                    }
                    is BitmapTargetState.BitmapFailed -> {
                        // do something with state.errorDrawable
                    }
                    is BitmapTargetState.BitmapLoaded -> {
                        // do something with state.bitmap or state.from
                    }
                }
            }, {
                it.printStackTrace()
            }, {
                // completed
            })

        // fetch image into cache
        picasso
            .observeFetch(path)
            .subscribe({
                // image loaded into cache
            }, {
                it.printStackTrace()
            })
    }
}
