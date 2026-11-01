package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzqc implements ValueCallback<String> {
    private final /* synthetic */ zzqd zzbpu;

    zzqc(zzqd zzqdVar) {
        this.zzbpu = zzqdVar;
    }

    @Override // android.webkit.ValueCallback
    public final /* synthetic */ void onReceiveValue(String str) {
        this.zzbpu.zzbph.zza(this.zzbpu.zzbpw, this.zzbpu.zzbpx, str, this.zzbpu.zzbpy);
    }
}
