package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.io.IOException;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzrt implements BaseGmsClient.BaseConnectionCallbacks {
    final /* synthetic */ zzrr zzbrg;
    private final /* synthetic */ zzrl zzbrm;
    private final /* synthetic */ zzaxf zzbrn;

    zzrt(zzrr zzrrVar, zzrl zzrlVar, zzaxf zzaxfVar) {
        this.zzbrg = zzrrVar;
        this.zzbrm = zzrlVar;
        this.zzbrn = zzaxfVar;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnectionSuspended(int i) {
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks
    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.zzbrg.lock) {
            if (this.zzbrg.zzbrh) {
                return;
            }
            zzrr.zza(this.zzbrg, true);
            final zzri zzriVar = this.zzbrg.zzbqw;
            if (zzriVar == null) {
                return;
            }
            zzdcs zzdcsVar = zzawx.zzdvx;
            final zzrl zzrlVar = this.zzbrm;
            final zzaxf zzaxfVar = this.zzbrn;
            final zzdcp<?> zzdcpVarSubmit = zzdcsVar.submit(new Runnable(this, zzriVar, zzrlVar, zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzrs
                private final zzrt zzbri;
                private final zzri zzbrj;
                private final zzrl zzbrk;
                private final zzaxf zzbrl;

                {
                    this.zzbri = this;
                    this.zzbrj = zzriVar;
                    this.zzbrk = zzrlVar;
                    this.zzbrl = zzaxfVar;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzrt zzrtVar = this.zzbri;
                    zzri zzriVar2 = this.zzbrj;
                    zzrl zzrlVar2 = this.zzbrk;
                    zzaxf zzaxfVar2 = this.zzbrl;
                    try {
                        zzrg zzrgVarZza = zzriVar2.zzmj().zza(zzrlVar2);
                        if (!zzrgVarZza.zzmg()) {
                            zzaxfVar2.setException(new RuntimeException("No entry contents."));
                            zzrtVar.zzbrg.disconnect();
                            return;
                        }
                        zzru zzruVar = new zzru(zzrtVar, zzrgVarZza.zzmh(), 1);
                        int i = zzruVar.read();
                        if (i == -1) {
                            throw new IOException("Unable to read from cache.");
                        }
                        zzruVar.unread(i);
                        zzaxfVar2.set(zzruVar);
                    } catch (RemoteException | IOException e) {
                        zzatm.zzc("Unable to obtain a cache service instance.", e);
                        zzaxfVar2.setException(e);
                        zzrtVar.zzbrg.disconnect();
                    }
                }
            });
            zzaxf zzaxfVar2 = this.zzbrn;
            final zzaxf zzaxfVar3 = this.zzbrn;
            zzaxfVar2.addListener(new Runnable(zzaxfVar3, zzdcpVarSubmit) { // from class: com.google.android.gms.internal.ads.zzrv
                private final zzaxf zzbrp;
                private final Future zzbrq;

                {
                    this.zzbrp = zzaxfVar3;
                    this.zzbrq = zzdcpVarSubmit;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzaxf zzaxfVar4 = this.zzbrp;
                    Future future = this.zzbrq;
                    if (zzaxfVar4.isCancelled()) {
                        future.cancel(true);
                    }
                }
            }, zzawx.zzdwb);
        }
    }
}
