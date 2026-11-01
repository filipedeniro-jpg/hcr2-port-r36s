package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.gms.internal.firebase_remote_config.zzhi;
import com.google.android.gms.internal.firebase_remote_config.zzhi.zzb;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public abstract class zzhi<MessageType extends zzhi<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfq<MessageType, BuilderType> {
    private static Map<Object, zzhi<?, ?>> zzte = new ConcurrentHashMap();
    protected zzka zztc = zzka.zzja();
    private int zztd = -1;

    public static class zza<T extends zzhi<T, ?>> extends zzfs<T> {
        private final T zztg;

        public zza(T t) {
            this.zztg = t;
        }
    }

    public static class zzc<ContainingType extends zziq, Type> extends zzgw<ContainingType, Type> {
    }

    public static final enum zze {
        public static final int zztm = 1;
        public static final int zztn = 2;
        public static final int zzto = 3;
        public static final int zztp = 4;
        public static final int zztq = 5;
        public static final int zztr = 6;
        public static final int zzts = 7;
        private static final /* synthetic */ int[] zztt = {zztm, zztn, zzto, zztp, zztq, zztr, zzts};
        public static final int zztu = 1;
        public static final int zztv = 2;
        private static final /* synthetic */ int[] zztw = {zztu, zztv};
        public static final int zztx = 1;
        public static final int zzty = 2;
        private static final /* synthetic */ int[] zztz = {zztx, zzty};

        public static int[] zzhd() {
            return (int[]) zztt.clone();
        }
    }

    protected abstract Object zzb(int i, Object obj, Object obj2);

    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzhi<MessageType, BuilderType> implements zzis {
        protected zzha<Object> zztj = zzha.zzgi();

        final zzha<Object> zzhc() {
            if (this.zztj.isImmutable()) {
                this.zztj = (zzha) this.zztj.clone();
            }
            return this.zztj;
        }
    }

    public String toString() {
        return zzir.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzoj != 0) {
            return this.zzoj;
        }
        this.zzoj = zzjb.zzii().zzz(this).hashCode(this);
        return this.zzoj;
    }

    public static abstract class zzb<MessageType extends zzhi<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfp<MessageType, BuilderType> {
        private final MessageType zztg;
        private MessageType zzth;
        private boolean zzti = false;

        protected zzb(MessageType messagetype) {
            this.zztg = messagetype;
            this.zzth = (MessageType) messagetype.zzb(zze.zztp, null, null);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzis
        public final boolean isInitialized() {
            return zzhi.zza(this.zzth, false);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzip
        /* JADX INFO: renamed from: zzgy, reason: merged with bridge method [inline-methods] */
        public MessageType zzha() {
            if (this.zzti) {
                return this.zzth;
            }
            this.zzth.zzes();
            this.zzti = true;
            return this.zzth;
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzip
        /* JADX INFO: renamed from: zzgz, reason: merged with bridge method [inline-methods] */
        public final MessageType zzhb() {
            MessageType messagetype = (MessageType) zzha();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjy(messagetype);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzfp
        /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
        public final BuilderType zza(MessageType messagetype) {
            if (this.zzti) {
                MessageType messagetype2 = (MessageType) this.zzth.zzb(zze.zztp, null, null);
                zza(messagetype2, this.zzth);
                this.zzth = messagetype2;
                this.zzti = false;
            }
            zza(this.zzth, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzjb.zzii().zzz(messagetype).zze(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzfp
        /* JADX INFO: renamed from: zzeo */
        public final /* synthetic */ zzfp clone() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzis
        public final /* synthetic */ zziq zzgw() {
            return this.zztg;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.firebase_remote_config.zzfp
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.zztg.zzb(zze.zztq, null, null);
            zzbVar.zza((zzhi) zzha());
            return zzbVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzhi) zzb(zze.zztr, null, null)).getClass().isInstance(obj)) {
            return zzjb.zzii().zzz(this).equals(this, (zzhi) obj);
        }
        return false;
    }

    protected final void zzes() {
        zzjb.zzii().zzz(this).zzm(this);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzis
    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfq
    final int zzeq() {
        return this.zztd;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzfq
    final void zzr(int i) {
        this.zztd = i;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zziq
    public final void zzb(zzgs zzgsVar) throws IOException {
        zzjb.zzii().zzk(getClass()).zza(this, zzgu.zza(zzgsVar));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zziq
    public final int zzgs() {
        if (this.zztd == -1) {
            this.zztd = zzjb.zzii().zzz(this).zzw(this);
        }
        return this.zztd;
    }

    static <T extends zzhi<?, ?>> T zzg(Class<T> cls) {
        zzhi<?, ?> zzhiVar = zzte.get(cls);
        if (zzhiVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzhiVar = zzte.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzhiVar == null) {
            zzhiVar = (T) ((zzhi) zzkd.zzm(cls)).zzb(zze.zztr, null, null);
            if (zzhiVar == null) {
                throw new IllegalStateException();
            }
            zzte.put(cls, zzhiVar);
        }
        return (T) zzhiVar;
    }

    protected static <T extends zzhi<?, ?>> void zza(Class<T> cls, T t) {
        zzte.put(cls, t);
    }

    protected static Object zza(zziq zziqVar, String str, Object[] objArr) {
        return new zzjd(zziqVar, str, objArr);
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

    protected static final <T extends zzhi<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zzb(zze.zztm, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzy = zzjb.zzii().zzz(t).zzy(t);
        if (z) {
            t.zzb(zze.zztn, zZzy ? t : null, null);
        }
        return zZzy;
    }

    protected static <E> zzhn<E> zzgt() {
        return zzje.zzil();
    }

    private static <T extends zzhi<T, ?>> T zza(T t, zzgj zzgjVar, zzgx zzgxVar) throws zzhq {
        T t2 = (T) t.zzb(zze.zztp, null, null);
        try {
            zzjb.zzii().zzz(t2).zza(t2, zzgq.zza(zzgjVar), zzgxVar);
            t2.zzes();
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzhq) {
                throw ((zzhq) e.getCause());
            }
            throw new zzhq(e.getMessage()).zzg(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzhq) {
                throw ((zzhq) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzhi<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzgx zzgxVar) throws zzhq {
        T t2 = (T) t.zzb(zze.zztp, null, null);
        try {
            zzjb.zzii().zzz(t2).zza(t2, bArr, 0, i2, new zzfw(zzgxVar));
            t2.zzes();
            if (t2.zzoj == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzhq) {
                throw ((zzhq) e.getCause());
            }
            throw new zzhq(e.getMessage()).zzg(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzhq.zzhe().zzg(t2);
        }
    }

    private static <T extends zzhi<T, ?>> T zza(T t) throws zzhq {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzhq(new zzjy(t).getMessage()).zzg(t);
    }

    protected static <T extends zzhi<T, ?>> T zza(T t, byte[] bArr) throws zzhq {
        return (T) zza(zza(t, bArr, 0, bArr.length, zzgx.zzgh()));
    }

    protected static <T extends zzhi<T, ?>> T zza(T t, InputStream inputStream) throws zzhq {
        zzgj zzgoVar;
        if (inputStream == null) {
            byte[] bArr = zzhm.zzua;
            zzgoVar = zzgj.zza(bArr, 0, bArr.length, false);
        } else {
            zzgoVar = new zzgo(inputStream);
        }
        return (T) zza(zza(t, zzgoVar, zzgx.zzgh()));
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zziq
    public final /* synthetic */ zzip zzgu() {
        zzb zzbVar = (zzb) zzb(zze.zztq, null, null);
        zzbVar.zza(this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zziq
    public final /* synthetic */ zzip zzgv() {
        return (zzb) zzb(zze.zztq, null, null);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzis
    public final /* synthetic */ zziq zzgw() {
        return (zzhi) zzb(zze.zztr, null, null);
    }
}
