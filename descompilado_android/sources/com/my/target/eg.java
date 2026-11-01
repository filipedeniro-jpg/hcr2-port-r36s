package com.my.target;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InterstitialPromoVideoView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface eg extends eh {
    void O();

    void a(@NonNull com.my.target.core.models.banners.i iVar);

    void destroy();

    void e(int i);

    void e(boolean z);

    void finish();

    @NonNull
    ej getPromoMediaView();

    boolean isPaused();

    boolean isPlaying();

    void pause();

    void play();

    void resume();

    void setMediaListener(@Nullable com.my.target.core.presenters.h.a aVar);

    void setTimeChanged(float f);

    void stop(boolean z);
}
