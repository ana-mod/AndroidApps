package com.example.hangman

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private val size = 5
    private val buttons = Array<Array<Button?>>(size) { arrayOfNulls(size) }
    private var player1turn = true
    private var hasWon = false
    private var round = 0
    private var available = true
    private var points1 = 0
    private var points2 = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 0..(size - 1)) {
            for (j in 0..(size - 1)) {
                val buttonID = "button$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<Button?>(resID)
            }
        }
    }
        /* fun checkWon(v: View) : Boolean {

        val butRow = resources.getResourceEntryName(v.getId())[6].toString().toInt()
        val butCol = resources.getResourceEntryName(v.getId())[7].toString().toInt()

        val compareRow = buttons[butRow][0]?.text
        myloop@ for (i in 1..(size-1)){
            if (buttons[butRow][i]?.text != compareRow ) {
                hasWon = false
                return false
                break@myloop
            }
            hasWon = true
        }

        val compareCol = buttons[0][butCol]?.text
        myloop2@ for (i in 1..(size-1)){
            if (buttons[i][butCol]?.text != compareCol ) {
                hasWon = false
                return false
                break@myloop2
            }
            hasWon = true

        }
        return true
    }
*/
        fun gridClick(v: View) {

            //SOMEONE WON, RESET NEEDS TO BE CLICKED
            if (!available) return

            //CHANGE STATUS

            if ((v as Button).text != "") {
                return
            }
            if (player1turn) {
                ((v as Button)).text = "X"
            } else {
                ((v as Button)).text = "O"
            }
            if (player1turn) findViewById<TextView>(R.id.textView).text = "O turn"
            else findViewById<TextView>(R.id.textView).text = "X turn"

            val butRow = resources.getResourceEntryName(v.getId())[6].toString().toInt() //row index of clicked button
            val butCol =
                resources.getResourceEntryName(v.getId())[7].toString().toInt() //column index of clicked button

            val compareRow = buttons[butRow][0]?.text
            myloop@ for (i in 1..(size - 1)) {                      //check if whole row is filled with one char
                if (buttons[butRow][i]?.text != compareRow) {
                    hasWon = false
                    break@myloop
                }
                hasWon = true
            }

            if (!hasWon) {
                val compareCol = buttons[0][butCol]?.text
                myloop2@ for (i in 1..(size - 1)) {                 //check if whole column is filled with one char
                    if (buttons[i][butCol]?.text != compareCol) {
                        hasWon = false
                        break@myloop2
                    }
                    hasWon = true
                }
            }
            if (!hasWon) {                                     //check if fallen diagonal is filled with one char
                myloop3@ for (i in 1..(size - 1)) {
                    if (buttons[0][0]?.text == "") break@myloop3
                    if (buttons[i][i]?.text != buttons[0][0]?.text) {
                        hasWon = false
                        break@myloop3
                    }
                    hasWon = true
                }
            }
            if (!hasWon) {
                myloop4@ for (i in 1..(size - 1)) {                //check if rising diagonal is filled with one char
                    if (buttons[0][size - 1]?.text == "") break@myloop4
                    if (buttons[i][size - 1 - i]?.text != buttons[0][size - 1]?.text) {
                        hasWon = false
                        break@myloop4
                    }
                    hasWon = true
                }
            }

            if (hasWon) {
                if (player1turn) {
                    Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()
                    points1++
                    findViewById<TextView>(R.id.textView2).text = "$points1"
                    findViewById<TextView>(R.id.textView).text = "Player 1 wins! Press NEW GAME to continue"
                } else {
                    Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()
                    points2++
                    findViewById<TextView>(R.id.textView3).text = "$points2"
                    findViewById<TextView>(R.id.textView).text = "Player 2 wins! Press NEW GAME to continue"
                }
                available = false
                round = 0
                return

            }

            round++
            if (round == size * size) {
                Toast.makeText(this, "Issa draw!", Toast.LENGTH_SHORT).show()
                findViewById<TextView>(R.id.textView).text = "It's a draw. Press NEW GAME to continue"
                available = false
                round = 0
                return
            }

            player1turn = !player1turn
        }

        fun reset(v: View) {

            for (i in 0..(size - 1)) {
                for (j in 0..(size - 1)) {
                    val buttonID = "button$i$j"
                    val resID = resources.getIdentifier(buttonID, "id", packageName)
                   findViewById<Button?>(resID)?.text = ""
                }
            }
            available = true
        }
}