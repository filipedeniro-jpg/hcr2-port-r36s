package com.google.android.gms.drive;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.ShowFirstParty;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@Deprecated
public final class zzn extends ExecutionOptions {
    private boolean zzat;

    public static zzn zza(@Nullable ExecutionOptions executionOptions) {
        zzp zzpVar = new zzp();
        if (executionOptions != null) {
            zzpVar.setConflictStrategy(executionOptions.zzn());
            zzpVar.setNotifyOnCompletion(executionOptions.zzm());
            String strZzl = executionOptions.zzl();
            if (strZzl != null) {
                zzpVar.setTrackingTag(strZzl);
            }
        }
        return (zzn) zzpVar.build();
    }

    private zzn(@Nullable String str, boolean z, int i, boolean z2) {
        super(str, z, i);
        this.zzat = z2;
    }

    public final boolean zzp() {
        return this.zzat;
    }
}
