package com.google.android.gms.internal.drive;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
final class zzje implements Comparator<zzjc> {
    zzje() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzjc zzjcVar, zzjc zzjcVar2) {
        zzjc zzjcVar3 = zzjcVar;
        zzjc zzjcVar4 = zzjcVar2;
        zzjj zzjjVar = (zzjj) zzjcVar3.iterator();
        zzjj zzjjVar2 = (zzjj) zzjcVar4.iterator();
        while (zzjjVar.hasNext() && zzjjVar2.hasNext()) {
            int iCompare = Integer.compare(zzjc.zza(zzjjVar.nextByte()), zzjc.zza(zzjjVar2.nextByte()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzjcVar3.size(), zzjcVar4.size());
    }
}
