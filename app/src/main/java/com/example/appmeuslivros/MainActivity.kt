package com.example.appmeuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCadastrar.setOnClickListener {
            startActivity(Intent(this, CadastrarLivro::class.java))
        }

        btnListar.setOnClickListener {
            startActivity(Intent(this, ListaLivros::class.java))
        }

        btnTodosLivros.setOnClickListener {
            startActivity(Intent(this, TodosLivros::class.java))
        }
    }


}
