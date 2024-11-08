package com.example.myapplication.ui.screens.DataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
@Dao
interface ReceitasDao {

    //Listar
    @Query("select * from minhareceita")
    fun listarReceitas(): Flow<List<MinhaReceita>>
    //suspend fun listarReceitas(): List<MinhaReceita>

    //Buscar por Id
    @Query("select * from minhareceita where id = :idx")
    suspend fun buscarReceitaPorId(idx: Int): MinhaReceita

    //Gravar @Update @Insert
    @Upsert
    suspend fun gravarReceita(minhaReceita: MinhaReceita)

    //Excluir
    @Delete
    suspend fun excluirReceita(minhaReceita: MinhaReceita)

}
