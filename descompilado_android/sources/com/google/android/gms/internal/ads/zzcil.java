package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcil {
    private final Clock zzbmd;
    private final List<String> zzfyr = Collections.synchronizedList(new ArrayList());

    public zzcil(Clock clock) {
        this.zzbmd = clock;
    }

    public final <T> zzdcp<T> zza(zzcvb zzcvbVar, zzdcp<T> zzdcpVar) {
        long jElapsedRealtime = this.zzbmd.elapsedRealtime();
        String str = zzcvbVar.zzdbn;
        if (str != null) {
            zzdcf.zza(zzdcpVar, new zzcio(this, str, jElapsedRealtime), zzawx.zzdwb);
        }
        return zzdcpVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, int i, long j) {
        List<String> list = this.zzfyr;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 33);
        sb.append(str);
        sb.append(".");
        sb.append(i);
        sb.append(".");
        sb.append(j);
        list.add(sb.toString());
    }

    public final String zzaks() {
        return TextUtils.join(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR, this.zzfyr);
    }
}
