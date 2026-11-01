package com.google.android.gms.internal.ads;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzem extends zzfl {
    private static final zzfk<zzcf> zzzt = new zzfk<>();
    private final Context zzzu;
    private zzbk.zza zzzv;

    public zzem(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2, Context context, zzbk.zza zzaVar) {
        super(zzdxVar, str, str2, zzbVar, i, 27);
        this.zzzv = null;
        this.zzzu = context;
        this.zzzv = zzaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzfl
    protected final void zzct() throws IllegalAccessException, InvocationTargetException {
        zzcf zzcfVar;
        zzbm zzbmVar;
        AtomicReference<zzcf> atomicReferenceZzau = zzzt.zzau(this.zzzu.getPackageName());
        synchronized (atomicReferenceZzau) {
            zzcf zzcfVar2 = atomicReferenceZzau.get();
            if (zzcfVar2 == null || zzee.zzat(zzcfVar2.zzno) || zzcfVar2.zzno.equals("E") || zzcfVar2.zzno.equals("0000000000000000000000000000000000000000000000000000000000000000")) {
                if (!zzee.zzat(zza(this.zzzv))) {
                    zzbmVar = zzbm.ENUM_SIGNAL_SOURCE_CALLER_PROVIDED;
                } else {
                    zzbk.zza zzaVar = this.zzzv;
                    if (Boolean.valueOf(zzee.zzat(zza(zzaVar)) && zzaVar != null && zzaVar.zzt() && zzaVar.zzu().zzy() == zzbm.ENUM_SIGNAL_SOURCE_GASS).booleanValue() && this.zzvo.zzcj()) {
                        zzbmVar = zzbm.ENUM_SIGNAL_SOURCE_GASS;
                    } else {
                        zzbmVar = zzbm.ENUM_SIGNAL_SOURCE_ADSHIELD;
                    }
                }
                Method method = this.zzaal;
                Object[] objArr = new Object[3];
                objArr[0] = this.zzzu;
                objArr[1] = Boolean.valueOf(zzbmVar == zzbm.ENUM_SIGNAL_SOURCE_ADSHIELD);
                objArr[2] = zzuo.zzoj().zzd(zzyt.zzcmy);
                zzcf zzcfVar3 = new zzcf((String) method.invoke(null, objArr));
                if (zzee.zzat(zzcfVar3.zzno) || zzcfVar3.zzno.equals("E")) {
                    int i = zzep.zzzx[zzbmVar.ordinal()];
                    if (i == 1) {
                        zzcfVar3.zzno = this.zzzv.zzw().zzad();
                    } else if (i == 2) {
                        String strZzcu = zzcu();
                        if (!zzee.zzat(strZzcu)) {
                            zzcfVar3.zzno = strZzcu;
                        }
                    }
                }
                atomicReferenceZzau.set(zzcfVar3);
            }
            zzcfVar = atomicReferenceZzau.get();
        }
        synchronized (this.zzaaa) {
            if (zzcfVar != null) {
                this.zzaaa.zzab(zzcfVar.zzno);
                this.zzaaa.zzba(zzcfVar.zznp);
                this.zzaaa.zzad(zzcfVar.zznq);
                this.zzaaa.zzae(zzcfVar.zznr);
                this.zzaaa.zzaf(zzcfVar.zzns);
            }
        }
    }

    private static String zza(zzbk.zza zzaVar) {
        if (zzaVar == null || !zzaVar.zzv() || zzee.zzat(zzaVar.zzw().zzad())) {
            return null;
        }
        return zzaVar.zzw().zzad();
    }

    private final String zzcu() {
        try {
            if (this.zzvo.zzcm() != null) {
                this.zzvo.zzcm().get();
            }
            zzbo.zza zzaVarZzcl = this.zzvo.zzcl();
            if (zzaVarZzcl == null || !zzaVarZzcl.zzah()) {
                return null;
            }
            return zzaVarZzcl.zzad();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }
}
