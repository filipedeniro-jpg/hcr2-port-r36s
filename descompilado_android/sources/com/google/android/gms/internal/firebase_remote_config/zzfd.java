package com.google.android.gms.internal.firebase_remote_config;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.remoteconfig.RemoteConfigComponent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzfd {
    private static final Charset zzlz = Charset.forName("UTF-8");

    @VisibleForTesting
    private static final ThreadLocal<DateFormat> zzma = new zzfg();
    private final String appId;
    private final Context zzja;
    private final SharedPreferences zzmb;

    public zzfd(Context context, String str) {
        this.zzja = context;
        this.appId = str;
        this.zzmb = context.getSharedPreferences("com.google.firebase.remoteconfig_legacy_settings", 0);
    }

    @WorkerThread
    public final boolean zzdg() throws Throwable {
        if (!this.zzmb.getBoolean("save_legacy_configs", true)) {
            return false;
        }
        zzfi.zze zzeVarZzdh = zzdh();
        HashMap map = new HashMap();
        if (zzeVarZzdh != null) {
            Map<String, zzen> mapZza = zza(zzeVarZzdh.zzdu());
            Map<String, zzen> mapZza2 = zza(zzeVarZzdh.zzdt());
            Map<String, zzen> mapZza3 = zza(zzeVarZzdh.zzdv());
            HashSet<String> hashSet = new HashSet();
            hashSet.addAll(mapZza.keySet());
            hashSet.addAll(mapZza2.keySet());
            hashSet.addAll(mapZza3.keySet());
            for (String str : hashSet) {
                zzff zzffVar = new zzff(null);
                if (mapZza.containsKey(str)) {
                    zzffVar.zzj(mapZza.get(str));
                }
                if (mapZza2.containsKey(str)) {
                    zzffVar.zzi(mapZza2.get(str));
                }
                if (mapZza3.containsKey(str)) {
                    zzffVar.zzk(mapZza3.get(str));
                }
                map.put(str, zzffVar);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            String str2 = (String) entry.getKey();
            zzff zzffVar2 = (zzff) entry.getValue();
            zzei zzeiVarZzd = zzd(str2, "fetch");
            zzei zzeiVarZzd2 = zzd(str2, "activate");
            zzei zzeiVarZzd3 = zzd(str2, "defaults");
            if (zzffVar2.zzcx() != null) {
                zzeiVarZzd.zzc(zzffVar2.zzcx());
            }
            if (zzffVar2.zzdi() != null) {
                zzeiVarZzd2.zzc(zzffVar2.zzdi());
            }
            if (zzffVar2.zzdj() != null) {
                zzeiVarZzd3.zzc(zzffVar2.zzdj());
            }
        }
        this.zzmb.edit().putBoolean("save_legacy_configs", false).commit();
        return true;
    }

    private final Map<String, zzen> zza(zzfi.zza zzaVar) {
        HashMap map = new HashMap();
        Date date = new Date(zzaVar.getTimestamp());
        List<zzfx> listZzdl = zzaVar.zzdl();
        ArrayList arrayList = new ArrayList();
        Iterator<zzfx> it = listZzdl.iterator();
        while (it.hasNext()) {
            zzkt.zzb zzbVarZza = zza(it.next());
            if (zzbVarZza != null) {
                zzdd zzddVar = new zzdd();
                zzddVar.zzan(zzbVarZza.zzjm());
                zzddVar.zzaq(zzbVarZza.zzjn());
                zzddVar.zzao(zzma.get().format(new Date(zzbVarZza.zzjo())));
                zzddVar.zzap(zzbVarZza.zzjp());
                zzddVar.zzb(Long.valueOf(zzbVarZza.zzjq()));
                zzddVar.zza(Long.valueOf(zzbVarZza.zzjr()));
                arrayList.add(zzddVar);
            }
        }
        for (zzfi.zzd zzdVar : zzaVar.zzdk()) {
            String namespace = zzdVar.getNamespace();
            if (namespace.startsWith("configns:")) {
                namespace = namespace.substring(9);
            }
            zzep zzepVarZzct = zzen.zzct();
            List<zzfi.zzb> listZzdr = zzdVar.zzdr();
            HashMap map2 = new HashMap();
            for (zzfi.zzb zzbVar : listZzdr) {
                map2.put(zzbVar.getKey(), zzbVar.zzdo().zzb(zzlz));
            }
            zzep zzepVarZza = zzepVarZzct.zzd(map2).zza(date);
            if (namespace.equals(FirebaseAuthProvider.PROVIDER_ID)) {
                zzepVarZza.zzb(arrayList);
            }
            try {
                map.put(namespace, zzepVarZza.zzcv());
            } catch (JSONException unused) {
                Log.i("FirebaseRemoteConfig", "A set of legacy configs could not be converted.");
            }
        }
        return map;
    }

    @Nullable
    private static zzkt.zzb zza(zzfx zzfxVar) {
        try {
            zzgg zzggVar = (zzgg) zzfxVar.iterator();
            byte[] bArr = new byte[zzfxVar.size()];
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = zzggVar.next().byteValue();
            }
            return zzkt.zzb.zzg(bArr);
        } catch (zzhq e) {
            Log.i("FirebaseRemoteConfig", "Payload was not defined or could not be deserialized.", e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    @WorkerThread
    private final zzfi.zze zzdh() throws Throwable {
        FileInputStream fileInputStreamOpenFileInput;
        ?? r2 = this.zzja;
        try {
            if (r2 == 0) {
                return null;
            }
            try {
                fileInputStreamOpenFileInput = r2.openFileInput("persisted_config");
                try {
                    zzfi.zze zzeVarZzb = zzfi.zze.zzb(fileInputStreamOpenFileInput);
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e) {
                            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e);
                        }
                    }
                    return zzeVarZzb;
                } catch (FileNotFoundException e2) {
                    e = e2;
                    if (Log.isLoggable("FirebaseRemoteConfig", 3)) {
                        Log.d("FirebaseRemoteConfig", "Persisted config file was not found.", e);
                    }
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e3) {
                            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e3);
                        }
                    }
                    return null;
                } catch (IOException e4) {
                    e = e4;
                    Log.e("FirebaseRemoteConfig", "Cannot initialize from persisted config.", e);
                    if (fileInputStreamOpenFileInput != null) {
                        try {
                            fileInputStreamOpenFileInput.close();
                        } catch (IOException e5) {
                            Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e5);
                        }
                    }
                    return null;
                }
            } catch (FileNotFoundException e6) {
                e = e6;
                fileInputStreamOpenFileInput = null;
            } catch (IOException e7) {
                e = e7;
                fileInputStreamOpenFileInput = null;
            } catch (Throwable th) {
                th = th;
                r2 = 0;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e8) {
                        Log.e("FirebaseRemoteConfig", "Failed to close persisted config file.", e8);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private final zzei zzd(String str, String str2) {
        return RemoteConfigComponent.zza(this.zzja, this.appId, str, str2);
    }
}
