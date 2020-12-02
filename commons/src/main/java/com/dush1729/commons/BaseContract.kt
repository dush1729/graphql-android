package com.dush1729.commons

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

interface BaseView

interface BaseState

interface BasePresenter<V : BaseView, S : BaseState> {
    var view: V?
    var disposable: CompositeDisposable

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun updateView(state: S)

    fun Disposable.addToDisposables() {
        disposable.add(this)
    }
}