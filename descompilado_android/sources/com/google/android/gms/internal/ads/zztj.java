package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "AdDataParcelCreator")
public final class zztj extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zztj> CREATOR = new zztm();

    @SafeParcelable.Field(id = 1)
    public final String zzcbj;

    @SafeParcelable.Field(id = 2)
    public final String zzcbk;

    @SafeParcelable.Constructor
    public zztj(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2) {
        this.zzcbj = str;
        this.zzcbk = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzcbj, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzcbk, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
