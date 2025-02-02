package com.example.appmeuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //Classe Pricipal

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

        btnToc.setOnClickListener {
            startActivity(Intent(this, Remove_livro_toc_tela::class.java))
        }

        btn_pos.setOnClickListener {
            startActivity(Intent(this, Mov_Lat_Hor_excluir::class.java))
        }

        btn_PV.setOnClickListener {
            startActivity(Intent(this, PVactivity::class.java))
        }
    }


}
