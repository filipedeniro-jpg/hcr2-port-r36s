package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzavg implements zzy {
    private final /* synthetic */ String zzdtz;
    private final /* synthetic */ zzavl zzdua;

    zzavg(zzave zzaveVar, String str, zzavl zzavlVar) {
        this.zzdtz = str;
        this.zzdua = zzavlVar;
    }

    @Override // com.google.android.gms.internal.ads.zzy
    public final void zzc(zzae zzaeVar) {
        String str = this.zzdtz;
        String string = zzaeVar.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21 + String.valueOf(string).length());
        sb.append("Failed to load URL: ");
        sb.append(str);
        sb.append("\n");
        sb.append(string);
        zzatm.zzeu(sb.toString());
        this.zzdua.zzb(null);
    }
}
