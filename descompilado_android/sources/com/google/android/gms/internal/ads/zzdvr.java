package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvr<T> implements zzdvi<Set<T>> {
    private static final zzdvi<Set<Object>> zzhxe = zzdvl.zzbb(Collections.emptySet());
    private final List<zzdvv<T>> zzhxf;
    private final List<zzdvv<Collection<T>>> zzhxg;

    public static <T> zzdvt<T> zzar(int i, int i2) {
        return new zzdvt<>(i, i2);
    }

    private zzdvr(List<zzdvv<T>> list, List<zzdvv<Collection<T>>> list2) {
        this.zzhxf = list;
        this.zzhxg = list2;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        int size = this.zzhxf.size();
        ArrayList arrayList = new ArrayList(this.zzhxg.size());
        int size2 = this.zzhxg.size();
        int size3 = size;
        for (int i = 0; i < size2; i++) {
            Collection<T> collection = this.zzhxg.get(i).get();
            size3 += collection.size();
            arrayList.add(collection);
        }
        HashSet hashSetZzhm = zzdvh.zzhm(size3);
        int size4 = this.zzhxf.size();
        for (int i2 = 0; i2 < size4; i2++) {
            hashSetZzhm.add(zzdvo.checkNotNull(this.zzhxf.get(i2).get()));
        }
        int size5 = arrayList.size();
        for (int i3 = 0; i3 < size5; i3++) {
            Iterator it = ((Collection) arrayList.get(i3)).iterator();
            while (it.hasNext()) {
                hashSetZzhm.add(zzdvo.checkNotNull(it.next()));
            }
        }
        return Collections.unmodifiableSet(hashSetZzhm);
    }
}
