package com.google.android.gms.internal.ads;

import android.content.Context;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.io.StringReader;
import java.util.concurrent.Executor;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdg {
    private final Executor executor;
    private final zzcvk zzffn;
    private final zzawv zzfui;
    private final Context zzlk;

    public zzcdg(Context context, zzawv zzawvVar, zzcvk zzcvkVar, Executor executor) {
        this.zzlk = context;
        this.zzfui = zzawvVar;
        this.zzffn = zzcvkVar;
        this.executor = executor;
    }

    public final zzdcp<zzcvj> zzake() {
        final zzahv zzahvVarZza = com.google.android.gms.ads.internal.zzp.zzkl().zzb(this.zzlk, this.zzfui).zza("google.afma.response.normalize", zzaic.zzdan, zzaic.zzdan);
        final zztj zztjVar = this.zzffn.zzgjs.zzcca;
        return zzdcf.zzb(zzdcf.zzb(zzdcf.zzb(zzdcf.zzah(""), new zzdbq(this, zztjVar) { // from class: com.google.android.gms.internal.ads.zzcdf
            private final zzcdg zzfug;
            private final zztj zzfuh;

            {
                this.zzfug = this;
                this.zzfuh = zztjVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) throws JSONException {
                zztj zztjVar2 = this.zzfuh;
                String str = zztjVar2.zzcbj;
                String str2 = zztjVar2.zzcbk;
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("headers", new JSONObject());
                jSONObject3.put(TtmlNode.TAG_BODY, str);
                jSONObject2.put("base_url", "");
                jSONObject2.put("signals", new JSONObject(str2));
                jSONObject.put(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, jSONObject2);
                jSONObject.put(ServerResponseWrapper.RESPONSE_FIELD, jSONObject3);
                jSONObject.put("flags", new JSONObject());
                return zzdcf.zzah(jSONObject);
            }
        }, this.executor), new zzdbq(zzahvVarZza) { // from class: com.google.android.gms.internal.ads.zzcdi
            private final zzahv zzfuj;

            {
                this.zzfuj = zzahvVarZza;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfuj.zzi((JSONObject) obj);
            }
        }, this.executor), new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzcdh
            private final zzcdg zzfug;

            {
                this.zzfug = this;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfug.zzn((JSONObject) obj);
            }
        }, this.executor);
    }

    final /* synthetic */ zzdcp zzn(JSONObject jSONObject) throws Exception {
        return zzdcf.zzah(new zzcvj(new zzcve(this.zzffn), zzcvh.zza(new StringReader(jSONObject.toString()))));
    }
}
