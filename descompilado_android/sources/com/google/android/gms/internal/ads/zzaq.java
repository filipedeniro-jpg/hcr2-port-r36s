package com.google.android.gms.internal.ads;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaq {
    public static zzd zzb(zzo zzoVar) {
        long j;
        long j2;
        boolean z;
        long j3;
        long j4;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = zzoVar.zzab;
        String str = map.get(HttpRequest.HEADER_DATE);
        long jZzf = str != null ? zzf(str) : 0L;
        String str2 = map.get(HttpRequest.HEADER_CACHE_CONTROL);
        int i = 0;
        if (str2 != null) {
            String[] strArrSplit = str2.split(",", 0);
            j = 0;
            int i2 = 0;
            j2 = 0;
            while (i < strArrSplit.length) {
                String strTrim = strArrSplit[i].trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    return null;
                }
                if (strTrim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(strTrim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(strTrim.substring(23));
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    i2 = 1;
                }
                i++;
            }
            i = i2;
            z = true;
        } else {
            j = 0;
            j2 = 0;
            z = false;
        }
        String str3 = map.get(HttpRequest.HEADER_EXPIRES);
        long jZzf2 = str3 != null ? zzf(str3) : 0L;
        String str4 = map.get(HttpRequest.HEADER_LAST_MODIFIED);
        long jZzf3 = str4 != null ? zzf(str4) : 0L;
        String str5 = map.get(HttpRequest.HEADER_ETAG);
        if (z) {
            j3 = jCurrentTimeMillis + (j * 1000);
            if (i != 0) {
                j4 = j3;
            } else {
                Long.signum(j2);
                j4 = (j2 * 1000) + j3;
            }
        } else if (jZzf <= 0 || jZzf2 < jZzf) {
            j3 = 0;
            j4 = j3;
        } else {
            j4 = (jZzf2 - jZzf) + jCurrentTimeMillis;
            j3 = j4;
        }
        zzd zzdVar = new zzd();
        zzdVar.data = zzoVar.data;
        zzdVar.zzg = str5;
        zzdVar.zzk = j3;
        zzdVar.zzj = j4;
        zzdVar.zzh = jZzf;
        zzdVar.zzi = jZzf3;
        zzdVar.zzl = map;
        zzdVar.zzm = zzoVar.allHeaders;
        return zzdVar;
    }

    private static long zzf(String str) {
        try {
            return zzp().parse(str).getTime();
        } catch (ParseException e) {
            zzag.zza(e, "Unable to parse dateStr: %s, falling back to 0", str);
            return 0L;
        }
    }

    static String zzb(long j) {
        return zzp().format(new Date(j));
    }

    private static SimpleDateFormat zzp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return simpleDateFormat;
    }
}
