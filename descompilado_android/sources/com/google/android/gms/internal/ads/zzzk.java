package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzzk extends zzzl {
    private final com.google.android.gms.ads.internal.zzg zzcuj;

    @Nullable
    private final String zzcuk;
    private final String zzcul;

    public zzzk(com.google.android.gms.ads.internal.zzg zzgVar, @Nullable String str, String str2) {
        this.zzcuj = zzgVar;
        this.zzcuk = str;
        this.zzcul = str2;
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final String zzpz() {
        return this.zzcuk;
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final String getContent() {
        return this.zzcul;
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final void zzr(@Nullable IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper == null) {
            return;
        }
        this.zzcuj.zzg((View) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final void recordClick() {
        this.zzcuj.zzjk();
    }

    @Override // com.google.android.gms.internal.ads.zzzm
    public final void recordImpression() {
        this.zzcuj.zzjl();
    }
}
