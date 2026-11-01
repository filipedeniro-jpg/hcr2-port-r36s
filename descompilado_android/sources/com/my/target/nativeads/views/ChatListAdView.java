package com.my.target.nativeads.views;

import android.R;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.StateSet;
import android.view.ViewGroup;
import android.view.ViewParent;
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
public class ChatListAdView extends RelativeLayout {
    private static final int COLOR_PLACEHOLDER_GRAY = -1118482;
    private static final int LETTERS_GREY = -6710887;

    @NonNull
    private final TextView advertisingLabel;

    @NonNull
    private final bu ageRestrictionLabel;

    @Nullable
    private NativePromoBanner banner;

    @NonNull
    private final TextView descriptionLabel;

    @NonNull
    private final TextView disclaimerLabel;

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

    @NonNull
    private final TextView votesLabel;
    private static final int AGE_ID = ck.br();
    private static final int ICON_ID = ck.br();
    private static final int LABELS_ID = ck.br();
    private static final int TITLE_ID = ck.br();
    private static final int URL_ID = ck.br();
    private static final int DESC_ID = ck.br();
    private static final int AD_ID = ck.br();
    private static final int TITLE_2_ID = ck.br();
    private static final int STARS_ID = ck.br();
    private static final int VOTES_ID = ck.br();
    private static final int RATING_ID = ck.br();

    public ChatListAdView(@NonNull Context context) {
        this(context, null);
    }

    public ChatListAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChatListAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ageRestrictionLabel = new bu(context);
        this.advertisingLabel = new TextView(context);
        this.iconImageView = new by(context);
        this.labelsLayout = new LinearLayout(context);
        this.titleLabel = new TextView(context);
        this.urlLabel = new TextView(context);
        this.descriptionLabel = new TextView(context);
        this.ratingLayout = new LinearLayout(context);
        this.starsView = new bz(context);
        this.votesLabel = new TextView(context);
        this.disclaimerLabel = new TextView(context);
        ck.a(this, "ad_view");
        ck.a(this.titleLabel, "title_text");
        ck.a(this.descriptionLabel, "description_text");
        ck.a(this.disclaimerLabel, "disclaimer_text");
        this.uiUtils = ck.x(context);
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
    public TextView getDescriptionTextView() {
        return this.descriptionLabel;
    }

    @NonNull
    public TextView getDisclaimerTextView() {
        return this.disclaimerLabel;
    }

    @NonNull
    public TextView getDomainOrCategoryTextView() {
        return this.urlLabel;
    }

    @NonNull
    public ImageView getIconImageView() {
        return this.iconImageView;
    }

    @NonNull
    public bz getStarsRatingView() {
        return this.starsView;
    }

    @NonNull
    public TextView getTitleTextView() {
        return this.titleLabel;
    }

    @NonNull
    public TextView getVotesTextView() {
        return this.votesLabel;
    }

