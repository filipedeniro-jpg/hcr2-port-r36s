package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.internal.ads.zzanz;
import com.google.android.gms.internal.ads.zzaro;
import com.google.android.gms.internal.ads.zzatv;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zza {
    private boolean zzbkl;
    private zzaro zzbkm;
    private zzanz zzbkn;
    private final Context zzlk;

    public zza(Context context, zzaro zzaroVar, zzanz zzanzVar) {
        this.zzlk = context;
        this.zzbkm = zzaroVar;
        this.zzbkn = null;
        if (this.zzbkn == null) {
            this.zzbkn = new zzanz();
        }
    }

    private final boolean zzjg() {
        zzaro zzaroVar = this.zzbkm;
        return (zzaroVar != null && zzaroVar.zzti().zzdop) || this.zzbkn.zzdlh;
    }

    public final void recordClick() {
        this.zzbkl = true;
    }

    public final boolean zzjh() {
        return !zzjg() || this.zzbkl;
    }

    public final void zzbl(@Nullable String str) {
        if (zzjg()) {
            if (str == null) {
                str = "";
            }
            zzaro zzaroVar = this.zzbkm;
            if (zzaroVar != null) {
                zzaroVar.zza(str, null, 3);
                return;
            }
            if (!this.zzbkn.zzdlh || this.zzbkn.zzdli == null) {
                return;
            }
            for (String str2 : this.zzbkn.zzdli) {
                if (!TextUtils.isEmpty(str2)) {
                    String strReplace = str2.replace("{NAVIGATION_URL}", Uri.encode(str));
                    zzp.zzjy();
                    zzatv.zzb(this.zzlk, "", strReplace);
                }
            }
        }
    }
}
