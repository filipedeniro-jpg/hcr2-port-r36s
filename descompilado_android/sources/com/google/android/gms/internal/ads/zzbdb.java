package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.amazon.device.ads.WebRequest;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.ironsource.sdk.ISNAdView.ISNAdViewConstants;
import com.my.target.aa;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@ParametersAreNonnullByDefault
final class zzbdb extends zzbdh implements ViewTreeObserver.OnGlobalLayoutListener, DownloadListener, zzagb, zzbbc {
    private int maxHeight;
    private int maxWidth;

    @GuardedBy("this")
    private String zzabd;
    private final zzawv zzblh;
    private final WindowManager zzbnm;
    private int zzdgc;
    private int zzdgd;

    @GuardedBy("this")
    private boolean zzdho;

    @GuardedBy("this")
    private String zzdis;
    private zzzf zzeag;
    private final zzrz zzeeb;
    private final zzbcq zzefl;

    @Nullable
    private final zzdf zzefm;
    private final com.google.android.gms.ads.internal.zzk zzefn;
    private final com.google.android.gms.ads.internal.zzb zzefo;

    @Nullable
    private final zzrb zzefq;
    private final boolean zzefr;

    @GuardedBy("this")
    private com.google.android.gms.ads.internal.overlay.zze zzefv;

    @GuardedBy("this")
    private zzbct zzefx;

    @GuardedBy("this")
    private boolean zzefz;

    @GuardedBy("this")
    private boolean zzega;

    @GuardedBy("this")
    private int zzegb;

    @GuardedBy("this")
    private boolean zzegc;

    @GuardedBy("this")
    private boolean zzegd;

    @GuardedBy("this")
    private zzbbu zzege;

    @GuardedBy("this")
    private boolean zzegf;

    @GuardedBy("this")
    private boolean zzegg;

    @GuardedBy("this")
    private zzaag zzegh;

    @GuardedBy("this")
    private zzaab zzegi;

    @GuardedBy("this")
    private zzqt zzegj;

    @GuardedBy("this")
    private int zzegk;

    @GuardedBy("this")
    private int zzegl;
    private zzzf zzegm;
    private zzzf zzegn;
    private zzzi zzego;
    private WeakReference<View.OnClickListener> zzegp;

    @GuardedBy("this")
    private com.google.android.gms.ads.internal.overlay.zze zzegq;
    private zzawf zzegs;
    private Map<String, zzbah> zzegt;
    private final zzbcs zzehp;
    private final AtomicReference<IObjectWrapper> zzehq;
    private final DisplayMetrics zzwl;

