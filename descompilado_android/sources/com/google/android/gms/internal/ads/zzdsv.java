package com.google.android.gms.internal.ads;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdsv extends RuntimeException {
    private final List<String> zzhoa;

    public zzdsv(zzdrn zzdrnVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzhoa = null;
    }
}
