package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzqg {
    private final int zzbqd;
    private final zzqh zzbqf = new zzqk();
    private final int zzbqc = 6;
    private final int zzbqe = 0;

    public zzqg(int i) {
        this.zzbqd = i;
    }

    public final String zza(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            String str = arrayList2.get(i);
            i++;
            sb.append(str.toLowerCase(Locale.US));
            sb.append('\n');
        }
        return zzbq(sb.toString());
    }

    @VisibleForTesting
    private final String zzbq(String str) {
        String[] strArrSplit = str.split("\n");
        if (strArrSplit.length == 0) {
            return "";
        }
        zzqi zzqiVar = new zzqi();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzbqd, new zzqj(this));
        for (String str2 : strArrSplit) {
            String[] strArrZze = zzql.zze(str2, false);
            if (strArrZze.length != 0) {
                zzqm.zza(strArrZze, this.zzbqd, this.zzbqc, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzqiVar.write(this.zzbqf.zzbr(((zzqp) it.next()).zzbqm));
            } catch (IOException e) {
                zzatm.zzc("Error while writing hash to byteStream", e);
            }
        }
        return zzqiVar.toString();
    }
}
