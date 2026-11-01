package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import net.nend.android.internal.d.c;
import net.nend.android.internal.d.e;
import net.nend.android.internal.d.g;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdInterstitialVideo extends a<net.nend.android.internal.b.c.a, NendAdVideoListener> {

    @VisibleForTesting
    private int k;
    private String l;
    private boolean m;
    private NendAdFullBoard.FullBoardAdListener n;

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

    public NendAdInterstitialVideo(Context context, int i, String str) {
        super(context, i, str);
        this.m = true;
        this.n = new NendAdFullBoard.FullBoardAdListener() { // from class: net.nend.android.NendAdInterstitialVideo.1
            @Override // net.nend.android.NendAdFullBoard.FullBoardAdListener
            public void onDismissAd(NendAdFullBoard nendAdFullBoard) {
                NendAdInterstitialVideo.this.j.send(1, null);
            }

            @Override // net.nend.android.NendAdFullBoard.FullBoardAdListener
            public void onShowAd(NendAdFullBoard nendAdFullBoard) {
                NendAdInterstitialVideo.this.j.send(2, null);
            }

            @Override // net.nend.android.NendAdFullBoardView.FullBoardAdClickListener
            public void onClickAd(NendAdFullBoard nendAdFullBoard) {
                NendAdInterstitialVideo.this.j.send(5, null);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setAdListener(NendAdVideoListener nendAdVideoListener) {
        this.h = nendAdVideoListener;
    }

    @Override // net.nend.android.a
    k<net.nend.android.internal.b.c.a> a() {
        k<net.nend.android.internal.b.c.a> kVarA = this.mVideoAdLoader.a(this.a, this.b, this.d, this.e);
        if (this.k > 0 && !TextUtils.isEmpty(this.l)) {
            return kVarA.a(new g<Throwable, net.nend.android.internal.b.c.a>() { // from class: net.nend.android.NendAdInterstitialVideo.3
                @Override // net.nend.android.internal.d.g
                public net.nend.android.internal.b.c.a a(Throwable th) {
                    j.b("Failed to load Interstitial Ad. Fallback full board ad.");
                    return null;
                }
            }).a(new c<net.nend.android.internal.b.c.a, Throwable, k<net.nend.android.internal.b.c.a>>() { // from class: net.nend.android.NendAdInterstitialVideo.2
                @Override // net.nend.android.internal.d.c
                public k<net.nend.android.internal.b.c.a> a(net.nend.android.internal.b.c.a aVar, Throwable th) {
                    if (aVar != null) {
                        return l.a(aVar);
                    }
                    return NendAdInterstitialVideo.this.b();
                }
            });
        }
        j.b("You can use fallback option at Interstitial Ad. Let's check the wiki.");
        return kVarA;
    }

    @Override // net.nend.android.a
    Intent a(Activity activity) {
        Intent intent = new Intent(activity, (Class<?>) NendAdInterstitialVideoActivity.class);
        intent.putExtras(NendAdInterstitialVideoActivity.newBundle((net.nend.android.internal.b.c.a) this.f, this.j, this.a, this.m));
        return intent;
    }

    @Override // net.nend.android.a
    protected void b(Activity activity) {
        if (((net.nend.android.internal.b.c.a) this.f).j != null) {
            ((net.nend.android.internal.b.c.a) this.f).j.show(activity);
        } else {
            super.b(activity);
        }
    }

    public boolean isMuteStartPlaying() {
        return this.m;
    }

    public void setMuteStartPlaying(boolean z) {
        this.m = z;
    }

    public void addFallbackFullboard(int i, String str) {
        this.k = i;
        this.l = str;
    }

    @VisibleForTesting
    k<net.nend.android.internal.b.c.a> b() {
        final e eVarA = l.a();
        new NendAdFullBoardLoader(this.c, this.k, this.l).loadAd(new NendAdFullBoardLoader.Callback() { // from class: net.nend.android.NendAdInterstitialVideo.4
            @Override // net.nend.android.NendAdFullBoardLoader.Callback
            public void onSuccess(NendAdFullBoard nendAdFullBoard) {
                nendAdFullBoard.setAdListener(NendAdInterstitialVideo.this.n);
                eVarA.a(net.nend.android.internal.b.c.a.a(nendAdFullBoard));
            }

            @Override // net.nend.android.NendAdFullBoardLoader.Callback
            public void onFailure(NendAdFullBoardLoader.FullBoardAdError fullBoardAdError) {
                eVarA.a((Throwable) new net.nend.android.internal.a.a(NendVideoAdClientError.FAILED_AD_FALLBACK));
            }
        });
        return eVarA.a();
    }
}
