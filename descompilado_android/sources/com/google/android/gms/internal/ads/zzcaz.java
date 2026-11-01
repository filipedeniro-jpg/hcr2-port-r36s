package com.google.android.gms.internal.ads;

import androidx.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcaz {

    @GuardedBy("this")
    private List<Map<String, String>> zzfrv = new ArrayList();

    @GuardedBy("this")
    private boolean zzfrw = false;

    @GuardedBy("this")
    private boolean zzfrx = false;
    private String zzfry;
    private zzcay zzfrz;

    public zzcaz(String str, zzcay zzcayVar) {
        this.zzfry = str;
        this.zzfrz = zzcayVar;
    }

    public final synchronized void zzfy(String str) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
            Map<String, String> mapZzajv = zzajv();
            mapZzajv.put("action", "adapter_init_started");
            mapZzajv.put("ancn", str);
            this.zzfrv.add(mapZzajv);
        }
    }

    public final synchronized void zzfz(String str) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
            Map<String, String> mapZzajv = zzajv();
            mapZzajv.put("action", "adapter_init_finished");
            mapZzajv.put("ancn", str);
            this.zzfrv.add(mapZzajv);
        }
    }

    public final synchronized void zzr(String str, String str2) {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
            Map<String, String> mapZzajv = zzajv();
            mapZzajv.put("action", "adapter_init_finished");
            mapZzajv.put("ancn", str);
            mapZzajv.put("rqe", str2);
            this.zzfrv.add(mapZzajv);
        }
    }

    public final synchronized void zzajt() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
            if (!this.zzfrw) {
                Map<String, String> mapZzajv = zzajv();
                mapZzajv.put("action", "init_started");
                this.zzfrv.add(mapZzajv);
                this.zzfrw = true;
            }
        }
    }

    public final synchronized void zzaju() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmn)).booleanValue()) {
            if (!this.zzfrx) {
                Map<String, String> mapZzajv = zzajv();
                mapZzajv.put("action", "init_finished");
                this.zzfrv.add(mapZzajv);
                Iterator<Map<String, String>> it = this.zzfrv.iterator();
                while (it.hasNext()) {
                    this.zzfrz.zzm(it.next());
                }
                this.zzfrx = true;
            }
        }
    }

    private final Map<String, String> zzajv() {
        Map<String, String> mapZzajr = this.zzfrz.zzajr();
        mapZzajr.put("tms", Long.toString(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime(), 10));
        mapZzajr.put("tid", this.zzfry);
        return mapZzajr;
    }
}
