package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.history

import android.content.Context
import client.yalantis.com.githubclient.mvp.BaseMvpPresenter
import client.yalantis.com.githubclient.mvp.BaseMvpView
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History

object HistoryActivityContract {

    interface View: BaseMvpView {
        fun showHistory(histories: MutableList<History>)
    }

    interface Presenter: BaseMvpPresenter<View> {
        fun loadHistory(context: Context)
    }
}