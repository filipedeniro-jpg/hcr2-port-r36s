package com.google.firebase.auth.api.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzej extends zzao implements Api.ApiOptions.HasOptions {
    private final String zzb;

    private zzej(String str) {
        this.zzb = Preconditions.checkNotEmpty(str, "A valid API key must be provided");
    }

    public final String zzb() {
        return this.zzb;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof zzej) {
            return Objects.equal(this.zzb, ((zzej) obj).zzb);
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzb);
    }

    @Override // com.google.firebase.auth.api.internal.zzao
    /* JADX INFO: renamed from: zza */
    public final /* synthetic */ zzao clone() {
        return (zzej) clone();
    }

    @Override // com.google.firebase.auth.api.internal.zzao
    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return new zzei(this.zzb).zza();
    }

    /* synthetic */ zzej(String str, zzeg zzegVar) {
        this(str);
    }
}
