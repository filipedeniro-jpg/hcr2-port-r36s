package com.vungle.warren;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.os.ResultReceiver;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"RestrictedApi"})
public class SDKDownloadClient {
    private WebView adWebView;
    private ValidationCheck appMarketValidation;
    private ResultReceiver callBackReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: com.vungle.warren.SDKDownloadClient.1
        @Override // android.support.v4.os.ResultReceiver
        @SuppressLint({"SetTextI18n"})
        protected void onReceiveResult(int i, Bundle bundle) {
            if (i == 6) {
                bundle.getString(DirectDownloadAdapter.PACKAGE_NAME);
                boolean z = bundle.getBoolean(DirectDownloadAdapter.RESULT);
                boolean z2 = bundle.getBoolean(DirectDownloadAdapter.IN_APP_PURCHASE, false);
                if (SDKDownloadClient.this.installStatusCheck != null) {
                    SDKDownloadClient.this.installStatusCheck.isAppInstalled(z, z2);
                    return;
                }
                return;
            }
            if (i == 16) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0})");
                    return;
                }
                return;
            }
            if (i == 17) {
                float f = bundle.getInt(DirectDownloadAdapter.PROGRESS) / 100.0f;
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0,\"percentage\":" + f + "})");
                    return;
                }
                return;
            }
            if (i == 18) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":0})");
                    return;
                }
                return;
            }
            if (i == 19) {
                boolean z3 = bundle.getBoolean(DirectDownloadAdapter.RESULT, true);
                if (SDKDownloadClient.this.adWebView != null) {
                    if (!z3) {
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":-1})");
                        return;
                    } else {
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":1})");
                        return;
                    }
                }
                return;
            }
            if (i != 26 && i == 36) {
                bundle.getString(DirectDownloadAdapter.PACKAGE_NAME);
                boolean z4 = bundle.getBoolean(DirectDownloadAdapter.RESULT);
                if (SDKDownloadClient.this.appMarketValidation != null) {
                    SDKDownloadClient.this.appMarketValidation.validateAppPresenceInMarket(z4);
                }
            }
        }
    };
    private InstallStatusCheck installStatusCheck;
    private String pkgName;
    private ResultReceiver sendingReceiver;

    public interface InstallStatusCheck {
        void isAppInstalled(boolean z, boolean z2);
    }

    public interface ValidationCheck {
        void validateAppPresenceInMarket(boolean z);
    }

    public SDKDownloadClient(String str) {
        this.pkgName = str;
    }

    public void setSendingReceiver(ResultReceiver resultReceiver) {
        this.sendingReceiver = resultReceiver;
    }

    public void setAdWebView(WebView webView) {
        this.adWebView = webView;
    }

    public void sendDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        this.sendingReceiver.send(11, bundle);
    }

    public void sendOpenPackageRequest() {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        this.sendingReceiver.send(51, bundle);
    }

    public void cancelDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        this.sendingReceiver.send(21, bundle);
    }

    public void installStatusRequest() {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        this.sendingReceiver.send(1, bundle);
    }

    public ResultReceiver getCallBackReceiver() {
        return this.callBackReceiver;
    }

    public void sendADDisplayingNotify(boolean z, DirectDownloadAdapter.CONTRACT_TYPE contract_type) {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        bundle.putString(DirectDownloadAdapter.ADTYPE, contract_type.name());
        this.sendingReceiver.send(z ? 71 : 72, bundle);
    }

    protected void sendValidation(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, str);
        this.sendingReceiver.send(31, bundle);
    }

    private void requestDetailOpen() {
        Bundle bundle = new Bundle();
        bundle.putString(DirectDownloadAdapter.PACKAGE_NAME, this.pkgName);
        this.sendingReceiver.send(61, bundle);
    }

    public void setAppMarketValidation(ValidationCheck validationCheck) {
        this.appMarketValidation = validationCheck;
    }

    public void setInstallStatusCheck(InstallStatusCheck installStatusCheck) {
        this.installStatusCheck = installStatusCheck;
    }

    public void cleanUp() {
        this.adWebView = null;
    }
}
