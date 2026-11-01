package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzcdl extends zzaoh {
    private final /* synthetic */ zzcdm zzful;

    protected zzcdl(zzcdm zzcdmVar) {
        this.zzful = zzcdmVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaoe
    public final void zzb(ParcelFileDescriptor parcelFileDescriptor) {
        this.zzful.zzdaz.set(new ParcelFileDescriptor.AutoCloseInputStream(parcelFileDescriptor));
    }

    @Override // com.google.android.gms.internal.ads.zzaoe
    public final void zza(zzauw zzauwVar) {
        this.zzful.zzdaz.setException(new zzauz(zzauwVar.zzdtk, zzauwVar.errorCode));
    }
}
