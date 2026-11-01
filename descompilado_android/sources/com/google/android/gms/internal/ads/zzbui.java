package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import androidx.collection.SimpleArrayMap;
import java.util.ArrayList;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbui {
    public static final zzbui zzfls = new zzbuk().zzaii();

    @Nullable
    private final zzabt zzfll;

    @Nullable
    private final zzabs zzflm;

    @Nullable
    private final zzacf zzfln;

    @Nullable
    private final zzace zzflo;

    @Nullable
    private final zzafp zzflp;
    private final SimpleArrayMap<String, zzabz> zzflq;
    private final SimpleArrayMap<String, zzaby> zzflr;

    @Nullable
    public final zzabt zzaia() {
        return this.zzfll;
    }

    @Nullable
    public final zzabs zzaib() {
        return this.zzflm;
    }

    @Nullable
    public final zzacf zzaic() {
        return this.zzfln;
    }

    @Nullable
    public final zzace zzaid() {
        return this.zzflo;
    }

    @Nullable
    public final zzafp zzaie() {
        return this.zzflp;
    }

    @Nullable
    public final zzabz zzfu(String str) {
        return this.zzflq.get(str);
    }

    @Nullable
    public final zzaby zzfv(String str) {
        return this.zzflr.get(str);
    }

    public final ArrayList<String> zzaif() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.zzfln != null) {
            arrayList.add(Integer.toString(6));
        }
        if (this.zzfll != null) {
            arrayList.add(Integer.toString(1));
        }
        if (this.zzflm != null) {
            arrayList.add(Integer.toString(2));
        }
        if (this.zzflq.size() > 0) {
            arrayList.add(Integer.toString(3));
        }
        if (this.zzflp != null) {
            arrayList.add(Integer.toString(7));
        }
        return arrayList;
    }

    public final ArrayList<String> zzaig() {
        ArrayList<String> arrayList = new ArrayList<>(this.zzflq.size());
        for (int i = 0; i < this.zzflq.size(); i++) {
            arrayList.add(this.zzflq.keyAt(i));
        }
        return arrayList;
    }

    private zzbui(zzbuk zzbukVar) {
        this.zzfll = zzbukVar.zzfll;
        this.zzflm = zzbukVar.zzflm;
        this.zzfln = zzbukVar.zzfln;
        this.zzflq = new SimpleArrayMap<>(zzbukVar.zzflq);
        this.zzflr = new SimpleArrayMap<>(zzbukVar.zzflr);
        this.zzflo = zzbukVar.zzflo;
        this.zzflp = zzbukVar.zzflp;
    }
}
