package com.google.android.gms.internal.firebase_auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfl implements com.google.firebase.auth.api.internal.zzfk<zzp.zzm> {
    private String zza;
    private String zzb;
    private String zzc;
    private String zzd;
    private String zze;
    private String zzi;

    @Nullable
    private String zzj;
    private boolean zzh = true;
    private zzfp zzg = new zzfp();
    private zzfp zzf = new zzfp();

    public final boolean zza(String str) {
        Preconditions.checkNotEmpty(str);
        return this.zzg.zza().contains(str);
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }

    @Nullable
    public final String zzc() {
        return this.zzc;
    }

    @Nullable
    public final String zzd() {
        return this.zzd;
    }

    @Nullable
    public final String zze() {
        return this.zze;
    }

    @NonNull
    public final zzfl zzb(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfl zzc(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("EMAIL");
        } else {
            this.zzb = str;
        }
        return this;
    }

    @NonNull
    public final zzfl zzd(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("PASSWORD");
        } else {
            this.zzc = str;
        }
        return this;
    }

    @NonNull
    public final zzfl zze(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("DISPLAY_NAME");
        } else {
            this.zzd = str;
        }
        return this;
    }

    @NonNull
    public final zzfl zzf(@Nullable String str) {
        if (str == null) {
            this.zzg.zza().add("PHOTO_URL");
        } else {
            this.zze = str;
        }
        return this;
    }

    @NonNull
    public final zzfl zzg(String str) {
        Preconditions.checkNotEmpty(str);
        this.zzf.zza().add(str);
        return this;
    }

    @NonNull
    public final zzfl zzh(String str) {
        this.zzi = Preconditions.checkNotEmpty(str);
        return this;
    }

    @NonNull
    public final zzfl zzi(@Nullable String str) {
        this.zzj = str;
        return this;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.firebase.auth.api.internal.zzfk
    public final /* synthetic */ zzjg zza() {
        zzv zzvVar;
        zzp.zzm.zza zzaVarZzb = zzp.zzm.zza().zza(this.zzh).zzb(this.zzf.zza());
        List<String> listZza = this.zzg.zza();
        zzv[] zzvVarArr = new zzv[listZza.size()];
        for (int i = 0; i < listZza.size(); i++) {
            String str = listZza.get(i);
            byte b = -1;
            switch (str.hashCode()) {
                case -333046776:
                    if (str.equals("DISPLAY_NAME")) {
                        b = 1;
                    }
                    break;
                case 66081660:
                    if (str.equals("EMAIL")) {
                        b = 0;
                    }
                    break;
                case 1939891618:
                    if (str.equals("PHOTO_URL")) {
                        b = 3;
                    }
                    break;
                case 1999612571:
                    if (str.equals("PASSWORD")) {
                        b = 2;
                    }
                    break;
            }
            if (b == 0) {
                zzvVar = zzv.EMAIL;
            } else if (b == 1) {
                zzvVar = zzv.DISPLAY_NAME;
            } else if (b == 2) {
                zzvVar = zzv.PASSWORD;
            } else if (b == 3) {
                zzvVar = zzv.PHOTO_URL;
            } else {
                zzvVar = zzv.USER_ATTRIBUTE_NAME_UNSPECIFIED;
            }
            zzvVarArr[i] = zzvVar;
        }
        zzp.zzm.zza zzaVarZza = zzaVarZzb.zza(Arrays.asList(zzvVarArr));
        String str2 = this.zza;
        if (str2 != null) {
            zzaVarZza.zza(str2);
        }
        String str3 = this.zzb;
        if (str3 != null) {
            zzaVarZza.zzc(str3);
        }
        String str4 = this.zzc;
        if (str4 != null) {
            zzaVarZza.zzd(str4);
        }
        String str5 = this.zzd;
        if (str5 != null) {
            zzaVarZza.zzb(str5);
        }
        String str6 = this.zze;
        if (str6 != null) {
            zzaVarZza.zzf(str6);
        }
        String str7 = this.zzi;
        if (str7 != null) {
            zzaVarZza.zze(str7);
        }
        String str8 = this.zzj;
        if (str8 != null) {
            zzaVarZza.zzg(str8);
        }
        return (zzp.zzm) ((zzhx) zzaVarZza.zzf());
    }
}
