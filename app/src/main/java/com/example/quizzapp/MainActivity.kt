package com.example.quizzapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.quizzapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val FLAG_BASE_URL = "https://restcountries.com/"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var questionsList: ArrayList<RandomQuestion> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        getFlags()

        binding.btnStart.setOnClickListener {
            val intent = Intent(this, QuizQuestionsActivity::class.java)
            getFlags()
            intent.putExtra("questionsList", questionsList)
            startActivity(intent)
            finish()
        }
    }

    private fun getFlags() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(FLAG_BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getFlagsData()
        retrofitData.enqueue(object : Callback<MyFlagData?> {
            override fun onResponse(call: Call<MyFlagData?>, response: Response<MyFlagData?>) {
                val responseBody = response.body()!!

                val data: List<MyFlagDataItem> = responseBody.shuffled().take(10)
                questionsList = Constants.getRandomQuestions(data, responseBody)
            }

            override fun onFailure(call: Call<MyFlagData?>, t: Throwable) {
                Log.i("no fetches", "bye")
            }
        })
    }
}