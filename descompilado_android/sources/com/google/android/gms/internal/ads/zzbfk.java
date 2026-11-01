package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbfk extends zzvt {
    private final zzawv zzblh;
    private final zzarv zzbmt;
    private final zzcgw zzezw;
    private final zzcfo<zzcvs, zzcgt> zzezx;
    private final zzckx zzezy;
    private final zzcbp zzezz;
    private final Context zzlk;

    @GuardedBy("this")
    private boolean zzye = false;

    zzbfk(Context context, zzawv zzawvVar, zzcgw zzcgwVar, zzcfo<zzcvs, zzcgt> zzcfoVar, zzckx zzckxVar, zzcbp zzcbpVar, zzarv zzarvVar) {
        this.zzlk = context;
        this.zzblh = zzawvVar;
        this.zzezw = zzcgwVar;
        this.zzezx = zzcfoVar;
        this.zzezy = zzckxVar;
        this.zzezz = zzcbpVar;
        this.zzbmt = zzarvVar;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized void initialize() {
        if (this.zzye) {
            zzatm.zzeu("Mobile ads is initialized already.");
            return;
        }
        zzyt.initialize(this.zzlk);
        com.google.android.gms.ads.internal.zzp.zzkc().zzd(this.zzlk, this.zzblh);
        com.google.android.gms.ads.internal.zzp.zzke().initialize(this.zzlk);
        this.zzye = true;
        this.zzezz.zzajw();
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcmd)).booleanValue()) {
            this.zzezy.zzaku();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized void setAppVolume(float f) {
        com.google.android.gms.ads.internal.zzp.zzkd().setAppVolume(f);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized float zzoo() {
        return com.google.android.gms.ads.internal.zzp.zzkd().zzoo();
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized void setAppMuted(boolean z) {
        com.google.android.gms.ads.internal.zzp.zzkd().setAppMuted(z);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized boolean zzop() {
        return com.google.android.gms.ads.internal.zzp.zzkd().zzop();
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final synchronized void zzby(String str) {
        zzyt.initialize(this.zzlk);
        if (!TextUtils.isEmpty(str)) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcoo)).booleanValue()) {
                com.google.android.gms.ads.internal.zzp.zzkg().zza(this.zzlk, this.zzblh, str, (Runnable) null);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzc(IObjectWrapper iObjectWrapper, String str) {
        if (iObjectWrapper == null) {
            zzatm.zzes("Wrapped context is null. Failed to open debug menu.");
            return;
        }
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        if (context == null) {
            zzatm.zzes("Context is null. Failed to open debug menu.");
            return;
        }
        zzaun zzaunVar = new zzaun(context);
        zzaunVar.setAdUnitId(str);
        zzaunVar.zzr(this.zzblh.zzbnh);
        zzaunVar.showDialog();
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzb(@Nullable String str, IObjectWrapper iObjectWrapper) {
        zzyt.initialize(this.zzlk);
        String strZzadq = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcop)).booleanValue() ? zzadq() : "";
        if (!TextUtils.isEmpty(strZzadq)) {
            str = strZzadq;
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        boolean zBooleanValue = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcoo)).booleanValue() | ((Boolean) zzuo.zzoj().zzd(zzyt.zzckd)).booleanValue();
        Runnable runnable = null;
        if (((Boolean) zzuo.zzoj().zzd(zzyt.zzckd)).booleanValue()) {
            zBooleanValue = true;
            final Runnable runnable2 = (Runnable) ObjectWrapper.unwrap(iObjectWrapper);
            runnable = new Runnable(this, runnable2) { // from class: com.google.android.gms.internal.ads.zzbfj
                private final zzbfk zzezu;
                private final Runnable zzezv;

                {
                    this.zzezu = this;
                    this.zzezv = runnable2;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    final zzbfk zzbfkVar = this.zzezu;
                    final Runnable runnable3 = this.zzezv;
                    zzawx.zzdwa.execute(new Runnable(zzbfkVar, runnable3) { // from class: com.google.android.gms.internal.ads.zzbfm
                        private final zzbfk zzezu;
                        private final Runnable zzezv;

                        {
                            this.zzezu = zzbfkVar;
                            this.zzezv = runnable3;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzezu.zze(this.zzezv);
                        }
                    });
                }
            };
        }
        if (zBooleanValue) {
            com.google.android.gms.ads.internal.zzp.zzkg().zza(this.zzlk, this.zzblh, str, runnable);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final String getVersionString() {
        return this.zzblh.zzbnh;
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zzbz(String str) {
        this.zzezy.zzgd(str);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzajd zzajdVar) throws RemoteException {
        this.zzezw.zzb(zzajdVar);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzafe zzafeVar) throws RemoteException {
        this.zzezz.zzb(zzafeVar);
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final List<zzaex> zzoq() throws RemoteException {
        return this.zzezz.zzajx();
    }

    private final String zzadq() {
        Context applicationContext = this.zzlk.getApplicationContext() == null ? this.zzlk : this.zzlk.getApplicationContext();
        try {
            String string = Wrappers.packageManager(applicationContext).getApplicationInfo(applicationContext.getPackageName(), 128).metaData.getString("com.google.android.gms.ads.APPLICATION_ID");
            if (TextUtils.isEmpty(string)) {
                return "";
            }
            return (string.matches("^ca-app-pub-[0-9]{16}~[0-9]{10}$") || string.matches("^/\\d+~.+$")) ? string : "";
        } catch (PackageManager.NameNotFoundException | NullPointerException e) {
            zzatm.zza("Error getting metadata", e);
            return "";
        }
    }

    @Override // com.google.android.gms.internal.ads.zzvu
    public final void zza(zzxw zzxwVar) throws RemoteException {
        this.zzbmt.zza(this.zzlk, zzxwVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    final /* synthetic */ void zze(Runnable runnable) {
        Preconditions.checkMainThread("Adapters must be initialized on the main thread.");
        Map<String, zzajc> mapZzty = com.google.android.gms.ads.internal.zzp.zzkc().zzuj().zzuy().zzty();
        if (mapZzty == null || mapZzty.isEmpty()) {
            return;
        }
        if (runnable != null) {
            try {
                runnable.run();
            } catch (Throwable th) {
                zzatm.zzd("Could not initialize rewarded ads.", th);
                return;
            }
        }
        if (this.zzezw.zzakq()) {
            HashMap map = new HashMap();
            Iterator<zzajc> it = mapZzty.values().iterator();
            while (it.hasNext()) {
                for (zzaiz zzaizVar : it.next().zzdch) {
                    String str = zzaizVar.zzdbu;
                    for (String str2 : zzaizVar.zzdbm) {
                        if (!map.containsKey(str2)) {
                            map.put(str2, new ArrayList());
                        }
                        if (str != null) {
                            ((Collection) map.get(str2)).add(str);
                        }
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry entry : map.entrySet()) {
                String str3 = (String) entry.getKey();
                try {
                    zzcfl<zzcvs, ListenerT> zzcflVarZzd = this.zzezx.zzd(str3, jSONObject);
                    if (zzcflVarZzd != 0) {
                        zzcvs zzcvsVar = (zzcvs) zzcflVarZzd.zzdde;
                        if (!zzcvsVar.isInitialized() && zzcvsVar.zzrp()) {
                            zzcvsVar.zza(this.zzlk, (zzcgt) zzcflVarZzd.zzfwt, (List<String>) entry.getValue());
                            String strValueOf = String.valueOf(str3);
                            zzatm.zzdv(strValueOf.length() != 0 ? "Initialized rewarded video mediation adapter ".concat(strValueOf) : new String("Initialized rewarded video mediation adapter "));
                        }
                    }
                } catch (zzcvr e) {
                    StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + 56);
                    sb.append("Failed to initialize rewarded video mediation adapter \"");
                    sb.append(str3);
                    sb.append("\"");
                    zzatm.zzd(sb.toString(), e);
                }
            }
        }
    }
}
