package com.example.tic

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val alphabetLength = 32
    private var alphabetArray = Array(alphabetLength) {""}
    private var alphabetButtons = Array<Button?>(alphabetLength) { null }
    private val dictionaryLength = 144
    private var words = Array(dictionaryLength) {""}
    private var word = ""
    private var isItIn = false
    private var available = true
    private var endIsNear = 0 //current number of wrong answers
    private var yay = 0 //current number of correct answers
    private val death = 7 //max number of wrong answers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        alphabetArray = resources.getStringArray(R.array.alphabet)

        for (i in 0..(alphabetLength - 1)) {
                val buttonID = "button"+(i+1)
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                alphabetButtons[i] = findViewById(resID)
                alphabetButtons[i]?.text = alphabetArray[i]
        }
        words = resources.getStringArray(R.array.words)
        word = words[Random().nextInt(dictionaryLength)]

        val textView: TextView = findViewById<TextView>(R.id.textView2)
        for(letter in word) {
            textView.append("-")
        }
        imageView.setImageResource(R.drawable.hangman0)
    }

    fun newGame(v: View){
        word = words[Random().nextInt(dictionaryLength)]
        var textView: TextView = findViewById<TextView>(R.id.textView2)
            textView.text=""
        for(letter in word) {
            textView.append("-")
        }
        endIsNear = 0
        yay = 0
        available = true

        for(button in alphabetButtons) {
            button?.setBackgroundResource(android.R.drawable.btn_default)
            button?.isClickable=true
        }
        imageView.setImageResource(R.drawable.hangman0)
    }

    fun letterClick(v: View) {

        if(!available) return

        val letterClicked = (v as Button)?.text

        for(i in 0..(word.length-1)){
            if(letterClicked[0]==word[i]) {
                isItIn=true
                yay++
                val old: String = findViewById<TextView>(R.id.textView2).text.toString()
                val new: String = old.substring(0, i)+letterClicked+old.substring(i+1)
                findViewById<TextView>(R.id.textView2).text=new
                if(yay==word.length) {
                    Toast.makeText(this, "You won!", Toast.LENGTH_SHORT).show()
                    available=false
                }
            }
        }

        if(!isItIn) {
            endIsNear++

            var imageID = resources.getIdentifier("hangman$endIsNear","drawable",packageName)
            imageView.setImageResource(imageID)

            if(endIsNear==death) {
                Toast.makeText(this, "You're a failure to our family! Correct answer was $word", Toast.LENGTH_SHORT).show()
                available=false
                return
            }
        }
        isItIn=false
        v.setBackgroundColor(Color.WHITE)
        v.setClickable(false)
    }
}
