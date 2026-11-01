package com.google.android.gms.internal.ads;

import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzpu {

    @VisibleForTesting
    private int zzbog;
    private final Object lock = new Object();
    private List<zzpv> zzboh = new LinkedList();

    @Nullable
    public final zzpv zzn(boolean z) {
        synchronized (this.lock) {
            zzpv zzpvVar = null;
            if (this.zzboh.size() == 0) {
                zzatm.zzdv("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzboh.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzpv zzpvVar2 : this.zzboh) {
                    int score = zzpvVar2.getScore();
                    if (score > i2) {
                        i = i3;
                        zzpvVar = zzpvVar2;
                        i2 = score;
                    }
                    i3++;
                }
                this.zzboh.remove(i);
                return zzpvVar;
            }
            zzpv zzpvVar3 = this.zzboh.get(0);
            if (z) {
                this.zzboh.remove(0);
            } else {
                zzpvVar3.zzll();
            }
            return zzpvVar3;
        }
    }

    public final boolean zza(zzpv zzpvVar) {
        synchronized (this.lock) {
            return this.zzboh.contains(zzpvVar);
        }
    }

    public final boolean zzb(zzpv zzpvVar) {
        synchronized (this.lock) {
            Iterator<zzpv> it = this.zzboh.iterator();
            while (it.hasNext()) {
                zzpv next = it.next();
                if (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzus()) {
                    if (zzpvVar != next && next.zzli().equals(zzpvVar.zzli())) {
                        it.remove();
                        return true;
                    }
                } else if (!com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuu() && zzpvVar != next && next.zzlk().equals(zzpvVar.zzlk())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzpv zzpvVar) {
        synchronized (this.lock) {
            if (this.zzboh.size() >= 10) {
                int size = this.zzboh.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzatm.zzdv(sb.toString());
                this.zzboh.remove(0);
            }
            int i = this.zzbog;
            this.zzbog = i + 1;
            zzpvVar.zzbo(i);
            zzpvVar.zzlo();
            this.zzboh.add(zzpvVar);
        }
    }
}
