package com.my.target.nativeads.views;

import android.R;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.StateSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.my.target.ck;

/* JADX INFO: loaded from: classes2.dex */
public class ContentStreamCardView extends LinearLayout implements PromoCardView {
    private static final int MARGIN_DP = 12;
    private static final int STANDARD_BLUE = -16748844;

    @Nullable
    private View.OnClickListener cardClickListener;

    @NonNull
    private final RelativeLayout containerLayout;

    @NonNull
    private final Button ctaButton;

    @NonNull
    private final TextView descriptionView;

    @NonNull
    private final View.OnClickListener elementClickListener;

    @NonNull
    private final MediaAdView mediaAdView;

    @NonNull
    private final LinearLayout textContainerLayout;

    @NonNull
    private final TextView titleView;

    @NonNull
    private final ck uiUtils;
    private static final int CTA_ID = ck.br();
    private static final int MEDIA_ID = ck.br();

    @Override // com.my.target.nativeads.views.PromoCardView
    @NonNull
    public View getView() {
        return this;
    }

    public ContentStreamCardView(@NonNull Context context) {
        super(context);
        this.elementClickListener = new View.OnClickListener() { // from class: com.my.target.nativeads.views.ContentStreamCardView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ContentStreamCardView.this.cardClickListener != null) {
                    ContentStreamCardView.this.cardClickListener.onClick(ContentStreamCardView.this);
                }
            }
        };
        this.mediaAdView = new MediaAdView(context);
        this.titleView = new TextView(context);
        this.descriptionView = new TextView(context);
        this.containerLayout = new RelativeLayout(context);
        this.ctaButton = new Button(context);
        this.uiUtils = ck.x(context);
        this.textContainerLayout = new LinearLayout(context);
        ck.a(this, "card_view");
        ck.a(this.mediaAdView, "card_media_view");
        ck.a(this.titleView, "card_title_text");
        ck.a(this.descriptionView, "card_description_text");
        ck.a(this.ctaButton, "card_cta_text");
        initView();
    }

    @Override // com.my.target.nativeads.views.PromoCardView
    @NonNull
    public MediaAdView getMediaAdView() {
        return this.mediaAdView;
    }

    @Override // com.my.target.nativeads.views.PromoCardView
    @NonNull
    public TextView getTitleTextView() {
        return this.titleView;
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        this.cardClickListener = onClickListener;
        super.setOnClickListener(onClickListener);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            getChildAt(i).setOnClickListener(this.elementClickListener);
        }
    }

    @Override // com.my.target.nativeads.views.PromoCardView
    @NonNull
    public TextView getDescriptionTextView() {
        return this.descriptionView;
    }

    @Override // com.my.target.nativeads.views.PromoCardView
    @NonNull
    public Button getCtaButtonView() {
        return this.ctaButton;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return x > ((float) this.mediaAdView.getLeft()) && x < ((float) this.mediaAdView.getRight()) && y > ((float) this.mediaAdView.getTop()) && y < ((float) this.mediaAdView.getBottom());
    }

    private void initView() {
        this.mediaAdView.setId(MEDIA_ID);
        setOrientation(1);
        setPadding(this.uiUtils.l(8), this.uiUtils.l(8), this.uiUtils.l(8), this.uiUtils.l(8));
        setClickable(true);
        ck.a(this, 0, -3806472);
        ck.a(this.containerLayout, 0, -3806472, -3355444, this.uiUtils.l(1), 0);
        this.ctaButton.setId(CTA_ID);
        this.ctaButton.setMaxEms(10);
        this.ctaButton.setLines(1);
        this.ctaButton.setEllipsize(TextUtils.TruncateAt.END);
        this.ctaButton.setPadding(this.uiUtils.l(10), 0, this.uiUtils.l(10), 0);
        this.ctaButton.setTextSize(2, 12.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, this.uiUtils.l(30));
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12), this.uiUtils.l(12));
        this.ctaButton.setLayoutParams(layoutParams);
        this.ctaButton.setTransformationMethod(null);
        if (Build.VERSION.SDK_INT >= 21) {
            this.ctaButton.setStateListAnimator(null);
        }
        this.ctaButton.setTextColor(STANDARD_BLUE);
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
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(0, CTA_ID);
        this.textContainerLayout.setLayoutParams(layoutParams2);
        this.textContainerLayout.setGravity(16);
        this.textContainerLayout.setOrientation(1);
        this.titleView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.titleView.setTextSize(2, 14.0f);
        this.titleView.setTypeface(null, 1);
        this.titleView.setLines(2);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleView.setPadding(this.uiUtils.l(12), this.uiUtils.l(6), this.uiUtils.l(1), this.uiUtils.l(1));
        this.descriptionView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.descriptionView.setTextSize(2, 12.0f);
        this.descriptionView.setLines(1);
        this.descriptionView.setEllipsize(TextUtils.TruncateAt.END);
        this.descriptionView.setPadding(this.uiUtils.l(12), this.uiUtils.l(1), this.uiUtils.l(1), this.uiUtils.l(12));
        addView(this.mediaAdView);
        addView(this.containerLayout);
        this.containerLayout.addView(this.ctaButton);
        this.containerLayout.addView(this.textContainerLayout);
        this.textContainerLayout.addView(this.titleView);
        this.textContainerLayout.addView(this.descriptionView);
    }
}
