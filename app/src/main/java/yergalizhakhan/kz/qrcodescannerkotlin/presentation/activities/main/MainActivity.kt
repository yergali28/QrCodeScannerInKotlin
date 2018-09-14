package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.main

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_scan_success.*
import kotlinx.android.synthetic.main.dialog_scan_success.view.*
import me.dm7.barcodescanner.zxing.ZXingScannerView
import yergalizhakhan.kz.qrcodescannerkotlin.R
import yergalizhakhan.kz.qrcodescannerkotlin.data.ORM.HistoryORM
import yergalizhakhan.kz.qrcodescannerkotlin.domain.History
import yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.history.HistoryActivity
import java.text.DateFormat
import java.util.*

class MainActivity : BaseMvpActivity<MainActivityContract.View, MainActivityContract.Presenter>(),
        MainActivityContract.View, View.OnClickListener, ZXingScannerView.ResultHandler {

    private var mScannerView: ZXingScannerView? = null
    private var flashState: Boolean = false
    private var dialog: AlertDialog? = null
    private var mHistoryOrm: HistoryORM? = null

    override var mPresenter: MainActivityContract.Presenter = MainActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mHistoryOrm = HistoryORM()
        initUI()
    }

    override fun onResume() {
        super.onResume()
        mScannerView?.setResultHandler(this)
        mScannerView?.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView?.stopCamera()
    }

    private fun initUI() {
        mScannerView = ZXingScannerView(this)
        frmContent.addView(mScannerView)
        btnLight.setOnClickListener(this)
        btnHistory.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnLight -> {
                if(flashState) {
                    v.setBackgroundResource(R.drawable.ic_flash_on)
                    showMessage(R.string.flashlight_turned_off)
                    mScannerView?.setFlash(false)
                    flashState = false
                }else {
                    v.setBackgroundResource(R.drawable.ic_flash_off)
                    showMessage(R.string.flashlight_turned_on)
                    mScannerView?.setFlash(true)
                    flashState = true
                }
            }
            R.id.btnHistory -> {
                startActivity(Intent(this, HistoryActivity::class.java))
            }
        }
    }

    override fun handleResult(result: Result?) {
        var history: History = History(
                DateFormat.getDateTimeInstance().format(Calendar.getInstance().time),
                result?.text.toString())
        mHistoryOrm?.add(this, history)
        mPresenter.qrCodeScanned(history)

    }

    override fun showSuccessScanningDialog(result: String) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_scan_success, null)
        val dialogBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
        mDialogView.tvResult.text = result
        mDialogView.btnSearch.setOnClickListener {
            mPresenter.searchByResultBtnPressed(result)
        }
        mDialogView.btnCopy.setOnClickListener {
            mPresenter.copyResultBtnPressed(result)
        }
        mDialogView.btnShare.setOnClickListener {
            mPresenter.shareResultBtnPressed(result)
        }
        dialog = dialogBuilder.create()
        dialog?.setOnCancelListener {
            continueScanning()
        }
        dialog?.show()
    }

    override fun continueScanning() {
        dialog?.dismiss()
        mScannerView?.resumeCameraPreview(this)
    }
}
