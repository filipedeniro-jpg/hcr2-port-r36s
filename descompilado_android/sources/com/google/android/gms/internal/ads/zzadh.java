package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.plus.PlusShare;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzadh implements zzadx<zzbbc> {
    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzbbc zzbbcVar2 = zzbbcVar;
        String str = (String) map.get("action");
        if ("tick".equals(str)) {
            String str2 = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
            String str3 = (String) map.get("start_label");
            String str4 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str2)) {
                zzatm.zzeu("No label given for CSI tick.");
                return;
            }
            if (TextUtils.isEmpty(str4)) {
                zzatm.zzeu("No timestamp given for CSI tick.");
                return;
            }
            try {
                long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() + (Long.parseLong(str4) - com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis());
                if (TextUtils.isEmpty(str3)) {
                    str3 = "native:view_load";
                }
                zzbbcVar2.zzxo().zza(str2, str3, jElapsedRealtime);
                return;
            } catch (NumberFormatException e) {
                zzatm.zzd("Malformed timestamp for CSI tick.", e);
                return;
            }
        }
        if ("experiment".equals(str)) {
            String str5 = (String) map.get("value");
            if (TextUtils.isEmpty(str5)) {
                zzatm.zzeu("No value given for CSI experiment.");
                return;
            }
            zzzh zzzhVarZzpy = zzbbcVar2.zzxo().zzpy();
            if (zzzhVarZzpy == null) {
                zzatm.zzeu("No ticker for WebView, dropping experiment ID.");
                return;
            } else {
                zzzhVarZzpy.zzj("e", str5);
                return;
            }
        }
        if ("extra".equals(str)) {
            String str6 = (String) map.get("name");
            String str7 = (String) map.get("value");
            if (TextUtils.isEmpty(str7)) {
                zzatm.zzeu("No value given for CSI extra.");
                return;
            }
            if (TextUtils.isEmpty(str6)) {
                zzatm.zzeu("No name given for CSI extra.");
                return;
            }
            zzzh zzzhVarZzpy2 = zzbbcVar2.zzxo().zzpy();
            if (zzzhVarZzpy2 == null) {
                zzatm.zzeu("No ticker for WebView, dropping extra parameter.");
            } else {
                zzzhVarZzpy2.zzj(str6, str7);
            }
        }
    }
}
