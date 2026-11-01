package com.appsflyer.share;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.google.android.gms.drive.DriveFile;

/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private String f255;

    c() {
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    final void m209(String str) {
        this.f255 = str;
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    final void m208(Context context) {
        String str = this.f255;
        if (str != null) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)).setFlags(DriveFile.MODE_READ_ONLY));
        }
    }
}
