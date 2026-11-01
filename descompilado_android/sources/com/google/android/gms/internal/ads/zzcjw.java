package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzbnj;
import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcjw<AdT, AdapterT, ListenerT extends zzbnj> implements zzcfk<AdT> {
    private final zzcfo<AdapterT, ListenerT> zzezx;
    private final zzcxn zzffo;
    private final zzcfn<AdT, AdapterT, ListenerT> zzfzu;
    private final zzdcs zzfzv;

    public zzcjw(zzcxn zzcxnVar, zzdcs zzdcsVar, zzcfo<AdapterT, ListenerT> zzcfoVar, zzcfn<AdT, AdapterT, ListenerT> zzcfnVar) {
        this.zzffo = zzcxnVar;
        this.zzfzv = zzdcsVar;
        this.zzfzu = zzcfnVar;
        this.zzezx = zzcfoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final boolean zza(zzcvj zzcvjVar, zzcvb zzcvbVar) {
        return !zzcvbVar.zzgix.isEmpty();
    }

    @Override // com.google.android.gms.internal.ads.zzcfk
    public final zzdcp<AdT> zzb(final zzcvj zzcvjVar, final zzcvb zzcvbVar) {
        final zzcfl<AdapterT, ListenerT> zzcflVarZzd;
        Iterator<String> it = zzcvbVar.zzgix.iterator();
        while (true) {
            if (!it.hasNext()) {
                zzcflVarZzd = null;
                break;
            }
            try {
                zzcflVarZzd = this.zzezx.zzd(it.next(), zzcvbVar.zzgiz);
                break;
            } catch (zzcvr unused) {
            }
        }
        if (zzcflVarZzd == null) {
            return zzdcf.zzi(new zzcib("unable to instantiate mediation adapter class"));
        }
        zzaxf zzaxfVar = new zzaxf();
        zzcflVarZzd.zzfwt.zza(new zzcjx(this, zzaxfVar, zzcflVarZzd));
        if (zzcvbVar.zzdlx) {
            Bundle bundle = zzcvjVar.zzgjo.zzffn.zzgjs.zzcbu;
            Bundle bundle2 = bundle.getBundle(AdMobAdapter.class.getName());
            if (bundle2 == null) {
                bundle2 = new Bundle();
                bundle.putBundle(AdMobAdapter.class.getName(), bundle2);
            }
            bundle2.putBoolean("render_test_ad_label", true);
        }
        return this.zzffo.zzu(zzcxk.ADAPTER_LOAD_AD_SYN).zza(new zzcwx(this, zzcvjVar, zzcvbVar, zzcflVarZzd) { // from class: com.google.android.gms.internal.ads.zzcjv
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcfl zzfzi;
            private final zzcjw zzfzt;

            {
                this.zzfzt = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
                this.zzfzi = zzcflVarZzd;
            }

            @Override // com.google.android.gms.internal.ads.zzcwx
            public final void run() throws Exception {
                this.zzfzt.zzd(this.zzfno, this.zzfdo, this.zzfzi);
            }
        }, this.zzfzv).zzw(zzcxk.ADAPTER_LOAD_AD_ACK).zzb(zzaxfVar).zzw(zzcxk.ADAPTER_WRAP_ADAPTER).zzb(new zzcwu(this, zzcvjVar, zzcvbVar, zzcflVarZzd) { // from class: com.google.android.gms.internal.ads.zzcjy
            private final zzcvb zzfdo;
            private final zzcvj zzfno;
            private final zzcfl zzfzi;
            private final zzcjw zzfzt;

            {
                this.zzfzt = this;
                this.zzfno = zzcvjVar;
                this.zzfdo = zzcvbVar;
                this.zzfzi = zzcflVarZzd;
            }

            @Override // com.google.android.gms.internal.ads.zzcwu
            public final Object apply(Object obj) {
                return this.zzfzt.zza(this.zzfno, this.zzfdo, this.zzfzi, (Void) obj);
            }
        }).zzano();
    }

    final /* synthetic */ Object zza(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar, Void r4) throws Exception {
        return this.zzfzu.zzb(zzcvjVar, zzcvbVar, zzcflVar);
    }

    final /* synthetic */ void zzd(zzcvj zzcvjVar, zzcvb zzcvbVar, zzcfl zzcflVar) throws Exception {
        this.zzfzu.zza(zzcvjVar, zzcvbVar, zzcflVar);
    }
}
