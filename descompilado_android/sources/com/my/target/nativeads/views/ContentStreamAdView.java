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
import com.my.target.nativeads.banners.NativePromoCard;
import com.my.target.nativeads.factories.NativeViewsFactory;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ContentStreamAdView extends RelativeLayout {
    private static final int COLOR_PLACEHOLDER_GRAY = -1118482;
    private static final int STANDARD_BLUE = -16748844;

    @NonNull
    private final TextView advertisingLabel;

    @NonNull
    private final bu ageRestrictionLabel;

    @Nullable
    private NativePromoBanner banner;
    private PromoCardRecyclerView.PromoCardAdapter cardAdapter;

    @NonNull
    private final Button ctaButton;

    @NonNull
    private final TextView descriptionLabel;

    @NonNull
    private final TextView disclaimerLabel;

    @Nullable
    private RelativeLayout.LayoutParams disclaimerParams;

    @NonNull
    private final by iconImageView;

    @NonNull
    private final LinearLayout labelsLayout;

    @Nullable
    private MediaAdView mediaAdView;

    @Nullable
    private PromoCardRecyclerView promoCardRecyclerView;

    @NonNull
    private final LinearLayout ratingLayout;

    @NonNull
    private final bz starsView;

    @NonNull
    private final TextView title2Label;

    @NonNull
    private final TextView titleLabel;

    @NonNull
    private final ck uiUtils;

    @NonNull
    private final TextView url2Label;

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
    private static final int MEDIA_ID = ck.br();
    private static final int TITLE_2_ID = ck.br();
    private static final int URL_2_ID = ck.br();
    private static final int CTA_ID = ck.br();
    private static final int STARS_ID = ck.br();
    private static final int VOTES_ID = ck.br();
    private static final int RATING_ID = ck.br();

    public ContentStreamAdView(@NonNull Context context) {
        this(context, null);
    }

    public ContentStreamAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ContentStreamAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.ageRestrictionLabel = new bu(context);
        this.advertisingLabel = new TextView(context);
        this.iconImageView = new by(context);
        this.labelsLayout = new LinearLayout(context);
        this.titleLabel = new TextView(context);
        this.urlLabel = new TextView(context);
        this.descriptionLabel = new TextView(context);
        this.title2Label = new TextView(context);
        this.url2Label = new TextView(context);
        this.ratingLayout = new LinearLayout(context);
        this.starsView = new bz(context);
        this.votesLabel = new TextView(context);
        this.disclaimerLabel = new TextView(context);
        this.ctaButton = new Button(context);
        this.uiUtils = ck.x(context);
        ck.a(this, "ad_view");
        ck.a(this.ageRestrictionLabel, "age_bordered");
        ck.a(this.advertisingLabel, "advertising_label");
        ck.a(this.titleLabel, "title_text");
        ck.a(this.descriptionLabel, "description_text");
        ck.a(this.title2Label, "title_text_2");
        ck.a(this.starsView, "rating_view");
        ck.a(this.votesLabel, "votes_text");
        ck.a(this.disclaimerLabel, "disclaimer_text");
        ck.a(this.ctaButton, "cta_button");
        ck.a(this.iconImageView, "icon_image");
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
    public Button getCtaButtonView() {
        return this.ctaButton;
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

    @Nullable
    public MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    @Nullable
    public PromoCardRecyclerView getPromoCardRecyclerView() {
        return this.promoCardRecyclerView;
    }

    @NonNull
    public TextView getSecondDomainOrCategoryTextView() {
        return this.url2Label;
    }

    @NonNull
    public TextView getSecondTitleTextView() {
        return this.title2Label;
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
        ViewParent parent;
        if (nativePromoBanner == null) {
            return;
        }
        this.banner = nativePromoBanner;
        g.a("Setup banner");
        boolean z = !nativePromoBanner.getCards().isEmpty();
        if (z) {
            this.title2Label.setVisibility(8);
            this.url2Label.setVisibility(8);
            this.ctaButton.setVisibility(8);
            this.ratingLayout.setVisibility(8);
            this.disclaimerLabel.setVisibility(8);
            MediaAdView mediaAdView = this.mediaAdView;
            if (mediaAdView != null && mediaAdView.getParent() != null) {
                ((ViewGroup) this.mediaAdView.getParent()).removeView(this.mediaAdView);
                this.mediaAdView = null;
            }
            if (this.promoCardRecyclerView == null) {
                this.promoCardRecyclerView = NativeViewsFactory.getPromoCardRecyclerView(getContext());
                this.promoCardRecyclerView.setId(MEDIA_ID);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(3, LABELS_ID);
                layoutParams.topMargin = this.uiUtils.l(2);
                this.promoCardRecyclerView.setLayoutParams(layoutParams);
                addView(this.promoCardRecyclerView);
            }
            this.promoCardRecyclerView.setPromoCardAdapter(useAdapter(nativePromoBanner.getCards()));
        } else {
            this.title2Label.setVisibility(0);
            this.ctaButton.setVisibility(0);
            PromoCardRecyclerView promoCardRecyclerView = this.promoCardRecyclerView;
            if (promoCardRecyclerView != null && (parent = promoCardRecyclerView.getParent()) != null) {
                ((ViewGroup) parent).removeView(this.promoCardRecyclerView);
                this.promoCardRecyclerView = null;
            }
            if (this.mediaAdView == null) {
                this.mediaAdView = NativeViewsFactory.getMediaAdView(getContext());
                this.mediaAdView.setId(MEDIA_ID);
                ck.a(this.mediaAdView, "media_view");
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams2.addRule(3, LABELS_ID);
                layoutParams2.topMargin = this.uiUtils.l(2);
                this.mediaAdView.setLayoutParams(layoutParams2);
                addView(this.mediaAdView);
            }
        }
        if ("web".equals(nativePromoBanner.getNavigationType())) {
            if (!z) {
                this.url2Label.setVisibility(0);
                this.ratingLayout.setVisibility(8);
                this.urlLabel.setText(nativePromoBanner.getDomain());
                this.url2Label.setText(nativePromoBanner.getDomain());
                this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
                this.disclaimerParams.addRule(3, URL_2_ID);
                this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
                ck.a(this.urlLabel, "domain_text");
                ck.a(this.url2Label, "domain_text_2");
            }
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
            ck.a(this.urlLabel, "category_text");
            ck.a(this.url2Label, "category_text_2");
            if (!z) {
                if (nativePromoBanner.getRating() > 0.0f && nativePromoBanner.getRating() <= 5.0f) {
                    this.starsView.setVisibility(0);
                    if (nativePromoBanner.getVotes() > 0) {
                        this.votesLabel.setVisibility(0);
                        this.votesLabel.setText(String.valueOf(nativePromoBanner.getVotes()));
                    } else {
                        this.votesLabel.setVisibility(8);
                    }
                    this.url2Label.setVisibility(8);
                    this.url2Label.setVisibility(8);
                    this.ratingLayout.setVisibility(0);
                    this.urlLabel.setText(str);
                    this.starsView.setRating(nativePromoBanner.getRating());
                    this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
                    this.disclaimerParams.addRule(3, RATING_ID);
                    this.disclaimerParams.addRule(0, CTA_ID);
                    this.disclaimerParams.rightMargin = this.uiUtils.l(4);
                    this.disclaimerParams.addRule(9, -1);
                    this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
                } else {
                    this.url2Label.setVisibility(0);
                    this.ratingLayout.setVisibility(8);
                    this.url2Label.setText(str);
                    this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
                    this.disclaimerParams.addRule(3, URL_2_ID);
                    this.disclaimerParams.addRule(0, CTA_ID);
                    this.disclaimerParams.rightMargin = this.uiUtils.l(4);
                    this.disclaimerParams.addRule(9, -1);
                    this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
                }
            }
            this.urlLabel.setText(str);
        }
        String disclaimer = nativePromoBanner.getDisclaimer();
        if (!TextUtils.isEmpty(disclaimer)) {
            this.disclaimerLabel.setVisibility(0);
            this.disclaimerLabel.setText(disclaimer);
        } else {
            this.disclaimerLabel.setVisibility(8);
        }
        ImageData icon = nativePromoBanner.getIcon();
        this.iconImageView.setImageData(icon);
        if (icon == null || icon.getData() == null) {
            this.iconImageView.setBackgroundColor(-1118482);
        }
        this.titleLabel.setText(nativePromoBanner.getTitle());
        this.descriptionLabel.setText(nativePromoBanner.getDescription());
        this.advertisingLabel.setText(nativePromoBanner.getAdvertisingLabel());
        if (!TextUtils.isEmpty(nativePromoBanner.getAgeRestrictions())) {
            this.ageRestrictionLabel.setText(nativePromoBanner.getAgeRestrictions());
        } else {
            this.ageRestrictionLabel.setVisibility(8);
        }
        this.ctaButton.setText(nativePromoBanner.getCtaText());
        this.title2Label.setText(nativePromoBanner.getTitle());
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
        this.advertisingLabel.setId(AD_ID);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(1, AGE_ID);
        this.advertisingLabel.setLayoutParams(layoutParams2);
        this.iconImageView.setId(ICON_ID);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(this.uiUtils.l(54), this.uiUtils.l(54));
        layoutParams3.addRule(3, AD_ID);
        layoutParams3.topMargin = this.uiUtils.l(9);
        this.iconImageView.setLayoutParams(layoutParams3);
        this.labelsLayout.setId(LABELS_ID);
        this.labelsLayout.setOrientation(1);
        this.labelsLayout.setMinimumHeight(this.uiUtils.l(54));
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams4.addRule(3, AD_ID);
        layoutParams4.addRule(1, ICON_ID);
        layoutParams4.leftMargin = this.uiUtils.l(9);
        layoutParams4.topMargin = this.uiUtils.l(2);
        this.labelsLayout.setLayoutParams(layoutParams4);
        this.titleLabel.setId(TITLE_ID);
        this.titleLabel.setLayoutParams(new RelativeLayout.LayoutParams(-2, -2));
        this.urlLabel.setId(URL_ID);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.topMargin = this.uiUtils.l(2);
        this.urlLabel.setLayoutParams(layoutParams5);
        this.descriptionLabel.setId(DESC_ID);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.topMargin = this.uiUtils.l(2);
        this.descriptionLabel.setLayoutParams(layoutParams6);
        this.title2Label.setId(TITLE_2_ID);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.addRule(3, MEDIA_ID);
        layoutParams7.topMargin = this.uiUtils.l(2);
        this.title2Label.setLayoutParams(layoutParams7);
        this.url2Label.setId(URL_2_ID);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams8.addRule(3, TITLE_2_ID);
        this.url2Label.setLayoutParams(layoutParams8);
        this.ratingLayout.setId(RATING_ID);
        this.ratingLayout.setOrientation(0);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams9.addRule(3, TITLE_2_ID);
        this.ratingLayout.setLayoutParams(layoutParams9);
        this.starsView.setId(STARS_ID);
        LinearLayout.LayoutParams layoutParams10 = new LinearLayout.LayoutParams(this.uiUtils.l(73), this.uiUtils.l(12));
        layoutParams10.topMargin = this.uiUtils.l(4);
        layoutParams10.rightMargin = this.uiUtils.l(4);
        this.starsView.setLayoutParams(layoutParams10);
        this.votesLabel.setId(VOTES_ID);
        this.disclaimerParams = new RelativeLayout.LayoutParams(-2, -2);
        this.disclaimerParams.addRule(3, RATING_ID);
        this.disclaimerLabel.setLayoutParams(this.disclaimerParams);
        this.ctaButton.setId(CTA_ID);
        this.ctaButton.setPadding(this.uiUtils.l(10), 0, this.uiUtils.l(10), 0);
        this.ctaButton.setMaxEms(8);
        this.ctaButton.setLines(1);
        this.ctaButton.setEllipsize(TextUtils.TruncateAt.END);
        RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-2, this.uiUtils.l(30));
        layoutParams11.addRule(3, TITLE_2_ID);
        layoutParams11.addRule(11);
        this.ctaButton.setLayoutParams(layoutParams11);
        this.ctaButton.setTransformationMethod(null);
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
        this.ctaButton.setBackgroundDrawable(stateListDrawable);
        setClickable(true);
        addView(this.ageRestrictionLabel);
        addView(this.advertisingLabel);
        addView(this.iconImageView);
        addView(this.labelsLayout);
        this.labelsLayout.addView(this.titleLabel);
        this.labelsLayout.addView(this.urlLabel);
        this.labelsLayout.addView(this.descriptionLabel);
        addView(this.title2Label);
        addView(this.url2Label);
        addView(this.ctaButton);
        addView(this.ratingLayout);
        addView(this.disclaimerLabel);
        this.ratingLayout.addView(this.starsView);
        this.ratingLayout.addView(this.votesLabel);
        updateDefaultParams();
    }

    @NonNull
    private PromoCardRecyclerView.PromoCardAdapter useAdapter(@NonNull List<NativePromoCard> list) {
        if (this.cardAdapter == null) {
            this.cardAdapter = new PromoCardRecyclerView.PromoCardAdapter() { // from class: com.my.target.nativeads.views.ContentStreamAdView.1
                @Override // com.my.target.nativeads.views.PromoCardRecyclerView.PromoCardAdapter
                @NonNull
                public PromoCardView getPromoCardView() {
                    return NativeViewsFactory.getContentStreamCardView(ContentStreamAdView.this.getContext());
                }
            };
        }
        this.cardAdapter.setCards(list);
        return this.cardAdapter;
    }

    private void updateDefaultParams() {
        this.ageRestrictionLabel.setTextColor(-6710887);
        this.ageRestrictionLabel.c(1, -6710887);
        this.ageRestrictionLabel.setBackgroundColor(0);
        this.advertisingLabel.setTextSize(2, 14.0f);
        this.advertisingLabel.setTextColor(-6710887);
        this.titleLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.titleLabel.setTextSize(2, 16.0f);
        this.titleLabel.setTypeface(null, 1);
        this.urlLabel.setTextColor(-6710887);
        this.urlLabel.setTextSize(2, 14.0f);
        this.descriptionLabel.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.descriptionLabel.setTextSize(2, 14.0f);
        this.title2Label.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.title2Label.setTextSize(2, 16.0f);
        this.title2Label.setTypeface(null, 1);
        this.url2Label.setTextColor(-6710887);
        this.url2Label.setTextSize(2, 14.0f);
        this.votesLabel.setTextColor(-6710887);
        this.votesLabel.setTextSize(2, 14.0f);
        this.disclaimerLabel.setTextColor(-6710887);
        this.disclaimerLabel.setTextSize(2, 12.0f);
        this.ctaButton.setTextColor(STANDARD_BLUE);
    }
}
