package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkv implements Parcelable {
    public static final Parcelable.Creator<zzkv> CREATOR = new zzkx();
    private final zza[] zzazj;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public interface zza extends Parcelable {
    }

    public zzkv(List<? extends zza> list) {
        this.zzazj = new zza[list.size()];
        list.toArray(this.zzazj);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    zzkv(Parcel parcel) {
        this.zzazj = new zza[parcel.readInt()];
        int i = 0;
        while (true) {
            zza[] zzaVarArr = this.zzazj;
            if (i >= zzaVarArr.length) {
                return;
            }
            zzaVarArr[i] = (zza) parcel.readParcelable(zza.class.getClassLoader());
            i++;
        }
    }

    public final int length() {
        return this.zzazj.length;
    }

    public final zza zzar(int i) {
        return this.zzazj[i];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzazj, ((zzkv) obj).zzazj);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.zzazj);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zzazj.length);
        for (zza zzaVar : this.zzazj) {
            parcel.writeParcelable(zzaVar, 0);
        }
    }
}
