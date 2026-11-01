package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznl extends zzni {
    private final int responseCode;
    private final Map<String, List<String>> zzbfl;

    public zznl(int i, Map<String, List<String>> map, zznh zznhVar) {
        StringBuilder sb = new StringBuilder(26);
        sb.append("Response code: ");
        sb.append(i);
        super(sb.toString(), zznhVar, 1);
        this.responseCode = i;
        this.zzbfl = map;
    }
}
