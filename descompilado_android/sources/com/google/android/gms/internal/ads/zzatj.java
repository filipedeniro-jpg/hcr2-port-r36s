package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatj implements zzpy {
    private final zzato zzdqz;

    @VisibleForTesting
    private final zzate zzdrk;
    private final Object lock = new Object();

    @VisibleForTesting
    private final HashSet<zzasw> zzdrl = new HashSet<>();

    @VisibleForTesting
    private final HashSet<Object> zzdrm = new HashSet<>();
    private final zzatg zzdrj = new zzatg();

    public zzatj(String str, zzato zzatoVar) {
        this.zzdrk = new zzate(str, zzatoVar);
        this.zzdqz = zzatoVar;
    }

    public final void zzb(zzasw zzaswVar) {
        synchronized (this.lock) {
            this.zzdrl.add(zzaswVar);
        }
    }

    public final void zzb(HashSet<zzasw> hashSet) {
        synchronized (this.lock) {
            this.zzdrl.addAll(hashSet);
        }
    }

    public final Bundle zza(Context context, zzath zzathVar) {
        HashSet<zzasw> hashSet = new HashSet<>();
        synchronized (this.lock) {
            hashSet.addAll(this.zzdrl);
            this.zzdrl.clear();
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("app", this.zzdrk.zzo(context, this.zzdrj.zzuo()));
        Bundle bundle2 = new Bundle();
        Iterator<Object> it = this.zzdrm.iterator();
        if (it.hasNext()) {
            it.next();
            throw new NoSuchMethodError();
        }
        bundle.putBundle("slots", bundle2);
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        Iterator<zzasw> it2 = hashSet.iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().toBundle());
        }
        bundle.putParcelableArrayList("ads", arrayList);
        zzathVar.zza(hashSet);
        return bundle;
    }

    @Override // com.google.android.gms.internal.ads.zzpy
    public final void zzo(boolean z) {
        long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
        if (z) {
            if (jCurrentTimeMillis - this.zzdqz.zzuz() > ((Long) zzuo.zzoj().zzd(zzyt.zzcki)).longValue()) {
                this.zzdrk.zzdqw = -1;
                return;
            } else {
                this.zzdrk.zzdqw = this.zzdqz.zzva();
                return;
            }
        }
        this.zzdqz.zzet(jCurrentTimeMillis);
        this.zzdqz.zzco(this.zzdrk.zzdqw);
    }

    public final void zztr() {
        synchronized (this.lock) {
            this.zzdrk.zztr();
        }
    }

    public final void zztq() {
        synchronized (this.lock) {
            this.zzdrk.zztq();
        }
    }

    public final void zza(zztp zztpVar, long j) {
        synchronized (this.lock) {
            this.zzdrk.zza(zztpVar, j);
        }
    }

    public final zzasw zza(Clock clock, String str) {
        return new zzasw(clock, this, this.zzdrj.zzun(), str);
    }
}
