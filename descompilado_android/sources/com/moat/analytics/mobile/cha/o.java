package com.moat.analytics.mobile.cha;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.net.URLEncoder;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
final class o extends Exception {

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    private static Long f402;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static final Long f401 = Long.valueOf(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static Exception f400 = null;

    o(String str) {
        super(str);
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static String m388(String str, Exception exc) {
        if (exc instanceof o) {
            return str + " failed: " + exc.getMessage();
        }
        return str + " failed unexpectedly";
    }

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    static void m389(Exception exc) {
        if (t.m423().f442) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        } else {
            m387(exc);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static void m387(Exception exc) {
        String str;
        String strM413;
        String string = "";
        try {
            if (t.m423().f441 == t.a.f452) {
                int i = t.m423().f444;
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
                sb2.append(exc instanceof o ? 1 : 0);
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
                    sb.append("&zMoatMMAKv=35d482907bc2811c2e46b96f16eb5f9fe00185f3");
                    str = BuildConfig.JMMAK_VERSION;
                    try {
                        r.e eVarM403 = r.m403();
                        sb.append("&zMoatMMAKan=" + eVarM403.m414());
                        strM413 = eVarM403.m413();
                        try {
                            string = Integer.toString(Build.VERSION.SDK_INT);
                        } catch (Exception unused2) {
                        }
                    } catch (Exception unused3) {
                        strM413 = "";
                    }
                } catch (Exception unused4) {
                    str = "";
                    strM413 = str;
                }
                sb.append("&d=Android:" + BuildConfig.NAMESPACE + ":" + strM413 + ":-");
                StringBuilder sb4 = new StringBuilder("&bo=");
                sb4.append(str);
                sb.append(sb4.toString());
                sb.append("&bd=" + string);
                Long lValueOf = Long.valueOf(System.currentTimeMillis());
                sb.append("&t=" + lValueOf);
                sb.append("&de=" + String.format(Locale.ROOT, "%.0f", Double.valueOf(Math.floor(Math.random() * Math.pow(10.0d, 12.0d)))));
                sb.append("&cs=0");
                if (f402 == null || lValueOf.longValue() - f402.longValue() > f401.longValue()) {
                    new Thread() { // from class: com.moat.analytics.mobile.cha.m.2

                        /* JADX INFO: renamed from: ˎ */
                        private /* synthetic */ String f389;

                        AnonymousClass2() {
                            str = str;
                        }

                        @Override // java.lang.Thread, java.lang.Runnable
                        public final void run() {
                            try {
                                m.m367(str);
                            } catch (Exception unused5) {
                            }
                        }
                    }.start();
                    f402 = lValueOf;
                    return;
                }
                return;
            }
            f400 = exc;
        } catch (Exception unused5) {
        }
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static void m390() {
        Exception exc = f400;
        if (exc != null) {
            m387(exc);
            f400 = null;
        }
    }
}
