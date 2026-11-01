package com.google.android.gms.internal.drive;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* JADX INFO: loaded from: classes2.dex */
final class zzmj<FieldDescriptorType> extends zzmi<FieldDescriptorType, Object> {
    zzmj(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.drive.zzmi
    public final void zzbp() {
        if (!isImmutable()) {
            for (int i = 0; i < zzer(); i++) {
                Map.Entry<FieldDescriptorType, Object> entryZzaw = zzaw(i);
                if (((zzkd) entryZzaw.getKey()).zzcs()) {
                    entryZzaw.setValue(Collections.unmodifiableList((List) entryZzaw.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzes()) {
                if (((zzkd) entry.getKey()).zzcs()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzbp();
    }
}
