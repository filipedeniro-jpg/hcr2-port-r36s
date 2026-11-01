package com.google.android.gms.internal.firebase_remote_config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class zzgx {
    private static volatile boolean zzqa = false;
    private static volatile zzgx zzqc;
    private final Map<zza, zzhi.zzc<?, ?>> zzqe;
    private static final Class<?> zzqb = zzgg();
    static final zzgx zzqd = new zzgx(true);

    private static Class<?> zzgg() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

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

    public static zzgx zzgh() {
        zzgx zzgxVarZzgf = zzqc;
        if (zzgxVarZzgf == null) {
            synchronized (zzgx.class) {
                zzgxVarZzgf = zzqc;
                if (zzgxVarZzgf == null) {
                    zzgxVarZzgf = zzgv.zzgf();
                    zzqc = zzgxVarZzgf;
                }
            }
        }
        return zzgxVarZzgf;
    }

    public final <ContainingType extends zziq> zzhi.zzc<ContainingType, ?> zza(ContainingType containingtype, int i) {
        return (zzhi.zzc) this.zzqe.get(new zza(containingtype, i));
    }

    zzgx() {
        this.zzqe = new HashMap();
    }

    private zzgx(boolean z) {
        this.zzqe = Collections.emptyMap();
    }
}
