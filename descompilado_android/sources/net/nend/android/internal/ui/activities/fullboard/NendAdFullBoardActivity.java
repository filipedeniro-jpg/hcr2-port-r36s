package net.nend.android.internal.ui.activities.fullboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.concurrent.atomic.AtomicInteger;
import net.nend.android.NendAdFullBoardView;
import net.nend.android.NendAdNative;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdFullBoardActivity extends Activity {
    private NendAdNative a;
    private int b;
    private int c;
    private int d;
    private NendAdFullBoardView.OnAdClickListener e = new NendAdFullBoardView.OnAdClickListener() { // from class: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity.1
        @Override // net.nend.android.NendAdFullBoardView.OnAdClickListener
        public void onClickAd() {
            a.d(NendAdFullBoardActivity.this.d);
        }
    };

    public interface b {
        void a();

        void b();

        void c();
    }

    public static Bundle newBundle(NendAdNative nendAdNative, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("NendAdFullBoardNativeAd", nendAdNative);
        bundle.putInt("NendAdFullBoardAdImageKey", i);
        bundle.putInt("NendAdFullBoardLogoImageKey", i2);
        bundle.putInt("NendAdFullBoardListenerKey", i3);
        return bundle;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c cVar = (c) getLastNonConfigurationInstance();
        if (cVar != null) {
            this.a = cVar.a;
            this.b = cVar.b;
            this.c = cVar.c;
            this.d = cVar.d;
        } else if (bundle == null) {
            Intent intent = getIntent();
            if (intent == null || intent.getParcelableExtra("NendAdFullBoardNativeAd") == null) {
                finish();
                return;
            }
            this.a = (NendAdNative) intent.getParcelableExtra("NendAdFullBoardNativeAd");
            this.b = intent.getIntExtra("NendAdFullBoardAdImageKey", -1);
            this.c = intent.getIntExtra("NendAdFullBoardLogoImageKey", -1);
            this.d = intent.getIntExtra("NendAdFullBoardListenerKey", -1);
            a.b(this.d);
        } else {
            this.a = (NendAdNative) bundle.getParcelable("NendAdFullBoardNativeAd");
            this.b = bundle.getInt("NendAdFullBoardAdImageKey");
            this.c = bundle.getInt("NendAdFullBoardLogoImageKey");
            this.d = bundle.getInt("NendAdFullBoardListenerKey");
        }
        b();
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("NendAdFullBoardNativeAd", this.a);
        bundle.putInt("NendAdFullBoardAdImageKey", this.b);
        bundle.putInt("NendAdFullBoardLogoImageKey", this.c);
        bundle.putInt("NendAdFullBoardListenerKey", this.d);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public Object onRetainNonConfigurationInstance() {
        return new c(this.a, this.b, this.c, this.d);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        a();
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        d.b(this.b);
        d.b(this.c);
        a.c(this.d);
        a.a(this.d);
    }

    private void b() {
        NendAdFullBoardView nendAdFullBoardView = (NendAdFullBoardView) LayoutInflater.from(this).inflate(n.c(this, "activity_nend_ad_full_board"), (ViewGroup) null);
        nendAdFullBoardView.setNativeAd(this.a, d.a(this.b), d.a(this.c));
        nendAdFullBoardView.setOnAdClickListener(this.e);
        nendAdFullBoardView.enableCloseButton(new View.OnClickListener() { // from class: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NendAdFullBoardActivity.this.a();
                NendAdFullBoardActivity.this.finish();
            }
        });
        setContentView(nendAdFullBoardView);
    }

    private static class c {
        final NendAdNative a;
        final int b;
        final int c;
        final int d;

        private c(NendAdNative nendAdNative, int i, int i2, int i3) {
            this.a = nendAdNative;
            this.b = i;
            this.c = i2;
            this.d = i3;
        }
    }

    public static class a {
        private static SparseArray<b> a = new SparseArray<>();

        public static void a(int i, b bVar) {
            a.append(i, bVar);
        }

        public static void a(int i) {
            a.remove(i);
        }

        static void b(int i) {
            b bVar = a.get(i);
            if (bVar != null) {
                bVar.a();
            }
        }

        static void c(int i) {
            b bVar = a.get(i);
            if (bVar != null) {
                bVar.b();
            }
        }

        static void d(int i) {
            b bVar = a.get(i);
            if (bVar != null) {
                bVar.c();
            }
        }
    }

    public static class d {
        private static AtomicInteger a = new AtomicInteger();
        private static SparseArray<Bitmap> b = new SparseArray<>();

        public static int a(Bitmap bitmap) {
            int andIncrement = a.getAndIncrement();
            b.put(andIncrement, bitmap);
            return andIncrement;
        }

        public static Bitmap a(int i) {
            return b.get(i);
        }

        public static void b(int i) {
            b.remove(i);
        }
    }
}
