package com.google.android.gms.internal.firebase_auth;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzau extends zzae<String> {
    final CharSequence zza;
    private final zzaf zzb;
    private int zze;
    private int zzd = 0;
    private final boolean zzc = false;

    protected zzau(zzan zzanVar, CharSequence charSequence) {
        this.zzb = zzanVar.zza;
        this.zze = zzanVar.zzd;
        this.zza = charSequence;
    }

    abstract int zza(int i);

    abstract int zzb(int i);

    @Override // com.google.android.gms.internal.firebase_auth.zzae
    protected final /* synthetic */ String zza() {
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 != -1) {
                int iZza = zza(i2);
                if (iZza == -1) {
                    iZza = this.zza.length();
                    this.zzd = -1;
                } else {
                    this.zzd = zzb(iZza);
                }
                int i3 = this.zzd;
                if (i3 == i) {
                    this.zzd = i3 + 1;
                    if (this.zzd > this.zza.length()) {
                        this.zzd = -1;
                    }
                } else {
                    while (i < iZza && this.zzb.zza(this.zza.charAt(i))) {
                        i++;
                    }
                    while (iZza > i && this.zzb.zza(this.zza.charAt(iZza - 1))) {
                        iZza--;
                    }
                    if (this.zzc && i == iZza) {
                        i = this.zzd;
                    } else {
                        int i4 = this.zze;
                        if (i4 == 1) {
                            iZza = this.zza.length();
                            this.zzd = -1;
                            while (iZza > i && this.zzb.zza(this.zza.charAt(iZza - 1))) {
                                iZza--;
                            }
                        } else {
                            this.zze = i4 - 1;
                        }
                        return this.zza.subSequence(i, iZza).toString();
                    }
                }
            } else {
                zzb();
                return null;
            }
        }
    }
}
