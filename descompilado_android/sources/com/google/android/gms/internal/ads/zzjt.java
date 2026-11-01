package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzjt extends zzjq {
    public final long zzaui;
    public final List<zzjs> zzauj;
    public final List<zzjt> zzauk;

    public zzjt(int i, long j) {
        super(i);
        this.zzaui = j;
        this.zzauj = new ArrayList();
        this.zzauk = new ArrayList();
    }

    public final zzjs zzan(int i) {
        int size = this.zzauj.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzjs zzjsVar = this.zzauj.get(i2);
            if (zzjsVar.type == i) {
                return zzjsVar;
            }
        }
        return null;
    }

    public final zzjt zzao(int i) {
        int size = this.zzauk.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzjt zzjtVar = this.zzauk.get(i2);
            if (zzjtVar.type == i) {
                return zzjtVar;
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.ads.zzjq
    public final String toString() {
        String strZzal = zzal(this.type);
        String string = Arrays.toString(this.zzauj.toArray());
        String string2 = Arrays.toString(this.zzauk.toArray());
        StringBuilder sb = new StringBuilder(String.valueOf(strZzal).length() + 22 + String.valueOf(string).length() + String.valueOf(string2).length());
        sb.append(strZzal);
        sb.append(" leaves: ");
        sb.append(string);
        sb.append(" containers: ");
        sb.append(string2);
        return sb.toString();
    }
}
