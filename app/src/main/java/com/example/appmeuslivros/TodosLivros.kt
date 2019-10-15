package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_todos_livros.*


class TodosLivros : AppCompatActivity() {

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "livro"
        )
            .allowMainThreadQueries()
            .build()
    }

    //var livros:List<Livro>? = null

    //var livros = ArrayList<Livro>()
    //var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todos_livros)

        var livros = db.livroDAO().listAll()

        listview.adapter = LivroAdapter(this, livros)

        listview.setOnItemClickListener{adapterView, view, i, l ->
            var livroSelecionado = livros.get(i)
            Toast.makeText(this, "${livroSelecionado?.Titulo} id=${livroSelecionado?.id}", Toast.LENGTH_SHORT).show()
        }

    }
}

