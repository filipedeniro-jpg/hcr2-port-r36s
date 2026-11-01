package com.inmobi.commons.core.utilities.b;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: DisplayInfo.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static final String a = "c";

    public static int a(int i) {
        return Math.round(i * a().c);
    }

    public static d a() {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return new d(0, 0, 2.0f);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) contextB.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.density;
        return new d(Math.round(displayMetrics.widthPixels / f), Math.round(displayMetrics.heightPixels / f), f);
    }

    public static int b(int i) {
        return Math.round(i / a().c);
    }

    public static int b() {
        Context contextB = com.inmobi.commons.a.a.b();
        if (contextB == null) {
            return 1;
        }
        int rotation = ((WindowManager) contextB.getSystemService("window")).getDefaultDisplay().getRotation();
        int i = contextB.getResources().getConfiguration().orientation;
        if (i == 1) {
            return (rotation == 1 || rotation == 2) ? 2 : 1;
        }
        if (i != 2) {
            return 1;
        }
        return (rotation == 0 || rotation == 1) ? 3 : 4;
    }

    public static Map<String, String> c() {
        String str;
        HashMap map = new HashMap();
        try {
            map.put("d-device-screen-density", String.valueOf(a().c));
            d dVarA = a();
            map.put("d-device-screen-size", dVarA.a + "X" + dVarA.b);
            Context contextB = com.inmobi.commons.a.a.b();
            if (contextB == null) {
                str = "0x0";
            } else {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                ((WindowManager) contextB.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
                str = displayMetrics.widthPixels + "x" + displayMetrics.heightPixels;
            }
            map.put("d-density-dependent-screen-size", str);
            map.put("d-orientation", String.valueOf(b()));
            map.put("d-textsize", String.valueOf(new TextView(com.inmobi.commons.a.a.b()).getTextSize()));
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in getting display info; ").append(e.getMessage());
        }
        return map;
    }
}
