package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_lista_livros.*


class ListaLivros : AppCompatActivity() {

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "livro"
        )
            .allowMainThreadQueries()
            .build()
    }


    var livros = ArrayList<Livro>()
    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros)


        btnDepois.setOnClickListener {
            cont++
            mudarLivro()
        }

        btnAntes.setOnClickListener {
            cont--
            mudarLivro()
        }

        //buttonExcluir.setOnClickListener {
            //db.livroDao().deletar(livros.get(cont))
            //onResume()
        //}
/*
        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i).toString()

            var livro = db.livroDAO().findByName(selected)

            txtTitulo.setText(livro.Titulo.toString())
            txtAutor.setText(livro.Autor.toString())
            txtAno.setText(livro.Ano.toString())
            txtNota.setText(livro.Nota.toString())

        }
*/
        mudarLivro()
        //autoComplete()


    }

    fun mudarLivro() {

        if (livros.size > 0) {
            txtTitulo.text = livros.get(cont).Titulo
            txtAutor.text = livros.get(cont).Autor
            txtAno.text = livros.get(cont).Ano.toString()
            txtNota.text = livros.get(cont).Nota.toString()

            btnAntes.visibility = View.VISIBLE
            btnDepois.visibility = View.VISIBLE
            //buttonExcluir.visibility = View.VISIBLE
            novoLivro()
        } else {
            btnAntes.visibility = View.INVISIBLE
            btnDepois.visibility = View.INVISIBLE
            //buttonExcluir.visibility = View.INVISIBLE
            limpar()
        }

    }

    fun voltarProximo() {
        if (cont + 1 >= livros.size) {
            btnDepois.visibility = View.INVISIBLE
        } else {
            btnDepois.visibility = View.VISIBLE
        }

        if (cont - 1 < 0) {
            btnAntes.visibility = View.INVISIBLE
        } else {
            btnAntes.visibility = View.VISIBLE
        }
    }


    fun limpar() {
        txtTitulo.text = ""
        txtAutor.text = ""
        txtAno.text = ""
        txtNota.text = ""
    }

    fun novoLivro() {
        //txtTitulo.text = livros.get(cont).Titulo.toString()
        //txtAutor.text = livros.get(cont).Autor.toString()
        //txtAno.text = livros.get(cont).Ano.toString()
        //txtNota.text = livros.get(cont).Nota.toString()
        voltarProximo()

    }
/*
    fun autoComplete() {
        var livrosAutoComplete = arrayOfNulls<String>(livros.size)

        for (i in 0 until livros.size) {
            livrosAutoComplete[i] = livros.get(i).Titulo
        }

        var livrosToListAdapter =
            ArrayAdapter<String>(this, R.layout.activity_main2, livrosAutoComplete)

        autoCompleteTextView.setAdapter(livrosToListAdapter)
    }
*/
    override fun onResume() {
        super.onResume()
        livros.clear()//limpar lista

        db.livroDAO().listAll().forEach { // atualizar lista
            livros.add(it)
        }

        cont = 0
        mudarLivro()
        //autoComplete()
    }


}
