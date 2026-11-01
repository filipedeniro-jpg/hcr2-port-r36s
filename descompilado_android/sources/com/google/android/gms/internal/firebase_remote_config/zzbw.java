package com.google.android.gms.internal.firebase_remote_config;

import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public final class zzbw implements Serializable {
    private static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Pattern zzfo = Pattern.compile("^(\\d{4})-(\\d{2})-(\\d{2})([Tt](\\d{2}):(\\d{2}):(\\d{2})(\\.\\d+)?)?([Zz]|([+-])(\\d{2}):(\\d{2}))?");
    private final long value;
    private final boolean zzfp;
    private final int zzfq;

    public zzbw(long j) {
        this(false, 0L, null);
    }

    private zzbw(boolean z, long j, Integer num) {
        int offset;
        this.zzfp = z;
        this.value = j;
        if (z) {
            offset = 0;
        } else {
            offset = num == null ? TimeZone.getDefault().getOffset(j) / 60000 : num.intValue();
        }
        this.zzfq = offset;
    }

    public final String zzbx() {
        StringBuilder sb = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.setTimeInMillis(this.value + (((long) this.zzfq) * ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS));
        zza(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        zza(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        zza(sb, gregorianCalendar.get(5), 2);
        if (!this.zzfp) {
            sb.append('T');
            zza(sb, gregorianCalendar.get(11), 2);
            sb.append(':');
            zza(sb, gregorianCalendar.get(12), 2);
            sb.append(':');
            zza(sb, gregorianCalendar.get(13), 2);
            if (gregorianCalendar.isSet(14)) {
                sb.append('.');
                zza(sb, gregorianCalendar.get(14), 3);
            }
            int i = this.zzfq;
            if (i == 0) {
                sb.append('Z');
            } else {
                if (i > 0) {
                    sb.append('+');
                } else {
                    sb.append('-');
                    i = -i;
                }
                zza(sb, i / 60, 2);
                sb.append(':');
                zza(sb, i % 60, 2);
            }
        }
        return sb.toString();
    }

    public final String toString() {
        return zzbx();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbw)) {
            return false;
        }
        zzbw zzbwVar = (zzbw) obj;
        return this.zzfp == zzbwVar.zzfp && this.value == zzbwVar.value && this.zzfq == zzbwVar.zzfq;
    }

    public final int hashCode() {
        long[] jArr = new long[3];
        jArr[0] = this.value;
        jArr[1] = this.zzfp ? 1L : 0L;
        jArr[2] = this.zzfq;
        return Arrays.hashCode(jArr);
    }

    public static zzbw zzaf(String str) throws NumberFormatException {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Matcher matcher = zzfo.matcher(str);
        if (!matcher.matches()) {
            String strValueOf = String.valueOf(str);
            throw new NumberFormatException(strValueOf.length() != 0 ? "Invalid date/time format: ".concat(strValueOf) : new String("Invalid date/time format: "));
        }
        int i6 = Integer.parseInt(matcher.group(1));
        int i7 = Integer.parseInt(matcher.group(2)) - 1;
        int i8 = Integer.parseInt(matcher.group(3));
        boolean z = matcher.group(4) != null;
        String strGroup = matcher.group(9);
        boolean z2 = strGroup != null;
        Integer numValueOf = null;
        if (z2 && !z) {
            String strValueOf2 = String.valueOf(str);
            throw new NumberFormatException(strValueOf2.length() != 0 ? "Invalid date/time format, cannot specify time zone shift without specifying time: ".concat(strValueOf2) : new String("Invalid date/time format, cannot specify time zone shift without specifying time: "));
        }
        if (z) {
            int i9 = Integer.parseInt(matcher.group(5));
            i2 = Integer.parseInt(matcher.group(6));
            i3 = Integer.parseInt(matcher.group(7));
            if (matcher.group(8) != null) {
                double d = Integer.parseInt(matcher.group(8).substring(1));
                double dPow = Math.pow(10.0d, matcher.group(8).substring(1).length() - 3);
                Double.isNaN(d);
                i4 = (int) (d / dPow);
            } else {
                i4 = 0;
            }
            i = i9;
        } else {
            z = z;
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar(GMT);
        gregorianCalendar.set(i6, i7, i8, i, i2, i3);
        gregorianCalendar.set(14, i4);
        long timeInMillis = gregorianCalendar.getTimeInMillis();
        if (z && z2) {
            if (Character.toUpperCase(strGroup.charAt(0)) == 'Z') {
                i5 = 0;
            } else {
                int i10 = (Integer.parseInt(matcher.group(11)) * 60) + Integer.parseInt(matcher.group(12));
                int i11 = matcher.group(10).charAt(0) == '-' ? -i10 : i10;
                timeInMillis -= ((long) i11) * ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS;
                i5 = i11;
            }
            numValueOf = Integer.valueOf(i5);
        }
        return new zzbw(!z, timeInMillis, numValueOf);
    }

    private static void zza(StringBuilder sb, int i, int i2) {
        if (i < 0) {
            sb.append('-');
            i = -i;
        }
        int i3 = i2;
        int i4 = i;
        while (i4 > 0) {
            i4 /= 10;
            i3--;
        }
        for (int i5 = 0; i5 < i3; i5++) {
            sb.append('0');
        }
        if (i != 0) {
            sb.append(i);
        }
    }
}
