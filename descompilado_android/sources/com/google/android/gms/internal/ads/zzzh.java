package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzzh {

    @Nullable
    private zzzh zzcuf;
    private final List<zzzf> zzcud = new LinkedList();
    private final Map<String, String> zzcue = new LinkedHashMap();
    private final Object lock = new Object();

    @VisibleForTesting
    boolean zzcuc = true;

    public zzzh(boolean z, String str, String str2) {
        this.zzcue.put("action", str);
        this.zzcue.put("ad_format", str2);
    }

    public final void zzc(@Nullable zzzh zzzhVar) {
        synchronized (this.lock) {
            this.zzcuf = zzzhVar;
        }
    }

    @Nullable
    public final zzzf zzer(long j) {
        if (this.zzcuc) {
            return new zzzf(j, null, null);
        }
        return null;
    }

    public final boolean zza(zzzf zzzfVar, long j, String... strArr) {
        synchronized (this.lock) {
            for (String str : strArr) {
                this.zzcud.add(new zzzf(j, str, zzzfVar));
            }
        }
        return true;
    }

    public final String zzpw() {
        String string;
        StringBuilder sb = new StringBuilder();
        synchronized (this.lock) {
            for (zzzf zzzfVar : this.zzcud) {
                long time = zzzfVar.getTime();
                String strZzpu = zzzfVar.zzpu();
                zzzf zzzfVarZzpv = zzzfVar.zzpv();
                if (zzzfVarZzpv != null && time > 0) {
                    long time2 = time - zzzfVarZzpv.getTime();
                    sb.append(strZzpu);
                    sb.append('.');
                    sb.append(time2);
                    sb.append(',');
                }
            }
            this.zzcud.clear();
            if (!TextUtils.isEmpty(null)) {
                sb.append((String) null);
            } else if (sb.length() > 0) {
                sb.setLength(sb.length() - 1);
            }
            string = sb.toString();
        }
        return string;
    }

    public final void zzj(String str, String str2) {
        zzyx zzyxVarZzud;
        if (!this.zzcuc || TextUtils.isEmpty(str2) || (zzyxVarZzud = com.google.android.gms.ads.internal.zzp.zzkc().zzud()) == null) {
            return;
        }
        synchronized (this.lock) {
            zzzb zzzbVarZzcl = zzyxVarZzud.zzcl(str);
            Map<String, String> map = this.zzcue;
            map.put(str, zzzbVarZzcl.zzi(map.get(str), str2));
        }
    }

    @VisibleForTesting
    final Map<String, String> zzpx() {
        synchronized (this.lock) {
            zzyx zzyxVarZzud = com.google.android.gms.ads.internal.zzp.zzkc().zzud();
            if (zzyxVarZzud != null && this.zzcuf != null) {
                return zzyxVarZzud.zza(this.zzcue, this.zzcuf.zzpx());
            }
            return this.zzcue;
        }
    }
}
