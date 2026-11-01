package com.google.android.gms.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import androidx.annotation.Nullable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public class MediationAdConfiguration {
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_FALSE = 0;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_TRUE = 1;
    public static final int TAG_FOR_CHILD_DIRECTED_TREATMENT_UNSPECIFIED = -1;
    private final String zzabl;
    private final int zzddj;
    private final String zzdek;
    private final String zzehw;
    private final Bundle zzehx;
    private final Bundle zzehy;
    private final int zzehz;
    private final Context zzlk;
    private final boolean zznf;
    private final Location zzng;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface TagForChildDirectedTreatment {
    }

    public MediationAdConfiguration(Context context, String str, Bundle bundle, Bundle bundle2, boolean z, @Nullable Location location, int i, int i2, @Nullable String str2, String str3) {
        this.zzehw = str;
        this.zzehx = bundle;
        this.zzehy = bundle2;
        this.zzlk = context;
        this.zznf = z;
        this.zzng = location;
        this.zzddj = i;
        this.zzehz = i2;
        this.zzabl = str2;
        this.zzdek = str3;
    }

    public String getBidResponse() {
        return this.zzehw;
    }

    public Bundle getServerParameters() {
        return this.zzehx;
    }

    public Bundle getMediationExtras() {
        return this.zzehy;
    }

    public Context getContext() {
        return this.zzlk;
    }

    public Location getLocation() {
        return this.zzng;
    }

    public int taggedForChildDirectedTreatment() {
        return this.zzddj;
    }

    public boolean isTestRequest() {
        return this.zznf;
    }

    public int taggedForUnderAgeTreatment() {
        return this.zzehz;
    }

    @Nullable
    public String getMaxAdContentRating() {
        return this.zzabl;
    }
}
