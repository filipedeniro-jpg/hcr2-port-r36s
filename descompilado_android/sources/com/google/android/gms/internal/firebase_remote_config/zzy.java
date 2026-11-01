package com.google.android.gms.internal.firebase_remote_config;

import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class zzy {
    private static final Pattern zzap = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    private static final Pattern zzaq = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    private static final Pattern zzar;
    private static final Pattern zzas;
    private String type;
    private String zzat;
    private final SortedMap<String, String> zzau = new TreeMap();
    private String zzav;

    public zzy(String str) {
        this.type = "application";
        this.zzat = "octet-stream";
        Matcher matcher = zzar.matcher(str);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Type must be in the 'maintype/subtype; parameter=value' format");
        }
        String strGroup = matcher.group(1);
        if (!zzap.matcher(strGroup).matches()) {
            throw new IllegalArgumentException("Type contains reserved characters");
        }
        this.type = strGroup;
        this.zzav = null;
        String strGroup2 = matcher.group(2);
        if (!zzap.matcher(strGroup2).matches()) {
            throw new IllegalArgumentException("Subtype contains reserved characters");
        }
        this.zzat = strGroup2;
        this.zzav = null;
        String strGroup3 = matcher.group(3);
        if (strGroup3 != null) {
            Matcher matcher2 = zzas.matcher(strGroup3);
            while (matcher2.find()) {
                String strGroup4 = matcher2.group(1);
                String strGroup5 = matcher2.group(3);
                if (strGroup5 == null) {
                    strGroup5 = matcher2.group(2);
                }
                zza(strGroup4, strGroup5);
            }
        }
    }

    private final zzy zza(String str, String str2) {
        if (str2 == null) {
            this.zzav = null;
            this.zzau.remove(str.toLowerCase(Locale.US));
            return this;
        }
        if (!zzaq.matcher(str).matches()) {
            throw new IllegalArgumentException("Name contains reserved characters");
        }
        this.zzav = null;
        this.zzau.put(str.toLowerCase(Locale.US), str2);
        return this;
    }

    static boolean zzv(String str) {
        return zzaq.matcher(str).matches();
    }

    public final String zzp() {
        String str = this.zzav;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append('/');
        sb.append(this.zzat);
        SortedMap<String, String> sortedMap = this.zzau;
        if (sortedMap != null) {
            for (Map.Entry<String, String> entry : sortedMap.entrySet()) {
                String value = entry.getValue();
                sb.append("; ");
                sb.append(entry.getKey());
                sb.append(Constants.RequestParameters.EQUAL);
                if (!zzv(value)) {
                    String strReplace = value.replace("\\", "\\\\").replace("\"", "\\\"");
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strReplace).length() + 2);
                    sb2.append("\"");
                    sb2.append(strReplace);
                    sb2.append("\"");
                    value = sb2.toString();
                }
                sb.append(value);
            }
        }
        this.zzav = sb.toString();
        return this.zzav;
    }

    public final String toString() {
        return zzp();
    }

    private final boolean zza(zzy zzyVar) {
        return zzyVar != null && this.type.equalsIgnoreCase(zzyVar.type) && this.zzat.equalsIgnoreCase(zzyVar.zzat);
    }

    public static boolean zzb(String str, String str2) {
        return str2 != null && new zzy(str).zza(new zzy(str2));
    }

    public final zzy zza(Charset charset) {
        zza(HttpRequest.PARAM_CHARSET, charset == null ? null : charset.name());
        return this;
    }

    public final Charset zzr() {
        String str = this.zzau.get(HttpRequest.PARAM_CHARSET.toLowerCase(Locale.US));
        if (str == null) {
            return null;
        }
        return Charset.forName(str);
    }

    public final int hashCode() {
        return zzp().hashCode();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzy)) {
            return false;
        }
        zzy zzyVar = (zzy) obj;
        return zza(zzyVar) && this.zzau.equals(zzyVar.zzau);
    }

    static {
        StringBuilder sb = new StringBuilder("[^\\s/=;\"]+".length() + 14 + "[^\\s/=;\"]+".length() + ";.*".length());
        sb.append("\\s*(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")/(");
        sb.append("[^\\s/=;\"]+");
        sb.append(")\\s*(");
        sb.append(";.*");
        sb.append(")?");
        zzar = Pattern.compile(sb.toString(), 32);
        StringBuilder sb2 = new StringBuilder("\"([^\"]*)\"".length() + 1 + "[^\\s;\"]*".length());
        sb2.append("\"([^\"]*)\"");
        sb2.append("|");
        sb2.append("[^\\s;\"]*");
        String string = sb2.toString();
        StringBuilder sb3 = new StringBuilder("[^\\s/=;\"]+".length() + 12 + String.valueOf(string).length());
        sb3.append("\\s*;\\s*(");
        sb3.append("[^\\s/=;\"]+");
        sb3.append(")=(");
        sb3.append(string);
        sb3.append(")");
        zzas = Pattern.compile(sb3.toString());
    }
}
