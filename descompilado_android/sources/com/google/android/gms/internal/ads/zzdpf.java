package com.google.android.gms.internal.ads;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzdpf {
    int zzhfv;
    int zzhfw;
    private int zzhfx;
    zzdpk zzhfy;
    private boolean zzhfz;

    static zzdpf zzc(byte[] bArr, int i, int i2, boolean z) {
        zzdph zzdphVar = new zzdph(bArr, i, i2, z);
        try {
            zzdphVar.zzfs(i2);
            return zzdphVar;
        } catch (zzdqn e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static long zzez(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static int zzfu(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract int zzaxp() throws IOException;

    public abstract long zzaxq() throws IOException;

    public abstract long zzaxr() throws IOException;

    public abstract int zzaxs() throws IOException;

    public abstract long zzaxt() throws IOException;

    public abstract int zzaxu() throws IOException;

    public abstract boolean zzaxv() throws IOException;

    public abstract String zzaxw() throws IOException;

    public abstract zzdot zzaxx() throws IOException;

    public abstract int zzaxy() throws IOException;

    public abstract int zzaxz() throws IOException;

    public abstract int zzaya() throws IOException;

    public abstract long zzayb() throws IOException;

    public abstract int zzayc() throws IOException;

    public abstract long zzayd() throws IOException;

    abstract long zzaye() throws IOException;

    public abstract boolean zzayf() throws IOException;

    public abstract int zzayg();

    public abstract void zzfq(int i) throws zzdqn;

    public abstract boolean zzfr(int i) throws IOException;

    public abstract int zzfs(int i) throws zzdqn;

    public abstract void zzft(int i);

    private zzdpf() {
        this.zzhfw = 100;
        this.zzhfx = Integer.MAX_VALUE;
        this.zzhfz = false;
    }
}
