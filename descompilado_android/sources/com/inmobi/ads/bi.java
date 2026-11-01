package com.inmobi.ads;

import android.content.ContentValues;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import java.util.Map;

/* JADX INFO: compiled from: Placement.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bi {
    public long a;
    public String b;
    public Map<String, String> c;
    public String d;
    String e;
    public InMobiAdRequest.MonetizationContext f;

    public static bi a(long j, Map<String, String> map, String str, String str2) {
        bi biVar = new bi(j, com.inmobi.ads.c.a.a(map), str);
        biVar.d = str2;
        biVar.c = map;
        return biVar;
    }

    private bi(long j, String str, String str2) {
        this.f = InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        this.a = j;
        this.b = str;
        this.e = str2;
        if (this.b == null) {
            this.b = "";
        }
    }

    public bi(ContentValues contentValues) {
        this.f = InMobiAdRequest.MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
        this.a = contentValues.getAsLong(FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER).longValue();
        this.b = contentValues.getAsString("tp_key");
        this.e = contentValues.getAsString(AppEventsConstants.EVENT_PARAM_AD_TYPE);
        this.f = InMobiAdRequest.MonetizationContext.a(contentValues.getAsString("m10_context"));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            bi biVar = (bi) obj;
            if (this.a == biVar.a && this.f == biVar.f && this.b.equals(biVar.b) && this.e.equals(biVar.e)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j = this.a;
        return (((((int) (j ^ (j >>> 32))) * 31) + this.e.hashCode()) * 30) + this.f.hashCode();
    }
}
