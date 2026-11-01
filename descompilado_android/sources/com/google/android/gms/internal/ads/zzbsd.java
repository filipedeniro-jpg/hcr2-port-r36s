package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsd implements com.google.android.gms.ads.internal.overlay.zzp, zzbnm {
    private final zzawv zzblh;

    @Nullable
    private final zzbbc zzcyx;
    private final zzcvb zzfef;

    @Nullable
    @VisibleForTesting
    private IObjectWrapper zzfeg;
    private final int zzfif;
    private final Context zzlk;

    public zzbsd(Context context, @Nullable zzbbc zzbbcVar, zzcvb zzcvbVar, zzawv zzawvVar, int i) {
        this.zzlk = context;
        this.zzcyx = zzbbcVar;
        this.zzfef = zzcvbVar;
        this.zzblh = zzawvVar;
        this.zzfif = i;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onPause() {
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void onResume() {
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final void onAdLoaded() {
        int i = this.zzfif;
        if ((i == 7 || i == 3) && this.zzfef.zzdlc && this.zzcyx != null && com.google.android.gms.ads.internal.zzp.zzkn().zzo(this.zzlk)) {
            int i2 = this.zzblh.zzdvs;
            int i3 = this.zzblh.zzdvt;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i2);
            sb.append(".");
            sb.append(i3);
            this.zzfeg = com.google.android.gms.ads.internal.zzp.zzkn().zza(sb.toString(), this.zzcyx.getWebView(), "", "javascript", this.zzfef.zzgjg.optInt(MessengerShareContentUtility.MEDIA_TYPE, -1) == 0 ? null : "javascript");
            if (this.zzfeg == null || this.zzcyx.getView() == null) {
                return;
            }
            com.google.android.gms.ads.internal.zzp.zzkn().zza(this.zzfeg, this.zzcyx.getView());
            this.zzcyx.zzaq(this.zzfeg);
            com.google.android.gms.ads.internal.zzp.zzkn().zzae(this.zzfeg);
        }
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzse() {
        this.zzfeg = null;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzp
    public final void zzsf() {
        zzbbc zzbbcVar;
        if (this.zzfeg == null || (zzbbcVar = this.zzcyx) == null) {
            return;
        }
        zzbbcVar.zza("onSdkImpression", new HashMap());
    }
}
