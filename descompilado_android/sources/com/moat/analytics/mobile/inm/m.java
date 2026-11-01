package com.moat.analytics.mobile.inm;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.net.URLEncoder;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class m extends Exception {
    private static Long b;
    private static final Long a = Long.valueOf(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);
    private static Exception c = null;

    m(String str) {
        super(str);
    }

    static String a(String str, Exception exc) {
        if (!(exc instanceof m)) {
            return str + " failed unexpectedly";
        }
        return str + " failed: " + exc.getMessage();
    }

    static void a() {
        Exception exc = c;
        if (exc != null) {
            b(exc);
            c = null;
        }
    }

    static void a(Exception exc) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        } else {
            b(exc);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    private static void b(Exception exc) {
        String str;
        String strB;
        String string = "";
        try {
            if (w.a().a != w.d.ON) {
                c = exc;
                return;
            }
            int i = w.a().e;
            if (i == 0) {
                return;
            }
            if (i < 100) {
                double d = i;
                Double.isNaN(d);
                if (d / 100.0d < Math.random()) {
                    return;
                }
            }
            StringBuilder sb = new StringBuilder("https://px.moatads.com/pixel.gif?e=0&i=MOATSDK1&ac=1");
            StringBuilder sb2 = new StringBuilder("&zt=");
            sb2.append(exc instanceof m ? 1 : 0);
            sb.append(sb2.toString());
            sb.append("&zr=" + i);
            try {
                StringBuilder sb3 = new StringBuilder("&zm=");
                sb3.append(exc.getMessage() == null ? "null" : URLEncoder.encode(Base64.encodeToString(exc.getMessage().getBytes("UTF-8"), 0), "UTF-8"));
                sb.append(sb3.toString());
                sb.append("&k=" + URLEncoder.encode(Base64.encodeToString(Log.getStackTraceString(exc).getBytes("UTF-8"), 0), "UTF-8"));
            } catch (Exception unused) {
            }
            try {
                sb.append("&zMoatMMAKv=c334ae83accfebb8da23104450c896463c9cfab7");
                str = "2.5.0";
                try {
                    s.a aVarD = s.d();
                    sb.append("&zMoatMMAKan=" + aVarD.a());
                    strB = aVarD.b();
                    try {
                        string = Integer.toString(Build.VERSION.SDK_INT);
                    } catch (Exception unused2) {
                    }
                } catch (Exception unused3) {
                    strB = "";
                }
            } catch (Exception unused4) {
                str = "";
                strB = str;
            }
            sb.append("&d=Android:INM:" + strB + ":-");
            StringBuilder sb4 = new StringBuilder("&bo=");
            sb4.append(str);
            sb.append(sb4.toString());
            sb.append("&bd=" + string);
            Long lValueOf = Long.valueOf(System.currentTimeMillis());
            sb.append("&t=" + lValueOf);
            sb.append("&de=" + String.format(Locale.ROOT, "%.0f", Double.valueOf(Math.floor(Math.random() * Math.pow(10.0d, 12.0d)))));
            sb.append("&cs=0");
            if (b == null || lValueOf.longValue() - b.longValue() > a.longValue()) {
                q.b(sb.toString());
                b = lValueOf;
            }
        } catch (Exception unused5) {
        }
    }
}
