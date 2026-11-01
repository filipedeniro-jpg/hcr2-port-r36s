package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@ParametersAreNonnullByDefault
public final class zzbcs extends zzbdf implements zzbcm {
    private volatile boolean zzbni;
    private zzth zzcbl;
    private zzadg zzcwp;
    private zzadi zzcwr;
    private com.google.android.gms.ads.internal.zza zzcxn;
    private zzamf zzcxo;
    private com.google.android.gms.ads.internal.overlay.zzp zzdhm;
    private com.google.android.gms.ads.internal.overlay.zzv zzdhq;
    private boolean zzdlf;
    protected zzbbc zzeea;
    private zzbcp zzeed;
    private zzbco zzeee;
    private zzbcr zzeef;

    @GuardedBy("lock")
    private boolean zzeeh;

    @GuardedBy("lock")
    private boolean zzeei;
    private zzamq zzeej;

    @Nullable
    private zzaro zzeek;
    private boolean zzeel;
    private boolean zzeem;
    private int zzeen;
    private View.OnAttachStateChangeListener zzeeo;
    private final Object lock = new Object();
    private boolean zzeeg = false;
    private final zzagf<zzbbc> zzehm = new zzagf<>();

    final void zza(zzbbc zzbbcVar, boolean z) {
        zzamq zzamqVar = new zzamq(zzbbcVar, zzbbcVar.zzyt(), new zzye(zzbbcVar.getContext()));
        this.zzeea = zzbbcVar;
        this.zzbni = z;
        this.zzeej = zzamqVar;
        this.zzcxo = null;
        this.zzehm.zzg(zzbbcVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zza(int i, int i2, boolean z) {
        this.zzeej.zzi(i, i2);
        zzamf zzamfVar = this.zzcxo;
        if (zzamfVar != null) {
            zzamfVar.zza(i, i2, false);
        }
    }

    public final void zza(String str, zzadx<? super zzbbc> zzadxVar) {
        this.zzehm.zza(str, zzadxVar);
    }

    public final void zzb(String str, zzadx<? super zzbbc> zzadxVar) {
        this.zzehm.zzb(str, zzadxVar);
    }

    public final void zza(String str, Predicate<zzadx<? super zzbbc>> predicate) {
        this.zzehm.zza(str, predicate);
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zza(zzth zzthVar, zzadg zzadgVar, com.google.android.gms.ads.internal.overlay.zzp zzpVar, zzadi zzadiVar, com.google.android.gms.ads.internal.overlay.zzv zzvVar, boolean z, @Nullable zzaea zzaeaVar, com.google.android.gms.ads.internal.zza zzaVar, zzams zzamsVar, @Nullable zzaro zzaroVar) {
        if (zzaVar == null) {
            zzaVar = new com.google.android.gms.ads.internal.zza(this.zzeea.getContext(), zzaroVar, null);
        }
        this.zzcxo = new zzamf(this.zzeea, zzamsVar);
        this.zzeek = zzaroVar;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckf)).booleanValue()) {
            zza("/adMetadata", new zzadd(zzadgVar));
        }
        zza("/appEvent", new zzadf(zzadiVar));
        zza("/backButton", zzadk.zzcxc);
        zza("/refresh", zzadk.zzcxd);
        zza("/canOpenURLs", zzadk.zzcwt);
        zza("/canOpenIntents", zzadk.zzcwu);
        zza("/click", zzadk.zzcwv);
        zza("/close", zzadk.zzcww);
        zza("/customClose", zzadk.zzcwx);
        zza("/instrument", zzadk.zzcxg);
        zza("/delayPageLoaded", zzadk.zzcxi);
        zza("/delayPageClosed", zzadk.zzcxj);
        zza("/getLocationInfo", zzadk.zzcxk);
        zza("/httpTrack", zzadk.zzcwy);
        zza("/log", zzadk.zzcwz);
        zza("/mraid", new zzaec(zzaVar, this.zzcxo, zzamsVar));
        zza("/mraidLoaded", this.zzeej);
        zza("/open", new zzaeb(zzaVar, this.zzcxo));
        zza("/precache", new zzbam());
        zza("/touch", zzadk.zzcxb);
        zza("/video", zzadk.zzcxe);
        zza("/videoMeta", zzadk.zzcxf);
        if (com.google.android.gms.ads.internal.zzp.zzkw().zzaa(this.zzeea.getContext())) {
            zza("/logScionEvent", new zzadz(this.zzeea.getContext()));
        }
        this.zzcbl = zzthVar;
        this.zzdhm = zzpVar;
        this.zzcwp = zzadgVar;
        this.zzcwr = zzadiVar;
        this.zzdhq = zzvVar;
        this.zzcxn = zzaVar;
        this.zzeeg = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final com.google.android.gms.ads.internal.zza zzzq() {
        return this.zzcxn;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final boolean zzzr() {
        return this.zzbni;
    }

    public final boolean zzzs() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzeeh;
        }
        return z;
    }

