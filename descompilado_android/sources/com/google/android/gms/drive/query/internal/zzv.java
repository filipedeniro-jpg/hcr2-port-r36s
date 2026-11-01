package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "NotFilterCreator")
@SafeParcelable.Reserved({1000})
public final class zzv extends zza {
    public static final Parcelable.Creator<zzv> CREATOR = new zzw();

    @SafeParcelable.Field(id = 1)
    private final FilterHolder zzmp;

    @SafeParcelable.Constructor
    zzv(@SafeParcelable.Param(id = 1) FilterHolder filterHolder) {
        this.zzmp = filterHolder;
    }

    public zzv(Filter filter) {
        this(new FilterHolder(filter));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.drive.query.Filter
    public final <T> T zza(zzj<T> zzjVar) {
        return (T) zzjVar.zza(this.zzmp.getFilter().zza(zzjVar));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzmp, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
