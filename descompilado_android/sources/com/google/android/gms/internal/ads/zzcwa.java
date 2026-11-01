package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import java.util.HashSet;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcwa implements zzath, zzbml {
    private final zzatj zzdpp;

    @GuardedBy("this")
    private final HashSet<zzasw> zzgki = new HashSet<>();
    private final Context zzlk;

    public zzcwa(Context context, zzatj zzatjVar) {
        this.zzlk = context;
        this.zzdpp = zzatjVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbml
    public final synchronized void onAdFailedToLoad(int i) {
        if (i != 3) {
            this.zzdpp.zzb(this.zzgki);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzath
    public final synchronized void zza(HashSet<zzasw> hashSet) {
        this.zzgki.clear();
        this.zzgki.addAll(hashSet);
    }

    public final Bundle zzanc() {
        return this.zzdpp.zza(this.zzlk, this);
    }
}
