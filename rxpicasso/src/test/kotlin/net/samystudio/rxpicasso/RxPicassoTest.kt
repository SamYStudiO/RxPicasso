package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso3.Picasso
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RxPicassoTest {
    @Mock
    lateinit var bitmap: Bitmap
    @Mock
    lateinit var drawablePlaceHolder: Drawable
    @Mock
    lateinit var drawableError: Drawable
    private val error = Exception("error")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun completableCallBack() {
        var observer = TestObserver<Unit>()
        Completable.create { emitter ->
            val callback = RxPicasso.CompletableCallBack(emitter)
            callback.onSuccess()
        }.subscribe(observer)
        observer.assertComplete()

        observer = TestObserver()
        Completable.create { emitter ->
            val callback = RxPicasso.CompletableCallBack(emitter)
            callback.onError(error)
        }.subscribe(observer)
        observer.assertError(error)
        observer.assertNotComplete()
    }

    @Test
    fun singleBitmapTarget() {
        var observer = TestObserver<Bitmap>()
        Single.create<Bitmap> { emitter ->
            val callback = RxPicasso.SingleTarget(emitter)
            callback.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        }.subscribe(observer)
        observer.assertValue(bitmap)
        observer.assertComplete()

        observer = TestObserver()
        Single.create<Bitmap> { emitter ->
            val callback = RxPicasso.SingleTarget(emitter)
            callback.onBitmapFailed(error, null)
        }.subscribe(observer)
        observer.assertError(error)
        observer.assertNotComplete()
    }

    @Test
    fun observableBitmapTarget() {
        var observer = TestObserver<BitmapTargetState>()
        Observable.create<BitmapTargetState> { emitter ->
            val callback = RxPicasso.ObservableTarget(emitter)
            callback.onPrepareLoad(drawablePlaceHolder)
            callback.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        }.subscribe(observer)
        observer.assertValueAt(0) { t -> t is BitmapTargetState.PrepareLoad && t.placeHolderDrawable == drawablePlaceHolder }
        observer.assertValueAt(1) { t -> t is BitmapTargetState.BitmapLoaded && t.bitmap == bitmap }
        observer.assertComplete()

        observer = TestObserver()
        Observable.create<BitmapTargetState> { emitter ->
            val callback = RxPicasso.ObservableTarget(emitter)
            callback.onPrepareLoad(drawablePlaceHolder)
            callback.onBitmapFailed(error, drawableError)
        }.subscribe(observer)
        observer.assertValueAt(1) { t -> t is BitmapTargetState.BitmapFailed && t.errorDrawable == drawableError }
        observer.assertError(error)
        observer.assertNotComplete()
    }
}
