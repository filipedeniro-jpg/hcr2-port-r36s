package com.google.firebase.auth.api.internal;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.firebase_auth.zzfl;
import com.google.android.gms.internal.firebase_auth.zzfo;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
final class zzi implements zzfe<zzfo> {
    private final /* synthetic */ zzfl zza;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzes zzb;
    private final /* synthetic */ zzdr zzc;
    private final /* synthetic */ com.google.android.gms.internal.firebase_auth.zzey zzd;
    private final /* synthetic */ zzfb zze;
    private final /* synthetic */ zza zzf;

    zzi(zza zzaVar, zzfl zzflVar, com.google.android.gms.internal.firebase_auth.zzes zzesVar, zzdr zzdrVar, com.google.android.gms.internal.firebase_auth.zzey zzeyVar, zzfb zzfbVar) {
        this.zzf = zzaVar;
        this.zza = zzflVar;
        this.zzb = zzesVar;
        this.zzc = zzdrVar;
        this.zzd = zzeyVar;
        this.zze = zzfbVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzfb
    public final void zza(@Nullable String str) {
        this.zze.zza(str);
    }

    @Override // com.google.firebase.auth.api.internal.zzfe
    public final /* synthetic */ void zza(zzfo zzfoVar) {
        zzfo zzfoVar2 = zzfoVar;
        if (this.zza.zza("EMAIL")) {
            this.zzb.zza((String) null);
        } else if (this.zza.zzb() != null) {
            this.zzb.zza(this.zza.zzb());
        }
        if (this.zza.zza("DISPLAY_NAME")) {
            this.zzb.zzb(null);
        } else if (this.zza.zzd() != null) {
            this.zzb.zzb(this.zza.zzd());
        }
        if (this.zza.zza("PHOTO_URL")) {
            this.zzb.zzc(null);
        } else if (this.zza.zze() != null) {
            this.zzb.zzc(this.zza.zze());
        }
        if (!TextUtils.isEmpty(this.zza.zzc())) {
            this.zzb.zzd(Base64Utils.encode("redacted".getBytes()));
        }
        List<com.google.android.gms.internal.firebase_auth.zzfc> listZzf = zzfoVar2.zzf();
        if (listZzf == null) {
            listZzf = new ArrayList<>();
        }
        this.zzb.zza(listZzf);
        zzdr zzdrVar = this.zzc;
        zza zzaVar = this.zzf;
        zzdrVar.zza(zza.zza(this.zzd, zzfoVar2), this.zzb);
    }
}
