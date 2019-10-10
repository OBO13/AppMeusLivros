package com.example.appmeuslivros


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livro(
    var Titulo:String,
    var Autor:String,
    var Ano:Int,
    var Nota:Float
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}