package com.google.android.gms.internal.firebase_remote_config;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class zzby extends AbstractMap<String, Object> implements Cloneable {
    final zzbr zzay;
    Map<String, Object> zzfr;

    public enum zzc {
        IGNORE_CASE
    }

    public zzby() {
        this(EnumSet.noneOf(zzc.class));
    }

    final class zza extends AbstractSet<Map.Entry<String, Object>> {
        private final zzbx zzfu;

        zza() {
            this.zzfu = (zzbx) new zzbs(zzby.this, zzby.this.zzay.zzbv()).entrySet();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator<Map.Entry<String, Object>> iterator() {
            return new zzb(zzby.this, this.zzfu);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return zzby.this.zzfr.size() + this.zzfu.size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            zzby.this.zzfr.clear();
            this.zzfu.clear();
        }
    }

    public zzby(EnumSet<zzc> enumSet) {
        this.zzfr = new zzbl();
        this.zzay = zzbr.zza(getClass(), enumSet.contains(zzc.IGNORE_CASE));
    }

    final class zzb implements Iterator<Map.Entry<String, Object>> {
        private boolean zzfw;
        private final Iterator<Map.Entry<String, Object>> zzfx;
        private final Iterator<Map.Entry<String, Object>> zzfy;

        zzb(zzby zzbyVar, zzbx zzbxVar) {
            this.zzfx = (zzbu) zzbxVar.iterator();
            this.zzfy = zzbyVar.zzfr.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.zzfx.hasNext() || this.zzfy.hasNext();
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (this.zzfw) {
                this.zzfy.remove();
            }
            this.zzfx.remove();
        }

        @Override // java.util.Iterator
        public final /* synthetic */ Map.Entry<String, Object> next() {
            if (!this.zzfw) {
                if (this.zzfx.hasNext()) {
                    return this.zzfx.next();
                }
                this.zzfw = true;
            }
            return this.zzfy.next();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String lowerCase = (String) obj;
        zzbz zzbzVarZzae = this.zzay.zzae(lowerCase);
        if (zzbzVarZzae != null) {
            return zzbzVarZzae.zzh(this);
        }
        if (this.zzay.zzbv()) {
            lowerCase = lowerCase.toLowerCase(Locale.US);
        }
        return this.zzfr.get(lowerCase);
    }

    @Override // java.util.AbstractMap, java.util.Map
    /* JADX INFO: renamed from: zzf, reason: merged with bridge method [inline-methods] */
    public final Object put(String str, Object obj) {
        zzbz zzbzVarZzae = this.zzay.zzae(str);
        if (zzbzVarZzae != null) {
            Object objZzh = zzbzVarZzae.zzh(this);
            zzbzVarZzae.zzb(this, obj);
            return objZzh;
        }
        if (this.zzay.zzbv()) {
            str = str.toLowerCase(Locale.US);
        }
        return this.zzfr.put(str, obj);
    }

    public zzby zzb(String str, Object obj) {
        zzbz zzbzVarZzae = this.zzay.zzae(str);
        if (zzbzVarZzae != null) {
            zzbzVarZzae.zzb(this, obj);
        } else {
            if (this.zzay.zzbv()) {
                str = str.toLowerCase(Locale.US);
            }
            this.zzfr.put(str, obj);
        }
        return this;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map<? extends String, ?> map) {
        for (Map.Entry<? extends String, ?> entry : map.entrySet()) {
            zzb(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (!(obj instanceof String)) {
            return null;
        }
        String lowerCase = (String) obj;
        if (this.zzay.zzae(lowerCase) != null) {
            throw new UnsupportedOperationException();
        }
        if (this.zzay.zzbv()) {
            lowerCase = lowerCase.toLowerCase(Locale.US);
        }
        return this.zzfr.remove(lowerCase);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return new zza();
    }

    @Override // java.util.AbstractMap
    /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
    public zzby clone() {
        try {
            zzby zzbyVar = (zzby) super.clone();
            zzbt.zza(this, zzbyVar);
            zzbyVar.zzfr = (Map) zzbt.clone(this.zzfr);
            return zzbyVar;
        } catch (CloneNotSupportedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final zzbr zzby() {
        return this.zzay;
    }
}
