package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.ConditionVariable;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.concurrent.Callable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzyp implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Context zzcft;
    private final Object lock = new Object();
    private final ConditionVariable zzcfq = new ConditionVariable();
    private volatile boolean zzye = false;

    @VisibleForTesting
    private volatile boolean zzcfr = false;

    @Nullable
    private SharedPreferences zzcfs = null;
    private Bundle metaData = new Bundle();
    private JSONObject zzcfu = new JSONObject();

    /* JADX WARN: Type inference failed for: r6v6, types: [com.google.android.gms.internal.ads.zzyu, com.google.android.gms.internal.ads.zzzy] */
    public final void initialize(Context context) {
        if (this.zzye) {
            return;
        }
        synchronized (this.lock) {
            if (this.zzye) {
                return;
            }
            if (!this.zzcfr) {
                this.zzcfr = true;
            }
            this.zzcft = context.getApplicationContext() == null ? context : context.getApplicationContext();
            try {
                this.metaData = Wrappers.packageManager(this.zzcft).getApplicationInfo(this.zzcft.getPackageName(), 128).metaData;
            } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            }
            try {
                Context remoteContext = GooglePlayServicesUtilLight.getRemoteContext(context);
                if (remoteContext == null && context != null && (remoteContext = context.getApplicationContext()) == null) {
                    remoteContext = context;
                }
                if (remoteContext == null) {
                    this.zzcfr = false;
                    this.zzcfq.open();
                    return;
                }
                zzuo.zzoh();
                this.zzcfs = remoteContext.getSharedPreferences("google_ads_flags", 0);
                if (this.zzcfs != null) {
                    this.zzcfs.registerOnSharedPreferenceChangeListener(this);
                }
                zzzx.zza(new zzyu(this));
                zzpp();
                this.zzye = true;
                this.zzcfr = false;
                this.zzcfq.open();
            } catch (Throwable th) {
                this.zzcfr = false;
                this.zzcfq.open();
                throw th;
            }
        }
    }

    public final <T> T zzd(final zzyi<T> zzyiVar) {
        if (!this.zzcfq.block(DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS)) {
            synchronized (this.lock) {
                if (!this.zzcfr) {
                    throw new IllegalStateException("Flags.initialize() was not called!");
                }
            }
        }
        if (!this.zzye || this.zzcfs == null) {
            synchronized (this.lock) {
                if (this.zzye && this.zzcfs != null) {
                }
                return zzyiVar.zzpm();
            }
        }
        if (zzyiVar.getSource() == 2) {
            Bundle bundle = this.metaData;
            if (bundle == null) {
                return zzyiVar.zzpm();
            }
            return zzyiVar.zza(bundle);
        }
        if (zzyiVar.getSource() == 1 && this.zzcfu.has(zzyiVar.getKey())) {
            return zzyiVar.zza(this.zzcfu);
        }
        return (T) zzavw.zza(this.zzcft, new Callable(this, zzyiVar) { // from class: com.google.android.gms.internal.ads.zzys
            private final zzyp zzcfv;
            private final zzyi zzcfw;

            {
                this.zzcfv = this;
                this.zzcfw = zzyiVar;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzcfv.zze(this.zzcfw);
            }
        });
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if ("flag_configuration".equals(str)) {
            zzpp();
        }
    }

    private final void zzpp() {
        if (this.zzcfs == null) {
            return;
        }
        try {
            this.zzcfu = new JSONObject((String) zzavw.zza(this.zzcft, new Callable(this) { // from class: com.google.android.gms.internal.ads.zzyr
                private final zzyp zzcfv;

                {
                    this.zzcfv = this;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return this.zzcfv.zzpq();
                }
            }));
        } catch (JSONException unused) {
        }
    }

    final /* synthetic */ String zzpq() throws Exception {
        return this.zzcfs.getString("flag_configuration", "{}");
    }

    final /* synthetic */ Object zze(zzyi zzyiVar) throws Exception {
        return zzyiVar.zza(this.zzcfs);
    }
}
