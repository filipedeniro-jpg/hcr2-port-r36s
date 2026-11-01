package com.my.target.core.engines;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ads.InterstitialAd;
import com.my.target.ai;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.br;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.MyTargetActivity;
import com.my.target.core.presenters.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: InterstitialAdHtmlEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d extends c {

    @NonNull
    private final com.my.target.core.models.banners.g e;

    @NonNull
    private final com.my.target.core.models.sections.c f;

    @Nullable
    private WeakReference<m> g;

    @NonNull
    static d a(@NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.g gVar, @NonNull com.my.target.core.models.sections.c cVar) {
        return new d(interstitialAd, gVar, cVar);
    }

    private d(InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.g gVar, @NonNull com.my.target.core.models.sections.c cVar) {
        super(interstitialAd);
        this.e = gVar;
        this.f = cVar;
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        super.a(brVar, frameLayout);
        a(frameLayout);
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void i(boolean z) {
        m mVar;
        super.i(z);
        WeakReference<m> weakReference = this.g;
        if (weakReference == null || (mVar = weakReference.get()) == null) {
            return;
        }
        if (z) {
            mVar.resume();
        } else {
            mVar.pause();
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void aV() {
        m mVar;
        super.aV();
        WeakReference<m> weakReference = this.g;
        if (weakReference != null && (mVar = weakReference.get()) != null) {
            mVar.destroy();
        }
        this.g = null;
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        super.onActivityCreate(myTargetActivity, intent, frameLayout);
        a(frameLayout);
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityPause() {
        m mVar;
        super.onActivityPause();
        WeakReference<m> weakReference = this.g;
        if (weakReference == null || (mVar = weakReference.get()) == null) {
            return;
        }
        mVar.pause();
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityResume() {
        m mVar;
        super.onActivityResume();
        WeakReference<m> weakReference = this.g;
        if (weakReference == null || (mVar = weakReference.get()) == null) {
            return;
        }
        mVar.resume();
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityDestroy() {
        m mVar;
        super.onActivityDestroy();
        WeakReference<m> weakReference = this.g;
        if (weakReference != null && (mVar = weakReference.get()) != null) {
            mVar.destroy();
        }
        this.g = null;
    }

    private void a(@NonNull ViewGroup viewGroup) {
        m mVarB;
        if ("mraid".equals(this.e.getType())) {
            mVarB = com.my.target.core.presenters.i.f(viewGroup.getContext());
        } else {
            mVarB = com.my.target.core.presenters.f.b(viewGroup.getContext());
        }
        this.g = new WeakReference<>(mVarB);
        mVarB.a(new a(this, this.a, this.e));
        mVarB.a(this.e);
        viewGroup.addView(mVarB.v(), new FrameLayout.LayoutParams(-1, -1));
    }

    /* JADX INFO: compiled from: InterstitialAdHtmlEngine.java */
    public static class a implements m.a {

        @NonNull
        private final InterstitialAd a;

        @NonNull
        private final com.my.target.core.models.banners.g e;

        @NonNull
        private final c engine;

        a(@NonNull c cVar, @NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.g gVar) {
            this.engine = cVar;
            this.a = interstitialAd;
            this.e = gVar;
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@Nullable ai aiVar, @Nullable String str, @NonNull Context context) {
            cc ccVarBg = cc.bg();
            if (TextUtils.isEmpty(str)) {
                ccVarBg.a(this.e, context);
            } else {
                ccVarBg.a(this.e, str, context);
            }
            InterstitialAd.InterstitialAdListener listener = this.a.getListener();
            if (listener != null) {
                listener.onClick(this.a);
            }
        }

        @Override // com.my.target.core.presenters.m.a
        public final void g() {
            this.engine.dismiss();
        }

        @Override // com.my.target.core.presenters.j.a
        public final void bf() {
            this.engine.dismiss();
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@NonNull ai aiVar, @NonNull Context context) {
            cj.a(aiVar.getStatHolder().x(aq.a.du), context);
        }

        @Override // com.my.target.core.presenters.m.a
        public final void b(@NonNull ai aiVar, @NonNull String str, @NonNull Context context) {
            cj.a(aiVar.getStatHolder().x(str), context);
        }

        @Override // com.my.target.core.presenters.m.a
        public final void a(@NonNull ai aiVar, float f, float f2, @NonNull Context context) {
            Set<ap> setAd = aiVar.getStatHolder().ad();
            if (setAd.isEmpty()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (ap apVar : setAd) {
                float f3 = f2 - f;
                float fY = apVar.Y();
                if (fY < 0.0f && apVar.Z() >= 0.0f) {
                    fY = (f2 / 100.0f) * apVar.Z();
                }
                if (fY >= 0.0f && fY < f3) {
                    arrayList.add(apVar);
                }
            }
            cj.a(arrayList, context);
        }
    }
}
