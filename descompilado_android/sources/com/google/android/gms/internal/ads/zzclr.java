package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.doubleclick.AppEventListener;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzclr implements AppEventListener {

    @GuardedBy("this")
    private zzvm zzgca;

    public final synchronized void zzb(zzvm zzvmVar) {
        this.zzgca = zzvmVar;
    }

    public final synchronized zzvm zzald() {
        return this.zzgca;
    }

    @Override // com.google.android.gms.ads.doubleclick.AppEventListener
    public final synchronized void onAppEvent(String str, String str2) {
        if (this.zzgca != null) {
            try {
                this.zzgca.onAppEvent(str, str2);
            } catch (RemoteException e) {
                zzatm.zzd("Remote Exception at onAppEvent.", e);
            }
        }
    }
}
