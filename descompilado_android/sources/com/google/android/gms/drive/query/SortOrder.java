package com.google.android.gms.drive.query;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.drive.metadata.SortableMetadataField;
import com.google.android.gms.drive.query.internal.zzf;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
@SafeParcelable.Class(creator = "SortOrderCreator")
@SafeParcelable.Reserved({1000})
public class SortOrder extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SortOrder> CREATOR = new zzc();

    @SafeParcelable.Field(id = 1)
    private final List<zzf> zzlw;

    @SafeParcelable.Field(defaultValue = "false", id = 2)
    private final boolean zzlx;

    @SafeParcelable.Constructor
    SortOrder(@SafeParcelable.Param(id = 1) List<zzf> list, @SafeParcelable.Param(id = 2) boolean z) {
        this.zzlw = list;
        this.zzlx = z;
    }

    public static class Builder {
        private final List<zzf> zzlw = new ArrayList();
        private boolean zzlx = false;

        public Builder addSortAscending(SortableMetadataField sortableMetadataField) {
            this.zzlw.add(new zzf(sortableMetadataField.getName(), true));
            return this;
        }

        public Builder addSortDescending(SortableMetadataField sortableMetadataField) {
            this.zzlw.add(new zzf(sortableMetadataField.getName(), false));
            return this;
        }

        public SortOrder build() {
            return new SortOrder(this.zzlw, false);
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, this.zzlw, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlx);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public String toString() {
        return String.format(Locale.US, "SortOrder[%s, %s]", TextUtils.join(",", this.zzlw), Boolean.valueOf(this.zzlx));
    }
}
