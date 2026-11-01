package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public abstract class zzq<T> implements Comparable<zzq<T>> {
    private final Object mLock;
    private final zzag.zza zzae;
    private final int zzaf;
    private final String zzag;
    private final int zzah;

    @Nullable
    @GuardedBy("mLock")
    private zzy zzai;
    private Integer zzaj;
    private zzu zzak;
    private boolean zzal;

    @GuardedBy("mLock")
    private boolean zzam;

    @GuardedBy("mLock")
    private boolean zzan;
    private boolean zzao;
    private zzad zzap;
    private zzd zzaq;

    @GuardedBy("mLock")
    private zzs zzar;

    public zzq(int i, String str, @Nullable zzy zzyVar) {
        Uri uri;
        String host;
        this.zzae = zzag.zza.zzbl ? new zzag.zza() : null;
        this.mLock = new Object();
        this.zzal = true;
        int iHashCode = 0;
        this.zzam = false;
        this.zzan = false;
        this.zzao = false;
        this.zzaq = null;
        this.zzaf = i;
        this.zzag = str;
        this.zzai = zzyVar;
        this.zzap = new zzg();
        if (!TextUtils.isEmpty(str) && (uri = Uri.parse(str)) != null && (host = uri.getHost()) != null) {
            iHashCode = host.hashCode();
        }
        this.zzah = iHashCode;
    }

    protected abstract zzz<T> zza(zzo zzoVar);

    protected abstract void zza(T t);

    public byte[] zzf() throws zzb {
        return null;
    }

    public final int getMethod() {
        return this.zzaf;
    }

    public final int zzc() {
        return this.zzah;
    }

    public final void zzb(String str) {
        if (zzag.zza.zzbl) {
            this.zzae.zza(str, Thread.currentThread().getId());
        }
    }

    final void zzc(String str) {
        zzu zzuVar = this.zzak;
        if (zzuVar != null) {
            zzuVar.zzf(this);
        }
        if (zzag.zza.zzbl) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzt(this, str, id));
            } else {
                this.zzae.zza(str, id);
                this.zzae.zzc(toString());
            }
        }
    }

    final void zza(int i) {
        zzu zzuVar = this.zzak;
        if (zzuVar != null) {
            zzuVar.zza(this, i);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzq<?> zza(zzu zzuVar) {
        this.zzak = zzuVar;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzq<?> zzb(int i) {
        this.zzaj = Integer.valueOf(i);
        return this;
    }

    public final String getUrl() {
        return this.zzag;
    }

    public final String zzd() {
        String str = this.zzag;
        int i = this.zzaf;
        if (i == 0 || i == -1) {
            return str;
        }
        String string = Integer.toString(i);
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 1 + String.valueOf(str).length());
        sb.append(string);
        sb.append('-');
        sb.append(str);
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final zzq<?> zza(zzd zzdVar) {
        this.zzaq = zzdVar;
        return this;
    }

    public final zzd zze() {
        return this.zzaq;
    }

    public final boolean isCanceled() {
        synchronized (this.mLock) {
        }
        return false;
    }

    public Map<String, String> getHeaders() throws zzb {
        return Collections.emptyMap();
    }

    public final boolean zzg() {
        return this.zzal;
    }

    public final int zzh() {
        return this.zzap.zza();
    }

    public final zzad zzi() {
        return this.zzap;
    }

    public final void zzj() {
        synchronized (this.mLock) {
            this.zzan = true;
        }
    }

    public final boolean zzk() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzan;
        }
        return z;
    }

    public final void zzb(zzae zzaeVar) {
        zzy zzyVar;
        synchronized (this.mLock) {
            zzyVar = this.zzai;
        }
        if (zzyVar != null) {
            zzyVar.zzc(zzaeVar);
        }
    }

    final void zza(zzs zzsVar) {
        synchronized (this.mLock) {
            this.zzar = zzsVar;
        }
    }

    final void zza(zzz<?> zzzVar) {
        zzs zzsVar;
        synchronized (this.mLock) {
            zzsVar = this.zzar;
        }
        if (zzsVar != null) {
            zzsVar.zza(this, zzzVar);
        }
    }

    final void zzl() {
        zzs zzsVar;
        synchronized (this.mLock) {
            zzsVar = this.zzar;
        }
        if (zzsVar != null) {
            zzsVar.zza(this);
        }
    }

    public String toString() {
        String strValueOf = String.valueOf(Integer.toHexString(this.zzah));
        String strConcat = strValueOf.length() != 0 ? "0x".concat(strValueOf) : new String("0x");
        isCanceled();
        String str = this.zzag;
        String strValueOf2 = String.valueOf(zzv.NORMAL);
        String strValueOf3 = String.valueOf(this.zzaj);
        StringBuilder sb = new StringBuilder("[ ] ".length() + 3 + String.valueOf(str).length() + String.valueOf(strConcat).length() + String.valueOf(strValueOf2).length() + String.valueOf(strValueOf3).length());
        sb.append("[ ] ");
        sb.append(str);
        sb.append(" ");
        sb.append(strConcat);
        sb.append(" ");
        sb.append(strValueOf2);
        sb.append(" ");
        sb.append(strValueOf3);
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Object obj) {
        zzq zzqVar = (zzq) obj;
        zzv zzvVar = zzv.NORMAL;
        zzv zzvVar2 = zzv.NORMAL;
        return zzvVar == zzvVar2 ? this.zzaj.intValue() - zzqVar.zzaj.intValue() : zzvVar2.ordinal() - zzvVar.ordinal();
    }
}
