package com.example.appmeuslivros

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper.*
import kotlinx.android.synthetic.main.activity_remove_livro_toc_tela.*


class Mov_Lat_Hor_excluir : AppCompatActivity() {

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
        setContentView(R.layout.activity_mov__lat__hor_excluir)




        var listaLivros:MutableList<Livro> = db.livroDAO().listAll()

        var adapter = LivroAdapter3(this, listaLivros)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            UP or DOWN, START or END  )
        {

            override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
                Log.i("LIVRO", "OnMove")
                //é usado para operações drag and drop
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val adapter = recyclerView.adapter as LivroAdapter3
                adapter.mover(fromPosition, toPosition)
                return true// true se moveu, se não moveu, retorne falso
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var posicao = viewHolder.adapterPosition
                var adapter = recyclerview.adapter as LivroAdapter3

                //adapter.remover(posicao)
                adapter.removerComTempo(posicao)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val background = ColorDrawable(resources.getColor(R.color.red))
                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.adapterPosition === -1) {
                    // not interested in those
                    return
                }
                Log.i("LIVRO", "dx = $dX")
                // Here, if dX > 0 then swiping right.
                // If dX < 0 then swiping left.
                // If dX == 0 then at at start position.
                // draw red background
                if (dX < 0) {
                    Log.i("LIVRO", "dX < 0")
                    background.setBounds(
                        (itemView.right + dX).toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                } else if (dX > 0) {
                    Log.i("LIVRO", "dX > 0")
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        (dX).toInt(),
                        itemView.bottom
                    )
                }
                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun isLongPressDragEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o drag and drop
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o swipe
                return true
            }

        })

        itemTouchHelper.attachToRecyclerView(recyclerview)

    }

}