    @VisibleForTesting
    protected zzbdb(zzbcq zzbcqVar, zzbcs zzbcsVar, zzbct zzbctVar, String str, boolean z, boolean z2, @Nullable zzdf zzdfVar, zzawv zzawvVar, zzzh zzzhVar, com.google.android.gms.ads.internal.zzk zzkVar, com.google.android.gms.ads.internal.zzb zzbVar, zzrz zzrzVar, zzrb zzrbVar, boolean z3) {
        super(zzbcqVar, zzbcsVar);
        this.zzegc = true;
        this.zzegd = false;
        this.zzdis = "";
        this.zzehq = new AtomicReference<>();
        this.zzdgd = -1;
        this.zzdgc = -1;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.zzefl = zzbcqVar;
        this.zzehp = zzbcsVar;
        this.zzefx = zzbctVar;
        this.zzabd = str;
        this.zzefz = z;
        this.zzegb = -1;
        this.zzefm = zzdfVar;
        this.zzblh = zzawvVar;
        this.zzefn = zzkVar;
        this.zzefo = zzbVar;
        this.zzbnm = (WindowManager) getContext().getSystemService("window");
        com.google.android.gms.ads.internal.zzp.zzjy();
        this.zzwl = zzatv.zza(this.zzbnm);
        this.zzeeb = zzrzVar;
        this.zzefq = zzrbVar;
        this.zzefr = z3;
        this.zzegs = new zzawf(this.zzefl.zzxl(), this, this, null);
        com.google.android.gms.ads.internal.zzp.zzjy().zza(zzbcqVar, zzawvVar.zzbnh, getSettings());
        setDownloadListener(this);
        zzaag();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(zzbbz.zzc(this), "googleAdsJsInterface");
        }
        zzaak();
        this.zzego = new zzzi(new zzzh(true, "make_wv", this.zzabd));
        this.zzego.zzpy().zzc(zzzhVar);
        this.zzeag = zzzc.zzb(this.zzego.zzpy());
        this.zzego.zza("native:view_create", this.zzeag);
        this.zzegn = null;
        this.zzegm = null;
        com.google.android.gms.ads.internal.zzp.zzka().zzbb(zzbcqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcn
    public final View getView() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final WebView getWebView() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, Map map) {
        zzage.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, JSONObject jSONObject) {
        zzage.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagu
    public final void zzb(String str, JSONObject jSONObject) {
        zzage.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagb
    public final void zzk(String str, String str2) {
        zzage.zza(this, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final zzayi zzxi() {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, com.google.android.gms.internal.ads.zzbbc
    public final void zzze() {
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzn() {
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final com.google.android.gms.ads.internal.zzb zzxm() {
        return this.zzefo;
    }

    private final boolean zzaad() {
        int i;
        int iZzb;
        if (!this.zzehp.zzzr() && !this.zzehp.zzzs()) {
            return false;
        }
        zzuo.zzof();
        DisplayMetrics displayMetrics = this.zzwl;
        int iZzb2 = zzawe.zzb(displayMetrics, displayMetrics.widthPixels);
        zzuo.zzof();
        DisplayMetrics displayMetrics2 = this.zzwl;
        int iZzb3 = zzawe.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity activityZzxl = this.zzefl.zzxl();
        if (activityZzxl == null || activityZzxl.getWindow() == null) {
            i = iZzb2;
            iZzb = iZzb3;
        } else {
            com.google.android.gms.ads.internal.zzp.zzjy();
            int[] iArrZzd = zzatv.zzd(activityZzxl);
            zzuo.zzof();
            int iZzb4 = zzawe.zzb(this.zzwl, iArrZzd[0]);
            zzuo.zzof();
            iZzb = zzawe.zzb(this.zzwl, iArrZzd[1]);
            i = iZzb4;
        }
        if (this.zzdgc == iZzb2 && this.zzdgd == iZzb3 && this.maxWidth == i && this.maxHeight == iZzb) {
            return false;
        }
        boolean z = (this.zzdgc == iZzb2 && this.zzdgd == iZzb3) ? false : true;
        this.zzdgc = iZzb2;
        this.zzdgd = iZzb3;
        this.maxWidth = i;
        this.maxHeight = iZzb;
        new zzamp(this).zza(iZzb2, iZzb3, i, iZzb, this.zzwl.density, this.zzbnm.getDefaultDisplay().getRotation());
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzb(String str, String str2, @Nullable String str3) {
        super.loadDataWithBaseURL(str, zzbcj.zzf(str2, zzbcj.zzaam()), WebRequest.CONTENT_TYPE_HTML, "UTF-8", str3);
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, com.google.android.gms.internal.ads.zzbdc, com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzagu
    public final synchronized void zzct(String str) {
        if (!isDestroyed()) {
            super.zzct(str);
        } else {
            zzatm.zzeu("The webview is destroyed. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzyr() {
        zzaaf();
        HashMap map = new HashMap(1);
        map.put("version", this.zzblh.zzbnh);
        zzage.zza(this, "onhide", map);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzdc(int i) {
        if (i == 0) {
            zzzc.zza(this.zzego.zzpy(), this.zzeag, "aebb2");
        }
        zzaaf();
        if (this.zzego.zzpy() != null) {
            this.zzego.zzpy().zzj("close_type", String.valueOf(i));
        }
        HashMap map = new HashMap(2);
        map.put("closetype", String.valueOf(i));
        map.put("version", this.zzblh.zzbnh);
        zzage.zza(this, "onhide", map);
    }

    private final void zzaaf() {
        zzzc.zza(this.zzego.zzpy(), this.zzeag, "aeh2");
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzsq() {
        if (this.zzegm == null) {
            zzzc.zza(this.zzego.zzpy(), this.zzeag, "aes2");
            this.zzegm = zzzc.zzb(this.zzego.zzpy());
            this.zzego.zza("native:view_show", this.zzegm);
        }
        HashMap map = new HashMap(1);
        map.put("version", this.zzblh.zzbnh);
        zzage.zza(this, "onshow", map);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzys() {
        HashMap map = new HashMap(3);
        map.put("app_muted", String.valueOf(com.google.android.gms.ads.internal.zzp.zzkd().zzop()));
        map.put("app_volume", String.valueOf(com.google.android.gms.ads.internal.zzp.zzkd().zzoo()));
        map.put("device_volume", String.valueOf(zzauk.zzbd(getContext())));
        zzage.zza(this, "volume", map);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zza(boolean z, long j) {
        HashMap map = new HashMap(2);
        map.put("success", z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        map.put("duration", Long.toString(j));
        zzage.zza(this, "onCacheAccessComplete", map);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized com.google.android.gms.ads.internal.overlay.zze zzyu() {
        return this.zzefv;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final IObjectWrapper zzzc() {
        return this.zzehq.get();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized com.google.android.gms.ads.internal.overlay.zze zzyv() {
        return this.zzegq;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbci
    public final synchronized zzbct zzyw() {
        return this.zzefx;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized String zzyx() {
        return this.zzabd;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final WebViewClient zzyz() {
        return this.zzehp;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized boolean zzza() {
        return this.zzdho;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcl
    public final zzdf zzzb() {
        return this.zzefm;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbck
    public final zzawv zzxp() {
        return this.zzblh;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcc
    public final synchronized boolean zzzd() {
        return this.zzefz;
    }

    @Override // android.webkit.DownloadListener
    public final void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            com.google.android.gms.ads.internal.zzp.zzjy();
            zzatv.zza(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 51 + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            zzatm.zzdv(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, android.webkit.WebView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzehp.zzzs()) {
            synchronized (this) {
                if (this.zzegh != null) {
                    this.zzegh.zzc(motionEvent);
                }
            }
        } else {
            zzdf zzdfVar = this.zzefm;
            if (zzdfVar != null) {
                zzdfVar.zzb(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        float axisValue = motionEvent.getAxisValue(9);
        float axisValue2 = motionEvent.getAxisValue(10);
        if (motionEvent.getActionMasked() == 8) {
            if (axisValue > 0.0f && !canScrollVertically(-1)) {
                return false;
            }
            if (axisValue < 0.0f && !canScrollVertically(1)) {
                return false;
            }
            if (axisValue2 > 0.0f && !canScrollHorizontally(-1)) {
                return false;
            }
            if (axisValue2 < 0.0f && !canScrollHorizontally(1)) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    protected final synchronized void onMeasure(int i, int i2) {
        int size;
        if (isDestroyed()) {
            setMeasuredDimension(0, 0);
            return;
        }
        if (!isInEditMode() && !this.zzefz && !this.zzefx.zzaar()) {
            if (this.zzefx.zzaat()) {
                super.onMeasure(i, i2);
                return;
            }
            if (this.zzefx.zzaas()) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcod)).booleanValue()) {
                    super.onMeasure(i, i2);
                    return;
                }
                zzbbu zzbbuVarZzxj = zzxj();
                float aspectRatio = zzbbuVarZzxj != null ? zzbbuVarZzxj.getAspectRatio() : 0.0f;
                if (aspectRatio == 0.0f) {
                    super.onMeasure(i, i2);
                    return;
                }
                int size2 = View.MeasureSpec.getSize(i);
                int size3 = View.MeasureSpec.getSize(i2);
                int i3 = (int) (size3 * aspectRatio);
                int i4 = (int) (size2 / aspectRatio);
                if (size3 == 0 && i4 != 0) {
                    i3 = (int) (i4 * aspectRatio);
                    size3 = i4;
                } else if (size2 == 0 && i3 != 0) {
                    i4 = (int) (i3 / aspectRatio);
                    size2 = i3;
                }
                setMeasuredDimension(Math.min(i3, size2), Math.min(i4, size3));
                return;
            }
            if (this.zzefx.isFluid()) {
                if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcog)).booleanValue() && PlatformVersion.isAtLeastJellyBeanMR1()) {
                    zza("/contentHeight", new zzbdd(this));
                    zzct("(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();");
                    int size4 = View.MeasureSpec.getSize(i);
                    if (this.zzegl == -1) {
                        size = View.MeasureSpec.getSize(i2);
                    } else {
                        size = (int) (this.zzegl * this.zzwl.density);
                    }
                    setMeasuredDimension(size4, size);
                    return;
                }
                super.onMeasure(i, i2);
                return;
            }
            if (this.zzefx.zzaaq()) {
                setMeasuredDimension(this.zzwl.widthPixels, this.zzwl.heightPixels);
                return;
            }
            int mode = View.MeasureSpec.getMode(i);
            int size5 = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size6 = View.MeasureSpec.getSize(i2);
            int i5 = (mode == Integer.MIN_VALUE || mode == 1073741824) ? size5 : Integer.MAX_VALUE;
            int i6 = (mode2 == Integer.MIN_VALUE || mode2 == 1073741824) ? size6 : Integer.MAX_VALUE;
            boolean z = true;
            boolean z2 = this.zzefx.widthPixels > i5 || this.zzefx.heightPixels > i6;
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcrb)).booleanValue()) {
                if (this.zzefx.widthPixels / this.zzwl.density > i5 / this.zzwl.density || this.zzefx.heightPixels / this.zzwl.density > i6 / this.zzwl.density) {
                    z = false;
                }
                if (z2) {
                    z2 = z;
                }
            }
            if (z2) {
                int i7 = (int) (this.zzefx.widthPixels / this.zzwl.density);
                int i8 = (int) (this.zzefx.heightPixels / this.zzwl.density);
                int i9 = (int) (size5 / this.zzwl.density);
                int i10 = (int) (size6 / this.zzwl.density);
                StringBuilder sb = new StringBuilder(103);
                sb.append("Not enough space to show ad. Needs ");
                sb.append(i7);
                sb.append("x");
                sb.append(i8);
                sb.append(" dp, but only has ");
                sb.append(i9);
                sb.append("x");
                sb.append(i10);
                sb.append(" dp.");
                zzatm.zzeu(sb.toString());
                if (getVisibility() != 8) {
                    setVisibility(4);
                }
                setMeasuredDimension(0, 0);
                return;
            }
            if (getVisibility() != 8) {
                setVisibility(0);
            }
            setMeasuredDimension(this.zzefx.widthPixels, this.zzefx.heightPixels);
            return;
        }
        super.onMeasure(i, i2);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        boolean zZzaad = zzaad();
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = zzyu();
        if (zzeVarZzyu == null || !zZzaad) {
            return;
        }
        zzeVarZzyu.zzsp();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zza(com.google.android.gms.ads.internal.overlay.zze zzeVar) {
        this.zzefv = zzeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzaq(IObjectWrapper iObjectWrapper) {
        this.zzehq.set(iObjectWrapper);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzb(com.google.android.gms.ads.internal.overlay.zze zzeVar) {
        this.zzegq = zzeVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zza(zzbct zzbctVar) {
        this.zzefx = zzbctVar;
        requestLayout();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzaq(boolean z) {
        boolean z2 = z != this.zzefz;
        this.zzefz = z;
        zzaag();
        if (z2) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcic)).booleanValue() || !this.zzefx.zzaaq()) {
                new zzamp(this).zzdp(z ? aa.f.bt : aa.f.bq);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzh() {
        this.zzegs.zzwe();
    }

    @Override // android.webkit.WebView, android.view.ViewGroup, android.view.View
    protected final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.zzegs.onAttachedToWindow();
        }
        boolean z = this.zzegf;
        if (this.zzehp != null && this.zzehp.zzzs()) {
            if (!this.zzegg) {
                this.zzehp.zzzt();
                this.zzehp.zzzu();
                this.zzegg = true;
            }
            zzaad();
            z = true;
        }
        zzav(z);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.zzegs.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
            if (this.zzegg && this.zzehp != null && this.zzehp.zzzs() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                this.zzehp.zzzt();
                this.zzehp.zzzu();
                this.zzegg = false;
            }
        }
        zzav(false);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzbq(Context context) {
        this.zzefl.setBaseContext(context);
        this.zzegs.zzh(this.zzefl.zzxl());
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzae(boolean z) {
        if (this.zzefv != null) {
            this.zzefv.zza(this.zzehp.zzzr(), z);
        } else {
            this.zzdho = z;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void setRequestedOrientation(int i) {
        this.zzegb = i;
        if (this.zzefv != null) {
            this.zzefv.setRequestedOrientation(this.zzegb);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbcd
    public final Activity zzxl() {
        return this.zzefl.zzxl();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Context zzyt() {
        return this.zzefl.zzyt();
    }

    private final synchronized void zzaag() {
        if (!this.zzefz && !this.zzefx.zzaaq()) {
            if (Build.VERSION.SDK_INT < 18) {
                zzatm.zzdv("Disabling hardware acceleration on an AdView.");
                zzaah();
                return;
            } else {
                zzatm.zzdv("Enabling hardware acceleration on an AdView.");
                zzaai();
                return;
            }
        }
        zzatm.zzdv("Enabling hardware acceleration on an overlay.");
        zzaai();
    }

    private final synchronized void zzaah() {
        if (!this.zzega) {
            com.google.android.gms.ads.internal.zzp.zzka();
            setLayerType(1, null);
        }
        this.zzega = true;
    }

    private final synchronized void zzaai() {
        if (this.zzega) {
            com.google.android.gms.ads.internal.zzp.zzka();
            setLayerType(0, null);
        }
        this.zzega = false;
    }

    @Override // com.google.android.gms.internal.ads.zzbdh
    protected final synchronized void zzaw(boolean z) {
        if (!z) {
            zzaak();
            this.zzegs.zzwf();
            if (this.zzefv != null) {
                this.zzefv.close();
                this.zzefv.onDestroy();
                this.zzefv = null;
            }
        }
        this.zzehq.set(null);
        this.zzehp.destroy();
        com.google.android.gms.ads.internal.zzp.zzku();
        zzbae.zzc(this);
        zzaaj();
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, android.webkit.WebView, android.view.View
    @TargetApi(21)
    protected final void onDraw(Canvas canvas) {
        if (Build.VERSION.SDK_INT == 21 && canvas.isHardwareAccelerated() && !isAttachedToWindow()) {
            return;
        }
        super.onDraw(canvas);
        zzbcs zzbcsVar = this.zzehp;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzi() {
        if (this.zzegn == null) {
            this.zzegn = zzzc.zzb(this.zzego.zzpy());
            this.zzego.zza("native:view_load", this.zzegn);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, android.webkit.WebView, com.google.android.gms.internal.ads.zzbbc
    public final void onPause() {
        try {
            super.onPause();
        } catch (Exception e) {
            zzatm.zzc("Could not pause webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, android.webkit.WebView, com.google.android.gms.internal.ads.zzbbc
    public final void onResume() {
        try {
            super.onResume();
        } catch (Exception e) {
            zzatm.zzc("Could not resume webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzl() {
        zzatm.zzdy("Cannot add text view to inner AdWebView");
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzat(boolean z) {
        this.zzehp.zzat(z);
    }

    @Override // com.google.android.gms.internal.ads.zzbdh, android.webkit.WebView
    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception e) {
            zzatm.zzc("Could not stop loading webview.", e);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzar(boolean z) {
        this.zzegc = z;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized boolean zzzf() {
        return this.zzegc;
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final synchronized void zzju() {
        this.zzegd = true;
        if (this.zzefn != null) {
            this.zzefn.zzju();
        }
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final synchronized void zzjv() {
        this.zzegd = false;
        if (this.zzefn != null) {
            this.zzefn.zzjv();
        }
    }

    private final synchronized void zzaaj() {
        if (this.zzegt != null) {
            Iterator<zzbah> it = this.zzegt.values().iterator();
            while (it.hasNext()) {
                it.next().release();
            }
        }
        this.zzegt = null;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final synchronized void zza(String str, zzbah zzbahVar) {
        if (this.zzegt == null) {
            this.zzegt = new HashMap();
        }
        this.zzegt.put(str, zzbahVar);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final synchronized zzbah zzez(String str) {
        if (this.zzegt == null) {
            return null;
        }
        return this.zzegt.get(str);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final synchronized String zzxn() {
        return this.zzdis;
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final zzzf zzxk() {
        return this.zzeag;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final zzzi zzxo() {
        return this.zzego;
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzbbc
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzegp = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zza(zzaag zzaagVar) {
        this.zzegh = zzaagVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized zzaag zzzj() {
        return this.zzegh;
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final synchronized void zzxs() {
        if (this.zzegi != null) {
            this.zzegi.zzqe();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zza(zzaab zzaabVar) {
        this.zzegi = zzaabVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zza(zzqt zzqtVar) {
        this.zzegj = zzqtVar;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized zzqt zzzm() {
        return this.zzegj;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final synchronized zzbbu zzxj() {
        return this.zzege;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final synchronized void zza(zzbbu zzbbuVar) {
        if (this.zzege != null) {
            zzatm.zzes("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.zzege = zzbbuVar;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized boolean zzzg() {
        return this.zzegk > 0;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final synchronized void zzas(boolean z) {
        this.zzegk += z ? 1 : -1;
        if (this.zzegk <= 0 && this.zzefv != null) {
            this.zzefv.zzss();
        }
    }

    private final void zzaak() {
        zzzh zzzhVarZzpy;
        zzzi zzziVar = this.zzego;
        if (zzziVar == null || (zzzhVarZzpy = zzziVar.zzpy()) == null || com.google.android.gms.ads.internal.zzp.zzkc().zzud() == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzkc().zzud().zza(zzzhVarZzpy);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzk() {
        setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zzao(boolean z) {
        this.zzehp.zzao(z);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zzsr() {
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = zzyu();
        if (zzeVarZzyu != null) {
            zzeVarZzyu.zzsr();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final int zzxq() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final int zzxr() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(com.google.android.gms.ads.internal.overlay.zzb zzbVar) {
        this.zzehp.zza(zzbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zzc(boolean z, int i) {
        this.zzehp.zzc(z, i);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(boolean z, int i, String str) {
        this.zzehp.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(boolean z, int i, String str, String str2) {
        this.zzehp.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final void zza(zzpi zzpiVar) {
        synchronized (this) {
            this.zzegf = zzpiVar.zzbnr;
        }
        zzav(zzpiVar.zzbnr);
    }

    private final void zzav(boolean z) {
        HashMap map = new HashMap();
        map.put(ISNAdViewConstants.IS_VISIBLE_KEY, z ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zzage.zza(this, "onAdVisibilityChanged", map);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(String str, zzadx<? super zzbbc> zzadxVar) {
        zzbcs zzbcsVar = this.zzehp;
        if (zzbcsVar != null) {
            zzbcsVar.zza(str, zzadxVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzb(String str, zzadx<? super zzbbc> zzadxVar) {
        zzbcs zzbcsVar = this.zzehp;
        if (zzbcsVar != null) {
            zzbcsVar.zzb(str, zzadxVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(String str, Predicate<zzadx<? super zzbbc>> predicate) {
        zzbcs zzbcsVar = this.zzehp;
        if (zzbcsVar != null) {
            zzbcsVar.zza(str, predicate);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzb(final boolean z, final int i) {
        destroy();
        this.zzeeb.zza(new zzry(z, i) { // from class: com.google.android.gms.internal.ads.zzbda
            private final int zzdsy;
            private final boolean zzegw;

            {
                this.zzegw = z;
                this.zzdsy = i;
            }

            @Override // com.google.android.gms.internal.ads.zzry
            public final void zza(zztd zztdVar) {
                zzbdb.zza(this.zzegw, this.zzdsy, zztdVar);
            }
        });
        this.zzeeb.zza(zzsb.zza.zzb.ANDROID_WEBVIEW_CRASH);
        return true;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(ViewGroup viewGroup, Activity activity, String str, String str2) {
        if (!zzzp()) {
            zzatm.zzdy("AR ad is not enabled or the ad from the server is not an AR ad.");
            return;
        }
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
        }
        zzatm.zzdy("Initializing ArWebView object.");
        this.zzefq.zza(activity, this);
        this.zzefq.zze(str, str2);
        if (viewGroup != null) {
            viewGroup.addView(this.zzefq.getView());
        } else {
            zzatm.zzes("The FrameLayout object cannot be null.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final zzrb zzzo() {
        return this.zzefq;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzp() {
        return ((Boolean) zzuo.zzoj().zzd(zzyt.zzcsy)).booleanValue() && this.zzefq != null && this.zzefr;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final /* synthetic */ zzbcm zzyy() {
        return this.zzehp;
    }

    static final /* synthetic */ void zza(boolean z, int i, zztd zztdVar) {
        zzsh.zzw.zza zzaVarZznt = zzsh.zzw.zznt();
        if (zzaVarZznt.zzns() != z) {
            zzaVarZznt.zzq(z);
        }
        zztdVar.zzcan = (zzsh.zzw) ((zzdqd) zzaVarZznt.zzcg(i).zzazm());
    }
}
