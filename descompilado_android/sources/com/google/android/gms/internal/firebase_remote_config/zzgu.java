package com.google.android.gms.internal.firebase_remote_config;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
final class zzgu implements zzku {
    private final zzgs zzpb;

    public static zzgu zza(zzgs zzgsVar) {
        return zzgsVar.zzpw != null ? zzgsVar.zzpw : new zzgu(zzgsVar);
    }

    private zzgu(zzgs zzgsVar) {
        this.zzpb = (zzgs) zzhm.zza(zzgsVar, "output");
        this.zzpb.zzpw = this;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final int zzgd() {
        return zzhi.zze.zztx;
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzn(int i, int i2) throws IOException {
        this.zzpb.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzi(int i, long j) throws IOException {
        this.zzpb.zza(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzj(int i, long j) throws IOException {
        this.zzpb.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, float f) throws IOException {
        this.zzpb.zza(i, f);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, double d) throws IOException {
        this.zzpb.zza(i, d);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzo(int i, int i2) throws IOException {
        this.zzpb.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, long j) throws IOException {
        this.zzpb.zza(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzd(int i, int i2) throws IOException {
        this.zzpb.zzd(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzc(int i, long j) throws IOException {
        this.zzpb.zzc(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzg(int i, int i2) throws IOException {
        this.zzpb.zzg(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzc(int i, boolean z) throws IOException {
        this.zzpb.zzc(i, z);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, String str) throws IOException {
        this.zzpb.zzb(i, str);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, zzfx zzfxVar) throws IOException {
        this.zzpb.zza(i, zzfxVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zze(int i, int i2) throws IOException {
        this.zzpb.zze(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzf(int i, int i2) throws IOException {
        this.zzpb.zzf(i, i2);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, long j) throws IOException {
        this.zzpb.zzb(i, j);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, Object obj, zzjf zzjfVar) throws IOException {
        this.zzpb.zza(i, (zziq) obj, zzjfVar);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, Object obj, zzjf zzjfVar) throws IOException {
        zzgs zzgsVar = this.zzpb;
        zzgsVar.zzc(i, 3);
        zzjfVar.zza((zziq) obj, zzgsVar.zzpw);
        zzgsVar.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzaz(int i) throws IOException {
        this.zzpb.zzc(i, 3);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzba(int i) throws IOException {
        this.zzpb.zzc(i, 4);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, Object obj) throws IOException {
        if (obj instanceof zzfx) {
            this.zzpb.zzb(i, (zzfx) obj);
        } else {
            this.zzpb.zza(i, (zziq) obj);
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzar = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzar += zzgs.zzar(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzar);
            while (i2 < list.size()) {
                this.zzpb.zzam(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzau = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzau += zzgs.zzau(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzau);
            while (i2 < list.size()) {
                this.zzpb.zzap(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzc(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzi = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzi += zzgs.zzi(list.get(i3).longValue());
            }
            this.zzpb.zzan(iZzi);
            while (i2 < list.size()) {
                this.zzpb.zzf(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzd(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzj = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzj += zzgs.zzj(list.get(i3).longValue());
            }
            this.zzpb.zzan(iZzj);
            while (i2 < list.size()) {
                this.zzpb.zzf(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zza(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zze(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzl = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzl += zzgs.zzl(list.get(i3).longValue());
            }
            this.zzpb.zzan(iZzl);
            while (i2 < list.size()) {
                this.zzpb.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzf(int i, List<Float> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzc = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzc += zzgs.zzc(list.get(i3).floatValue());
            }
            this.zzpb.zzan(iZzc);
            while (i2 < list.size()) {
                this.zzpb.zzb(list.get(i2).floatValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zza(i, list.get(i2).floatValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzg(int i, List<Double> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzd = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzd += zzgs.zzd(list.get(i3).doubleValue());
            }
            this.zzpb.zzan(iZzd);
            while (i2 < list.size()) {
                this.zzpb.zzc(list.get(i2).doubleValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zza(i, list.get(i2).doubleValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzh(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzaw = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzaw += zzgs.zzaw(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzaw);
            while (i2 < list.size()) {
                this.zzpb.zzam(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzd(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzi(int i, List<Boolean> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZze = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZze += zzgs.zze(list.get(i3).booleanValue());
            }
            this.zzpb.zzan(iZze);
            while (i2 < list.size()) {
                this.zzpb.zzd(list.get(i2).booleanValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzc(i, list.get(i2).booleanValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, List<String> list) throws IOException {
        int i2 = 0;
        if (list instanceof zzhx) {
            zzhx zzhxVar = (zzhx) list;
            while (i2 < list.size()) {
                Object objZzbd = zzhxVar.zzbd(i2);
                if (objZzbd instanceof String) {
                    this.zzpb.zzb(i, (String) objZzbd);
                } else {
                    this.zzpb.zza(i, (zzfx) objZzbd);
                }
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzb(i, list.get(i2));
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, List<zzfx> list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zzpb.zza(i, list.get(i2));
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzj(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzas = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzas += zzgs.zzas(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzas);
            while (i2 < list.size()) {
                this.zzpb.zzan(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zze(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzk(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzav = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzav += zzgs.zzav(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzav);
            while (i2 < list.size()) {
                this.zzpb.zzap(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzg(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzl(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzm = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzm += zzgs.zzm(list.get(i3).longValue());
            }
            this.zzpb.zzan(iZzm);
            while (i2 < list.size()) {
                this.zzpb.zzh(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzc(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzm(int i, List<Integer> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzat = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzat += zzgs.zzat(list.get(i3).intValue());
            }
            this.zzpb.zzan(iZzat);
            while (i2 < list.size()) {
                this.zzpb.zzao(list.get(i2).intValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzf(i, list.get(i2).intValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzn(int i, List<Long> list, boolean z) throws IOException {
        int i2 = 0;
        if (z) {
            this.zzpb.zzc(i, 2);
            int iZzk = 0;
            for (int i3 = 0; i3 < list.size(); i3++) {
                iZzk += zzgs.zzk(list.get(i3).longValue());
            }
            this.zzpb.zzan(iZzk);
            while (i2 < list.size()) {
                this.zzpb.zzg(list.get(i2).longValue());
                i2++;
            }
            return;
        }
        while (i2 < list.size()) {
            this.zzpb.zzb(i, list.get(i2).longValue());
            i2++;
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zza(int i, List<?> list, zzjf zzjfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zza(i, list.get(i2), zzjfVar);
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final void zzb(int i, List<?> list, zzjf zzjfVar) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            zzb(i, list.get(i2), zzjfVar);
        }
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzku
    public final <K, V> void zza(int i, zzih<K, V> zzihVar, Map<K, V> map) throws IOException {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            this.zzpb.zzc(i, 2);
            this.zzpb.zzan(zzii.zza(zzihVar, entry.getKey(), entry.getValue()));
            zzii.zza(this.zzpb, zzihVar, entry.getKey(), entry.getValue());
        }
    }
}
