package com.google.android.gms.internal.firebase_remote_config;

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

/* JADX INFO: loaded from: classes2.dex */
class zzjk<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzqg;
    private final int zzws;
    private List<zzjp> zzwt;
    private Map<K, V> zzwu;
    private volatile zzjr zzwv;
    private Map<K, V> zzww;
    private volatile zzjl zzwx;

    static <FieldDescriptorType extends zzhc<FieldDescriptorType>> zzjk<FieldDescriptorType, Object> zzbl(int i) {
        return new zzjj(i);
    }

    private zzjk(int i) {
        this.zzws = i;
        this.zzwt = Collections.emptyList();
        this.zzwu = Collections.emptyMap();
        this.zzww = Collections.emptyMap();
    }

    public void zzes() {
        Map<K, V> mapUnmodifiableMap;
        Map<K, V> mapUnmodifiableMap2;
        if (this.zzqg) {
            return;
        }
        if (this.zzwu.isEmpty()) {
            mapUnmodifiableMap = Collections.emptyMap();
        } else {
            mapUnmodifiableMap = Collections.unmodifiableMap(this.zzwu);
        }
        this.zzwu = mapUnmodifiableMap;
        if (this.zzww.isEmpty()) {
            mapUnmodifiableMap2 = Collections.emptyMap();
        } else {
            mapUnmodifiableMap2 = Collections.unmodifiableMap(this.zzww);
        }
        this.zzww = mapUnmodifiableMap2;
        this.zzqg = true;
    }

    public final boolean isImmutable() {
        return this.zzqg;
    }

    public final int zzir() {
        return this.zzwt.size();
    }

    public final Map.Entry<K, V> zzbm(int i) {
        return this.zzwt.get(i);
    }

    public final Iterable<Map.Entry<K, V>> zzis() {
        if (this.zzwu.isEmpty()) {
            return zzjo.zzix();
        }
        return this.zzwu.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int size() {
        return this.zzwt.size() + this.zzwu.size();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzwu.containsKey(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return (V) this.zzwt.get(iZza).getValue();
        }
        return this.zzwu.get(comparable);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final V put(K k, V v) {
        zziu();
        int iZza = zza(k);
        if (iZza >= 0) {
            return (V) this.zzwt.get(iZza).setValue(v);
        }
        zziu();
        if (this.zzwt.isEmpty() && !(this.zzwt instanceof ArrayList)) {
            this.zzwt = new ArrayList(this.zzws);
        }
        int i = -(iZza + 1);
        if (i >= this.zzws) {
            return zziv().put(k, v);
        }
        int size = this.zzwt.size();
        int i2 = this.zzws;
        if (size == i2) {
            zzjp zzjpVarRemove = this.zzwt.remove(i2 - 1);
            zziv().put((Comparable) zzjpVarRemove.getKey(), zzjpVarRemove.getValue());
        }
        this.zzwt.add(i, new zzjp(this, k, v));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public void clear() {
        zziu();
        if (!this.zzwt.isEmpty()) {
            this.zzwt.clear();
        }
        if (this.zzwu.isEmpty()) {
            return;
        }
        this.zzwu.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public V remove(Object obj) {
        zziu();
        Comparable comparable = (Comparable) obj;
        int iZza = zza(comparable);
        if (iZza >= 0) {
            return zzbn(iZza);
        }
        if (this.zzwu.isEmpty()) {
            return null;
        }
        return this.zzwu.remove(comparable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final V zzbn(int i) {
        zziu();
        V v = (V) this.zzwt.remove(i).getValue();
        if (!this.zzwu.isEmpty()) {
            Iterator<Map.Entry<K, V>> it = zziv().entrySet().iterator();
            this.zzwt.add(new zzjp(this, it.next()));
            it.remove();
        }
        return v;
    }

    private final int zza(K k) {
        int size = this.zzwt.size() - 1;
        if (size >= 0) {
            int iCompareTo = k.compareTo((Comparable) this.zzwt.get(size).getKey());
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
            int iCompareTo2 = k.compareTo((Comparable) this.zzwt.get(i2).getKey());
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
        if (this.zzwv == null) {
            this.zzwv = new zzjr(this, null);
        }
        return this.zzwv;
    }

    final Set<Map.Entry<K, V>> zzit() {
        if (this.zzwx == null) {
            this.zzwx = new zzjl(this, null);
        }
        return this.zzwx;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zziu() {
        if (this.zzqg) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zziv() {
        zziu();
        if (this.zzwu.isEmpty() && !(this.zzwu instanceof TreeMap)) {
            this.zzwu = new TreeMap();
            this.zzww = ((TreeMap) this.zzwu).descendingMap();
        }
        return (SortedMap) this.zzwu;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzjk)) {
            return super.equals(obj);
        }
        zzjk zzjkVar = (zzjk) obj;
        int size = size();
        if (size != zzjkVar.size()) {
            return false;
        }
        int iZzir = zzir();
        if (iZzir != zzjkVar.zzir()) {
            return entrySet().equals(zzjkVar.entrySet());
        }
        for (int i = 0; i < iZzir; i++) {
            if (!zzbm(i).equals(zzjkVar.zzbm(i))) {
                return false;
            }
        }
        if (iZzir != size) {
            return this.zzwu.equals(zzjkVar.zzwu);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        int iZzir = zzir();
        int iHashCode = 0;
        for (int i = 0; i < iZzir; i++) {
            iHashCode += this.zzwt.get(i).hashCode();
        }
        return this.zzwu.size() > 0 ? iHashCode + this.zzwu.hashCode() : iHashCode;
    }

    /* synthetic */ zzjk(int i, zzjj zzjjVar) {
        this(i);
    }
}
