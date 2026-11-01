package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-gass@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "GassResponseParcelCreator")
public final class zzczc extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzczc> CREATOR = new zzczb();

    @SafeParcelable.VersionField(id = 1)
    private final int versionCode;

    @SafeParcelable.Field(getter = "getAfmaSignalsAsBytes", id = 2, type = "byte[]")
    private zzbo.zza zzgns = null;
    private byte[] zzgnt;

    @SafeParcelable.Constructor
    zzczc(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) byte[] bArr) {
        this.versionCode = i;
        this.zzgnt = bArr;
        zzany();
    }

    public final zzbo.zza zzanx() {
        if (!(this.zzgns != null)) {
            try {
                this.zzgns = zzbo.zza.zzb(this.zzgnt, zzdpq.zzayw());
                this.zzgnt = null;
            } catch (zzdqn e) {
                throw new IllegalStateException(e);
            }
        }
        zzany();
        return this.zzgns;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        byte[] byteArray = this.zzgnt;
        if (byteArray == null) {
            byteArray = this.zzgns.toByteArray();
        }
        SafeParcelWriter.writeByteArray(parcel, 2, byteArray, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    private final void zzany() {
        if (this.zzgns != null || this.zzgnt == null) {
            if (this.zzgns == null || this.zzgnt != null) {
                if (this.zzgns != null && this.zzgnt != null) {
                    throw new IllegalStateException("Invalid internal representation - full");
                }
                if (this.zzgns == null && this.zzgnt == null) {
                    throw new IllegalStateException("Invalid internal representation - empty");
                }
                throw new IllegalStateException("Impossible");
            }
        }
    }
}
