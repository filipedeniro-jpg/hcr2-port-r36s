package com.my.target.core.engines;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.ads.InterstitialAd;
import com.my.target.ai;
import com.my.target.ap;
import com.my.target.aq;
import com.my.target.br;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.MyTargetActivity;
import com.my.target.core.presenters.k;
import com.my.target.core.presenters.m;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: InterstitialAdPromoEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends c {

    @NonNull
    private final com.my.target.core.models.sections.c f;

    @NonNull
    private final com.my.target.core.models.banners.i j;

    @Nullable
    private WeakReference<com.my.target.core.presenters.j> k;

    @NonNull
    public static f a(@NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.i iVar, @NonNull com.my.target.core.models.sections.c cVar) {
        return new f(interstitialAd, iVar, cVar);
    }

    @Nullable
    @VisibleForTesting
    private com.my.target.core.presenters.j h() {
        WeakReference<com.my.target.core.presenters.j> weakReference = this.k;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private f(@NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.i iVar, @NonNull com.my.target.core.models.sections.c cVar) {
        super(interstitialAd);
        this.j = iVar;
        this.f = cVar;
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        super.a(brVar, frameLayout);
        a((ViewGroup) frameLayout, true);
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void i(boolean z) {
        super.i(z);
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH != null) {
            if (z) {
                jVarH.resume();
            } else {
                jVarH.pause();
            }
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void aV() {
        super.aV();
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH != null) {
            jVarH.destroy();
        }
        WeakReference<com.my.target.core.presenters.j> weakReference = this.k;
        if (weakReference != null) {
            weakReference.clear();
            this.k = null;
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        super.onActivityCreate(myTargetActivity, intent, frameLayout);
        a((ViewGroup) frameLayout, false);
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityPause() {
        super.onActivityPause();
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH != null) {
            jVarH.pause();
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityResume() {
        super.onActivityResume();
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH != null) {
            jVarH.resume();
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityStop() {
        super.onActivityStop();
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH != null) {
            jVarH.stop();
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityDestroy() {
        super.onActivityDestroy();
        WeakReference<com.my.target.core.presenters.j> weakReference = this.k;
        if (weakReference != null) {
            com.my.target.core.presenters.j jVar = weakReference.get();
            if (jVar != null) {
                View viewV = jVar.v();
                ViewParent parent = viewV.getParent();
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(viewV);
                }
                jVar.destroy();
            }
            this.k.clear();
            this.k = null;
        }
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final boolean onActivityBackPressed() {
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH instanceof k) {
            return ((k) jVarH).x();
        }
        return true;
    }

    final void a(@NonNull ai aiVar, @Nullable String str, @NonNull Context context) {
        if (h() == null) {
            return;
        }
        cc ccVarBg = cc.bg();
        if (TextUtils.isEmpty(str)) {
            ccVarBg.a(aiVar, context);
        } else {
            ccVarBg.a(aiVar, str, context);
        }
        if (aiVar instanceof com.my.target.core.models.banners.f) {
            cj.a(this.j.getStatHolder().x(aq.a.dF), context);
        }
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onClick(this.a);
        }
        if (this.j.getVideoBanner() == null && this.j.isCloseOnClick()) {
            dismiss();
        }
    }

    final void i() {
        m mVarB;
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onVideoCompleted(this.a);
        }
        com.my.target.core.models.banners.e endCard = this.j.getEndCard();
        com.my.target.core.presenters.j jVarH = h();
        ViewParent parent = jVarH != null ? jVarH.v().getParent() : null;
        if (endCard == null || !(parent instanceof ViewGroup)) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        com.my.target.core.presenters.j jVarH2 = h();
        if (jVarH2 != null) {
            jVarH2.destroy();
        }
        if (endCard instanceof com.my.target.core.models.banners.g) {
            viewGroup.removeAllViews();
            if ("mraid".equals(endCard.getType())) {
                mVarB = com.my.target.core.presenters.i.f(viewGroup.getContext());
            } else {
                mVarB = com.my.target.core.presenters.f.b(viewGroup.getContext());
            }
            this.k = new WeakReference<>(mVarB);
            mVarB.a(new a(this));
            mVarB.a((com.my.target.core.models.banners.g) endCard);
            viewGroup.addView(mVarB.v(), new FrameLayout.LayoutParams(-1, -1));
            return;
        }
        if (endCard instanceof com.my.target.core.models.banners.h) {
            viewGroup.removeAllViews();
            com.my.target.core.presenters.g gVarC = com.my.target.core.presenters.g.c(viewGroup.getContext());
            this.k = new WeakReference<>(gVarC);
            gVarC.a(new a(this));
            gVarC.e((com.my.target.core.models.banners.h) endCard);
            viewGroup.addView(gVarC.v(), new FrameLayout.LayoutParams(-1, -1));
        }
    }

    final void j() {
        com.my.target.core.presenters.j jVarH = h();
        if (jVarH instanceof k) {
            ((k) jVarH).I();
        }
    }

    static void a(@NonNull ai aiVar, float f, float f2, @NonNull Context context) {
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

    static void b(ai aiVar, @NonNull String str, @NonNull Context context) {
        cj.a(aiVar.getStatHolder().x(str), context);
    }

    static void a(ai aiVar, @NonNull Context context) {
        cj.a(aiVar.getStatHolder().x(aq.a.du), context);
    }

    private void a(@NonNull ViewGroup viewGroup, boolean z) {
        k kVarA = k.a(this.j, this.a.isUseExoPlayer(), viewGroup.getContext());
        this.k = new WeakReference<>(kVarA);
        kVarA.a(z);
        kVarA.b(new a(this));
        viewGroup.addView(kVarA.v(), new FrameLayout.LayoutParams(-1, -1));
        cj.a(this.j.getStatHolder().x(aq.a.du), viewGroup.getContext());
        cj.a(this.f.q(aq.a.dB), viewGroup.getContext());
    }

    /* JADX INFO: compiled from: InterstitialAdPromoEngine.java */
    private static class a implements k.b, m.a {

        @NonNull
        private final f l;

        @Override // com.my.target.core.presenters.m.a
        public final void g() {
        }

        a(@NonNull f fVar) {
            this.l = fVar;
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@Nullable ai aiVar, @Nullable String str, @NonNull Context context) {
            if (aiVar != null) {
                this.l.a(aiVar, str, context);
            }
        }

        @Override // com.my.target.core.presenters.j.a
        public final void bf() {
            this.l.dismiss();
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@NonNull ai aiVar, @NonNull Context context) {
            f.a(aiVar, context);
        }

        @Override // com.my.target.core.presenters.m.a
        public final void b(@NonNull ai aiVar, @NonNull String str, @NonNull Context context) {
            f.b(aiVar, str, context);
        }

        @Override // com.my.target.core.presenters.m.a
        public final void a(@NonNull ai aiVar, float f, float f2, @NonNull Context context) {
            f.a(aiVar, f, f2, context);
        }

        @Override // com.my.target.core.presenters.k.b
        public final void j() {
            this.l.j();
        }

        @Override // com.my.target.core.presenters.k.b
        public final void i() {
            this.l.i();
        }
    }
}
