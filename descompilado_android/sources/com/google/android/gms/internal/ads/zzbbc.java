package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
@ParametersAreNonnullByDefault
public interface zzbbc extends com.google.android.gms.ads.internal.zzk, zzaft, zzagu, zzayt, zzbcc, zzbcd, zzbcg, zzbck, zzbcl, zzbcn, zzpl {
    void destroy();

    @Override // com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbcd
    Context getContext();

    int getHeight();

    ViewGroup.LayoutParams getLayoutParams();

    void getLocationOnScreen(int[] iArr);

    ViewParent getParent();

    @Override // com.google.android.gms.internal.ads.zzbcn
    View getView();

    WebView getWebView();

    int getWidth();

    boolean isDestroyed();

    void loadData(String str, String str2, String str3);

    void loadDataWithBaseURL(String str, String str2, String str3, String str4, @Nullable String str5);

    void loadUrl(String str);

    void measure(int i, int i2);

    void onPause();

    void onResume();

    @Override // com.google.android.gms.internal.ads.zzayt
    void setBackgroundColor(int i);

    void setOnClickListener(View.OnClickListener onClickListener);

    void setOnTouchListener(View.OnTouchListener onTouchListener);

    void setRequestedOrientation(int i);

    void setWebChromeClient(WebChromeClient webChromeClient);

    void setWebViewClient(WebViewClient webViewClient);

    void zza(ViewGroup viewGroup, Activity activity, String str, String str2);

    void zza(com.google.android.gms.ads.internal.overlay.zze zzeVar);

    void zza(zzaab zzaabVar);

    void zza(zzaag zzaagVar);

    @Override // com.google.android.gms.internal.ads.zzayt
    void zza(zzbbu zzbbuVar);

    void zza(zzbct zzbctVar);

    void zza(zzqt zzqtVar);

    void zza(String str, Predicate<zzadx<? super zzbbc>> predicate);

    void zza(String str, zzadx<? super zzbbc> zzadxVar);

    @Override // com.google.android.gms.internal.ads.zzayt
    void zza(String str, zzbah zzbahVar);

    void zzae(boolean z);

    void zzaq(IObjectWrapper iObjectWrapper);

    void zzaq(boolean z);

    void zzar(boolean z);

    void zzas(boolean z);

    void zzat(boolean z);

    void zzb(com.google.android.gms.ads.internal.overlay.zze zzeVar);

    void zzb(String str, zzadx<? super zzbbc> zzadxVar);

    void zzb(String str, String str2, @Nullable String str3);

    boolean zzb(boolean z, int i);

    void zzbq(Context context);

    void zzdc(int i);

    void zzsq();

    @Override // com.google.android.gms.internal.ads.zzayt
    @Nullable
    zzbbu zzxj();

    @Override // com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbcd
    Activity zzxl();

    @Override // com.google.android.gms.internal.ads.zzayt
    com.google.android.gms.ads.internal.zzb zzxm();

    @Override // com.google.android.gms.internal.ads.zzayt
    zzzi zzxo();

    @Override // com.google.android.gms.internal.ads.zzayt, com.google.android.gms.internal.ads.zzbck
    zzawv zzxp();

    void zzyr();

    void zzys();

    Context zzyt();

    com.google.android.gms.ads.internal.overlay.zze zzyu();

    com.google.android.gms.ads.internal.overlay.zze zzyv();

    @Override // com.google.android.gms.internal.ads.zzbci
    zzbct zzyw();

    String zzyx();

    @Nullable
    zzbcm zzyy();

    WebViewClient zzyz();

    boolean zzza();

    @Override // com.google.android.gms.internal.ads.zzbcl
    zzdf zzzb();

    @Nullable
    IObjectWrapper zzzc();

    @Override // com.google.android.gms.internal.ads.zzbcc
    boolean zzzd();

    void zzze();

    boolean zzzf();

    boolean zzzg();

    void zzzh();

    void zzzi();

    zzaag zzzj();

    void zzzk();

    void zzzl();

    zzqt zzzm();

    boolean zzzn();

    zzrb zzzo();

    boolean zzzp();
}
