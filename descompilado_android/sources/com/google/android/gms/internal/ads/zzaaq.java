package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaaq extends zzfm implements zzaan {
    public zzaaq() {
        super("com.google.android.gms.ads.internal.formats.client.IMediaContent");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 2) {
            float aspectRatio = getAspectRatio();
            parcel2.writeNoException();
            parcel2.writeFloat(aspectRatio);
            return true;
        }
        if (i == 3) {
            zzs(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            return true;
        }
        if (i != 4) {
            return false;
        }
        IObjectWrapper iObjectWrapperZzqh = zzqh();
        parcel2.writeNoException();
        zzfp.zza(parcel2, iObjectWrapperZzqh);
        return true;
    }
}
