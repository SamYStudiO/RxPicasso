package net.samystudio.rxpicasso.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso3.Picasso;

import net.samystudio.rxpicasso.BitmapTargetState;
import net.samystudio.rxpicasso.RxPicasso;

import androidx.appcompat.app.AppCompatActivity;

@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class MainActivityJava extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Picasso picasso = new Picasso.Builder(this).build();
        String path = "https://avatars0.githubusercontent.com/u/3678076?s=400&u=6d8288bcdd22f9ce85e3c12d49bfeaac54a035f9&v=4";
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        setContentView(imageView);

        // load image into an imageView
        RxPicasso
                .observeInto(picasso, path, imageView)
                .subscribe(() -> {
                    // image loaded
                }, Throwable::printStackTrace);

        // load image into an imageView building request directly from stream
        RxPicasso
                .observeInto(picasso, path, imageView)
                .resize(500, 500)
                .rotate(180)
                .subscribe(() -> {
                    // image loaded
                }, Throwable::printStackTrace);

        // load image into remote view (notification)
        /*RxPicasso
                .observeInto(picasso, picasso.load(path), remoteViews, viewId, notficationId, notification, notificationTag)
                .subscribe(() -> {
                    // image loaded
                }, Throwable::printStackTrace);

        // load image into remote view (notification)
        RxPicasso
                .observeInto(picasso, picasso.load(path), remoteViews, viewId, appWidgetIds)
                .subscribe(() -> {
                    // image loaded
                }, Throwable::printStackTrace);*/

        // load image into a bitmap
        RxPicasso
                .observeIntoBitmap(picasso, path)
                .subscribe(bitmap -> {
                    // bitmap loaded
                }, Throwable::printStackTrace);

        // load image into a Picasso Target
        RxPicasso
                .observeIntoBitmapTarget(picasso, path)
                .subscribe(bitmapTargetState -> {
                    if (bitmapTargetState instanceof BitmapTargetState.PrepareLoad) {
                        // do something with targetState.placeHolderDrawable
                    }
                    if (bitmapTargetState instanceof BitmapTargetState.BitmapFailed) {
                        // do something with targetState.errorDrawable
                    }
                    if (bitmapTargetState instanceof BitmapTargetState.BitmapLoaded) {
                        // do something with targetState.bitmap or targetState.from
                    }
                }, Throwable::printStackTrace, () -> { /*completed*/ });

        // fetch image into cache
        RxPicasso
                .observeFetch(picasso, path)
                .subscribe(() -> {
                    // image loaded into cache
                }, Throwable::printStackTrace);
    }
}
