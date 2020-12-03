package com.dush1729.pokedex.main.activity

import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dush1729.commons.BaseActivity
import com.dush1729.pokedex.R
import com.dush1729.pokedex.base.BaseApplication
import com.dush1729.pokedex.main.contract.IMainPresenter
import com.dush1729.pokedex.main.contract.IMainView
import com.dush1729.pokedex.main.contract.MainState
import com.dush1729.pokedex.main.presenter.MainPresenter
import com.dush1729.repository.GetPokemonQuery
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<IMainView, MainState, IMainPresenter>(), IMainView {
    @Inject
    override lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseApplication.baseInstance?.getMainComponent()?.inject(this)
        presenter.attachView(this)

        btnGetInfo.setOnClickListener {
            presenter.getPokemon(tvPokemonName.text.toString())
        }
    }

    override fun onError(error: Throwable) {
        Toast.makeText(this, error.message.toString(), Toast.LENGTH_LONG).show()
    }

    override fun onPokemonFetched(pokemon: GetPokemonQuery.Pokemon) {
        Glide.with(ivPhoto.context)
            .load(pokemon.image())
            .into(ivPhoto)

        tvName.text = pokemon.name()
        tvTypes.text = pokemon.types()?.joinToString(", ")
        tvClassification.text = pokemon.types()?.joinToString(", ")
        tvWeaknesses.text = pokemon.weaknesses()?.joinToString(", ")
        tvMaxCP.text = pokemon.maxCP()?.toString()
        tvEvolutions.text = pokemon.evolutions()?.map { it.name() }?.joinToString(", ")
    }
}