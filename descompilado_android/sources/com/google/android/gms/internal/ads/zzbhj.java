package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhj {
    private final View view;
    private final zzbbc zzcyx;
    private final zzcva zzfcp;
    private final int zzfcq;

    public zzbhj(View view, @Nullable zzbbc zzbbcVar, zzcva zzcvaVar, int i) {
        this.view = view;
        this.zzcyx = zzbbcVar;
        this.zzfcp = zzcvaVar;
        this.zzfcq = i;
    }

    @Nullable
    public final zzbbc zzaek() {
        return this.zzcyx;
    }

    public final View zzael() {
        return this.view;
    }

    public final zzcva zzaem() {
        return this.zzfcp;
    }

    public final int zzaen() {
        return this.zzfcq;
    }
}
