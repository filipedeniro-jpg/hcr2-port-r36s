package com.google.android.gms.internal.firebase_remote_config;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzbx extends AbstractSet<Map.Entry<String, Object>> {
    private final /* synthetic */ zzbs zzfl;

    zzbx(zzbs zzbsVar) {
        this.zzfl = zzbsVar;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Iterator<String> it = this.zzfl.zzay.zzer.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (this.zzfl.zzay.zzae(it.next()).zzh(this.zzfl.object) != null) {
                i++;
            }
        }
        return i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Iterator<String> it = this.zzfl.zzay.zzer.iterator();
        while (it.hasNext()) {
            this.zzfl.zzay.zzae(it.next()).zzb(this.zzfl.object, null);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        Iterator<String> it = this.zzfl.zzay.zzer.iterator();
        while (it.hasNext()) {
            if (this.zzfl.zzay.zzae(it.next()).zzh(this.zzfl.object) != null) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final /* synthetic */ Iterator iterator() {
        return new zzbu(this.zzfl);
    }
}
