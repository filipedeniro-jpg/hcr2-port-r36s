package com.inmobi.ads;

import android.content.ContentValues;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.facebook.FacebookMediationAdapter;
import com.ironsource.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import net.nend.android.NendAdNativeMediaView;

/* JADX INFO: compiled from: AdDao.java */
/* JADX INFO: loaded from: classes2.dex */
public class d {
    private static final String a = "d";
    private static d b;
    private static final Object c = new Object();
    private static final String[] d = {"id", "ad_content", "video_url", "video_track_duration", NendAdNativeMediaView.RESULT_CLICK_URL, "video_trackers", "companion_ads", "web_vast", "preload_webView", "asset_urls", AppEventsConstants.EVENT_PARAM_AD_TYPE, "ad_size", FacebookMediationAdapter.RTB_PLACEMENT_PARAMETER, "tp_key", "insertion_ts", "expiry_duration", "imp_id", "m10_context", "client_request_id", "bid", "bidInfo", "marked"};

    public static d a() {
        d dVar = b;
        if (dVar == null) {
            synchronized (c) {
                dVar = b;
                if (dVar == null) {
                    dVar = new d();
                    b = dVar;
                }
            }
        }
        return dVar;
    }

    private static String[] a(long j, InMobiAdRequest.MonetizationContext monetizationContext, String str) {
        return new String[]{String.valueOf(j), monetizationContext.a, str, AppEventsConstants.EVENT_PARAM_VALUE_NO};
    }

    private static String[] e(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        return new String[]{String.valueOf(j), str, monetizationContext.a, str2, AppEventsConstants.EVENT_PARAM_VALUE_NO};
    }

