package com.moat.analytics.mobile.cha;

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
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
final class u {

    /* JADX INFO: renamed from: ʼ, reason: contains not printable characters */
    private static int f463 = 0;

    /* JADX INFO: renamed from: ˋॱ, reason: contains not printable characters */
    private static int f464 = 1;

    /* JADX INFO: renamed from: ʻ, reason: contains not printable characters */
    private Location f465;

    /* JADX INFO: renamed from: ʽ, reason: contains not printable characters */
    private JSONObject f466;

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private Rect f467;

    /* JADX INFO: renamed from: ˊॱ, reason: contains not printable characters */
    private JSONObject f468;

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private JSONObject f469;

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private Rect f470;

    /* JADX INFO: renamed from: ᐝ, reason: contains not printable characters */
    private Map<String, Object> f473 = new HashMap();

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    String f472 = "{}";

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private c f471 = new c();

    static class c {

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        Rect f480 = new Rect(0, 0, 0, 0);

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        double f479 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
        double f481 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;

        c() {
        }
    }

    static class a {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        final Rect f474;

        /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
        final View f475;

        a(View view, a aVar) {
            this.f475 = view;
            if (aVar == null) {
                this.f474 = u.m441(view);
                return;
            }
            int i = aVar.f474.left;
            int i2 = aVar.f474.top;
            int left = i + view.getLeft();
            int top = i2 + view.getTop();
            this.f474 = new Rect(left, top, view.getWidth() + left, view.getHeight() + top);
        }
    }

    u() {
    }

