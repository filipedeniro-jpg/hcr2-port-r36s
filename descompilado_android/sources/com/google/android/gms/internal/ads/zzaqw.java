package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzaqw {
    public static zzaqg zzd(Context context, String str, zzajd zzajdVar) {
        try {
            IBinder iBinderZzd = ((zzaqm) zzawr.zza(context, "com.google.android.gms.ads.rewarded.ChimeraRewardedAdCreatorImpl", zzaqz.zzbtn)).zzd(ObjectWrapper.wrap(context), str, zzajdVar, 15301000);
            if (iBinderZzd == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderZzd.queryLocalInterface("com.google.android.gms.ads.internal.rewarded.client.IRewardedAd");
            return iInterfaceQueryLocalInterface instanceof zzaqg ? (zzaqg) iInterfaceQueryLocalInterface : new zzaqi(iBinderZzd);
        } catch (RemoteException | zzawt e) {
            zzawo.zze("#007 Could not call remote method.", e);
            return null;
        }
    }
}
