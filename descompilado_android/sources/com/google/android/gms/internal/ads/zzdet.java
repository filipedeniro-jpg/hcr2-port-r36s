package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdet implements zzddj {
    private static final byte[] zzgss = new byte[0];
    private final zzdja zzgst;
    private final zzddj zzgsu;

    public zzdet(zzdja zzdjaVar, zzddj zzddjVar) {
        this.zzgst = zzdjaVar;
        this.zzgsu = zzddjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzddj
    public final byte[] zzc(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] byteArray = zzdef.zzb(this.zzgst).toByteArray();
        byte[] bArrZzc = this.zzgsu.zzc(byteArray, zzgss);
        byte[] bArrZzc2 = ((zzddj) zzdef.zza(this.zzgst.zzatp(), byteArray, zzddj.class)).zzc(bArr, bArr2);
        return ByteBuffer.allocate(bArrZzc.length + 4 + bArrZzc2.length).putInt(bArrZzc.length).put(bArrZzc).put(bArrZzc2).array();
    }
}
