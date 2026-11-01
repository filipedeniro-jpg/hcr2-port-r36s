package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdlz extends zzdly {
    public zzdlz(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    @Override // com.google.android.gms.internal.ads.zzdly
    final zzdlw zzd(byte[] bArr, int i) throws InvalidKeyException {
        return new zzdlx(bArr, i);
    }

    @Override // com.google.android.gms.internal.ads.zzdly, com.google.android.gms.internal.ads.zzddj
    public final /* bridge */ /* synthetic */ byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.zzc(bArr, bArr2);
    }
}
