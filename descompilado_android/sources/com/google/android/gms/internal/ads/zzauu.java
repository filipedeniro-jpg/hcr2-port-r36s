package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.net.Uri;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzauu implements DialogInterface.OnClickListener {
    private final /* synthetic */ zzauv zzdtg;

    zzauu(zzauv zzauvVar) {
        this.zzdtg = zzauvVar;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzatv.zza(this.zzdtg.val$context, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
