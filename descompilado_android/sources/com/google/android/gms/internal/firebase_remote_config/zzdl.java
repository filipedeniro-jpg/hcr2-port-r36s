package com.google.android.gms.internal.firebase_remote_config;

/* JADX INFO: loaded from: classes2.dex */
final class zzdl extends zzdm {
    private final char zzhd;

    zzdl(char c) {
        this.zzhd = c;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzdj
    public final boolean zzb(char c) {
        return c == this.zzhd;
    }

    public final String toString() {
        String strZzc = zzdj.zzc(this.zzhd);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(strZzc);
        sb.append("')");
        return sb.toString();
    }
}
