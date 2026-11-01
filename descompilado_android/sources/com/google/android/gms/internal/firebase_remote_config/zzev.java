package com.google.android.gms.internal.firebase_remote_config;

import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public final class zzev {

    @VisibleForTesting
    static final Date zzli = new Date(-1);

    @VisibleForTesting
    static final Date zzlj = new Date(-1);
    private final SharedPreferences zzlk;
    private final Object zzll = new Object();
    private final Object zzlm = new Object();

    public zzev(SharedPreferences sharedPreferences) {
        this.zzlk = sharedPreferences;
    }

    public final boolean isDeveloperModeEnabled() {
        return this.zzlk.getBoolean("is_developer_mode_enabled", false);
    }

    public final long getFetchTimeoutInSeconds() {
        return this.zzlk.getLong("fetch_timeout_in_seconds", 5L);
    }

    public final long getMinimumFetchIntervalInSeconds() {
        return this.zzlk.getLong("minimum_fetch_interval_in_seconds", zzes.zzla);
    }

    final Date zzcy() {
        return new Date(this.zzlk.getLong("last_fetch_time_in_millis", -1L));
    }

    @Nullable
    final String zzcz() {
        return this.zzlk.getString("last_fetch_etag", null);
    }

    public final void zzm(int i) {
        synchronized (this.zzll) {
            this.zzlk.edit().putInt("last_fetch_status", i).apply();
        }
    }

    public final void zzf(Date date) {
        synchronized (this.zzll) {
            this.zzlk.edit().putLong("last_fetch_time_in_millis", date.getTime()).apply();
        }
    }

    public final FirebaseRemoteConfigInfo getInfo() {
        zzez zzezVarZzdf;
        synchronized (this.zzll) {
            long j = this.zzlk.getLong("last_fetch_time_in_millis", -1L);
            int i = this.zzlk.getInt("last_fetch_status", 0);
            zzezVarZzdf = new zzfb().zzn(i).zzc(j).zzc(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(this.zzlk.getBoolean("is_developer_mode_enabled", false)).setFetchTimeoutInSeconds(this.zzlk.getLong("fetch_timeout_in_seconds", 5L)).setMinimumFetchIntervalInSeconds(this.zzlk.getLong("minimum_fetch_interval_in_seconds", zzes.zzla)).build()).zzdf();
        }
        return zzezVarZzdf;
    }

    @WorkerThread
    public final void clear() {
        synchronized (this.zzll) {
            this.zzlk.edit().clear().commit();
        }
    }

    @WorkerThread
    public final void setConfigSettings(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.zzll) {
            this.zzlk.edit().putBoolean("is_developer_mode_enabled", firebaseRemoteConfigSettings.isDeveloperModeEnabled()).putLong("fetch_timeout_in_seconds", firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong("minimum_fetch_interval_in_seconds", firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).commit();
        }
    }

    public final void zzb(FirebaseRemoteConfigSettings firebaseRemoteConfigSettings) {
        synchronized (this.zzll) {
            this.zzlk.edit().putBoolean("is_developer_mode_enabled", firebaseRemoteConfigSettings.isDeveloperModeEnabled()).putLong("fetch_timeout_in_seconds", firebaseRemoteConfigSettings.getFetchTimeoutInSeconds()).putLong("minimum_fetch_interval_in_seconds", firebaseRemoteConfigSettings.getMinimumFetchIntervalInSeconds()).apply();
        }
    }

    final void zzbd(String str) {
        synchronized (this.zzll) {
            this.zzlk.edit().putString("last_fetch_etag", str).apply();
        }
    }

    final zzey zzda() {
        zzey zzeyVar;
        synchronized (this.zzlm) {
            zzeyVar = new zzey(this.zzlk.getInt("num_failed_fetches", 0), new Date(this.zzlk.getLong("backoff_end_time_in_millis", -1L)));
        }
        return zzeyVar;
    }

    final void zza(int i, Date date) {
        synchronized (this.zzlm) {
            this.zzlk.edit().putInt("num_failed_fetches", i).putLong("backoff_end_time_in_millis", date.getTime()).apply();
        }
    }
}
