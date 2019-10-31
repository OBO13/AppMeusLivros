package com.example.appmeuslivros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_remove_livro_toc_tela.*


class Remove_livro_toc_tela : AppCompatActivity() {

    val db: AppDataBase by lazy {
        Room.databaseBuilder(
            this,
            AppDataBase::class.java,
            "livro"
        )
            .allowMainThreadQueries()
            .build()
    }

    //var livros = ArrayList<Livro>()
    //var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_livro_toc_tela)

        var listaLivros:MutableList<Livro> = db.livroDAO().listAll()

        var adapter = LivroAdapter2(this, listaLivros)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        recyclerview.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@Remove_livro_toc_tela,
                recyclerview,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@Remove_livro_toc_tela, "Mudar a imagem do livro", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaLivros[position]
                        listaLivros.remove(removida)
                        recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@Remove_livro_toc_tela, "Exclusão do registro", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            recyclerview.parent as View,"Removido",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaLivros.add(position, removida)
                                recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

    }
}

