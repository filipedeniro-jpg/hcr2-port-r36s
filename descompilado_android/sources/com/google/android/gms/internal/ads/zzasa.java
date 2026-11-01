package com.google.android.gms.internal.ads;

import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzasa implements zzasi {
    static final zzasi zzdpj = new zzasa();

    private zzasa() {
    }

    @Override // com.google.android.gms.internal.ads.zzasi
    public final Object zzb(zzbdl zzbdlVar) throws RemoteException {
        String currentScreenName = zzbdlVar.getCurrentScreenName();
        if (currentScreenName != null) {
            return currentScreenName;
        }
        String currentScreenClass = zzbdlVar.getCurrentScreenClass();
        return currentScreenClass != null ? currentScreenClass : "";
    }
}
