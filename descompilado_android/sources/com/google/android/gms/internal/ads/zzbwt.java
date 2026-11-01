package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import com.ironsource.sdk.ISNAdView.ISNAdViewConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwt {
    private final Executor zzfbm;
    private final zzbqb zzfjh;
    private final zzbhd zzfos;

    zzbwt(Executor executor, zzbhd zzbhdVar, zzbqb zzbqbVar) {
        this.zzfbm = executor;
        this.zzfjh = zzbqbVar;
        this.zzfos = zzbhdVar;
    }

    public final void zzl(final zzbbc zzbbcVar) {
        if (zzbbcVar == null) {
            return;
        }
        this.zzfjh.zzq(zzbbcVar.getView());
        this.zzfjh.zza(new zzpl(zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbww
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzpl
            public final void zza(zzpi zzpiVar) {
                this.zzehf.zzyy().zza(zzpiVar.zzbnx.left, zzpiVar.zzbnx.top, false);
            }
        }, this.zzfbm);
        this.zzfjh.zza(new zzpl(zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbwv
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzpl
            public final void zza(zzpi zzpiVar) {
                zzbbc zzbbcVar2 = this.zzehf;
                HashMap map = new HashMap();
                map.put(ISNAdViewConstants.IS_VISIBLE_KEY, zzpiVar.zzbnr ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
                zzbbcVar2.zza("onAdVisibilityChanged", map);
            }
        }, this.zzfbm);
        this.zzfjh.zza(this.zzfos, this.zzfbm);
        this.zzfos.zzg(zzbbcVar);
        zzbbcVar.zza("/trackActiveViewUnit", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbwy
            private final zzbwt zzfot;

            {
                this.zzfot = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfot.zzf((zzbbc) obj, map);
            }
        });
        zzbbcVar.zza("/untrackActiveViewUnit", new zzadx(this) { // from class: com.google.android.gms.internal.ads.zzbwx
            private final zzbwt zzfot;

            {
                this.zzfot = this;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                this.zzfot.zze((zzbbc) obj, map);
            }
        });
    }

    final /* synthetic */ void zze(zzbbc zzbbcVar, Map map) {
        this.zzfos.disable();
    }

    final /* synthetic */ void zzf(zzbbc zzbbcVar, Map map) {
        this.zzfos.enable();
    }
}
