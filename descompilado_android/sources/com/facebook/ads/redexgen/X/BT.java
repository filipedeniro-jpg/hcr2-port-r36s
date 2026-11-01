package com.facebook.ads.redexgen.X;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.List;

/* JADX INFO: loaded from: assets/audience_network.dex */
@TargetApi(19)
public final class BT extends LinearLayout {
    private ImageView B;
    private InterfaceC0576Ho C;
    private ImageView D;
    private BM E;
    private String F;
    private static final int H = Color.rgb(224, 224, 224);
    private static final Uri G = Uri.parse("http://www.facebook.com");
    private static final View.OnTouchListener I = new BZ();
    private static final int J = Color.argb(34, 0, 0, 0);

    public BT(Context context) {
        super(context);
        E(context);
    }

    private void E(Context context) {
        float f = getResources().getDisplayMetrics().density;
        int i = (int) (50.0f * f);
        int i2 = (int) (4.0f * f);
        J6.O(this, -1);
        setGravity(16);
        this.B = new ImageView(context);
        this.B.setContentDescription("Close");
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
        this.B.setScaleType(ImageView.ScaleType.CENTER);
        this.B.setImageBitmap(C0605Ir.E(EnumC0606Is.BROWSER_CLOSE));
        this.B.setOnTouchListener(I);
        this.B.setOnClickListener(new BY(this));
        addView(this.B, layoutParams);
        this.E = new BM(context);
        LinearLayout.LayoutParams titleViewParams = new LinearLayout.LayoutParams(0, -2);
        titleViewParams.weight = 1.0f;
        this.E.setPadding(0, i2, 0, i2);
        addView(this.E, titleViewParams);
        this.D = new ImageView(context);
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i, i);
        this.D.setContentDescription("Open native browser");
        this.D.setScaleType(ImageView.ScaleType.CENTER);
        this.D.setOnTouchListener(I);
        this.D.setOnClickListener(new BU(this));
        addView(this.D, layoutParams2);
        setupDefaultNativeBrowser(context);
    }

    public void setListener(InterfaceC0576Ho mListener) {
        this.C = mListener;
    }

    public void setTitle(String title) {
        this.E.setTitle(title);
    }

    public void setUrl(String mUrl) {
        this.F = mUrl;
        if (TextUtils.isEmpty(mUrl) || "about:blank".equals(mUrl)) {
            this.E.setSubtitle(null);
            this.D.setEnabled(false);
            this.D.setColorFilter(new PorterDuffColorFilter(H, PorterDuff.Mode.SRC_IN));
        } else {
            this.E.setSubtitle(mUrl);
            this.D.setEnabled(true);
            this.D.setColorFilter((ColorFilter) null);
        }
    }

    private void setupDefaultNativeBrowser(Context context) {
        Bitmap bitmapE;
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = pm.queryIntentActivities(new Intent("android.intent.action.VIEW", G), 65536);
        if (listQueryIntentActivities.size() == 0) {
            this.D.setVisibility(8);
            bitmapE = null;
        } else if (listQueryIntentActivities.size() == 1 && "com.android.chrome".equals(listQueryIntentActivities.get(0).activityInfo.packageName)) {
            bitmapE = C0605Ir.E(EnumC0606Is.BROWSER_LAUNCH_CHROME);
        } else {
            bitmapE = C0605Ir.E(EnumC0606Is.BROWSER_LAUNCH_NATIVE);
        }
        this.D.setImageBitmap(bitmapE);
    }
}
