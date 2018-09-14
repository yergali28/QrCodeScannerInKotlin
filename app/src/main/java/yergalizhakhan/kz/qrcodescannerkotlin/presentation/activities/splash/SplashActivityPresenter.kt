package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.splash

import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl

open class SplashActivityPresenter: BaseMvpPresenterImpl<SplashActivityContract.View>(),
        SplashActivityContract.Presenter {

    override fun permissionGranted() {
        mView?.startActivity()
    }

    override fun permissionDenied() {
        mView?.setupPermissions()
    }


}