package com.google.firebase.auth.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.ActionCodeResult;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzg implements ActionCodeResult {
    private final int zza;
    private final String zzb;
    private final String zzc;
    private final com.google.firebase.auth.zza zzd;

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public zzg(zzff zzffVar) {
        this.zzb = zzffVar.zzg() ? zzffVar.zzc() : zzffVar.zzb();
        this.zzc = zzffVar.zzb();
        com.google.firebase.auth.zza zzeVar = null;
        if (!zzffVar.zzh()) {
            this.zza = 3;
            this.zzd = null;
            return;
        }
        String strZzd = zzffVar.zzd();
        byte b = -1;
        switch (strZzd.hashCode()) {
            case -1874510116:
                if (strZzd.equals("REVERT_SECOND_FACTOR_ADDITION")) {
                    b = 5;
                }
                break;
            case -1452371317:
                if (strZzd.equals("PASSWORD_RESET")) {
                    b = 0;
                }
                break;
            case -1341836234:
                if (strZzd.equals("VERIFY_EMAIL")) {
                    b = 1;
                }
                break;
            case -1288726400:
                if (strZzd.equals("VERIFY_BEFORE_UPDATE_EMAIL")) {
                    b = 3;
                }
                break;
            case 870738373:
                if (strZzd.equals("EMAIL_SIGNIN")) {
                    b = 2;
                }
                break;
            case 970484929:
                if (strZzd.equals("RECOVER_EMAIL")) {
                    b = 4;
                }
                break;
        }
        this.zza = b != 0 ? b != 1 ? b != 2 ? b != 3 ? b != 4 ? b != 5 ? 3 : 6 : 2 : 5 : 4 : 1 : 0;
        int i = this.zza;
        if (i == 4 || i == 3) {
            this.zzd = null;
            return;
        }
        if (zzffVar.zzi()) {
            zzeVar = new zzd(zzffVar.zzb(), zzar.zza(zzffVar.zze()));
        } else if (zzffVar.zzg()) {
            zzeVar = new zzb(zzffVar.zzc(), zzffVar.zzb());
        } else if (zzffVar.zzf()) {
            zzeVar = new zze(zzffVar.zzb());
        }
        this.zzd = zzeVar;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    public final int getOperation() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.ActionCodeResult
    @Nullable
    public final String getData(int i) {
        if (this.zza == 4) {
            return null;
        }
        if (i == 0) {
            return this.zzb;
        }
        if (i != 1) {
            return null;
        }
        return this.zzc;
    }
}
