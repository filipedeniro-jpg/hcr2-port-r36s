package net.nend.android;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.LinkedBlockingQueue;
import net.nend.android.internal.b.c.b;
import net.nend.android.internal.c.f.d;
import net.nend.android.internal.d.k;
import net.nend.android.internal.d.l;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.m;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdNativeVideoLoader {

    @VisibleForTesting
    k<b> a;

    @VisibleForTesting
    BlockingQueue<Callback> b;
    private final Context c;
    private final int d;
    private final d e;
    private final net.nend.android.internal.d.a f;

    public interface Callback {
        void onFailure(int i);

        void onSuccess(NendAdNativeVideo nendAdNativeVideo);
    }

    public NendAdNativeVideoLoader(Context context, int i, String str) {
        this(context, i, str, NendAdNativeVideo.VideoClickOption.FullScreen);
    }

    public NendAdNativeVideoLoader(Context context, int i, String str, NendAdNativeVideo.VideoClickOption videoClickOption) {
        this.b = new LinkedBlockingQueue();
        this.c = (Context) m.a(context);
        this.d = m.a(i, net.nend.android.internal.utilities.k.ERR_INVALID_SPOT_ID.a("spot id : " + i));
        this.e = new d(this.c, this.d, (String) m.a(str, (Object) net.nend.android.internal.utilities.k.ERR_INVALID_API_KEY.a("api key : " + str)), videoClickOption);
        this.f = new net.nend.android.internal.d.a(this.c.getMainLooper());
        e.a(this.c);
        l.a(g.a().b(), new g.d(this.c)).a(new net.nend.android.internal.d.b<String, Throwable>() { // from class: net.nend.android.NendAdNativeVideoLoader.1
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

    private boolean a() {
        k<b> kVar = this.a;
        if (kVar == null || !kVar.b()) {
            return false;
        }
        j.c("Ex loading of NendAdNativeVideo is not completed yet.");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Callback callbackPoll = this.b.poll();
        if (callbackPoll != null) {
            loadAd(callbackPoll);
        }
    }

    public void loadAd(final Callback callback) {
        if (a()) {
            j.b("Added your loading request to queue...");
            this.b.add(callback);
        } else {
            this.a = this.e.b();
            this.a.a(this.f).a(new net.nend.android.internal.d.d<b>() { // from class: net.nend.android.NendAdNativeVideoLoader.3
                @Override // net.nend.android.internal.d.d
                public void a(b bVar) {
                    callback.onSuccess(NendAdNativeVideoLoader.this.a(bVar));
                    NendAdNativeVideoLoader.this.b();
                }
            }).b(new net.nend.android.internal.d.d<Throwable>() { // from class: net.nend.android.NendAdNativeVideoLoader.2
                @Override // net.nend.android.internal.d.d
                public void a(Throwable th) {
                    if (th instanceof CancellationException) {
                        return;
                    }
                    j.b("Failed to load ad.", th);
                    if (th instanceof net.nend.android.internal.a.a) {
                        net.nend.android.internal.a.a aVar = (net.nend.android.internal.a.a) th;
                        callback.onFailure(aVar.a());
                        net.nend.android.internal.utilities.b.a("FailedToLoadEvent", Integer.valueOf(aVar.a()), aVar.b());
                    } else {
                        callback.onFailure(NendVideoAdClientError.FAILED_INTERNAL.getCode());
                        net.nend.android.internal.utilities.b.a("FailedToLoadEvent", Integer.valueOf(NendVideoAdClientError.FAILED_INTERNAL.getCode()), NendVideoAdClientError.FAILED_INTERNAL.getMessage());
                    }
                    NendAdNativeVideoLoader.this.b();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public NendAdNativeVideo a(b bVar) {
        NendAdNativeVideo nendAdNativeVideoA;
        if (bVar == null) {
            return null;
        }
        if (bVar.p != null) {
            nendAdNativeVideoA = new NendAdNativeVideo.a().a(bVar.p).a();
        } else {
            NendAdNativeVideo nendAdNativeVideoA2 = new NendAdNativeVideo.a().a(bVar).a(this.e.a()).a(this.d).a(net.nend.android.internal.utilities.a.a.a(bVar.i)).a();
            this.e.a(bVar.h);
            nendAdNativeVideoA = nendAdNativeVideoA2;
        }
        nendAdNativeVideoA.a(new WeakReference<>(this.c));
        nendAdNativeVideoA.b(new WeakReference<>(this.e));
        return nendAdNativeVideoA;
    }

    public void setFillerImageNativeAd(int i, String str) {
        this.e.a(i, str);
    }

    public void setMediationName(String str) {
        this.e.a(str);
    }

    public void setUserId(String str) {
        this.e.b(str);
    }

    public void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        this.e.a(nendAdUserFeature);
    }

    public void setLocationEnabled(boolean z) {
        this.e.a(z);
    }

    public void releaseLoader() {
        k<b> kVar = this.a;
        if (kVar != null && kVar.b()) {
            this.a.c();
        }
        this.a = null;
        this.b.clear();
    }
}
