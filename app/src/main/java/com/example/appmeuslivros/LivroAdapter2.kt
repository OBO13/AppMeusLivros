package com.example.appmeuslivros

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup

class LivroAdapter2(var c: Context, var livros:MutableList<Livro>) : RecyclerView.Adapter<LivroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.livro_card_inflater, parent, false);

        return LivroViewHolder(view)
    }

    override fun getItemCount(): Int {
        return livros.size
    }


    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {

        val livrotoque = livros[position]
        holder.textViewNome.text = livrotoque.Titulo
        holder.img.setImageResource(livrotoque.Ano)

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