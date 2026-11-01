package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzbv implements zzdqh {
    UNKNOWN_ENCRYPTION_METHOD(0),
    BITSLICER(1),
    TINK_HYBRID(2),
    UNENCRYPTED(3);

    private static final zzdqg<zzbv> zzeg = new zzdqg<zzbv>() { // from class: com.google.android.gms.internal.ads.zzbx
    };
    private final int value;

    @Override // com.google.android.gms.internal.ads.zzdqh
    public final int zzab() {
        return this.value;
    }

    public static zzbv zzi(int i) {
        if (i == 0) {
            return UNKNOWN_ENCRYPTION_METHOD;
        }
        if (i == 1) {
            return BITSLICER;
        }
        if (i == 2) {
            return TINK_HYBRID;
        }
        if (i != 3) {
            return null;
        }
        return UNENCRYPTED;
    }

    public static zzdqj zzac() {
        return zzbw.zzep;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
    }

    zzbv(int i) {
        this.value = i;
    }
}
