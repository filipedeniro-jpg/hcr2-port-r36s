package com.google.gson.internal.bind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* JADX INFO: loaded from: classes2.dex */
public class ISO8601Utils {
    private static final String UTC_ID = "UTC";
    private static final TimeZone TIMEZONE_UTC = TimeZone.getTimeZone(UTC_ID);

    public static String format(Date date) {
        return format(date, false, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z) {
        return format(date, z, TIMEZONE_UTC);
    }

    public static String format(Date date, boolean z, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(19 + (z ? 4 : 0) + (timeZone.getRawOffset() == 0 ? 1 : 6));
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(':');
        padInt(sb, gregorianCalendar.get(13), 2);
        if (z) {
            sb.append('.');
            padInt(sb, gregorianCalendar.get(14), 3);
        }
        int offset = timeZone.getOffset(gregorianCalendar.getTimeInMillis());
        if (offset != 0) {
            int i = offset / 60000;
            int iAbs = Math.abs(i / 60);
            int iAbs2 = Math.abs(i % 60);
            sb.append(offset >= 0 ? '+' : '-');
            padInt(sb, iAbs, 2);
            sb.append(':');
            padInt(sb, iAbs2, 2);
        } else {
            sb.append('Z');
        }
        return sb.toString();
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00cc A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, NumberFormatException -> 0x01bd, IndexOutOfBoundsException -> 0x01bf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:35:0x0094, B:37:0x009c, B:48:0x00c6, B:50:0x00cc, B:52:0x00d3, B:77:0x0181, B:57:0x00df, B:58:0x00f8, B:59:0x00f9, B:63:0x0115, B:65:0x0122, B:68:0x012b, B:70:0x014a, B:73:0x0159, B:74:0x017b, B:76:0x017e, B:62:0x0104, B:79:0x01b3, B:80:0x01ba, B:41:0x00b4, B:42:0x00b7), top: B:96:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:52:0x00d3 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, NumberFormatException -> 0x01bd, IndexOutOfBoundsException -> 0x01bf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:35:0x0094, B:37:0x009c, B:48:0x00c6, B:50:0x00cc, B:52:0x00d3, B:77:0x0181, B:57:0x00df, B:58:0x00f8, B:59:0x00f9, B:63:0x0115, B:65:0x0122, B:68:0x012b, B:70:0x014a, B:73:0x0159, B:74:0x017b, B:76:0x017e, B:62:0x0104, B:79:0x01b3, B:80:0x01ba, B:41:0x00b4, B:42:0x00b7), top: B:96:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:53:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:61:0x0103  */
    /* JADX WARN: Code duplicated, block: B:62:0x0104 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, NumberFormatException -> 0x01bd, IndexOutOfBoundsException -> 0x01bf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:35:0x0094, B:37:0x009c, B:48:0x00c6, B:50:0x00cc, B:52:0x00d3, B:77:0x0181, B:57:0x00df, B:58:0x00f8, B:59:0x00f9, B:63:0x0115, B:65:0x0122, B:68:0x012b, B:70:0x014a, B:73:0x0159, B:74:0x017b, B:76:0x017e, B:62:0x0104, B:79:0x01b3, B:80:0x01ba, B:41:0x00b4, B:42:0x00b7), top: B:96:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:76:0x017e A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, NumberFormatException -> 0x01bd, IndexOutOfBoundsException -> 0x01bf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:35:0x0094, B:37:0x009c, B:48:0x00c6, B:50:0x00cc, B:52:0x00d3, B:77:0x0181, B:57:0x00df, B:58:0x00f8, B:59:0x00f9, B:63:0x0115, B:65:0x0122, B:68:0x012b, B:70:0x014a, B:73:0x0159, B:74:0x017b, B:76:0x017e, B:62:0x0104, B:79:0x01b3, B:80:0x01ba, B:41:0x00b4, B:42:0x00b7), top: B:96:0x0006 }] */
    /* JADX WARN: Code duplicated, block: B:79:0x01b3 A[Catch: IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, NumberFormatException -> 0x01bd, IndexOutOfBoundsException -> 0x01bf, TryCatch #2 {IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException -> 0x01bb, blocks: (B:3:0x0006, B:5:0x0018, B:6:0x001a, B:8:0x0026, B:9:0x0028, B:11:0x0037, B:13:0x003d, B:17:0x0052, B:19:0x0062, B:20:0x0064, B:22:0x0070, B:23:0x0072, B:25:0x0078, B:29:0x0082, B:35:0x0094, B:37:0x009c, B:48:0x00c6, B:50:0x00cc, B:52:0x00d3, B:77:0x0181, B:57:0x00df, B:58:0x00f8, B:59:0x00f9, B:63:0x0115, B:65:0x0122, B:68:0x012b, B:70:0x014a, B:73:0x0159, B:74:0x017b, B:76:0x017e, B:62:0x0104, B:79:0x01b3, B:80:0x01ba, B:41:0x00b4, B:42:0x00b7), top: B:96:0x0006 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:62:0x0104, please report this as an issue */
    public static Date parse(String str, ParsePosition parsePosition) throws ParseException {
        String str2;
        int iIndexOfNonDigit;
        int i;
        int i2;
        int i3;
        int i4;
        char cCharAt;
        String strSubstring;
        int length;
        TimeZone timeZone;
        char cCharAt2;
        try {
            int index = parsePosition.getIndex();
            int i5 = index + 4;
            int i6 = parseInt(str, index, i5);
            if (checkOffset(str, i5, '-')) {
                i5++;
            }
            int i7 = i5 + 2;
            int i8 = parseInt(str, i5, i7);
            if (checkOffset(str, i7, '-')) {
                i7++;
            }
            int i9 = i7 + 2;
            int i10 = parseInt(str, i7, i9);
            boolean zCheckOffset = checkOffset(str, i9, 'T');
            if (!zCheckOffset && str.length() <= i9) {
                GregorianCalendar gregorianCalendar = new GregorianCalendar(i6, i8 - 1, i10);
                parsePosition.setIndex(i9);
                return gregorianCalendar.getTime();
            }
            if (zCheckOffset) {
                int i11 = i9 + 1;
                int i12 = i11 + 2;
                i2 = parseInt(str, i11, i12);
                if (checkOffset(str, i12, ':')) {
                    i12++;
                }
                iIndexOfNonDigit = i12 + 2;
                i3 = parseInt(str, i12, iIndexOfNonDigit);
                if (checkOffset(str, iIndexOfNonDigit, ':')) {
                    iIndexOfNonDigit++;
                }
                if (str.length() <= iIndexOfNonDigit || (cCharAt2 = str.charAt(iIndexOfNonDigit)) == 'Z' || cCharAt2 == '+' || cCharAt2 == '-') {
                    i = 0;
                } else {
                    int i13 = iIndexOfNonDigit + 2;
                    int i14 = parseInt(str, iIndexOfNonDigit, i13);
                    i4 = (i14 <= 59 || i14 >= 63) ? i14 : 59;
                    if (checkOffset(str, i13, '.')) {
                        int i15 = i13 + 1;
                        iIndexOfNonDigit = indexOfNonDigit(str, i15 + 1);
                        int iMin = Math.min(iIndexOfNonDigit, i15 + 3);
                        int i16 = parseInt(str, i15, iMin);
                        int i17 = iMin - i15;
                        if (i17 == 1) {
                            i16 *= 100;
                        } else if (i17 == 2) {
                            i16 *= 10;
                        }
                        i = i16;
                    } else {
                        iIndexOfNonDigit = i13;
                        i = 0;
                    }
                }
                if (str.length() > iIndexOfNonDigit) {
                    throw new IllegalArgumentException("No time zone indicator");
                }
                cCharAt = str.charAt(iIndexOfNonDigit);
                if (cCharAt == 'Z') {
                    timeZone = TIMEZONE_UTC;
                    length = iIndexOfNonDigit + 1;
                } else {
                    if (cCharAt != '+' && cCharAt != '-') {
                        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                    }
                    strSubstring = str.substring(iIndexOfNonDigit);
                    if (strSubstring.length() >= 5) {
                        strSubstring = strSubstring + "00";
                    }
                    length = iIndexOfNonDigit + strSubstring.length();
                    if (!"+0000".equals(strSubstring) || "+00:00".equals(strSubstring)) {
                        timeZone = TIMEZONE_UTC;
                    } else {
                        String str3 = "GMT" + strSubstring;
                        TimeZone timeZone2 = TimeZone.getTimeZone(str3);
                        String id = timeZone2.getID();
                        if (!id.equals(str3) && !id.replace(":", "").equals(str3)) {
                            throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + str3 + " given, resolves to " + timeZone2.getID());
                        }
                        timeZone = timeZone2;
                    }
                }
                GregorianCalendar gregorianCalendar2 = new GregorianCalendar(timeZone);
                gregorianCalendar2.setLenient(false);
                gregorianCalendar2.set(1, i6);
                gregorianCalendar2.set(2, i8 - 1);
                gregorianCalendar2.set(5, i10);
                gregorianCalendar2.set(11, i2);
                gregorianCalendar2.set(12, i3);
                gregorianCalendar2.set(13, i4);
                gregorianCalendar2.set(14, i);
                parsePosition.setIndex(length);
                return gregorianCalendar2.getTime();
            }
            iIndexOfNonDigit = i9;
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            if (str.length() > iIndexOfNonDigit) {
                throw new IllegalArgumentException("No time zone indicator");
            }
            cCharAt = str.charAt(iIndexOfNonDigit);
            if (cCharAt == 'Z') {
                timeZone = TIMEZONE_UTC;
                length = iIndexOfNonDigit + 1;
            } else {
                if (cCharAt != '+') {
                    throw new IndexOutOfBoundsException("Invalid time zone indicator '" + cCharAt + "'");
                }
                strSubstring = str.substring(iIndexOfNonDigit);
                if (strSubstring.length() >= 5) {
                    strSubstring = strSubstring + "00";
                }
                length = iIndexOfNonDigit + strSubstring.length();
                if (!"+0000".equals(strSubstring)) {
                    timeZone = TIMEZONE_UTC;
                } else {
                    timeZone = TIMEZONE_UTC;
                }
            }
            GregorianCalendar gregorianCalendar3 = new GregorianCalendar(timeZone);
            gregorianCalendar3.setLenient(false);
            gregorianCalendar3.set(1, i6);
            gregorianCalendar3.set(2, i8 - 1);
            gregorianCalendar3.set(5, i10);
            gregorianCalendar3.set(11, i2);
            gregorianCalendar3.set(12, i3);
            gregorianCalendar3.set(13, i4);
            gregorianCalendar3.set(14, i);
            parsePosition.setIndex(length);
            return gregorianCalendar3.getTime();
        } catch (IllegalArgumentException | IndexOutOfBoundsException | NumberFormatException e) {
            if (str == null) {
                str2 = null;
            } else {
                str2 = '\"' + str + "'";
            }
            String message = e.getMessage();
            if (message == null || message.isEmpty()) {
                message = "(" + e.getClass().getName() + ")";
            }
            ParseException parseException = new ParseException("Failed to parse date [" + str2 + "]: " + message, parsePosition.getIndex());
            parseException.initCause(e);
            throw parseException;
        }
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i3 = i + 1;
            int iDigit = Character.digit(str.charAt(i), 10);
            if (iDigit < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = -iDigit;
        } else {
            i3 = i;
            i4 = 0;
        }
        while (i3 < i2) {
            int i5 = i3 + 1;
            int iDigit2 = Character.digit(str.charAt(i3), 10);
            if (iDigit2 < 0) {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
            i4 = (i4 * 10) - iDigit2;
            i3 = i5;
        }
        return -i4;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String string = Integer.toString(i);
        for (int length = i2 - string.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(string);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '0' || cCharAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
