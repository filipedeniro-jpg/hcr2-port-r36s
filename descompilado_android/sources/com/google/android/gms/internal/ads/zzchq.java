package com.google.android.gms.internal.ads;

import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzchq implements zzchn<zzbtp> {
    private final zzdcs zzfnq;
    private final zzbsr zzfyh;
    private final zzbvw zzfyi;

    public zzchq(zzbsr zzbsrVar, zzdcs zzdcsVar, zzbvw zzbvwVar) {
        this.zzfyh = zzbsrVar;
        this.zzfnq = zzdcsVar;
        this.zzfyi = zzbvwVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return (zzcvbVar.zzgiw == null || zzcvbVar.zzgiw.zzfja == null) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<List<zzdcp<zzbtp>>> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        zzdcp<zzbxn> zzdcpVarZzanb = this.zzfyh.zzack().zzanb();
        this.zzfyh.zzack().zza(zzdcpVarZzanb);
        return zzdcf.zzb(zzdcf.zzb(zzdcpVarZzanb, new zzdbq(this, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzchp
            private final zzcvb zzfxy;
            private final zzchq zzfyg;

            {
                this.zzfyg = this;
                this.zzfxy = zzcvbVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfyg.zza(this.zzfxy, (zzbxn) obj);
            }
        }, this.zzfnq), new zzdbq(this, zzcvjVar, zzcvbVar) { // from class: com.google.android.gms.internal.ads.zzchs
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzchq zzfyg;

            {
                this.zzfyg = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
            }

            @Override // com.google.android.gms.internal.ads.zzdbq
            public final zzdcp zzf(Object obj) {
                return this.zzfyg.zza(this.zzfno, this.zzfdo, (JSONArray) obj);
            }
        }, this.zzfnq);
    }

    private final zzdcp<zzbtp> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar, final JSONObject jSONObject) {
        final zzdcp<zzbxn> zzdcpVarZzanb = this.zzfyh.zzack().zzanb();
        final zzdcp<zzbtx> zzdcpVarZza = this.zzfyi.zza(zzcvjVar, zzcvbVar, jSONObject);
        return zzdcf.zza(zzdcpVarZzanb, zzdcpVarZza).zza(new Callable(this, zzdcpVarZza, zzdcpVarZzanb, zzcvjVar, zzcvbVar, jSONObject) { // from class: com.google.android.gms.internal.ads.zzcht
            private final zzdcp zzffe;
            private final zzdcp zzfnv;
            private final zzcvj zzfya;
            private final zzchq zzfyg;
            private final zzcvb zzfyj;
            private final JSONObject zzfyk;

            {
                this.zzfyg = this;
                this.zzfnv = zzdcpVarZza;
                this.zzffe = zzdcpVarZzanb;
                this.zzfya = zzcvjVar;
                this.zzfyj = zzcvbVar;
                this.zzfyk = jSONObject;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzfyg.zza(this.zzfnv, this.zzffe, this.zzfya, this.zzfyj, this.zzfyk);
            }
        }, this.zzfnq);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ zzbtp zza(zzdcp zzdcpVar, zzdcp zzdcpVar2, zzcvj zzcvjVar, zzcvb zzcvbVar, JSONObject jSONObject) throws Exception {
        zzbtx zzbtxVar = (zzbtx) zzdcpVar.get();
        zzbxn zzbxnVar = (zzbxn) zzdcpVar2.get();
        zzbtz zzbtzVarZza = this.zzfyh.zza(new zzbkk(zzcvjVar, zzcvbVar, null), new zzbuj(zzbtxVar), new zzbtd(jSONObject, zzbxnVar));
        zzbtzVarZza.zzace().zzajc();
        zzbtzVarZza.zzacf().zzb(zzbxnVar);
        zzbtzVarZza.zzacg().zzl(zzbtxVar.zzahq());
        return zzbtzVarZza.zzacd();
    }

    final /* synthetic */ zzdcp zza(zzcvj zzcvjVar, zzcvb zzcvbVar, JSONArray jSONArray) throws Exception {
        if (jSONArray.length() == 0) {
            return zzdcf.zzi(new zzcce(3));
        }
        if (zzcvjVar.zzgjo.zzffn.zzgcq > 1) {
            int length = jSONArray.length();
            this.zzfyh.zzack().zzdk(Math.min(length, zzcvjVar.zzgjo.zzffn.zzgcq));
            ArrayList arrayList = new ArrayList(zzcvjVar.zzgjo.zzffn.zzgcq);
            for (int i = 0; i < zzcvjVar.zzgjo.zzffn.zzgcq; i++) {
                if (i < length) {
                    arrayList.add(zzb(zzcvjVar, zzcvbVar, jSONArray.getJSONObject(i)));
                } else {
                    arrayList.add(zzdcf.zzi(new zzcce(3)));
                }
            }
            return zzdcf.zzah(arrayList);
        }
        return zzdcf.zzb(zzb(zzcvjVar, zzcvbVar, jSONArray.getJSONObject(0)), zzchu.zzdoi, this.zzfnq);
    }

    final /* synthetic */ zzdcp zza(zzcvb zzcvbVar, zzbxn zzbxnVar) throws Exception {
        JSONObject jSONObjectZza = zzavm.zza("isNonagon", (Object) true);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServerResponseWrapper.RESPONSE_FIELD, zzcvbVar.zzgiw.zzfja);
        jSONObject.put("sdk_params", jSONObjectZza);
        return zzdcf.zzb(zzbxnVar.zzc("google.afma.nativeAds.preProcessJson", jSONObject), zzchr.zzbks, this.zzfnq);
    }
}
