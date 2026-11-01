package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
final class zzm extends WebViewClient {
    private final /* synthetic */ zzj zzblp;

    zzm(zzj zzjVar) {
        this.zzblp = zzjVar;
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.zzblp.zzjt())) {
            return false;
        }
        if (str.startsWith((String) zzuo.zzoj().zzd(zzyt.zzcos))) {
            if (this.zzblp.zzblm != null) {
                try {
                    this.zzblp.zzblm.onAdFailedToLoad(3);
                } catch (RemoteException e) {
                    zzatm.zze("#007 Could not call remote method.", e);
                }
            }
            this.zzblp.zzbl(0);
            return true;
        }
        if (str.startsWith((String) zzuo.zzoj().zzd(zzyt.zzcot))) {
            if (this.zzblp.zzblm != null) {
                try {
                    this.zzblp.zzblm.onAdFailedToLoad(0);
                } catch (RemoteException e2) {
                    zzatm.zze("#007 Could not call remote method.", e2);
                }
            }
            this.zzblp.zzbl(0);
            return true;
        }
        if (str.startsWith((String) zzuo.zzoj().zzd(zzyt.zzcou))) {
            if (this.zzblp.zzblm != null) {
                try {
                    this.zzblp.zzblm.onAdLoaded();
                } catch (RemoteException e3) {
                    zzatm.zze("#007 Could not call remote method.", e3);
                }
            }
            this.zzblp.zzbl(this.zzblp.zzbn(str));
            return true;
        }
        if (str.startsWith("gmsg://")) {
            return true;
        }
        if (this.zzblp.zzblm != null) {
            try {
                this.zzblp.zzblm.onAdLeftApplication();
            } catch (RemoteException e4) {
                zzatm.zze("#007 Could not call remote method.", e4);
            }
        }
        this.zzblp.zzbp(this.zzblp.zzbo(str));
        return true;
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (this.zzblp.zzblm != null) {
            try {
                this.zzblp.zzblm.onAdFailedToLoad(0);
            } catch (RemoteException e) {
                zzatm.zze("#007 Could not call remote method.", e);
            }
        }
    }
}
