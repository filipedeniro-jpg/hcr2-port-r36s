package com.google.android.gms.internal.ads;

import android.os.Environment;
import android.util.Base64;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzrz {
    private final zzse zzbrr;

    @GuardedBy("this")
    private final zztd zzbrs;
    private final boolean zzbrt;

    public static zzrz zzmk() {
        return new zzrz();
    }

    public zzrz(zzse zzseVar) {
        this.zzbrr = zzseVar;
        this.zzbrt = ((Boolean) zzuo.zzoj().zzd(zzyt.zzcqd)).booleanValue();
        this.zzbrs = new zztd();
        zzml();
    }

    private zzrz() {
        this.zzbrt = false;
        this.zzbrr = new zzse();
        this.zzbrs = new zztd();
        zzml();
    }

    public final synchronized void zza(zzsb.zza.zzb zzbVar) {
        if (this.zzbrt) {
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcqe)).booleanValue()) {
                zzc(zzbVar);
            } else {
                zzb(zzbVar);
            }
        }
    }

    private final synchronized void zzb(zzsb.zza.zzb zzbVar) {
        this.zzbrs.zzcah = zzmm();
        this.zzbrr.zzf(zzdtz.zzb(this.zzbrs)).zzbr(zzbVar.zzab()).zzdf();
        String strValueOf = String.valueOf(Integer.toString(zzbVar.zzab(), 10));
        zzatm.zzdy(strValueOf.length() != 0 ? "Logging Event with event code : ".concat(strValueOf) : new String("Logging Event with event code : "));
    }

    private final synchronized void zzc(zzsb.zza.zzb zzbVar) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(externalStorageDirectory, "clearcut_events.txt"), true);
            try {
                fileOutputStream.write(zzd(zzbVar).getBytes());
            } catch (IOException unused) {
                zzatm.zzdy("Could not write Clearcut to file.");
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                    zzatm.zzdy("Could not close Clearcut output stream.");
                }
            }
        } catch (FileNotFoundException unused3) {
            zzatm.zzdy("Could not find file for Clearcut");
        }
    }

    private final synchronized String zzd(zzsb.zza.zzb zzbVar) {
        return String.format("id=%s,timestamp=%s,event=%s,data=%s\n", this.zzbrs.zzcad, Long.valueOf(com.google.android.gms.ads.internal.zzp.zzkf().elapsedRealtime()), Integer.valueOf(zzbVar.zzab()), Base64.encodeToString(zzdtz.zzb(this.zzbrs), 3));
    }

    public final synchronized void zza(zzry zzryVar) {
        if (this.zzbrt) {
            try {
                zzryVar.zza(this.zzbrs);
            } catch (NullPointerException e) {
                com.google.android.gms.ads.internal.zzp.zzkc().zza(e, "AdMobClearcutLogger.modify");
            }
        }
    }

    private final synchronized void zzml() {
        this.zzbrs.zzcal = new zzta();
        this.zzbrs.zzcal.zzbzh = new zztc();
        this.zzbrs.zzcai = new zztb();
    }

    private static long[] zzmm() {
        int i;
        List<String> listZzpo = zzyt.zzpo();
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = listZzpo.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            String[] strArrSplit = it.next().split(",");
            int length = strArrSplit.length;
            while (i < length) {
                try {
                    arrayList.add(Long.valueOf(strArrSplit[i]));
                } catch (NumberFormatException unused) {
                    zzatm.zzdy("Experiment ID is not a number");
                }
                i++;
            }
        }
        long[] jArr = new long[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            jArr[i2] = ((Long) obj).longValue();
            i2++;
        }
        return jArr;
    }
}
