package com.my.target.core.presenters;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.az;
import com.my.target.bt;
import com.my.target.cj;
import com.my.target.dk;
import com.my.target.o;
import com.my.target.s;
import com.my.target.u;
import com.my.target.v;
import com.my.target.z;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: WebViewPresenter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements bt.a, c {

    @Nullable
    private com.my.target.core.models.banners.c O;

    @NonNull
    private final dk V;

    @NonNull
    private final bt aJ;

    @NonNull
    private final String aK;

    @Nullable
    private a aL;

    @Nullable
    private c.a af;

    @NonNull
    private final Context context;

    /* JADX INFO: compiled from: WebViewPresenter.java */
    public interface a {
        void bk();

        void onNoAd(@NonNull String str);
    }

    @NonNull
    public static d b(@NonNull String str, @NonNull Context context) {
        return new d(str, context);
    }

    @VisibleForTesting
    private d(@NonNull bt btVar, @NonNull dk dkVar, @NonNull String str, @NonNull Context context) {
        this.aJ = btVar;
        this.V = dkVar;
        this.context = context;
        this.aK = str;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 1;
        dkVar.addView(this.aJ);
        this.aJ.setLayoutParams(layoutParams);
        this.aJ.setBannerWebViewListener(this);
    }

    private d(@NonNull String str, @NonNull Context context) {
        this(new bt(context), new dk(context), str, context);
    }

    public final void b(@NonNull com.my.target.core.models.sections.b bVar) {
        this.O = bVar.G();
        JSONObject rawData = bVar.getRawData();
        String strA_ = bVar.a_();
        if (rawData == null) {
            e("failed to load, null raw data");
        } else if (strA_ == null) {
            e("failed to load, null html");
        } else {
            this.aJ.a(rawData, strA_);
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void start() {
        try {
            this.aJ.a(new o(this.aK, null, this.context.getResources().getConfiguration().orientation));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void stop() {
        try {
            this.aJ.a(new com.my.target.l(com.my.target.m.at));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void pause() {
        try {
            this.aJ.a(new com.my.target.l("pause"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void resume() {
        try {
            this.aJ.a(new com.my.target.l("resume"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public final dk I() {
        return this.V;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:56:0x00c7  */
    /* JADX WARN: switch over string: strings are not added: [[onExpand], [onCollapse], [onCloseClick], [onAdStop], [onAdPause], [onAdResume], [onAdComplete], [onSizeChange], [onRequestNewAds]] */
    @Override // com.my.target.bt.a
    public final void a(@NonNull v vVar) {
        String str;
        com.my.target.core.models.banners.c cVar;
        switch (vVar.getType()) {
            case "onReady":
                a aVar = this.aL;
                if (aVar != null) {
                    aVar.bk();
                    break;
                }
                break;
            case "onError":
            case "onAdError":
                u uVar = (u) vVar;
                if (uVar.j() != null) {
                    str = az.a.el + ": " + uVar.j();
                } else {
                    str = az.a.el;
                }
                az azVarB = az.z(az.a.el).A(str).B(this.aJ.getUrl());
                com.my.target.core.models.banners.c cVar2 = this.O;
                azVarB.C(cVar2 != null ? cVar2.getId() : null).e(this.context);
                if (vVar.getType().equals(v.aC)) {
                    e(az.a.el);
                    break;
                }
                break;
            case "onComplete":
                e("Ad completed");
                break;
            case "onNoAd":
                e("No ad");
                break;
            case "onAdStart":
                c.a aVar2 = this.af;
                if (aVar2 != null && (cVar = this.O) != null) {
                    aVar2.a(cVar);
                    break;
                }
                break;
            case "onAdClick":
                f(((s) vVar).getUrl());
                break;
            case "onStat":
                cj.b(((z) vVar).k(), this.context);
                break;
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void a(@Nullable c.a aVar) {
        this.af = aVar;
    }

    public final void a(@Nullable a aVar) {
        this.aL = aVar;
    }

    @Override // com.my.target.bt.a
    public final void onError(@NonNull String str) {
        e(str);
    }

    @Override // com.my.target.bt.a
    public final void J(@NonNull String str) {
        if (this.O != null) {
            f(str);
        }
    }

    private void f(@Nullable String str) {
        com.my.target.core.models.banners.c cVar;
        c.a aVar = this.af;
        if (aVar == null || (cVar = this.O) == null) {
            return;
        }
        aVar.a(cVar, str);
    }

    private void e(@NonNull String str) {
        a aVar = this.aL;
        if (aVar != null) {
            aVar.onNoAd(str);
        }
    }

    @Override // com.my.target.core.presenters.c
    public final void destroy() {
        this.aL = null;
        this.af = null;
        if (this.aJ.getParent() != null) {
            ((ViewGroup) this.aJ.getParent()).removeView(this.aJ);
        }
        this.aJ.destroy();
    }
}
