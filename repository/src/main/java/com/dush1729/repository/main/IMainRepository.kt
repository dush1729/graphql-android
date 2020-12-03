package com.dush1729.repository.main

import com.dush1729.repository.GetPokemonQuery
import io.reactivex.Observable

interface IMainRepository {
    fun getPokemon(pokemonName: String): Observable<GetPokemonQuery.Pokemon>
}