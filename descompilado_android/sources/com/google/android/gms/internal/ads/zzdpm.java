package com.google.android.gms.internal.ads;

import com.google.android.gms.games.Notifications;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdpm extends zzdou {
    private static final Logger logger = Logger.getLogger(zzdpm.class.getName());
    private static final boolean zzhgj = zzdta.zzbbv();
    zzdpn zzhgk;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zza extends zzb {
        private final ByteBuffer zzhgl;
        private int zzhgm;

        zza(ByteBuffer byteBuffer) {
            super(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining());
            this.zzhgl = byteBuffer;
            this.zzhgm = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.ads.zzdpm.zzb, com.google.android.gms.internal.ads.zzdpm
        public final void flush() {
            this.zzhgl.position(this.zzhgm + zzays());
        }
    }

    public static zzdpm zzaa(byte[] bArr) {
        return new zzb(bArr, 0, bArr.length);
    }

    public static int zzbi(boolean z) {
        return 1;
    }

    public static int zzc(double d) {
        return 8;
    }

    public static int zzfe(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int zzfg(long j) {
        return 8;
    }

    public static int zzfh(long j) {
        return 8;
    }

    private static long zzfi(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzg(float f) {
        return 4;
    }

    public static int zzgg(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzgi(int i) {
        return 4;
    }

    public static int zzgj(int i) {
        return 4;
    }

    private static int zzgl(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zza(int i, zzdot zzdotVar) throws IOException;

    public abstract void zza(int i, zzdrn zzdrnVar) throws IOException;

    abstract void zza(int i, zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException;

    abstract void zza(zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException;

    public abstract void zzaa(int i, int i2) throws IOException;

    public abstract void zzab(int i, int i2) throws IOException;

    public abstract void zzad(int i, int i2) throws IOException;

    public abstract int zzayp();

    public abstract void zzb(int i, zzdot zzdotVar) throws IOException;

    public abstract void zzb(int i, zzdrn zzdrnVar) throws IOException;

    public abstract void zzcz(zzdot zzdotVar) throws IOException;

    public abstract void zzd(byte b) throws IOException;

    public abstract void zzfa(long j) throws IOException;

    public abstract void zzfc(long j) throws IOException;

    public abstract void zzg(int i, long j) throws IOException;

    public abstract void zzg(int i, String str) throws IOException;

    public abstract void zzga(int i) throws IOException;

    public abstract void zzgb(int i) throws IOException;

    public abstract void zzgd(int i) throws IOException;

    public abstract void zzh(int i, boolean z) throws IOException;

    public abstract void zzhi(String str) throws IOException;

    public abstract void zzi(int i, long j) throws IOException;

    public abstract void zzj(zzdrn zzdrnVar) throws IOException;

    abstract void zzk(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzz(int i, int i2) throws IOException;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    public static class zzd extends IOException {
        zzd() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzd(String str) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "));
        }

        zzd(Throwable th) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
        }

        /* JADX WARN: Illegal instructions before constructor call */
        zzd(String str, Throwable th) {
            String strValueOf = String.valueOf(str);
            super(strValueOf.length() != 0 ? "CodedOutputStream was writing to a flat byte array and ran out of space.: ".concat(strValueOf) : new String("CodedOutputStream was writing to a flat byte array and ran out of space.: "), th);
        }
    }

    public static zzdpm zzm(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            return new zza(byteBuffer);
        }
        if (byteBuffer.isDirect() && !byteBuffer.isReadOnly()) {
            if (zzdta.zzbbw()) {
                return new zze(byteBuffer);
            }
            return new zzc(byteBuffer);
        }
        throw new IllegalArgumentException("ByteBuffer is read-only");
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zzc extends zzdpm {
        private final ByteBuffer zzakh;
        private final int zzhgm;
        private final ByteBuffer zzhgn;

        zzc(ByteBuffer byteBuffer) {
            super();
            this.zzhgn = byteBuffer;
            this.zzakh = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzhgm = byteBuffer.position();
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzz(int i, int i2) throws IOException {
            zzgb((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzaa(int i, int i2) throws IOException {
            zzz(i, 0);
            zzga(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzab(int i, int i2) throws IOException {
            zzz(i, 0);
            zzgb(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzad(int i, int i2) throws IOException {
            zzz(i, 5);
            zzgd(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, long j) throws IOException {
            zzz(i, 0);
            zzfa(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzi(int i, long j) throws IOException {
            zzz(i, 1);
            zzfc(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzh(int i, boolean z) throws IOException {
            zzz(i, 0);
            zzd(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, String str) throws IOException {
            zzz(i, 2);
            zzhi(str);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdot zzdotVar) throws IOException {
            zzz(i, 2);
            zzcz(zzdotVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdrn zzdrnVar) throws IOException {
            zzz(i, 2);
            zzj(zzdrnVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(int i, zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzz(i, 2);
            zza(zzdrnVar, zzdscVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdrn zzdrnVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdrnVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdot zzdotVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdotVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzj(zzdrn zzdrnVar) throws IOException {
            zzgb(zzdrnVar.zzazp());
            zzdrnVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzdom zzdomVar = (zzdom) zzdrnVar;
            int iZzaxc = zzdomVar.zzaxc();
            if (iZzaxc == -1) {
                iZzaxc = zzdscVar.zzau(zzdomVar);
                zzdomVar.zzfj(iZzaxc);
            }
            zzgb(iZzaxc);
            zzdscVar.zza(zzdrnVar, this.zzhgk);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzd(byte b) throws IOException {
            try {
                this.zzakh.put(b);
            } catch (BufferOverflowException e) {
                throw new zzd(e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzcz(zzdot zzdotVar) throws IOException {
            zzgb(zzdotVar.size());
            zzdotVar.zza(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzk(byte[] bArr, int i, int i2) throws IOException {
            zzgb(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzga(int i) throws IOException {
            if (i >= 0) {
                zzgb(i);
            } else {
                zzfa(i);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgb(int i) throws IOException {
            while ((i & (-128)) != 0) {
                try {
                    this.zzakh.put((byte) ((i & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    i >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzd(e);
                }
            }
            this.zzakh.put((byte) i);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgd(int i) throws IOException {
            try {
                this.zzakh.putInt(i);
            } catch (BufferOverflowException e) {
                throw new zzd(e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfa(long j) throws IOException {
            while (((-128) & j) != 0) {
                try {
                    this.zzakh.put((byte) ((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    j >>>= 7;
                } catch (BufferOverflowException e) {
                    throw new zzd(e);
                }
            }
            this.zzakh.put((byte) j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfc(long j) throws IOException {
            try {
                this.zzakh.putLong(j);
            } catch (BufferOverflowException e) {
                throw new zzd(e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                this.zzakh.put(bArr, i, i2);
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(e);
            } catch (BufferOverflowException e2) {
                throw new zzd(e2);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdou
        public final void zzi(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzhi(String str) throws IOException {
            int iPosition = this.zzakh.position();
            try {
                int iZzgg = zzgg(str.length() * 3);
                int iZzgg2 = zzgg(str.length());
                if (iZzgg2 == iZzgg) {
                    int iPosition2 = this.zzakh.position() + iZzgg2;
                    this.zzakh.position(iPosition2);
                    zzhk(str);
                    int iPosition3 = this.zzakh.position();
                    this.zzakh.position(iPosition);
                    zzgb(iPosition3 - iPosition2);
                    this.zzakh.position(iPosition3);
                    return;
                }
                zzgb(zzdtd.zza(str));
                zzhk(str);
            } catch (zzdtg e) {
                this.zzakh.position(iPosition);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzd(e2);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void flush() {
            this.zzhgn.position(this.zzakh.position());
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final int zzayp() {
            return this.zzakh.remaining();
        }

        private final void zzhk(String str) throws IOException {
            try {
                zzdtd.zza(str, this.zzakh);
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(e);
            }
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static final class zze extends zzdpm {
        private final ByteBuffer zzakh;
        private long zzams;
        private final ByteBuffer zzhgn;
        private final long zzhgo;
        private final long zzhgp;
        private final long zzhgq;
        private final long zzhgr;

        zze(ByteBuffer byteBuffer) {
            super();
            this.zzhgn = byteBuffer;
            this.zzakh = byteBuffer.duplicate().order(ByteOrder.LITTLE_ENDIAN);
            this.zzhgo = zzdta.zzn(byteBuffer);
            this.zzhgp = this.zzhgo + ((long) byteBuffer.position());
            this.zzhgq = this.zzhgo + ((long) byteBuffer.limit());
            this.zzhgr = this.zzhgq - 10;
            this.zzams = this.zzhgp;
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzz(int i, int i2) throws IOException {
            zzgb((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzaa(int i, int i2) throws IOException {
            zzz(i, 0);
            zzga(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzab(int i, int i2) throws IOException {
            zzz(i, 0);
            zzgb(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzad(int i, int i2) throws IOException {
            zzz(i, 5);
            zzgd(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, long j) throws IOException {
            zzz(i, 0);
            zzfa(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzi(int i, long j) throws IOException {
            zzz(i, 1);
            zzfc(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzh(int i, boolean z) throws IOException {
            zzz(i, 0);
            zzd(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, String str) throws IOException {
            zzz(i, 2);
            zzhi(str);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdot zzdotVar) throws IOException {
            zzz(i, 2);
            zzcz(zzdotVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdrn zzdrnVar) throws IOException {
            zzz(i, 2);
            zzj(zzdrnVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(int i, zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzz(i, 2);
            zza(zzdrnVar, zzdscVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdrn zzdrnVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdrnVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdot zzdotVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdotVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzj(zzdrn zzdrnVar) throws IOException {
            zzgb(zzdrnVar.zzazp());
            zzdrnVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzdom zzdomVar = (zzdom) zzdrnVar;
            int iZzaxc = zzdomVar.zzaxc();
            if (iZzaxc == -1) {
                iZzaxc = zzdscVar.zzau(zzdomVar);
                zzdomVar.zzfj(iZzaxc);
            }
            zzgb(iZzaxc);
            zzdscVar.zza(zzdrnVar, this.zzhgk);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzd(byte b) throws IOException {
            long j = this.zzams;
            if (j >= this.zzhgq) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(j), Long.valueOf(this.zzhgq), 1));
            }
            this.zzams = 1 + j;
            zzdta.zza(j, b);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzcz(zzdot zzdotVar) throws IOException {
            zzgb(zzdotVar.size());
            zzdotVar.zza(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzk(byte[] bArr, int i, int i2) throws IOException {
            zzgb(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzga(int i) throws IOException {
            if (i >= 0) {
                zzgb(i);
            } else {
                zzfa(i);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgb(int i) throws IOException {
            if (this.zzams <= this.zzhgr) {
                while ((i & (-128)) != 0) {
                    long j = this.zzams;
                    this.zzams = j + 1;
                    zzdta.zza(j, (byte) ((i & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    i >>>= 7;
                }
                long j2 = this.zzams;
                this.zzams = 1 + j2;
                zzdta.zza(j2, (byte) i);
                return;
            }
            while (true) {
                long j3 = this.zzams;
                if (j3 >= this.zzhgq) {
                    throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(j3), Long.valueOf(this.zzhgq), 1));
                }
                if ((i & (-128)) == 0) {
                    this.zzams = 1 + j3;
                    zzdta.zza(j3, (byte) i);
                    return;
                } else {
                    this.zzams = j3 + 1;
                    zzdta.zza(j3, (byte) ((i & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    i >>>= 7;
                }
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgd(int i) throws IOException {
            this.zzakh.putInt((int) (this.zzams - this.zzhgo), i);
            this.zzams += 4;
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfa(long j) throws IOException {
            if (this.zzams <= this.zzhgr) {
                while ((j & (-128)) != 0) {
                    long j2 = this.zzams;
                    this.zzams = j2 + 1;
                    zzdta.zza(j2, (byte) ((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    j >>>= 7;
                }
                long j3 = this.zzams;
                this.zzams = 1 + j3;
                zzdta.zza(j3, (byte) j);
                return;
            }
            while (true) {
                long j4 = this.zzams;
                if (j4 >= this.zzhgq) {
                    throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(j4), Long.valueOf(this.zzhgq), 1));
                }
                if ((j & (-128)) == 0) {
                    this.zzams = 1 + j4;
                    zzdta.zza(j4, (byte) j);
                    return;
                } else {
                    this.zzams = j4 + 1;
                    zzdta.zza(j4, (byte) ((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    j >>>= 7;
                }
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfc(long j) throws IOException {
            this.zzakh.putLong((int) (this.zzams - this.zzhgo), j);
            this.zzams += 8;
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            if (bArr != null && i >= 0 && i2 >= 0 && bArr.length - i2 >= i) {
                long j = i2;
                long j2 = this.zzhgq - j;
                long j3 = this.zzams;
                if (j2 >= j3) {
                    zzdta.zza(bArr, i, j3, j);
                    this.zzams += j;
                    return;
                }
            }
            if (bArr == null) {
                throw new NullPointerException("value");
            }
            throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Long.valueOf(this.zzams), Long.valueOf(this.zzhgq), Integer.valueOf(i2)));
        }

        @Override // com.google.android.gms.internal.ads.zzdou
        public final void zzi(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzhi(String str) throws IOException {
            long j = this.zzams;
            try {
                int iZzgg = zzgg(str.length() * 3);
                int iZzgg2 = zzgg(str.length());
                if (iZzgg2 == iZzgg) {
                    int i = ((int) (this.zzams - this.zzhgo)) + iZzgg2;
                    this.zzakh.position(i);
                    zzdtd.zza(str, this.zzakh);
                    int iPosition = this.zzakh.position() - i;
                    zzgb(iPosition);
                    this.zzams += (long) iPosition;
                    return;
                }
                int iZza = zzdtd.zza(str);
                zzgb(iZza);
                zzfj(this.zzams);
                zzdtd.zza(str, this.zzakh);
                this.zzams += (long) iZza;
            } catch (zzdtg e) {
                this.zzams = j;
                zzfj(this.zzams);
                zza(str, e);
            } catch (IllegalArgumentException e2) {
                throw new zzd(e2);
            } catch (IndexOutOfBoundsException e3) {
                throw new zzd(e3);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void flush() {
            this.zzhgn.position((int) (this.zzams - this.zzhgo));
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final int zzayp() {
            return (int) (this.zzhgq - this.zzams);
        }

        private final void zzfj(long j) {
            this.zzakh.position((int) (j - this.zzhgo));
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
    static class zzb extends zzdpm {
        private final byte[] buffer;
        private final int limit;
        private final int offset;
        private int position;

        zzb(byte[] bArr, int i, int i2) {
            super();
            if (bArr == null) {
                throw new NullPointerException("buffer");
            }
            int i3 = i + i2;
            if ((i | i2 | (bArr.length - i3)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
            }
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i3;
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public void flush() {
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzz(int i, int i2) throws IOException {
            zzgb((i << 3) | i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzaa(int i, int i2) throws IOException {
            zzz(i, 0);
            zzga(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzab(int i, int i2) throws IOException {
            zzz(i, 0);
            zzgb(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzad(int i, int i2) throws IOException {
            zzz(i, 5);
            zzgd(i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, long j) throws IOException {
            zzz(i, 0);
            zzfa(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzi(int i, long j) throws IOException {
            zzz(i, 1);
            zzfc(j);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzh(int i, boolean z) throws IOException {
            zzz(i, 0);
            zzd(z ? (byte) 1 : (byte) 0);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzg(int i, String str) throws IOException {
            zzz(i, 2);
            zzhi(str);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdot zzdotVar) throws IOException {
            zzz(i, 2);
            zzcz(zzdotVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzcz(zzdot zzdotVar) throws IOException {
            zzgb(zzdotVar.size());
            zzdotVar.zza(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzk(byte[] bArr, int i, int i2) throws IOException {
            zzgb(i2);
            write(bArr, 0, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zza(int i, zzdrn zzdrnVar) throws IOException {
            zzz(i, 2);
            zzj(zzdrnVar);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(int i, zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzz(i, 2);
            zzdom zzdomVar = (zzdom) zzdrnVar;
            int iZzaxc = zzdomVar.zzaxc();
            if (iZzaxc == -1) {
                iZzaxc = zzdscVar.zzau(zzdomVar);
                zzdomVar.zzfj(iZzaxc);
            }
            zzgb(iZzaxc);
            zzdscVar.zza(zzdrnVar, this.zzhgk);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdrn zzdrnVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdrnVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzb(int i, zzdot zzdotVar) throws IOException {
            zzz(1, 3);
            zzab(2, i);
            zza(3, zzdotVar);
            zzz(1, 4);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzj(zzdrn zzdrnVar) throws IOException {
            zzgb(zzdrnVar.zzazp());
            zzdrnVar.zzb(this);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        final void zza(zzdrn zzdrnVar, zzdsc zzdscVar) throws IOException {
            zzdom zzdomVar = (zzdom) zzdrnVar;
            int iZzaxc = zzdomVar.zzaxc();
            if (iZzaxc == -1) {
                iZzaxc = zzdscVar.zzau(zzdomVar);
                zzdomVar.zzfj(iZzaxc);
            }
            zzgb(iZzaxc);
            zzdscVar.zza(zzdrnVar, this.zzhgk);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzd(byte b) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzga(int i) throws IOException {
            if (i >= 0) {
                zzgb(i);
            } else {
                zzfa(i);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgb(int i) throws IOException {
            if (!zzdpm.zzhgj || zzdoq.zzaxg() || zzayp() < 5) {
                while ((i & (-128)) != 0) {
                    try {
                        byte[] bArr = this.buffer;
                        int i2 = this.position;
                        this.position = i2 + 1;
                        bArr[i2] = (byte) ((i & Notifications.NOTIFICATION_TYPES_ALL) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                    }
                }
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) i;
                return;
            }
            if ((i & (-128)) == 0) {
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                zzdta.zza(bArr3, i4, (byte) i);
                return;
            }
            byte[] bArr4 = this.buffer;
            int i5 = this.position;
            this.position = i5 + 1;
            zzdta.zza(bArr4, i5, (byte) (i | 128));
            int i6 = i >>> 7;
            if ((i6 & (-128)) == 0) {
                byte[] bArr5 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                zzdta.zza(bArr5, i7, (byte) i6);
                return;
            }
            byte[] bArr6 = this.buffer;
            int i8 = this.position;
            this.position = i8 + 1;
            zzdta.zza(bArr6, i8, (byte) (i6 | 128));
            int i9 = i6 >>> 7;
            if ((i9 & (-128)) == 0) {
                byte[] bArr7 = this.buffer;
                int i10 = this.position;
                this.position = i10 + 1;
                zzdta.zza(bArr7, i10, (byte) i9);
                return;
            }
            byte[] bArr8 = this.buffer;
            int i11 = this.position;
            this.position = i11 + 1;
            zzdta.zza(bArr8, i11, (byte) (i9 | 128));
            int i12 = i9 >>> 7;
            if ((i12 & (-128)) == 0) {
                byte[] bArr9 = this.buffer;
                int i13 = this.position;
                this.position = i13 + 1;
                zzdta.zza(bArr9, i13, (byte) i12);
                return;
            }
            byte[] bArr10 = this.buffer;
            int i14 = this.position;
            this.position = i14 + 1;
            zzdta.zza(bArr10, i14, (byte) (i12 | 128));
            byte[] bArr11 = this.buffer;
            int i15 = this.position;
            this.position = i15 + 1;
            zzdta.zza(bArr11, i15, (byte) (i12 >>> 7));
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzgd(int i) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr[i2] = (byte) i;
                byte[] bArr2 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr2[i3] = (byte) (i >> 8);
                byte[] bArr3 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr3[i4] = (byte) (i >> 16);
                byte[] bArr4 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr4[i5] = (byte) (i >>> 24);
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfa(long j) throws IOException {
            if (zzdpm.zzhgj && zzayp() >= 10) {
                while ((j & (-128)) != 0) {
                    byte[] bArr = this.buffer;
                    int i = this.position;
                    this.position = i + 1;
                    zzdta.zza(bArr, i, (byte) ((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128));
                    j >>>= 7;
                }
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                zzdta.zza(bArr2, i2, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    byte[] bArr3 = this.buffer;
                    int i3 = this.position;
                    this.position = i3 + 1;
                    bArr3[i3] = (byte) ((((int) j) & Notifications.NOTIFICATION_TYPES_ALL) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
                }
            }
            byte[] bArr4 = this.buffer;
            int i4 = this.position;
            this.position = i4 + 1;
            bArr4[i4] = (byte) j;
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzfc(long j) throws IOException {
            try {
                byte[] bArr = this.buffer;
                int i = this.position;
                this.position = i + 1;
                bArr[i] = (byte) j;
                byte[] bArr2 = this.buffer;
                int i2 = this.position;
                this.position = i2 + 1;
                bArr2[i2] = (byte) (j >> 8);
                byte[] bArr3 = this.buffer;
                int i3 = this.position;
                this.position = i3 + 1;
                bArr3[i3] = (byte) (j >> 16);
                byte[] bArr4 = this.buffer;
                int i4 = this.position;
                this.position = i4 + 1;
                bArr4[i4] = (byte) (j >> 24);
                byte[] bArr5 = this.buffer;
                int i5 = this.position;
                this.position = i5 + 1;
                bArr5[i5] = (byte) (j >> 32);
                byte[] bArr6 = this.buffer;
                int i6 = this.position;
                this.position = i6 + 1;
                bArr6[i6] = (byte) (j >> 40);
                byte[] bArr7 = this.buffer;
                int i7 = this.position;
                this.position = i7 + 1;
                bArr7[i7] = (byte) (j >> 48);
                byte[] bArr8 = this.buffer;
                int i8 = this.position;
                this.position = i8 + 1;
                bArr8[i8] = (byte) (j >> 56);
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), 1), e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void write(byte[] bArr, int i, int i2) throws IOException {
            try {
                System.arraycopy(bArr, i, this.buffer, this.position, i2);
                this.position += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new zzd(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)), e);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdou
        public final void zzi(byte[] bArr, int i, int i2) throws IOException {
            write(bArr, i, i2);
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final void zzhi(String str) throws IOException {
            int i = this.position;
            try {
                int iZzgg = zzgg(str.length() * 3);
                int iZzgg2 = zzgg(str.length());
                if (iZzgg2 == iZzgg) {
                    this.position = i + iZzgg2;
                    int iZza = zzdtd.zza(str, this.buffer, this.position, zzayp());
                    this.position = i;
                    zzgb((iZza - i) - iZzgg2);
                    this.position = iZza;
                    return;
                }
                zzgb(zzdtd.zza(str));
                this.position = zzdtd.zza(str, this.buffer, this.position, zzayp());
            } catch (zzdtg e) {
                this.position = i;
                zza(str, e);
            } catch (IndexOutOfBoundsException e2) {
                throw new zzd(e2);
            }
        }

        @Override // com.google.android.gms.internal.ads.zzdpm
        public final int zzayp() {
            return this.limit - this.position;
        }

        public final int zzays() {
            return this.position - this.offset;
        }
    }

    private zzdpm() {
    }

    public final void zzac(int i, int i2) throws IOException {
        zzab(i, zzgl(i2));
    }

    public final void zzh(int i, long j) throws IOException {
        zzg(i, zzfi(j));
    }

    public final void zza(int i, float f) throws IOException {
        zzad(i, Float.floatToRawIntBits(f));
    }

    public final void zzb(int i, double d) throws IOException {
        zzi(i, Double.doubleToRawLongBits(d));
    }

    public final void zzgc(int i) throws IOException {
        zzgb(zzgl(i));
    }

    public final void zzfb(long j) throws IOException {
        zzfa(zzfi(j));
    }

    public final void zzf(float f) throws IOException {
        zzgd(Float.floatToRawIntBits(f));
    }

    public final void zzb(double d) throws IOException {
        zzfc(Double.doubleToRawLongBits(d));
    }

    public final void zzbh(boolean z) throws IOException {
        zzd(z ? (byte) 1 : (byte) 0);
    }

    public static int zzae(int i, int i2) {
        return zzge(i) + zzgf(i2);
    }

    public static int zzaf(int i, int i2) {
        return zzge(i) + zzgg(i2);
    }

    public static int zzag(int i, int i2) {
        return zzge(i) + zzgg(zzgl(i2));
    }

    public static int zzah(int i, int i2) {
        return zzge(i) + 4;
    }

    public static int zzai(int i, int i2) {
        return zzge(i) + 4;
    }

    public static int zzj(int i, long j) {
        return zzge(i) + zzfe(j);
    }

    public static int zzk(int i, long j) {
        return zzge(i) + zzfe(j);
    }

    public static int zzl(int i, long j) {
        return zzge(i) + zzfe(zzfi(j));
    }

    public static int zzm(int i, long j) {
        return zzge(i) + 8;
    }

    public static int zzn(int i, long j) {
        return zzge(i) + 8;
    }

    public static int zzb(int i, float f) {
        return zzge(i) + 4;
    }

    public static int zzc(int i, double d) {
        return zzge(i) + 8;
    }

    public static int zzi(int i, boolean z) {
        return zzge(i) + 1;
    }

    public static int zzaj(int i, int i2) {
        return zzge(i) + zzgf(i2);
    }

    public static int zzh(int i, String str) {
        return zzge(i) + zzhj(str);
    }

    public static int zzc(int i, zzdot zzdotVar) {
        int iZzge = zzge(i);
        int size = zzdotVar.size();
        return iZzge + zzgg(size) + size;
    }

    public static int zza(int i, zzdqs zzdqsVar) {
        int iZzge = zzge(i);
        int iZzazp = zzdqsVar.zzazp();
        return iZzge + zzgg(iZzazp) + iZzazp;
    }

    public static int zzc(int i, zzdrn zzdrnVar) {
        return zzge(i) + zzk(zzdrnVar);
    }

    static int zzb(int i, zzdrn zzdrnVar, zzdsc zzdscVar) {
        return zzge(i) + zzb(zzdrnVar, zzdscVar);
    }

    public static int zzd(int i, zzdrn zzdrnVar) {
        return (zzge(1) << 1) + zzaf(2, i) + zzc(3, zzdrnVar);
    }

    public static int zzd(int i, zzdot zzdotVar) {
        return (zzge(1) << 1) + zzaf(2, i) + zzc(3, zzdotVar);
    }

    public static int zzb(int i, zzdqs zzdqsVar) {
        return (zzge(1) << 1) + zzaf(2, i) + zza(3, zzdqsVar);
    }

    public static int zzge(int i) {
        return zzgg(i << 3);
    }

    public static int zzgf(int i) {
        if (i >= 0) {
            return zzgg(i);
        }
        return 10;
    }

    public static int zzgh(int i) {
        return zzgg(zzgl(i));
    }

    public static int zzfd(long j) {
        return zzfe(j);
    }

    public static int zzff(long j) {
        return zzfe(zzfi(j));
    }

    public static int zzgk(int i) {
        return zzgf(i);
    }

    public static int zzhj(String str) {
        int length;
        try {
            length = zzdtd.zza(str);
        } catch (zzdtg unused) {
            length = str.getBytes(zzdqe.UTF_8).length;
        }
        return zzgg(length) + length;
    }

    public static int zza(zzdqs zzdqsVar) {
        int iZzazp = zzdqsVar.zzazp();
        return zzgg(iZzazp) + iZzazp;
    }

    public static int zzda(zzdot zzdotVar) {
        int size = zzdotVar.size();
        return zzgg(size) + size;
    }

    public static int zzab(byte[] bArr) {
        int length = bArr.length;
        return zzgg(length) + length;
    }

    public static int zzk(zzdrn zzdrnVar) {
        int iZzazp = zzdrnVar.zzazp();
        return zzgg(iZzazp) + iZzazp;
    }

    static int zzb(zzdrn zzdrnVar, zzdsc zzdscVar) {
        zzdom zzdomVar = (zzdom) zzdrnVar;
        int iZzaxc = zzdomVar.zzaxc();
        if (iZzaxc == -1) {
            iZzaxc = zzdscVar.zzau(zzdomVar);
            zzdomVar.zzfj(iZzaxc);
        }
        return zzgg(iZzaxc) + iZzaxc;
    }

    public final void zzayq() {
        if (zzayp() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    final void zza(String str, zzdtg zzdtgVar) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzdtgVar);
        byte[] bytes = str.getBytes(zzdqe.UTF_8);
        try {
            zzgb(bytes.length);
            zzi(bytes, 0, bytes.length);
        } catch (zzd e) {
            throw e;
        } catch (IndexOutOfBoundsException e2) {
            throw new zzd(e2);
        }
    }

    @Deprecated
    static int zzc(int i, zzdrn zzdrnVar, zzdsc zzdscVar) {
        int iZzge = zzge(i) << 1;
        zzdom zzdomVar = (zzdom) zzdrnVar;
        int iZzaxc = zzdomVar.zzaxc();
        if (iZzaxc == -1) {
            iZzaxc = zzdscVar.zzau(zzdomVar);
            zzdomVar.zzfj(iZzaxc);
        }
        return iZzge + iZzaxc;
    }

    @Deprecated
    public static int zzl(zzdrn zzdrnVar) {
        return zzdrnVar.zzazp();
    }

    @Deprecated
    public static int zzgm(int i) {
        return zzgg(i);
    }
}
