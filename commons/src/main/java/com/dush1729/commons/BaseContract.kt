package com.dush1729.commons

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

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

    fun <T> Observable<T>.subscribeDisposable(onNext: (T) -> Unit, onError: (Throwable) -> Unit) {
        this.compose(applyObservableSchedulers()).subscribe(onNext, onError).addToDisposables()
    }

    fun <T> applyObservableSchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { observer ->
            observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}