package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzbz implements zzdqh {
    ENUM_FALSE(0),
    ENUM_TRUE(1),
    ENUM_FAILURE(2),
    ENUM_UNKNOWN(1000);

    private static final zzdqg<zzbz> zzeg = new zzdqg<zzbz>() { // from class: com.google.android.gms.internal.ads.zzby
    };
    private final int value;

    @Override // com.google.android.gms.internal.ads.zzdqh
    public final int zzab() {
        return this.value;
    }

    public static zzbz zzj(int i) {
        if (i == 0) {
            return ENUM_FALSE;
        }
        if (i == 1) {
            return ENUM_TRUE;
        }
        if (i == 2) {
            return ENUM_FAILURE;
        }
        if (i != 1000) {
            return null;
        }
        return ENUM_UNKNOWN;
    }

    public static zzdqj zzac() {
        return zzcb.zzep;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + '>';
    }

    zzbz(int i) {
        this.value = i;
    }
}
