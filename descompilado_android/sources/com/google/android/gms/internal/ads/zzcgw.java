package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcgw {
    private final AtomicReference<zzajd> zzfxw = new AtomicReference<>();

    zzcgw() {
    }

    public final void zzb(zzajd zzajdVar) {
        this.zzfxw.compareAndSet(null, zzajdVar);
    }

    public final zzcvs zze(String str, JSONObject jSONObject) throws zzcvr {
        zzaji zzajiVarZzf;
        try {
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(str)) {
                zzajiVarZzf = new zzajz(new AdMobAdapter());
            } else if ("com.google.ads.mediation.AdUrlAdapter".equals(str)) {
                zzajiVarZzf = new zzajz(new AdUrlAdapter());
            } else if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(str)) {
                zzajiVarZzf = new zzajz(new zzalz());
            } else {
                zzajiVarZzf = zzf(str, jSONObject);
            }
            return new zzcvs(zzajiVarZzf);
        } catch (Throwable th) {
            throw new zzcvr(th);
        }
    }

    public final zzalj zzdb(String str) throws RemoteException {
        return zzakr().zzdb(str);
    }

    public final boolean zzakq() {
        return this.zzfxw.get() != null;
    }

    private final zzaji zzf(String str, JSONObject jSONObject) throws RemoteException {
        zzajd zzajdVarZzakr = zzakr();
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str) || "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            try {
                if (zzajdVarZzakr.zzda(jSONObject.getString("class_name"))) {
                    return zzajdVarZzakr.zzcz("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
                }
                return zzajdVarZzakr.zzcz("com.google.ads.mediation.customevent.CustomEventAdapter");
            } catch (JSONException e) {
                zzatm.zzc("Invalid custom event.", e);
            }
        }
        return zzajdVarZzakr.zzcz(str);
    }

    private final zzajd zzakr() throws RemoteException {
        zzajd zzajdVar = this.zzfxw.get();
        if (zzajdVar != null) {
            return zzajdVar;
        }
        zzatm.zzeu("Unexpected call to adapter creator.");
        throw new RemoteException();
    }
}
