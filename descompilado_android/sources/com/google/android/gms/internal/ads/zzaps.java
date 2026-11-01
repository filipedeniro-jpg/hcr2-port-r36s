package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaps extends RemoteCreator<zzapm> {
    public zzaps() {
        super("com.google.android.gms.ads.reward.RewardedVideoAdCreatorImpl");
    }

    public final zzapl zza(Context context, zzajd zzajdVar) {
        try {
            IBinder iBinderZzb = getRemoteCreatorInstance(context).zzb(ObjectWrapper.wrap(context), zzajdVar, 15301000);
            if (iBinderZzb == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzb.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAd");
            if (iInterfaceQueryLocalInterface instanceof zzapl) {
                return (zzapl) iInterfaceQueryLocalInterface;
            }
            return new zzapn(iBinderZzb);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzawo.zzd("Could not get remote RewardedVideoAd.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzapm getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.reward.client.IRewardedVideoAdCreator");
        if (iInterfaceQueryLocalInterface instanceof zzapm) {
            return (zzapm) iInterfaceQueryLocalInterface;
        }
        return new zzapp(iBinder);
    }
}
