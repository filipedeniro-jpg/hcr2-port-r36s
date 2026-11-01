package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.security.NetworkSecurityPolicy;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatr implements zzato {

    @Nullable
    @GuardedBy("lock")
    private SharedPreferences zzcfs;
    private boolean zzdrp;
    private zzdcp<?> zzdrr;

    @Nullable
    @GuardedBy("lock")
    private SharedPreferences.Editor zzdrt;

    @Nullable
    @GuardedBy("lock")
    private String zzdrv;

    @Nullable
    @GuardedBy("lock")
    private String zzdrw;
    private final Object lock = new Object();
    private final List<Runnable> zzdrq = new ArrayList();

    @Nullable
    @GuardedBy("lock")
    private zzqb zzdrs = null;

    @GuardedBy("lock")
    private boolean zzdru = false;

    @GuardedBy("lock")
    private boolean zzdip = true;

    @GuardedBy("lock")
    private boolean zzdjc = false;

    @GuardedBy("lock")
    private String zzdjf = "";

    @GuardedBy("lock")
    private long zzdrx = 0;

    @GuardedBy("lock")
    private long zzdry = 0;

    @GuardedBy("lock")
    private long zzdrz = 0;

    @GuardedBy("lock")
    private int zzdsa = -1;

    @GuardedBy("lock")
    private int zzdsb = 0;

    @GuardedBy("lock")
    private Set<String> zzdsc = Collections.emptySet();

    @GuardedBy("lock")
    private JSONObject zzdsd = new JSONObject();

    @GuardedBy("lock")
    private boolean zzdko = true;

    @GuardedBy("lock")
    private boolean zzdkz = true;

    @GuardedBy("lock")
    private String zzdse = null;

    @GuardedBy("lock")
    private int zzdsf = -1;

    public final void zza(final Context context, String str, boolean z) {
        final String strConcat;
        synchronized (this.lock) {
            if (this.zzcfs != null) {
                return;
            }
            if (str == null) {
                strConcat = "admob";
            } else {
                String strValueOf = String.valueOf(str);
                strConcat = strValueOf.length() != 0 ? "admob__".concat(strValueOf) : new String("admob__");
            }
            this.zzdrr = zzawx.zzdvx.submit(new Runnable(this, context, strConcat) { // from class: com.google.android.gms.internal.ads.zzatq
                private final Context zzcev;
                private final String zzdbj;
                private final zzatr zzdro;

                {
                    this.zzdro = this;
                    this.zzcev = context;
                    this.zzdbj = strConcat;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzdro.zzp(this.zzcev, this.zzdbj);
                }
            });
            this.zzdrp = z;
        }
    }

    private final void zzvf() {
        zzdcp<?> zzdcpVar = this.zzdrr;
        if (zzdcpVar == null || zzdcpVar.isDone()) {
            return;
        }
        try {
            this.zzdrr.get(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            zzatm.zzd("Interrupted while waiting for preferences loaded.", e);
        } catch (CancellationException e2) {
            e = e2;
            zzatm.zzc("Fail to initialize AdSharedPreferenceManager.", e);
        } catch (ExecutionException e3) {
            e = e3;
            zzatm.zzc("Fail to initialize AdSharedPreferenceManager.", e);
        } catch (TimeoutException e4) {
            e = e4;
            zzatm.zzc("Fail to initialize AdSharedPreferenceManager.", e);
        }
    }

    private final Bundle zzvg() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("listener_registration_bundle", true);
        synchronized (this.lock) {
            bundle.putBoolean("use_https", this.zzdip);
            bundle.putBoolean("content_url_opted_out", this.zzdko);
            bundle.putBoolean("content_vertical_opted_out", this.zzdkz);
            bundle.putBoolean("auto_collect_location", this.zzdjc);
            bundle.putInt("version_code", this.zzdsb);
            bundle.putStringArray("never_pool_slots", (String[]) this.zzdsc.toArray(new String[0]));
            bundle.putString("app_settings_json", this.zzdjf);
            bundle.putLong("app_settings_last_update_ms", this.zzdrx);
            bundle.putLong("app_last_background_time_ms", this.zzdry);
            bundle.putInt("request_in_session_count", this.zzdsa);
            bundle.putLong("first_ad_req_time_ms", this.zzdrz);
            bundle.putString("native_advanced_settings", this.zzdsd.toString());
            bundle.putString("display_cutout", this.zzdse);
            bundle.putInt("app_measurement_npa", this.zzdsf);
            if (this.zzdrv != null) {
                bundle.putString("content_url_hashes", this.zzdrv);
            }
            if (this.zzdrw != null) {
                bundle.putString("content_vertical_hashes", this.zzdrw);
            }
        }
        return bundle;
    }

    private final void zzc(Bundle bundle) {
        zzawx.zzdvx.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzatt
            private final zzatr zzdro;

            {
                this.zzdro = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzdro.zzur();
            }
        });
    }

    @Override // com.google.android.gms.internal.ads.zzato
    @Nullable
    public final zzqb zzur() {
        if (!this.zzdrp || !PlatformVersion.isAtLeastIceCreamSandwich()) {
            return null;
        }
        if (zzus() && zzuu()) {
            return null;
        }
        if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcih)).booleanValue()) {
            return null;
        }
        synchronized (this.lock) {
            if (Looper.getMainLooper() == null) {
                return null;
            }
            if (this.zzdrs == null) {
                this.zzdrs = new zzqb();
            }
            this.zzdrs.zzlr();
            zzatm.zzet("start fetching content...");
            return this.zzdrs;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzah(boolean z) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdko == z) {
                return;
            }
            this.zzdko = z;
            if (this.zzdrt != null) {
                this.zzdrt.putBoolean("content_url_opted_out", z);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzdko);
            bundle.putBoolean("content_vertical_opted_out", this.zzdkz);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final boolean zzus() {
        boolean z;
        zzvf();
        synchronized (this.lock) {
            z = this.zzdko;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzdz(@Nullable String str) {
        zzvf();
        synchronized (this.lock) {
            if (str != null) {
                if (!str.equals(this.zzdrv)) {
                    this.zzdrv = str;
                    if (this.zzdrt != null) {
                        this.zzdrt.putString("content_url_hashes", str);
                        this.zzdrt.apply();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("content_url_hashes", str);
                    zzc(bundle);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    @Nullable
    public final String zzut() {
        String str;
        zzvf();
        synchronized (this.lock) {
            str = this.zzdrv;
        }
        return str;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzai(boolean z) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdkz == z) {
                return;
            }
            this.zzdkz = z;
            if (this.zzdrt != null) {
                this.zzdrt.putBoolean("content_vertical_opted_out", z);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", this.zzdko);
            bundle.putBoolean("content_vertical_opted_out", this.zzdkz);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final boolean zzuu() {
        boolean z;
        zzvf();
        synchronized (this.lock) {
            z = this.zzdkz;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzea(@Nullable String str) {
        zzvf();
        synchronized (this.lock) {
            if (str != null) {
                if (!str.equals(this.zzdrw)) {
                    this.zzdrw = str;
                    if (this.zzdrt != null) {
                        this.zzdrt.putString("content_vertical_hashes", str);
                        this.zzdrt.apply();
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("content_vertical_hashes", str);
                    zzc(bundle);
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    @Nullable
    public final String zzuv() {
        String str;
        zzvf();
        synchronized (this.lock) {
            str = this.zzdrw;
        }
        return str;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzaj(boolean z) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdjc == z) {
                return;
            }
            this.zzdjc = z;
            if (this.zzdrt != null) {
                this.zzdrt.putBoolean("auto_collect_location", z);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putBoolean("auto_collect_location", z);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final boolean zzuw() {
        boolean z;
        zzvf();
        synchronized (this.lock) {
            z = this.zzdjc;
        }
        return z;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzcn(int i) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdsb == i) {
                return;
            }
            this.zzdsb = i;
            if (this.zzdrt != null) {
                this.zzdrt.putInt("version_code", i);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("version_code", i);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final int zzux() {
        int i;
        zzvf();
        synchronized (this.lock) {
            i = this.zzdsb;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzeb(String str) {
        zzvf();
        synchronized (this.lock) {
            long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
            this.zzdrx = jCurrentTimeMillis;
            if (str != null && !str.equals(this.zzdjf)) {
                this.zzdjf = str;
                if (this.zzdrt != null) {
                    this.zzdrt.putString("app_settings_json", str);
                    this.zzdrt.putLong("app_settings_last_update_ms", jCurrentTimeMillis);
                    this.zzdrt.apply();
                }
                Bundle bundle = new Bundle();
                bundle.putString("app_settings_json", str);
                bundle.putLong("app_settings_last_update_ms", jCurrentTimeMillis);
                zzc(bundle);
                Iterator<Runnable> it = this.zzdrq.iterator();
                while (it.hasNext()) {
                    it.next().run();
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final zzasy zzuy() {
        zzasy zzasyVar;
        zzvf();
        synchronized (this.lock) {
            zzasyVar = new zzasy(this.zzdjf, this.zzdrx);
        }
        return zzasyVar;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzc(Runnable runnable) {
        this.zzdrq.add(runnable);
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzet(long j) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdry == j) {
                return;
            }
            this.zzdry = j;
            if (this.zzdrt != null) {
                this.zzdrt.putLong("app_last_background_time_ms", j);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("app_last_background_time_ms", j);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final long zzuz() {
        long j;
        zzvf();
        synchronized (this.lock) {
            j = this.zzdry;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzco(int i) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdsa == i) {
                return;
            }
            this.zzdsa = i;
            if (this.zzdrt != null) {
                this.zzdrt.putInt("request_in_session_count", i);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putInt("request_in_session_count", i);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final int zzva() {
        int i;
        zzvf();
        synchronized (this.lock) {
            i = this.zzdsa;
        }
        return i;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzeu(long j) {
        zzvf();
        synchronized (this.lock) {
            if (this.zzdrz == j) {
                return;
            }
            this.zzdrz = j;
            if (this.zzdrt != null) {
                this.zzdrt.putLong("first_ad_req_time_ms", j);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putLong("first_ad_req_time_ms", j);
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final long zzvb() {
        long j;
        zzvf();
        synchronized (this.lock) {
            j = this.zzdrz;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzc(String str, String str2, boolean z) {
        zzvf();
        synchronized (this.lock) {
            JSONArray jSONArrayOptJSONArray = this.zzdsd.optJSONArray(str);
            if (jSONArrayOptJSONArray == null) {
                jSONArrayOptJSONArray = new JSONArray();
            }
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                if (jSONObjectOptJSONObject == null) {
                    return;
                }
                if (str2.equals(jSONObjectOptJSONObject.optString("template_id"))) {
                    if (!z || !jSONObjectOptJSONObject.optBoolean("uses_media_view", false)) {
                        length = i;
                        break;
                    }
                    return;
                }
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("template_id", str2);
                jSONObject.put("uses_media_view", z);
                jSONObject.put("timestamp_ms", com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis());
                jSONArrayOptJSONArray.put(length, jSONObject);
                this.zzdsd.put(str, jSONArrayOptJSONArray);
            } catch (JSONException e) {
                zzatm.zzd("Could not update native advanced settings", e);
            }
            if (this.zzdrt != null) {
                this.zzdrt.putString("native_advanced_settings", this.zzdsd.toString());
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", this.zzdsd.toString());
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final JSONObject zzvc() {
        JSONObject jSONObject;
        zzvf();
        synchronized (this.lock) {
            jSONObject = this.zzdsd;
        }
        return jSONObject;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzvd() {
        zzvf();
        synchronized (this.lock) {
            this.zzdsd = new JSONObject();
            if (this.zzdrt != null) {
                this.zzdrt.remove("native_advanced_settings");
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("native_advanced_settings", "{}");
            zzc(bundle);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final String zzve() {
        String str;
        zzvf();
        synchronized (this.lock) {
            str = this.zzdse;
        }
        return str;
    }

    @Override // com.google.android.gms.internal.ads.zzato
    public final void zzec(String str) {
        zzvf();
        synchronized (this.lock) {
            if (TextUtils.equals(this.zzdse, str)) {
                return;
            }
            this.zzdse = str;
            if (this.zzdrt != null) {
                this.zzdrt.putString("display_cutout", str);
                this.zzdrt.apply();
            }
            Bundle bundle = new Bundle();
            bundle.putString("display_cutout", str);
            zzc(bundle);
        }
    }

    final /* synthetic */ void zzp(Context context, String str) {
        boolean z = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(str, 0);
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        synchronized (this.lock) {
            this.zzcfs = sharedPreferences;
            this.zzdrt = editorEdit;
            if (PlatformVersion.isAtLeastM() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                z = true;
            }
            this.zzdru = z;
            this.zzdip = this.zzcfs.getBoolean("use_https", this.zzdip);
            this.zzdko = this.zzcfs.getBoolean("content_url_opted_out", this.zzdko);
            this.zzdrv = this.zzcfs.getString("content_url_hashes", this.zzdrv);
            this.zzdjc = this.zzcfs.getBoolean("auto_collect_location", this.zzdjc);
            this.zzdkz = this.zzcfs.getBoolean("content_vertical_opted_out", this.zzdkz);
            this.zzdrw = this.zzcfs.getString("content_vertical_hashes", this.zzdrw);
            this.zzdsb = this.zzcfs.getInt("version_code", this.zzdsb);
            this.zzdjf = this.zzcfs.getString("app_settings_json", this.zzdjf);
            this.zzdrx = this.zzcfs.getLong("app_settings_last_update_ms", this.zzdrx);
            this.zzdry = this.zzcfs.getLong("app_last_background_time_ms", this.zzdry);
            this.zzdsa = this.zzcfs.getInt("request_in_session_count", this.zzdsa);
            this.zzdrz = this.zzcfs.getLong("first_ad_req_time_ms", this.zzdrz);
            this.zzdsc = this.zzcfs.getStringSet("never_pool_slots", this.zzdsc);
            this.zzdse = this.zzcfs.getString("display_cutout", this.zzdse);
            this.zzdsf = this.zzcfs.getInt("app_measurement_npa", this.zzdsf);
            try {
                this.zzdsd = new JSONObject(this.zzcfs.getString("native_advanced_settings", "{}"));
            } catch (JSONException e) {
                zzatm.zzd("Could not convert native advanced settings to json object", e);
            }
            zzc(zzvg());
        }
    }
}
