package net.hockeyapp.android.views;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import net.hockeyapp.android.utils.ViewHelper;

/* JADX INFO: loaded from: classes2.dex */
public class UpdateView extends RelativeLayout {
    public static final int HEADER_VIEW_ID = 4097;
    public static final int NAME_LABEL_ID = 4098;
    public static final int UPDATE_BUTTON_ID = 4100;
    public static final int VERSION_LABEL_ID = 4099;
    public static final int WEB_VIEW_ID = 4101;
    protected RelativeLayout headerView;
    protected boolean layoutHorizontally;
    protected boolean limitHeight;

    public UpdateView(Context context) {
        this(context, true);
    }

    public UpdateView(Context context, AttributeSet attributeSet) {
        this(context, true, false);
    }

    public UpdateView(Context context, boolean z) {
        this(context, true, false);
    }

    public UpdateView(Context context, boolean z, boolean z2) {
        super(context);
        this.headerView = null;
        this.layoutHorizontally = false;
        this.limitHeight = false;
        if (z) {
            setLayoutHorizontally(context);
        } else {
            this.layoutHorizontally = false;
        }
        this.limitHeight = z2;
        loadLayoutParams(context);
        loadHeaderView(context);
        loadWebView(context);
        loadShadow(this.headerView, context);
    }

    private void setLayoutHorizontally(Context context) {
        if (getResources().getConfiguration().orientation == 2) {
            this.layoutHorizontally = true;
        } else {
            this.layoutHorizontally = false;
        }
    }

    private void loadLayoutParams(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        setBackgroundColor(-1);
        setLayoutParams(layoutParams);
    }

    private void loadHeaderView(Context context) {
        RelativeLayout.LayoutParams layoutParams;
        this.headerView = new RelativeLayout(context);
        this.headerView.setId(4097);
        if (this.layoutHorizontally) {
            layoutParams = new RelativeLayout.LayoutParams((int) TypedValue.applyDimension(1, 250.0f, getResources().getDisplayMetrics()), -1);
            layoutParams.addRule(9, -1);
            this.headerView.setPadding(0, 0, 0, 0);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            this.headerView.setPadding(0, 0, 0, (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics()));
        }
        this.headerView.setLayoutParams(layoutParams);
        this.headerView.setBackgroundColor(Color.rgb(230, 236, 239));
        loadTitleLabel(this.headerView, context);
        loadVersionLabel(this.headerView, context);
        loadUpdateButton(this.headerView, context);
        addView(this.headerView);
    }

    private void loadTitleLabel(RelativeLayout relativeLayout, Context context) {
        TextView textView = new TextView(context);
        textView.setId(4098);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, iApplyDimension, iApplyDimension, 0);
        textView.setBackgroundColor(Color.rgb(230, 236, 239));
        textView.setLayoutParams(layoutParams);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        textView.setSingleLine(true);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(2, 20.0f);
        textView.setTypeface(null, 1);
        relativeLayout.addView(textView);
    }

    private void loadVersionLabel(RelativeLayout relativeLayout, Context context) {
        TextView textView = new TextView(context);
        textView.setId(4099);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        layoutParams.setMargins(iApplyDimension, (int) TypedValue.applyDimension(1, 10.0f, getResources().getDisplayMetrics()), iApplyDimension, 0);
        layoutParams.addRule(3, 4098);
        textView.setBackgroundColor(Color.rgb(230, 236, 239));
        textView.setLayoutParams(layoutParams);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setShadowLayer(1.0f, 0.0f, 1.0f, -1);
        textView.setLines(2);
        textView.setLineSpacing(0.0f, 1.1f);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        textView.setTextSize(2, 16.0f);
        textView.setTypeface(null, 1);
        relativeLayout.addView(textView);
    }

    private void loadUpdateButton(RelativeLayout relativeLayout, Context context) {
        Button button = new Button(context);
        button.setId(UPDATE_BUTTON_ID);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) TypedValue.applyDimension(1, 120.0f, getResources().getDisplayMetrics()), -2);
        layoutParams.setMargins(iApplyDimension, iApplyDimension, iApplyDimension, iApplyDimension);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(3, 4099);
        button.setLayoutParams(layoutParams);
        button.setBackgroundDrawable(getButtonSelector());
        button.setText("Update");
        button.setTextColor(-1);
        button.setTextSize(2, 16.0f);
        relativeLayout.addView(button);
    }

    private Drawable getButtonSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842919}, new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        stateListDrawable.addState(new int[]{-16842919, R.attr.state_focused}, new ColorDrawable(-12303292));
        stateListDrawable.addState(new int[]{R.attr.state_pressed}, new ColorDrawable(-7829368));
        return stateListDrawable;
    }

    private void loadShadow(RelativeLayout relativeLayout, Context context) {
        RelativeLayout.LayoutParams layoutParams;
        int iApplyDimension = (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics());
        ImageView imageView = new ImageView(context);
        if (this.layoutHorizontally) {
            layoutParams = new RelativeLayout.LayoutParams(1, -1);
            layoutParams.addRule(11, -1);
            imageView.setBackgroundDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        } else {
            layoutParams = new RelativeLayout.LayoutParams(-1, iApplyDimension);
            layoutParams.addRule(10, -1);
            imageView.setBackgroundDrawable(ViewHelper.getGradient());
        }
        imageView.setLayoutParams(layoutParams);
        relativeLayout.addView(imageView);
        ImageView imageView2 = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, iApplyDimension);
        if (this.layoutHorizontally) {
            layoutParams2.addRule(10, -1);
        } else {
            layoutParams2.addRule(3, 4097);
        }
        imageView2.setLayoutParams(layoutParams2);
        imageView2.setBackgroundDrawable(ViewHelper.getGradient());
        addView(imageView2);
    }

    private void loadWebView(Context context) {
        WebView webView = new WebView(context);
        webView.setId(WEB_VIEW_ID);
        int iApplyDimension = (int) TypedValue.applyDimension(1, 400.0f, context.getResources().getDisplayMetrics());
        if (!this.limitHeight) {
            iApplyDimension = -1;
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, iApplyDimension);
        if (this.layoutHorizontally) {
            layoutParams.addRule(1, 4097);
        } else {
            layoutParams.addRule(3, 4097);
        }
        layoutParams.setMargins(0, 0, 0, 0);
        webView.setLayoutParams(layoutParams);
        webView.setBackgroundColor(-1);
        addView(webView);
    }
}
