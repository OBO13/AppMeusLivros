package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_lista_livros.*


class ListaLivros : AppCompatActivity() {

    var currentIdx = 0

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java, "livros"
        )
            .allowMainThreadQueries()
            .build()
    }
    var livros = ArrayList<Livro>()

    var cont = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_livros)

        //var listAll = db.livroDAO().listAll()


        /*
        fun updateTexts() {
            txtTitulo.setText(listAll[currentIdx].Titulo.toString())
            //txtAutor.setText(listAll[currentIdx].Autor.toString())
            //txtAno.setText(listAll[currentIdx].Ano.toString())

        }
*/
        /*
        listAll.forEach { Log.i("APPROOM", it.toString()) }

        if (listAll.size < 1) {
            btnAntes.isEnabled = false
            btnDepois.isEnabled = false
        } else {
            updateTexts()
        }

        btnDepois.setOnClickListener {
            if (listAll.size > currentIdx && currentIdx <= listAll.size) {
                currentIdx++
                updateTexts()
            }
        }

        btnAntes.setOnClickListener {
            if (currentIdx > 0) {
                currentIdx--
                updateTexts()
            }
        }

*/

    }
}