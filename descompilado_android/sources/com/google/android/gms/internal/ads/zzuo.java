package com.google.android.gms.internal.ads;

import java.util.Random;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzuo {
    private static zzuo zzcdg = new zzuo();
    private final zzawe zzcdh;
    private final zzuc zzcdi;
    private final String zzcdj;
    private final zzyo zzcdk;
    private final zzyq zzcdl;
    private final zzyp zzcdm;
    private final zzawv zzcdn;
    private final Random zzcdo;
    private final WeakHashMap<Object, String> zzcdp;

    protected zzuo() {
        this(new zzawe(), new zzuc(new zztn(), new zzto(), new zzxg(), new zzact(), new zzaps(), new zzaqw(), new zzamv(), new zzacw()), new zzyo(), new zzyq(), new zzyp(), zzawe.zzwd(), new zzawv(0, 15301000, true), new Random(), new WeakHashMap());
    }

    private zzuo(zzawe zzaweVar, zzuc zzucVar, zzyo zzyoVar, zzyq zzyqVar, zzyp zzypVar, String str, zzawv zzawvVar, Random random, WeakHashMap<Object, String> weakHashMap) {
        this.zzcdh = zzaweVar;
        this.zzcdi = zzucVar;
        this.zzcdk = zzyoVar;
        this.zzcdl = zzyqVar;
        this.zzcdm = zzypVar;
        this.zzcdj = str;
        this.zzcdn = zzawvVar;
        this.zzcdo = random;
        this.zzcdp = weakHashMap;
    }

    public static zzawe zzof() {
        return zzcdg.zzcdh;
    }

    public static zzuc zzog() {
        return zzcdg.zzcdi;
    }

    public static zzyq zzoh() {
        return zzcdg.zzcdl;
    }

    public static zzyo zzoi() {
        return zzcdg.zzcdk;
    }

    public static zzyp zzoj() {
        return zzcdg.zzcdm;
    }

    public static String zzok() {
        return zzcdg.zzcdj;
    }

    public static zzawv zzol() {
        return zzcdg.zzcdn;
    }

    public static Random zzom() {
        return zzcdg.zzcdo;
    }
}
