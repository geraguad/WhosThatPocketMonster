package com.example.mobileappdev2025

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var rightNum: String = "Submit"
    private var score: Int = 0
    private var titleName: String = "   Who's That Pokemon??"
    private var counter: Int = 0
    private var selectedAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val radioButtons = listOf(
            findViewById<RadioButton>(R.id.radioButton1),
            findViewById<RadioButton>(R.id.radioButton2),
            findViewById<RadioButton>(R.id.radioButton3),
            findViewById<RadioButton>(R.id.radioButton4)
        )

        val submitB: Button = findViewById(R.id.submitButton)
        val nButton = findViewById<Button>(R.id.nextButton)

        radioButtons.forEach { radioButton ->
            radioButton.setOnClickListener {
                radioButtons.forEach { it.isChecked = false }
                radioButton.isChecked = true
                selectedAnswer = radioButton.text.toString()
            }
        }

        submitB.setOnClickListener {
            if (selectedAnswer != null) {
                checkAnswer(selectedAnswer!!)
                counter += 1
                updateButtons()
                nextPicture()
                setTitle()
            }
        }

        nButton.setOnClickListener {
            counter += 1
            updateButtons()
            nextPicture()
            setTitle()
        }

        setScore(0)
        setTitle(titleName)
        updateButtons()
        pokeNames()
        nextPicture()
    }

    fun checkAnswer(selectedAnswer: String) {
        val correctAnswers = mapOf(
            0 to "Sceptile",
            2 to "Snorlax",
            4 to "Umbreon",
            6 to "Mewtwo"
        )

        if (correctAnswers[counter] == selectedAnswer) {
            setScore(score + 1)
        }
    }

    fun updateButtons() {
        findViewById<Button>(R.id.submitButton).visibility =
            if (counter % 2 == 0 && counter <= 7) View.VISIBLE else View.INVISIBLE
        findViewById<Button>(R.id.nextButton).visibility =
            if (counter % 2 == 1) View.VISIBLE else View.INVISIBLE
        }


    fun pokeNames() {
        val Name1: RadioButton = findViewById(R.id.radioButton1)
        Name1.text = "Sceptile"
        val Name2: RadioButton = findViewById(R.id.radioButton2)
        Name2.text = "Mewtwo"
        val Name3: RadioButton = findViewById(R.id.radioButton3)
        Name3.text = "Umbreon"
        val Name4: RadioButton = findViewById(R.id.radioButton4)
        Name4.text = "Snorlax"
    }

    fun nextPicture() {
        findViewById<ImageView>(R.id.sceptile_s).visibility = if (counter == 0) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.sceptile_r).visibility = if (counter == 1) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.mewtwo_s).visibility = if (counter == 6) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.mewtwo_r).visibility = if (counter == 7) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.umbreon_s).visibility = if (counter == 4) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.umbreon_r).visibility = if (counter == 5) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.snorlax_s).visibility = if (counter == 2) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.snorlax_r).visibility = if (counter == 3) View.VISIBLE else View.INVISIBLE
        findViewById<ImageView>(R.id.game_over).visibility = if (counter == 8) View.VISIBLE else View.INVISIBLE
    }

    fun setScore(_score: Int) {
        score = _score
        findViewById<TextView>(R.id.score_text).text = "Score: $score"
    }


    fun setTitle() {
        if (counter == 0 || counter == 2 || counter == 4 || counter == 6){
            findViewById<TextView>(R.id.Title).text = "$titleName"
        }
        if (counter == 1) {
            findViewById<TextView>(R.id.Title).text = "            It's Sceptile!"
        }
        if (counter == 3) {
            findViewById<TextView>(R.id.Title).text = "             It's Snorlax!"
        }
        if (counter == 5) {
            findViewById<TextView>(R.id.Title).text = "             It's Umbreon!"
        }
        if (counter == 7) {
            findViewById<TextView>(R.id.Title).text = "              It's Mewtwo!"
        }
        if (counter == 8){
            findViewById<TextView>(R.id.Title).text = "       Thanks For Playing!"
        }
    }

}