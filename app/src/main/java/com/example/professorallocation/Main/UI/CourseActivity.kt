package com.example.professorallocation.Main.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.professorallocation.Main.Adapter.CourseAdapter
import com.example.professorallocation.Main.Repository.CourseRepository
import com.example.professorallocation.Main.Service.CourseService
import com.example.professorallocation.R

class CourseActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var repository: CourseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_course)

        recyclerView = findViewById(R.id.courseList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val courseService = CourseService.create()
        repository = CourseRepository(courseService)

        buscarCursos()

        val newCourse = findViewById<Button>(R.id.newCourse)
        newCourse.setOnClickListener {
            val intent = Intent(this , CreateCourseActivity :: class.java)
            startActivity(intent)
        }  


    }





    private fun buscarCursos() {
        repository.buscarCursos(
            onCall = { cursos ->
                if (cursos != null) {
                    val adapter = CourseAdapter(cursos,
                        onEditClick = { course ->
                            // Lógica para editar o curso
                        },
                        onDeleteClick = { course ->
                            // Lógica para excluir o curso
                        }
                    )
                    recyclerView.adapter = adapter
                } else {
                    // Exibir mensagem ou tratar caso de lista vazia
                }
            },
            onError = {
                Log.e("CourseActivity", "Erro ao buscar cursos")
                // Tratar o erro
            }
        )
    }



}
