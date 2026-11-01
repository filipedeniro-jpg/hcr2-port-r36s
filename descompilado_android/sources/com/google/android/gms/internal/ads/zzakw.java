package com.google.android.gms.internal.ads;

import com.google.ads.AdRequest;
import com.google.ads.mediation.MediationAdRequest;
import java.util.Date;
import java.util.HashSet;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzakw {
    public static int zza(AdRequest.ErrorCode errorCode) {
        int i = zzakv.zzdee[errorCode.ordinal()];
        if (i == 2) {
            return 1;
        }
        if (i != 3) {
            return i != 4 ? 0 : 3;
        }
        return 2;
    }

    public static MediationAdRequest zza(zztp zztpVar, boolean z) {
        AdRequest.Gender gender;
        HashSet hashSet = zztpVar.zzcbp != null ? new HashSet(zztpVar.zzcbp) : null;
        Date date = new Date(zztpVar.zzcbn);
        int i = zztpVar.zzcbo;
        if (i == 1) {
            gender = AdRequest.Gender.MALE;
        } else if (i == 2) {
            gender = AdRequest.Gender.FEMALE;
        } else {
            gender = AdRequest.Gender.UNKNOWN;
        }
        return new MediationAdRequest(date, gender, hashSet, z, zztpVar.zzng);
    }
}
