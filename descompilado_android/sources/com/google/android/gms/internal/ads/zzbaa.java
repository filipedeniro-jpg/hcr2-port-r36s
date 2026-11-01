package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import com.my.target.aq;
import com.my.target.bf;
import com.my.target.i;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbaa implements zzadx<zzayt> {
    private boolean zzeco;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = map.get(str);
        if (str2 == null) {
            return i;
        }
        try {
            zzuo.zzof();
            return zzawe.zza(context, Integer.parseInt(str2));
        } catch (NumberFormatException unused) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(str2).length());
            sb.append("Could not parse ");
            sb.append(str);
            sb.append(" in a video GMSG: ");
            sb.append(str2);
            zzatm.zzeu(sb.toString());
            return i;
        }
    }

    private static void zza(zzayc zzaycVar, Map<String, String> map) {
        String str = map.get("minBufferMs");
        String str2 = map.get("maxBufferMs");
        String str3 = map.get("bufferForPlaybackMs");
        String str4 = map.get("bufferForPlaybackAfterRebufferMs");
        String str5 = map.get("socketReceiveBufferSize");
        if (str != null) {
            try {
                zzaycVar.zzct(Integer.parseInt(str));
            } catch (NumberFormatException unused) {
                zzatm.zzeu(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", str, str2));
                return;
            }
        }
        if (str2 != null) {
            zzaycVar.zzcu(Integer.parseInt(str2));
        }
        if (str3 != null) {
            zzaycVar.zzcv(Integer.parseInt(str3));
        }
        if (str4 != null) {
            zzaycVar.zzcw(Integer.parseInt(str4));
        }
        if (str5 != null) {
            zzaycVar.zzcx(Integer.parseInt(str5));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzayt zzaytVar, Map map) {
        int i;
        zzayt zzaytVar2 = zzaytVar;
        String str = (String) map.get("action");
        if (str == null) {
            zzatm.zzeu("Action missing from video GMSG.");
            return;
        }
        if (zzatm.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String string = jSONObject.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 13 + String.valueOf(string).length());
            sb.append("Video GMSG: ");
            sb.append(str);
            sb.append(" ");
            sb.append(string);
            zzatm.zzdv(sb.toString());
        }
        if ("background".equals(str)) {
            String str2 = (String) map.get("color");
            if (TextUtils.isEmpty(str2)) {
                zzatm.zzeu("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzaytVar2.setBackgroundColor(Color.parseColor(str2));
                return;
            } catch (IllegalArgumentException unused) {
                zzatm.zzeu("Invalid color parameter in video GMSG.");
                return;
            }
        }
        if ("decoderProps".equals(str)) {
            String str3 = (String) map.get("mimeTypes");
            if (str3 == null) {
                zzatm.zzeu("No MIME types specified for decoder properties inspection.");
                zzayc.zza(zzaytVar2, "missingMimeTypes");
                return;
            }
            if (Build.VERSION.SDK_INT < 16) {
                zzatm.zzeu("Video decoder properties available on API versions >= 16.");
                zzayc.zza(zzaytVar2, "deficientApiVersion");
                return;
            }
            HashMap map2 = new HashMap();
            for (String str4 : str3.split(",")) {
                map2.put(str4, zzawc.zzem(str4.trim()));
            }
            zzayc.zza(zzaytVar2, map2);
            return;
        }
        zzayi zzayiVarZzxi = zzaytVar2.zzxi();
        if (zzayiVarZzxi == null) {
            zzatm.zzeu("Could not get underlay container for a video GMSG.");
            return;
        }
        boolean zEquals = AppSettingsData.STATUS_NEW.equals(str);
        boolean zEquals2 = Constants.ParametersKeys.POSITION.equals(str);
        if (zEquals || zEquals2) {
            Context context = zzaytVar2.getContext();
            int iZza = zza(context, map, "x", 0);
            int iZza2 = zza(context, map, "y", 0);
            int iZza3 = zza(context, map, i.WIDTH, -1);
            int iZza4 = zza(context, map, i.HEIGHT, -1);
            int iMin = Math.min(iZza3, zzaytVar2.zzxr() - iZza);
            int iMin2 = Math.min(iZza4, zzaytVar2.zzxq() - iZza2);
            try {
                i = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException unused2) {
                i = 0;
            }
            boolean z = Boolean.parseBoolean((String) map.get("spherical"));
            if (zEquals && zzayiVarZzxi.zzxc() == null) {
                zzayiVarZzxi.zza(iZza, iZza2, iMin, iMin2, i, z, new zzayq((String) map.get("flags")));
                zzayc zzaycVarZzxc = zzayiVarZzxi.zzxc();
                if (zzaycVarZzxc != null) {
                    zza(zzaycVarZzxc, (Map<String, String>) map);
                    return;
                }
                return;
            }
            zzayiVarZzxi.zze(iZza, iZza2, iMin, iMin2);
            return;
        }
        zzbbu zzbbuVarZzxj = zzaytVar2.zzxj();
        if (zzbbuVarZzxj != null) {
            if ("timeupdate".equals(str)) {
                String str5 = (String) map.get("currentTime");
                if (str5 == null) {
                    zzatm.zzeu("currentTime parameter missing from timeupdate video GMSG.");
                    return;
                }
                try {
                    zzbbuVarZzxj.zze(Float.parseFloat(str5));
                    return;
                } catch (NumberFormatException unused3) {
                    String strValueOf = String.valueOf(str5);
                    zzatm.zzeu(strValueOf.length() != 0 ? "Could not parse currentTime parameter from timeupdate video GMSG: ".concat(strValueOf) : new String("Could not parse currentTime parameter from timeupdate video GMSG: "));
                    return;
                }
            }
            if ("skip".equals(str)) {
                zzbbuVarZzxj.zzaal();
                return;
            }
        }
        zzayc zzaycVarZzxc2 = zzayiVarZzxi.zzxc();
        if (zzaycVarZzxc2 == null) {
            zzayc.zza(zzaytVar2);
            return;
        }
        if (aq.a.dF.equals(str)) {
            Context context2 = zzaytVar2.getContext();
            int iZza5 = zza(context2, map, "x", 0);
            int iZza6 = zza(context2, map, "y", 0);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, iZza5, iZza6, 0);
            zzaycVarZzxc2.zze(motionEventObtain);
            motionEventObtain.recycle();
            return;
        }
        if ("currentTime".equals(str)) {
            String str6 = (String) map.get(LocationConst.TIME);
            if (str6 == null) {
                zzatm.zzeu("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                zzaycVarZzxc2.seekTo((int) (Float.parseFloat(str6) * 1000.0f));
                return;
            } catch (NumberFormatException unused4) {
                String strValueOf2 = String.valueOf(str6);
                zzatm.zzeu(strValueOf2.length() != 0 ? "Could not parse time parameter from currentTime video GMSG: ".concat(strValueOf2) : new String("Could not parse time parameter from currentTime video GMSG: "));
                return;
            }
        }
        if (MessengerShareContentUtility.SHARE_BUTTON_HIDE.equals(str)) {
            zzaycVarZzxc2.setVisibility(4);
            return;
        }
        if ("load".equals(str)) {
            zzaycVarZzxc2.zzhh();
            return;
        }
        if ("loadControl".equals(str)) {
            zza(zzaycVarZzxc2, (Map<String, String>) map);
            return;
        }
        if ("muted".equals(str)) {
            if (Boolean.parseBoolean((String) map.get("muted"))) {
                zzaycVarZzxc2.zzww();
                return;
            } else {
                zzaycVarZzxc2.zzwx();
                return;
            }
        }
        if ("pause".equals(str)) {
            zzaycVarZzxc2.pause();
            return;
        }
        if ("play".equals(str)) {
            zzaycVarZzxc2.play();
            return;
        }
        if ("show".equals(str)) {
            zzaycVarZzxc2.setVisibility(0);
            return;
        }
        if (bf.a.fE.equals(str)) {
            String str7 = (String) map.get(bf.a.fE);
            String[] strArr = {str7};
            String str8 = (String) map.get("demuxed");
            if (str8 != null) {
                try {
                    JSONArray jSONArray = new JSONArray(str8);
                    String[] strArr2 = new String[jSONArray.length()];
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        strArr2[i2] = jSONArray.getString(i2);
                    }
                    strArr = strArr2;
                } catch (JSONException unused5) {
                    String strValueOf3 = String.valueOf(str8);
                    zzatm.zzeu(strValueOf3.length() != 0 ? "Malformed demuxed URL list for playback: ".concat(strValueOf3) : new String("Malformed demuxed URL list for playback: "));
                    strArr = new String[]{str7};
                }
            }
            zzaycVarZzxc2.zzc(str7, strArr);
            return;
        }
        if ("touchMove".equals(str)) {
            Context context3 = zzaytVar2.getContext();
            zzaycVarZzxc2.zza(zza(context3, map, "dx", 0), zza(context3, map, "dy", 0));
            if (this.zzeco) {
                return;
            }
            zzaytVar2.zzsr();
            this.zzeco = true;
            return;
        }
        if ("volume".equals(str)) {
            String str9 = (String) map.get("volume");
            if (str9 == null) {
                zzatm.zzeu("Level parameter missing from volume video GMSG.");
                return;
            }
            try {
                zzaycVarZzxc2.setVolume(Float.parseFloat(str9));
                return;
            } catch (NumberFormatException unused6) {
                String strValueOf4 = String.valueOf(str9);
                zzatm.zzeu(strValueOf4.length() != 0 ? "Could not parse volume parameter from volume video GMSG: ".concat(strValueOf4) : new String("Could not parse volume parameter from volume video GMSG: "));
                return;
            }
        }
        if ("watermark".equals(str)) {
            zzaycVarZzxc2.zzwy();
        } else {
            String strValueOf5 = String.valueOf(str);
            zzatm.zzeu(strValueOf5.length() != 0 ? "Unknown video action: ".concat(strValueOf5) : new String("Unknown video action: "));
        }
    }
}
