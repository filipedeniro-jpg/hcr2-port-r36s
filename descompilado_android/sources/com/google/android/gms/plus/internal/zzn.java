package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "PlusSessionCreator")
public final class zzn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzn> CREATOR = new zzo();

    @SafeParcelable.Field(getter = "getAccountName", id = 1)
    private final String zzaa;

    @SafeParcelable.Field(getter = "getRequestedScopes", id = 2)
    private final String[] zzab;

    @SafeParcelable.Field(getter = "getVisibleActions", id = 3)
    private final String[] zzac;

    @SafeParcelable.Field(getter = "getRequiredFeatures", id = 4)
    private final String[] zzad;

    @SafeParcelable.Field(getter = "getPackageNameForAuth", id = 5)
    private final String zzae;

    @SafeParcelable.Field(getter = "getCallingPackageName", id = 6)
    private final String zzaf;

    @SafeParcelable.Field(getter = "getApplicationName", id = 7)
    private final String zzag;

    @SafeParcelable.Field(getter = "getClientId_DEPRECATED", id = 8)
    private final String zzah;

    @SafeParcelable.Field(getter = "getExtras", id = 9)
    private final PlusCommonExtras zzai;

    @SafeParcelable.VersionField(getter = "getVersionCode", id = 1000)
    private final int zzw;

    @SafeParcelable.Constructor
    zzn(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String[] strArr, @SafeParcelable.Param(id = 3) String[] strArr2, @SafeParcelable.Param(id = 4) String[] strArr3, @SafeParcelable.Param(id = 5) String str2, @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 7) String str4, @SafeParcelable.Param(id = 8) String str5, @SafeParcelable.Param(id = 9) PlusCommonExtras plusCommonExtras) {
        this.zzw = i;
        this.zzaa = str;
        this.zzab = strArr;
        this.zzac = strArr2;
        this.zzad = strArr3;
        this.zzae = str2;
        this.zzaf = str3;
        this.zzag = str4;
        this.zzah = str5;
        this.zzai = plusCommonExtras;
    }

    public zzn(String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, PlusCommonExtras plusCommonExtras) {
        this.zzw = 1;
        this.zzaa = str;
        this.zzab = strArr;
        this.zzac = strArr2;
        this.zzad = strArr3;
        this.zzae = str2;
        this.zzaf = str3;
        this.zzag = null;
        this.zzah = null;
        this.zzai = plusCommonExtras;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzn)) {
            return false;
        }
        zzn zznVar = (zzn) obj;
        return this.zzw == zznVar.zzw && Objects.equal(this.zzaa, zznVar.zzaa) && Arrays.equals(this.zzab, zznVar.zzab) && Arrays.equals(this.zzac, zznVar.zzac) && Arrays.equals(this.zzad, zznVar.zzad) && Objects.equal(this.zzae, zznVar.zzae) && Objects.equal(this.zzaf, zznVar.zzaf) && Objects.equal(this.zzag, zznVar.zzag) && Objects.equal(this.zzah, zznVar.zzah) && Objects.equal(this.zzai, zznVar.zzai);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzw), this.zzaa, this.zzab, this.zzac, this.zzad, this.zzae, this.zzaf, this.zzag, this.zzah, this.zzai);
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("versionCode", Integer.valueOf(this.zzw)).add("accountName", this.zzaa).add("requestedScopes", this.zzab).add("visibleActivities", this.zzac).add("requiredFeatures", this.zzad).add("packageNameForAuth", this.zzae).add("callingPackageName", this.zzaf).add("applicationName", this.zzag).add("extra", this.zzai.toString()).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzaa, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzab, false);
        SafeParcelWriter.writeStringArray(parcel, 3, this.zzac, false);
        SafeParcelWriter.writeStringArray(parcel, 4, this.zzad, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzae, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzaf, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzag, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzw);
        SafeParcelWriter.writeString(parcel, 8, this.zzah, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzai, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final String[] zzc() {
        return this.zzac;
    }

    public final String zzd() {
        return this.zzae;
    }

    public final Bundle zze() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", SafeParcelableSerializer.serializeToBytes(this.zzai));
        return bundle;
    }
}
