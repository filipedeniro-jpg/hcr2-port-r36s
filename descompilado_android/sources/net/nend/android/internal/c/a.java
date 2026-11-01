package net.nend.android.internal.c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import net.nend.android.BuildConfig;
import net.nend.android.NendAdUserFeature;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.d.n;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AbsNend2AdLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    private static String d;
    protected net.nend.android.internal.c.f.a a;
    protected WeakReference<Context> b;
    private NendAdUserFeature e;
    private boolean f = true;
    protected long c = 0;

    public abstract d.a a(int i, String str, String str2);

    public a(Context context) {
        this.b = new WeakReference<>(context);
        this.a = net.nend.android.internal.c.f.a.a(context);
    }

    private static String c(Context context) {
        if (TextUtils.isEmpty(d)) {
            WebView webView = new WebView(context);
            d = webView.getSettings().getUserAgentString();
            webView.destroy();
        }
        return d;
    }

    private static String d(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    private static int e(Context context) {
        return context.getResources().getConfiguration().orientation == 1 ? 1 : 2;
    }

    private static net.nend.android.internal.c.e.a.a.c f(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new net.nend.android.internal.c.e.a.a.c.a().a(displayMetrics.widthPixels).b(displayMetrics.heightPixels).c(displayMetrics.densityDpi).a();
    }

    private static net.nend.android.internal.c.e.a.a.d a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        return new net.nend.android.internal.c.e.a.a.d.a().a(jSONObject).b(jSONObject2).c(jSONObject3).a();
    }

    @VisibleForTesting
    protected static int a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return 0;
    }

    @VisibleForTesting
    protected static String b(Context context) {
        TelephonyManager telephonyManager;
        if (!net.nend.android.internal.utilities.video.e.a(context, "android.permission.READ_PHONE_STATE") || (telephonyManager = (TelephonyManager) context.getSystemService("phone")) == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    private static String g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "";
        }
    }

    public void a(NendAdUserFeature nendAdUserFeature) {
        this.e = nendAdUserFeature;
    }

    public void a(boolean z) {
        this.f = z;
    }

    protected <V extends net.nend.android.internal.b.a> k<V> a(int i, String str, String str2, final String str3, final net.nend.android.internal.utilities.g.c<V> cVar) {
        final Context context = this.b.get();
        if (context == null) {
            return l.a((Throwable) new IllegalStateException("Context is null"));
        }
        net.nend.android.internal.utilities.b.a("ApiKeyEvent", str);
        final ExecutorService executorServiceB = net.nend.android.internal.utilities.g.a().b();
        net.nend.android.internal.d.a aVar = new net.nend.android.internal.d.a(context.getMainLooper());
        final d.a aVarA = a(i, str, str2);
        final net.nend.android.internal.utilities.video.d dVar = new net.nend.android.internal.utilities.video.d(context);
        return l.a(executorServiceB, new net.nend.android.internal.utilities.g.d(context)).b(new net.nend.android.internal.d.g<String, k<? extends n<JSONObject, JSONObject, JSONObject>>>() { // from class: net.nend.android.internal.c.a.3
            @Override // net.nend.android.internal.d.g
            public k<? extends n<JSONObject, JSONObject, JSONObject>> a(String str4) {
                aVarA.a.d(str4);
                return l.a(dVar.b(), dVar.a(), dVar.a(a.this.f));
            }
        }).a(aVar).b(new net.nend.android.internal.d.g<n<JSONObject, JSONObject, JSONObject>, k<? extends JSONObject>>() { // from class: net.nend.android.internal.c.a.2
            @Override // net.nend.android.internal.d.g
            public k<? extends JSONObject> a(n<JSONObject, JSONObject, JSONObject> nVar) {
                return a.this.a(context, aVarA, nVar, str3);
            }
        }).b((net.nend.android.internal.d.g) new net.nend.android.internal.d.g<JSONObject, k<? extends V>>() { // from class: net.nend.android.internal.c.a.1
            @Override // net.nend.android.internal.d.g
            public k<? extends V> a(JSONObject jSONObject) {
                net.nend.android.internal.utilities.b.a("JsonRequestEvent", jSONObject);
                if (g.a(context)) {
                    a.this.c = System.currentTimeMillis();
                    return l.a(executorServiceB, net.nend.android.internal.utilities.g.f.a(cVar, jSONObject));
                }
                return l.a((Throwable) new net.nend.android.internal.a.a(NendVideoAdClientError.NETWORK_IS_NOT_ACTIVE));
            }
        });
    }

    public k<JSONObject> a(Context context, d.a aVar, n<JSONObject, JSONObject, JSONObject> nVar, String str) {
        net.nend.android.internal.c.e.a.a.b bVarA = aVar.a.a(2).a(Build.VERSION.RELEASE).b(Build.MODEL).c(c(context)).e(d(context)).b(e(context)).a(f(context)).a(a(nVar.a, nVar.b, nVar.c)).c(a(context)).f(b(context)).a(g.b(context)).a();
        aVar.a(bVarA).a(aVar.b.a(context.getPackageName()).b(g(context)).c(str).a()).c("Nend SDK").d(BuildConfig.VERSION_NAME).a(System.currentTimeMillis()).a(this.e);
        try {
            return l.a(aVar.a().a());
        } catch (JSONException e) {
            return l.a(e.getCause());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: renamed from: net.nend.android.internal.c.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsNend2AdLoader.java */
    public static abstract class AbstractC0107a<V> implements net.nend.android.internal.utilities.g.c<V> {
        protected abstract V a(JSONObject jSONObject);

        @Override // net.nend.android.internal.utilities.g.b
        public V makeResponse(byte[] bArr) {
            return null;
        }

        protected AbstractC0107a() {
        }

        @Override // net.nend.android.internal.utilities.g.c
        public V a(f fVar) throws net.nend.android.internal.a.a {
            int iB = fVar.b();
            String strA = a(fVar.a());
            if (net.nend.android.internal.utilities.k.SUCCESS.a() != iB) {
                throw new net.nend.android.internal.a.a(iB, strA);
            }
            try {
                return a(new JSONObject(strA));
            } catch (JSONException e) {
                throw new IllegalArgumentException(e.getCause());
            }
        }

        private String a(byte[] bArr) {
            return (bArr == null || bArr.length == 0) ? "" : new String(bArr);
        }
    }
}
