package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzoi implements Parcelable {
    public static final Parcelable.Creator<zzoi> CREATOR = new zzol();
    private int zzafu;
    public final int zzapv;
    public final int zzapw;
    public final int zzapx;
    public final byte[] zzbgx;

    public zzoi(int i, int i2, int i3, byte[] bArr) {
        this.zzapv = i;
        this.zzapx = i2;
        this.zzapw = i3;
        this.zzbgx = bArr;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    zzoi(Parcel parcel) {
        this.zzapv = parcel.readInt();
        this.zzapx = parcel.readInt();
        this.zzapw = parcel.readInt();
        this.zzbgx = parcel.readInt() != 0 ? parcel.createByteArray() : null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzoi zzoiVar = (zzoi) obj;
            if (this.zzapv == zzoiVar.zzapv && this.zzapx == zzoiVar.zzapx && this.zzapw == zzoiVar.zzapw && Arrays.equals(this.zzbgx, zzoiVar.zzbgx)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        int i = this.zzapv;
        int i2 = this.zzapx;
        int i3 = this.zzapw;
        boolean z = this.zzbgx != null;
        StringBuilder sb = new StringBuilder(55);
        sb.append("ColorInfo(");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(", ");
        sb.append(z);
        sb.append(")");
        return sb.toString();
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = ((((((this.zzapv + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31) + this.zzapx) * 31) + this.zzapw) * 31) + Arrays.hashCode(this.zzbgx);
        }
        return this.zzafu;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.zzapv);
        parcel.writeInt(this.zzapx);
        parcel.writeInt(this.zzapw);
        parcel.writeInt(this.zzbgx != null ? 1 : 0);
        byte[] bArr = this.zzbgx;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
    }
}
