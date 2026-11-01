package com.google.firebase.auth.internal;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.api.internal.zzdw;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzas {

    @VisibleForTesting
    private static long zza = 3600000;
    private static final com.google.android.gms.internal.firebase_auth.zzaz<String> zzb = com.google.android.gms.internal.firebase_auth.zzaz.zza("firebaseAppName", "firebaseUserUid", "operation", "tenantId", "verifyAssertionRequest", "statusCode", "statusMessage", "timestamp");
    private static final zzas zzc = new zzas();
    private Task<AuthResult> zzd;
    private long zze = 0;

    private zzas() {
    }

    public static zzas zza() {
        return zzc;
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        editorEdit.putString("firebaseAppName", firebaseAuth.zzb().getName());
        editorEdit.commit();
    }

    public static void zza(Context context, FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(firebaseAuth);
        Preconditions.checkNotNull(firebaseUser);
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        editorEdit.putString("firebaseAppName", firebaseAuth.zzb().getName());
        editorEdit.putString("firebaseUserUid", firebaseUser.getUid());
        editorEdit.commit();
    }

    public static void zza(Context context, zzfr zzfrVar, String str, @Nullable String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        editorEdit.putString("verifyAssertionRequest", SafeParcelableSerializer.serializeToString(zzfrVar));
        editorEdit.putString("operation", str);
        editorEdit.putString("tenantId", str2);
        editorEdit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        editorEdit.commit();
    }

    public static void zza(Context context, Status status) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0).edit();
        editorEdit.putInt("statusCode", status.getStatusCode());
        editorEdit.putString("statusMessage", status.getStatusMessage());
        editorEdit.putLong("timestamp", DefaultClock.getInstance().currentTimeMillis());
        editorEdit.commit();
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0096  */
    public final void zza(FirebaseAuth firebaseAuth) {
        Preconditions.checkNotNull(firebaseAuth);
        byte b = 0;
        SharedPreferences sharedPreferences = firebaseAuth.zzb().getApplicationContext().getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0);
        if (firebaseAuth.zzb().getName().equals(sharedPreferences.getString("firebaseAppName", ""))) {
            if (sharedPreferences.contains("verifyAssertionRequest")) {
                zzfr zzfrVar = (zzfr) SafeParcelableSerializer.deserializeFromString(sharedPreferences.getString("verifyAssertionRequest", ""), zzfr.CREATOR);
                String string = sharedPreferences.getString("operation", "");
                String string2 = sharedPreferences.getString("tenantId", null);
                String string3 = sharedPreferences.getString("firebaseUserUid", "");
                this.zze = sharedPreferences.getLong("timestamp", 0L);
                if (string2 != null) {
                    firebaseAuth.zza(string2);
                    zzfrVar.zzb(string2);
                }
                int iHashCode = string.hashCode();
                if (iHashCode != -1843829902) {
                    if (iHashCode != -286760092) {
                        if (iHashCode != 1731327805 || !string.equals("com.google.firebase.auth.internal.SIGN_IN")) {
                            b = -1;
                        }
                    } else if (string.equals("com.google.firebase.auth.internal.LINK")) {
                        b = 1;
                    } else {
                        b = -1;
                    }
                } else if (string.equals("com.google.firebase.auth.internal.REAUTHENTICATE")) {
                    b = 2;
                } else {
                    b = -1;
                }
                if (b == 0) {
                    this.zzd = firebaseAuth.signInWithCredential(com.google.firebase.auth.zzg.zza(zzfrVar));
                } else if (b == 1) {
                    if (firebaseAuth.getCurrentUser().getUid().equals(string3)) {
                        this.zzd = firebaseAuth.getCurrentUser().linkWithCredential(com.google.firebase.auth.zzg.zza(zzfrVar));
                    } else {
                        this.zzd = null;
                    }
                } else if (b == 2 && firebaseAuth.getCurrentUser().getUid().equals(string3)) {
                    this.zzd = firebaseAuth.getCurrentUser().reauthenticateAndRetrieveData(com.google.firebase.auth.zzg.zza(zzfrVar));
                } else {
                    this.zzd = null;
                }
                zza(sharedPreferences);
                return;
            }
            if (sharedPreferences.contains("statusCode")) {
                Status status = new Status(sharedPreferences.getInt("statusCode", 17062), sharedPreferences.getString("statusMessage", ""));
                this.zze = sharedPreferences.getLong("timestamp", 0L);
                zza(sharedPreferences);
                this.zzd = Tasks.forException(zzdw.zza(status));
            }
        }
    }

    public final Task<AuthResult> zzb() {
        if (DefaultClock.getInstance().currentTimeMillis() - this.zze < zza) {
            return this.zzd;
        }
        return null;
    }

    public final void zza(Context context) {
        Preconditions.checkNotNull(context);
        zza(context.getSharedPreferences("com.google.firebase.auth.internal.ProcessDeathHelper", 0));
        this.zzd = null;
        this.zze = 0L;
    }

    private static void zza(SharedPreferences sharedPreferences) {
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        com.google.android.gms.internal.firebase_auth.zzaz<String> zzazVar = zzb;
        int size = zzazVar.size();
        int i = 0;
        while (i < size) {
            String str = zzazVar.get(i);
            i++;
            editorEdit.remove(str);
        }
        editorEdit.commit();
    }
}
