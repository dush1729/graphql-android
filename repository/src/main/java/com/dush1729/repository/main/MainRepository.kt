package com.dush1729.repository.main

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.rx2.rxQuery
import com.dush1729.repository.GetPokemonQuery
import io.reactivex.Observable
import javax.inject.Inject

// TODO 3 fetching data
class MainRepository @Inject constructor(private val apolloClient: ApolloClient) : IMainRepository {
    override fun getPokemon(pokemonName: String): Observable<GetPokemonQuery.Pokemon> {
        return apolloClient.rxQuery(GetPokemonQuery(pokemonName)).flatMap {
            if (it.errors?.isNotEmpty() == true) {
                Observable.error(Throwable("Error: fetching pokemon. ${it.errors}"))
            } else {
                val pokemon = it.data?.pokemon()
                if(pokemon == null) {
                    Observable.error(Throwable("$pokemonName not found in database"))
                } else {
                    Observable.just(it.data?.pokemon())
                }
            }
        }
    }
}