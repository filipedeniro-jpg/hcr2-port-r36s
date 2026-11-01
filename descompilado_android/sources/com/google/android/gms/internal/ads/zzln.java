package com.google.android.gms.internal.ads;

import android.net.Uri;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzln implements zzno {
    private final Uri uri;
    private final zznc zzamq;
    private final zzlm zzazw;
    private final zznv zzazx;
    private final /* synthetic */ zzlg zzbaq;
    private volatile boolean zzbaw;
    private long zzbay;
    private final zzja zzbav = new zzja();
    private boolean zzbax = true;
    private long zzcb = -1;

    public zzln(zzlg zzlgVar, Uri uri, zznc zzncVar, zzlm zzlmVar, zznv zznvVar) {
        this.zzbaq = zzlgVar;
        this.uri = (Uri) zznt.checkNotNull(uri);
        this.zzamq = (zznc) zznt.checkNotNull(zzncVar);
        this.zzazw = (zzlm) zznt.checkNotNull(zzlmVar);
        this.zzazx = zznvVar;
    }

    public final void zze(long j, long j2) {
        this.zzbav.zzams = j;
        this.zzbay = j2;
        this.zzbax = true;
    }

    @Override // com.google.android.gms.internal.ads.zzno
    public final void cancelLoad() {
        this.zzbaw = true;
    }

    @Override // com.google.android.gms.internal.ads.zzno
    public final boolean zzhg() {
        return this.zzbaw;
    }

    @Override // com.google.android.gms.internal.ads.zzno
    public final void zzhh() throws Throwable {
        zziv zzivVar;
        int iZza = 0;
        while (iZza == 0 && !this.zzbaw) {
            try {
                long position = this.zzbav.zzams;
                this.zzcb = this.zzamq.zza(new zznh(this.uri, position, -1L, this.zzbaq.zzazt));
                if (this.zzcb != -1) {
                    this.zzcb += position;
                }
                zzivVar = new zziv(this.zzamq, position, this.zzcb);
                try {
                    zziu zziuVarZza = this.zzazw.zza(zzivVar, this.zzamq.getUri());
                    if (this.zzbax) {
                        zziuVarZza.zzc(position, this.zzbay);
                        this.zzbax = false;
                    }
                    while (iZza == 0 && !this.zzbaw) {
                        this.zzazx.block();
                        iZza = zziuVarZza.zza(zzivVar, this.zzbav);
                        if (zzivVar.getPosition() > this.zzbaq.zzazu + position) {
                            position = zzivVar.getPosition();
                            this.zzazx.zzid();
                            this.zzbaq.handler.post(this.zzbaq.zzazz);
                        }
                    }
                    if (iZza == 1) {
                        iZza = 0;
                    } else {
                        this.zzbav.zzams = zzivVar.getPosition();
                    }
                    zzoh.zza(this.zzamq);
                } catch (Throwable th) {
                    th = th;
                    if (iZza != 1 && zzivVar != null) {
                        this.zzbav.zzams = zzivVar.getPosition();
                    }
                    zzoh.zza(this.zzamq);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                zzivVar = null;
            }
        }
    }
}
