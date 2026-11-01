package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.ironsource.sdk.constants.LocationConst;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmy implements zzcrb<Bundle> {
    private final zzcvk zzffn;

    public zzcmy(zzcvk zzcvkVar) {
        Preconditions.checkNotNull(zzcvkVar, "the targeting must not be null");
        this.zzffn = zzcvkVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        zztp zztpVar = this.zzffn.zzgjs;
        bundle2.putString("slotname", this.zzffn.zzgjt);
        if (this.zzffn.zzgjz.contains("new_rewarded")) {
            bundle2.putBoolean("is_new_rewarded", true);
        }
        zzcvq.zza(bundle2, "cust_age", new SimpleDateFormat("yyyyMMdd", Locale.US).format(new Date(zztpVar.zzcbn)), zztpVar.zzcbn != -1);
        zzcvq.zza(bundle2, "extras", zztpVar.extras);
        zzcvq.zza(bundle2, "cust_gender", Integer.valueOf(zztpVar.zzcbo), zztpVar.zzcbo != -1);
        zzcvq.zza(bundle2, "kw", zztpVar.zzcbp);
        zzcvq.zza(bundle2, "tag_for_child_directed_treatment", Integer.valueOf(zztpVar.zzabj), zztpVar.zzabj != -1);
        if (zztpVar.zzcbq) {
            bundle2.putBoolean("test_request", zztpVar.zzcbq);
        }
        zzcvq.zza(bundle2, "d_imp_hdr", (Integer) 1, zztpVar.versionCode >= 2 && zztpVar.zzbke);
        zzcvq.zza(bundle2, "ppid", zztpVar.zzcbr, zztpVar.versionCode >= 2 && !TextUtils.isEmpty(zztpVar.zzcbr));
        if (zztpVar.zzng != null) {
            Location location = zztpVar.zzng;
            Float fValueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
            Long lValueOf = Long.valueOf(location.getTime() * 1000);
            Long lValueOf2 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
            Long lValueOf3 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
            Bundle bundle3 = new Bundle();
            bundle3.putFloat("radius", fValueOf.floatValue());
            bundle3.putLong("lat", lValueOf2.longValue());
            bundle3.putLong("long", lValueOf3.longValue());
            bundle3.putLong(LocationConst.TIME, lValueOf.longValue());
            bundle2.putBundle("uule", bundle3);
        }
        zzcvq.zza(bundle2, "url", zztpVar.zzcbt);
        zzcvq.zza(bundle2, "custom_targeting", zztpVar.zzcbv);
        zzcvq.zza(bundle2, "category_exclusions", zztpVar.zzcbw);
        zzcvq.zza(bundle2, "request_agent", zztpVar.zzcbx);
        zzcvq.zza(bundle2, "request_pkg", zztpVar.zzcby);
        zzcvq.zza(bundle2, "is_designed_for_families", Boolean.valueOf(zztpVar.zzcbz), zztpVar.versionCode >= 7);
        if (zztpVar.versionCode >= 8) {
            zzcvq.zza(bundle2, "tag_for_under_age_of_consent", Integer.valueOf(zztpVar.zzabk), zztpVar.zzabk != -1);
            zzcvq.zza(bundle2, "max_ad_content_rating", zztpVar.zzabl);
        }
    }
}
