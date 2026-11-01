package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzfl implements Callable {
    private final String TAG = getClass().getSimpleName();
    private final String className;
    protected final zzbo.zza.zzb zzaaa;
    private final String zzaaj;
    protected Method zzaal;
    private final int zzaap;
    private final int zzaaq;
    protected final zzdx zzvo;

    public zzfl(zzdx zzdxVar, String str, String str2, zzbo.zza.zzb zzbVar, int i, int i2) {
        this.zzvo = zzdxVar;
        this.className = str;
        this.zzaaj = str2;
        this.zzaaa = zzbVar;
        this.zzaap = i;
        this.zzaaq = i2;
    }

    protected abstract void zzct() throws IllegalAccessException, InvocationTargetException;

    @Override // java.util.concurrent.Callable
    /* JADX INFO: renamed from: zzcv, reason: merged with bridge method [inline-methods] */
    public Void call() throws Exception {
        try {
            long jNanoTime = System.nanoTime();
            this.zzaal = this.zzvo.zzc(this.className, this.zzaaj);
            if (this.zzaal == null) {
                return null;
            }
            zzct();
            zzda zzdaVarZzci = this.zzvo.zzci();
            if (zzdaVarZzci != null && this.zzaap != Integer.MIN_VALUE) {
                zzdaVarZzci.zza(this.zzaaq, this.zzaap, (System.nanoTime() - jNanoTime) / 1000);
            }
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
        return null;
    }
}
