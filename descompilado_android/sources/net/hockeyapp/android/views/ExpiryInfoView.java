package net.hockeyapp.android.views;

import android.content.Context;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import net.hockeyapp.android.utils.ViewHelper;

/* JADX INFO: loaded from: classes2.dex */
public class ExpiryInfoView extends RelativeLayout {
    public ExpiryInfoView(Context context) {
        this(context, "");
    }

    public ExpiryInfoView(Context context, String str) {
        super(context);
        loadLayoutParams(context);
        loadShadowView(context);
        loadTextView(context, str);
    }

    private void loadLayoutParams(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        setBackgroundColor(-1);
        setLayoutParams(layoutParams);
    }

    private void loadShadowView(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) TypedValue.applyDimension(1, 3.0f, getResources().getDisplayMetrics()));
        layoutParams.addRule(10, -1);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundDrawable(ViewHelper.getGradient());
        addView(imageView);
    }

    private void loadTextView(Context context, String str) {
        int iApplyDimension = (int) TypedValue.applyDimension(1, 20.0f, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        layoutParams.setMargins(iApplyDimension, iApplyDimension, iApplyDimension, iApplyDimension);
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setLayoutParams(layoutParams);
        textView.setText(str);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        addView(textView);
    }
}
