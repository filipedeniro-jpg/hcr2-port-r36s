package com.my.target.ads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.b;
import com.my.target.common.BaseAd;
import com.my.target.core.engines.g;
import com.my.target.core.models.sections.d;
import com.my.target.ds;

/* JADX INFO: loaded from: classes2.dex */
public final class InterstitialSliderAd extends BaseAd {

    @NonNull
    private final Context context;

    @Nullable
    private g engine;
    private boolean hideStatusBarInDialog;

    @Nullable
    private InterstitialSliderAdListener listener;

    public interface InterstitialSliderAdListener {
        void onClick(@NonNull InterstitialSliderAd interstitialSliderAd);

        void onDismiss(@NonNull InterstitialSliderAd interstitialSliderAd);

        void onDisplay(@NonNull InterstitialSliderAd interstitialSliderAd);

        void onLoad(@NonNull InterstitialSliderAd interstitialSliderAd);

        void onNoAd(@NonNull String str, @NonNull InterstitialSliderAd interstitialSliderAd);
    }

    public final boolean isHideStatusBarInDialog() {
        return this.hideStatusBarInDialog;
    }

    public final void setHideStatusBarInDialog(boolean z) {
        this.hideStatusBarInDialog = z;
    }

    @Nullable
    public final InterstitialSliderAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable InterstitialSliderAdListener interstitialSliderAdListener) {
        this.listener = interstitialSliderAdListener;
    }

    public InterstitialSliderAd(int i, @NonNull Context context) {
        super(i, b.a.e);
        this.hideStatusBarInDialog = false;
        this.context = context;
        com.my.target.g.c("InterstitialSliderAd created. Version: 5.3.9");
    }

    public final void load() {
        ds.newFactory(this.adConfig).a(new ds.b() { // from class: com.my.target.ads.InterstitialSliderAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable d dVar, @Nullable String str) {
                InterstitialSliderAd.this.handleResult(dVar, str);
            }
        }).a(this.context);
    }

    public final void show() {
        g gVar = this.engine;
        if (gVar == null) {
            com.my.target.g.c("InterstitialSliderAd.show: No ad");
        } else {
            gVar.s(this.context);
        }
    }

    public final void showDialog() {
        g gVar = this.engine;
        if (gVar == null) {
            com.my.target.g.c("InterstitialSliderAd.showDialog: No ad");
        } else {
            gVar.showDialog(this.context);
        }
    }

    public final void dismiss() {
        g gVar = this.engine;
        if (gVar != null) {
            gVar.dismiss();
        }
    }

    public final void destroy() {
        g gVar = this.engine;
        if (gVar != null) {
            gVar.dismiss();
            this.engine = null;
        }
        this.listener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable d dVar, @Nullable String str) {
        InterstitialSliderAdListener interstitialSliderAdListener = this.listener;
        if (interstitialSliderAdListener != null) {
            if (dVar == null) {
                if (str == null) {
                    str = "no ad";
                }
                interstitialSliderAdListener.onNoAd(str, this);
            } else {
                this.engine = g.a(this, dVar);
                this.listener.onLoad(this);
            }
        }
    }
}
