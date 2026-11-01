package com.google.android.gms.internal.ads;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
class zzdsh<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzhhc;
    private final int zzhnn;
    private List<zzdsm> zzhno;
    private Map<K, V> zzhnp;
    private volatile zzdso zzhnq;
    private Map<K, V> zzhnr;
    private volatile zzdsi zzhns;

    static <FieldDescriptorType extends zzdpv<FieldDescriptorType>> zzdsh<FieldDescriptorType, Object> zzgz(int i) {
        return new zzdsg(i);
    }

    private zzdsh(int i) {
        this.zzhnn = i;
        this.zzhno = Collections.emptyList();
        this.zzhnp = Collections.emptyMap();
        this.zzhnr = Collections.emptyMap();
    }

    public void zzaxe() {
        Map<K, V> mapUnmodifiableMap;
        Map<K, V> mapUnmodifiableMap2;
        if (this.zzhhc) {
            return;
        }
        if (this.zzhnp.isEmpty()) {
            mapUnmodifiableMap = Collections.emptyMap();
        } else {
            mapUnmodifiableMap = Collections.unmodifiableMap(this.zzhnp);
        }
        this.zzhnp = mapUnmodifiableMap;
        if (this.zzhnr.isEmpty()) {
            mapUnmodifiableMap2 = Collections.emptyMap();
        } else {
            mapUnmodifiableMap2 = Collections.unmodifiableMap(this.zzhnr);
        }
        this.zzhnr = mapUnmodifiableMap2;
        this.zzhhc = true;
    }

    public final boolean isImmutable() {
        return this.zzhhc;
    }

    public final int zzbbj() {
        return this.zzhno.size();
    }

    public final Map.Entry<K, V> zzha(int i) {
        return this.zzhno.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzbbk() {
        if (this.zzhnp.isEmpty()) {
            return zzdsl.zzbbp();
        }
        return this.zzhnp.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzhno.size() + this.zzhnp.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzhnp.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return (V) this.zzhno.get(iZza).getValue();
        }
        return this.zzhnp.get(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final V put(K k, V v) {
        zzbbm();
        int iZza = zza(k);
        if (iZza >= 0) {
            return (V) this.zzhno.get(iZza).setValue(v);
        }
        zzbbm();
        if (this.zzhno.isEmpty() && !(this.zzhno instanceof ArrayList)) {
            this.zzhno = new ArrayList(this.zzhnn);
        }
        int i = -(iZza + 1);
        if (i >= this.zzhnn) {
            return zzbbn().put(k, v);
        }
        int size = this.zzhno.size();
        int i2 = this.zzhnn;
        if (size == i2) {
            zzdsm zzdsmVarRemove = this.zzhno.remove(i2 - 1);
            zzbbn().put((Comparable) zzdsmVarRemove.getKey(), zzdsmVarRemove.getValue());
        }
        this.zzhno.add(i, new zzdsm(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zzbbm();
        if (!this.zzhno.isEmpty()) {
            this.zzhno.clear();
        }
        if (this.zzhnp.isEmpty()) {
            return;
        }
        this.zzhnp.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zzbbm();
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return zzhb(iZza);
        }
        if (this.zzhnp.isEmpty()) {
            return null;
        }
        return this.zzhnp.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzhb(int i) {
        zzbbm();
        V v = (V) this.zzhno.remove(i).getValue();
        if (!this.zzhnp.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zzbbn().entrySet().iterator();
            this.zzhno.add(new zzdsm(this, it.next()));
            it.remove();
        }
        return v;
    }

    private final int zza(K k) {
        int size = this.zzhno.size() - 1;
        if (size >= 0) {
            int iCompareTo = k.compareTo((Comparable) this.zzhno.get(size).getKey());
            if (iCompareTo > 0) {
                return -(size + 2);
            }
            if (iCompareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int iCompareTo2 = k.compareTo((Comparable) this.zzhno.get(i2).getKey());
            if (iCompareTo2 < 0) {
                size = i2 - 1;
            } else {
                if (iCompareTo2 <= 0) {
                    return i2;
                }
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.zzhnq == null) {
            this.zzhnq = new zzdso(this, null);
        }
        return this.zzhnq;
    }

    final Set<Map.Entry<K, V>> zzbbl() {
        if (this.zzhns == null) {
            this.zzhns = new zzdsi(this, null);
        }
        return this.zzhns;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbbm() {
        if (this.zzhhc) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzbbn() {
        zzbbm();
        if (this.zzhnp.isEmpty() && !(this.zzhnp instanceof TreeMap)) {
            this.zzhnp = new TreeMap();
            this.zzhnr = ((TreeMap) this.zzhnp).descendingMap();
        }
        return (SortedMap) this.zzhnp;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdsh)) {
            return super.equals(obj);
        }
        zzdsh zzdshVar = (zzdsh) obj;
        int size = size();
        if (size != zzdshVar.size()) {
            return false;
        }
        int iZzbbj = zzbbj();
        if (iZzbbj != zzdshVar.zzbbj()) {
            return entrySet().equals(zzdshVar.entrySet());
        }
        for (int i = 0; i < iZzbbj; i++) {
            if (!zzha(i).equals(zzdshVar.zzha(i))) {
                return false;
            }
        }
        if (iZzbbj != size) {
            return this.zzhnp.equals(zzdshVar.zzhnp);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZzbbj = zzbbj();
        int iHashCode = 0;
        for (int i = 0; i < iZzbbj; i++) {
            iHashCode += this.zzhno.get(i).hashCode();
        }
        return this.zzhnp.size() > 0 ? iHashCode + this.zzhnp.hashCode() : iHashCode;
    }

    /* synthetic */ zzdsh(int i, zzdsg zzdsgVar) {
        this(i);
    }
}
