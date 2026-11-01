package com.google.android.gms.internal.ads;

import android.os.Bundle;
import io.fabric.sdk.android.services.network.HttpRequest;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqs implements zzcrb<Bundle> {
    private final String zzbnh;
    private final int zzdiz;
    private final boolean zzdjw;
    private final boolean zzdvv;
    private final boolean zzgfo;
    private final int zzgfp;
    private final int zzgfq;

    public zzcqs(boolean z, boolean z2, String str, boolean z3, int i, int i2, int i3) {
        this.zzgfo = z;
        this.zzdvv = z2;
        this.zzbnh = str;
        this.zzdjw = z3;
        this.zzdiz = i;
        this.zzgfp = i2;
        this.zzgfq = i3;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putString("js", this.zzbnh);
        bundle2.putBoolean("is_nonagon", true);
        bundle2.putString("extra_caps", (String) zzuo.zzoj().zzd(zzyt.zzcnn));
        bundle2.putInt("target_api", this.zzdiz);
        bundle2.putInt("dv", this.zzgfp);
        bundle2.putInt("lv", this.zzgfq);
        Bundle bundleZza = zzcvq.zza(bundle2, "sdk_env");
        bundleZza.putBoolean("mf", ((Boolean) zzuo.zzoj().zzd(zzyt.zzcnp)).booleanValue());
        bundleZza.putBoolean("instant_app", this.zzgfo);
        bundleZza.putBoolean("lite", this.zzdvv);
        bundleZza.putBoolean("is_privileged_process", this.zzdjw);
        bundle2.putBundle("sdk_env", bundleZza);
        Bundle bundleZza2 = zzcvq.zza(bundleZza, "build_meta");
        bundleZza2.putString("cl", "258870853");
        bundleZza2.putString("rapid_rc", "dev");
        bundleZza2.putString("rapid_rollup", HttpRequest.METHOD_HEAD);
        bundleZza.putBundle("build_meta", bundleZza2);
    }
}
