package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "AdSizeParcelCreator")
@SafeParcelable.Reserved({1})
public final class zztw extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zztw> CREATOR = new zztv();

    @SafeParcelable.Field(id = 3)
    public final int height;

    @SafeParcelable.Field(id = 4)
    public final int heightPixels;

    @SafeParcelable.Field(id = 6)
    public final int width;

    @SafeParcelable.Field(id = 7)
    public final int widthPixels;

    @SafeParcelable.Field(id = 2)
    public final String zzabd;

    @SafeParcelable.Field(id = 9)
    public final boolean zzbnj;

    @SafeParcelable.Field(id = 5)
    public final boolean zzcce;

    @SafeParcelable.Field(id = 8)
    public final zztw[] zzccf;

    @SafeParcelable.Field(id = 10)
    public final boolean zzccg;

    @SafeParcelable.Field(id = 11)
    public boolean zzcch;

    @SafeParcelable.Field(id = 12)
    public boolean zzcci;

    @SafeParcelable.Field(id = 13)
    private boolean zzccj;

    @SafeParcelable.Field(id = 14)
    public boolean zzcck;

    public static int zzb(DisplayMetrics displayMetrics) {
        return displayMetrics.widthPixels;
    }

    public static int zzc(DisplayMetrics displayMetrics) {
        return (int) (zzd(displayMetrics) * displayMetrics.density);
    }

    private static int zzd(DisplayMetrics displayMetrics) {
        int i = (int) (displayMetrics.heightPixels / displayMetrics.density);
        if (i <= 400) {
            return 32;
        }
        return i <= 720 ? 50 : 90;
    }

    public static zztw zzg(Context context) {
        return new zztw("320x50_mb", 0, 0, false, 0, 0, null, true, false, false, false, false, false);
    }

    public static zztw zzny() {
        return new zztw("reward_mb", 0, 0, true, 0, 0, null, false, false, false, false, false, false);
    }

    public zztw() {
        this("interstitial_mb", 0, 0, true, 0, 0, null, false, false, false, false, false, false);
    }

    public static zztw zznz() {
        return new zztw("invalid", 0, 0, false, 0, 0, null, false, false, false, true, false, false);
    }

    public zztw(Context context, AdSize adSize) {
        this(context, new AdSize[]{adSize});
    }

    /* JADX WARN: Code duplicated, block: B:21:0x006f  */
    public zztw(Context context, AdSize[] adSizeArr) {
        int i;
        int iZzd;
        AdSize adSize = adSizeArr[0];
        this.zzcce = false;
        this.zzccg = adSize.isFluid();
        this.zzcck = adSize.zzdb();
        if (this.zzccg) {
            this.width = AdSize.BANNER.getWidth();
            this.height = AdSize.BANNER.getHeight();
        } else {
            this.width = adSize.getWidth();
            this.height = adSize.getHeight();
        }
        boolean z = this.width == -1;
        boolean z2 = this.height == -2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (z) {
            zzuo.zzof();
            if (zzawe.zzbk(context)) {
                zzuo.zzof();
                if (zzawe.zzbl(context)) {
                    int i2 = displayMetrics.widthPixels;
                    zzuo.zzof();
                    this.widthPixels = i2 - zzawe.zzbm(context);
                } else {
                    this.widthPixels = displayMetrics.widthPixels;
                }
            } else {
                this.widthPixels = displayMetrics.widthPixels;
            }
            double d = this.widthPixels / displayMetrics.density;
            i = (int) d;
            double d2 = i;
            Double.isNaN(d);
            Double.isNaN(d2);
            if (d - d2 >= 0.01d) {
                i++;
            }
        } else {
            i = this.width;
            zzuo.zzof();
            this.widthPixels = zzawe.zza(displayMetrics, this.width);
        }
        if (z2) {
            iZzd = zzd(displayMetrics);
        } else {
            iZzd = this.height;
        }
        zzuo.zzof();
        this.heightPixels = zzawe.zza(displayMetrics, iZzd);
        if (z || z2) {
            StringBuilder sb = new StringBuilder(26);
            sb.append(i);
            sb.append("x");
            sb.append(iZzd);
            sb.append("_as");
            this.zzabd = sb.toString();
        } else if (this.zzccg) {
            this.zzabd = "320x50_mb";
        } else {
            this.zzabd = adSize.toString();
        }
        if (adSizeArr.length > 1) {
            this.zzccf = new zztw[adSizeArr.length];
            for (int i3 = 0; i3 < adSizeArr.length; i3++) {
                this.zzccf[i3] = new zztw(context, adSizeArr[i3]);
            }
        } else {
            this.zzccf = null;
        }
        this.zzbnj = false;
        this.zzcch = false;
    }

    @SafeParcelable.Constructor
    zztw(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) int i, @SafeParcelable.Param(id = 4) int i2, @SafeParcelable.Param(id = 5) boolean z, @SafeParcelable.Param(id = 6) int i3, @SafeParcelable.Param(id = 7) int i4, @SafeParcelable.Param(id = 8) zztw[] zztwVarArr, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) boolean z4, @SafeParcelable.Param(id = 12) boolean z5, @SafeParcelable.Param(id = 13) boolean z6, @SafeParcelable.Param(id = 14) boolean z7) {
        this.zzabd = str;
        this.height = i;
        this.heightPixels = i2;
        this.zzcce = z;
        this.width = i3;
        this.widthPixels = i4;
        this.zzccf = zztwVarArr;
        this.zzbnj = z2;
        this.zzccg = z3;
        this.zzcch = z4;
        this.zzcci = z5;
        this.zzccj = z6;
        this.zzcck = z7;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzabd, false);
        SafeParcelWriter.writeInt(parcel, 3, this.height);
        SafeParcelWriter.writeInt(parcel, 4, this.heightPixels);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzcce);
        SafeParcelWriter.writeInt(parcel, 6, this.width);
        SafeParcelWriter.writeInt(parcel, 7, this.widthPixels);
        SafeParcelWriter.writeTypedArray(parcel, 8, this.zzccf, i, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzbnj);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzccg);
        SafeParcelWriter.writeBoolean(parcel, 11, this.zzcch);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzcci);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzccj);
        SafeParcelWriter.writeBoolean(parcel, 14, this.zzcck);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
