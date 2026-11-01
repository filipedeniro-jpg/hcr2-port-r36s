package com.google.android.gms.internal.ads;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzdvd {
    public static final zzdvd zzhwt = new zzdvd(1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzdvd zzhwu = new zzdvd(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, -1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzdvd zzhwv = new zzdvd(-1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, -1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private static final zzdvd zzhww = new zzdvd(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, -1.0d, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, 1.0d, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double w;
    private final double zzhwp;
    private final double zzhwq;
    private final double zzhwr;
    private final double zzhws;

    private zzdvd(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        this.zzhwp = d5;
        this.zzhwq = d6;
        this.w = d7;
        this.a = d;
        this.b = d2;
        this.c = d3;
        this.d = d4;
        this.zzhwr = d8;
        this.zzhws = d9;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzdvd zzdvdVar = (zzdvd) obj;
        return Double.compare(zzdvdVar.a, this.a) == 0 && Double.compare(zzdvdVar.b, this.b) == 0 && Double.compare(zzdvdVar.c, this.c) == 0 && Double.compare(zzdvdVar.d, this.d) == 0 && Double.compare(zzdvdVar.zzhwr, this.zzhwr) == 0 && Double.compare(zzdvdVar.zzhws, this.zzhws) == 0 && Double.compare(zzdvdVar.zzhwp, this.zzhwp) == 0 && Double.compare(zzdvdVar.zzhwq, this.zzhwq) == 0 && Double.compare(zzdvdVar.w, this.w) == 0;
    }

    public final int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.zzhwp);
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.zzhwq);
        int i = (((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) * 31) + ((int) (jDoubleToLongBits2 ^ (jDoubleToLongBits2 >>> 32)));
        long jDoubleToLongBits3 = Double.doubleToLongBits(this.w);
        int i2 = (i * 31) + ((int) (jDoubleToLongBits3 ^ (jDoubleToLongBits3 >>> 32)));
        long jDoubleToLongBits4 = Double.doubleToLongBits(this.a);
        int i3 = (i2 * 31) + ((int) (jDoubleToLongBits4 ^ (jDoubleToLongBits4 >>> 32)));
        long jDoubleToLongBits5 = Double.doubleToLongBits(this.b);
        int i4 = (i3 * 31) + ((int) (jDoubleToLongBits5 ^ (jDoubleToLongBits5 >>> 32)));
        long jDoubleToLongBits6 = Double.doubleToLongBits(this.c);
        int i5 = (i4 * 31) + ((int) (jDoubleToLongBits6 ^ (jDoubleToLongBits6 >>> 32)));
        long jDoubleToLongBits7 = Double.doubleToLongBits(this.d);
        int i6 = (i5 * 31) + ((int) (jDoubleToLongBits7 ^ (jDoubleToLongBits7 >>> 32)));
        long jDoubleToLongBits8 = Double.doubleToLongBits(this.zzhwr);
        int i7 = (i6 * 31) + ((int) (jDoubleToLongBits8 ^ (jDoubleToLongBits8 >>> 32)));
        long jDoubleToLongBits9 = Double.doubleToLongBits(this.zzhws);
        return (i7 * 31) + ((int) (jDoubleToLongBits9 ^ (jDoubleToLongBits9 >>> 32)));
    }

    public final String toString() {
        if (equals(zzhwt)) {
            return "Rotate 0°";
        }
        if (equals(zzhwu)) {
            return "Rotate 90°";
        }
        if (equals(zzhwv)) {
            return "Rotate 180°";
        }
        if (equals(zzhww)) {
            return "Rotate 270°";
        }
        double d = this.zzhwp;
        double d2 = this.zzhwq;
        double d3 = this.w;
        double d4 = this.a;
        double d5 = this.b;
        double d6 = this.c;
        double d7 = this.d;
        double d8 = this.zzhwr;
        double d9 = this.zzhws;
        StringBuilder sb = new StringBuilder(260);
        sb.append("Matrix{u=");
        sb.append(d);
        sb.append(", v=");
        sb.append(d2);
        sb.append(", w=");
        sb.append(d3);
        sb.append(", a=");
        sb.append(d4);
        sb.append(", b=");
        sb.append(d5);
        sb.append(", c=");
        sb.append(d6);
        sb.append(", d=");
        sb.append(d7);
        sb.append(", tx=");
        sb.append(d8);
        sb.append(", ty=");
        sb.append(d9);
        sb.append("}");
        return sb.toString();
    }

    public static zzdvd zzp(ByteBuffer byteBuffer) {
        double dZzd = zzbc.zzd(byteBuffer);
        double dZzd2 = zzbc.zzd(byteBuffer);
        double dZze = zzbc.zze(byteBuffer);
        return new zzdvd(dZzd, dZzd2, zzbc.zzd(byteBuffer), zzbc.zzd(byteBuffer), dZze, zzbc.zze(byteBuffer), zzbc.zze(byteBuffer), zzbc.zzd(byteBuffer), zzbc.zzd(byteBuffer));
    }
}
