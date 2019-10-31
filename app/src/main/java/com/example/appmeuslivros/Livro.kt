package com.example.appmeuslivros


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Livro(
    var Titulo:String,
    var Autor:String,
    var Ano:Int,
    var Nota:Float
    //var img: Int
) {
    var bitten:Boolean=false
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0
}