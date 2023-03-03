package com.example.quizzapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomQuestion(
    val id: Int,
    val question: String,
    val image: Int,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int,
    val alphaCode: String,
): Parcelable
