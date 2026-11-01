package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.appsflyer.share.Constants;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzatv;
import com.google.android.gms.internal.ads.zzuo;
import com.google.android.gms.internal.ads.zzyt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zza {
    public static boolean zza(Context context, zzb zzbVar, zzv zzvVar) {
        int i = 0;
        if (zzbVar == null) {
            zzatm.zzeu("No intent data for launcher overlay.");
            return false;
        }
        zzyt.initialize(context);
        if (zzbVar.intent != null) {
            return zza(context, zzbVar.intent, zzvVar);
        }
        Intent intent = new Intent();
        if (TextUtils.isEmpty(zzbVar.url)) {
            zzatm.zzeu("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty(zzbVar.mimeType)) {
            intent.setDataAndType(Uri.parse(zzbVar.url), zzbVar.mimeType);
        } else {
            intent.setData(Uri.parse(zzbVar.url));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty(zzbVar.packageName)) {
            intent.setPackage(zzbVar.packageName);
        }
        if (!TextUtils.isEmpty(zzbVar.zzdgj)) {
            String[] strArrSplit = zzbVar.zzdgj.split(Constants.URL_PATH_DELIMITER, 2);
            if (strArrSplit.length < 2) {
                String strValueOf = String.valueOf(zzbVar.zzdgj);
                zzatm.zzeu(strValueOf.length() != 0 ? "Could not parse component name from open GMSG: ".concat(strValueOf) : new String("Could not parse component name from open GMSG: "));
                return false;
            }
            intent.setClassName(strArrSplit[0], strArrSplit[1]);
        }
        String str = zzbVar.zzdgk;
        if (!TextUtils.isEmpty(str)) {
            try {
                i = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                zzatm.zzeu("Could not parse intent flags.");
            }
            intent.addFlags(i);
        }
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpr)).booleanValue()) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
            intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
        } else {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpq)).booleanValue()) {
                com.google.android.gms.ads.internal.zzp.zzjy();
                zzatv.zzb(context, intent);
            }
        }
        return zza(context, intent, zzvVar);
    }

    private static boolean zza(Context context, Intent intent, zzv zzvVar) {
        try {
            String strValueOf = String.valueOf(intent.toURI());
            zzatm.zzdy(strValueOf.length() != 0 ? "Launching an intent: ".concat(strValueOf) : new String("Launching an intent: "));
            com.google.android.gms.ads.internal.zzp.zzjy();
            zzatv.zza(context, intent);
            if (zzvVar == null) {
                return true;
            }
            zzvVar.zzsv();
            return true;
        } catch (ActivityNotFoundException e) {
            zzatm.zzeu(e.getMessage());
            return false;
        }
    }
}
