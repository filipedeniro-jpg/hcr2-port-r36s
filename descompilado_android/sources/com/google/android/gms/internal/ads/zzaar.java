package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzaar extends zzfm implements zzaas {
    public zzaar() {
        super("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
    }

    public static zzaas zzl(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeAdImage");
        if (iInterfaceQueryLocalInterface instanceof zzaas) {
            return (zzaas) iInterfaceQueryLocalInterface;
        }
        return new zzaau(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper iObjectWrapperZzqf = zzqf();
            parcel2.writeNoException();
            zzfp.zza(parcel2, iObjectWrapperZzqf);
        } else if (i == 2) {
            Uri uri = getUri();
            parcel2.writeNoException();
            zzfp.zzb(parcel2, uri);
        } else if (i == 3) {
            double scale = getScale();
            parcel2.writeNoException();
            parcel2.writeDouble(scale);
        } else if (i == 4) {
            int width = getWidth();
            parcel2.writeNoException();
            parcel2.writeInt(width);
        } else {
            if (i != 5) {
                return false;
            }
            int height = getHeight();
            parcel2.writeNoException();
            parcel2.writeInt(height);
        }
        return true;
    }
}
