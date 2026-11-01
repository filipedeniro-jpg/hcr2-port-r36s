package com.google.android.gms.internal.firebase_auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import androidx.annotation.Nullable;
import com.facebook.AccessToken;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.Strings;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.firebase:firebase-auth@@18.1.0 */
/* JADX INFO: loaded from: classes2.dex */
@SafeParcelable.Class(creator = "GetTokenResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzey extends AbstractSafeParcelable implements com.google.firebase.auth.api.internal.zzea<zzey, zzlo.zzb> {
    public static final Parcelable.Creator<zzey> CREATOR = new zzex();

    @SafeParcelable.Field(getter = "getRefreshToken", id = 2)
    private String zza;

    @SafeParcelable.Field(getter = "getAccessToken", id = 3)
    private String zzb;

    @SafeParcelable.Field(getter = "getExpiresIn", id = 4)
    private Long zzc;

    @SafeParcelable.Field(getter = "getTokenType", id = 5)
    private String zzd;

    @SafeParcelable.Field(getter = "getIssuedAt", id = 6)
    private Long zze;

    public zzey() {
        this.zze = Long.valueOf(System.currentTimeMillis());
    }

    public zzey(String str, String str2, Long l, String str3) {
        this(str, str2, l, str3, Long.valueOf(System.currentTimeMillis()));
    }

    @SafeParcelable.Constructor
    zzey(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) String str2, @SafeParcelable.Param(id = 4) Long l, @SafeParcelable.Param(id = 5) String str3, @SafeParcelable.Param(id = 6) Long l2) {
        this.zza = str;
        this.zzb = str2;
        this.zzc = l;
        this.zzd = str3;
        this.zze = l2;
    }

    public final boolean zzb() {
        return DefaultClock.getInstance().currentTimeMillis() + 300000 < this.zze.longValue() + (this.zzc.longValue() * 1000);
    }

    public final void zza(String str) {
        this.zza = Preconditions.checkNotEmpty(str);
    }

    public final String zzc() {
        return this.zza;
    }

    public final String zzd() {
        return this.zzb;
    }

    public final long zze() {
        Long l = this.zzc;
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    @Nullable
    public final String zzf() {
        return this.zzd;
    }

    public final long zzg() {
        return this.zze.longValue();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zza, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzb, false);
        SafeParcelWriter.writeLongObject(parcel, 4, Long.valueOf(zze()), false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.writeLongObject(parcel, 6, Long.valueOf(this.zze.longValue()), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final zzjq<zzlo.zzb> zza() {
        return zzlo.zzb.zze();
    }

    public final String zzh() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("refresh_token", this.zza);
            jSONObject.put("access_token", this.zzb);
            jSONObject.put(AccessToken.EXPIRES_IN_KEY, this.zzc);
            jSONObject.put("token_type", this.zzd);
            jSONObject.put("issued_at", this.zze);
            return jSONObject.toString();
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to convert GetTokenResponse to JSON");
            throw new zzbq(e);
        }
    }

    public static zzey zzb(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            zzey zzeyVar = new zzey();
            zzeyVar.zza = jSONObject.optString("refresh_token", null);
            zzeyVar.zzb = jSONObject.optString("access_token", null);
            zzeyVar.zzc = Long.valueOf(jSONObject.optLong(AccessToken.EXPIRES_IN_KEY));
            zzeyVar.zzd = jSONObject.optString("token_type", null);
            zzeyVar.zze = Long.valueOf(jSONObject.optLong("issued_at"));
            return zzeyVar;
        } catch (JSONException e) {
            Log.d("GetTokenResponse", "Failed to read GetTokenResponse from JSONObject");
            throw new zzbq(e);
        }
    }

    @Override // com.google.firebase.auth.api.internal.zzea
    public final /* synthetic */ com.google.firebase.auth.api.internal.zzea zza(zzjg zzjgVar) {
        if (!(zzjgVar instanceof zzlo.zzb)) {
            throw new IllegalArgumentException("The passed proto must be an instance of GrantTokenResponse.");
        }
        zzlo.zzb zzbVar = (zzlo.zzb) zzjgVar;
        this.zza = Strings.emptyToNull(zzbVar.zzd());
        this.zzb = Strings.emptyToNull(zzbVar.zza());
        this.zzc = Long.valueOf(zzbVar.zzb());
        this.zzd = Strings.emptyToNull(zzbVar.zzc());
        this.zze = Long.valueOf(System.currentTimeMillis());
        return this;
    }
}
