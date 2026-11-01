package com.applovin.impl.a;

import android.net.Uri;
import android.webkit.URLUtil;
import com.applovin.impl.sdk.utils.n;
import com.applovin.impl.sdk.utils.o;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.my.target.bj;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class i {
    private static DateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.US);
    private static Random b = new Random(System.currentTimeMillis());

    public static Uri a(String str, long j, Uri uri, d dVar, com.applovin.impl.sdk.h hVar) {
        if (!URLUtil.isValidUrl(str)) {
            hVar.w().d("VastUtils", "Unable to replace macros in invalid URL string.");
            return null;
        }
        try {
            String strReplace = str.replace("[ERRORCODE]", Integer.toString(dVar.a()));
            if (j >= 0) {
                strReplace = strReplace.replace("[CONTENTPLAYHEAD]", a(j));
            }
            if (uri != null) {
                strReplace = strReplace.replace("[ASSETURI]", uri.toString());
            }
            return Uri.parse(strReplace.replace("[CACHEBUSTING]", a()).replace("[TIMESTAMP]", b()));
        } catch (Throwable th) {
            hVar.w().b("VastUtils", "Unable to replace macros in URL string " + str, th);
            return null;
        }
    }

    public static d a(a aVar) {
        if (b(aVar) || c(aVar)) {
            return null;
        }
        return d.GENERAL_WRAPPER_ERROR;
    }

    private static String a() {
        return Integer.toString(b.nextInt(89999999) + 10000000);
    }

    private static String a(long j) {
        if (j <= 0) {
            return "00:00:00.000";
        }
        return String.format(Locale.US, "%02d:%02d:%02d.000", Long.valueOf(TimeUnit.SECONDS.toHours(j)), Long.valueOf(TimeUnit.SECONDS.toMinutes(j) % TimeUnit.MINUTES.toSeconds(1L)), Long.valueOf(j % TimeUnit.MINUTES.toSeconds(1L)));
    }

    public static String a(c cVar) {
        o oVarC;
        if (cVar == null) {
            throw new IllegalArgumentException("Unable to get resolution uri string for fetching the next wrapper or inline response in the chain");
        }
        List<o> listB = cVar.b();
        int size = cVar.b().size();
        if (size <= 0 || (oVarC = listB.get(size - 1).c(bj.fT)) == null) {
            return null;
        }
        return oVarC.c();
    }

    public static String a(o oVar, String str, String str2) {
        o oVarB = oVar.b(str);
        if (oVarB != null) {
            String strC = oVarB.c();
            if (com.applovin.impl.sdk.utils.k.b(strC)) {
                return strC;
            }
        }
        return str2;
    }

    private static Set<g> a(c cVar, com.applovin.impl.sdk.h hVar) {
        if (cVar == null) {
            return null;
        }
        List<o> listB = cVar.b();
        Set<g> hashSet = new HashSet<>(listB.size());
        for (o oVar : listB) {
            o oVarC = oVar.c(bj.fW);
            if (oVarC == null) {
                oVarC = oVar.c(bj.fV);
            }
            hashSet = a(hashSet, oVarC != null ? oVarC.a("Error") : oVar.a("Error"), cVar, hVar);
        }
        hVar.w().a("VastUtils", "Retrieved " + hashSet.size() + " top level error trackers: " + hashSet);
        return hashSet;
    }

    private static Set<g> a(Set<g> set, List<o> list, c cVar, com.applovin.impl.sdk.h hVar) {
        if (list != null) {
            Iterator<o> it = list.iterator();
            while (it.hasNext()) {
                g gVarA = g.a(it.next(), cVar, hVar);
                if (gVarA != null) {
                    set.add(gVarA);
                }
            }
        }
        return set;
    }

    public static void a(c cVar, AppLovinAdLoadListener appLovinAdLoadListener, d dVar, int i, com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("Unable to handle failure. No sdk specified.");
        }
        n.a(appLovinAdLoadListener, cVar.g(), i, hVar);
        a(a(cVar, hVar), dVar, hVar);
    }

    public static void a(o oVar, Map<String, Set<g>> map, c cVar, com.applovin.impl.sdk.h hVar) {
        List<o> listA;
        com.applovin.impl.sdk.n nVarW;
        String str;
        if (hVar == null) {
            throw new IllegalArgumentException("Unable to render event trackers. No sdk specified.");
        }
        if (oVar == null) {
            nVarW = hVar.w();
            str = "Unable to render event trackers; null node provided";
        } else {
            if (map != null) {
                o oVarB = oVar.b(bj.gc);
                if (oVarB == null || (listA = oVarB.a(bj.gd)) == null) {
                    return;
                }
                for (o oVar2 : listA) {
                    String str2 = oVar2.b().get("event");
                    if (com.applovin.impl.sdk.utils.k.b(str2)) {
                        g gVarA = g.a(oVar2, cVar, hVar);
                        if (gVarA != null) {
                            Set<g> set = map.get(str2);
                            if (set != null) {
                                set.add(gVarA);
                            } else {
                                HashSet hashSet = new HashSet();
                                hashSet.add(gVarA);
                                map.put(str2, hashSet);
                            }
                        }
                    } else {
                        hVar.w().d("VastUtils", "Could not find event for tracking node = " + oVar2);
                    }
                }
                return;
            }
            nVarW = hVar.w();
            str = "Unable to render event trackers; null event trackers provided";
        }
        nVarW.d("VastUtils", str);
    }

    public static void a(List<o> list, Set<g> set, c cVar, com.applovin.impl.sdk.h hVar) {
        com.applovin.impl.sdk.n nVarW;
        String str;
        if (hVar == null) {
            throw new IllegalArgumentException("Unable to render trackers. No sdk specified.");
        }
        if (list == null) {
            nVarW = hVar.w();
            str = "Unable to render trackers; null nodes provided";
        } else {
            if (set != null) {
                Iterator<o> it = list.iterator();
                while (it.hasNext()) {
                    g gVarA = g.a(it.next(), cVar, hVar);
                    if (gVarA != null) {
                        set.add(gVarA);
                    }
                }
                return;
            }
            nVarW = hVar.w();
            str = "Unable to render trackers; null trackers provided";
        }
        nVarW.d("VastUtils", str);
    }

    public static void a(Set<g> set, long j, Uri uri, d dVar, com.applovin.impl.sdk.h hVar) {
        if (hVar == null) {
            throw new IllegalArgumentException("Unable to fire trackers. No sdk specified.");
        }
        if (set == null || set.isEmpty()) {
            return;
        }
        Iterator<g> it = set.iterator();
        while (it.hasNext()) {
            Uri uriA = a(it.next().b(), j, uri, dVar, hVar);
            if (uriA != null) {
                hVar.I().a(com.applovin.impl.sdk.network.e.j().a(uriA.toString()).a(false).a(), false);
            }
        }
    }

    public static void a(Set<g> set, d dVar, com.applovin.impl.sdk.h hVar) {
        a(set, -1L, (Uri) null, dVar, hVar);
    }

    public static void a(Set<g> set, com.applovin.impl.sdk.h hVar) {
        a(set, -1L, (Uri) null, d.UNSPECIFIED, hVar);
    }

    public static boolean a(o oVar) {
        if (oVar != null) {
            return oVar.c(bj.fW) != null;
        }
        throw new IllegalArgumentException("Unable to check if a given XmlNode contains a wrapper response");
    }

    private static String b() {
        a.setTimeZone(TimeZone.getDefault());
        return a.format(new Date());
    }

    public static boolean b(a aVar) {
        j jVarA;
        List<k> listA;
        return (aVar == null || (jVarA = aVar.a()) == null || (listA = jVarA.a()) == null || listA.isEmpty()) ? false : true;
    }

    public static boolean b(o oVar) {
        if (oVar != null) {
            return oVar.c(bj.fV) != null;
        }
        throw new IllegalArgumentException("Unable to check if a given XmlNode contains an inline response");
    }

    public static boolean c(a aVar) {
        b bVarD;
        e eVarB;
        if (aVar == null || (bVarD = aVar.d()) == null || (eVarB = bVarD.b()) == null) {
            return false;
        }
        return eVarB.b() != null || com.applovin.impl.sdk.utils.k.b(eVarB.c());
    }
}
