package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public final class zzagt {
    private final Object lock;
    private int status;
    private final zzawv zzblh;
    private final String zzcze;
    private zzavb<zzagk> zzczf;
    private zzavb<zzagk> zzczg;

    @Nullable
    private zzaho zzczh;
    private final Context zzlk;

    public zzagt(Context context, zzawv zzawvVar, String str) {
        this.lock = new Object();
        this.status = 1;
        this.zzcze = str;
        this.zzlk = context.getApplicationContext();
        this.zzblh = zzawvVar;
        this.zzczf = new zzahh();
        this.zzczg = new zzahh();
    }

    public zzagt(Context context, zzawv zzawvVar, String str, zzavb<zzagk> zzavbVar, zzavb<zzagk> zzavbVar2) {
        this(context, zzawvVar, str);
        this.zzczf = zzavbVar;
        this.zzczg = zzavbVar2;
    }

    protected final zzaho zza(@Nullable final zzdf zzdfVar) {
        final zzaho zzahoVar = new zzaho(this.zzczg);
        zzawx.zzdwa.execute(new Runnable(this, zzdfVar, zzahoVar) { // from class: com.google.android.gms.internal.ads.zzagw
            private final zzagt zzczi;
            private final zzdf zzczj;
            private final zzaho zzczk;

            {
                this.zzczi = this;
                this.zzczj = zzdfVar;
                this.zzczk = zzahoVar;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzczi.zza(this.zzczj, this.zzczk);
            }
        });
        zzahoVar.zza(new zzahg(this, zzahoVar), new zzahf(this, zzahoVar));
        return zzahoVar;
    }

    public final zzahk zzb(@Nullable zzdf zzdfVar) {
        synchronized (this.lock) {
            synchronized (this.lock) {
                if (this.zzczh != null && this.status == 0) {
                    if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcgf)).booleanValue()) {
                        this.zzczh.zza(new zzaxj(this) { // from class: com.google.android.gms.internal.ads.zzagv
                            private final zzagt zzczi;

                            {
                                this.zzczi = this;
                            }

                            @Override // com.google.android.gms.internal.ads.zzaxj
                            public final void zzh(Object obj) {
                                this.zzczi.zza((zzagk) obj);
                            }
                        }, zzagy.zzczn);
                    }
                }
            }
            if (this.zzczh != null && this.zzczh.getStatus() != -1) {
                if (this.status == 0) {
                    return this.zzczh.zzrc();
                }
                if (this.status == 1) {
                    this.status = 2;
                    zza((zzdf) null);
                    return this.zzczh.zzrc();
                }
                if (this.status == 2) {
                    return this.zzczh.zzrc();
                }
                return this.zzczh.zzrc();
            }
            this.status = 2;
            this.zzczh = zza((zzdf) null);
            return this.zzczh.zzrc();
        }
    }

    final /* synthetic */ void zza(zzagk zzagkVar) {
        if (zzagkVar.isDestroyed()) {
            this.status = 1;
        }
    }

    final /* synthetic */ void zza(zzdf zzdfVar, final zzaho zzahoVar) {
        final zzagk zzagmVar;
        try {
            Context context = this.zzlk;
            zzawv zzawvVar = this.zzblh;
            if (((Boolean) zzuo.zzoj().zzd(zzyt.zzcjs)).booleanValue()) {
                zzagmVar = new zzafw(context, zzawvVar);
            } else {
                zzagmVar = new zzagm(context, zzawvVar, zzdfVar, null);
            }
            zzagmVar.zza(new zzagj(this, zzahoVar, zzagmVar) { // from class: com.google.android.gms.internal.ads.zzagx
                private final zzagt zzczi;
                private final zzaho zzczl;
                private final zzagk zzczm;

                {
                    this.zzczi = this;
                    this.zzczl = zzahoVar;
                    this.zzczm = zzagmVar;
                }

                @Override // com.google.android.gms.internal.ads.zzagj
                public final void zzra() {
                    final zzagt zzagtVar = this.zzczi;
                    final zzaho zzahoVar2 = this.zzczl;
                    final zzagk zzagkVar = this.zzczm;
                    zzatv.zzdsk.postDelayed(new Runnable(zzagtVar, zzahoVar2, zzagkVar) { // from class: com.google.android.gms.internal.ads.zzaha
                        private final zzagt zzczi;
                        private final zzaho zzczl;
                        private final zzagk zzczm;

                        {
                            this.zzczi = zzagtVar;
                            this.zzczl = zzahoVar2;
                            this.zzczm = zzagkVar;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            this.zzczi.zza(this.zzczl, this.zzczm);
                        }
                    }, zzahi.zzczw);
                }
            });
            zzagmVar.zza("/jsLoaded", new zzahc(this, zzahoVar, zzagmVar));
            zzavx zzavxVar = new zzavx();
            zzahb zzahbVar = new zzahb(this, zzdfVar, zzagmVar, zzavxVar);
            zzavxVar.set(zzahbVar);
            zzagmVar.zza("/requestReload", zzahbVar);
            if (this.zzcze.endsWith(".js")) {
                zzagmVar.zzcq(this.zzcze);
            } else if (this.zzcze.startsWith("<html>")) {
                zzagmVar.zzcr(this.zzcze);
            } else {
                zzagmVar.zzcs(this.zzcze);
            }
            zzatv.zzdsk.postDelayed(new zzahe(this, zzahoVar, zzagmVar), zzahi.zzczv);
        } catch (Throwable th) {
            zzatm.zzc("Error creating webview.", th);
            com.google.android.gms.ads.internal.zzp.zzkc().zza(th, "SdkJavascriptFactory.loadJavascriptEngine");
            zzahoVar.reject();
        }
    }

    final /* synthetic */ void zza(zzaho zzahoVar, zzagk zzagkVar) {
        synchronized (this.lock) {
            if (zzahoVar.getStatus() != -1 && zzahoVar.getStatus() != 1) {
                zzahoVar.reject();
                zzdcs zzdcsVar = zzawx.zzdwa;
                zzagkVar.getClass();
                zzdcsVar.execute(zzagz.zzb(zzagkVar));
                zzatm.zzdy("Could not receive loaded message in a timely manner. Rejecting.");
            }
        }
    }
}
