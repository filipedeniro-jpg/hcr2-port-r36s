package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbib {
    private final View view;
    private final zzbbc zzcyx;
    private final zzcva zzfcp;
    private final zzbjr zzfdb;

    public zzbib(View view, @Nullable zzbbc zzbbcVar, zzbjr zzbjrVar, zzcva zzcvaVar) {
        this.view = view;
        this.zzcyx = zzbbcVar;
        this.zzfdb = zzbjrVar;
        this.zzfcp = zzcvaVar;
    }

    @Nullable
    public final zzbbc zzaek() {
        return this.zzcyx;
    }

    public final View zzaer() {
        return this.view;
    }

    public final zzbjr zzaey() {
        return this.zzfdb;
    }

    public final zzcva zzaez() {
        return this.zzfcp;
    }

    public zzbnl zza(Set<zzbqc<zzbnm>> set) {
        return new zzbnl(set);
    }
}
