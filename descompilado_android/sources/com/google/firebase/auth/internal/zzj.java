package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.firebase_auth.zzbq;
import com.google.android.gms.internal.firebase_auth.zzes;
import com.google.android.gms.internal.firebase_auth.zzfc;
import com.google.firebase.auth.UserInfo;
import com.ironsource.mediationsdk.utils.ServerResponseWrapper;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "DefaultAuthUserInfoCreator")
public final class zzj extends AbstractSafeParcelable implements UserInfo {
    public static final Parcelable.Creator<zzj> CREATOR = new zzm();

    @NonNull
    @SafeParcelable.Field(getter = "getUid", id = 1)
    private String zza;

    @NonNull
    @SafeParcelable.Field(getter = "getProviderId", id = 2)
    private String zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getDisplayName", id = 3)
    private String zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getPhotoUrlString", id = 4)
    private String zzd;

    @Nullable
    private Uri zze;

    @Nullable
    @SafeParcelable.Field(getter = "getEmail", id = 5)
    private String zzf;

    @Nullable
    @SafeParcelable.Field(getter = "getPhoneNumber", id = 6)
    private String zzg;

    @SafeParcelable.Field(getter = "isEmailVerified", id = 7)
    private boolean zzh;

    @Nullable
    @SafeParcelable.Field(getter = "getRawUserInfo", id = 8)
    private String zzi;

    @SafeParcelable.Constructor
    @VisibleForTesting
    public zzj(@SafeParcelable.Param(id = 1) String str, @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 5) String str3, @Nullable @SafeParcelable.Param(id = 4) String str4, @Nullable @SafeParcelable.Param(id = 3) String str5, @Nullable @SafeParcelable.Param(id = 6) String str6, @SafeParcelable.Param(id = 7) boolean z, @Nullable @SafeParcelable.Param(id = 8) String str7) {
        this.zza = str;
        this.zzb = str2;
        this.zzf = str3;
        this.zzg = str4;
        this.zzc = str5;
        this.zzd = str6;
        if (!TextUtils.isEmpty(this.zzd)) {
            this.zze = Uri.parse(this.zzd);
        }
        this.zzh = z;
        this.zzi = str7;
    }

    public zzj(zzes zzesVar, String str) {
        Preconditions.checkNotNull(zzesVar);
        Preconditions.checkNotEmpty(str);
        this.zza = Preconditions.checkNotEmpty(zzesVar.zzc());
        this.zzb = str;
        this.zzf = zzesVar.zza();
        this.zzc = zzesVar.zzd();
        Uri uriZze = zzesVar.zze();
        if (uriZze != null) {
            this.zzd = uriZze.toString();
            this.zze = uriZze;
        }
        this.zzh = zzesVar.zzb();
        this.zzi = null;
        this.zzg = zzesVar.zzf();
    }

    public zzj(zzfc zzfcVar) {
        Preconditions.checkNotNull(zzfcVar);
        this.zza = zzfcVar.zza();
        this.zzb = Preconditions.checkNotEmpty(zzfcVar.zzd());
        this.zzc = zzfcVar.zzb();
        Uri uriZzc = zzfcVar.zzc();
        if (uriZzc != null) {
            this.zzd = uriZzc.toString();
            this.zze = uriZzc;
        }
        this.zzf = zzfcVar.zzg();
        this.zzg = zzfcVar.zze();
        this.zzh = false;
        this.zzi = zzfcVar.zzf();
    }

    @Override // com.google.firebase.auth.UserInfo
    @NonNull
    public final String getUid() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.UserInfo
    @NonNull
    public final String getProviderId() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.UserInfo
    @Nullable
    public final String getDisplayName() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.UserInfo
    @Nullable
    public final Uri getPhotoUrl() {
        if (!TextUtils.isEmpty(this.zzd) && this.zze == null) {
            this.zze = Uri.parse(this.zzd);
        }
        return this.zze;
    }

    @Override // com.google.firebase.auth.UserInfo
    @Nullable
    public final String getEmail() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.UserInfo
    @Nullable
    public final String getPhoneNumber() {
        return this.zzg;
    }

    @Override // com.google.firebase.auth.UserInfo
    public final boolean isEmailVerified() {
        return this.zzh;
    }

    @Nullable
    public final String zza() {
        return this.zzi;
    }

    @Nullable
    public final String zzb() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt(ServerResponseWrapper.USER_ID_FIELD, this.zza);
            jSONObject.putOpt("providerId", this.zzb);
            jSONObject.putOpt("displayName", this.zzc);
            jSONObject.putOpt("photoUrl", this.zzd);
            jSONObject.putOpt("email", this.zzf);
            jSONObject.putOpt("phoneNumber", this.zzg);
            jSONObject.putOpt("isEmailVerified", Boolean.valueOf(this.zzh));
            jSONObject.putOpt("rawUserInfo", this.zzi);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to jsonify this object");
            throw new zzbq(e);
        }
    }

    @Nullable
    public static zzj zza(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new zzj(jSONObject.optString(ServerResponseWrapper.USER_ID_FIELD), jSONObject.optString("providerId"), jSONObject.optString("email"), jSONObject.optString("phoneNumber"), jSONObject.optString("displayName"), jSONObject.optString("photoUrl"), jSONObject.optBoolean("isEmailVerified"), jSONObject.optString("rawUserInfo"));
        } catch (JSONException e) {
            Log.d("DefaultAuthUserInfo", "Failed to unpack UserInfo from JSON");
            throw new zzbq(e);
        }
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getUid(), false);
        SafeParcelWriter.writeString(parcel, 2, getProviderId(), false);
        SafeParcelWriter.writeString(parcel, 3, getDisplayName(), false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeString(parcel, 5, getEmail(), false);
        SafeParcelWriter.writeString(parcel, 6, getPhoneNumber(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, isEmailVerified());
        SafeParcelWriter.writeString(parcel, 8, this.zzi, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
