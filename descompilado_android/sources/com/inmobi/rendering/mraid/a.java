package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: compiled from: CalendarUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final SimpleDateFormat[] a = {new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US), new SimpleDateFormat("yyyy-MM", Locale.US), new SimpleDateFormat("yyyyMMddHHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMddHHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMM", Locale.US), new SimpleDateFormat("yyyy", Locale.US)};
    private static String b = a.class.getSimpleName();

    @TargetApi(14)
    public static int a(Context context) {
        int i = 0;
        Cursor cursorQuery = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, new String[]{"_id", "title"}, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToLast()) {
            i = cursorQuery.getString(cursorQuery.getColumnIndex("title")) != null ? Integer.parseInt(cursorQuery.getString(cursorQuery.getColumnIndex("_id"))) : 0;
            cursorQuery.close();
        }
        return i;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String a(String str) {
        Date date;
        String str2 = null;
        if (str != null && !"".equals(str)) {
            SimpleDateFormat[] simpleDateFormatArr = a;
            int length = simpleDateFormatArr.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    date = null;
                    break;
                }
                try {
                    date = simpleDateFormatArr[i].parse(str);
                    break;
                } catch (ParseException unused) {
                    i++;
                }
            }
            if (date != null) {
                DateFormat[] dateFormatArr = {new SimpleDateFormat("yyyyMMdd'T'HHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US)};
                for (int i2 = 0; i2 < 3; i2++) {
                    try {
                        str2 = dateFormatArr[i2].format(Long.valueOf(date.getTime()));
                        break;
                    } catch (IllegalArgumentException unused2) {
                    }
                }
            }
        }
        return str2;
    }

    public static GregorianCalendar b(String str) {
        for (SimpleDateFormat simpleDateFormat : a) {
            try {
                Date date = simpleDateFormat.parse(str);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(date);
                new StringBuilder("Date format: ").append(simpleDateFormat.toPattern());
                return gregorianCalendar;
            } catch (ParseException unused) {
                new StringBuilder("Skipping format: ").append(simpleDateFormat.toPattern());
            }
        }
        return null;
    }

    public static String a(JSONArray jSONArray, int i, int i2) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            try {
                int i4 = jSONArray.getInt(i3);
                if (i4 >= i && i4 <= i2 && i4 != 0) {
                    sb.append(i4);
                    sb.append(",");
                }
            } catch (JSONException e) {
                new StringBuilder("Could not parse day ").append(e.getMessage());
                return null;
            }
        }
        String string = sb.toString();
        int length = string.length();
        if (length == 0) {
            return null;
        }
        int i5 = length - 1;
        return string.charAt(i5) == ',' ? string.substring(0, i5) : string;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                sb.append(jSONArray.get(i));
                sb.append(",");
            } catch (JSONException e) {
                new StringBuilder("Could not parse day object ").append(e.toString());
                return null;
            }
        }
        String string = sb.toString();
        int length = string.length();
        if (length == 0) {
            return null;
        }
        int i2 = length - 1;
        return string.charAt(i2) == ',' ? string.substring(0, i2) : string;
    }
}
