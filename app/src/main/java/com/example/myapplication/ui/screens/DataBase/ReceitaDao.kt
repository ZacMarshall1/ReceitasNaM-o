package com.example.myapplication.ui.screens.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceitasDao {

    @Query("SELECT * FROM minha_receita ORDER BY titulo ASC")
    fun listarReceitas(): Flow<List<MinhaReceita>>

    @Query("SELECT * FROM minha_receita WHERE tipoReceita = :tipo")
    fun listarReceitasPorTipo(tipo: String): Flow<List<MinhaReceita>>

    @Query("SELECT COUNT(*) FROM minha_receita WHERE tipoReceita = :tipo")
    suspend fun contarReceitasPorTipo(tipo: String): Int

    @Query("SELECT * FROM minha_receita WHERE id = :idx")
    suspend fun buscarReceitaPorId(idx: Int): MinhaReceita?

    @Query("""
        SELECT * FROM minha_receita
        WHERE tipoReceita = :tipo AND titulo LIKE '%' || :titulo || '%'
    """)
    fun buscarReceitasPorTituloETipo(titulo: String, tipo: String): Flow<List<MinhaReceita>>

    @Query("UPDATE minha_receita SET tipoReceita = :novoTipo WHERE id = :id")
    suspend fun atualizarTipoReceita(id: Int, novoTipo: String)

    @Upsert
    suspend fun gravarReceita(minhaReceita: MinhaReceita)

    @Delete
    suspend fun excluirReceita(minhaReceita: MinhaReceita)

    @Query("DELETE FROM minha_receita WHERE tipoReceita = :tipo")
    suspend fun excluirReceitasPorTipo(tipo: String)
}
