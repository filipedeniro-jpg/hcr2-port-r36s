package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "GetAccountInfoUserListCreator")
@SafeParcelable.Reserved({1})
public final class zzeu extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzeu> CREATOR = new zzet();

    @SafeParcelable.Field(getter = "getUsers", id = 2)
    private List<zzes> zza;

    public zzeu() {
        this.zza = new ArrayList();
    }

    @SafeParcelable.Constructor
    zzeu(@SafeParcelable.Param(id = 2) List<zzes> list) {
        List<zzes> listUnmodifiableList;
        if (list == null) {
            listUnmodifiableList = Collections.emptyList();
        } else {
            listUnmodifiableList = Collections.unmodifiableList(list);
        }
        this.zza = listUnmodifiableList;
    }

    public final List<zzes> zza() {
        return this.zza;
    }

    public static zzeu zza(zzeu zzeuVar) {
        Preconditions.checkNotNull(zzeuVar);
        List<zzes> list = zzeuVar.zza;
        zzeu zzeuVar2 = new zzeu();
        if (list != null && !list.isEmpty()) {
            zzeuVar2.zza.addAll(list);
        }
        return zzeuVar2;
    }

    public static zzeu zza(zzp.zzg zzgVar) {
        ArrayList arrayList = new ArrayList(zzgVar.zza());
        for (int i = 0; i < zzgVar.zza(); i++) {
            zzz zzzVarZza = zzgVar.zza(i);
            arrayList.add(new zzes(Strings.emptyToNull(zzzVarZza.zza()), Strings.emptyToNull(zzzVarZza.zzb()), zzzVarZza.zze(), Strings.emptyToNull(zzzVarZza.zzc()), Strings.emptyToNull(zzzVarZza.zzd()), zzfe.zza(zzzVarZza.zzf()), Strings.emptyToNull(zzzVarZza.zzi()), Strings.emptyToNull(zzzVarZza.zzj()), zzzVarZza.zzh(), zzzVarZza.zzg(), false, null, zzfa.zza(zzzVarZza.zzk())));
        }
        return new zzeu(arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
