package com.my.target.core.engines;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ads.InterstitialAd;
import com.my.target.ai;
import com.my.target.aq;
import com.my.target.br;
import com.my.target.cc;
import com.my.target.cj;
import com.my.target.common.MyTargetActivity;

/* JADX INFO: compiled from: InterstitialAdImageEngine.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends c {

    @NonNull
    private final com.my.target.core.models.banners.h h;

    @NonNull
    static e a(@NonNull InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.h hVar) {
        return new e(interstitialAd, hVar);
    }

    private e(InterstitialAd interstitialAd, @NonNull com.my.target.core.models.banners.h hVar) {
        super(interstitialAd);
        this.h = hVar;
    }

    @Override // com.my.target.core.engines.c, com.my.target.br.a
    public final void a(@NonNull br brVar, @NonNull FrameLayout frameLayout) {
        super.a(brVar, frameLayout);
        a(frameLayout);
    }

    @Override // com.my.target.core.engines.c, com.my.target.common.MyTargetActivity.ActivityEngine
    public final void onActivityCreate(@NonNull MyTargetActivity myTargetActivity, @NonNull Intent intent, @NonNull FrameLayout frameLayout) {
        super.onActivityCreate(myTargetActivity, intent, frameLayout);
        a(frameLayout);
    }

    private void a(@NonNull ViewGroup viewGroup) {
        com.my.target.core.presenters.g gVarC = com.my.target.core.presenters.g.c(viewGroup.getContext());
        gVarC.a(new a(this));
        gVarC.e(this.h);
        viewGroup.addView(gVarC.v(), new FrameLayout.LayoutParams(-1, -1));
    }

    final void a(@NonNull Context context) {
        cc.bg().a(this.h, context);
        InterstitialAd.InterstitialAdListener listener = this.a.getListener();
        if (listener != null) {
            listener.onClick(this.a);
        }
        dismiss();
    }

    static void a(ai aiVar, Context context) {
        cj.a(aiVar.getStatHolder().x(aq.a.du), context);
    }

    /* JADX INFO: compiled from: InterstitialAdImageEngine.java */
    private static class a implements com.my.target.core.presenters.j.a {

        @NonNull
        private final e i;

        a(@NonNull e eVar) {
            this.i = eVar;
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@Nullable ai aiVar, @Nullable String str, @NonNull Context context) {
            this.i.a(context);
        }

        @Override // com.my.target.core.presenters.j.a
        public final void bf() {
            this.i.dismiss();
        }

        @Override // com.my.target.core.presenters.j.a
        public final void a(@NonNull ai aiVar, @NonNull Context context) {
            e.a(aiVar, context);
        }
    }
}
