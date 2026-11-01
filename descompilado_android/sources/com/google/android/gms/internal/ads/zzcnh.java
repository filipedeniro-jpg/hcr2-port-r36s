package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnh implements zzcrb<Bundle> {
    private final String zzabd;
    private final zztw zzbli;
    private final float zzbod;
    private final boolean zzccg;
    private final int zzdgc;
    private final int zzdgd;
    private final String zzgdp;
    private final String zzgdq;

    public zzcnh(zztw zztwVar, String str, boolean z, String str2, float f, int i, int i2, String str3) {
        Preconditions.checkNotNull(zztwVar, "the adSize must not be null");
        this.zzbli = zztwVar;
        this.zzabd = str;
        this.zzccg = z;
        this.zzgdp = str2;
        this.zzbod = f;
        this.zzdgc = i;
        this.zzdgd = i2;
        this.zzgdq = str3;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        zzcvq.zza(bundle2, "smart_w", MessengerShareContentUtility.WEBVIEW_RATIO_FULL, this.zzbli.width == -1);
        zzcvq.zza(bundle2, "smart_h", "auto", this.zzbli.height == -2);
        zzcvq.zza(bundle2, "ene", (Boolean) true, this.zzbli.zzcch);
        zzcvq.zza(bundle2, "rafmt", "102", this.zzbli.zzcck);
        zzcvq.zza(bundle2, "format", this.zzabd);
        zzcvq.zza(bundle2, "fluid", "height", this.zzccg);
        String str = this.zzgdp;
        zzcvq.zza(bundle2, "sz", str, !TextUtils.isEmpty(str));
        bundle2.putFloat("u_sd", this.zzbod);
        bundle2.putInt("sw", this.zzdgc);
        bundle2.putInt("sh", this.zzdgd);
        String str2 = this.zzgdq;
        zzcvq.zza(bundle2, "sc", str2, true ^ TextUtils.isEmpty(str2));
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (this.zzbli.zzccf == null) {
            Bundle bundle3 = new Bundle();
            bundle3.putInt("height", this.zzbli.height);
            bundle3.putInt("width", this.zzbli.width);
            bundle3.putBoolean("is_fluid_height", this.zzbli.zzccg);
            arrayList.add(bundle3);
        } else {
            for (zztw zztwVar : this.zzbli.zzccf) {
                Bundle bundle4 = new Bundle();
                bundle4.putBoolean("is_fluid_height", zztwVar.zzccg);
                bundle4.putInt("height", zztwVar.height);
                bundle4.putInt("width", zztwVar.width);
                arrayList.add(bundle4);
            }
        }
        bundle2.putParcelableArrayList("valid_ad_sizes", arrayList);
    }
}
