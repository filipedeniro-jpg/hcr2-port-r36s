package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.util.Clock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzasw {
    private final Clock zzbmd;
    private final String zzdiu;
    private final zzatj zzdpp;
    private final String zzdpr;
    private final Object lock = new Object();

    @GuardedBy("lock")
    private long zzdki = -1;

    @GuardedBy("lock")
    private long zzdps = -1;

    @GuardedBy("lock")
    private boolean zzdke = false;

    @GuardedBy("lock")
    private long zzdpt = -1;

    @GuardedBy("lock")
    private long zzdpu = 0;

    @GuardedBy("lock")
    private long zzdpv = -1;

    @GuardedBy("lock")
    private long zzdpw = -1;

    @GuardedBy("lock")
    private final LinkedList<zzasz> zzdpq = new LinkedList<>();

    zzasw(Clock clock, zzatj zzatjVar, String str, String str2) {
        this.zzbmd = clock;
        this.zzdpp = zzatjVar;
        this.zzdpr = str;
        this.zzdiu = str2;
    }

    public final void zze(zztp zztpVar) {
        synchronized (this.lock) {
            this.zzdpv = this.zzbmd.elapsedRealtime();
            this.zzdpp.zza(zztpVar, this.zzdpv);
        }
    }

    public final void zzes(long j) {
        synchronized (this.lock) {
            this.zzdpw = j;
            if (this.zzdpw != -1) {
                this.zzdpp.zzb(this);
            }
        }
    }

    public final void zztq() {
        synchronized (this.lock) {
            if (this.zzdpw != -1 && this.zzdps == -1) {
                this.zzdps = this.zzbmd.elapsedRealtime();
                this.zzdpp.zzb(this);
            }
            this.zzdpp.zztq();
        }
    }

    public final void zztr() {
        synchronized (this.lock) {
            if (this.zzdpw != -1) {
                zzasz zzaszVar = new zzasz(this);
                zzaszVar.zzuc();
                this.zzdpq.add(zzaszVar);
                this.zzdpu++;
                this.zzdpp.zztr();
                this.zzdpp.zzb(this);
            }
        }
    }

    public final void zzts() {
        synchronized (this.lock) {
            if (this.zzdpw != -1 && !this.zzdpq.isEmpty()) {
                zzasz last = this.zzdpq.getLast();
                if (last.zzua() == -1) {
                    last.zzub();
                    this.zzdpp.zzb(this);
                }
            }
        }
    }

    public final void zzag(boolean z) {
        synchronized (this.lock) {
            if (this.zzdpw != -1) {
                this.zzdpt = this.zzbmd.elapsedRealtime();
            }
        }
    }

    public final Bundle toBundle() {
        Bundle bundle;
        synchronized (this.lock) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzdpr);
            bundle.putString("slotid", this.zzdiu);
            bundle.putBoolean("ismediation", false);
            bundle.putLong("treq", this.zzdpv);
            bundle.putLong("tresponse", this.zzdpw);
            bundle.putLong("timp", this.zzdps);
            bundle.putLong("tload", this.zzdpt);
            bundle.putLong("pcc", this.zzdpu);
            bundle.putLong("tfetch", this.zzdki);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            Iterator<zzasz> it = this.zzdpq.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }

    public final String zztt() {
        return this.zzdpr;
    }
}
