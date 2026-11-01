package com.google.android.gms.internal.ads;

import com.ironsource.sdk.constants.Constants;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbyp implements zzdvi<String> {
    private static final zzbyp zzfpx = new zzbyp();

    public static zzbyp zzaje() {
        return zzfpx;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(Constants.CONVERT_REWARDED, "Cannot return null from a non-@Nullable @Provides method");
    }
}
