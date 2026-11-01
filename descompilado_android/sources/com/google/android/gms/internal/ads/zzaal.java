package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.ads.formats.NativeAd;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaal extends NativeAd.AdChoicesInfo {
    private String zzazo;
    private final List<NativeAd.Image> zzcve = new ArrayList();
    private final zzaak zzcvp;

    public zzaal(zzaak zzaakVar) {
        zzaas zzaauVar;
        IBinder iBinder;
        this.zzcvp = zzaakVar;
        try {
            this.zzazo = this.zzcvp.getText();
        } catch (RemoteException e) {
            zzawo.zzc("", e);
            this.zzazo = "";
        }
        try {
            for (zzaas zzaasVar : zzaakVar.zzqa()) {
                if (!(zzaasVar instanceof IBinder) || (iBinder = (IBinder) zzaasVar) == null) {
                    zzaauVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
                    zzaauVar = iInterfaceQueryLocalInterface instanceof zzaas ? (zzaas) iInterfaceQueryLocalInterface : new zzaau(iBinder);
                }
                if (zzaauVar != null) {
                    this.zzcve.add(new zzaat(zzaauVar));
                }
            }
        } catch (RemoteException e2) {
            zzawo.zzc("", e2);
        }
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final CharSequence getText() {
        return this.zzazo;
    }

    @Override // com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo
    public final List<NativeAd.Image> getImages() {
        return this.zzcve;
    }
}
