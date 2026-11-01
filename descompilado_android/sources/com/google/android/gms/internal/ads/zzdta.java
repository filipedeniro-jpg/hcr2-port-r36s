package com.google.android.gms.internal.ads;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import libcore.io.Memory;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdta {
    private static final boolean zzhgj;
    private static final zzd zzhoi;
    private static final boolean zzhoj;
    static final long zzhok;
    private static final long zzhol;
    private static final long zzhom;
    private static final long zzhon;
    private static final long zzhoo;
    private static final long zzhop;
    private static final long zzhoq;
    private static final long zzhor;
    private static final long zzhos;
    private static final long zzhot;
    private static final long zzhou;
    private static final long zzhov;
    private static final long zzhow;
    private static final long zzhox;
    private static final int zzhoy;
    static final boolean zzhoz;
    private static final Logger logger = Logger.getLogger(zzdta.class.getName());
    private static final Unsafe zzgpv = zzbbx();
    private static final Class<?> zzhff = zzdoq.zzaxh();
    private static final boolean zzhog = zzm(Long.TYPE);
    private static final boolean zzhoh = zzm(Integer.TYPE);

    private zzdta() {
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(long j, byte b) {
            this.zzhpc.putByte(j, b);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final byte zzy(Object obj, long j) {
            return this.zzhpc.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zze(Object obj, long j, byte b) {
            this.zzhpc.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final boolean zzm(Object obj, long j) {
            return this.zzhpc.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, boolean z) {
            this.zzhpc.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final float zzn(Object obj, long j) {
            return this.zzhpc.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, float f) {
            this.zzhpc.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final double zzo(Object obj, long j) {
            return this.zzhpc.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, double d) {
            this.zzhpc.putDouble(obj, j, d);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            this.zzhpc.copyMemory(bArr, zzdta.zzhok + j, (Object) null, j2, j3);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(long j, byte b) {
            Memory.pokeByte(j, b);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final byte zzy(Object obj, long j) {
            if (zzdta.zzhoz) {
                return zzdta.zzq(obj, j);
            }
            return zzdta.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzdta.zzhoz) {
                zzdta.zza(obj, j, b);
            } else {
                zzdta.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzdta.zzhoz) {
                return zzdta.zzs(obj, j);
            }
            return zzdta.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzdta.zzhoz) {
                zzdta.zzb(obj, j, z);
            } else {
                zzdta.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray(j2, bArr, (int) j, (int) j3);
        }
    }

    static boolean zzbbv() {
        return zzhgj;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static abstract class zzd {
        Unsafe zzhpc;

        zzd(Unsafe unsafe) {
            this.zzhpc = unsafe;
        }

        public abstract void zza(long j, byte b);

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zza(byte[] bArr, long j, long j2, long j3);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zzhpc.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zzhpc.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zzhpc.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzhpc.putLong(obj, j, j2);
        }
    }

    static boolean zzbbw() {
        return zzhoj;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(long j, byte b) {
            Memory.pokeByte((int) (j & (-1)), b);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final byte zzy(Object obj, long j) {
            if (zzdta.zzhoz) {
                return zzdta.zzq(obj, j);
            }
            return zzdta.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zze(Object obj, long j, byte b) {
            if (zzdta.zzhoz) {
                zzdta.zza(obj, j, b);
            } else {
                zzdta.zzb(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzdta.zzhoz) {
                return zzdta.zzs(obj, j);
            }
            return zzdta.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, boolean z) {
            if (zzdta.zzhoz) {
                zzdta.zzb(obj, j, z);
            } else {
                zzdta.zzc(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }

        @Override // com.google.android.gms.internal.ads.zzdta.zzd
        public final void zza(byte[] bArr, long j, long j2, long j3) {
            Memory.pokeByteArray((int) (j2 & (-1)), bArr, (int) j, (int) j3);
        }
    }

    static <T> T zzj(Class<T> cls) {
        try {
            return (T) zzgpv.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzk(Class<?> cls) {
        if (zzhgj) {
            return zzhoi.zzhpc.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzl(Class<?> cls) {
        if (zzhgj) {
            return zzhoi.zzhpc.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzk(Object obj, long j) {
        return zzhoi.zzk(obj, j);
    }

    static void zzb(Object obj, long j, int i) {
        zzhoi.zzb(obj, j, i);
    }

    static long zzl(Object obj, long j) {
        return zzhoi.zzl(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzhoi.zza(obj, j, j2);
    }

    static boolean zzm(Object obj, long j) {
        return zzhoi.zzm(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzhoi.zza(obj, j, z);
    }

    static float zzn(Object obj, long j) {
        return zzhoi.zzn(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzhoi.zza(obj, j, f);
    }

    static double zzo(Object obj, long j) {
        return zzhoi.zzo(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzhoi.zza(obj, j, d);
    }

    static Object zzp(Object obj, long j) {
        return zzhoi.zzhpc.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzhoi.zzhpc.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzhoi.zzy(bArr, zzhok + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzhoi.zze(bArr, zzhok + j, b);
    }

    static void zza(byte[] bArr, long j, long j2, long j3) {
        zzhoi.zza(bArr, j, j2, j3);
    }

    static void zza(long j, byte b) {
        zzhoi.zza(j, b);
    }

    static long zzn(ByteBuffer byteBuffer) {
        return zzhoi.zzl(byteBuffer, zzhox);
    }

    static Unsafe zzbbx() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzdtc());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzbby() {
        Unsafe unsafe = zzgpv;
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
            if (zzdoq.zzaxg()) {
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

    private static boolean zzbbz() {
        Unsafe unsafe = zzgpv;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzbca() == null) {
                return false;
            }
            if (zzdoq.zzaxg()) {
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

    private static boolean zzm(Class<?> cls) {
        if (!zzdoq.zzaxg()) {
            return false;
        }
        try {
            Class<?> cls2 = zzhff;
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

    private static Field zzbca() {
        Field fieldZzb;
        if (zzdoq.zzaxg() && (fieldZzb = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
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
        if (zzgpv != null) {
            if (zzdoq.zzaxg()) {
                if (zzhog) {
                    zzbVar = new zzc(zzgpv);
                } else if (zzhoh) {
                    zzbVar = new zza(zzgpv);
                }
            } else {
                zzbVar = new zzb(zzgpv);
            }
        }
        zzhoi = zzbVar;
        zzhoj = zzbbz();
        zzhgj = zzbby();
        zzhok = zzk(byte[].class);
        zzhol = zzk(boolean[].class);
        zzhom = zzl(boolean[].class);
        zzhon = zzk(int[].class);
        zzhoo = zzl(int[].class);
        zzhop = zzk(long[].class);
        zzhoq = zzl(long[].class);
        zzhor = zzk(float[].class);
        zzhos = zzl(float[].class);
        zzhot = zzk(double[].class);
        zzhou = zzl(double[].class);
        zzhov = zzk(Object[].class);
        zzhow = zzl(Object[].class);
        Field fieldZzbca = zzbca();
        zzhox = (fieldZzbca == null || (zzdVar = zzhoi) == null) ? -1L : zzdVar.zzhpc.objectFieldOffset(fieldZzbca);
        zzhoy = (int) (zzhok & 7);
        zzhoz = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}
