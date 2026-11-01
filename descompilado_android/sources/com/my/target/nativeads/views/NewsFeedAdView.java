package com.my.target.nativeads.views;

import android.R;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.my.target.bu;
import com.my.target.by;
import com.my.target.bz;
import com.my.target.cf;
import com.my.target.ck;
import com.my.target.common.models.ImageData;
import com.my.target.g;
import com.my.target.nativeads.banners.NativePromoBanner;

/* JADX INFO: loaded from: classes2.dex */
public class NewsFeedAdView extends RelativeLayout {
    private static final int COLOR_PLACEHOLDER_GRAY = -1118482;
    private static final int STANDARD_BLUE = -16748844;
    private static final int STANDARD_GREY = -6710887;

    @NonNull
    private final TextView advertisingLabel;

    @NonNull
    private final bu ageRestrictionLabel;

    @Nullable
    private NativePromoBanner banner;

    @NonNull
    private final Button ctaButton;

    @Nullable
    private RelativeLayout.LayoutParams ctaParams;

    @NonNull
    private final TextView disclaimerLabel;

    @Nullable
    private RelativeLayout.LayoutParams disclaimerParams;

    @NonNull
    private final by iconImageView;

    @NonNull
    private final LinearLayout labelsLayout;

    @NonNull
    private final LinearLayout ratingLayout;

    @NonNull
    private final bz starsView;

    @NonNull
    private final TextView titleLabel;

    @NonNull
    private final ck uiUtils;

    @NonNull
    private final TextView urlLabel;

    @Nullable
    private RelativeLayout.LayoutParams urlLabelParams;

    @NonNull
    private final TextView votesLabel;
    private static final int AGE_ID = ck.br();
    private static final int ICON_ID = ck.br();
    private static final int LABELS_ID = ck.br();
    private static final int TITLE_ID = ck.br();
    private static final int URL_ID = ck.br();
    private static final int AD_ID = ck.br();
    private static final int TITLE_2_ID = ck.br();
    private static final int CTA_ID = ck.br();
    private static final int STARS_ID = ck.br();
    private static final int VOTES_ID = ck.br();
    private static final int RATING_ID = ck.br();

    public NewsFeedAdView(@NonNull Context context) {
        this(context, null);
    }

