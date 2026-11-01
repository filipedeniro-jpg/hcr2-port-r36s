package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(21)
final class zzkw implements zzku {
    private final int zzazk;
    private MediaCodecInfo[] zzazl;

    public zzkw(boolean z) {
        this.zzazk = z ? 1 : 0;
    }

    @Override // com.google.android.gms.internal.ads.zzku
    public final boolean zzgu() {
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzku
    public final int getCodecCount() {
        zzgv();
        return this.zzazl.length;
    }

    @Override // com.google.android.gms.internal.ads.zzku
    public final MediaCodecInfo getCodecInfoAt(int i) {
        zzgv();
        return this.zzazl[i];
    }

    @Override // com.google.android.gms.internal.ads.zzku
    public final boolean zza(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("secure-playback");
    }

    private final void zzgv() {
        if (this.zzazl == null) {
            this.zzazl = new MediaCodecList(this.zzazk).getCodecInfos();
        }
    }
}
