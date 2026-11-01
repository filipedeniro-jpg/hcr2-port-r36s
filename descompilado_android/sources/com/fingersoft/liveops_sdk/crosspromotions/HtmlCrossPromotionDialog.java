package com.fingersoft.liveops_sdk.crosspromotions;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.amazon.device.ads.WebRequest;
import com.fingersoft.liveops_sdk.LiveopsManager;
import com.fingersoft.liveops_sdk.utils.CommonUtils;
import com.fingersoft.liveops_sdk.utils.Log;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
public class HtmlCrossPromotionDialog {
    static final String APPRELEASE_APPNAME_ID = "app_name";
    static final String APPRELEASE_DATAFILE = "ignored_appreleases";
    static final String APPRELEASE_DESCRIPTION_ID = "app_desc";
    static final String APPRELEASE_PACKAGE_ID = "app_package_id";
    static final String APPRELEASE_TITLE_ID = "app_title";
    private static CrossPromotionCollection.CrossPromotion mCurrentPromotion;
    private static PopupWindow mPopupWindow;
    private static WebView mWebView;
    private ICrossPromotionListener mCrossPromotionListener;
    private CrossPromotionManager mCrossPromotionManager;
    private boolean mForceShowPromotion;
    LiveopsManager mLiveopsManager;
    volatile long mTimeout = 0;
    int mStartupCount = 0;
    String mIgnoreList = "";

    public HtmlCrossPromotionDialog(LiveopsManager liveopsManager, CrossPromotionManager crossPromotionManager, boolean z, ICrossPromotionListener iCrossPromotionListener) {
        this.mForceShowPromotion = false;
        this.mLiveopsManager = liveopsManager;
        this.mCrossPromotionListener = iCrossPromotionListener;
        this.mCrossPromotionManager = crossPromotionManager;
        this.mForceShowPromotion = z;
    }

    private CrossPromotionCollection.CrossPromotion getCrossPromotion() {
        CommonUtils.log("Getting crosspromotion");
        if (this.mLiveopsManager.getVIPStatus()) {
            CommonUtils.log("Promotion vip");
            return null;
        }
        if (this.mLiveopsManager.getContainer() == null) {
            CommonUtils.log("Promotion Container is null");
            return null;
        }
        if (this.mLiveopsManager.getContainer().getPromotions() == null) {
            CommonUtils.log("Promotion promotions is null");
            return null;
        }
        CrossPromotionCollection promotions = this.mLiveopsManager.getContainer().getPromotions();
        CommonUtils.log("Promotion promotions collection " + promotions.getCrossPromotions().size());
        for (CrossPromotionCollection.CrossPromotion crossPromotion : promotions.getCrossPromotions()) {
            CommonUtils.log("Promotion force show promotion: " + this.mForceShowPromotion);
            if (!this.mForceShowPromotion) {
                if (this.mCrossPromotionManager.checkIfReshowTimeHasPassed(crossPromotion.getGuid(), crossPromotion.getReshowTime())) {
                    CommonUtils.log("Crosspromotion reshow timer has passed");
                    CommonUtils.log("Crosspromotion ignore list: " + this.mIgnoreList.contains(crossPromotion.getGuid()) + " is installed: " + isInstalled(crossPromotion.getTargetPackageName()));
                    if (isInstalled(crossPromotion.getTargetPackageName())) {
                        break;
                    }
                    CommonUtils.log("Crosspromotion target package not found, ok");
                    CommonUtils.log("Crosspromotion can show ads, ok " + crossPromotion.getStartCount() + " " + this.mLiveopsManager.getStartCountWithVersion());
                    if (crossPromotion.getStartCount() >= 0 && crossPromotion.getStartCount() <= this.mLiveopsManager.getStartCountWithVersion()) {
                        CommonUtils.log("Crosspromotion startcount, ok");
                        if (!crossPromotion.notNow()) {
                            return crossPromotion;
                        }
                        CommonUtils.log("No ads clicked for this promo, not showing.");
                    }
                } else {
                    continue;
                }
            } else if (!this.mIgnoreList.contains(crossPromotion.getGuid()) && !isInstalled(crossPromotion.getTargetPackageName())) {
                return crossPromotion;
            }
        }
        CommonUtils.log("Promotion was not there...");
        return null;
    }

