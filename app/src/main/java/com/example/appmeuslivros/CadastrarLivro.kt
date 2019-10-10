package com.example.appmeuslivros

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_cadastrar_livro.*


class CadastrarLivro : AppCompatActivity() {

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "banco-livro"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)

        btnInsert.setOnClickListener {
            cadastrarLIvro()
        }

        btnVoltar.setOnClickListener {
            Toast.makeText(this, "Retorno a tela inicial", Toast.LENGTH_SHORT).show()
            finish()
        }


    }

    fun cadastrarLIvro() {
        try {
            db.livroDAO().inserir(
                Livro(
                    txtNome.text.toString(),
                    txtAutor.text.toString(),
                    Integer.parseInt(txtAno.text.toString()),
                    ratingBar.rating
                )
            )
        } catch (e: java.lang.NumberFormatException) {
            db.livroDAO().inserir(
                Livro(
                    txtNome.text.toString(),
                    txtAutor.text.toString(),
                    0,
                    ratingBar.rating
                )
            )
        }
        Toast.makeText(this, "Livro Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

        txtNome.setText("")
        txtAutor.setText("")
        txtAno.setText("")
        ratingBar.rating = 0f
    }
}
