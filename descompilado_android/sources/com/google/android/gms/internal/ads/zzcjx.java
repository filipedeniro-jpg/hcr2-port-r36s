package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcjx implements zzbni {
    private final /* synthetic */ zzcfl zzfzl;
    private final /* synthetic */ zzaxf zzfzw;

    zzcjx(zzcjw zzcjwVar, zzaxf zzaxfVar, zzcfl zzcflVar) {
        this.zzfzw = zzaxfVar;
        this.zzfzl = zzcflVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbni
    public final synchronized void onAdFailedToLoad(int i) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcre)).booleanValue()) {
            i = 3;
        }
        zzaxf zzaxfVar = this.zzfzw;
        String str = this.zzfzl.zzfev;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 23);
        sb.append("adapter ");
        sb.append(str);
        sb.append(" failed to load");
        zzaxfVar.setException(new zzcin(sb.toString(), i));
    }

    @Override // com.google.android.gms.internal.ads.zzbni
    public final synchronized void onAdLoaded() {
        this.zzfzw.set(null);
    }
}
