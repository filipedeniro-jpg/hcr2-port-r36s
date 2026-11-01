package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
@SafeParcelable.Class(creator = "NonagonRequestParcelCreator")
public final class zzaok extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaok> CREATOR = new zzaon();

    @SafeParcelable.Field(id = 3)
    public final ApplicationInfo applicationInfo;

    @SafeParcelable.Field(id = 4)
    public final String packageName;

    @SafeParcelable.Field(id = 2)
    public final zzawv zzdid;

    @Nullable
    @SafeParcelable.Field(id = 6)
    public final PackageInfo zzdij;

    @SafeParcelable.Field(id = 5)
    public final List<String> zzdit;

    @SafeParcelable.Field(id = 7)
    public final String zzdjd;

    @SafeParcelable.Field(id = 1)
    public final Bundle zzdlo;

    @SafeParcelable.Field(id = 8)
    public final boolean zzdlp;

    @SafeParcelable.Field(id = 9)
    public final String zzdlq;

    @SafeParcelable.Constructor
    public zzaok(@SafeParcelable.Param(id = 1) Bundle bundle, @SafeParcelable.Param(id = 2) zzawv zzawvVar, @SafeParcelable.Param(id = 3) ApplicationInfo applicationInfo, @SafeParcelable.Param(id = 4) String str, @SafeParcelable.Param(id = 5) List<String> list, @Nullable @SafeParcelable.Param(id = 6) PackageInfo packageInfo, @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) String str3) {
        this.zzdlo = bundle;
        this.zzdid = zzawvVar;
        this.packageName = str;
        this.applicationInfo = applicationInfo;
        this.zzdit = list;
        this.zzdij = packageInfo;
        this.zzdjd = str2;
        this.zzdlp = z;
        this.zzdlq = str3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 1, this.zzdlo, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdid, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.applicationInfo, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.packageName, false);
        SafeParcelWriter.writeStringList(parcel, 5, this.zzdit, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdij, i, false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdjd, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdlp);
        SafeParcelWriter.writeString(parcel, 9, this.zzdlq, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
