package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.Uri;
import com.appsflyer.share.Constants;
import com.google.android.gms.common.util.Clock;
import com.my.target.bj;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzbas extends zzbah implements zzazu {
    private String zzdxy;
    private boolean zzedh;
    private zzazm zzedk;
    private Exception zzedl;
    private boolean zzedm;

    public zzbas(zzayt zzaytVar, zzayq zzayqVar) {
        super(zzaytVar);
        this.zzedk = new zzazm(zzaytVar.getContext(), zzayqVar);
        this.zzedk.zza(this);
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzcy(int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzm(int i, int i2) {
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzb(final boolean z, final long j) {
        final zzayt zzaytVar = this.zzecy.get();
        if (zzaytVar != null) {
            zzawx.zzdwa.execute(new Runnable(zzaytVar, z, j) { // from class: com.google.android.gms.internal.ads.zzbav
                private final boolean zzdye;
                private final long zzebf;
                private final zzayt zzeds;

                {
                    this.zzeds = zzaytVar;
                    this.zzdye = z;
                    this.zzebf = j;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzeds.zza(this.zzdye, this.zzebf);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zza(String str, Exception exc) {
        String str2 = (String) zzuo.zzoj().zzd(zzyt.zzcgx);
        if (str2 != null) {
            List listAsList = Arrays.asList(str2.split(","));
            if (listAsList.contains(bj.gK) || listAsList.contains(exc.getClass().getCanonicalName())) {
                return;
            }
        }
        this.zzedl = exc;
        zzatm.zzd("Precache error", exc);
        zzfg(str);
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void zzcu(int i) {
        this.zzedk.zzyn().zzdb(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void zzct(int i) {
        this.zzedk.zzyn().zzda(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void zzcv(int i) {
        this.zzedk.zzyn().zzcv(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void zzcw(int i) {
        this.zzedk.zzyn().zzcw(i);
    }

    @Override // com.google.android.gms.internal.ads.zzbah, com.google.android.gms.common.api.Releasable
    public final void release() {
        zzazm zzazmVar = this.zzedk;
        if (zzazmVar != null) {
            zzazmVar.zza((zzazu) null);
            this.zzedk.release();
        }
        super.release();
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    protected final String zzfe(String str) {
        String strValueOf = String.valueOf(super.zzfe(str));
        return strValueOf.length() != 0 ? "cache:".concat(strValueOf) : new String("cache:");
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final boolean zzfd(String str) {
        return zze(str, new String[]{str});
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v7 */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r14v9 */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r20v1 */
    /* JADX WARN: Type inference failed for: r20v2 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r33v0, types: [com.google.android.gms.internal.ads.zzbah, com.google.android.gms.internal.ads.zzbas, java.lang.Object] */
    @Override // com.google.android.gms.internal.ads.zzbah
    public final boolean zze(String str, String[] strArr) throws Throwable {
        ?? r20;
        long j;
        this.zzdxy = str;
        String strZzfe = zzfe(str);
        ?? r14 = "error";
        try {
            Uri[] uriArr = new Uri[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                try {
                    uriArr[i] = Uri.parse(strArr[i]);
                } catch (Exception e) {
                    e = e;
                    String message = e.getMessage();
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 34 + String.valueOf(message).length());
                    sb.append("Failed to preload url ");
                    sb.append(str);
                    sb.append(" Exception: ");
                    sb.append(message);
                    zzatm.zzeu(sb.toString());
                    release();
                    zza(str, strZzfe, r14, zzb((String) r14, e));
                    return false;
                }
            }
            this.zzedk.zza(uriArr, this.zzdur);
            zzayt zzaytVar = this.zzecy.get();
            if (zzaytVar != null) {
                zzaytVar.zza(strZzfe, (zzbah) this);
            }
            Clock clockZzkf = com.google.android.gms.ads.internal.zzp.zzkf();
            long jCurrentTimeMillis = clockZzkf.currentTimeMillis();
            long jLongValue = ((Long) zzuo.zzoj().zzd(zzyt.zzche)).longValue();
            long jLongValue2 = ((Long) zzuo.zzoj().zzd(zzyt.zzchd)).longValue() * 1000;
            long jIntValue = ((Integer) zzuo.zzoj().zzd(zzyt.zzchc)).intValue();
            long j2 = -1;
            r14 = r14;
            while (true) {
                try {
                    synchronized (this) {
                        try {
                            if (clockZzkf.currentTimeMillis() - jCurrentTimeMillis > jLongValue2) {
                                long j3 = jLongValue2;
                                StringBuilder sb2 = new StringBuilder(47);
                                sb2.append("Timeout reached. Limit: ");
                                sb2.append(j3);
                                sb2.append(" ms");
                                throw new IOException(sb2.toString());
                            }
                            if (this.zzedh) {
                                if (this.zzedl != null) {
                                    throw this.zzedl;
                                }
                                throw new IOException("Abort requested before buffering finished. ");
                            }
                            if (!this.zzedm) {
                                zzge zzgeVarZzyk = this.zzedk.zzyk();
                                if (zzgeVarZzyk == null) {
                                    throw new IOException("ExoPlayer was released during preloading.");
                                }
                                r20 = r14;
                                try {
                                    long duration = zzgeVarZzyk.getDuration();
                                    if (duration > 0) {
                                        long bufferedPosition = zzgeVarZzyk.getBufferedPosition();
                                        if (bufferedPosition != j2) {
                                            zza(str, strZzfe, bufferedPosition, duration, bufferedPosition > 0, zzazm.zzyl(), zzazm.zzym());
                                            j2 = bufferedPosition;
                                        }
                                        if (bufferedPosition >= duration) {
                                            zzb(str, strZzfe, duration);
                                        } else if (this.zzedk.getBytesTransferred() < jIntValue || bufferedPosition <= 0) {
                                            j = j2;
                                        }
                                    } else {
                                        jIntValue = jIntValue;
                                        jLongValue2 = jLongValue2;
                                        j = j2;
                                    }
                                    try {
                                        try {
                                            wait(jLongValue);
                                        } catch (Throwable th) {
                                            th = th;
                                            r14 = jLongValue;
                                            throw th;
                                        }
                                    } catch (InterruptedException unused) {
                                        throw new IOException("Wait interrupted.");
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    r14 = r20;
                                }
                            }
                            return true;
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    }
                    return true;
                    jLongValue = jLongValue;
                    j2 = j;
                    r14 = r20;
                    jIntValue = jIntValue;
                    jLongValue2 = jLongValue2;
                } catch (Throwable th4) {
                    th = th4;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzbah
    public final void abort() {
        zzfg(null);
    }

    private final void zzfg(String str) {
        synchronized (this) {
            this.zzedh = true;
            notify();
            release();
        }
        String str2 = this.zzdxy;
        if (str2 != null) {
            String strZzfe = zzfe(str2);
            Exception exc = this.zzedl;
            if (exc != null) {
                zza(this.zzdxy, strZzfe, "badUrl", zzb(str, exc));
            } else {
                zza(this.zzdxy, strZzfe, "externalAbort", "Programmatic precache abort.");
            }
        }
    }

    public final zzazm zzyp() {
        synchronized (this) {
            this.zzedm = true;
            notify();
        }
        this.zzedk.zza((zzazu) null);
        zzazm zzazmVar = this.zzedk;
        this.zzedk = null;
        return zzazmVar;
    }

    private static String zzb(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(canonicalName).length() + String.valueOf(message).length());
        sb.append(str);
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(canonicalName);
        sb.append(":");
        sb.append(message);
        return sb.toString();
    }
}
