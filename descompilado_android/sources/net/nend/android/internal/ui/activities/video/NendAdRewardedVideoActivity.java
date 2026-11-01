package net.nend.android.internal.ui.activities.video;

import android.os.Bundle;
import android.os.ResultReceiver;
import net.nend.android.internal.b.c.c;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdRewardedVideoActivity extends a<c> {
    public static Bundle newBundle(c cVar, ResultReceiver resultReceiver, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("videoAd", cVar);
        bundle.putParcelable("resultReceiver", resultReceiver);
        bundle.putInt("spotId", i);
        return bundle;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.a.getVisibility() == 8) {
            c();
            super.onBackPressed();
        }
    }

    @Override // net.nend.android.internal.ui.activities.video.a, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.g = false;
        super.onCreate(bundle);
    }
}
