package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "FilterHolderCreator")
@SafeParcelable.Reserved({1000})
public class FilterHolder extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<FilterHolder> CREATOR = new zzh();
    private final Filter zzbc;

    @SafeParcelable.Field(id = 1)
    private final zzb<?> zzmd;

    @SafeParcelable.Field(id = 2)
    private final zzd zzme;

    @SafeParcelable.Field(id = 3)
    private final zzr zzmf;

    @SafeParcelable.Field(id = 4)
    private final zzv zzmg;

    @SafeParcelable.Field(id = 5)
    private final zzp<?> zzmh;

    @SafeParcelable.Field(id = 6)
    private final zzt zzmi;

    @SafeParcelable.Field(id = 7)
    private final zzn zzmj;

    @SafeParcelable.Field(id = 8)
    private final zzl zzmk;

    @SafeParcelable.Field(id = 9)
    private final zzz zzml;

    @SafeParcelable.Constructor
    FilterHolder(@SafeParcelable.Param(id = 1) zzb<?> zzbVar, @SafeParcelable.Param(id = 2) zzd zzdVar, @SafeParcelable.Param(id = 3) zzr zzrVar, @SafeParcelable.Param(id = 4) zzv zzvVar, @SafeParcelable.Param(id = 5) zzp<?> zzpVar, @SafeParcelable.Param(id = 6) zzt zztVar, @SafeParcelable.Param(id = 7) zzn<?> zznVar, @SafeParcelable.Param(id = 8) zzl zzlVar, @SafeParcelable.Param(id = 9) zzz zzzVar) {
        this.zzmd = zzbVar;
        this.zzme = zzdVar;
        this.zzmf = zzrVar;
        this.zzmg = zzvVar;
        this.zzmh = zzpVar;
        this.zzmi = zztVar;
        this.zzmj = zznVar;
        this.zzmk = zzlVar;
        this.zzml = zzzVar;
        zzb<?> zzbVar2 = this.zzmd;
        if (zzbVar2 != null) {
            this.zzbc = zzbVar2;
            return;
        }
        zzd zzdVar2 = this.zzme;
        if (zzdVar2 != null) {
            this.zzbc = zzdVar2;
            return;
        }
        zzr zzrVar2 = this.zzmf;
        if (zzrVar2 != null) {
            this.zzbc = zzrVar2;
            return;
        }
        zzv zzvVar2 = this.zzmg;
        if (zzvVar2 != null) {
            this.zzbc = zzvVar2;
            return;
        }
        zzp<?> zzpVar2 = this.zzmh;
        if (zzpVar2 != null) {
            this.zzbc = zzpVar2;
            return;
        }
        zzt zztVar2 = this.zzmi;
        if (zztVar2 != null) {
            this.zzbc = zztVar2;
            return;
        }
        zzn zznVar2 = this.zzmj;
        if (zznVar2 != null) {
            this.zzbc = zznVar2;
            return;
        }
        zzl zzlVar2 = this.zzmk;
        if (zzlVar2 != null) {
            this.zzbc = zzlVar2;
            return;
        }
        zzz zzzVar2 = this.zzml;
        if (zzzVar2 == null) {
            throw new IllegalArgumentException("At least one filter must be set.");
        }
        this.zzbc = zzzVar2;
    }

    public FilterHolder(Filter filter) {
        Preconditions.checkNotNull(filter, "Null filter.");
        this.zzmd = filter instanceof zzb ? (zzb) filter : null;
        this.zzme = filter instanceof zzd ? (zzd) filter : null;
        this.zzmf = filter instanceof zzr ? (zzr) filter : null;
        this.zzmg = filter instanceof zzv ? (zzv) filter : null;
        this.zzmh = filter instanceof zzp ? (zzp) filter : null;
        this.zzmi = filter instanceof zzt ? (zzt) filter : null;
        this.zzmj = filter instanceof zzn ? (zzn) filter : null;
        this.zzmk = filter instanceof zzl ? (zzl) filter : null;
        this.zzml = filter instanceof zzz ? (zzz) filter : null;
        if (this.zzmd == null && this.zzme == null && this.zzmf == null && this.zzmg == null && this.zzmh == null && this.zzmi == null && this.zzmj == null && this.zzmk == null && this.zzml == null) {
            throw new IllegalArgumentException("Invalid filter type.");
        }
        this.zzbc = filter;
    }

    public final Filter getFilter() {
        return this.zzbc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzmd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzme, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzmf, i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzmg, i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzmh, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzmi, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzmj, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzmk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 9, this.zzml, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
