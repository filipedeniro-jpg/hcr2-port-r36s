package com.my.target;

import android.os.Parcelable;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: PromoCardSlider.java */
/* JADX INFO: loaded from: classes2.dex */
public interface dd {

    /* JADX INFO: compiled from: PromoCardSlider.java */
    public interface a {
        void b(@NonNull View view, int i);

        void b(@NonNull View view, @NonNull int[] iArr);
    }

    void dispose();

    @Nullable
    Parcelable getState();

    @NonNull
    int[] getVisibleCardNumbers();

    void restoreState(@NonNull Parcelable parcelable);

    void setPromoCardSliderListener(@Nullable a aVar);
}
