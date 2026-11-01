package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmu implements zzcrb<Bundle> {
    private final zztw zzbli;
    private final List<Parcelable> zzgdf;
    private final Context zzlk;

    public zzcmu(Context context, zztw zztwVar, List<Parcelable> list) {
        this.zzlk = context;
        this.zzbli = zztwVar;
        this.zzgdf = list;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        com.google.android.gms.ads.internal.zzp.zzjy();
        bundle2.putString("activity", zzatv.zzas(this.zzlk));
        Bundle bundle3 = new Bundle();
        bundle3.putInt("width", this.zzbli.width);
        bundle3.putInt("height", this.zzbli.height);
        bundle2.putBundle("size", bundle3);
        if (this.zzgdf.size() > 0) {
            List<Parcelable> list = this.zzgdf;
            bundle2.putParcelableArray("parents", (Parcelable[]) list.toArray(new Parcelable[list.size()]));
        }
    }
}
