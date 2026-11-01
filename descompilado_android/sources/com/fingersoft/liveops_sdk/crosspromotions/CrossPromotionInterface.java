package com.fingersoft.liveops_sdk.crosspromotions;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import com.fingersoft.liveops_sdk.utils.Log;

/* JADX INFO: loaded from: classes.dex */
public class CrossPromotionInterface {
    private Context mContext;
    private HtmlCrossPromotionDialog mCrossPromotionDialog;

    public CrossPromotionInterface(Context context, HtmlCrossPromotionDialog htmlCrossPromotionDialog) {
        Log.e("", "promotion interface ------------>");
        this.mContext = context;
        this.mCrossPromotionDialog = htmlCrossPromotionDialog;
    }

    @JavascriptInterface
    public void showToast(String str) {
        Toast.makeText(this.mContext, str, 0).show();
    }

    @JavascriptInterface
    public void neverButton() {
        this.mCrossPromotionDialog.neverButtonClicked();
        this.mCrossPromotionDialog.dismissPromotion();
        Log.e("", "never pressed");
    }

    @JavascriptInterface
    public void yesButton() {
        Log.e("", "yes pressed");
        this.mCrossPromotionDialog.dismissPromotion();
        this.mCrossPromotionDialog.yesButtonClicked();
    }

    @JavascriptInterface
    public void noButton() {
        Log.e("", "no pressed");
        this.mCrossPromotionDialog.noButtonClicked();
        this.mCrossPromotionDialog.dismissPromotion();
    }

    @JavascriptInterface
    public void promoLoaded() {
        Log.e("", "On promo loaded");
        this.mCrossPromotionDialog.onPromotionLoaded();
    }
}
