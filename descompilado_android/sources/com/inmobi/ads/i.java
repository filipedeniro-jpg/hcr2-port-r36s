package com.inmobi.ads;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.VisibleForTesting;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.ShareConstants;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.rendering.RenderView;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.ironsource.sdk.constants.Constants;
import com.squareup.picasso.Picasso;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AdUnit.java */
/* JADX INFO: loaded from: classes2.dex */
@UiThread
public abstract class i implements bl.a, h.a, com.inmobi.commons.core.configs.b.c, RenderView.a, com.inmobi.rendering.a {
    private static final String y = "i";
    private boolean A;

    @Nullable
    private h B;
    private long C;
    private long D;
    private WeakReference<b> E;
    private RenderView F;
    private bn G;
    private long H;

    @NonNull
    private a J;
    private Runnable K;
    private Set<bq> L;
    private InMobiAdRequest.MonetizationContext M;
    private bl N;
    private boolean O;
    private boolean Q;

    @Nullable
    private com.inmobi.ads.b.a R;
    int a;
    public long d;
    public String e;
    public Map<String, String> f;
    String h;
    JSONObject i;
    bx j;
    String k;
    public String l;
    ah o;
    ExecutorService p;
    public d q;
    int r;
    Handler s;
    boolean t;
    RenderView u;
    boolean v;
    String x;
    private WeakReference<Context> z;
    final JSONObject b = new JSONObject();
    final boolean c = false;
    public boolean n = false;
    private long I = 0;
    boolean w = false;
    private RenderView.a S = new RenderView.a() { // from class: com.inmobi.ads.i.14
        @Override // com.inmobi.rendering.RenderView.a
        public final void A() {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void B() {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void G() {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void a(HashMap<Object, Object> map) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void b(RenderView renderView) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void b(String str, Map<String, Object> map) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void b(HashMap<Object, Object> map) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void c(RenderView renderView) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void d(RenderView renderView) {
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void w() {
            i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.14.1
                @Override // java.lang.Runnable
                public final void run() {
                    if (2 == i.this.a) {
                        i.l(i.this);
                        i.this.J();
                    }
                }
            });
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void y() {
            i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.14.2
                @Override // java.lang.Runnable
                public final void run() {
                    if (i.this.k != null) {
                        i.this.i().a(i.this.k);
                    }
                    i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
                }
            });
        }

        @Override // com.inmobi.rendering.RenderView.a
        public final void a(RenderView renderView) {
            i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.14.3
                @Override // java.lang.Runnable
                public final void run() {
                    if (2 == i.this.a) {
                        i.this.L();
                    }
                }
            });
        }
    };
    private com.inmobi.ads.d.a P = new com.inmobi.ads.d.b(com.inmobi.b.a.a());
    String m = "unknown";
    com.inmobi.ads.c g = new com.inmobi.ads.c();

    /* JADX INFO: compiled from: AdUnit.java */
    public static abstract class b {
        public void a() {
        }

        public void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        void a(i iVar) {
        }

        void a(@NonNull Map<Object, Object> map) {
        }

        public void a(boolean z) {
        }

        void a(byte[] bArr) {
        }

        void b() {
        }

        void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        }

        void b(@NonNull Map<Object, Object> map) {
        }

        void b(boolean z) {
        }

        void c() {
        }

        void d() {
        }

        void e() {
        }

        void f() {
        }

        void g() {
        }

        void h() {
        }

        public boolean i() {
            return true;
        }

        void j() {
        }
    }

    /* JADX INFO: compiled from: AdUnit.java */
    public interface d {
        void a(@NonNull i iVar);

        void a(@NonNull i iVar, @NonNull InMobiAdRequestStatus inMobiAdRequestStatus);
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void G() {
    }

    void K() {
    }

    void L() {
    }

    public abstract String b();

    protected abstract void b(com.inmobi.ads.a aVar);

    void b(b bVar) {
    }

    public abstract String c();

    void c(b bVar) {
    }

    protected abstract AdContainer.RenderingProperties.PlacementType d();

    protected void x() {
    }

    static /* synthetic */ boolean l(i iVar) {
        iVar.O = true;
        return true;
    }

    @Override // com.inmobi.commons.core.configs.b.c
    public final void a(com.inmobi.commons.core.configs.a aVar) {
        this.g = (com.inmobi.ads.c) aVar;
        i().d = this.g.a(b());
        bl blVar = this.N;
        if (blVar != null) {
            blVar.b = this.g.a(b());
        }
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
    }

    public i(Context context, long j, b bVar) {
        this.z = new WeakReference<>(context);
        this.d = j;
        this.E = new WeakReference<>(bVar);
        com.inmobi.commons.core.configs.b.a().a(new com.inmobi.commons.core.configs.g(), (com.inmobi.commons.core.configs.b.c) null);
        com.inmobi.commons.core.configs.b.a().a(this.g, this);
        this.p = Executors.newSingleThreadExecutor();
        this.p.submit(new Runnable() { // from class: com.inmobi.ads.i.1
            @Override // java.lang.Runnable
            public final void run() {
                i iVar = i.this;
                iVar.B = new h(iVar, iVar.g.a(i.this.b()), i.this.u());
            }
        });
        this.J = new a(this);
        this.G = new bn(this);
        this.L = new HashSet();
        this.r = -1;
        this.K = new Runnable() { // from class: com.inmobi.ads.i.4
            @Override // java.lang.Runnable
            public final void run() {
                int iR = i.this.r();
                if (iR != -2 && iR != -1 && iR != 0 && iR != 1 && iR != 2) {
                    StringBuilder sb = new StringBuilder("Unknown return value (");
                    sb.append(iR);
                    sb.append(") from #doAdLoadWork()");
                }
                String unused = i.y;
            }
        };
        com.inmobi.commons.core.e.b.a().a("ads", this.g.l);
        this.s = new Handler(Looper.getMainLooper());
        this.t = false;
        this.x = "";
        this.i = this.b;
        this.A = false;
        this.a = 0;
    }

    @Nullable
    public final Context a() {
        WeakReference<Context> weakReference = this.z;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    public void a(Context context) {
        this.z = new WeakReference<>(context);
    }

    @NonNull
    protected Map<String, String> e() {
        HashMap map = new HashMap();
        map.put("preload-request", this.n ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        return map;
    }

    @Nullable
    final b f() {
        b bVar = this.E.get();
        if (bVar == null) {
            g();
        }
        return bVar;
    }

    final void g() {
        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Listener was garbage collected. Unable to give callback");
        d("ListenerNotFound");
    }

    final void a(b bVar) {
        this.E = new WeakReference<>(bVar);
    }

    public final boolean h() {
        if (1 == this.a) {
            return false;
        }
        if (this.D == -1) {
            return this.C != 0 && System.currentTimeMillis() - this.C > TimeUnit.SECONDS.toMillis(this.g.a(b()).d);
        }
        return this.C != 0 && System.currentTimeMillis() > this.D;
    }

    @NonNull
    public final h i() {
        if (this.B == null) {
            this.B = new h(this, this.g.a(b()), u());
        }
        return this.B;
    }

    @NonNull
    protected RenderView k() {
        RenderView renderView = this.F;
        if ((renderView == null || renderView.s.get()) && a() != null) {
            this.F = new RenderView(a(), new AdContainer.RenderingProperties(d()), this.L, this.k);
            this.F.a(this, this.g);
            this.F.setPlacementId(this.d);
            this.F.setCreativeId(this.x);
            this.F.setAllowAutoRedirection(this.A);
        }
        return this.F;
    }

    /* JADX WARN: Code duplicated, block: B:20:0x005d  */
    /* JADX WARN: Code duplicated, block: B:34:0x0085 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:36:0x0087 A[Catch: IllegalArgumentException -> 0x01b6, JSONException -> 0x01c6, TRY_LEAVE, TryCatch #5 {IllegalArgumentException -> 0x01b6, JSONException -> 0x01c6, blocks: (B:3:0x000e, B:29:0x006c, B:32:0x0075, B:36:0x0087, B:42:0x00a6, B:41:0x009c), top: B:116:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:39:0x0091 A[Catch: IllegalArgumentException -> 0x0066, JSONException -> 0x0069, TRY_ENTER, TRY_LEAVE, TryCatch #4 {IllegalArgumentException -> 0x0066, JSONException -> 0x0069, blocks: (B:6:0x0037, B:9:0x003e, B:14:0x004d, B:17:0x0055, B:31:0x0071, B:39:0x0091, B:44:0x00aa, B:46:0x00b2, B:48:0x00ba), top: B:118:0x0037 }] */
    /* JADX WARN: Code duplicated, block: B:41:0x009c A[Catch: IllegalArgumentException -> 0x01b6, JSONException -> 0x01c6, TRY_ENTER, TryCatch #5 {IllegalArgumentException -> 0x01b6, JSONException -> 0x01c6, blocks: (B:3:0x000e, B:29:0x006c, B:32:0x0075, B:36:0x0087, B:42:0x00a6, B:41:0x009c), top: B:116:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:50:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:53:0x00e4 A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:55:0x00ea A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:62:0x0115 A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x012b A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0135 A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x013a  */
    /* JADX WARN: Code duplicated, block: B:76:0x015f  */
    /* JADX WARN: Code duplicated, block: B:82:0x016d A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0173  */
    /* JADX WARN: Code duplicated, block: B:85:0x0175  */
    /* JADX WARN: Code duplicated, block: B:87:0x0178 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x017a  */
    /* JADX WARN: Code duplicated, block: B:89:0x017b  */
    /* JADX WARN: Code duplicated, block: B:90:0x017d  */
    /* JADX WARN: Code duplicated, block: B:93:0x0187 A[Catch: IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, TryCatch #6 {IllegalArgumentException -> 0x01b2, JSONException -> 0x01b4, blocks: (B:51:0x00d2, B:53:0x00e4, B:55:0x00ea, B:57:0x0104, B:59:0x0108, B:60:0x0111, B:62:0x0115, B:63:0x0125, B:65:0x012b, B:67:0x0135, B:69:0x013b, B:71:0x0141, B:73:0x0147, B:79:0x0165, B:91:0x017e, B:93:0x0187, B:94:0x0197, B:82:0x016d, B:95:0x019c, B:97:0x01a2, B:99:0x01ae), top: B:114:0x00d2 }] */
    public boolean a(com.inmobi.ads.a aVar) {
        boolean z;
        byte b2;
        JSONObject jSONObject;
        String string;
        bq bqVar;
        int iHashCode;
        byte b3;
        bq bqVar2;
        Context contextA;
        try {
            JSONObject jSONObject2 = new JSONObject(aVar.c);
            this.C = aVar.e;
            this.D = aVar.c();
            this.k = aVar.g;
            this.l = aVar.h;
            String strOptString = jSONObject2.optString("markupType");
            String str = com.my.target.ai.a.da;
            String str2 = "unknown";
            if (strOptString == null) {
                this.m = str;
                this.A = jSONObject2.optBoolean("allowAutoRedirection", false);
                this.i = aVar.b();
                if ("unknown".equals(this.m)) {
                    return false;
                }
                if ("inmobiJson".equals(this.m)) {
                    this.h = jSONObject2.getJSONObject("pubContent").toString();
                } else {
                    this.h = jSONObject2.getString("pubContent").trim();
                }
                if (this.h != null) {
                    z = false;
                } else {
                    z = false;
                }
                this.x = jSONObject2.optString(Constants.RequestParameters.CREATIVE_ID, "");
                if (this.L.isEmpty()) {
                    if (jSONObject2.has("viewability")) {
                        bqVar2 = new bq(1);
                        bqVar2.b = c.a(jSONObject2.getJSONArray("viewability"));
                        this.L.add(bqVar2);
                        contextA = a();
                        if (contextA != null) {
                            z.a(((Activity) contextA).getApplication());
                        }
                        if (bqVar2.b != null) {
                            new StringBuilder("Read out Moat params: ").append(bqVar2.b.toString());
                        }
                    }
                    if (jSONObject2.has("metaInfo")) {
                        jSONObject = jSONObject2.getJSONObject("metaInfo");
                        if (jSONObject.has("creativeType")) {
                            string = jSONObject.getString("creativeType");
                        } else {
                            string = "unknown";
                        }
                        if (jSONObject.has("iasEnabled")) {
                            bqVar = new bq(3);
                            HashMap map = new HashMap();
                            iHashCode = string.hashCode();
                            if (iHashCode != 112202875) {
                                if (iHashCode != 1425678798) {
                                    b3 = -1;
                                } else {
                                    b3 = 1;
                                }
                            } else if (string.equals("video")) {
                                b3 = 2;
                            } else {
                                b3 = -1;
                            }
                            if (b3 != 1) {
                                str2 = "nonvideo";
                            } else if (b3 != 2) {
                                str2 = "video";
                            }
                            map.put("creativeType", str2);
                            bqVar.b = map;
                            if (bqVar.b != null) {
                                new StringBuilder("Read out IAS params: ").append(bqVar.b.toString());
                            }
                            this.L.add(bqVar);
                        }
                    }
                    if (jSONObject2.has("tracking")) {
                        this.r = 0;
                    }
                }
                z = false;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            } else {
                try {
                    if (strOptString.length() != 0) {
                        int iHashCode2 = strOptString.hashCode();
                        if (iHashCode2 != -1084172778) {
                            if (iHashCode2 == 3213227 && strOptString.equals(com.my.target.ai.a.da)) {
                                b2 = 1;
                            } else {
                                b2 = -1;
                            }
                        } else if (strOptString.equals("inmobiJson")) {
                            b2 = 2;
                        } else {
                            b2 = -1;
                        }
                        if (b2 != 1) {
                            str = b2 != 2 ? "unknown" : "inmobiJson";
                        }
                    }
                    this.m = str;
                    this.A = jSONObject2.optBoolean("allowAutoRedirection", false);
                    this.i = aVar.b();
                    if ("unknown".equals(this.m)) {
                        return false;
                    }
                    if ("inmobiJson".equals(this.m)) {
                        this.h = jSONObject2.getJSONObject("pubContent").toString();
                    } else {
                        this.h = jSONObject2.getString("pubContent").trim();
                    }
                    if (this.h != null || this.h.length() == 0 || "unknown".equals(this.m)) {
                        z = false;
                    } else {
                        this.h = this.h.replace("@__imm_aft@", String.valueOf(System.currentTimeMillis() - this.H));
                        z = true;
                    }
                    try {
                        this.x = jSONObject2.optString(Constants.RequestParameters.CREATIVE_ID, "");
                        if (this.L.isEmpty()) {
                            if (jSONObject2.has("viewability")) {
                                bqVar2 = new bq(1);
                                bqVar2.b = c.a(jSONObject2.getJSONArray("viewability"));
                                this.L.add(bqVar2);
                                contextA = a();
                                if (contextA != null && (contextA instanceof Activity)) {
                                    z.a(((Activity) contextA).getApplication());
                                }
                                if (bqVar2.b != null) {
                                    new StringBuilder("Read out Moat params: ").append(bqVar2.b.toString());
                                }
                            }
                            if (jSONObject2.has("metaInfo")) {
                                jSONObject = jSONObject2.getJSONObject("metaInfo");
                                if (jSONObject.has("creativeType")) {
                                    string = jSONObject.getString("creativeType");
                                } else {
                                    string = "unknown";
                                }
                                if (jSONObject.has("iasEnabled") && jSONObject.getBoolean("iasEnabled")) {
                                    bqVar = new bq(3);
                                    HashMap map2 = new HashMap();
                                    iHashCode = string.hashCode();
                                    if (iHashCode != 112202875) {
                                        if (iHashCode != 1425678798 && string.equals("nonvideo")) {
                                            b3 = 1;
                                        } else {
                                            b3 = -1;
                                        }
                                    } else if (string.equals("video")) {
                                        b3 = 2;
                                    } else {
                                        b3 = -1;
                                    }
                                    if (b3 != 1) {
                                        str2 = "nonvideo";
                                    } else if (b3 != 2) {
                                        str2 = "video";
                                    }
                                    map2.put("creativeType", str2);
                                    bqVar.b = map2;
                                    if (bqVar.b != null) {
                                        new StringBuilder("Read out IAS params: ").append(bqVar.b.toString());
                                    }
                                    this.L.add(bqVar);
                                }
                            }
                            if (jSONObject2.has("tracking") && "web".equals(jSONObject2.getString("tracking"))) {
                                this.r = 0;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        e = e;
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    } catch (JSONException e2) {
                        e = e2;
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                } catch (IllegalArgumentException e3) {
                    e = e3;
                    z = false;
                } catch (JSONException e4) {
                    e = e4;
                    z = false;
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return z;
                }
                z = false;
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
        } catch (IllegalArgumentException e5) {
            e = e5;
        } catch (JSONException e6) {
            e = e6;
        }
        return z;
    }

    protected void b(long j, boolean z) {
        StringBuilder sb = new StringBuilder("Asset availability changed (");
        sb.append(z);
        sb.append(") for placement ID (");
        sb.append(j);
        sb.append(")");
    }

    @UiThread
    protected void a(long j, boolean z, @NonNull com.inmobi.ads.a aVar) {
        if (j == this.d && 1 == this.a && z) {
            this.C = aVar.e;
            this.D = aVar.c();
        }
    }

    @UiThread
    protected void c(long j, @NonNull com.inmobi.ads.a aVar) {
        if (j == this.d && this.a == 1) {
            if (a(aVar)) {
                a(f(), "ARF", "");
                this.I = SystemClock.elapsedRealtime();
                this.a = 2;
            } else {
                b("ParsingFailed");
                a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), true);
            }
        }
    }

    final void a(InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (InMobiAdRequestStatus.StatusCode.NO_FILL.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NoFill");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.SERVER_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("ServerError");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("NetworkUnreachable");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.AD_ACTIVE.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("AdActive");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.REQUEST_PENDING.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestPending");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.REQUEST_INVALID.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestInvalid");
            return;
        }
        if (InMobiAdRequestStatus.StatusCode.REQUEST_TIMED_OUT.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("RequestTimedOut");
        } else if (InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("EarlyRefreshRequest");
        } else if (InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR.equals(inMobiAdRequestStatus.getStatusCode())) {
            b("InternalError");
        }
    }

    public void a(InMobiAdRequest.MonetizationContext monetizationContext) {
        this.M = monetizationContext;
    }

    public InMobiAdRequest.MonetizationContext l() {
        return this.M;
    }

    static boolean m() {
        try {
            RecyclerView.class.getName();
            Picasso.class.getName();
            return false;
        } catch (NoClassDefFoundError unused) {
            return true;
        }
    }

    @UiThread
    public void n() {
        d("AdLoadRequested");
        if (!com.inmobi.commons.core.utilities.d.a()) {
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE), true);
        } else if (this.Q) {
            a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.LOAD_CALLED_AFTER_GET_SIGNALS), false);
        } else {
            this.p.execute(this.K);
        }
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0084 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:34:0x0085  */
    final void o() {
        boolean z;
        final b bVarF = f();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        if (!com.inmobi.commons.core.utilities.d.a()) {
            if (bVarF != null) {
                bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE));
            }
            a(bVarF, "ART", "NetworkNotAvailable");
            a("AdGetSignalsFailed", jCurrentTimeMillis);
        } else {
            int i = this.a;
            if (i == 1 || i == 2 || i == 4) {
                if (bVarF != null) {
                    bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GET_SIGNALS_CALLED_WHILE_LOADING));
                }
                a(bVarF, "ART", "LoadInProgress");
                a("AdGetSignalsFailed", jCurrentTimeMillis);
            } else {
                if (i != 10) {
                    if (i == 6 || i == 7 || i == 8) {
                        if (bVarF != null) {
                            bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE));
                        }
                        a(bVarF, "ART", "ReloadNotPermitted");
                        a("AdGetSignalsFailed", jCurrentTimeMillis);
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                    this.Q = true;
                    d("AdGetSignalsRequested");
                    this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.17
                        @Override // java.lang.Runnable
                        public final void run() {
                            i iVar = i.this;
                            iVar.a = 10;
                            String strA = com.inmobi.ads.c.a.a(iVar.f);
                            if (i.this.R == null) {
                                i iVar2 = i.this;
                                iVar2.R = new com.inmobi.ads.b.a(iVar2, strA);
                            } else {
                                i.this.R.b = strA;
                            }
                            if (bVarF != null) {
                                try {
                                    byte[] bArrA = i.this.R.a();
                                    if (bArrA != null) {
                                        bVarF.a(bArrA);
                                        i.this.a = 11;
                                        i.this.a(i.this.f(), "VAR", "");
                                        i.this.a("AdGetSignalsSucceeded", jCurrentTimeMillis);
                                        return;
                                    }
                                    i.this.a = 3;
                                    bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                                    i.this.a(i.this.f(), "ART", "RequestCreationFailed");
                                    i.this.a("AdGetSignalsFailed", jCurrentTimeMillis);
                                } catch (com.inmobi.ads.a.b unused) {
                                    i.this.a = 3;
                                    bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GDPR_COMPLIANCE_ENFORCED));
                                }
                            }
                        }
                    });
                }
                if (bVarF != null) {
                    bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.FETCHING_SIGNALS_STATE_ERROR));
                }
                a(bVarF, "ART", "SignalsFetchInProgress");
                a("AdGetSignalsFailed", jCurrentTimeMillis);
            }
        }
        z = true;
        if (z) {
            return;
        }
        this.Q = true;
        d("AdGetSignalsRequested");
        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.17
            @Override // java.lang.Runnable
            public final void run() {
                i iVar = i.this;
                iVar.a = 10;
                String strA = com.inmobi.ads.c.a.a(iVar.f);
                if (i.this.R == null) {
                    i iVar2 = i.this;
                    iVar2.R = new com.inmobi.ads.b.a(iVar2, strA);
                } else {
                    i.this.R.b = strA;
                }
                if (bVarF != null) {
                    try {
                        byte[] bArrA = i.this.R.a();
                        if (bArrA != null) {
                            bVarF.a(bArrA);
                            i.this.a = 11;
                            i.this.a(i.this.f(), "VAR", "");
                            i.this.a("AdGetSignalsSucceeded", jCurrentTimeMillis);
                            return;
                        }
                        i.this.a = 3;
                        bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                        i.this.a(i.this.f(), "ART", "RequestCreationFailed");
                        i.this.a("AdGetSignalsFailed", jCurrentTimeMillis);
                    } catch (com.inmobi.ads.a.b unused) {
                        i.this.a = 3;
                        bVarF.b(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GDPR_COMPLIANCE_ENFORCED));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(@NonNull String str, long j) {
        HashMap map = new HashMap();
        map.put("latency", Long.valueOf(System.currentTimeMillis() - j));
        a(str, map);
    }

    @UiThread
    public void a(final boolean z) {
        d("AdPrefetchRequested");
        this.a = 1;
        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (!com.inmobi.commons.core.utilities.d.a()) {
                        i.this.b(i.this.d, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE));
                        return;
                    }
                    com.inmobi.a.o.a().e();
                    i.N();
                    com.inmobi.commons.core.configs.h hVar = new com.inmobi.commons.core.configs.h();
                    com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
                    if (hVar.g) {
                        return;
                    }
                    i.this.H = System.currentTimeMillis();
                    try {
                        if (i.this.N == null) {
                            i.this.N = new bl(i.this, i.this.g.a(i.this.b()));
                        }
                        i.this.l = i.this.N.a(i.this.u(), z, i.this.g.c);
                    } catch (com.inmobi.ads.a.a e) {
                        String unused = i.y;
                        e.getMessage();
                        if (i.this.N.a) {
                            return;
                        }
                        i.this.b(i.this.d, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST));
                    }
                } catch (Exception e2) {
                    Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Unable to Prefetch ad; SDK encountered an unexpected error");
                    String unused2 = i.y;
                    new StringBuilder("Prefetch failed with unexpected error: ").append(e2.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @UiThread
    public void q() {
        a(false);
    }

    private void a(@NonNull final String str, final WeakReference<b> weakReference) {
        this.s.post(new Runnable() { // from class: com.inmobi.ads.i.5
            @Override // java.lang.Runnable
            public final void run() {
                i iVar = i.this;
                iVar.a = 3;
                iVar.b(str);
                if (i.this.w) {
                    String unused = i.y;
                    return;
                }
                b bVar = (b) weakReference.get();
                if (bVar != null) {
                    if ("int".equals(i.this.b())) {
                        i.this.a(bVar, "AVFB", "");
                        bVar.b();
                        return;
                    } else {
                        bVar.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
                        return;
                    }
                }
                i.this.g();
            }
        });
    }

    final void s() {
        AdContainer adContainerJ = j();
        if (adContainerJ == null) {
            return;
        }
        adContainerJ.a(2, null);
    }

    public final f t() {
        f fVarU = u();
        fVarU.l = true;
        return fVarU;
    }

    @NonNull
    public final f u() {
        String str = this.g.a;
        long j = this.d;
        com.inmobi.commons.core.utilities.uid.d dVar = new com.inmobi.commons.core.utilities.uid.d(this.g.p.a);
        com.inmobi.ads.cache.d.a();
        f fVar = new f(str, j, dVar, com.inmobi.ads.cache.d.c());
        fVar.f = this.e;
        fVar.g = this.f;
        fVar.e = b();
        fVar.b = "sdkJson";
        fVar.d = this.g.a(b()).b;
        fVar.h = e();
        fVar.c = c();
        fVar.r = this.g.e * 1000;
        fVar.s = this.g.e * 1000;
        fVar.j = this.M;
        fVar.y = O();
        return fVar;
    }

    @UiThread
    public void v() {
        if (this.w) {
            return;
        }
        this.w = true;
        this.k = null;
        this.C = 0L;
        this.D = -1L;
        this.L.clear();
        AdContainer adContainerJ = j();
        if (adContainerJ != null) {
            adContainerJ.destroy();
        }
        this.a = 0;
        this.m = "unknown";
        this.O = false;
        this.u = null;
        this.t = false;
        this.v = false;
        this.x = "";
        this.i = this.b;
        this.A = false;
        this.Q = false;
    }

    protected void z() {
        b("RenderFailed");
    }

    final void C() {
        this.G.removeMessages(0);
    }

    final void D() {
        this.s.post(new Runnable() { // from class: com.inmobi.ads.i.9
            @Override // java.lang.Runnable
            public final void run() {
                i.this.E();
            }
        });
    }

    protected void E() {
        b("RenderTimeOut");
        if (this.a == 2) {
            this.a = 3;
            if (f() != null) {
                f().a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR));
            }
        }
    }

    final void a(String str) {
        HashMap map = new HashMap();
        map.put("errorCode", str);
        c("AdLoadRejected", map);
    }

    final void F() {
        HashMap map = new HashMap();
        map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadSuccessful", map);
    }

    final void b(String str) {
        HashMap map = new HashMap();
        map.put("errorCode", str);
        map.put("latency", Long.valueOf(SystemClock.elapsedRealtime() - this.I));
        c("AdLoadFailed", map);
    }

    final void c(String str) {
        HashMap map = new HashMap();
        map.put("errorCode", str);
        c("AdPrefetchRejected", map);
    }

    final void a(b bVar, String str, String str2) {
        if (bVar == null || !bVar.i()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(str2);
        com.inmobi.ads.c cVar = this.g;
        com.inmobi.ads.c.a aVar = cVar.g.get(b() + "Dict");
        if (aVar == null) {
            aVar = cVar.f;
        }
        if (aVar.h) {
            String str3 = this.l;
            if (str3 == null) {
                str3 = "";
            }
            this.P.a(new com.inmobi.commons.core.f.b(UUID.randomUUID().toString(), this.m, str, this.d, str3, str2, com.inmobi.commons.core.utilities.b.b.a(O()).get("d-nettype-raw"), b(), System.currentTimeMillis()));
        }
    }

    @VisibleForTesting
    private boolean O() {
        return this.g.i.m && com.inmobi.commons.a.a.d();
    }

    /* JADX INFO: compiled from: AdUnit.java */
    static final class a extends Handler {
        private WeakReference<i> a;

        a(i iVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(iVar);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            WeakReference<i> weakReference = this.a;
            i iVar = weakReference == null ? null : weakReference.get();
            if (iVar == null) {
            }
            Bundle data = message.getData();
            long j = data.getLong(Constants.PLACEMENT_ID);
            int i = message.what;
            if (i == 1) {
                iVar.a(j, data.getBoolean("adAvailable"), (com.inmobi.ads.a) message.obj);
                return;
            }
            if (i == 2) {
                iVar.c(j, (com.inmobi.ads.a) message.obj);
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    iVar.b(j, data.getBoolean("assetAvailable"));
                    return;
                }
                switch (i) {
                    case 11:
                        iVar.x();
                        break;
                    case 12:
                        iVar.z();
                        break;
                    case 13:
                        iVar.b((InMobiAdRequestStatus) message.obj);
                        break;
                    case 14:
                        iVar.H();
                        break;
                }
            }
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void b(String str, Map<String, Object> map) {
        c(str, map);
    }

    @Override // com.inmobi.ads.bl.a
    public final void a(String str, Map<String, Object> map) {
        c(str, map);
    }

    public final void d(String str) {
        c(str, new HashMap());
    }

    public final void c(String str, Map<String, Object> map) {
        HashMap map2 = new HashMap();
        map2.put("type", b());
        map2.put("plId", Long.valueOf(this.d));
        map2.put("impId", this.k);
        map2.put("isPreloaded", this.n ? "1" : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        int iA = com.inmobi.commons.core.utilities.b.b.a();
        map2.put("networkType", iA != 0 ? iA != 1 ? "NIL" : "wifi" : "carrier");
        map2.put("ts", Long.valueOf(System.currentTimeMillis()));
        if (map.get("clientRequestId") == null) {
            map2.put("clientRequestId", this.l);
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            map2.put(entry.getKey(), entry.getValue());
        }
        try {
            com.inmobi.commons.core.e.b.a();
            com.inmobi.commons.core.e.b.a("ads", str, map2);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error in submitting telemetry event : (");
            sb.append(e.getMessage());
            sb.append(")");
        }
    }

    final void c(@NonNull com.inmobi.ads.a aVar) {
        if (aVar instanceof bc) {
            bc bcVar = (bc) aVar;
            Context contextA = a();
            boolean z = this.g.k.j;
            for (bq bqVar : this.L) {
                if (z && 3 == bqVar.a && "video" == bqVar.b.get("creativeType")) {
                    try {
                        be beVar = (be) new ao(d(), new JSONObject(this.h), this.g, new bx(bcVar.l, bcVar.m, bcVar.n, bcVar.h(), bcVar.i(), this.g.m)).c(ShareConstants.VIDEO_URL).get(0);
                        if (contextA != null) {
                            HashSet hashSet = new HashSet();
                            for (NativeTracker nativeTracker : beVar.u) {
                                if (NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_IAS == nativeTracker.b) {
                                    hashSet.add(d(nativeTracker.a, nativeTracker.c));
                                }
                            }
                            if (hashSet.size() != 0) {
                                bqVar.b.put("avidAdSession", w.a(contextA, hashSet));
                                bqVar.b.put("deferred", true);
                            }
                        }
                    } catch (Exception e) {
                        new StringBuilder("Setting up impression tracking for AVID encountered an unexpected error: ").append(e.getMessage());
                        com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    }
                }
            }
        }
    }

    @Nullable
    private static String d(String str, Map<String, String> map) {
        if (map != null && str != null) {
            for (String str2 : map.keySet()) {
                str = str.replace(str2, map.get(str2));
            }
        }
        return str;
    }

    /* JADX INFO: compiled from: AdUnit.java */
    static class c {
        /* JADX WARN: Code duplicated, block: B:10:0x002d A[Catch: Exception -> 0x0029, TryCatch #0 {Exception -> 0x0029, blocks: (B:4:0x0008, B:6:0x000f, B:10:0x002d, B:12:0x0033, B:14:0x004f, B:15:0x0053, B:17:0x0059), top: B:21:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:12:0x0033 A[Catch: Exception -> 0x0029, LOOP:0: B:11:0x0031->B:12:0x0033, LOOP_END, TryCatch #0 {Exception -> 0x0029, blocks: (B:4:0x0008, B:6:0x000f, B:10:0x002d, B:12:0x0033, B:14:0x004f, B:15:0x0053, B:17:0x0059), top: B:21:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:14:0x004f A[Catch: Exception -> 0x0029, TryCatch #0 {Exception -> 0x0029, blocks: (B:4:0x0008, B:6:0x000f, B:10:0x002d, B:12:0x0033, B:14:0x004f, B:15:0x0053, B:17:0x0059), top: B:21:0x0008 }] */
        /* JADX WARN: Code duplicated, block: B:17:0x0059 A[Catch: Exception -> 0x0029, LOOP:1: B:15:0x0053->B:17:0x0059, LOOP_END, TRY_LEAVE, TryCatch #0 {Exception -> 0x0029, blocks: (B:4:0x0008, B:6:0x000f, B:10:0x002d, B:12:0x0033, B:14:0x004f, B:15:0x0053, B:17:0x0059), top: B:21:0x0008 }] */
        @NonNull
        static HashMap<String, String> a(@NonNull String str, @NonNull String str2, JSONArray jSONArray, JSONArray jSONArray2, JSONObject jSONObject) {
            Iterator<String> itKeys;
            int length;
            HashMap<String, String> map = new HashMap<>();
            int i = 0;
            if (jSONArray != null) {
                try {
                    int length2 = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length2) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(str);
                        int i3 = i2 + 1;
                        sb.append(i3);
                        map.put(sb.toString(), jSONArray.getString(i2));
                        i2 = i3;
                    }
                    if (jSONArray2 != null) {
                        length = jSONArray2.length();
                        while (i < length) {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str2);
                            int i4 = i + 1;
                            sb2.append(i4);
                            map.put(sb2.toString(), jSONArray2.getString(i));
                            i = i4;
                        }
                    }
                    if (jSONObject != null) {
                        itKeys = jSONObject.keys();
                        while (itKeys.hasNext()) {
                            String next = itKeys.next();
                            map.put(next, jSONObject.optString(next));
                        }
                    }
                } catch (Exception e) {
                    String unused = i.y;
                    new StringBuilder("Exception while parsing map details for Moat : ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            } else {
                if (jSONArray2 != null) {
                    length = jSONArray2.length();
                    while (i < length) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str2);
                        int i5 = i + 1;
                        sb3.append(i5);
                        map.put(sb3.toString(), jSONArray2.getString(i));
                        i = i5;
                    }
                }
                if (jSONObject != null) {
                    itKeys = jSONObject.keys();
                    while (itKeys.hasNext()) {
                        String next2 = itKeys.next();
                        map.put(next2, jSONObject.optString(next2));
                    }
                }
            }
            return map;
        }

        @Nullable
        static Map<String, Object> a(@NonNull JSONArray jSONArray) {
            JSONObject jSONObject;
            try {
                int length = jSONArray.length();
                int i = 0;
                while (true) {
                    if (i >= length) {
                        jSONObject = null;
                        break;
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    if (jSONObject2.has("moat")) {
                        jSONObject = jSONObject2.getJSONObject("moat");
                        break;
                    }
                    i++;
                }
                if (jSONObject == null) {
                    return null;
                }
                HashMap map = new HashMap();
                map.put("enabled", Boolean.valueOf(jSONObject.getBoolean("enabled")));
                map.put("instrumentVideo", Boolean.valueOf(jSONObject.optBoolean("instrumentVideo", false)));
                map.put("partnerCode", jSONObject.optString("partnerCode", null));
                map.put("clientLevels", jSONObject.optJSONArray("clientLevels"));
                map.put("clientSlicers", jSONObject.optJSONArray("clientSlicers"));
                map.put("zMoatExtras", jSONObject.optJSONObject("zMoatExtras"));
                return map;
            } catch (JSONException e) {
                String unused = i.y;
                new StringBuilder("Exception while parsing MoatParams from response : ").append(e.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                return null;
            }
        }
    }

    @Override // com.inmobi.ads.bl.a
    public final void a(long j) {
        d("AdPrefetchSuccessful");
        if (this.w || a() == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 14;
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PLACEMENT_ID, j);
        messageObtain.setData(bundle);
        this.J.sendMessage(messageObtain);
    }

    @Override // com.inmobi.ads.bl.a
    public final void b(long j, InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("EarlyRefreshRequest");
        } else if (InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE.equals(inMobiAdRequestStatus.getStatusCode())) {
            c("NetworkUnreachable");
        } else {
            d("AdPrefetchFailed");
        }
        if (this.w || a() == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 13;
        messageObtain.obj = inMobiAdRequestStatus;
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PLACEMENT_ID, j);
        messageObtain.setData(bundle);
        this.J.sendMessage(messageObtain);
    }

    @Override // com.inmobi.rendering.a
    public final void e(final String str) {
        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.10
            @Override // java.lang.Runnable
            public final void run() {
                if (i.this.k == null || str == null) {
                    String unused = i.y;
                    return;
                }
                com.inmobi.ads.d.a();
                String str2 = i.this.k;
                String str3 = str;
                com.inmobi.commons.core.d.b bVarA = com.inmobi.commons.core.d.b.a();
                com.inmobi.ads.a aVarC = com.inmobi.ads.d.c(str2);
                int iB = 0;
                if (aVarC != null) {
                    aVarC.i = str3;
                    iB = bVarA.b("ad", aVarC.a(), "imp_id=?", new String[]{str2});
                }
                String unused2 = i.y;
                StringBuilder sb = new StringBuilder("Updated ");
                sb.append(iB);
                sb.append("for blob ");
                sb.append(str);
            }
        });
    }

    @Override // com.inmobi.rendering.a
    public final void a(final String str, final String str2, @NonNull final com.inmobi.rendering.b bVar) {
        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.12
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (i.this.k == null) {
                        String unused = i.y;
                        bVar.a(str, str2, "");
                        return;
                    }
                    com.inmobi.ads.d.a();
                    com.inmobi.ads.a aVarC = com.inmobi.ads.d.c(i.this.k);
                    if (aVarC == null) {
                        String unused2 = i.y;
                        bVar.a(str, str2, "");
                    } else {
                        bVar.a(str, str2, aVarC.i);
                        String unused3 = i.y;
                    }
                } catch (Exception e) {
                    String unused4 = i.y;
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    final void I() {
        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.13
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    final ao aoVar = new ao(i.this.d(), new JSONObject(i.this.h), i.this.g, null);
                    i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.13.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                bf bfVar = aoVar.k;
                                if (bfVar != null) {
                                    i.this.u = new RenderView(i.this.a(), new AdContainer.RenderingProperties(i.this.d()), i.this.L, i.this.k);
                                    i.this.u.a(i.this.S, i.this.g);
                                    i.this.u.j = true;
                                    i.this.u.setBlobProvider(i.this);
                                    i.this.u.setIsPreload(true);
                                    i.this.u.setPlacementId(i.this.d);
                                    i.this.u.setCreativeId(i.this.x);
                                    i.this.u.setAllowAutoRedirection(i.this.A);
                                    if (i.this.r == 0) {
                                        i.this.a(true, i.this.u);
                                    }
                                    if ("URL".equals(bfVar.z)) {
                                        i.this.u.b((String) bfVar.e);
                                    } else {
                                        i.this.u.a((String) bfVar.e);
                                    }
                                }
                                i.e(i.this);
                            } catch (Exception e) {
                                String unused = i.y;
                                i.this.a = 3;
                                i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
                                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                            }
                        }
                    });
                } catch (Exception e) {
                    String unused = i.y;
                    i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.13.2
                        @Override // java.lang.Runnable
                        public final void run() {
                            i.this.a = 3;
                            i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), false);
                        }
                    });
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @UiThread
    final void J() {
        if (this.t && this.v && this.O) {
            C();
            K();
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0029  */
    @Nullable
    final AdContainer j() {
        byte b2;
        int i = this.a;
        String str = this.m;
        int iHashCode = str.hashCode();
        if (iHashCode != -1084172778) {
            if (iHashCode == 3213227 && str.equals(com.my.target.ai.a.da)) {
                b2 = 1;
            } else {
                b2 = -1;
            }
        } else if (str.equals("inmobiJson")) {
            b2 = 2;
        } else {
            b2 = -1;
        }
        if (b2 == 1) {
            if (i == 0 || 1 == i || 3 == i) {
                return null;
            }
            return k();
        }
        if (b2 != 2 || i == 0 || 1 == i || 3 == i || 2 == i) {
            return null;
        }
        return this.o;
    }

    @Override // com.inmobi.ads.h.a
    public final void a(long j, boolean z) {
        if (this.w || a() == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 4;
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PLACEMENT_ID, j);
        bundle.putBoolean("assetAvailable", z);
        messageObtain.setData(bundle);
        this.J.sendMessage(messageObtain);
    }

    @Override // com.inmobi.ads.h.a
    public final void b(long j, com.inmobi.ads.a aVar) {
        if (this.w || a() == null) {
            return;
        }
        this.I = SystemClock.elapsedRealtime();
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.obj = aVar;
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PLACEMENT_ID, j);
        bundle.putBoolean("adAvailable", true);
        messageObtain.setData(bundle);
        this.J.sendMessage(messageObtain);
    }

    @Override // com.inmobi.ads.h.a
    public final void a(long j, @NonNull com.inmobi.ads.a aVar) {
        if (this.w || a() == null) {
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        Bundle bundle = new Bundle();
        bundle.putLong(Constants.PLACEMENT_ID, j);
        messageObtain.setData(bundle);
        messageObtain.obj = aVar;
        this.J.sendMessage(messageObtain);
    }

    public void a(final long j, final InMobiAdRequestStatus inMobiAdRequestStatus) {
        if (this.w || a() == null) {
            return;
        }
        this.s.post(new Runnable() { // from class: com.inmobi.ads.i.11
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (j == i.this.d) {
                        i.this.a(i.this.f(), "ARN", "");
                        Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Failed to fetch ad for placement id: " + i.this.d + ", reason phrase available in onAdLoadFailed callback.");
                        i.this.a(inMobiAdRequestStatus, true);
                    }
                } catch (Exception e) {
                    Logger.a(Logger.InternalLogLevel.ERROR, "[InMobi]", "Unable to load Ad; SDK encountered an unexpected error");
                    String unused = i.y;
                    new StringBuilder("onAdFetchFailed with error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        });
    }

    @UiThread
    void a(InMobiAdRequestStatus inMobiAdRequestStatus, boolean z) {
        if (this.a == 1 && z) {
            this.a = 3;
        }
        b bVarF = f();
        if (bVarF != null) {
            bVarF.a(inMobiAdRequestStatus);
        }
        a(inMobiAdRequestStatus);
    }

    /* JADX WARN: Code duplicated, block: B:24:0x005c A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:25:0x005d  */
    @UiThread
    final void a(@Nullable final byte[] bArr) {
        boolean z;
        if (!com.inmobi.commons.core.utilities.b.e.e()) {
            v();
            this.s.post(new Runnable() { // from class: com.inmobi.ads.i.18
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GDPR_COMPLIANCE_ENFORCED), false);
                }
            });
        } else {
            int i = this.a;
            if (i == 1) {
                this.s.post(new Runnable() { // from class: com.inmobi.ads.i.20
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.LOAD_WITH_RESPONSE_CALLED_WHILE_LOADING), false);
                    }
                });
            } else if (i == 6 || i == 7 || i == 8) {
                this.s.post(new Runnable() { // from class: com.inmobi.ads.i.21
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE), false);
                    }
                });
            } else {
                if (i != 10) {
                    if (i != 11) {
                        this.s.post(new Runnable() { // from class: com.inmobi.ads.i.2
                            @Override // java.lang.Runnable
                            public final void run() {
                                i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.GET_SIGNALS_NOT_CALLED_FOR_LOAD_WITH_RESPONSE), false);
                            }
                        });
                    } else {
                        z = false;
                    }
                    if (z) {
                        return;
                    }
                    if (bArr != null || bArr.length == 0) {
                        a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INVALID_RESPONSE_IN_LOAD), true);
                    } else if (this.R == null) {
                        a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), true);
                        return;
                    } else {
                        this.p.execute(new Runnable() { // from class: com.inmobi.ads.i.15
                            @Override // java.lang.Runnable
                            public final void run() {
                                i iVar = i.this;
                                i.a(iVar, bArr, iVar.R);
                            }
                        });
                        return;
                    }
                }
                this.s.post(new Runnable() { // from class: com.inmobi.ads.i.19
                    @Override // java.lang.Runnable
                    public final void run() {
                        i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.FETCHING_SIGNALS_STATE_ERROR), false);
                    }
                });
            }
        }
        z = true;
        if (z) {
            return;
        }
        if (bArr != null) {
        }
        a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INVALID_RESPONSE_IN_LOAD), true);
    }

    final boolean p() {
        int i = this.a;
        if (1 == i) {
            b(this.d, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "An ad prefetch is already in progress. Please wait for the prefetch to complete before requesting for another ad for placement id: " + this.d);
            return true;
        }
        if (8 == i || 7 == i) {
            b(this.d, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.AD_ACTIVE));
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "An ad is currently being viewed by the user. Please wait for the user to close the ad before requesting for another ad for placement id: " + this.d);
            return true;
        }
        if (2 == i) {
            if (com.my.target.ai.a.da.equals(this.m)) {
                b(this.d, new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.REQUEST_PENDING));
                Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "An ad load is already in progress. Please wait for the load to complete before requesting prefetch for another ad for placement id: " + this.d);
                return true;
            }
            if ("inmobiJson".equals(this.m)) {
                a(this.d);
                return true;
            }
        }
        int i2 = this.a;
        if (5 != i2 && 9 != i2) {
            return false;
        }
        a(this.d);
        return true;
    }

    protected int r() {
        String strB;
        boolean z = true;
        try {
            this.a = 1;
            com.inmobi.a.o.a().e();
            com.inmobi.commons.core.utilities.uid.c.a();
            com.inmobi.commons.core.utilities.uid.c.c();
            com.inmobi.commons.core.configs.h hVar = new com.inmobi.commons.core.configs.h();
            com.inmobi.commons.core.configs.b.a().a(hVar, (com.inmobi.commons.core.configs.b.c) null);
            if (!hVar.g) {
                f fVarU = u();
                this.H = System.currentTimeMillis();
                h hVarI = i();
                try {
                    int i = this.g.c;
                    h.a();
                    hVarI.c = fVarU;
                    if ("int".equals(hVarI.c.e)) {
                        h.c();
                        List<com.inmobi.ads.a> listC = hVarI.b.c(hVarI.c.a, hVarI.c.c, hVarI.c.j, com.inmobi.ads.c.a.a(hVarI.c.g));
                        if (listC.size() == 0) {
                            if (SystemClock.elapsedRealtime() - hVarI.e >= i * 1000) {
                                z = false;
                            }
                            if (z) {
                                throw new com.inmobi.ads.a.a("Ignoring request to fetch an ad from the network sooner than the minimum request interval");
                            }
                            strB = hVarI.a(hVarI.c, hVarI.c.c().equals("1"));
                        } else {
                            strB = listC.get(0).h;
                            if ("INMOBIJSON".equalsIgnoreCase(listC.get(0).e())) {
                                hVarI.a.b(hVarI.c.a, listC.get(0));
                                hVarI.a(listC);
                            } else {
                                strB = hVarI.b();
                            }
                        }
                    } else {
                        strB = hVarI.b();
                    }
                    HashMap map = new HashMap();
                    map.put("im-accid", com.inmobi.commons.a.a.e());
                    map.put("isPreloaded", hVarI.c.c());
                    hVarI.a.a("AdCacheAdRequested", map);
                    this.l = strB;
                    a(f(), "VAR", "");
                    if (this.n) {
                        d("AdPreLoadRequested");
                    }
                } catch (com.inmobi.ads.a.a e) {
                    e.getMessage();
                    this.s.post(new Runnable() { // from class: com.inmobi.ads.i.8
                        @Override // java.lang.Runnable
                        public final void run() {
                            i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.EARLY_REFRESH_REQUEST), true);
                        }
                    });
                }
                return 0;
            }
            d("LoadAfterMonetizationDisabled");
            Logger.a(Logger.InternalLogLevel.ERROR, y, "SDK will not perform this load operation as monetization has been disabled. Please contact InMobi for further info.");
            return -1;
        } catch (Exception e2) {
            Logger.a(Logger.InternalLogLevel.ERROR, "InMobi", "Unable to load ad; SDK encountered an unexpected error");
            new StringBuilder("Load failed with unexpected error: ").append(e2.getMessage());
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            return -2;
        }
    }

    final void a(b bVar, @NonNull final String str, @Nullable Runnable runnable, @Nullable Looper looper) {
        if (com.my.target.ai.a.da.equals(this.m)) {
            this.s.post(new Runnable() { // from class: com.inmobi.ads.i.7
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.k().a(str);
                    i.e(i.this);
                }
            });
            return;
        }
        if ("inmobiJson".equals(this.m)) {
            final WeakReference<b> weakReference = new WeakReference<>(bVar);
            try {
                this.I = SystemClock.elapsedRealtime();
                ao aoVar = new ao(d(), new JSONObject(this.h), this.g, this.j);
                if (aoVar.c() && a() != null) {
                    ah ahVarA = ah.b.a(a(), new AdContainer.RenderingProperties(d()), aoVar, this.k, this.l, this.L, this.g, this.d, this.A, this.x);
                    ahVarA.a(new ah.c() { // from class: com.inmobi.ads.i.6
                        @Override // com.inmobi.ads.ah.c
                        public final void a() {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                i.this.a(bVar2, "AVFB", "");
                                bVar2.b();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void b() {
                            i.this.d("AdRendered");
                            if (i.this.w) {
                                return;
                            }
                            i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.6.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    i.this.b((b) weakReference.get());
                                }
                            });
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void c() {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.c();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void d() {
                            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Successfully impressed ad for placement id: " + i.this.d);
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.g();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void e() {
                            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Ad interaction for placement id: " + i.this.d);
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.a(new HashMap());
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void f() {
                            if (i.this.w) {
                                return;
                            }
                            Logger.a(Logger.InternalLogLevel.DEBUG, "InMobi", "Ad dismissed for placement id: " + i.this.d);
                            i.this.s.post(new Runnable() { // from class: com.inmobi.ads.i.6.2
                                @Override // java.lang.Runnable
                                public final void run() {
                                    i.this.c((b) weakReference.get());
                                }
                            });
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void a(Map<String, String> map) {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.b(new HashMap(map));
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void g() {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.f();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void h() {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.h();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void a(String str2, Map<String, Object> map) {
                            i.this.c(str2, map);
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void i() {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.j();
                            } else {
                                i.this.g();
                            }
                        }

                        @Override // com.inmobi.ads.ah.c
                        public final void a(boolean z) {
                            if (i.this.w) {
                                return;
                            }
                            b bVar2 = (b) weakReference.get();
                            if (bVar2 != null) {
                                bVar2.b(z);
                            } else {
                                i.this.g();
                            }
                        }
                    });
                    this.o = ahVarA;
                    if (runnable == null || looper == null) {
                        return;
                    }
                    new Handler(looper).post(runnable);
                    return;
                }
                a("DataModelValidationFailed", weakReference);
            } catch (JSONException e) {
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            } catch (Exception e2) {
                new StringBuilder("Encountered unexpected error in loading ad markup into container: ").append(e2.getMessage());
                a("InternalError", weakReference);
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void w() {
        if (this.w || a() == null) {
            return;
        }
        this.J.sendEmptyMessage(11);
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void y() {
        if (this.w || a() == null) {
            return;
        }
        this.J.sendEmptyMessage(12);
    }

    public void a(RenderView renderView) {
        if (this.w || a() == null) {
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public void b(RenderView renderView) {
        if (this.w || a() == null) {
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void A() {
        if (this.w || a() == null || 7 != this.a) {
            return;
        }
        this.a = 3;
        a(f(), "AVFB", "");
        if (f() != null) {
            f().b();
        }
    }

    public void c(RenderView renderView) {
        if (this.w || a() == null) {
        }
    }

    public void d(RenderView renderView) {
        if (this.w || a() == null) {
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void a(HashMap<Object, Object> map) {
        if (this.w || a() == null) {
            return;
        }
        new StringBuilder("Ad reward action completed. Params:").append(map.toString());
        if (f() != null) {
            f().b(map);
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void b(HashMap<Object, Object> map) {
        if (this.w || a() == null) {
            return;
        }
        new StringBuilder("Ad interaction. Params:").append(map.toString());
        d("AdInteracted");
        if (f() != null) {
            f().a(map);
        }
    }

    @Override // com.inmobi.rendering.RenderView.a
    public final void B() {
        if (this.w || a() == null || f() == null) {
            return;
        }
        f().f();
    }

    final void a(boolean z, RenderView renderView) {
        boolean z2 = this.g.k.j;
        for (bq bqVar : this.L) {
            if (z2 && 3 == bqVar.a) {
                try {
                    AbstractAvidAdSession<WebView> abstractAvidAdSessionA = v.a(a(), z, (String) bqVar.b.get("creativeType"), renderView);
                    if (abstractAvidAdSessionA != null) {
                        bqVar.b.put("avidAdSession", abstractAvidAdSessionA);
                        bqVar.b.put("deferred", Boolean.valueOf(z));
                    }
                } catch (Exception e) {
                    new StringBuilder("Setting up impression tracking for IAS encountered an unexpected error: ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                }
            }
        }
    }

    public void H() {
        d dVar;
        if (1 != this.a || (dVar = this.q) == null) {
            return;
        }
        dVar.a(this);
    }

    public void b(InMobiAdRequestStatus inMobiAdRequestStatus) {
        d dVar;
        if (1 != this.a || (dVar = this.q) == null) {
            return;
        }
        dVar.a(this, inMobiAdRequestStatus);
    }

    static /* synthetic */ void a(i iVar, byte[] bArr, com.inmobi.ads.b.a aVar) {
        g gVar;
        try {
            iVar.a("AdLoadWithResponseRequested", aVar.c);
            iVar.a = 1;
            if (aVar.d == null) {
                throw new IllegalStateException("GMARequest is null.");
            }
            com.inmobi.ads.b.b bVar = aVar.d;
            byte[] bArrA = bVar.a.a(bArr);
            if (bArrA == null || bArrA.length == 0) {
                gVar = null;
            } else {
                com.inmobi.commons.core.network.d dVar = new com.inmobi.commons.core.network.d();
                dVar.b(bArrA);
                gVar = new g(bVar.a, dVar);
            }
            if (gVar == null) {
                throw new IllegalStateException("Unable to decrypt response.");
            }
            aVar.a.i().a(gVar);
        } catch (Exception unused) {
            iVar.s.post(new Runnable() { // from class: com.inmobi.ads.i.16
                @Override // java.lang.Runnable
                public final void run() {
                    i.this.a(new InMobiAdRequestStatus(InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR), true);
                }
            });
        }
    }

    static /* synthetic */ void N() {
        com.inmobi.commons.core.utilities.uid.c.a();
        com.inmobi.commons.core.utilities.uid.c.c();
    }

    static /* synthetic */ void e(i iVar) {
        iVar.C();
        iVar.G.sendEmptyMessageDelayed(0, iVar.g.i.a * 1000);
    }
}
