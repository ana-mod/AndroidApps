package com.example.lista1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {

    private var points = 0
    private var randomAndroidStringColor = ""
    private var usedColor = ""
    private var sciencePlayer = MediaPlayer()
    private var ohNoPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sciencePlayer = MediaPlayer.create(this, R.raw.yesscience)
        ohNoPlayer = MediaPlayer.create(this, R.raw.ohno)

        roll(0)
    }

    private fun roll(f: Int) {

        if( f==0) {}

        else if ( (randomAndroidStringColor == usedColor && f==1) || (randomAndroidStringColor!= usedColor && f==2)) {
            points++
            if(sciencePlayer.isPlaying) {
                sciencePlayer.pause()
                sciencePlayer.seekTo(0)
            }
            sciencePlayer.start()
        }

        else {
            points--
            if(ohNoPlayer.isPlaying) {ohNoPlayer.pause()
                ohNoPlayer.seekTo(0)}
            ohNoPlayer.start()
        }

       findViewById<TextView>(R.id.textView).text = "$points"

        val androidColors = resources.getIntArray(R.array.androidcolors)
        val androidStringColors = resources.getStringArray(R.array.androidstringcolors)

        val r = Random().nextInt(androidColors.size)

        val randomAndroidColor = androidColors[r]
        val notUsedColor = androidStringColors[Random().nextInt(androidStringColors.size)]

        randomAndroidStringColor = androidStringColors[r]
        usedColor = androidStringColors[Random().nextInt(androidStringColors.size)]

        textView5.setTextColor(randomAndroidColor)
        findViewById<TextView>(R.id.textView5).text = "$notUsedColor" //nieważny
        findViewById<TextView>(R.id.textView6).text = "$usedColor" //spr czy ten sam co kolor tekstu textView5

    }

    fun leftClick(view: View) {
        roll(1)
    }

    fun rightClick(view: View) {
        roll(2)
    }

    fun reset(view: View) {
        points = 0
        roll(0)
    }

}