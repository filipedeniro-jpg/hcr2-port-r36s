package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzdaq<E> extends zzdai<E> {
    private int zzafu;

    @NullableDecl
    private Object[] zzgox;

    public zzdaq() {
        super(4);
    }

    zzdaq(int i) {
        super(i);
        this.zzgox = new Object[zzdan.zzds(i)];
    }

    public final zzdan<E> zzaoq() {
        zzdan<E> zzdanVarZza;
        int i = this.size;
        if (i == 0) {
            return zzdba.zzgpg;
        }
        if (i == 1) {
            return zzdan.zzae(this.zzgol[0]);
        }
        if (this.zzgox != null && zzdan.zzds(this.size) == this.zzgox.length) {
            Object[] objArrCopyOf = zzdan.zzu(this.size, this.zzgol.length) ? Arrays.copyOf(this.zzgol, this.size) : this.zzgol;
            int i2 = this.zzafu;
            Object[] objArr = this.zzgox;
            zzdanVarZza = new zzdba<>(objArrCopyOf, i2, objArr, objArr.length - 1, this.size);
        } else {
            zzdanVarZza = zzdan.zza(this.size, this.zzgol);
            this.size = zzdanVarZza.size();
        }
        this.zzgom = true;
        this.zzgox = null;
        return zzdanVarZza;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdai, com.google.android.gms.internal.ads.zzdah
    public final /* synthetic */ zzdah zze(Iterable iterable) {
        zzczx.checkNotNull(iterable);
        if (this.zzgox != null) {
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                zzab(it.next());
            }
        } else {
            super.zze(iterable);
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdai
    /* JADX INFO: renamed from: zzac */
    public final /* synthetic */ zzdai zzab(Object obj) {
        return (zzdaq) zzab(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzdah
    public final /* synthetic */ zzdah zza(Iterator it) {
        zzczx.checkNotNull(it);
        while (it.hasNext()) {
            zzab(it.next());
        }
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzdai, com.google.android.gms.internal.ads.zzdah
    public final /* synthetic */ zzdah zzab(Object obj) {
        zzczx.checkNotNull(obj);
        if (this.zzgox != null) {
            int iZzds = zzdan.zzds(this.size);
            Object[] objArr = this.zzgox;
            if (iZzds <= objArr.length) {
                int length = objArr.length - 1;
                int iHashCode = obj.hashCode();
                int iZzdq = zzdag.zzdq(iHashCode);
                while (true) {
                    int i = iZzdq & length;
                    Object[] objArr2 = this.zzgox;
                    Object obj2 = objArr2[i];
                    if (obj2 == null) {
                        objArr2[i] = obj;
                        this.zzafu += iHashCode;
                        super.zzab(obj);
                        break;
                    }
                    if (obj2.equals(obj)) {
                        break;
                    }
                    iZzdq = i + 1;
                }
                return this;
            }
        }
        this.zzgox = null;
        super.zzab(obj);
        return this;
    }
}
