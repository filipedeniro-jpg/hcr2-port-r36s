package com.google.android.gms.internal.firebase_remote_config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class zzds {
    private final int limit;
    private final zzdj zzhg;
    private final boolean zzhh;
    private final zzdw zzhi;

    private zzds(zzdw zzdwVar) {
        this(zzdwVar, false, zzdn.zzhe, Integer.MAX_VALUE);
    }

    private zzds(zzdw zzdwVar, boolean z, zzdj zzdjVar, int i) {
        this.zzhi = zzdwVar;
        this.zzhh = false;
        this.zzhg = zzdjVar;
        this.limit = Integer.MAX_VALUE;
    }

    public static zzds zza(zzdj zzdjVar) {
        zzdt.checkNotNull(zzdjVar);
        return new zzds(new zzdv(zzdjVar));
    }

    public final List<String> zza(CharSequence charSequence) {
        zzdt.checkNotNull(charSequence);
        Iterator<String> itZza = this.zzhi.zza(this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (itZza.hasNext()) {
            arrayList.add(itZza.next());
        }
        return Collections.unmodifiableList(arrayList);
    }
}
