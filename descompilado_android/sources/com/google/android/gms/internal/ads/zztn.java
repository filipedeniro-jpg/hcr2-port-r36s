package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zztn extends RemoteCreator<zzvf> {
    @VisibleForTesting
    public zztn() {
        super("com.google.android.gms.ads.AdManagerCreatorImpl");
    }

    public final zzve zza(Context context, zztw zztwVar, String str, zzajd zzajdVar, int i) {
        try {
            IBinder iBinderZza = getRemoteCreatorInstance(context).zza(ObjectWrapper.wrap(context), zztwVar, str, zzajdVar, 15301000, i);
            if (iBinderZza == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZza.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManager");
            if (iInterfaceQueryLocalInterface instanceof zzve) {
                return (zzve) iInterfaceQueryLocalInterface;
            }
            return new zzvg(iBinderZza);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzawo.zzb("Could not create remote AdManager.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzvf getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
        if (iInterfaceQueryLocalInterface instanceof zzvf) {
            return (zzvf) iInterfaceQueryLocalInterface;
        }
        return new zzvi(iBinder);
    }
}
