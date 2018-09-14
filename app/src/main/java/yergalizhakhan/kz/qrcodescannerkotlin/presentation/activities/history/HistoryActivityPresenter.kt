package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.history

import android.content.Context
import client.yalantis.com.githubclient.mvp.BaseMvpPresenterImpl
import yergalizhakhan.kz.qrcodescannerkotlin.data.ORM.HistoryORM

open class HistoryActivityPresenter: BaseMvpPresenterImpl<HistoryActivityContract.View>(),
        HistoryActivityContract.Presenter {

    private var historyORM: HistoryORM? = null

    override fun loadHistory(context: Context) {
        historyORM = HistoryORM()
        mView?.showHistory(historyORM!!.getAll(context))
    }

}