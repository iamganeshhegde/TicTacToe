package com.example.tictactoe.db

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.R
import com.example.tictactoe.db.dao.GenderDao
import com.example.tictactoe.db.database.AppDatabase
import com.example.tictactoe.db.dataclass.Gender
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_database.*
import java.util.*

class DatabaseActivity:AppCompatActivity() {

    var db: AppDatabase? = null

    var genderDao: GenderDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_database)


       /* Observable.fromCallable({
            db = AppDatabase.getAppDatabase(context = this)

            genderDao = db?.genderDao()


            var gender1 = Gender(name = "Male")
            var gender2 = Gender(name = "Female")

            with(genderDao){
                this?.insertGender(gender1)

                this?.insertGender(gender2)

            }

            db?.genderDao()?.getAllGender()


        } ).doOnNext({
            var finalLastString = ""
            it?.map { finalLastString+= it.name+" - " }

            genderTV.text = finalLastString
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()*/

    }
}