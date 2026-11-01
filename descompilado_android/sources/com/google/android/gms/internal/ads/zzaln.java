package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaln extends zzfm implements zzalo {
    public zzaln() {
        super("com.google.android.gms.ads.internal.mediation.client.rtb.ISignalsCallback");
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzdi(parcel.readString());
        } else {
            if (i != 2) {
                return false;
            }
            onFailure(parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}
