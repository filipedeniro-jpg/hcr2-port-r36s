package com.google.android.gms.internal.ads;

import android.content.Context;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
public abstract class zzbah implements Releasable {
    protected Context mContext;
    protected String zzdur;
    protected WeakReference<zzayt> zzecy;

    public zzbah(zzayt zzaytVar) {
        this.mContext = zzaytVar.getContext();
        this.zzdur = com.google.android.gms.ads.internal.zzp.zzjy().zzr(this.mContext, zzaytVar.zzxp().zzbnh);
        this.zzecy = new WeakReference<>(zzaytVar);
    }

    public abstract void abort();

    @Override // com.google.android.gms.common.api.Releasable
    public void release() {
    }

    protected void zzct(int i) {
    }

    protected void zzcu(int i) {
    }

    protected void zzcv(int i) {
    }

    protected void zzcw(int i) {
    }

    public abstract boolean zzfd(String str);

    public boolean zze(String str, String[] strArr) {
        return zzfd(str);
    }

    @VisibleForTesting
    public final void zza(String str, String str2, long j, long j2, boolean z, int i, int i2) {
        zzawe.zzzb.post(new zzbaj(this, str, str2, j, j2, z, i, i2));
    }

    @VisibleForTesting
    public final void zza(String str, String str2, int i, int i2, long j, long j2, boolean z, int i3, int i4) {
        zzawe.zzzb.post(new zzbai(this, str, str2, i, i2, j, j2, z, i3, i4));
    }

    protected final void zza(String str, String str2, int i) {
        zzawe.zzzb.post(new zzbal(this, str, str2, i));
    }

    @VisibleForTesting
    public final void zzb(String str, String str2, long j) {
        zzawe.zzzb.post(new zzbak(this, str, str2, j));
    }

    @VisibleForTesting
    public final void zza(String str, String str2, String str3, @Nullable String str4) {
        zzawe.zzzb.post(new zzban(this, str, str2, str3, str4));
    }

    protected String zzfe(String str) {
        zzuo.zzof();
        return zzawe.zzen(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:41:0x0087  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String zzff(String str) {
        byte b;
        switch (str.hashCode()) {
            case -1947652542:
                if (!str.equals("interrupted")) {
                    b = -1;
                } else {
                    b = 3;
                }
                break;
            case -1396664534:
                if (!str.equals("badUrl")) {
                    b = -1;
                } else {
                    b = 8;
                }
                break;
            case -1347010958:
                if (!str.equals("inProgress")) {
                    b = -1;
                } else {
                    b = 2;
                }
                break;
            case -918817863:
                if (!str.equals("downloadTimeout")) {
                    b = -1;
                } else {
                    b = 9;
                }
                break;
            case -659376217:
                if (!str.equals("contentLengthMissing")) {
                    b = -1;
                } else {
                    b = 0;
                }
                break;
            case -642208130:
                if (!str.equals("playerFailed")) {
                    b = -1;
                } else {
                    b = 5;
                }
                break;
            case -354048396:
                if (!str.equals("sizeExceeded")) {
                    b = -1;
                } else {
                    b = 11;
                }
                break;
            case -32082395:
                if (!str.equals("externalAbort")) {
                    b = -1;
                } else {
                    b = 10;
                }
                break;
            case 3387234:
                if (!str.equals("noop")) {
                    b = -1;
                } else {
                    b = 4;
                }
                break;
            case 96784904:
                if (!str.equals("error")) {
                    b = -1;
                } else {
                    b = 1;
                }
                break;
            case 580119100:
                if (!str.equals("expireFailed")) {
                    b = -1;
                } else {
                    b = 6;
                }
                break;
            case 725497484:
                if (!str.equals("noCacheDir")) {
                    b = -1;
                } else {
                    b = 7;
                }
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            default:
                return "internal";
            case 6:
            case 7:
                return "io";
            case 8:
            case 9:
                return "network";
            case 10:
            case 11:
                return "policy";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(String str, Map<String, String> map) {
        zzayt zzaytVar = this.zzecy.get();
        if (zzaytVar != null) {
            zzaytVar.zza(str, map);
        }
    }
}
