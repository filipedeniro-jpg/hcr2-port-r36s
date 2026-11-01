package com.my.target;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: StandardNativeView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface dn {
    @NonNull
    View N();

    void a(@NonNull ag agVar, boolean z, @NonNull View.OnClickListener onClickListener);

    void b(int i, int i2);

    void b(@NonNull ai aiVar);

    @NonNull
    bu getAgeRestrictionsView();

    @Nullable
    by getBannerImage();

    @NonNull
    Button getCtaButton();

    @Nullable
    TextView getDescriptionTextView();

    @Nullable
    TextView getDisclaimerTextView();

    @NonNull
    TextView getDomainTextView();

    @NonNull
    by getIconImage();

    @Nullable
    by getMainImage();

    @NonNull
    TextView getRatingTextView();

    @NonNull
    bz getStarsRatingView();

    @NonNull
    TextView getTitleTextView();

    void start();

    void stop();
}
