package com.google.android.gms.internal.ads;

import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class zzczt {
    private final String className;
    private final zzczw zzgod;
    private zzczw zzgoe;
    private boolean zzgof;

    private zzczt(String str) {
        this.zzgod = new zzczw();
        this.zzgoe = this.zzgod;
        this.zzgof = false;
        this.className = (String) zzczx.checkNotNull(str);
    }

    public final zzczt zzy(@NullableDecl Object obj) {
        zzczw zzczwVar = new zzczw();
        this.zzgoe.zzgog = zzczwVar;
        this.zzgoe = zzczwVar;
        zzczwVar.value = obj;
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.className);
        sb.append('{');
        zzczw zzczwVar = this.zzgod.zzgog;
        String str = "";
        while (zzczwVar != null) {
            Object obj = zzczwVar.value;
            sb.append(str);
            if (obj != null && obj.getClass().isArray()) {
                String strDeepToString = Arrays.deepToString(new Object[]{obj});
                sb.append((CharSequence) strDeepToString, 1, strDeepToString.length() - 1);
            } else {
                sb.append(obj);
            }
            zzczwVar = zzczwVar.zzgog;
            str = ", ";
        }
        sb.append('}');
        return sb.toString();
    }
}