    public NewsFeedAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NewsFeedAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ageRestrictionLabel = new bu(context);
        this.advertisingLabel = new TextView(context);
        this.iconImageView = new by(context);
        this.labelsLayout = new LinearLayout(context);
        this.titleLabel = new TextView(context);
        this.urlLabel = new TextView(context);
        this.ratingLayout = new LinearLayout(context);
        this.starsView = new bz(context);
        this.votesLabel = new TextView(context);
        this.disclaimerLabel = new TextView(context);
        this.ctaButton = new Button(context);
        this.uiUtils = ck.x(context);
        initView();
    }

    @NonNull
    public TextView getAgeRestrictionTextView() {
        return this.ageRestrictionLabel;
    }

    @NonNull
    public TextView getAdvertisingTextView() {
        return this.advertisingLabel;
    }

    @NonNull
    public ImageView getIconImageView() {
        return this.iconImageView;
    }

    @NonNull
    public TextView getTitleTextView() {
        return this.titleLabel;
    }

    @NonNull
    public TextView getDomainOrCategoryTextView() {
        return this.urlLabel;
    }

    @NonNull
    public Button getCtaButtonView() {
        return this.ctaButton;
    }

    @NonNull
    public TextView getVotesTextView() {
        return this.votesLabel;
    }

    @NonNull
    public bz getStarsRatingView() {
        return this.starsView;
    }

    @NonNull
    public TextView getDisclaimerTextView() {
        return this.disclaimerLabel;
    }

    public void setupView(@Nullable NativePromoBanner nativePromoBanner) {
        String str;
        boolean z;
        if (nativePromoBanner == null) {
            return;
        }
        this.banner = nativePromoBanner;
        g.a("Setup banner");
        if ("web".equals(nativePromoBanner.getNavigationType())) {
            this.urlLabel.setVisibility(0);
            this.ratingLayout.setVisibility(8);
            this.urlLabel.setText(nativePromoBanner.getDomain());
            ck.a(this.urlLabel, "domain_text");
        } else if ("store".equals(nativePromoBanner.getNavigationType())) {
            String category = nativePromoBanner.getCategory();
            String subCategory = nativePromoBanner.getSubCategory();
            if (TextUtils.isEmpty(category)) {
                str = "";
            } else {
                str = "" + category;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(subCategory)) {
                str = str + ", ";
            }
            if (!TextUtils.isEmpty(subCategory)) {
                str = str + subCategory;
            }
            if (nativePromoBanner.getRating() > 0.0f && nativePromoBanner.getRating() <= 5.0f) {
                this.starsView.setRating(nativePromoBanner.getRating());
                this.votesLabel.setText(String.valueOf(nativePromoBanner.getVotes()));
                this.urlLabel.setVisibility(8);
                ViewParent parent = this.ratingLayout.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(this.ratingLayout);
                }
                this.labelsLayout.addView(this.ratingLayout, 1);
                this.ratingLayout.setVisibility(0);
                this.starsView.setVisibility(0);
                if (nativePromoBanner.getVotes() > 0) {
                    this.votesLabel.setVisibility(0);
                } else {
                    this.votesLabel.setVisibility(8);
                }
                this.urlLabel.setVisibility(8);
                ck.a(this.votesLabel, "votes_text");
                ck.a(this.starsView, "rating_view");
            } else {
                this.urlLabel.setVisibility(0);
                this.urlLabel.setText(str);
                ck.a(this.urlLabel, "category_text");
                this.ratingLayout.setVisibility(8);
            }
        }
        ImageData icon = nativePromoBanner.getIcon();
        if (icon == null) {
            z = false;
        } else if (icon.getData() != null) {
            this.iconImageView.setImageBitmap(icon.getData());
            z = true;
        } else {
            this.iconImageView.setBackgroundColor(-1118482);
            this.iconImageView.setPlaceholderWidth(icon.getWidth());
            this.iconImageView.setPlaceholderHeight(icon.getHeight());
            z = false;
        }
        if (!z) {
            this.iconImageView.setImageData(null);
            this.iconImageView.setContentDescription("");
        }
        this.titleLabel.setText(nativePromoBanner.getTitle());
        this.advertisingLabel.setText(nativePromoBanner.getAdvertisingLabel());
        this.ctaButton.setText(nativePromoBanner.getCtaText());
        ck.a(this.ctaButton, "cta_button");
        if (!TextUtils.isEmpty(nativePromoBanner.getAgeRestrictions())) {
            this.ageRestrictionLabel.setText(nativePromoBanner.getAgeRestrictions());
            ck.a(this.ageRestrictionLabel, "age_bordered");
        } else {
            this.ageRestrictionLabel.setVisibility(8);
        }
        String disclaimer = nativePromoBanner.getDisclaimer();
        ViewParent parent2 = this.ctaButton.getParent();
        if (!TextUtils.isEmpty(disclaimer)) {
            ViewParent parent3 = this.disclaimerLabel.getParent();
            if (parent3 != null) {
                ((ViewGroup) parent3).removeView(this.disclaimerLabel);
            }
            if (parent2 != null) {
                ((ViewGroup) parent2).removeView(this.ctaButton);
            }
            RelativeLayout relativeLayout = new RelativeLayout(getContext());
            relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            this.disclaimerLabel.setText(disclaimer);
            this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
            this.disclaimerParams.addRule(9);
            this.disclaimerParams.addRule(0, this.ctaButton.getId());
            this.disclaimerParams.rightMargin = this.uiUtils.l(3);
            this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
            ck.a(this.disclaimerLabel, "disclaimer_text");
            this.ctaParams = new RelativeLayout.LayoutParams(-2, this.uiUtils.l(30));
            this.ctaParams.addRule(11);
            this.ctaButton.setLayoutParams(this.ctaParams);
            relativeLayout.addView(this.disclaimerLabel);
            relativeLayout.addView(this.ctaButton);
            this.labelsLayout.addView(relativeLayout, 2);
            return;
        }
        this.disclaimerLabel.setVisibility(8);
        if (!"web".equals(nativePromoBanner.getNavigationType()) || TextUtils.isEmpty(nativePromoBanner.getDomain())) {
            return;
        }
        ViewParent parent4 = this.urlLabel.getParent();
        if (parent4 != null) {
            ((ViewGroup) parent4).removeView(this.urlLabel);
        }
        if (parent2 != null) {
            ((ViewGroup) parent2).removeView(this.ctaButton);
        }
        RelativeLayout relativeLayout2 = new RelativeLayout(getContext());
        relativeLayout2.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.urlLabelParams = new RelativeLayout.LayoutParams(-2, -2);
        this.urlLabelParams.addRule(9);
        this.urlLabelParams.addRule(0, this.ctaButton.getId());
        this.urlLabelParams.rightMargin = this.uiUtils.l(3);
        this.urlLabel.setLayoutParams(this.urlLabelParams);
        this.ctaParams = new RelativeLayout.LayoutParams(-2, this.uiUtils.l(30));
        this.ctaParams.addRule(11);
        this.ctaParams.topMargin = this.uiUtils.l(9);
        this.ctaButton.setLayoutParams(this.ctaParams);
        relativeLayout2.addView(this.urlLabel);
        relativeLayout2.addView(this.ctaButton);
        this.labelsLayout.addView(relativeLayout2, 1);
    }

    public void loadImages() {
        ImageData icon;
        NativePromoBanner nativePromoBanner = this.banner;
        if (nativePromoBanner == null || (icon = nativePromoBanner.getIcon()) == null) {
            return;
        }
        if (icon.getData() == null) {
            cf.a(icon, this.iconImageView);
        } else {
            this.iconImageView.setImageBitmap(icon.getData());
        }
    }

    private void initView() {
        ck.a(this, "ad_view");
        setPadding(this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12));
        this.ageRestrictionLabel.setId(AGE_ID);
        this.ageRestrictionLabel.c(1, -7829368);
        this.ageRestrictionLabel.setPadding(this.uiUtils.l(2), 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = this.uiUtils.l(9);
        this.ageRestrictionLabel.setLayoutParams(layoutParams);
        this.advertisingLabel.setId(AD_ID);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, AGE_ID);
        this.advertisingLabel.setLayoutParams(layoutParams2);
        ck.a(this.advertisingLabel, "advertising_label");
        this.iconImageView.setId(ICON_ID);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.uiUtils.l(54), this.uiUtils.l(54));
        layoutParams3.addRule(3, AD_ID);
        layoutParams3.topMargin = this.uiUtils.l(9);
        this.iconImageView.setLayoutParams(layoutParams3);
        ck.a(this.iconImageView, "icon_image");
        this.labelsLayout.setId(LABELS_ID);
        this.labelsLayout.setOrientation(1);
        this.labelsLayout.setMinimumHeight(this.uiUtils.l(54));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(3, AD_ID);
        layoutParams4.addRule(1, ICON_ID);
        layoutParams4.leftMargin = this.uiUtils.l(9);
        layoutParams4.topMargin = this.uiUtils.l(3);
        this.labelsLayout.setLayoutParams(layoutParams4);
        this.titleLabel.setId(TITLE_ID);
        ck.a(this.titleLabel, "title_text");
        this.titleLabel.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.urlLabel.setId(URL_ID);
        this.urlLabelParams = new RelativeLayout.LayoutParams(-2, -2);
        this.urlLabelParams.topMargin = this.uiUtils.l(2);
        this.urlLabel.setLayoutParams(this.urlLabelParams);
        this.ratingLayout.setId(RATING_ID);
        this.ratingLayout.setOrientation(0);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.addRule(3, TITLE_2_ID);
        this.ratingLayout.setLayoutParams(layoutParams5);
        this.starsView.setId(STARS_ID);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(this.uiUtils.l(73), this.uiUtils.l(12));
        layoutParams6.topMargin = this.uiUtils.l(4);
        layoutParams6.rightMargin = this.uiUtils.l(4);
        this.starsView.setLayoutParams(layoutParams6);
        this.votesLabel.setId(VOTES_ID);
        this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
        this.disclaimerParams.addRule(3, RATING_ID);
        this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
        this.ctaButton.setId(CTA_ID);
        this.ctaButton.setPadding(this.uiUtils.l(10), 0, this.uiUtils.l(10), 0);
        this.ctaButton.setTransformationMethod(null);
        this.ctaButton.setMaxEms(8);
        this.ctaButton.setLines(1);
        this.ctaButton.setEllipsize(TextUtils.TruncateAt.END);
        this.ctaParams = new RelativeLayout.LayoutParams(-2, this.uiUtils.l(30));
        this.ctaParams.addRule(3, LABELS_ID);
        this.ctaParams.addRule(11);
        this.ctaParams.topMargin = -this.uiUtils.l(23);
        this.ctaButton.setLayoutParams(this.ctaParams);
        ck.a(this, 0, -3806472);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, 0});
        gradientDrawable.setStroke(this.uiUtils.l(1), STANDARD_BLUE);
        gradientDrawable.setCornerRadius(this.uiUtils.l(1));
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-3806472, -3806472});
        gradientDrawable2.setStroke(this.uiUtils.l(1), STANDARD_BLUE);
        gradientDrawable2.setCornerRadius(this.uiUtils.l(1));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        if (Build.VERSION.SDK_INT >= 16) {
            this.ctaButton.setBackground(stateListDrawable);
        } else {
            this.ctaButton.setBackgroundDrawable(stateListDrawable);
        }
        setClickable(true);
        addView(this.ageRestrictionLabel);
        addView(this.advertisingLabel);
        addView(this.iconImageView);
        addView(this.labelsLayout);
        this.labelsLayout.addView(this.titleLabel);
        this.labelsLayout.addView(this.urlLabel);
        addView(this.ctaButton);
        addView(this.ratingLayout);
        addView(this.disclaimerLabel);
        this.ratingLayout.addView(this.starsView);
        this.ratingLayout.addView(this.votesLabel);
        updateDefaultParams();
    }

    private void updateDefaultParams() {
        this.ageRestrictionLabel.setTextColor(STANDARD_GREY);
        this.ageRestrictionLabel.c(1, STANDARD_GREY);
        this.ageRestrictionLabel.setBackgroundColor(0);
        this.advertisingLabel.setTextSize(2, 14.0f);
        this.advertisingLabel.setTextColor(STANDARD_GREY);
        this.titleLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.titleLabel.setTextSize(2, 16.0f);
        this.titleLabel.setTypeface(null, 1);
        this.urlLabel.setTextColor(STANDARD_GREY);
        this.urlLabel.setTextSize(2, 14.0f);
        this.votesLabel.setTextColor(STANDARD_GREY);
        this.votesLabel.setTextSize(2, 14.0f);
        this.disclaimerLabel.setTextColor(STANDARD_GREY);
        this.disclaimerLabel.setTextSize(2, 12.0f);
        this.ctaButton.setTextColor(STANDARD_BLUE);
    }
}
