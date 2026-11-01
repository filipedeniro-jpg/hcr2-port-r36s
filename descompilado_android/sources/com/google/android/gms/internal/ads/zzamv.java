package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzamv extends RemoteCreator<zzamz> {
    public zzamv() {
        super("com.google.android.gms.ads.AdOverlayCreatorImpl");
    }

    public final zzamu zzc(Activity activity) {
        try {
            IBinder iBinderZzah = getRemoteCreatorInstance(activity).zzah(ObjectWrapper.wrap(activity));
            if (iBinderZzah == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzah.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
            if (iInterfaceQueryLocalInterface instanceof zzamu) {
                return (zzamu) iInterfaceQueryLocalInterface;
            }
            return new zzamw(iBinderZzah);
        } catch (RemoteException e) {
            zzawo.zzd("Could not create remote AdOverlay.", e);
            return null;
        } catch (RemoteCreator.RemoteCreatorException e2) {
            zzawo.zzd("Could not create remote AdOverlay.", e2);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzamz getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlayCreator");
        if (iInterfaceQueryLocalInterface instanceof zzamz) {
            return (zzamz) iInterfaceQueryLocalInterface;
        }
        return new zzamy(iBinder);
    }
}
