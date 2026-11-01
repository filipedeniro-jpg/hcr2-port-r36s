package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.ironsource.mediationsdk.utils.GeneralPropertiesWorker;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzyx {
    private File file;

    @VisibleForTesting
    private String zzbnh;

    @VisibleForTesting
    private String zzcts;
    private AtomicBoolean zzctu;

    @VisibleForTesting
    private Context zzlk;

    @VisibleForTesting
    private BlockingQueue<zzzh> zzctp = new ArrayBlockingQueue(100);

    @VisibleForTesting
    private LinkedHashMap<String, String> zzctq = new LinkedHashMap<>();

    @VisibleForTesting
    private Map<String, zzzb> zzctr = new HashMap();
    private final HashSet<String> zzctt = new HashSet<>(Arrays.asList("noop", "activeViewPingSent", "viewabilityChanged", "visibilityChanged"));

    public final void zza(Context context, String str, String str2, Map<String, String> map) {
        File externalStorageDirectory;
        this.zzlk = context;
        this.zzbnh = str;
        this.zzcts = str2;
        this.zzctu = new AtomicBoolean(false);
        this.zzctu.set(((Boolean) zzuo.zzoj().zzd(zzyt.zzcig)).booleanValue());
        if (this.zzctu.get() && (externalStorageDirectory = Environment.getExternalStorageDirectory()) != null) {
            this.file = new File(externalStorageDirectory, "sdk_csi_data.txt");
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            this.zzctq.put(entry.getKey(), entry.getValue());
        }
        zzawx.zzdvx.execute(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzza
            private final zzyx zzctw;

            {
                this.zzctw = this;
            }

            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.zzctw.zzpr();
            }
        });
        this.zzctr.put("action", zzzb.zzcty);
        this.zzctr.put("ad_format", zzzb.zzcty);
        this.zzctr.put("e", zzzb.zzctz);
    }

    public final boolean zza(zzzh zzzhVar) {
        return this.zzctp.offer(zzzhVar);
    }

    private final void zza(Map<String, String> map, String str) throws Throwable {
        Uri.Builder builderBuildUpon = Uri.parse(this.zzcts).buildUpon();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builderBuildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        StringBuilder sb = new StringBuilder(builderBuildUpon.build().toString());
        if (!TextUtils.isEmpty(str)) {
            sb.append("&it=");
            sb.append(str);
        }
        String string = sb.toString();
        if (this.zzctu.get()) {
            File file = this.file;
            if (file != null) {
                FileOutputStream fileOutputStream = null;
                try {
                    try {
                        FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
                        try {
                            fileOutputStream2.write(string.getBytes());
                            fileOutputStream2.write(10);
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e) {
                                zzatm.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e);
                            }
                        } catch (IOException e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            zzatm.zzd("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e);
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    zzatm.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e3);
                                }
                            }
                        } catch (Throwable th) {
                            th = th;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                    zzatm.zzd("CsiReporter: Cannot close file: sdk_csi_data.txt.", e4);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                zzatm.zzeu("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
            }
        } else {
            com.google.android.gms.ads.internal.zzp.zzjy();
            zzatv.zzb(this.zzlk, this.zzbnh, string);
        }
    }

    final Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            linkedHashMap.put(key, zzcl(key).zzi((String) linkedHashMap.get(key), value));
        }
        return linkedHashMap;
    }

    public final zzzb zzcl(String str) {
        zzzb zzzbVar = this.zzctr.get(str);
        return zzzbVar != null ? zzzbVar : zzzb.zzctx;
    }

    public final void zzcm(String str) throws Throwable {
        if (this.zzctt.contains(str)) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(GeneralPropertiesWorker.SDK_VERSION, this.zzbnh);
        linkedHashMap.put("ue", str);
        zza(zza(this.zzctq, linkedHashMap), "");
    }

    final /* synthetic */ void zzpr() throws Throwable {
        while (true) {
            try {
                zzzh zzzhVarTake = this.zzctp.take();
                String strZzpw = zzzhVarTake.zzpw();
                if (!TextUtils.isEmpty(strZzpw)) {
                    zza(zza(this.zzctq, zzzhVarTake.zzpx()), strZzpw);
                }
            } catch (InterruptedException e) {
                zzatm.zzd("CsiReporter:reporter interrupted", e);
                return;
            }
        }
    }
}
