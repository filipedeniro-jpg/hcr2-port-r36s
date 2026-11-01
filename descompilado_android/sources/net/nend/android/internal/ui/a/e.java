package net.nend.android.internal.ui.a;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.VisibleForTesting;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.NumberFormat;
import net.nend.android.internal.utilities.n;

/* JADX INFO: compiled from: NativeVideoUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class e {
    private static FrameLayout.LayoutParams a(int i, int i2, float f) {
        int iB = (int) (b(i, i2, f) * f);
        return new FrameLayout.LayoutParams(iB, iB);
    }

    private static int b(int i, int i2, float f) {
        return (int) (Math.min(i, i2) * f);
    }

    private static boolean b(View view, int i) {
        return view != null && (i < view.getHeight() || view.getHeight() == 0);
    }

    public static void a(int i, int i2, ImageView imageView) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        FrameLayout.LayoutParams layoutParamsA = a(i, i2, 0.3f);
        layoutParamsA.gravity = 83;
        if (b(imageView, layoutParamsA.height)) {
            imageView.setLayoutParams(layoutParamsA);
        }
    }

    public static View a(FrameLayout frameLayout, Context context, net.nend.android.internal.b.c.b bVar) {
        String str;
        String str2;
        if (bVar.c == 1) {
            str = "media_replay_cta_port";
            str2 = "media_view_replay_cta_port";
        } else {
            str = "media_replay_cta_land";
            str2 = "media_view_replay_cta_land";
        }
        return View.inflate(context, n.c(context, str), (ViewGroup) frameLayout.findViewById(n.b(context, str2)));
    }

    public static float a(View view, int i) {
        float f;
        int width;
        int iA = a(view.getContext(), i);
        if (i == 1) {
            f = 0.5f;
            width = view.getHeight();
        } else {
            f = 0.7f;
            width = view.getWidth();
        }
        return Math.min(width / 1.7777778f, iA) * f;
    }

    private static int a(Context context, int i) {
        return context.getResources().getDimensionPixelSize(n.f(context, i == 1 ? "max_width_rectangle_media_view_replay_cta_port" : "max_height_rectangle_media_view_replay_cta_land"));
    }

    public static void a(Context context, View view, float f, float f2, int i) {
        String str;
        String str2 = "rectangle_media_view_replay_cta_land";
        if (i == 1) {
            str = f2 / f <= 1.7777778f ? "w,9:16" : "h,9:16";
            str2 = "rectangle_media_view_replay_cta_port";
        } else {
            str = f / f2 <= 1.7777778f ? "h,16:9" : "w,16:9";
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(n.b(context, str2));
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        layoutParams.dimensionRatio = str;
        constraintLayout.setLayoutParams(layoutParams);
    }

    @VisibleForTesting
    static int a(Context context, float f, float f2) {
        if (f >= f2) {
            return n.e(context, "nend_ad_star_none");
        }
        if (f2 - f <= 0.5f) {
            return n.e(context, "nend_ad_star_half");
        }
        return n.e(context, "nend_ad_star_filled");
    }

    public static void a(Activity activity, net.nend.android.internal.b.c.b bVar, int[] iArr) {
        float f = 0.0f;
        for (int i : iArr) {
            int iA = a(activity, f, bVar.m);
            ImageView imageView = (ImageView) activity.findViewById(i);
            imageView.setImageResource(iA);
            imageView.setTag(Integer.valueOf(iA));
            f += 1.0f;
        }
    }

    public static String a(long j) {
        return "(" + NumberFormat.getNumberInstance().format(j) + ")";
    }
}
