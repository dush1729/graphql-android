package com.dush1729.pokedex.main.contract

import com.dush1729.commons.BasePresenter
import com.dush1729.commons.BaseState
import com.dush1729.commons.BaseView
import com.dush1729.repository.GetPokemonQuery

interface IMainView : BaseView {
    fun onError(error: Throwable)
    fun onPokemonFetched(pokemon: GetPokemonQuery.Pokemon)
}

sealed class MainState : BaseState {
    data class PokemonState(
        val isLoading: Boolean = false,
        val error: Throwable? = null,
        val pokemon: GetPokemonQuery.Pokemon? = null
    ) : MainState()
}

interface IMainPresenter : BasePresenter<IMainView, MainState> {
    fun getPokemon(pokemonName: String)
}