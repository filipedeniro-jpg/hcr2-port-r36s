package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzto extends RemoteCreator<zzvc> {
    public zzto() {
        super("com.google.android.gms.ads.AdLoaderBuilderCreatorImpl");
    }

    public final zzux zza(Context context, String str, zzajd zzajdVar) {
        try {
            IBinder iBinderZzc = getRemoteCreatorInstance(context).zzc(ObjectWrapper.wrap(context), str, zzajdVar, 15301000);
            if (iBinderZzc == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzc.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilder");
            if (iInterfaceQueryLocalInterface instanceof zzux) {
                return (zzux) iInterfaceQueryLocalInterface;
            }
            return new zzuz(iBinderZzc);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzawo.zzd("Could not create remote builder for AdLoader.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzvc getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdLoaderBuilderCreator");
        if (iInterfaceQueryLocalInterface instanceof zzvc) {
            return (zzvc) iInterfaceQueryLocalInterface;
        }
        return new zzvb(iBinder);
    }
}
