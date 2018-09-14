package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.splash

import android.content.Intent
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView

object SplashActivityContract {

    interface View : BaseMvpView {
        fun setupPermissions()
        fun startActivity()
        fun requestPermissions()
    }

    interface Presenter : BaseMvpPresenter<View> {
        fun permissionGranted()
        fun permissionDenied()
    }
}