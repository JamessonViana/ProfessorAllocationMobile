package com.example.professorallocation.Main.Repository

import com.example.professorallocation.Main.Service.CourseService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.212.153.13:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val servico = retrofit.create(CourseService::class.java)
}