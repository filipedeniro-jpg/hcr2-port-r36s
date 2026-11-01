package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.bu;
import com.my.target.bz;
import com.my.target.ck;
import com.my.target.g;
import com.my.target.nativeads.banners.NativePromoBanner;
import com.my.target.nativeads.factories.NativeViewsFactory;

/* JADX INFO: loaded from: classes2.dex */
public class ContentWallAdView extends RelativeLayout {

    @NonNull
    private final TextView advertisingLabel;

    @NonNull
    private final bu ageRestrictionLabel;

    @Nullable
    private NativePromoBanner banner;

    @NonNull
    private final MediaAdView mediaAdView;

    @NonNull
    private final bz starsView;

    @NonNull
    private final ck uiUtils;

    @NonNull
    private final TextView votesLabel;
    private static final int AGE_ID = ck.br();
    private static final int AD_ID = ck.br();
    private static final int IMAGE_ID = ck.br();
    private static final int STARS_ID = ck.br();
    private static final int VOTES_ID = ck.br();

    public ContentWallAdView(@NonNull Context context) {
        this(context, null);
    }

    public ContentWallAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentWallAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ageRestrictionLabel = new bu(context);
        this.advertisingLabel = new TextView(context);
        this.mediaAdView = NativeViewsFactory.getMediaAdView(context);
        this.starsView = new bz(context);
        this.votesLabel = new TextView(context);
        this.uiUtils = ck.x(context);
        ck.a(this, "ad_view");
        ck.a(this.ageRestrictionLabel, "age_border");
        ck.a(this.advertisingLabel, "advertising_label");
        ck.a(this.mediaAdView, "media_view");
        ck.a(this.starsView, "rating_view");
        ck.a(this.votesLabel, "votes_text");
        initView();
    }

    @NonNull
    public TextView getAdvertisingTextView() {
        return this.advertisingLabel;
    }

    @NonNull
    public TextView getAgeRestrictionTextView() {
        return this.ageRestrictionLabel;
    }

    @NonNull
    public MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    public void setupView(@Nullable NativePromoBanner nativePromoBanner) {
        if (nativePromoBanner == null) {
            return;
        }
        this.banner = nativePromoBanner;
        g.a("Setup banner");
        String ageRestrictions = nativePromoBanner.getAgeRestrictions();
        if (ageRestrictions != null) {
            if (ageRestrictions.length() > 0) {
                this.ageRestrictionLabel.setText(ageRestrictions);
            } else {
                this.ageRestrictionLabel.setVisibility(8);
                this.advertisingLabel.setPadding(0, 0, 0, 0);
            }
        }
        this.ageRestrictionLabel.setText(ageRestrictions);
        this.advertisingLabel.setText(nativePromoBanner.getAdvertisingLabel());
    }

    private void initView() {
        setPadding(this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12));
        this.ageRestrictionLabel.setId(AGE_ID);
        this.ageRestrictionLabel.setPadding(this.uiUtils.l(2), 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = this.uiUtils.l(9);
        this.ageRestrictionLabel.setLayoutParams(layoutParams);
        this.ageRestrictionLabel.setTextColor(-1);
        this.ageRestrictionLabel.c(1, -1);
        this.advertisingLabel.setId(AD_ID);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, AGE_ID);
        this.advertisingLabel.setLayoutParams(layoutParams2);
        this.advertisingLabel.setTextColor(-1);
        this.advertisingLabel.setPadding(this.uiUtils.l(3), 0, 0, 0);
        this.mediaAdView.setId(IMAGE_ID);
        this.mediaAdView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        this.starsView.setId(STARS_ID);
        LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(this.uiUtils.l(73), this.uiUtils.l(12));
        layoutParams3.topMargin = this.uiUtils.l(4);
        layoutParams3.rightMargin = this.uiUtils.l(4);
        this.starsView.setLayoutParams(layoutParams3);
        this.votesLabel.setId(VOTES_ID);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(0);
        linearLayout.setPadding(this.uiUtils.l(3), this.uiUtils.l(3), this.uiUtils.l(3), this.uiUtils.l(3));
        linearLayout.setBackgroundColor(Color.parseColor("#55000000"));
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -2));
        ck.a(this, 0, -3806472);
        setClickable(true);
        addView(this.mediaAdView);
        linearLayout.addView(this.ageRestrictionLabel);
        linearLayout.addView(this.advertisingLabel);
        addView(linearLayout);
    }
}
