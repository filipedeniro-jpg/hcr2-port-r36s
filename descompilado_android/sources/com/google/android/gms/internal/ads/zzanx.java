package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Collections;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@ParametersAreNonnullByDefault
@SafeParcelable.Class(creator = "AdResponseParcelCreator")
public final class zzanx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzanx> CREATOR = new zzanw();

    @SafeParcelable.Field(id = 5)
    private final int errorCode;

    @SafeParcelable.Field(id = 12)
    private final int orientation;

    @SafeParcelable.Field(id = 1)
    private final int versionCode;

    @Nullable
    @SafeParcelable.Field(id = 37)
    private final zzanz zzbkn;

    @SafeParcelable.Field(id = 49)
    private final boolean zzblf;

    @SafeParcelable.Field(id = 53)
    private final boolean zzblg;

    @SafeParcelable.Field(id = 23)
    private final boolean zzbnj;

    @SafeParcelable.Field(id = 30)
    private final String zzbzx;

    @SafeParcelable.Field(id = 31)
    private final boolean zzccg;

    @SafeParcelable.Field(id = 32)
    private final boolean zzcch;

    @SafeParcelable.Field(id = 4)
    private final List<String> zzdci;

    @SafeParcelable.Field(id = 6)
    private final List<String> zzdcj;

    @SafeParcelable.Field(id = 52)
    private final List<String> zzdck;

    @SafeParcelable.Field(id = 40)
    private final List<String> zzdcm;

    @SafeParcelable.Field(id = 42)
    private final boolean zzdcn;

    @SafeParcelable.Field(id = 11)
    private final long zzdcp;

    @SafeParcelable.Field(id = 2)
    private final String zzdhn;

    @SafeParcelable.Field(id = 24)
    private final boolean zzdip;

    @SafeParcelable.Field(id = 38)
    private final boolean zzdjc;

    @Nullable
    @SafeParcelable.Field(id = 39)
    private String zzdjd;

    @SafeParcelable.Field(id = 47)
    private final boolean zzdjp;

    @SafeParcelable.Field(id = 3)
    private String zzdkc;

    @SafeParcelable.Field(id = 7)
    private final long zzdkd;

    @SafeParcelable.Field(id = 8)
    private final boolean zzdke;

    @SafeParcelable.Field(id = 9)
    private final long zzdkf;

    @SafeParcelable.Field(id = 10)
    private final List<String> zzdkg;

    @SafeParcelable.Field(id = 13)
    private final String zzdkh;

    @SafeParcelable.Field(id = 14)
    private final long zzdki;

    @SafeParcelable.Field(id = 15)
    private final String zzdkj;

    @SafeParcelable.Field(id = 18)
    private final boolean zzdkk;

    @SafeParcelable.Field(id = 19)
    private final String zzdkl;

    @SafeParcelable.Field(id = 21)
    private final String zzdkm;

    @SafeParcelable.Field(id = 22)
    private final boolean zzdkn;

    @SafeParcelable.Field(id = 25)
    private final boolean zzdko;

    @SafeParcelable.Field(id = 26)
    private final boolean zzdkp;

    @SafeParcelable.Field(id = 28)
    private zzaoj zzdkq;

    @SafeParcelable.Field(id = 29)
    private String zzdkr;

    @Nullable
    @SafeParcelable.Field(id = 33)
    private final zzaqd zzdks;

    @Nullable
    @SafeParcelable.Field(id = 34)
    private final List<String> zzdkt;

    @Nullable
    @SafeParcelable.Field(id = 35)
    private final List<String> zzdku;

    @SafeParcelable.Field(id = 36)
    private final boolean zzdkv;

    @Nullable
    @SafeParcelable.Field(id = 43)
    private final String zzdkw;

    @Nullable
    @SafeParcelable.Field(id = 44)
    private final zzarn zzdkx;

    @Nullable
    @SafeParcelable.Field(id = 45)
    private final String zzdky;

    @SafeParcelable.Field(id = 46)
    private final boolean zzdkz;

    @SafeParcelable.Field(id = 48)
    private Bundle zzdla;

    @SafeParcelable.Field(id = 50)
    private final int zzdlb;

    @SafeParcelable.Field(id = 51)
    private final boolean zzdlc;

    @Nullable
    @SafeParcelable.Field(id = 54)
    private final String zzdld;

    @Nullable
    @SafeParcelable.Field(id = 55)
    private String zzdle;

    @SafeParcelable.Field(id = 56)
    private boolean zzdlf;

    @SafeParcelable.Field(id = 57)
    private boolean zzdlg;

    @SafeParcelable.Constructor
    zzanx(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) List<String> list, @SafeParcelable.Param(id = 5) int i2, @SafeParcelable.Param(id = 6) List<String> list2, @SafeParcelable.Param(id = 7) long j, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) long j2, @SafeParcelable.Param(id = 10) List<String> list3, @SafeParcelable.Param(id = 11) long j3, @SafeParcelable.Param(id = 12) int i3, @SafeParcelable.Param(id = 13) String str3, @SafeParcelable.Param(id = 14) long j4, @SafeParcelable.Param(id = 15) String str4, @SafeParcelable.Param(id = 18) boolean z2, @SafeParcelable.Param(id = 19) String str5, @SafeParcelable.Param(id = 21) String str6, @SafeParcelable.Param(id = 22) boolean z3, @SafeParcelable.Param(id = 23) boolean z4, @SafeParcelable.Param(id = 24) boolean z5, @SafeParcelable.Param(id = 25) boolean z6, @SafeParcelable.Param(id = 26) boolean z7, @SafeParcelable.Param(id = 28) zzaoj zzaojVar, @SafeParcelable.Param(id = 29) String str7, @SafeParcelable.Param(id = 30) String str8, @SafeParcelable.Param(id = 31) boolean z8, @SafeParcelable.Param(id = 32) boolean z9, @SafeParcelable.Param(id = 33) zzaqd zzaqdVar, @SafeParcelable.Param(id = 34) List<String> list4, @SafeParcelable.Param(id = 35) List<String> list5, @SafeParcelable.Param(id = 36) boolean z10, @SafeParcelable.Param(id = 37) zzanz zzanzVar, @SafeParcelable.Param(id = 38) boolean z11, @SafeParcelable.Param(id = 39) String str9, @SafeParcelable.Param(id = 40) List<String> list6, @SafeParcelable.Param(id = 42) boolean z12, @SafeParcelable.Param(id = 43) String str10, @SafeParcelable.Param(id = 44) zzarn zzarnVar, @SafeParcelable.Param(id = 45) String str11, @SafeParcelable.Param(id = 46) boolean z13, @SafeParcelable.Param(id = 47) boolean z14, @SafeParcelable.Param(id = 48) Bundle bundle, @SafeParcelable.Param(id = 49) boolean z15, @SafeParcelable.Param(id = 50) int i4, @SafeParcelable.Param(id = 51) boolean z16, @SafeParcelable.Param(id = 52) List<String> list7, @SafeParcelable.Param(id = 53) boolean z17, @SafeParcelable.Param(id = 54) String str12, @Nullable @SafeParcelable.Param(id = 55) String str13, @SafeParcelable.Param(id = 56) boolean z18, @SafeParcelable.Param(id = 57) boolean z19) {
        zzaoj zzaojVar2;
        zzaom zzaomVar;
        this.versionCode = i;
        this.zzdhn = str;
        this.zzdkc = str2;
        this.zzdci = list != null ? Collections.unmodifiableList(list) : null;
        this.errorCode = i2;
        this.zzdcj = list2 != null ? Collections.unmodifiableList(list2) : null;
        this.zzdkd = j;
        this.zzdke = z;
        this.zzdkf = j2;
        this.zzdkg = list3 != null ? Collections.unmodifiableList(list3) : null;
        this.zzdcp = j3;
        this.orientation = i3;
        this.zzdkh = str3;
        this.zzdki = j4;
        this.zzdkj = str4;
        this.zzdkk = z2;
        this.zzdkl = str5;
        this.zzdkm = str6;
        this.zzdkn = z3;
        this.zzbnj = z4;
        this.zzdip = z5;
        this.zzdko = z6;
        this.zzdkz = z13;
        this.zzdkp = z7;
        this.zzdkq = zzaojVar;
        this.zzdkr = str7;
        this.zzbzx = str8;
        if (this.zzdkc == null && (zzaojVar2 = this.zzdkq) != null && (zzaomVar = (zzaom) zzaojVar2.zza(zzaom.CREATOR)) != null && !TextUtils.isEmpty(zzaomVar.zzdlr)) {
            this.zzdkc = zzaomVar.zzdlr;
        }
        this.zzccg = z8;
        this.zzcch = z9;
        this.zzdks = zzaqdVar;
        this.zzdkt = list4;
        this.zzdku = list5;
        this.zzdkv = z10;
        this.zzbkn = zzanzVar;
        this.zzdjc = z11;
        this.zzdjd = str9;
        this.zzdcm = list6;
        this.zzdcn = z12;
        this.zzdkw = str10;
        this.zzdkx = zzarnVar;
        this.zzdky = str11;
        this.zzdjp = z14;
        this.zzdla = bundle;
        this.zzblf = z15;
        this.zzdlb = i4;
        this.zzdlc = z16;
        this.zzdck = list7 != null ? Collections.unmodifiableList(list7) : null;
        this.zzblg = z17;
        this.zzdld = str12;
        this.zzdle = str13;
        this.zzdlf = z18;
        this.zzdlg = z19;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zzdhn, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdkc, false);
        SafeParcelWriter.writeStringList(parcel, 4, this.zzdci, false);
        SafeParcelWriter.writeInt(parcel, 5, this.errorCode);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzdcj, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzdkd);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdke);
        SafeParcelWriter.writeLong(parcel, 9, this.zzdkf);
        SafeParcelWriter.writeStringList(parcel, 10, this.zzdkg, false);
        SafeParcelWriter.writeLong(parcel, 11, this.zzdcp);
        SafeParcelWriter.writeInt(parcel, 12, this.orientation);
        SafeParcelWriter.writeString(parcel, 13, this.zzdkh, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzdki);
        SafeParcelWriter.writeString(parcel, 15, this.zzdkj, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzdkk);
        SafeParcelWriter.writeString(parcel, 19, this.zzdkl, false);
        SafeParcelWriter.writeString(parcel, 21, this.zzdkm, false);
        SafeParcelWriter.writeBoolean(parcel, 22, this.zzdkn);
        SafeParcelWriter.writeBoolean(parcel, 23, this.zzbnj);
        SafeParcelWriter.writeBoolean(parcel, 24, this.zzdip);
        SafeParcelWriter.writeBoolean(parcel, 25, this.zzdko);
        SafeParcelWriter.writeBoolean(parcel, 26, this.zzdkp);
        SafeParcelWriter.writeParcelable(parcel, 28, this.zzdkq, i, false);
        SafeParcelWriter.writeString(parcel, 29, this.zzdkr, false);
        SafeParcelWriter.writeString(parcel, 30, this.zzbzx, false);
        SafeParcelWriter.writeBoolean(parcel, 31, this.zzccg);
        SafeParcelWriter.writeBoolean(parcel, 32, this.zzcch);
        SafeParcelWriter.writeParcelable(parcel, 33, this.zzdks, i, false);
        SafeParcelWriter.writeStringList(parcel, 34, this.zzdkt, false);
        SafeParcelWriter.writeStringList(parcel, 35, this.zzdku, false);
        SafeParcelWriter.writeBoolean(parcel, 36, this.zzdkv);
        SafeParcelWriter.writeParcelable(parcel, 37, this.zzbkn, i, false);
        SafeParcelWriter.writeBoolean(parcel, 38, this.zzdjc);
        SafeParcelWriter.writeString(parcel, 39, this.zzdjd, false);
        SafeParcelWriter.writeStringList(parcel, 40, this.zzdcm, false);
        SafeParcelWriter.writeBoolean(parcel, 42, this.zzdcn);
        SafeParcelWriter.writeString(parcel, 43, this.zzdkw, false);
        SafeParcelWriter.writeParcelable(parcel, 44, this.zzdkx, i, false);
        SafeParcelWriter.writeString(parcel, 45, this.zzdky, false);
        SafeParcelWriter.writeBoolean(parcel, 46, this.zzdkz);
        SafeParcelWriter.writeBoolean(parcel, 47, this.zzdjp);
        SafeParcelWriter.writeBundle(parcel, 48, this.zzdla, false);
        SafeParcelWriter.writeBoolean(parcel, 49, this.zzblf);
        SafeParcelWriter.writeInt(parcel, 50, this.zzdlb);
        SafeParcelWriter.writeBoolean(parcel, 51, this.zzdlc);
        SafeParcelWriter.writeStringList(parcel, 52, this.zzdck, false);
        SafeParcelWriter.writeBoolean(parcel, 53, this.zzblg);
        SafeParcelWriter.writeString(parcel, 54, this.zzdld, false);
        SafeParcelWriter.writeString(parcel, 55, this.zzdle, false);
        SafeParcelWriter.writeBoolean(parcel, 56, this.zzdlf);
        SafeParcelWriter.writeBoolean(parcel, 57, this.zzdlg);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
