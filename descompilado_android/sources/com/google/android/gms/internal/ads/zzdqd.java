package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdqd;
import com.google.android.gms.internal.ads.zzdqd.zza;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdqd<MessageType extends zzdqd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdom<MessageType, BuilderType> {
    private static Map<Object, zzdqd<?, ?>> zzhkf = new ConcurrentHashMap();
    protected zzdsx zzhkd = zzdsx.zzbbs();
    private int zzhke = -1;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static class zzc<T extends zzdqd<T, ?>> extends zzdoo<T> {
        private final T zzhka;

        public zzc(T t) {
            this.zzhka = t;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static final enum zzd {
        public static final int zzhkh = 1;
        public static final int zzhki = 2;
        public static final int zzhkj = 3;
        public static final int zzhkk = 4;
        public static final int zzhkl = 5;
        public static final int zzhkm = 6;
        public static final int zzhkn = 7;
        private static final /* synthetic */ int[] zzhko = {zzhkh, zzhki, zzhkj, zzhkk, zzhkl, zzhkm, zzhkn};
        public static final int zzhkp = 1;
        public static final int zzhkq = 2;
        private static final /* synthetic */ int[] zzhkr = {zzhkp, zzhkq};
        public static final int zzhks = 1;
        public static final int zzhkt = 2;
        private static final /* synthetic */ int[] zzhku = {zzhks, zzhkt};

        public static int[] zzazv() {
            return (int[]) zzhko.clone();
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static class zze<ContainingType extends zzdrn, Type> extends zzdpo<ContainingType, Type> {
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static abstract class zzb<MessageType extends zzb<MessageType, BuilderType>, BuilderType> extends zzdqd<MessageType, BuilderType> implements zzdrp {
        protected zzdpt<Object> zzhkg = zzdpt.zzayx();

        final zzdpt<Object> zzazu() {
            if (this.zzhkg.isImmutable()) {
                this.zzhkg = (zzdpt) this.zzhkg.clone();
            }
            return this.zzhkg;
        }
    }

    public String toString() {
        return zzdro.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zzhfc != 0) {
            return this.zzhfc;
        }
        this.zzhfc = zzdry.zzbba().zzax(this).hashCode(this);
        return this.zzhfc;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static abstract class zza<MessageType extends zzdqd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzdol<MessageType, BuilderType> {
        private final MessageType zzhka;
        protected MessageType zzhkb;
        private boolean zzhkc = false;

        protected zza(MessageType messagetype) {
            this.zzhka = messagetype;
            this.zzhkb = (MessageType) messagetype.zza(zzd.zzhkk, null, null);
        }

        protected final void zzazi() {
            if (this.zzhkc) {
                MessageType messagetype = (MessageType) this.zzhkb.zza(zzd.zzhkk, null, null);
                zza(messagetype, this.zzhkb);
                this.zzhkb = messagetype;
                this.zzhkc = false;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdrp
        public final boolean isInitialized() {
            return zzdqd.zza(this.zzhkb, false);
        }

        @Override // com.google.android.gms.internal.ads.zzdrm
        /* JADX INFO: renamed from: zzazj, reason: merged with bridge method [inline-methods] */
        public MessageType zzazl() {
            if (this.zzhkc) {
                return this.zzhkb;
            }
            MessageType messagetype = this.zzhkb;
            zzdry.zzbba().zzax(messagetype).zzak(messagetype);
            this.zzhkc = true;
            return this.zzhkb;
        }

        @Override // com.google.android.gms.internal.ads.zzdrm
        /* JADX INFO: renamed from: zzazk, reason: merged with bridge method [inline-methods] */
        public final MessageType zzazm() {
            MessageType messagetype = (MessageType) zzazl();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzdsv(messagetype);
        }

        @Override // com.google.android.gms.internal.ads.zzdol
        public final BuilderType zza(MessageType messagetype) {
            zzazi();
            zza(this.zzhkb, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zzdry.zzbba().zzax(messagetype).zzf(messagetype, messagetype2);
        }

        private final BuilderType zzb(byte[] bArr, int i, int i2, zzdpq zzdpqVar) throws zzdqn {
            zzazi();
            try {
                zzdry.zzbba().zzax(this.zzhkb).zza(this.zzhkb, bArr, 0, i2 + 0, new zzdos(zzdpqVar));
                return this;
            } catch (zzdqn e) {
                throw e;
            } catch (IOException e2) {
                throw new RuntimeException("Reading from byte array should not throw IOException.", e2);
            } catch (IndexOutOfBoundsException unused) {
                throw zzdqn.zzazx();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.google.android.gms.internal.ads.zzdol
        /* JADX INFO: renamed from: zzb, reason: merged with bridge method [inline-methods] */
        public final BuilderType zza(zzdpf zzdpfVar, zzdpq zzdpqVar) throws IOException {
            zzazi();
            try {
                zzdry.zzbba().zzax(this.zzhkb).zza(this.zzhkb, zzdpk.zza(zzdpfVar), zzdpqVar);
                return this;
            } catch (RuntimeException e) {
                if (e.getCause() instanceof IOException) {
                    throw ((IOException) e.getCause());
                }
                throw e;
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdol
        public final /* synthetic */ zzdol zza(byte[] bArr, int i, int i2, zzdpq zzdpqVar) throws zzdqn {
            return zzb(bArr, 0, i2, zzdpqVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdol
        /* JADX INFO: renamed from: zzaxa */
        public final /* synthetic */ zzdol clone() {
            return (zza) clone();
        }

        @Override // com.google.android.gms.internal.ads.zzdrp
        public final /* synthetic */ zzdrn zzazn() {
            return this.zzhka;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.ads.zzdol
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zza zzaVar = (zza) this.zzhka.zza(zzd.zzhkl, null, null);
            zzaVar.zza((zzdqd) zzazl());
            return zzaVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzdqd) zza(zzd.zzhkm, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzdry.zzbba().zzax(this).equals(this, (zzdqd) obj);
        }
        return false;
    }

    protected final <MessageType extends zzdqd<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> BuilderType zzazo() {
        return (BuilderType) zza(zzd.zzhkl, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.ads.zzdrp
    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    @Override // com.google.android.gms.internal.ads.zzdom
    final int zzaxc() {
        return this.zzhke;
    }

    @Override // com.google.android.gms.internal.ads.zzdom
    final void zzfj(int i) {
        this.zzhke = i;
    }

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final void zzb(zzdpm zzdpmVar) throws IOException {
        zzdry.zzbba().zzax(this).zza(this, zzdpn.zza(zzdpmVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final int zzazp() {
        if (this.zzhke == -1) {
            this.zzhke = zzdry.zzbba().zzax(this).zzau(this);
        }
        return this.zzhke;
    }

    static <T extends zzdqd<?, ?>> T zzf(Class<T> cls) {
        zzdqd<?, ?> zzdqdVar = zzhkf.get(cls);
        if (zzdqdVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzdqdVar = zzhkf.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzdqdVar == null) {
            zzdqdVar = (T) ((zzdqd) zzdta.zzj(cls)).zza(zzd.zzhkm, (Object) null, (Object) null);
            if (zzdqdVar == null) {
                throw new IllegalStateException();
            }
            zzhkf.put(cls, zzdqdVar);
        }
        return (T) zzdqdVar;
    }

    protected static <T extends zzdqd<?, ?>> void zza(Class<T> cls, T t) {
        zzhkf.put(cls, t);
    }

    protected static Object zza(zzdrn zzdrnVar, String str, Object[] objArr) {
        return new zzdsa(zzdrnVar, str, objArr);
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

    protected static final <T extends zzdqd<T, ?>> boolean zza(T t, boolean z) {
        byte bByteValue = ((Byte) t.zza(zzd.zzhkh, null, null)).byteValue();
        if (bByteValue == 1) {
            return true;
        }
        if (bByteValue == 0) {
            return false;
        }
        boolean zZzaw = zzdry.zzbba().zzax(t).zzaw(t);
        if (z) {
            t.zza(zzd.zzhki, zZzaw ? t : null, null);
        }
        return zZzaw;
    }

    protected static zzdqi zzazq() {
        return zzdqf.zzazw();
    }

    protected static zzdqi zza(zzdqi zzdqiVar) {
        int size = zzdqiVar.size();
        return zzdqiVar.zzfm(size == 0 ? 10 : size << 1);
    }

    protected static <E> zzdqk<E> zzazr() {
        return zzdsb.zzbbd();
    }

    protected static <E> zzdqk<E> zza(zzdqk<E> zzdqkVar) {
        int size = zzdqkVar.size();
        return zzdqkVar.zzfm(size == 0 ? 10 : size << 1);
    }

    private static <T extends zzdqd<T, ?>> T zza(T t, zzdpf zzdpfVar, zzdpq zzdpqVar) throws zzdqn {
        T t2 = (T) t.zza(zzd.zzhkk, null, null);
        try {
            zzdsc zzdscVarZzax = zzdry.zzbba().zzax(t2);
            zzdscVarZzax.zza(t2, zzdpk.zza(zzdpfVar), zzdpqVar);
            zzdscVarZzax.zzak(t2);
            return t2;
        } catch (IOException e) {
            if (e.getCause() instanceof zzdqn) {
                throw ((zzdqn) e.getCause());
            }
            throw new zzdqn(e.getMessage()).zzo(t2);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzdqn) {
                throw ((zzdqn) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzdqd<T, ?>> T zza(T t, byte[] bArr, int i, int i2, zzdpq zzdpqVar) throws zzdqn {
        T t2 = (T) t.zza(zzd.zzhkk, null, null);
        try {
            zzdsc zzdscVarZzax = zzdry.zzbba().zzax(t2);
            zzdscVarZzax.zza(t2, bArr, 0, i2, new zzdos(zzdpqVar));
            zzdscVarZzax.zzak(t2);
            if (t2.zzhfc == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzdqn) {
                throw ((zzdqn) e.getCause());
            }
            throw new zzdqn(e.getMessage()).zzo(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzdqn.zzazx().zzo(t2);
        }
    }

    private static <T extends zzdqd<T, ?>> T zzb(T t) throws zzdqn {
        if (t == null || t.isInitialized()) {
            return t;
        }
        throw new zzdqn(new zzdsv(t).getMessage()).zzo(t);
    }

    protected static <T extends zzdqd<T, ?>> T zza(T t, zzdot zzdotVar) throws zzdqn {
        return (T) zzb(zzb(zza(t, zzdotVar, zzdpq.zzayv())));
    }

    private static <T extends zzdqd<T, ?>> T zza(T t, zzdot zzdotVar, zzdpq zzdpqVar) throws zzdqn {
        try {
            zzdpf zzdpfVarZzaxk = zzdotVar.zzaxk();
            T t2 = (T) zza(t, zzdpfVarZzaxk, zzdpqVar);
            try {
                zzdpfVarZzaxk.zzfq(0);
                return t2;
            } catch (zzdqn e) {
                throw e.zzo(t2);
            }
        } catch (zzdqn e2) {
            throw e2;
        }
    }

    protected static <T extends zzdqd<T, ?>> T zza(T t, byte[] bArr) throws zzdqn {
        return (T) zzb(zza(t, bArr, 0, bArr.length, zzdpq.zzayv()));
    }

    protected static <T extends zzdqd<T, ?>> T zza(T t, byte[] bArr, zzdpq zzdpqVar) throws zzdqn {
        return (T) zzb(zza(t, bArr, 0, bArr.length, zzdpqVar));
    }

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final /* synthetic */ zzdrm zzazs() {
        zza zzaVar = (zza) zza(zzd.zzhkl, (Object) null, (Object) null);
        zzaVar.zza(this);
        return zzaVar;
    }

    @Override // com.google.android.gms.internal.ads.zzdrn
    public final /* synthetic */ zzdrm zzazt() {
        return (zza) zza(zzd.zzhkl, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.ads.zzdrp
    public final /* synthetic */ zzdrn zzazn() {
        return (zzdqd) zza(zzd.zzhkm, (Object) null, (Object) null);
    }
}
