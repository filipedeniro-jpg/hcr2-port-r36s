package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "NativeAdOptionsParcelCreator")
public final class zzaai extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzaai> CREATOR = new zzaah();

    @SafeParcelable.Field(id = 1)
    public final int versionCode;

    @SafeParcelable.Field(id = 3)
    public final int zzbjt;

    @SafeParcelable.Field(id = 8)
    public final int zzbju;

    @SafeParcelable.Field(id = 4)
    public final boolean zzbjv;

    @SafeParcelable.Field(id = 5)
    public final int zzbjw;

    @SafeParcelable.Field(id = 7)
    public final boolean zzbjy;

    @SafeParcelable.Field(id = 2)
    public final boolean zzcvn;

    @Nullable
    @SafeParcelable.Field(id = 6)
    public final zzyc zzcvo;

    public zzaai(NativeAdOptions nativeAdOptions) {
        this(4, nativeAdOptions.shouldReturnUrlsForImageAssets(), nativeAdOptions.getImageOrientation(), nativeAdOptions.shouldRequestMultipleImages(), nativeAdOptions.getAdChoicesPlacement(), nativeAdOptions.getVideoOptions() != null ? new zzyc(nativeAdOptions.getVideoOptions()) : null, nativeAdOptions.zzjb(), nativeAdOptions.getMediaAspectRatio());
    }

    @SafeParcelable.Constructor
    public zzaai(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) boolean z, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) boolean z2, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 6) zzyc zzycVar, @SafeParcelable.Param(id = 7) boolean z3, @SafeParcelable.Param(id = 8) int i4) {
        this.versionCode = i;
        this.zzcvn = z;
        this.zzbjt = i2;
        this.zzbjv = z2;
        this.zzbjw = i3;
        this.zzcvo = zzycVar;
        this.zzbjy = z3;
        this.zzbju = i4;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzcvn);
        SafeParcelWriter.writeInt(parcel, 3, this.zzbjt);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzbjv);
        SafeParcelWriter.writeInt(parcel, 5, this.zzbjw);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzcvo, i, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzbjy);
        SafeParcelWriter.writeInt(parcel, 8, this.zzbju);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
