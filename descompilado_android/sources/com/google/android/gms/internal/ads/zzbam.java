package com.google.android.gms.internal.ads;

import com.my.target.bf;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbam implements zzadx<zzayt> {
    private static Integer zzb(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt(map.get(str)));
        } catch (NumberFormatException unused) {
            String str2 = map.get(str);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 39 + String.valueOf(str2).length());
            sb.append("Precache invalid numeric parameter '");
            sb.append(str);
            sb.append("': ");
            sb.append(str2);
            zzatm.zzeu(sb.toString());
            return null;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzayt zzaytVar, Map map) {
        zzbah zzbahVarZza;
        zzayt zzaytVar2 = zzaytVar;
        if (zzatm.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String strValueOf = String.valueOf(jSONObject);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 15);
            sb.append("Precache GMSG: ");
            sb.append(strValueOf);
            zzatm.zzdv(sb.toString());
        }
        com.google.android.gms.ads.internal.zzp.zzku();
        if (map.containsKey("abort")) {
            if (zzbae.zzc(zzaytVar2)) {
                return;
            }
            zzatm.zzeu("Precache abort but no precache task running.");
            return;
        }
        String str = (String) map.get(bf.a.fE);
        if (str != null) {
            String[] strArr = {str};
            String str2 = (String) map.get("demuxed");
            if (str2 != null) {
                try {
                    JSONArray jSONArray = new JSONArray(str2);
                    String[] strArr2 = new String[jSONArray.length()];
                    for (int i = 0; i < jSONArray.length(); i++) {
                        strArr2[i] = jSONArray.getString(i);
                    }
                    strArr = strArr2;
                } catch (JSONException unused) {
                    String strValueOf2 = String.valueOf(str2);
                    zzatm.zzeu(strValueOf2.length() != 0 ? "Malformed demuxed URL list for precache: ".concat(strValueOf2) : new String("Malformed demuxed URL list for precache: "));
                    strArr = null;
                }
            }
            if (strArr == null) {
                strArr = new String[]{str};
            }
            if (zzbae.zzd(zzaytVar2) != null) {
                zzatm.zzeu("Precache task is already running.");
                return;
            }
            if (zzaytVar2.zzxm() == null) {
                zzatm.zzeu("Precache requires a dependency provider.");
                return;
            }
            zzayq zzayqVar = new zzayq((String) map.get("flags"));
            Integer numZzb = zzb(map, "player");
            if (numZzb == null) {
                numZzb = 0;
            }
            zzbahVarZza = zzaytVar2.zzxm().zzbko.zza(zzaytVar2, numZzb.intValue(), null, zzayqVar);
            new zzbac(zzaytVar2, zzbahVarZza, str, strArr).zzup();
        } else {
            zzbac zzbacVarZzd = zzbae.zzd(zzaytVar2);
            if (zzbacVarZzd != null) {
                zzbahVarZza = zzbacVarZzd.zzecq;
            } else {
                zzatm.zzeu("Precache must specify a source.");
                return;
            }
        }
        Integer numZzb2 = zzb(map, "minBufferMs");
        if (numZzb2 != null) {
            zzbahVarZza.zzct(numZzb2.intValue());
        }
        Integer numZzb3 = zzb(map, "maxBufferMs");
        if (numZzb3 != null) {
            zzbahVarZza.zzcu(numZzb3.intValue());
        }
        Integer numZzb4 = zzb(map, "bufferForPlaybackMs");
        if (numZzb4 != null) {
            zzbahVarZza.zzcv(numZzb4.intValue());
        }
        Integer numZzb5 = zzb(map, "bufferForPlaybackAfterRebufferMs");
        if (numZzb5 != null) {
            zzbahVarZza.zzcw(numZzb5.intValue());
        }
    }
}
