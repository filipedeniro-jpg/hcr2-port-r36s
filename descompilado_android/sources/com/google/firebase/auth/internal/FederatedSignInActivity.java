package com.google.firebase.auth.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.fragment.app.FragmentActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.internal.firebase_auth.zzfr;
import com.google.firebase.FirebaseError;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@KeepName
public class FederatedSignInActivity extends FragmentActivity {
    private static long zza;
    private static final zzam zzc = zzam.zza();
    private static Handler zzd;
    private static Runnable zze;
    private boolean zzb = false;

    @Override // androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String action = getIntent().getAction();
        if (!"com.google.firebase.auth.internal.SIGN_IN".equals(action) && !"com.google.firebase.auth.internal.LINK".equals(action) && !"com.google.firebase.auth.internal.REAUTHENTICATE".equals(action)) {
            String strValueOf = String.valueOf(action);
            Log.e("IdpSignInActivity", strValueOf.length() != 0 ? "Could not do operation - unknown action: ".concat(strValueOf) : new String("Could not do operation - unknown action: "));
            zza();
            return;
        }
        long jCurrentTimeMillis = DefaultClock.getInstance().currentTimeMillis();
        if (jCurrentTimeMillis - zza < 30000) {
            Log.e("IdpSignInActivity", "Could not start operation - already in progress");
            return;
        }
        zza = jCurrentTimeMillis;
        if (bundle != null) {
            this.zzb = bundle.getBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN");
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("com.google.firebase.auth.internal.KEY_STARTED_SIGN_IN", this.zzb);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x009f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:26:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:28:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:34:0x00df  */
    /* JADX WARN: Code duplicated, block: B:36:0x00ea  */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        boolean z = false;
        if ("com.google.firebase.auth.internal.WEB_SIGN_IN_FAILED".equals(intent.getAction())) {
            Log.e("IdpSignInActivity", "Web sign-in failed, finishing");
            if (zzay.zza(intent)) {
                zza(zzay.zzb(intent));
            } else {
                zza();
            }
        } else {
            if (intent.hasExtra("com.google.firebase.auth.internal.OPERATION") && intent.hasExtra("com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST")) {
                String stringExtra = intent.getStringExtra("com.google.firebase.auth.internal.OPERATION");
                if ("com.google.firebase.auth.internal.SIGN_IN".equals(stringExtra) || "com.google.firebase.auth.internal.LINK".equals(stringExtra) || "com.google.firebase.auth.internal.REAUTHENTICATE".equals(stringExtra)) {
                    zzfr zzfrVar = (zzfr) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST", zzfr.CREATOR);
                    String stringExtra2 = intent.getStringExtra("com.google.firebase.auth.internal.EXTRA_TENANT_ID");
                    zzfrVar.zzb(stringExtra2);
                    zza = 0L;
                    this.zzb = false;
                    Intent intent2 = new Intent();
                    SafeParcelableSerializer.serializeToIntentExtra(zzfrVar, intent2, "com.google.firebase.auth.internal.VERIFY_ASSERTION_REQUEST");
                    intent2.putExtra("com.google.firebase.auth.internal.OPERATION", stringExtra);
                    intent2.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
                    if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent2)) {
                        zzas.zza(getApplicationContext(), zzfrVar, stringExtra, stringExtra2);
                    } else {
                        zzc.zza(this);
                    }
                    finish();
                }
            }
            if (z) {
                return;
            }
            if (!this.zzb) {
                Intent intent3 = new Intent("com.google.firebase.auth.api.gms.ui.START_WEB_SIGN_IN");
                intent3.setPackage("com.google.android.gms");
                intent3.putExtras(getIntent().getExtras());
                intent3.putExtra("com.google.firebase.auth.internal.OPERATION", getIntent().getAction());
                try {
                    startActivityForResult(intent3, 40963);
                } catch (ActivityNotFoundException unused) {
                    Log.w("IdpSignInActivity", "Could not launch web sign-in Intent. Google Play service is unavailable");
                    zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR, "Could not launch web sign-in Intent. Google Play service is unavailable"));
                }
                this.zzb = true;
                return;
            }
            zze = new zzan(this);
            if (zzd == null) {
                zzd = new com.google.android.gms.internal.firebase_auth.zzj();
            }
            zzd.postDelayed(zze, 800L);
        }
        z = true;
        if (z) {
            return;
        }
        if (!this.zzb) {
            Intent intent4 = new Intent("com.google.firebase.auth.api.gms.ui.START_WEB_SIGN_IN");
            intent4.setPackage("com.google.android.gms");
            intent4.putExtras(getIntent().getExtras());
            intent4.putExtra("com.google.firebase.auth.internal.OPERATION", getIntent().getAction());
            startActivityForResult(intent4, 40963);
            this.zzb = true;
            return;
        }
        zze = new zzan(this);
        if (zzd == null) {
            zzd = new com.google.android.gms.internal.firebase_auth.zzj();
        }
        zzd.postDelayed(zze, 800L);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        Runnable runnable;
        super.onNewIntent(intent);
        Handler handler = zzd;
        if (handler != null && (runnable = zze) != null) {
            handler.removeCallbacks(runnable);
            zze = null;
        }
        setIntent(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza() {
        zza = 0L;
        this.zzb = false;
        Intent intent = new Intent();
        intent.putExtra("com.google.firebase.auth.internal.EXTRA_CANCELED", true);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzas.zza(this, zzy.zza("WEB_CONTEXT_CANCELED"));
        } else {
            zzc.zza(this);
        }
        finish();
    }

    private final void zza(Status status) {
        zza = 0L;
        this.zzb = false;
        Intent intent = new Intent();
        zzay.zza(intent, status);
        intent.setAction("com.google.firebase.auth.ACTION_RECEIVE_FIREBASE_AUTH_INTENT");
        if (!LocalBroadcastManager.getInstance(this).sendBroadcast(intent)) {
            zzas.zza(getApplicationContext(), status);
        } else {
            zzc.zza(this);
        }
        finish();
    }

    static /* synthetic */ Runnable zza(Runnable runnable) {
        zze = null;
        return null;
    }
}
