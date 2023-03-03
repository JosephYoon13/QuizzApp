package com.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizzapp.databinding.ResultPageBinding

class ResultPage : AppCompatActivity() {
    private lateinit var binding: ResultPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val correctAnswers = intent.getIntExtra("correctAnswers", 0)
        binding.resultText.text = "Congratulations! You scored ${correctAnswers}/10!"

        binding.finishBtn.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}