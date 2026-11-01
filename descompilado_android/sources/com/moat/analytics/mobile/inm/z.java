package com.moat.analytics.mobile.inm;

import android.app.Activity;
import android.graphics.Rect;
import android.location.Location;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.ironsource.sdk.constants.Constants;
import com.ironsource.sdk.constants.LocationConst;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
class z {
    private JSONObject c;
    private Rect d;
    private Rect e;
    private JSONObject f;
    private JSONObject g;
    private Location h;
    private Map<String, Object> i = new HashMap();
    String a = "{}";
    private c b = new c();

    static class a {
        int a = 0;
        final Set<Rect> b = new HashSet();
        boolean c = false;

        a() {
        }
    }

    private static class b {
        final View a;
        final Rect b;

        b(View view, b bVar) {
            this.a = view;
            this.b = bVar != null ? z.b(view, bVar.b.left, bVar.b.top) : z.k(view);
        }
    }

    private static class c {
        Rect a = new Rect(0, 0, 0, 0);
        double b = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        double c = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        c() {
        }
    }

    z() {
    }

    @VisibleForTesting
    static int a(Rect rect, Set<Rect> set) {
        int i = 0;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList<Rect> arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() { // from class: com.moat.analytics.mobile.inm.z.1
            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public final int compare(Rect rect2, Rect rect3) {
                return Integer.valueOf(rect2.top).compareTo(Integer.valueOf(rect3.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        for (Rect rect2 : arrayList) {
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int iWidth = 0;
        while (i < arrayList2.size() - 1) {
            int i2 = i + 1;
            if (!((Integer) arrayList2.get(i)).equals(arrayList2.get(i2))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i)).intValue(), rect.top, ((Integer) arrayList2.get(i2)).intValue(), rect.bottom);
                int i3 = rect.top;
                for (Rect rect4 : arrayList) {
                    if (Rect.intersects(rect4, rect3)) {
                        if (rect4.bottom > i3) {
                            iWidth += rect3.width() * (rect4.bottom - Math.max(i3, rect4.top));
                            i3 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i = i2;
        }
        return iWidth;
    }

    private static Rect a(DisplayMetrics displayMetrics) {
        return new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    static Rect a(View view) {
        return view != null ? k(view) : new Rect(0, 0, 0, 0);
    }

    @VisibleForTesting
    static a a(Rect rect, @NonNull View view) {
        a aVar = new a();
        try {
            ArrayDeque<View> arrayDequeI = i(view);
            if (arrayDequeI != null && !arrayDequeI.isEmpty()) {
                p.b(2, "VisibilityInfo", view, "starting covering rect search");
                b bVar = null;
                loop0: while (!arrayDequeI.isEmpty()) {
                    View viewPollLast = arrayDequeI.pollLast();
                    b bVar2 = new b(viewPollLast, bVar);
                    if (viewPollLast.getParent() != null && (viewPollLast.getParent() instanceof ViewGroup)) {
                        ViewGroup viewGroup = (ViewGroup) viewPollLast.getParent();
                        int childCount = viewGroup.getChildCount();
                        boolean z = false;
                        for (int i = 0; i < childCount; i++) {
                            if (aVar.a >= 500) {
                                p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting cover retrieval, reached max");
                                break loop0;
                            }
                            View childAt = viewGroup.getChildAt(i);
                            if (childAt == viewPollLast) {
                                z = true;
                            } else {
                                aVar.a++;
                                if (a(childAt, viewPollLast, z)) {
                                    b(new b(childAt, bVar), rect, aVar);
                                    if (aVar.c) {
                                        return aVar;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                    bVar = bVar2;
                }
                return aVar;
            }
            return aVar;
        } catch (Exception e) {
            m.a(e);
        }
    }

    private static c a(View view, Rect rect, boolean z, boolean z2, boolean z3) {
        c cVar = new c();
        int iB = b(rect);
        if (view != null && z && z2 && !z3 && iB > 0) {
            Rect rect2 = new Rect(0, 0, 0, 0);
            if (a(view, rect2)) {
                int iB2 = b(rect2);
                if (iB2 < iB) {
                    p.b(2, "VisibilityInfo", null, "Ad is clipped");
                }
                if (view.getRootView() instanceof ViewGroup) {
                    cVar.a = rect2;
                    a aVarA = a(rect2, view);
                    if (aVarA.c) {
                        cVar.c = 1.0d;
                    } else {
                        int iA = a(rect2, aVarA.b);
                        if (iA > 0) {
                            double d = iA;
                            double d2 = iB2;
                            Double.isNaN(d2);
                            Double.isNaN(d);
                            cVar.c = d / (d2 * 1.0d);
                        }
                        double d3 = iB2 - iA;
                        double d4 = iB;
                        Double.isNaN(d4);
                        Double.isNaN(d3);
                        cVar.b = d3 / (d4 * 1.0d);
                    }
                }
            }
        }
        return cVar;
    }

    private static Map<String, String> a(Rect rect) {
        HashMap map = new HashMap();
        map.put("x", String.valueOf(rect.left));
        map.put("y", String.valueOf(rect.top));
        map.put(com.my.target.i.WIDTH, String.valueOf(rect.right - rect.left));
        map.put(com.my.target.i.HEIGHT, String.valueOf(rect.bottom - rect.top));
        return map;
    }

    private static Map<String, String> a(Rect rect, DisplayMetrics displayMetrics) {
        return a(b(rect, displayMetrics));
    }

    private static JSONObject a(Location location) {
        Map<String, String> mapB = b(location);
        if (mapB == null) {
            return null;
        }
        return new JSONObject(mapB);
    }

    private static void a(b bVar, Rect rect, a aVar) {
        Rect rect2 = bVar.b;
        if (rect2.setIntersect(rect, rect2)) {
            if (Build.VERSION.SDK_INT >= 22) {
                Rect rect3 = new Rect(0, 0, 0, 0);
                if (!a(bVar.a, rect3)) {
                    return;
                }
                Rect rect4 = bVar.b;
                if (!rect4.setIntersect(rect3, rect4)) {
                    return;
                } else {
                    rect2 = rect4;
                }
            }
            if (w.a().c) {
                p.b(2, "VisibilityInfo", bVar.a, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", bVar.a.getClass().getName(), rect2.toString(), Float.valueOf(bVar.a.getAlpha())));
            }
            aVar.b.add(rect2);
            if (rect2.contains(rect)) {
                aVar.c = true;
            }
        }
    }

    private static boolean a(View view, Rect rect) {
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int[] iArr2 = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    private static boolean a(View view, View view2, boolean z) {
        if (z) {
            return Build.VERSION.SDK_INT < 21 || view.getZ() >= view2.getZ();
        }
        return Build.VERSION.SDK_INT >= 21 && view.getZ() > view2.getZ();
    }

    private static int b(Rect rect) {
        return rect.width() * rect.height();
    }

    private static Rect b(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(rect.left / f), Math.round(rect.top / f), Math.round(rect.right / f), Math.round(rect.bottom / f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rect b(View view, int i, int i2) {
        int left = i + view.getLeft();
        int top = i2 + view.getTop();
        return new Rect(left, top, view.getWidth() + left, view.getHeight() + top);
    }

    private static Map<String, String> b(Location location) {
        if (location == null) {
            return null;
        }
        HashMap map = new HashMap();
        map.put(LocationConst.LATITUDE, Double.toString(location.getLatitude()));
        map.put(LocationConst.LONGITUDE, Double.toString(location.getLongitude()));
        map.put("timestamp", Long.toString(location.getTime()));
        map.put("horizontalAccuracy", Float.toString(location.getAccuracy()));
        return map;
    }

    private static void b(b bVar, Rect rect, a aVar) {
        boolean z;
        if (h(bVar.a)) {
            if (bVar.a instanceof ViewGroup) {
                z = (ViewGroup.class.equals(bVar.a.getClass().getSuperclass()) && j(bVar.a)) ? false : true;
                ViewGroup viewGroup = (ViewGroup) bVar.a;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    int i2 = aVar.a + 1;
                    aVar.a = i2;
                    if (i2 > 500) {
                        return;
                    }
                    b(new b(viewGroup.getChildAt(i), bVar), rect, aVar);
                    if (aVar.c) {
                        return;
                    }
                }
            } else {
                z = true;
            }
            if (z) {
                a(bVar, rect, aVar);
            }
        }
    }

    private static boolean c(View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            return view != null && view.isAttachedToWindow();
        }
        return (view == null || view.getWindowToken() == null) ? false : true;
    }

    private static boolean d(View view) {
        return view != null && view.hasWindowFocus();
    }

    private static boolean e(View view) {
        return view == null || !view.isShown();
    }

    private static float f(View view) {
        if (view == null) {
            return 0.0f;
        }
        return g(view);
    }

    private static float g(View view) {
        float alpha = view.getAlpha();
        while (view != null && view.getParent() != null && alpha != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && (view.getParent() instanceof View)) {
            alpha *= ((View) view.getParent()).getAlpha();
            view = (View) view.getParent();
        }
        return alpha;
    }

    private static boolean h(View view) {
        return view.isShown() && ((double) view.getAlpha()) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    private static ArrayDeque<View> i(@NonNull View view) {
        ArrayDeque<View> arrayDeque = new ArrayDeque<>();
        int i = 0;
        View view2 = view;
        while (true) {
            if (view2.getParent() == null && view2 != view.getRootView()) {
                break;
            }
            i++;
            if (i <= 50) {
                arrayDeque.add(view2);
                if (!(view2.getParent() instanceof View)) {
                    break;
                }
                view2 = (View) view2.getParent();
            } else {
                p.a(3, "VisibilityInfo", (Object) null, "Short-circuiting chain retrieval, reached max");
                break;
            }
        }
        return arrayDeque;
    }

    private static boolean j(View view) {
        return Build.VERSION.SDK_INT < 19 || view.getBackground() == null || view.getBackground().getAlpha() == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Rect k(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }

    private static DisplayMetrics l(View view) {
        Activity activity;
        if (Build.VERSION.SDK_INT < 17 || com.moat.analytics.mobile.inm.a.a == null || (activity = com.moat.analytics.mobile.inm.a.a.get()) == null) {
            return view.getContext().getResources().getDisplayMetrics();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics;
    }

    void a(String str, View view) {
        HashMap map = new HashMap();
        String string = "{}";
        if (view != null) {
            try {
                DisplayMetrics displayMetricsL = l(view);
                boolean zC = c(view);
                boolean zD = d(view);
                boolean zE = e(view);
                float f = f(view);
                map.put("dr", Float.valueOf(displayMetricsL.density));
                map.put("dv", Double.valueOf(s.a()));
                map.put("adKey", str);
                boolean z = false;
                map.put("isAttached", Integer.valueOf(zC ? 1 : 0));
                map.put("inFocus", Integer.valueOf(zD ? 1 : 0));
                map.put("isHidden", Integer.valueOf(zE ? 1 : 0));
                map.put("opacity", Float.valueOf(f));
                Rect rectA = a(displayMetricsL);
                Rect rectA2 = a(view);
                c cVarA = a(view, rectA2, zC, zD, zE);
                if (this.c == null || cVarA.b != this.b.b || !cVarA.a.equals(this.b.a) || cVarA.c != this.b.c) {
                    this.b = cVarA;
                    this.c = new JSONObject(a(this.b.a, displayMetricsL));
                    z = true;
                }
                map.put("coveredPercent", Double.valueOf(cVarA.c));
                if (this.g == null || !rectA.equals(this.e)) {
                    this.e = rectA;
                    this.g = new JSONObject(a(rectA, displayMetricsL));
                    z = true;
                }
                if (this.f == null || !rectA2.equals(this.d)) {
                    this.d = rectA2;
                    this.f = new JSONObject(a(rectA2, displayMetricsL));
                    z = true;
                }
                if (this.i == null || !map.equals(this.i)) {
                    this.i = map;
                    z = true;
                }
                Location locationB = o.a().b();
                if (!o.a(locationB, this.h)) {
                    this.h = locationB;
                    z = true;
                }
                if (z) {
                    JSONObject jSONObject = new JSONObject(this.i);
                    jSONObject.accumulate("screen", this.g);
                    jSONObject.accumulate(Constants.ParametersKeys.VIEW, this.f);
                    jSONObject.accumulate("visible", this.c);
                    jSONObject.accumulate("maybe", this.c);
                    jSONObject.accumulate("visiblePercent", Double.valueOf(this.b.b));
                    if (locationB != null) {
                        jSONObject.accumulate("location", a(locationB));
                    }
                    string = jSONObject.toString();
                    this.a = string;
                }
            } catch (Exception e) {
                m.a(e);
                this.a = string;
            }
        }
    }
}
