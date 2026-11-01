package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlf extends zzlc {
    public static final Parcelable.Creator<zzlf> CREATOR = new zzle();
    private final String description;
    private final String value;

    public zzlf(String str, String str2, String str3) {
        super(str);
        this.description = null;
        this.value = str3;
    }

    zzlf(Parcel parcel) {
        super(parcel.readString());
        this.description = parcel.readString();
        this.value = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzlf zzlfVar = (zzlf) obj;
            if (this.zzaew.equals(zzlfVar.zzaew) && zzoh.zza(this.description, zzlfVar.description) && zzoh.zza(this.value, zzlfVar.value)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.zzaew.hashCode() + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        String str = this.description;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.value;
        return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzaew);
        parcel.writeString(this.description);
        parcel.writeString(this.value);
    }
}
