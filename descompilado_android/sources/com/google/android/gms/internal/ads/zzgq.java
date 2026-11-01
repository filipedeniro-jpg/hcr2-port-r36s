package com.google.android.gms.internal.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzgq implements Parcelable {
    public static final Parcelable.Creator<zzgq> CREATOR = new zzgp();
    public final int height;
    public final int width;
    private final String zzaew;
    public final int zzaex;
    public final String zzaey;
    private final zzkv zzaez;
    private final String zzafa;
    public final String zzafb;
    public final int zzafc;
    public final List<byte[]> zzafd;
    public final zzil zzafe;
    public final float zzaff;
    public final int zzafg;
    public final float zzafh;
    private final int zzafi;
    private final byte[] zzafj;
    private final zzoi zzafk;
    public final int zzafl;
    public final int zzafm;
    public final int zzafn;
    private final int zzafo;
    private final int zzafp;
    public final long zzafq;
    public final int zzafr;
    public final String zzafs;
    private final int zzaft;
    private int zzafu;

    public static zzgq zza(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, byte[] bArr, int i6, zzoi zzoiVar, zzil zzilVar) {
        return new zzgq(str, null, str2, null, -1, i2, i3, i4, -1.0f, i5, f2, bArr, i6, zzoiVar, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, list, zzilVar, null);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public static zzgq zza(String str, String str2, String str3, int i, int i2, int i3, int i4, List<byte[]> list, zzil zzilVar, int i5, String str4) {
        return zza(str, str2, null, -1, -1, i3, i4, -1, null, zzilVar, 0, str4);
    }

    public static zzgq zza(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, List<byte[]> list, zzil zzilVar, int i6, String str4) {
        return new zzgq(str, null, str2, null, -1, i2, -1, -1, -1.0f, -1, -1.0f, null, -1, null, i3, i4, i5, -1, -1, i6, str4, -1, Long.MAX_VALUE, list, zzilVar, null);
    }

    public static zzgq zza(String str, String str2, String str3, int i, int i2, String str4, zzil zzilVar) {
        return zza(str, str2, null, -1, i2, str4, -1, zzilVar, Long.MAX_VALUE, Collections.emptyList());
    }

    public static zzgq zza(String str, String str2, String str3, int i, int i2, String str4, int i3, zzil zzilVar, long j, List<byte[]> list) {
        return new zzgq(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, i2, str4, -1, j, list, zzilVar, null);
    }

    public static zzgq zza(String str, String str2, String str3, int i, List<byte[]> list, String str4, zzil zzilVar) {
        return new zzgq(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, str4, -1, Long.MAX_VALUE, list, zzilVar, null);
    }

    public static zzgq zza(String str, String str2, String str3, int i, zzil zzilVar) {
        return new zzgq(str, null, str2, null, -1, -1, -1, -1, -1.0f, -1, -1.0f, null, -1, null, -1, -1, -1, -1, -1, 0, null, -1, Long.MAX_VALUE, null, null, null);
    }

    private zzgq(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, float f, int i5, float f2, byte[] bArr, int i6, zzoi zzoiVar, int i7, int i8, int i9, int i10, int i11, int i12, String str5, int i13, long j, List<byte[]> list, zzil zzilVar, zzkv zzkvVar) {
        this.zzaew = str;
        this.zzafa = str2;
        this.zzafb = str3;
        this.zzaey = str4;
        this.zzaex = i;
        this.zzafc = i2;
        this.width = i3;
        this.height = i4;
        this.zzaff = f;
        this.zzafg = i5;
        this.zzafh = f2;
        this.zzafj = bArr;
        this.zzafi = i6;
        this.zzafk = zzoiVar;
        this.zzafl = i7;
        this.zzafm = i8;
        this.zzafn = i9;
        this.zzafo = i10;
        this.zzafp = i11;
        this.zzafr = i12;
        this.zzafs = str5;
        this.zzaft = i13;
        this.zzafq = j;
        this.zzafd = list == null ? Collections.emptyList() : list;
        this.zzafe = zzilVar;
        this.zzaez = zzkvVar;
    }

    zzgq(Parcel parcel) {
        this.zzaew = parcel.readString();
        this.zzafa = parcel.readString();
        this.zzafb = parcel.readString();
        this.zzaey = parcel.readString();
        this.zzaex = parcel.readInt();
        this.zzafc = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.zzaff = parcel.readFloat();
        this.zzafg = parcel.readInt();
        this.zzafh = parcel.readFloat();
        this.zzafj = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.zzafi = parcel.readInt();
        this.zzafk = (zzoi) parcel.readParcelable(zzoi.class.getClassLoader());
        this.zzafl = parcel.readInt();
        this.zzafm = parcel.readInt();
        this.zzafn = parcel.readInt();
        this.zzafo = parcel.readInt();
        this.zzafp = parcel.readInt();
        this.zzafr = parcel.readInt();
        this.zzafs = parcel.readString();
        this.zzaft = parcel.readInt();
        this.zzafq = parcel.readLong();
        int i = parcel.readInt();
        this.zzafd = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            this.zzafd.add(parcel.createByteArray());
        }
        this.zzafe = (zzil) parcel.readParcelable(zzil.class.getClassLoader());
        this.zzaez = (zzkv) parcel.readParcelable(zzkv.class.getClassLoader());
    }

    public final zzgq zzo(int i) {
        return new zzgq(this.zzaew, this.zzafa, this.zzafb, this.zzaey, this.zzaex, i, this.width, this.height, this.zzaff, this.zzafg, this.zzafh, this.zzafj, this.zzafi, this.zzafk, this.zzafl, this.zzafm, this.zzafn, this.zzafo, this.zzafp, this.zzafr, this.zzafs, this.zzaft, this.zzafq, this.zzafd, this.zzafe, this.zzaez);
    }

    public final zzgq zzdm(long j) {
        return new zzgq(this.zzaew, this.zzafa, this.zzafb, this.zzaey, this.zzaex, this.zzafc, this.width, this.height, this.zzaff, this.zzafg, this.zzafh, this.zzafj, this.zzafi, this.zzafk, this.zzafl, this.zzafm, this.zzafn, this.zzafo, this.zzafp, this.zzafr, this.zzafs, this.zzaft, j, this.zzafd, this.zzafe, this.zzaez);
    }

    public final zzgq zza(int i, int i2) {
        return new zzgq(this.zzaew, this.zzafa, this.zzafb, this.zzaey, this.zzaex, this.zzafc, this.width, this.height, this.zzaff, this.zzafg, this.zzafh, this.zzafj, this.zzafi, this.zzafk, this.zzafl, this.zzafm, this.zzafn, i, i2, this.zzafr, this.zzafs, this.zzaft, this.zzafq, this.zzafd, this.zzafe, this.zzaez);
    }

    public final zzgq zza(zzkv zzkvVar) {
        return new zzgq(this.zzaew, this.zzafa, this.zzafb, this.zzaey, this.zzaex, this.zzafc, this.width, this.height, this.zzaff, this.zzafg, this.zzafh, this.zzafj, this.zzafi, this.zzafk, this.zzafl, this.zzafm, this.zzafn, this.zzafo, this.zzafp, this.zzafr, this.zzafs, this.zzaft, this.zzafq, this.zzafd, this.zzafe, zzkvVar);
    }

    public final int zzeg() {
        int i;
        int i2 = this.width;
        if (i2 == -1 || (i = this.height) == -1) {
            return -1;
        }
        return i2 * i;
    }

    @SuppressLint({"InlinedApi"})
    @TargetApi(16)
    public final MediaFormat zzeh() {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", this.zzafb);
        String str = this.zzafs;
        if (str != null) {
            mediaFormat.setString("language", str);
        }
        zza(mediaFormat, "max-input-size", this.zzafc);
        zza(mediaFormat, "width", this.width);
        zza(mediaFormat, "height", this.height);
        float f = this.zzaff;
        if (f != -1.0f) {
            mediaFormat.setFloat("frame-rate", f);
        }
        zza(mediaFormat, "rotation-degrees", this.zzafg);
        zza(mediaFormat, "channel-count", this.zzafl);
        zza(mediaFormat, "sample-rate", this.zzafm);
        zza(mediaFormat, "encoder-delay", this.zzafo);
        zza(mediaFormat, "encoder-padding", this.zzafp);
        for (int i = 0; i < this.zzafd.size(); i++) {
            StringBuilder sb = new StringBuilder(15);
            sb.append("csd-");
            sb.append(i);
            mediaFormat.setByteBuffer(sb.toString(), ByteBuffer.wrap(this.zzafd.get(i)));
        }
        zzoi zzoiVar = this.zzafk;
        if (zzoiVar != null) {
            zza(mediaFormat, "color-transfer", zzoiVar.zzapw);
            zza(mediaFormat, "color-standard", zzoiVar.zzapv);
            zza(mediaFormat, "color-range", zzoiVar.zzapx);
            byte[] bArr = zzoiVar.zzbgx;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
        return mediaFormat;
    }

    public final String toString() {
        String str = this.zzaew;
        String str2 = this.zzafa;
        String str3 = this.zzafb;
        int i = this.zzaex;
        String str4 = this.zzafs;
        int i2 = this.width;
        int i3 = this.height;
        float f = this.zzaff;
        int i4 = this.zzafl;
        int i5 = this.zzafm;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 100 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append("Format(");
        sb.append(str);
        sb.append(", ");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(i);
        sb.append(", ");
        sb.append(str4);
        sb.append(", [");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(", ");
        sb.append(f);
        sb.append("], [");
        sb.append(i4);
        sb.append(", ");
        sb.append(i5);
        sb.append("])");
        return sb.toString();
    }

    public final int hashCode() {
        if (this.zzafu == 0) {
            String str = this.zzaew;
            int iHashCode = ((str == null ? 0 : str.hashCode()) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31;
            String str2 = this.zzafa;
            int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.zzafb;
            int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.zzaey;
            int iHashCode4 = (((((((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.zzaex) * 31) + this.width) * 31) + this.height) * 31) + this.zzafl) * 31) + this.zzafm) * 31;
            String str5 = this.zzafs;
            int iHashCode5 = (((iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.zzaft) * 31;
            zzil zzilVar = this.zzafe;
            int iHashCode6 = (iHashCode5 + (zzilVar == null ? 0 : zzilVar.hashCode())) * 31;
            zzkv zzkvVar = this.zzaez;
            this.zzafu = iHashCode6 + (zzkvVar != null ? zzkvVar.hashCode() : 0);
        }
        return this.zzafu;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzgq zzgqVar = (zzgq) obj;
            if (this.zzaex == zzgqVar.zzaex && this.zzafc == zzgqVar.zzafc && this.width == zzgqVar.width && this.height == zzgqVar.height && this.zzaff == zzgqVar.zzaff && this.zzafg == zzgqVar.zzafg && this.zzafh == zzgqVar.zzafh && this.zzafi == zzgqVar.zzafi && this.zzafl == zzgqVar.zzafl && this.zzafm == zzgqVar.zzafm && this.zzafn == zzgqVar.zzafn && this.zzafo == zzgqVar.zzafo && this.zzafp == zzgqVar.zzafp && this.zzafq == zzgqVar.zzafq && this.zzafr == zzgqVar.zzafr && zzoh.zza(this.zzaew, zzgqVar.zzaew) && zzoh.zza(this.zzafs, zzgqVar.zzafs) && this.zzaft == zzgqVar.zzaft && zzoh.zza(this.zzafa, zzgqVar.zzafa) && zzoh.zza(this.zzafb, zzgqVar.zzafb) && zzoh.zza(this.zzaey, zzgqVar.zzaey) && zzoh.zza(this.zzafe, zzgqVar.zzafe) && zzoh.zza(this.zzaez, zzgqVar.zzaez) && zzoh.zza(this.zzafk, zzgqVar.zzafk) && Arrays.equals(this.zzafj, zzgqVar.zzafj) && this.zzafd.size() == zzgqVar.zzafd.size()) {
                for (int i = 0; i < this.zzafd.size(); i++) {
                    if (!Arrays.equals(this.zzafd.get(i), zzgqVar.zzafd.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @TargetApi(16)
    private static void zza(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzaew);
        parcel.writeString(this.zzafa);
        parcel.writeString(this.zzafb);
        parcel.writeString(this.zzaey);
        parcel.writeInt(this.zzaex);
        parcel.writeInt(this.zzafc);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.zzaff);
        parcel.writeInt(this.zzafg);
        parcel.writeFloat(this.zzafh);
        parcel.writeInt(this.zzafj != null ? 1 : 0);
        byte[] bArr = this.zzafj;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.zzafi);
        parcel.writeParcelable(this.zzafk, i);
        parcel.writeInt(this.zzafl);
        parcel.writeInt(this.zzafm);
        parcel.writeInt(this.zzafn);
        parcel.writeInt(this.zzafo);
        parcel.writeInt(this.zzafp);
        parcel.writeInt(this.zzafr);
        parcel.writeString(this.zzafs);
        parcel.writeInt(this.zzaft);
        parcel.writeLong(this.zzafq);
        int size = this.zzafd.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeByteArray(this.zzafd.get(i2));
        }
        parcel.writeParcelable(this.zzafe, 0);
        parcel.writeParcelable(this.zzaez, 0);
    }
}
