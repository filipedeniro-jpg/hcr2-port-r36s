package com.google.android.gms.internal.firebase_remote_config;

import com.appsflyer.share.Constants;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
final class zzg {
    private static final zzg zzz = new zzg();
    private final String zzaa;

    /* JADX WARN: Illegal instructions before constructor call */
    zzg() {
        String property = System.getProperty("java.version");
        this(property.startsWith("9") ? "9.0.0" : zzh(property), zzdz.OS_NAME.value(), zzdz.OS_VERSION.value(), zzb.VERSION);
    }

    private zzg(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder("java/");
        sb.append(zzh(str));
        sb.append(" http-google-%s/");
        sb.append(zzh(str4));
        if (str2 != null && str3 != null) {
            sb.append(" ");
            sb.append(zzg(str2));
            sb.append(Constants.URL_PATH_DELIMITER);
            sb.append(zzh(str3));
        }
        this.zzaa = sb.toString();
    }

    final String zzf(String str) {
        return String.format(this.zzaa, zzg(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static zzg zzj() {
        return zzz;
    }

    private static String zzg(String str) {
        return str.toLowerCase().replaceAll("[^\\w\\d\\-]", "-");
    }

    private static String zzh(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("(\\d+\\.\\d+\\.\\d+).*").matcher(str);
        return matcher.find() ? matcher.group(1) : str;
    }
}
