package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzxg extends RemoteCreator<zzvv> {
    public zzxg() {
        super("com.google.android.gms.ads.MobileAdsSettingManagerCreatorImpl");
    }

    public final zzvu zzi(Context context) {
        try {
            IBinder iBinderZzb = getRemoteCreatorInstance(context).zzb(ObjectWrapper.wrap(context), 15301000);
            if (iBinderZzb == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzb.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManager");
            if (iInterfaceQueryLocalInterface instanceof zzvu) {
                return (zzvu) iInterfaceQueryLocalInterface;
            }
            return new zzvw(iBinderZzb);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzawo.zzd("Could not get remote MobileAdsSettingManager.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzvv getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IMobileAdsSettingManagerCreator");
        if (iInterfaceQueryLocalInterface instanceof zzvv) {
            return (zzvv) iInterfaceQueryLocalInterface;
        }
        return new zzvy(iBinder);
    }
}
