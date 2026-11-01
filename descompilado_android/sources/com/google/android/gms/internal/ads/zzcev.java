package com.google.android.gms.internal.ads;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcev {
    private int responseCode = 0;
    private long zzfvq = 0;
    private long zzfvr = 0;
    private long zzfvs = 0;
    private final Object zzfvt = new Object();
    private final Object zzfvu = new Object();
    private final Object zzfvv = new Object();
    private final Object zzfvw = new Object();

    public final void zzdi(int i) {
        synchronized (this.zzfvt) {
            this.responseCode = i;
        }
    }

    public final int getResponseCode() {
        int i;
        synchronized (this.zzfvt) {
            i = this.responseCode;
        }
        return i;
    }

    public final void zzei(long j) {
        synchronized (this.zzfvu) {
            this.zzfvq = j;
        }
    }

    public final long zzakj() {
        long j;
        synchronized (this.zzfvu) {
            j = this.zzfvq;
        }
        return j;
    }

    public final synchronized void zzey(long j) {
        synchronized (this.zzfvv) {
            this.zzfvr = j;
        }
    }

    public final synchronized long zzakk() {
        long j;
        synchronized (this.zzfvv) {
            j = this.zzfvr;
        }
        return j;
    }

    public final synchronized void zzej(long j) {
        synchronized (this.zzfvw) {
            this.zzfvs = j;
        }
    }

    public final synchronized long zzakl() {
        long j;
        synchronized (this.zzfvw) {
            j = this.zzfvs;
        }
        return j;
    }
}
