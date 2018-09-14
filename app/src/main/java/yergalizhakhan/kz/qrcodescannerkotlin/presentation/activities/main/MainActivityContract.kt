package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.main

import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History

object MainActivityContract {

    interface View: BaseMvpView {
        fun showSuccessScanningDialog(result: String)
        fun continueScanning()
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun qrCodeScanned(history: History)
        fun searchByResultBtnPressed(result:String)
        fun copyResultBtnPressed(result: String)
        fun shareResultBtnPressed(result: String)
    }
}