package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzil implements Parcelable, Comparator<zza> {
    public static final Parcelable.Creator<zzil> CREATOR = new zzin();
    private int zzafu;
    private final zza[] zzamf;
    public final int zzamg;

    public zzil(zza... zzaVarArr) {
        this(true, zzaVarArr);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
    public static final class zza implements Parcelable {
        public static final Parcelable.Creator<zza> CREATOR = new zzip();
        private final byte[] data;
        private final String mimeType;
        private final UUID uuid;
        private int zzafu;
        public final boolean zzamk;

        public zza(UUID uuid, String str, byte[] bArr) {
            this(uuid, str, bArr, false);
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        private zza(UUID uuid, String str, byte[] bArr, boolean z) {
            this.uuid = (UUID) zznt.checkNotNull(uuid);
            this.mimeType = (String) zznt.checkNotNull(str);
            this.data = (byte[]) zznt.checkNotNull(bArr);
            this.zzamk = false;
        }

        zza(Parcel parcel) {
            this.uuid = new UUID(parcel.readLong(), parcel.readLong());
            this.mimeType = parcel.readString();
            this.data = parcel.createByteArray();
            this.zzamk = parcel.readByte() != 0;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            zza zzaVar = (zza) obj;
            return this.mimeType.equals(zzaVar.mimeType) && zzoh.zza(this.uuid, zzaVar.uuid) && Arrays.equals(this.data, zzaVar.data);
        }

        public final int hashCode() {
            if (this.zzafu == 0) {
                this.zzafu = (((this.uuid.hashCode() * 31) + this.mimeType.hashCode()) * 31) + Arrays.hashCode(this.data);
            }
            return this.zzafu;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeLong(this.uuid.getMostSignificantBits());
            parcel.writeLong(this.uuid.getLeastSignificantBits());
            parcel.writeString(this.mimeType);
            parcel.writeByteArray(this.data);
            parcel.writeByte(this.zzamk ? (byte) 1 : (byte) 0);
        }
    }

    private zzil(boolean z, zza... zzaVarArr) {
        zza[] zzaVarArr2 = (zza[]) zzaVarArr.clone();
        Arrays.sort(zzaVarArr2, this);
        for (int i = 1; i < zzaVarArr2.length; i++) {
            if (zzaVarArr2[i - 1].uuid.equals(zzaVarArr2[i].uuid)) {
                String strValueOf = String.valueOf(zzaVarArr2[i].uuid);
                StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 25);
                sb.append("Duplicate data for uuid: ");
                sb.append(strValueOf);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        this.zzamf = zzaVarArr2;
        this.zzamg = zzaVarArr2.length;
    }

    zzil(Parcel parcel) {
        this.zzamf = (zza[]) parcel.createTypedArray(zza.CREATOR);
        this.zzamg = this.zzamf.length;
    }

    public final zza zzx(int i) {
        return this.zzamf[i];
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            this.zzafu = Arrays.hashCode(this.zzamf);
        }
        return this.zzafu;
    }

    @Override // java.util.Comparator
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.zzamf, ((zzil) obj).zzamf);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.zzamf, 0);
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(zza zzaVar, zza zzaVar2) {
        zza zzaVar3 = zzaVar;
        zza zzaVar4 = zzaVar2;
        if (zzgc.zzacf.equals(zzaVar3.uuid)) {
            return zzgc.zzacf.equals(zzaVar4.uuid) ? 0 : 1;
        }
        return zzaVar3.uuid.compareTo(zzaVar4.uuid);
    }
}
