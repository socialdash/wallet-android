package com.storiqa.storiqawallet.screen_scan_finger

import android.hardware.fingerprint.FingerprintManager
import android.os.Build
import android.support.annotation.RequiresApi
import com.storiqa.storiqawallet.StoriqaApp
import com.storiqa.storiqawallet.objects.FingerprintHepler

class ScanFingerModel {
    fun startListenForFingerprint(success: () -> Unit, failed: () -> Unit): Unit {
        FingerprintHepler(StoriqaApp.context, @RequiresApi(Build.VERSION_CODES.M)
        object : FingerprintManager.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                super.onAuthenticationError(errorCode, errString)
                failed()
            }

            override fun onAuthenticationSucceeded(result: FingerprintManager.AuthenticationResult?) {
                super.onAuthenticationSucceeded(result)
                success()
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                failed()
            }
        })
    }
}