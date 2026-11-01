package com.my.target;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: InterstitialPromoView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface eh {
    public static final int bG = ck.br();

    /* JADX INFO: compiled from: InterstitialPromoView.java */
    public interface a {
        void K();

        void c(boolean z);
    }

    void N();

    @NonNull
    View getCloseButton();

    @NonNull
    View getView();

    void setBanner(@NonNull com.my.target.core.models.banners.i iVar);

    void setClickArea(@NonNull ag agVar);

    void setInterstitialPromoViewListener(@Nullable a aVar);
}
