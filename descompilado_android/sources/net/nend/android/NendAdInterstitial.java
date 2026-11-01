package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.my.target.i;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import net.nend.android.internal.ui.activities.interstitial.NendAdInterstitialActivity;
import net.nend.android.internal.utilities.c;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.m;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdInterstitial {
    private static int b = Integer.MIN_VALUE;
    private static OnCompletionListener c = null;
    public static boolean isAutoReloadEnabled = true;
    private static a.b d = new a.b() { // from class: net.nend.android.NendAdInterstitial.1
        @Override // net.nend.android.NendAdInterstitial.a.b
        public void a(int i, NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
            if (NendAdInterstitial.c == null) {
                return;
            }
            if (NendAdInterstitial.c instanceof OnCompletionListenerSpot) {
                ((OnCompletionListenerSpot) NendAdInterstitial.c).onCompletion(nendAdInterstitialStatusCode, i);
            } else {
                NendAdInterstitial.c.onCompletion(nendAdInterstitialStatusCode);
            }
        }
    };

    @VisibleForTesting
    static SparseArray<a> a = new SparseArray<>();

    public enum NendAdInterstitialClickType {
        DOWNLOAD,
        CLOSE,
        INFORMATION
    }

    public enum NendAdInterstitialShowResult {
        AD_SHOW_SUCCESS,
        AD_LOAD_INCOMPLETE,
        AD_REQUEST_INCOMPLETE,
        AD_DOWNLOAD_INCOMPLETE,
        AD_FREQUENCY_NOT_RECHABLE,
        AD_SHOW_ALREADY
    }

    public enum NendAdInterstitialStatusCode {
        SUCCESS,
        INVALID_RESPONSE_TYPE,
        FAILED_AD_REQUEST,
        FAILED_AD_INCOMPLETE,
        FAILED_AD_DOWNLOAD
    }

    public interface OnClickListener {
        void onClick(NendAdInterstitialClickType nendAdInterstitialClickType);
    }

    public interface OnClickListenerSpot extends OnClickListener {
        void onClick(NendAdInterstitialClickType nendAdInterstitialClickType, int i);
    }

    public interface OnCompletionListener {
        void onCompletion(NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
    }

    public interface OnCompletionListenerSpot extends OnCompletionListener {
        void onCompletion(NendAdInterstitialStatusCode nendAdInterstitialStatusCode, int i);
    }

    public static void loadAd(Context context, String str, int i) {
        e.a(context);
        a aVar = a.get(i);
        if (aVar == null) {
            aVar = new a(i, str, d);
            a.put(i, aVar);
        }
        aVar.a(context);
        b = i;
    }

    public static NendAdInterstitialShowResult showAd(Activity activity) {
        return a(activity, b, (OnClickListener) null);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, OnClickListener onClickListener) {
        return a(activity, b, onClickListener);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, int i) {
        return a(activity, i, (OnClickListener) null);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, int i, OnClickListener onClickListener) {
        return a(activity, i, onClickListener);
    }

    public static boolean dismissAd() {
        boolean z = false;
        for (int i = 0; i < a.size(); i++) {
            SparseArray<a> sparseArray = a;
            if (sparseArray.get(sparseArray.keyAt(i)).a()) {
                z = true;
            }
        }
        return z;
    }

    public static void setListener(OnCompletionListener onCompletionListener) {
        c = onCompletionListener;
    }

    private static NendAdInterstitialShowResult a(Activity activity, int i, OnClickListener onClickListener) {
        if (b()) {
            return NendAdInterstitialShowResult.AD_SHOW_ALREADY;
        }
        a aVar = a.get(i);
        if (aVar == null) {
            return NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
        }
        return aVar.a(activity, onClickListener);
    }

    private static boolean b() {
        for (int i = 0; i < a.size(); i++) {
            SparseArray<a> sparseArray = a;
            if (sparseArray.get(sparseArray.keyAt(i)).c()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(NendAdInterstitialClickType nendAdInterstitialClickType, OnClickListener onClickListener, int i) {
        if (onClickListener != null) {
            if (onClickListener instanceof OnClickListenerSpot) {
                ((OnClickListenerSpot) onClickListener).onClick(nendAdInterstitialClickType, i);
            } else {
                onClickListener.onClick(nendAdInterstitialClickType);
            }
        }
    }

    public static net.nend.android.internal.ui.views.b.a getInterstitialAdView(int i) {
        a aVar = a.get(i);
        if (aVar != null) {
            return aVar.g();
        }
        return null;
    }

    private static class a implements g.b<net.nend.android.internal.c.d.b> {
        private final String a;
        private final int b;
        private Context c;
        private b d;
        private net.nend.android.internal.ui.views.b.a e;
        private net.nend.android.internal.c.d.a f;
        private int g;
        private String h;
        private String i;
        private String j;
        private EnumC0105a k;

        /* JADX INFO: renamed from: net.nend.android.NendAdInterstitial$a$a, reason: collision with other inner class name */
        private enum EnumC0105a {
            LOADING,
            LOADED,
            NOT_LOADED
        }

        private interface b {
            void a(int i, NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
        }

        private a(int i, String str, b bVar) {
            this.k = EnumC0105a.NOT_LOADED;
            this.b = m.a(i, k.ERR_INVALID_SPOT_ID.a("spot id : " + i));
            this.a = (String) m.a(str, (Object) k.ERR_INVALID_API_KEY.a("api key : " + str));
            this.d = bVar;
        }

        private static String a(int i) {
            return String.format(Locale.US, "%s_%d", i.aa, Integer.valueOf(i));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Context context) {
            this.c = (Context) m.a(context);
            if (this.k == EnumC0105a.LOADING) {
                j.c("Ad is loading.");
                return;
            }
            this.k = EnumC0105a.LOADING;
            this.f = new net.nend.android.internal.c.d.a(this.c, this.b, this.a);
            g.a().a(new g.f(this), new g.a<net.nend.android.internal.c.d.b>() { // from class: net.nend.android.NendAdInterstitial.a.1
                @Override // net.nend.android.internal.utilities.g.a
                public void a(net.nend.android.internal.c.d.b bVar, Exception exc) {
                    a.this.a(bVar);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public NendAdInterstitialShowResult a(Activity activity, OnClickListener onClickListener) {
            if (this.k != EnumC0105a.LOADED) {
                a(activity.getApplicationContext());
                return e();
            }
            if (!d()) {
                b();
                return NendAdInterstitialShowResult.AD_DOWNLOAD_INCOMPLETE;
            }
            if (c(activity.getApplicationContext())) {
                if (b(activity, onClickListener)) {
                    f();
                    return NendAdInterstitialShowResult.AD_SHOW_SUCCESS;
                }
                a(activity.getApplicationContext());
                return NendAdInterstitialShowResult.AD_REQUEST_INCOMPLETE;
            }
            return NendAdInterstitialShowResult.AD_FREQUENCY_NOT_RECHABLE;
        }

        private boolean b(final Activity activity, final OnClickListener onClickListener) {
            if (this.e.b()) {
                return false;
            }
            this.e.setOnClickListener(new net.nend.android.internal.ui.views.b.a.c() { // from class: net.nend.android.NendAdInterstitial.a.2
                @Override // net.nend.android.internal.ui.views.b.a.c
                public void a(NendAdInterstitialClickType nendAdInterstitialClickType) {
                    NendAdInterstitial.b(nendAdInterstitialClickType, onClickListener, a.this.b);
                    a.this.b(activity.getApplicationContext());
                }
            });
            activity.startActivity(new Intent(activity, (Class<?>) NendAdInterstitialActivity.class).putExtras(NendAdInterstitialActivity.newBundle(this.b)));
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            net.nend.android.internal.ui.views.b.a aVar = this.e;
            if (aVar == null || !aVar.a()) {
                return false;
            }
            b(this.e.getContext());
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(Context context) {
            if (NendAdInterstitial.isAutoReloadEnabled) {
                a(context);
            } else {
                NendAdInterstitial.a.delete(this.b);
            }
        }

        private void b() {
            net.nend.android.internal.ui.views.b.a aVar = this.e;
            if (aVar != null) {
                aVar.a(this.i + "&ad=" + this.j + "&dn=");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            net.nend.android.internal.ui.views.b.a aVar = this.e;
            return aVar != null && aVar.b();
        }

        private boolean d() {
            net.nend.android.internal.ui.views.b.a aVar = this.e;
            return aVar != null && aVar.c();
        }

        private NendAdInterstitialShowResult e() {
            net.nend.android.internal.ui.views.b.a aVar = this.e;
            return aVar != null ? aVar.getStatus() : NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
        }

        private boolean c(Context context) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editorEdit = defaultSharedPreferences.edit();
            String strA = a(this.b);
            int i = defaultSharedPreferences.getInt(strA, 0);
            if (i >= this.g) {
                editorEdit.putInt(strA, 0);
                editorEdit.apply();
                return true;
            }
            editorEdit.putInt(strA, i + 1);
            editorEdit.apply();
            return false;
        }

        private void f() {
            g.a().a(new g.f(this.h + "&ad=" + this.j));
        }

        @Override // net.nend.android.internal.utilities.g.b
        public String getRequestUrl() {
            net.nend.android.internal.c.d.a aVar = this.f;
            return aVar != null ? aVar.b(c.c(this.c)) : "";
        }

        @Override // net.nend.android.internal.utilities.g.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public net.nend.android.internal.c.d.b makeResponse(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            try {
                return new net.nend.android.internal.c.d.c(this.c).a(new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                j.c(k.ERR_HTTP_REQUEST, e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(net.nend.android.internal.c.d.b bVar) {
            if (bVar != null) {
                if (bVar.j() != null) {
                    this.d.a(this.b, bVar.j());
                    this.k = EnumC0105a.NOT_LOADED;
                    return;
                }
                this.h = bVar.a();
                this.g = bVar.c();
                this.i = bVar.b();
                this.j = bVar.e();
                this.e = new net.nend.android.internal.ui.views.b.a(this.c, bVar);
                this.e.setOnCompletionListener(new net.nend.android.internal.ui.views.b.a.b() { // from class: net.nend.android.NendAdInterstitial.a.3
                    @Override // net.nend.android.internal.ui.views.b.a.b
                    public void a(NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
                        a.this.k = EnumC0105a.LOADED;
                        a.this.d.a(a.this.b, nendAdInterstitialStatusCode);
                    }
                });
                b();
                return;
            }
            this.d.a(this.b, NendAdInterstitialStatusCode.FAILED_AD_REQUEST);
            this.k = EnumC0105a.NOT_LOADED;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public net.nend.android.internal.ui.views.b.a g() {
            return this.e;
        }
    }
}
