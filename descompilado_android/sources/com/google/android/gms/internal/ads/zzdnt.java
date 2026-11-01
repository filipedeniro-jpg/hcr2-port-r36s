package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdnt extends ThreadLocal<SecureRandom> {
    zzdnt() {
    }

    @Override // java.lang.ThreadLocal
    protected final /* synthetic */ SecureRandom initialValue() {
        return zzdnq.zzaww();
    }
}
