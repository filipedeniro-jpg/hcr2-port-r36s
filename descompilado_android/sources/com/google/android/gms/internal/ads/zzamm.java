package com.google.android.gms.internal.ads;

import android.content.DialogInterface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzamm implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzamk zzdfu;

    zzamm(zzamk zzamkVar) {
        this.zzdfu = zzamkVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdfu.zzdn("User canceled the download.");
    }
}
