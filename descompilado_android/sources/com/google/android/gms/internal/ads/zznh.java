package com.google.android.gms.internal.ads;

import android.net.Uri;
import com.ironsource.sdk.constants.Constants;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zznh {
    public final int flags;
    public final Uri uri;
    public final long zzams;
    public final byte[] zzbeq;
    public final long zzber;
    public final long zzcb;
    public final String zzce;

    public zznh(Uri uri) {
        this(uri, 0);
    }

    private zznh(Uri uri, int i) {
        this(uri, 0L, -1L, null, 0);
    }

    public zznh(Uri uri, long j, long j2, String str) {
        this(uri, j, j, j2, str, 0);
    }

    private zznh(Uri uri, long j, long j2, String str, int i) {
        this(uri, 0L, 0L, -1L, null, 0);
    }

    private zznh(Uri uri, long j, long j2, long j3, String str, int i) {
        this(uri, null, j, j2, j3, str, i);
    }

    public zznh(Uri uri, byte[] bArr, long j, long j2, long j3, String str, int i) {
        boolean z = true;
        zznt.checkArgument(j >= 0);
        zznt.checkArgument(j2 >= 0);
        if (j3 <= 0 && j3 != -1) {
            z = false;
        }
        zznt.checkArgument(z);
        this.uri = uri;
        this.zzbeq = bArr;
        this.zzber = j;
        this.zzams = j2;
        this.zzcb = j3;
        this.zzce = str;
        this.flags = i;
    }

    public final boolean zzba(int i) {
        return (this.flags & 1) == 1;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.uri);
        String string = Arrays.toString(this.zzbeq);
        long j = this.zzber;
        long j2 = this.zzams;
        long j3 = this.zzcb;
        String str = this.zzce;
        int i = this.flags;
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 93 + String.valueOf(string).length() + String.valueOf(str).length());
        sb.append("DataSpec[");
        sb.append(strValueOf);
        sb.append(", ");
        sb.append(string);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append(", ");
        sb.append(j3);
        sb.append(", ");
        sb.append(str);
        sb.append(", ");
        sb.append(i);
        sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
        return sb.toString();
    }
}
