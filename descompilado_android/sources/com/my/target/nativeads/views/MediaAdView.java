package com.my.target.nativeads.views;

import android.R;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.bw;
import com.my.target.by;
import com.my.target.ck;
import com.my.target.core.resources.a;

/* JADX INFO: loaded from: classes2.dex */
public class MediaAdView extends ViewGroup {
    public static final int COLOR_PLACEHOLDER_GRAY = -1118482;

    @NonNull
    private final by imageView;
    private int placeholderHeight;
    private int placeholderWidth;

    @NonNull
    private final bw playButton;

    @NonNull
    private final ProgressBar progressBar;
    private static final int IMAGE_ID = ck.br();
    private static final int BUTTON_ID = ck.br();
    private static final int PROGRESS_ID = ck.br();

    public MediaAdView(@NonNull Context context) {
        super(context);
        this.imageView = new by(context);
        this.playButton = new bw(context);
        this.progressBar = new ProgressBar(context, null, R.attr.progressBarStyle);
        initViews(context);
    }

    public MediaAdView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.imageView = new by(context);
        this.playButton = new bw(context);
        this.progressBar = new ProgressBar(context, null, R.attr.progressBarStyle);
        initViews(context);
    }

    public MediaAdView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.imageView = new by(context);
        this.playButton = new bw(context);
        this.progressBar = new ProgressBar(context, null, R.attr.progressBarStyle);
        initViews(context);
    }

    @NonNull
    public ProgressBar getProgressBarView() {
        return this.progressBar;
    }

    @NonNull
    public ImageView getImageView() {
        return this.imageView;
    }

    @NonNull
    public View getPlayButtonView() {
        return this.playButton;
    }

    public void setPlaceHolderDimension(int i, int i2) {
        this.placeholderWidth = i;
        this.placeholderHeight = i2;
    }

    @Override // android.view.View
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
        this.playButton.setOnClickListener(onClickListener);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        float f;
        int i4;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i5 = this.placeholderHeight;
        if (i5 <= 0 || (i3 = this.placeholderWidth) <= 0) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(0, 1073741824), View.MeasureSpec.makeMeasureSpec(0, 1073741824));
            return;
        }
        float f2 = i3 / i5;
        if (f2 == 0.0f) {
            super.onMeasure(i, i2);
            return;
        }
        if (mode == 0 && mode2 == 0) {
            size2 = i5;
            size = i3;
        } else if (mode == 0) {
            size = (int) (size2 * f2);
        } else {
            if (mode2 == 0) {
                f = size;
            } else {
                f = size;
                float f3 = f / this.placeholderWidth;
                float f4 = size2;
                if (Math.min(f3, f4 / this.placeholderHeight) != f3) {
                    size = (int) Math.ceil(f4 * f2);
                }
            }
            size2 = (int) (f / f2);
        }
        for (int i6 = 0; i6 < getChildCount(); i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                int i7 = Integer.MIN_VALUE;
                if (childAt == this.imageView) {
                    i4 = 1073741824;
                    i7 = 1073741824;
                } else {
                    i4 = Integer.MIN_VALUE;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(size, i7), View.MeasureSpec.makeMeasureSpec(size2, i4));
            }
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i6 = ((i3 - i) - measuredWidth) / 2;
                int i7 = ((i4 - i2) - measuredHeight) / 2;
                childAt.layout(i6, i7, measuredWidth + i6, measuredHeight + i7);
            }
        }
    }

    private void initViews(@NonNull Context context) {
        ck.a(this.imageView, "media_image");
        this.imageView.setId(IMAGE_ID);
        ck.a(this.progressBar, "progress_bar");
        this.progressBar.setId(PROGRESS_ID);
        ck.a(this.playButton, "play_button");
        this.playButton.setId(BUTTON_ID);
        setBackgroundColor(COLOR_PLACEHOLDER_GRAY);
        this.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        this.imageView.setAdjustViewBounds(false);
        addView(this.imageView);
        this.progressBar.setVisibility(8);
        this.progressBar.getIndeterminateDrawable().setColorFilter(-16733198, PorterDuff.Mode.SRC_ATOP);
        addView(this.progressBar);
        this.playButton.a(a.getPlayIcon(ck.x(context).l(64)), false);
        this.playButton.setVisibility(8);
        addView(this.playButton);
    }
}
