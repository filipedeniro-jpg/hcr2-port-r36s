package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.ads.internal.bridge.gms.AdvertisingId;
import com.ironsource.sdk.constants.Constants;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Go, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public final class C0550Go {
    public static final String E = C0550Go.class.getSimpleName();
    private final String B;
    private final EnumC0549Gn C;
    private final boolean D;

    private C0550Go(String mId, boolean mLimitAdTrackingEnabled, EnumC0549Gn mIdSource) {
        this.B = mId;
        this.D = mLimitAdTrackingEnabled;
        this.C = mIdSource;
    }

    public final String A() {
        return this.B;
    }

    public final EnumC0549Gn B() {
        return this.C;
    }

    public static C0550Go B(Context context, C0552Gq fb4aData) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot get advertising info on main thread.");
        }
        if (MK.E() && MK.D("idfa_override")) {
            return new C0550Go(MK.B("idfa_override"), false, EnumC0549Gn.DIRECT);
        }
        if (fb4aData != null && !TextUtils.isEmpty(fb4aData.B)) {
            return new C0550Go(fb4aData.B, fb4aData.D, EnumC0549Gn.FB4A);
        }
        C0550Go c0550GoC = C(context);
        if (c0550GoC == null || TextUtils.isEmpty(c0550GoC.A())) {
            c0550GoC = D(context);
        }
        if (c0550GoC != null && !TextUtils.isEmpty(c0550GoC.A())) {
            return c0550GoC;
        }
        return E(context);
    }

    private static C0550Go C(Context context) {
        AdvertisingId advertisingIdInfoDirectly = AdvertisingId.getAdvertisingIdInfoDirectly(context);
        if (advertisingIdInfoDirectly != null) {
            return new C0550Go(advertisingIdInfoDirectly.getId(), advertisingIdInfoDirectly.isLimitAdTracking(), EnumC0549Gn.DIRECT);
        }
        return null;
    }

    public final boolean C() {
        return this.D;
    }

    private static C0550Go D(Context context) {
        Object objD;
        Method methodC;
        Object objD2;
        Method methodC2 = C0554Gs.C("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", Context.class);
        if (methodC2 == null || (objD = C0554Gs.D(null, methodC2, context)) == null || ((Integer) objD).intValue() != 0 || (methodC = C0554Gs.C("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", Context.class)) == null || (objD2 = C0554Gs.D(null, methodC, context)) == null) {
            return null;
        }
        Method isGooglePlayServicesAvailable = C0554Gs.B(objD2.getClass(), "getId", new Class[0]);
        Method methodB = C0554Gs.B(objD2.getClass(), Constants.RequestParameters.isLAT, new Class[0]);
        if (isGooglePlayServicesAvailable == null || methodB == null) {
            return null;
        }
        String str = (String) C0554Gs.D(objD2, isGooglePlayServicesAvailable, new Object[0]);
        Boolean mLimitAdTrackingEnabled = (Boolean) C0554Gs.D(objD2, methodB, new Object[0]);
        return new C0550Go(str, mLimitAdTrackingEnabled.booleanValue(), EnumC0549Gn.REFLECTION);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [com.facebook.ads.internal.attribution.AdvertisingIdInfo$GoogleAdInfo] */
    private static C0550Go E(Context context) {
        ServiceConnectionC0548Gm serviceConnectionC0548Gm = new ServiceConnectionC0548Gm();
        Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, serviceConnectionC0548Gm, 1)) {
            try {
                final IBinder iBinderA = serviceConnectionC0548Gm.A();
                ?? r1 = new IInterface(iBinderA) { // from class: com.facebook.ads.internal.attribution.AdvertisingIdInfo$GoogleAdInfo
                    private IBinder B;

                    {
                        this.B = iBinderA;
                    }

                    public final String A() throws RemoteException {
                        Parcel parcelObtain = Parcel.obtain();
                        Parcel reply = Parcel.obtain();
                        try {
                            parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                            this.B.transact(1, parcelObtain, reply, 0);
                            reply.readException();
                            return reply.readString();
                        } finally {
                            reply.recycle();
                            parcelObtain.recycle();
                        }
                    }

                    public final boolean B() throws RemoteException {
                        boolean limitAdTracking = true;
                        Parcel parcelObtain = Parcel.obtain();
                        Parcel parcelObtain2 = Parcel.obtain();
                        try {
                            parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                            parcelObtain.writeInt(1);
                            this.B.transact(2, parcelObtain, parcelObtain2, 0);
                            parcelObtain2.readException();
                            if (parcelObtain2.readInt() == 0) {
                                limitAdTracking = false;
                            }
                            return limitAdTracking;
                        } finally {
                            parcelObtain2.recycle();
                            parcelObtain.recycle();
                        }
                    }

                    @Override // android.os.IInterface
                    public final IBinder asBinder() {
                        return this.B;
                    }
                };
                return new C0550Go(r1.A(), r1.B(), EnumC0549Gn.SERVICE);
            } catch (Exception e) {
            } finally {
                context.unbindService(serviceConnectionC0548Gm);
            }
        }
        return null;
    }
}
