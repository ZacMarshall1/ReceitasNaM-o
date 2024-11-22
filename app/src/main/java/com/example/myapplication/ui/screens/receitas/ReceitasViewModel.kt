package com.example.myapplication.ui.screens.receitas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.ui.screens.DataBase.MinhaReceita
import com.example.myapplication.ui.screens.DataBase.ReceitaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReceitasViewModel(
    private val receitaRepository: ReceitaRepository // Agora utilizando o ReceitaRepository
) : ViewModel() {

    // Função para excluir uma receita
    fun excluir(minhaReceita: MinhaReceita) {
        viewModelScope.launch {
            receitaRepository.deleteReceita(minhaReceita) // Usando o método do repositório
        }
    }

    // Função para gravar uma receita (inserir ou atualizar)
    fun gravar(minhaReceita: MinhaReceita) {
        viewModelScope.launch {
            receitaRepository.addReceita(minhaReceita) // Usando o método do repositório
        }
    }

    // Função suspensa para buscar uma receita por ID
    suspend fun buscarReceitaPorId(receitaID: Int): MinhaReceita? {
        return withContext(Dispatchers.IO) {
            receitaRepository.getReceitaPorId(receitaID) // Usando o método do repositório
        }
    }

    // Flow para manter as receitas em tempo real
    private val _afazeres = MutableStateFlow<List<MinhaReceita>>(emptyList())
    val afazeres: StateFlow<List<MinhaReceita>> get() = _afazeres

    init {
        // Coleta a lista de receitas e atualiza o _afazeres
        viewModelScope.launch {
            receitaRepository.getAllReceitas().collectLatest { listaDeReceitas ->
                _afazeres.value = listaDeReceitas
            }
        }
    }
}
