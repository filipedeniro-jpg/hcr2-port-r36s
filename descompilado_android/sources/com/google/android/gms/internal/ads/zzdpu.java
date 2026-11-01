package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpu {
    private static final zzdps<?> zzhhf = new zzdpr();
    private static final zzdps<?> zzhhg = zzayz();

    private static zzdps<?> zzayz() {
        try {
            return (zzdps) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzdps<?> zzaza() {
        return zzhhf;
    }

    static zzdps<?> zzazb() {
        zzdps<?> zzdpsVar = zzhhg;
        if (zzdpsVar != null) {
            return zzdpsVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
