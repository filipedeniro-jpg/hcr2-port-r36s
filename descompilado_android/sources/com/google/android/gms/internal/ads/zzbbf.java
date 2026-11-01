package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.games.Notifications;
import com.google.android.gms.iid.InstanceID;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@ParametersAreNonnullByDefault
public class zzbbf extends WebViewClient implements zzbcm {
    private static final String[] zzedy = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", InstanceID.ERROR_TIMEOUT, "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzedz = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object lock;

    @GuardedBy("lock")
    private boolean zzbni;
    private zzth zzcbl;
    private zzadg zzcwp;
    private zzadi zzcwr;
    private com.google.android.gms.ads.internal.zza zzcxn;
    private zzamf zzcxo;
    private com.google.android.gms.ads.internal.overlay.zzp zzdhm;
    private com.google.android.gms.ads.internal.overlay.zzv zzdhq;
    private boolean zzdlf;
    protected zzbbc zzeea;

    @Nullable
    private final zzrz zzeeb;
    private final HashMap<String, List<zzadx<? super zzbbc>>> zzeec;
    private zzbcp zzeed;
    private zzbco zzeee;
    private zzbcr zzeef;
    private boolean zzeeg;

    @GuardedBy("lock")
    private boolean zzeeh;

    @GuardedBy("lock")
    private boolean zzeei;
    private final zzamq zzeej;

    @Nullable
    protected zzaro zzeek;
    private boolean zzeel;
    private boolean zzeem;
    private int zzeen;
    private View.OnAttachStateChangeListener zzeeo;

    public zzbbf(zzbbc zzbbcVar, zzrz zzrzVar, boolean z) {
        this(zzbbcVar, zzrzVar, z, new zzamq(zzbbcVar, zzbbcVar.zzyt(), new zzye(zzbbcVar.getContext())), null);
    }

