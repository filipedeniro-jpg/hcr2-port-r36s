package com.google.android.gms.internal.ads;

import android.content.Context;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbjj implements zzbmt, zzbnm {
    private final zzawv zzblh;

    @Nullable
    private final zzbbc zzcyx;
    private final zzcvb zzfef;

    @Nullable
    @GuardedBy("this")
    private IObjectWrapper zzfeg;

    @GuardedBy("this")
    private boolean zzfeh;
    private final Context zzlk;

    public zzbjj(Context context, @Nullable zzbbc zzbbcVar, zzcvb zzcvbVar, zzawv zzawvVar) {
        this.zzlk = context;
        this.zzcyx = zzbbcVar;
        this.zzfef = zzcvbVar;
        this.zzblh = zzawvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbnm
    public final synchronized void onAdLoaded() {
        if (this.zzfeh) {
            return;
        }
        zzaff();
    }

    @Override // com.google.android.gms.internal.ads.zzbmt
    public final synchronized void onAdImpression() {
        if (!this.zzfeh) {
            zzaff();
        }
        if (this.zzfef.zzdlc && this.zzfeg != null && this.zzcyx != null) {
            this.zzcyx.zza("onSdkImpression", new ArrayMap());
        }
    }

    private final synchronized void zzaff() {
        if (this.zzfef.zzdlc) {
            if (this.zzcyx == null) {
                return;
            }
            if (com.google.android.gms.ads.internal.zzp.zzkn().zzo(this.zzlk)) {
                int i = this.zzblh.zzdvs;
                int i2 = this.zzblh.zzdvt;
                StringBuilder sb = new StringBuilder(23);
                sb.append(i);
                sb.append(".");
                sb.append(i2);
                this.zzfeg = com.google.android.gms.ads.internal.zzp.zzkn().zza(sb.toString(), this.zzcyx.getWebView(), "", "javascript", this.zzfef.zzgjg.optInt(MessengerShareContentUtility.MEDIA_TYPE, -1) == 0 ? null : "javascript");
                View view = this.zzcyx.getView();
                if (this.zzfeg != null && view != null) {
                    com.google.android.gms.ads.internal.zzp.zzkn().zza(this.zzfeg, view);
                    this.zzcyx.zzaq(this.zzfeg);
                    com.google.android.gms.ads.internal.zzp.zzkn().zzae(this.zzfeg);
                    this.zzfeh = true;
                }
            }
        }
    }
}
