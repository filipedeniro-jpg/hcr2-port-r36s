package com.google.android.gms.ads.internal.overlay;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadg;
import com.google.android.gms.internal.ads.zzadi;
import com.google.android.gms.internal.ads.zzawv;
import com.google.android.gms.internal.ads.zzbbc;
import com.google.android.gms.internal.ads.zzth;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "AdOverlayInfoCreator")
@SafeParcelable.Reserved({1})
public final class AdOverlayInfoParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<AdOverlayInfoParcel> CREATOR = new zzn();

    @SafeParcelable.Field(id = 11)
    public final int orientation;

    @SafeParcelable.Field(id = 13)
    public final String url;

    @SafeParcelable.Field(id = 14)
    public final zzawv zzblh;

    @SafeParcelable.Field(getter = "getAdClickListenerAsBinder", id = 3, type = "android.os.IBinder")
    public final zzth zzcbl;

    @SafeParcelable.Field(getter = "getAdMetadataGmsgListenerAsBinder", id = 18, type = "android.os.IBinder")
    public final zzadg zzcwp;

    @SafeParcelable.Field(getter = "getAppEventGmsgListenerAsBinder", id = 6, type = "android.os.IBinder")
    public final zzadi zzcwr;

    @SafeParcelable.Field(getter = "getAdWebViewAsBinder", id = 5, type = "android.os.IBinder")
    public final zzbbc zzcyx;

    @SafeParcelable.Field(id = 2)
    public final zzb zzdhl;

    @SafeParcelable.Field(getter = "getAdOverlayListenerAsBinder", id = 4, type = "android.os.IBinder")
    public final zzp zzdhm;

    @SafeParcelable.Field(id = 7)
    public final String zzdhn;

    @SafeParcelable.Field(id = 8)
    public final boolean zzdho;

    @SafeParcelable.Field(id = 9)
    public final String zzdhp;

    @SafeParcelable.Field(getter = "getLeaveApplicationListenerAsBinder", id = 10, type = "android.os.IBinder")
    public final zzv zzdhq;

    @SafeParcelable.Field(id = 12)
    public final int zzdhr;

    @SafeParcelable.Field(id = 16)
    public final String zzdhs;

    @SafeParcelable.Field(id = 17)
    public final com.google.android.gms.ads.internal.zzi zzdht;

    public static void zza(Intent intent, AdOverlayInfoParcel adOverlayInfoParcel) {
        Bundle bundle = new Bundle(1);
        bundle.putParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", adOverlayInfoParcel);
        intent.putExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo", bundle);
    }

    public static AdOverlayInfoParcel zzc(Intent intent) {
        try {
            Bundle bundleExtra = intent.getBundleExtra("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
            bundleExtra.setClassLoader(AdOverlayInfoParcel.class.getClassLoader());
            return (AdOverlayInfoParcel) bundleExtra.getParcelable("com.google.android.gms.ads.inernal.overlay.AdOverlayInfo");
        } catch (Exception unused) {
            return null;
        }
    }

    public AdOverlayInfoParcel(zzth zzthVar, zzp zzpVar, zzv zzvVar, zzbbc zzbbcVar, int i, zzawv zzawvVar, String str, com.google.android.gms.ads.internal.zzi zziVar, String str2, String str3) {
        this.zzdhl = null;
        this.zzcbl = null;
        this.zzdhm = zzpVar;
        this.zzcyx = zzbbcVar;
        this.zzcwp = null;
        this.zzcwr = null;
        this.zzdhn = str2;
        this.zzdho = false;
        this.zzdhp = str3;
        this.zzdhq = null;
        this.orientation = i;
        this.zzdhr = 1;
        this.url = null;
        this.zzblh = zzawvVar;
        this.zzdhs = str;
        this.zzdht = zziVar;
    }

    public AdOverlayInfoParcel(zzth zzthVar, zzp zzpVar, zzv zzvVar, zzbbc zzbbcVar, boolean z, int i, zzawv zzawvVar) {
        this.zzdhl = null;
        this.zzcbl = zzthVar;
        this.zzdhm = zzpVar;
        this.zzcyx = zzbbcVar;
        this.zzcwp = null;
        this.zzcwr = null;
        this.zzdhn = null;
        this.zzdho = z;
        this.zzdhp = null;
        this.zzdhq = zzvVar;
        this.orientation = i;
        this.zzdhr = 2;
        this.url = null;
        this.zzblh = zzawvVar;
        this.zzdhs = null;
        this.zzdht = null;
    }

    public AdOverlayInfoParcel(zzth zzthVar, zzp zzpVar, zzadg zzadgVar, zzadi zzadiVar, zzv zzvVar, zzbbc zzbbcVar, boolean z, int i, String str, zzawv zzawvVar) {
        this.zzdhl = null;
        this.zzcbl = zzthVar;
        this.zzdhm = zzpVar;
        this.zzcyx = zzbbcVar;
        this.zzcwp = zzadgVar;
        this.zzcwr = zzadiVar;
        this.zzdhn = null;
        this.zzdho = z;
        this.zzdhp = null;
        this.zzdhq = zzvVar;
        this.orientation = i;
        this.zzdhr = 3;
        this.url = str;
        this.zzblh = zzawvVar;
        this.zzdhs = null;
        this.zzdht = null;
    }

    public AdOverlayInfoParcel(zzth zzthVar, zzp zzpVar, zzadg zzadgVar, zzadi zzadiVar, zzv zzvVar, zzbbc zzbbcVar, boolean z, int i, String str, String str2, zzawv zzawvVar) {
        this.zzdhl = null;
        this.zzcbl = zzthVar;
        this.zzdhm = zzpVar;
        this.zzcyx = zzbbcVar;
        this.zzcwp = zzadgVar;
        this.zzcwr = zzadiVar;
        this.zzdhn = str2;
        this.zzdho = z;
        this.zzdhp = str;
        this.zzdhq = zzvVar;
        this.orientation = i;
        this.zzdhr = 3;
        this.url = null;
        this.zzblh = zzawvVar;
        this.zzdhs = null;
        this.zzdht = null;
    }

    public AdOverlayInfoParcel(zzb zzbVar, zzth zzthVar, zzp zzpVar, zzv zzvVar, zzawv zzawvVar) {
        this.zzdhl = zzbVar;
        this.zzcbl = zzthVar;
        this.zzdhm = zzpVar;
        this.zzcyx = null;
        this.zzcwp = null;
        this.zzcwr = null;
        this.zzdhn = null;
        this.zzdho = false;
        this.zzdhp = null;
        this.zzdhq = zzvVar;
        this.orientation = -1;
        this.zzdhr = 4;
        this.url = null;
        this.zzblh = zzawvVar;
        this.zzdhs = null;
        this.zzdht = null;
    }

    @SafeParcelable.Constructor
    AdOverlayInfoParcel(@SafeParcelable.Param(id = 2) zzb zzbVar, @SafeParcelable.Param(id = 3) IBinder iBinder, @SafeParcelable.Param(id = 4) IBinder iBinder2, @SafeParcelable.Param(id = 5) IBinder iBinder3, @SafeParcelable.Param(id = 6) IBinder iBinder4, @SafeParcelable.Param(id = 7) String str, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) String str2, @SafeParcelable.Param(id = 10) IBinder iBinder5, @SafeParcelable.Param(id = 11) int i, @SafeParcelable.Param(id = 12) int i2, @SafeParcelable.Param(id = 13) String str3, @SafeParcelable.Param(id = 14) zzawv zzawvVar, @SafeParcelable.Param(id = 16) String str4, @SafeParcelable.Param(id = 17) com.google.android.gms.ads.internal.zzi zziVar, @SafeParcelable.Param(id = 18) IBinder iBinder6) {
        this.zzdhl = zzbVar;
        this.zzcbl = (zzth) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzdhm = (zzp) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder2));
        this.zzcyx = (zzbbc) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder3));
        this.zzcwp = (zzadg) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder6));
        this.zzcwr = (zzadi) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder4));
        this.zzdhn = str;
        this.zzdho = z;
        this.zzdhp = str2;
        this.zzdhq = (zzv) ObjectWrapper.unwrap(IObjectWrapper.Stub.asInterface(iBinder5));
        this.orientation = i;
        this.zzdhr = i2;
        this.url = str3;
        this.zzblh = zzawvVar;
        this.zzdhs = str4;
        this.zzdht = zziVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzdhl, i, false);
        SafeParcelWriter.writeIBinder(parcel, 3, ObjectWrapper.wrap(this.zzcbl).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 4, ObjectWrapper.wrap(this.zzdhm).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 5, ObjectWrapper.wrap(this.zzcyx).asBinder(), false);
        SafeParcelWriter.writeIBinder(parcel, 6, ObjectWrapper.wrap(this.zzcwr).asBinder(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzdhn, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdho);
        SafeParcelWriter.writeString(parcel, 9, this.zzdhp, false);
        SafeParcelWriter.writeIBinder(parcel, 10, ObjectWrapper.wrap(this.zzdhq).asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 11, this.orientation);
        SafeParcelWriter.writeInt(parcel, 12, this.zzdhr);
        SafeParcelWriter.writeString(parcel, 13, this.url, false);
        SafeParcelWriter.writeParcelable(parcel, 14, this.zzblh, i, false);
        SafeParcelWriter.writeString(parcel, 16, this.zzdhs, false);
        SafeParcelWriter.writeParcelable(parcel, 17, this.zzdht, i, false);
        SafeParcelWriter.writeIBinder(parcel, 18, ObjectWrapper.wrap(this.zzcwp).asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
