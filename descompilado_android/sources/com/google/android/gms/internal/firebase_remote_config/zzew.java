package com.google.android.gms.internal.firebase_remote_config;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzew {

    @VisibleForTesting(otherwise = 3)
    public static final Charset zzln = Charset.forName("UTF-8");
    static final Pattern zzlo = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    static final Pattern zzlp = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    private final zzei zzje;
    private final zzei zzjf;

    public zzew(zzei zzeiVar, zzei zzeiVar2) {
        this.zzje = zzeiVar;
        this.zzjf = zzeiVar2;
    }

    public final String getString(String str) {
        String strZza = zza(this.zzje, str, "String");
        if (strZza != null) {
            return strZza;
        }
        String strZza2 = zza(this.zzjf, str, "String");
        return strZza2 != null ? strZza2 : "";
    }

    public final boolean getBoolean(String str) {
        String strZza = zza(this.zzje, str, "Boolean");
        if (strZza != null) {
            if (zzlo.matcher(strZza).matches()) {
                return true;
            }
            if (zzlp.matcher(strZza).matches()) {
                return false;
            }
        }
        String strZza2 = zza(this.zzjf, str, "Boolean");
        if (strZza2 != null) {
            if (zzlo.matcher(strZza2).matches()) {
                return true;
            }
            if (zzlp.matcher(strZza2).matches()) {
            }
        }
        return false;
    }

    public final byte[] getByteArray(String str) {
        String strZza = zza(this.zzje, str, "ByteArray");
        if (strZza != null) {
            return strZza.getBytes(zzln);
        }
        String strZza2 = zza(this.zzjf, str, "ByteArray");
        if (strZza2 != null) {
            return strZza2.getBytes(zzln);
        }
        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY;
    }

    public final double getDouble(String str) {
        Double dZza = zza(this.zzje, str);
        if (dZza != null) {
            return dZza.doubleValue();
        }
        Double dZza2 = zza(this.zzjf, str);
        return dZza2 != null ? dZza2.doubleValue() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public final long getLong(String str) {
        Long lZzb = zzb(this.zzje, str);
        if (lZzb != null) {
            return lZzb.longValue();
        }
        Long lZzb2 = zzb(this.zzjf, str);
        if (lZzb2 != null) {
            return lZzb2.longValue();
        }
        return 0L;
    }

    public final FirebaseRemoteConfigValue getValue(String str) {
        String strZza = zza(this.zzje, str, "FirebaseRemoteConfigValue");
        if (strZza != null) {
            return new zzfe(strZza, 2);
        }
        String strZza2 = zza(this.zzjf, str, "FirebaseRemoteConfigValue");
        if (strZza2 != null) {
            return new zzfe(strZza2, 1);
        }
        return new zzfe("", 0);
    }

    public final Set<String> getKeysByPrefix(String str) {
        if (str == null) {
            str = "";
        }
        TreeSet treeSet = new TreeSet();
        zzen zzenVarZzb = this.zzje.zzb(5L);
        if (zzenVarZzb != null) {
            treeSet.addAll(zza(str, zzenVarZzb));
        }
        zzen zzenVarZzb2 = this.zzjf.zzb(5L);
        if (zzenVarZzb2 != null) {
            treeSet.addAll(zza(str, zzenVarZzb2));
        }
        return treeSet;
    }

    private static TreeSet<String> zza(String str, zzen zzenVar) {
        TreeSet<String> treeSet = new TreeSet<>();
        Iterator<String> itKeys = zzenVar.zzcq().keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (next.startsWith(str)) {
                treeSet.add(next);
            }
        }
        return treeSet;
    }

    public final Map<String, FirebaseRemoteConfigValue> getAll() {
        HashSet<String> hashSet = new HashSet();
        hashSet.addAll(zza(this.zzje));
        hashSet.addAll(zza(this.zzjf));
        HashMap map = new HashMap();
        for (String str : hashSet) {
            map.put(str, getValue(str));
        }
        return map;
    }

    @Nullable
    private static String zza(zzei zzeiVar, String str, String str2) {
        zzen zzenVarZzb = zzeiVar.zzb(5L);
        if (zzenVarZzb == null) {
            return null;
        }
        try {
            return zzenVarZzb.zzcq().getString(str);
        } catch (JSONException unused) {
            zze(str, str2);
            return null;
        }
    }

    @Nullable
    private static Double zza(zzei zzeiVar, String str) {
        zzen zzenVarZzb = zzeiVar.zzb(5L);
        if (zzenVarZzb == null) {
            return null;
        }
        try {
            return Double.valueOf(zzenVarZzb.zzcq().getDouble(str));
        } catch (JSONException unused) {
            zze(str, "Double");
            return null;
        }
    }

    @Nullable
    private static Long zzb(zzei zzeiVar, String str) {
        zzen zzenVarZzb = zzeiVar.zzb(5L);
        if (zzenVarZzb == null) {
            return null;
        }
        try {
            return Long.valueOf(zzenVarZzb.zzcq().getLong(str));
        } catch (JSONException unused) {
            zze(str, "Long");
            return null;
        }
    }

    private static Set<String> zza(zzei zzeiVar) {
        HashSet hashSet = new HashSet();
        zzen zzenVarZzb = zzeiVar.zzb(5L);
        if (zzenVarZzb == null) {
            return hashSet;
        }
        Iterator<String> itKeys = zzenVarZzb.zzcq().keys();
        while (itKeys.hasNext()) {
            hashSet.add(itKeys.next());
        }
        return hashSet;
    }

    private static void zze(String str, String str2) {
        Log.w("FirebaseRemoteConfig", String.format("No value of type '%s' exists for parameter key '%s'.", str2, str));
    }
}
