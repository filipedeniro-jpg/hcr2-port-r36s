package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import com.google.android.gms.internal.ads.zzbcd;
import com.google.android.gms.internal.ads.zzbcl;
import com.google.android.gms.internal.ads.zzbcn;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(17)
public final class zzbbz<WebViewT extends zzbcd & zzbcl & zzbcn> {
    private final zzbca zzehg;
    private final WebViewT zzehh;

    public static zzbbz<zzbbc> zzc(final zzbbc zzbbcVar) {
        return new zzbbz<>(zzbbcVar, new zzbca(zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbby
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbca
            public final void zzh(Uri uri) {
                zzbcm zzbcmVarZzyy = this.zzehf.zzyy();
                if (zzbcmVarZzyy == null) {
                    zzatm.zzes("Unable to pass GMSG, no AdWebViewClient for AdWebView!");
                } else {
                    zzbcmVarZzyy.zzh(uri);
                }
            }
        });
    }

    private zzbbz(WebViewT webviewt, zzbca zzbcaVar) {
        this.zzehg = zzbcaVar;
        this.zzehh = webviewt;
    }

    @JavascriptInterface
    public final void notify(final String str) {
        if (TextUtils.isEmpty(str)) {
            zzatm.zzeu("URL is empty, ignoring message");
        } else {
            zzatv.zzdsk.post(new Runnable(this, str) { // from class: com.google.android.gms.internal.ads.zzbcb
                private final String zzcyl;
                private final zzbbz zzehi;

                {
                    this.zzehi = this;
                    this.zzcyl = str;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzehi.zzfl(this.zzcyl);
                }
            });
        }
    }

    @JavascriptInterface
    public final String getClickSignals(String str) {
        if (TextUtils.isEmpty(str)) {
            zzatm.zzdy("Click string is empty, not proceeding.");
            return "";
        }
        zzdf zzdfVarZzzb = this.zzehh.zzzb();
        if (zzdfVarZzzb == null) {
            zzatm.zzdy("Signal utils is empty, ignoring.");
            return "";
        }
        zzdc zzdcVarZzcc = zzdfVarZzzb.zzcc();
        if (zzdcVarZzcc == null) {
            zzatm.zzdy("Signals object is empty, ignoring.");
            return "";
        }
        if (this.zzehh.getContext() == null) {
            zzatm.zzdy("Context is null, ignoring.");
            return "";
        }
        return zzdcVarZzcc.zza(this.zzehh.getContext(), str, this.zzehh.getView(), this.zzehh.zzxl());
    }

    final /* synthetic */ void zzfl(String str) {
        this.zzehg.zzh(Uri.parse(str));
    }
}