    private d() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("ad", "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, placement_id INTEGER NOT NULL, ad_content TEXT NOT NULL, ad_type TEXT NOT NULL, ad_size TEXT, asset_urls TEXT, video_url TEXT, video_track_duration TEXT, click_url TEXT, video_trackers TEXT, companion_ads TEXT, web_vast TEXT, preload_webView INTEGER DEFAULT 0, insertion_ts INTEGER NOT NULL, imp_id TEXT NOT NULL UNIQUE, m10_context TEXT NOT NULL, tp_key TEXT, expiry_duration INTEGER NOT NULL, client_request_id TEXT NOT NULL,bid INTEGER NOT NULL,bidInfo TEXT,marked INTEGER DEFAULT 0)");
        d();
        bVarA.b();
    }

    static List<a> a(String str, long j) {
        long jC;
        long jCurrentTimeMillis;
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = 0;
        List<ContentValues> listA = bVarA.a("ad", d, "ad_type=?", new String[]{str}, null, null, null, null);
        if (listA.size() == 0) {
            bVarA.b();
            return arrayList;
        }
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            a aVarA = a.C0070a.a(it.next());
            if (aVarA.c() == -1) {
                jC = aVarA.e + TimeUnit.SECONDS.toMillis(j);
                jCurrentTimeMillis = System.currentTimeMillis();
            } else {
                jC = aVarA.c();
                jCurrentTimeMillis = System.currentTimeMillis();
            }
            if (jC - jCurrentTimeMillis < 0) {
                iA += a(aVarA.g);
                arrayList.add(aVarA);
            }
        }
        StringBuilder sb = new StringBuilder("Deleted ");
        sb.append(iA);
        sb.append(" expired ads from cache of type: ");
        sb.append(str);
        bVarA.b();
        return arrayList;
    }

    static List<a> b() {
        ArrayList arrayList = new ArrayList();
        Iterator<ContentValues> it = com.inmobi.commons.core.d.b.a().a("ad", d, null, null, null, null, null, null).iterator();
        while (it.hasNext()) {
            arrayList.add(a.C0070a.a(it.next()));
        }
        return arrayList;
    }

    private static void d() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        for (ContentValues contentValues : bVarA.a("ad", d, "marked=?", new String[]{"1"}, null, null, null, null)) {
            contentValues.put("marked", AppEventsConstants.EVENT_PARAM_VALUE_NO);
            bVarA.a("ad", contentValues, "imp_id=?", new String[]{contentValues.getAsString("imp_id")});
        }
        bVarA.b();
    }

    static int a(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        int iB;
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (str != null && str.trim().length() != 0) {
            iB = bVarA.b("ad", "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2));
        } else {
            iB = bVarA.b("ad", "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2));
        }
        bVarA.b();
        return iB;
    }

    final synchronized a b(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        a aVarF;
        aVarF = f(j, str, monetizationContext, str2);
        if (aVarF != null) {
            com.inmobi.commons.core.d.b.a().a("ad", "id=?", new String[]{String.valueOf(aVarF.a)});
        }
        return aVarF;
    }

    private synchronized a f(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        List<ContentValues> listA;
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (str != null && str.trim().length() != 0) {
            listA = bVarA.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, "insertion_ts", "1");
        } else {
            listA = bVarA.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, "insertion_ts", "1");
        }
        if (listA.size() == 0) {
            return null;
        }
        return a.C0070a.a(listA.get(0));
    }

    @NonNull
    public final synchronized List<a> c(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, false);
    }

    final synchronized List<a> a(String str, String str2) {
        ArrayList arrayList;
        List<ContentValues> listA;
        arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (str2 != null && str2.trim().length() != 0) {
            listA = bVarA.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        } else {
            listA = bVarA.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        }
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(a.C0070a.a(it.next()));
        }
        return arrayList;
    }

    public final synchronized List<a> d(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2) {
        return a(j, str, monetizationContext, str2, true);
    }

    private static List<a> a(long j, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2, boolean z) {
        List<ContentValues> listA;
        ArrayList arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (str != null && str.trim().length() != 0) {
            listA = bVarA.a("ad", d, "placement_id=? AND ad_size=? AND m10_context=? AND tp_key=? AND marked=?", e(j, str, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        } else {
            listA = bVarA.a("ad", d, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, z ? "bid" : "insertion_ts", null);
        }
        Iterator<ContentValues> it = listA.iterator();
        while (it.hasNext()) {
            arrayList.add(a.C0070a.a(it.next()));
        }
        return arrayList;
    }

    final synchronized List<a> b(String str, String str2) {
        ArrayList arrayList;
        List<ContentValues> listA;
        arrayList = new ArrayList();
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        if (str2 != null && str2.trim().length() != 0) {
            listA = bVarA.a("ad", d, "video_url=? AND ad_size=?", new String[]{str, str2}, null, null, "insertion_ts", null);
        } else {
            listA = bVarA.a("ad", d, "video_url=?", new String[]{str}, null, null, "insertion_ts", null);
        }
        for (ContentValues contentValues : listA) {
            bVarA.a("ad", "id=?", new String[]{String.valueOf(contentValues.getAsInteger("id").intValue())});
            arrayList.add(a.C0070a.a(contentValues));
        }
        return arrayList;
    }

    public static int a(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        int iA = bVarA.a("ad", "imp_id = ?", new String[]{String.valueOf(str)});
        bVarA.b();
        return iA;
    }

    public static void b(String str) {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        a aVarC = c(str);
        if (aVarC != null) {
            ContentValues contentValuesA = aVarC.a();
            contentValuesA.put("marked", "1");
            bVarA.b("ad", contentValuesA, "imp_id=?", new String[]{str});
        }
    }

    @Nullable
    public static a c(String str) {
        List<ContentValues> listA = com.inmobi.commons.core.d.b.a().a("ad", d, "imp_id=?", new String[]{str}, null, null, null, "1");
        if (listA.size() == 0) {
            return null;
        }
        return a.C0070a.a(listA.get(0));
    }

    public final synchronized void a(List<a> list, long j, int i, String str, InMobiAdRequest.MonetizationContext monetizationContext, String str2, @Nullable String str3) {
        boolean zIsEmpty = TextUtils.isEmpty(str3);
        if ((zIsEmpty && i == 0) || list.size() == 0) {
            return;
        }
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        for (a aVar : list) {
            aVar.e = System.currentTimeMillis();
            ContentValues contentValuesA = aVar.a();
            contentValuesA.put("tp_key", str2);
            bVarA.a("ad", contentValuesA);
        }
        if (!zIsEmpty) {
            b(str3);
        }
        int iA = a(j, null, monetizationContext, str2) - i;
        if (iA > 0) {
            HashMap map = new HashMap();
            map.put("type", str);
            map.put(com.my.target.i.aa, Integer.valueOf(iA));
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "DbSpaceOverflow", map);
            List<ContentValues> listA = bVarA.a("ad", new String[]{"id"}, "placement_id=? AND m10_context=? AND tp_key=? AND marked=?", a(j, monetizationContext, str2), null, null, "insertion_ts ASC", String.valueOf(iA));
            String[] strArr = new String[listA.size()];
            for (int i2 = 0; i2 < listA.size(); i2++) {
                strArr[i2] = String.valueOf(listA.get(i2).getAsInteger("id"));
            }
            bVarA.a("ad", "id IN " + Arrays.toString(strArr).replace(Constants.RequestParameters.LEFT_BRACKETS, "(").replace(Constants.RequestParameters.RIGHT_BRACKETS, ")"), null);
        }
        bVarA.b();
    }

    public static void c() {
        com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
        bVarA.a("ad", null, null);
        bVarA.b();
    }

    public static int a(a aVar) {
        return a(aVar.g);
    }
}
