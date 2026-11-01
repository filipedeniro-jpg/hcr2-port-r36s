package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbue extends zzbub {
    private final boolean zzdcv;
    private final boolean zzdcw;
    private final boolean zzdlc;
    private final JSONObject zzflj;
    private final boolean zzflk;

    public zzbue(zzcvb zzcvbVar, JSONObject jSONObject) {
        super(zzcvbVar);
        boolean z = false;
        this.zzflj = zzavm.zza(jSONObject, "tracking_urls_and_actions", "active_view");
        this.zzdcw = zzavm.zza(false, jSONObject, "allow_pub_owned_ad_view");
        this.zzdcv = zzavm.zza(false, jSONObject, "attribution", "allow_pub_rendering");
        this.zzdlc = zzavm.zza(false, jSONObject, "enable_omid");
        if (jSONObject != null && jSONObject.optJSONObject("overlay") != null) {
            z = true;
        }
        this.zzflk = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbub
    public final JSONObject zzahw() {
        JSONObject jSONObject = this.zzflj;
        if (jSONObject != null) {
            return jSONObject;
        }
        try {
            return new JSONObject(this.zzfli.zzdkm);
        } catch (JSONException unused) {
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbub
    public final boolean zzahx() {
        return this.zzflk;
    }

    @Override // com.google.android.gms.internal.ads.zzbub
    public final boolean zzahy() {
        return this.zzdcw;
    }

    @Override // com.google.android.gms.internal.ads.zzbub
    public final boolean zzahh() {
        return this.zzdlc;
    }

    @Override // com.google.android.gms.internal.ads.zzbub
    public final boolean zzahz() {
        return this.zzdcv;
    }
}
