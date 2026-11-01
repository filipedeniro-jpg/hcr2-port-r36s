package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlx implements zzls {
    private final zzls[] zzbbk;
    private final ArrayList<zzls> zzbbl;
    private zzlv zzbbm;
    private zzha zzbbn;
    private Object zzbbo;
    private zzlz zzbbq;
    private final zzhb zzacs = new zzhb();
    private int zzbbp = -1;

    public zzlx(zzls... zzlsVarArr) {
        this.zzbbk = zzlsVarArr;
        this.zzbbl = new ArrayList<>(Arrays.asList(zzlsVarArr));
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zza(zzge zzgeVar, boolean z, zzlv zzlvVar) {
        this.zzbbm = zzlvVar;
        int i = 0;
        while (true) {
            zzls[] zzlsVarArr = this.zzbbk;
            if (i >= zzlsVarArr.length) {
                return;
            }
            zzlsVarArr[i].zza(zzgeVar, false, new zzlw(this, i));
            i++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzhi() throws IOException {
        zzlz zzlzVar = this.zzbbq;
        if (zzlzVar != null) {
            throw zzlzVar;
        }
        for (zzls zzlsVar : this.zzbbk) {
            zzlsVar.zzhi();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final zzlq zza(int i, zzna zznaVar) {
        zzlq[] zzlqVarArr = new zzlq[this.zzbbk.length];
        for (int i2 = 0; i2 < zzlqVarArr.length; i2++) {
            zzlqVarArr[i2] = this.zzbbk[i2].zza(i, zznaVar);
        }
        return new zzlu(zzlqVarArr);
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzb(zzlq zzlqVar) {
        zzlu zzluVar = (zzlu) zzlqVar;
        int i = 0;
        while (true) {
            zzls[] zzlsVarArr = this.zzbbk;
            if (i >= zzlsVarArr.length) {
                return;
            }
            zzlsVarArr[i].zzb(zzluVar.zzbbd[i]);
            i++;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzls
    public final void zzhj() {
        for (zzls zzlsVar : this.zzbbk) {
            zzlsVar.zzhj();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(int i, zzha zzhaVar, Object obj) {
        zzlz zzlzVar;
        if (this.zzbbq == null) {
            int iZzem = zzhaVar.zzem();
            int i2 = 0;
            while (true) {
                if (i2 < iZzem) {
                    if (zzhaVar.zza(i2, this.zzacs, false).zzagi) {
                        zzlzVar = new zzlz(0);
                        break;
                    }
                    i2++;
                } else {
                    if (this.zzbbp == -1) {
                        this.zzbbp = zzhaVar.zzen();
                    } else if (zzhaVar.zzen() != this.zzbbp) {
                        zzlzVar = new zzlz(1);
                        break;
                    }
                    zzlzVar = null;
                    break;
                }
            }
            this.zzbbq = zzlzVar;
        }
        if (this.zzbbq != null) {
            return;
        }
        this.zzbbl.remove(this.zzbbk[i]);
        if (i == 0) {
            this.zzbbn = zzhaVar;
            this.zzbbo = obj;
        }
        if (this.zzbbl.isEmpty()) {
            this.zzbbm.zzb(this.zzbbn, this.zzbbo);
        }
    }
}
