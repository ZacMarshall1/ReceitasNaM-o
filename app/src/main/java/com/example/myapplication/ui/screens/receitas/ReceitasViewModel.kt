package com.example.myapplication.ui.screens.receitas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.screens.DataBase.MinhaReceita
import com.example.myapplication.ui.screens.DataBase.ReceitasDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceitasViewModel(
    private val dao : ReceitasDao
) : ViewModel(){

    fun excluir(minhaReceita: MinhaReceita) {
        viewModelScope.launch {
            dao.excluirReceita(minhaReceita)
        }
    }

    fun gravar(minhaReceita: MinhaReceita) {
        viewModelScope.launch {
            dao.gravarReceita(minhaReceita)
        }
    }

    suspend fun buscarReceitaPorId(receitaID: Int): MinhaReceita? {
        return withContext(Dispatchers.IO){
            dao.buscarReceitaPorId(receitaID)
        }
    }

    private val _afazeres = MutableStateFlow<List<MinhaReceita>>(emptyList())
    val afazeres: StateFlow<List<MinhaReceita>> get() = _afazeres

    init {
        viewModelScope.launch {
            dao.listarReceitas().collectLatest { listaDeReceitas ->
                _afazeres.value = listaDeReceitas
            } //.collectLastest
        }
    }

}
