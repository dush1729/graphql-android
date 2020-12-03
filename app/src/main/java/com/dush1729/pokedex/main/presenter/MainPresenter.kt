package com.dush1729.pokedex.main.presenter

import android.util.Log
import com.dush1729.pokedex.main.contract.IMainPresenter
import com.dush1729.pokedex.main.contract.IMainView
import com.dush1729.pokedex.main.contract.MainState
import com.dush1729.repository.main.IMainRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.properties.Delegates

class MainPresenter @Inject constructor(private val mainRepository: IMainRepository) : IMainPresenter {
    override var view: IMainView? = null
    override var disposable: CompositeDisposable = CompositeDisposable()

    private var pokemonState: MainState.PokemonState by Delegates.observable(
        MainState.PokemonState()
    ) { _, oldValue, newValue ->
        if (oldValue != newValue) {
            updateView(newValue)
        }
    }

    override fun getPokemon(pokemonName: String) {
        if(pokemonState.isLoading) {
            return
        }

        pokemonState = pokemonState.copy(isLoading = true, error = null, pokemon = null)
        mainRepository.getPokemon(pokemonName).subscribeDisposable({
            pokemonState = pokemonState.copy(isLoading = false, error = null, pokemon = it)
        }, {
            pokemonState = pokemonState.copy(isLoading = false, error = it, pokemon = null)
            Log.d("MainPresenter", "Error: $it")
        })
    }

    override fun updateView(state: MainState) {
        if(state is MainState.PokemonState) {
            when {
                state.error != null -> view?.onError(state.error)
                state.pokemon != null -> view?.onPokemonFetched(state.pokemon)
            }
        }
    }
}