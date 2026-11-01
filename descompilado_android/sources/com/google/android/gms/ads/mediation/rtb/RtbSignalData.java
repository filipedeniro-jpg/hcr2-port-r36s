package com.google.android.gms.ads.mediation.rtb;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.mediation.MediationConfiguration;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public class RtbSignalData {
    private final Bundle zzcbu;

    @Nullable
    private final AdSize zzdg;
    private final List<MediationConfiguration> zzejf;
    private final Context zzlk;

    public RtbSignalData(Context context, List<MediationConfiguration> list, Bundle bundle, @Nullable AdSize adSize) {
        this.zzlk = context;
        this.zzejf = list;
        this.zzcbu = bundle;
        this.zzdg = adSize;
    }

    public Context getContext() {
        return this.zzlk;
    }

    @Deprecated
    public MediationConfiguration getConfiguration() {
        List<MediationConfiguration> list = this.zzejf;
        if (list == null || list.size() <= 0) {
            return null;
        }
        return this.zzejf.get(0);
    }

    public List<MediationConfiguration> getConfigurations() {
        return this.zzejf;
    }

    public Bundle getNetworkExtras() {
        return this.zzcbu;
    }

    @Nullable
    public AdSize getAdSize() {
        return this.zzdg;
    }
}
