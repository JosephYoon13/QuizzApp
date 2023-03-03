package com.example.quizzapp

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("/v3.1/all?fields=name")
    fun getFlagsData(): Call<MyFlagData>
}