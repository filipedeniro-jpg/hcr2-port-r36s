package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsg<FieldDescriptorType> extends zzdsh<FieldDescriptorType, Object> {
    zzdsg(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.ads.zzdsh
    public final void zzaxe() {
        if (!isImmutable()) {
            for (int i = 0; i < zzbbj(); i++) {
                Map.Entry<FieldDescriptorType, Object> entryZzha = zzha(i);
                if (((zzdpv) entryZzha.getKey()).zzaze()) {
                    entryZzha.setValue(Collections.unmodifiableList((List) entryZzha.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzbbk()) {
                if (((zzdpv) entry.getKey()).zzaze()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzaxe();
    }
}
