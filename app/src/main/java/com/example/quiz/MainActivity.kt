package com.example.quiz

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun updateQuestion() {
            val questionTextResId = questionBank[currentIndex].textResId
            questionTextView.setText(questionTextResId)
        }
        fun checkAnswer(userAnswer: Boolean){
            val correctAnswer = questionBank[currentIndex].answer
            val messageResId =
                if (userAnswer == correctAnswer) R.string.correct_toast
                else R.string.incorrect_toast
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }
        fun nextQuestion(){
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener {
            checkAnswer(true)
            nextQuestion()}
        falseButton.setOnClickListener {
            checkAnswer(false)
            nextQuestion()}
        nextButton.setOnClickListener {
            nextQuestion()
        }
        prevButton.setOnClickListener {
            if (currentIndex == 0) currentIndex = questionBank.size - 1
            else currentIndex -= 1
            updateQuestion()
        }
         updateQuestion()
    }
}