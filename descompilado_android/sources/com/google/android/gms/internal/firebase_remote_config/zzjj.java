package com.google.android.gms.internal.firebase_remote_config;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* JADX INFO: loaded from: classes2.dex */
final class zzjj<FieldDescriptorType> extends zzjk<FieldDescriptorType, Object> {
    zzjj(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzjk
    public final void zzes() {
        if (!isImmutable()) {
            for (int i = 0; i < zzir(); i++) {
                Map.Entry<FieldDescriptorType, Object> entryZzbm = zzbm(i);
                if (((zzhc) entryZzbm.getKey()).zzgq()) {
                    entryZzbm.setValue(Collections.unmodifiableList((List) entryZzbm.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzis()) {
                if (((zzhc) entry.getKey()).zzgq()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzes();
    }
}
