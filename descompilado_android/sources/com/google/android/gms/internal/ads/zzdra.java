package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdra implements zzdsf {
    private static final zzdrk zzhmb = new zzdrd();
    private final zzdrk zzhma;

    public zzdra() {
        this(new zzdrc(zzdqb.zzazg(), zzbam()));
    }

    private zzdra(zzdrk zzdrkVar) {
        this.zzhma = (zzdrk) zzdqe.zza(zzdrkVar, "messageInfoFactory");
    }

    @Override // com.google.android.gms.internal.ads.zzdsf
    public final <T> zzdsc<T> zzg(Class<T> cls) {
        zzdse.zzi(cls);
        zzdrl zzdrlVarZzd = this.zzhma.zzd(cls);
        if (zzdrlVarZzd.zzbau()) {
            if (zzdqd.class.isAssignableFrom(cls)) {
                return zzdrt.zza(zzdse.zzbbg(), zzdpu.zzaza(), zzdrlVarZzd.zzbav());
            }
            return zzdrt.zza(zzdse.zzbbe(), zzdpu.zzazb(), zzdrlVarZzd.zzbav());
        }
        if (zzdqd.class.isAssignableFrom(cls)) {
            if (zza(zzdrlVarZzd)) {
                return zzdrr.zza(cls, zzdrlVarZzd, zzdrx.zzbay(), zzdqx.zzbal(), zzdse.zzbbg(), zzdpu.zzaza(), zzdri.zzbar());
            }
            return zzdrr.zza(cls, zzdrlVarZzd, zzdrx.zzbay(), zzdqx.zzbal(), zzdse.zzbbg(), (zzdps<?>) null, zzdri.zzbar());
        }
        if (zza(zzdrlVarZzd)) {
            return zzdrr.zza(cls, zzdrlVarZzd, zzdrx.zzbax(), zzdqx.zzbak(), zzdse.zzbbe(), zzdpu.zzazb(), zzdri.zzbaq());
        }
        return zzdrr.zza(cls, zzdrlVarZzd, zzdrx.zzbax(), zzdqx.zzbak(), zzdse.zzbbf(), (zzdps<?>) null, zzdri.zzbaq());
    }

    private static boolean zza(zzdrl zzdrlVar) {
        return zzdrlVar.zzbat() == zzdqd.zzd.zzhkp;
    }

    private static zzdrk zzbam() {
        try {
            return (zzdrk) Class.forName("com.google.protobuf.DescriptorMessageInfoFactory").getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return zzhmb;
        }
    }
}
