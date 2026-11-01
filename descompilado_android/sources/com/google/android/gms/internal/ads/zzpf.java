package com.google.android.gms.internal.ads;

import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzpf {
    private final String zzbne;
    private final JSONObject zzbnf;
    private final String zzbng;
    private final String zzbnh;
    private final boolean zzbni = false;
    private final boolean zzbnj;

    public zzpf(String str, zzawv zzawvVar, String str2, JSONObject jSONObject, boolean z, boolean z2) {
        this.zzbnh = zzawvVar.zzbnh;
        this.zzbnf = jSONObject;
        this.zzbng = str;
        this.zzbne = str2;
        this.zzbnj = z2;
    }

    public final String zzla() {
        return this.zzbne;
    }

    public final String zzlb() {
        return this.zzbnh;
    }

    public final JSONObject zzlc() {
        return this.zzbnf;
    }

    public final String zzld() {
        return this.zzbng;
    }

    public final boolean zzle() {
        return this.zzbnj;
    }
}
