package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "StringParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzaom extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaom> CREATOR = new zzaop();

    @SafeParcelable.Field(id = 2)
    String zzdlr;

    @SafeParcelable.Constructor
    public zzaom(@SafeParcelable.Param(id = 2) String str) {
        this.zzdlr = str;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdlr, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
