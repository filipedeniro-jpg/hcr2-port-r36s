package com.moat.analytics.mobile.iro;

import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import java.net.URLEncoder;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
final class o extends Exception {

    /* JADX INFO: renamed from: ˎ, reason: contains not printable characters */
    private static Long f624;

    /* JADX INFO: renamed from: ˏ, reason: contains not printable characters */
    private static final Long f625 = Long.valueOf(ChunkedTrackBlacklistUtil.DEFAULT_TRACK_BLACKLIST_MS);

    /* JADX INFO: renamed from: ˋ, reason: contains not printable characters */
    private static Exception f623 = null;

    o(String str) {
        super(str);
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static String m568(String str, Exception exc) {
        if (exc instanceof o) {
            return str + " failed: " + exc.getMessage();
        }
        return str + " failed unexpectedly";
    }

    /* JADX INFO: renamed from: ॱ, reason: contains not printable characters */
    static void m569(Exception exc) {
        if (t.m598().f662) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        } else {
            m567(exc);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 2 */
    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    private static void m567(Exception exc) {
        String str;
        String strM583;
        String string = "";
        try {
            if (t.m598().f660 == t.c.f677) {
                int i = t.m598().f658;
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
                    sb.append("&zMoatMMAKv=2bc3418b93f01686fcbd1ebebcc04694651821b2");
                    str = "2.4.0";
                    try {
                        p.b bVarM576 = p.m576();
                        sb.append("&zMoatMMAKan=" + bVarM576.m584());
                        strM583 = bVarM576.m583();
                        try {
                            string = Integer.toString(Build.VERSION.SDK_INT);
                        } catch (Exception unused2) {
                        }
                    } catch (Exception unused3) {
                        strM583 = "";
                    }
                } catch (Exception unused4) {
                    str = "";
                    strM583 = str;
                }
                sb.append("&d=Android:IRO:" + strM583 + ":-");
                StringBuilder sb4 = new StringBuilder("&bo=");
                sb4.append(str);
                sb.append(sb4.toString());
                sb.append("&bd=" + string);
                Long lValueOf = Long.valueOf(System.currentTimeMillis());
                sb.append("&t=" + lValueOf);
                sb.append("&de=" + String.format(Locale.ROOT, "%.0f", Double.valueOf(Math.floor(Math.random() * Math.pow(10.0d, 12.0d)))));
                sb.append("&cs=0");
                if (f624 == null || lValueOf.longValue() - f624.longValue() > f625.longValue()) {
                    new Thread() { // from class: com.moat.analytics.mobile.iro.l.1

                        /* JADX INFO: renamed from: ˎ */
                        private /* synthetic */ String f613;

                        AnonymousClass1() {
                            str = str;
                        }

                        @Override // java.lang.Thread, java.lang.Runnable
                        public final void run() {
                            try {
                                l.m565(str);
                            } catch (Exception unused5) {
                            }
                        }
                    }.start();
                    f624 = lValueOf;
                    return;
                }
                return;
            }
            f623 = exc;
        } catch (Exception unused5) {
        }
    }

    /* JADX INFO: renamed from: ˊ, reason: contains not printable characters */
    static void m566() {
        Exception exc = f623;
        if (exc != null) {
            m567(exc);
            f623 = null;
        }
    }
}