    public void setupView(@Nullable NativePromoBanner nativePromoBanner) {
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
            String str = "";
            if (!TextUtils.isEmpty(category)) {
                str = "" + category;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(subCategory)) {
                str = str + ", ";
            }
            if (!TextUtils.isEmpty(subCategory)) {
                str = str + subCategory;
            }
            if (nativePromoBanner.getRating() > 0.0f && nativePromoBanner.getRating() <= 5.0f) {
                this.urlLabel.setVisibility(8);
                ViewParent parent = this.ratingLayout.getParent();
                if (parent != null) {
                    ((ViewGroup) parent).removeView(this.ratingLayout);
                }
                this.labelsLayout.addView(this.ratingLayout, 1);
                this.ratingLayout.setVisibility(0);
                this.starsView.setRating(nativePromoBanner.getRating());
                if (nativePromoBanner.getVotes() > 0) {
                    this.votesLabel.setText(String.valueOf(nativePromoBanner.getVotes()));
                    this.votesLabel.setVisibility(0);
                } else {
                    this.votesLabel.setVisibility(8);
                }
                ck.a(this.votesLabel, "votes_text");
                ck.a(this.starsView, "rating_view");
            } else {
                this.urlLabel.setVisibility(0);
                this.urlLabel.setText(str);
                this.ratingLayout.setVisibility(8);
                ck.a(this.urlLabel, "category_text");
            }
        }
        ImageData icon = nativePromoBanner.getIcon();
        if (icon != null) {
            if (icon.getData() != null) {
                this.iconImageView.setImageBitmap(icon.getData());
            } else {
                this.iconImageView.setBackgroundColor(-1118482);
                this.iconImageView.setPlaceholderWidth(icon.getWidth());
                this.iconImageView.setPlaceholderHeight(icon.getHeight());
            }
        }
        this.titleLabel.setText(nativePromoBanner.getTitle());
        this.descriptionLabel.setText(nativePromoBanner.getDescription());
        this.advertisingLabel.setText(nativePromoBanner.getAdvertisingLabel());
        if (!TextUtils.isEmpty(nativePromoBanner.getAgeRestrictions())) {
            this.ageRestrictionLabel.setText(nativePromoBanner.getAgeRestrictions());
            ck.a(this.ageRestrictionLabel, "age_bordered");
        } else {
            this.ageRestrictionLabel.setVisibility(8);
        }
        String disclaimer = nativePromoBanner.getDisclaimer();
        if (!TextUtils.isEmpty(disclaimer)) {
            this.disclaimerLabel.setText(disclaimer);
        } else {
            this.disclaimerLabel.setVisibility(8);
        }
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
        setPadding(this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12));
        this.ageRestrictionLabel.setId(AGE_ID);
        this.ageRestrictionLabel.c(1, -7829368);
        this.ageRestrictionLabel.setPadding(this.uiUtils.l(2), 0, 0, 0);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.rightMargin = this.uiUtils.l(9);
        this.ageRestrictionLabel.setLayoutParams(layoutParams);
        this.ageRestrictionLabel.setTextColor(LETTERS_GREY);
        this.ageRestrictionLabel.c(1, LETTERS_GREY);
        this.ageRestrictionLabel.setBackgroundColor(0);
        this.advertisingLabel.setId(AD_ID);
        ck.a(this.advertisingLabel, "advertising_label");
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, AGE_ID);
        this.advertisingLabel.setLayoutParams(layoutParams2);
        this.advertisingLabel.setTextSize(2, 14.0f);
        this.advertisingLabel.setTextColor(LETTERS_GREY);
        this.iconImageView.setId(ICON_ID);
        ck.a(this.iconImageView, "icon_image");
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.uiUtils.l(54), this.uiUtils.l(54));
        layoutParams3.addRule(3, AD_ID);
        layoutParams3.topMargin = this.uiUtils.l(2);
        this.iconImageView.setLayoutParams(layoutParams3);
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
        ck.a(this, "ad_view");
        ck.a(this.titleLabel, "title_text");
        this.titleLabel.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.titleLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.titleLabel.setTextSize(2, 16.0f);
        this.titleLabel.setTypeface(null, 1);
        this.urlLabel.setId(URL_ID);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.topMargin = this.uiUtils.l(2);
        this.urlLabel.setLayoutParams(layoutParams5);
        this.urlLabel.setTextColor(LETTERS_GREY);
        this.urlLabel.setTextSize(2, 14.0f);
        this.descriptionLabel.setId(DESC_ID);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.topMargin = this.uiUtils.l(2);
        this.descriptionLabel.setLayoutParams(layoutParams6);
        this.descriptionLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.descriptionLabel.setTextSize(2, 14.0f);
        this.ratingLayout.setId(RATING_ID);
        this.ratingLayout.setOrientation(0);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(3, TITLE_2_ID);
        this.ratingLayout.setLayoutParams(layoutParams7);
        this.starsView.setId(STARS_ID);
        LinearLayout.LayoutParams layoutParams8 = new LinearLayout.LayoutParams(this.uiUtils.l(73), this.uiUtils.l(12));
        layoutParams8.topMargin = this.uiUtils.l(4);
        layoutParams8.rightMargin = this.uiUtils.l(4);
        this.starsView.setLayoutParams(layoutParams8);
        this.votesLabel.setId(VOTES_ID);
        this.votesLabel.setTextColor(LETTERS_GREY);
        this.votesLabel.setTextSize(2, 14.0f);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(3, RATING_ID);
        this.disclaimerLabel.setLayoutParams(layoutParams9);
        this.disclaimerLabel.setTextColor(LETTERS_GREY);
        this.disclaimerLabel.setTextSize(2, 12.0f);
        ck.a(this, 0, -3806472);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{0, 0});
        gradientDrawable.setStroke(this.uiUtils.l(1), -3355444);
        gradientDrawable.setCornerRadius(this.uiUtils.l(1));
        GradientDrawable gradientDrawable2 = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{-3806472, -3806472});
        gradientDrawable2.setStroke(this.uiUtils.l(1), -3355444);
        gradientDrawable2.setCornerRadius(this.uiUtils.l(1));
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        setClickable(true);
        addView(this.ageRestrictionLabel);
        addView(this.advertisingLabel);
        addView(this.iconImageView);
        addView(this.labelsLayout);
        this.labelsLayout.addView(this.titleLabel);
        this.labelsLayout.addView(this.urlLabel);
        this.labelsLayout.addView(this.descriptionLabel);
        this.labelsLayout.addView(this.disclaimerLabel);
        addView(this.ratingLayout);
        this.ratingLayout.addView(this.starsView);
        this.ratingLayout.addView(this.votesLabel);
    }
}
