package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.amazon.device.ads.WebRequest;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzamp;
import com.google.android.gms.internal.ads.zzamx;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzatv;
import com.google.android.gms.internal.ads.zzaub;
import com.google.android.gms.internal.ads.zzbbc;
import com.google.android.gms.internal.ads.zzbbl;
import com.google.android.gms.internal.ads.zzbcm;
import com.google.android.gms.internal.ads.zzbcp;
import com.google.android.gms.internal.ads.zzrz;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;
import com.my.target.ab;
import java.util.Collections;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class zze extends zzamx implements zzw {

    @VisibleForTesting
    private static final int zzdgn = Color.argb(0, 0, 0, 0);

    @VisibleForTesting
    zzbbc zzcyx;

    @VisibleForTesting
    AdOverlayInfoParcel zzdgo;

    @VisibleForTesting
    private zzk zzdgp;

    @VisibleForTesting
    private zzo zzdgq;

    @VisibleForTesting
    private FrameLayout zzdgs;

    @VisibleForTesting
    private WebChromeClient.CustomViewCallback zzdgt;

    @VisibleForTesting
    private zzh zzdgv;
    private Runnable zzdgz;
    private boolean zzdha;
    private boolean zzdhb;
    protected final Activity zzzr;

    @VisibleForTesting
    private boolean zzdgr = false;

    @VisibleForTesting
    private boolean zzdgu = false;

    @VisibleForTesting
    private boolean zzbky = false;

    @VisibleForTesting
    private boolean zzdgw = false;

    @VisibleForTesting
    int zzdgx = 0;
    private final Object zzdgy = new Object();
    private boolean zzdhc = false;
    private boolean zzdhd = false;
    private boolean zzdhe = true;

    public zze(Activity activity) {
        this.zzzr = activity;
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onActivityResult(int i, int i2, Intent intent) {
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onRestart() {
    }

    public final void close() {
        this.zzdgx = 2;
        this.zzzr.finish();
    }

    public final void zzsj() {
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdgo;
        if (adOverlayInfoParcel != null && this.zzdgr) {
            setRequestedOrientation(adOverlayInfoParcel.orientation);
        }
        if (this.zzdgs != null) {
            this.zzzr.setContentView(this.zzdgv);
            this.zzdhb = true;
            this.zzdgs.removeAllViews();
            this.zzdgs = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback = this.zzdgt;
        if (customViewCallback != null) {
            customViewCallback.onCustomViewHidden();
            this.zzdgt = null;
        }
        this.zzdgr = false;
    }

    @Override // com.google.android.gms.ads.internal.overlay.zzw
    public final void zzsk() {
        this.zzdgx = 1;
        this.zzzr.finish();
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onBackPressed() {
        this.zzdgx = 0;
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final boolean zzsl() {
        this.zzdgx = 0;
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar == null) {
            return true;
        }
        boolean zZzzf = zzbbcVar.zzzf();
        if (!zZzzf) {
            this.zzcyx.zza("onbackblocked", Collections.emptyMap());
        }
        return zZzzf;
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public void onCreate(Bundle bundle) {
        this.zzzr.requestWindowFeature(1);
        this.zzdgu = bundle != null && bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        try {
            this.zzdgo = AdOverlayInfoParcel.zzc(this.zzzr.getIntent());
            if (this.zzdgo == null) {
                throw new zzi("Could not get info for ad overlay.");
            }
            if (this.zzdgo.zzblh.zzdvt > 7500000) {
                this.zzdgx = 3;
            }
            if (this.zzzr.getIntent() != null) {
                this.zzdhe = this.zzzr.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzdgo.zzdht != null) {
                this.zzbky = this.zzdgo.zzdht.zzbky;
            } else {
                this.zzbky = false;
            }
            if (this.zzbky && this.zzdgo.zzdht.zzbld != -1) {
                new zzj(this).zzup();
            }
            if (bundle == null) {
                if (this.zzdgo.zzdhm != null && this.zzdhe) {
                    this.zzdgo.zzdhm.zzsf();
                }
                if (this.zzdgo.zzdhr != 1 && this.zzdgo.zzcbl != null) {
                    this.zzdgo.zzcbl.onAdClicked();
                }
            }
            this.zzdgv = new zzh(this.zzzr, this.zzdgo.zzdhs, this.zzdgo.zzblh.zzbnh);
            this.zzdgv.setId(1000);
            com.google.android.gms.ads.internal.zzp.zzka().zzg(this.zzzr);
            int i = this.zzdgo.zzdhr;
            if (i == 1) {
                zzad(false);
                return;
            }
            if (i == 2) {
                this.zzdgp = new zzk(this.zzdgo.zzcyx);
                zzad(false);
            } else {
                if (i == 3) {
                    zzad(true);
                    return;
                }
                throw new zzi("Could not determine ad overlay type.");
            }
        } catch (zzi e) {
            zzatm.zzeu(e.getMessage());
            this.zzdgx = 3;
            this.zzzr.finish();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onStart() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqa)).booleanValue()) {
            zzbbc zzbbcVar = this.zzcyx;
            if (zzbbcVar != null && !zzbbcVar.isDestroyed()) {
                com.google.android.gms.ads.internal.zzp.zzka();
                zzaub.zzb(this.zzcyx);
            } else {
                zzatm.zzeu("The webview does not exist. Ignoring action.");
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onResume() {
        if (this.zzdgo.zzdhm != null) {
            this.zzdgo.zzdhm.onResume();
        }
        zza(this.zzzr.getResources().getConfiguration());
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqa)).booleanValue()) {
            return;
        }
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null && !zzbbcVar.isDestroyed()) {
            com.google.android.gms.ads.internal.zzp.zzka();
            zzaub.zzb(this.zzcyx);
        } else {
            zzatm.zzeu("The webview does not exist. Ignoring action.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onPause() {
        zzsj();
        if (this.zzdgo.zzdhm != null) {
            this.zzdgo.zzdhm.onPause();
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcqa)).booleanValue() && this.zzcyx != null && (!this.zzzr.isFinishing() || this.zzdgp == null)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            zzaub.zza(this.zzcyx);
        }
        zzsn();
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void zzag(IObjectWrapper iObjectWrapper) {
        zza((Configuration) ObjectWrapper.unwrap(iObjectWrapper));
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzdgu);
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onStop() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqa)).booleanValue() && this.zzcyx != null && (!this.zzzr.isFinishing() || this.zzdgp == null)) {
            com.google.android.gms.ads.internal.zzp.zzka();
            zzaub.zza(this.zzcyx);
        }
        zzsn();
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void onDestroy() {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            this.zzdgv.removeView(zzbbcVar.getView());
        }
        zzsn();
    }

    private final void zzac(boolean z) {
        int iIntValue = ((Integer) zzuo.zzoj().zzd(zzyt.zzcqc)).intValue();
        zzr zzrVar = new zzr();
        zzrVar.size = 50;
        zzrVar.paddingLeft = z ? iIntValue : 0;
        zzrVar.paddingRight = z ? 0 : iIntValue;
        zzrVar.paddingTop = 0;
        zzrVar.paddingBottom = iIntValue;
        this.zzdgq = new zzo(this.zzzr, zzrVar, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        zza(z, this.zzdgo.zzdho);
        this.zzdgv.addView(this.zzdgq, layoutParams);
    }

    @Override // com.google.android.gms.internal.ads.zzamu
    public final void zzcz() {
        this.zzdhb = true;
    }

    public final void zza(boolean z, boolean z2) {
        AdOverlayInfoParcel adOverlayInfoParcel;
        AdOverlayInfoParcel adOverlayInfoParcel2;
        boolean z3 = true;
        boolean z4 = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcko)).booleanValue() && (adOverlayInfoParcel2 = this.zzdgo) != null && adOverlayInfoParcel2.zzdht != null && this.zzdgo.zzdht.zzblf;
        boolean z5 = ((Boolean) zzuo.zzoj().zzd(zzyt.zzckp)).booleanValue() && (adOverlayInfoParcel = this.zzdgo) != null && adOverlayInfoParcel.zzdht != null && this.zzdgo.zzdht.zzblg;
        if (z && z2 && z4 && !z5) {
            new zzamp(this.zzcyx, ab.by).zzdn("Custom close has been disabled for interstitial ads in this ad slot.");
        }
        zzo zzoVar = this.zzdgq;
        if (zzoVar != null) {
            if (!z5 && (!z2 || z4)) {
                z3 = false;
            }
            zzoVar.zzae(z3);
        }
    }

    public final void zzsm() {
        this.zzdgv.removeView(this.zzdgq);
        zzac(true);
    }

    public final void setRequestedOrientation(int i) {
        if (this.zzzr.getApplicationInfo().targetSdkVersion >= ((Integer) zzuo.zzoj().zzd(zzyt.zzcse)).intValue()) {
            if (this.zzzr.getApplicationInfo().targetSdkVersion <= ((Integer) zzuo.zzoj().zzd(zzyt.zzcsf)).intValue()) {
                if (Build.VERSION.SDK_INT >= ((Integer) zzuo.zzoj().zzd(zzyt.zzcsg)).intValue()) {
                    if (Build.VERSION.SDK_INT <= ((Integer) zzuo.zzoj().zzd(zzyt.zzcsh)).intValue()) {
                        return;
                    }
                }
            }
        }
        try {
            this.zzzr.setRequestedOrientation(i);
        } catch (Throwable th) {
            com.google.android.gms.ads.internal.zzp.zzkc().zzb(th, "AdOverlay.setRequestedOrientation");
        }
    }

    public final void zza(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        this.zzdgs = new FrameLayout(this.zzzr);
        this.zzdgs.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.zzdgs.addView(view, -1, -1);
        this.zzzr.setContentView(this.zzdgs);
        this.zzdhb = true;
        this.zzdgt = customViewCallback;
        this.zzdgr = true;
    }

    private final void zzad(boolean z) throws zzi {
        if (!this.zzdhb) {
            this.zzzr.requestWindowFeature(1);
        }
        Window window = this.zzzr.getWindow();
        if (window == null) {
            throw new zzi("Invalid activity, no window available.");
        }
        zzbcm zzbcmVarZzyy = this.zzdgo.zzcyx != null ? this.zzdgo.zzcyx.zzyy() : null;
        boolean z2 = zzbcmVarZzyy != null && zzbcmVarZzyy.zzzr();
        this.zzdgw = false;
        if (z2) {
            int i = this.zzdgo.orientation;
            com.google.android.gms.ads.internal.zzp.zzka();
            if (i == 6) {
                this.zzdgw = this.zzzr.getResources().getConfiguration().orientation == 1;
            } else {
                int i2 = this.zzdgo.orientation;
                com.google.android.gms.ads.internal.zzp.zzka();
                if (i2 == 7) {
                    this.zzdgw = this.zzzr.getResources().getConfiguration().orientation == 2;
                }
            }
        }
        boolean z3 = this.zzdgw;
        StringBuilder sb = new StringBuilder(46);
        sb.append("Delay onShow to next orientation change: ");
        sb.append(z3);
        zzatm.zzdv(sb.toString());
        setRequestedOrientation(this.zzdgo.orientation);
        com.google.android.gms.ads.internal.zzp.zzka();
        window.setFlags(16777216, 16777216);
        zzatm.zzdv("Hardware acceleration on the AdActivity window enabled.");
        if (!this.zzbky) {
            this.zzdgv.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        } else {
            this.zzdgv.setBackgroundColor(zzdgn);
        }
        this.zzzr.setContentView(this.zzdgv);
        this.zzdhb = true;
        if (z) {
            try {
                com.google.android.gms.ads.internal.zzp.zzjz();
                this.zzcyx = zzbbl.zza(this.zzzr, this.zzdgo.zzcyx != null ? this.zzdgo.zzcyx.zzyw() : null, this.zzdgo.zzcyx != null ? this.zzdgo.zzcyx.zzyx() : null, true, z2, null, this.zzdgo.zzblh, null, null, this.zzdgo.zzcyx != null ? this.zzdgo.zzcyx.zzxm() : null, zzrz.zzmk(), null, false);
                this.zzcyx.zzyy().zza(null, this.zzdgo.zzcwp, null, this.zzdgo.zzcwr, this.zzdgo.zzdhq, true, null, this.zzdgo.zzcyx != null ? this.zzdgo.zzcyx.zzyy().zzzq() : null, null, null);
                this.zzcyx.zzyy().zza(new zzbcp(this) { // from class: com.google.android.gms.ads.internal.overlay.zzd
                    private final zze zzdgm;

                    {
                        this.zzdgm = this;
                    }

                    @Override // com.google.android.gms.internal.ads.zzbcp
                    public final void zzab(boolean z4) {
                        zze zzeVar = this.zzdgm;
                        if (zzeVar.zzcyx != null) {
                            zzeVar.zzcyx.zzsq();
                        }
                    }
                });
                if (this.zzdgo.url != null) {
                    this.zzcyx.loadUrl(this.zzdgo.url);
                } else if (this.zzdgo.zzdhp != null) {
                    this.zzcyx.loadDataWithBaseURL(this.zzdgo.zzdhn, this.zzdgo.zzdhp, WebRequest.CONTENT_TYPE_HTML, "UTF-8", null);
                } else {
                    throw new zzi("No URL or HTML to display in ad overlay.");
                }
                if (this.zzdgo.zzcyx != null) {
                    this.zzdgo.zzcyx.zzb(this);
                }
            } catch (Exception e) {
                zzatm.zzc("Error obtaining webview.", e);
                throw new zzi("Could not obtain webview for the overlay.");
            }
        } else {
            this.zzcyx = this.zzdgo.zzcyx;
            this.zzcyx.zzbq(this.zzzr);
        }
        this.zzcyx.zza(this);
        if (this.zzdgo.zzcyx != null) {
            zzc(this.zzdgo.zzcyx.zzzc(), this.zzdgv);
        }
        ViewParent parent = this.zzcyx.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zzcyx.getView());
        }
        if (this.zzbky) {
            this.zzcyx.zzzk();
        }
        this.zzcyx.zza((ViewGroup) null, this.zzzr, this.zzdgo.zzdhn, this.zzdgo.zzdhp);
        this.zzdgv.addView(this.zzcyx.getView(), -1, -1);
        if (!z && !this.zzdgw) {
            zzsq();
        }
        zzac(z2);
        if (this.zzcyx.zzza()) {
            zza(z2, true);
        }
    }

    private final void zzsn() {
        if (!this.zzzr.isFinishing() || this.zzdhc) {
            return;
        }
        this.zzdhc = true;
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            zzbbcVar.zzdc(this.zzdgx);
            synchronized (this.zzdgy) {
                if (!this.zzdha && this.zzcyx.zzzg()) {
                    this.zzdgz = new Runnable(this) { // from class: com.google.android.gms.ads.internal.overlay.zzg
                        private final zze zzdgm;

                        {
                            this.zzdgm = this;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzdgm.zzso();
                        }
                    };
                    zzatv.zzdsk.postDelayed(this.zzdgz, ((Long) zzuo.zzoj().zzd(zzyt.zzckn)).longValue());
                    return;
                }
            }
        }
        zzso();
    }

    @VisibleForTesting
    final void zzso() {
        if (this.zzdhd) {
            return;
        }
        this.zzdhd = true;
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar != null) {
            this.zzdgv.removeView(zzbbcVar.getView());
            zzk zzkVar = this.zzdgp;
            if (zzkVar != null) {
                this.zzcyx.zzbq(zzkVar.zzlk);
                this.zzcyx.zzaq(false);
                this.zzdgp.parent.addView(this.zzcyx.getView(), this.zzdgp.index, this.zzdgp.zzdhi);
                this.zzdgp = null;
            } else if (this.zzzr.getApplicationContext() != null) {
                this.zzcyx.zzbq(this.zzzr.getApplicationContext());
            }
            this.zzcyx = null;
        }
        AdOverlayInfoParcel adOverlayInfoParcel = this.zzdgo;
        if (adOverlayInfoParcel != null && adOverlayInfoParcel.zzdhm != null) {
            this.zzdgo.zzdhm.zzse();
        }
        AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzdgo;
        if (adOverlayInfoParcel2 == null || adOverlayInfoParcel2.zzcyx == null) {
            return;
        }
        zzc(this.zzdgo.zzcyx.zzzc(), this.zzdgo.zzcyx.getView());
    }

    private static void zzc(@Nullable IObjectWrapper iObjectWrapper, @Nullable View view) {
        if (iObjectWrapper == null || view == null) {
            return;
        }
        com.google.android.gms.ads.internal.zzp.zzkn().zza(iObjectWrapper, view);
    }

    public final void zzsp() {
        if (this.zzdgw) {
            this.zzdgw = false;
            zzsq();
        }
    }

    private final void zzsq() {
        this.zzcyx.zzsq();
    }

    public final void zzsr() {
        this.zzdgv.zzdhg = true;
    }

    public final void zzss() {
        synchronized (this.zzdgy) {
            this.zzdha = true;
            if (this.zzdgz != null) {
                zzatv.zzdsk.removeCallbacks(this.zzdgz);
                zzatv.zzdsk.post(this.zzdgz);
            }
        }
    }

    private final void zza(Configuration configuration) {
        AdOverlayInfoParcel adOverlayInfoParcel;
        AdOverlayInfoParcel adOverlayInfoParcel2 = this.zzdgo;
        boolean z = true;
        boolean z2 = false;
        boolean z3 = (adOverlayInfoParcel2 == null || adOverlayInfoParcel2.zzdht == null || !this.zzdgo.zzdht.zzbkz) ? false : true;
        boolean zZza = com.google.android.gms.ads.internal.zzp.zzka().zza(this.zzzr, configuration);
        if ((this.zzbky && !z3) || zZza) {
            z = false;
        } else if (Build.VERSION.SDK_INT >= 19 && (adOverlayInfoParcel = this.zzdgo) != null && adOverlayInfoParcel.zzdht != null && this.zzdgo.zzdht.zzble) {
            z2 = true;
        }
        Window window = this.zzzr.getWindow();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckq)).booleanValue() && Build.VERSION.SDK_INT >= 19) {
            View decorView = window.getDecorView();
            int i = 256;
            if (z) {
                i = 5380;
                if (z2) {
                    i = 5894;
                }
            }
            decorView.setSystemUiVisibility(i);
            return;
        }
        if (z) {
            window.addFlags(1024);
            window.clearFlags(2048);
            if (Build.VERSION.SDK_INT < 19 || !z2) {
                return;
            }
            window.getDecorView().setSystemUiVisibility(4098);
            return;
        }
        window.addFlags(2048);
        window.clearFlags(1024);
    }
}
