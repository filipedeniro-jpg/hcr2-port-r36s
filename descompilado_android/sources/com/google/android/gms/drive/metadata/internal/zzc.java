package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.ironsource.sdk.constants.Constants;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "CustomPropertyCreator")
@SafeParcelable.Reserved({1})
public final class zzc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzc> CREATOR = new zzd();

    @Nullable
    @SafeParcelable.Field(id = 3)
    final String value;

    @SafeParcelable.Field(id = 2)
    final CustomPropertyKey zzje;

    @SafeParcelable.Constructor
    public zzc(@SafeParcelable.Param(id = 2) CustomPropertyKey customPropertyKey, @Nullable @SafeParcelable.Param(id = 3) String str) {
        Preconditions.checkNotNull(customPropertyKey, Constants.ParametersKeys.KEY);
        this.zzje = customPropertyKey;
        this.value = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzje, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.value, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzje, this.value);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass()) {
            zzc zzcVar = (zzc) obj;
            if (Objects.equal(this.zzje, zzcVar.zzje) && Objects.equal(this.value, zzcVar.value)) {
                return true;
            }
        }
        return false;
    }
}
