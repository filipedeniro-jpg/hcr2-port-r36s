package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzah extends zzai {
    private final char zza;

    zzah(char c) {
        this.zza = c;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzaf
    public final boolean zza(char c) {
        return c == this.zza;
    }

    public final String toString() {
        String strZzc = zzaf.zzc(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(strZzc).length() + 18);
        sb.append("CharMatcher.is('");
        sb.append(strZzc);
        sb.append("')");
        return sb.toString();
    }
}
