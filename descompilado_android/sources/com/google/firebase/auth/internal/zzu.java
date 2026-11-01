package com.google.firebase.auth.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzed;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "DefaultMultiFactorResolverCreator")
public final class zzu extends com.google.firebase.auth.zzab {
    public static final Parcelable.Creator<zzu> CREATOR = new zzt();

    @SafeParcelable.Field(getter = "getPhoneMultiFactorInfoList", id = 1)
    private final List<com.google.firebase.auth.zzae> zza = new ArrayList();

    @SafeParcelable.Field(getter = "getSession", id = 2)
    private final zzw zzb;

    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private final String zzc;

    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 4)
    private final com.google.firebase.auth.zzg zzd;

    @SafeParcelable.Field(getter = "getReauthUser", id = 5)
    private final zzn zze;

    @SafeParcelable.Constructor
    public zzu(@SafeParcelable.Param(id = 1) List<com.google.firebase.auth.zzae> list, @SafeParcelable.Param(id = 2) zzw zzwVar, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) com.google.firebase.auth.zzg zzgVar, @Nullable @SafeParcelable.Param(id = 5) zzn zznVar) {
        for (com.google.firebase.auth.zzae zzaeVar : list) {
            if (zzaeVar instanceof com.google.firebase.auth.zzae) {
                this.zza.add(zzaeVar);
            }
        }
        this.zzb = (zzw) Preconditions.checkNotNull(zzwVar);
        this.zzc = Preconditions.checkNotEmpty(str);
        this.zzd = zzgVar;
        this.zze = zznVar;
    }

    public static zzu zza(zzed zzedVar, FirebaseAuth firebaseAuth, @Nullable FirebaseUser firebaseUser) {
        List<com.google.firebase.auth.zzy> listZzc = zzedVar.zzc();
        ArrayList arrayList = new ArrayList();
        for (com.google.firebase.auth.zzy zzyVar : listZzc) {
            if (zzyVar instanceof com.google.firebase.auth.zzae) {
                arrayList.add((com.google.firebase.auth.zzae) zzyVar);
            }
        }
        return new zzu(arrayList, zzw.zza(zzedVar.zzc(), zzedVar.zza()), firebaseAuth.zzb().getName(), zzedVar.zzb(), (zzn) firebaseUser);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zza, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zze, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
