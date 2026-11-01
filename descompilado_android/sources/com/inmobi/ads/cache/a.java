package com.inmobi.ads.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/* JADX INFO: compiled from: AdAsset.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final String m = "a";
    int b;
    int c;
    public String d;
    public String e;
    long f;
    long g;
    public long h;
    long i;
    public int l;
    public long a = 0;
    public boolean j = false;
    public String k = null;

    /* JADX INFO: renamed from: com.inmobi.ads.cache.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AdAsset.java */
    public static final class C0074a {
        int b;
        String c;
        String d;
        long g;
        long h;
        int a = new Random().nextInt() & Integer.MAX_VALUE;
        long e = System.currentTimeMillis();
        long f = System.currentTimeMillis();

        public final C0074a a(String str, int i, long j) {
            this.c = str;
            this.b = i;
            this.g = System.currentTimeMillis() + j;
            return this;
        }

        private static long a(String str) {
            try {
                return new SimpleDateFormat("EEE,dd MMM yyyy HH:mm:ss z", Locale.ENGLISH).parse(str).getTime();
            } catch (ParseException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return 0L;
            }
        }

        @NonNull
        public final a a() {
            return new a(this.a, this.c, this.d, this.b, this.e, this.f, this.g, this.h);
        }

        public final C0074a a(String str, String str2, com.inmobi.commons.core.network.d dVar, int i, long j) {
            long j2;
            long j3;
            boolean z;
            boolean z2;
            long j4;
            long j5;
            String str3;
            String str4;
            String str5;
            String str6;
            Map<String, List<String>> map = dVar.d;
            long jCurrentTimeMillis = System.currentTimeMillis();
            List<String> list = map.get(HttpRequest.HEADER_DATE);
            long j6 = 0;
            long jA = (list == null || list.size() <= 0 || (str6 = map.get(HttpRequest.HEADER_DATE).get(0)) == null) ? 0L : a(str6);
            List<String> list2 = map.get(HttpRequest.HEADER_CACHE_CONTROL);
            if (list2 == null || list2.size() <= 0 || (str5 = map.get(HttpRequest.HEADER_CACHE_CONTROL).get(0)) == null) {
                j2 = 0;
                j3 = 0;
                z = false;
                z2 = false;
            } else {
                j2 = 0;
                j3 = 0;
                z2 = false;
                for (String str7 : str5.split(",")) {
                    String strTrim = str7.trim();
                    if (!strTrim.equals("no-cache") && !strTrim.equals("no-store")) {
                        if (strTrim.startsWith("max-age=")) {
                            try {
                                j2 = Long.parseLong(strTrim.substring(8));
                            } catch (Exception e) {
                                String unused = a.m;
                                e.getMessage();
                            }
                        } else if (strTrim.startsWith("stale-while-revalidate=")) {
                            try {
                                j3 = Long.parseLong(strTrim.substring(23));
                            } catch (Exception e2) {
                                String unused2 = a.m;
                                e2.getMessage();
                            }
                        } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                            z2 = true;
                        }
                    }
                }
                z = true;
            }
            List<String> list3 = map.get(HttpRequest.HEADER_EXPIRES);
            long jA2 = (list3 == null || list3.size() <= 0 || (str4 = map.get(HttpRequest.HEADER_EXPIRES).get(0)) == null) ? 0L : a(str4);
            List<String> list4 = map.get(HttpRequest.HEADER_LAST_MODIFIED);
            if (list4 != null && list4.size() > 0 && (str3 = map.get(HttpRequest.HEADER_LAST_MODIFIED).get(0)) != null) {
                a(str3);
            }
            List<String> list5 = map.get(HttpRequest.HEADER_ETAG);
            if (list5 != null && list5.size() > 0) {
                map.get(HttpRequest.HEADER_ETAG).get(0);
            }
            if (z) {
                j6 = jCurrentTimeMillis + (j2 * 1000);
                if (z2) {
                    j5 = j6;
                } else {
                    Long.signum(j3);
                    j5 = j6 + (j3 * 1000);
                }
                j4 = j5;
            } else {
                if (jA > 0 && jA2 >= jA) {
                    j6 = jCurrentTimeMillis + (jA2 - jA);
                }
                j4 = j6;
            }
            this.c = str;
            this.d = str2;
            this.b = i;
            this.g = jCurrentTimeMillis + (j * 1000);
            this.h = j6;
            this.g = Math.min(this.g, j4);
            return this;
        }
    }

    a(int i, @NonNull String str, @Nullable String str2, int i2, long j, long j2, long j3, long j4) {
        this.b = i;
        this.d = str;
        this.e = str2;
        this.c = i2;
        this.f = j;
        this.g = j2;
        this.h = j3;
        this.i = j4;
    }

    public final boolean a() {
        String str = this.e;
        return (str == null || str.length() == 0 || !new File(this.e).exists()) ? false : true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.d.equals(((a) obj).d);
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    public String toString() {
        return "AdAsset{url='" + this.d + "'}";
    }
}
