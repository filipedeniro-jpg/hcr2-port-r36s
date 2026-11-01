package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public abstract class zza<T> implements MetadataField<T> {
    private final String fieldName;
    private final Set<String> zziw;
    private final Set<String> zzix;
    private final int zziy;

    protected zza(String str, int i) {
        this.fieldName = (String) Preconditions.checkNotNull(str, "fieldName");
        this.zziw = Collections.singleton(str);
        this.zzix = Collections.emptySet();
        this.zziy = i;
    }

    protected abstract void zza(Bundle bundle, T t);

    protected abstract T zzb(Bundle bundle);

    protected abstract T zzc(DataHolder dataHolder, int i, int i2);

    protected zza(String str, Collection<String> collection, Collection<String> collection2, int i) {
        this.fieldName = (String) Preconditions.checkNotNull(str, "fieldName");
        this.zziw = Collections.unmodifiableSet(new HashSet(collection));
        this.zzix = Collections.unmodifiableSet(new HashSet(collection2));
        this.zziy = i;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final String getName() {
        return this.fieldName;
    }

    public final Collection<String> zzaz() {
        return this.zziw;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final T zza(Bundle bundle) {
        Preconditions.checkNotNull(bundle, "bundle");
        if (bundle.get(this.fieldName) != null) {
            return zzb(bundle);
        }
        return null;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final void zza(T t, Bundle bundle) {
        Preconditions.checkNotNull(bundle, "bundle");
        if (t == null) {
            bundle.putString(this.fieldName, null);
        } else {
            zza(bundle, t);
        }
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final void zza(DataHolder dataHolder, MetadataBundle metadataBundle, int i, int i2) {
        Preconditions.checkNotNull(dataHolder, "dataHolder");
        Preconditions.checkNotNull(metadataBundle, "bundle");
        if (zzb(dataHolder, i, i2)) {
            metadataBundle.zzb(this, zzc(dataHolder, i, i2));
        }
    }

    public String toString() {
        return this.fieldName;
    }

    @Override // com.google.android.gms.drive.metadata.MetadataField
    public final T zza(DataHolder dataHolder, int i, int i2) {
        if (zzb(dataHolder, i, i2)) {
            return zzc(dataHolder, i, i2);
        }
        return null;
    }

    protected boolean zzb(DataHolder dataHolder, int i, int i2) {
        for (String str : this.zziw) {
            if (dataHolder.isClosed() || !dataHolder.hasColumn(str) || dataHolder.hasNull(str, i, i2)) {
                return false;
            }
        }
        return true;
    }
}
