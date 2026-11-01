package com.google.android.gms.internal.ads;

import android.util.JsonReader;
import android.util.JsonWriter;
import androidx.annotation.Nullable;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcvf implements zzavr {

    @Nullable
    public final String zzdhn;

    @Nullable
    public final String zzdhp;
    public final JSONObject zzfja;
    private final JSONObject zzgjj;

    zzcvf(JsonReader jsonReader) throws IllegalStateException, JSONException, IOException, NumberFormatException {
        this.zzgjj = zzavm.zzc(jsonReader);
        this.zzdhp = this.zzgjj.optString("ad_html", null);
        this.zzdhn = this.zzgjj.optString("ad_base_url", null);
        this.zzfja = this.zzgjj.optJSONObject("ad_json");
    }

    @Override // com.google.android.gms.internal.ads.zzavr
    public final void zza(JsonWriter jsonWriter) throws IOException {
        zzavm.zza(jsonWriter, this.zzgjj);
    }
}
