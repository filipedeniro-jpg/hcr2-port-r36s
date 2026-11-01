package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcld extends zzaln {
    private final String zzcyb;
    private final zzalj zzgax;
    private zzaxf<JSONObject> zzgay;
    private final JSONObject zzgaz = new JSONObject();

    @GuardedBy("this")
    private boolean zzgba = false;

    public zzcld(String str, zzalj zzaljVar, zzaxf<JSONObject> zzaxfVar) {
        this.zzgay = zzaxfVar;
        this.zzcyb = str;
        this.zzgax = zzaljVar;
        try {
            this.zzgaz.put("adapter_version", this.zzgax.zzsc().toString());
            this.zzgaz.put("sdk_version", this.zzgax.zzsd().toString());
            this.zzgaz.put("name", this.zzcyb);
        } catch (RemoteException | NullPointerException | JSONException unused) {
        }
    }

    @Override // com.google.android.gms.internal.ads.zzalo
    public final synchronized void zzdi(String str) throws RemoteException {
        if (this.zzgba) {
            return;
        }
        if (str == null) {
            onFailure("Adapter returned null signals");
            return;
        }
        try {
            this.zzgaz.put("signals", str);
        } catch (JSONException unused) {
        }
        this.zzgay.set(this.zzgaz);
        this.zzgba = true;
    }

    @Override // com.google.android.gms.internal.ads.zzalo
    public final synchronized void onFailure(String str) throws RemoteException {
        if (this.zzgba) {
            return;
        }
        try {
            this.zzgaz.put("signal_error", str);
        } catch (JSONException unused) {
        }
        this.zzgay.set(this.zzgaz);
        this.zzgba = true;
    }
}
