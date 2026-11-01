package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdva {
    public abstract void zzhr(String str);

    public static zzdva zzn(Class cls) {
        return System.getProperty("java.vm.name").equalsIgnoreCase("Dalvik") ? new zzdux(cls.getSimpleName()) : new zzduz(cls.getSimpleName());
    }
}
