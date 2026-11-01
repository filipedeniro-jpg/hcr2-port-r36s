package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
final class zzi {
    static MetadataField<?> zza(MetadataBundle metadataBundle) {
        Set<MetadataField<?>> setZzbg = metadataBundle.zzbg();
        if (setZzbg.size() == 1) {
            return setZzbg.iterator().next();
        }
        throw new IllegalArgumentException("bundle should have exactly 1 populated field");
    }
}
