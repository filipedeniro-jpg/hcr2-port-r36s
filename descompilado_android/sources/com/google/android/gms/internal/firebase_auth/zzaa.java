package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public enum zzaa implements zzia {
    VERIFY_OP_UNSPECIFIED(0),
    SIGN_UP_OR_IN(1),
    REAUTH(2),
    UPDATE(3),
    LINK(4);

    private static final zzid<zzaa> zzf = new zzid<zzaa>() { // from class: com.google.android.gms.internal.firebase_auth.zzac
    };
    private final int zzg;

    @Override // com.google.android.gms.internal.firebase_auth.zzia
    public final int zza() {
        return this.zzg;
    }

    public static zzaa zza(int i) {
        if (i == 0) {
            return VERIFY_OP_UNSPECIFIED;
        }
        if (i == 1) {
            return SIGN_UP_OR_IN;
        }
        if (i == 2) {
            return REAUTH;
        }
        if (i == 3) {
            return UPDATE;
        }
        if (i != 4) {
            return null;
        }
        return LINK;
    }

    public static zzic zzb() {
        return zzab.zza;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.zzg + " name=" + name() + '>';
    }

    zzaa(int i) {
        this.zzg = i;
    }
}
