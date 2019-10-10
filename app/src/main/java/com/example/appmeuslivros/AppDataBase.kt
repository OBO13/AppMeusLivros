package com.example.appmeuslivros

import androidx.room.Database
import androidx.room.RoomDatabase


@Database (entities = [Livro::class], version = 1)
abstract  class AppDataBase : RoomDatabase() {
    abstract fun livroDAO(): LivroDAO
}