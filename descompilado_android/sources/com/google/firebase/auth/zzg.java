package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzfr;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "DefaultOAuthCredentialCreator")
public class zzg extends OAuthCredential {
    public static final Parcelable.Creator<zzg> CREATOR = new zzi();

    @Nullable
    @SafeParcelable.Field(getter = "getProvider", id = 1)
    private final String zza;

    @Nullable
    @SafeParcelable.Field(getter = "getIdToken", id = 2)
    private final String zzb;

    @Nullable
    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    private final String zzc;

    @Nullable
    @SafeParcelable.Field(getter = "getWebSignInCredential", id = 4)
    private final zzfr zzd;

    @Nullable
    @SafeParcelable.Field(getter = "getPendingToken", id = 5)
    private final String zze;

    @Nullable
    @SafeParcelable.Field(getter = "getSecret", id = 6)
    private final String zzf;

    @SafeParcelable.Constructor
    zzg(@NonNull @SafeParcelable.Param(id = 1) String str, @Nullable @SafeParcelable.Param(id = 2) String str2, @Nullable @SafeParcelable.Param(id = 3) String str3, @Nullable @SafeParcelable.Param(id = 4) zzfr zzfrVar, @Nullable @SafeParcelable.Param(id = 5) String str4, @Nullable @SafeParcelable.Param(id = 6) String str5) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = zzfrVar;
        this.zze = str4;
        this.zzf = str5;
    }

    public static zzg zza(String str, String str2, String str3) {
        return zza(str, str2, str3, null, null);
    }

    public static zzg zza(String str, String str2, String str3, @Nullable String str4, @Nullable String str5) {
        Preconditions.checkNotEmpty(str, "Must specify a non-empty providerId");
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("Must specify an idToken or an accessToken.");
        }
        return new zzg(str, str2, str3, null, str4, str5);
    }

    public static zzg zza(@NonNull zzfr zzfrVar) {
        Preconditions.checkNotNull(zzfrVar, "Must specify a non-null webSignInCredential");
        return new zzg(null, null, null, zzfrVar, null, null);
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getProvider() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.AuthCredential
    public String getSignInMethod() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    @Nullable
    public String getIdToken() {
        return this.zzb;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    @Nullable
    public String getAccessToken() {
        return this.zzc;
    }

    @Override // com.google.firebase.auth.OAuthCredential
    @Nullable
    public String getSecret() {
        return this.zzf;
    }

    public static zzfr zza(@NonNull zzg zzgVar, @Nullable String str) {
        Preconditions.checkNotNull(zzgVar);
        zzfr zzfrVar = zzgVar.zzd;
        return zzfrVar != null ? zzfrVar : new zzfr(zzgVar.getIdToken(), zzgVar.getAccessToken(), zzgVar.getProvider(), null, zzgVar.getSecret(), null, str, zzgVar.zze);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getProvider(), false);
        SafeParcelWriter.writeString(parcel, 2, getIdToken(), false);
        SafeParcelWriter.writeString(parcel, 3, getAccessToken(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzd, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zze, false);
        SafeParcelWriter.writeString(parcel, 6, getSecret(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
