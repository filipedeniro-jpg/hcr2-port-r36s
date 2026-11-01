package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaao implements NativeCustomTemplateAd.DisplayOpenMeasurement {
    private final zzabo zzcvq;

    public zzaao(zzabo zzaboVar) {
        this.zzcvq = zzaboVar;
        try {
            zzaboVar.zzqq();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd.DisplayOpenMeasurement
    public final boolean start() {
        try {
            return this.zzcvq.zzqp();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            return false;
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeCustomTemplateAd.DisplayOpenMeasurement
    public final void setView(View view) {
        try {
            this.zzcvq.zzu(ObjectWrapper.wrap(view));
        } catch (RemoteException e) {
            zzawo.zzc("", e);
        }
    }
}
