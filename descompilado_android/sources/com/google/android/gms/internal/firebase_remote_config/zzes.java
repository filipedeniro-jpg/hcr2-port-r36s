package com.google.android.gms.internal.firebase_remote_config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.format.DateUtils;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigClientException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigFetchThrottledException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigServerException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;

/* JADX INFO: loaded from: classes2.dex */
public final class zzes {
    public static final long zzla = TimeUnit.HOURS.toSeconds(12);

    @VisibleForTesting
    private static final int[] zzlb = {2, 4, 8, 16, 32, 64, 128, 256};
    private static final Pattern zzlg = Pattern.compile("^[^:]+:([0-9]+):(android|ios|web):([0-9a-f]+)");
    private final String appId;
    private final Executor executor;
    private final String namespace;
    private final Context zzja;
    private final zzei zzjd;
    private final zzev zzji;
    private final FirebaseInstanceId zzjy;

    @Nullable
    private final AnalyticsConnector zzjz;
    private final Clock zzlc;
    private final Random zzld;
    private final zzcy zzle;
    private final String zzlf;

    public zzes(Context context, String str, FirebaseInstanceId firebaseInstanceId, @Nullable AnalyticsConnector analyticsConnector, String str2, Executor executor, Clock clock, Random random, zzei zzeiVar, zzcy zzcyVar, zzev zzevVar) {
        this.zzja = context;
        this.appId = str;
        this.zzjy = firebaseInstanceId;
        this.zzjz = analyticsConnector;
        this.namespace = str2;
        this.executor = executor;
        this.zzlc = clock;
        this.zzld = random;
        this.zzjd = zzeiVar;
        this.zzle = zzcyVar;
        this.zzji = zzevVar;
        Matcher matcher = zzlg.matcher(str);
        this.zzlf = matcher.matches() ? matcher.group(1) : null;
    }

    public final Task<zzet> zza(boolean z) {
        return zza(z, this.zzji.getMinimumFetchIntervalInSeconds());
    }

