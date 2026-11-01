package com.google.android.gms.internal.ads;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdec<P> {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private final Class<P> zzgrx;
    private ConcurrentMap<String, List<zzdeb<P>>> zzgsh = new ConcurrentHashMap();
    private zzdeb<P> zzgsi;

    public final zzdeb<P> zzapq() {
        return this.zzgsi;
    }

    private zzdec(Class<P> cls) {
        this.zzgrx = cls;
    }

    public static <P> zzdec<P> zza(Class<P> cls) {
        return new zzdec<>(cls);
    }

    public final void zza(zzdeb<P> zzdebVar) {
        if (zzdebVar == null) {
            throw new IllegalArgumentException("the primary entry must be non-null");
        }
        if (zzdebVar.zzapn() != zzdiy.ENABLED) {
            throw new IllegalArgumentException("the primary entry has to be ENABLED");
        }
        List<zzdeb<P>> listEmptyList = this.zzgsh.get(new String(zzdebVar.zzapp(), UTF_8));
        if (listEmptyList == null) {
            listEmptyList = Collections.emptyList();
        }
        if (listEmptyList.isEmpty()) {
            throw new IllegalArgumentException("the primary entry cannot be set to an entry which is not held by this primitive set");
        }
        this.zzgsi = zzdebVar;
    }

    public final zzdeb<P> zza(P p, zzdje.zza zzaVar) throws GeneralSecurityException {
        byte[] bArrArray;
        if (zzaVar.zzapn() != zzdiy.ENABLED) {
            throw new GeneralSecurityException("only ENABLED key is allowed");
        }
        int i = zzddn.zzgrt[zzaVar.zzapo().ordinal()];
        if (i == 1 || i == 2) {
            bArrArray = ByteBuffer.allocate(5).put((byte) 0).putInt(zzaVar.zzauk()).array();
        } else if (i == 3) {
            bArrArray = ByteBuffer.allocate(5).put((byte) 1).putInt(zzaVar.zzauk()).array();
        } else if (i == 4) {
            bArrArray = zzddo.zzgru;
        } else {
            throw new GeneralSecurityException("unknown output prefix type");
        }
        zzdeb<P> zzdebVar = new zzdeb<>(p, bArrArray, zzaVar.zzapn(), zzaVar.zzapo());
        ArrayList arrayList = new ArrayList();
        arrayList.add(zzdebVar);
        String str = new String(zzdebVar.zzapp(), UTF_8);
        List<zzdeb<P>> listPut = this.zzgsh.put(str, Collections.unmodifiableList(arrayList));
        if (listPut != null) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(listPut);
            arrayList2.add(zzdebVar);
            this.zzgsh.put(str, Collections.unmodifiableList(arrayList2));
        }
        return zzdebVar;
    }

    public final Class<P> zzapj() {
        return this.zzgrx;
    }
}
