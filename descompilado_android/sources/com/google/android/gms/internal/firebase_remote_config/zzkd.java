package com.google.android.gms.internal.firebase_remote_config;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes2.dex */
final class zzkd {
    private static final boolean zzpv;
    private static final zzd zzxo;
    private static final boolean zzxp;
    private static final long zzxq;
    private static final long zzxr;
    private static final long zzxs;
    private static final long zzxt;
    private static final long zzxu;
    private static final long zzxv;
    private static final long zzxw;
    private static final long zzxx;
    private static final long zzxy;
    private static final long zzxz;
    private static final long zzya;
    private static final long zzyb;
    private static final long zzyc;
    private static final long zzyd;
    private static final int zzye;
    static final boolean zzyf;
    private static final Logger logger = Logger.getLogger(zzkd.class.getName());
    private static final Unsafe zzvq = zzjf();
    private static final Class<?> zzon = zzfu.zzev();
    private static final boolean zzxm = zzp(Long.TYPE);
    private static final boolean zzxn = zzp(Integer.TYPE);

    private zzkd() {
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final byte zzy(Object obj, long j) {
            if (zzkd.zzyf) {
                return zzkd.zzq(obj, j);
            }
            return zzkd.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzkd.zzyf) {
                zzkd.zza(obj, j, b);
            } else {
                zzkd.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzkd.zzyf) {
                return zzkd.zzs(obj, j);
            }
            return zzkd.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzkd.zzyf) {
                zzkd.zzb(obj, j, z);
            } else {
                zzkd.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final byte zzy(Object obj, long j) {
            return this.zzyi.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zze(Object obj, long j, byte b) {
            this.zzyi.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final boolean zzm(Object obj, long j) {
            return this.zzyi.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, boolean z) {
            this.zzyi.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final float zzn(Object obj, long j) {
            return this.zzyi.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, float f) {
            this.zzyi.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final double zzo(Object obj, long j) {
            return this.zzyi.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, double d) {
            this.zzyi.putDouble(obj, j, d);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final byte zzy(Object obj, long j) {
            if (zzkd.zzyf) {
                return zzkd.zzq(obj, j);
            }
            return zzkd.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzkd.zzyf) {
                zzkd.zza(obj, j, b);
            } else {
                zzkd.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzkd.zzyf) {
                return zzkd.zzs(obj, j);
            }
            return zzkd.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzkd.zzyf) {
                zzkd.zzb(obj, j, z);
            } else {
                zzkd.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.firebase_remote_config.zzkd.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    static boolean zzjd() {
        return zzpv;
    }

    static abstract class zzd {
        Unsafe zzyi;

        zzd(Unsafe unsafe) {
            this.zzyi = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzyi.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzyi.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzyi.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzyi.putLong(obj, j, j2);
        }
    }

    static boolean zzje() {
        return zzxp;
    }

    static <T> T zzm(Class<T> cls) {
        try {
            return (T) zzvq.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzn(Class<?> cls) {
        if (zzpv) {
            return zzxo.zzyi.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzo(Class<?> cls) {
        if (zzpv) {
            return zzxo.zzyi.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzxo.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzxo.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzxo.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzxo.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzxo.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzxo.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzxo.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzxo.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzxo.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzxo.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzxo.zzyi.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzxo.zzyi.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzxo.zzy(bArr, zzxq + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzxo.zze(bArr, zzxq + j, b);
    }

    static Unsafe zzjf() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzkf());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzjg() {
        Unsafe unsafe = zzvq;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzfu.zzeu()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzjh() {
        Unsafe unsafe = zzvq;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzji() == null) {
                return false;
            }
            if (zzfu.zzeu()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String strValueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(strValueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzp(Class<?> cls) {
        if (!zzfu.zzeu()) {
            return false;
        }
        try {
            Class<?> cls2 = zzon;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzji() {
        Field fieldZzb;
        if (zzfu.zzeu() && (fieldZzb = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return fieldZzb;
        }
        Field fieldZzb2 = zzb(Buffer.class, "address");
        if (fieldZzb2 == null || fieldZzb2.getType() != Long.TYPE) {
            return null;
        }
        return fieldZzb2;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) (((j ^ (-1)) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) ((j & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = ((((int) j) ^ (-1)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ (-1))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & ((255 << i) ^ (-1))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static {
        zzd zzdVar;
        zzd zzbVar = null;
        if (zzvq != null) {
            if (zzfu.zzeu()) {
                if (zzxm) {
                    zzbVar = new zzc(zzvq);
                } else if (zzxn) {
                    zzbVar = new zza(zzvq);
                }
            } else {
                zzbVar = new zzb(zzvq);
            }
        }
        zzxo = zzbVar;
        zzxp = zzjh();
        zzpv = zzjg();
        zzxq = zzn(byte[].class);
        zzxr = zzn(boolean[].class);
        zzxs = zzo(boolean[].class);
        zzxt = zzn(int[].class);
        zzxu = zzo(int[].class);
        zzxv = zzn(long[].class);
        zzxw = zzo(long[].class);
        zzxx = zzn(float[].class);
        zzxy = zzo(float[].class);
        zzxz = zzn(double[].class);
        zzya = zzo(double[].class);
        zzyb = zzn(Object[].class);
        zzyc = zzo(Object[].class);
        Field fieldZzji = zzji();
        zzyd = (fieldZzji == null || (zzdVar = zzxo) == null) ? -1L : zzdVar.zzyi.objectFieldOffset(fieldZzji);
        zzye = (int) (zzxq & 7);
        zzyf = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}
