package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzcde implements zzdcg<zzcvj> {
    private final /* synthetic */ zzccz zzfuf;

    zzcde(zzccz zzcczVar) {
        this.zzfuf = zzcczVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final void zzb(Throwable th) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue()) {
            Matcher matcher = zzccz.zzfuc.matcher(th.getMessage());
            if (matcher.matches()) {
                this.zzfuf.zzfub.zzdi(Integer.parseInt(matcher.group(1)));
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdcg
    public final /* synthetic */ void onSuccess(zzcvj zzcvjVar) {
        zzcvj zzcvjVar2 = zzcvjVar;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcst)).booleanValue()) {
            this.zzfuf.zzfub.zzdi(zzcvjVar2.zzgjp.zzgjm.responseCode);
            this.zzfuf.zzfub.zzei(zzcvjVar2.zzgjp.zzgjm.zzfvh);
        }
    }
}
