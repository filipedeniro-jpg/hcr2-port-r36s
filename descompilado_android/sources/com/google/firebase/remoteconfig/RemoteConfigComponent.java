package com.google.firebase.remoteconfig;

import android.content.Context;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.firebase_remote_config.zzab;
import com.google.android.gms.internal.firebase_remote_config.zzad;
import com.google.android.gms.internal.firebase_remote_config.zzas;
import com.google.android.gms.internal.firebase_remote_config.zzbf;
import com.google.android.gms.internal.firebase_remote_config.zzcx;
import com.google.android.gms.internal.firebase_remote_config.zzcy;
import com.google.android.gms.internal.firebase_remote_config.zzde;
import com.google.android.gms.internal.firebase_remote_config.zzei;
import com.google.android.gms.internal.firebase_remote_config.zzes;
import com.google.android.gms.internal.firebase_remote_config.zzev;
import com.google.android.gms.internal.firebase_remote_config.zzew;
import com.google.android.gms.internal.firebase_remote_config.zzex;
import com.google.android.gms.internal.firebase_remote_config.zzfd;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ironsource.sdk.precache.DownloadManager;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes2.dex */
@KeepForSdk
public class RemoteConfigComponent {
    private static final ExecutorService zzju = Executors.newCachedThreadPool();
    private static final Clock zzjv = DefaultClock.getInstance();
    private static final Random zzjw = new Random();
    private final String appId;

    @GuardedBy("this")
    private String zzg;
    private final Context zzja;
    private final FirebaseApp zzjb;
    private final FirebaseABTesting zzjc;

    @GuardedBy("this")
    private final Map<String, FirebaseRemoteConfig> zzjx;
    private final FirebaseInstanceId zzjy;

    @Nullable
    private final AnalyticsConnector zzjz;

    @GuardedBy("this")
    private Map<String, String> zzka;

    RemoteConfigComponent(Context context, FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, FirebaseABTesting firebaseABTesting, @Nullable AnalyticsConnector analyticsConnector) {
        this(context, zzju, firebaseApp, firebaseInstanceId, firebaseABTesting, analyticsConnector, new zzfd(context, firebaseApp.getOptions().getApplicationId()));
    }

    @VisibleForTesting
    private RemoteConfigComponent(Context context, Executor executor, FirebaseApp firebaseApp, FirebaseInstanceId firebaseInstanceId, FirebaseABTesting firebaseABTesting, @Nullable AnalyticsConnector analyticsConnector, zzfd zzfdVar) {
        this.zzjx = new HashMap();
        this.zzka = new HashMap();
        this.zzg = "https://firebaseremoteconfig.googleapis.com/";
        this.zzja = context;
        this.zzjb = firebaseApp;
        this.zzjy = firebaseInstanceId;
        this.zzjc = firebaseABTesting;
        this.zzjz = analyticsConnector;
        this.appId = firebaseApp.getOptions().getApplicationId();
        Tasks.call(executor, new Callable(this) { // from class: com.google.firebase.remoteconfig.zzo
            private final RemoteConfigComponent zzjt;

            {
                this.zzjt = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zzjt.get(FirebaseAuthProvider.PROVIDER_ID);
            }
        });
        zzfdVar.getClass();
        Tasks.call(executor, zzq.zza(zzfdVar));
    }

    @KeepForSdk
    public synchronized FirebaseRemoteConfig get(String str) {
        zzei zzeiVarZzd;
        zzei zzeiVarZzd2;
        zzei zzeiVarZzd3;
        zzev zzevVar;
        zzeiVarZzd = zzd(str, "fetch");
        zzeiVarZzd2 = zzd(str, "activate");
        zzeiVarZzd3 = zzd(str, "defaults");
        zzevVar = new zzev(this.zzja.getSharedPreferences(String.format("%s_%s_%s_%s", "frc", this.appId, str, DownloadManager.SETTINGS), 0));
        return zza(this.zzjb, str, this.zzjc, zzju, zzeiVarZzd, zzeiVarZzd2, zzeiVarZzd3, new zzes(this.zzja, this.zzjb.getOptions().getApplicationId(), this.zzjy, this.zzjz, str, zzju, zzjv, zzjw, zzeiVarZzd, zza(this.zzjb.getOptions().getApiKey(), zzevVar), zzevVar), new zzew(zzeiVarZzd2, zzeiVarZzd3), zzevVar);
    }

    @VisibleForTesting
    private final synchronized FirebaseRemoteConfig zza(FirebaseApp firebaseApp, String str, FirebaseABTesting firebaseABTesting, Executor executor, zzei zzeiVar, zzei zzeiVar2, zzei zzeiVar3, zzes zzesVar, zzew zzewVar, zzev zzevVar) {
        if (!this.zzjx.containsKey(str)) {
            FirebaseRemoteConfig firebaseRemoteConfig = new FirebaseRemoteConfig(this.zzja, firebaseApp, str.equals(FirebaseAuthProvider.PROVIDER_ID) ? firebaseABTesting : null, executor, zzeiVar, zzeiVar2, zzeiVar3, zzesVar, zzewVar, zzevVar);
            firebaseRemoteConfig.zzcm();
            this.zzjx.put(str, firebaseRemoteConfig);
        }
        return this.zzjx.get(str);
    }

    private final zzei zzd(String str, String str2) {
        return zza(this.zzja, this.appId, str, str2);
    }

    public static zzei zza(Context context, String str, String str2, String str3) {
        return zzei.zza(zzju, zzex.zzb(context, String.format("%s_%s_%s_%s.json", "frc", str, str2, str3)));
    }

    @VisibleForTesting
    private final zzcy zza(String str, final zzev zzevVar) {
        zzcy zzcyVarZzce;
        zzde zzdeVar = new zzde(str);
        synchronized (this) {
            zzcyVarZzce = ((zzcx) new zzcx(new zzas(), zzbf.zzbq(), new zzad(this, zzevVar) { // from class: com.google.firebase.remoteconfig.zzp
                private final RemoteConfigComponent zzjt;
                private final zzev zzkb;

                {
                    this.zzjt = this;
                    this.zzkb = zzevVar;
                }

                @Override // com.google.android.gms.internal.firebase_remote_config.zzad
                public final void zza(zzab zzabVar) throws IOException {
                    this.zzjt.zza(this.zzkb, zzabVar);
                }
            }).zzc(this.zzg)).zza(zzdeVar).zzce();
        }
        return zzcyVarZzce;
    }

    final /* synthetic */ void zza(zzev zzevVar, zzab zzabVar) throws IOException {
        zzabVar.zza((int) TimeUnit.SECONDS.toMillis(zzevVar.getFetchTimeoutInSeconds()));
        zzabVar.zzb((int) TimeUnit.SECONDS.toMillis(5L));
        synchronized (this) {
            for (Map.Entry<String, String> entry : this.zzka.entrySet()) {
                zzabVar.zzx().zzb(entry.getKey(), entry.getValue());
            }
        }
    }
}
