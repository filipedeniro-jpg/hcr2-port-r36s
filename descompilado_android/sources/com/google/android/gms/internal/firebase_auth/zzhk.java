package com.google.android.gms.internal.firebase_auth;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public class zzhk {
    private static volatile boolean zza = false;
    private static boolean zzb = true;
    private static volatile zzhk zzd;
    private static volatile zzhk zze;
    private final Map<zza, zzhx.zzc<?, ?>> zzg;
    private static final Class<?> zzc = zzc();
    private static final zzhk zzf = new zzhk(true);

    private static Class<?> zzc() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    static final class zza {
        private final Object zza;
        private final int zzb;

        zza(Object obj, int i) {
            this.zza = obj;
            this.zzb = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.zza) * 65535) + this.zzb;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.zza == zzaVar.zza && this.zzb == zzaVar.zzb;
        }
    }

    public static zzhk zza() {
        zzhk zzhkVar = zzd;
        if (zzhkVar == null) {
            synchronized (zzhk.class) {
                zzhkVar = zzd;
                if (zzhkVar == null) {
                    zzhkVar = zzf;
                    zzd = zzhkVar;
                }
            }
        }
        return zzhkVar;
    }

    public static zzhk zzb() {
        zzhk zzhkVarZza = zze;
        if (zzhkVarZza == null) {
            synchronized (zzhk.class) {
                zzhkVarZza = zze;
                if (zzhkVarZza == null) {
                    zzhkVarZza = zzhv.zza(zzhk.class);
                    zze = zzhkVarZza;
                }
            }
        }
        return zzhkVarZza;
    }

    public final <ContainingType extends zzjg> zzhx.zzc<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzhx.zzc) this.zzg.get(new zza(containingtype, i));
    }

    zzhk() {
        this.zzg = new HashMap();
    }

    private zzhk(boolean z) {
        this.zzg = Collections.emptyMap();
    }
}
