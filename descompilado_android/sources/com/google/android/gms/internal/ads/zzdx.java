package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.util.Log;
import android.util.Pair;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdx {
    private static final String TAG = "zzdx";
    protected Context zzlk;
    private ExecutorService zzxr;
    private DexClassLoader zzxs;
    private zzdh zzxt;
    private byte[] zzxu;
    private boolean zzxx;
    private zzda zzya;
    private Map<Pair<String, String>, zzfj> zzyd;
    private volatile AdvertisingIdClient zzxv = null;
    private volatile boolean zzwo = false;
    private Future zzxw = null;
    private volatile zzbo.zza zzxy = null;
    private Future zzxz = null;
    protected boolean zzyb = false;
    private boolean zzyc = false;
    private boolean zzye = false;
    private boolean zzyf = true;
    private boolean zzyg = false;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    final class zza extends BroadcastReceiver {
        private zza() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                zzdx.this.zzyf = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                zzdx.this.zzyf = false;
            }
        }

        /* synthetic */ zza(zzdx zzdxVar, zzea zzeaVar) {
            this();
        }
    }

    public static zzdx zza(Context context, String str, String str2, boolean z) throws Throwable {
        zzdx zzdxVar = new zzdx(context);
        try {
            zzdxVar.zzxr = Executors.newCachedThreadPool(new zzea());
            zzdxVar.zzwo = z;
            if (z) {
                zzdxVar.zzxw = zzdxVar.zzxr.submit(new zzdz(zzdxVar));
            }
            zzdxVar.zzxr.execute(new zzeb(zzdxVar));
            try {
                GoogleApiAvailabilityLight googleApiAvailabilityLight = GoogleApiAvailabilityLight.getInstance();
                zzdxVar.zzyb = googleApiAvailabilityLight.getApkVersion(zzdxVar.zzlk) > 0;
                zzdxVar.zzyc = googleApiAvailabilityLight.isGooglePlayServicesAvailable(zzdxVar.zzlk) == 0;
            } catch (Throwable unused) {
            }
            zzdxVar.zza(0, true);
            if (zzee.isMainThread() && ((Boolean) zzuo.zzoj().zzd(zzyt.zzcnc)).booleanValue()) {
                throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
            }
            zzea zzeaVar = null;
            zzdxVar.zzxt = new zzdh(null);
            try {
                zzdxVar.zzxu = zzdxVar.zzxt.zzaq(str);
                try {
                    try {
                        try {
                            File cacheDir = zzdxVar.zzlk.getCacheDir();
                            if (cacheDir == null && (cacheDir = zzdxVar.zzlk.getDir("dex", 0)) == null) {
                                throw new zzdw();
                            }
                            File file = new File(String.format("%s/%s.jar", cacheDir, "1557357152169"));
                            if (!file.exists()) {
                                byte[] bArrZza = zzdxVar.zzxt.zza(zzdxVar.zzxu, str2);
                                file.createNewFile();
                                FileOutputStream fileOutputStream = new FileOutputStream(file);
                                fileOutputStream.write(bArrZza, 0, bArrZza.length);
                                fileOutputStream.close();
                            }
                            zzdxVar.zzb(cacheDir, "1557357152169");
                            try {
                                zzdxVar.zzxs = new DexClassLoader(file.getAbsolutePath(), cacheDir.getAbsolutePath(), null, zzdxVar.zzlk.getClassLoader());
                                zzb(file);
                                zzdxVar.zza(cacheDir, "1557357152169");
                                zzar(String.format("%s/%s.dex", cacheDir, "1557357152169"));
                                if (!zzdxVar.zzyg) {
                                    IntentFilter intentFilter = new IntentFilter();
                                    intentFilter.addAction("android.intent.action.USER_PRESENT");
                                    intentFilter.addAction("android.intent.action.SCREEN_OFF");
                                    zzdxVar.zzlk.registerReceiver(new zza(zzdxVar, zzeaVar), intentFilter);
                                    zzdxVar.zzyg = true;
                                }
                                zzdxVar.zzya = new zzda(zzdxVar);
                                zzdxVar.zzye = true;
                                return zzdxVar;
                            } catch (Throwable th) {
                                zzb(file);
                                zzdxVar.zza(cacheDir, "1557357152169");
                                zzar(String.format("%s/%s.dex", cacheDir, "1557357152169"));
                                throw th;
                            }
                        } catch (IOException e) {
                            throw new zzdw(e);
                        }
                    } catch (NullPointerException e2) {
                        throw new zzdw(e2);
                    }
                } catch (zzdk e3) {
                    throw new zzdw(e3);
                } catch (FileNotFoundException e4) {
                    throw new zzdw(e4);
                }
            } catch (zzdk e5) {
                throw new zzdw(e5);
            }
        } catch (zzdw unused2) {
        }
    }

    public final Context getContext() {
        return this.zzlk;
    }

    public final boolean isInitialized() {
        return this.zzye;
    }

    public final ExecutorService zzcd() {
        return this.zzxr;
    }

    public final DexClassLoader zzce() {
        return this.zzxs;
    }

    public final zzdh zzcf() {
        return this.zzxt;
    }

    public final byte[] zzcg() {
        return this.zzxu;
    }

    public final boolean zzch() {
        return this.zzyb;
    }

    public final zzda zzci() {
        return this.zzya;
    }

    public final boolean zzcj() {
        return this.zzyc;
    }

    public final boolean zzck() {
        return this.zzyf;
    }

    public final zzbo.zza zzcl() {
        return this.zzxy;
    }

    public final Future zzcm() {
        return this.zzxz;
    }

    private zzdx(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.zzxx = applicationContext != null;
        this.zzlk = this.zzxx ? applicationContext : context;
        this.zzyd = new HashMap();
    }

    private final void zza(File file, String str) throws Throwable {
        FileInputStream fileInputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (file2.exists()) {
            return;
        }
        File file3 = new File(String.format("%s/%s.dex", file, str));
        if (file3.exists()) {
            long length = file3.length();
            if (length <= 0) {
                return;
            }
            byte[] bArr = new byte[(int) length];
            FileOutputStream fileOutputStream = null;
            try {
                fileInputStream = new FileInputStream(file3);
                try {
                    try {
                        if (fileInputStream.read(bArr) <= 0) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused) {
                            }
                            zzb(file3);
                            return;
                        }
                        System.out.print("test");
                        System.out.print("test");
                        System.out.print("test");
                        zzbo.zzd.zza zzaVarZzk = zzbo.zzd.zzbb().zzl(zzdot.zzy(Build.VERSION.SDK.getBytes())).zzk(zzdot.zzy(str.getBytes()));
                        byte[] bytes = this.zzxt.zzb(this.zzxu, bArr).getBytes();
                        zzaVarZzk.zzi(zzdot.zzy(bytes)).zzj(zzdot.zzy(zzci.zzb(bytes)));
                        file2.createNewFile();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                        try {
                            byte[] byteArray = ((zzbo.zzd) ((zzdqd) zzaVarZzk.zzazm())).toByteArray();
                            fileOutputStream2.write(byteArray, 0, byteArray.length);
                            fileOutputStream2.close();
                            try {
                                fileInputStream.close();
                            } catch (IOException unused2) {
                            }
                            try {
                                fileOutputStream2.close();
                            } catch (IOException unused3) {
                            }
                            zzb(file3);
                            return;
                        } catch (zzdk | IOException | NoSuchAlgorithmException unused4) {
                            fileOutputStream = fileOutputStream2;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            zzb(file3);
                            throw th;
                        }
                    } catch (zzdk | IOException | NoSuchAlgorithmException unused7) {
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (zzdk | IOException | NoSuchAlgorithmException unused8) {
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused9) {
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException unused10) {
                }
            }
            zzb(file3);
        }
    }

    private static void zzar(String str) {
        zzb(new File(str));
    }

    private static void zzb(File file) {
        if (!file.exists()) {
            Log.d(TAG, String.format("File %s not found. No need for deletion", file.getAbsolutePath()));
        } else {
            file.delete();
        }
    }

    private final boolean zzb(File file, String str) throws Throwable {
        FileInputStream fileInputStream;
        File file2 = new File(String.format("%s/%s.tmp", file, str));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", file, str));
        if (file3.exists()) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            long length = file2.length();
            if (length <= 0) {
                zzb(file2);
                return false;
            }
            byte[] bArr = new byte[(int) length];
            fileInputStream = new FileInputStream(file2);
            try {
                try {
                    if (fileInputStream.read(bArr) <= 0) {
                        Log.d(TAG, "Cannot read the cache data.");
                        zzb(file2);
                        try {
                            fileInputStream.close();
                        } catch (IOException unused) {
                        }
                        return false;
                    }
                    zzbo.zzd zzdVarZzc = zzbo.zzd.zzc(bArr, zzdpq.zzayw());
                    if (str.equals(new String(zzdVarZzc.zzaz().toByteArray())) && Arrays.equals(zzdVarZzc.zzay().toByteArray(), zzci.zzb(zzdVarZzc.zzax().toByteArray())) && Arrays.equals(zzdVarZzc.zzba().toByteArray(), Build.VERSION.SDK.getBytes())) {
                        byte[] bArrZza = this.zzxt.zza(this.zzxu, new String(zzdVarZzc.zzax().toByteArray()));
                        file3.createNewFile();
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file3);
                        try {
                            fileOutputStream2.write(bArrZza, 0, bArrZza.length);
                            try {
                                fileInputStream.close();
                            } catch (IOException unused2) {
                            }
                            try {
                                fileOutputStream2.close();
                            } catch (IOException unused3) {
                            }
                            return true;
                        } catch (zzdk | IOException | NoSuchAlgorithmException unused4) {
                            fileOutputStream = fileOutputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused5) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused6) {
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException unused7) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                    throw th;
                                } catch (IOException unused8) {
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    }
                    zzb(file2);
                    try {
                        fileInputStream.close();
                    } catch (IOException unused9) {
                    }
                    return false;
                } catch (zzdk | IOException | NoSuchAlgorithmException unused10) {
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (zzdk | IOException | NoSuchAlgorithmException unused11) {
            fileInputStream = null;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzyd.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzyd.put(new Pair<>(str, str2), new zzfj(this, str, str2, clsArr));
        return true;
    }

    public final Method zzc(String str, String str2) {
        zzfj zzfjVar = this.zzyd.get(new Pair(str, str2));
        if (zzfjVar == null) {
            return null;
        }
        return zzfjVar.zzcy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzcn() {
        try {
            if (this.zzxv == null && this.zzxx) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzlk);
                advertisingIdClient.start();
                this.zzxv = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException unused) {
            this.zzxv = null;
        }
    }

    @VisibleForTesting
    final void zza(int i, boolean z) {
        if (this.zzyc) {
            Future<?> futureSubmit = this.zzxr.submit(new zzec(this, i, z));
            if (i == 0) {
                this.zzxz = futureSubmit;
            }
        }
    }

    @VisibleForTesting
    final zzbo.zza zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep(i * 1000);
            } catch (InterruptedException unused) {
            }
        }
        return zzco();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zza(int i, zzbo.zza zzaVar) {
        if (i < 4) {
            return zzaVar == null || !zzaVar.zzah() || zzaVar.zzad().equals("0000000000000000000000000000000000000000000000000000000000000000") || !zzaVar.zzaj() || !zzaVar.zzak().zzbd() || zzaVar.zzak().zzbe() == -2;
        }
        return false;
    }

    @VisibleForTesting
    private final zzbo.zza zzco() {
        try {
            return zzcyk.zzj(this.zzlk, this.zzlk.getPackageName(), Integer.toString(this.zzlk.getPackageManager().getPackageInfo(this.zzlk.getPackageName(), 0).versionCode));
        } catch (Throwable unused) {
            return null;
        }
    }

    public final AdvertisingIdClient zzcp() {
        if (!this.zzwo) {
            return null;
        }
        if (this.zzxv != null) {
            return this.zzxv;
        }
        Future future = this.zzxw;
        if (future != null) {
            try {
                future.get(2000L, TimeUnit.MILLISECONDS);
                this.zzxw = null;
            } catch (InterruptedException | ExecutionException unused) {
            } catch (TimeoutException unused2) {
                this.zzxw.cancel(true);
            }
        }
        return this.zzxv;
    }

    public final int zzbz() {
        if (this.zzya != null) {
            return zzda.zzbz();
        }
        return Integer.MIN_VALUE;
    }
}
