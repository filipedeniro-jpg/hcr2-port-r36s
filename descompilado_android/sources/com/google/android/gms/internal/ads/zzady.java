package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzady implements zzadx<zzbbc> {
    zzady() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.ads.zzadx
    public final /* synthetic */ void zza(zzbbc zzbbcVar, Map map) {
        zzbbc zzbbcVar2 = zzbbcVar;
        WindowManager windowManager = (WindowManager) zzbbcVar2.getContext().getSystemService("window");
        com.google.android.gms.ads.internal.zzp.zzjy();
        DisplayMetrics displayMetricsZza = zzatv.zza(windowManager);
        int i = displayMetricsZza.widthPixels;
        int i2 = displayMetricsZza.heightPixels;
        int[] iArr = new int[2];
        HashMap map2 = new HashMap();
        ((View) zzbbcVar2).getLocationInWindow(iArr);
        map2.put("xInPixels", Integer.valueOf(iArr[0]));
        map2.put("yInPixels", Integer.valueOf(iArr[1]));
        map2.put("windowWidthInPixels", Integer.valueOf(i));
        map2.put("windowHeightInPixels", Integer.valueOf(i2));
        zzbbcVar2.zza("locationReady", map2);
        zzatm.zzeu("GET LOCATION COMPILED");
    }
}
