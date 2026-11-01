package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzavf {
    private final List<String> zzdtw = new ArrayList();
    private final List<Double> zzdtx = new ArrayList();
    private final List<Double> zzdty = new ArrayList();

    public final zzavf zza(String str, double d, double d2) {
        int i = 0;
        while (i < this.zzdtw.size()) {
            double dDoubleValue = this.zzdty.get(i).doubleValue();
            double dDoubleValue2 = this.zzdtx.get(i).doubleValue();
            if (d < dDoubleValue || (dDoubleValue == d && d2 < dDoubleValue2)) {
                break;
            }
            i++;
        }
        this.zzdtw.add(i, str);
        this.zzdty.add(i, Double.valueOf(d));
        this.zzdtx.add(i, Double.valueOf(d2));
        return this;
    }

    public final zzava zzvy() {
        return new zzava(this);
    }
}
