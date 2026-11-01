package com.google.android.gms.internal.ads;

import com.facebook.share.internal.MessengerShareContentUtility;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbwh {
    private final Executor executor;
    private final zzbwa zzfnr;

    public zzbwh(Executor executor, zzbwa zzbwaVar) {
        this.executor = executor;
        this.zzfnr = zzbwaVar;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0070  */
    public final zzdcp<List<zzbwm>> zzg(JSONObject jSONObject, String str) {
        zzdcp zzdcpVarZzah;
        final String strOptString;
        char c;
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(str);
        if (jSONArrayOptJSONArray == null) {
            return zzdcf.zzah(Collections.emptyList());
        }
        ArrayList arrayList = new ArrayList();
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null && (strOptString = jSONObjectOptJSONObject.optString("name")) != null) {
                String strOptString2 = jSONObjectOptJSONObject.optString("type");
                if ("string".equals(strOptString2)) {
                    c = 1;
                } else {
                    c = MessengerShareContentUtility.MEDIA_IMAGE.equals(strOptString2) ? (char) 2 : (char) 0;
                }
                if (c == 1) {
                    zzdcpVarZzah = zzdcf.zzah(new zzbwm(strOptString, jSONObjectOptJSONObject.optString("string_value")));
                } else if (c == 2) {
                    zzdcpVarZzah = zzdcf.zzb(this.zzfnr.zzc(jSONObjectOptJSONObject, "image_value"), new zzczs(strOptString) { // from class: com.google.android.gms.internal.ads.zzbwj
                        private final String zzcys;

                        {
                            this.zzcys = strOptString;
                        }

                        @Override // com.google.android.gms.internal.ads.zzczs
                        public final Object apply(Object obj) {
                            return new zzbwm(this.zzcys, (zzaae) obj);
                        }
                    }, this.executor);
                } else {
                    zzdcpVarZzah = zzdcf.zzah(null);
                }
            } else {
                zzdcpVarZzah = zzdcf.zzah(null);
            }
            arrayList.add(zzdcpVarZzah);
        }
        return zzdcf.zzb(zzdcf.zzg(arrayList), zzbwk.zzdoi, this.executor);
    }
}
