package com.example.appmeuslivros


import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        btnAntes.setOnClickListener {
            cont--
            novoLivro()
        }

        btnDepois.setOnClickListener {
            cont++
            novoLivro()
        }

        btnDeletar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Atenção!")
            builder.setMessage("Você tem certeza que deseja excluir o registro?")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext, android.R.string.yes, Toast.LENGTH_SHORT).show()
                if (livros.size > 0) {
                    db.livroDAO().deletar(livros.get(cont))
                    onResume()
                } else {
                    limpar()
                }
            }
            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext, android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }

    fun mostrarLivro() {
        if (livros.size > 0) {
            txtTitulo.text = livros.get(cont).Titulo
            txtAutor.text = livros.get(cont).Autor
            txtAno.text = livros.get(cont).Ano.toString()
            txtNota.text = livros.get(cont).Nota.toString()

            btnAntes.visibility = View.VISIBLE
            btnDepois.visibility = View.VISIBLE
            btnDeletar.visibility = View.VISIBLE
            //novoLivro()
        } else {
            btnAntes.visibility = View.INVISIBLE
            btnDepois.visibility = View.INVISIBLE
            btnDeletar.visibility = View.INVISIBLE
            limpar()
        }

    }

    fun limpar() {
        txtTitulo.text = ""
        txtAutor.text = ""
        txtAno.text = ""
        txtNota.text = ""
    }

    fun navegarregistros() {
        if (cont + 1 >= livros.size) {
            btnDepois.visibility = View.INVISIBLE
            Toast.makeText(this, "Não existe mais registros a frente!", Toast.LENGTH_SHORT).show()
        } else {
            btnDepois.visibility = View.VISIBLE
        }

        if (cont - 1 < 0) {
            btnAntes.visibility = View.INVISIBLE
            Toast.makeText(this, "Não existe mais registros anterior!", Toast.LENGTH_SHORT).show()
        } else {
            btnAntes.visibility = View.VISIBLE
        }
    }

    fun novoLivro() {
        txtTitulo.text = livros.get(cont).Titulo.toString()
        txtAutor.text = livros.get(cont).Autor.toString()
        txtAno.text = livros.get(cont).Ano.toString()
        txtNota.text = livros.get(cont).Nota.toString()
        navegarregistros()

    }

    fun Dialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Androidly Alert")
        builder.setMessage("We have a message")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.yes, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            Toast.makeText(applicationContext,
                android.R.string.no, Toast.LENGTH_SHORT).show()
        }
        builder.show()
    }

    /*
    fun autoComplete() {
        var livrosAutoComplete = arrayOfNulls<String>(livros.size)

        for (i in 0 until livros.size) {
            livrosAutoComplete[i] = livros.get(i).Titulo
        }

        var livrosToListAdapter =
            ArrayAdapter<String>(this, R.layout.activity_cadastrar_livro, livrosAutoComplete)

        autoCompleteTextView.setAdapter(livrosToListAdapter)
    }
*/
    override fun onResume() {
        super.onResume()
        livros.clear()//limpar lista

        db.livroDAO().listAll().forEach {
            // atualizar lista
            livros.add(it)
        }

        cont = 0
        mostrarLivro()
        //autoComplete()
    }

}
