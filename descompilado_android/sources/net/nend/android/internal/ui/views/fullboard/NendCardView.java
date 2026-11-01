package net.nend.android.internal.ui.views.fullboard;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import net.nend.android.internal.utilities.n;

/* JADX INFO: loaded from: classes2.dex */
public class NendCardView extends FrameLayout {
    private static final d c;
    final Rect a;
    final Rect b;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            c = new a();
        } else {
            c = new b();
        }
        c.a();
    }

    public NendCardView(@NonNull Context context) {
        this(context, null);
    }

    public NendCardView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendCardView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Rect();
        this.b = new Rect();
        ColorStateList colorStateList = new ColorStateList(new int[][]{new int[]{R.attr.state_enabled}}, new int[]{-1});
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float f = displayMetrics.density * 8.0f;
        float f2 = displayMetrics.density * 4.0f;
        float f3 = displayMetrics.density * 2.0f;
        TypedArray typedArrayB = n.b(context, attributeSet, i);
        final boolean z = typedArrayB.getBoolean(n.g(context, "boardPreventCornerOverlap"), true);
        typedArrayB.recycle();
        c.a(new c() { // from class: net.nend.android.internal.ui.views.fullboard.NendCardView.1
            @Override // net.nend.android.internal.ui.views.fullboard.c
            public void a(Drawable drawable) {
                NendCardView.this.setBackgroundDrawable(drawable);
            }

            @Override // net.nend.android.internal.ui.views.fullboard.c
            public boolean a() {
                return z;
            }

            @Override // net.nend.android.internal.ui.views.fullboard.c
            public void a(int i2, int i3, int i4, int i5) {
                NendCardView.this.b.set(i2, i3, i4, i5);
                NendCardView nendCardView = NendCardView.this;
                NendCardView.super.setPadding(i2 + nendCardView.a.left, i3 + NendCardView.this.a.top, i4 + NendCardView.this.a.right, i5 + NendCardView.this.a.bottom);
            }

            @Override // net.nend.android.internal.ui.views.fullboard.c
            public View b() {
                return NendCardView.this;
            }
        }, context, colorStateList, f, f2, f3);
    }
}
