package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzsi {
    private final byte[] zzbtq;
    private int zzbtr;
    private int zzbts;
    private final /* synthetic */ zzse zzbtt;

    private zzsi(zzse zzseVar, byte[] bArr) {
        this.zzbtt = zzseVar;
        this.zzbtq = bArr;
    }

    public final synchronized void zzdf() {
        try {
            if (this.zzbtt.zzbtp) {
                this.zzbtt.zzbto.zzc(this.zzbtq);
                this.zzbtt.zzbto.zzl(this.zzbtr);
                this.zzbtt.zzbto.zzm(this.zzbts);
                this.zzbtt.zzbto.zza(null);
                this.zzbtt.zzbto.zzdf();
            }
        } catch (RemoteException e) {
            zzawo.zzb("Clearcut log failed", e);
        }
    }

    public final zzsi zzbq(int i) {
        this.zzbtr = i;
        return this;
    }

    public final zzsi zzbr(int i) {
        this.zzbts = i;
        return this;
    }
}
