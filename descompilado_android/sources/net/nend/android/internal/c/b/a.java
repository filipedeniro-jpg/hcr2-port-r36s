package net.nend.android.internal.c.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeClient;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdNativeLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {
    private Context a;
    private b b;
    private Handler d = new Handler(Looper.getMainLooper());
    private ExecutorService c = Executors.newSingleThreadExecutor();

    public a(Context context, b bVar) {
        this.a = context;
        this.b = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public g.f<NendAdNative> a() {
        return new g.f<>(new g.b<NendAdNative>() { // from class: net.nend.android.internal.c.b.a.1
            static final /* synthetic */ boolean a = !a.class.desiredAssertionStatus();

            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return a.this.b.b(net.nend.android.internal.utilities.c.c(a.this.a));
            }

            @Override // net.nend.android.internal.utilities.g.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public NendAdNative makeResponse(byte[] bArr) {
                String str;
                NendAdNative nendAdNativeA;
                if (bArr != null) {
                    try {
                        str = new String(bArr, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        if (!a) {
                            throw new AssertionError();
                        }
                        j.a(k.ERR_HTTP_REQUEST, e);
                        str = null;
                    }
                    if (TextUtils.isEmpty(str) || (nendAdNativeA = new c(a.this.a).a(str)) == null) {
                        return null;
                    }
                    nendAdNativeA.setSpotId(a.this.b.j());
                    return nendAdNativeA;
                }
                j.a(k.ERR_INVALID_URL);
                return null;
            }
        });
    }

    public void a(final NendAdNativeClient.Callback callback) {
        this.c.execute(new Runnable() { // from class: net.nend.android.internal.c.b.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    final NendAdNative nendAdNative = (NendAdNative) g.a().a(a.this.a()).get();
                    a.this.d.post(new Runnable() { // from class: net.nend.android.internal.c.b.a.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            NendAdNative nendAdNative2 = nendAdNative;
                            if (nendAdNative2 != null) {
                                if (nendAdNative2.getCampaignId() != null) {
                                    a.this.b.c(nendAdNative.getCampaignId());
                                    callback.onSuccess(nendAdNative);
                                    return;
                                } else {
                                    callback.onFailure(NendAdNativeClient.NendError.FAILED_AD_REQUEST);
                                    return;
                                }
                            }
                            callback.onFailure(NendAdNativeClient.NendError.FAILED_AD_REQUEST);
                        }
                    });
                } catch (InterruptedException | ExecutionException unused) {
                    a.this.d.post(new Runnable() { // from class: net.nend.android.internal.c.b.a.2.2
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onFailure(NendAdNativeClient.NendError.FAILED_AD_REQUEST);
                        }
                    });
                }
            }
        });
    }
}
