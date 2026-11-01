package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbvw {
    private final zzdcs zzfnq;
    private final zzbwa zzfnr;
    private final zzbwh zzfns;

    public zzbvw(zzdcs zzdcsVar, zzbwa zzbwaVar, zzbwh zzbwhVar) {
        this.zzfnq = zzdcsVar;
        this.zzfnr = zzbwaVar;
        this.zzfns = zzbwhVar;
    }

    public final zzdcp<zzbtx> zza(final zzcvj zzcvjVar, final zzcvb zzcvbVar, final JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        zzdcp zzdcpVarZzb;
        final zzdcp zzdcpVarSubmit = this.zzfnq.submit(new Callable(this, zzcvjVar, zzcvbVar, jSONObject) { // from class: com.google.android.gms.internal.ads.zzbvv
            private final zzcvb zzfdo;
            private final zzbvw zzfnn;
            private final zzcvj zzfno;
            private final JSONObject zzfnp;

            {
                this.zzfnn = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
                this.zzfnp = jSONObject;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() throws zzcin {
                zzbvw zzbvwVar = this.zzfnn;
                zzcvj zzcvjVar2 = this.zzfno;
                zzcvb zzcvbVar2 = this.zzfdo;
                JSONObject jSONObject2 = this.zzfnp;
                zzbtx zzbtxVar = new zzbtx();
                zzbtxVar.zzdh(jSONObject2.optInt("template_id", -1));
                zzbtxVar.zzfr(jSONObject2.optString("custom_template_id"));
                JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("omid_settings");
                zzbtxVar.zzfs(jSONObjectOptJSONObject2 != null ? jSONObjectOptJSONObject2.optString("omid_partner_name") : null);
                zzcvk zzcvkVar = zzcvjVar2.zzgjo.zzffn;
                if (!zzcvkVar.zzgju.contains(Integer.toString(zzbtxVar.zzahl()))) {
                    int iZzahl = zzbtxVar.zzahl();
                    StringBuilder sb = new StringBuilder(32);
                    sb.append("Invalid template ID: ");
                    sb.append(iZzahl);
                    throw new zzcin(sb.toString(), 0);
                }
                if (zzbtxVar.zzahl() == 3) {
                    if (zzbtxVar.getCustomTemplateId() == null) {
                        throw new zzcin("No custom template id for custom template ad response.", 0);
                    }
                    if (!zzcvkVar.zzgjv.contains(zzbtxVar.getCustomTemplateId())) {
                        throw new zzcin("Unexpected custom template id in the response.", 0);
                    }
                }
                zzbtxVar.setStarRating(jSONObject2.optDouble("rating", -1.0d));
                String strOptString = jSONObject2.optString("headline", null);
                if (zzcvbVar2.zzdlx) {
                    com.google.android.gms.ads.internal.zzp.zzjy();
                    String strZzvl = zzatv.zzvl();
                    StringBuilder sb2 = new StringBuilder(String.valueOf(strZzvl).length() + 3 + String.valueOf(strOptString).length());
                    sb2.append(strZzvl);
                    sb2.append(" : ");
                    sb2.append(strOptString);
                    strOptString = sb2.toString();
                }
                zzbtxVar.zzo("headline", strOptString);
                zzbtxVar.zzo(TtmlNode.TAG_BODY, jSONObject2.optString(TtmlNode.TAG_BODY, null));
                zzbtxVar.zzo("call_to_action", jSONObject2.optString("call_to_action", null));
                zzbtxVar.zzo("store", jSONObject2.optString("store", null));
                zzbtxVar.zzo("price", jSONObject2.optString("price", null));
                zzbtxVar.zzo("advertiser", jSONObject2.optString("advertiser", null));
                return zzbtxVar;
            }
        });
        final zzdcp<List<zzaae>> zzdcpVarZzd = this.zzfnr.zzd(jSONObject, "images");
        final zzdcp<zzaae> zzdcpVarZzc = this.zzfnr.zzc(jSONObject, "secondary_image");
        final zzdcp<zzaae> zzdcpVarZzc2 = this.zzfnr.zzc(jSONObject, "app_icon");
        final zzdcp<zzzz> zzdcpVarZze = this.zzfnr.zze(jSONObject, "attribution");
        final zzdcp<zzbbc> zzdcpVarZzl = this.zzfnr.zzl(jSONObject);
        final zzbwa zzbwaVar = this.zzfnr;
        if (!jSONObject.optBoolean("enable_omid") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("omid_settings")) == null) {
            zzdcpVarZzb = zzdcf.zzah(null);
        } else {
            final String strOptString = jSONObjectOptJSONObject.optString("omid_html");
            if (TextUtils.isEmpty(strOptString)) {
                zzdcpVarZzb = zzdcf.zzah(null);
            } else {
                zzdcpVarZzb = zzdcf.zzb(zzdcf.zzah(null), new zzdbq(zzbwaVar, strOptString) { // from class: com.google.android.gms.internal.ads.zzbwe
                    private final String zzcyl;
                    private final zzbwa zzfog;

                    {
                        this.zzfog = zzbwaVar;
                        this.zzcyl = strOptString;
                    }

                    @Override // com.google.android.gms.internal.ads.zzdbq
                    public final zzdcp zzf(Object obj) {
                        return this.zzfog.zzb(this.zzcyl, obj);
                    }
                }, zzawx.zzdwa);
            }
        }
        final zzdcp zzdcpVar = zzdcpVarZzb;
        final zzdcp<List<zzbwm>> zzdcpVarZzg = this.zzfns.zzg(jSONObject, "custom_assets");
        return zzdcf.zza(zzdcpVarSubmit, zzdcpVarZzd, zzdcpVarZzc, zzdcpVarZzc2, zzdcpVarZze, zzdcpVarZzl, zzdcpVar, zzdcpVarZzg).zza(new Callable(this, zzdcpVarSubmit, zzdcpVarZzd, zzdcpVarZzc2, zzdcpVarZzc, zzdcpVarZze, jSONObject, zzdcpVarZzl, zzdcpVar, zzdcpVarZzg) { // from class: com.google.android.gms.internal.ads.zzbvy
            private final zzdcp zzffe;
            private final zzbvw zzfnn;
            private final zzdcp zzfnv;
            private final zzdcp zzfnw;
            private final zzdcp zzfnx;
            private final zzdcp zzfny;
            private final JSONObject zzfnz;
            private final zzdcp zzfoa;
            private final zzdcp zzfob;
            private final zzdcp zzfoc;

            {
                this.zzfnn = this;
                this.zzfnv = zzdcpVarSubmit;
                this.zzffe = zzdcpVarZzd;
                this.zzfnw = zzdcpVarZzc2;
                this.zzfnx = zzdcpVarZzc;
                this.zzfny = zzdcpVarZze;
                this.zzfnz = jSONObject;
                this.zzfoa = zzdcpVarZzl;
                this.zzfob = zzdcpVar;
                this.zzfoc = zzdcpVarZzg;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                zzbvw zzbvwVar = this.zzfnn;
                zzdcp zzdcpVar2 = this.zzfnv;
                zzdcp zzdcpVar3 = this.zzffe;
                zzdcp zzdcpVar4 = this.zzfnw;
                zzdcp zzdcpVar5 = this.zzfnx;
                zzdcp zzdcpVar6 = this.zzfny;
                JSONObject jSONObject2 = this.zzfnz;
                zzdcp zzdcpVar7 = this.zzfoa;
                zzdcp zzdcpVar8 = this.zzfob;
                zzdcp zzdcpVar9 = this.zzfoc;
                zzbtx zzbtxVar = (zzbtx) zzdcpVar2.get();
                zzbtxVar.setImages((List) zzdcpVar3.get());
                zzbtxVar.zza((zzaas) zzdcpVar4.get());
                zzbtxVar.zzb((zzaas) zzdcpVar5.get());
                zzbtxVar.zza((zzaak) zzdcpVar6.get());
                zzbtxVar.zzf(zzbwa.zzi(jSONObject2));
                zzbtxVar.zza(zzbwa.zzj(jSONObject2));
                zzbbc zzbbcVar = (zzbbc) zzdcpVar7.get();
                if (zzbbcVar != null) {
                    zzbtxVar.zzi(zzbbcVar);
                    zzbtxVar.zzaa(zzbbcVar.getView());
                    zzbtxVar.zzb(zzbbcVar.zzxj());
                }
                zzbbc zzbbcVar2 = (zzbbc) zzdcpVar8.get();
                if (zzbbcVar2 != null) {
                    zzbtxVar.zzj(zzbbcVar2);
                }
                for (zzbwm zzbwmVar : (List) zzdcpVar9.get()) {
                    int i = zzbwmVar.type;
                    if (i == 1) {
                        zzbtxVar.zzo(zzbwmVar.zzce, zzbwmVar.zzfon);
                    } else if (i == 2) {
                        zzbtxVar.zza(zzbwmVar.zzce, zzbwmVar.zzfoo);
                    }
                }
                return zzbtxVar;
            }
        }, this.zzfnq);
    }
}
