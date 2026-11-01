package com.google.android.gms.internal.firebase_remote_config;

import java.util.Comparator;

/* JADX INFO: loaded from: classes2.dex */
final class zzfz implements Comparator<zzfx> {
    zzfz() {
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zzfx zzfxVar, zzfx zzfxVar2) {
        zzfx zzfxVar3 = zzfxVar;
        zzfx zzfxVar4 = zzfxVar2;
        zzgg zzggVar = (zzgg) zzfxVar3.iterator();
        zzgg zzggVar2 = (zzgg) zzfxVar4.iterator();
        while (zzggVar.hasNext() && zzggVar2.hasNext()) {
            int iCompare = Integer.compare(zzfx.zza(zzggVar.nextByte()), zzfx.zza(zzggVar2.nextByte()));
            if (iCompare != 0) {
                return iCompare;
            }
        }
        return Integer.compare(zzfxVar3.size(), zzfxVar4.size());
    }
}