    public final ViewTreeObserver.OnGlobalLayoutListener zzzt() {
        synchronized (this.lock) {
        }
        return null;
    }

    public final ViewTreeObserver.OnScrollChangedListener zzzu() {
        synchronized (this.lock) {
        }
        return null;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        zzrb zzrbVarZzzo = this.zzeea.zzzo();
        if (zzrbVarZzzo != null && webView == zzrbVarZzzo.getWebView()) {
            zzrbVarZzzo.onPageStarted(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zza(zzbde zzbdeVar) {
        this.zzeel = true;
        zzbco zzbcoVar = this.zzeee;
        if (zzbcoVar != null) {
            zzbcoVar.zzrb();
            this.zzeee = null;
        }
        zzaaa();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(View view, zzaro zzaroVar, int i) {
        if (!zzaroVar.zztj() || i <= 0) {
            return;
        }
        zzaroVar.zzj(view);
        if (zzaroVar.zztj()) {
            zzatv.zzdsk.postDelayed(new zzbcu(this, view, zzaroVar, i), 100L);
        }
    }

    private final void zzzv() {
        if (this.zzeeo == null) {
            return;
        }
        this.zzeea.getView().removeOnAttachStateChangeListener(this.zzeeo);
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzzw() {
        zzaro zzaroVar = this.zzeek;
        if (zzaroVar != null) {
            WebView webView = this.zzeea.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza(webView, zzaroVar, 10);
                return;
            }
            zzzv();
            this.zzeeo = new zzbcx(this, zzaroVar);
            this.zzeea.getView().addOnAttachStateChangeListener(this.zzeeo);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzzx() {
        synchronized (this.lock) {
            this.zzeei = true;
        }
        this.zzeen++;
        zzaaa();
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzzy() {
        this.zzeen--;
        zzaaa();
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzzz() {
        this.zzeem = true;
        zzaaa();
    }

    private final void zzaaa() {
        if (this.zzeed != null && ((this.zzeel && this.zzeen <= 0) || this.zzeem)) {
            this.zzeed.zzab(!this.zzeem);
            this.zzeed = null;
        }
        this.zzeea.zzzi();
    }

    public final void zza(com.google.android.gms.ads.internal.overlay.zzb zzbVar) {
        boolean zZzzd = this.zzeea.zzzd();
        zza(new AdOverlayInfoParcel(zzbVar, (!zZzzd || this.zzeea.zzyw().zzaaq()) ? this.zzcbl : null, zZzzd ? null : this.zzdhm, this.zzdhq, this.zzeea.zzxp()));
    }

    public final void zzc(boolean z, int i) {
        zzth zzthVar = (!this.zzeea.zzzd() || this.zzeea.zzyw().zzaaq()) ? this.zzcbl : null;
        com.google.android.gms.ads.internal.overlay.zzp zzpVar = this.zzdhm;
        com.google.android.gms.ads.internal.overlay.zzv zzvVar = this.zzdhq;
        zzbbc zzbbcVar = this.zzeea;
        zza(new AdOverlayInfoParcel(zzthVar, zzpVar, zzvVar, zzbbcVar, z, i, zzbbcVar.zzxp()));
    }

    public final void zza(boolean z, int i, String str) {
        boolean zZzzd = this.zzeea.zzzd();
        zzth zzthVar = (!zZzzd || this.zzeea.zzyw().zzaaq()) ? this.zzcbl : null;
        zzbcw zzbcwVar = zZzzd ? null : new zzbcw(this.zzeea, this.zzdhm);
        zzadg zzadgVar = this.zzcwp;
        zzadi zzadiVar = this.zzcwr;
        com.google.android.gms.ads.internal.overlay.zzv zzvVar = this.zzdhq;
        zzbbc zzbbcVar = this.zzeea;
        zza(new AdOverlayInfoParcel(zzthVar, zzbcwVar, zzadgVar, zzadiVar, zzvVar, zzbbcVar, z, i, str, zzbbcVar.zzxp()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zZzzd = this.zzeea.zzzd();
        zzth zzthVar = (!zZzzd || this.zzeea.zzyw().zzaaq()) ? this.zzcbl : null;
        zzbcw zzbcwVar = zZzzd ? null : new zzbcw(this.zzeea, this.zzdhm);
        zzadg zzadgVar = this.zzcwp;
        zzadi zzadiVar = this.zzcwr;
        com.google.android.gms.ads.internal.overlay.zzv zzvVar = this.zzdhq;
        zzbbc zzbbcVar = this.zzeea;
        zza(new AdOverlayInfoParcel(zzthVar, zzbcwVar, zzadgVar, zzadiVar, zzvVar, zzbbcVar, z, i, str, str2, zzbbcVar.zzxp()));
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        zzamf zzamfVar = this.zzcxo;
        boolean zZzsg = zzamfVar != null ? zzamfVar.zzsg() : false;
        com.google.android.gms.ads.internal.zzp.zzjx();
        com.google.android.gms.ads.internal.overlay.zzl.zza(this.zzeea.getContext(), adOverlayInfoParcel, zZzsg ? false : true);
        if (this.zzeek != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzdhl != null) {
                str = adOverlayInfoParcel.zzdhl.url;
            }
            this.zzeek.zzdq(str);
        }
    }

    public final void destroy() {
        zzaro zzaroVar = this.zzeek;
        if (zzaroVar != null) {
            zzaroVar.zztl();
            this.zzeek = null;
        }
        zzzv();
        this.zzehm.reset();
        this.zzehm.zzg((zzbbc) null);
        synchronized (this.lock) {
            this.zzcbl = null;
            this.zzdhm = null;
            this.zzeed = null;
            this.zzeee = null;
            this.zzcwp = null;
            this.zzcwr = null;
            this.zzdhq = null;
            this.zzeef = null;
            if (this.zzcxo != null) {
                this.zzcxo.zzv(true);
                this.zzcxo = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zza(zzbcp zzbcpVar) {
        this.zzeed = zzbcpVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zza(zzbco zzbcoVar) {
        this.zzeee = zzbcoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final void zzb(zzbde zzbdeVar) {
        this.zzehm.zzg(zzbdeVar.uri);
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    public final boolean zzc(zzbde zzbdeVar) {
        String strValueOf = String.valueOf(zzbdeVar.url);
        zzatm.zzdy(strValueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(strValueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri uriZza = zzbdeVar.uri;
        if (this.zzehm.zzg(uriZza)) {
            return true;
        }
        if (this.zzeeg) {
            String scheme = uriZza.getScheme();
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                zzth zzthVar = this.zzcbl;
                if (zzthVar != null) {
                    zzthVar.onAdClicked();
                    zzaro zzaroVar = this.zzeek;
                    if (zzaroVar != null) {
                        zzaroVar.zzdq(zzbdeVar.url);
                    }
                    this.zzcbl = null;
                }
                return false;
            }
        }
        if (!this.zzeea.getWebView().willNotDraw()) {
            try {
                zzdf zzdfVarZzzb = this.zzeea.zzzb();
                if (zzdfVarZzzb != null && zzdfVarZzzb.zzc(uriZza)) {
                    uriZza = zzdfVarZzzb.zza(uriZza, this.zzeea.getContext(), this.zzeea.getView(), this.zzeea.zzxl());
                }
            } catch (zzdi unused) {
                String strValueOf2 = String.valueOf(zzbdeVar.url);
                zzatm.zzeu(strValueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(strValueOf2) : new String("Unable to append parameter to URL: "));
            }
            com.google.android.gms.ads.internal.zza zzaVar = this.zzcxn;
            if (zzaVar == null || zzaVar.zzjh()) {
                zza(new com.google.android.gms.ads.internal.overlay.zzb("android.intent.action.VIEW", uriZza.toString(), null, null, null, null, null));
            } else {
                this.zzcxn.zzbl(zzbdeVar.url);
            }
        } else {
            String strValueOf3 = String.valueOf(zzbdeVar.url);
            zzatm.zzeu(strValueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(strValueOf3) : new String("AdWebView unable to handle URL: "));
        }
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzbdf
    @Nullable
    public final WebResourceResponse zzd(zzbde zzbdeVar) {
        String str;
        WebResourceResponse webResourceResponseZzd;
        zzrg zzrgVarZza;
        zzaro zzaroVar = this.zzeek;
        if (zzaroVar != null) {
            zzaroVar.zza(zzbdeVar.url, zzbdeVar.zzab, 1);
        }
        if ("mraid.js".equalsIgnoreCase(new File(zzbdeVar.url).getName())) {
            zzsm();
            if (this.zzeea.zzyw().zzaaq()) {
                str = (String) zzuo.zzoj().zzd(zzyt.zzcia);
            } else if (this.zzeea.zzzd()) {
                str = (String) zzuo.zzoj().zzd(zzyt.zzchz);
            } else {
                str = (String) zzuo.zzoj().zzd(zzyt.zzchy);
            }
            com.google.android.gms.ads.internal.zzp.zzjy();
            webResourceResponseZzd = zzatv.zzd(this.zzeea.getContext(), this.zzeea.zzxp().zzbnh, str);
        } else {
            webResourceResponseZzd = null;
        }
        if (webResourceResponseZzd != null) {
            return webResourceResponseZzd;
        }
        try {
            if (!zzask.zzd(zzbdeVar.url, this.zzeea.getContext(), this.zzdlf).equals(zzbdeVar.url)) {
                return zze(zzbdeVar);
            }
            zzrl zzrlVarZzbt = zzrl.zzbt(zzbdeVar.url);
            if (zzrlVarZzbt != null && (zzrgVarZza = com.google.android.gms.ads.internal.zzp.zzke().zza(zzrlVarZzbt)) != null && zzrgVarZza.zzmg()) {
                return new WebResourceResponse("", "", zzrgVarZza.zzmh());
            }
            if (zzawi.isEnabled()) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclm)).booleanValue()) {
                    return zze(zzbdeVar);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AdWebViewClient.interceptRequest");
            return zzaab();
        }
    }

    private static WebResourceResponse zzaab() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcju)).booleanValue()) {
            return new WebResourceResponse("", "", new ByteArrayInputStream(new byte[0]));
        }
        return null;
    }

    private final WebResourceResponse zze(zzbde zzbdeVar) throws IOException {
        URL url = new URL(zzbdeVar.url);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(10000);
                uRLConnectionOpenConnection.setReadTimeout(10000);
                for (Map.Entry<String, String> entry : zzbdeVar.zzab.entrySet()) {
                    uRLConnectionOpenConnection.addRequestProperty(entry.getKey(), entry.getValue());
                }
                if (!(uRLConnectionOpenConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) uRLConnectionOpenConnection;
                com.google.android.gms.ads.internal.zzp.zzjy().zza(this.zzeea.getContext(), this.zzeea.zzxp().zzbnh, false, httpURLConnection);
                zzawi zzawiVar = new zzawi();
                zzawiVar.zza(httpURLConnection, (byte[]) null);
                int responseCode = httpURLConnection.getResponseCode();
                zzawiVar.zza(httpURLConnection, responseCode);
                if (responseCode >= 300 && responseCode < 400) {
                    String headerField = httpURLConnection.getHeaderField("Location");
                    if (headerField == null) {
                        throw new IOException("Missing Location header in redirect");
                    }
                    URL url2 = new URL(url, headerField);
                    String protocol = url2.getProtocol();
                    if (protocol == null) {
                        zzatm.zzeu("Protocol is null");
                        return zzaab();
                    }
                    if (!protocol.equals("http") && !protocol.equals("https")) {
                        String strValueOf = String.valueOf(protocol);
                        zzatm.zzeu(strValueOf.length() != 0 ? "Unsupported scheme: ".concat(strValueOf) : new String("Unsupported scheme: "));
                        return zzaab();
                    }
                    String strValueOf2 = String.valueOf(headerField);
                    zzatm.zzdv(strValueOf2.length() != 0 ? "Redirecting to ".concat(strValueOf2) : new String("Redirecting to "));
                    httpURLConnection.disconnect();
                    url = url2;
                } else {
                    com.google.android.gms.ads.internal.zzp.zzjy();
                    return zzatv.zzd(httpURLConnection);
                }
            } else {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Too many redirects (20)");
                throw new IOException(sb.toString());
            }
        }
    }

    public final void zzao(boolean z) {
        this.zzeeg = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final zzaro zzaac() {
        return this.zzeek;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzsm() {
        synchronized (this.lock) {
            this.zzeeg = false;
            this.zzbni = true;
            zzawx.zzdwa.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbcv
                private final zzbcs zzeho;

                {
                    this.zzeho = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzbcs zzbcsVar = this.zzeho;
                    zzbcsVar.zzeea.zzzh();
                    com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = zzbcsVar.zzeea.zzyu();
                    if (zzeVarZzyu != null) {
                        zzeVarZzyu.zzsm();
                    }
                }
            });
        }
    }

    public final void zzat(boolean z) {
        this.zzdlf = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzh(int i, int i2) {
        zzamf zzamfVar = this.zzcxo;
        if (zzamfVar != null) {
            zzamfVar.zzh(i, i2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzau(boolean z) {
        synchronized (this.lock) {
            this.zzeeh = true;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzh(Uri uri) {
        this.zzehm.zzh(uri);
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(26)
    public final boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzeea.zzb(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
    }
}
