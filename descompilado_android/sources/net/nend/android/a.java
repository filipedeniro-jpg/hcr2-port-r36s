package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import net.nend.android.NendAdVideoListener;
import net.nend.android.internal.b.c.d;
import net.nend.android.internal.c.f.f;
import net.nend.android.internal.d.b;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.m;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* JADX INFO: compiled from: NendAdVideoImpl.java */
/* JADX INFO: loaded from: classes2.dex */
abstract class a<Ad extends d, Listener extends NendAdVideoListener> implements NendAdVideo {
    protected final int a;
    protected final String b;
    Context c;
    String d;
    String e;
    Ad f;
    boolean g;

    @Nullable
    Listener h;

    @Nullable
    k<Ad> i;
    ResultReceiver j = new ResultReceiver(new Handler(Looper.getMainLooper())) { // from class: net.nend.android.a.1
        @Override // android.os.ResultReceiver
        @VisibleForTesting
        protected void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            j.a("resultCode: " + i);
            switch (i) {
                case 1:
                    a aVar = a.this;
                    aVar.g = false;
                    aVar.a((d) null);
                    if (a.this.h != null) {
                        a.this.h.onClosed(a.this);
                    }
                    break;
                case 2:
                    if (a.this.h != null) {
                        a.this.h.onShown(a.this);
                    }
                    break;
                case 3:
                    if (a.this.h != null) {
                        a.this.h.onStarted(a.this);
                    }
                    break;
                case 4:
                    a.this.a(bundle.getBoolean(net.nend.android.internal.ui.activities.video.a.RESULT_DATA_KEY_VIDEO_IS_COMPLETION));
                    break;
                case 5:
                    if (a.this.h != null) {
                        a.this.h.onAdClicked(a.this);
                    }
                    break;
                case 6:
                    if (a.this.h != null) {
                        a.this.h.onInformationClicked(a.this);
                    }
                    break;
                case 7:
                    a aVar2 = a.this;
                    aVar2.g = false;
                    if (aVar2.h != null) {
                        a.this.h.onFailedToPlay(a.this);
                    }
                    break;
            }
        }
    };
    private net.nend.android.internal.d.a k;

    @VisibleForTesting
    public f mVideoAdLoader;

    abstract Intent a(Activity activity);

    abstract k<Ad> a();

    a(Context context, int i, String str) {
        this.c = (Context) m.a(context, "Context is null.");
        this.a = m.a(i, net.nend.android.internal.utilities.k.ERR_INVALID_SPOT_ID.a("spot id : " + i));
        this.b = (String) m.a(str, (Object) net.nend.android.internal.utilities.k.ERR_INVALID_API_KEY.a("api key : " + str));
        this.mVideoAdLoader = new f(this.c);
        this.k = new net.nend.android.internal.d.a(this.c.getMainLooper());
        e.a(this.c);
        l.a(g.a().b(), new g.d(this.c)).a(new b<String, Throwable>() { // from class: net.nend.android.a.2
            @Override // net.nend.android.internal.d.b
            public void a(String str2, Throwable th) {
                if (TextUtils.isEmpty(str2)) {
                    j.a("Cannot get Google Advertising ID...", th);
                    return;
                }
                j.b("Google Advertising ID = " + str2);
            }
        });
    }

    protected void a(boolean z) {
        Listener listener = this.h;
        if (listener != null) {
            if (z) {
                listener.onCompleted(this);
            } else {
                listener.onStopped(this);
            }
        }
    }

    @VisibleForTesting
    void a(Ad ad) {
        Ad ad2 = this.f;
        if (ad2 != null) {
            this.mVideoAdLoader.a(ad2);
        }
        this.f = ad;
    }

    @VisibleForTesting
    boolean c() {
        k<Ad> kVar = this.i;
        if (kVar != null && kVar.b()) {
            j.c("NendAdVideo is loading.");
            return true;
        }
        if (!this.g) {
            return false;
        }
        j.c("NendAdVideo is playing.");
        return true;
    }

    protected void b(Activity activity) {
        activity.startActivity(a(activity));
    }

    @Override // net.nend.android.NendAdVideo
    public void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        this.mVideoAdLoader.a(nendAdUserFeature);
    }

    @Override // net.nend.android.NendAdVideo
    public void setLocationEnabled(boolean z) {
        this.mVideoAdLoader.a(z);
    }

    @Override // net.nend.android.NendAdVideo
    public void loadAd() {
        if (c()) {
            return;
        }
        this.i = a();
        this.i.a(this.k).a((net.nend.android.internal.d.d<? super Ad>) new net.nend.android.internal.d.d<Ad>() { // from class: net.nend.android.a.4
            @Override // net.nend.android.internal.d.d
            public void a(Ad ad) {
                a.this.a(ad);
                if (a.this.h != null) {
                    a.this.h.onLoaded(a.this);
                }
            }
        }).b(new net.nend.android.internal.d.d<Throwable>() { // from class: net.nend.android.a.3
            @Override // net.nend.android.internal.d.d
            public void a(Throwable th) {
                a.this.a((d) null);
                j.b("Failed to load ad.", th);
                if (a.this.h != null) {
                    if (th instanceof net.nend.android.internal.a.a) {
                        net.nend.android.internal.a.a aVar = (net.nend.android.internal.a.a) th;
                        a.this.h.onFailedToLoad(a.this, aVar.a());
                        net.nend.android.internal.utilities.b.a("FailedToLoadEvent", Integer.valueOf(aVar.a()), aVar.b());
                    } else {
                        a.this.h.onFailedToLoad(a.this, NendVideoAdClientError.FAILED_INTERNAL.getCode());
                        net.nend.android.internal.utilities.b.a("FailedToLoadEvent", Integer.valueOf(NendVideoAdClientError.FAILED_INTERNAL.getCode()), NendVideoAdClientError.FAILED_INTERNAL.getMessage());
                    }
                }
            }
        });
    }

    @Override // net.nend.android.NendAdVideo
    public void showAd(Activity activity) {
        if (!isLoaded()) {
            j.d("Failed to showAd. loadAd is not complete.");
        } else {
            if (c()) {
                return;
            }
            this.g = true;
            b(activity);
        }
    }

    @Override // net.nend.android.NendAdVideo
    public void releaseAd() {
        this.h = null;
        this.c = null;
        if (this.g) {
            return;
        }
        a((d) null);
        k<Ad> kVar = this.i;
        if (kVar != null && kVar.b()) {
            this.i.c();
        }
        this.i = null;
        this.g = false;
    }

    @Override // net.nend.android.NendAdVideo
    public boolean isLoaded() {
        Ad ad = this.f;
        boolean z = (ad == null || ad.c()) ? false : true;
        if (!z) {
            a((d) null);
        }
        return z;
    }

    @Override // net.nend.android.NendAdVideo
    public void setMediationName(String str) {
        this.d = str;
    }

    @Override // net.nend.android.NendAdVideo
    public void setUserId(String str) {
        this.e = str;
    }
}
