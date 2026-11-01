package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import net.nend.android.internal.b.c.c;
import net.nend.android.internal.d.k;
import net.nend.android.internal.ui.activities.video.NendAdRewardedVideoActivity;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdRewardedVideo extends a<c, NendAdRewardedListener> {
    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ boolean isLoaded() {
        return super.isLoaded();
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void loadAd() {
        super.loadAd();
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void releaseAd() {
        super.releaseAd();
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void setLocationEnabled(boolean z) {
        super.setLocationEnabled(z);
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void setMediationName(String str) {
        super.setMediationName(str);
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        super.setUserFeature(nendAdUserFeature);
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void setUserId(String str) {
        super.setUserId(str);
    }

    @Override // net.nend.android.a, net.nend.android.NendAdVideo
    public /* bridge */ /* synthetic */ void showAd(Activity activity) {
        super.showAd(activity);
    }

    public NendAdRewardedVideo(Context context, int i, String str) {
        super(context, i, str);
    }

    public void setAdListener(NendAdRewardedListener nendAdRewardedListener) {
        this.h = nendAdRewardedListener;
    }

    @Override // net.nend.android.a
    protected void a(boolean z) {
        if (z && this.h != 0) {
            ((NendAdRewardedListener) this.h).onRewarded(this, new NendAdRewardItem(((c) this.f).h, ((c) this.f).i));
        }
        super.a(z);
    }

    @Override // net.nend.android.a
    k<c> a() {
        return this.mVideoAdLoader.b(this.a, this.b, this.d, this.e);
    }

    @Override // net.nend.android.a
    Intent a(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) NendAdRewardedVideoActivity.class);
        intent.putExtras(NendAdRewardedVideoActivity.newBundle((c) this.f, this.j, this.a));
        return intent;
    }
}
