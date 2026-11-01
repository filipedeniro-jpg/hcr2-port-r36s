package com.google.android.gms.internal.ads;

import com.facebook.internal.AnalyticsEvents;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzbsv implements zzdvi<String> {
    private static final zzbsv zzfit = new zzbsv();

    public static zzbsv zzagw() {
        return zzfit;
    }

    @Override // com.google.android.gms.internal.ads.zzdvv
    public final /* synthetic */ Object get() {
        return (String) zzdvo.zza(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE, "Cannot return null from a non-@Nullable @Provides method");
    }
}
