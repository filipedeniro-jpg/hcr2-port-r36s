package com.google.android.gms.internal.ads;

import android.view.View;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzbrd {

    @Nullable
    private final zzbbc zzcyx;
    private final zzbse zzfhz;

    public zzbrd(zzbse zzbseVar) {
        this(zzbseVar, null);
    }

    public zzbrd(zzbse zzbseVar, @Nullable zzbbc zzbbcVar) {
        this.zzfhz = zzbseVar;
        this.zzcyx = zzbbcVar;
    }

    public final zzbse zzagq() {
        return this.zzfhz;
    }

    @Nullable
    public final zzbbc zzaek() {
        return this.zzcyx;
    }

    @Nullable
    public final View zzagr() {
        zzbbc zzbbcVar = this.zzcyx;
        if (zzbbcVar == null) {
            return null;
        }
        return zzbbcVar.getWebView();
    }

    public Set<zzbqc<zzbmg>> zza(zzbsf zzbsfVar) {
        return Collections.singleton(zzbqc.zzb(zzbsfVar, zzawx.zzdwb));
    }

    public final zzbqc<zzbom> zzb(Executor executor) {
        final zzbbc zzbbcVar = this.zzcyx;
        return new zzbqc<>(new zzbom(zzbbcVar) { // from class: com.google.android.gms.internal.ads.zzbrf
            private final zzbbc zzehf;

            {
                this.zzehf = zzbbcVar;
            }

            @Override // com.google.android.gms.internal.ads.zzbom
            public final void zzafa() {
                zzbbc zzbbcVar2 = this.zzehf;
                if (zzbbcVar2.zzyu() != null) {
                    zzbbcVar2.zzyu().close();
                }
            }
        }, executor);
    }
}
