package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-base@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzdpn implements zzdtr {
    private final zzdpm zzhft;

    public static zzdpn zza(zzdpm zzdpmVar) {
        return zzdpmVar.zzhgk != null ? zzdpmVar.zzhgk : new zzdpn(zzdpmVar);
    }

    private zzdpn(zzdpm zzdpmVar) {
        this.zzhft = (zzdpm) zzdqe.zza(zzdpmVar, "output");
        this.zzhft.zzhgk = this;
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final int zzayt() {
        return zzdqd.zzd.zzhks;
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzak(int i, int i2) throws IOException {
        this.zzhft.zzad(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzo(int i, long j) throws IOException {
        this.zzhft.zzg(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzp(int i, long j) throws IOException {
        this.zzhft.zzi(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, float f) throws IOException {
        this.zzhft.zza(i, f);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, double d) throws IOException {
        this.zzhft.zzb(i, d);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzal(int i, int i2) throws IOException {
        this.zzhft.zzaa(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzg(int i, long j) throws IOException {
        this.zzhft.zzg(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzaa(int i, int i2) throws IOException {
        this.zzhft.zzaa(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzi(int i, long j) throws IOException {
        this.zzhft.zzi(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzad(int i, int i2) throws IOException {
        this.zzhft.zzad(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzh(int i, boolean z) throws IOException {
        this.zzhft.zzh(i, z);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzg(int i, String str) throws IOException {
        this.zzhft.zzg(i, str);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, zzdot zzdotVar) throws IOException {
        this.zzhft.zza(i, zzdotVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzab(int i, int i2) throws IOException {
        this.zzhft.zzab(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzac(int i, int i2) throws IOException {
        this.zzhft.zzac(i, i2);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzh(int i, long j) throws IOException {
        this.zzhft.zzh(i, j);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, Object obj, zzdsc zzdscVar) throws IOException {
        this.zzhft.zza(i, (zzdrn) obj, zzdscVar);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, Object obj, zzdsc zzdscVar) throws IOException {
        zzdpm zzdpmVar = this.zzhft;
        zzdpmVar.zzz(i, 3);
        zzdscVar.zza((zzdrn) obj, zzdpmVar.zzhgk);
        zzdpmVar.zzz(i, 4);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzgn(int i) throws IOException {
        this.zzhft.zzz(i, 3);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzgo(int i) throws IOException {
        this.zzhft.zzz(i, 4);
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, Object obj) throws IOException {
        if (obj instanceof zzdot) {
            this.zzhft.zzb(i, (zzdot) obj);
        } else {
            this.zzhft.zzb(i, (zzdrn) obj);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgf = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgf += zzdpm.zzgf(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgf);
            while (i2 < list.size()) {
                this.zzhft.zzga(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzaa(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgi = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgi += zzdpm.zzgi(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgi);
            while (i2 < list.size()) {
                this.zzhft.zzgd(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzad(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzfd = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzfd += zzdpm.zzfd(list.get(i3).longValue());
            }
            this.zzhft.zzgb(iZzfd);
            while (i2 < list.size()) {
                this.zzhft.zzfa(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzg(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzfe = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzfe += zzdpm.zzfe(list.get(i3).longValue());
            }
            this.zzhft.zzgb(iZzfe);
            while (i2 < list.size()) {
                this.zzhft.zzfa(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzg(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzfg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzfg += zzdpm.zzfg(list.get(i3).longValue());
            }
            this.zzhft.zzgb(iZzfg);
            while (i2 < list.size()) {
                this.zzhft.zzfc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzi(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzg += zzdpm.zzg(list.get(i3).floatValue());
            }
            this.zzhft.zzgb(iZzg);
            while (i2 < list.size()) {
                this.zzhft.zzf(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzc = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzc += zzdpm.zzc(list.get(i3).doubleValue());
            }
            this.zzhft.zzgb(iZzc);
            while (i2 < list.size()) {
                this.zzhft.zzb(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzb(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgk = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgk += zzdpm.zzgk(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgk);
            while (i2 < list.size()) {
                this.zzhft.zzga(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzaa(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzbi = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzbi += zzdpm.zzbi(list.get(i3).booleanValue());
            }
            this.zzhft.zzgb(iZzbi);
            while (i2 < list.size()) {
                this.zzhft.zzbh(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzh(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzdqu) {
            zzdqu zzdquVar = (zzdqu) list;
            while (i2 < list.size()) {
                Object objZzgr = zzdquVar.zzgr(i2);
                if (objZzgr instanceof String) {
                    this.zzhft.zzg(i, (String) objZzgr);
                } else {
                    this.zzhft.zza(i, (zzdot) objZzgr);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzg(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, List<zzdot> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzhft.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgg = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgg += zzdpm.zzgg(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgg);
            while (i2 < list.size()) {
                this.zzhft.zzgb(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzab(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgj = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgj += zzdpm.zzgj(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgj);
            while (i2 < list.size()) {
                this.zzhft.zzgd(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzad(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzfh = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzfh += zzdpm.zzfh(list.get(i3).longValue());
            }
            this.zzhft.zzgb(iZzfh);
            while (i2 < list.size()) {
                this.zzhft.zzfc(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzi(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzgh = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzgh += zzdpm.zzgh(list.get(i3).intValue());
            }
            this.zzhft.zzgb(iZzgh);
            while (i2 < list.size()) {
                this.zzhft.zzgc(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzac(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzhft.zzz(i, 2);
            int iZzff = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzff += zzdpm.zzff(list.get(i3).longValue());
            }
            this.zzhft.zzgb(iZzff);
            while (i2 < list.size()) {
                this.zzhft.zzfb(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzhft.zzh(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zza(int i, List<?> list, zzdsc zzdscVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzdscVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final void zzb(int i, List<?> list, zzdsc zzdscVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzdscVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtr
    public final <K, V> void zza(int i, zzdre<K, V> zzdreVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzhft.zzz(i, 2);
            this.zzhft.zzgb(zzdrf.zza(zzdreVar, entry.getKey(), entry.getValue()));
            zzdrf.zza(this.zzhft, zzdreVar, entry.getKey(), entry.getValue());
        }
    }
}
