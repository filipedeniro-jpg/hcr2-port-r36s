package com.google.android.gms.internal.firebase_auth;

import com.google.android.gms.internal.firebase_auth.zzhx;
import com.google.android.gms.internal.firebase_auth.zzhx.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzhx<MessageType extends zzhx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzge<MessageType, BuilderType> {
    private static Map<Object, zzhx<?, ?>> zzd = new ConcurrentHashMap();
    protected zzkr zzb = zzkr.zza();
    private int zzc = -1;

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static class zzc<ContainingType extends zzjg, Type> extends zzhl<ContainingType, Type> {
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzhx<MessageType, BuilderType> implements zzji {
        protected zzhq<Object> zzc = zzhq.zza();
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static final enum zze {
        public static final int zza = 1;
        public static final int zzb = 2;
        public static final int zzc = 3;
        public static final int zzd = 4;
        public static final int zze = 5;
        public static final int zzf = 6;
        public static final int zzg = 7;
        private static final /* synthetic */ int[] zzl = {zza, zzb, zzc, zzd, zze, zzf, zzg};
        public static final int zzh = 1;
        public static final int zzi = 2;
        private static final /* synthetic */ int[] zzm = {zzh, zzi};
        public static final int zzj = 1;
        public static final int zzk = 2;
        private static final /* synthetic */ int[] zzn = {zzj, zzk};

        public static int[] zza() {
            return (int[]) zzl.clone();
        }
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static class zza<T extends zzhx<T, ?>> extends zzgj<T> {
        private final T zza;

        public zza(T t) {
            this.zza = t;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjq
        public final /* synthetic */ Object zza(zzgy zzgyVar, zzhk zzhkVar) throws zzig {
            return zzhx.zza(this.zza, zzgyVar, zzhkVar);
        }
    }

    public String toString() {
        return zzjl.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zza != 0) {
            return this.zza;
        }
        this.zza = zzjs.zza().zza(this).zza(this);
        return this.zza;
    }

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    public static abstract class zzb<MessageType extends zzhx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzgh<MessageType, BuilderType> {
        protected MessageType zza;
        private final MessageType zzb;
        private boolean zzc = false;

        protected zzb(MessageType messagetype) {
            this.zzb = messagetype;
            this.zza = (MessageType) messagetype.zza(zze.zzd, null, null);
        }

        protected final void zzb() {
            if (this.zzc) {
                MessageType messagetype = (MessageType) this.zza.zza(zze.zzd, null, null);
                zza(messagetype, this.zza);
                this.zza = messagetype;
                this.zzc = false;
            }
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzji
        public final boolean zzaa() {
            return zzhx.zza(this.zza, false);
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjj
        /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
        public MessageType zze() {
            if (this.zzc) {
                return this.zza;
            }
            MessageType messagetype = this.zza;
            zzjs.zza().zza(messagetype).zzc(messagetype);
            this.zzc = true;
            return this.zza;
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzjj
        /* JADX INFO: renamed from: zzd, reason: merged with bridge method [inline-methods] */
        public final MessageType zzf() {
            MessageType messagetype = (MessageType) zze();
            if (messagetype.zzaa()) {
                return messagetype;
            }
            throw new zzkq(messagetype);
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzgh
        public final BuilderType zza(MessageType messagetype) {
            zzb();
            zza(this.zza, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjs.zza().zza(messagetype).zzb(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzgh
        /* JADX INFO: renamed from: zza */
        public final /* synthetic */ zzgh clone() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.firebase_auth.zzji
        public final /* synthetic */ zzjg zzag() {
            return this.zzb;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_auth.zzgh
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.zzb.zza(zze.zze, null, null);
            zzbVar.zza((zzhx) zze());
            return zzbVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzhx) zza(zze.zzf, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzjs.zza().zza(this).zza(this, (zzhx) obj);
        }
        return false;
    }

    protected final <MessageType extends zzhx<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> BuilderType zzz() {
        return (BuilderType) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzji
    public final boolean zzaa() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzge
    final int zzy() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzge
    final void zzb(int i) {
        this.zzc = i;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjg
    public final void zza(zzhh zzhhVar) throws IOException {
        zzjs.zza().zza(this).zza(this, zzhj.zza(zzhhVar));
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjg
    public final int zzab() {
        if (this.zzc == -1) {
            this.zzc = zzjs.zza().zza(this).zzb(this);
        }
        return this.zzc;
    }

    static <T extends zzhx<?, ?>> T zza(Class<T> cls) {
        zzhx<?, ?> zzhxVar = zzd.get(cls);
        if (zzhxVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzhxVar = zzd.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzhxVar == null) {
            zzhxVar = (T) ((zzhx) zzky.zza(cls)).zza(zze.zzf, (Object) null, (Object) null);
            if (zzhxVar == null) {
                throw new IllegalStateException();
            }
            zzd.put(cls, zzhxVar);
        }
        return (T) zzhxVar;
    }

    protected static <T extends zzhx<?, ?>> void zza(Class<T> cls, T t) {
        zzd.put(cls, t);
    }

    protected static Object zza(zzjg zzjgVar, String str, Object[] objArr) {
        return new zzju(zzjgVar, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static final <T extends zzhx<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zze.zza, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzd = zzjs.zza().zza(t).zzd(t);
        if (z) {
            t.zza(zze.zzb, zZzd ? t : null, null);
        }
        return zZzd;
    }

    protected static zzif zzac() {
        return zzhz.zzd();
    }

    protected static <E> zzih<E> zzad() {
        return zzjv.zzd();
    }

    static <T extends zzhx<T, ?>> T zza(T t, zzgy zzgyVar, zzhk zzhkVar) throws zzig {
        T t2 = (T) t.zza(zze.zzd, null, null);
        try {
            zzjw zzjwVarZza = zzjs.zza().zza(t2);
            zzjwVarZza.zza(t2, zzhf.zza(zzgyVar), zzhkVar);
            zzjwVarZza.zzc(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzig) {
                throw ((zzig) e.getCause());
            }
            throw new zzig(e.getMessage()).zza(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzig) {
                throw ((zzig) e2.getCause());
            }
            throw e2;
        }
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjg
    public final /* synthetic */ zzjj zzae() {
        zzb zzbVar = (zzb) zza(zze.zze, (Object) null, (Object) null);
        zzbVar.zza(this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzjg
    public final /* synthetic */ zzjj zzaf() {
        return (zzb) zza(zze.zze, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.firebase_auth.zzji
    public final /* synthetic */ zzjg zzag() {
        return (zzhx) zza(zze.zzf, (Object) null, (Object) null);
    }
}
