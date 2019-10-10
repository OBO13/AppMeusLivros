package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_cadastrar_livro.*


class CadastrarLivro : AppCompatActivity() {

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "livro"
        )
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_livro)

        btnGravar.setOnClickListener {
            cadastrarLIvro()
        }
    }
        fun cadastrarLIvro() {
        try {
            db.livroDAO().inserir(Livro(txtNome.text.toString(), txtAutor.text.toString(), Integer.parseInt(txtAno.text.toString()), ratingBar.rating))

        } catch (e: java.lang.NumberFormatException) {

            db.livroDAO().inserir(Livro(txtNome.text.toString(), txtAutor.text.toString(), 0, ratingBar.rating
                )
            )
        }

        Toast.makeText(this, "Livro Cadastrado com sucesso!", Toast.LENGTH_SHORT).show()

        btnCancelar.setOnClickListener {
            Toast.makeText(this, "Cancela operação e retorna a tela inicial", Toast.LENGTH_SHORT)
                .show()
            finish()
        }
            txtNome.setText("") //tela cadastro de livros limpa os campos
            txtAutor.setText("")
            txtAno.setText("")
            ratingBar.rating = 0f


    }

}
