package com.google.android.gms.ads.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "InterstitialAdParameterParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzi extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzi> CREATOR = new zzh();

    @SafeParcelable.Field(id = 2)
    public final boolean zzbky;

    @SafeParcelable.Field(id = 3)
    public final boolean zzbkz;

    @SafeParcelable.Field(id = 4)
    private final String zzbla;

    @SafeParcelable.Field(id = 5)
    public final boolean zzblb;

    @SafeParcelable.Field(id = 6)
    public final float zzblc;

    @SafeParcelable.Field(id = 7)
    public final int zzbld;

    @SafeParcelable.Field(id = 8)
    public final boolean zzble;

    @SafeParcelable.Field(id = 9)
    public final boolean zzblf;

    @SafeParcelable.Field(id = 10)
    public final boolean zzblg;

    public zzi(boolean z, boolean z2, boolean z3, float f, int i, boolean z4, boolean z5, boolean z6) {
        this(false, z2, null, false, 0.0f, -1, z4, z5, z6);
    }

    @SafeParcelable.Constructor
    zzi(@SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) boolean z2, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) boolean z3, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) boolean z4, @SafeParcelable.Param(id = 9) boolean z5, @SafeParcelable.Param(id = 10) boolean z6) {
        this.zzbky = z;
        this.zzbkz = z2;
        this.zzbla = str;
        this.zzblb = z3;
        this.zzblc = f;
        this.zzbld = i;
        this.zzble = z4;
        this.zzblf = z5;
        this.zzblg = z6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbky);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbkz);
        SafeParcelWriter.writeString(parcel, 4, this.zzbla, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzblb);
        SafeParcelWriter.writeFloat(parcel, 6, this.zzblc);
        SafeParcelWriter.writeInt(parcel, 7, this.zzbld);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzble);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzblf);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzblg);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
