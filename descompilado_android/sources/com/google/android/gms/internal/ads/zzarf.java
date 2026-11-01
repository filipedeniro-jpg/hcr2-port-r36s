package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzarf implements zzaro {
    private static List<Future<Void>> zzdnv = Collections.synchronizedList(new ArrayList());
    private final zzarn zzdkx;

    @GuardedBy("lock")
    private final zzdul zzdnw;

    @GuardedBy("lock")
    private final LinkedHashMap<String, zzduo> zzdnx;
    private final zzarq zzdoa;

    @VisibleForTesting
    private boolean zzdob;
    private final zzart zzdoc;
    private final Context zzlk;

    @GuardedBy("lock")
    private final List<String> zzdny = new ArrayList();

    @GuardedBy("lock")
    private final List<String> zzdnz = new ArrayList();
    private final Object lock = new Object();
    private HashSet<String> zzdod = new HashSet<>();
    private boolean zzdoe = false;
    private boolean zzdof = false;
    private boolean zzdog = false;

    public zzarf(Context context, zzawv zzawvVar, zzarn zzarnVar, String str, zzarq zzarqVar) {
        Preconditions.checkNotNull(zzarnVar, "SafeBrowsing config is not present.");
        this.zzlk = context.getApplicationContext() != null ? context.getApplicationContext() : context;
        this.zzdnx = new LinkedHashMap<>();
        this.zzdoa = zzarqVar;
        this.zzdkx = zzarnVar;
        Iterator<String> it = this.zzdkx.zzdoo.iterator();
        while (it.hasNext()) {
            this.zzdod.add(it.next().toLowerCase(Locale.ENGLISH));
        }
        this.zzdod.remove("cookie".toLowerCase(Locale.ENGLISH));
        zzdul zzdulVar = new zzdul();
        zzdulVar.zzhur = zzdua.zzb.zzg.OCTAGON_AD;
        zzdulVar.url = str;
        zzdulVar.zzhut = str;
        zzdua.zzb.C0048zzb.zza zzaVarZzbci = zzdua.zzb.C0048zzb.zzbci();
        if (this.zzdkx.zzdok != null) {
            zzaVarZzbci.zzhn(this.zzdkx.zzdok);
        }
        zzdulVar.zzhuv = (zzdua.zzb.C0048zzb) ((zzdqd) zzaVarZzbci.zzazm());
        zzdua.zzb.zzi.zza zzaVarZzbm = zzdua.zzb.zzi.zzbcs().zzbm(Wrappers.packageManager(this.zzlk).isCallerInstantApp());
        if (zzawvVar.zzbnh != null) {
            zzaVarZzbm.zzhq(zzawvVar.zzbnh);
        }
        long apkVersion = GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzlk);
        if (apkVersion > 0) {
            zzaVarZzbm.zzfo(apkVersion);
        }
        zzdulVar.zzhvf = (zzdua.zzb.zzi) ((zzdqd) zzaVarZzbm.zzazm());
        this.zzdnw = zzdulVar;
        this.zzdoc = new zzart(this.zzlk, this.zzdkx.zzdor, this);
    }

    static final /* synthetic */ Void zzdu(String str) {
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final zzarn zzti() {
        return this.zzdkx;
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final void zzdq(String str) {
        synchronized (this.lock) {
            this.zzdnw.zzhux = str;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final boolean zztj() {
        return PlatformVersion.isAtLeastKitKat() && this.zzdkx.zzdom && !this.zzdof;
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final void zzj(View view) {
        if (this.zzdkx.zzdom && !this.zzdof) {
            com.google.android.gms.ads.internal.zzp.zzjy();
            Bitmap bitmapZzl = zzatv.zzl(view);
            if (bitmapZzl == null) {
                zzarp.zzdv("Failed to capture the webview bitmap.");
            } else {
                this.zzdof = true;
                zzatv.zzd(new zzarg(this, bitmapZzl));
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final void zza(String str, Map<String, String> map, int i) {
        synchronized (this.lock) {
            if (i == 3) {
                this.zzdog = true;
            }
            if (this.zzdnx.containsKey(str)) {
                if (i == 3) {
                    this.zzdnx.get(str).zzhvu = zzdua.zzb.zzh.zza.zzhk(i);
                }
                return;
            }
            zzduo zzduoVar = new zzduo();
            zzduoVar.zzhvu = zzdua.zzb.zzh.zza.zzhk(i);
            zzduoVar.zzhvo = Integer.valueOf(this.zzdnx.size());
            zzduoVar.url = str;
            zzduoVar.zzhvp = new zzdun();
            if (this.zzdod.size() > 0 && map != null) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey() != null ? entry.getKey() : "";
                    String value = entry.getValue() != null ? entry.getValue() : "";
                    if (this.zzdod.contains(key.toLowerCase(Locale.ENGLISH))) {
                        arrayList.add((zzdua.zzb.zzc) ((zzdqd) zzdua.zzb.zzc.zzbck().zzdd(zzdot.zzhh(key)).zzde(zzdot.zzhh(value)).zzazm()));
                    }
                }
                zzdua.zzb.zzc[] zzcVarArr = new zzdua.zzb.zzc[arrayList.size()];
                arrayList.toArray(zzcVarArr);
                zzduoVar.zzhvp.zzhvj = zzcVarArr;
            }
            this.zzdnx.put(str, zzduoVar);
        }
    }

    final void zzdr(String str) {
        synchronized (this.lock) {
            this.zzdny.add(str);
        }
    }

    final void zzds(String str) {
        synchronized (this.lock) {
            this.zzdnz.add(str);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final String[] zza(String[] strArr) {
        return (String[]) this.zzdoc.zzb(strArr).toArray(new String[0]);
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final void zztk() {
        this.zzdoe = true;
    }

    @Nullable
    private final zzduo zzdt(String str) {
        zzduo zzduoVar;
        synchronized (this.lock) {
            zzduoVar = this.zzdnx.get(str);
        }
        return zzduoVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaro
    public final void zztl() {
        synchronized (this.lock) {
            zzdcp zzdcpVarZzb = zzdcf.zzb(this.zzdoa.zza(this.zzlk, this.zzdnx.keySet()), new zzdbq(this) { // from class: com.google.android.gms.internal.ads.zzare
                private final zzarf zzdnu;

                {
                    this.zzdnu = this;
                }

                @Override // com.google.android.gms.internal.ads.zzdbq
                public final zzdcp zzf(Object obj) {
                    return this.zzdnu.zzh((Map) obj);
                }
            }, zzawx.zzdwb);
            zzdcp zzdcpVarZza = zzdcf.zza(zzdcpVarZzb, 10L, TimeUnit.SECONDS, zzawx.zzdvz);
            zzdcf.zza(zzdcpVarZzb, new zzarj(this, zzdcpVarZza), zzawx.zzdwb);
            zzdnv.add(zzdcpVarZza);
        }
    }

    @VisibleForTesting
    private final zzdcp<Void> zztm() {
        zzdcp<Void> zzdcpVarZzb;
        if (!((this.zzdob && this.zzdkx.zzdoq) || (this.zzdog && this.zzdkx.zzdop) || (!this.zzdob && this.zzdkx.zzdon))) {
            return zzdcf.zzah(null);
        }
        synchronized (this.lock) {
            this.zzdnw.zzhuw = new zzduo[this.zzdnx.size()];
            this.zzdnx.values().toArray(this.zzdnw.zzhuw);
            this.zzdnw.zzhvg = (String[]) this.zzdny.toArray(new String[0]);
            this.zzdnw.zzhvh = (String[]) this.zzdnz.toArray(new String[0]);
            if (zzarp.isEnabled()) {
                String str = this.zzdnw.url;
                String str2 = this.zzdnw.zzhux;
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 53 + String.valueOf(str2).length());
                sb.append("Sending SB report\n  url: ");
                sb.append(str);
                sb.append("\n  clickUrl: ");
                sb.append(str2);
                sb.append("\n  resources: \n");
                StringBuilder sb2 = new StringBuilder(sb.toString());
                for (zzduo zzduoVar : this.zzdnw.zzhuw) {
                    sb2.append("    [");
                    sb2.append(zzduoVar.zzhvv.length);
                    sb2.append("] ");
                    sb2.append(zzduoVar.url);
                }
                zzarp.zzdv(sb2.toString());
            }
            zzdcp<String> zzdcpVarZza = new zzave(this.zzlk).zza(1, this.zzdkx.zzdol, null, zzdtz.zzb(this.zzdnw));
            if (zzarp.isEnabled()) {
                zzdcpVarZza.addListener(new zzari(this), zzawx.zzdvx);
            }
            zzdcpVarZzb = zzdcf.zzb(zzdcpVarZza, zzarh.zzdoi, zzawx.zzdwb);
        }
        return zzdcpVarZzb;
    }

    final /* synthetic */ zzdcp zzh(Map map) throws Exception {
        if (map != null) {
            try {
                for (String str : map.keySet()) {
                    JSONArray jSONArrayOptJSONArray = new JSONObject((String) map.get(str)).optJSONArray("matches");
                    if (jSONArrayOptJSONArray != null) {
                        synchronized (this.lock) {
                            int length = jSONArrayOptJSONArray.length();
                            zzduo zzduoVarZzdt = zzdt(str);
                            if (zzduoVarZzdt == null) {
                                String strValueOf = String.valueOf(str);
                                zzarp.zzdv(strValueOf.length() != 0 ? "Cannot find the corresponding resource object for ".concat(strValueOf) : new String("Cannot find the corresponding resource object for "));
                            } else {
                                zzduoVarZzdt.zzhvv = new String[length];
                                for (int i = 0; i < length; i++) {
                                    zzduoVarZzdt.zzhvv[i] = jSONArrayOptJSONArray.getJSONObject(i).getString("threat_type");
                                }
                                this.zzdob = (length > 0) | this.zzdob;
                            }
                        }
                    }
                }
            } catch (JSONException e) {
                if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcpb)).booleanValue()) {
                    zzatm.zzb("Failed to get SafeBrowsing metadata", e);
                }
                return zzdcf.zzi(new Exception("Safebrowsing report transmission failed."));
            }
        }
        if (this.zzdob) {
            synchronized (this.lock) {
                this.zzdnw.zzhur = zzdua.zzb.zzg.OCTAGON_AD_SB_MATCH;
            }
        }
        return zztm();
    }
}
