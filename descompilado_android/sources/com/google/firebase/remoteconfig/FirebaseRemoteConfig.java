package com.google.firebase.remoteconfig;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.annotation.XmlRes;
import com.google.android.gms.internal.firebase_remote_config.zzag;
import com.google.android.gms.internal.firebase_remote_config.zzei;
import com.google.android.gms.internal.firebase_remote_config.zzen;
import com.google.android.gms.internal.firebase_remote_config.zzes;
import com.google.android.gms.internal.firebase_remote_config.zzet;
import com.google.android.gms.internal.firebase_remote_config.zzev;
import com.google.android.gms.internal.firebase_remote_config.zzew;
import com.google.android.gms.internal.firebase_remote_config.zzfa;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.AbtException;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.auth.FirebaseAuthProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FirebaseRemoteConfig {
    public static final boolean DEFAULT_VALUE_FOR_BOOLEAN = false;
    public static final byte[] DEFAULT_VALUE_FOR_BYTE_ARRAY = new byte[0];
    public static final double DEFAULT_VALUE_FOR_DOUBLE = 0.0d;
    public static final long DEFAULT_VALUE_FOR_LONG = 0;
    public static final String DEFAULT_VALUE_FOR_STRING = "";
    public static final int LAST_FETCH_STATUS_FAILURE = 1;
    public static final int LAST_FETCH_STATUS_NO_FETCH_YET = 0;
    public static final int LAST_FETCH_STATUS_SUCCESS = -1;
    public static final int LAST_FETCH_STATUS_THROTTLED = 2;
    public static final int VALUE_SOURCE_DEFAULT = 1;
    public static final int VALUE_SOURCE_REMOTE = 2;
    public static final int VALUE_SOURCE_STATIC = 0;
    private final Executor executor;
    private final Context zzja;
    private final FirebaseApp zzjb;

    @Nullable
    private final FirebaseABTesting zzjc;
    private final zzei zzjd;
    private final zzei zzje;
    private final zzei zzjf;
    private final zzes zzjg;
    private final zzew zzjh;
    private final zzev zzji;

    public static FirebaseRemoteConfig getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    final /* synthetic */ boolean zzc(Task task) {
        return zza((Task<zzen>) task);
    }

    public static FirebaseRemoteConfig getInstance(FirebaseApp firebaseApp) {
        return ((RemoteConfigComponent) firebaseApp.get(RemoteConfigComponent.class)).get(FirebaseAuthProvider.PROVIDER_ID);
    }

    FirebaseRemoteConfig(Context context, FirebaseApp firebaseApp, @Nullable FirebaseABTesting firebaseABTesting, Executor executor, zzei zzeiVar, zzei zzeiVar2, zzei zzeiVar3, zzes zzesVar, zzew zzewVar, zzev zzevVar) {
        this.zzja = context;
        this.zzjb = firebaseApp;
        this.zzjc = firebaseABTesting;
        this.executor = executor;
        this.zzjd = zzeiVar;
        this.zzje = zzeiVar2;
        this.zzjf = zzeiVar3;
        this.zzjg = zzesVar;
        this.zzjh = zzewVar;
        this.zzji = zzevVar;
    }

    public Task<FirebaseRemoteConfigInfo> ensureInitialized() {
        Task<zzen> taskZzcp = this.zzje.zzcp();
        Task<zzen> taskZzcp2 = this.zzjf.zzcp();
        Task<zzen> taskZzcp3 = this.zzjd.zzcp();
        final Task taskCall = Tasks.call(this.executor, new Callable(this) { // from class: com.google.firebase.remoteconfig.zza
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zziz.getInfo();
            }
        });
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{taskZzcp, taskZzcp2, taskZzcp3, taskCall}).continueWith(this.executor, new Continuation(taskCall) { // from class: com.google.firebase.remoteconfig.zzc
            private final Task zzjj;

            {
                this.zzjj = taskCall;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return (FirebaseRemoteConfigInfo) this.zzjj.getResult();
            }
        });
    }

    public Task<Boolean> fetchAndActivate() {
        return fetch().onSuccessTask(this.executor, new SuccessContinuation(this) { // from class: com.google.firebase.remoteconfig.zzg
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public final Task then(Object obj) {
                return this.zziz.activate();
            }
        });
    }

    @WorkerThread
    @Deprecated
    public boolean activateFetched() {
        zzen zzenVarZzco = this.zzjd.zzco();
        if (zzenVarZzco == null || !zza(zzenVarZzco, this.zzje.zzco())) {
            return false;
        }
        this.zzje.zzb(zzenVarZzco).addOnSuccessListener(this.executor, new OnSuccessListener(this) { // from class: com.google.firebase.remoteconfig.zzf
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                this.zziz.zza((zzen) obj);
            }
        });
        return true;
    }

    public Task<Boolean> activate() {
        final Task<zzen> taskZzcp = this.zzjd.zzcp();
        final Task<zzen> taskZzcp2 = this.zzje.zzcp();
        return Tasks.whenAllComplete((Task<?>[]) new Task[]{taskZzcp, taskZzcp2}).continueWithTask(this.executor, new Continuation(this, taskZzcp, taskZzcp2) { // from class: com.google.firebase.remoteconfig.zzi
            private final FirebaseRemoteConfig zziz;
            private final Task zzjl;
            private final Task zzjm;

            {
                this.zziz = this;
                this.zzjl = taskZzcp;
                this.zzjm = taskZzcp2;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zziz.zza(this.zzjl, this.zzjm, task);
            }
        });
    }

    public Task<Void> fetch() {
        Task<zzet> taskZza = this.zzjg.zza(this.zzji.isDeveloperModeEnabled());
        taskZza.addOnCompleteListener(this.executor, new OnCompleteListener(this) { // from class: com.google.firebase.remoteconfig.zzh
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zziz.zzb(task);
            }
        });
        return taskZza.onSuccessTask(zzk.zzjk);
    }

    public Task<Void> fetch(long j) {
        Task<zzet> taskZza = this.zzjg.zza(this.zzji.isDeveloperModeEnabled(), j);
        taskZza.addOnCompleteListener(this.executor, new OnCompleteListener(this) { // from class: com.google.firebase.remoteconfig.zzj
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public final void onComplete(Task task) {
                this.zziz.zzb(task);
            }
        });
        return taskZza.onSuccessTask(zzm.zzjk);
    }

    public String getString(String str) {
        return this.zzjh.getString(str);
    }

    public boolean getBoolean(String str) {
        return this.zzjh.getBoolean(str);
    }

    @Deprecated
    public byte[] getByteArray(String str) {
        return this.zzjh.getByteArray(str);
    }

    public double getDouble(String str) {
        return this.zzjh.getDouble(str);
    }

    public long getLong(String str) {
        return this.zzjh.getLong(str);
    }

    public FirebaseRemoteConfigValue getValue(String str) {
        return this.zzjh.getValue(str);
    }

    public Set<String> getKeysByPrefix(String str) {
        return this.zzjh.getKeysByPrefix(str);
    }

    public Map<String, FirebaseRemoteConfigValue> getAll() {
        return this.zzjh.getAll();
    }

    public FirebaseRemoteConfigInfo getInfo() {
        return this.zzji.getInfo();
    }

    @Deprecated
    public void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        this.zzji.zzb(firebaseRemoteConfigSettings);
        if (firebaseRemoteConfigSettings.isDeveloperModeEnabled()) {
            Logger.getLogger(zzag.class.getName()).setLevel(Level.CONFIG);
        }
    }

    public Task<Void> setConfigSettingsAsync(final FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        return Tasks.call(this.executor, new Callable(this, firebaseRemoteConfigSettings) { // from class: com.google.firebase.remoteconfig.zzl
            private final FirebaseRemoteConfig zziz;
            private final FirebaseRemoteConfigSettings zzjn;

            {
                this.zziz = this;
                this.zzjn = firebaseRemoteConfigSettings;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zziz.zza(this.zzjn);
            }
        });
    }

    public void setDefaults(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue().toString());
        }
        zzb(map2);
    }

    public Task<Void> setDefaultsAsync(Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue().toString());
        }
        return zzc(map2);
    }

    public void setDefaults(@XmlRes int i) {
        zzb(zzfa.zza(this.zzja, i));
    }

    public Task<Void> setDefaultsAsync(@XmlRes int i) {
        return zzc(zzfa.zza(this.zzja, i));
    }

    public Task<Void> reset() {
        return Tasks.call(this.executor, new Callable(this) { // from class: com.google.firebase.remoteconfig.zzb
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // java.util.concurrent.Callable
            public final Object call() {
                return this.zziz.zzcn();
            }
        });
    }

    final void zzcm() {
        this.zzje.zzcp();
        this.zzjf.zzcp();
        this.zzjd.zzcp();
    }

    private final boolean zza(Task<zzen> task) {
        if (!task.isSuccessful()) {
            return false;
        }
        this.zzjd.clear();
        if (task.getResult() != null) {
            zza(task.getResult().zzcs());
            return true;
        }
        Log.e("FirebaseRemoteConfig", "Activated configs are null.");
        return true;
    }

    private final void zzb(Map<String, String> map) {
        try {
            this.zzjf.zzb(zzen.zzct().zzd(map).zzcv());
        } catch (JSONException e) {
            Log.e("FirebaseRemoteConfig", "The provided defaults map could not be processed.", e);
        }
    }

    private final Task<Void> zzc(Map<String, String> map) {
        try {
            return this.zzjf.zza(zzen.zzct().zzd(map).zzcv(), true).onSuccessTask(zze.zzjk);
        } catch (JSONException e) {
            Log.e("FirebaseRemoteConfig", "The provided defaults map could not be processed.", e);
            return Tasks.forResult(null);
        }
    }

    @VisibleForTesting
    private final void zza(@NonNull JSONArray jSONArray) {
        if (this.zzjc == null) {
            return;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                HashMap map = new HashMap();
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject.getString(next));
                }
                arrayList.add(map);
            }
            this.zzjc.replaceAllExperiments(arrayList);
        } catch (AbtException e) {
            Log.w("FirebaseRemoteConfig", "Could not update ABT experiments.", e);
        } catch (JSONException e2) {
            Log.e("FirebaseRemoteConfig", "Could not parse ABT experiments from the JSON response.", e2);
        }
    }

    private static boolean zza(zzen zzenVar, @Nullable zzen zzenVar2) {
        return zzenVar2 == null || !zzenVar.zzcr().equals(zzenVar2.zzcr());
    }

    final /* synthetic */ Void zzcn() throws Exception {
        this.zzje.clear();
        this.zzjd.clear();
        this.zzjf.clear();
        this.zzji.clear();
        return null;
    }

    final /* synthetic */ Void zza(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) throws Exception {
        this.zzji.setConfigSettings(firebaseRemoteConfigSettings);
        if (!firebaseRemoteConfigSettings.isDeveloperModeEnabled()) {
            return null;
        }
        Logger.getLogger(zzag.class.getName()).setLevel(Level.CONFIG);
        return null;
    }

    final /* synthetic */ Task zza(Task task, Task task2, Task task3) throws Exception {
        if (!task.isSuccessful() || task.getResult() == null) {
            return Tasks.forResult(false);
        }
        zzen zzenVar = (zzen) task.getResult();
        if (task2.isSuccessful() && !zza(zzenVar, (zzen) task2.getResult())) {
            return Tasks.forResult(false);
        }
        return this.zzje.zza(zzenVar, true).continueWith(this.executor, new Continuation(this) { // from class: com.google.firebase.remoteconfig.zzd
            private final FirebaseRemoteConfig zziz;

            {
                this.zziz = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task4) {
                return Boolean.valueOf(this.zziz.zzc(task4));
            }
        });
    }

    final /* synthetic */ void zza(zzen zzenVar) {
        this.zzjd.clear();
        zza(zzenVar.zzcs());
    }

    final /* synthetic */ void zzb(Task task) {
        if (task.isSuccessful()) {
            this.zzji.zzm(-1);
            zzen zzenVarZzcx = ((zzet) task.getResult()).zzcx();
            if (zzenVarZzcx != null) {
                this.zzji.zzf(zzenVarZzcx.zzcr());
            }
            Log.i("FirebaseRemoteConfig", "Fetch succeeded!");
            return;
        }
        Exception exception = task.getException();
        if (exception == null) {
            Log.e("FirebaseRemoteConfig", "Fetch was cancelled... This should never covfefe");
        } else if (exception instanceof FirebaseRemoteConfigFetchThrottledException) {
            this.zzji.zzm(2);
            Log.w("FirebaseRemoteConfig", "Fetch was throttled!", exception);
        } else {
            this.zzji.zzm(1);
            Log.e("FirebaseRemoteConfig", "Fetch failed!", exception);
        }
    }
}
