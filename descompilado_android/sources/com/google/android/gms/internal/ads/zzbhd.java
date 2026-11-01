package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.Clock;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbhd implements zzpl {
    private final Clock zzbmd;
    private zzbbc zzcyx;
    private final zzbgs zzfbj;
    private final Executor zzfbm;
    private boolean zzbrt = false;
    private boolean zzfci = false;
    private zzbgw zzfbo = new zzbgw();

    public zzbhd(Executor executor, zzbgs zzbgsVar, Clock clock) {
        this.zzfbm = executor;
        this.zzfbj = zzbgsVar;
        this.zzbmd = clock;
    }

    private final void zzaeh() {
        try {
            final JSONObject jSONObjectZzj = this.zzfbj.zzj(this.zzfbo);
            if (this.zzcyx != null) {
                this.zzfbm.execute(new Runnable(this, jSONObjectZzj) { // from class: com.google.android.gms.internal.ads.zzbhg
                    private final JSONObject zzfbh;
                    private final zzbhd zzfcj;

                    {
                        this.zzfcj = this;
                        this.zzfbh = jSONObjectZzj;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzfcj.zzh(this.zzfbh);
                    }
                });
            }
        } catch (JSONException e) {
            zzatm.zza("Failed to call video active view js", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final void zza(zzpi zzpiVar) {
        this.zzfbo.zzbnr = this.zzfci ? false : zzpiVar.zzbnr;
        this.zzfbo.timestamp = this.zzbmd.elapsedRealtime();
        this.zzfbo.zzfbz = zzpiVar;
        if (this.zzbrt) {
            zzaeh();
        }
    }

    public final void zzg(zzbbc zzbbcVar) {
        this.zzcyx = zzbbcVar;
    }

    public final void disable() {
        this.zzbrt = false;
    }

    public final void enable() {
        this.zzbrt = true;
        zzaeh();
    }

    public final void zzax(boolean z) {
        this.zzfci = z;
    }

    final /* synthetic */ void zzh(JSONObject jSONObject) {
        this.zzcyx.zzb("AFMA_updateActiveView", jSONObject);
    }
}
