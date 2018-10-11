@file:SuppressLint("CheckResult")

package net.samystudio.rxpicasso.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import net.samystudio.rxpicasso.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val picasso = Picasso.Builder(this).build()
        val path =
            "https://avatars0.githubusercontent.com/u/3678076?s=400&u=6d8288bcdd22f9ce85e3c12d49bfeaac54a035f9&v=4"
        val imageView = ImageView(this)
        setContentView(imageView)

        // load image into an imageView
        picasso
            .observeInto(picasso.load(path), imageView)
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
            .observeIntoBitmap(picasso.load(path))
            .subscribe({ bitmap ->
                // bitmap loaded
            }, {
                it.printStackTrace()
            })

        // load image into a Picasso Target
        picasso
            .observeIntoTarget(picasso.load(path))
            .subscribe({ state: TargetState ->
                when (state) {
                    is TargetState.PrepareLoad -> {
                        // do something with state.placeHolderDrawable
                    }
                    is TargetState.BitmapFailed -> {
                        // do something with state.errorDrawable
                    }
                    is TargetState.BitmapLoaded -> {
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
            .observeFetch(picasso.load(path))
            .subscribe({
                // image loaded into cache
            }, {
                it.printStackTrace()
            })
    }
}
