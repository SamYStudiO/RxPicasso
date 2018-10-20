package net.samystudio.rxpicasso

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.squareup.picasso3.Picasso
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
        var listener: RequestIntoCompletable.Listener =
            RequestIntoCompletable.Listener(observer, null, null)
        observer.onSubscribe(listener)
        listener.onSuccess()
        observer.assertComplete()

        observer = TestObserver()
        listener = RequestIntoCompletable.Listener(observer, null, null)
        observer.onSubscribe(listener)
        listener.onError(error)
        observer.assertError(error)
        observer.assertNotComplete()
    }

    @Test
    fun singleBitmapTarget() {
        var observer = TestObserver<Bitmap>()
        var listener: RequestIntoBitmapSingle.Listener =
            RequestIntoBitmapSingle.Listener(observer, null)
        observer.onSubscribe(listener)
        listener.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        observer.assertValue(bitmap)
        observer.assertComplete()

        observer = TestObserver()
        listener = RequestIntoBitmapSingle.Listener(observer, null)
        observer.onSubscribe(listener)
        listener.onBitmapFailed(error, null)
        observer.assertError(error)
        observer.assertNotComplete()
    }

    @Test
    fun observableBitmapTarget() {
        var observer = TestObserver<BitmapTargetState>()
        var listener: RequestIntoBitmapTargetObservable.Listener =
            RequestIntoBitmapTargetObservable.Listener(observer, null)
        observer.onSubscribe(listener)
        listener.onPrepareLoad(drawablePlaceHolder)
        listener.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        observer.assertValueAt(0) { t -> t is BitmapTargetState.PrepareLoad && t.placeHolderDrawable == drawablePlaceHolder }
        observer.assertValueAt(1) { t -> t is BitmapTargetState.BitmapLoaded && t.bitmap == bitmap }
        observer.assertComplete()

        observer = TestObserver()
        listener = RequestIntoBitmapTargetObservable.Listener(observer, null)
        observer.onSubscribe(listener)
        listener.onPrepareLoad(drawablePlaceHolder)
        listener.onBitmapFailed(error, drawableError)
        observer.assertValueAt(0) { t -> t is BitmapTargetState.PrepareLoad && t.placeHolderDrawable == drawablePlaceHolder }
        observer.assertValueAt(1) { t -> t is BitmapTargetState.BitmapFailed && t.errorDrawable == drawableError }
        observer.assertError(error)
        observer.assertNotComplete()
    }
}
