package com.facebook.ads.redexgen.X;

import android.os.Parcel;
import android.support.annotation.Nullable;
import java.io.Serializable;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class LZ {
    private LZ() {
    }

    @Nullable
    public static Serializable B(@Nullable byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bytes, 0, bytes.length);
        parcelObtain.setDataPosition(0);
        Serializable serializable = parcelObtain.readSerializable();
        parcelObtain.recycle();
        return serializable;
    }

    @Nullable
    public static byte[] C(@Nullable Serializable serializable) {
        if (serializable == null) {
            return null;
        }
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeSerializable(serializable);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }
}
