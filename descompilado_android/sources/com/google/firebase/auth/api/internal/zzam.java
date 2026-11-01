package com.google.firebase.auth.api.internal;

import androidx.annotation.GuardedBy;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.api.internal.zzao;
import java.util.concurrent.Future;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzam<T extends zzao> {
    private static Logger zza = new Logger("BiChannelGoogleApi", "FirebaseAuth: ");

    @GuardedBy("this")
    private zzal<T> zzb;

    abstract Future<zzal<T>> zza();

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zza(zzap<A, ResultT> zzapVar) {
        GoogleApi<T> googleApiZza = zza(zzapVar.zza());
        if (googleApiZza == null) {
            return zzb();
        }
        if (((zzao) googleApiZza.getApiOptions()).zza) {
            zzapVar.zzd();
        }
        return (Task<ResultT>) googleApiZza.doRead(zzapVar.zzb());
    }

    public final <ResultT, A extends Api.AnyClient> Task<ResultT> zzb(zzap<A, ResultT> zzapVar) {
        GoogleApi<T> googleApiZza = zza(zzapVar.zza());
        if (googleApiZza == null) {
            return zzb();
        }
        if (((zzao) googleApiZza.getApiOptions()).zza) {
            zzapVar.zzd();
        }
        return (Task<ResultT>) googleApiZza.doWrite(zzapVar.zzb());
    }

    private static <ResultT> Task<ResultT> zzb() {
        return Tasks.forException(zzdw.zza(new Status(FirebaseError.ERROR_INTERNAL_ERROR, "Unable to connect to GoogleApi instance - Google Play Services may be unavailable")));
    }

    private final GoogleApi<T> zza(String str) {
        zzal<T> zzalVarZzc = zzc();
        if (zzalVarZzc.zzc.zza(str)) {
            Logger logger = zza;
            String strValueOf = String.valueOf(zzalVarZzc.zzb);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 43);
            sb.append("getGoogleApiForMethod() returned Fallback: ");
            sb.append(strValueOf);
            logger.w(sb.toString(), new Object[0]);
            return (GoogleApi<T>) zzalVarZzc.zzb;
        }
        Logger logger2 = zza;
        String strValueOf2 = String.valueOf(zzalVarZzc.zza);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 38);
        sb2.append("getGoogleApiForMethod() returned Gms: ");
        sb2.append(strValueOf2);
        logger2.w(sb2.toString(), new Object[0]);
        return (GoogleApi<T>) zzalVarZzc.zza;
    }

    private final zzal<T> zzc() {
        zzal<T> zzalVar;
        synchronized (this) {
            if (this.zzb == null) {
                try {
                    this.zzb = zza().get();
                } catch (Exception e) {
                    String strValueOf = String.valueOf(e.getMessage());
                    throw new RuntimeException(strValueOf.length() != 0 ? "There was an error while initializing the connection to Google Play Services: ".concat(strValueOf) : new String("There was an error while initializing the connection to Google Play Services: "));
                }
            }
            zzalVar = this.zzb;
        }
        return zzalVar;
    }
}
