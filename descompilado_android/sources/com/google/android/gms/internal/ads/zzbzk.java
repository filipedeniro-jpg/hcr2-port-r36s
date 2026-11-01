package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbzk implements zzbms {

    @Nullable
    private final zzbbc zzcyx;

    zzbzk(@Nullable zzbbc zzbbcVar) {
        this.zzcyx = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcka)).booleanValue() ? zzbbcVar : null;
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbt(@Nullable Context context) {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            zzbbcVar.onPause();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbu(@Nullable Context context) {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            zzbbcVar.onResume();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final void zzbv(@Nullable Context context) {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            zzbbcVar.destroy();
        }
    }
}
