package com.applovin.impl.a;

import com.applovin.impl.sdk.utils.o;
import com.my.target.bj;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class g {
    private String a;
    private String b;
    private String c;
    private long d = -1;
    private int e = -1;

    private g() {
    }

    private static int a(String str, c cVar) {
        if ("start".equalsIgnoreCase(str)) {
            return 0;
        }
        if (bj.gq.equalsIgnoreCase(str)) {
            return 25;
        }
        if (bj.gr.equalsIgnoreCase(str)) {
            return 50;
        }
        if (bj.gs.equalsIgnoreCase(str)) {
            return 75;
        }
        if (!bj.gt.equalsIgnoreCase(str)) {
            return -1;
        }
        if (cVar != null) {
            return cVar.i();
        }
        return 95;
    }

    public static g a(o oVar, c cVar, com.applovin.impl.sdk.h hVar) {
        TimeUnit timeUnit;
        long seconds;
        if (oVar == null) {
            throw new IllegalArgumentException("No node specified.");
        }
        if (hVar == null) {
            throw new IllegalArgumentException("No sdk specified.");
        }
        try {
            String strC = oVar.c();
            if (!com.applovin.impl.sdk.utils.k.b(strC)) {
                hVar.w().d("VastTracker", "Unable to create tracker. Could not find URL.");
                return null;
            }
            g gVar = new g();
            gVar.c = strC;
            gVar.a = oVar.b().get("id");
            gVar.b = oVar.b().get("event");
            gVar.e = a(gVar.a(), cVar);
            String str = oVar.b().get(bj.gD);
            if (com.applovin.impl.sdk.utils.k.b(str)) {
                String strTrim = str.trim();
                if (strTrim.contains("%")) {
                    gVar.e = com.applovin.impl.sdk.utils.k.a(strTrim.substring(0, strTrim.length() - 1));
                } else if (strTrim.contains(":")) {
                    List<String> listA = com.applovin.impl.sdk.utils.d.a(strTrim, ":");
                    int size = listA.size();
                    if (size > 0) {
                        long j = 0;
                        int i = size - 1;
                        for (int i2 = i; i2 >= 0; i2--) {
                            String str2 = listA.get(i2);
                            if (com.applovin.impl.sdk.utils.k.d(str2)) {
                                int i3 = Integer.parseInt(str2);
                                if (i2 == i) {
                                    seconds = i3;
                                } else {
                                    if (i2 == size - 2) {
                                        timeUnit = TimeUnit.MINUTES;
                                    } else if (i2 == size - 3) {
                                        timeUnit = TimeUnit.HOURS;
                                    }
                                    seconds = timeUnit.toSeconds(i3);
                                }
                                j += seconds;
                            }
                        }
                        gVar.d = j;
                        gVar.e = -1;
                    }
                } else {
                    hVar.w().d("VastTracker", "Unable to parse time offset from rawOffsetString = " + strTrim);
                }
            }
            return gVar;
        } catch (Throwable th) {
            hVar.w().b("VastTracker", "Error occurred while initializing", th);
            return null;
        }
    }

    public String a() {
        return this.b;
    }

    public boolean a(long j, int i) {
        boolean z = this.d >= 0;
        boolean z2 = j >= this.d;
        boolean z3 = this.e >= 0;
        boolean z4 = i >= this.e;
        if (z && z2) {
            return true;
        }
        return z3 && z4;
    }

    public String b() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof g)) {
            return false;
        }
        g gVar = (g) obj;
        if (this.d != gVar.d || this.e != gVar.e) {
            return false;
        }
        String str = this.a;
        if (str == null ? gVar.a != null : !str.equals(gVar.a)) {
            return false;
        }
        String str2 = this.b;
        if (str2 == null ? gVar.b == null : str2.equals(gVar.b)) {
            return this.c.equals(gVar.c);
        }
        return false;
    }

    public int hashCode() {
        String str = this.a;
        int iHashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        int iHashCode2 = (((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.c.hashCode()) * 31;
        long j = this.d;
        return ((iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31) + this.e;
    }

    public String toString() {
        return "VastTracker{identifier='" + this.a + "', event='" + this.b + "', uriString='" + this.c + "', offsetSeconds=" + this.d + ", offsetPercent=" + this.e + '}';
    }
}
