package com.google.android.gms.internal.firebase_auth;

import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes.dex */
public abstract class zzbg<E> extends zzba<E> implements Set<E> {

    @NullableDecl
    private transient zzaz<E> zza;

    zzbg() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        return zzbl.zza(this, obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return zzbl.zza(this);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba
    public zzaz<E> zzc() {
        zzaz<E> zzazVar = this.zza;
        if (zzazVar != null) {
            return zzazVar;
        }
        zzaz<E> zzazVarZza = zza();
        this.zza = zzazVarZza;
        return zzazVarZza;
    }

    zzaz<E> zza() {
        return zzaz.zza(toArray());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzba, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public /* synthetic */ Iterator iterator() {
        return iterator();
    }
}
