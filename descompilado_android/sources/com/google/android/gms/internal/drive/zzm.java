package com.google.android.gms.internal.drive;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "CloseContentsAndUpdateMetadataRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzm extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzm> CREATOR = new zzn();

    @SafeParcelable.Field(id = 6)
    private final String zzan;

    @SafeParcelable.Field(id = 5)
    private final boolean zzao;

    @SafeParcelable.Field(defaultValue = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE, id = 10)
    private final boolean zzat;

    @SafeParcelable.Field(id = 2)
    private final DriveId zzdd;

    @SafeParcelable.Field(id = 3)
    private final MetadataBundle zzde;

    @Nullable
    @SafeParcelable.Field(id = 4)
    private final Contents zzdf;

    @SafeParcelable.Field(id = 7)
    private final int zzdg;

    @SafeParcelable.Field(id = 8)
    private final int zzdh;

    @SafeParcelable.Field(id = 9)
    private final boolean zzdi;

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzde, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdf, i, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzao);
        SafeParcelWriter.writeString(parcel, 6, this.zzan, false);
        SafeParcelWriter.writeInt(parcel, 7, this.zzdg);
        SafeParcelWriter.writeInt(parcel, 8, this.zzdh);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdi);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    zzm(@SafeParcelable.Param(id = 2) DriveId driveId, @SafeParcelable.Param(id = 3) MetadataBundle metadataBundle, @SafeParcelable.Param(id = 4) Contents contents, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) String str, @SafeParcelable.Param(id = 7) int i, @SafeParcelable.Param(id = 8) int i2, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3) {
        this.zzdd = driveId;
        this.zzde = metadataBundle;
        this.zzdf = contents;
        this.zzao = z;
        this.zzan = str;
        this.zzdg = i;
        this.zzdh = i2;
        this.zzdi = z2;
        this.zzat = z3;
    }

    @VisibleForTesting
    public zzm(DriveId driveId, MetadataBundle metadataBundle, int i, boolean z, com.google.android.gms.drive.zzn zznVar) {
        this(driveId, metadataBundle, null, zznVar.zzm(), zznVar.zzl(), zznVar.zzn(), i, z, zznVar.zzp());
    }
}