    public final Task<zzet> zza(final boolean z, final long j) {
        return this.zzjd.zzcp().continueWithTask(this.executor, new Continuation(this, z, j) { // from class: com.google.android.gms.internal.firebase_remote_config.zzer
            private final boolean zzkm;
            private final zzes zzky;
            private final long zzkz;

            {
                this.zzky = this;
                this.zzkm = z;
                this.zzkz = j;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public final Object then(Task task) {
                return this.zzky.zza(this.zzkm, this.zzkz, task);
            }
        });
    }

    private final Task<zzet> zzb(Date date) {
        try {
            zzdf zzdfVarZzc = zzc(date);
            if (!(zzdfVarZzc.getState() == null || !zzdfVarZzc.getState().equals("NO_CHANGE"))) {
                return Tasks.forResult(zzet.zzd(date));
            }
            return this.zzjd.zzc(zza(zzdfVarZzc, date)).onSuccessTask(this.executor, zzeu.zzjk);
        } catch (FirebaseRemoteConfigException e) {
            return Tasks.forException(e);
        }
    }

    @WorkerThread
    private final zzdf zzc(Date date) throws FirebaseRemoteConfigException {
        String str;
        try {
            zzdc zzdcVarZza = new zzcz(new zzda(this.zzle)).zza(this.zzlf, this.namespace, zzcw());
            zzw zzwVarZzg = zzdcVarZza.zzg();
            zzwVarZzg.zzr(this.zzji.zzcz());
            zzwVarZzg.zzb("X-Android-Package", this.zzja.getPackageName());
            zzwVarZzg.zzb("X-Android-Cert", zza(this.zzja, this.zzja.getPackageName()));
            zzdf zzdfVarZzi = zzdcVarZza.zzi();
            this.zzji.zzbd(zzdcVarZza.zzh().zzq());
            this.zzji.zza(0, zzev.zzlj);
            return zzdfVarZzi;
        } catch (zzaf e) {
            Log.e("FirebaseRemoteConfig", "Fetch failed! Server responded with an error.", e);
            int statusCode = e.getStatusCode();
            if (statusCode == 429 || statusCode == 503 || statusCode == 504) {
                int iZzdd = this.zzji.zzda().zzdd() + 1;
                TimeUnit timeUnit = TimeUnit.MINUTES;
                int[] iArr = zzlb;
                long millis = timeUnit.toMillis(iArr[Math.min(iZzdd, iArr.length) - 1]);
                this.zzji.zza(iZzdd, new Date(date.getTime() + (millis / 2) + ((long) this.zzld.nextInt((int) millis))));
            }
            int statusCode2 = e.getStatusCode();
            if (statusCode2 == 401) {
                str = "The request did not have the required credentials. Please make sure your google-services.json is valid.";
            } else if (statusCode2 == 403) {
                str = "The user is not authorized to access the project. Please make sure you are using the API key that corresponds to your Firebase project.";
            } else if (statusCode2 == 429) {
                str = "You have reached the throttle limit for your project. Please wait before making more requests.";
            } else if (statusCode2 != 500) {
                str = (statusCode2 == 503 || statusCode2 == 504) ? "The server is unavailable. Please try again later." : "The server returned an unexpected error.";
            } else {
                str = "There was an internal server error.";
            }
            throw new FirebaseRemoteConfigServerException(statusCode2, String.format("Fetch failed: %s", str));
        } catch (IOException e2) {
            throw new FirebaseRemoteConfigClientException("Fetch failed due to an unexpected error! Check logs for details.", e2);
        }
    }

    private static String zza(Context context, String str) {
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, str);
            if (packageCertificateHashBytes == null) {
                String strValueOf = String.valueOf(str);
                Log.e("FirebaseRemoteConfig", strValueOf.length() != 0 ? "Could not get fingerprint hash for package: ".concat(strValueOf) : new String("Could not get fingerprint hash for package: "));
                return null;
            }
            return Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException e) {
            String strValueOf2 = String.valueOf(str);
            Log.e("FirebaseRemoteConfig", strValueOf2.length() != 0 ? "No such package: ".concat(strValueOf2) : new String("No such package: "), e);
            return null;
        }
    }

    private static zzen zza(zzdf zzdfVar, Date date) throws FirebaseRemoteConfigClientException {
        try {
            zzep zzepVarZza = zzen.zzct().zza(date);
            Map<String, String> entries = zzdfVar.getEntries();
            if (entries != null) {
                zzepVarZza.zzd(entries);
            }
            List<zzdd> listZzcf = zzdfVar.zzcf();
            if (listZzcf != null) {
                zzepVarZza.zzb(listZzcf);
            }
            return zzepVarZza.zzcv();
        } catch (JSONException e) {
            throw new FirebaseRemoteConfigClientException("Fetch failed: fetch response could not be parsed.", e);
        }
    }

    @VisibleForTesting
    @WorkerThread
    private final zzdg zzcw() throws FirebaseRemoteConfigClientException {
        String id = this.zzjy.getId();
        if (id == null) {
            throw new FirebaseRemoteConfigClientException("Fetch request could not be created: Firebase instance id is null.");
        }
        String token = this.zzjy.getToken();
        zzdg zzdgVar = new zzdg();
        zzdgVar.zzas(id);
        if (token != null) {
            zzdgVar.zzat(token);
        }
        zzdgVar.zzar(this.appId);
        Locale locale = this.zzja.getResources().getConfiguration().locale;
        zzdgVar.zzav(locale.getCountry());
        zzdgVar.zzaw(locale.toString());
        zzdgVar.zzay(Integer.toString(Build.VERSION.SDK_INT));
        zzdgVar.zzba(TimeZone.getDefault().getID());
        try {
            PackageInfo packageInfo = this.zzja.getPackageManager().getPackageInfo(this.zzja.getPackageName(), 0);
            if (packageInfo != null) {
                zzdgVar.zzau(packageInfo.versionName);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        zzdgVar.zzax(this.zzja.getPackageName());
        zzdgVar.zzaz(BuildConfig.VERSION_NAME);
        HashMap map = new HashMap();
        AnalyticsConnector analyticsConnector = this.zzjz;
        if (analyticsConnector != null) {
            for (Map.Entry<String, Object> entry : analyticsConnector.getUserProperties(false).entrySet()) {
                map.put(entry.getKey(), entry.getValue().toString());
            }
        }
        zzdgVar.zza(map);
        return zzdgVar;
    }

    /* JADX WARN: Code duplicated, block: B:5:0x0014  */
    final /* synthetic */ Task zza(boolean z, long j, Task task) throws Exception {
        boolean zBefore;
        Date date = new Date(this.zzlc.currentTimeMillis());
        if (task.isSuccessful()) {
            if (z) {
                zBefore = false;
            } else {
                Date dateZzcy = this.zzji.zzcy();
                if (dateZzcy.equals(zzev.zzli)) {
                    zBefore = false;
                } else {
                    zBefore = date.before(new Date(dateZzcy.getTime() + TimeUnit.SECONDS.toMillis(j)));
                }
            }
            if (zBefore) {
                return Tasks.forResult(zzet.zze(date));
            }
        }
        Date dateZzde = this.zzji.zzda().zzde();
        if (!date.before(dateZzde)) {
            dateZzde = null;
        }
        if (dateZzde != null) {
            return Tasks.forException(new FirebaseRemoteConfigFetchThrottledException(String.format("Fetch is throttled. Please wait before calling fetch again: %s", DateUtils.formatElapsedTime(TimeUnit.MILLISECONDS.toSeconds(dateZzde.getTime() - date.getTime()))), dateZzde.getTime()));
        }
        return zzb(date);
    }
}
