package com.google.android.gms.internal.drive;

/* JADX INFO: loaded from: classes2.dex */
final class zzjw {
    private static final Class<?> zzok = zzce();

    private static Class<?> zzce() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzjx zzcf() {
        if (zzok != null) {
            try {
                return zzn("getEmptyRegistry");
            } catch (Exception unused) {
            }
        }
        return zzjx.zzoo;
    }

    static zzjx zzcg() {
        zzjx zzjxVarZzn;
        if (zzok != null) {
            try {
                zzjxVarZzn = zzn("loadGeneratedRegistry");
            } catch (Exception unused) {
                zzjxVarZzn = null;
            }
        } else {
            zzjxVarZzn = null;
        }
        if (zzjxVarZzn == null) {
            zzjxVarZzn = zzjx.zzcg();
        }
        return zzjxVarZzn == null ? zzcf() : zzjxVarZzn;
    }

    private static final zzjx zzn(String str) throws Exception {
        return (zzjx) zzok.getDeclaredMethod(str, new Class[0]).invoke(null, new Object[0]);
    }
}
