package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bignerdranch.android.geoquiz.R.*

class MainActivity : AppCompatActivity() {
    private lateinit var Button1: Button
    private lateinit var Button2: Button
    private lateinit var Button3: Button
    private lateinit var Button4: Button


    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private lateinit var scoreCorrectTextView: TextView
    private lateinit var scoreIncorrectTextView: TextView
    private lateinit var resetScore: Button

    private val questionBank = listOf(
            Question(R.string.question_venezuela,"Caracas","Paris","Madrid", "Caracas","Dallas" ),
            Question(R.string.question_france,"Paris","Washington D.C", "Miami", "Brasilia", "Paris"),
            Question(R.string.question_spain,"Madrid", "Lisbon", "Madrid", "Tampa", "Caens"),
            Question(R.string.question_usa,"Washington D.C", "Barcelona", "Sevilla", "Washington D.C","Mallorca"),
            Question(R.string.question_brazil,"Brasilia","Rio de Janerio", "Brasilia", "Lisbon","Ibiza"),
            Question(R.string.question_portugal,"Lisbon","Hongkong","Sydney","Lisbon","Rome"))


    private var currentIndex = 0
    private var correctScore = 0
    private var incorrectScore = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)


        Button1 = findViewById(id.first_choice)
        Button2 = findViewById(id.second_choice)
        Button3 = findViewById(id.third_choice)
        Button4 = findViewById(id.fourth_choice)


        nextButton = findViewById(id.next_button)
        prevButton = findViewById(id.prev_button)
        questionTextView = findViewById(id.question_text_view)
        scoreCorrectTextView = findViewById(id.score_correct_text_view)
        scoreIncorrectTextView = findViewById(id.score_incorrect_text_view)
        resetScore = findViewById(id.reset_button)


        Button1.setOnClickListener { view: View ->
            checkAnswer(questionBank[currentIndex].choice1)
            updateScore(questionBank[currentIndex].choice1)
        }

        Button2.setOnClickListener { view: View ->
            checkAnswer(questionBank[currentIndex].choice2)
            updateScore(questionBank[currentIndex].choice2)
        }
        Button3.setOnClickListener { view: View ->
            checkAnswer(questionBank[currentIndex].choice3)
            updateScore(questionBank[currentIndex].choice3)
        }
        Button4.setOnClickListener { view: View ->
            checkAnswer(questionBank[currentIndex].choice4)
            updateScore(questionBank[currentIndex].choice4)
        }



        nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            updateAnswer()
        }
        // previous button
        prevButton.setOnClickListener{
            if (currentIndex == 0){
                currentIndex = 6
            }
            currentIndex = (currentIndex-1) % questionBank.size
            updateQuestion()
            updateAnswer()
        }

        updateQuestion()

        resetScore.setOnClickListener{
            resetScore()
        }
    }

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun updateAnswer(){
        Button1.text = questionBank[currentIndex].choice1
        Button2.text = questionBank[currentIndex].choice2
        Button3.text = questionBank[currentIndex].choice3
        Button4.text = questionBank[currentIndex].choice4
    }


    private fun checkAnswer(userAnswer: String){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if( userAnswer == correctAnswer){
            R.string.correct_toast
        }else{
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun updateScore(userAnswer: String){
        val correctAnswer = questionBank[currentIndex].answer
        if( userAnswer == correctAnswer){
            this.correctScore++
        }else{
            this.incorrectScore++
        }
        scoreCorrectTextView.setText(this.correctScore.toString())
        scoreIncorrectTextView.setText(this.incorrectScore.toString())
    }

    private fun resetScore(){
        this.correctScore = 0;
        this.incorrectScore = 0;
        scoreCorrectTextView.setText(this.correctScore.toString())
        scoreIncorrectTextView.setText(this.incorrectScore.toString())
    }
}
