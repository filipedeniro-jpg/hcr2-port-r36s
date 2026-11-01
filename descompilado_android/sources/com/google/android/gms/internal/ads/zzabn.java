package com.google.android.gms.internal.ads;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzabn extends zzfm implements zzabo {
    public zzabn() {
        super("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
    }

    public static zzabo zzo(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.formats.client.INativeCustomTemplateAd");
        if (iInterfaceQueryLocalInterface instanceof zzabo) {
            return (zzabo) iInterfaceQueryLocalInterface;
        }
        return new zzabq(iBinder);
    }

    @Override // com.google.android.gms.internal.ads.zzfm
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                String strZzco = zzco(parcel.readString());
                parcel2.writeNoException();
                parcel2.writeString(strZzco);
                return true;
            case 2:
                zzaas zzaasVarZzcp = zzcp(parcel.readString());
                parcel2.writeNoException();
                zzfp.zza(parcel2, zzaasVarZzcp);
                return true;
            case 3:
                List<String> availableAssetNames = getAvailableAssetNames();
                parcel2.writeNoException();
                parcel2.writeStringList(availableAssetNames);
                return true;
            case 4:
                String customTemplateId = getCustomTemplateId();
                parcel2.writeNoException();
                parcel2.writeString(customTemplateId);
                return true;
            case 5:
                performClick(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                recordImpression();
                parcel2.writeNoException();
                return true;
            case 7:
                zzwk videoController = getVideoController();
                parcel2.writeNoException();
                zzfp.zza(parcel2, videoController);
                return true;
            case 8:
                destroy();
                parcel2.writeNoException();
                return true;
            case 9:
                IObjectWrapper iObjectWrapperZzqn = zzqn();
                parcel2.writeNoException();
                zzfp.zza(parcel2, iObjectWrapperZzqn);
                return true;
            case 10:
                boolean zZzt = zzt(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZzt);
                return true;
            case 11:
                IObjectWrapper iObjectWrapperZzqi = zzqi();
                parcel2.writeNoException();
                zzfp.zza(parcel2, iObjectWrapperZzqi);
                return true;
            case 12:
                boolean zZzqo = zzqo();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZzqo);
                return true;
            case 13:
                boolean zZzqp = zzqp();
                parcel2.writeNoException();
                zzfp.writeBoolean(parcel2, zZzqp);
                return true;
            case 14:
                zzu(IObjectWrapper.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            case 15:
                zzqq();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
