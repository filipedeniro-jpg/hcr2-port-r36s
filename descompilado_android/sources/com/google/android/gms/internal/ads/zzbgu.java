package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbgu implements com.google.android.gms.ads.internal.overlay.zzp, zzbms, zzbmt, zzpl {
    private final Clock zzbmd;
    private final zzbgl zzfbi;
    private final zzbgs zzfbj;
    private final zzaip<JSONObject, JSONObject> zzfbl;
    private final Executor zzfbm;
    private final Set<zzbbc> zzfbk = new HashSet();
    private final AtomicBoolean zzfbn = new AtomicBoolean(false);

    @GuardedBy("this")
    private final zzbgw zzfbo = new zzbgw();
    private boolean zzfbp = false;
    private WeakReference<?> zzfbq = new WeakReference<>(this);

    public zzbgu(zzaim zzaimVar, zzbgs zzbgsVar, Executor executor, zzbgl zzbglVar, Clock clock) {
        this.zzfbi = zzbglVar;
        this.zzfbl = zzaimVar.zzb("google.afma.activeView.handleUpdate", zzaic.zzdan, zzaic.zzdan);
        this.zzfbj = zzbgsVar;
        this.zzfbm = executor;
        this.zzbmd = clock;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
    }

    public final synchronized void zzaee() {
        if (!(this.zzfbq.get() != null)) {
            zzaeg();
            return;
        }
        if (!this.zzfbp && this.zzfbn.get()) {
            try {
                this.zzfbo.timestamp = this.zzbmd.elapsedRealtime();
                final JSONObject jSONObjectZzj = this.zzfbj.zzj(this.zzfbo);
                for (final zzbbc zzbbcVar : this.zzfbk) {
                    this.zzfbm.execute(new Runnable(zzbbcVar, jSONObjectZzj) { // from class: com.google.android.gms.internal.ads.zzbgt
                        private final zzbbc zzehf;
                        private final JSONObject zzfbh;

                        {
                            this.zzehf = zzbbcVar;
                            this.zzfbh = jSONObjectZzj;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzehf.zzb("AFMA_updateActiveView", this.zzfbh);
                        }
                    });
                }
                zzaxb.zzb(this.zzfbl.zzf(jSONObjectZzj), "ActiveViewListener.callActiveViewJs");
            } catch (Exception e) {
                zzatm.zza("Failed to call ActiveViewJS", e);
            }
        }
    }

    private final void zzaef() {
        Iterator<zzbbc> it = this.zzfbk.iterator();
        while (it.hasNext()) {
            this.zzfbi.zze(it.next());
        }
        this.zzfbi.zzaed();
    }

    public final synchronized void zzaeg() {
        zzaef();
        this.zzfbp = true;
    }

    public final synchronized void zzf(zzbbc zzbbcVar) {
        this.zzfbk.add(zzbbcVar);
        this.zzfbi.zzd(zzbbcVar);
    }

    public final void zzo(Object obj) {
        this.zzfbq = new WeakReference<>(obj);
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final synchronized void zza(zzpi zzpiVar) {
        this.zzfbo.zzbnr = zzpiVar.zzbnr;
        this.zzfbo.zzfbz = zzpiVar;
        zzaee();
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final synchronized void zzbt(@Nullable Context context) {
        this.zzfbo.zzfbw = true;
        zzaee();
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final synchronized void zzbu(@Nullable Context context) {
        this.zzfbo.zzfbw = false;
        zzaee();
    }

    @Override // com.google.android.gms.internal.ads.zzbms
    public final synchronized void zzbv(@Nullable Context context) {
        this.zzfbo.zzfby = "u";
        zzaee();
        zzaef();
        this.zzfbp = true;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onPause() {
        this.zzfbo.zzfbw = true;
        zzaee();
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final synchronized void onResume() {
        this.zzfbo.zzfbw = false;
        zzaee();
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final synchronized void onAdImpression() {
        if (this.zzfbn.compareAndSet(false, true)) {
            this.zzfbi.zza(this);
            zzaee();
        }
    }
}
