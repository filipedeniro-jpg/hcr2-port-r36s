package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.RemoteCreator;
import java.util.HashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzacw extends RemoteCreator<zzabe> {
    @VisibleForTesting
    public zzacw() {
        super("com.google.android.gms.ads.NativeAdViewHolderDelegateCreatorImpl");
    }

    public final zzaaz zzb(View view, HashMap<String, View> map, HashMap<String, View> map2) {
        try {
            IBinder iBinderZzb = getRemoteCreatorInstance(view.getContext()).zzb(ObjectWrapper.wrap(view), ObjectWrapper.wrap(map), ObjectWrapper.wrap(map2));
            if (iBinderZzb == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzb.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegate");
            if (iInterfaceQueryLocalInterface instanceof zzaaz) {
                return (zzaaz) iInterfaceQueryLocalInterface;
            }
            return new zzabb(iBinderZzb);
        } catch (RemoteException | RemoteCreator.RemoteCreatorException e) {
            zzawo.zzd("Could not create remote NativeAdViewHolderDelegate.", e);
            return null;
        }
    }

    @Override // com.google.android.gms.dynamic.RemoteCreator
    protected final /* synthetic */ zzabe getRemoteCreator(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdViewHolderDelegateCreator");
        if (iInterfaceQueryLocalInterface instanceof zzabe) {
            return (zzabe) iInterfaceQueryLocalInterface;
        }
        return new zzabd(iBinder);
    }
}
