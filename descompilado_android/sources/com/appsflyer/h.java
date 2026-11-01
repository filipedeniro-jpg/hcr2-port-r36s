package com.appsflyer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes.dex */
final class h {
    h() {
    }

    static final class e {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final boolean f149;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private final String f150;

        e(String str, boolean z) {
            this.f150 = str;
            this.f149 = z;
        }

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        public final String m138() {
            return this.f150;
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final boolean m137() {
            return this.f149;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static e m133(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            byte b2 = 0;
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b(b2);
            Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
            intent.setPackage("com.google.android.gms");
            try {
                try {
                    if (context.bindService(intent, bVar, 1)) {
                        a aVar = new a(bVar.m136());
                        e eVar = new e(aVar.m135(), aVar.m134());
                        if (context != null) {
                            context.unbindService(bVar);
                        }
                        return eVar;
                    }
                    if (context != null) {
                        context.unbindService(bVar);
                    }
                    throw new IOException("Google Play connection failed");
                } catch (Exception e2) {
                    throw e2;
                }
            } catch (Throwable th) {
                if (context != null) {
                    context.unbindService(bVar);
                }
                throw th;
            }
        } catch (Exception e3) {
            throw e3;
        }
    }

    static final class b implements ServiceConnection {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final LinkedBlockingQueue<IBinder> f147;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private boolean f148;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        private b() {
            this.f148 = false;
            this.f147 = new LinkedBlockingQueue<>(1);
        }

        /* synthetic */ b(byte b) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.f147.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        public final IBinder m136() throws InterruptedException {
            if (this.f148) {
                throw new IllegalStateException();
            }
            this.f148 = true;
            return this.f147.take();
        }
    }

    static final class a implements IInterface {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private IBinder f146;

        a(IBinder iBinder) {
            this.f146 = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f146;
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        public final String m135() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                this.f146.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final boolean m134() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken(AdvertisingInfoServiceStrategy.AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                parcelObtain.writeInt(1);
                this.f146.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }
}
