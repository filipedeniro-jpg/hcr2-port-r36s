package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
public final class zzb {
    public static final String VERSION;
    public static final Integer zzb = 1;
    public static final Integer zzc = 26;
    private static final Integer zzd = 0;

    static {
        String strValueOf = String.valueOf(zzb);
        String strValueOf2 = String.valueOf(zzc);
        String strValueOf3 = String.valueOf(zzd);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 11 + String.valueOf(strValueOf2).length() + String.valueOf(strValueOf3).length());
        sb.append(strValueOf);
        sb.append(".");
        sb.append(strValueOf2);
        sb.append(".");
        sb.append(strValueOf3);
        sb.append("-SNAPSHOT");
        VERSION = sb.toString().toString();
    }
}
