package com.inmobi.commons.core.utilities.b;

import android.location.Location;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: compiled from: PublisherProvidedUserInfoDao.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    private static int a = Integer.MIN_VALUE;
    private static String b = null;
    private static String c = null;
    private static String d = null;
    private static String e = null;
    private static String f = null;
    private static String g = null;
    private static int h = Integer.MIN_VALUE;
    private static String i;
    private static String j;
    private static String k;
    private static String l;
    private static Location m;

    public static String a() {
        return com.inmobi.commons.core.d.c.a("user_info_store");
    }

    public static void b() {
        a(a);
        a(b);
        b(c);
        c(d);
        d(e);
        e(f);
        f(g);
        b(h);
        g(i);
        h(j);
        i(k);
        j(l);
        a(m);
    }

    public static void a(int i2) {
        if (com.inmobi.commons.a.a.a() && i2 != Integer.MIN_VALUE) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_age", i2);
        } else {
            a = i2;
        }
    }

    public static void a(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_age_group", str);
        } else {
            b = str;
        }
    }

    public static void b(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_area_code", str);
        } else {
            c = str;
        }
    }

    public static void c(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_post_code", str);
        } else {
            d = str;
        }
    }

    public static void d(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_city_code", str);
        } else {
            e = str;
        }
    }

    public static void e(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_state_code", str);
        } else {
            f = str;
        }
    }

    public static void f(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_country_code", str);
        } else {
            g = str;
        }
    }

    public static void b(int i2) {
        if (com.inmobi.commons.a.a.a() && i2 != Integer.MIN_VALUE) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_yob", i2);
        } else {
            h = i2;
        }
    }

    public static void g(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_gender", str);
        } else {
            i = str;
        }
    }

    public static void h(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_education", str);
        } else {
            j = str;
        }
    }

    public static void i(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_language", str);
        } else {
            k = str;
        }
    }

    public static void j(String str) {
        if (com.inmobi.commons.a.a.a() && str != null) {
            com.inmobi.commons.core.d.c.b("user_info_store").a("user_interest", str);
        } else {
            l = str;
        }
    }

    public static Location c() {
        Location location = m;
        if (location != null) {
            return location;
        }
        String strC = com.inmobi.commons.core.d.c.b("user_info_store").c("user_location");
        if (strC == null) {
            return null;
        }
        Location location2 = new Location("");
        try {
            String[] strArrSplit = strC.split(",");
            location2.setLatitude(Double.parseDouble(strArrSplit[0]));
            location2.setLongitude(Double.parseDouble(strArrSplit[1]));
            location2.setAccuracy(Float.parseFloat(strArrSplit[2]));
            location2.setTime(Long.parseLong(strArrSplit[3]));
            return location2;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    public static void a(Location location) {
        if (!com.inmobi.commons.a.a.a() || location == null) {
            m = location;
            return;
        }
        com.inmobi.commons.core.d.c.b("user_info_store").a("user_location", location.getLatitude() + "," + location.getLongitude() + "," + ((int) location.getAccuracy()) + "," + location.getTime());
    }

    public static HashMap<String, String> d() {
        HashMap<String, String> map = new HashMap<>();
        int iD = a;
        if (iD == Integer.MIN_VALUE) {
            iD = com.inmobi.commons.core.d.c.b("user_info_store").d("user_age");
        }
        if (iD != Integer.MIN_VALUE && iD > 0) {
            map.put("u-age", String.valueOf(iD));
        }
        int iD2 = h;
        if (iD2 == Integer.MIN_VALUE) {
            iD2 = com.inmobi.commons.core.d.c.b("user_info_store").d("user_yob");
        }
        if (iD2 != Integer.MIN_VALUE && iD2 > 0) {
            map.put("u-yearofbirth", String.valueOf(iD2));
        }
        String strC = e;
        if (strC == null) {
            strC = com.inmobi.commons.core.d.c.b("user_info_store").c("user_city_code");
        }
        String strC2 = f;
        if (strC2 == null) {
            strC2 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_state_code");
        }
        String strC3 = g;
        if (strC3 == null) {
            strC3 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_country_code");
        }
        String strTrim = (strC == null || strC.trim().length() == 0) ? "" : strC.trim();
        if (strC2 != null && strC2.trim().length() != 0) {
            strTrim = strTrim + "-" + strC2.trim();
        }
        if (strC3 != null && strC3.trim().length() != 0) {
            strTrim = strTrim + "-" + strC3.trim();
        }
        if (strTrim != null && strTrim.trim().length() != 0) {
            map.put("u-location", strTrim);
        }
        String strC4 = b;
        if (strC4 == null) {
            strC4 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_age_group");
        }
        if (strC4 != null) {
            map.put("u-agegroup", strC4.toLowerCase(Locale.ENGLISH));
        }
        String strC5 = c;
        if (strC5 == null) {
            strC5 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_area_code");
        }
        if (strC5 != null) {
            map.put("u-areacode", strC5);
        }
        String strC6 = d;
        if (strC6 == null) {
            strC6 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_post_code");
        }
        if (strC6 != null) {
            map.put("u-postalcode", strC6);
        }
        String strC7 = i;
        if (strC7 == null) {
            strC7 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_gender");
        }
        if (strC7 != null) {
            map.put("u-gender", strC7);
        }
        String strC8 = j;
        if (strC8 == null) {
            strC8 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_education");
        }
        if (strC8 != null) {
            map.put("u-education", strC8);
        }
        String strC9 = k;
        if (strC9 == null) {
            strC9 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_language");
        }
        if (strC9 != null) {
            map.put("u-language", strC9);
        }
        String strC10 = l;
        if (strC10 == null) {
            strC10 = com.inmobi.commons.core.d.c.b("user_info_store").c("user_interest");
        }
        if (strC10 != null) {
            map.put("u-interests", strC10);
        }
        return map;
    }
}
