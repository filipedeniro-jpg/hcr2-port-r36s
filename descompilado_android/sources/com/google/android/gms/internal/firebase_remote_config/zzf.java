package com.google.android.gms.internal.firebase_remote_config;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public class zzf<T> extends zzby {
    private final zzd zzo;
    private final String zzp;
    private final String zzq;
    private final zzs zzr;
    private zzw zzt;
    private String zzv;
    private Class<T> zzw;
    private zzw zzs = new zzw();
    private int zzu = -1;

    protected zzf(zzd zzdVar, String str, String str2, zzs zzsVar, Class<T> cls) {
        this.zzw = (Class) zzdt.checkNotNull(cls);
        this.zzo = (zzd) zzdt.checkNotNull(zzdVar);
        this.zzp = (String) zzdt.checkNotNull(str);
        this.zzq = (String) zzdt.checkNotNull(str2);
        this.zzr = zzsVar;
        this.zzs.zzu("Google-API-Java-Client");
        this.zzs.zzb("X-Goog-Api-Client", zzg.zzj().zzf(zzdVar.getClass().getSimpleName()));
    }

    public zzd zzf() {
        return this.zzo;
    }

    public final zzw zzg() {
        return this.zzs;
    }

    public final zzw zzh() {
        return this.zzt;
    }

    protected IOException zza(zzac zzacVar) {
        return new zzaf(zzacVar);
    }

    public final T zzi() throws IOException {
        zzab zzabVarZza = zzf().zzd().zza(this.zzp, new zzt(zzal.zza(this.zzo.zzc(), this.zzq, (Object) this, true)), this.zzr);
        new zza().zzb(zzabVarZza);
        zzabVarZza.zza(zzf().zze());
        if (this.zzr == null && (this.zzp.equals(HttpRequest.METHOD_POST) || this.zzp.equals(HttpRequest.METHOD_PUT) || this.zzp.equals("PATCH"))) {
            zzabVarZza.zza(new zzo());
        }
        zzabVarZza.zzx().putAll(this.zzs);
        zzabVarZza.zza(new zzr());
        zzabVarZza.zza(new zzh(this, zzabVarZza.zzz(), zzabVarZza));
        zzac zzacVarZzac = zzabVarZza.zzac();
        this.zzt = zzacVarZzac.zzx();
        this.zzu = zzacVarZzac.getStatusCode();
        this.zzv = zzacVarZzac.getStatusMessage();
        return (T) zzacVarZzac.zza(this.zzw);
    }

    @Override // com.google.android.gms.internal.firebase_remote_config.zzby
    /* JADX INFO: renamed from: zzc, reason: merged with bridge method [inline-methods] */
    public zzf<T> zzb(String str, Object obj) {
        return (zzf) super.zzb(str, obj);
    }
}
