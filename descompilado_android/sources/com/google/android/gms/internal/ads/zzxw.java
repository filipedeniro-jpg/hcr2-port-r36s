package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "RequestConfigurationParcelCreator")
public final class zzxw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzxw> CREATOR = new zzxv();

    @SafeParcelable.Field(id = 1)
    private final int zzabj;

    @SafeParcelable.Field(id = 2)
    private final int zzabk;

    @SafeParcelable.Constructor
    public zzxw(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2) {
        this.zzabj = i;
        this.zzabk = i2;
    }

    public zzxw(RequestConfiguration requestConfiguration) {
        this.zzabj = requestConfiguration.getTagForChildDirectedTreatment();
        this.zzabk = requestConfiguration.getTagForUnderAgeOfConsent();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzabj);
        SafeParcelWriter.writeInt(parcel, 2, this.zzabk);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
