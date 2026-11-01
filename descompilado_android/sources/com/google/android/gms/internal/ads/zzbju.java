package com.google.android.gms.internal.ads;

import org.json.JSONException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class zzbju {
    protected final zzcvj zzfaq;
    protected final zzcvb zzfef;
    private final zzbmv zzfet;
    private final zzbnl zzfeu;
    private final String zzfev;

    protected zzbju(zzbjt zzbjtVar) {
        this.zzfaq = zzbjtVar.zzfaq;
        this.zzfef = zzbjtVar.zzfef;
        this.zzfet = zzbjtVar.zzfet;
        this.zzfeu = zzbjtVar.zzfeu;
        this.zzfev = zzbjtVar.zzfev;
    }

    public final zzbmv zzafi() {
        return this.zzfet;
    }

    public void zzaew() {
        this.zzfeu.onAdLoaded();
    }

    public final String getMediationAdapterClassName() {
        return this.zzfev;
    }

    public final String zzjp() {
        String strZzb = "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(this.zzfev) || "com.google.ads.mediation.customevent.CustomEventAdapter".equals(this.zzfev) ? zzb(this.zzfef) : null;
        return strZzb == null ? this.zzfev : strZzb;
    }

    public void destroy() {
        this.zzfet.zzbv(null);
    }

    private static String zzb(zzcvb zzcvbVar) {
        try {
            return zzcvbVar.zzgiz.getString("class_name");
        } catch (JSONException unused) {
            return null;
        }
    }
}
