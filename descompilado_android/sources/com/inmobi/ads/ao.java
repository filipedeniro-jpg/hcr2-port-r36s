package com.inmobi.ads;

import android.graphics.Point;
import android.webkit.URLUtil;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.appevents.codeless.internal.Constants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: NativeDataModel.java */
/* JADX INFO: loaded from: classes2.dex */
public class ao {
    private static final String l = "ao";
    int a;
    public boolean b;
    public boolean c;
    public am d;
    JSONArray e;
    final ao f;

    @Nullable
    Map<String, String> g;
    Map<String, List<ak>> h;
    a i;
    boolean j;
    bf k;
    private JSONObject m;
    private JSONObject n;
    private Map<String, ak> o;
    private Map<String, String> p;

    @Nullable
    private bx q;
    private c r;
    private AdContainer.RenderingProperties.PlacementType s;
    private boolean t;

    /* JADX INFO: compiled from: NativeDataModel.java */
    class a {
        JSONObject a;

        @NonNull
        C0071a b = new C0071a();
        ak c;

        a() {
        }

        /* JADX INFO: renamed from: com.inmobi.ads.ao$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: NativeDataModel.java */
        class C0071a {
            public String a;
            public String b;
            public String c;
            public String d;
            public float e;
            public String f;
            public boolean g;

            C0071a() {
            }
        }
    }

    ao() {
        this.f = null;
    }

