package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.sdk.controller.BannerJSAdapter;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzaed implements zzadx<Object> {
    private final Object lock = new Object();

    @GuardedBy("lock")
    private final Map<String, zzaef> zzcxr = new HashMap();

    public final void zza(String str, zzaef zzaefVar) {
        synchronized (this.lock) {
            this.zzcxr.put(str, zzaefVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzadx
    public final void zza(Object obj, Map<String, String> map) {
        String strConcat;
        String str = map.get("id");
        String str2 = map.get(BannerJSAdapter.FAIL);
        String str3 = map.get("fail_reason");
        String str4 = map.get("fail_stack");
        String str5 = map.get(IronSourceConstants.EVENTS_RESULT);
        if (TextUtils.isEmpty(str4)) {
            str3 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str4)) {
            strConcat = "";
        } else {
            String strValueOf = String.valueOf(str4);
            strConcat = strValueOf.length() != 0 ? "\n".concat(strValueOf) : new String("\n");
        }
        synchronized (this.lock) {
            zzaef zzaefVarRemove = this.zzcxr.remove(str);
            if (zzaefVarRemove == null) {
                String strValueOf2 = String.valueOf(str);
                zzatm.zzeu(strValueOf2.length() != 0 ? "Received result for unexpected method invocation: ".concat(strValueOf2) : new String("Received result for unexpected method invocation: "));
                return;
            }
            if (!TextUtils.isEmpty(str2)) {
                String strValueOf3 = String.valueOf(str3);
                String strValueOf4 = String.valueOf(strConcat);
                zzaefVarRemove.onFailure(strValueOf4.length() != 0 ? strValueOf3.concat(strValueOf4) : new String(strValueOf3));
            } else {
                if (str5 == null) {
                    zzaefVarRemove.zzc(null);
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(str5);
                    if (zzatm.zzuq()) {
                        String strValueOf5 = String.valueOf(jSONObject.toString(2));
                        zzatm.zzdy(strValueOf5.length() != 0 ? "Result GMSG: ".concat(strValueOf5) : new String("Result GMSG: "));
                    }
                    zzaefVarRemove.zzc(jSONObject);
                } catch (JSONException e) {
                    zzaefVarRemove.onFailure(e.getMessage());
                }
            }
        }
    }

    public final <EngineT extends zzagu> zzdcp<JSONObject> zza(EngineT enginet, String str, JSONObject jSONObject) {
        zzaxf zzaxfVar = new zzaxf();
        com.google.android.gms.ads.internal.zzp.zzjy();
        String strZzvi = zzatv.zzvi();
        zza(strZzvi, new zzaeg(this, zzaxfVar));
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("id", strZzvi);
            jSONObject2.put("args", jSONObject);
            enginet.zzb(str, jSONObject2);
        } catch (Exception e) {
            zzaxfVar.setException(e);
        }
        return zzaxfVar;
    }
}
