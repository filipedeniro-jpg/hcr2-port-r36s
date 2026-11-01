package com.google.firebase.auth.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.firebase_auth.zzey;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.UserInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "DefaultFirebaseUserCreator")
public class zzn extends FirebaseUser {
    public static final Parcelable.Creator<zzn> CREATOR = new zzq();

    @SafeParcelable.Field(getter = "getCachedTokenState", id = 1)
    private zzey zza;

    @SafeParcelable.Field(getter = "getDefaultAuthUserInfo", id = 2)
    private zzj zzb;

    @SafeParcelable.Field(getter = "getFirebaseAppName", id = 3)
    private String zzc;

    @SafeParcelable.Field(getter = "getUserType", id = 4)
    private String zzd;

    @SafeParcelable.Field(getter = "getUserInfos", id = 5)
    private List<zzj> zze;

    @SafeParcelable.Field(getter = "getProviders", id = 6)
    private List<String> zzf;

    @SafeParcelable.Field(getter = "getCurrentVersion", id = 7)
    private String zzg;

    @SafeParcelable.Field(getter = "isAnonymous", id = 8)
    private Boolean zzh;

    @SafeParcelable.Field(getter = "getMetadata", id = 9)
    private zzp zzi;

    @SafeParcelable.Field(getter = "isNewUser", id = 10)
    private boolean zzj;

    @SafeParcelable.Field(getter = "getDefaultOAuthCredential", id = 11)
    private com.google.firebase.auth.zzg zzk;

    @SafeParcelable.Field(getter = "getMultiFactorInfoList", id = 12)
    private zzaq zzl;

    @SafeParcelable.Constructor
    zzn(@SafeParcelable.Param(id = 1) zzey zzeyVar, @SafeParcelable.Param(id = 2) zzj zzjVar, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @SafeParcelable.Param(id = 5) List<zzj> list, @SafeParcelable.Param(id = 6) List<String> list2, @SafeParcelable.Param(id = 7) String str3, @SafeParcelable.Param(id = 8) Boolean bool, @SafeParcelable.Param(id = 9) zzp zzpVar, @SafeParcelable.Param(id = 10) boolean z, @SafeParcelable.Param(id = 11) com.google.firebase.auth.zzg zzgVar, @SafeParcelable.Param(id = 12) zzaq zzaqVar) {
        this.zza = zzeyVar;
        this.zzb = zzjVar;
        this.zzc = str;
        this.zzd = str2;
        this.zze = list;
        this.zzf = list2;
        this.zzg = str3;
        this.zzh = bool;
        this.zzi = zzpVar;
        this.zzj = z;
        this.zzk = zzgVar;
        this.zzl = zzaqVar;
    }

