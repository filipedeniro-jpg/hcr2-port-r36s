package com.google.android.gms.internal.ads;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
@SafeParcelable.Class(creator = "AdRequestInfoParcelCreator")
public final class zzanv extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzanv> CREATOR = new zzanu();

    @SafeParcelable.Field(id = 6)
    private final ApplicationInfo applicationInfo;

    @SafeParcelable.Field(id = 1)
    private final int versionCode;

    @SafeParcelable.Field(id = 11)
    private final zzawv zzblh;

    @SafeParcelable.Field(id = 4)
    private final zztw zzbli;

    @SafeParcelable.Field(id = 20)
    private final float zzbod;

    @SafeParcelable.Field(id = 5)
    private final String zzccm;

    @SafeParcelable.Field(id = 10)
    private final String zzcdj;

    @SafeParcelable.Field(id = 42)
    private final boolean zzdcn;

    @SafeParcelable.Field(id = 29)
    private final zzaai zzddy;

    @SafeParcelable.Field(id = 14)
    private final List<String> zzddz;

    @SafeParcelable.Field(id = 18)
    private final int zzdgc;

    @SafeParcelable.Field(id = 19)
    private final int zzdgd;

    @Nullable
    @SafeParcelable.Field(id = 2)
    private final Bundle zzdih;

    @SafeParcelable.Field(id = 3)
    private final zztp zzdii;

    @Nullable
    @SafeParcelable.Field(id = 7)
    private final PackageInfo zzdij;

    @SafeParcelable.Field(id = 8)
    private final String zzdik;

    @SafeParcelable.Field(id = 9)
    private final String zzdil;

    @SafeParcelable.Field(id = 12)
    private final Bundle zzdim;

    @SafeParcelable.Field(id = 13)
    private final int zzdin;

    @SafeParcelable.Field(id = 15)
    private final Bundle zzdio;

    @SafeParcelable.Field(id = 16)
    private final boolean zzdip;

    @SafeParcelable.Field(id = 21)
    private final String zzdiq;

    @SafeParcelable.Field(id = 25)
    private final long zzdir;

    @SafeParcelable.Field(id = 26)
    private final String zzdis;

    @Nullable
    @SafeParcelable.Field(id = 27)
    private final List<String> zzdit;

    @SafeParcelable.Field(id = 28)
    private final String zzdiu;

    @SafeParcelable.Field(id = 30)
    private final List<String> zzdiv;

    @SafeParcelable.Field(id = 31)
    private final long zzdiw;

    @SafeParcelable.Field(id = 33)
    private final String zzdix;

    @SafeParcelable.Field(id = 34)
    private final float zzdiy;

    @SafeParcelable.Field(id = 35)
    private final int zzdiz;

    @SafeParcelable.Field(id = 36)
    private final int zzdja;

    @SafeParcelable.Field(id = 37)
    private final boolean zzdjb;

    @SafeParcelable.Field(id = 38)
    private final boolean zzdjc;

    @SafeParcelable.Field(id = 39)
    private final String zzdjd;

    @SafeParcelable.Field(id = 40)
    private final boolean zzdje;

    @SafeParcelable.Field(id = 41)
    private final String zzdjf;

    @SafeParcelable.Field(id = 43)
    private final int zzdjg;

    @SafeParcelable.Field(id = 44)
    private final Bundle zzdjh;

    @SafeParcelable.Field(id = 45)
    private final String zzdji;

    @Nullable
    @SafeParcelable.Field(id = 46)
    private final zzwq zzdjj;

    @SafeParcelable.Field(id = 47)
    private final boolean zzdjk;

    @SafeParcelable.Field(id = 48)
    private final Bundle zzdjl;

    @Nullable
    @SafeParcelable.Field(id = 49)
    private final String zzdjm;

    @Nullable
    @SafeParcelable.Field(id = 50)
    private final String zzdjn;

    @Nullable
    @SafeParcelable.Field(id = 51)
    private final String zzdjo;

    @SafeParcelable.Field(id = 52)
    private final boolean zzdjp;

    @SafeParcelable.Field(id = 53)
    private final List<Integer> zzdjq;

    @SafeParcelable.Field(id = 54)
    private final String zzdjr;

    @SafeParcelable.Field(id = 55)
    private final List<String> zzdjs;

    @SafeParcelable.Field(id = 56)
    private final int zzdjt;

    @SafeParcelable.Field(id = 57)
    private final boolean zzdju;

    @SafeParcelable.Field(id = 58)
    private final boolean zzdjv;

    @SafeParcelable.Field(id = 59)
    private final boolean zzdjw;

    @SafeParcelable.Field(id = 60)
    private final ArrayList<String> zzdjx;

    @SafeParcelable.Field(id = 61)
    private final String zzdjy;

    @SafeParcelable.Field(id = 63)
    private final zzafj zzdjz;

    @Nullable
    @SafeParcelable.Field(id = 64)
    private final String zzdka;

    @SafeParcelable.Field(id = 65)
    private final Bundle zzdkb;

    @SafeParcelable.Constructor
    zzanv(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) Bundle bundle, @SafeParcelable.Param(id = 3) zztp zztpVar, @SafeParcelable.Param(id = 4) zztw zztwVar, @SafeParcelable.Param(id = 5) String str, @SafeParcelable.Param(id = 6) ApplicationInfo applicationInfo, @SafeParcelable.Param(id = 7) PackageInfo packageInfo, @SafeParcelable.Param(id = 8) String str2, @SafeParcelable.Param(id = 9) String str3, @SafeParcelable.Param(id = 10) String str4, @SafeParcelable.Param(id = 11) zzawv zzawvVar, @SafeParcelable.Param(id = 12) Bundle bundle2, @SafeParcelable.Param(id = 13) int i2, @SafeParcelable.Param(id = 14) List<String> list, @SafeParcelable.Param(id = 15) Bundle bundle3, @SafeParcelable.Param(id = 16) boolean z, @SafeParcelable.Param(id = 18) int i3, @SafeParcelable.Param(id = 19) int i4, @SafeParcelable.Param(id = 20) float f, @SafeParcelable.Param(id = 21) String str5, @SafeParcelable.Param(id = 25) long j, @SafeParcelable.Param(id = 26) String str6, @SafeParcelable.Param(id = 27) List<String> list2, @SafeParcelable.Param(id = 28) String str7, @SafeParcelable.Param(id = 29) zzaai zzaaiVar, @SafeParcelable.Param(id = 30) List<String> list3, @SafeParcelable.Param(id = 31) long j2, @SafeParcelable.Param(id = 33) String str8, @SafeParcelable.Param(id = 34) float f2, @SafeParcelable.Param(id = 40) boolean z2, @SafeParcelable.Param(id = 35) int i5, @SafeParcelable.Param(id = 36) int i6, @SafeParcelable.Param(id = 37) boolean z3, @SafeParcelable.Param(id = 38) boolean z4, @SafeParcelable.Param(id = 39) String str9, @SafeParcelable.Param(id = 41) String str10, @SafeParcelable.Param(id = 42) boolean z5, @SafeParcelable.Param(id = 43) int i7, @SafeParcelable.Param(id = 44) Bundle bundle4, @SafeParcelable.Param(id = 45) String str11, @SafeParcelable.Param(id = 46) zzwq zzwqVar, @SafeParcelable.Param(id = 47) boolean z6, @SafeParcelable.Param(id = 48) Bundle bundle5, @Nullable @SafeParcelable.Param(id = 49) String str12, @Nullable @SafeParcelable.Param(id = 50) String str13, @Nullable @SafeParcelable.Param(id = 51) String str14, @SafeParcelable.Param(id = 52) boolean z7, @SafeParcelable.Param(id = 53) List<Integer> list4, @SafeParcelable.Param(id = 54) String str15, @SafeParcelable.Param(id = 55) List<String> list5, @SafeParcelable.Param(id = 56) int i8, @SafeParcelable.Param(id = 57) boolean z8, @SafeParcelable.Param(id = 58) boolean z9, @SafeParcelable.Param(id = 59) boolean z10, @SafeParcelable.Param(id = 60) ArrayList<String> arrayList, @SafeParcelable.Param(id = 61) String str16, @SafeParcelable.Param(id = 63) zzafj zzafjVar, @Nullable @SafeParcelable.Param(id = 64) String str17, @SafeParcelable.Param(id = 65) Bundle bundle6) {
        this.versionCode = i;
        this.zzdih = bundle;
        this.zzdii = zztpVar;
        this.zzbli = zztwVar;
        this.zzccm = str;
        this.applicationInfo = applicationInfo;
        this.zzdij = packageInfo;
        this.zzdik = str2;
        this.zzdil = str3;
        this.zzcdj = str4;
        this.zzblh = zzawvVar;
        this.zzdim = bundle2;
        this.zzdin = i2;
        this.zzddz = list;
        this.zzdiv = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzdio = bundle3;
        this.zzdip = z;
        this.zzdgc = i3;
        this.zzdgd = i4;
        this.zzbod = f;
        this.zzdiq = str5;
        this.zzdir = j;
        this.zzdis = str6;
        this.zzdit = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzdiu = str7;
        this.zzddy = zzaaiVar;
        this.zzdiw = j2;
        this.zzdix = str8;
        this.zzdiy = f2;
        this.zzdje = z2;
        this.zzdiz = i5;
        this.zzdja = i6;
        this.zzdjb = z3;
        this.zzdjc = z4;
        this.zzdjd = str9;
        this.zzdjf = str10;
        this.zzdcn = z5;
        this.zzdjg = i7;
        this.zzdjh = bundle4;
        this.zzdji = str11;
        this.zzdjj = zzwqVar;
        this.zzdjk = z6;
        this.zzdjl = bundle5;
        this.zzdjm = str12;
        this.zzdjn = str13;
        this.zzdjo = str14;
        this.zzdjp = z7;
        this.zzdjq = list4;
        this.zzdjr = str15;
        this.zzdjs = list5;
        this.zzdjt = i8;
        this.zzdju = z8;
        this.zzdjv = z9;
        this.zzdjw = z10;
        this.zzdjx = arrayList;
        this.zzdjy = str16;
        this.zzdjz = zzafjVar;
        this.zzdka = str17;
        this.zzdkb = bundle6;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzdih, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzdii, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbli, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzccm, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.applicationInfo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdij, i, false);
        SafeParcelWriter.writeString(parcel, 8, this.zzdik, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzdil, false);
        SafeParcelWriter.writeString(parcel, 10, this.zzcdj, false);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzblh, i, false);
        SafeParcelWriter.writeBundle(parcel, 12, this.zzdim, false);
        SafeParcelWriter.writeInt(parcel, 13, this.zzdin);
        SafeParcelWriter.writeStringList(parcel, 14, this.zzddz, false);
        SafeParcelWriter.writeBundle(parcel, 15, this.zzdio, false);
        SafeParcelWriter.writeBoolean(parcel, 16, this.zzdip);
        SafeParcelWriter.writeInt(parcel, 18, this.zzdgc);
        SafeParcelWriter.writeInt(parcel, 19, this.zzdgd);
        SafeParcelWriter.writeFloat(parcel, 20, this.zzbod);
        SafeParcelWriter.writeString(parcel, 21, this.zzdiq, false);
        SafeParcelWriter.writeLong(parcel, 25, this.zzdir);
        SafeParcelWriter.writeString(parcel, 26, this.zzdis, false);
        SafeParcelWriter.writeStringList(parcel, 27, this.zzdit, false);
        SafeParcelWriter.writeString(parcel, 28, this.zzdiu, false);
        SafeParcelWriter.writeParcelable(parcel, 29, this.zzddy, i, false);
        SafeParcelWriter.writeStringList(parcel, 30, this.zzdiv, false);
        SafeParcelWriter.writeLong(parcel, 31, this.zzdiw);
        SafeParcelWriter.writeString(parcel, 33, this.zzdix, false);
        SafeParcelWriter.writeFloat(parcel, 34, this.zzdiy);
        SafeParcelWriter.writeInt(parcel, 35, this.zzdiz);
        SafeParcelWriter.writeInt(parcel, 36, this.zzdja);
        SafeParcelWriter.writeBoolean(parcel, 37, this.zzdjb);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzdjc);
        SafeParcelWriter.writeString(parcel, 39, this.zzdjd, false);
        SafeParcelWriter.writeBoolean(parcel, 40, this.zzdje);
        SafeParcelWriter.writeString(parcel, 41, this.zzdjf, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzdcn);
        SafeParcelWriter.writeInt(parcel, 43, this.zzdjg);
        SafeParcelWriter.writeBundle(parcel, 44, this.zzdjh, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzdji, false);
        SafeParcelWriter.writeParcelable(parcel, 46, this.zzdjj, i, false);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzdjk);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzdjl, false);
        SafeParcelWriter.writeString(parcel, 49, this.zzdjm, false);
        SafeParcelWriter.writeString(parcel, 50, this.zzdjn, false);
        SafeParcelWriter.writeString(parcel, 51, this.zzdjo, false);
        SafeParcelWriter.writeBoolean(parcel, 52, this.zzdjp);
        SafeParcelWriter.writeIntegerList(parcel, 53, this.zzdjq, false);
        SafeParcelWriter.writeString(parcel, 54, this.zzdjr, false);
        SafeParcelWriter.writeStringList(parcel, 55, this.zzdjs, false);
        SafeParcelWriter.writeInt(parcel, 56, this.zzdjt);
        SafeParcelWriter.writeBoolean(parcel, 57, this.zzdju);
        SafeParcelWriter.writeBoolean(parcel, 58, this.zzdjv);
        SafeParcelWriter.writeBoolean(parcel, 59, this.zzdjw);
        SafeParcelWriter.writeStringList(parcel, 60, this.zzdjx, false);
        SafeParcelWriter.writeString(parcel, 61, this.zzdjy, false);
        SafeParcelWriter.writeParcelable(parcel, 63, this.zzdjz, i, false);
        SafeParcelWriter.writeString(parcel, 64, this.zzdka, false);
        SafeParcelWriter.writeBundle(parcel, 65, this.zzdkb, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
