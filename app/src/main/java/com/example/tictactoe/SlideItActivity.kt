package com.example.tictactoe

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.slide_it_activity.*
import java.util.*
import kotlin.collections.ArrayList

class SlideItActivity : AppCompatActivity(), View.OnClickListener {


    lateinit var shakeAnimation: Animation

    lateinit var sliderArray: Array<Array<Button>>


    /* var answerArray = Array(3){Array(3){i ->

         i+1

     }}*/

    var innerArray1 = Array(3) { i -> i + 1 }
    var innerArray2 = Array(3) { i -> i + 4 }
    var innerArray3 = Array(3) { i -> i + 7 }


    var answerArray = arrayOf(arrayOf("1", "2", "3"), arrayOf("4", "5", "6"), arrayOf("7", "8", ""))


    var randomList: ArrayList<String> = ArrayList(9)


    override fun onClick(v: View?) {


//        if(shakeAnimation != null && (shakeAnimation.hasStarted() && !shakeAnimation.hasEnded()))
        {
            //                shakeAnimation.cancel()
        }

        var leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right);

        var rightToLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left)
//        v?.startAnimation(leftToRight);

//        v?.startAnimation(shakeAnimation)

//        Toast.makeText(this, (v as Button).text.toString(), Toast.LENGTH_SHORT).show()

        if ((v as Button).text.toString().equals("")) {

            v.startAnimation(shakeAnimation)
            return
        }


        swapPosition(v, (v as Button).text.toString())


        /*   for( i in 0..2)
           {
               for(j in 0..2)
               {

                   if(sliderArray[i][j].equals(""))
                   {
                       var positionOfBlank = sliderArray[i][j];

                   }

   //                (v as Button).text.toString()

               }
           }*/

    }

    private fun swapPosition(clickedButton: Button, clickedValue: String) {

        var leftToRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right)
        var rightToLeft = AnimationUtils.loadAnimation(this, R.anim.right_to_left)
        var topToBottom = AnimationUtils.loadAnimation(this, R.anim.top_to_bottom)
        var bottomToTop = AnimationUtils.loadAnimation(this, R.anim.bottom_to_top)


        var positionOfBlank: Button? = null
        var clickedValueInArray: Button? = null
        var clickedRow: Int = 0
        var clickedColumn: Int = 0

        for (i in 0..2) {
            for (j in 0..2) {

                positionOfBlank = Button(this)
                if (sliderArray[i][j].equals("")) {


                    positionOfBlank = sliderArray[i][j];

                }

                if (sliderArray[i][j].text.toString() == clickedValue) {

                    clickedRow = i;
                    clickedColumn = j;
                    clickedValueInArray = sliderArray[i][j]
                }
            }
        }
        var top: Button? = null
        var bottom: Button? = null
        var left: Button? = null
        var right: Button? = null


        if (clickedRow != 0) {
            top = sliderArray[clickedRow - 1][clickedColumn]
        }

        if (clickedRow != 2) {
            bottom = sliderArray[clickedRow + 1][clickedColumn]
        }

        if (clickedColumn != 0) {
            left = sliderArray[clickedRow][clickedColumn - 1]
        }

        if (clickedColumn != 2) {
            right = sliderArray[clickedRow][clickedColumn + 1]

        }

        Toast.makeText(this, "top " + top?.text.toString() + ", bottom " + bottom?.text.toString(), Toast.LENGTH_SHORT)
            .show()

        if (top?.text.toString() == "") {

            var handler = Handler()

            top?.startAnimation(topToBottom)
            sliderArray[clickedRow][clickedColumn].startAnimation(bottomToTop)

            /*if(topToBottom.hasEnded() && bottomToTop.hasEnded())
            {
                top!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }*/









            handler.postDelayed(Runnable {
                top!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }, 500)


        } else if (bottom?.text.toString() == "") {

            bottom?.startAnimation(bottomToTop)
            sliderArray[clickedRow][clickedColumn].startAnimation(topToBottom)

       /*
            if(topToBottom.hasEnded() && bottomToTop.hasEnded())
            {
                bottom!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }
*/





            var handler = Handler()
            handler.postDelayed(Runnable {
                bottom!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }, 500)


        } else if (left?.text.toString() == "") {

            left?.startAnimation(leftToRight)
            sliderArray[clickedRow][clickedColumn]?.startAnimation(rightToLeft)

/*
            if(rightToLeft.hasEnded() && leftToRight.hasEnded())
            {
                left!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }
            */



            var handler = Handler()

            handler.postDelayed(Runnable {
                left!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }, 500)

        } else if (right?.text.toString() == "") {


            right?.startAnimation(rightToLeft)
            sliderArray[clickedRow][clickedColumn].startAnimation(leftToRight)

       /*     if(rightToLeft.hasEnded() && leftToRight.hasEnded())
            {
                right!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }*/


            var handler = Handler()

            handler.postDelayed (Runnable {
                right!!.text = sliderArray[clickedRow][clickedColumn].text.toString()
                sliderArray[clickedRow][clickedColumn].text = ""
            }, 500)


        }

        var completed = false

        var handler = Handler()
        handler.postDelayed(Runnable {
            completed = checkCompletion()
            if (completed) {

                winImage.visibility = View.VISIBLE
                Toast.makeText(this, "Congratulations on your victory", Toast.LENGTH_SHORT).show()
            }
        }, 500)



        Handler().postDelayed(Runnable { winImage.visibility = View.INVISIBLE },8000)


        /*    for (i in 0..2) {
                for (j in 0..2) {



                    if (i != 0) {
    //                    top = sliderArray[i - 1][j]
                    }


                    if (i != 2) {
                        var bottom = sliderArray[i + 1][j]

                    }



                    if (j != 0) {
                        var left = sliderArray[i][j - 1]
                    }

                    if (j != 2) {
                        right = sliderArray[i][j + 1]

                    }


                    *//*if (top != null && positionOfBlank!!.text.toString() == top.text.toString()) {
                    top.text = sliderArray[i][j].text.toString()
                    sliderArray[i][j].text = ""
                }*//*
                if (bottom != null && clickedButton.) {
                    bottom.text = sliderArray[i][j].text.toString()
                    sliderArray[i][j].text = ""
                } *//* else if (left != null && left.text.toString() == positionOfBlank!!.text.toString()) {
                    left.text = sliderArray[i][j].text
                    sliderArray[i][j].text = ""
                } else if (right != null && right.text.toString() == positionOfBlank!!.text.toString()) {
                    right.text = sliderArray[i][j].text
                    sliderArray[i][j].text = ""
                }*//*
            }
        }*/

    }

    private fun checkCompletion(): Boolean {
        var isCompleted = true
        for (i in 0..2) {
            for (j in 0..2) {


//                        Log.i("["+i+"]["+j+"]",""+sliderArray[i][j].text)
                Log.i("answer[" + i + "][" + j + "]", "" + sliderArray[i][j].text)




                if (sliderArray[i][j].text != answerArray[i][j]) {

                    isCompleted = false
                    break
//                    return


//                    Toast.makeText(this,"Congratulations on your victory",Toast.LENGTH_SHORT ).show()
                }
            }
        }

        return isCompleted

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.slide_it_activity)


        shakeAnimation = AnimationUtils.loadAnimation(this, R.anim.shake_button);

          Glide.with(this).load(R.raw.win_sparks).into(winImage)




        sliderArray = Array(3) { Array(3) { Button(this) } }


        for (i in 0..2) {
            for (j in 0..2) {
                var buttonName = "button_" + i + j
                var resId = resources.getIdentifier(buttonName, "id", getPackageName())

                sliderArray[i][j] = findViewById<Button>(resId)

                sliderArray[i][j].setOnClickListener(this)
//
            }
        }

        createRandomList()


    }

    private fun createRandomList() {

        for (i in 0..7) {

            randomList.add(i, (i + 1).toString())
        }

        randomList.add(8, "")

        Collections.shuffle(randomList)


        changeTheButtonText()


    }

    private fun changeTheButtonText() {

        var temp = 0;
        for (i in 0..2) {
            for (j in 0..2) {
                sliderArray[i][j].text = randomList.get(temp++).toString();


            }
        }
        Log.i("random list", "" + randomList)
        for (i in 0..2) {
            for (j in 0..2) {
                Log.i("[" + i + "][" + j + "]", "" + sliderArray[i][j].text)


            }
        }

    }
}