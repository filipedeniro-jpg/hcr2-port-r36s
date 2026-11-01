package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzar extends zzau {
    private final /* synthetic */ zzas zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzar(zzas zzasVar, zzan zzanVar, CharSequence charSequence) {
        super(zzanVar, charSequence);
        this.zzb = zzasVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzau
    public final int zza(int i) {
        int length = this.zzb.zza.length();
        int length2 = this.zza.length() - length;
        while (i <= length2) {
            for (int i2 = 0; i2 < length; i2++) {
                if (this.zza.charAt(i2 + i) != this.zzb.zza.charAt(i2)) {
                    i++;
                }
            }
            return i;
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzau
    public final int zzb(int i) {
        return i + this.zzb.zza.length();
    }
}
