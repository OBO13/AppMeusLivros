package com.example.appmeuslivros

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import java.util.*
import java.util.Collections.swap
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class LivroAdapter3(var c:Context, var livros:MutableList<Livro>) : RecyclerView.Adapter<LivroViewHolder2>() {

    private val PENDING_REMOVAL_TIMEOUT:Long = 3000 // 3sec
    var itemsPendingRemoval = ArrayList<Livro>()

    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Livro, Runnable> =
        HashMap() // map de frutas com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder2 {
        val view = LayoutInflater.from(c).inflate(R.layout.novo_livro_inflater, parent, false);

        return LivroViewHolder2(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    override fun onBindViewHolder(holder: LivroViewHolder2, position: Int) {

        val livrotoque = livros[position]
        holder.textViewNome.text = livrotoque.Titulo
        holder.img.setImageResource(livrotoque.Ano)

        if (livrotoque.bitten) {
            holder.img.setImageResource(R.drawable.imglistarlivros)
        } else {
            holder.img.setImageResource(R.drawable.imglivros)
        }

        if (itemsPendingRemoval.contains(livrotoque)) {
            //view do swipe/delete
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                // usou o undo, remover a pendingRennable
                val pendingRemovalRunnable = pendingRunnables[livrotoque]
                Log.i("LIVRO", "CLICOU")
                pendingRunnables.remove(livrotoque)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(livrotoque)
                //binda novamente para redesenhar
                notifyItemChanged(livros.indexOf(livrotoque))
            }
        } else {
            //mostra o padrão
            holder.textViewNome.setText(livrotoque.Titulo)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)
            if (livrotoque.bitten) {
                holder.img.setImageResource(R.drawable.imglistarlivros)
            } else {
                holder.img.setImageResource(R.drawable.imglivros)
            }

            holder.img.setOnClickListener{
                livrotoque.bitten = true
                notifyItemChanged(position)
            }
        }


    }

    fun remover (position: Int){
        var livro = livros[position]

        if (livros.contains(livro)){
            livros.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removerComTempo(position: Int) {

        val livro = livros[position]
        if (!itemsPendingRemoval.contains(livro)) {
            itemsPendingRemoval.add(livro)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[livro] = pendingRemovalRunnable
        }
    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                swap(livros, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                swap(livros, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)
    }
}