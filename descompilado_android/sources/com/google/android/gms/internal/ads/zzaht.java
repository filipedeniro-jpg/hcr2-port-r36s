package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaht implements zzagb, zzahu {
    private final zzahr zzdag;
    private final HashSet<AbstractMap.SimpleEntry<String, zzadx<? super zzahr>>> zzdah = new HashSet<>();

    public zzaht(zzahr zzahrVar) {
        this.zzdag = zzahrVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, Map map) {
        zzage.zza(this, str, map);
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzaft
    public final void zza(String str, JSONObject jSONObject) {
        zzage.zzb(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagu
    public final void zzb(String str, JSONObject jSONObject) {
        zzage.zza(this, str, jSONObject);
    }

    @Override // com.google.android.gms.internal.ads.zzagb
    public final void zzk(String str, String str2) {
        zzage.zza(this, str, str2);
    }

    @Override // com.google.android.gms.internal.ads.zzagb, com.google.android.gms.internal.ads.zzagu
    public final void zzct(String str) {
        this.zzdag.zzct(str);
    }

    @Override // com.google.android.gms.internal.ads.zzahr
    public final void zza(String str, zzadx<? super zzahr> zzadxVar) {
        this.zzdag.zza(str, zzadxVar);
        this.zzdah.add(new AbstractMap.SimpleEntry<>(str, zzadxVar));
    }

    @Override // com.google.android.gms.internal.ads.zzahr
    public final void zzb(String str, zzadx<? super zzahr> zzadxVar) {
        this.zzdag.zzb(str, zzadxVar);
        this.zzdah.remove(new AbstractMap.SimpleEntry(str, zzadxVar));
    }

    @Override // com.google.android.gms.internal.ads.zzahu
    public final void zzrg() {
        for (AbstractMap.SimpleEntry<String, zzadx<? super zzahr>> simpleEntry : this.zzdah) {
            String strValueOf = String.valueOf(simpleEntry.getValue().toString());
            zzatm.zzdy(strValueOf.length() != 0 ? "Unregistering eventhandler: ".concat(strValueOf) : new String("Unregistering eventhandler: "));
            this.zzdag.zzb(simpleEntry.getKey(), simpleEntry.getValue());
        }
        this.zzdah.clear();
    }
}
