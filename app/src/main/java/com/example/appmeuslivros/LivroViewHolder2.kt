package com.example.appmeuslivros

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LivroViewHolder2 (v: View) : RecyclerView.ViewHolder(v) {

    val textViewNome: TextView = v.findViewById(R.id.nomeLivro)
    val img: ImageView = v.findViewById(R.id.imgLivro)
    val layoutNormal: LinearLayout = v.findViewById(R.id.layout_normal)
    val layoutGone: LinearLayout = v.findViewById(R.id.layout_gone)

    val undoButton: Button = v.findViewById(R.id.undo_button)

}