package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.Strings;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "CreateAuthUriResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzei extends AbstractSafeParcelable implements com.google.firebase.auth.api.internal.zzea<zzei, zzp.zzb> {
    public static final Parcelable.Creator<zzei> CREATOR = new zzeh();

    @SafeParcelable.Field(getter = "getAuthUri", id = 2)
    private String zza;

    @SafeParcelable.Field(getter = "isRegistered", id = 3)
    private boolean zzb;

    @SafeParcelable.Field(getter = "getProviderId", id = 4)
    private String zzc;

    @SafeParcelable.Field(getter = "isForExistingProvider", id = 5)
    private boolean zzd;

    @SafeParcelable.Field(getter = "getStringList", id = 6)
    private zzfp zze;

    @SafeParcelable.Field(getter = "getSignInMethods", id = 7)
    private List<String> zzf;

    public zzei() {
        this.zze = zzfp.zzb();
    }

    @SafeParcelable.Constructor
    public zzei(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) boolean z, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) zzfp zzfpVar, @SafeParcelable.Param(id = 7) List<String> list) {
        this.zza = str;
        this.zzb = z;
        this.zzc = str2;
        this.zzd = z2;
        this.zze = zzfpVar == null ? zzfp.zzb() : zzfp.zza(zzfpVar);
        this.zzf = list;
    }

    @Nullable
    public final List<String> zzb() {
        return this.zzf;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzb);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzd);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zze, i, false);
        SafeParcelWriter.writeStringList(parcel, 7, this.zzf, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzp.zzb> zza() {
        return zzp.zzb.zzi();
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        zzfp zzfpVar;
        if (!(zzjgVar instanceof zzp.zzb)) {
            throw new IllegalArgumentException("The passed proto must be an instance of CreateAuthUriResponse.");
        }
        zzp.zzb zzbVar = (zzp.zzb) zzjgVar;
        this.zza = Strings.emptyToNull(zzbVar.zza());
        this.zzb = zzbVar.zzd();
        this.zzc = Strings.emptyToNull(zzbVar.zze());
        this.zzd = zzbVar.zzf();
        if (zzbVar.zzc() == 0) {
            zzfpVar = zzfp.zzb();
        } else {
            zzfpVar = new zzfp(1, new ArrayList(zzbVar.zzb()));
        }
        this.zze = zzfpVar;
        this.zzf = zzbVar.zzh() == 0 ? new ArrayList<>(0) : zzbVar.zzg();
        return this;
    }
}
