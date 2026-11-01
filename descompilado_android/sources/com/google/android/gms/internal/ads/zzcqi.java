package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcqi implements zzcra<zzcqj> {
    private final ScheduledExecutorService zzfez;
    private final zzcvk zzffn;
    private final zzdcs zzfnq;
    private final zzckv zzfzs;
    private String zzgda;
    private final zzckx zzgfe;
    private final Context zzlk;

    public zzcqi(zzdcs zzdcsVar, ScheduledExecutorService scheduledExecutorService, String str, zzckx zzckxVar, Context context, zzcvk zzcvkVar, zzckv zzckvVar) {
        this.zzfnq = zzdcsVar;
        this.zzfez = scheduledExecutorService;
        this.zzgda = str;
        this.zzgfe = zzckxVar;
        this.zzlk = context;
        this.zzffn = zzcvkVar;
        this.zzfzs = zzckvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcra
    public final zzdcp<zzcqj> zzalr() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmc)).booleanValue()) {
            return zzdcf.zzb(this.zzfnq.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcql
                private final zzcqi zzgfg;

                {
                    this.zzgfg = this;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zzgfg.zzamb();
                }
            }), new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzcqk
                private final zzcqi zzgfg;

                {
                    this.zzgfg = this;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    return this.zzgfg.zzh((List) obj);
                }
            }, this.zzfnq);
        }
        return zzdcf.zzah(null);
    }

    static final /* synthetic */ zzcqj zzg(List list) throws Exception {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            try {
                jSONArray.put(((zzdcp) it.next()).get());
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        if (jSONArray.length() == 0) {
            return null;
        }
        return new zzcqj(jSONArray.toString());
    }

    final /* synthetic */ void zza(String str, zzaxf zzaxfVar, Bundle bundle, List list) {
        try {
            this.zzfzs.zzgb(str);
            zzalj zzaljVarZzgc = this.zzfzs.zzgc(str);
            if (zzaljVarZzgc == null) {
                throw new Exception("Missing Adapter.");
            }
            zzaljVarZzgc.zza(ObjectWrapper.wrap(this.zzlk), this.zzgda, bundle, (Bundle) list.get(0), this.zzffn.zzbli, new zzcld(str, zzaljVarZzgc, zzaxfVar));
        } catch (Throwable th) {
            zzaxfVar.setException(new Exception("Error calling adapter"));
            String strValueOf = String.valueOf(str);
            zzatm.zzc(strValueOf.length() != 0 ? "Error calling adapter: ".concat(strValueOf) : new String("Error calling adapter: "), th);
        }
    }

    final /* synthetic */ zzdcp zzh(final List list) throws Exception {
        return zzdcf.zzh(list).zza(new Callable(list) { // from class: com.google.android.gms.internal.ads.zzcqm
            private final List zzgfh;

            {
                this.zzgfh = list;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return zzcqi.zzg(this.zzgfh);
            }
        }, this.zzfnq);
    }

    final /* synthetic */ List zzamb() throws Exception {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, List<Bundle>> entry : this.zzgfe.zzs(this.zzgda, this.zzffn.zzgjt).entrySet()) {
            final String key = entry.getKey();
            final List<Bundle> value = entry.getValue();
            final zzaxf zzaxfVar = new zzaxf();
            final Bundle bundle = this.zzffn.zzgjs.zzcbu != null ? this.zzffn.zzgjs.zzcbu.getBundle(key) : null;
            arrayList.add(zzdcf.zza(zzaxfVar, ((Long) zzuo.zzoj().zzd(zzyt.zzcmb)).longValue(), TimeUnit.MILLISECONDS, this.zzfez));
            this.zzfnq.execute(new Runnable(this, key, zzaxfVar, bundle, value) { // from class: com.google.android.gms.internal.ads.zzcqn
                private final String zzcyl;
                private final zzaxf zzfsp;
                private final List zzftq;
                private final zzcqi zzgfg;
                private final Bundle zzgfi;

                {
                    this.zzgfg = this;
                    this.zzcyl = key;
                    this.zzfsp = zzaxfVar;
                    this.zzgfi = bundle;
                    this.zzftq = value;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzgfg.zza(this.zzcyl, this.zzfsp, this.zzgfi, this.zzftq);
                }
            });
        }
        return arrayList;
    }
}
