package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzdts;
import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzdts<M extends zzdts<M>> extends zzdtz {
    protected zzdtu zzhqk;

    @Override // com.google.android.gms.internal.ads.zzdtz
    protected int zznv() {
        if (this.zzhqk != null) {
            for (int i = 0; i < this.zzhqk.size(); i++) {
                this.zzhqk.zzhg(i).zznv();
            }
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzdtz
    public void zza(zzdtq zzdtqVar) throws IOException {
        if (this.zzhqk == null) {
            return;
        }
        for (int i = 0; i < this.zzhqk.size(); i++) {
            this.zzhqk.zzhg(i).zza(zzdtqVar);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzdtz
    /* JADX INFO: renamed from: zzbcd */
    public final /* synthetic */ zzdtz clone() throws CloneNotSupportedException {
        return (zzdts) clone();
    }

    @Override // com.google.android.gms.internal.ads.zzdtz
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzdts zzdtsVar = (zzdts) super.clone();
        zzdtw.zza(this, zzdtsVar);
        return zzdtsVar;
    }
}
