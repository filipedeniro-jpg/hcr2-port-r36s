package com.google.firebase.auth.api.internal;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzgd;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzau extends zzam {
    private final Context zza;
    private final zzej zzb;
    private final Future<zzal<zzej>> zzc = zza();

    zzau(Context context, zzej zzejVar) {
        this.zza = context;
        this.zzb = zzejVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzam
    final Future<zzal<zzej>> zza() {
        Future<zzal<zzej>> future = this.zzc;
        if (future != null) {
            return future;
        }
        return com.google.android.gms.internal.firebase_auth.zzf.zza().zza(com.google.android.gms.internal.firebase_auth.zzk.zza).submit(new zzds(this.zzb, this.zza));
    }

    public final Task<GetTokenResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzbi zzbiVar = (zzbi) new zzbi(str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zza(zzbiVar), zzbiVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, @Nullable String str2, com.google.firebase.auth.internal.zza zzaVar) {
        zzcu zzcuVar = (zzcu) new zzcu(str, str2).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzcuVar), zzcuVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, AuthCredential authCredential, @Nullable String str, com.google.firebase.auth.internal.zza zzaVar) {
        zzcs zzcsVar = (zzcs) new zzcs(authCredential, str).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzcsVar), zzcsVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzbs zzbsVar = (zzbs) new zzbs(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzbsVar), zzbsVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, @Nullable String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzbu zzbuVar = (zzbu) new zzbu(authCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzbuVar), zzbuVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, com.google.firebase.auth.internal.zza zzaVar, @Nullable String str) {
        zzcq zzcqVar = (zzcq) new zzcq(str).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzcqVar), zzcqVar);
    }

    public final void zza(FirebaseApp firebaseApp, com.google.android.gms.internal.firebase_auth.zzfk zzfkVar, PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, Executor executor) {
        zzdq zzdqVar = (zzdq) new zzdq(zzfkVar).zza(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor);
        zza(zzb(zzdqVar), zzdqVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, UserProfileChangeRequest userProfileChangeRequest, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzdm zzdmVar = (zzdm) new zzdm(userProfileChangeRequest).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzdmVar), zzdmVar);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzdg zzdgVar = (zzdg) new zzdg(str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzdgVar), zzdgVar);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzdi zzdiVar = (zzdi) new zzdi(str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzdiVar), zzdiVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzdk zzdkVar = (zzdk) new zzdk(phoneAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzdkVar), zzdkVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, String str, String str2, String str3, com.google.firebase.auth.internal.zza zzaVar) {
        zzbc zzbcVar = (zzbc) new zzbc(str, str2, str3).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzbcVar), zzbcVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3, com.google.firebase.auth.internal.zza zzaVar) {
        zzcw zzcwVar = (zzcw) new zzcw(str, str2, str3).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzcwVar), zzcwVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, EmailAuthCredential emailAuthCredential, com.google.firebase.auth.internal.zza zzaVar) {
        zzcy zzcyVar = (zzcy) new zzcy(emailAuthCredential).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzcyVar), zzcyVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, @Nullable String str3, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzca zzcaVar = (zzca) new zzca(str, str2, str3).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzcaVar), zzcaVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, String str2, String str3, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzcc zzccVar = (zzcc) new zzcc(str, str2, str3).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzccVar), zzccVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzbw zzbwVar = (zzbw) new zzbw(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzbwVar), zzbwVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, EmailAuthCredential emailAuthCredential, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzby zzbyVar = (zzby) new zzby(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzbyVar), zzbyVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, PhoneAuthCredential phoneAuthCredential, @Nullable String str, com.google.firebase.auth.internal.zza zzaVar) {
        zzda zzdaVar = (zzda) new zzda(phoneAuthCredential, str).zza(firebaseApp).zza(zzaVar);
        return zza(zzb(zzdaVar), zzdaVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzce zzceVar = (zzce) new zzce(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzceVar), zzceVar);
    }

    public final Task<AuthResult> zzb(FirebaseApp firebaseApp, FirebaseUser firebaseUser, PhoneAuthCredential phoneAuthCredential, @Nullable String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzcg zzcgVar = (zzcg) new zzcg(phoneAuthCredential, str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzcgVar), zzcgVar);
    }

    public final Task<SignInMethodQueryResult> zza(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzbg zzbgVar = (zzbg) new zzbg(str, str2).zza(firebaseApp);
        return zza(zza(zzbgVar), zzbgVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2) {
        actionCodeSettings.zza(zzgd.PASSWORD_RESET);
        zzcm zzcmVar = (zzcm) new zzcm(str, actionCodeSettings, str2, "sendPasswordResetEmail").zza(firebaseApp);
        return zza(zzb(zzcmVar), zzcmVar);
    }

    public final Task<Void> zzb(FirebaseApp firebaseApp, String str, ActionCodeSettings actionCodeSettings, @Nullable String str2) {
        actionCodeSettings.zza(zzgd.EMAIL_SIGNIN);
        zzcm zzcmVar = (zzcm) new zzcm(str, actionCodeSettings, str2, "sendSignInLinkToEmail").zza(firebaseApp);
        return zza(zzb(zzcmVar), zzcmVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, @Nullable ActionCodeSettings actionCodeSettings, String str) {
        zzck zzckVar = (zzck) new zzck(str, actionCodeSettings).zza(firebaseApp);
        return zza(zzb(zzckVar), zzckVar);
    }

    public final Task<ActionCodeResult> zzb(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzay zzayVar = (zzay) new zzay(str, str2).zza(firebaseApp);
        return zza(zzb(zzayVar), zzayVar);
    }

    public final Task<Void> zzc(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzaw zzawVar = (zzaw) new zzaw(str, str2).zza(firebaseApp);
        return zza(zzb(zzawVar), zzawVar);
    }

    public final Task<String> zzd(FirebaseApp firebaseApp, String str, @Nullable String str2) {
        zzdo zzdoVar = (zzdo) new zzdo(str, str2).zza(firebaseApp);
        return zza(zzb(zzdoVar), zzdoVar);
    }

    public final Task<Void> zza(FirebaseApp firebaseApp, String str, String str2, @Nullable String str3) {
        zzba zzbaVar = (zzba) new zzba(str, str2, str3).zza(firebaseApp);
        return zza(zzb(zzbaVar), zzbaVar);
    }

    public final Task<AuthResult> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, AuthCredential authCredential, com.google.firebase.auth.internal.zzaz zzazVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzazVar);
        List<String> listZza = firebaseUser.zza();
        if (listZza != null && listZza.contains(authCredential.getProvider())) {
            return Tasks.forException(zzdw.zza(new Status(FirebaseError.ERROR_PROVIDER_ALREADY_LINKED)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            if (!emailAuthCredential.zzf()) {
                zzbk zzbkVar = (zzbk) new zzbk(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
                return zza(zzb(zzbkVar), zzbkVar);
            }
            zzbq zzbqVar = (zzbq) new zzbq(emailAuthCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
            return zza(zzb(zzbqVar), zzbqVar);
        }
        if (authCredential instanceof PhoneAuthCredential) {
            zzbo zzboVar = (zzbo) new zzbo((PhoneAuthCredential) authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
            return zza(zzb(zzboVar), zzboVar);
        }
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(authCredential);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzazVar);
        zzbm zzbmVar = (zzbm) new zzbm(authCredential).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzbmVar), zzbmVar);
    }

    public final Task<AuthResult> zzd(FirebaseApp firebaseApp, FirebaseUser firebaseUser, String str, com.google.firebase.auth.internal.zzaz zzazVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotEmpty(str);
        Preconditions.checkNotNull(firebaseUser);
        Preconditions.checkNotNull(zzazVar);
        List<String> listZza = firebaseUser.zza();
        if ((listZza != null && !listZza.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzdw.zza(new Status(FirebaseError.ERROR_NO_SUCH_PROVIDER, str)));
        }
        byte b = -1;
        if (str.hashCode() == 1216985755 && str.equals("password")) {
            b = 0;
        }
        if (b == 0) {
            zzdc zzdcVar = (zzdc) new zzdc().zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
            return zza(zzb(zzdcVar), zzdcVar);
        }
        zzde zzdeVar = (zzde) new zzde(str).zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zzb(zzdeVar), zzdeVar);
    }

    @NonNull
    public final Task<Void> zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser, com.google.firebase.auth.internal.zzaz zzazVar) {
        zzci zzciVar = (zzci) new zzci().zza(firebaseApp).zza(firebaseUser).zza(zzazVar).zza((com.google.firebase.auth.internal.zzae) zzazVar);
        return zza(zza(zzciVar), zzciVar);
    }

    @NonNull
    public final Task<Void> zza(FirebaseUser firebaseUser, com.google.firebase.auth.internal.zzab zzabVar) {
        zzbe zzbeVar = (zzbe) new zzbe().zza(firebaseUser).zza(zzabVar).zza((com.google.firebase.auth.internal.zzae) zzabVar);
        return zza(zzb(zzbeVar), zzbeVar);
    }

    @NonNull
    public final Task<Void> zza(String str) {
        zzco zzcoVar = new zzco(str);
        return zza(zzb(zzcoVar), zzcoVar);
    }

    @NonNull
    @VisibleForTesting
    static com.google.firebase.auth.internal.zzn zza(FirebaseApp firebaseApp, com.google.android.gms.internal.firebase_auth.zzes zzesVar) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(zzesVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new com.google.firebase.auth.internal.zzj(zzesVar, FirebaseAuthProvider.PROVIDER_ID));
        List<com.google.android.gms.internal.firebase_auth.zzfc> listZzj = zzesVar.zzj();
        if (listZzj != null && !listZzj.isEmpty()) {
            for (int i = 0; i < listZzj.size(); i++) {
                arrayList.add(new com.google.firebase.auth.internal.zzj(listZzj.get(i)));
            }
        }
        com.google.firebase.auth.internal.zzn zznVar = new com.google.firebase.auth.internal.zzn(firebaseApp, arrayList);
        zznVar.zza(new com.google.firebase.auth.internal.zzp(zzesVar.zzh(), zzesVar.zzg()));
        zznVar.zza(zzesVar.zzi());
        zznVar.zza(zzesVar.zzl());
        zznVar.zzb(com.google.firebase.auth.internal.zzar.zza(zzesVar.zzm()));
        return zznVar;
    }

    @NonNull
    @VisibleForTesting
    private final <ResultT> Task<ResultT> zza(Task<ResultT> task, zzap<zzdu, ResultT> zzapVar) {
        return (Task<ResultT>) task.continueWithTask(new zzat(this, zzapVar));
    }
}
