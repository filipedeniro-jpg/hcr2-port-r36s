package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "HttpResponseParcelCreator")
public final class zzael extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzael> CREATOR = new zzaeo();

    @SafeParcelable.Field(id = 4)
    public final byte[] data;

    @SafeParcelable.Field(id = 3)
    public final int statusCode;

    @SafeParcelable.Field(id = 7)
    public final boolean zzac;

    @SafeParcelable.Field(id = 8)
    public final long zzad;

    @SafeParcelable.Field(id = 5)
    public final String[] zzcxu;

    @SafeParcelable.Field(id = 6)
    public final String[] zzcxv;

    @SafeParcelable.Field(id = 1)
    public final boolean zzcxw;

    @SafeParcelable.Field(id = 2)
    public final String zzcxx;

    @SafeParcelable.Constructor
    zzael(@SafeParcelable.Param(id = 1) boolean z, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) byte[] bArr, @SafeParcelable.Param(id = 5) String[] strArr, @SafeParcelable.Param(id = 6) String[] strArr2, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) long j) {
        this.zzcxw = z;
        this.zzcxx = str;
        this.statusCode = i;
        this.data = bArr;
        this.zzcxu = strArr;
        this.zzcxv = strArr2;
        this.zzac = z2;
        this.zzad = j;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.zzcxw);
        SafeParcelWriter.writeString(parcel, 2, this.zzcxx, false);
        SafeParcelWriter.writeInt(parcel, 3, this.statusCode);
        SafeParcelWriter.writeByteArray(parcel, 4, this.data, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.zzcxu, false);
        SafeParcelWriter.writeStringArray(parcel, 6, this.zzcxv, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzac);
        SafeParcelWriter.writeLong(parcel, 8, this.zzad);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
