package com.google.android.gms.internal.ads;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zziy {
    private static final zzld zzamw = new zzjb();
    private static final Pattern zzamx = Pattern.compile("^ [0-9a-fA-F]{8} ([0-9a-fA-F]{8}) ([0-9a-fA-F]{8})");
    public int zzafo = -1;
    public int zzafp = -1;

    public final boolean zzb(zzkv zzkvVar) {
        for (int i = 0; i < zzkvVar.length(); i++) {
            zzkv.zza zzaVarZzar = zzkvVar.zzar(i);
            if (zzaVarZzar instanceof zzlb) {
                zzlb zzlbVar = (zzlb) zzaVarZzar;
                if (zzd(zzlbVar.description, zzlbVar.zzazo)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean zzd(String str, String str2) {
        if (!"iTunSMPB".equals(str)) {
            return false;
        }
        Matcher matcher = zzamx.matcher(str2);
        if (matcher.find()) {
            try {
                int i = Integer.parseInt(matcher.group(1), 16);
                int i2 = Integer.parseInt(matcher.group(2), 16);
                if (i > 0 || i2 > 0) {
                    this.zzafo = i;
                    this.zzafp = i2;
                    return true;
                }
            } catch (NumberFormatException unused) {
            }
        }
        return false;
    }

    public final boolean zzgb() {
        return (this.zzafo == -1 || this.zzafp == -1) ? false : true;
    }
}
