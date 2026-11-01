package com.fingersoft.liveops_sdk.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import com.fingersoft.liveops_sdk.LiveopsManager;

/* JADX INFO: loaded from: classes.dex */
public class AppRatingDialog implements DialogInterface.OnClickListener {
    Activity mActivity;
    String mAppName;
    LiveopsManager mLiveopsManager;
    int mMarketVariation;
    String mPackageId;

    public AppRatingDialog(LiveopsManager liveopsManager, int i) {
        this.mMarketVariation = 0;
        this.mAppName = null;
        this.mPackageId = null;
        this.mLiveopsManager = liveopsManager;
        this.mActivity = this.mLiveopsManager.getActivity();
        this.mMarketVariation = i;
        try {
            PackageManager packageManager = this.mActivity.getPackageManager();
            this.mAppName = packageManager.getApplicationLabel(packageManager.getPackageInfo(this.mActivity.getPackageName(), 0).applicationInfo).toString();
            this.mPackageId = this.mActivity.getPackageName();
        } catch (Exception unused) {
        }
    }

    public Uri getRatingUri() {
        int i = this.mMarketVariation;
        if (i == 0) {
            return Uri.parse("market://details?id=" + this.mPackageId);
        }
        if (i == 1) {
            return Uri.parse("amzn://apps/android?p=" + this.mPackageId);
        }
        if (i == 2) {
            return Uri.parse("samsungapps://ProductDetail/" + this.mPackageId);
        }
        if (i == 8) {
            return Uri.parse("amzn://apps/android?p=" + this.mPackageId);
        }
        return Uri.parse("market://details?id=" + this.mPackageId);
    }

    public void showDialog() {
        try {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mActivity).create();
            alertDialogCreate.setTitle("Rate " + this.mAppName + "?");
            alertDialogCreate.setMessage("Would you like to rate " + this.mAppName + " and possibly give some feedback?\n\nBy giving us constructive feedback you help us create the best possible experience for our users.");
            alertDialogCreate.setButton(-1, "Yes", this);
            alertDialogCreate.setButton(-3, "No", this);
            alertDialogCreate.setButton(-2, "Never", this);
            alertDialogCreate.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i != -3) {
            if (i == -2) {
                LiveopsManager.setPrefValueInt(this.mActivity.getApplicationContext(), "APP_RATED", 1);
            } else {
                if (i != -1) {
                    return;
                }
                this.mActivity.startActivity(new Intent("android.intent.action.VIEW", getRatingUri()));
                LiveopsManager.setPrefValueInt(this.mActivity.getApplicationContext(), "APP_RATED", 1);
            }
        }
    }
}
