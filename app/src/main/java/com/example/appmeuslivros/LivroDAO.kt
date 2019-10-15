package com.example.appmeuslivros

import androidx.room.*

@Dao
interface LivroDAO {

    @Insert
    fun inserir(livro: Livro): Long

    @Delete
    fun deletar(livro: Livro): Int

    @Update
    fun atualizar(livro: Livro): Int

    @Query("SELECT * FROM livro")
    fun listAll(): List<Livro>

    @Query("SELECT * FROM livro WHERE id = :id")
    fun findById(id: Long): Livro

    @Query("SELECT * FROM livro WHERE Titulo = :Titulo")
    fun findByName (Titulo: String) : Livro
}