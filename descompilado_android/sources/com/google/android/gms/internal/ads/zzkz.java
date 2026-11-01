package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.ApicFrame;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkz extends zzlc {
    public static final Parcelable.Creator<zzkz> CREATOR = new zzky();
    private final String description;
    private final String mimeType;
    private final int zzazm;
    private final byte[] zzazn;

    public zzkz(String str, String str2, int i, byte[] bArr) {
        super(ApicFrame.ID);
        this.mimeType = str;
        this.description = null;
        this.zzazm = 3;
        this.zzazn = bArr;
    }

    zzkz(Parcel parcel) {
        super(ApicFrame.ID);
        this.mimeType = parcel.readString();
        this.description = parcel.readString();
        this.zzazm = parcel.readInt();
        this.zzazn = parcel.createByteArray();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzkz zzkzVar = (zzkz) obj;
            if (this.zzazm == zzkzVar.zzazm && zzoh.zza(this.mimeType, zzkzVar.mimeType) && zzoh.zza(this.description, zzkzVar.description) && Arrays.equals(this.zzazn, zzkzVar.zzazn)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = (this.zzazm + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        String str = this.mimeType;
        int iHashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.description;
        return ((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Arrays.hashCode(this.zzazn);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mimeType);
        parcel.writeString(this.description);
        parcel.writeInt(this.zzazm);
        parcel.writeByteArray(this.zzazn);
    }
}
