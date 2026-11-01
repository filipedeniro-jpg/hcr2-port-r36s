package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationExtrasReceiver;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzwr {
    private String zzabl;
    private String zzcbr;
    private String zzcbt;
    private String zzcbx;
    private boolean zzcbz;
    private Date zznc;
    private Location zzng;
    private final HashSet<String> zzcdt = new HashSet<>();
    private final Bundle zzcdu = new Bundle();
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzcdv = new HashMap<>();
    private final HashSet<String> zzcdw = new HashSet<>();
    private final Bundle zzcbv = new Bundle();
    private final HashSet<String> zzcdx = new HashSet<>();
    private int zzcbo = -1;
    private boolean zzbke = false;
    private int zzabj = -1;
    private int zzabk = -1;

    public final void zzca(String str) {
        this.zzcdt.add(str);
    }

    @Deprecated
    public final void zza(NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            zza(AdMobAdapter.class, ((AdMobExtras) networkExtras).getExtras());
        } else {
            this.zzcdv.put((Class<? extends NetworkExtras>) networkExtras.getClass(), networkExtras);
        }
    }

    public final void zza(Class<? extends MediationExtrasReceiver> cls, Bundle bundle) {
        this.zzcdu.putBundle(cls.getName(), bundle);
    }

    public final void zzb(Class<? extends CustomEvent> cls, Bundle bundle) {
        if (this.zzcdu.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzcdu.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzcdu.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(cls.getName(), bundle);
    }

    public final void zzcb(String str) {
        this.zzcdw.add(str);
    }

    public final void zzcc(String str) {
        this.zzcdw.remove(str);
    }

    @Deprecated
    public final void zza(Date date) {
        this.zznc = date;
    }

    public final void zzcd(String str) {
        this.zzcbt = str;
    }

    @Deprecated
    public final void zzch(int i) {
        this.zzcbo = i;
    }

    public final void zza(Location location) {
        this.zzng = location;
    }

    public final void setManualImpressionsEnabled(boolean z) {
        this.zzbke = z;
    }

    public final void zzce(String str) {
        this.zzcbr = str;
    }

    public final void zzcf(String str) {
        this.zzcbx = str;
    }

    @Deprecated
    public final void zzs(boolean z) {
        this.zzabj = z ? 1 : 0;
    }

    public final void zzg(String str, String str2) {
        this.zzcbv.putString(str, str2);
    }

    public final void zzcg(String str) {
        this.zzcdx.add(str);
    }

    @Deprecated
    public final void zzt(boolean z) {
        this.zzcbz = z;
    }

    @Deprecated
    public final void zzci(int i) {
        if (i == -1 || i == 0 || i == 1) {
            this.zzabk = i;
        }
    }

    @Deprecated
    public final void zzch(String str) {
        if ("G".equals(str) || "PG".equals(str) || "T".equals(str) || "MA".equals(str)) {
            this.zzabl = str;
        }
    }
}
