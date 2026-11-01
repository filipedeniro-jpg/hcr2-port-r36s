package com.google.android.gms.internal.ads;

import com.facebook.appevents.AppEventsConstants;
import java.math.BigInteger;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzatg {

    @GuardedBy("this")
    private BigInteger zzdrg = BigInteger.ONE;

    @GuardedBy("this")
    private String zzdil = AppEventsConstants.EVENT_PARAM_VALUE_NO;

    public final synchronized String zzun() {
        String string;
        string = this.zzdrg.toString();
        this.zzdrg = this.zzdrg.add(BigInteger.ONE);
        this.zzdil = string;
        return string;
    }

    public final synchronized String zzuo() {
        return this.zzdil;
    }
}
