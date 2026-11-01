package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcay {
    private final Executor executor;
    private final String packageName;
    private final zzaws zzduo;
    private final Context zzlk;
    private final String zzcts = (String) zzuo.zzoj().zzd(zzyt.zzcif);
    private final Map<String, String> zzfru = new HashMap();

    public zzcay(Executor executor, zzaws zzawsVar, Context context) {
        this.executor = executor;
        this.zzduo = zzawsVar;
        this.zzlk = context;
        this.packageName = context.getPackageName();
        this.zzfru.put("s", "gmob_sdk");
        this.zzfru.put("v", "3");
        this.zzfru.put("os", Build.VERSION.RELEASE);
        this.zzfru.put("sdk", Build.VERSION.SDK);
        Map<String, String> map = this.zzfru;
        com.google.android.gms.ads.internal.zzp.zzjy();
        map.put("device", zzatv.zzvj());
        this.zzfru.put("app", this.packageName);
        Map<String, String> map2 = this.zzfru;
        com.google.android.gms.ads.internal.zzp.zzjy();
        map2.put("is_lite_sdk", zzatv.zzax(this.zzlk) ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        this.zzfru.put("e", TextUtils.join(",", zzyt.zzpo()));
    }

    /* JADX WARN: Code duplicated, block: B:12:0x005d  */
    final void zzm(Map<String, String> map) {
        boolean z;
        Uri.Builder builderBuildUpon = Uri.parse(this.zzcts).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        final String string = builderBuildUpon.build().toString();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcie)).booleanValue()) {
            z = true;
        } else {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            this.executor.execute(new Runnable(this, string) { // from class: com.google.android.gms.internal.ads.zzcax
                private final String zzcyl;
                private final zzcay zzfrt;

                {
                    this.zzfrt = this;
                    this.zzcyl = string;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzfrt.zzfx(this.zzcyl);
                }
            });
        }
        zzatm.zzdy(string);
    }

    public final Map<String, String> zzajr() {
        return new HashMap(this.zzfru);
    }

    public final ConcurrentHashMap<String, String> zzajs() {
        return new ConcurrentHashMap<>(this.zzfru);
    }

    final /* synthetic */ void zzfx(String str) {
        this.zzduo.zzei(str);
    }
}
