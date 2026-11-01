package com.fingersoft.liveops_sdk.eula;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.PopupWindow;
import com.fingersoft.liveops_sdk.LiveopsManager;
import com.fingersoft.liveops_sdk.utils.CommonUtils;
import com.fingersoft.liveops_sdk.utils.Log;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class EULAWindow {
    private static final String EULA_KEY = "EULAVERSION_1_SHOWED";
    private static final int EULA_VERSION = 1;
    private static PopupWindow mPopupWindow;
    private static WebView mWebView;
    private Activity mActivity;
    private View mDecorView;
    private EULAListener mEulaListener;

    public static int getEulaVersion() {
        return 1;
    }

    public EULAWindow(Activity activity, EULAListener eULAListener) {
        this.mEulaListener = eULAListener;
        this.mActivity = activity;
    }

    public boolean show() {
        try {
            if (mPopupWindow != null) {
                return true;
            }
            mPopupWindow = new PopupWindow();
            mWebView = new WebView(this.mActivity);
            mWebView.setSystemUiVisibility(2);
            this.mDecorView = this.mActivity.getWindow().getDecorView();
            WebSettings settings = mWebView.getSettings();
            if (Build.VERSION.SDK_INT >= 17) {
                settings.setJavaScriptEnabled(true);
                settings.setCacheMode(2);
                mWebView.addJavascriptInterface(new EULAInterface(this.mActivity, this), Constants.JAVASCRIPT_INTERFACE_NAME);
            }
            final View currentFocus = this.mActivity.getCurrentFocus();
            mWebView.loadUrl("file:///android_asset/eula.html");
            mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.fingersoft.liveops_sdk.eula.EULAWindow.1
                @Override // android.webkit.WebChromeClient
                public void onConsoleMessage(String str, int i, String str2) {
                    Log.d("Eula", "WEB :: " + str + " (@line " + i + ")");
                }
            });
            mWebView.setWebViewClient(new WebViewClient() { // from class: com.fingersoft.liveops_sdk.eula.EULAWindow.2
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    if (str != null && str.startsWith("file://")) {
                        webView.loadUrl(str);
                        Log.d("Eula", "loading url " + str);
                        return false;
                    }
                    webView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                }

                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
                    webView.setVisibility(0);
                    PopupWindow popupWindow = EULAWindow.mPopupWindow;
                    View view = currentFocus;
                    popupWindow.showAsDropDown(view, 0, -view.getHeight());
                    EULAWindow.mPopupWindow.setOutsideTouchable(false);
                    EULAWindow.mPopupWindow.setTouchable(true);
                    EULAWindow.mPopupWindow.setFocusable(false);
                    EULAWindow.mPopupWindow.update();
                    webView.setBackgroundColor(0);
                }
            });
            mPopupWindow.setContentView(mWebView);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.gravity = 17;
            mWebView.setLayoutParams(layoutParams);
            mWebView.clearCache(true);
            mWebView.getSettings().setLoadWithOverviewMode(true);
            mWebView.getSettings().setUseWideViewPort(true);
            mWebView.setScrollbarFadingEnabled(true);
            mWebView.setVerticalScrollBarEnabled(false);
            mWebView.setHorizontalScrollBarEnabled(false);
            mWebView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fingersoft.liveops_sdk.eula.EULAWindow.3
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            Point point = new Point();
            this.mActivity.getWindowManager().getDefaultDisplay().getRealSize(point);
            int i = point.x;
            int i2 = point.y;
            Log.i(LiveopsManager.TAG, "width        = " + i);
            Log.i(LiveopsManager.TAG, "height       = " + i2);
            Point point2 = new Point();
            this.mActivity.getWindowManager().getDefaultDisplay().getSize(point2);
            int i3 = point2.x;
            int i4 = point2.y;
            Log.i(LiveopsManager.TAG, "width        = " + i3);
            Log.i(LiveopsManager.TAG, "height       = " + i4);
            if (i4 <= i) {
                i = i4;
                i4 = i;
            }
            if (i4 <= i3) {
                i4 = i3;
            }
            Log.i(LiveopsManager.TAG, "width        = " + i4);
            mPopupWindow.setWidth(i4);
            mPopupWindow.setHeight(i);
            mPopupWindow.setTouchable(false);
            mPopupWindow.setAnimationStyle(R.style.Animation);
            mPopupWindow.update();
            mWebView.setBackgroundColor(Color.argb(1, 0, 0, 0));
            mWebView.setVisibility(4);
            mWebView.setInitialScale(1);
            hideSystemUI(mPopupWindow.getContentView());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void hideSystemUI(View view) {
        view.setSystemUiVisibility(1798);
    }

    public void onEulaAccepted() {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.eula.EULAWindow.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (EULAWindow.mPopupWindow != null) {
                        EULAWindow.mPopupWindow.dismiss();
                        PopupWindow unused = EULAWindow.mPopupWindow = null;
                    }
                } catch (Exception unused2) {
                }
            }
        });
        setEulaAccepted();
        EULAListener eULAListener = this.mEulaListener;
        if (eULAListener != null) {
            eULAListener.onEULAAccepted();
        }
    }

    public void onEulaCanceled() {
        this.mActivity.runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.eula.EULAWindow.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (EULAWindow.mPopupWindow != null) {
                        EULAWindow.mPopupWindow.dismiss();
                        PopupWindow unused = EULAWindow.mPopupWindow = null;
                    }
                } catch (Exception unused2) {
                }
            }
        });
        EULAListener eULAListener = this.mEulaListener;
        if (eULAListener != null) {
            eULAListener.onEULACanceled();
        }
    }

    public static boolean isEulaAcceptedAlready(Activity activity) {
        return LiveopsManager.getPrefValueInt(activity, EULA_KEY, 0) != 0;
    }

    private void setEulaAccepted() {
        CommonUtils.log("Eula accepted, set value: EULAVERSION_1_SHOWED");
        LiveopsManager.setPrefValueInt(this.mActivity, EULA_KEY, 1);
    }
}
