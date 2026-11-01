package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.util.JsonReader;
import java.io.IOException;
import org.json.JSONException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcmv {
    public final String zzgdg;
    public String zzgdh;

    public zzcmv(JsonReader jsonReader) throws IllegalStateException, JSONException, IOException, NumberFormatException {
        jsonReader.beginObject();
        String strNextString = "";
        while (jsonReader.hasNext()) {
            String strNextName = jsonReader.nextName();
            strNextName = strNextName == null ? "" : strNextName;
            byte b = -1;
            if (strNextName.hashCode() == -995427962 && strNextName.equals("params")) {
                b = 0;
            }
            if (b != 0) {
                jsonReader.skipValue();
            } else {
                strNextString = jsonReader.nextString();
            }
        }
        this.zzgdg = strNextString;
        jsonReader.endObject();
    }

    final zzcmv zzn(Bundle bundle) {
        try {
            this.zzgdh = com.google.android.gms.ads.internal.zzp.zzjy().zzd(bundle).toString();
        } catch (JSONException unused) {
            this.zzgdh = "{}";
        }
        return this;
    }
}
