package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tictactoe.db.DatabaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {


    var player1Points = 0
    var player2Points = 0
    var player1Turn = true
    var roundCount = 0;

//     var buttons: Array<Array<Button>> = arrayOf<Array(Button)>()

//    var buttons = arrayOf<Array<Button>>()

//    var buttons:Array<Array<Button>> = Array(3){Array(3,{ Button(this, null)})}

//    var buttons = Array(3,{i -> Array(3,{j ->
//
//
//        var buttonName = "button_" + i + j
//        var resId = resources.getIdentifier(buttonName, "id", this.getPackageName())
//
////         findViewById<Button>(resId)
//    })})

    lateinit var buttons:Array<Array<Button>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        buttons  = Array(3){Array(3){ Button(MainActivity@this) }}

        for (i in 0..2) {
            for (j in 0..2) {
                var buttonName = "button_" + i + j
                var resId = resources.getIdentifier(buttonName, "id", getPackageName())

                buttons[i][j] = findViewById<Button>(resId)
//
                buttons[i][j].setOnClickListener(this)

            }
        }


        reset_button.setOnClickListener {

            player1Points = 0
            player2Points = 0
            updtatePointstext()
            resetBoard()

        }
motionLayoutAnimationButton.setOnClickListener { startActivity(Intent(this,SlideItActivity::class.java)) }


        databaseTV.setOnClickListener { startActivity(Intent(this, DatabaseActivity::class.java)) }

        todoTV.setOnClickListener { startActivity(Intent(this,TodoActivity::class.java)) }


    }
    override fun onClick(v: View?) {


        if (!(v as Button).text.toString().equals("")) {
            return
        }

        if (player1Turn) {
            (v as Button).text = "X"
        } else {
            (v as Button).text = "O"
        }
        roundCount++;


        if (checkForWin()) {
            if (player1Turn) {
                player1Win()
            } else {
                player2Win()
            }
        } else if (roundCount == 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun draw() {

        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show()
        updtatePointstext()
        resetBoard()
    }

    private fun player2Win() {

        player2Points++
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show()
        updtatePointstext()
        resetBoard()

    }

    private fun player1Win() {

        player1Points++
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show()
        updtatePointstext()
        resetBoard()

    }

    private fun resetBoard() {

        for (i in 0..2) {
            for (j in 0..2) {
                buttons[i][j].text = "";
            }
        }

        roundCount = 0;
        player1Turn = true

    }

    private fun updtatePointstext() {

        player1_score.text = player1Points.toString()
        player2_score.text = player2Points.toString()

    }

    fun checkForWin(): Boolean {
        var stringArray: Array<Array<String>> = Array(3, { Array(3) { "" } })
        for (i in 0..2) {
            for (j in 0..2) {
                stringArray[i][j] = buttons[i][j].text.toString()
            }
        }


        for (i in 0..2) {
            if (stringArray[i][0].equals(stringArray[i][1])
                && stringArray[i][0].equals(stringArray[i][2])
                && !stringArray[i][0].equals("")
            ) {
                return true
            }
        }


        for (i in 0..2) {
            if (stringArray[0][i].equals(stringArray[1][i])
                && stringArray[0][i].equals(stringArray[2][i])
                && !stringArray[0][i].equals("")
            ) {
                return true
            }
        }


        if (stringArray[0][0].equals(stringArray[1][1])
            && stringArray[0][0].equals(stringArray[2][2])
            && !stringArray[0][0].equals("")
        ) {
            return true
        }

        if (stringArray[0][2].equals(stringArray[1][1])
            && stringArray[0][2].equals(stringArray[2][0])
            && !stringArray[2][0].equals("")
        ) {
            return true
        }

        return false
    }


//    var
//             = Array(3){Array(3){}}


    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)



        outState?.putInt("roundCount",roundCount)
        outState?.putInt("player1Points",player1Points)
        outState?.putInt("player2Points",player2Points)

        outState?.putBoolean("player1Turn",player1Turn)

    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)


        roundCount = savedInstanceState?.getInt("roundCount") ?: 0
        player1Points = savedInstanceState?.getInt("player1Points")?:0
        player2Points = savedInstanceState?.getInt("player2Points")?:0
        player1Turn = savedInstanceState?.getBoolean("player1Turn") ?: true
    }

}