    @VisibleForTesting
    private zzbbf(zzbbc zzbbcVar, zzrz zzrzVar, boolean z, zzamq zzamqVar, zzamf zzamfVar) {
        this.zzeec = new HashMap<>();
        this.lock = new Object();
        this.zzeeg = false;
        this.zzeeb = zzrzVar;
        this.zzeea = zzbbcVar;
        this.zzbni = z;
        this.zzeej = zzamqVar;
        this.zzcxo = null;
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zza(int i, int i2, boolean z) {
        this.zzeej.zzi(i, i2);
        zzamf zzamfVar = this.zzcxo;
        if (zzamfVar != null) {
            zzamfVar.zza(i, i2, false);
        }
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
        boolean z;
        synchronized (this.lock) {
            z = this.zzbni;
        }
        return z;
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
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        zzrb zzrbVarZzzo = this.zzeea.zzzo();
        if (zzrbVarZzzo != null && webView == zzrbVarZzzo.getWebView()) {
            zzrbVarZzzo.onPageStarted(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        synchronized (this.lock) {
            if (this.zzeea.isDestroyed()) {
                zzatm.zzdy("Blank page loaded, 1...");
                this.zzeea.zzze();
                return;
            }
            this.zzeel = true;
            zzbco zzbcoVar = this.zzeee;
            if (zzbcoVar != null) {
                zzbcoVar.zzrb();
                this.zzeee = null;
            }
            zzaaa();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(View view, zzaro zzaroVar, int i) {
        if (!zzaroVar.zztj() || i <= 0) {
            return;
        }
        zzaroVar.zzj(view);
        if (zzaroVar.zztj()) {
            zzatv.zzdsk.postDelayed(new zzbbg(this, view, zzaroVar, i), 100L);
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
            this.zzeeo = new zzbbj(this, zzaroVar);
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
        zzrz zzrzVar = this.zzeeb;
        if (zzrzVar != null) {
            zzrzVar.zza(zzsb.zza.zzb.DELAY_PAGE_LOAD_CANCELLED_AD);
        }
        this.zzeem = true;
        zzaaa();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrq)).booleanValue()) {
            this.zzeea.destroy();
        }
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
        zzbbi zzbbiVar = zZzzd ? null : new zzbbi(this.zzeea, this.zzdhm);
        zzadg zzadgVar = this.zzcwp;
        zzadi zzadiVar = this.zzcwr;
        com.google.android.gms.ads.internal.overlay.zzv zzvVar = this.zzdhq;
        zzbbc zzbbcVar = this.zzeea;
        zza(new AdOverlayInfoParcel(zzthVar, zzbbiVar, zzadgVar, zzadiVar, zzvVar, zzbbcVar, z, i, str, zzbbcVar.zzxp()));
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zZzzd = this.zzeea.zzzd();
        zzth zzthVar = (!zZzzd || this.zzeea.zzyw().zzaaq()) ? this.zzcbl : null;
        zzbbi zzbbiVar = zZzzd ? null : new zzbbi(this.zzeea, this.zzdhm);
        zzadg zzadgVar = this.zzcwp;
        zzadi zzadiVar = this.zzcwr;
        com.google.android.gms.ads.internal.overlay.zzv zzvVar = this.zzdhq;
        zzbbc zzbbcVar = this.zzeea;
        zza(new AdOverlayInfoParcel(zzthVar, zzbbiVar, zzadgVar, zzadiVar, zzvVar, zzbbcVar, z, i, str, str2, zzbbcVar.zzxp()));
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

    public final void zza(String str, zzadx<? super zzbbc> zzadxVar) {
        synchronized (this.lock) {
            List<zzadx<? super zzbbc>> copyOnWriteArrayList = this.zzeec.get(str);
            if (copyOnWriteArrayList == null) {
                copyOnWriteArrayList = new CopyOnWriteArrayList<>();
                this.zzeec.put(str, copyOnWriteArrayList);
            }
            copyOnWriteArrayList.add(zzadxVar);
        }
    }

    public final void zzb(String str, zzadx<? super zzbbc> zzadxVar) {
        synchronized (this.lock) {
            List<zzadx<? super zzbbc>> list = this.zzeec.get(str);
            if (list == null) {
                return;
            }
            list.remove(zzadxVar);
        }
    }

    public final void zza(String str, Predicate<zzadx<? super zzbbc>> predicate) {
        synchronized (this.lock) {
            List<zzadx<? super zzbbc>> list = this.zzeec.get(str);
            if (list == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (zzadx<? super zzbbc> zzadxVar : list) {
                if (predicate.apply(zzadxVar)) {
                    arrayList.add(zzadxVar);
                }
            }
            list.removeAll(arrayList);
        }
    }

    public final void reset() {
        zzaro zzaroVar = this.zzeek;
        if (zzaroVar != null) {
            zzaroVar.zztl();
            this.zzeek = null;
        }
        zzzv();
        synchronized (this.lock) {
            this.zzeec.clear();
            this.zzcbl = null;
            this.zzdhm = null;
            this.zzeed = null;
            this.zzeee = null;
            this.zzcwp = null;
            this.zzcwr = null;
            this.zzeeg = false;
            this.zzbni = false;
            this.zzeeh = false;
            this.zzeei = false;
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

    @Override // android.webkit.WebViewClient
    public final void onLoadResource(WebView webView, String str) {
        String strValueOf = String.valueOf(str);
        zzatm.zzdy(strValueOf.length() != 0 ? "Loading resource: ".concat(strValueOf) : new String("Loading resource: "));
        Uri uri = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(uri.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            zzh(uri);
        }
    }

    /* JADX WARN: Code duplicated, block: B:6:0x000d  */
    @Override // android.webkit.WebViewClient
    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String strValueOf;
        if (i < 0) {
            int i2 = (-i) - 1;
            String[] strArr = zzedy;
            if (i2 < strArr.length) {
                strValueOf = strArr[i2];
            } else {
                strValueOf = String.valueOf(i);
            }
        } else {
            strValueOf = String.valueOf(i);
        }
        zze(this.zzeea.getContext(), "http_err", strValueOf, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0010  */
    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        String strValueOf;
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            if (primaryError >= 0) {
                String[] strArr = zzedz;
                if (primaryError < strArr.length) {
                    strValueOf = strArr[primaryError];
                } else {
                    strValueOf = String.valueOf(primaryError);
                }
            } else {
                strValueOf = String.valueOf(primaryError);
            }
            Context context = this.zzeea.getContext();
            com.google.android.gms.ads.internal.zzp.zzka();
            zze(context, "ssl_err", strValueOf, sslError.getUrl());
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0037  */
    private final void zze(Context context, String str, String str2, String str3) {
        String host;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclx)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            if (TextUtils.isEmpty(str3)) {
                host = "";
            } else {
                Uri uri = Uri.parse(str3);
                if (uri.getHost() != null) {
                    host = uri.getHost();
                } else {
                    host = "";
                }
            }
            bundle.putString("host", host);
            com.google.android.gms.ads.internal.zzp.zzjy().zza(context, this.zzeea.zzxp().zzbnh, "gmob-apps", bundle, true);
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) throws zzdi {
        String strValueOf = String.valueOf(str);
        zzatm.zzdy(strValueOf.length() != 0 ? "AdWebView shouldOverrideUrlLoading: ".concat(strValueOf) : new String("AdWebView shouldOverrideUrlLoading: "));
        Uri uriZza = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(uriZza.getScheme()) && "mobileads.google.com".equalsIgnoreCase(uriZza.getHost())) {
            zzh(uriZza);
        } else {
            if (this.zzeeg && webView == this.zzeea.getWebView()) {
                String scheme = uriZza.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    zzth zzthVar = this.zzcbl;
                    if (zzthVar != null) {
                        zzthVar.onAdClicked();
                        zzaro zzaroVar = this.zzeek;
                        if (zzaroVar != null) {
                            zzaroVar.zzdq(str);
                        }
                        this.zzcbl = null;
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzeea.getWebView().willNotDraw()) {
                try {
                    zzdf zzdfVarZzzb = this.zzeea.zzzb();
                    if (zzdfVarZzzb != null && zzdfVarZzzb.zzc(uriZza)) {
                        uriZza = zzdfVarZzzb.zza(uriZza, this.zzeea.getContext(), this.zzeea.getView(), this.zzeea.zzxl());
                    }
                } catch (zzdi unused) {
                    String strValueOf2 = String.valueOf(str);
                    zzatm.zzeu(strValueOf2.length() != 0 ? "Unable to append parameter to URL: ".concat(strValueOf2) : new String("Unable to append parameter to URL: "));
                }
                com.google.android.gms.ads.internal.zza zzaVar = this.zzcxn;
                if (zzaVar == null || zzaVar.zzjh()) {
                    zza(new com.google.android.gms.ads.internal.overlay.zzb("android.intent.action.VIEW", uriZza.toString(), null, null, null, null, null));
                } else {
                    this.zzcxn.zzbl(str);
                }
            } else {
                String strValueOf3 = String.valueOf(str);
                zzatm.zzeu(strValueOf3.length() != 0 ? "AdWebView unable to handle URL: ".concat(strValueOf3) : new String("AdWebView unable to handle URL: "));
            }
        }
        return true;
    }

    @Override // android.webkit.WebViewClient
    @Nullable
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    @Nullable
    protected final WebResourceResponse zzd(String str, Map<String, String> map) {
        zzrg zzrgVarZza;
        try {
            String strZzd = zzask.zzd(str, this.zzeea.getContext(), this.zzdlf);
            if (!strZzd.equals(str)) {
                return zze(strZzd, map);
            }
            zzrl zzrlVarZzbt = zzrl.zzbt(str);
            if (zzrlVarZzbt != null && (zzrgVarZza = com.google.android.gms.ads.internal.zzp.zzke().zza(zzrlVarZzbt)) != null && zzrgVarZza.zzmg()) {
                return new WebResourceResponse("", "", zzrgVarZza.zzmh());
            }
            if (!zzawi.isEnabled()) {
                return null;
            }
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzclm)).booleanValue()) {
                return zze(str, map);
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

    private final WebResourceResponse zze(String str, Map<String, String> map) throws IOException {
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection uRLConnectionOpenConnection = url.openConnection();
                uRLConnectionOpenConnection.setConnectTimeout(10000);
                uRLConnectionOpenConnection.setReadTimeout(10000);
                for (Map.Entry<String, String> entry : map.entrySet()) {
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
            zzawx.zzdwa.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzbbe
                private final zzbbf zzedx;

                {
                    this.zzedx = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    zzbbf zzbbfVar = this.zzedx;
                    zzbbfVar.zzeea.zzzh();
                    com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = zzbbfVar.zzeea.zzyu();
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

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 79 || keyCode == 222) {
            return true;
        }
        switch (keyCode) {
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
                return true;
            default:
                switch (keyCode) {
                    case 126:
                    case Notifications.NOTIFICATION_TYPES_ALL /* 127 */:
                    case 128:
                    case TsExtractor.TS_STREAM_TYPE_AC3 /* 129 */:
                    case TsExtractor.TS_STREAM_TYPE_HDMV_DTS /* 130 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzh(Uri uri) {
        final String path = uri.getPath();
        List<zzadx<? super zzbbc>> list = this.zzeec.get(path);
        if (list != null) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            Map<String, String> mapZzi = zzatv.zzi(uri);
            if (zzatm.isLoggable(2)) {
                String strValueOf = String.valueOf(path);
                zzatm.zzdy(strValueOf.length() != 0 ? "Received GMSG: ".concat(strValueOf) : new String("Received GMSG: "));
                for (String str : mapZzi.keySet()) {
                    String str2 = mapZzi.get(str);
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 4 + String.valueOf(str2).length());
                    sb.append("  ");
                    sb.append(str);
                    sb.append(": ");
                    sb.append(str2);
                    zzatm.zzdy(sb.toString());
                }
            }
            Iterator<zzadx<? super zzbbc>> it = list.iterator();
            while (it.hasNext()) {
                it.next().zza(this.zzeea, mapZzi);
            }
            return;
        }
        String strValueOf2 = String.valueOf(uri);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 32);
        sb2.append("No GMSG handler found for GMSG: ");
        sb2.append(strValueOf2);
        zzatm.zzdy(sb2.toString());
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzctn)).booleanValue() || com.google.android.gms.ads.internal.zzp.zzkc().zzud() == null) {
            return;
        }
        zzawx.zzdvx.execute(new Runnable(path) { // from class: com.google.android.gms.internal.ads.zzbbh
            private final String zzcys;

            {
                this.zzcys = path;
            }

            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                com.google.android.gms.ads.internal.zzp.zzkc().zzud().zzcm(this.zzcys.substring(1));
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzbcm
    public final void zzau(boolean z) {
        synchronized (this.lock) {
            this.zzeeh = true;
        }
    }

    @Override // android.webkit.WebViewClient
    @TargetApi(26)
    public boolean onRenderProcessGone(WebView webView, RenderProcessGoneDetail renderProcessGoneDetail) {
        return this.zzeea.zzb(renderProcessGoneDetail.didCrash(), renderProcessGoneDetail.rendererPriorityAtExit());
    }
}
