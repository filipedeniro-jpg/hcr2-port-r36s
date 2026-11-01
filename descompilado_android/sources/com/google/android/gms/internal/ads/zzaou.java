package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzaou implements Callable<zzaot> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaov zzdmz;

    zzaou(zzaov zzaovVar, Context context) {
        this.zzdmz = zzaovVar;
        this.val$context = context;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0042  */
    @Override // java.util.concurrent.Callable
    public final /* synthetic */ zzaot call() throws Exception {
        zzaot zzaotVarZzte;
        zzaox zzaoxVar = (zzaox) this.zzdmz.zzdna.get(this.val$context);
        if (zzaoxVar != null) {
            if (!(zzaoxVar.zzdnd + ((Long) zzuo.zzoj().zzd(zzyt.zzclv)).longValue() < com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis())) {
                zzaotVarZzte = new zzaos(this.val$context, zzaoxVar.zzdne).zzte();
            } else {
                zzaotVarZzte = new zzaos(this.val$context).zzte();
            }
        } else {
            zzaotVarZzte = new zzaos(this.val$context).zzte();
        }
        this.zzdmz.zzdna.put(this.val$context, new zzaox(this.zzdmz, zzaotVarZzte));
        return zzaotVarZzte;
    }
}
