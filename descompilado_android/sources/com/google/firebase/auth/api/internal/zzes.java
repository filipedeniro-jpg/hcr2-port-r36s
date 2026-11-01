package com.google.firebase.auth.api.internal;

import android.app.Activity;
import androidx.annotation.MainThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
abstract class zzes<ResultT, CallbackT> implements zzap<zzdu, ResultT> {
    private Activity zza;
    protected final int zzb;
    protected FirebaseApp zzd;
    protected FirebaseUser zze;
    protected CallbackT zzf;
    protected com.google.firebase.auth.internal.zzae zzg;
    protected zzeq<ResultT> zzh;
    protected Executor zzj;
    protected com.google.android.gms.internal.firebase_auth.zzey zzk;
    protected com.google.android.gms.internal.firebase_auth.zzes zzl;
    protected com.google.android.gms.internal.firebase_auth.zzei zzm;
    protected zzff zzn;
    protected String zzo;
    protected String zzp;
    protected AuthCredential zzq;
    protected String zzr;
    protected String zzs;
    protected com.google.android.gms.internal.firebase_auth.zzed zzt;
    protected boolean zzu;
    protected boolean zzv;

    @VisibleForTesting
    boolean zzw;
    private boolean zzx;

    @VisibleForTesting
    private ResultT zzy;

    @VisibleForTesting
    private Status zzz;

    @VisibleForTesting
    final zzeu zzc = new zzeu(this);
    protected final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zzi = new ArrayList();

    public zzes(int i) {
        this.zzb = i;
    }

    public abstract void zze();

    /* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
    static class zza extends LifecycleCallback {
        private final List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> zza;

        public static void zza(Activity activity, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            LifecycleFragment fragment = getFragment(activity);
            if (((zza) fragment.getCallbackOrNull("PhoneAuthActivityStopCallback", zza.class)) == null) {
                new zza(fragment, list);
            }
        }

        private zza(LifecycleFragment lifecycleFragment, List<PhoneAuthProvider.OnVerificationStateChangedCallbacks> list) {
            super(lifecycleFragment);
            this.mLifecycleFragment.addCallback("PhoneAuthActivityStopCallback", this);
            this.zza = list;
        }

        @Override // com.google.android.gms.common.api.internal.LifecycleCallback
        @MainThread
        public void onStop() {
            synchronized (this.zza) {
                this.zza.clear();
            }
        }
    }

    public final zzes<ResultT, CallbackT> zza(FirebaseApp firebaseApp) {
        this.zzd = (FirebaseApp) Preconditions.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzes<ResultT, CallbackT> zza(FirebaseUser firebaseUser) {
        this.zze = (FirebaseUser) Preconditions.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }

    public final zzes<ResultT, CallbackT> zza(CallbackT callbackt) {
        this.zzf = (CallbackT) Preconditions.checkNotNull(callbackt, "external callback cannot be null");
        return this;
    }

    public final zzes<ResultT, CallbackT> zza(com.google.firebase.auth.internal.zzae zzaeVar) {
        this.zzg = (com.google.firebase.auth.internal.zzae) Preconditions.checkNotNull(zzaeVar, "external failure callback cannot be null");
        return this;
    }

    public final zzes<ResultT, CallbackT> zza(PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        synchronized (this.zzi) {
            this.zzi.add((PhoneAuthProvider.OnVerificationStateChangedCallbacks) Preconditions.checkNotNull(onVerificationStateChangedCallbacks));
        }
        this.zza = activity;
        if (this.zza != null) {
            zza.zza(activity, this.zzi);
        }
        this.zzj = (Executor) Preconditions.checkNotNull(executor);
        return this;
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final zzap<zzdu, ResultT> zzc() {
        this.zzu = true;
        return this;
    }

    @Override // com.google.firebase.auth.api.internal.zzap
    public final zzap<zzdu, ResultT> zzd() {
        this.zzv = true;
        return this;
    }

    public final void zzb(ResultT resultt) {
        this.zzx = true;
        this.zzw = true;
        this.zzy = resultt;
        this.zzh.zza(resultt, null);
    }

    public final void zza(Status status) {
        this.zzx = true;
        this.zzw = false;
        this.zzz = status;
        this.zzh.zza(null, status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzf() {
        zze();
        Preconditions.checkState(this.zzx, "no success or failure set on method implementation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(Status status) {
        com.google.firebase.auth.internal.zzae zzaeVar = this.zzg;
        if (zzaeVar != null) {
            zzaeVar.zza(status);
        }
    }

    static /* synthetic */ boolean zza(zzes zzesVar, boolean z) {
        zzesVar.zzx = true;
        return true;
    }
}
