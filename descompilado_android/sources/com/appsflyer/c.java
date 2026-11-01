package com.appsflyer;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.hardware.SensorManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.extractor.ts.TsExtractor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.internal.http.StatusLine;

/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static int f104 = 0;

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static int f106 = 1;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static char[] f103 = {12953, 46792, 14880, 49040, 9199, 42823, 10408, 44100, 4220, 38355, 6456, 33431, 1760, 35414, 4019, 62228, 30561, 63720, 31792, 57447, 26060, 59691, 21148, 55010, 23111, 57272, 17165, 51039, 18641, 52272, 45469, 13821, 47453, 8852, 36797, '0', 47217, 15407, 45261, 13675, 43357, 11667, 41585, 9911, 39580, 7970, 37883, 2122, 35880, 163, 34113, 31225, 64923, 29292, 'b', 33855, 2270, 36223, 4377, 38323, 6660, 40615, 8905, 42794, 11161, 45113, 13321, 47268, 15645, 49585, 17876, 51800, 20136, 53919, 22380, 56275, 24608, 58434, 26804, 60695, 29117, 62860, 31354, 65174, 33589, 1875, 'a', 33840, 2264, 36200, 4375, 38335, 6736, 40636, 8863, 42813, 11138, 45128, 13321, 47282, 15696, 49639, 17810, 51783, 20177, 53915, 22326, 56279, 24691, 58391, 26786, 'a', 33840, 2264, 36200, 4375, 38335, 6736, 40636, 8856, 42799, 11230, 45166, 13343, 47271, 15702, 49639, 17870, 51821, 20217, 53908, 22315, 56281, 24678, 'a', 33840, 2264, 36200, 4375, 38335, 6736, 40636, 8863, 42813, 11138, 45131, 13339, 47295, 15690, 49633, 17844, 51807, 20207, 53905, 46291, 12418, 48234, 14810, 42405, 8461, 44770, 10766, 38444, 5017, 40810, 1174, 32911, 3078, 35327, '.', '\\', 33904, 'j', 33855, 2250, 36219, 4438, 38335, 6747, 40636, 8836, 42787, 11228, 45166, 13313, 47284, '/', 33853, 2269, 36217, 4368, 38323, '.', 33908, 2195, 36166, 4363, 38396, 6684, 40636, 8922, 42855, 'C', 33846, 2265, 36217, 4371, 38309, 6721, 40703, 8885, 42806, 11215, 45167, 13336, 47282, 15693, 49645, 17806, 'F', 33855, 2261, 36214, 4381, 38322, 6676, 40678, 8863, 42862, 11211, 45167, 13340, 47334, 15687, 49635, 17795, 51798, 20217, 53978, 22312, 56279, 24672, 58394, 26864, 60761, 29157, 62878, 31264, 65158, 33633, 1818, 35747, 4219, 37900, 6318, 40273, 8697, 42394, 10856, 44688, 'g', 33851, 2248, 36179, 4374, 38309, 6720, 40691, 8860, 42786, 11209, 45166, 13353, 47286, 15700, 49646, 17801, 51805, 20221, 53902, 22321, 56281, 24698, 58369, 'C', 33846, 2265, 36217, 4371, 38309, 6721, 40703, 8866, 42795, 11210, 45158, 13325, 47269, 15696, 49607, 17816, 51805, 20217, 53898, 22316, 56287, 24699, 58396, 37970, 4139, 40129, 6498, 34057, 422, 36352, 2799, 46730, 13100, 49111, 9333, 40985, 11506, 43330, 22003, 53650, 24134, 56045, 18061, 49976, 20423, 62564, 28742, 64681, 31071, 58860, 24982, 60979, 27350, 5936, 37633, 8125, 33918, 0, 36078, 2377, 46586, 12675, 48675, 15060, 42862, 8977, 44977, 21586, 53416, 23760};

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static long f105 = 2406303584873383006L;

    c() {
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0143  */
    /* JADX WARN: Code duplicated, block: B:30:0x0146  */
    /* JADX WARN: Code duplicated, block: B:32:0x014a  */
    /* JADX WARN: Code duplicated, block: B:34:0x0154  */
    /* JADX WARN: Code duplicated, block: B:35:0x0156  */
    /* JADX WARN: Code duplicated, block: B:37:0x0159  */
    /* JADX WARN: Code duplicated, block: B:38:0x0164  */
    /* JADX WARN: Code duplicated, block: B:39:0x0169  */
    /* JADX WARN: Code duplicated, block: B:43:0x0186  */
    /* JADX WARN: Code duplicated, block: B:44:0x0194  */
    /* JADX WARN: Code duplicated, block: B:47:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:48:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:50:0x01bd  */
    /* JADX WARN: Code duplicated, block: B:52:0x01c6  */
    @Nullable
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static String m100(Context context, long j) {
        String strM102;
        String strM103;
        String strIntern;
        char c;
        String strM104;
        String strIntern2;
        String strM105;
        char c2;
        String strM106;
        int i;
        boolean z;
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        if (m104(m102(34, 0, (char) 13048).intern())) {
            int i2 = f104 + 103;
            f106 = i2 % 128;
            int i3 = i2 % 2;
            strM102 = m102(1, 34, (char) 36748);
        } else {
            strM102 = m102(1, 35, (char) 0);
        }
        sb2.append(strM102.intern());
        StringBuilder sb4 = new StringBuilder();
        String packageName = context.getPackageName();
        String strM107 = m105(packageName);
        sb2.append(m102(1, 34, (char) 36748).intern());
        sb4.append(strM107);
        if ((m106(context) == null ? 'Z' : '!') != 'Z') {
            sb2.append(m102(1, 34, (char) 36748).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m102(1, 35, (char) 0).intern());
            sb4.append(packageName);
        }
        String strM99 = m99(context);
        if (strM99 == null) {
            sb2.append(m102(1, 35, (char) 0).intern());
            sb4.append(packageName);
        } else {
            sb2.append(m102(1, 34, (char) 36748).intern());
            sb4.append(strM99);
        }
        sb4.append(m103(context, packageName));
        sb.append(sb4.toString());
        try {
            long j2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).firstInstallTime;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(m102(18, 36, (char) 47112).intern(), Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            sb.append(simpleDateFormat.format(new Date(j2)));
            sb.append(j);
            if (m104(m102(25, 86, (char) 0).intern())) {
                int i4 = f106 + 13;
                f104 = i4 % 128;
                if (i4 % 2 != 0) {
                    strIntern = m102(1, 99, (char) 36748).intern();
                } else {
                    strM103 = m102(1, 34, (char) 36748);
                }
                sb3.append(strIntern);
                if (m104(m102(23, 111, (char) 0).intern())) {
                    c = 'O';
                } else {
                    c = 'D';
                }
                if (c != 'D') {
                    i = f104 + 1;
                    f106 = i % 128;
                    if (i % 2 == 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (z) {
                        strIntern2 = m102(1, 45, (char) 36748).intern();
                    } else {
                        strM104 = m102(1, 34, (char) 36748);
                    }
                    sb3.append(strIntern2);
                    if (m104(m102(20, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, (char) 0).intern())) {
                        int i5 = f104 + 87;
                        f106 = i5 % 128;
                        int i6 = i5 % 2;
                        strM105 = m102(1, 34, (char) 36748);
                    } else {
                        strM105 = m102(1, 35, (char) 0);
                    }
                    sb3.append(strM105.intern());
                    if (m104(m102(15, 154, (char) 46258).intern())) {
                        c2 = ')';
                    } else {
                        c2 = 'H';
                    }
                    if (c2 != 'H') {
                        strM106 = m102(1, 34, (char) 36748);
                    } else {
                        strM106 = m102(1, 35, (char) 0);
                    }
                    sb3.append(strM106.intern());
                    String strM179 = C0120r.m179(C0120r.m177(sb.toString()));
                    String string = sb2.toString();
                    StringBuilder sb5 = new StringBuilder(strM179);
                    sb5.setCharAt(17, Integer.toString(Integer.parseInt(string, 2), 16).charAt(0));
                    String string2 = sb5.toString();
                    String string3 = sb3.toString();
                    StringBuilder sb6 = new StringBuilder(string2);
                    sb6.setCharAt(27, Integer.toString(Integer.parseInt(string3, 2), 16).charAt(0));
                    return m101(sb6.toString(), Long.valueOf(j));
                }
                strM104 = m102(1, 35, (char) 0);
                strIntern2 = strM104.intern();
                sb3.append(strIntern2);
                if (m104(m102(20, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, (char) 0).intern())) {
                    int i7 = f104 + 87;
                    f106 = i7 % 128;
                    int i8 = i7 % 2;
                    strM105 = m102(1, 34, (char) 36748);
                } else {
                    strM105 = m102(1, 35, (char) 0);
                }
                sb3.append(strM105.intern());
                if (m104(m102(15, 154, (char) 46258).intern())) {
                    c2 = ')';
                } else {
                    c2 = 'H';
                }
                if (c2 != 'H') {
                    strM106 = m102(1, 34, (char) 36748);
                } else {
                    strM106 = m102(1, 35, (char) 0);
                }
                sb3.append(strM106.intern());
                String strM1710 = C0120r.m179(C0120r.m177(sb.toString()));
                String string4 = sb2.toString();
                StringBuilder sb7 = new StringBuilder(strM1710);
                sb7.setCharAt(17, Integer.toString(Integer.parseInt(string4, 2), 16).charAt(0));
                String string5 = sb7.toString();
                String string6 = sb3.toString();
                StringBuilder sb8 = new StringBuilder(string5);
                sb8.setCharAt(27, Integer.toString(Integer.parseInt(string6, 2), 16).charAt(0));
                return m101(sb8.toString(), Long.valueOf(j));
            }
            strM103 = m102(1, 35, (char) 0);
            strIntern = strM103.intern();
            sb3.append(strIntern);
            if (m104(m102(23, 111, (char) 0).intern())) {
                c = 'O';
            } else {
                c = 'D';
            }
            if (c != 'D') {
                i = f104 + 1;
                f106 = i % 128;
                if (i % 2 == 0) {
                    z = true;
                } else {
                    z = false;
                }
                if (z) {
                    strIntern2 = m102(1, 45, (char) 36748).intern();
                } else {
                    strM104 = m102(1, 34, (char) 36748);
                }
                sb3.append(strIntern2);
                if (m104(m102(20, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, (char) 0).intern())) {
                    int i9 = f104 + 87;
                    f106 = i9 % 128;
                    int i10 = i9 % 2;
                    strM105 = m102(1, 34, (char) 36748);
                } else {
                    strM105 = m102(1, 35, (char) 0);
                }
                sb3.append(strM105.intern());
                if (m104(m102(15, 154, (char) 46258).intern())) {
                    c2 = ')';
                } else {
                    c2 = 'H';
                }
                if (c2 != 'H') {
                    strM106 = m102(1, 34, (char) 36748);
                } else {
                    strM106 = m102(1, 35, (char) 0);
                }
                sb3.append(strM106.intern());
                String strM1711 = C0120r.m179(C0120r.m177(sb.toString()));
                String string7 = sb2.toString();
                StringBuilder sb9 = new StringBuilder(strM1711);
                sb9.setCharAt(17, Integer.toString(Integer.parseInt(string7, 2), 16).charAt(0));
                String string8 = sb9.toString();
                String string9 = sb3.toString();
                StringBuilder sb10 = new StringBuilder(string8);
                sb10.setCharAt(27, Integer.toString(Integer.parseInt(string9, 2), 16).charAt(0));
                return m101(sb10.toString(), Long.valueOf(j));
            }
            strM104 = m102(1, 35, (char) 0);
            strIntern2 = strM104.intern();
            sb3.append(strIntern2);
            if (m104(m102(20, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, (char) 0).intern())) {
                int i11 = f104 + 87;
                f106 = i11 % 128;
                int i12 = i11 % 2;
                strM105 = m102(1, 34, (char) 36748);
            } else {
                strM105 = m102(1, 35, (char) 0);
            }
            sb3.append(strM105.intern());
            if (m104(m102(15, 154, (char) 46258).intern())) {
                c2 = ')';
            } else {
                c2 = 'H';
            }
            if (c2 != 'H') {
                strM106 = m102(1, 34, (char) 36748);
            } else {
                strM106 = m102(1, 35, (char) 0);
            }
            sb3.append(strM106.intern());
            String strM1712 = C0120r.m179(C0120r.m177(sb.toString()));
            String string10 = sb2.toString();
            StringBuilder sb11 = new StringBuilder(strM1712);
            sb11.setCharAt(17, Integer.toString(Integer.parseInt(string10, 2), 16).charAt(0));
            String string11 = sb11.toString();
            String string12 = sb3.toString();
            StringBuilder sb12 = new StringBuilder(string11);
            sb12.setCharAt(27, Integer.toString(Integer.parseInt(string12, 2), 16).charAt(0));
            return m101(sb12.toString(), Long.valueOf(j));
        } catch (PackageManager.NameNotFoundException unused) {
            return m102(32, 54, (char) 0).intern();
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static String m101(String str, Long l) {
        int i = f106 + 103;
        f104 = i % 128;
        int i2 = i % 2;
        if (str != null && l != null && str.length() == 32) {
            StringBuilder sb = new StringBuilder(str);
            String string = l.toString();
            long numericValue = 0;
            int i3 = 0;
            int numericValue2 = 0;
            while (true) {
                if ((i3 < string.length() ? '[' : (char) 28) != '[') {
                    break;
                }
                numericValue2 += Character.getNumericValue(string.charAt(i3));
                i3++;
            }
            String hexString = Integer.toHexString(numericValue2);
            sb.replace(7, hexString.length() + 7, hexString);
            int i4 = 0;
            while (true) {
                if (!(i4 < sb.length())) {
                    break;
                }
                int i5 = f104 + 71;
                f106 = i5 % 128;
                if (i5 % 2 == 0) {
                    numericValue /= (long) Character.getNumericValue(sb.charAt(i4));
                    i4 += 106;
                } else {
                    numericValue += (long) Character.getNumericValue(sb.charAt(i4));
                    i4++;
                }
            }
            while (true) {
                if ((numericValue > 100 ? 'S' : 'D') == 'D') {
                    break;
                }
                numericValue %= 100;
            }
            sb.insert(23, (int) numericValue);
            if ((numericValue < 10 ? '\\' : (char) 31) != 31) {
                int i6 = f104 + 89;
                f106 = i6 % 128;
                if (i6 % 2 != 0) {
                    sb.insert(23, m102(1, 35, (char) 0).intern());
                } else {
                    sb.insert(40, m102(0, 86, (char) 0).intern());
                }
            }
            String string2 = sb.toString();
            int i7 = f104 + 99;
            f106 = i7 % 128;
            int i8 = i7 % 2;
            return string2;
        }
        return m102(32, 54, (char) 0).intern();
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean m104(String str) {
        int i = f104 + 39;
        f106 = i % 128;
        int i2 = i % 2;
        try {
            Class.forName(str);
            int i3 = f104 + 3;
            f106 = i3 % 128;
            if ((i3 % 2 == 0 ? (char) 11 : (char) 26) != 11) {
                return true;
            }
            Object[] objArr = null;
            int length = objArr.length;
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static String m105(String str) {
        if (!(str.contains(m102(1, 169, (char) 0).intern()))) {
            return str;
        }
        String[] strArrSplit = str.split(m102(2, BuildConfig.VERSION_CODE, (char) 0).intern());
        int length = strArrSplit.length;
        StringBuilder sb = new StringBuilder();
        int i = length - 1;
        sb.append(strArrSplit[i]);
        sb.append(m102(1, 169, (char) 0).intern());
        int i2 = f106 + 67;
        f104 = i2 % 128;
        if (i2 % 2 != 0) {
        }
        int i3 = 1;
        while (i3 < i) {
            int i4 = f104 + 27;
            f106 = i4 % 128;
            if ((i4 % 2 == 0 ? '>' : '?') != '?') {
                sb.append(strArrSplit[i3]);
                sb.append(m102(1, 4461, (char) 0).intern());
                i3 += 54;
            } else {
                sb.append(strArrSplit[i3]);
                sb.append(m102(1, 169, (char) 0).intern());
                i3++;
            }
        }
        sb.append(strArrSplit[0]);
        return sb.toString();
    }

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static String m106(Context context) {
        if ((System.getProperties().containsKey(m102(14, 172, (char) 0).intern()) ? 'O' : 'T') == 'T') {
            return null;
        }
        int i = f106 + 55;
        f104 = i % 128;
        int i2 = i % 2;
        try {
            Matcher matcher = Pattern.compile(m102(10, PsExtractor.AUDIO_STREAM, (char) 0).intern()).matcher(context.getCacheDir().getPath().replace(m102(6, 186, (char) 0).intern(), ""));
            if (!matcher.find()) {
                return null;
            }
            String strGroup = matcher.group(1);
            int i3 = f106 + 53;
            f104 = i3 % 128;
            if (i3 % 2 != 0) {
            }
            return strGroup;
        } catch (Exception e) {
            s sVarM187 = s.m187();
            String strIntern = m102(17, 202, (char) 0).intern();
            StringBuilder sb = new StringBuilder();
            sb.append(m102(41, 219, (char) 0).intern());
            sb.append(e);
            sVarM187.m193(strIntern, sb.toString());
            return null;
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static String m99(Context context) {
        int i = f104 + 43;
        f106 = i % 128;
        try {
            String str = (!(i % 2 == 0) ? context.getPackageManager().getPackageInfo(context.getPackageName(), 0) : context.getPackageManager().getPackageInfo(context.getPackageName(), 1)).packageName;
            int i2 = f106 + 69;
            f104 = i2 % 128;
            int i3 = i2 % 2;
            return str;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m103(Context context, String str) {
        try {
            Iterator it = ((List) PackageManager.class.getDeclaredMethod(m102(24, 260, (char) 0).intern(), Integer.TYPE).invoke(context.getPackageManager(), 0)).iterator();
            int i = f106 + 49;
            f104 = i % 128;
            int i2 = i % 2;
            do {
                if ((it.hasNext() ? (char) 3 : (char) 23) == 23) {
                    int i3 = f104 + 57;
                    f106 = i3 % 128;
                    int i4 = i3 % 2;
                }
                return Boolean.FALSE.toString();
            } while ((((PackageItemInfo) ((ApplicationInfo) it.next())).packageName.equals(str) ? (char) 27 : (char) 31) != 27);
            int i5 = f106 + 21;
            f104 = i5 % 128;
            int i6 = i5 % 2;
            return Boolean.TRUE.toString();
        } catch (IllegalAccessException e) {
            s sVarM187 = s.m187();
            String strIntern = m102(24, 284, (char) 0).intern();
            StringBuilder sb = new StringBuilder();
            sb.append(m102(47, StatusLine.HTTP_PERM_REDIRECT, (char) 37908).intern());
            sb.append(e);
            sVarM187.m193(strIntern, sb.toString());
        } catch (NoSuchMethodException e2) {
            s sVarM188 = s.m187();
            String strIntern2 = m102(24, 284, (char) 0).intern();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(m102(47, StatusLine.HTTP_PERM_REDIRECT, (char) 37908).intern());
            sb2.append(e2);
            sVarM188.m193(strIntern2, sb2.toString());
        } catch (InvocationTargetException e3) {
            s sVarM189 = s.m187();
            String strIntern3 = m102(24, 284, (char) 0).intern();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(m102(47, StatusLine.HTTP_PERM_REDIRECT, (char) 37908).intern());
            sb3.append(e3);
            sVarM189.m193(strIntern3, sb3.toString());
        }
    }

    static class a extends HashMap<String, Object> {

        /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
        private static int f107 = 1;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        private static int f109;

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        private final Map<String, Object> f111;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private final Context f112;

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private static char[] f108 = {'a', 33465, 1505, 35049, 2837, 36406, 4447, 37994, 5772, 39350, 7387, 40933, 'b', 33453, 1503, 35059, 2840, 'N', 33424, 1505, 35039, 2862, 36378, 4468, 37981, 13289, 45368, 13914, 4021, 36205, 2564, 34594, 1226, 33260, 7881, 39853, 6478, 38506, 4864, 36916, 11718, 43764, 10120, 42156, 8772, 48956, 15382, 47419, 14041, 46008, 12434, 52671, 19266, 51252, 17666, 49727, 24515, 56568, 22993, 54967, 21579, 53519, 28200, 60382, 26875, 58753, 25254, 57412, 32049, 64068, 1004, 33085, 1631, 35619, 2242, 36325, 4740, 7213, 40701, 6535, 38053, 5955, 37468, 3344, 34855, 2781, 34303, 149, 33690, 15966, 47468, 13340, '4', 33521, 1414, 34995, 2894, 36459, '-', 33453, 1501, 34995, 2902, 65354, 32146, 64251, 30685, 62517, 28947, 60982, 27474, 59825, 26261, 58367, 24779, 56633, 23051, 55159, 21587, 53947, 20419, 52457, 18884, 50726, 17223, 49264, 15684, 48040, 14494, 46575, 12937, 44863, 11270, 43386, 9797, 42220, 8694, 40650, 6962, 38933, 5479, 37442, 4284, 36251, 2805, 34688, 1145, 36258, 3966, 34844, 1336, 34525, 1022, 40138, 6636, 39692, 5159, 37138, 4716, 44933, 10485, 42432, 9953, 40961, 15671, 'a', 33457, 1498, 35055, 2835, 36402, 4446, 37943, 5777, 39353, 7362, 40944, 8730, 42279, 10268, 43888, 11667, 45243, 13255, 46818, 14594, 48229, 16232, 49736, 17596, 51091, 19171, 52695, 20541, 54044, 22113, 55625, 23457, 57073, 25049, 58424, 26392, 't', 33466, 1491, 35053, 2841, 36393, 4443, 37997, 5773, 39333, 7379, 'x', 33511, 1416, 's', 33466, 1488, 35054, 2835, 36393, 'b', '&', 33447, '&', 33452, 15218, 47611, 20017, 52408, 19352, 50938, 17754, 49213, 24349, 55935, 22751, 55218, 21203, 53665, 27723, 60272, 26116, 58667};

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private static long f110 = -701580288792034593L;

        a(Map<String, Object> map, Context context) {
            this.f111 = map;
            this.f112 = context;
            put(m108(), m110());
        }

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private static StringBuilder m109(@NonNull String... strArr) throws Exception {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                if (i >= 3) {
                    break;
                }
                int i2 = f109 + 63;
                f107 = i2 % 128;
                if (i2 % 2 == 0) {
                    arrayList.add(Integer.valueOf(strArr[i].length()));
                    i += 31;
                } else {
                    arrayList.add(Integer.valueOf(strArr[i].length()));
                    i++;
                }
            }
            Collections.sort(arrayList);
            int iIntValue = ((Integer) arrayList.get(0)).intValue();
            StringBuilder sb = new StringBuilder();
            for (int i3 = 0; i3 < iIntValue; i3++) {
                int i4 = f107 + 99;
                f109 = i4 % 128;
                Integer numValueOf = null;
                int i5 = f109 + 67;
                f107 = i5 % 128;
                int i6 = i5 % 2;
                for (int i7 = (i4 % 2 != 0 ? '9' : 'S') != 'S' ? 1 : 0; i7 < 3; i7++) {
                    int i8 = f109 + 87;
                    f107 = i8 % 128;
                    int i9 = i8 % 2;
                    int iCharAt = strArr[i7].charAt(i3);
                    if ((numValueOf == null ? '\\' : '\'') == '\'') {
                        iCharAt ^= numValueOf.intValue();
                    } else {
                        int i10 = f109 + 55;
                        f107 = i10 % 128;
                        int i11 = i10 % 2;
                        int i12 = f107 + 39;
                        f109 = i12 % 128;
                        int i13 = i12 % 2;
                    }
                    numValueOf = Integer.valueOf(iCharAt);
                }
                sb.append(Integer.toHexString(numValueOf.intValue()));
            }
            return sb;
        }

        @NonNull
        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        private String m108() {
            try {
                String string = Integer.toString(Build.VERSION.SDK_INT);
                String string2 = this.f111.get(m107(12, 0, (char) 0).intern()).toString();
                String string3 = this.f111.get(m107(5, 12, (char) 0).intern()).toString();
                if (string3 == null) {
                    string3 = m107(8, 17, (char) 0).intern();
                    int i = f107 + 119;
                    f109 = i % 128;
                    int i2 = i % 2;
                }
                StringBuilder sb = new StringBuilder(string2);
                sb.reverse();
                StringBuilder sbM109 = m109(string, string3, sb.toString());
                int length = sbM109.length();
                if (length > 4) {
                    int i3 = f107 + 65;
                    f109 = i3 % 128;
                    if (!(i3 % 2 != 0)) {
                        sbM109.delete(4, length);
                    } else {
                        sbM109.delete(5, length);
                    }
                } else {
                    while (length < 4) {
                        int i4 = f107 + 119;
                        f109 = i4 % 128;
                        if ((i4 % 2 != 0 ? (char) 16 : ']') != 16) {
                            length++;
                            sbM109.append('1');
                        } else {
                            length += 66;
                            sbM109.append('H');
                        }
                        int i5 = f107 + 109;
                        f109 = i5 % 128;
                        int i6 = i5 % 2;
                    }
                }
                sbM109.insert(0, m107(3, 25, (char) 13186).intern());
                return sbM109.toString();
            } catch (Exception e) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(m107(42, 28, (char) 4051).intern());
                sb2.append(e);
                AFLogger.afRDLog(sb2.toString());
                return m107(7, 70, (char) 903).intern();
            }
        }

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        private String m110() {
            String string;
            int i;
            try {
                String string2 = this.f111.get(m107(12, 0, (char) 0).intern()).toString();
                String string3 = this.f111.get(m107(15, 77, (char) 7243).intern()).toString();
                String strReplaceAll = m107(6, 92, (char) 0).intern().replaceAll(m107(5, 98, (char) 0).intern(), "");
                StringBuilder sb = new StringBuilder();
                sb.append(string2);
                sb.append(string3);
                sb.append(strReplaceAll);
                String strM177 = C0120r.m177(sb.toString());
                StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(strM177.substring(0, 16));
                string = sb2.toString();
            } catch (Exception e) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(m107(44, 103, (char) 65324).intern());
                sb3.append(e);
                AFLogger.afRDLog(sb3.toString());
                StringBuilder sb4 = new StringBuilder();
                sb4.append("");
                sb4.append(m107(18, 147, (char) 36288).intern());
                string = sb4.toString();
            }
            try {
                Intent intentRegisterReceiver = this.f112.registerReceiver(null, new IntentFilter(m107(37, 165, (char) 0).intern()));
                int intExtra = -2700;
                if (intentRegisterReceiver != null) {
                    int i2 = f107 + 9;
                    f109 = i2 % 128;
                    intExtra = i2 % 2 == 0 ? intentRegisterReceiver.getIntExtra(m107(11, 202, (char) 0).intern(), -2700) : intentRegisterReceiver.getIntExtra(m107(26, 19887, (char) 0).intern(), 22337);
                }
                if (this.f112.getApplicationInfo().nativeLibraryDir.contains(m107(3, 213, (char) 0).intern())) {
                    int i3 = f109 + 37;
                    f107 = i3 % 128;
                    int i4 = i3 % 2;
                    i = 1;
                } else {
                    i = 0;
                }
                int size = ((SensorManager) this.f112.getSystemService(m107(6, 216, (char) 0).intern())).getSensorList(-1).size();
                StringBuilder sb5 = new StringBuilder();
                sb5.append(m107(1, 222, (char) 0).intern());
                sb5.append(intExtra);
                sb5.append(m107(2, 223, (char) 0).intern());
                sb5.append(i);
                sb5.append(m107(2, 225, (char) 0).intern());
                sb5.append(size);
                sb5.append(m107(2, 227, (char) 15188).intern());
                sb5.append(this.f111.size());
                String string4 = sb5.toString();
                StringBuilder sb6 = new StringBuilder();
                sb6.append(string);
                sb6.append(C0031c.m111(string4));
                return sb6.toString();
            } catch (Exception e2) {
                StringBuilder sb7 = new StringBuilder();
                sb7.append(m107(44, 103, (char) 65324).intern());
                sb7.append(e2);
                AFLogger.afRDLog(sb7.toString());
                StringBuilder sb8 = new StringBuilder();
                sb8.append(string);
                sb8.append(m107(16, 229, (char) 20055).intern());
                return sb8.toString();
            }
        }

        /* JADX INFO: renamed from: com.appsflyer.c$a$c, reason: collision with other inner class name */
        static class C0031c {

            /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
            private long f113;

            /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
            private final Object f114;

            /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
            private String f115;

            C0031c() {
            }

            @NonNull
            /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
            static String m111(String str) throws Exception {
                byte[] bytes = str.getBytes();
                for (int i = 0; i < bytes.length; i++) {
                    bytes[i] = (byte) (bytes[i] ^ ((i % 2) + 42));
                }
                StringBuilder sb = new StringBuilder();
                for (byte b : bytes) {
                    String hexString = Integer.toHexString(b);
                    if (hexString.length() == 1) {
                        hexString = AppEventsConstants.EVENT_PARAM_VALUE_NO.concat(String.valueOf(hexString));
                    }
                    sb.append(hexString);
                }
                return sb.toString();
            }

            C0031c(long j, String str) {
                this.f114 = new Object();
                this.f113 = 0L;
                this.f115 = "";
                this.f113 = j;
                this.f115 = str;
            }

            C0031c(String str) {
                this(System.currentTimeMillis(), str);
            }

            @NonNull
            /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
            static C0031c m113(String str) {
                if (str == null) {
                    return new C0031c(0L, "");
                }
                String[] strArrSplit = str.split(",");
                if (strArrSplit.length >= 2) {
                    return new C0031c(Long.parseLong(strArrSplit[0]), strArrSplit[1]);
                }
                return new C0031c(0L, "");
            }

            /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
            final boolean m115(C0031c c0031c) {
                return m112(c0031c.f113, c0031c.f115);
            }

            /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
            private boolean m112(long j, String str) {
                synchronized (this.f114) {
                    if (str != null) {
                        if (!str.equals(this.f115)) {
                            if (j - this.f113 > 2000) {
                                this.f113 = j;
                                this.f115 = str;
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }

            public final String toString() {
                StringBuilder sb = new StringBuilder();
                sb.append(this.f113);
                sb.append(",");
                sb.append(this.f115);
                return sb.toString();
            }

            /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
            final String m114() {
                return this.f115;
            }
        }

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        private static String m107(int i, int i2, char c) {
            int i3 = f107 + 109;
            f109 = i3 % 128;
            int i4 = i3 % 2;
            char[] cArr = new char[i];
            int i5 = 0;
            while (true) {
                if (!(i5 >= i)) {
                    int i6 = f109 + 13;
                    f107 = i6 % 128;
                    if ((i6 % 2 == 0 ? '8' : '4') != '8') {
                        cArr[i5] = (char) ((((long) f108[i2 + i5]) ^ (((long) i5) * f110)) ^ ((long) c));
                        i5++;
                    } else {
                        cArr[i5] = (char) ((((long) f108[i2 + i5]) - (((long) i5) ^ f110)) / ((long) c));
                        i5 += 73;
                    }
                } else {
                    String str = new String(cArr);
                    int i7 = f107 + 69;
                    f109 = i7 % 128;
                    int i8 = i7 % 2;
                    return str;
                }
            }
        }
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static String m102(int i, int i2, char c) {
        char[] cArr = new char[i];
        int i3 = f106 + 91;
        f104 = i3 % 128;
        if (i3 % 2 != 0) {
        }
        int i4 = 0;
        while (true) {
            if ((i4 < i ? (char) 0 : (char) 7) == 7) {
                return new String(cArr);
            }
            int i5 = f104 + 73;
            f106 = i5 % 128;
            if ((i5 % 2 == 0 ? '\n' : '\b') != '\n') {
                cArr[i4] = (char) ((((long) f103[i2 + i4]) ^ (((long) i4) * f105)) ^ ((long) c));
                i4++;
            } else {
                cArr[i4] = (char) ((((long) f103[i2 - i4]) * (((long) i4) & f105)) ^ ((long) c));
                i4 += 119;
            }
        }
    }
}
