package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.impl.R;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbbo extends FrameLayout implements zzbbc {
    private final zzbbc zzefi;
    private final zzayi zzefj;
    private final AtomicBoolean zzefk;

    public zzbbo(zzbbc zzbbcVar) {
        super(zzbbcVar.getContext());
        this.zzefk = new AtomicBoolean();
        this.zzefi = zzbbcVar;
        this.zzefj = new zzayi(zzbbcVar.zzyt(), this, this);
        if (zzzp()) {
            return;
        }
        addView(this.zzefi.getView());
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcn
    public final View getView() {
        return this;
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final zzayi zzxi() {
        return this.zzefj;
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void onPause() {
        this.zzefj.onPause();
        this.zzefi.onPause();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzze() {
        this.zzefj.onDestroy();
        this.zzefi.zzze();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzk() {
        setBackgroundColor(0);
        this.zzefi.setBackgroundColor(0);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final int zzxq() {
        return getMeasuredHeight();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final int zzxr() {
        return getMeasuredWidth();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zzxs() {
        this.zzefi.zzxs();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final WebView getWebView() {
        return this.zzefi.getWebView();
    }

    @Override // com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, Map<String, ?> map) {
        this.zzefi.zza(str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, JSONObject jSONObject) {
        this.zzefi.zza(str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(String str, zzadx<? super zzbbc> zzadxVar) {
        this.zzefi.zza(str, zzadxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzb(String str, zzadx<? super zzbbc> zzadxVar) {
        this.zzefi.zzb(str, zzadxVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(String str, Predicate<zzadx<? super zzbbc>> predicate) {
        this.zzefi.zza(str, predicate);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzyr() {
        this.zzefi.zzyr();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzdc(int i) {
        this.zzefi.zzdc(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzsq() {
        this.zzefi.zzsq();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzys() {
        this.zzefi.zzys();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zza(boolean z, long j) {
        this.zzefi.zza(z, j);
    }

    @Override // com.google.android.gms.internal.ads.zzagu
    public final void zzct(String str) {
        this.zzefi.zzct(str);
    }

    @Override // com.google.android.gms.internal.ads.zzagu
    public final void zzb(String str, JSONObject jSONObject) {
        this.zzefi.zzb(str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbcd
    public final Activity zzxl() {
        return this.zzefi.zzxl();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final Context zzyt() {
        return this.zzefi.zzyt();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final com.google.android.gms.ads.internal.zzb zzxm() {
        return this.zzefi.zzxm();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final com.google.android.gms.ads.internal.overlay.zze zzyu() {
        return this.zzefi.zzyu();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final IObjectWrapper zzzc() {
        return this.zzefi.zzzc();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final com.google.android.gms.ads.internal.overlay.zze zzyv() {
        return this.zzefi.zzyv();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbci
    public final zzbct zzyw() {
        return this.zzefi.zzyw();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final String zzyx() {
        return this.zzefi.zzyx();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final zzbcm zzyy() {
        return this.zzefi.zzyy();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final WebViewClient zzyz() {
        return this.zzefi.zzyz();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzza() {
        return this.zzefi.zzza();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcl
    public final zzdf zzzb() {
        return this.zzefi.zzzb();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbck
    public final zzawv zzxp() {
        return this.zzefi.zzxp();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzbcc
    public final boolean zzzd() {
        return this.zzefi.zzzd();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean isDestroyed() {
        return this.zzefi.isDestroyed();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzf() {
        return this.zzefi.zzzf();
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final void zzjv() {
        this.zzefi.zzjv();
    }

    @Override // com.google.android.gms.ads.internal.zzk
    public final void zzju() {
        this.zzefi.zzju();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final String zzxn() {
        return this.zzefi.zzxn();
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final zzzf zzxk() {
        return this.zzefi.zzxk();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final zzzi zzxo() {
        return this.zzefi.zzxo();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final zzbbu zzxj() {
        return this.zzefi.zzxj();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(com.google.android.gms.ads.internal.overlay.zze zzeVar) {
        this.zzefi.zza(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzaq(IObjectWrapper iObjectWrapper) {
        this.zzefi.zzaq(iObjectWrapper);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(zzbct zzbctVar) {
        this.zzefi.zza(zzbctVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzaq(boolean z) {
        this.zzefi.zzaq(z);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzh() {
        this.zzefi.zzzh();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzbq(Context context) {
        this.zzefi.zzbq(context);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzae(boolean z) {
        this.zzefi.zzae(z);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void setRequestedOrientation(int i) {
        this.zzefi.setRequestedOrientation(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzb(com.google.android.gms.ads.internal.overlay.zze zzeVar) {
        this.zzefi.zzb(zzeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzar(boolean z) {
        this.zzefi.zzar(z);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final void zza(String str, zzbah zzbahVar) {
        this.zzefi.zza(str, zzbahVar);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final zzbah zzez(String str) {
        return this.zzefi.zzez(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzi() {
        this.zzefi.zzzi();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void destroy() {
        IObjectWrapper iObjectWrapperZzzc = zzzc();
        if (iObjectWrapperZzzc != null) {
            com.google.android.gms.ads.internal.zzp.zzkn().zzaf(iObjectWrapperZzzc);
            zzatv.zzdsk.postDelayed(new zzbbr(this), ((Integer) zzuo.zzoj().zzd(zzyt.zzcqn)).intValue());
            return;
        }
        this.zzefi.destroy();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void loadData(String str, String str2, String str3) {
        this.zzefi.loadData(str, str2, str3);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzefi.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void loadUrl(String str) {
        this.zzefi.loadUrl(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzb(String str, String str2, @Nullable String str3) {
        this.zzefi.zzb(str, str2, str3);
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzbbc
    public final void setOnClickListener(View.OnClickListener onClickListener) {
        this.zzefi.setOnClickListener(onClickListener);
    }

    @Override // android.view.View, com.google.android.gms.internal.ads.zzbbc
    public final void setOnTouchListener(View.OnTouchListener onTouchListener) {
        this.zzefi.setOnTouchListener(onTouchListener);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzefi.setWebChromeClient(webChromeClient);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void setWebViewClient(WebViewClient webViewClient) {
        this.zzefi.setWebViewClient(webViewClient);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void onResume() {
        this.zzefi.onResume();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzzl() {
        TextView textView = new TextView(getContext());
        Resources resources = com.google.android.gms.ads.internal.zzp.zzkc().getResources();
        textView.setText(resources != null ? resources.getString(R.string.s7) : "Test Ad");
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (Build.VERSION.SDK_INT >= 16) {
            textView.setBackground(gradientDrawable);
        } else {
            textView.setBackgroundDrawable(gradientDrawable);
        }
        addView(textView, new FrameLayout.LayoutParams(-2, -2, 49));
        bringChildToFront(textView);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzat(boolean z) {
        this.zzefi.zzat(z);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(zzaab zzaabVar) {
        this.zzefi.zza(zzaabVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(zzqt zzqtVar) {
        this.zzefi.zza(zzqtVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final zzqt zzzm() {
        return this.zzefi.zzzm();
    }

    @Override // com.google.android.gms.internal.ads.zzpl
    public final void zza(zzpi zzpiVar) {
        this.zzefi.zza(zzpiVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(@Nullable zzaag zzaagVar) {
        this.zzefi.zza(zzaagVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    @Nullable
    public final zzaag zzzj() {
        return this.zzefi.zzzj();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc, com.google.android.gms.internal.ads.zzayt
    public final void zza(zzbbu zzbbuVar) {
        this.zzefi.zza(zzbbuVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzg() {
        return this.zzefi.zzzg();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zzas(boolean z) {
        this.zzefi.zzas(z);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zzao(boolean z) {
        this.zzefi.zzao(z);
    }

    @Override // com.google.android.gms.internal.ads.zzayt
    public final void zzsr() {
        this.zzefi.zzsr();
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(com.google.android.gms.ads.internal.overlay.zzb zzbVar) {
        this.zzefi.zza(zzbVar);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zzc(boolean z, int i) {
        this.zzefi.zzc(z, i);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(boolean z, int i, String str) {
        this.zzefi.zza(z, i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzbcg
    public final void zza(boolean z, int i, String str, String str2) {
        this.zzefi.zza(z, i, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzb(boolean z, int i) {
        if (!this.zzefk.compareAndSet(false, true)) {
            return true;
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjx)).booleanValue()) {
            return false;
        }
        if (this.zzefi.getParent() instanceof ViewGroup) {
            ((ViewGroup) this.zzefi.getParent()).removeView(this.zzefi.getView());
        }
        return this.zzefi.zzb(z, i);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzn() {
        return this.zzefk.get();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final void zza(ViewGroup viewGroup, Activity activity, String str, String str2) {
        this.zzefi.zza(this, activity, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final zzrb zzzo() {
        return this.zzefi.zzzo();
    }

    @Override // com.google.android.gms.internal.ads.zzbbc
    public final boolean zzzp() {
        return this.zzefi.zzzp();
    }
}
