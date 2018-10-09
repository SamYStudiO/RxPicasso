package net.samystudio.rxpicasso

import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RxPicassoTest {
    @Mock
    lateinit var bitmap: Bitmap
    private val error = Exception("error")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun completableCallBack() {
        var success = false

        Completable.create { emitter ->
            val callback = RxPicasso.CompletableCallBack(emitter)
            callback.onSuccess()
        }.subscribe { success = true }

        assertTrue(success)

        Completable.create { emitter ->
            val callback = RxPicasso.CompletableCallBack(emitter)
            callback.onError(error)
        }.subscribe({ success = true }, {
            assertEquals(it, error)
            success = false
        })

        assertFalse(success)
    }

    @Test
    fun singleTarget() {
        var success = false

        Single.create<Bitmap> { emitter ->
            val callback = RxPicasso.SingleTarget(emitter)
            callback.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        }.subscribe { b ->
            assertEquals(this.bitmap, b)
            success = true
        }

        assertTrue(success)

        Single.create<Bitmap> { emitter ->
            val callback = RxPicasso.SingleTarget(emitter)
            callback.onBitmapFailed(error, null)
        }.subscribe({ success = true }, {
            assertEquals(it, error)
            success = false
        })

        assertFalse(success)
    }

    @Test
    fun observableTarget() {
        var completed = false
        var count = 0

        Observable.create<TargetState> { emitter ->
            val callback = RxPicasso.ObservableTarget(emitter)
            callback.onPrepareLoad(null)
            count++
            callback.onBitmapLoaded(bitmap, Picasso.LoadedFrom.MEMORY)
        }.subscribe({
            if (count == 0) assertTrue(it is TargetState.PrepareLoad)
            else assertTrue(it is TargetState.BitmapLoaded)
        }, { }, { completed = true })

        assertTrue(completed)

        completed = false

        Observable.create<TargetState> { emitter ->
            val callback = RxPicasso.ObservableTarget(emitter)
            callback.onBitmapFailed(error, null)
        }.subscribe({ assertTrue(it is TargetState.BitmapFailed) },
            { assertEquals(it, error) },
            { completed = true })

        assertFalse(completed)
    }
}
