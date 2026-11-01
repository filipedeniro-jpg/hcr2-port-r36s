package net.nend.android.internal.ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import net.nend.android.internal.utilities.n;

/* JADX INFO: compiled from: TopOverlayView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public class d extends RelativeLayout {
    private final Button a;
    private final a b;

    /* JADX INFO: compiled from: TopOverlayView.java */
    public interface a {
        void a();

        void b();
    }

    public d(Context context, String str, a aVar) {
        super(context);
        this.a = new Button(context);
        this.b = aVar;
        a(context, str);
    }

    private void a(Context context, String str) {
        this.a.setText(str);
        this.a.setTextColor(-1);
        this.a.setBackgroundResource(n.e(context, "nend_ad_video_cta"));
        this.a.setTypeface(Typeface.DEFAULT_BOLD);
        this.a.setTextSize(1, 20.0f);
        this.a.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        int dimensionPixelSize = getResources().getDimensionPixelSize(n.f(context, "nend_video_ad_overlay_elements_margin"));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, dimensionPixelSize, 0, 0);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        this.a.setLayoutParams(layoutParams);
        Button button = new Button(context);
        button.setBackgroundResource(n.e(context, "nend_ad_video_close"));
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(n.f(context, "nend_video_ad_overlay_content_size"));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams2.setMargins(0, dimensionPixelSize, dimensionPixelSize, 0);
        layoutParams2.addRule(11);
        layoutParams2.addRule(15);
        button.setLayoutParams(layoutParams2);
        addView(this.a);
        addView(button);
        this.a.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.views.video.d.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                d.this.b.a();
            }
        });
        button.setOnClickListener(new View.OnClickListener() { // from class: net.nend.android.internal.ui.views.video.d.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                d.this.b.b();
            }
        });
    }

    public void setHideCallToAction(boolean z) {
        if (z) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
        }
    }
}
