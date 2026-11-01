package com.my.target.nativeads.views;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public interface PromoCardView {
    @NonNull
    Button getCtaButtonView();

    @NonNull
    TextView getDescriptionTextView();

    @NonNull
    MediaAdView getMediaAdView();

    @NonNull
    TextView getTitleTextView();

    @NonNull
    View getView();
}
