package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznk {
    private final Map<String, String> zzbfj = new HashMap();
    private Map<String, String> zzbfk;

    public final synchronized Map<String, String> zzib() {
        if (this.zzbfk == null) {
            this.zzbfk = Collections.unmodifiableMap(new HashMap(this.zzbfj));
        }
        return this.zzbfk;
    }
}
