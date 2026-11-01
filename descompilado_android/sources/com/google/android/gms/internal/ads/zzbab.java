package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzbab extends zzay {
    static final zzbab zzecp = new zzbab();

    zzbab() {
    }

    @Override // com.google.android.gms.internal.ads.zzay
    public final zzbb zza(String str, byte[] bArr, String str2) {
        if ("moov".equals(str)) {
            return new zzbd();
        }
        return "mvhd".equals(str) ? new zzbg() : new zzbf(str);
    }
}
