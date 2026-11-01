package com.google.firebase.auth.api.internal;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.PhoneAuthCredential;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@VisibleForTesting
final class zzeu extends zzec {
    final /* synthetic */ zzes zza;

    zzeu(zzes zzesVar) {
        this.zza = zzesVar;
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar) throws RemoteException {
        boolean z = this.zza.zzb == 1;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar = this.zza;
        zzesVar.zzk = zzeyVar;
        zzesVar.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzey zzeyVar, com.google.android.gms.internal.firebase_auth.zzes zzesVar) throws RemoteException {
        boolean z = this.zza.zzb == 2;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(37);
        sb.append("Unexpected response type: ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar2 = this.zza;
        zzesVar2.zzk = zzeyVar;
        zzesVar2.zzl = zzesVar;
        zzesVar2.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzei zzeiVar) throws RemoteException {
        boolean z = this.zza.zzb == 3;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar = this.zza;
        zzesVar.zzm = zzeiVar;
        zzesVar.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(@Nullable zzff zzffVar) throws RemoteException {
        boolean z = this.zza.zzb == 4;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar = this.zza;
        zzesVar.zzn = zzffVar;
        zzesVar.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void a_() throws RemoteException {
        boolean z = this.zza.zzb == 5;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzb() throws RemoteException {
        boolean z = this.zza.zzb == 6;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(String str) throws RemoteException {
        boolean z = this.zza.zzb == 7;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar = this.zza;
        zzesVar.zzo = str;
        zzesVar.zzf();
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzb(String str) throws RemoteException {
        boolean z = this.zza.zzb == 8;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzp = str;
        zza(new zzet(this, str));
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z = this.zza.zzb == 8;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes.zza(this.zza, true);
        this.zza.zzw = true;
        zza(new zzew(this, phoneAuthCredential));
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzc(String str) throws RemoteException {
        boolean z = this.zza.zzb == 8;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zzes zzesVar = this.zza;
        zzesVar.zzp = str;
        zzes.zza(zzesVar, true);
        this.zza.zzw = true;
        zza(new zzev(this, str));
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(Status status) throws RemoteException {
        String statusMessage = status.getStatusMessage();
        if (statusMessage != null) {
            if (statusMessage.contains("MISSING_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17081);
            } else if (statusMessage.contains("MISSING_MFA_ENROLLMENT_ID")) {
                status = new Status(17082);
            } else if (statusMessage.contains("INVALID_MFA_PENDING_CREDENTIAL")) {
                status = new Status(17083);
            } else if (statusMessage.contains("MFA_ENROLLMENT_NOT_FOUND")) {
                status = new Status(17084);
            } else if (statusMessage.contains("ADMIN_ONLY_OPERATION")) {
                status = new Status(17085);
            } else if (statusMessage.contains("UNVERIFIED_EMAIL")) {
                status = new Status(17086);
            } else if (statusMessage.contains("SECOND_FACTOR_EXISTS")) {
                status = new Status(17087);
            } else if (statusMessage.contains("SECOND_FACTOR_LIMIT_EXCEEDED")) {
                status = new Status(17088);
            } else if (statusMessage.contains("UNSUPPORTED_FIRST_FACTOR")) {
                status = new Status(17089);
            } else if (statusMessage.contains("EMAIL_CHANGE_NEEDS_VERIFICATION")) {
                status = new Status(17090);
            }
        }
        if (this.zza.zzb != 8) {
            this.zza.zzb(status);
            this.zza.zza(status);
        } else {
            zzes.zza(this.zza, true);
            this.zza.zzw = false;
            zza(new zzey(this, status));
        }
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        boolean z = this.zza.zzb == 2;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        zza(status, phoneAuthCredential, (String) null, (String) null);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzeb zzebVar) {
        zza(zzebVar.zza(), zzebVar.zzb(), zzebVar.zzc(), zzebVar.zzd());
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zza(com.google.android.gms.internal.firebase_auth.zzed zzedVar) {
        zzes zzesVar = this.zza;
        zzesVar.zzt = zzedVar;
        zzesVar.zza(com.google.firebase.auth.internal.zzy.zza("REQUIRES_SECOND_FACTOR_AUTH"));
    }

    private final void zza(Status status, AuthCredential authCredential, @Nullable String str, @Nullable String str2) {
        this.zza.zzb(status);
        zzes zzesVar = this.zza;
        zzesVar.zzq = authCredential;
        zzesVar.zzr = str;
        zzesVar.zzs = str2;
        if (zzesVar.zzg != null) {
            this.zza.zzg.zza(status);
        }
        this.zza.zza(status);
    }

    @Override // com.google.firebase.auth.api.internal.zzdz
    public final void zzc() throws RemoteException {
        boolean z = this.zza.zzb == 9;
        int i = this.zza.zzb;
        StringBuilder sb = new StringBuilder(36);
        sb.append("Unexpected response type ");
        sb.append(i);
        Preconditions.checkState(z, sb.toString());
        this.zza.zzf();
    }

    private final void zza(zzfa zzfaVar) {
        this.zza.zzj.execute(new zzex(this, zzfaVar));
    }
}
