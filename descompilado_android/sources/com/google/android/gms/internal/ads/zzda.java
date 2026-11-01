package com.google.android.gms.internal.ads;

import android.os.Build;
import android.os.ConditionVariable;
import androidx.annotation.VisibleForTesting;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzda {
    private static final ConditionVariable zzvp = new ConditionVariable();

    @VisibleForTesting
    protected static volatile zzse zzvq = null;
    private static volatile Random zzvs = null;
    private zzdx zzvo;

    @VisibleForTesting
    protected volatile Boolean zzvr;

    public zzda(zzdx zzdxVar) {
        this.zzvo = zzdxVar;
        zzdxVar.zzcd().execute(new zzcz(this));
    }

    public final void zza(int i, int i2, long j) {
        zza(i, i2, j, null, null);
    }

    public final void zza(int i, int i2, long j, String str) {
        zza(i, -1, j, str, null);
    }

    public final void zza(int i, int i2, long j, String str, Exception exc) {
        try {
            zzvp.block();
            if (!this.zzvr.booleanValue() || zzvq == null) {
                return;
            }
            zzbi.zza.C0040zza c0040zzaZzc = zzbi.zza.zzr().zzi(this.zzvo.zzlk.getPackageName()).zzc(j);
            if (str != null) {
                c0040zzaZzc.zzl(str);
            }
            if (exc != null) {
                StringWriter stringWriter = new StringWriter();
                zzdof.zza(exc, new PrintWriter(stringWriter));
                c0040zzaZzc.zzj(stringWriter.toString()).zzk(exc.getClass().getName());
            }
            zzsi zzsiVarZzf = zzvq.zzf(((zzbi.zza) ((zzdqd) c0040zzaZzc.zzazm())).toByteArray());
            zzsiVarZzf.zzbr(i);
            if (i2 != -1) {
                zzsiVarZzf.zzbq(i2);
            }
            zzsiVarZzf.zzdf();
        } catch (Exception unused) {
        }
    }

    public static int zzbz() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                return ThreadLocalRandom.current().nextInt();
            }
            return zzca().nextInt();
        } catch (RuntimeException unused) {
            return zzca().nextInt();
        }
    }

    private static Random zzca() {
        if (zzvs == null) {
            synchronized (zzda.class) {
                if (zzvs == null) {
                    zzvs = new Random();
                }
            }
        }
        return zzvs;
    }
}
