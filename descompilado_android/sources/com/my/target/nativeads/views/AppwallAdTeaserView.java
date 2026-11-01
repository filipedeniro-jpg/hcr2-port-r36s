package com.my.target.nativeads.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.by;
import com.my.target.bz;
import com.my.target.ck;
import com.my.target.common.models.ImageData;
import com.my.target.nativeads.banners.NativeAppwallBanner;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class AppwallAdTeaserView extends RelativeLayout {

    @Nullable
    private NativeAppwallBanner banner;

    @NonNull
    private final by bannerIcon;

    @NonNull
    private final ShapeDrawable coinsBgShape;

    @NonNull
    private final TextView coinsCountView;

    @NonNull
    private final by coinsIconView;

    @NonNull
    private final LinearLayout coinsLayout;

    @NonNull
    private final TextView descrView;

    @NonNull
    private final by notificationView;

    @NonNull
    private final by openImageView;

    @NonNull
    private final bz starsRatingView;

    @NonNull
    private final by statusIconView;
    private final int textColor;

    @NonNull
    private final TextView titleView;

    @NonNull
    private final ck uiUtils;
    private boolean viewed;

    @NonNull
    private final TextView votesCountView;

    public AppwallAdTeaserView(Context context) {
        super(context);
        this.textColor = Color.rgb(36, 36, 36);
        this.viewed = false;
        this.bannerIcon = new by(context);
        this.coinsLayout = new LinearLayout(context);
        this.coinsCountView = new TextView(context);
        this.coinsIconView = new by(context);
        this.openImageView = new by(context);
        this.statusIconView = new by(context);
        this.titleView = new TextView(context);
        this.descrView = new TextView(context);
        this.starsRatingView = new bz(context);
        this.votesCountView = new TextView(context);
        this.notificationView = new by(context);
        this.uiUtils = ck.x(context);
        float fL = this.uiUtils.l(6);
        this.coinsBgShape = new ShapeDrawable(new RoundRectShape(new float[]{fL, fL, fL, fL, fL, fL, fL, fL}, null, null));
        P();
    }

    public void setNativeAppwallBanner(NativeAppwallBanner nativeAppwallBanner) {
        this.banner = nativeAppwallBanner;
        this.bannerIcon.setImageData(nativeAppwallBanner.getIcon());
        ImageData bubbleIcon = nativeAppwallBanner.getBubbleIcon();
        this.notificationView.setImageData(bubbleIcon);
        String description = nativeAppwallBanner.getDescription();
        this.titleView.setText(nativeAppwallBanner.getTitle());
        this.descrView.setText(description);
        if (nativeAppwallBanner.isHasNotification()) {
            this.notificationView.setVisibility(0);
            this.notificationView.setImageData(bubbleIcon);
        } else {
            this.notificationView.setVisibility(8);
        }
        if (nativeAppwallBanner.getCoins() > 0) {
            this.coinsLayout.setVisibility(0);
            this.coinsIconView.setImageData(nativeAppwallBanner.getCoinsIcon());
            this.coinsCountView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(nativeAppwallBanner.getCoins())));
            this.coinsCountView.setTextColor(nativeAppwallBanner.getCoinsIconTextColor());
            this.coinsBgShape.getPaint().setColor(nativeAppwallBanner.getCoinsIconBgColor());
            this.openImageView.setVisibility(8);
        } else if (nativeAppwallBanner.isAppInstalled()) {
            this.coinsLayout.setVisibility(8);
            this.openImageView.setVisibility(0);
            this.openImageView.setImageData(nativeAppwallBanner.getGotoAppIcon());
        } else {
            this.coinsLayout.setVisibility(8);
            this.openImageView.setVisibility(8);
        }
        ImageData statusIcon = nativeAppwallBanner.getStatusIcon();
        if (statusIcon != null) {
            this.statusIconView.setVisibility(0);
            this.statusIconView.setImageData(statusIcon);
        } else {
            this.statusIconView.setVisibility(8);
        }
        if (nativeAppwallBanner.getCoins() != 0 || nativeAppwallBanner.isAppInstalled()) {
            this.descrView.setPadding(0, 0, this.uiUtils.l(70), 0);
        } else if (statusIcon != null) {
            this.descrView.setPadding(0, 0, this.uiUtils.l(20), 0);
        }
        if (nativeAppwallBanner.getRating() > 0.0f) {
            this.starsRatingView.setRating(nativeAppwallBanner.getRating());
            this.starsRatingView.setVisibility(0);
            if (nativeAppwallBanner.getVotes() > 0) {
                this.votesCountView.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(nativeAppwallBanner.getVotes())));
                this.votesCountView.setVisibility(0);
                return;
            } else {
                this.votesCountView.setVisibility(8);
                return;
            }
        }
        this.starsRatingView.setVisibility(8);
        this.votesCountView.setVisibility(8);
        TextView textView = this.descrView;
        textView.setPadding(textView.getPaddingLeft(), this.descrView.getPaddingTop(), this.descrView.getPaddingRight(), this.uiUtils.l(20));
    }

    @Nullable
    public NativeAppwallBanner getBanner() {
        return this.banner;
    }

    public boolean isViewed() {
        return this.viewed;
    }

    public void setViewed(boolean z) {
        this.viewed = z;
    }

    @NonNull
    public ImageView getNotificationImageView() {
        return this.notificationView;
    }

    @NonNull
    public ImageView getOpenImageView() {
        return this.openImageView;
    }

    @NonNull
    public ImageView getBannerIconImageView() {
        return this.bannerIcon;
    }

    @NonNull
    public TextView getCoinsCountTextView() {
        return this.coinsCountView;
    }

    @NonNull
    public TextView getDescriptionTextView() {
        return this.descrView;
    }

    @NonNull
    public bz getStarsRatingView() {
        return this.starsRatingView;
    }

    @NonNull
    public TextView getVotesCountTextView() {
        return this.votesCountView;
    }

    @NonNull
    public ImageView getStatusIconImageView() {
        return this.statusIconView;
    }

    @NonNull
    public TextView getTitleTextView() {
        return this.titleView;
    }

    @NonNull
    public ImageView getCoinsIconImageView() {
        return this.coinsIconView;
    }

    protected void removeNotification() {
        removeView(this.notificationView);
    }

    private void P() {
        int iL = this.uiUtils.l(18);
        int iL2 = this.uiUtils.l(14);
        int iL3 = this.uiUtils.l(53);
        int iBr = ck.br();
        int iBr2 = ck.br();
        int iBr3 = ck.br();
        setBackgroundColor(-1);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iL3 + iL2 + iL2, iL3 + iL + iL);
        this.bannerIcon.setPadding(iL2, iL, iL2, iL);
        addView(this.bannerIcon, layoutParams);
        int iL4 = this.uiUtils.l(20);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(iL4, iL4);
        layoutParams2.leftMargin = this.uiUtils.l(57);
        layoutParams2.topMargin = this.uiUtils.l(10);
        this.notificationView.setLayoutParams(layoutParams2);
        addView(this.notificationView);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(iL3, iL3);
        layoutParams3.addRule(11);
        layoutParams3.rightMargin = iL2;
        layoutParams3.topMargin = iL;
        this.coinsLayout.setBackgroundDrawable(this.coinsBgShape);
        this.coinsLayout.setOrientation(1);
        addView(this.coinsLayout, layoutParams3);
        LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        this.coinsCountView.setTypeface(Typeface.SANS_SERIF);
        this.coinsCountView.setPadding(0, this.uiUtils.l(10), 0, this.uiUtils.l(2));
        this.coinsCountView.setTextSize(2, 13.0f);
        this.coinsCountView.setGravity(49);
        this.coinsLayout.addView(this.coinsCountView, layoutParams4);
        LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(this.uiUtils.l(20), this.uiUtils.l(20));
        layoutParams5.gravity = 1;
        this.coinsLayout.addView(this.coinsIconView, layoutParams5);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(this.uiUtils.l(19), -2);
        layoutParams6.addRule(15);
        layoutParams6.addRule(11);
        layoutParams6.rightMargin = this.uiUtils.l(30);
        addView(this.openImageView, layoutParams6);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(iL3, iL3);
        layoutParams7.addRule(10);
        layoutParams7.addRule(11);
        addView(this.statusIconView, layoutParams7);
        this.titleView.setTypeface(Typeface.SANS_SERIF);
        this.titleView.setTextSize(2, 18.0f);
        this.titleView.setTextColor(this.textColor);
        this.titleView.setPadding(0, 0, this.uiUtils.l(67), 0);
        this.titleView.setId(iBr3);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams8.leftMargin = this.uiUtils.l(91);
        layoutParams8.rightMargin = this.uiUtils.l(15);
        layoutParams8.topMargin = this.uiUtils.l(13);
        this.titleView.setLayoutParams(layoutParams8);
        addView(this.titleView);
        this.descrView.setTypeface(Typeface.SANS_SERIF);
        this.descrView.setTextSize(2, 13.0f);
        this.descrView.setTextColor(this.textColor);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams9.leftMargin = this.uiUtils.l(91);
        layoutParams9.addRule(3, iBr3);
        this.descrView.setId(iBr);
        this.descrView.setLayoutParams(layoutParams9);
        addView(this.descrView);
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams10.addRule(3, iBr);
        layoutParams10.leftMargin = this.uiUtils.l(91);
        layoutParams10.topMargin = this.uiUtils.l(5);
        this.starsRatingView.setPadding(0, 0, 0, this.uiUtils.l(20));
        this.starsRatingView.setStarsPadding(this.uiUtils.l(2));
        this.starsRatingView.setStarSize(this.uiUtils.l(12));
        this.starsRatingView.setId(iBr2);
        addView(this.starsRatingView, layoutParams10);
        RelativeLayout.LayoutParams layoutParams11 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams11.addRule(1, iBr2);
        layoutParams11.addRule(3, iBr);
        layoutParams11.leftMargin = this.uiUtils.l(9);
        this.votesCountView.setTypeface(Typeface.SANS_SERIF);
        this.votesCountView.setPadding(0, this.uiUtils.l(2), 0, 0);
        this.votesCountView.setTextSize(2, 13.0f);
        this.votesCountView.setTextColor(this.textColor);
        this.votesCountView.setGravity(16);
        addView(this.votesCountView, layoutParams11);
    }
}
