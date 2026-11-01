package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [V, K] */
/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdsm<K, V> implements Comparable<zzdsm>, Map.Entry<K, V> {
    private V value;
    private final /* synthetic */ zzdsh zzhnt;

    /* JADX INFO: Incorrect field signature: TK; */
    private final Comparable zzhnx;

    zzdsm(zzdsh zzdshVar, Map.Entry<K, V> entry) {
        this(zzdshVar, (Comparable) entry.getKey(), entry.getValue());
    }

    /* JADX WARN: Multi-variable type inference failed */
    zzdsm(zzdsh zzdshVar, K k, V v) {
        this.zzhnt = zzdshVar;
        this.zzhnx = k;
        this.value = v;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        return this.value;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        this.zzhnt.zzbbm();
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return equals(this.zzhnx, entry.getKey()) && equals(this.value, entry.getValue());
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Comparable comparable = this.zzhnx;
        int iHashCode = comparable == null ? 0 : comparable.hashCode();
        V v = this.value;
        return iHashCode ^ (v != null ? v.hashCode() : 0);
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zzhnx);
        String strValueOf2 = String.valueOf(this.value);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 1 + String.valueOf(strValueOf2).length());
        sb.append(strValueOf);
        sb.append(Constants.RequestParameters.EQUAL);
        sb.append(strValueOf2);
        return sb.toString();
    }

    private static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @Override // java.util.Map.Entry
    public final /* synthetic */ Object getKey() {
        return this.zzhnx;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzdsm zzdsmVar) {
        return ((Comparable) getKey()).compareTo((Comparable) zzdsmVar.getKey());
    }
}
