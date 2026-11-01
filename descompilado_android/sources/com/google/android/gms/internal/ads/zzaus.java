package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaus {
    private final Object lock = new Object();

    @GuardedBy("lock")
    private String zzdtc = "";

    @GuardedBy("lock")
    private String zzdtd = "";

    @GuardedBy("lock")
    private boolean zzdte = false;

    @VisibleForTesting
    private String zzdtf = "";

    public final void zze(Context context, String str, String str2) {
        if (!zzf(context, str, str2)) {
            zza(context, "In-app preview failed to load because of a system error. Please try again later.", true, true);
            return;
        }
        if ("2".equals(this.zzdtf)) {
            zzatm.zzdv("Creative is not pushed for this device.");
            zza(context, "There was no creative pushed from DFP to the device.", false, false);
        } else if ("1".equals(this.zzdtf)) {
            zzatm.zzdv("The app is not linked for creative preview.");
            zzi(context, str, str2);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(this.zzdtf)) {
            zzatm.zzdv("Device is linked for in app preview.");
            zza(context, "The device is successfully linked for creative preview.", false, true);
        }
    }

    public final void zza(Context context, String str, String str2, @Nullable String str3) {
        boolean zZzvw = zzvw();
        if (zzg(context, str, str2)) {
            if (!zZzvw && !TextUtils.isEmpty(str3)) {
                zzc(context, str2, str3, str);
            }
            zzatm.zzdv("Device is linked for debug signals.");
            zza(context, "The device is successfully linked for troubleshooting.", false, true);
            return;
        }
        zzi(context, str, str2);
    }

    @VisibleForTesting
    private final boolean zzf(Context context, String str, String str2) {
        String strZzh = zzh(context, zzd(context, (String) zzuo.zzoj().zzd(zzyt.zzcpu), str, str2).toString(), str2);
        if (TextUtils.isEmpty(strZzh)) {
            zzatm.zzdv("Not linked for in app preview.");
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(strZzh.trim());
            String strOptString = jSONObject.optString("gct");
            this.zzdtf = jSONObject.optString("status");
            synchronized (this.lock) {
                this.zzdtd = strOptString;
            }
            return true;
        } catch (JSONException e) {
            zzatm.zzd("Fail to get in app preview response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private final boolean zzg(Context context, String str, String str2) {
        String strZzh = zzh(context, zzd(context, (String) zzuo.zzoj().zzd(zzyt.zzcpv), str, str2).toString(), str2);
        if (TextUtils.isEmpty(strZzh)) {
            zzatm.zzdv("Not linked for debug signals.");
            return false;
        }
        try {
            boolean zEquals = "1".equals(new JSONObject(strZzh.trim()).optString("debug_mode"));
            synchronized (this.lock) {
                this.zzdte = zEquals;
            }
            return zEquals;
        } catch (JSONException e) {
            zzatm.zzd("Fail to get debug mode response json.", e);
            return false;
        }
    }

    @VisibleForTesting
    private static String zzh(Context context, String str, String str2) {
        HashMap map = new HashMap();
        map.put("User-Agent", com.google.android.gms.ads.internal.zzp.zzjy().zzr(context, str2));
        zzdcp<String> zzdcpVarZzc = new zzave(context).zzc(str, map);
        try {
            return zzdcpVarZzc.get(((Integer) zzuo.zzoj().zzd(zzyt.zzcpx)).intValue(), TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            String strValueOf = String.valueOf(str);
            zzatm.zzc(strValueOf.length() != 0 ? "Interrupted while retriving a response from: ".concat(strValueOf) : new String("Interrupted while retriving a response from: "), e);
            zzdcpVarZzc.cancel(true);
            return null;
        } catch (TimeoutException e2) {
            String strValueOf2 = String.valueOf(str);
            zzatm.zzc(strValueOf2.length() != 0 ? "Timeout while retriving a response from: ".concat(strValueOf2) : new String("Timeout while retriving a response from: "), e2);
            zzdcpVarZzc.cancel(true);
            return null;
        } catch (Exception e3) {
            String strValueOf3 = String.valueOf(str);
            zzatm.zzc(strValueOf3.length() != 0 ? "Error retriving a response from: ".concat(strValueOf3) : new String("Error retriving a response from: "), e3);
            return null;
        }
    }

    private final void zzi(Context context, String str, String str2) {
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzatv.zza(context, zzd(context, (String) zzuo.zzoj().zzd(zzyt.zzcpt), str, str2));
    }

    public final boolean zzb(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) || !com.google.android.gms.ads.internal.zzp.zzki().zzvw()) {
            return false;
        }
        zzatm.zzdv("Sending troubleshooting signals to the server.");
        zzc(context, str, str2, str3);
        return true;
    }

    private final void zzc(Context context, String str, String str2, String str3) {
        Uri.Builder builderBuildUpon = zzd(context, (String) zzuo.zzoj().zzd(zzyt.zzcpw), str3, str).buildUpon();
        builderBuildUpon.appendQueryParameter("debugData", str2);
        com.google.android.gms.ads.internal.zzp.zzjy();
        zzatv.zzb(context, str, builderBuildUpon.build().toString());
    }

    private final Uri zzd(Context context, String str, String str2, String str3) {
        Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
        builderBuildUpon.appendQueryParameter("linkedDeviceId", zzbe(context));
        builderBuildUpon.appendQueryParameter("adSlotPath", str2);
        builderBuildUpon.appendQueryParameter("afmaVersion", str3);
        return builderBuildUpon.build();
    }

    private final String zzbe(Context context) {
        String str;
        synchronized (this.lock) {
            if (TextUtils.isEmpty(this.zzdtc)) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                this.zzdtc = zzatv.zzs(context, "debug_signals_id.txt");
                if (TextUtils.isEmpty(this.zzdtc)) {
                    com.google.android.gms.ads.internal.zzp.zzjy();
                    this.zzdtc = zzatv.zzvi();
                    com.google.android.gms.ads.internal.zzp.zzjy();
                    zzatv.zzc(context, "debug_signals_id.txt", this.zzdtc);
                }
            }
            str = this.zzdtc;
        }
        return str;
    }

    public final String zzvv() {
        String str;
        synchronized (this.lock) {
            str = this.zzdtd;
        }
        return str;
    }

    public final boolean zzvw() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzdte;
        }
        return z;
    }

    @VisibleForTesting
    private final void zza(Context context, String str, boolean z, boolean z2) {
        if (!(context instanceof Activity)) {
            zzatm.zzet("Can not create dialog without Activity Context");
        } else {
            zzatv.zzdsk.post(new zzauv(this, context, str, z, z2));
        }
    }
}
