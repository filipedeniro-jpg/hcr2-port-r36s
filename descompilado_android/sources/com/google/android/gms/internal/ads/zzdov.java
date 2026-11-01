package com.google.android.gms.internal.ads;

import java.util.Comparator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdov implements Comparator<zzdot> {
    zzdov() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzdot zzdotVar, zzdot zzdotVar2) {
        zzdot zzdotVar3 = zzdotVar;
        zzdot zzdotVar4 = zzdotVar2;
        zzdpc zzdpcVar = (zzdpc) zzdotVar3.iterator();
        zzdpc zzdpcVar2 = (zzdpc) zzdotVar4.iterator();
        while (zzdpcVar.hasNext() && zzdpcVar2.hasNext()) {
            int iCompare = Integer.compare(zzdot.zzb(zzdpcVar.nextByte()), zzdot.zzb(zzdpcVar2.nextByte()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzdotVar3.size(), zzdotVar4.size());
    }
}
