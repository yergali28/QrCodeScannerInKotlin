package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.util.Patterns
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import yergalizhakhan.kz.qrcodescannerkotlin.R
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History
import yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.splash.SplashActivityContract

open class MainActivityPresenter: BaseMvpPresenterImpl<MainActivityContract.View>(),
        MainActivityContract.Presenter {

    private val preUrl: String = "http://www.google.com/#q="

    override fun searchByResultBtnPressed(result: String) {
        var url: String = result
        if (!Patterns.WEB_URL.matcher(result).matches())
            url = preUrl + result
        mView?.continueScanning()
        mView?.searchInWWW(url)
    }

    override fun copyResultBtnPressed(result: String) {
        mView?.copyToClipboard(result)
        mView?.continueScanning()
        mView?.showMessage(R.string.text_copied)
    }

    override fun shareResultBtnPressed(result: String) {
        mView?.continueScanning()
        mView?.shareResultViewSharingIntent(result)
    }

    override fun qrCodeScanned(history: History) {
        mView?.showSuccessScanningDialog(history.context)
    }
}