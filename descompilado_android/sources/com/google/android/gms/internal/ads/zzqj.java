package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzqj implements Comparator<zzqp> {
    zzqj(zzqg zzqgVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzqp zzqpVar, zzqp zzqpVar2) {
        zzqp zzqpVar3 = zzqpVar;
        zzqp zzqpVar4 = zzqpVar2;
        int i = zzqpVar3.zzbqc - zzqpVar4.zzbqc;
        return i != 0 ? i : (int) (zzqpVar3.value - zzqpVar4.value);
    }
}
