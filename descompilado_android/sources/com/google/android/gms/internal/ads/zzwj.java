package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzwj extends zzfm implements zzwk {
    public zzwj() {
        super("com.google.android.gms.ads.internal.client.IVideoController");
    }

    public static zzwk zzi(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoController");
        if (iInterfaceQueryLocalInterface instanceof zzwk) {
            return (zzwk) iInterfaceQueryLocalInterface;
        }
        return new zzwm(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzwl zzwnVar;
        switch (i) {
            case 1:
                play();
                parcel2.writeNoException();
                return true;
            case 2:
                pause();
                parcel2.writeNoException();
                return true;
            case 3:
                mute(zzfp.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 4:
                boolean zIsMuted = isMuted();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsMuted);
                return true;
            case 5:
                int playbackState = getPlaybackState();
                parcel2.writeNoException();
                parcel2.writeInt(playbackState);
                return true;
            case 6:
                float fZzot = zzot();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzot);
                return true;
            case 7:
                float fZzou = zzou();
                parcel2.writeNoException();
                parcel2.writeFloat(fZzou);
                return true;
            case 8:
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzwnVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IVideoLifecycleCallbacks");
                    if (iInterfaceQueryLocalInterface instanceof zzwl) {
                        zzwnVar = (zzwl) iInterfaceQueryLocalInterface;
                    } else {
                        zzwnVar = new zzwn(strongBinder);
                    }
                }
                zza(zzwnVar);
                parcel2.writeNoException();
                return true;
            case 9:
                float aspectRatio = getAspectRatio();
                parcel2.writeNoException();
                parcel2.writeFloat(aspectRatio);
                return true;
            case 10:
                boolean zIsCustomControlsEnabled = isCustomControlsEnabled();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsCustomControlsEnabled);
                return true;
            case 11:
                zzwl zzwlVarZzov = zzov();
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzwlVarZzov);
                return true;
            case 12:
                boolean zIsClickToExpandEnabled = isClickToExpandEnabled();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zIsClickToExpandEnabled);
                return true;
            case 13:
                stop();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
