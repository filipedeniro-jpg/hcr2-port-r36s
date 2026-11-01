package net.nend.android.internal.c.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.VisibleForTesting;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import java.util.ArrayList;
import net.nend.android.internal.utilities.j;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EventTransmitter.java */
/* JADX INFO: loaded from: classes2.dex */
public class c {
    private static BroadcastReceiver a;

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            throw new NullPointerException("response body is empty.");
        }
        return new String(bArr);
    }

    private static JSONObject a(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", str);
        jSONObject.put("clientTime", System.currentTimeMillis());
        return jSONObject;
    }

    private static JSONObject b(String str) {
        try {
            return a(str);
        } catch (JSONException unused) {
            return null;
        }
    }

    @VisibleForTesting
    static JSONObject a(String str, int i, int i2) {
        try {
            JSONObject jSONObjectA = a(str);
            jSONObjectA.put("width", i);
            jSONObjectA.put("height", i2);
            return jSONObjectA;
        } catch (JSONException unused) {
            return null;
        }
    }

    @VisibleForTesting
    static JSONObject a(String str, boolean z) {
        try {
            JSONObject jSONObjectA = a(str);
            if (z) {
                jSONObjectA.put("isFullScreen", 1);
            }
            return jSONObjectA;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static JSONObject a(String str, boolean z, boolean z2, long j) {
        try {
            JSONObject jSONObjectA = a(str);
            if (z) {
                jSONObjectA.put("type", 1);
            } else if (z2) {
                jSONObjectA.put("type", 2);
            } else {
                jSONObjectA.put("type", 3);
            }
            if (j >= 0) {
                jSONObjectA.put("timeSpentViewing", j);
            }
            return jSONObjectA;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static JSONObject a(String str, long j) {
        try {
            JSONObject jSONObjectA = a(str);
            if (j >= 0) {
                jSONObjectA.put("timeSpentViewing", j);
            }
            return jSONObjectA;
        } catch (JSONException unused) {
            return null;
        }
    }

    private static void a(Context context, net.nend.android.internal.utilities.g.b<String> bVar, JSONObject jSONObject) {
        try {
            net.nend.android.internal.utilities.video.b.a(context, new JSONObject().put(IronSourceConstants.REQUEST_URL, bVar.getRequestUrl()).put("postJsonObj", jSONObject.toString()));
        } catch (JSONException unused) {
        }
        if (a == null) {
            a = new BroadcastReceiver() { // from class: net.nend.android.internal.c.f.c.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    if (net.nend.android.internal.c.g.a(context2)) {
                        c.b(context2);
                    }
                }
            };
            net.nend.android.internal.c.g.a(context, a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context) {
        ArrayList<JSONObject> arrayListA = net.nend.android.internal.utilities.video.b.a(context);
        if (!arrayListA.isEmpty()) {
            for (JSONObject jSONObject : arrayListA) {
                try {
                    a(new a(jSONObject.getString(IronSourceConstants.REQUEST_URL)), new JSONObject(jSONObject.getString("postJsonObj")));
                } catch (JSONException unused) {
                }
            }
            net.nend.android.internal.utilities.video.b.b(context);
        }
        BroadcastReceiver broadcastReceiver = a;
        if (broadcastReceiver != null) {
            try {
                try {
                    net.nend.android.internal.c.g.b(context, broadcastReceiver);
                } catch (IllegalArgumentException unused2) {
                    j.a("NetworkChecker receiver is already unregistered");
                }
            } finally {
                a = null;
            }
        }
    }

    private static void a(net.nend.android.internal.utilities.g.b<String> bVar, JSONObject jSONObject) {
        net.nend.android.internal.utilities.g.a().a(net.nend.android.internal.utilities.g.f.a(bVar, jSONObject), new net.nend.android.internal.utilities.g.a<String>() { // from class: net.nend.android.internal.c.f.c.2
            @Override // net.nend.android.internal.utilities.g.a
            public void a(String str, Exception exc) {
                if (str != null) {
                    j.a(str);
                } else if (exc != null) {
                    j.b("Failed to send video event. ", exc);
                }
            }
        });
    }

    private static void a(Context context, String str, JSONObject jSONObject) {
        a aVar = new a(str);
        if (net.nend.android.internal.c.g.a(context)) {
            b(context);
            a(aVar, jSONObject);
        } else {
            a(context, aVar, jSONObject);
        }
    }

    public static void a(Context context, String str) {
        a(context, b.d, b(str));
    }

    public static void a(Context context, String str, int i, int i2) {
        a(context, b.e, a(str, i, i2));
        j.a("width: " + i + ", height: " + i2);
    }

    public static void a(Context context, String str, boolean z, boolean z2, int i) {
        a(context, b.f, a(str, z, z2, i));
        j.a("isCompletion: " + z + ", isSkipped: " + z2 + ", timeSpent: " + i);
    }

    public static void b(Context context, String str) {
        a(context, b.g, b(str));
    }

    public static void c(Context context, String str) {
        a(context, b.i, b(str));
    }

    public static void a(Context context, String str, boolean z) {
        a(context, b.i, a(str, z));
        j.a("isFullScreen: " + z);
    }

    public static void a(Context context, String str, net.nend.android.internal.utilities.video.a aVar) {
        if (aVar.d()) {
            aVar.b();
            j.a("End card display time = " + aVar.c());
            a(context, b.h, a(str, aVar.c()));
        }
    }

    public static <AD extends net.nend.android.internal.b.a> boolean a(AD ad, boolean z, int i, boolean z2) {
        return !z && ((z2 && ad.e == -1) || (ad.e > -1 && net.nend.android.internal.utilities.video.b.a(i) > ad.e));
    }

    /* JADX INFO: compiled from: EventTransmitter.java */
    private static class a implements net.nend.android.internal.utilities.g.b<String> {
        private final String a;

        a(String str) {
            this.a = str;
        }

        @Override // net.nend.android.internal.utilities.g.b
        public String getRequestUrl() {
            return this.a;
        }

        @Override // net.nend.android.internal.utilities.g.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public String makeResponse(byte[] bArr) {
            return c.b(bArr);
        }
    }
}
