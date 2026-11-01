package com.google.android.gms.internal.ads;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.view.View;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.util.PlatformVersion;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(11)
public final class zzbau extends WebChromeClient {
    private final zzbbc zzcyx;

    public zzbau(zzbbc zzbbcVar) {
        this.zzcyx = zzbbcVar;
    }

    private final boolean zza(Context context, String str, String str2, String str3, String str4, JsResult jsResult, JsPromptResult jsPromptResult, boolean z) {
        com.google.android.gms.ads.internal.zza zzaVarZzzq;
        try {
            if (this.zzcyx != null && this.zzcyx.zzyy() != null && this.zzcyx.zzyy().zzzq() != null && (zzaVarZzzq = this.zzcyx.zzyy().zzzq()) != null && !zzaVarZzzq.zzjh()) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 11 + String.valueOf(str3).length());
                sb.append("window.");
                sb.append(str);
                sb.append("('");
                sb.append(str3);
                sb.append("')");
                zzaVarZzzq.zzbl(sb.toString());
                return false;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(str2);
            if (z) {
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(1);
                TextView textView = new TextView(context);
                textView.setText(str3);
                EditText editText = new EditText(context);
                editText.setText(str4);
                linearLayout.addView(textView);
                linearLayout.addView(editText);
                builder.setView(linearLayout).setPositiveButton(R.string.ok, new zzbba(jsPromptResult, editText)).setNegativeButton(R.string.cancel, new zzbbb(jsPromptResult)).setOnCancelListener(new zzbay(jsPromptResult)).create().show();
            } else {
                builder.setMessage(str3).setPositiveButton(R.string.ok, new zzbaz(jsResult)).setNegativeButton(R.string.cancel, new zzbaw(jsResult)).setOnCancelListener(new zzbax(jsResult)).create().show();
            }
            return true;
        } catch (WindowManager.BadTokenException e) {
            zzatm.zzd("Fail to display Dialog.", e);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        WebView.WebViewTransport webViewTransport = (WebView.WebViewTransport) message.obj;
        WebView webView2 = new WebView(webView.getContext());
        if (this.zzcyx.zzyz() != null) {
            webView2.setWebViewClient(this.zzcyx.zzyz());
        }
        webViewTransport.setWebView(webView2);
        message.sendToTarget();
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.webkit.WebChromeClient
    public final void onCloseWindow(WebView webView) {
        if (!(webView instanceof zzbbc)) {
            zzatm.zzeu("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = ((zzbbc) webView).zzyu();
        if (zzeVarZzyu == null) {
            zzatm.zzeu("Tried to close an AdWebView not associated with an overlay.");
        } else {
            zzeVarZzyu.close();
        }
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String strMessage = consoleMessage.message();
        String strSourceId = consoleMessage.sourceId();
        int iLineNumber = consoleMessage.lineNumber();
        StringBuilder sb = new StringBuilder(String.valueOf(strMessage).length() + 19 + String.valueOf(strSourceId).length());
        sb.append("JS: ");
        sb.append(strMessage);
        sb.append(" (");
        sb.append(strSourceId);
        sb.append(":");
        sb.append(iLineNumber);
        sb.append(")");
        String string = sb.toString();
        if (string.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        int i = zzbbd.zzedw[consoleMessage.messageLevel().ordinal()];
        if (i == 1) {
            zzatm.zzes(string);
        } else if (i == 2) {
            zzatm.zzeu(string);
        } else if (i != 3 && i != 4 && i == 5) {
            zzatm.zzdv(string);
        } else {
            zzatm.zzet(string);
        }
        return super.onConsoleMessage(consoleMessage);
    }

    @Override // android.webkit.WebChromeClient
    public final void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        long j4 = 5242880 - j3;
        if (j4 <= 0) {
            quotaUpdater.updateQuota(j);
            return;
        }
        if (j != 0) {
            if (j2 == 0) {
                j = Math.min(j + Math.min(PlaybackStateCompat.ACTION_PREPARE_FROM_URI, j4), PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED);
            } else if (j2 <= Math.min(PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED - j, j4)) {
                j += j2;
            }
            j2 = j;
        } else if (j2 > j4 || j2 > PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            j2 = 0;
        }
        quotaUpdater.updateQuota(j2);
    }

    @Override // android.webkit.WebChromeClient
    public final void onHideCustomView() {
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = this.zzcyx.zzyu();
        if (zzeVarZzyu == null) {
            zzatm.zzeu("Could not get ad overlay when hiding custom view.");
        } else {
            zzeVarZzyu.zzsj();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static Context zza(WebView webView) {
        if (!(webView instanceof zzbbc)) {
            return webView.getContext();
        }
        zzbbc zzbbcVar = (zzbbc) webView;
        Activity activityZzxl = zzbbcVar.zzxl();
        return activityZzxl != null ? activityZzxl : zzbbcVar.getContext();
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "alert", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "onBeforeUnload", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
        return zza(zza(webView), "confirm", str, str2, null, jsResult, null, false);
    }

    @Override // android.webkit.WebChromeClient
    public final boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        return zza(zza(webView), SettingsJsonConstants.PROMPT_KEY, str, str2, str3, null, jsPromptResult, true);
    }

    public final void onReachedMaxAppCacheSize(long j, long j2, WebStorage.QuotaUpdater quotaUpdater) {
        long j3 = 5242880 - j2;
        long j4 = j + PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        if (j3 < j4) {
            quotaUpdater.updateQuota(0L);
        } else {
            quotaUpdater.updateQuota(j4);
        }
    }

    @Override // android.webkit.WebChromeClient
    public final void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        onShowCustomView(view, -1, customViewCallback);
    }

    @Override // android.webkit.WebChromeClient
    @Deprecated
    public final void onShowCustomView(View view, int i, WebChromeClient.CustomViewCallback customViewCallback) {
        com.google.android.gms.ads.internal.overlay.zze zzeVarZzyu = this.zzcyx.zzyu();
        if (zzeVarZzyu == null) {
            zzatm.zzeu("Could not get ad overlay when showing custom view.");
            customViewCallback.onCustomViewHidden();
        } else {
            zzeVarZzyu.zza(view, customViewCallback);
            zzeVarZzyu.setRequestedOrientation(i);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x0028  */
    @Override // android.webkit.WebChromeClient
    public final void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        boolean z;
        if (callback != null) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            if (zzatv.zzq(this.zzcyx.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                z = true;
            } else {
                com.google.android.gms.ads.internal.zzp.zzjy();
                if (zzatv.zzq(this.zzcyx.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                    z = true;
                } else {
                    z = false;
                }
            }
            callback.invoke(str, z, true);
        }
    }

    @Override // android.webkit.WebChromeClient
    @TargetApi(21)
    public final void onPermissionRequest(PermissionRequest permissionRequest) {
        if (PlatformVersion.isAtLeastLollipop()) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcjt)).booleanValue()) {
                zzbbc zzbbcVar = this.zzcyx;
                if (zzbbcVar == null || zzbbcVar.zzyy() == null || this.zzcyx.zzyy().zzaac() == null) {
                    super.onPermissionRequest(permissionRequest);
                    return;
                }
                String[] strArrZza = this.zzcyx.zzyy().zzaac().zza(permissionRequest.getResources());
                if (strArrZza.length > 0) {
                    permissionRequest.grant(strArrZza);
                    return;
                } else {
                    permissionRequest.deny();
                    return;
                }
            }
        }
        super.onPermissionRequest(permissionRequest);
    }
}
