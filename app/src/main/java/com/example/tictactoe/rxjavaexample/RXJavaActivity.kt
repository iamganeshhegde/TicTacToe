package com.example.tictactoe.rxjavaexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import com.example.tictactoe.rxjavaexample.model.WeatherCurrent
import com.example.tictactoe.rxjavaexample.networking.RestApiImpl
import com.example.tictactoe.rxjavaexample.utils.Constants
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_java.*

class RXJavaActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var animalObservable: Observable<String>
    lateinit var observer: Observer<String>
    lateinit var allCapsObserver: Observer<String>
    lateinit var disposable: CompositeDisposable
    lateinit var restApi: RestApiImpl


    override fun onClick(v: View?) {

        if (v != null) {
            when (v.id) {
                R.id.btnStartRXJava -> {
                    displayData()
                    toast("Hi")
                }

                R.id.rxJavaModel -> {
                    displayModelData()
                }

                R.id.rangeRxJava -> {
                    displayRangeData()
                }

                R.id.btnPredictWeather -> {
                    callWeatherMethod()
                }

                else ->{

                }


            }
        }
    }

    private fun callWeatherMethod() {

        restApi = RestApiImpl(this)

        restApi.getCurrentWeather(Constants.WEATHER_API_KEY, "Bangalore")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { disposable.add(it) }
            .doOnNext { updateCurrentWeather(it) }
            .doOnError { Log.i(TAG, it.message) }
            .doOnComplete { Log.i(TAG, "completed emitting") }
            .subscribe()


    }

    private fun updateCurrentWeather(it: WeatherCurrent?) {

        var tempC = it?.getCurrent()?.getTempC()

        tvCurrentWeather.text = tempC.toString()

    }

    private fun displayRangeData() {


        Observable.range(0, 10).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { Log.i(TAG, it.toString()) }.doOnComplete { Log.i(TAG, "completed") }
            .doOnError { Log.i(TAG, it.message.toString()) }.doOnSubscribe { Log.i(TAG, "subscribed") }.subscribe()
//            .subscribe( { count -> (Log.i(TAG, count.toString()))},{ error ->(Log.i(TAG,error.toString()))})


        /*    Observable.range(0, 20).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    Log.i(TAG, it.toString())
                })*/
    }


    private fun displayModelData() {


        getNotesObservable().observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())

            .map {
                it.text = it.text.toUpperCase()
                return@map it
            }
            /*.map(object :  io.reactivex.functions.Function< Note, Note> {
                override fun apply(t: Note): Note {

                    //make all note to uppercase
                    t.text = t.text.toUpperCase()
                    return t

                }
(
            })*/
            .subscribeWith(getNotesObserver())

    }

    private fun getNotesObserver(): Observer<Note> {

        return object : Observer<Note> {
            override fun onComplete() {
                Log.i(TAG, "onComplete notes : All items are emitted")


            }

            override fun onSubscribe(d: Disposable) {
                Log.i(TAG, "onSubscribe notes : subscribe")
                disposable.add(d)
            }

            override fun onNext(t: Note) {
                Log.i(TAG, "onNext notes : " + t.text)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError notes : " + e.message)
            }

        }

    }

    private fun getNotesObservable(): Observable<Note> {

        var notesList: List<Note> = prepareNotes()

        return Observable.create { emitter ->
            for (note: Note in notesList) {
                if (!emitter.isDisposed) {
                    emitter.onNext(note)
                }
            }

            if (!emitter.isDisposed) {
                emitter.onComplete()
            }

        }
    }

    private fun prepareNotes(): List<Note> {

        var notes = ArrayList<Note>()

        notes.add(Note(1, "Buy toothPaste"))
        notes.add(Note(2, "CAll brother"))
        notes.add(Note(3, "Pay roomrent"))
        notes.add(Note(4, "Sleep"))

        return notes

    }

    fun displayData() {

        animalObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            //filtering out
//            .filter { it.toLowerCase().startsWith("b") }

            //2nd type of filter
            .filter(object : io.reactivex.functions.Predicate<String> {
                override fun test(t: String): Boolean {

                    return t.toLowerCase().startsWith("b")
                }

            }).subscribeWith(observer)
//            .subscribeWith(observer))


        animalObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .filter { it.toLowerCase().startsWith("c") }
            .map { it.toUpperCase() }
            .subscribeWith(allCapsObserver)


    }

    fun Context.toast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    var TAG = RXJavaActivity::class.java.name


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)

        btnStartRXJava.setOnClickListener(this)
        rxJavaModel.setOnClickListener(this)
        rangeRxJava.setOnClickListener(this)
        btnPredictWeather.setOnClickListener(this)

        disposable = CompositeDisposable()

        animalObservable = getObservable()

        allCapsObserver = getAllAnimalsCapsObserver()

        observer = getAnimalObserver()


    }

    private fun getAllAnimalsCapsObserver(): Observer<String> {

        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {
                disposable.add(d)

            }

            override fun onComplete() {
                Log.i(TAG, "onComplete allCaps : All items are emitted")
            }

            override fun onNext(t: String) {
                Log.i(TAG, "onNext allCaps: Message: " + t)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError allCaps" + e.message)
            }
        }


    }

    private fun getObservable(): Observable<String> {

        //for multiple elements
        return Observable.fromArray(
            "Ant",
            "Bat",
            "Bee",
            "Bear",
            "Butterfly",
            "Cat",
            "Dog",
            "Fox",
            "aa,",
            "bb",
            "cc",
            "dd",
            "ee",
            "ff",
            "gg"
        )

        //for simple elements
        return Observable.just("Ant", "Bat", "Bee", "Bear", "Butterfly", "Cat", "Dog", "Fox")
    }

    private fun getAnimalObserver(): Observer<String> {


        return object : Observer<String> {
            override fun onSubscribe(d: Disposable) {

                disposable.add(d)
            }

            override fun onComplete() {

                Log.i(TAG, "onComplete : All items are emitted")

            }


            override fun onNext(t: String) {
                Log.i(TAG, "onNext : Message: " + t)
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, "onError" + e.message)
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()


        if (disposable != null && !disposable.isDisposed) {
            disposable.clear()
        }

    }

}

