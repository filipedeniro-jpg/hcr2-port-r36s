package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzatb {
    private zzawv zzblh;
    private zzpe zzdqi;

    @GuardedBy("grantedPermissionLock")
    private zzdcp<ArrayList<String>> zzdqp;
    private Context zzlk;
    private final Object lock = new Object();
    private final zzatr zzdqj = new zzatr();
    private final zzatj zzdpp = new zzatj(zzuo.zzok(), this.zzdqj);
    private boolean zzye = false;

    @Nullable
    private zzyx zzdqk = null;

    @Nullable
    private Boolean zzdql = null;
    private final AtomicInteger zzdqm = new AtomicInteger(0);
    private final zzatc zzdqn = new zzatc(null);
    private final Object zzdqo = new Object();

    @Nullable
    public final zzyx zzud() {
        zzyx zzyxVar;
        synchronized (this.lock) {
            zzyxVar = this.zzdqk;
        }
        return zzyxVar;
    }

    public final void zza(Boolean bool) {
        synchronized (this.lock) {
            this.zzdql = bool;
        }
    }

    public final Boolean zzue() {
        Boolean bool;
        synchronized (this.lock) {
            bool = this.zzdql;
        }
        return bool;
    }

    public final void zzuf() {
        this.zzdqn.zzuf();
    }

    @TargetApi(23)
    public final void zzd(Context context, zzawv zzawvVar) {
        synchronized (this.lock) {
            if (!this.zzye) {
                this.zzlk = context.getApplicationContext();
                this.zzblh = zzawvVar;
                com.google.android.gms.ads.internal.zzp.zzkb().zza(this.zzdpp);
                zzyx zzyxVar = null;
                this.zzdqj.zza(this.zzlk, null, true);
                zzann.zzc(this.zzlk, this.zzblh);
                this.zzdqi = new zzpe(context.getApplicationContext(), this.zzblh);
                com.google.android.gms.ads.internal.zzp.zzkh();
                if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcie)).booleanValue()) {
                    zzatm.zzdy("CsiReporterFactory: CSI is not enabled. No CSI reporter created.");
                } else {
                    zzyxVar = new zzyx();
                }
                this.zzdqk = zzyxVar;
                if (this.zzdqk != null) {
                    zzaxb.zza(new zzatd(this).zzup(), "AppState.registerCsiReporter");
                }
                this.zzye = true;
                zzuk();
            }
        }
        com.google.android.gms.ads.internal.zzp.zzjy().zzr(context, zzawvVar.zzbnh);
    }

    @Nullable
    public final Resources getResources() {
        if (this.zzblh.zzdvu) {
            return this.zzlk.getResources();
        }
        try {
            zzawr.zzbo(this.zzlk).getResources();
            return null;
        } catch (zzawt e) {
            zzatm.zzd("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public final void zza(Throwable th, String str) {
        zzann.zzc(this.zzlk, this.zzblh).zza(th, str);
    }

    public final void zzb(Throwable th, String str) {
        zzann.zzc(this.zzlk, this.zzblh).zza(th, str, ((Float) zzuo.zzoj().zzd(zzyt.zzcgm)).floatValue());
    }

    public final void zzug() {
        this.zzdqm.incrementAndGet();
    }

    public final void zzuh() {
        this.zzdqm.decrementAndGet();
    }

    public final int zzui() {
        return this.zzdqm.get();
    }

    public final zzato zzuj() {
        zzatr zzatrVar;
        synchronized (this.lock) {
            zzatrVar = this.zzdqj;
        }
        return zzatrVar;
    }

    @Nullable
    public final Context getApplicationContext() {
        return this.zzlk;
    }

    public final zzdcp<ArrayList<String>> zzuk() {
        if (PlatformVersion.isAtLeastJellyBean() && this.zzlk != null) {
            if (!((Boolean) zzuo.zzoj().zzd(zzyt.zzcmx)).booleanValue()) {
                synchronized (this.zzdqo) {
                    if (this.zzdqp != null) {
                        return this.zzdqp;
                    }
                    zzdcp<ArrayList<String>> zzdcpVarSubmit = zzawx.zzdvx.submit(new Callable(this) { // from class: com.google.android.gms.internal.ads.zzata
                        private final zzatb zzdqh;

                        {
                            this.zzdqh = this;
                        }

                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            return this.zzdqh.zzum();
                        }
                    });
                    this.zzdqp = zzdcpVarSubmit;
                    return zzdcpVarSubmit;
                }
            }
        }
        return zzdcf.zzah(new ArrayList());
    }

    @TargetApi(16)
    private static ArrayList<String> zzak(Context context) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(context.getApplicationInfo().packageName, 4096);
            if (packageInfo.requestedPermissions != null && packageInfo.requestedPermissionsFlags != null) {
                for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                    if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                        arrayList.add(packageInfo.requestedPermissions[i]);
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }

    public final zzatj zzul() {
        return this.zzdpp;
    }

    final /* synthetic */ ArrayList zzum() throws Exception {
        return zzak(zzapf.zzz(this.zzlk));
    }
}
