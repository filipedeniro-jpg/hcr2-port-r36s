package com.google.android.gms.internal.ads;

import android.os.Bundle;
import com.google.android.exoplayer2.text.ttml.TtmlNode;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcnm implements zzcrb<Bundle> {
    private final zztx zzgdv;

    public zzcnm(zztx zztxVar) {
        this.zzgdv = zztxVar;
    }

    @Override // com.google.android.gms.internal.ads.zzcrb
    public final /* synthetic */ void zzr(Bundle bundle) {
        Bundle bundle2 = bundle;
        zztx zztxVar = this.zzgdv;
        if (zztxVar != null) {
            if (zztxVar.orientation == 1) {
                bundle2.putString("avo", TtmlNode.TAG_P);
            } else if (this.zzgdv.orientation == 2) {
                bundle2.putString("avo", "l");
            }
        }
    }
}
