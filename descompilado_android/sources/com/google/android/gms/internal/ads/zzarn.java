package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "SafeBrowsingConfigParcelCreator")
@SafeParcelable.Reserved({1})
public final class zzarn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzarn> CREATOR = new zzarm();

    @SafeParcelable.Field(id = 2)
    public final String zzdok;

    @SafeParcelable.Field(id = 3)
    public final String zzdol;

    @SafeParcelable.Field(id = 4)
    public final boolean zzdom;

    @SafeParcelable.Field(id = 5)
    public final boolean zzdon;

    @SafeParcelable.Field(id = 6)
    public final List<String> zzdoo;

    @SafeParcelable.Field(id = 7)
    public final boolean zzdop;

    @SafeParcelable.Field(id = 8)
    public final boolean zzdoq;

    @SafeParcelable.Field(id = 9)
    public final List<String> zzdor;

    @SafeParcelable.Constructor
    public zzarn(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) boolean z2, @SafeParcelable.Param(id = 6) List<String> list, @SafeParcelable.Param(id = 7) boolean z3, @SafeParcelable.Param(id = 8) boolean z4, @SafeParcelable.Param(id = 9) List<String> list2) {
        this.zzdok = str;
        this.zzdol = str2;
        this.zzdom = z;
        this.zzdon = z2;
        this.zzdoo = list;
        this.zzdop = z3;
        this.zzdoq = z4;
        this.zzdor = list2 == null ? new ArrayList<>() : list2;
    }

    @Nullable
    public static zzarn zzg(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        return new zzarn(jSONObject.optString("click_string", ""), jSONObject.optString("report_url", ""), jSONObject.optBoolean("rendered_ad_enabled", false), jSONObject.optBoolean("non_malicious_reporting_enabled", false), zzavm.zza(jSONObject.optJSONArray("allowed_headers"), (List<String>) null), jSONObject.optBoolean("protection_enabled", false), jSONObject.optBoolean("malicious_reporting_enabled", false), zzavm.zza(jSONObject.optJSONArray("webview_permissions"), (List<String>) null));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzdok, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdol, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzdom);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzdon);
        SafeParcelWriter.writeStringList(parcel, 6, this.zzdoo, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzdop);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdoq);
        SafeParcelWriter.writeStringList(parcel, 9, this.zzdor, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
