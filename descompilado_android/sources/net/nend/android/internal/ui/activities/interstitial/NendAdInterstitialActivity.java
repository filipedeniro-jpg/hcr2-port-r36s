package net.nend.android.internal.ui.activities.interstitial;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import net.nend.android.NendAdInterstitial;
import net.nend.android.internal.ui.views.b.a;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdInterstitialActivity extends Activity {
    private a a;
    private a.InterfaceC0115a b = new a.InterfaceC0115a() { // from class: net.nend.android.internal.ui.activities.interstitial.NendAdInterstitialActivity.1
        @Override // net.nend.android.internal.ui.views.b.a.InterfaceC0115a
        public void a() {
            NendAdInterstitialActivity.this.finish();
        }
    };

    public static Bundle newBundle(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("ARGS_SPOT_ID", i);
        return bundle;
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.a = NendAdInterstitial.getInterstitialAdView(getIntent().getIntExtra("ARGS_SPOT_ID", -1));
        a aVar = this.a;
        if (aVar != null && aVar.getParent() == null) {
            this.a.setDismissDelegate(this.b);
            this.a.setOrientation(getResources().getConfiguration().orientation);
            setContentView(this.a, new ViewGroup.LayoutParams(-1, -1));
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        this.a.d();
        super.onBackPressed();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        a aVar = this.a;
        if (aVar != null) {
            aVar.setDismissDelegate(null);
        }
    }
}
