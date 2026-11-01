package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzask {
    public static String zzd(String str, Context context, boolean z) {
        String strZzaf;
        if ((((Boolean) zzuo.zzoj().zzd(zzyt.zzcjk)).booleanValue() && !z) || !com.google.android.gms.ads.internal.zzp.zzkw().zzaa(context) || TextUtils.isEmpty(str) || (strZzaf = com.google.android.gms.ads.internal.zzp.zzkw().zzaf(context)) == null) {
            return str;
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcjc)).booleanValue()) {
            if (str.contains("fbs_aeid")) {
                return str;
            }
            if (com.google.android.gms.ads.internal.zzp.zzjy().zzef(str)) {
                com.google.android.gms.ads.internal.zzp.zzkw().zzh(context, strZzaf);
                return zza(str, "fbs_aeid", strZzaf).toString();
            }
            if (!com.google.android.gms.ads.internal.zzp.zzjy().zzeg(str)) {
                return str;
            }
            com.google.android.gms.ads.internal.zzp.zzkw().zzi(context, strZzaf);
            return zza(str, "fbs_aeid", strZzaf).toString();
        }
        CharSequence charSequence = (String) zzuo.zzoj().zzd(zzyt.zzcjd);
        if (!str.contains(charSequence)) {
            return str;
        }
        if (com.google.android.gms.ads.internal.zzp.zzjy().zzef(str)) {
            com.google.android.gms.ads.internal.zzp.zzkw().zzh(context, strZzaf);
            return str.replace(charSequence, strZzaf);
        }
        if (!com.google.android.gms.ads.internal.zzp.zzjy().zzeg(str)) {
            return str;
        }
        com.google.android.gms.ads.internal.zzp.zzkw().zzi(context, strZzaf);
        return str.replace(charSequence, strZzaf);
    }

    public static String zzc(Uri uri, Context context) {
        if (!com.google.android.gms.ads.internal.zzp.zzkw().zzaa(context)) {
            return uri.toString();
        }
        String strZzaf = com.google.android.gms.ads.internal.zzp.zzkw().zzaf(context);
        if (strZzaf == null) {
            return uri.toString();
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjc)).booleanValue()) {
            String str = (String) zzuo.zzoj().zzd(zzyt.zzcjd);
            String string = uri.toString();
            if (string.contains(str)) {
                com.google.android.gms.ads.internal.zzp.zzkw().zzh(context, strZzaf);
                return string.replace(str, strZzaf);
            }
        } else if (TextUtils.isEmpty(uri.getQueryParameter("fbs_aeid"))) {
            uri = zza(uri.toString(), "fbs_aeid", strZzaf);
            com.google.android.gms.ads.internal.zzp.zzkw().zzh(context, strZzaf);
        }
        return uri.toString();
    }

    @VisibleForTesting
    private static Uri zza(String str, String str2, String str3) {
        int iIndexOf = str.indexOf("&adurl");
        if (iIndexOf == -1) {
            iIndexOf = str.indexOf("?adurl");
        }
        if (iIndexOf != -1) {
            int i = iIndexOf + 1;
            return Uri.parse(str.substring(0, i) + str2 + Constants.RequestParameters.EQUAL + str3 + Constants.RequestParameters.AMPERSAND + str.substring(i));
        }
        return Uri.parse(str).buildUpon().appendQueryParameter(str2, str3).build();
    }
}
