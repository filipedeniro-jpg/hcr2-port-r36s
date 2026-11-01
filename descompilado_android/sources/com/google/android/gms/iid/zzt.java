package com.google.android.gms.iid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: loaded from: classes.dex */
final class zzt implements ServiceConnection {

    @GuardedBy("this")
    int state;
    final Messenger zzch;
    zzy zzci;

    @GuardedBy("this")
    final Queue<zzz<?>> zzcj;

    @GuardedBy("this")
    final SparseArray<zzz<?>> zzck;
    final /* synthetic */ zzr zzcl;

    private zzt(zzr zzrVar) {
        this.zzcl = zzrVar;
        this.state = 0;
        this.zzch = new Messenger(new com.google.android.gms.internal.gcm.zzj(Looper.getMainLooper(), new Handler.Callback(this) { // from class: com.google.android.gms.iid.zzu
            private final zzt zzcm;

            {
                this.zzcm = this;
            }

            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return this.zzcm.zzd(message);
            }
        }));
        this.zzcj = new ArrayDeque();
        this.zzck = new SparseArray<>();
    }

    final synchronized boolean zze(zzz zzzVar) {
        int i = this.state;
        if (i == 0) {
            this.zzcj.add(zzzVar);
            Preconditions.checkState(this.state == 0);
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Starting bind to GmsCore");
            }
            this.state = 1;
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            if (!ConnectionTracker.getInstance().bindService(this.zzcl.zzl, intent, this, 1)) {
                zzd(0, "Unable to bind to service");
            } else {
                this.zzcl.zzce.schedule(new Runnable(this) { // from class: com.google.android.gms.iid.zzv
                    private final zzt zzcm;

                    {
                        this.zzcm = this;
                    }

                    @Override // java.lang.Runnable
                    public final void run() {
                        this.zzcm.zzv();
                    }
                }, 30L, TimeUnit.SECONDS);
            }
            return true;
        }
        if (i == 1) {
            this.zzcj.add(zzzVar);
            return true;
        }
        if (i == 2) {
            this.zzcj.add(zzzVar);
            zzt();
            return true;
        }
        if (i != 3 && i != 4) {
            int i2 = this.state;
            StringBuilder sb = new StringBuilder(26);
            sb.append("Unknown state: ");
            sb.append(i2);
            throw new IllegalStateException(sb.toString());
        }
        return false;
    }

    final boolean zzd(Message message) {
        int i = message.arg1;
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Received response to request: ");
            sb.append(i);
            Log.d("MessengerIpcClient", sb.toString());
        }
        synchronized (this) {
            zzz<?> zzzVar = this.zzck.get(i);
            if (zzzVar == null) {
                StringBuilder sb2 = new StringBuilder(50);
                sb2.append("Received response for unknown request: ");
                sb2.append(i);
                Log.w("MessengerIpcClient", sb2.toString());
                return true;
            }
            this.zzck.remove(i);
            zzu();
            Bundle data = message.getData();
            if (data.getBoolean("unsupported", false)) {
                zzzVar.zzd(new zzaa(4, "Not supported by GmsCore"));
            } else {
                zzzVar.zzh(data);
            }
            return true;
        }
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service connected");
        }
        if (iBinder == null) {
            zzd(0, "Null service connection");
            return;
        }
        try {
            this.zzci = new zzy(iBinder);
            this.state = 2;
            zzt();
        } catch (RemoteException e) {
            zzd(0, e.getMessage());
        }
    }

    private final void zzt() {
        this.zzcl.zzce.execute(new Runnable(this) { // from class: com.google.android.gms.iid.zzw
            private final zzt zzcm;

            {
                this.zzcm = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                final zzt zztVar = this.zzcm;
                while (true) {
                    synchronized (zztVar) {
                        if (zztVar.state != 2) {
                            return;
                        }
                        if (zztVar.zzcj.isEmpty()) {
                            zztVar.zzu();
                            return;
                        }
                        final zzz<?> zzzVarPoll = zztVar.zzcj.poll();
                        zztVar.zzck.put(zzzVarPoll.zzcp, zzzVarPoll);
                        zztVar.zzcl.zzce.schedule(new Runnable(zztVar, zzzVarPoll) { // from class: com.google.android.gms.iid.zzx
                            private final zzt zzcm;
                            private final zzz zzcn;

                            {
                                this.zzcm = zztVar;
                                this.zzcn = zzzVarPoll;
                            }

                            @Override // java.lang.Runnable
                            public final void run() {
                                this.zzcm.zzg(this.zzcn.zzcp);
                            }
                        }, 30L, TimeUnit.SECONDS);
                        if (Log.isLoggable("MessengerIpcClient", 3)) {
                            String strValueOf = String.valueOf(zzzVarPoll);
                            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 8);
                            sb.append("Sending ");
                            sb.append(strValueOf);
                            Log.d("MessengerIpcClient", sb.toString());
                        }
                        Context context = zztVar.zzcl.zzl;
                        Messenger messenger = zztVar.zzch;
                        Message messageObtain = Message.obtain();
                        messageObtain.what = zzzVarPoll.what;
                        messageObtain.arg1 = zzzVarPoll.zzcp;
                        messageObtain.replyTo = messenger;
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("oneWay", zzzVarPoll.zzw());
                        bundle.putString("pkg", context.getPackageName());
                        bundle.putBundle("data", zzzVarPoll.zzcr);
                        messageObtain.setData(bundle);
                        try {
                            zzy zzyVar = zztVar.zzci;
                            if (zzyVar.zzad != null) {
                                zzyVar.zzad.send(messageObtain);
                            } else {
                                if (zzyVar.zzco == null) {
                                    throw new IllegalStateException("Both messengers are null");
                                }
                                zzyVar.zzco.send(messageObtain);
                            }
                        } catch (RemoteException e) {
                            zztVar.zzd(2, e.getMessage());
                        }
                    }
                }
            }
        });
    }

    @Override // android.content.ServiceConnection
    public final synchronized void onServiceDisconnected(ComponentName componentName) {
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Service disconnected");
        }
        zzd(2, "Service disconnected");
    }

    final synchronized void zzd(int i, String str) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String strValueOf = String.valueOf(str);
            Log.d("MessengerIpcClient", strValueOf.length() != 0 ? "Disconnected: ".concat(strValueOf) : new String("Disconnected: "));
        }
        int i2 = this.state;
        if (i2 == 0) {
            throw new IllegalStateException();
        }
        if (i2 != 1 && i2 != 2) {
            if (i2 == 3) {
                this.state = 4;
                return;
            } else {
                if (i2 == 4) {
                    return;
                }
                int i3 = this.state;
                StringBuilder sb = new StringBuilder(26);
                sb.append("Unknown state: ");
                sb.append(i3);
                throw new IllegalStateException(sb.toString());
            }
        }
        if (Log.isLoggable("MessengerIpcClient", 2)) {
            Log.v("MessengerIpcClient", "Unbinding service");
        }
        this.state = 4;
        ConnectionTracker.getInstance().unbindService(this.zzcl.zzl, this);
        zzaa zzaaVar = new zzaa(i, str);
        Iterator<zzz<?>> it = this.zzcj.iterator();
        while (it.hasNext()) {
            it.next().zzd(zzaaVar);
        }
        this.zzcj.clear();
        for (int i4 = 0; i4 < this.zzck.size(); i4++) {
            this.zzck.valueAt(i4).zzd(zzaaVar);
        }
        this.zzck.clear();
    }

    final synchronized void zzu() {
        if (this.state == 2 && this.zzcj.isEmpty() && this.zzck.size() == 0) {
            if (Log.isLoggable("MessengerIpcClient", 2)) {
                Log.v("MessengerIpcClient", "Finished handling requests, unbinding");
            }
            this.state = 3;
            ConnectionTracker.getInstance().unbindService(this.zzcl.zzl, this);
        }
    }

    final synchronized void zzv() {
        if (this.state == 1) {
            zzd(1, "Timed out while binding");
        }
    }

    final synchronized void zzg(int i) {
        zzz<?> zzzVar = this.zzck.get(i);
        if (zzzVar != null) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Timing out request: ");
            sb.append(i);
            Log.w("MessengerIpcClient", sb.toString());
            this.zzck.remove(i);
            zzzVar.zzd(new zzaa(3, "Timed out waiting for response"));
            zzu();
        }
    }
}
