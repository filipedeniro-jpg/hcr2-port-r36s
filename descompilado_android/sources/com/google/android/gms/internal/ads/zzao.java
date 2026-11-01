package com.google.android.gms.internal.ads;

import androidx.annotation.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzao {
    long zzcd;
    final String zzce;
    final String zzg;
    final long zzh;
    final long zzi;
    final long zzj;
    final long zzk;
    final List<zzk> zzm;

    private zzao(String str, String str2, long j, long j2, long j3, long j4, List<zzk> list) {
        this.zzce = str;
        this.zzg = "".equals(str2) ? null : str2;
        this.zzh = j;
        this.zzi = j2;
        this.zzj = j3;
        this.zzk = j4;
        this.zzm = list;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    zzao(String str, zzd zzdVar) {
        List<zzk> list;
        String str2 = zzdVar.zzg;
        long j = zzdVar.zzh;
        long j2 = zzdVar.zzi;
        long j3 = zzdVar.zzj;
        long j4 = zzdVar.zzk;
        if (zzdVar.zzm != null) {
            list = zzdVar.zzm;
        } else {
            Map<String, String> map = zzdVar.zzl;
            ArrayList arrayList = new ArrayList(map.size());
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new zzk(entry.getKey(), entry.getValue()));
            }
            list = arrayList;
        }
        this(str, str2, j, j2, j3, j4, list);
    }

    static zzao zzc(zzan zzanVar) throws IOException {
        if (zzal.zzb((InputStream) zzanVar) != 538247942) {
            throw new IOException();
        }
        return new zzao(zzal.zza(zzanVar), zzal.zza(zzanVar), zzal.zzc(zzanVar), zzal.zzc(zzanVar), zzal.zzc(zzanVar), zzal.zzc(zzanVar), zzal.zzb(zzanVar));
    }

    final boolean zza(OutputStream outputStream) {
        try {
            zzal.zza(outputStream, 538247942);
            zzal.zza(outputStream, this.zzce);
            zzal.zza(outputStream, this.zzg == null ? "" : this.zzg);
            zzal.zza(outputStream, this.zzh);
            zzal.zza(outputStream, this.zzi);
            zzal.zza(outputStream, this.zzj);
            zzal.zza(outputStream, this.zzk);
            List<zzk> list = this.zzm;
            if (list != null) {
                zzal.zza(outputStream, list.size());
                for (zzk zzkVar : list) {
                    zzal.zza(outputStream, zzkVar.getName());
                    zzal.zza(outputStream, zzkVar.getValue());
                }
            } else {
                zzal.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzag.d("%s", e.toString());
            return false;
        }
    }
}
