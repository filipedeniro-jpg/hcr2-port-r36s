package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.facebook.internal.AnalyticsEvents;
import com.ironsource.sdk.constants.Constants;
import com.my.target.aa;
import com.my.target.ai;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcps implements zzcrb<Bundle> {
    private final String zzlt;

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzd(Set<String> set) {
        return set.contains(Constants.CONVERT_REWARDED) || set.contains(aa.e.bo) || set.contains(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE) || set.contains(ai.a.cW);
    }

    public zzcps(String str) {
        this.zzlt = str;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        zzcvq.zza(bundle, "omid_v", this.zzlt);
    }
}
