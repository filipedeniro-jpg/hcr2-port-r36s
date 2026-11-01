package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdmj {
    private final zzdnn zzhbv;
    private final zzdnn zzhbw;

    public zzdmj(byte[] bArr, byte[] bArr2) {
        this.zzhbv = zzdnn.zzu(bArr);
        this.zzhbw = zzdnn.zzu(bArr2);
    }

    public final byte[] zzawr() {
        zzdnn zzdnnVar = this.zzhbv;
        if (zzdnnVar == null) {
            return null;
        }
        return zzdnnVar.getBytes();
    }

    public final byte[] zzaws() {
        zzdnn zzdnnVar = this.zzhbw;
        if (zzdnnVar == null) {
            return null;
        }
        return zzdnnVar.getBytes();
    }
}
