package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "VersionInfoParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzawv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzawv> CREATOR = new zzawu();

    @SafeParcelable.Field(id = 2)
    public String zzbnh;

    @SafeParcelable.Field(id = 3)
    public int zzdvs;

    @SafeParcelable.Field(id = 4)
    public int zzdvt;

    @SafeParcelable.Field(id = 5)
    public boolean zzdvu;

    @SafeParcelable.Field(id = 6)
    private boolean zzdvv;

    public zzawv(int i, int i2, boolean z) {
        this(i, i2, z, false, false);
    }

    public zzawv(int i, int i2, boolean z, boolean z2) {
        this(i, i2, true, false, false);
    }

    private zzawv(int i, int i2, boolean z, boolean z2, boolean z3) {
        String str = z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : "1";
        StringBuilder sb = new StringBuilder(str.length() + 36);
        sb.append("afma-sdk-a-v");
        sb.append(i);
        sb.append(".");
        sb.append(i2);
        sb.append(".");
        sb.append(str);
        this(sb.toString(), i, i2, z, false);
    }

    @SafeParcelable.Constructor
    zzawv(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) boolean z2) {
        this.zzbnh = str;
        this.zzdvs = i;
        this.zzdvt = i2;
        this.zzdvu = z;
        this.zzdvv = z2;
    }

    public static zzawv zzwk() {
        return new zzawv(12451009, 12451009, true);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbnh, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdvs);
        SafeParcelWriter.writeInt(parcel, 4, this.zzdvt);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdvu);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzdvv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
