package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.query.Filter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "LogicalFilterCreator")
@SafeParcelable.Reserved({1000})
public final class zzr extends zza {
    public static final Parcelable.Creator<zzr> CREATOR = new zzs();
    private List<Filter> zzls;

    @SafeParcelable.Field(id = 1)
    private final zzx zzlz;

    @SafeParcelable.Field(id = 2)
    private final List<FilterHolder> zzmo;

    @SafeParcelable.Constructor
    zzr(@SafeParcelable.Param(id = 1) zzx zzxVar, @SafeParcelable.Param(id = 2) List<FilterHolder> list) {
        this.zzlz = zzxVar;
        this.zzmo = list;
    }

    public zzr(zzx zzxVar, Iterable<Filter> iterable) {
        this.zzlz = zzxVar;
        this.zzls = new ArrayList();
        this.zzmo = new ArrayList();
        for (Filter filter : iterable) {
            this.zzls.add(filter);
            this.zzmo.add(new FilterHolder(filter));
        }
    }

    public zzr(zzx zzxVar, Filter filter, Filter... filterArr) {
        this.zzlz = zzxVar;
        this.zzmo = new ArrayList(filterArr.length + 1);
        this.zzmo.add(new FilterHolder(filter));
        this.zzls = new ArrayList(filterArr.length + 1);
        this.zzls.add(filter);
        for (Filter filter2 : filterArr) {
            this.zzmo.add(new FilterHolder(filter2));
            this.zzls.add(filter2);
        }
    }

    @Override // com.google.android.gms.drive.query.Filter
    public final <T> T zza(zzj<T> zzjVar) {
        ArrayList arrayList = new ArrayList();
        Iterator<FilterHolder> it = this.zzmo.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getFilter().zza(zzjVar));
        }
        return zzjVar.zza(this.zzlz, arrayList);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzlz, i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, this.zzmo, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
