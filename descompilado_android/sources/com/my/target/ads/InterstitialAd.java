package com.my.target.ads;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.BaseAd;
import com.my.target.core.engines.c;
import com.my.target.core.models.banners.e;
import com.my.target.dp;
import com.my.target.g;

/* JADX INFO: loaded from: classes2.dex */
public final class InterstitialAd extends BaseAd {

    @NonNull
    private final Context context;

    @Nullable
    private c engine;
    private boolean hideStatusBarInDialog;

    @Nullable
    private InterstitialAdListener listener;
    private boolean useExoPlayer;

    public interface InterstitialAdListener {
        void onClick(@NonNull InterstitialAd interstitialAd);

        void onDismiss(@NonNull InterstitialAd interstitialAd);

        void onDisplay(@NonNull InterstitialAd interstitialAd);

        void onLoad(@NonNull InterstitialAd interstitialAd);

        void onNoAd(@NonNull String str, @NonNull InterstitialAd interstitialAd);

        void onVideoCompleted(@NonNull InterstitialAd interstitialAd);
    }

    public InterstitialAd(int i, @NonNull Context context) {
        super(i, "fullscreen");
        this.hideStatusBarInDialog = false;
        this.useExoPlayer = true;
        this.context = context;
        g.c("InterstitialAd created. Version: 5.3.9");
    }

    public final void useExoPlayer(boolean z) {
        this.useExoPlayer = z;
    }

    public final boolean isUseExoPlayer() {
        return this.useExoPlayer;
    }

    public final boolean isHideStatusBarInDialog() {
        return this.hideStatusBarInDialog;
    }

    public final void setHideStatusBarInDialog(boolean z) {
        this.hideStatusBarInDialog = z;
    }

    @Nullable
    public final InterstitialAdListener getListener() {
        return this.listener;
    }

    public final void setListener(@Nullable InterstitialAdListener interstitialAdListener) {
        this.listener = interstitialAdListener;
    }

    public final void load() {
        dp.newFactory(this.adConfig).a(new dp.b() { // from class: com.my.target.ads.InterstitialAd.1
            @Override // com.my.target.c.b
            /* JADX INFO: renamed from: onResult, reason: merged with bridge method [inline-methods] */
            public void b(@Nullable com.my.target.core.models.sections.c cVar, @Nullable String str) {
                InterstitialAd.this.handleResult(cVar, str);
            }
        }).a(this.context);
    }

    public final void loadFromBid(@NonNull String str) {
        this.adConfig.setBidId(str);
        load();
    }

    public final void show() {
        c cVar = this.engine;
        if (cVar == null) {
            g.c("InterstitialAd.show: No ad");
        } else {
            cVar.s(this.context);
        }
    }

    public final void showDialog() {
        c cVar = this.engine;
        if (cVar == null) {
            g.c("InterstitialAd.showDialog: No ad");
        } else {
            cVar.showDialog(this.context);
        }
    }

    public final void dismiss() {
        c cVar = this.engine;
        if (cVar != null) {
            cVar.dismiss();
        }
    }

    public final void destroy() {
        c cVar = this.engine;
        if (cVar != null) {
            cVar.dismiss();
            this.engine = null;
        }
        this.listener = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleResult(@Nullable com.my.target.core.models.sections.c cVar, @Nullable String str) {
        if (this.listener != null) {
            e eVarM = cVar == null ? null : cVar.m();
            if (eVarM == null) {
                InterstitialAdListener interstitialAdListener = this.listener;
                if (str == null) {
                    str = "no ad";
                }
                interstitialAdListener.onNoAd(str, this);
                return;
            }
            this.engine = c.a(this, eVarM, cVar);
            if (this.engine != null) {
                this.listener.onLoad(this);
            } else {
                this.listener.onNoAd("no ad", this);
            }
        }
    }
}
