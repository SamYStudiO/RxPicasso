RxPicasso
=========
[Reactive](https://github.com/ReactiveX/RxJava) [Picasso](https://github.com/square/picasso) callbacks.

**Warning**: this library is only compatible with Picasso 3 which is under development for now and thus this library api may changes drastically as Picasso 3 makes its own changes.

Download
--------
**Java**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso:0.3.0'
```
**Kotlin**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso-kotlin:0.3.0'
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
implementation 'net.samystudio.rxpicasso:rxpicasso:0.4.0-SNAPSHOT'
```
**Kotlin**
```groovy
implementation 'net.samystudio.rxpicasso:rxpicasso-kotlin:0.4.0-SNAPSHOT'
```

Usage
-----
**Java:**
```java
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

// load image into a bitmap
RxPicasso
        .observeIntoBitmap(picasso, path)
        .subscribe(bitmap -> {
            // bitmap loaded
        }, Throwable::printStackTrace);
```
Check [rxpicasso-sample](https://github.com/SamYStudiO/RxPicasso/blob/master/rxpicasso-sample/src/main/java/net/samystudio/rxpicasso/sample/MainActivityJava.java) for more examples.

**Kotlin:**
```kotlin
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

// load image into a bitmap
picasso
    .observeIntoBitmap(path)
    .subscribe({ bitmap ->
        // bitmap loaded
    }, {
        it.printStackTrace()
    })
```
Check [rxpicasso-kotlin-sample](https://github.com/SamYStudiO/RxPicasso/blob/master/rxpicasso-kotlin-sample/src/main/kotlin/net/samystudio/rxpicasso/sample/MainActivityKotlin.kt) for more examples.

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
