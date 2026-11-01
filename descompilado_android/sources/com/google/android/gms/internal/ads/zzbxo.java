package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbxo implements zzadx<Object> {
    private final zzdvc<zzbxh> zzfki;
    private final zzbxn zzfms;

    @Nullable
    private final zzaby zzfpd;

    public zzbxo(zzbui zzbuiVar, zzbtx zzbtxVar, zzbxn zzbxnVar, zzdvc<zzbxh> zzdvcVar) {
        this.zzfpd = zzbuiVar.zzfv(zzbtxVar.getCustomTemplateId());
        this.zzfms = zzbxnVar;
        this.zzfki = zzdvcVar;
    }

    public final void zzajc() {
        if (this.zzfpd == null) {
            return;
        }
        this.zzfms.zza("/nativeAdCustomClick", this);
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        String str = map.get("asset");
        try {
            this.zzfpd.zza(this.zzfki.get(), str);
        } catch (RemoteException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40);
            sb.append("Failed to call onCustomClick for asset ");
            sb.append(str);
            sb.append(".");
            zzatm.zzd(sb.toString(), e);
        }
    }
}
