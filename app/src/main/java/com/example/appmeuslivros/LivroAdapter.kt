package com.example.appmeuslivros

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class LivroAdapter(val c: Context, val livros: List<Livro>) : BaseAdapter() {

    var context:Context = c

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var v = LayoutInflater.from(context).inflate(R.layout.listagemlivros, parent, false)
        var nomeLivro = v.findViewById<TextView>(R.id.nomeLivro)
        var imageLivro = v.findViewById<ImageView>(R.id.imgLivro)

        var livroAtual = livros.get(position)

        nomeLivro.text = livroAtual.Titulo
        //imageLivro.setImageResource(livroAtual.id)

        return v
    }

    override fun getItem(position: Int): Any {
        return livros.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return livros.size
    }
}