package com.example.appmeuslivros

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LivroViewHolder (v: View) : RecyclerView.ViewHolder(v) {

    val textViewNome: TextView
    val img: ImageView

    init {
        textViewNome = v.findViewById(R.id.nomeLivro)
        img = v.findViewById(R.id.imgLivro)
    }
}