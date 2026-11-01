package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzcrh implements zzcrb<Bundle> {
    private final boolean zzdmp;
    private final boolean zzdmq;
    private final String zzdmr;
    private final boolean zzdms;
    private final boolean zzdmt;
    private final boolean zzdmu;
    private final String zzdmv;
    private final String zzdmw;
    private final String zzdmx;
    private final boolean zzdmy;
    private final ArrayList<String> zzgfy;
    private final String zzgfz;
    private final String zzgga;
    private final long zzggb;

    public zzcrh(boolean z, boolean z2, String str, boolean z3, boolean z4, boolean z5, String str2, ArrayList<String> arrayList, @Nullable String str3, @Nullable String str4, String str5, boolean z6, String str6, long j) {
        this.zzdmp = z;
        this.zzdmq = z2;
        this.zzdmr = str;
        this.zzdms = z3;
        this.zzdmt = z4;
        this.zzdmu = z5;
        this.zzdmv = str2;
        this.zzgfy = arrayList;
        this.zzdmw = str3;
        this.zzdmx = str4;
        this.zzgfz = str5;
        this.zzdmy = z6;
        this.zzgga = str6;
        this.zzggb = j;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        bundle2.putBoolean("cog", this.zzdmp);
        bundle2.putBoolean("coh", this.zzdmq);
        bundle2.putString("gl", this.zzdmr);
        bundle2.putBoolean("simulator", this.zzdms);
        bundle2.putBoolean("is_latchsky", this.zzdmt);
        bundle2.putBoolean("is_sidewinder", this.zzdmu);
        bundle2.putString("hl", this.zzdmv);
        if (!this.zzgfy.isEmpty()) {
            bundle2.putStringArrayList("hl_list", this.zzgfy);
        }
        bundle2.putString("mv", this.zzdmw);
        bundle2.putString("submodel", this.zzgga);
        Bundle bundleZza = zzcvq.zza(bundle2, "device");
        bundle2.putBundle("device", bundleZza);
        bundleZza.putString("build", this.zzgfz);
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcnl)).booleanValue()) {
            bundleZza.putLong("remaining_data_partition_space", this.zzggb);
        }
        Bundle bundleZza2 = zzcvq.zza(bundleZza, "browser");
        bundleZza.putBundle("browser", bundleZza2);
        bundleZza2.putBoolean("is_browser_custom_tabs_capable", this.zzdmy);
        if (TextUtils.isEmpty(this.zzdmx)) {
            return;
        }
        Bundle bundleZza3 = zzcvq.zza(bundleZza, "play_store");
        bundleZza.putBundle("play_store", bundleZza3);
        bundleZza3.putString("package_version", this.zzdmx);
    }
}
