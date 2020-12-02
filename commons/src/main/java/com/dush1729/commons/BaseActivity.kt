package com.dush1729.commons

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<V : BaseView, S : BaseState, P : BasePresenter<V, S>> :
    AppCompatActivity() {
    abstract val presenter: P

    override fun onStop() {
        super.onStop()
        if (isFinishing || isDestroyed) {
            presenter.detachView()
        }
    }
}