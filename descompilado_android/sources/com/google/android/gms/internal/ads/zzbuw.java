package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.util.Clock;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbuw implements View.OnClickListener {
    private final Clock zzbmd;
    private final zzbxn zzfms;

    @Nullable
    private zzacl zzfmt;

    @Nullable
    private zzadx<Object> zzfmu;

    @Nullable
    @VisibleForTesting
    String zzfmv;

    @Nullable
    @VisibleForTesting
    Long zzfmw;

    @Nullable
    @VisibleForTesting
    WeakReference<View> zzfmx;

    public zzbuw(zzbxn zzbxnVar, Clock clock) {
        this.zzfms = zzbxnVar;
        this.zzbmd = clock;
    }

    public final void zza(final zzacl zzaclVar) {
        this.zzfmt = zzaclVar;
        zzadx<Object> zzadxVar = this.zzfmu;
        if (zzadxVar != null) {
            this.zzfms.zzb("/unconfirmedClick", zzadxVar);
        }
        this.zzfmu = new zzadx(this, zzaclVar) { // from class: com.google.android.gms.internal.ads.zzbuv
            private final zzbuw zzfmq;
            private final zzacl zzfmr;

            {
                this.zzfmq = this;
                this.zzfmr = zzaclVar;
            }

            @Override // com.google.android.gms.internal.ads.zzadx
            public final void zza(Object obj, Map map) {
                zzbuw zzbuwVar = this.zzfmq;
                zzacl zzaclVar2 = this.zzfmr;
                try {
                    zzbuwVar.zzfmw = Long.valueOf(Long.parseLong((String) map.get("timestamp")));
                } catch (NumberFormatException unused) {
                    zzatm.zzes("Failed to call parse unconfirmedClickTimestamp.");
                }
                zzbuwVar.zzfmv = (String) map.get("id");
                String str = (String) map.get("asset_id");
                if (zzaclVar2 == null) {
                    zzatm.zzdv("Received unconfirmed click but UnconfirmedClickListener is null.");
                    return;
                }
                try {
                    zzaclVar2.onUnconfirmedClickReceived(str);
                } catch (RemoteException e) {
                    zzawo.zze("#007 Could not call remote method.", e);
                }
            }
        };
        this.zzfms.zza("/unconfirmedClick", this.zzfmu);
    }

    @Nullable
    public final zzacl zzais() {
        return this.zzfmt;
    }

    public final void cancelUnconfirmedClick() {
        if (this.zzfmt == null || this.zzfmw == null) {
            return;
        }
        zzait();
        try {
            this.zzfmt.onUnconfirmedClickCancelled();
        } catch (RemoteException e) {
            zzawo.zze("#007 Could not call remote method.", e);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        WeakReference<View> weakReference = this.zzfmx;
        if (weakReference == null || weakReference.get() != view) {
            return;
        }
        if (this.zzfmv != null && this.zzfmw != null) {
            HashMap map = new HashMap();
            map.put("id", this.zzfmv);
            map.put("time_interval", String.valueOf(this.zzbmd.currentTimeMillis() - this.zzfmw.longValue()));
            map.put("messageType", "onePointFiveClick");
            this.zzfms.zza("sendMessageToNativeJs", map);
        }
        zzait();
    }

    private final void zzait() {
        View view;
        this.zzfmv = null;
        this.zzfmw = null;
        WeakReference<View> weakReference = this.zzfmx;
        if (weakReference == null || (view = weakReference.get()) == null) {
            return;
        }
        view.setClickable(false);
        view.setOnClickListener(null);
        this.zzfmx = null;
    }
}
