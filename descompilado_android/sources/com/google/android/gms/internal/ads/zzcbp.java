package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcbp {
    private final Executor executor;
    private final zzawv zzbkw;
    private final Context zzcft;
    private final Executor zzfbm;
    private final ScheduledExecutorService zzfck;
    private final WeakReference<Context> zzfsi;
    private final zzcgw zzfsj;
    private final zzcaz zzfsk;
    private boolean zzfse = false;

    @GuardedBy("this")
    private boolean zzfsf = false;
    private final zzaxf<Boolean> zzfsh = new zzaxf<>();
    private Map<String, zzaex> zzfsl = new ConcurrentHashMap();
    private final long zzfsg = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();

    public zzcbp(Executor executor, Context context, WeakReference<Context> weakReference, Executor executor2, zzcgw zzcgwVar, ScheduledExecutorService scheduledExecutorService, zzcaz zzcazVar, zzawv zzawvVar) {
        this.zzfsj = zzcgwVar;
        this.zzcft = context;
        this.zzfsi = weakReference;
        this.executor = executor2;
        this.zzfck = scheduledExecutorService;
        this.zzfbm = executor;
        this.zzfsk = zzcazVar;
        this.zzbkw = zzawvVar;
        zza("com.google.android.gms.ads.MobileAds", false, "", 0);
    }

    public final void zzb(final zzafe zzafeVar) {
        this.zzfsh.addListener(new Runnable(this, zzafeVar) { // from class: com.google.android.gms.internal.ads.zzcbs
            private final zzcbp zzfsm;
            private final zzafe zzfsn;

            {
                this.zzfsm = this;
                this.zzfsn = zzafeVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfsm.zzc(this.zzfsn);
            }
        }, this.zzfbm);
    }

    public final void zzajw() {
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmi)).booleanValue()) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcmk)).booleanValue()) {
                if (this.zzbkw.zzdvt >= ((Integer) zzuo.zzoj().zzd(zzyt.zzcmj)).intValue()) {
                    if (this.zzfse) {
                        return;
                    }
                    synchronized (this) {
                        if (this.zzfse) {
                            return;
                        }
                        this.zzfsk.zzajt();
                        this.zzfsh.addListener(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcbr
                            private final zzcbp zzfsm;

                            {
                                this.zzfsm = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzfsm.zzakb();
                            }
                        }, this.executor);
                        this.zzfse = true;
                        zzdcp<String> zzdcpVarZzajy = zzajy();
                        this.zzfck.schedule(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzcbt
                            private final zzcbp zzfsm;

                            {
                                this.zzfsm = this;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzfsm.zzaka();
                            }
                        }, ((Long) zzuo.zzoj().zzd(zzyt.zzcmm)).longValue(), TimeUnit.SECONDS);
                        zzdcf.zza(zzdcpVarZzajy, new zzcca(this), this.executor);
                        return;
                    }
                }
            }
        }
        zza("com.google.android.gms.ads.MobileAds", true, "", 0);
        this.zzfsh.set(false);
    }

    public final List<zzaex> zzajx() {
        ArrayList arrayList = new ArrayList();
        for (String str : this.zzfsl.keySet()) {
            zzaex zzaexVar = this.zzfsl.get(str);
            arrayList.add(new zzaex(str, zzaexVar.zzcyc, zzaexVar.zzcyd, zzaexVar.description));
        }
        return arrayList;
    }

    private final synchronized zzdcp<String> zzajy() {
        String strZztw = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuy().zztw();
        if (!TextUtils.isEmpty(strZztw)) {
            return zzdcf.zzah(strZztw);
        }
        final zzaxf zzaxfVar = new zzaxf();
        com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzc(new Runnable(this, zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzcbu
            private final zzcbp zzfsm;
            private final zzaxf zzfso;

            {
                this.zzfsm = this;
                this.zzfso = zzaxfVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzfsm.zza(this.zzfso);
            }
        });
        return zzaxfVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzga(String str) {
        try {
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject = new JSONObject(str).getJSONObject("initializer_settings").getJSONObject("config");
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                final String next = itKeys.next();
                final Object obj = new Object();
                final zzaxf zzaxfVar = new zzaxf();
                zzdcp zzdcpVarZza = zzdcf.zza(zzaxfVar, ((Long) zzuo.zzoj().zzd(zzyt.zzcml)).longValue(), TimeUnit.SECONDS, this.zzfck);
                this.zzfsk.zzfy(next);
                final long jElapsedRealtime = com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime();
                Iterator<String> it = itKeys;
                zzdcpVarZza.addListener(new Runnable(this, obj, zzaxfVar, next, jElapsedRealtime) { // from class: com.google.android.gms.internal.ads.zzcbw
                    private final Object zzdbe;
                    private final String zzfqp;
                    private final zzcbp zzfsm;
                    private final zzaxf zzfsp;
                    private final long zzfsq;

                    {
                        this.zzfsm = this;
                        this.zzdbe = obj;
                        this.zzfsp = zzaxfVar;
                        this.zzfqp = next;
                        this.zzfsq = jElapsedRealtime;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzfsm.zza(this.zzdbe, this.zzfsp, this.zzfqp, this.zzfsq);
                    }
                }, this.executor);
                arrayList.add(zzdcpVarZza);
                final zzccc zzcccVar = new zzccc(this, obj, next, jElapsedRealtime, zzaxfVar);
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
                final ArrayList arrayList2 = new ArrayList();
                if (jSONObjectOptJSONObject != null) {
                    try {
                        JSONArray jSONArray = jSONObjectOptJSONObject.getJSONArray("data");
                        for (int i = 0; i < jSONArray.length(); i++) {
                            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                            String strOptString = jSONObject2.optString("format", "");
                            JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("data");
                            Bundle bundle = new Bundle();
                            if (jSONObjectOptJSONObject2 != null) {
                                Iterator<String> itKeys2 = jSONObjectOptJSONObject2.keys();
                                while (itKeys2.hasNext()) {
                                    String next2 = itKeys2.next();
                                    bundle.putString(next2, jSONObjectOptJSONObject2.optString(next2, ""));
                                }
                            }
                            arrayList2.add(new zzafh(strOptString, bundle));
                        }
                    } catch (JSONException unused) {
                    }
                }
                zza(next, false, "", 0);
                try {
                    try {
                        final zzcvs zzcvsVarZze = this.zzfsj.zze(next, new JSONObject());
                        this.zzfbm.execute(new Runnable(this, zzcvsVarZze, zzcccVar, arrayList2, next) { // from class: com.google.android.gms.internal.ads.zzcby
                            private final String zzeev;
                            private final zzcbp zzfsm;
                            private final zzcvs zzfsr;
                            private final zzaez zzfss;
                            private final List zzfst;

                            {
                                this.zzfsm = this;
                                this.zzfsr = zzcvsVarZze;
                                this.zzfss = zzcccVar;
                                this.zzfst = arrayList2;
                                this.zzeev = next;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzfsm.zza(this.zzfsr, this.zzfss, this.zzfst, this.zzeev);
                            }
                        });
                    } catch (RemoteException e) {
                        zzawo.zzc("", e);
                    }
                } catch (zzcvr unused2) {
                    zzcccVar.onInitializationFailed("Failed to create Adapter.");
                }
                itKeys = it;
            }
            zzdcf.zzh(arrayList).zza(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzcbv
                private final zzcbp zzfsm;

                {
                    this.zzfsm = this;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zzfsm.zzajz();
                }
            }, this.executor);
        } catch (JSONException e2) {
            zzatm.zza("Malformed CLD response", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, boolean z, String str2, int i) {
        this.zzfsl.put(str, new zzaex(str, z, i, str2));
    }

    final /* synthetic */ void zza(zzcvs zzcvsVar, zzaez zzaezVar, List list, String str) {
        try {
            try {
                Context context = this.zzfsi.get();
                if (context == null) {
                    context = this.zzcft;
                }
                zzcvsVar.zza(context, zzaezVar, (List<zzafh>) list);
            } catch (zzcvr unused) {
                StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 74);
                sb.append("Failed to initialize adapter. ");
                sb.append(str);
                sb.append(" does not implement the initialize() method.");
                zzaezVar.onInitializationFailed(sb.toString());
            }
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    final /* synthetic */ Object zzajz() throws Exception {
        this.zzfsh.set(true);
        return null;
    }

    final /* synthetic */ void zza(Object obj, zzaxf zzaxfVar, String str, long j) {
        synchronized (obj) {
            if (!zzaxfVar.isDone()) {
                zza(str, false, "Timeout.", (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - j));
                this.zzfsk.zzr(str, "timeout");
                zzaxfVar.set(false);
            }
        }
    }

    final /* synthetic */ void zzaka() {
        synchronized (this) {
            if (this.zzfsf) {
                return;
            }
            zza("com.google.android.gms.ads.MobileAds", false, "Timeout.", (int) (com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime() - this.zzfsg));
            this.zzfsh.setException(new Exception());
        }
    }

    final /* synthetic */ void zza(final zzaxf zzaxfVar) {
        this.executor.execute(new Runnable(this, zzaxfVar) { // from class: com.google.android.gms.internal.ads.zzcbx
            private final zzcbp zzfsm;
            private final zzaxf zzfso;

            {
                this.zzfsm = this;
                this.zzfso = zzaxfVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                zzaxf zzaxfVar2 = this.zzfso;
                String strZztw = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuy().zztw();
                if (TextUtils.isEmpty(strZztw)) {
                    zzaxfVar2.setException(new Exception());
                } else {
                    zzaxfVar2.set(strZztw);
                }
            }
        });
    }

    final /* synthetic */ void zzakb() {
        this.zzfsk.zzaju();
    }

    final /* synthetic */ void zzc(zzafe zzafeVar) {
        try {
            zzafeVar.zzc(zzajx());
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    static /* synthetic */ boolean zza(zzcbp zzcbpVar, boolean z) {
        zzcbpVar.zzfsf = true;
        return true;
    }
}
