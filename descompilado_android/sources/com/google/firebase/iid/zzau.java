package com.google.firebase.iid;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@19.0.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzau {

    @GuardedBy("this")
    private int zza = 0;

    @GuardedBy("this")
    private final Map<Integer, TaskCompletionSource<Void>> zzb = new ArrayMap();

    @GuardedBy("itself")
    private final zzaq zzc;

    zzau(zzaq zzaqVar) {
        this.zzc = zzaqVar;
    }

    final synchronized Task<Void> zza(String str) {
        String strZza;
        TaskCompletionSource<Void> taskCompletionSource;
        synchronized (this.zzc) {
            strZza = this.zzc.zza();
            zzaq zzaqVar = this.zzc;
            StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 1 + String.valueOf(str).length());
            sb.append(strZza);
            sb.append(",");
            sb.append(str);
            zzaqVar.zza(sb.toString());
        }
        taskCompletionSource = new TaskCompletionSource<>();
        this.zzb.put(Integer.valueOf(this.zza + (TextUtils.isEmpty(strZza) ? 0 : strZza.split(",").length - 1)), taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    final synchronized boolean zza() {
        return zzb() != null;
    }

    @WorkerThread
    final boolean zza(FirebaseInstanceId firebaseInstanceId) throws IOException {
        TaskCompletionSource<Void> taskCompletionSourceRemove;
        while (true) {
            synchronized (this) {
                String strZzb = zzb();
                if (strZzb == null) {
                    if (FirebaseInstanceId.zzd()) {
                        Log.d("FirebaseInstanceId", "topic sync succeeded");
                    }
                    return true;
                }
                if (!zza(firebaseInstanceId, strZzb)) {
                    return false;
                }
                synchronized (this) {
                    taskCompletionSourceRemove = this.zzb.remove(Integer.valueOf(this.zza));
                    zzb(strZzb);
                    this.zza++;
                }
                if (taskCompletionSourceRemove != null) {
                    taskCompletionSourceRemove.setResult(null);
                }
            }
        }
    }

    @Nullable
    @GuardedBy("this")
    private final String zzb() {
        String strZza;
        synchronized (this.zzc) {
            strZza = this.zzc.zza();
        }
        if (TextUtils.isEmpty(strZza)) {
            return null;
        }
        String[] strArrSplit = strZza.split(",");
        if (strArrSplit.length <= 1 || TextUtils.isEmpty(strArrSplit[1])) {
            return null;
        }
        return strArrSplit[1];
    }

    private final synchronized boolean zzb(String str) {
        synchronized (this.zzc) {
            String strZza = this.zzc.zza();
            String strValueOf = String.valueOf(str);
            if (!strZza.startsWith(strValueOf.length() != 0 ? ",".concat(strValueOf) : new String(","))) {
                return false;
            }
            String strValueOf2 = String.valueOf(str);
            this.zzc.zza(strZza.substring((strValueOf2.length() != 0 ? ",".concat(strValueOf2) : new String(",")).length()));
            return true;
        }
    }

    @WorkerThread
    private static boolean zza(FirebaseInstanceId firebaseInstanceId, String str) throws IOException {
        String[] strArrSplit = str.split("!");
        if (strArrSplit.length == 2) {
            String str2 = strArrSplit[0];
            String str3 = strArrSplit[1];
            byte b = -1;
            try {
                int iHashCode = str2.hashCode();
                if (iHashCode != 83) {
                    if (iHashCode == 85 && str2.equals("U")) {
                        b = 1;
                    }
                } else if (str2.equals("S")) {
                    b = 0;
                }
                if (b == 0) {
                    firebaseInstanceId.zzb(str3);
                    if (FirebaseInstanceId.zzd()) {
                        Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    }
                } else if (b == 1) {
                    firebaseInstanceId.zzc(str3);
                    if (FirebaseInstanceId.zzd()) {
                        Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    }
                }
            } catch (IOException e) {
                if ("SERVICE_NOT_AVAILABLE".equals(e.getMessage()) || "INTERNAL_SERVER_ERROR".equals(e.getMessage())) {
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(message).length() + 53);
                    sb.append("Topic operation failed: ");
                    sb.append(message);
                    sb.append(". Will retry Topic operation.");
                    Log.e("FirebaseInstanceId", sb.toString());
                    return false;
                }
                if (e.getMessage() == null) {
                    Log.e("FirebaseInstanceId", "Topic operation failed without exception message. Will retry Topic operation.");
                    return false;
                }
                throw e;
            }
        }
        return true;
    }
}
