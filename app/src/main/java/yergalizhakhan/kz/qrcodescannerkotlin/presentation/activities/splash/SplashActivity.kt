package yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.splash

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import client.yalantis.com.githubclient.mvp.BaseMvpActivity
import yergalizhakhan.kz.qrcodescannerkotlin.R
import yergalizhakhan.kz.qrcodescannerkotlin.presentation.activities.main.MainActivity

class SplashActivity: BaseMvpActivity<SplashActivityContract.View, SplashActivityContract.Presenter>(),
        SplashActivityContract.View {

    private val RECORD_REQUEST_CODE = 101

    override var mPresenter: SplashActivityContract.Presenter = SplashActivityPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupPermissions()
    }

    override fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

        if (permission != PackageManager.PERMISSION_GRANTED)
            requestPermissions()
        else
            mPresenter.permissionGranted()
    }

    override fun startActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

    override fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                RECORD_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    mPresenter.permissionDenied()
                } else {
                    mPresenter.permissionGranted()
                }
            }
        }
    }

}