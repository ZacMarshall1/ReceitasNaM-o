package com.example.myapplication.ui.screens.DataBase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReceitaRepository @Inject constructor(
    private val receitasDao: ReceitasDao, // Corrigido o nome para "receitasDao"
) {

    // Adicionar uma nova receita
    suspend fun addReceita(receita: MinhaReceita) {
        receitasDao.gravarReceita(receita) // Usando o método "gravarReceita" do ReceitasDao
    }

    // Atualizar a receita (caso necessário)
    suspend fun updateReceita(receita: MinhaReceita) {
        receitasDao.gravarReceita(receita) // Para atualizar, o Upsert já cuida disso
    }

    // Excluir uma receita
    suspend fun deleteReceita(receita: MinhaReceita) {
        receitasDao.excluirReceita(receita)
    }

    // Excluir todas as receitas de um tipo
    suspend fun deleteReceitasPorTipo(tipo: String) {
        receitasDao.excluirReceitasPorTipo(tipo)
    }

    // Obter todas as receitas
    suspend fun getAllReceitas(): Flow<List<MinhaReceita>> {
        return receitasDao.listarReceitas().flowOn(Dispatchers.IO).conflate()
    }

    // Obter receitas por tipo
    suspend fun getReceitasPorTipo(tipo: String): Flow<List<MinhaReceita>> {
        return receitasDao.listarReceitasPorTipo(tipo).flowOn(Dispatchers.IO).conflate()
    }

    // Buscar receita por ID
    suspend fun getReceitaPorId(id: Int): MinhaReceita? {
        return receitasDao.buscarReceitaPorId(id)
    }

    // Buscar receitas por título e tipo
    suspend fun getReceitasPorTituloETipo(titulo: String, tipo: String): Flow<List<MinhaReceita>> {
        return receitasDao.buscarReceitasPorTituloETipo(titulo, tipo).flowOn(Dispatchers.IO).conflate()
    }
}
