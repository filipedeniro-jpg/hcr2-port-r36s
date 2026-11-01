package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.LinkedHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdve<K, V, V2> {
    final LinkedHashMap<K, zzdvv<V>> zzhwx;

    zzdve(int i) {
        this.zzhwx = zzdvh.zzhn(i);
    }

    zzdve<K, V, V2> zza(K k, zzdvv<V> zzdvvVar) {
        this.zzhwx.put((K) zzdvo.zza(k, Constants.ParametersKeys.KEY), (zzdvv<V>) zzdvo.zza(zzdvvVar, "provider"));
        return this;
    }
}
