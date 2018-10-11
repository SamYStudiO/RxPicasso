RxPicasso
=========
[Reactive](https://github.com/ReactiveX/RxJava) [Picasso](https://github.com/square/picasso) callbacks.

Download
--------
**Java**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso:0.2.0'
```
**Kotlin**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso-kotlin:0.2.0'
```

Snapshots are available from [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/).
If you want to run latest snapshot add its repository from your root `build.gradle`:
```groovy
allprojects {
    repositories {
        google()
        jcenter()
        ...
        maven { url "https://oss.sonatype.org/content/repositories/snapshots" }
    }
}
```
and change library version:

**Java**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso:0.3.0-SNAPSHOT'
```
**Kotlin**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso-kotlin:0.3.0-SNAPSHOT'
```

Usage
-----
**Java:**
```java
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
RxPicasso
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
        });

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
```
**Kotlin:**
```kotlin
// load image into an imageView
picasso
    .observeInto(picasso.load(path), imageView)
    .subscribe({
        // image loaded
    }, {
        it.printStackTrace()
    })

// load image into remote view (notification)
picasso
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
    })

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
```

License
-------

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.