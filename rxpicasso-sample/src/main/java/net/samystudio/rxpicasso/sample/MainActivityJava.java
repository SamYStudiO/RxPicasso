package net.samystudio.rxpicasso.sample;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import net.samystudio.rxpicasso.RxPicasso;
import net.samystudio.rxpicasso.TargetState;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

@SuppressWarnings("ResultOfMethodCallIgnored")
@SuppressLint("CheckResult")
public class MainActivityJava extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Picasso picasso = new Picasso.Builder(this).build();
        String path = "https://avatars0.githubusercontent.com/u/3678076?s=400&u=6d8288bcdd22f9ce85e3c12d49bfeaac54a035f9&v=4";
        ImageView imageView = new ImageView(this);
        setContentView(imageView);

        // load image into an imageView
        RxPicasso
                .observeInto(picasso, picasso.load(path), imageView)
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        // image loaded
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

        // load image into remote view (notification)
        /*RxPicasso
                .observeInto(picasso, picasso.load(path), remoteViews, viewId, notficationId, notification, notificationTag)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        // image loaded
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

        // load image into remote view (notification)
        RxPicasso
                .observeInto(picasso, picasso.load(path), remoteViews, viewId, appWidgetIds)
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        // image loaded
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });*/

        // load image into a bitmap
        RxPicasso
                .observeIntoBitmap(picasso, picasso.load(path))
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) {
                        // bitmap loaded
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });

        // load image into a Picasso Target
        RxPicasso
                .observeIntoTarget(picasso, picasso.load(path))
                .subscribe(new Observer<TargetState>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TargetState targetState) {
                        if (targetState instanceof TargetState.PrepareLoad) {
                            // do something with targetState.placeHolderDrawable
                        }

                        if (targetState instanceof TargetState.BitmapFailed) {
                            // do something with targetState.errorDrawable
                        }

                        if (targetState instanceof TargetState.BitmapLoaded) {
                            // do something with targetState.bitmap or targetState.from
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        // completed
                    }
                });

        // fetch image into cache
        RxPicasso
                .observeFetch(picasso, picasso.load(path))
                .subscribe(new Action() {
                    @Override
                    public void run() {
                        // image loaded into cache
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}