    private boolean createDialog() {
        try {
            if (mPopupWindow != null) {
                CommonUtils.log("Promo already exists");
                return false;
            }
            CommonUtils.log("Creating promo");
            this.mIgnoreList = this.mLiveopsManager.getActivity().getSharedPreferences(APPRELEASE_DATAFILE, 0).getString("ignore", "");
            CrossPromotionCollection.CrossPromotion crossPromotion = getCrossPromotion();
            if (crossPromotion == null) {
                CommonUtils.log("Promotion null");
                return false;
            }
            CommonUtils.log("Got promo");
            if (crossPromotion.getPromotionType().equals(CrossPromotionCollection.PromotionType.REWARD)) {
                this.mLiveopsManager.getActivity();
            }
            mCurrentPromotion = crossPromotion;
            this.mCrossPromotionManager.setCrossPromotionPlacementInt(crossPromotion.getPromotionPlacement());
            Activity activity = this.mLiveopsManager.getActivity();
            mPopupWindow = new PopupWindow();
            mWebView = new WebView(activity);
            mWebView.setSystemUiVisibility(2);
            WebSettings settings = mWebView.getSettings();
            if (Build.VERSION.SDK_INT >= 17) {
                mWebView.addJavascriptInterface(new CrossPromotionInterface(activity, this), Constants.JAVASCRIPT_INTERFACE_NAME);
                settings.setJavaScriptEnabled(true);
                settings.setCacheMode(2);
                CommonUtils.log("jscript enabled");
            } else {
                CommonUtils.log("Promotion JavaScript disabled");
            }
            String htmlString = mCurrentPromotion.getHtmlString();
            if (htmlString == null) {
                CommonUtils.log("html string is null");
                return false;
            }
            mWebView.loadData(htmlString, WebRequest.CONTENT_TYPE_HTML, "UTF-8");
            mWebView.setWebChromeClient(new WebChromeClient() { // from class: com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog.1
                @Override // android.webkit.WebChromeClient
                public void onConsoleMessage(String str, int i, String str2) {
                    Log.e("", "WEB :: " + str + " (@line " + i + ")");
                }
            });
            mWebView.setWebViewClient(new WebViewClient() { // from class: com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog.2
                @Override // android.webkit.WebViewClient
                public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                    webView.loadUrl(str);
                    Log.e("", "loading url " + str);
                    return true;
                }

                @Override // android.webkit.WebViewClient
                public void onPageFinished(WebView webView, String str) {
                    super.onPageFinished(webView, str);
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
            this.mLiveopsManager.getActivity().getCurrentFocus();
            mWebView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog.3
                @Override // android.view.View.OnLongClickListener
                public boolean onLongClick(View view) {
                    return true;
                }
            });
            Point point = new Point();
            this.mLiveopsManager.getActivity().getWindowManager().getDefaultDisplay().getRealSize(point);
            int i = point.x;
            int i2 = point.y;
            if (i2 <= i) {
                i = i2;
                i2 = i;
            }
            mPopupWindow.setWidth(i2);
            mPopupWindow.setHeight(i);
            mPopupWindow.setTouchable(false);
            mPopupWindow.setAnimationStyle(R.style.Animation);
            mPopupWindow.update();
            mWebView.setBackgroundColor(Color.argb(1, 0, 0, 0));
            mWebView.setVisibility(4);
            mWebView.setInitialScale(1);
            CommonUtils.log("Promo done" + mWebView.toString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.log("Error in creating cross promotion: " + e.getMessage());
            Log.e("", "error in creating promo " + e);
            return true;
        }
    }

    public void yesButtonClicked() {
        if (mCurrentPromotion == null) {
            return;
        }
        try {
            CommonUtils.log("Promo: Yes button clicked, save to ignore list");
            if (this.mIgnoreList.length() > 0) {
                this.mIgnoreList += "," + mCurrentPromotion.getGuid();
            } else {
                this.mIgnoreList = "" + mCurrentPromotion.getGuid();
            }
            SharedPreferences.Editor editorEdit = this.mLiveopsManager.getActivity().getSharedPreferences(APPRELEASE_DATAFILE, 0).edit();
            editorEdit.putString("ignore", this.mIgnoreList);
            editorEdit.apply();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.log("Yes button clicked, failure");
        }
        try {
            this.mLiveopsManager.getActivity().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(mCurrentPromotion.getClickUrl())));
            this.mCrossPromotionManager.recordCrosspromotionClick(mCurrentPromotion.getGuid());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void noButtonClicked() {
        CrossPromotionCollection.CrossPromotion crossPromotion = mCurrentPromotion;
        if (crossPromotion == null) {
            return;
        }
        crossPromotion.setNotNow();
    }

    public void neverButtonClicked() {
        try {
            if (this.mIgnoreList.length() > 0) {
                this.mIgnoreList += "," + mCurrentPromotion.getGuid();
            } else {
                this.mIgnoreList += mCurrentPromotion.getGuid();
            }
            SharedPreferences.Editor editorEdit = this.mLiveopsManager.getActivity().getSharedPreferences(APPRELEASE_DATAFILE, 0).edit();
            editorEdit.putString("ignore", this.mIgnoreList);
            editorEdit.commit();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.log("Never button clicked, failure");
        }
    }

    public void onPromotionLoaded() {
        CommonUtils.log("On promotion loaded");
        ICrossPromotionListener iCrossPromotionListener = this.mCrossPromotionListener;
        if (iCrossPromotionListener != null) {
            iCrossPromotionListener.onCrossPromotionLoaded();
        }
    }

    public void showPromotion(final boolean z) {
        CommonUtils.log("Cross promotion, liveopsmanager is ?" + z);
        if (mPopupWindow != null) {
            this.mLiveopsManager.getActivity().runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog.4
                @Override // java.lang.Runnable
                public void run() {
                    if (z || HtmlCrossPromotionDialog.this.mLiveopsManager.getVIPStatus()) {
                        PopupWindow unused = HtmlCrossPromotionDialog.mPopupWindow = null;
                        return;
                    }
                    CommonUtils.log("Cross promotion, liveopsmanager is 2? vipstatus: " + HtmlCrossPromotionDialog.this.mLiveopsManager.getVIPStatus());
                    View currentFocus = HtmlCrossPromotionDialog.this.mLiveopsManager.getActivity().getCurrentFocus();
                    if (currentFocus == null) {
                        PopupWindow unused2 = HtmlCrossPromotionDialog.mPopupWindow = null;
                        return;
                    }
                    CommonUtils.log("Cross promotion, liveopsmanager is 3?");
                    if (HtmlCrossPromotionDialog.this.mLiveopsManager.getActivity().isFinishing()) {
                        return;
                    }
                    try {
                        CommonUtils.log("Cross promotion, liveopsmanager is 4?");
                        HtmlCrossPromotionDialog.mWebView.setVisibility(0);
                        HtmlCrossPromotionDialog.mPopupWindow.showAsDropDown(currentFocus, 0, -currentFocus.getHeight());
                        HtmlCrossPromotionDialog.mPopupWindow.setTouchable(true);
                        HtmlCrossPromotionDialog.mPopupWindow.update();
                        HtmlCrossPromotionDialog.this.mCrossPromotionManager.recordCrosspromotionImpression(HtmlCrossPromotionDialog.mCurrentPromotion.getGuid());
                        if (!HtmlCrossPromotionDialog.this.mForceShowPromotion) {
                            HtmlCrossPromotionDialog.this.mCrossPromotionManager.savePromotionShowTime(HtmlCrossPromotionDialog.mCurrentPromotion.getGuid());
                            HtmlCrossPromotionDialog.this.mCrossPromotionManager.onCrossPromotionShow();
                        }
                        HtmlCrossPromotionDialog.this.hideSystemUI(HtmlCrossPromotionDialog.mPopupWindow.getContentView());
                    } catch (Exception e) {
                        e.printStackTrace();
                        CommonUtils.log("Most likely trying to open crosspromo too early... " + e.toString());
                    }
                }
            });
        }
    }

    public void dismissPromotion() {
        CommonUtils.log("Promotion Dismiss");
        if (mPopupWindow != null) {
            CommonUtils.log("Promotion Dismiss 2");
            this.mLiveopsManager.getActivity().runOnUiThread(new Runnable() { // from class: com.fingersoft.liveops_sdk.crosspromotions.HtmlCrossPromotionDialog.5
                @Override // java.lang.Runnable
                public void run() {
                    CommonUtils.log("Promotion Dismiss 3");
                    if (HtmlCrossPromotionDialog.mPopupWindow != null) {
                        CommonUtils.log("Promotion Dismiss 4");
                        HtmlCrossPromotionDialog.mPopupWindow.dismiss();
                        PopupWindow unused = HtmlCrossPromotionDialog.mPopupWindow = null;
                        HtmlCrossPromotionDialog.this.hideNavigationKeys();
                        CommonUtils.log("dismiss called");
                    }
                }
            });
        }
    }

    public String getPromotionGUID() {
        CrossPromotionCollection.CrossPromotion crossPromotion = mCurrentPromotion;
        if (crossPromotion != null) {
            return crossPromotion.getGuid();
        }
        return null;
    }

    private boolean isInstalled(String str) {
        return this.mLiveopsManager.isPackageInstalled(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideNavigationKeys() {
        this.mLiveopsManager.getActivity().getWindow().getDecorView().setSystemUiVisibility(2);
    }

    public void destroy() {
        PopupWindow popupWindow = mPopupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
            mPopupWindow = null;
        }
    }

    public void setTimeout(long j) {
        if (j == 0) {
            this.mTimeout = 0L;
        } else {
            this.mTimeout = System.currentTimeMillis() + j;
        }
    }

    public void execute(long j, int i) {
        try {
            createDialog();
        } catch (Exception e) {
            e.printStackTrace();
            CommonUtils.log("Show x promo html dialog failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideSystemUI(View view) {
        view.setSystemUiVisibility(1798);
    }
}
