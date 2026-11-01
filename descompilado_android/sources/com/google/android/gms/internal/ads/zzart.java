package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
@TargetApi(21)
final class zzart {
    private static final Map<String, String> zzdot;
    private final List<String> zzdou;
    private final zzarf zzdov;
    private final Context zzlk;

    zzart(Context context, List<String> list, zzarf zzarfVar) {
        this.zzlk = context;
        this.zzdou = list;
        this.zzdov = zzarfVar;
    }

    final List<String> zzb(String[] strArr) {
        boolean z;
        boolean z2;
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            Iterator<String> it = this.zzdou.iterator();
            while (true) {
                z = true;
                if (!it.hasNext()) {
                    z2 = false;
                    break;
                }
                String next = it.next();
                if (!next.equals(str)) {
                    String strValueOf = String.valueOf(next);
                    if ((strValueOf.length() != 0 ? "android.webkit.resource.".concat(strValueOf) : new String("android.webkit.resource.")).equals(str)) {
                    }
                }
                z2 = true;
                break;
            }
            if (z2) {
                if (zzdot.containsKey(str)) {
                    com.google.android.gms.ads.internal.zzp.zzjy();
                    if (!zzatv.zzq(this.zzlk, zzdot.get(str))) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(str);
                } else {
                    this.zzdov.zzds(str);
                }
            } else {
                this.zzdov.zzdr(str);
            }
        }
        return arrayList;
    }

    static {
        HashMap map = new HashMap();
        if (PlatformVersion.isAtLeastLollipop()) {
            map.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            map.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzdot = map;
    }
}
