package net.nend.android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import net.nend.android.internal.c.c.b;
import net.nend.android.internal.c.c.c;
import net.nend.android.internal.utilities.e;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;
import net.nend.android.internal.utilities.l;
import net.nend.android.internal.utilities.m;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdIconLoader implements NendAdIconView.a, g.b<b> {
    static final /* synthetic */ boolean a = !NendAdIconLoader.class.desiredAssertionStatus();
    private Context c;
    private List<NendAdIconView> d;
    private boolean e;
    private Handler f;
    private int i;
    private Future<b> j;
    private net.nend.android.internal.c.c.a k;
    private OnClickListener l;
    private OnInformationClickListener m;
    private OnFailedListener n;
    private OnReceiveListener o;
    private int b = 60;
    private boolean g = false;
    private boolean h = true;

    public interface OnClickListener {
        void onClick(NendAdIconView nendAdIconView);
    }

    public interface OnFailedListener {
        void onFailedToReceiveAd(NendIconError nendIconError);
    }

    public interface OnInformationClickListener {
        void onClickInformation(NendAdIconView nendAdIconView);
    }

    public interface OnReceiveListener {
        void onReceiveAd(NendAdIconView nendAdIconView);
    }

    public NendAdIconLoader(Context context, int i, String str) {
        this.c = (Context) m.a(context);
        e.a(this.c);
        this.k = new net.nend.android.internal.c.c.a(this.c, i, str);
        this.i = i;
        this.d = new ArrayList();
        this.f = new Handler(new Handler.Callback() { // from class: net.nend.android.NendAdIconLoader.1
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (NendAdIconLoader.this.d.size() > 0) {
                    NendAdIconLoader.this.k.a(NendAdIconLoader.this.d.size());
                    g.f fVar = new g.f(NendAdIconLoader.this);
                    NendAdIconLoader.this.j = g.a().a(fVar, new g.a<b>() { // from class: net.nend.android.NendAdIconLoader.1.1
                        @Override // net.nend.android.internal.utilities.g.a
                        public void a(b bVar, Exception exc) {
                            NendAdIconLoader.this.a(bVar);
                        }
                    });
                    return true;
                }
                j.a("NendAdIconView is nothing.");
                return true;
            }
        });
    }

    public void addIconView(NendAdIconView nendAdIconView) {
        if (nendAdIconView == null || this.d.size() >= 8 || this.d.contains(nendAdIconView)) {
            return;
        }
        if (this.g) {
            loadAd();
        }
        this.h = true;
        this.d.add(nendAdIconView);
        nendAdIconView.setListner(this);
    }

    public void removeIconView(NendAdIconView nendAdIconView) {
        if (nendAdIconView != null) {
            nendAdIconView.a();
            this.d.remove(nendAdIconView);
            if (this.d.size() == 0) {
                pause();
            }
        }
    }

    public int getIconCount() {
        return this.d.size();
    }

    public void loadAd() {
        this.f.removeMessages(719);
        this.f.sendEmptyMessage(719);
        this.g = true;
    }

    public void resume() {
        this.h = true;
        a();
    }

    private void a() {
        if (!this.h || this.f.hasMessages(719)) {
            return;
        }
        this.f.sendEmptyMessageDelayed(719, this.b * 1000);
    }

    public void pause() {
        this.h = false;
        b();
    }

    private void b() {
        Future<b> future = this.j;
        if (future != null) {
            future.cancel(true);
        }
        Handler handler = this.f;
        if (handler != null) {
            handler.removeMessages(719);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(b bVar) {
        if (bVar != null) {
            this.b = l.a(bVar.a());
            String strB = bVar.b();
            ArrayList<net.nend.android.internal.a> arrayListC = bVar.c();
            String str = strB;
            for (int i = 0; i < this.d.size(); i++) {
                if (arrayListC.size() > i) {
                    net.nend.android.internal.a aVar = arrayListC.get(i);
                    if (!TextUtils.isEmpty(str)) {
                        str = str + String.format("&ic[]=%s", aVar.i());
                    }
                    this.d.get(i).a(aVar, this.i);
                }
            }
            g.a().a(new g.f(str));
        } else {
            j.a("onFailedToImageDownload!");
            if (this.n != null) {
                NendIconError nendIconError = new NendIconError();
                nendIconError.a(this);
                nendIconError.setErrorType(0);
                nendIconError.setNendError(NendAdView.NendError.FAILED_AD_REQUEST);
                this.n.onFailedToReceiveAd(nendIconError);
            }
        }
        if (!this.h || this.f.hasMessages(719)) {
            return;
        }
        this.f.sendEmptyMessageDelayed(719, this.b * 1000);
    }

    @Override // net.nend.android.internal.utilities.g.b
    public b makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new c(this.c, this.d.size()).a(new String(bArr, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            if (!a) {
                throw new AssertionError();
            }
            j.a(k.ERR_HTTP_REQUEST, e);
            return null;
        }
    }

    @Override // net.nend.android.internal.utilities.g.b
    public String getRequestUrl() {
        return this.k.b(net.nend.android.internal.utilities.c.c(this.c));
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.l = onClickListener;
    }

    @Override // net.nend.android.NendAdIconView.a
    public void onClick(View view) {
        OnClickListener onClickListener = this.l;
        if (onClickListener != null) {
            onClickListener.onClick((NendAdIconView) view);
        }
    }

    public void setOnInformationClickListener(OnInformationClickListener onInformationClickListener) {
        this.m = onInformationClickListener;
    }

    @Override // net.nend.android.NendAdIconView.a
    public void onClickInformation(View view) {
        OnInformationClickListener onInformationClickListener = this.m;
        if (onInformationClickListener != null) {
            onInformationClickListener.onClickInformation((NendAdIconView) view);
        }
    }

    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.o = onReceiveListener;
    }

    @Override // net.nend.android.NendAdIconView.a
    public void onReceive(View view) {
        OnReceiveListener onReceiveListener = this.o;
        if (onReceiveListener != null) {
            onReceiveListener.onReceiveAd((NendAdIconView) view);
        }
    }

    public void setOnFailedListener(OnFailedListener onFailedListener) {
        this.n = onFailedListener;
    }

    @Override // net.nend.android.NendAdIconView.a
    public void onFailedToReceive(NendIconError nendIconError) {
        OnFailedListener onFailedListener = this.n;
        if (onFailedListener != null) {
            onFailedListener.onFailedToReceiveAd(nendIconError);
        }
    }

    @Override // net.nend.android.NendAdIconView.a
    public void onWindowFocusChanged(boolean z) {
        if (z) {
            if (this.e) {
                return;
            }
            this.e = true;
            resume();
            return;
        }
        if (this.e) {
            this.e = false;
            pause();
        }
    }
}
