package com.google.android.gms.internal.ads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzauv implements Runnable {
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzdth;
    private final /* synthetic */ boolean zzdti;
    private final /* synthetic */ boolean zzdtj;

    zzauv(zzaus zzausVar, Context context, String str, boolean z, boolean z2) {
        this.val$context = context;
        this.zzdth = str;
        this.zzdti = z;
        this.zzdtj = z2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.val$context);
        builder.setMessage(this.zzdth);
        if (this.zzdti) {
            builder.setTitle("Error");
        } else {
            builder.setTitle("Info");
        }
        if (this.zzdtj) {
            builder.setNeutralButton("Dismiss", (DialogInterface.OnClickListener) null);
        } else {
            builder.setPositiveButton("Learn More", new zzauu(this));
            builder.setNegativeButton("Dismiss", (DialogInterface.OnClickListener) null);
        }
        builder.create().show();
    }
}
