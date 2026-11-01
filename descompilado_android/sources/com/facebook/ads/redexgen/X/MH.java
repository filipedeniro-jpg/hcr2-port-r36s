package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.UiThread;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.dynamicloading.DynamicLoaderImpl;
import com.facebook.ads.internal.ipc.RemoteANActivity;

/* JADX INFO: loaded from: assets/audience_network.dex */
@UiThread
public final class MH {
    public static final Package D = MH.class.getPackage();
    private static final String C = D + ".ADNW_ORIGINAL_EXTRAS";
    public static final String B = D + ".ADNW_SAVED_STATE_DATA";

    private MH() {
    }

    public static Class B() {
        return I6.D ? RemoteANActivity.class : AudienceNetworkActivity.class;
    }

    public static Bundle C(Bundle originalSavedState) {
        Parcel parcelObtain = Parcel.obtain();
        byte[] byteArray = originalSavedState.getByteArray(B);
        if (byteArray != null) {
            parcelObtain.unmarshall(byteArray, 0, byteArray.length);
            parcelObtain.setDataPosition(0);
            Bundle bundle = parcelObtain.readBundle(DynamicLoaderImpl.class.getClassLoader());
            parcelObtain.recycle();
            return bundle;
        }
        throw new IllegalStateException("Saved state bundle is empty!");
    }

    public static Intent D(Intent cloneIntent) {
        Intent intentCloneFilter = cloneIntent.cloneFilter();
        intentCloneFilter.setFlags(cloneIntent.getFlags());
        Bundle bundle = new Bundle();
        Parcel parcelObtain = Parcel.obtain();
        byte[] byteArrayExtra = cloneIntent.getByteArrayExtra(C);
        if (byteArrayExtra != null) {
            parcelObtain.unmarshall(byteArrayExtra, 0, byteArrayExtra.length);
            parcelObtain.setDataPosition(0);
            bundle = parcelObtain.readBundle(DynamicLoaderImpl.class.getClassLoader());
            parcelObtain.recycle();
        }
        intentCloneFilter.putExtras(bundle);
        return intentCloneFilter;
    }

    public static void E(Context context, Intent startIntent) {
        context.startActivity(G(startIntent));
    }

    public static void F(Bundle originalSavedState, Bundle adnwSavedState) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeBundle(adnwSavedState);
        originalSavedState.putByteArray(B, parcelObtain.marshall());
        parcelObtain.recycle();
    }

    private static Intent G(Intent startIntent) {
        Intent intentCloneFilter = startIntent.cloneFilter();
        intentCloneFilter.setFlags(startIntent.getFlags());
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.writeBundle(startIntent.getExtras());
        intentCloneFilter.putExtra(C, parcelObtain.marshall());
        parcelObtain.recycle();
        return intentCloneFilter;
    }
}
