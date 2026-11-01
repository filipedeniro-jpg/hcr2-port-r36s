package net.nend.android.internal.ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.core.view.ViewCompat;
import net.nend.android.internal.utilities.n;

/* JADX INFO: compiled from: BottomOverlayView.java */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public class a extends RelativeLayout {
    private final TextView a;
    private final ToggleButton b;
    private final InterfaceC0117a c;

    /* JADX INFO: renamed from: net.nend.android.internal.ui.views.video.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BottomOverlayView.java */
    public interface InterfaceC0117a {
        void a(boolean z);
    }

    public a(Context context, InterfaceC0117a interfaceC0117a) {
        super(context);
        this.b = new ToggleButton(context);
        this.a = new TextView(context);
        this.c = interfaceC0117a;
        a();
    }

    private void a() {
        this.b.setBackgroundResource(n.e(getContext(), "nend_ad_video_toggle_volume"));
        this.b.setTextOff("");
        this.b.setTextOn("");
        this.b.setChecked(false);
        int iF = n.f(getContext(), "nend_video_ad_overlay_elements_margin");
        int iF2 = n.f(getContext(), "nend_video_ad_overlay_content_size");
        int dimensionPixelSize = getResources().getDimensionPixelSize(iF);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(iF2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        layoutParams.setMargins(dimensionPixelSize, 0, 0, dimensionPixelSize);
        this.b.setLayoutParams(layoutParams);
        this.a.setTextSize(1, 16.0f);
        this.a.setBackgroundResource(n.e(getContext(), "nend_ad_video_mask"));
        this.a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.a.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams2.addRule(11);
        layoutParams2.addRule(12);
        layoutParams2.setMargins(0, 0, dimensionPixelSize, dimensionPixelSize);
        this.a.setLayoutParams(layoutParams2);
        addView(this.b);
        addView(this.a);
        this.b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: net.nend.android.internal.ui.views.video.a.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                a.this.setMute(!z);
            }
        });
    }

    public void setCounterText(String str) {
        this.a.setText(str);
    }

    public void setCheckToggleButton(boolean z) {
        this.b.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMute(boolean z) {
        this.c.a(z);
    }
}
