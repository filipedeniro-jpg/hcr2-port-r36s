package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzava {
    private final String[] zzdtl;
    private final double[] zzdtm;
    private final double[] zzdtn;
    private final int[] zzdto;
    private int zzdtp;

    private zzava(zzavf zzavfVar) {
        int size = zzavfVar.zzdtx.size();
        this.zzdtl = (String[]) zzavfVar.zzdtw.toArray(new String[size]);
        this.zzdtm = zze(zzavfVar.zzdtx);
        this.zzdtn = zze(zzavfVar.zzdty);
        this.zzdto = new int[size];
        this.zzdtp = 0;
    }

    private static double[] zze(List<Double> list) {
        double[] dArr = new double[list.size()];
        for (int i = 0; i < dArr.length; i++) {
            dArr[i] = list.get(i).doubleValue();
        }
        return dArr;
    }

    public final void zza(double d) {
        this.zzdtp++;
        int i = 0;
        while (true) {
            double[] dArr = this.zzdtn;
            if (i >= dArr.length) {
                return;
            }
            if (dArr[i] <= d && d < this.zzdtm[i]) {
                int[] iArr = this.zzdto;
                iArr[i] = iArr[i] + 1;
            }
            if (d < this.zzdtn[i]) {
                return;
            } else {
                i++;
            }
        }
    }

    public final List<zzavc> zzvx() {
        ArrayList arrayList = new ArrayList(this.zzdtl.length);
        int i = 0;
        while (true) {
            String[] strArr = this.zzdtl;
            if (i >= strArr.length) {
                return arrayList;
            }
            String str = strArr[i];
            double d = this.zzdtn[i];
            double d2 = this.zzdtm[i];
            int[] iArr = this.zzdto;
            double d3 = iArr[i];
            double d4 = this.zzdtp;
            Double.isNaN(d3);
            Double.isNaN(d4);
            arrayList.add(new zzavc(str, d, d2, d3 / d4, iArr[i]));
            i++;
        }
    }
}
