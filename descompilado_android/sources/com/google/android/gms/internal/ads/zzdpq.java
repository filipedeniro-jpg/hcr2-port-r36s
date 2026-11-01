package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdpq {
    private static volatile boolean zzhgu = false;
    private static boolean zzhgv = true;
    private static volatile zzdpq zzhgx;
    private static volatile zzdpq zzhgy;
    private final Map<zza, zzdqd.zze<?, ?>> zzhha;
    private static final Class<?> zzhgw = zzayu();
    private static final zzdpq zzhgz = new zzdpq(true);

    private static Class<?> zzayu() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zza {
        private final int number;
        private final Object object;

        zza(Object obj, int i) {
            this.object = obj;
            this.number = i;
        }

        public final int hashCode() {
            return (System.identityHashCode(this.object) * 65535) + this.number;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            zza zzaVar = (zza) obj;
            return this.object == zzaVar.object && this.number == zzaVar.number;
        }
    }

    public static zzdpq zzayv() {
        zzdpq zzdpqVar = zzhgx;
        if (zzdpqVar == null) {
            synchronized (zzdpq.class) {
                zzdpqVar = zzhgx;
                if (zzdpqVar == null) {
                    zzdpqVar = zzhgz;
                    zzhgx = zzdpqVar;
                }
            }
        }
        return zzdpqVar;
    }

    public static zzdpq zzayw() {
        zzdpq zzdpqVarZze = zzhgy;
        if (zzdpqVarZze == null) {
            synchronized (zzdpq.class) {
                zzdpqVarZze = zzhgy;
                if (zzdpqVarZze == null) {
                    zzdpqVarZze = zzdqc.zze(zzdpq.class);
                    zzhgy = zzdpqVarZze;
                }
            }
        }
        return zzdpqVarZze;
    }

    public final <ContainingType extends zzdrn> zzdqd.zze<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzdqd.zze) this.zzhha.get(new zza(containingtype, i));
    }

    zzdpq() {
        this.zzhha = new HashMap();
    }

    private zzdpq(boolean z) {
        this.zzhha = Collections.emptyMap();
    }
}
