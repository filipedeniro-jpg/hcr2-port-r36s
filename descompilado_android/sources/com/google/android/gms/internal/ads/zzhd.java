package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import com.ironsource.sdk.constants.Constants;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(21)
public final class zzhd {
    private static final zzhd zzagq = new zzhd(new int[]{2}, 2);
    private final int[] zzagr;
    private final int zzags;

    private zzhd(int[] iArr, int i) {
        this.zzagr = Arrays.copyOf(iArr, iArr.length);
        Arrays.sort(this.zzagr);
        this.zzags = 2;
    }

    public final boolean zzp(int i) {
        return Arrays.binarySearch(this.zzagr, i) >= 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhd)) {
            return false;
        }
        zzhd zzhdVar = (zzhd) obj;
        return Arrays.equals(this.zzagr, zzhdVar.zzagr) && this.zzags == zzhdVar.zzags;
    }

    public final int hashCode() {
        return this.zzags + (Arrays.hashCode(this.zzagr) * 31);
    }

    public final String toString() {
        int i = this.zzags;
        String string = Arrays.toString(this.zzagr);
        StringBuilder sb = new StringBuilder(String.valueOf(string).length() + 67);
        sb.append("AudioCapabilities[maxChannelCount=");
        sb.append(i);
        sb.append(", supportedEncodings=");
        sb.append(string);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }
}
