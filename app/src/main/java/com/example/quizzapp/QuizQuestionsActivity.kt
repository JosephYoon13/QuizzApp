package com.example.quizzapp

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.quizzapp.databinding.ActivityQuizQuestionsBinding
import com.squareup.picasso.Picasso

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var position: Int = 1
    private var questionsList: ArrayList<RandomQuestion> ?= null
    private var selectedOption: Int = 0
    private var correctAnswers: Int = 0

    private lateinit var binding: ActivityQuizQuestionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        questionsList = intent.getParcelableArrayListExtra("questionsList")

        setQuestion()

        binding.option1.setOnClickListener(this)
        binding.option2.setOnClickListener(this)
        binding.option3.setOnClickListener(this)
        binding.option4.setOnClickListener(this)

        binding.submitButton.setOnClickListener {
            if (selectedOption == 0) {
                Toast.makeText(this, "You must select an answer!", Toast.LENGTH_SHORT).show()
            } else {
                val question = questionsList!![position - 1]
                if (question.correctAnswer == selectedOption) ++correctAnswers
                if (position == 10) {
                    val intent = Intent(this, ResultPage::class.java)
                    intent.putExtra("correctAnswers", correctAnswers)
                    startActivity(intent)
                    finish()
                } else {
                    ++position
                    setQuestion()
                }
            }
        }
    }

    private fun setQuestion() {
        if (position == 10) {
            binding.submitButton.text = "Finish"
        }
        selectedOption = 0
        defaultOptionsView()

        val question = questionsList!![position - 1]
        Picasso.get().load("https://countryflagsapi.com/png/${question.alphaCode}")
            .placeholder(R.drawable.flag_default)
            .into(binding.flagImage)
//        binding.flagImage.setImageResource(question.image)
        binding.progressBar.progress = position
        binding.progressText.text = "$position" + "/" + binding.progressBar.max
        binding.option1.text = question.option1 + question.correctAnswer
        binding.option2.text = question.option2
        binding.option3.text = question.option3
        binding.option4.text = question.option4
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0, binding.option1)
        options.add(1, binding.option2)
        options.add(2, binding.option3)
        options.add(3, binding.option4)

        options.forEach{
            it.typeface = Typeface.DEFAULT
            it.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id) {
            R.id.option1 -> {
                selectedOptionView(binding.option1, 1)
            }
            R.id.option2 -> {
                selectedOptionView(binding.option2, 2)
            }
            R.id.option3 -> {
                selectedOptionView(binding.option3, 3)
            }
            R.id.option4 -> {
                selectedOptionView(binding.option4, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        selectedOption = selectedOptionNum
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }
}