    public zzn(FirebaseApp firebaseApp, List<? extends UserInfo> list) {
        Preconditions.checkNotNull(firebaseApp);
        this.zzc = firebaseApp.getName();
        this.zzd = "com.google.firebase.auth.internal.DefaultFirebaseUser";
        this.zzg = "2";
        zza(list);
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @Nullable
    public String getDisplayName() {
        return this.zzb.getDisplayName();
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @Nullable
    public Uri getPhotoUrl() {
        return this.zzb.getPhotoUrl();
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @Nullable
    public String getEmail() {
        return this.zzb.getEmail();
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @Nullable
    public String getPhoneNumber() {
        return this.zzb.getPhoneNumber();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @Nullable
    public final String zzd() {
        Map map;
        zzey zzeyVar = this.zza;
        if (zzeyVar == null || zzeyVar.zzd() == null || (map = (Map) zzap.zza(this.zza.zzd()).getClaims().get(FirebaseAuthProvider.PROVIDER_ID)) == null) {
            return null;
        }
        return (String) map.get("tenant");
    }

    public final zzn zza(String str) {
        this.zzg = str;
        return this;
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @NonNull
    public String getProviderId() {
        return this.zzb.getProviderId();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public final FirebaseApp zzc() {
        return FirebaseApp.getInstance(this.zzc);
    }

    public final List<zzj> zzi() {
        return this.zze;
    }

    @Override // com.google.firebase.auth.FirebaseUser, com.google.firebase.auth.UserInfo
    @NonNull
    public String getUid() {
        return this.zzb.getUid();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public boolean isAnonymous() {
        GetTokenResult getTokenResultZza;
        Boolean bool = this.zzh;
        if (bool == null || bool.booleanValue()) {
            zzey zzeyVar = this.zza;
            String signInProvider = "";
            if (zzeyVar != null && (getTokenResultZza = zzap.zza(zzeyVar.zzd())) != null) {
                signInProvider = getTokenResultZza.getSignInProvider();
            }
            boolean z = true;
            if (getProviderData().size() > 1 || (signInProvider != null && signInProvider.equals("custom"))) {
                z = false;
            }
            this.zzh = Boolean.valueOf(z);
        }
        return this.zzh.booleanValue();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @Nullable
    public final List<String> zza() {
        return this.zzf;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public final FirebaseUser zza(List<? extends UserInfo> list) {
        Preconditions.checkNotNull(list);
        this.zze = new ArrayList(list.size());
        this.zzf = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            UserInfo userInfo = list.get(i);
            if (userInfo.getProviderId().equals(FirebaseAuthProvider.PROVIDER_ID)) {
                this.zzb = (zzj) userInfo;
            } else {
                this.zzf.add(userInfo.getProviderId());
            }
            this.zze.add((zzj) userInfo);
        }
        if (this.zzb == null) {
            this.zzb = this.zze.get(0);
        }
        return this;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public List<? extends UserInfo> getProviderData() {
        return this.zze;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public final zzey zze() {
        return this.zza;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public final String zzg() {
        return zze().zzd();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    @NonNull
    public final String zzf() {
        return this.zza.zzh();
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public final void zza(zzey zzeyVar) {
        this.zza = (zzey) Preconditions.checkNotNull(zzeyVar);
    }

    @Override // com.google.firebase.auth.UserInfo
    public boolean isEmailVerified() {
        return this.zzb.isEmailVerified();
    }

    public final void zza(zzp zzpVar) {
        this.zzi = zzpVar;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public FirebaseUserMetadata getMetadata() {
        return this.zzi;
    }

    public final void zza(boolean z) {
        this.zzj = z;
    }

    public final boolean zzj() {
        return this.zzj;
    }

    public final void zza(com.google.firebase.auth.zzg zzgVar) {
        this.zzk = zzgVar;
    }

    @Nullable
    public final com.google.firebase.auth.zzg zzk() {
        return this.zzk;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public final void zzb(List<com.google.firebase.auth.zzy> list) {
        this.zzl = zzaq.zza(list);
    }

    @Nullable
    public final List<com.google.firebase.auth.zzy> zzl() {
        zzaq zzaqVar = this.zzl;
        if (zzaqVar != null) {
            return zzaqVar.zza();
        }
        return com.google.android.gms.internal.firebase_auth.zzaz.zza();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, zze(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzd, false);
        SafeParcelWriter.writeTypedList(parcel, 5, this.zze, false);
        SafeParcelWriter.writeStringList(parcel, 6, zza(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzg, false);
        SafeParcelWriter.writeBooleanObject(parcel, 8, Boolean.valueOf(isAnonymous()), false);
        SafeParcelWriter.writeParcelable(parcel, 9, getMetadata(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 10, this.zzj);
        SafeParcelWriter.writeParcelable(parcel, 11, this.zzk, i, false);
        SafeParcelWriter.writeParcelable(parcel, 12, this.zzl, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public static FirebaseUser zza(FirebaseApp firebaseApp, FirebaseUser firebaseUser) {
        zzn zznVar = new zzn(firebaseApp, firebaseUser.getProviderData());
        if (firebaseUser instanceof zzn) {
            zzn zznVar2 = (zzn) firebaseUser;
            zznVar.zzg = zznVar2.zzg;
            zznVar.zzd = zznVar2.zzd;
            zznVar.zzi = (zzp) zznVar2.getMetadata();
        } else {
            zznVar.zzi = null;
        }
        if (firebaseUser.zze() != null) {
            zznVar.zza(firebaseUser.zze());
        }
        if (!firebaseUser.isAnonymous()) {
            zznVar.zzb();
        }
        return zznVar;
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public final /* synthetic */ com.google.firebase.auth.zzz zzh() {
        return new zzr(this);
    }

    @Override // com.google.firebase.auth.FirebaseUser
    public final /* synthetic */ FirebaseUser zzb() {
        this.zzh = false;
        return this;
    }
}
