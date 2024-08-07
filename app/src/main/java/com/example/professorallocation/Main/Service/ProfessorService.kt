package com.example.professorallocation.Main.Service

import com.example.professorallocation.Main.Models.Professor
import com.example.professorallocation.Main.Repository.RetrofitConfig
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProfessorService {

    @GET("professor")
    fun getProfessor() : Call<List<Professor>>

    @POST("professor")
    fun postProfessor(@Body curso: Professor) : Call<Any>

    @GET("professor/{id}")
    fun getProfessorPorId(@Path("id") cursoId: Int) : Call<Professor>

    @PUT("professor/{id}")
    fun putProfessor(@Path("id") cursoId: Int, @Body curso: Professor) : Call<Professor>

    @DELETE("professor/{id}")
    fun deleteProfessor(@Path("id") cursoId: Int) : Call<Any>

    companion object {
        fun create(): ProfessorService {
            val retrofit = RetrofitConfig.retrofit
            return retrofit.create(ProfessorService::class.java)
        }
    }

}