    public ao(@NonNull AdContainer.RenderingProperties.PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, null, false, cVar, bxVar);
    }

    public ao(@NonNull AdContainer.RenderingProperties.PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar) {
        this(placementType, jSONObject, aoVar, z, cVar, bxVar, (byte) 0);
    }

    private ao(@NonNull AdContainer.RenderingProperties.PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable ao aoVar, boolean z, @Nullable c cVar, @Nullable bx bxVar, byte b) {
        this.s = placementType;
        this.f = aoVar;
        this.r = cVar == null ? new c() : cVar;
        this.m = jSONObject;
        this.a = 0;
        this.b = false;
        this.q = bxVar;
        this.o = new HashMap();
        this.p = new HashMap();
        this.h = new HashMap();
        this.i = new a();
        this.t = z;
        f();
    }

    final JSONObject a() {
        try {
            return this.e.getJSONObject(0);
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return null;
        }
    }

    final int b() {
        am amVar = this.d;
        if (amVar == null) {
            return 0;
        }
        for (ak akVar : amVar) {
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                return ((am) akVar).C;
            }
        }
        return 0;
    }

    final am a(int i) {
        for (ak akVar : this.d) {
            if (akVar.d.equalsIgnoreCase("card_scrollable")) {
                am amVar = (am) akVar;
                if (i >= amVar.C) {
                    return null;
                }
                return (am) amVar.a(i);
            }
        }
        return null;
    }

    static am a(@NonNull ak akVar) {
        if (akVar instanceof am) {
            am amVar = (am) akVar;
            if (a(amVar)) {
                return amVar;
            }
        }
        for (am amVar2 = (am) akVar.t; amVar2 != null; amVar2 = (am) amVar2.t) {
            if (a(amVar2)) {
                return amVar2;
            }
        }
        return null;
    }

    private void d() {
        bt.a next;
        for (ak akVar : c(ShareConstants.IMAGE_URL)) {
            if (!URLUtil.isValidUrl((String) akVar.e)) {
                ak akVarA = a(this, akVar);
                if (akVarA == null) {
                    StringBuilder sb = new StringBuilder("Could not find referenced asset for asset (");
                    sb.append(akVar.d);
                    sb.append(")");
                } else if (!akVarA.b.equals(akVar.b)) {
                    if (ShareConstants.VIDEO_URL.equals(akVarA.b) && 1 != akVarA.m && 2 == akVarA.m) {
                        be beVar = (be) akVarA;
                        by byVarB = beVar.b();
                        bt btVarA = bs.a(beVar, akVar);
                        List<bt.a> listA = btVarA == null ? null : btVarA.a(1);
                        if (listA == null) {
                            next = null;
                            break;
                        }
                        Iterator<bt.a> it = listA.iterator();
                        do {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                        } while (!URLUtil.isValidUrl(next.b));
                        if (btVarA == null || next == null) {
                            a(beVar);
                            a(btVarA == null ? "NoBestFitCompanion" : "NoValidResource", "Static", "URL", (String) null, (String) null);
                        } else {
                            byVarB.a(btVarA);
                            new StringBuilder("Setting image asset value: ").append(next.b);
                            akVar.e = next.b;
                            akVar.a(btVarA.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                            akVar.a(beVar.u, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR);
                        }
                    }
                } else {
                    akVar.e = akVarA.e;
                }
            }
        }
    }

    private static void a(String str, String str2, String str3, String str4, String str5) {
        try {
            HashMap map = new HashMap();
            map.put("errorCode", str);
            map.put("type", str2);
            map.put("dataType", str3);
            map.put("clientRequestId", null);
            map.put("impId", null);
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", "EndCardCompanionFailure", map);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error in sendTelemetryEventForCompanionFailure : (");
            sb.append(e.getMessage());
            sb.append(")");
        }
    }

    private void e() {
        for (ak akVar : c("WEBVIEW")) {
            bf bfVar = (bf) akVar;
            if (!"URL".equals(bfVar.z) && !"HTML".equals(bfVar.z)) {
                ak akVarA = a(this, akVar);
                if (akVarA == null) {
                    StringBuilder sb = new StringBuilder("Could not find referenced asset for asset (");
                    sb.append(akVar.d);
                    sb.append(")");
                } else if (!akVarA.b.equals(akVar.b)) {
                    if (ShareConstants.VIDEO_URL.equals(akVarA.b) && 2 == akVarA.m) {
                        be beVar = (be) akVarA;
                        by byVarB = beVar.b();
                        bt btVarA = bs.a(beVar, akVar);
                        String strA = btVarA == null ? null : a(btVarA, bfVar);
                        boolean zEquals = "REF_IFRAME".equals(bfVar.z);
                        boolean zEquals2 = "REF_HTML".equals(bfVar.z);
                        if (btVarA == null || ((zEquals && !URLUtil.isValidUrl(strA)) || (zEquals2 && strA == null))) {
                            a(beVar);
                            a(btVarA == null ? "NoBestFitCompanion" : "NoValidResource", "Rich", bfVar.z, (String) null, (String) null);
                            bfVar.z = "UNKNOWN";
                        } else {
                            byVarB.a(btVarA);
                            akVar.e = strA;
                            akVar.a(btVarA.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW));
                        }
                    }
                } else {
                    akVar.e = akVarA.e;
                }
            }
        }
    }

    private void f() {
        int i;
        int i2;
        JSONObject jSONObject;
        String string;
        boolean z;
        JSONObject jSONObject2;
        try {
            this.n = this.m.optJSONObject("styleRefs");
            if (!this.m.isNull("orientation")) {
                String strTrim = this.m.getString("orientation").toLowerCase(Locale.US).trim();
                byte b = -1;
                int iHashCode = strTrim.hashCode();
                if (iHashCode != -1626174665) {
                    if (iHashCode != 729267099) {
                        if (iHashCode == 1430647483 && strTrim.equals("landscape")) {
                            b = 3;
                        }
                    } else if (strTrim.equals("portrait")) {
                        b = 2;
                    }
                } else if (strTrim.equals("unspecified")) {
                    b = 1;
                }
                i = b != 2 ? b != 3 ? 0 : 2 : 1;
            }
            this.a = i;
            this.j = this.m.optBoolean("shouldAutoOpenLandingPage", true);
            this.b = this.m.optBoolean("disableBackButton", false);
            this.d = (am) a(this.m.getJSONObject("rootContainer"), "CONTAINER", "/rootContainer", null);
            try {
                if (!this.m.isNull("passThroughJson")) {
                    this.i.a = this.m.getJSONObject("passThroughJson");
                }
                if (!this.m.isNull("adContent") && (jSONObject2 = this.m.getJSONObject("adContent")) != null) {
                    a aVar = this.i;
                    aVar.getClass();
                    a.C0071a c0071a = aVar.new C0071a();
                    c0071a.a = jSONObject2.optString("title", null);
                    c0071a.b = jSONObject2.optString("description", null);
                    c0071a.d = jSONObject2.optString(com.my.target.be.a.fd, null);
                    c0071a.c = jSONObject2.optString("iconUrl", null);
                    c0071a.e = jSONObject2.optLong("rating", 0L);
                    c0071a.f = jSONObject2.optString("landingPageUrl", null);
                    c0071a.g = jSONObject2.optBoolean("isApp");
                    this.i.b = c0071a;
                }
                ak akVar = new ak();
                if (!this.m.isNull("onClick")) {
                    JSONObject jSONObject3 = this.m.getJSONObject("onClick");
                    try {
                        String string2 = "";
                        if (jSONObject3.isNull("itemUrl")) {
                            string = "";
                            z = false;
                        } else {
                            string = jSONObject3.getString("itemUrl");
                            z = true;
                        }
                        if (!jSONObject3.isNull("action")) {
                            string2 = jSONObject3.getString("action");
                            z = true;
                        }
                        akVar.a(string);
                        akVar.b(jSONObject3.optString("fallbackUrl"));
                        akVar.j = string2;
                        akVar.h = z;
                        akVar.w = jSONObject3.optString("appBundleId");
                    } catch (JSONException unused) {
                    }
                    if (!jSONObject3.isNull("openMode")) {
                        akVar.i = d(jSONObject3.getString("openMode"));
                        akVar.b(jSONObject3.optString("fallbackUrl"));
                    }
                }
                if (!this.m.isNull("trackers")) {
                    akVar.a(b(this.m));
                }
                this.i.c = akVar;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            this.c = this.m.optBoolean("prefetchNextPage");
            if (this.m.has("rewards")) {
                this.g = new HashMap();
            }
            if (!this.m.isNull("rewards") && (jSONObject = this.m.getJSONObject("rewards")) != null) {
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    this.g.put(next, jSONObject.getString(next));
                }
            }
            d();
            e();
            for (Map.Entry<String, String> entry : this.p.entrySet()) {
                String value = entry.getValue();
                ak akVar2 = this.o.get(entry.getKey());
                if (4 == akVar2.n) {
                    ak akVar3 = this.o.get(value);
                    if (ShareConstants.VIDEO_URL.equals(akVar3.b)) {
                        String[] strArrSplit = ((bx) ((be) akVar3).b()).b.split(":");
                        try {
                            try {
                                i2 = (Integer.parseInt(strArrSplit[1]) * 60) + Integer.parseInt(strArrSplit[2]);
                            } catch (ArrayIndexOutOfBoundsException e2) {
                                e = e2;
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                                i2 = 0;
                            }
                        } catch (ArrayIndexOutOfBoundsException e3) {
                            e = e3;
                        }
                        if (i2 == 0) {
                            akVar2.o = i2 / 4;
                        } else {
                            int i3 = akVar2.o;
                            if (i3 == 50) {
                                akVar2.o = i2 / 2;
                            } else if (i3 == 75) {
                                akVar2.o = (i2 * 3) / 4;
                            } else if (i3 != 100) {
                                akVar2.o = i2 / 4;
                            } else {
                                akVar2.o = i2;
                            }
                        }
                        ((be) akVar3).z.add(akVar2);
                    }
                }
            }
            if (this.m.isNull("pages")) {
                this.e = new JSONArray();
            } else {
                this.e = this.m.getJSONArray("pages");
            }
        } catch (JSONException e4) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e4));
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x003c  */
    static int a(String str) {
        byte b;
        String strTrim = str.toLowerCase(Locale.US).trim();
        int iHashCode = strTrim.hashCode();
        if (iHashCode != -1412832500) {
            if (iHashCode != 0) {
                if (iHashCode == 112202875 && strTrim.equals("video")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (strTrim.equals("")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (strTrim.equals(com.my.target.ai.a.db)) {
            b = 3;
        } else {
            b = -1;
        }
        if (b == 1 || b == 2) {
            return 1;
        }
        return b != 3 ? 0 : 2;
    }

    final boolean c() {
        am amVar;
        am amVar2 = this.d;
        if (amVar2 == null) {
            return false;
        }
        Iterator<ak> it = amVar2.iterator();
        while (true) {
            if (!it.hasNext()) {
                amVar = null;
                break;
            }
            ak next = it.next();
            if (next.d.equalsIgnoreCase("card_scrollable")) {
                amVar = (am) next;
                break;
            }
        }
        if (amVar == null) {
            return g();
        }
        if (b() <= 0) {
            return false;
        }
        return g();
    }

    private boolean g() {
        List<bv> listC;
        List<ak> listC2 = c(ShareConstants.VIDEO_URL);
        if (listC2 != null && listC2.size() > 0) {
            for (ak akVar : listC2) {
                akVar.a.length();
                be beVar = (be) akVar;
                if (beVar.b() != null && (listC = beVar.b().c()) != null && listC.size() != 0) {
                    String strB = beVar.b().b();
                    if (strB == null || strB.length() == 0) {
                        HashMap map = new HashMap();
                        map.put("[ERRORCODE]", "403");
                        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR, map);
                    }
                }
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:380:0x07f0 -> B:381:0x07fc). Please report as a decompilation issue!!! */
    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 22681. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:79)
        */
    @android.annotation.TargetApi(15)
    private com.inmobi.ads.ak a(@androidx.annotation.NonNull org.json.JSONObject r41, java.lang.String r42, java.lang.String r43, @androidx.annotation.Nullable com.inmobi.ads.al r44) {
        /*
            Method dump skipped, instruction units count: 2268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.ao.a(org.json.JSONObject, java.lang.String, java.lang.String, com.inmobi.ads.al):com.inmobi.ads.ak");
    }

    private static void a(@NonNull ak akVar, @NonNull JSONObject jSONObject) throws JSONException {
        String string;
        boolean z;
        boolean z2 = true;
        String string2 = "";
        if (p(jSONObject)) {
            if (jSONObject.getJSONObject("assetOnclick").isNull("itemUrl")) {
                new StringBuilder("Missing itemUrl on asset ").append(jSONObject.toString());
                string = "";
                z = false;
            } else {
                string = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                z = true;
            }
            if (jSONObject.getJSONObject("assetOnclick").isNull("action")) {
                z2 = z;
            } else {
                string2 = jSONObject.getJSONObject("assetOnclick").getString("action");
            }
        } else {
            string = "";
            z2 = false;
        }
        akVar.a(string);
        akVar.j = string2;
        akVar.h = z2;
    }

    @Nullable
    final ak b(String str) {
        if (str != null && str.length() != 0) {
            if (this.o.get(str) != null) {
                return this.o.get(str);
            }
            ao aoVar = this.f;
            if (aoVar != null) {
                return aoVar.o.get(str);
            }
        }
        return null;
    }

    final List<ak> c(String str) {
        if (this.h.containsKey(str)) {
            return this.h.get(str);
        }
        return Collections.emptyList();
    }

    private static boolean a(JSONObject jSONObject, String str) {
        if (jSONObject.isNull("geometry")) {
            return false;
        }
        try {
            if (!a(jSONObject.getJSONArray("geometry"))) {
                return false;
            }
            switch (str) {
                case "CONTAINER":
                case "ICON":
                case "IMAGE":
                case "VIDEO":
                case "TIMER":
                case "WEBVIEW":
                case "GIF":
                    return true;
                case "TEXT":
                case "CTA":
                    if (jSONObject.isNull(MimeTypes.BASE_TYPE_TEXT)) {
                        return false;
                    }
                    try {
                        return ((int) Double.parseDouble(jSONObject.getJSONObject(MimeTypes.BASE_TYPE_TEXT).getString("size"))) > 0;
                    } catch (NumberFormatException e) {
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                        return false;
                    }
                default:
                    return false;
            }
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        } catch (JSONException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return false;
        }
    }

    private static boolean a(JSONArray jSONArray) {
        try {
            return jSONArray.getInt(2) > 0 && jSONArray.getInt(3) > 0;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return false;
        }
    }

    private static NativeTracker a(int i, NativeTracker.TrackerEventType trackerEventType, JSONObject jSONObject) throws JSONException {
        String strTrim = jSONObject.isNull("url") ? "" : jSONObject.getString("url").trim();
        HashMap map = new HashMap();
        if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER == trackerEventType) {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(DataBaseEventsStorage.EventEntry.TABLE_NAME);
            if ((strTrim.length() == 0 || ((strTrim.startsWith("http") && !URLUtil.isValidUrl(strTrim)) || !strTrim.startsWith("http"))) && jSONArrayOptJSONArray == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (jSONArrayOptJSONArray != null) {
                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                    NativeTracker.TrackerEventType trackerEventTypeA = NativeTracker.a(jSONArrayOptJSONArray.getString(i2));
                    if (trackerEventTypeA == NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CREATIVE_VIEW || trackerEventTypeA == NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PLAY || trackerEventTypeA == NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER) {
                        arrayList.add(trackerEventTypeA);
                    }
                }
            }
            map.put("referencedEvents", arrayList);
        } else if (strTrim.length() == 0 || !URLUtil.isValidUrl(strTrim)) {
            return null;
        }
        HashMap map2 = new HashMap();
        try {
            if (!jSONObject.isNull("params")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("params");
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map2.put(next, jSONObject2.getString(next));
                }
            }
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        NativeTracker nativeTracker = new NativeTracker(strTrim, i, trackerEventType, map2);
        nativeTracker.d = new HashMap(map);
        return nativeTracker;
    }

    private static List<NativeTracker> a(JSONObject jSONObject) {
        LinkedList linkedList = new LinkedList();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("passThroughJson");
            HashMap map = new HashMap();
            if (!jSONObject2.isNull("macros")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("macros");
                Iterator<String> itKeys = jSONObject3.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map.put(next, jSONObject3.getString(next));
                }
            }
            if (!jSONObject2.isNull("urls")) {
                JSONArray jSONArray = jSONObject2.getJSONArray("urls");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    linkedList.add(new NativeTracker(jSONArray.getString(i), 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS, map));
                }
            }
            if (linkedList.isEmpty()) {
                linkedList.add(new NativeTracker("", 0, NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS, map));
            }
        } catch (Exception e) {
            new StringBuilder("Failed to parse IAS tracker : ").append(e.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return linkedList;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0072  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ff  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static List<NativeTracker> b(@NonNull JSONObject jSONObject) {
        byte b;
        String str;
        NativeTracker.TrackerEventType trackerEventType;
        if (jSONObject.isNull("trackers")) {
            return null;
        }
        LinkedList linkedList = new LinkedList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("trackers");
            int length = jSONArray.length();
            if (length == 0) {
                return linkedList;
            }
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i2);
                if (!jSONObject2.isNull("trackerType")) {
                    String strTrim = jSONObject2.getString("trackerType").toUpperCase(Locale.US).trim();
                    int iHashCode = strTrim.hashCode();
                    byte b2 = -1;
                    if (iHashCode != -1430070305) {
                        if (iHashCode != -158113182) {
                            if (iHashCode == 1110926088 && strTrim.equals("URL_WEBVIEW_PING")) {
                                b = 2;
                            } else {
                                b = -1;
                            }
                        } else if (strTrim.equals("URL_PING")) {
                            b = 1;
                        } else {
                            b = -1;
                        }
                    } else if (strTrim.equals("HTML_SCRIPT")) {
                        b = 3;
                    } else {
                        b = -1;
                    }
                    if (b == 1) {
                        str = "url_ping";
                    } else if (b != 2) {
                        str = b != 3 ? "unknown" : "html_script";
                    } else {
                        str = "webview_ping";
                    }
                    if ("url_ping".equals(str)) {
                        int iOptInt = jSONObject2.optInt("eventId", i);
                        if (!jSONObject2.isNull("uiEvent")) {
                            String string = jSONObject2.getString("uiEvent");
                            switch (string.toUpperCase(Locale.US).trim()) {
                                case "LOAD":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_LOAD;
                                    break;
                                case "CLIENT_FILL":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLIENT_FILL;
                                    break;
                                case "RENDER":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_RENDER;
                                    break;
                                case "VIEW":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_PAGE_VIEW;
                                    break;
                                case "CLICK":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_CLICK;
                                    break;
                                case "VIDEO_VIEWABILITY":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_VIDEO_RENDER;
                                    break;
                                case "IAS_VIEWABILITY":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS;
                                    break;
                                case "FALLBACK_URL_CLICK":
                                    trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_FALLBACK_URL;
                                    break;
                                default:
                                    String strTrim2 = string.toUpperCase(Locale.US).trim();
                                    switch (strTrim2.hashCode()) {
                                        case -1836567951:
                                            if (strTrim2.equals("DOWNLOADER_DOWNLOADED")) {
                                                b2 = 3;
                                            }
                                            break;
                                        case -1099027408:
                                            if (strTrim2.equals("DOWNLOADER_DOWNLOADING")) {
                                                b2 = 2;
                                            }
                                            break;
                                        case 1331888222:
                                            if (strTrim2.equals("DOWNLOADER_ERROR")) {
                                                b2 = 4;
                                            }
                                            break;
                                        case 1346121898:
                                            if (strTrim2.equals("DOWNLOADER_INITIALIZED")) {
                                                b2 = 1;
                                            }
                                            break;
                                    }
                                    if (b2 != 1) {
                                        if (b2 == 2) {
                                            trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADING;
                                        } else if (b2 == 3) {
                                            trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_DOWNLOADED;
                                        } else if (b2 != 4) {
                                            trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN;
                                        } else {
                                            trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_ERROR;
                                        }
                                        break;
                                    } else {
                                        trackerEventType = NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_DOWNLOADER_INIT;
                                        break;
                                    }
                                    break;
                            }
                            if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_UNKNOWN != trackerEventType) {
                                if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS != trackerEventType) {
                                    NativeTracker nativeTrackerA = a(iOptInt, trackerEventType, jSONObject2);
                                    if (nativeTrackerA != null) {
                                        linkedList.add(nativeTrackerA);
                                    }
                                } else {
                                    linkedList.addAll(a(jSONObject2));
                                }
                            }
                        }
                    }
                }
                i2++;
                i = 0;
            }
            return linkedList;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return linkedList;
        }
    }

    @Nullable
    private by a(@NonNull JSONObject jSONObject, @NonNull String str, ak akVar) {
        if (f(jSONObject).equalsIgnoreCase(ShareConstants.VIDEO_URL)) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray != null && jSONArray.length() != 0) {
                    if (akVar != null && (akVar instanceof be)) {
                        return (by) akVar.e;
                    }
                    return new bu(this.r.m).a(str);
                }
                return null;
            } catch (JSONException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        }
        return null;
    }

    private static String c(@NonNull JSONObject jSONObject) {
        try {
            return ((f(jSONObject).equalsIgnoreCase("ICON") || f(jSONObject).equalsIgnoreCase(ShareConstants.IMAGE_URL) || f(jSONObject).equalsIgnoreCase("GIF")) && jSONObject.getJSONArray("assetValue").getString(0).length() != 0) ? jSONObject.getJSONArray("assetValue").getString(0) : "";
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String d(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetId");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return Integer.toString(jSONObject.hashCode());
        }
    }

    private static String e(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetName");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String f(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String g(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("valueType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static String h(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("dataType");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    @NonNull
    private JSONObject i(@NonNull JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = jSONObject.isNull("assetStyle") ? null : jSONObject.getJSONObject("assetStyle");
            if (jSONObject2 != null) {
                return jSONObject2;
            }
            if (jSONObject.isNull("assetStyleRef")) {
                return new JSONObject();
            }
            return this.n == null ? new JSONObject() : this.n.getJSONObject(jSONObject.getString("assetStyleRef"));
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONObject();
        }
    }

    private Point j(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject jSONObjectI = i(jSONObject);
            if (jSONObjectI.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = jSONObjectI.getJSONArray("geometry");
            point.x = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(0));
            point.y = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(1));
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return point;
    }

    private Point a(@NonNull JSONObject jSONObject, @NonNull Point point) {
        try {
            JSONObject jSONObjectI = i(jSONObject);
            if (jSONObjectI.isNull("finalGeometry")) {
                return point;
            }
            Point point2 = new Point();
            try {
                JSONArray jSONArray = jSONObjectI.getJSONArray("finalGeometry");
                point2.x = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(0));
                point2.y = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(1));
                return point2;
            } catch (JSONException unused) {
                return point2;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }

    private Point k(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject jSONObjectI = i(jSONObject);
            if (jSONObjectI.isNull("geometry")) {
                return point;
            }
            JSONArray jSONArray = jSONObjectI.getJSONArray("geometry");
            point.x = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(2));
            point.y = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(3));
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
        }
        return point;
    }

    private Point b(@NonNull JSONObject jSONObject, @NonNull Point point) {
        try {
            JSONObject jSONObjectI = i(jSONObject);
            if (jSONObjectI.isNull("finalGeometry")) {
                return point;
            }
            Point point2 = new Point();
            try {
                JSONArray jSONArray = jSONObjectI.getJSONArray("finalGeometry");
                point2.x = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(2));
                point2.y = com.inmobi.commons.core.utilities.b.c.a(jSONArray.getInt(3));
                return point2;
            } catch (JSONException unused) {
                return point2;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }

    private static int l(@NonNull JSONObject jSONObject) {
        try {
            JSONObject jSONObjectN = n(jSONObject);
            if (jSONObjectN.isNull("type")) {
                return 2;
            }
            String lowerCase = jSONObjectN.getString("type").trim().toLowerCase(Locale.US);
            byte b = -1;
            int iHashCode = lowerCase.hashCode();
            if (iHashCode != -921832806) {
                if (iHashCode != -284840886) {
                    if (iHashCode == 1728122231 && lowerCase.equals(Constants.PATH_TYPE_ABSOLUTE)) {
                        b = 2;
                    }
                } else if (lowerCase.equals("unknown")) {
                    b = 1;
                }
            } else if (lowerCase.equals("percentage")) {
                b = 3;
            }
            if (b != 2) {
                return b != 3 ? 1 : 4;
            }
            return 3;
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return 2;
        }
    }

    private static int a(@NonNull JSONObject jSONObject, boolean z) {
        try {
            JSONObject jSONObjectN = n(jSONObject);
            String str = "delay";
            if (jSONObjectN.isNull(z ? "delay" : "hideAfterDelay")) {
                return -1;
            }
            if (!z) {
                str = "hideAfterDelay";
            }
            int i = jSONObjectN.getInt(str);
            if (3 == l(jSONObject)) {
                return i;
            }
            if (4 != l(jSONObject) || i <= 0 || i > 100) {
                return -1;
            }
            int[] iArr = {25, 50, 75, 100};
            double d = Double.MAX_VALUE;
            int i2 = -1;
            for (int i3 = 0; i3 < 4; i3++) {
                int i4 = i - iArr[i3];
                double d2 = i4 * i4;
                if (d2 < d) {
                    i2 = i3;
                    d = d2;
                }
            }
            return iArr[i2];
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return -1;
        }
    }

    private static String m(@NonNull JSONObject jSONObject) {
        try {
            JSONObject jSONObjectN = n(jSONObject);
            return jSONObjectN.isNull("reference") ? "" : jSONObjectN.getString("reference");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return "";
        }
    }

    private static JSONObject n(@NonNull JSONObject jSONObject) {
        if (jSONObject.isNull("display")) {
            return new JSONObject();
        }
        try {
            return jSONObject.getJSONObject("display");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONObject();
        }
    }

    private static JSONArray o(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("assetValue");
        } catch (JSONException e) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            return new JSONArray();
        }
    }

    private static boolean p(@NonNull JSONObject jSONObject) {
        return !jSONObject.isNull("assetOnclick");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x003e  */
    private static int d(@NonNull String str) {
        byte b;
        switch (str.toUpperCase(Locale.US).trim()) {
            case "DOWNLOAD":
                b = 4;
                break;
            case "EXTERNAL":
                b = 1;
                break;
            case "INAPP":
                b = 2;
                break;
            case "DEEPLINK":
                b = 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 2) {
            return 1;
        }
        if (b != 3) {
            return b != 4 ? 2 : 4;
        }
        return 3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:20:0x0048  */
    private static String e(@NonNull String str) {
        byte b;
        switch (str.toLowerCase(Locale.US).trim()) {
            case "italic":
                b = 3;
                break;
            case "underline":
                b = 5;
                break;
            case "strike":
                b = 4;
                break;
            case "bold":
                b = 2;
                break;
            case "none":
                b = 1;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 2) {
            return TtmlNode.BOLD;
        }
        if (b == 3) {
            return TtmlNode.ITALIC;
        }
        if (b != 4) {
            return b != 5 ? "none" : TtmlNode.UNDERLINE;
        }
        return "strike";
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002e  */
    private static String f(@NonNull String str) {
        byte b;
        String strTrim = str.toLowerCase(Locale.US).trim();
        int iHashCode = strTrim.hashCode();
        if (iHashCode != 3321844) {
            if (iHashCode == 3387192 && strTrim.equals("none")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (strTrim.equals("line")) {
            b = 2;
        } else {
            b = -1;
        }
        return b != 2 ? "none" : "line";
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002e  */
    private static String g(@NonNull String str) {
        byte b;
        String strTrim = str.toLowerCase(Locale.US).trim();
        int iHashCode = strTrim.hashCode();
        if (iHashCode != -1349116587) {
            if (iHashCode == 1787472634 && strTrim.equals("straight")) {
                b = 1;
            } else {
                b = -1;
            }
        } else if (strTrim.equals("curved")) {
            b = 2;
        } else {
            b = -1;
        }
        return b != 2 ? "straight" : "curved";
    }

    private ba.a q(JSONObject jSONObject) {
        return new ba.a(jSONObject.optLong(Constants.PATH_TYPE_ABSOLUTE), jSONObject.optLong("percentage"), jSONObject.optString("reference"), this);
    }

    private ba.a r(JSONObject jSONObject) {
        return new ba.a(jSONObject.optLong(Constants.PATH_TYPE_ABSOLUTE), jSONObject.optLong("percentage"), jSONObject.optString("reference"), this);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private al a(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String strF = "none";
        String strTrim = "#ff000000";
        String strG = "straight";
        if (!jSONObject.isNull("border")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("border");
            if (!jSONObject2.isNull("style")) {
                strF = f(jSONObject2.getString("style"));
                strG = jSONObject2.isNull("corner") ? "straight" : g(jSONObject2.getString("corner"));
                if (!jSONObject2.isNull("color")) {
                    strTrim = jSONObject2.getString("color").trim();
                }
            }
        }
        String str = strF;
        String str2 = strTrim;
        String str3 = strG;
        String strTrim2 = jSONObject.isNull(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) ? "#00000000" : jSONObject.getString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR).trim();
        String str4 = "fill";
        if (!jSONObject.isNull("contentMode")) {
            String strTrim3 = jSONObject.getString("contentMode").trim();
            byte b = -1;
            switch (strTrim3.hashCode()) {
                case -1626174665:
                    if (strTrim3.equals("unspecified")) {
                        b = 1;
                    }
                    break;
                case -1362001767:
                    if (strTrim3.equals("aspectFit")) {
                        b = 4;
                    }
                    break;
                case 3143043:
                    if (strTrim3.equals("fill")) {
                        b = 2;
                    }
                    break;
                case 727618043:
                    if (strTrim3.equals("aspectFill")) {
                        b = 3;
                    }
                    break;
            }
            if (b != 2) {
                str4 = b != 3 ? b != 4 ? "unspecified" : "aspectFit" : "aspectFill";
            }
        }
        return new al(point.x, point.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, str4, str, str3, str2, strTrim2, s(jSONObject));
    }

    private ba s(JSONObject jSONObject) throws JSONException {
        return new ba(!jSONObject.isNull("startOffset") ? r(jSONObject.getJSONObject("startOffset")) : null, jSONObject.isNull("timerDuration") ? null : r(jSONObject.getJSONObject("timerDuration")));
    }

    /* JADX WARN: Code duplicated, block: B:4:0x001c  */
    private az.a b(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String strTrim;
        String str;
        String str2;
        int length;
        String[] strArr;
        int i;
        if (jSONObject.isNull("border")) {
            str2 = "straight";
            strTrim = "#ff000000";
            str = "none";
        } else {
            JSONObject jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                str2 = "straight";
                strTrim = "#ff000000";
                str = "none";
            } else {
                String strF = f(jSONObject2.getString("style"));
                String strG = jSONObject2.isNull("corner") ? "straight" : g(jSONObject2.getString("corner"));
                if (jSONObject2.isNull("color")) {
                    str = strF;
                    str2 = strG;
                    strTrim = "#ff000000";
                } else {
                    strTrim = jSONObject2.getString("color").trim();
                    str = strF;
                    str2 = strG;
                }
            }
        }
        String strTrim2 = jSONObject.isNull(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) ? "#00000000" : jSONObject.getString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR).trim();
        JSONObject jSONObject3 = jSONObject.getJSONObject(MimeTypes.BASE_TYPE_TEXT);
        try {
            int i2 = (int) Double.parseDouble(jSONObject3.getString("size"));
            int i3 = jSONObject3.isNull("length") ? Integer.MAX_VALUE : Integer.parseInt(jSONObject3.getString("length"));
            String strTrim3 = jSONObject3.isNull("color") ? "#ff000000" : jSONObject3.getString("color").trim();
            if (jSONObject3.isNull("style") || (length = jSONObject3.getJSONArray("style").length()) == 0) {
                String[] strArr2 = {"none"};
                strArr = strArr2;
            } else {
                String[] strArr3 = new String[length];
                for (int i4 = 0; i4 < length; i4++) {
                    strArr3[i4] = e(jSONObject3.getJSONArray("style").getString(i4));
                }
                strArr = strArr3;
            }
            if (jSONObject3.isNull("align")) {
                i = 0;
            } else {
                String strTrim4 = jSONObject3.getString("align").trim();
                byte b = -1;
                int iHashCode = strTrim4.hashCode();
                int i5 = 2;
                if (iHashCode != -1364013605) {
                    if (iHashCode != 3317767) {
                        if (iHashCode == 108511772 && strTrim4.equals(TtmlNode.RIGHT)) {
                            b = 2;
                        }
                    } else if (strTrim4.equals(TtmlNode.LEFT)) {
                        b = 1;
                    }
                } else if (strTrim4.equals("centre")) {
                    b = 3;
                }
                if (b == 2) {
                    i5 = 1;
                } else if (b != 3) {
                    i5 = 0;
                }
                i = i5;
            }
            return new az.a(point.x, point.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, str, str2, strTrim, strTrim2, i2, i, i3, strTrim3, strArr, s(jSONObject));
        } catch (NumberFormatException e) {
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            throw jSONException;
        }
    }

    /* JADX WARN: Code duplicated, block: B:4:0x001c  */
    private az.a c(@NonNull Point point, @NonNull Point point2, @NonNull Point point3, @NonNull Point point4, @NonNull JSONObject jSONObject) throws JSONException {
        String strTrim;
        String str;
        String str2;
        int length;
        String[] strArr;
        if (jSONObject.isNull("border")) {
            str2 = "straight";
            strTrim = "#ff000000";
            str = "none";
        } else {
            JSONObject jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                str2 = "straight";
                strTrim = "#ff000000";
                str = "none";
            } else {
                String strF = f(jSONObject2.getString("style"));
                String strG = jSONObject2.isNull("corner") ? "straight" : g(jSONObject2.getString("corner"));
                if (jSONObject2.isNull("color")) {
                    str = strF;
                    str2 = strG;
                    strTrim = "#ff000000";
                } else {
                    strTrim = jSONObject2.getString("color").trim();
                    str = strF;
                    str2 = strG;
                }
            }
        }
        String strTrim2 = jSONObject.isNull(TtmlNode.ATTR_TTS_BACKGROUND_COLOR) ? "#00000000" : jSONObject.getString(TtmlNode.ATTR_TTS_BACKGROUND_COLOR).trim();
        JSONObject jSONObject3 = jSONObject.getJSONObject(MimeTypes.BASE_TYPE_TEXT);
        try {
            int i = (int) Double.parseDouble(jSONObject3.getString("size"));
            String strTrim3 = jSONObject3.isNull("color") ? "#ff000000" : jSONObject3.getString("color").trim();
            if (jSONObject3.isNull("style") || (length = jSONObject3.getJSONArray("style").length()) == 0) {
                String[] strArr2 = {"none"};
                strArr = strArr2;
            } else {
                String[] strArr3 = new String[length];
                for (int i2 = 0; i2 < length; i2++) {
                    strArr3[i2] = e(jSONObject3.getJSONArray("style").getString(i2));
                }
                strArr = strArr3;
            }
            return new an.a(point.x, point.y, point2.x, point2.y, point3.x, point3.y, point4.x, point4.y, str, str2, strTrim, strTrim2, i, strTrim3, strArr, s(jSONObject));
        } catch (NumberFormatException e) {
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            throw jSONException;
        }
    }

    private static boolean a(@NonNull am amVar) {
        return "card_scrollable".equalsIgnoreCase(amVar.d);
    }

    private static void a(be beVar) {
        beVar.x = 8;
        HashMap map = new HashMap();
        map.put("[ERRORCODE]", "601");
        beVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR, map);
    }

    @Nullable
    private static String a(bt btVar, bf bfVar) {
        if ("REF_HTML".equals(bfVar.z)) {
            List<bt.a> listA = btVar.a(2);
            if (listA.size() > 0) {
                return listA.get(0).b;
            }
            List<bt.a> listA2 = btVar.a(3);
            if (listA2.size() > 0) {
                String str = listA2.get(0).b;
                if (!URLUtil.isValidUrl(str)) {
                    a("MalformedURL", "Rich", "REF_HTML", (String) null, (String) null);
                } else {
                    bfVar.z = "REF_IFRAME";
                    return str;
                }
            }
        } else if ("REF_IFRAME".equals(bfVar.z)) {
            List<bt.a> listA3 = btVar.a(3);
            if (listA3.size() > 0) {
                String str2 = listA3.get(0).b;
                if (!URLUtil.isValidUrl(str2)) {
                    a("MalformedURL", "Rich", "REF_IFRAME", (String) null, (String) null);
                } else {
                    bfVar.z = "REF_IFRAME";
                    return str2;
                }
            } else {
                List<bt.a> listA4 = btVar.a(2);
                if (listA4.size() > 0) {
                    bfVar.z = "REF_HTML";
                    return listA4.get(0).b;
                }
            }
        }
        return null;
    }

    private static ak a(@NonNull ao aoVar, @NonNull ak akVar) {
        while (true) {
            String str = (String) akVar.e;
            if (str == null || str.length() == 0) {
                break;
            }
            String[] strArrSplit = str.split("\\|");
            ak akVarB = aoVar.b(strArrSplit[0]);
            if (akVarB != null) {
                if (akVarB.equals(akVar)) {
                    return null;
                }
                if (1 == strArrSplit.length) {
                    akVarB.m = 1;
                    return akVarB;
                }
                akVarB.m = a(strArrSplit[1]);
                StringBuilder sb = new StringBuilder("Referenced asset (");
                sb.append(akVarB.d);
                sb.append(")");
                return akVarB;
            }
            aoVar = aoVar.f;
        }
        return null;
    }

    private static boolean a(al alVar, al alVar2) {
        return alVar.a.x + alVar.c.x >= alVar2.a.x + alVar2.c.x && alVar.a.y + alVar.c.y >= alVar2.a.y + alVar2.c.y;
    }
}
