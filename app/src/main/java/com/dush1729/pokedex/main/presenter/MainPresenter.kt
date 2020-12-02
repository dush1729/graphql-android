package com.dush1729.pokedex.main.presenter

import com.dush1729.pokedex.main.contract.IMainPresenter
import com.dush1729.pokedex.main.contract.IMainView
import com.dush1729.pokedex.main.contract.MainState
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MainPresenter @Inject constructor() : IMainPresenter {
    override var view: IMainView? = null
    override var disposable: CompositeDisposable = CompositeDisposable()

    override fun updateView(state: MainState) {
    }
}