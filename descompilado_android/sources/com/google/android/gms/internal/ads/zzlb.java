package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.exoplayer2.metadata.id3.CommentFrame;
import com.ironsource.mediationsdk.logger.IronSourceError;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzlb extends zzlc {
    public static final Parcelable.Creator<zzlb> CREATOR = new zzla();
    public final String description;
    private final String zzafs;
    public final String zzazo;

    public zzlb(String str, String str2, String str3) {
        super(CommentFrame.ID);
        this.zzafs = str;
        this.description = str2;
        this.zzazo = str3;
    }

    zzlb(Parcel parcel) {
        super(CommentFrame.ID);
        this.zzafs = parcel.readString();
        this.description = parcel.readString();
        this.zzazo = parcel.readString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzlb zzlbVar = (zzlb) obj;
            if (zzoh.zza(this.description, zzlbVar.description) && zzoh.zza(this.zzafs, zzlbVar.zzafs) && zzoh.zza(this.zzazo, zzlbVar.zzazo)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.zzafs;
        int iHashCode = ((str != null ? str.hashCode() : 0) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
        String str2 = this.description;
        int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.zzazo;
        return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzaew);
        parcel.writeString(this.zzafs);
        parcel.writeString(this.zzazo);
    }
}