    /* JADX WARN: Code duplicated, block: B:106:0x0141  */
    /* JADX WARN: Code duplicated, block: B:212:0x02b4 A[Catch: Exception -> 0x04d6, PHI: r6
  0x02b4: PHI (r6v63 android.graphics.Rect) = (r6v62 android.graphics.Rect), (r6v70 android.graphics.Rect) binds: [B:210:0x02b0, B:204:0x029e] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:214:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:215:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:217:0x02c5 A[Catch: Exception -> 0x04d6, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:220:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:221:0x02d7  */
    /* JADX WARN: Code duplicated, block: B:223:0x02da  */
    /* JADX WARN: Code duplicated, block: B:224:0x02db A[Catch: Exception -> 0x04d6, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:226:0x02e5  */
    /* JADX WARN: Code duplicated, block: B:227:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:229:0x02ea A[Catch: Exception -> 0x04d6, TRY_LEAVE, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:231:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:232:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:235:0x02f9  */
    /* JADX WARN: Code duplicated, block: B:239:0x031b A[Catch: Exception -> 0x04d6, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:258:0x035e  */
    /* JADX WARN: Code duplicated, block: B:259:0x0360  */
    /* JADX WARN: Code duplicated, block: B:274:0x03ad A[Catch: Exception -> 0x04d6, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:294:0x0408 A[Catch: Exception -> 0x04d6, PHI: r0
  0x0408: PHI (r0v18 boolean) = (r0v17 boolean), (r0v31 boolean) binds: [B:287:0x03ed, B:293:0x0406] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:38:0x0075 A[Catch: Exception -> 0x04d6, PHI: r6
  0x0075: PHI (r6v109 android.app.Activity) = (r6v108 android.app.Activity), (r6v114 android.app.Activity) binds: [B:37:0x0073, B:31:0x0061] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0086 A[Catch: Exception -> 0x04d6, TryCatch #0 {Exception -> 0x04d6, blocks: (B:8:0x001e, B:16:0x0037, B:27:0x0054, B:38:0x0075, B:40:0x0092, B:57:0x00bf, B:92:0x0123, B:112:0x014b, B:124:0x0163, B:126:0x0169, B:138:0x01ab, B:146:0x01cb, B:157:0x01ef, B:163:0x0213, B:165:0x021d, B:200:0x028a, B:212:0x02b4, B:217:0x02c5, B:218:0x02cd, B:224:0x02db, B:229:0x02ea, B:236:0x030c, B:238:0x0318, B:239:0x031b, B:206:0x02a2, B:240:0x031f, B:248:0x0336, B:256:0x0352, B:261:0x0363, B:266:0x0386, B:272:0x03a5, B:275:0x03bf, B:280:0x03ca, B:283:0x03e4, B:289:0x03f9, B:295:0x040b, B:302:0x042e, B:308:0x043a, B:325:0x04c5, B:327:0x04cc, B:319:0x0481, B:328:0x04cf, B:294:0x0408, B:282:0x03d2, B:274:0x03ad, B:251:0x0341, B:265:0x0370, B:164:0x0219, B:99:0x0133, B:63:0x00cd, B:75:0x00ef, B:33:0x0064, B:39:0x0086), top: B:333:0x001e }] */
    /* JADX WARN: Type inference failed for: r0v62, types: [java.util.HashSet, java.util.Set<android.graphics.Rect>] */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    final void m443(String str, View view) {
        DisplayMetrics displayMetrics;
        boolean z;
        boolean z2;
        boolean z3;
        int i;
        int i2;
        int i3;
        boolean z4;
        boolean z5;
        boolean z6;
        char c2;
        char c3;
        HashMap map;
        boolean z7;
        boolean z8;
        Rect rect;
        int iWidth;
        boolean z9;
        boolean z10;
        b bVarM437;
        boolean z11;
        int iM434;
        char c4;
        char c5;
        char c6;
        Activity activity;
        HashMap map2 = new HashMap();
        String string = "{}";
        if (view != null) {
            int i4 = f463 + 5;
            f464 = i4 % 128;
            int i5 = i4 % 2;
            try {
                if (!(Build.VERSION.SDK_INT < 17)) {
                    int i6 = f464 + 113;
                    f463 = i6 % 128;
                    int i7 = i6 % 2;
                    if ((com.moat.analytics.mobile.cha.c.f298 != null ? '9' : (char) 25) != 25) {
                        int i8 = f463 + 57;
                        f464 = i8 % 128;
                        if (i8 % 2 == 0) {
                            activity = com.moat.analytics.mobile.cha.c.f298.get();
                            if (!(activity != null)) {
                                displayMetrics = view.getContext().getResources().getDisplayMetrics();
                            } else {
                                displayMetrics = new DisplayMetrics();
                                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                            }
                        } else {
                            activity = com.moat.analytics.mobile.cha.c.f298.get();
                            if ((activity != null ? '<' : '0') != '0') {
                                displayMetrics = new DisplayMetrics();
                                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
                            } else {
                                displayMetrics = view.getContext().getResources().getDisplayMetrics();
                            }
                        }
                    } else {
                        displayMetrics = view.getContext().getResources().getDisplayMetrics();
                    }
                } else {
                    displayMetrics = view.getContext().getResources().getDisplayMetrics();
                }
                if (Build.VERSION.SDK_INT < 19) {
                    if ((view != null ? '9' : '\t') == '9') {
                        int i9 = f464 + 45;
                        f463 = i9 % 128;
                        if ((i9 % 2 != 0 ? (char) 1 : 'A') != 1) {
                            z = (view.getWindowToken() != null ? '[' : 'E') != 'E';
                        } else {
                            if ((view.getWindowToken() != null ? 'Z' : (char) 20) != 'Z') {
                            }
                        }
                    }
                } else {
                    int i10 = f463 + 51;
                    f464 = i10 % 128;
                    int i11 = i10 % 2;
                    if (view != null) {
                        if (view.isAttachedToWindow()) {
                            int i12 = f464 + 69;
                            f463 = i12 % 128;
                            int i13 = i12 % 2;
                        }
                    }
                }
                if ((view != null ? '6' : '%') != '%') {
                    int i14 = f464 + 101;
                    f463 = i14 % 128;
                    if ((i14 % 2 != 0 ? '[' : (char) 31) != '[') {
                        if ((view.hasWindowFocus() ? (char) 14 : (char) 16) != 16) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                    } else {
                        if (!(view.hasWindowFocus())) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                    }
                } else {
                    z2 = false;
                }
                if ((view != null ? 'R' : (char) 4) != 4) {
                    z3 = (!view.isShown() ? '@' : '%') != '%';
                }
                float fM433 = !(view == null) ? m433(view) : 0.0f;
                map2.put("dr", Float.valueOf(displayMetrics.density));
                map2.put("dv", Double.valueOf(r.m410()));
                map2.put("adKey", str);
                if ((z ? (char) 23 : (char) 30) != 23) {
                    i = 0;
                } else {
                    int i15 = f464 + 89;
                    f463 = i15 % 128;
                    if (i15 % 2 != 0) {
                    }
                    i = 1;
                }
                map2.put("isAttached", Integer.valueOf(i));
                if ((z2 ? 'U' : (char) 1) != 'U') {
                    i2 = 0;
                } else {
                    int i16 = f463 + 19;
                    f464 = i16 % 128;
                    int i17 = i16 % 2;
                    i2 = 1;
                }
                map2.put("inFocus", Integer.valueOf(i2));
                if ((z3 ? '0' : '*') != '0') {
                    i3 = 0;
                } else {
                    int i18 = f464 + 27;
                    f463 = i18 % 128;
                    if (i18 % 2 != 0) {
                    }
                    i3 = 1;
                }
                map2.put("isHidden", Integer.valueOf(i3));
                map2.put("opacity", Float.valueOf(fM433));
                Rect rect2 = new Rect(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
                Rect rect3 = (view != null ? 'X' : '9') != 'X' ? new Rect(0, 0, 0, 0) : m441(view);
                c cVar = new c();
                int iWidth2 = rect3.width() * rect3.height();
                if ((view != null ? (char) 5 : 'J') == 5) {
                    int i19 = f464 + 21;
                    f463 = i19 % 128;
                    int i20 = i19 % 2;
                    if ((z ? 'C' : 'V') != 'V') {
                        if ((z2 ? '\n' : '(') != '(') {
                            int i21 = f463 + 87;
                            f464 = i21 % 128;
                            int i22 = i21 % 2;
                            if ((!z3 ? ')' : 'N') != 'N') {
                                if ((iWidth2 > 0 ? (char) 1 : '\r') == 1) {
                                    int i23 = f464 + 101;
                                    f463 = i23 % 128;
                                    if ((i23 % 2 != 0 ? '&' : '9') != '&') {
                                        rect = new Rect(0, 0, 0, 0);
                                        if (m438(view, rect)) {
                                            c6 = '\t';
                                            c5 = '\t';
                                        } else {
                                            c5 = '1';
                                            c6 = '\t';
                                        }
                                        if (c5 == c6) {
                                            iWidth = rect.width() * rect.height();
                                            if (iWidth < iWidth2) {
                                                z9 = true;
                                            } else {
                                                z9 = false;
                                            }
                                            if (z9) {
                                                com.moat.analytics.mobile.cha.a.m274("VisibilityInfo", null, "Ad is clipped");
                                            }
                                            if (view.getRootView() instanceof ViewGroup) {
                                                z10 = true;
                                            } else {
                                                z10 = false;
                                            }
                                            if (!z10) {
                                                cVar.f480 = rect;
                                                bVarM437 = m437(rect, view);
                                                if (bVarM437.f476) {
                                                    z11 = true;
                                                } else {
                                                    z11 = false;
                                                }
                                                if (!z11) {
                                                    iM434 = m434(rect, (Set<Rect>) bVarM437.f477);
                                                    if (iM434 > 0) {
                                                        c4 = 'Z';
                                                    } else {
                                                        c4 = 4;
                                                    }
                                                    if (c4 != 4) {
                                                        int i24 = f464 + 35;
                                                        f463 = i24 % 128;
                                                        int i25 = i24 % 2;
                                                        double d = iM434;
                                                        double d2 = iWidth;
                                                        Double.isNaN(d);
                                                        Double.isNaN(d2);
                                                        cVar.f481 = d / d2;
                                                    }
                                                    double d3 = iWidth - iM434;
                                                    double d4 = iWidth2;
                                                    Double.isNaN(d3);
                                                    Double.isNaN(d4);
                                                    cVar.f479 = d3 / d4;
                                                } else {
                                                    cVar.f481 = 1.0d;
                                                }
                                            }
                                        }
                                    } else {
                                        rect = new Rect(0, 0, 0, 0);
                                        if (m438(view, rect)) {
                                            iWidth = rect.width() * rect.height();
                                            if (iWidth < iWidth2) {
                                                z9 = true;
                                            } else {
                                                z9 = false;
                                            }
                                            if (z9) {
                                                com.moat.analytics.mobile.cha.a.m274("VisibilityInfo", null, "Ad is clipped");
                                            }
                                            if (view.getRootView() instanceof ViewGroup) {
                                                z10 = true;
                                            } else {
                                                z10 = false;
                                            }
                                            if (!z10) {
                                                cVar.f480 = rect;
                                                bVarM437 = m437(rect, view);
                                                if (bVarM437.f476) {
                                                    z11 = true;
                                                } else {
                                                    z11 = false;
                                                }
                                                if (!z11) {
                                                    iM434 = m434(rect, (Set<Rect>) bVarM437.f477);
                                                    if (iM434 > 0) {
                                                        c4 = 'Z';
                                                    } else {
                                                        c4 = 4;
                                                    }
                                                    if (c4 != 4) {
                                                        int i26 = f464 + 35;
                                                        f463 = i26 % 128;
                                                        int i27 = i26 % 2;
                                                        double d5 = iM434;
                                                        double d6 = iWidth;
                                                        Double.isNaN(d5);
                                                        Double.isNaN(d6);
                                                        cVar.f481 = d5 / d6;
                                                    }
                                                    double d7 = iWidth - iM434;
                                                    double d8 = iWidth2;
                                                    Double.isNaN(d7);
                                                    Double.isNaN(d8);
                                                    cVar.f479 = d7 / d8;
                                                } else {
                                                    cVar.f481 = 1.0d;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (this.f469 != null) {
                    int i28 = f464 + 39;
                    f463 = i28 % 128;
                    if (i28 % 2 == 0) {
                        if ((cVar.f479 == this.f471.f479 ? '&' : 'G') != 'G') {
                            if (cVar.f480.equals(this.f471.f480)) {
                                z8 = true;
                            } else {
                                z8 = false;
                            }
                            if (!z8) {
                            }
                        }
                    } else if (cVar.f479 == this.f471.f479) {
                        if (cVar.f480.equals(this.f471.f480)) {
                            z8 = true;
                        } else {
                            z8 = false;
                        }
                        if (!z8 && cVar.f481 == this.f471.f481) {
                            z4 = false;
                        }
                    }
                    this.f471 = cVar;
                    this.f469 = new JSONObject(m440(m435(this.f471.f480, displayMetrics)));
                    z4 = true;
                } else {
                    this.f471 = cVar;
                    this.f469 = new JSONObject(m440(m435(this.f471.f480, displayMetrics)));
                    z4 = true;
                }
                map2.put("coveredPercent", Double.valueOf(cVar.f481));
                if (this.f468 != null) {
                    int i29 = f464 + 37;
                    f463 = i29 % 128;
                    int i30 = i29 % 2;
                    if (!rect2.equals(this.f470)) {
                        this.f470 = rect2;
                        this.f468 = new JSONObject(m440(m435(rect2, displayMetrics)));
                        z4 = true;
                    }
                } else {
                    this.f470 = rect2;
                    this.f468 = new JSONObject(m440(m435(rect2, displayMetrics)));
                    z4 = true;
                }
                if ((this.f466 != null ? (char) 18 : 'G') == 'G' || !rect3.equals(this.f467)) {
                    this.f467 = rect3;
                    this.f466 = new JSONObject(m440(m435(rect3, displayMetrics)));
                    z4 = true;
                }
                if (this.f473 != null) {
                    z5 = true;
                    z6 = false;
                } else {
                    z5 = true;
                    z6 = true;
                }
                if (z6 != z5) {
                    int i31 = f463 + 113;
                    f464 = i31 % 128;
                    int i32 = i31 % 2;
                    if (map2.equals(this.f473)) {
                        z5 = true;
                        z7 = true;
                    } else {
                        z5 = true;
                        z7 = false;
                    }
                    if (z7 != z5) {
                        this.f473 = map2;
                        z4 = true;
                    }
                } else {
                    this.f473 = map2;
                    z4 = true;
                }
                Location locationM384 = n.m375().m384();
                if ((!n.m374(locationM384, this.f465) ? (char) 21 : ']') != ']') {
                    int i33 = f464 + 81;
                    f463 = i33 % 128;
                    int i34 = i33 % 2;
                    this.f465 = locationM384;
                    z4 = true;
                }
                if (!z4) {
                    return;
                }
                JSONObject jSONObject = new JSONObject(this.f473);
                jSONObject.accumulate("screen", this.f468);
                jSONObject.accumulate(Constants.ParametersKeys.VIEW, this.f466);
                jSONObject.accumulate("visible", this.f469);
                jSONObject.accumulate("maybe", this.f469);
                jSONObject.accumulate("visiblePercent", Double.valueOf(this.f471.f479));
                if (locationM384 != null) {
                    c3 = '>';
                    c2 = '<';
                } else {
                    c2 = '<';
                    c3 = '<';
                }
                if (c3 != c2) {
                    if (locationM384 != null) {
                        z5 = false;
                    }
                    if (z5) {
                        map = null;
                    } else {
                        map = new HashMap();
                        map.put(LocationConst.LATITUDE, Double.toString(locationM384.getLatitude()));
                        map.put(LocationConst.LONGITUDE, Double.toString(locationM384.getLongitude()));
                        map.put("timestamp", Long.toString(locationM384.getTime()));
                        map.put("horizontalAccuracy", Float.toString(locationM384.getAccuracy()));
                    }
                    jSONObject.accumulate("location", (map == null ? (char) 14 : 'U') != 14 ? new JSONObject(map) : null);
                }
                string = jSONObject.toString();
                this.f472 = string;
            } catch (Exception e) {
                o.m389(e);
                this.f472 = string;
            }
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static float m433(View view) {
        float alpha = view.getAlpha();
        while (true) {
            if ((view != null ? (char) 25 : 'E') == 25) {
                if (!(view.getParent() != null)) {
                    break;
                }
                if (!(((double) alpha) != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                    break;
                }
                if ((view.getParent() instanceof View ? (char) 27 : ']') != 27) {
                    break;
                }
                alpha *= ((View) view.getParent()).getAlpha();
                view = (View) view.getParent();
            } else {
                break;
            }
        }
        return alpha;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    static Rect m436(View view) {
        if ((view != null ? '+' : '>') != '+') {
            return new Rect(0, 0, 0, 0);
        }
        int i = f464 + 39;
        f463 = i % 128;
        return i % 2 == 0 ? m441(view) : m441(view);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0032  */
    /* JADX WARN: Code duplicated, block: B:48:0x009e  */
    /* JADX WARN: Code duplicated, block: B:50:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:54:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:77:0x00e3  */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static void m442(a aVar, Rect rect, b bVar) {
        boolean z;
        char c2;
        boolean z2;
        boolean z3;
        View view = aVar.f475;
        if ((view.isShown() ? (char) 19 : 'J') == 'J') {
            z = false;
        } else {
            if (((double) view.getAlpha()) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                z = false;
            } else {
                int i = f463 + 3;
                f464 = i % 128;
                int i2 = i % 2;
                z = true;
            }
        }
        if (z) {
            if (!(!(aVar.f475 instanceof ViewGroup))) {
                int i3 = f464 + 39;
                f463 = i3 % 128;
                int i4 = i3 % 2;
                boolean zEquals = ViewGroup.class.equals(aVar.f475.getClass().getSuperclass());
                View view2 = aVar.f475;
                if ((Build.VERSION.SDK_INT >= 19 ? (char) 4 : (char) 1) == 4) {
                    int i5 = f464 + 113;
                    f463 = i5 % 128;
                    if (!(i5 % 2 != 0)) {
                        if ((view2.getBackground() != null ? 'Y' : '0') == 'Y') {
                            if (view2.getBackground().getAlpha() == 0) {
                                c2 = 31;
                            } else {
                                c2 = '\r';
                            }
                            z2 = c2 == 31;
                        }
                    } else {
                        if ((view2.getBackground() != null ? '=' : (char) 6) != 6) {
                            if (view2.getBackground().getAlpha() == 0) {
                                c2 = 31;
                            } else {
                                c2 = '\r';
                            }
                            if (c2 == 31) {
                            }
                        }
                    }
                }
                if ((zEquals ? 'F' : '[') != 'F') {
                    z3 = true;
                } else {
                    int i6 = f464 + 75;
                    f463 = i6 % 128;
                    if (!(i6 % 2 == 0)) {
                        if (z2) {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                    } else {
                        if ((z2 ? 'S' : '9') != '9') {
                            z3 = false;
                        } else {
                            z3 = true;
                        }
                    }
                }
                ViewGroup viewGroup = (ViewGroup) aVar.f475;
                int childCount = viewGroup.getChildCount();
                int i7 = 0;
                while (true) {
                    if ((i7 < childCount ? 'Z' : 'c') == 'c') {
                        break;
                    }
                    int i8 = bVar.f478 + 1;
                    bVar.f478 = i8;
                    if (i8 > 500) {
                        return;
                    }
                    m442(new a(viewGroup.getChildAt(i7), aVar), rect, bVar);
                    if (bVar.f476) {
                        return;
                    } else {
                        i7++;
                    }
                }
            } else {
                z3 = true;
            }
            if ((z3 ? '4' : (char) 16) != '4') {
                return;
            }
            Rect rect2 = aVar.f474;
            if ((rect2.setIntersect(rect, rect2) ? 'Z' : '0') != '0') {
                if (!(Build.VERSION.SDK_INT < 22)) {
                    Rect rect3 = new Rect(0, 0, 0, 0);
                    if (!m438(aVar.f475, rect3)) {
                        return;
                    }
                    Rect rect4 = aVar.f474;
                    if (!rect4.setIntersect(rect3, rect4)) {
                        return;
                    } else {
                        rect2 = rect4;
                    }
                }
                if (t.m423().f439) {
                    com.moat.analytics.mobile.cha.a.m274("VisibilityInfo", aVar.f475, String.format(Locale.ROOT, "Covered by %s-%s alpha=%f", aVar.f475.getClass().getName(), rect2.toString(), Float.valueOf(aVar.f475.getAlpha())));
                }
                bVar.f477.add(rect2);
                if ((rect2.contains(rect) ? '[' : (char) 25) != 25) {
                    int i9 = f464 + 39;
                    f463 = i9 % 128;
                    int i10 = i9 % 2;
                    bVar.f476 = true;
                }
            }
        }
    }

    static class b {

        /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
        int f478 = 0;

        /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
        final Set<Rect> f477 = new HashSet();

        /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
        boolean f476 = false;

        b() {
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0139  */
    /* JADX WARN: Code duplicated, block: B:101:0x013a A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:103:0x0140  */
    /* JADX WARN: Code duplicated, block: B:104:0x0142  */
    /* JADX WARN: Code duplicated, block: B:109:0x0152  */
    /* JADX WARN: Code duplicated, block: B:110:0x0154  */
    /* JADX WARN: Code duplicated, block: B:112:0x0157 A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:114:0x015e  */
    /* JADX WARN: Code duplicated, block: B:115:0x0160  */
    /* JADX WARN: Code duplicated, block: B:117:0x0164 A[Catch: Exception -> 0x0203, TRY_LEAVE, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:120:0x016a  */
    /* JADX WARN: Code duplicated, block: B:121:0x016d  */
    /* JADX WARN: Code duplicated, block: B:123:0x0171  */
    /* JADX WARN: Code duplicated, block: B:125:0x017d  */
    /* JADX WARN: Code duplicated, block: B:126:0x0180  */
    /* JADX WARN: Code duplicated, block: B:129:0x0186 A[Catch: Exception -> 0x0203, TRY_ENTER, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x0192  */
    /* JADX WARN: Code duplicated, block: B:132:0x0194  */
    /* JADX WARN: Code duplicated, block: B:134:0x0197  */
    /* JADX WARN: Code duplicated, block: B:135:0x0198 A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:141:0x01ab A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:143:0x01b1  */
    /* JADX WARN: Code duplicated, block: B:144:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:146:0x01b6 A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:151:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:152:0x01ca  */
    /* JADX WARN: Code duplicated, block: B:154:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:157:0x01d8 A[Catch: Exception -> 0x0203, TRY_LEAVE, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:159:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:160:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:164:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:165:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:175:0x005e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:176:0x0087 A[EDGE_INSN: B:176:0x0087->B:41:0x0087 BREAK  A[LOOP:0: B:4:0x000d->B:40:0x0080], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:178:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:179:0x0145 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:180:0x01ec A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:181:0x00cf A[EDGE_INSN: B:181:0x00cf->B:64:0x00cf BREAK  A[LOOP:2: B:85:0x0115->B:166:0x01f6], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:184:0x01f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:28:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0057  */
    /* JADX WARN: Code duplicated, block: B:31:0x005a  */
    /* JADX WARN: Code duplicated, block: B:35:0x006e A[Catch: Exception -> 0x0203, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:37:0x0079  */
    /* JADX WARN: Code duplicated, block: B:38:0x007c  */
    /* JADX WARN: Code duplicated, block: B:40:0x0080 A[Catch: Exception -> 0x0203, LOOP:0: B:4:0x000d->B:40:0x0080, LOOP_END, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x0117  */
    /* JADX WARN: Code duplicated, block: B:87:0x0119  */
    /* JADX WARN: Code duplicated, block: B:90:0x011d  */
    /* JADX WARN: Code duplicated, block: B:92:0x0129  */
    /* JADX WARN: Code duplicated, block: B:93:0x012b  */
    /* JADX WARN: Code duplicated, block: B:95:0x012e A[Catch: Exception -> 0x0203, TRY_ENTER, TryCatch #0 {Exception -> 0x0203, blocks: (B:3:0x0005, B:4:0x000d, B:17:0x0039, B:41:0x0087, B:53:0x00a6, B:54:0x00ae, B:59:0x00b9, B:70:0x00e5, B:83:0x0108, B:95:0x012e, B:107:0x014c, B:112:0x0157, B:117:0x0164, B:129:0x0186, B:157:0x01d8, B:135:0x0198, B:141:0x01ab, B:146:0x01b6, B:106:0x0145, B:101:0x013a, B:77:0x00f7, B:23:0x0045, B:34:0x0068, B:35:0x006e, B:40:0x0080), top: B:171:0x0005 }] */
    /* JADX WARN: Code duplicated, block: B:97:0x0134  */
    /* JADX WARN: Code duplicated, block: B:98:0x0136  */
    /* JADX WARN: Instruction removed from duplicated block: B:152:0x01ca, please report this as an issue */
    @VisibleForTesting
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static b m437(Rect rect, @NonNull View view) {
        int i;
        char c2;
        char c3;
        ViewGroup viewGroup;
        int childCount;
        int i2;
        boolean z;
        boolean z2;
        int i3;
        boolean z3;
        boolean z4;
        View childAt;
        boolean z5;
        char c4;
        boolean z6;
        char c5;
        boolean z7;
        char c6;
        char c7;
        boolean z8;
        char c8;
        int i4;
        char c9;
        boolean z9;
        boolean z10;
        boolean z11;
        b bVar = new b();
        try {
            ArrayDeque arrayDeque = new ArrayDeque();
            View view2 = view;
            int i5 = 0;
            while (true) {
                i = 3;
                if ((view2.getParent() == null ? '_' : 'I') == '_') {
                    int i6 = f464 + 81;
                    f463 = i6 % 128;
                    if (i6 % 2 == 0) {
                        if (!(view2 == view.getRootView())) {
                            break;
                        }
                        i5++;
                        if (i5 > 50) {
                            c2 = '5';
                        } else {
                            c2 = '\\';
                        }
                        if (c2 != '\\') {
                            int i7 = f463 + 123;
                            f464 = i7 % 128;
                            int i8 = i7 % 2;
                            com.moat.analytics.mobile.cha.a.m273(3, "VisibilityInfo", null, "Short-circuiting chain retrieval, reached max");
                            break;
                        }
                        arrayDeque.add(view2);
                        if (view2.getParent() instanceof View) {
                            c3 = 29;
                        } else {
                            c3 = '1';
                        }
                        if (c3 == '1') {
                            break;
                            break;
                        }
                        view2 = (View) view2.getParent();
                    } else {
                        if (view2 == view.getRootView()) {
                            i5++;
                            if (i5 > 50) {
                                c2 = '5';
                            } else {
                                c2 = '\\';
                            }
                            if (c2 != '\\') {
                                int i9 = f463 + 123;
                                f464 = i9 % 128;
                                int i10 = i9 % 2;
                                com.moat.analytics.mobile.cha.a.m273(3, "VisibilityInfo", null, "Short-circuiting chain retrieval, reached max");
                                break;
                            }
                            arrayDeque.add(view2);
                            if (view2.getParent() instanceof View) {
                                c3 = 29;
                            } else {
                                c3 = '1';
                            }
                            if (c3 == '1') {
                                break;
                                break;
                            }
                            view2 = (View) view2.getParent();
                        } else {
                            break;
                        }
                    }
                } else {
                    i5++;
                    if (i5 > 50) {
                        c2 = '5';
                    } else {
                        c2 = '\\';
                    }
                    if (c2 != '\\') {
                        int i11 = f463 + 123;
                        f464 = i11 % 128;
                        int i12 = i11 % 2;
                        com.moat.analytics.mobile.cha.a.m273(3, "VisibilityInfo", null, "Short-circuiting chain retrieval, reached max");
                        break;
                    }
                    arrayDeque.add(view2);
                    if (view2.getParent() instanceof View) {
                        c3 = 29;
                    } else {
                        c3 = '1';
                    }
                    if (c3 == '1') {
                        break;
                    }
                    view2 = (View) view2.getParent();
                }
            }
            if ((arrayDeque.isEmpty() ? 'I' : '@') == '@') {
                com.moat.analytics.mobile.cha.a.m274("VisibilityInfo", view, "starting covering rect search");
                a aVar = null;
                loop1: while (true) {
                    if (arrayDeque.isEmpty()) {
                        break;
                    }
                    View view3 = (View) arrayDeque.pollLast();
                    a aVar2 = new a(view3, aVar);
                    if (!(view3.getParent() == null)) {
                        int i13 = f463 + 19;
                        f464 = i13 % 128;
                        if (!(i13 % 2 != 0)) {
                            if ((view3.getParent() instanceof ViewGroup ? 'Z' : 'G') != 'G') {
                                viewGroup = (ViewGroup) view3.getParent();
                                childCount = viewGroup.getChildCount();
                                i2 = 0;
                                z = false;
                                while (true) {
                                    if (i2 < childCount) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (!z2) {
                                        break;
                                    }
                                    i3 = f463 + 25;
                                    f464 = i3 % 128;
                                    if (i3 % 2 == 0) {
                                        z3 = false;
                                    } else {
                                        z3 = true;
                                    }
                                    if (!z3) {
                                        if (bVar.f478 >= 500) {
                                            z11 = true;
                                        } else {
                                            z11 = false;
                                        }
                                        if (!z11) {
                                            com.moat.analytics.mobile.cha.a.m273(i, "VisibilityInfo", null, "Short-circuiting cover retrieval, reached max");
                                            break loop1;
                                        }
                                        childAt = viewGroup.getChildAt(i2);
                                        if (childAt == view3) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                        if (!z5) {
                                            bVar.f478++;
                                            if (z) {
                                                c4 = 0;
                                            } else {
                                                c4 = '1';
                                            }
                                            if (c4 != 0) {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    z6 = false;
                                                } else {
                                                    z6 = true;
                                                }
                                                if (!z6) {
                                                    if (childAt.getZ() >= view3.getZ()) {
                                                        c5 = '5';
                                                    } else {
                                                        c5 = '^';
                                                    }
                                                    z7 = c5 == '5';
                                                }
                                            } else {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    c8 = 'M';
                                                } else {
                                                    c8 = 19;
                                                }
                                                if (c8 != 19) {
                                                    i4 = f463 + 115;
                                                    f464 = i4 % 128;
                                                    if (i4 % 2 == 0) {
                                                        c9 = '$';
                                                    } else {
                                                        c9 = 'H';
                                                    }
                                                    if (c9 != 'H') {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z10 = false;
                                                        } else {
                                                            z10 = true;
                                                        }
                                                        if (z10) {
                                                        }
                                                    } else {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z9 = false;
                                                        } else {
                                                            z9 = true;
                                                        }
                                                        if (z9) {
                                                        }
                                                    }
                                                }
                                            }
                                            if (z7) {
                                                c7 = 16;
                                                c6 = 18;
                                            } else {
                                                c6 = 18;
                                                c7 = 18;
                                            }
                                            if (c7 != c6) {
                                                m442(new a(childAt, aVar), rect, bVar);
                                                if (bVar.f476) {
                                                    z8 = false;
                                                } else {
                                                    z8 = true;
                                                }
                                                if (z8) {
                                                    return bVar;
                                                }
                                            }
                                        } else {
                                            z = true;
                                        }
                                        i2++;
                                        i = 3;
                                    } else {
                                        if (bVar.f478 >= 500) {
                                            z4 = false;
                                        } else {
                                            z4 = true;
                                        }
                                        if (!z4) {
                                            com.moat.analytics.mobile.cha.a.m273(i, "VisibilityInfo", null, "Short-circuiting cover retrieval, reached max");
                                            break loop1;
                                        }
                                        childAt = viewGroup.getChildAt(i2);
                                        if (childAt == view3) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                        if (!z5) {
                                            bVar.f478++;
                                            if (z) {
                                                c4 = 0;
                                            } else {
                                                c4 = '1';
                                            }
                                            if (c4 != 0) {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    z6 = false;
                                                } else {
                                                    z6 = true;
                                                }
                                                if (!z6) {
                                                    if (childAt.getZ() >= view3.getZ()) {
                                                        c5 = '5';
                                                    } else {
                                                        c5 = '^';
                                                    }
                                                    if (c5 == '5') {
                                                    }
                                                }
                                            } else {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    c8 = 'M';
                                                } else {
                                                    c8 = 19;
                                                }
                                                if (c8 != 19) {
                                                    i4 = f463 + 115;
                                                    f464 = i4 % 128;
                                                    if (i4 % 2 == 0) {
                                                        c9 = '$';
                                                    } else {
                                                        c9 = 'H';
                                                    }
                                                    if (c9 != 'H') {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z10 = false;
                                                        } else {
                                                            z10 = true;
                                                        }
                                                        if (z10) {
                                                        }
                                                    } else {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z9 = false;
                                                        } else {
                                                            z9 = true;
                                                        }
                                                        if (z9) {
                                                        }
                                                    }
                                                }
                                            }
                                            if (z7) {
                                                c7 = 16;
                                                c6 = 18;
                                            } else {
                                                c6 = 18;
                                                c7 = 18;
                                            }
                                            if (c7 != c6) {
                                                m442(new a(childAt, aVar), rect, bVar);
                                                if (bVar.f476) {
                                                    z8 = false;
                                                } else {
                                                    z8 = true;
                                                }
                                                if (z8) {
                                                    return bVar;
                                                }
                                            }
                                        } else {
                                            z = true;
                                        }
                                        i2++;
                                        i = 3;
                                    }
                                }
                            }
                        } else {
                            if ((view3.getParent() instanceof ViewGroup ? (char) 18 : 'S') != 'S') {
                                viewGroup = (ViewGroup) view3.getParent();
                                childCount = viewGroup.getChildCount();
                                i2 = 0;
                                z = false;
                                while (true) {
                                    if (i2 < childCount) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (!z2) {
                                        break;
                                        break;
                                    }
                                    i3 = f463 + 25;
                                    f464 = i3 % 128;
                                    if (i3 % 2 == 0) {
                                        z3 = false;
                                    } else {
                                        z3 = true;
                                    }
                                    if (!z3) {
                                        if (bVar.f478 >= 500) {
                                            z11 = true;
                                        } else {
                                            z11 = false;
                                        }
                                        if (!z11) {
                                            com.moat.analytics.mobile.cha.a.m273(i, "VisibilityInfo", null, "Short-circuiting cover retrieval, reached max");
                                            break loop1;
                                        }
                                        childAt = viewGroup.getChildAt(i2);
                                        if (childAt == view3) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                        if (!z5) {
                                            bVar.f478++;
                                            if (z) {
                                                c4 = 0;
                                            } else {
                                                c4 = '1';
                                            }
                                            if (c4 != 0) {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    z6 = false;
                                                } else {
                                                    z6 = true;
                                                }
                                                if (!z6) {
                                                    if (childAt.getZ() >= view3.getZ()) {
                                                        c5 = '5';
                                                    } else {
                                                        c5 = '^';
                                                    }
                                                    if (c5 == '5') {
                                                    }
                                                }
                                            } else {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    c8 = 'M';
                                                } else {
                                                    c8 = 19;
                                                }
                                                if (c8 != 19) {
                                                    i4 = f463 + 115;
                                                    f464 = i4 % 128;
                                                    if (i4 % 2 == 0) {
                                                        c9 = '$';
                                                    } else {
                                                        c9 = 'H';
                                                    }
                                                    if (c9 != 'H') {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z10 = false;
                                                        } else {
                                                            z10 = true;
                                                        }
                                                        if (z10) {
                                                        }
                                                    } else {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z9 = false;
                                                        } else {
                                                            z9 = true;
                                                        }
                                                        if (z9) {
                                                        }
                                                    }
                                                }
                                            }
                                            if (z7) {
                                                c7 = 16;
                                                c6 = 18;
                                            } else {
                                                c6 = 18;
                                                c7 = 18;
                                            }
                                            if (c7 != c6) {
                                                m442(new a(childAt, aVar), rect, bVar);
                                                if (bVar.f476) {
                                                    z8 = false;
                                                } else {
                                                    z8 = true;
                                                }
                                                if (z8) {
                                                    return bVar;
                                                }
                                            }
                                        } else {
                                            z = true;
                                        }
                                        i2++;
                                        i = 3;
                                    } else {
                                        if (bVar.f478 >= 500) {
                                            z4 = false;
                                        } else {
                                            z4 = true;
                                        }
                                        if (!z4) {
                                            com.moat.analytics.mobile.cha.a.m273(i, "VisibilityInfo", null, "Short-circuiting cover retrieval, reached max");
                                            break loop1;
                                        }
                                        childAt = viewGroup.getChildAt(i2);
                                        if (childAt == view3) {
                                            z5 = true;
                                        } else {
                                            z5 = false;
                                        }
                                        if (!z5) {
                                            bVar.f478++;
                                            if (z) {
                                                c4 = 0;
                                            } else {
                                                c4 = '1';
                                            }
                                            if (c4 != 0) {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    z6 = false;
                                                } else {
                                                    z6 = true;
                                                }
                                                if (!z6) {
                                                    if (childAt.getZ() >= view3.getZ()) {
                                                        c5 = '5';
                                                    } else {
                                                        c5 = '^';
                                                    }
                                                    if (c5 == '5') {
                                                    }
                                                }
                                            } else {
                                                if (Build.VERSION.SDK_INT >= 21) {
                                                    c8 = 'M';
                                                } else {
                                                    c8 = 19;
                                                }
                                                if (c8 != 19) {
                                                    i4 = f463 + 115;
                                                    f464 = i4 % 128;
                                                    if (i4 % 2 == 0) {
                                                        c9 = '$';
                                                    } else {
                                                        c9 = 'H';
                                                    }
                                                    if (c9 != 'H') {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z10 = false;
                                                        } else {
                                                            z10 = true;
                                                        }
                                                        if (z10) {
                                                        }
                                                    } else {
                                                        if (childAt.getZ() > view3.getZ()) {
                                                            z9 = false;
                                                        } else {
                                                            z9 = true;
                                                        }
                                                        if (z9) {
                                                        }
                                                    }
                                                }
                                            }
                                            if (z7) {
                                                c7 = 16;
                                                c6 = 18;
                                            } else {
                                                c6 = 18;
                                                c7 = 18;
                                            }
                                            if (c7 != c6) {
                                                m442(new a(childAt, aVar), rect, bVar);
                                                if (bVar.f476) {
                                                    z8 = false;
                                                } else {
                                                    z8 = true;
                                                }
                                                if (z8) {
                                                    return bVar;
                                                }
                                            }
                                        } else {
                                            z = true;
                                        }
                                        i2++;
                                        i = 3;
                                    }
                                }
                            }
                        }
                    }
                    aVar = aVar2;
                    i = 3;
                }
            } else {
                int i14 = f464 + 125;
                f463 = i14 % 128;
                if (i14 % 2 == 0) {
                }
                return bVar;
            }
        } catch (Exception e) {
            o.m389(e);
        }
        return bVar;
    }

    @VisibleForTesting
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static int m434(Rect rect, Set<Rect> set) {
        Object next;
        if (set.isEmpty()) {
            return 0;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(set);
        Collections.sort(arrayList, new Comparator<Rect>() { // from class: com.moat.analytics.mobile.cha.u.1
            @Override // java.util.Comparator
            public final /* synthetic */ int compare(Rect rect2, Rect rect3) {
                return Integer.valueOf(rect2.top).compareTo(Integer.valueOf(rect3.top));
            }
        });
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (true) {
            if (!(it.hasNext())) {
                break;
            }
            int i = f463 + 59;
            f464 = i % 128;
            if (!(i % 2 == 0)) {
                next = it.next();
            } else {
                next = it.next();
            }
            Rect rect2 = (Rect) next;
            arrayList2.add(Integer.valueOf(rect2.left));
            arrayList2.add(Integer.valueOf(rect2.right));
        }
        Collections.sort(arrayList2);
        int i2 = 0;
        int iWidth = 0;
        while (true) {
            if ((i2 < arrayList2.size() - 1 ? '&' : 'U') == 'U') {
                return iWidth;
            }
            int i3 = i2 + 1;
            if (!(((Integer) arrayList2.get(i2)).equals(arrayList2.get(i3)))) {
                Rect rect3 = new Rect(((Integer) arrayList2.get(i2)).intValue(), rect.top, ((Integer) arrayList2.get(i3)).intValue(), rect.bottom);
                int i4 = rect.top;
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if ((it2.hasNext() ? (char) 25 : '\t') == '\t') {
                        break;
                    }
                    int i5 = f464 + 23;
                    f463 = i5 % 128;
                    int i6 = i5 % 2;
                    Rect rect4 = (Rect) it2.next();
                    if (Rect.intersects(rect4, rect3)) {
                        if ((rect4.bottom > i4 ? (char) 16 : 'A') == 16) {
                            iWidth += rect3.width() * (rect4.bottom - Math.max(i4, rect4.top));
                            i4 = rect4.bottom;
                        }
                        if (rect4.bottom == rect3.bottom) {
                            break;
                        }
                    }
                }
            }
            i2 = i3;
        }
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static Map<String, String> m440(Rect rect) {
        HashMap map = new HashMap();
        map.put("x", String.valueOf(rect.left));
        map.put("y", String.valueOf(rect.top));
        map.put(com.my.target.i.WIDTH, String.valueOf(rect.right - rect.left));
        map.put(com.my.target.i.HEIGHT, String.valueOf(rect.bottom - rect.top));
        return map;
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static Rect m435(Rect rect, DisplayMetrics displayMetrics) {
        float f = displayMetrics.density;
        if (!(f != 0.0f)) {
            return rect;
        }
        return new Rect(Math.round(rect.left / f), Math.round(rect.top / f), Math.round(rect.right / f), Math.round(rect.bottom / f));
    }

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static boolean m438(View view, Rect rect) {
        if ((view.getGlobalVisibleRect(rect) ? 'Z' : (char) 1) != 'Z') {
            return false;
        }
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationInWindow(iArr);
        int[] iArr2 = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr2);
        rect.offset(iArr2[0] - iArr[0], iArr2[1] - iArr[1]);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    public static Rect m441(View view) {
        int[] iArr = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, view.getWidth() + i, view.getHeight() + i2);
    }
}
