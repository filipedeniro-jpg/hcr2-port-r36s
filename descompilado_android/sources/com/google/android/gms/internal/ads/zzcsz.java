package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import androidx.annotation.Nullable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcsz {
    private final zzaok zzfuo;

    public zzcsz(zzaok zzaokVar) {
        this.zzfuo = zzaokVar;
    }

    public final String zzamh() {
        return this.zzfuo.packageName;
    }

    public final String zzami() {
        return this.zzfuo.zzdlo.getString("ms");
    }

    @Nullable
    public final PackageInfo zzamj() {
        return this.zzfuo.zzdij;
    }

    public final boolean zzamk() {
        return this.zzfuo.zzdlp;
    }

    public final List<String> zzaml() {
        return this.zzfuo.zzdit;
    }

    public final ApplicationInfo zzamm() {
        return this.zzfuo.applicationInfo;
    }

    public final String zzamn() {
        return this.zzfuo.zzdlq;
    }
}
