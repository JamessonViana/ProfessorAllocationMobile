package com.example.professorallocation.Main.Repository

import com.example.professorallocation.Main.Models.Professor
import com.example.professorallocation.Main.Service.ProfessorService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfessorRepository(val servicoProfessor: ProfessorService) {

    fun criarProfessor(professor: Professor, onCall: () -> Unit, onError: () -> Unit) {
        servicoProfessor.postProfessor(professor).enqueue(object : Callback<Any> {
            override fun onResponse(p0: Call<Any>, p1: Response<Any>) {
                onCall()
            }

            override fun onFailure(p0: Call<Any>, p1: Throwable) {
                onError()
            }
        })
    }

    fun buscarProfessor(onCall: (course: List<Professor>?) -> Unit, onError: () -> Unit) {
        servicoProfessor.getProfessor().enqueue(object : Callback<List<Professor>> {
            override fun onResponse(p0: Call<List<Professor>>, response: Response<List<Professor>>) {
                val professor = response.body()
                onCall(professor)
            }

            override fun onFailure(p0: Call<List<Professor>>, p1: Throwable) {
                onError()
            }
        })
    }


    fun buscarProfessorPorId(
        idProfessor: Int,
        onCall: (professor: Professor?) -> Unit,
        onError: (mensagem: String) -> Unit){

        servicoProfessor.getProfessorPorId(idProfessor).enqueue(object : Callback<Professor> {
            override fun onResponse(p0: Call<Professor>, response: Response<Professor>) {
                if (response.isSuccessful) {
                    val professor = response.body()
                    onCall(professor)
                } else {
                    if (response.code() == 404)
                        onError("Dado não exite na base")
                    else
                        onError(response.message())
                }
            }

            override fun onFailure(p0: Call<Professor>, p1: Throwable) {
                val mensagem = p1.message
                if (mensagem != null)
                    onError(mensagem)
            }

        })

    }


    fun alterarProfessor(
        idProfessor: Int,
        professor: Professor,
        onCall: (professor: Professor?) -> Unit,
        onError: (mensagem: String) -> Unit
    ){
        servicoProfessor.putProfessor(idProfessor, professor).enqueue(object : Callback<Professor> {
            override fun onResponse(p0: Call<Professor>, p1: Response<Professor>) {
                onCall(p1.body())
            }

            override fun onFailure(p0: Call<Professor>, p1: Throwable) {
                onError(p1.message ?: "")
            }
        })
    }

    fun apagarProfessor(
        idProfessor: Int,
        onCall: () -> Unit,
        onError: (mensagem: String) -> Unit
    ){
        servicoProfessor.deleteProfessor(idProfessor).enqueue(object : Callback<Any> {
            override fun onResponse(p0: Call<Any>, p1: Response<Any>) {
                onCall()
            }

            override fun onFailure(p0: Call<Any>, p1: Throwable) {
                onError(p1.message ?: " Erro")
            }
        })
    }

}