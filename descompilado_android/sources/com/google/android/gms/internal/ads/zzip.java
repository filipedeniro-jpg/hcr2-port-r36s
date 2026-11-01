package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
final class zzip implements Parcelable.Creator<zzil.zza> {
    zzip() {
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzil.zza[] newArray(int i) {
        return new zzil.zza[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzil.zza createFromParcel(Parcel parcel) {
        return new zzil.zza(parcel);
    }
}
