package com.example.tictactoe.twitterExample.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tictactoe.BaseActivity
import com.example.tictactoe.R
import com.example.tictactoe.twitterExample.presenter.TwitterMainPresenter
import com.example.tictactoe.twitterExample.ui.screen_contracts.ITwitterMainListener
import com.example.tictactoe.twitterExample.utils.RecyclerViewItemAnimator
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import kotlinx.android.synthetic.main.activity_twitter_main.*
import twitter4j.*
import twitter4j.auth.AccessToken
import twitter4j.management.APIStatistics

class TwitterMainActivity : BaseActivity(), ITwitterMainListener {

    var TAG = "aaaa"


    override fun updateList() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_main)

        init()

        /* Socnet.init(this);
         SocialTwitter.getInstance().login("yRv4kyj6uDTGdVeTeJpR4MMa3","CBm9FXsaee93PueFbr005cgYcq0hM0Ad1aF5qC69scGJYyj0PY","https://www.google.com",
             CallBack.OnLogin {
                 if(it != null){
                     Log.i(TAG, it.toString())
                 }
             },
             SocialTwitter.onDialogCancelListner {
             } )

         btnSearch.setOnClickListener {
             SocialTwitter.getInstance().shareToTimeline(null,"test msg", CallBack.OnShare {
                 if(it != null){
                     Log.i(TAG, it.toString())
                 }
             })
         }*/


    }

    private fun init() {

        btnSearch.setOnClickListener {
            getTwitterObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getTwitterFinalObserver())

        }




        /*Observable.just {




        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { it -> Log.i("tweets", it.toString()) }
            .subscribe{
                Log.i("subscribe", it.toString())
            }*/


    }

    private fun getTwitterFinalObserver():Observer<MutableList<Status>>{


        return object : Observer<MutableList<Status>> {
            override fun onComplete() {

                Log.i("TAG", "subscribed")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i("TAG", "disposed")
            }

            override fun onNext(t: MutableList<Status>) {

                setRecyclerView(t)


            }

            override fun onError(e: Throwable) {
                Log.i("TAG", "error")
                e.printStackTrace()
            }

        }

    }
 /*   override fun onSubscribe(d: Disposable) {

        Log.i("TAG", "subscribed")
    }

    override fun onNext(t: MutableList<twitter4j.Status>) {
        Log.i("tweets", t.toString())



    }

    override fun onError(e: Throwable) {

        e.printStackTrace()
    }

}*/

    private fun setRecyclerView(t: MutableList<Status>) {

        rvTwitter.layoutManager = LinearLayoutManager(this)

        var loadAnimation:LayoutAnimationController = AnimationUtils.loadLayoutAnimation(this, R.anim.item_layout_animation_fall_down)

        rvTwitter.layoutAnimation = loadAnimation

        rvTwitter.itemAnimator = RecyclerViewItemAnimator()
        rvTwitter.adapter = TwitterAdapter(t)
    }

    private fun getTwitterObservable(): Observable<MutableList<twitter4j.Status>> {


//        return tweets


        return Observable.create { emitter ->

            var twitterMainPresenter = TwitterMainPresenter()
            twitterMainPresenter.setTwitterMainPresenter(this)
            twitterMainPresenter.getTwitterData()

            var queryString =  etSearchTwitter?.text?.toString()/* "Google"*/
            var twitterInstance = TwitterFactory().instance

            twitterInstance.setOAuthConsumer(
                "Rqs9yyvYT1fEAWyrKdwLSG76p",
                "DadGovflbwi5XD1Vbc5QrfaS9CeYqE9R55CHPYJBxOsc8GWKmE"
            )
            twitterInstance.oAuthAccessToken = AccessToken(
                "1141299687795781632-vMLl4qcOi44nsbhAhy9RdmZnoy7pRU",
                "CUHhiUBIaGk7i4x2rVjnypihJk9Y5MJdrnnAtX9UXCcnv"
            )

//        twitterInstance.auth
            var query = Query(queryString)

            query.count = 10

            var queryResult = twitterInstance.search(query)

            var tweets = queryResult.tweets
            Log.i("Inside values", tweets.toString())
            emitter.onNext(tweets)

//            var apiStateException = APIStatistics

         /*   for (normalTweets in tweets)


                if (!emitter.isDisposed) {
                    emitter.onNext(normalTweets)
                }*/
        }


    }

}