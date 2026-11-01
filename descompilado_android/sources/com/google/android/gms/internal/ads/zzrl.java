package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "CacheOfferingCreator")
@SafeParcelable.Reserved({1})
public final class zzrl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzrl> CREATOR = new zzrk();

    @Nullable
    @SafeParcelable.Field(id = 2)
    public final String url;

    @SafeParcelable.Field(id = 3)
    private final long zzbqz;

    @SafeParcelable.Field(id = 4)
    private final String zzbra;

    @SafeParcelable.Field(id = 5)
    private final String zzbrb;

    @SafeParcelable.Field(id = 6)
    private final String zzbrc;

    @SafeParcelable.Field(id = 7)
    private final Bundle zzbrd;

    @SafeParcelable.Field(id = 8)
    public final boolean zzbre;

    @SafeParcelable.Field(id = 9)
    public long zzbrf;

    @Nullable
    public static zzrl zzbt(String str) {
        return zze(Uri.parse(str));
    }

    @Nullable
    public static zzrl zze(Uri uri) {
        try {
            if (!"gcache".equals(uri.getScheme())) {
                return null;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() != 2) {
                int size = pathSegments.size();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Expected 2 path parts for namespace and id, found :");
                sb.append(size);
                zzatm.zzeu(sb.toString());
                return null;
            }
            String str = pathSegments.get(0);
            String str2 = pathSegments.get(1);
            String host = uri.getHost();
            String queryParameter = uri.getQueryParameter("url");
            boolean zEquals = "1".equals(uri.getQueryParameter("read_only"));
            String queryParameter2 = uri.getQueryParameter("expiration");
            long j = queryParameter2 == null ? 0L : Long.parseLong(queryParameter2);
            Bundle bundle = new Bundle();
            com.google.android.gms.ads.internal.zzp.zzka();
            for (String str3 : uri.getQueryParameterNames()) {
                if (str3.startsWith("tag.")) {
                    bundle.putString(str3.substring(4), uri.getQueryParameter(str3));
                }
            }
            return new zzrl(queryParameter, j, host, str, str2, bundle, zEquals, 0L);
        } catch (NullPointerException | NumberFormatException e) {
            zzatm.zzd("Unable to parse Uri into cache offering.", e);
            return null;
        }
    }

    @SafeParcelable.Constructor
    zzrl(@Nullable @SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) String str4, @SafeParcelable.Param(id = 7) Bundle bundle, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) long j2) {
        this.url = str;
        this.zzbqz = j;
        this.zzbra = str2 == null ? "" : str2;
        this.zzbrb = str3 == null ? "" : str3;
        this.zzbrc = str4 != null ? str4 : "";
        this.zzbrd = bundle == null ? new Bundle() : bundle;
        this.zzbre = z;
        this.zzbrf = j2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzbqz);
        SafeParcelWriter.writeString(parcel, 4, this.zzbra, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbrb, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzbrc, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzbrd, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzbre);
        SafeParcelWriter.writeLong(parcel, 9, this.zzbrf);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
