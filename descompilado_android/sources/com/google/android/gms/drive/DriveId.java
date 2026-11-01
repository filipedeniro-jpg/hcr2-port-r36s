package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.drive.zzbn;
import com.google.android.gms.internal.drive.zzbs;
import com.google.android.gms.internal.drive.zzdp;
import com.google.android.gms.internal.drive.zzfb;
import com.google.android.gms.internal.drive.zzfd;
import com.google.android.gms.internal.drive.zzjx;
import com.google.android.gms.internal.drive.zzkk;
import com.google.android.gms.internal.drive.zzkq;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "DriveIdCreator")
@SafeParcelable.Reserved({1})
public class DriveId extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveId> CREATOR = new zzk();
    public static final int RESOURCE_TYPE_FILE = 0;
    public static final int RESOURCE_TYPE_FOLDER = 1;
    public static final int RESOURCE_TYPE_UNKNOWN = -1;

    @SafeParcelable.Field(id = 2)
    private final String zzad;

    @SafeParcelable.Field(id = 3)
    private final long zzae;

    @SafeParcelable.Field(defaultValueUnchecked = "com.google.android.gms.drive.DriveId.RESOURCE_TYPE_UNKNOWN", id = 5)
    private final int zzaf;

    @SafeParcelable.Field(id = 4)
    private final long zzf;
    private volatile String zzh = null;
    private volatile String zzag = null;

    @VisibleForTesting
    public static DriveId zza(String str) {
        Preconditions.checkNotNull(str);
        return new DriveId(str, -1L, -1L, -1);
    }

    @Nullable
    public String getResourceId() {
        return this.zzad;
    }

    public int getResourceType() {
        return this.zzaf;
    }

    public DriveFile asDriveFile() {
        if (this.zzaf == 1) {
            throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
        }
        return new zzbn(this);
    }

    public DriveFolder asDriveFolder() {
        if (this.zzaf == 0) {
            throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
        }
        return new zzbs(this);
    }

    public DriveResource asDriveResource() {
        int i = this.zzaf;
        if (i == 1) {
            return asDriveFolder();
        }
        if (i == 0) {
            return asDriveFile();
        }
        return new zzdp(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzad, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzae);
        SafeParcelWriter.writeLong(parcel, 4, this.zzf);
        SafeParcelWriter.writeInt(parcel, 5, this.zzaf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @SafeParcelable.Constructor
    public DriveId(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) long j2, @SafeParcelable.Param(id = 5) int i) {
        this.zzad = str;
        boolean z = true;
        Preconditions.checkArgument(!"".equals(str));
        if (str == null && j == -1) {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.zzae = j;
        this.zzf = j2;
        this.zzaf = i;
    }

    public int hashCode() {
        if (this.zzae == -1) {
            return this.zzad.hashCode();
        }
        String strValueOf = String.valueOf(String.valueOf(this.zzf));
        String strValueOf2 = String.valueOf(String.valueOf(this.zzae));
        return (strValueOf2.length() != 0 ? strValueOf.concat(strValueOf2) : new String(strValueOf)).hashCode();
    }

    public boolean equals(Object obj) {
        String str;
        if (obj != null && obj.getClass() == DriveId.class) {
            DriveId driveId = (DriveId) obj;
            if (driveId.zzf != this.zzf) {
                return false;
            }
            if (driveId.zzae == -1 && this.zzae == -1) {
                return driveId.zzad.equals(this.zzad);
            }
            String str2 = this.zzad;
            if (str2 != null && (str = driveId.zzad) != null) {
                return driveId.zzae == this.zzae && str.equals(str2);
            }
            if (driveId.zzae == this.zzae) {
                return true;
            }
        }
        return false;
    }

    public final String encodeToString() {
        if (this.zzh == null) {
            zzfb.zza zzaVarZzm = zzfb.zzan().zzm(1);
            String str = this.zzad;
            if (str == null) {
                str = "";
            }
            String strValueOf = String.valueOf(Base64.encodeToString(((zzfb) ((zzkk) zzaVarZzm.zze(str).zzg(this.zzae).zzh(this.zzf).zzn(this.zzaf).zzdf())).toByteArray(), 10));
            this.zzh = strValueOf.length() != 0 ? "DriveId:".concat(strValueOf) : new String("DriveId:");
        }
        return this.zzh;
    }

    public final String toInvariantString() {
        if (this.zzag == null) {
            this.zzag = Base64.encodeToString(((zzfd) ((zzkk) zzfd.zzap().zzi(this.zzae).zzj(this.zzf).zzdf())).toByteArray(), 10);
        }
        return this.zzag;
    }

    public String toString() {
        return encodeToString();
    }

    public static DriveId decodeFromString(String str) {
        boolean zStartsWith = str.startsWith("DriveId:");
        String strValueOf = String.valueOf(str);
        Preconditions.checkArgument(zStartsWith, strValueOf.length() != 0 ? "Invalid DriveId: ".concat(strValueOf) : new String("Invalid DriveId: "));
        return zza(Base64.decode(str.substring(8), 10));
    }

    @VisibleForTesting
    private static DriveId zza(byte[] bArr) {
        try {
            zzfb zzfbVarZza = zzfb.zza(bArr, zzjx.zzcj());
            return new DriveId("".equals(zzfbVarZza.getResourceId()) ? null : zzfbVarZza.getResourceId(), zzfbVarZza.zzal(), zzfbVarZza.zzam(), zzfbVarZza.getResourceType());
        } catch (zzkq unused) {
            throw new IllegalArgumentException();
        }
    }
}
