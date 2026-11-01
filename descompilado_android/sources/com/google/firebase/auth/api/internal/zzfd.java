package com.google.firebase.auth.api.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfd {
    private final String zza;
    private final String zzb;

    public zzfd(Context context) {
        this(context, context.getPackageName());
    }

    private zzfd(Context context, String str) {
        Preconditions.checkNotNull(context);
        this.zza = Preconditions.checkNotEmpty(str);
        try {
            byte[] packageCertificateHashBytes = AndroidUtilsLight.getPackageCertificateHashBytes(context, this.zza);
            if (packageCertificateHashBytes == null) {
                String strValueOf = String.valueOf(str);
                Log.e("FBA-PackageInfo", strValueOf.length() != 0 ? "single cert required: ".concat(strValueOf) : new String("single cert required: "));
                this.zzb = null;
                return;
            }
            this.zzb = Hex.bytesToStringUppercase(packageCertificateHashBytes, false);
        } catch (PackageManager.NameNotFoundException unused) {
            String strValueOf2 = String.valueOf(str);
            Log.e("FBA-PackageInfo", strValueOf2.length() != 0 ? "no pkg: ".concat(strValueOf2) : new String("no pkg: "));
            this.zzb = null;
        }
    }

    public final String zza() {
        return this.zza;
    }

    @Nullable
    public final String zzb() {
        return this.zzb;
    }
}
