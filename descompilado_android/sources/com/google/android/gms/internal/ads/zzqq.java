package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqq implements Comparator<zzqe> {
    public zzqq(zzqr zzqrVar) {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzqe zzqeVar, zzqe zzqeVar2) {
        zzqe zzqeVar3 = zzqeVar;
        zzqe zzqeVar4 = zzqeVar2;
        if (zzqeVar3.zzlx() < zzqeVar4.zzlx()) {
            return -1;
        }
        if (zzqeVar3.zzlx() > zzqeVar4.zzlx()) {
            return 1;
        }
        if (zzqeVar3.zzlw() < zzqeVar4.zzlw()) {
            return -1;
        }
        if (zzqeVar3.zzlw() > zzqeVar4.zzlw()) {
            return 1;
        }
        float fZzlz = (zzqeVar3.zzlz() - zzqeVar3.zzlx()) * (zzqeVar3.zzly() - zzqeVar3.zzlw());
        float fZzlz2 = (zzqeVar4.zzlz() - zzqeVar4.zzlx()) * (zzqeVar4.zzly() - zzqeVar4.zzlw());
        if (fZzlz > fZzlz2) {
            return -1;
        }
        return fZzlz < fZzlz2 ? 1 : 0;
    }
}
