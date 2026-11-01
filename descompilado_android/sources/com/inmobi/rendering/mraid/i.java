package com.inmobi.rendering.mraid;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import com.amazon.device.ads.WebRequest;
import com.google.android.gms.plus.PlusShare;
import com.inmobi.rendering.RenderView;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SystemTasksProcessor.java */
/* JADX INFO: loaded from: classes2.dex */
public class i {
    private static final String b = "i";
    public RenderView a;
    private a c;

    public i(RenderView renderView) {
        this.a = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.c = new a(handlerThread.getLooper(), renderView);
    }

    public static boolean a() {
        try {
            PlusShare.class.getName();
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0085  */
    /* JADX WARN: Code duplicated, block: B:19:0x009c  */
    public static void a(Context context, int i, String str, String str2, String str3) {
        String str4;
        String str5 = str + " " + str2 + " " + str3;
        try {
            if (i == 1) {
                str4 = "https://www.facebook.com/dialog/feed?app_id=181821551957328&link=" + URLEncoder.encode(str2, "UTF-8") + "&picture=" + URLEncoder.encode(str3, "UTF-8") + "&name=&description=" + URLEncoder.encode(str, "UTF-8") + "&redirect_uri=" + URLEncoder.encode(str2, "UTF-8");
            } else {
                if (i != 2) {
                    if (i != 3) {
                        str4 = null;
                    } else {
                        str4 = "http://twitter.com/home?status=" + URLEncoder.encode(str5, "UTF-8");
                    }
                    if (str4 != null) {
                        Intent intent = new Intent("android.intent.action.VIEW");
                        intent.setData(Uri.parse(str4));
                        try {
                            com.inmobi.commons.a.a.a(context, intent);
                        } catch (ActivityNotFoundException e) {
                            e.getMessage();
                            return;
                        }
                    }
                    Intent intent2 = new Intent();
                    intent2.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    intent2.putExtra("android.intent.extra.TEXT", str5);
                    try {
                        com.inmobi.commons.a.a.a(context, intent2);
                    } catch (ActivityNotFoundException e2) {
                        e2.getMessage();
                        return;
                    }
                }
                str4 = "https://m.google.com/app/plus/x/?v=compose&content=" + URLEncoder.encode(str5, "UTF-8");
            }
            if (str4 != null) {
                Intent intent3 = new Intent("android.intent.action.VIEW");
                intent3.setData(Uri.parse(str4));
                com.inmobi.commons.a.a.a(context, intent3);
            } else {
                Intent intent4 = new Intent();
                intent4.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                intent4.putExtra("android.intent.extra.TEXT", str5);
                com.inmobi.commons.a.a.a(context, intent4);
            }
        } catch (UnsupportedEncodingException unused) {
        }
    }

    public final void a(Context context) {
        a aVar = this.c;
        if (aVar == null || !aVar.hasMessages(1)) {
            return;
        }
        this.c.removeMessages(1);
        ((Vibrator) context.getSystemService("vibrator")).cancel();
    }

    public static String a(String str) {
        String strA;
        String strA2;
        String strA3;
        String strA4;
        String strA5;
        StringBuilder sb = new StringBuilder();
        if (str.length() != 0) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String strOptString = jSONObject.optString("frequency");
                if (strOptString == null || "".equals(strOptString)) {
                    return "";
                }
                if (!"daily".equals(strOptString) && !"weekly".equals(strOptString) && !"monthly".equals(strOptString) && !"yearly".equals(strOptString)) {
                    return "";
                }
                sb.append("freq=");
                sb.append(strOptString);
                sb.append(";");
                String strOptString2 = jSONObject.optString("interval");
                if (strOptString2 != null && !"".equals(strOptString2)) {
                    sb.append("interval=");
                    sb.append(Integer.parseInt(strOptString2));
                    sb.append(";");
                }
                String strA6 = com.inmobi.rendering.mraid.a.a(jSONObject.optString("expires"));
                if (strA6 != null) {
                    sb.append("until=");
                    sb.append(strA6.replace("+", "Z+").replace("-", "Z-"));
                    sb.append(";");
                }
                if (strOptString.equals("weekly") && (strA5 = com.inmobi.rendering.mraid.a.a(jSONObject.optJSONArray("daysInWeek"))) != null) {
                    sb.append("byday=");
                    sb.append(strA5);
                    sb.append(";");
                }
                if (strOptString.equals("monthly") && (strA4 = com.inmobi.rendering.mraid.a.a(jSONObject.optJSONArray("daysInMonth"), -31, 31)) != null) {
                    sb.append("bymonthday=");
                    sb.append(strA4);
                    sb.append(";");
                }
                if (strOptString.equals("yearly") && (strA3 = com.inmobi.rendering.mraid.a.a(jSONObject.optJSONArray("daysInYear"), -366, 366)) != null) {
                    sb.append("byyearday=");
                    sb.append(strA3);
                    sb.append(";");
                }
                if (strOptString.equals("monthly") && (strA2 = com.inmobi.rendering.mraid.a.a(jSONObject.optJSONArray("weeksInMonth"), -4, 4)) != null) {
                    sb.append("byweekno=");
                    sb.append(strA2);
                    sb.append(";");
                }
                if (strOptString.equals("yearly") && (strA = com.inmobi.rendering.mraid.a.a(jSONObject.optJSONArray("monthsInYear"), 1, 12)) != null) {
                    sb.append("bymonth=");
                    sb.append(strA);
                    sb.append(";");
                }
                return sb.toString();
            } catch (JSONException e) {
                new StringBuilder("Error Parsing recurrence string").append(e.toString());
            }
        }
        return "";
    }

    /* JADX INFO: compiled from: SystemTasksProcessor.java */
    static final class a extends Handler {
        private static final String a = "a";
        private WeakReference<RenderView> b;

        public a(Looper looper, RenderView renderView) {
            super(looper);
            this.b = new WeakReference<>(renderView);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            String str = (String) message.obj;
            RenderView renderView = this.b.get();
            if (renderView != null) {
                renderView.a(str, "broadcastEvent('vibrateComplete');");
            }
        }
    }
}
