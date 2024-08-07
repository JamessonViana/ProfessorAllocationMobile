package com.example.professorallocation.Main.Repository

import com.example.professorallocation.Main.Service.CourseService
import com.example.professorallocation.Main.Service.ProfessorService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.100:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val servico = retrofit.create(CourseService::class.java)

    val servicoProfessor = retrofit.create(ProfessorService::class.java)
}