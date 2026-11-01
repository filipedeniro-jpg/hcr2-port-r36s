package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "DefaultMultiFactorSessionCreator")
public final class zzw extends com.google.firebase.auth.zzaa {
    public static final Parcelable.Creator<zzw> CREATOR = new zzv();

    @Nullable
    @SafeParcelable.Field(getter = "getIdToken", id = 1)
    private String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getPendingCredential", id = 2)
    private String zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 3)
    private List<com.google.firebase.auth.zzae> zzc;

    private zzw() {
    }

    @SafeParcelable.Constructor
    zzw(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @SafeParcelable.Param(id = 3) List<com.google.firebase.auth.zzae> list) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = list;
    }

    public static zzw zza(List<com.google.firebase.auth.zzy> list, String str) {
        Preconditions.checkNotNull(list);
        Preconditions.checkNotEmpty(str);
        zzw zzwVar = new zzw();
        zzwVar.zzc = new ArrayList();
        for (com.google.firebase.auth.zzy zzyVar : list) {
            if (zzyVar instanceof com.google.firebase.auth.zzae) {
                zzwVar.zzc.add((com.google.firebase.auth.zzae) zzyVar);
            }
        }
        zzwVar.zzb = str;
        return zzwVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zza, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzb, false);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
