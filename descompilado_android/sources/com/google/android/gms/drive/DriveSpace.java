package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.stats.CodePackage;
import java.util.Set;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "DriveSpaceCreator")
@SafeParcelable.Reserved({1})
public class DriveSpace extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DriveSpace> CREATOR = new zzm();
    public static final DriveSpace zzah = new DriveSpace(CodePackage.DRIVE);
    public static final DriveSpace zzai = new DriveSpace("APP_DATA_FOLDER");
    public static final DriveSpace zzaj = new DriveSpace(ShareConstants.PHOTOS);
    private static final Set<DriveSpace> zzak = CollectionUtils.setOf(zzah, zzai, zzaj);
    private static final String zzal = TextUtils.join(",", zzak.toArray());
    private static final Pattern zzam = Pattern.compile("[A-Z0-9_]*");

    @SafeParcelable.Field(getter = "getName", id = 2)
    private final String name;

    @SafeParcelable.Constructor
    DriveSpace(@SafeParcelable.Param(id = 2) String str) {
        this.name = (String) Preconditions.checkNotNull(str);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != DriveSpace.class) {
            return false;
        }
        return this.name.equals(((DriveSpace) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1247068382;
    }

    public String toString() {
        return this.name;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
