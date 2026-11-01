package com.google.android.gms.internal.ads;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzasz {
    private long zzdqe = -1;
    private long zzdqf = -1;
    private final /* synthetic */ zzasw zzdqg;

    public zzasz(zzasw zzaswVar) {
        this.zzdqg = zzaswVar;
    }

    public final long zzua() {
        return this.zzdqf;
    }

    public final void zzub() {
        this.zzdqf = this.zzdqg.zzbmd.elapsedRealtime();
    }

    public final void zzuc() {
        this.zzdqe = this.zzdqg.zzbmd.elapsedRealtime();
    }

    public final Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putLong("topen", this.zzdqe);
        bundle.putLong("tclose", this.zzdqf);
        return bundle;
    }
}
