package com.google.android.gms.internal.ads;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "AdRequestParcelCreator")
public final class zztp extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zztp> CREATOR = new zztr();

    @SafeParcelable.Field(id = 3)
    public final Bundle extras;

    @SafeParcelable.Field(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(id = 7)
    public final int zzabj;

    @SafeParcelable.Field(id = 20)
    public final int zzabk;

    @Nullable
    @SafeParcelable.Field(id = 21)
    public final String zzabl;

    @SafeParcelable.Field(id = 8)
    public final boolean zzbke;

    @SafeParcelable.Field(id = 2)
    @Deprecated
    public final long zzcbn;

    @SafeParcelable.Field(id = 4)
    @Deprecated
    public final int zzcbo;

    @SafeParcelable.Field(id = 5)
    public final List<String> zzcbp;

    @SafeParcelable.Field(id = 6)
    public final boolean zzcbq;

    @SafeParcelable.Field(id = 9)
    public final String zzcbr;

    @SafeParcelable.Field(id = 10)
    public final zzxy zzcbs;

    @SafeParcelable.Field(id = 12)
    public final String zzcbt;

    @SafeParcelable.Field(id = 13)
    public final Bundle zzcbu;

    @SafeParcelable.Field(id = 14)
    public final Bundle zzcbv;

    @SafeParcelable.Field(id = 15)
    public final List<String> zzcbw;

    @SafeParcelable.Field(id = 16)
    public final String zzcbx;

    @SafeParcelable.Field(id = 17)
    public final String zzcby;

    @SafeParcelable.Field(id = 18)
    @Deprecated
    public final boolean zzcbz;

    @Nullable
    @SafeParcelable.Field(id = 19)
    public final zztj zzcca;

    @SafeParcelable.Field(id = 11)
    public final Location zzng;

    @SafeParcelable.Constructor
    public zztp(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) long j, @SafeParcelable.Param(id = 3) Bundle bundle, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) List<String> list, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) int i3, @SafeParcelable.Param(id = 8) boolean z2, @SafeParcelable.Param(id = 9) String str, @SafeParcelable.Param(id = 10) zzxy zzxyVar, @SafeParcelable.Param(id = 11) Location location, @SafeParcelable.Param(id = 12) String str2, @SafeParcelable.Param(id = 13) Bundle bundle2, @SafeParcelable.Param(id = 14) Bundle bundle3, @SafeParcelable.Param(id = 15) List<String> list2, @SafeParcelable.Param(id = 16) String str3, @SafeParcelable.Param(id = 17) String str4, @SafeParcelable.Param(id = 18) boolean z3, @SafeParcelable.Param(id = 19) zztj zztjVar, @SafeParcelable.Param(id = 20) int i4, @Nullable @SafeParcelable.Param(id = 21) String str5) {
        this.versionCode = i;
        this.zzcbn = j;
        this.extras = bundle == null ? new Bundle() : bundle;
        this.zzcbo = i2;
        this.zzcbp = list;
        this.zzcbq = z;
        this.zzabj = i3;
        this.zzbke = z2;
        this.zzcbr = str;
        this.zzcbs = zzxyVar;
        this.zzng = location;
        this.zzcbt = str2;
        this.zzcbu = bundle2 == null ? new Bundle() : bundle2;
        this.zzcbv = bundle3;
        this.zzcbw = list2;
        this.zzcbx = str3;
        this.zzcby = str4;
        this.zzcbz = z3;
        this.zzcca = zztjVar;
        this.zzabk = i4;
        this.zzabl = str5;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeLong(parcel, 2, this.zzcbn);
        SafeParcelWriter.writeBundle(parcel, 3, this.extras, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzcbo);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzcbp, false);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzcbq);
        SafeParcelWriter.writeInt(parcel, 7, this.zzabj);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbke);
        SafeParcelWriter.writeString(parcel, 9, this.zzcbr, false);
        SafeParcelWriter.writeParcelable(parcel, 10, this.zzcbs, i, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzng, i, false);
        SafeParcelWriter.writeString(parcel, 12, this.zzcbt, false);
        SafeParcelWriter.writeBundle(parcel, 13, this.zzcbu, false);
        SafeParcelWriter.writeBundle(parcel, 14, this.zzcbv, false);
        SafeParcelWriter.writeStringList(parcel, 15, this.zzcbw, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzcbx, false);
        SafeParcelWriter.writeString(parcel, 17, this.zzcby, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcbz);
        SafeParcelWriter.writeParcelable(parcel, 19, this.zzcca, i, false);
        SafeParcelWriter.writeInt(parcel, 20, this.zzabk);
        SafeParcelWriter.writeString(parcel, 21, this.zzabl, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zztp)) {
            return false;
        }
        zztp zztpVar = (zztp) obj;
        return this.versionCode == zztpVar.versionCode && this.zzcbn == zztpVar.zzcbn && Objects.equal(this.extras, zztpVar.extras) && this.zzcbo == zztpVar.zzcbo && Objects.equal(this.zzcbp, zztpVar.zzcbp) && this.zzcbq == zztpVar.zzcbq && this.zzabj == zztpVar.zzabj && this.zzbke == zztpVar.zzbke && Objects.equal(this.zzcbr, zztpVar.zzcbr) && Objects.equal(this.zzcbs, zztpVar.zzcbs) && Objects.equal(this.zzng, zztpVar.zzng) && Objects.equal(this.zzcbt, zztpVar.zzcbt) && Objects.equal(this.zzcbu, zztpVar.zzcbu) && Objects.equal(this.zzcbv, zztpVar.zzcbv) && Objects.equal(this.zzcbw, zztpVar.zzcbw) && Objects.equal(this.zzcbx, zztpVar.zzcbx) && Objects.equal(this.zzcby, zztpVar.zzcby) && this.zzcbz == zztpVar.zzcbz && this.zzabk == zztpVar.zzabk && Objects.equal(this.zzabl, zztpVar.zzabl);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.versionCode), Long.valueOf(this.zzcbn), this.extras, Integer.valueOf(this.zzcbo), this.zzcbp, Boolean.valueOf(this.zzcbq), Integer.valueOf(this.zzabj), Boolean.valueOf(this.zzbke), this.zzcbr, this.zzcbs, this.zzng, this.zzcbt, this.zzcbu, this.zzcbv, this.zzcbw, this.zzcbx, this.zzcby, Boolean.valueOf(this.zzcbz), Integer.valueOf(this.zzabk), this.zzabl);
    }
}
