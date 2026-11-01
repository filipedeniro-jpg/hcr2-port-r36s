package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "GetAccountInfoResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzeq extends AbstractSafeParcelable implements com.google.firebase.auth.api.internal.zzea<zzeq, zzp.zzg> {
    public static final Parcelable.Creator<zzeq> CREATOR = new zzep();

    @SafeParcelable.Field(getter = "getUserList", id = 2)
    private zzeu zza;

    public zzeq() {
    }

    @SafeParcelable.Constructor
    zzeq(@SafeParcelable.Param(id = 2) zzeu zzeuVar) {
        this.zza = zzeuVar == null ? new zzeu() : zzeu.zza(zzeuVar);
    }

    public final List<zzes> zzb() {
        return this.zza.zza();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zza, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzg> zza() {
        return zzp.zzg.zzb();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzp.zzg)) {
            throw new IllegalArgumentException("The passed proto must be an instance of GetAccountInfoResponse.");
        }
        zzp.zzg zzgVar = (zzp.zzg) zzjgVar;
        if (zzgVar.zza() == 0) {
            this.zza = new zzeu();
        } else {
            this.zza = zzeu.zza(zzgVar);
        }
        return this;
    }
}
