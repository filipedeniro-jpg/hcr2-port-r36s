package com.google.android.gms.internal.ads;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public class zzcmm {
    private String zzgda;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static class zza {
        private String zzgda;

        public final zza zzge(String str) {
            this.zzgda = str;
            return this;
        }
    }

    private zzcmm(zza zzaVar) {
        this.zzgda = zzaVar.zzgda;
    }

    public final Set<String> zzalo() {
        HashSet hashSet = new HashSet();
        hashSet.add(this.zzgda.toLowerCase(Locale.ROOT));
        return hashSet;
    }

    public final String zzalp() {
        return this.zzgda.toLowerCase(Locale.ROOT);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0036  */
    public final int zzalq() {
        byte b;
        switch (this.zzgda) {
            case "NATIVE":
                b = 2;
                break;
            case "INTERSTITIAL":
                b = 1;
                break;
            case "REWARDED":
                b = 3;
                break;
            case "BANNER":
                b = 0;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return 3;
        }
        if (b != 2) {
            return b != 3 ? 0 : 7;
        }
        return 6;
    }
}
