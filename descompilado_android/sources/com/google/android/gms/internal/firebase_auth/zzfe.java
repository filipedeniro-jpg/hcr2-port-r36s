package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "ProviderUserInfoListCreator")
@SafeParcelable.Reserved({1})
public final class zzfe extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzfe> CREATOR = new zzfd();

    @SafeParcelable.Field(getter = "getProviderUserInfos", id = 2)
    private List<zzfc> zza;

    public zzfe() {
        this.zza = new ArrayList();
    }

    @SafeParcelable.Constructor
    zzfe(@SafeParcelable.Param(id = 2) List<zzfc> list) {
        if (list != null && !list.isEmpty()) {
            this.zza = Collections.unmodifiableList(list);
        } else {
            this.zza = Collections.emptyList();
        }
    }

    public final List<zzfc> zza() {
        return this.zza;
    }

    public static zzfe zza(zzfe zzfeVar) {
        List<zzfc> list = zzfeVar.zza;
        zzfe zzfeVar2 = new zzfe();
        if (list != null) {
            zzfeVar2.zza.addAll(list);
        }
        return zzfeVar2;
    }

    public static zzfe zza(List<zzu> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzu zzuVar = list.get(i);
            arrayList.add(new zzfc(Strings.emptyToNull(zzuVar.zzd()), Strings.emptyToNull(zzuVar.zzb()), Strings.emptyToNull(zzuVar.zzc()), Strings.emptyToNull(zzuVar.zza()), null, Strings.emptyToNull(zzuVar.zzf()), Strings.emptyToNull(zzuVar.zze())));
        }
        return new zzfe(arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zza, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
