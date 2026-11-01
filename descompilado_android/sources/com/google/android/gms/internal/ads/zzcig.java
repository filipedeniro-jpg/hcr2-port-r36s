package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzcig<AdT> implements zzcfk<AdT> {
    protected abstract zzdcp<AdT> zza(zzcvk zzcvkVar, Bundle bundle);

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return !TextUtils.isEmpty(zzcvbVar.zzgiz.optString("pubid", ""));
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<AdT> zzb(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        String strOptString = zzcvbVar.zzgiz.optString("pubid", "");
        zzcvk zzcvkVar = zzcvjVar.zzgjo.zzffn;
        zzcvm zzcvmVarZzgf = new zzcvm().zzg(zzcvkVar.zzgjs).zzd(zzcvkVar.zzbli).zzc(zzcvkVar.zzgjq).zzgf(zzcvkVar.zzgjt).zzc(zzcvkVar.zzgjr).zzb(zzcvkVar.zzgju).zzc(zzcvkVar.zzgjv).zzb(zzcvkVar.zzddy).zzb(zzcvkVar.zzgjw).zzb(zzcvkVar.zzgjx).zzgf(strOptString);
        Bundle bundleZzm = zzm(zzcvkVar.zzgjs.zzcbu);
        Bundle bundleZzm2 = zzm(bundleZzm.getBundle("com.google.ads.mediation.admob.AdMobAdapter"));
        bundleZzm2.putInt("gw", 1);
        String strOptString2 = zzcvbVar.zzgiz.optString("mad_hac", null);
        if (strOptString2 != null) {
            bundleZzm2.putString("mad_hac", strOptString2);
        }
        String strOptString3 = zzcvbVar.zzgiz.optString("adJson", null);
        if (strOptString3 != null) {
            bundleZzm2.putString("_ad", strOptString3);
        }
        bundleZzm2.putBoolean("_noRefresh", true);
        Iterator<String> itKeys = zzcvbVar.zzgjc.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString4 = zzcvbVar.zzgjc.optString(next, null);
            if (next != null) {
                bundleZzm2.putString(next, strOptString4);
            }
        }
        bundleZzm.putBundle("com.google.ads.mediation.admob.AdMobAdapter", bundleZzm2);
        zzcvk zzcvkVarZzamz = zzcvmVarZzgf.zzg(new zztp(zzcvkVar.zzgjs.versionCode, zzcvkVar.zzgjs.zzcbn, bundleZzm2, zzcvkVar.zzgjs.zzcbo, zzcvkVar.zzgjs.zzcbp, zzcvkVar.zzgjs.zzcbq, zzcvkVar.zzgjs.zzabj, zzcvkVar.zzgjs.zzbke, zzcvkVar.zzgjs.zzcbr, zzcvkVar.zzgjs.zzcbs, zzcvkVar.zzgjs.zzng, zzcvkVar.zzgjs.zzcbt, bundleZzm, zzcvkVar.zzgjs.zzcbv, zzcvkVar.zzgjs.zzcbw, zzcvkVar.zzgjs.zzcbx, zzcvkVar.zzgjs.zzcby, zzcvkVar.zzgjs.zzcbz, zzcvkVar.zzgjs.zzcca, zzcvkVar.zzgjs.zzabk, zzcvkVar.zzgjs.zzabl)).zzamz();
        Bundle bundle = new Bundle();
        zzcvd zzcvdVar = zzcvjVar.zzgjp.zzgjm;
        Bundle bundle2 = new Bundle();
        bundle2.putStringArrayList("nofill_urls", new ArrayList<>(zzcvdVar.zzdcl));
        bundle2.putInt("refresh_interval", zzcvdVar.zzgji);
        bundle2.putString("gws_query_id", zzcvdVar.zzbzx);
        bundle.putBundle("parent_common_config", bundle2);
        String str = zzcvjVar.zzgjo.zzffn.zzgjt;
        Bundle bundle3 = new Bundle();
        bundle3.putString("initial_ad_unit_id", str);
        bundle3.putString("allocation_id", zzcvbVar.zzdbn);
        bundle3.putStringArrayList("click_urls", new ArrayList<>(zzcvbVar.zzdci));
        bundle3.putStringArrayList("imp_urls", new ArrayList<>(zzcvbVar.zzdcj));
        bundle3.putStringArrayList("manual_tracking_urls", new ArrayList<>(zzcvbVar.zzdkg));
        bundle3.putStringArrayList("fill_urls", new ArrayList<>(zzcvbVar.zzgiu));
        bundle3.putStringArrayList("video_start_urls", new ArrayList<>(zzcvbVar.zzdkt));
        bundle3.putStringArrayList("video_reward_urls", new ArrayList<>(zzcvbVar.zzdku));
        bundle3.putStringArrayList("video_complete_urls", new ArrayList<>(zzcvbVar.zzgit));
        bundle3.putString("transaction_id", zzcvbVar.zzdby);
        bundle3.putString("valid_from_timestamp", zzcvbVar.zzdbz);
        bundle3.putBoolean("is_closable_area_disabled", zzcvbVar.zzblg);
        if (zzcvbVar.zzdks != null) {
            Bundle bundle4 = new Bundle();
            bundle4.putInt("rb_amount", zzcvbVar.zzdks.zzdng);
            bundle4.putString("rb_type", zzcvbVar.zzdks.type);
            bundle3.putParcelableArray("rewards", new Bundle[]{bundle4});
        }
        bundle.putBundle("parent_ad_config", bundle3);
        return zza(zzcvkVarZzamz, bundle);
    }

    private static Bundle zzm(Bundle bundle) {
        return bundle == null ? new Bundle() : new Bundle(bundle);
    }
}
