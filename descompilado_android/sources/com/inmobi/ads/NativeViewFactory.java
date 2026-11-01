package com.inmobi.ads;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.inmobi.rendering.CustomView;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.squareup.picasso.Callback;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/* JADX INFO: loaded from: classes2.dex */
class NativeViewFactory {
    private static final String a = "NativeViewFactory";

    @NonNull
    private static final Map<Class, Integer> c;

    @Nullable
    private static volatile WeakReference<NativeViewFactory> e = null;
    private static WeakReference<Context> f = null;
    private static int g = 1;
    private static int h = 1;
    private int b;

    @NonNull
    private Map<Integer, c> d;

    static /* synthetic */ int a(NativeViewFactory nativeViewFactory) {
        int i = nativeViewFactory.b;
        nativeViewFactory.b = i + 1;
        return i;
    }

    static /* synthetic */ int b(NativeViewFactory nativeViewFactory) {
        int i = nativeViewFactory.b;
        nativeViewFactory.b = i - 1;
        return i;
    }

    private abstract class c {

        @NonNull
        LinkedList<View> b = new LinkedList<>();
        private int a = 0;
        private int d = 0;

        protected abstract View a(@NonNull Context context);

        public c() {
        }

        public boolean a(@NonNull View view) {
            NativeViewFactory.b(view);
            view.setOnClickListener(null);
            this.b.addLast(view);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
            NativeViewFactory.a(NativeViewFactory.this);
            return true;
        }

        public final View a(@NonNull Context context, ak akVar, com.inmobi.ads.c cVar) {
            View viewRemoveFirst;
            WeakReference unused = NativeViewFactory.f = new WeakReference(context);
            if (this.b.isEmpty()) {
                this.a++;
                viewRemoveFirst = a(context);
            } else {
                this.d++;
                viewRemoveFirst = this.b.removeFirst();
                NativeViewFactory.b(NativeViewFactory.this);
            }
            a(viewRemoveFirst, akVar, cVar);
            return viewRemoveFirst;
        }

        public String toString() {
            return "Size:" + this.b.size() + " Miss Count:" + this.a + " Hit Count:" + this.d;
        }

        protected void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
            view.setVisibility(akVar.x);
            view.setOnClickListener(null);
        }
    }

    static {
        HashMap map = new HashMap();
        c = map;
        map.put(aw.class, 0);
        c.put(bp.class, 1);
        c.put(bo.class, 2);
        c.put(NativeContainerLayout.class, 3);
        c.put(ImageView.class, 6);
        c.put(NativeVideoWrapper.class, 7);
        c.put(b.class, 4);
        c.put(Button.class, 5);
        c.put(NativeTimerView.class, 8);
        c.put(RenderView.class, 9);
        c.put(GifView.class, 10);
    }

    static void a(int i) {
        g = i;
    }

    static void b(int i) {
        h = i;
    }

    static int c(int i) {
        int i2;
        Context context = f.get();
        if ((context != null && (context instanceof InMobiAdActivity)) || (i2 = g) == 0) {
            return i;
        }
        double d = i;
        double d2 = i2;
        Double.isNaN(d2);
        double d3 = h;
        Double.isNaN(d3);
        Double.isNaN(d);
        return (int) (d * ((d2 * 1.0d) / d3));
    }

    @SuppressLint({"UseSparseArrays"})
    private NativeViewFactory(Context context) {
        f = new WeakReference<>(context);
        this.d = new HashMap();
        this.d.put(0, new c() { // from class: com.inmobi.ads.NativeViewFactory.1
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new aw(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(3, new c() { // from class: com.inmobi.ads.NativeViewFactory.5
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new NativeContainerLayout(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(1, new c() { // from class: com.inmobi.ads.NativeViewFactory.6
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new bp(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                ((bp) view).a = null;
                return super.a(view);
            }
        });
        this.d.put(2, new c() { // from class: com.inmobi.ads.NativeViewFactory.7
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new bo(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(view, akVar.c);
            }
        });
        this.d.put(6, new c() { // from class: com.inmobi.ads.NativeViewFactory.8
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new ImageView(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((ImageView) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                if (!(view instanceof ImageView)) {
                    return false;
                }
                ((ImageView) view).setImageDrawable(null);
                return super.a(view);
            }
        });
        this.d.put(10, new c() { // from class: com.inmobi.ads.NativeViewFactory.9
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new GifView(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((GifView) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                if (!(view instanceof GifView)) {
                    return false;
                }
                ((GifView) view).setGif(null);
                return super.a(view);
            }
        });
        this.d.put(7, new c() { // from class: com.inmobi.ads.NativeViewFactory.10
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new NativeVideoWrapper(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((NativeVideoWrapper) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            @TargetApi(15)
            public final boolean a(@NonNull View view) {
                if (!(view instanceof NativeVideoWrapper)) {
                    return false;
                }
                NativeVideoWrapper nativeVideoWrapper = (NativeVideoWrapper) view;
                nativeVideoWrapper.getProgressBar().setVisibility(8);
                nativeVideoWrapper.getPoster().setImageBitmap(null);
                nativeVideoWrapper.getVideoView().a();
                return super.a(view);
            }
        });
        this.d.put(4, new c() { // from class: com.inmobi.ads.NativeViewFactory.11
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new b(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((TextView) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                NativeViewFactory.a((TextView) view);
                return super.a(view);
            }
        });
        this.d.put(5, new c() { // from class: com.inmobi.ads.NativeViewFactory.12
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new Button(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.b((Button) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                if (!(view instanceof Button)) {
                    return false;
                }
                NativeViewFactory.a((TextView) view);
                return super.a(view);
            }
        });
        this.d.put(8, new c() { // from class: com.inmobi.ads.NativeViewFactory.2
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new NativeTimerView(context2.getApplicationContext());
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a(NativeViewFactory.this, (NativeTimerView) view, akVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                return (view instanceof NativeTimerView) && super.a(view);
            }
        });
        this.d.put(9, new c() { // from class: com.inmobi.ads.NativeViewFactory.3
            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final View a(@NonNull Context context2) {
                return new RenderView(context2.getApplicationContext(), new AdContainer.RenderingProperties(AdContainer.RenderingProperties.PlacementType.PLACEMENT_TYPE_INLINE), null, null);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            protected final void a(@NonNull View view, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
                super.a(view, akVar, cVar);
                NativeViewFactory.a((RenderView) view, akVar, cVar);
            }

            @Override // com.inmobi.ads.NativeViewFactory.c
            public final boolean a(@NonNull View view) {
                return (view instanceof RenderView) && !((RenderView) view).v && super.a(view);
            }
        });
    }

    public static NativeViewFactory a(Context context) {
        NativeViewFactory nativeViewFactory = null;
        NativeViewFactory nativeViewFactory2 = e == null ? null : e.get();
        if (nativeViewFactory2 == null) {
            synchronized (NativeViewFactory.class) {
                if (e != null) {
                    nativeViewFactory = e.get();
                }
                if (nativeViewFactory == null) {
                    nativeViewFactory2 = new NativeViewFactory(context);
                    e = new WeakReference<>(nativeViewFactory2);
                } else {
                    nativeViewFactory2 = nativeViewFactory;
                }
            }
        }
        return nativeViewFactory2;
    }

    public final void a(@NonNull View view) {
        if ((view instanceof aw) || (view instanceof NativeContainerLayout)) {
            NativeContainerLayout nativeContainerLayout = (NativeContainerLayout) view;
            if (nativeContainerLayout.getChildCount() != 0) {
                Stack stack = new Stack();
                stack.push(nativeContainerLayout);
                while (!stack.isEmpty()) {
                    NativeContainerLayout nativeContainerLayout2 = (NativeContainerLayout) stack.pop();
                    for (int childCount = nativeContainerLayout2.getChildCount() - 1; childCount >= 0; childCount--) {
                        View childAt = nativeContainerLayout2.getChildAt(childCount);
                        nativeContainerLayout2.removeViewAt(childCount);
                        if (childAt instanceof NativeContainerLayout) {
                            stack.push((NativeContainerLayout) childAt);
                        } else {
                            c(childAt);
                        }
                    }
                    c(nativeContainerLayout2);
                }
                return;
            }
        }
        c(view);
    }

    private void c(@NonNull View view) {
        c cVarB;
        int iIntValue = c.get(view.getClass()).intValue();
        if (-1 == iIntValue) {
            new StringBuilder("View type unknown, ignoring recycle:").append(view);
            return;
        }
        c cVar = this.d.get(Integer.valueOf(iIntValue));
        if (cVar == null) {
            StringBuilder sb = new StringBuilder("Unsupported AssetType:");
            sb.append(iIntValue);
            sb.append(" failed to recycle view");
        } else {
            if (this.b >= 300 && (cVarB = b()) != null && cVarB.b.size() > 0) {
                cVarB.b.removeFirst();
            }
            cVar.a(view);
        }
    }

    @Nullable
    private c b() {
        int size = 0;
        c cVar = null;
        for (Map.Entry<Integer, c> entry : this.d.entrySet()) {
            if (entry.getValue().b.size() > size) {
                c value = entry.getValue();
                cVar = value;
                size = value.b.size();
            }
        }
        return cVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, ImageView imageView) {
        Bitmap bitmapCreateBitmap;
        if (imageView.getDrawable() == null) {
            float f2 = com.inmobi.commons.core.utilities.b.c.a().c;
            CustomView customView = new CustomView(context, f2, 0);
            if (Build.VERSION.SDK_INT < 28) {
                customView.layout(0, 0, (int) (c(40) * f2), (int) (c(40) * f2));
                customView.setDrawingCacheEnabled(true);
                customView.buildDrawingCache();
                bitmapCreateBitmap = customView.getDrawingCache();
            } else {
                customView.layout(0, 0, (int) (c(40) * f2), (int) (c(40) * f2));
                bitmapCreateBitmap = Bitmap.createBitmap((int) (c(40) * f2), (int) (c(40) * f2), Bitmap.Config.ARGB_8888);
                customView.draw(new Canvas(bitmapCreateBitmap));
            }
            imageView.setImageBitmap(bitmapCreateBitmap);
        }
    }

    static class PicassoCallback implements Callback {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;
        private ak c;

        @Override // com.squareup.picasso.Callback
        public void onSuccess() {
        }

        PicassoCallback(Context context, ImageView imageView, ak akVar) {
            this.a = new WeakReference<>(context);
            this.b = new WeakReference<>(imageView);
            this.c = akVar;
        }

        @Override // com.squareup.picasso.Callback
        public void onError() {
            NativeViewFactory.a(this.a.get(), this.b.get(), this.c);
        }
    }

    private static final class a implements Runnable {
        private WeakReference<Context> a;
        private WeakReference<ImageView> b;

        a(Context context, ImageView imageView) {
            this.a = new WeakReference<>(context);
            this.b = new WeakReference<>(imageView);
        }

        @Override // java.lang.Runnable
        public final void run() {
            Context context = this.a.get();
            ImageView imageView = this.b.get();
            if (context == null || imageView == null) {
                return;
            }
            NativeViewFactory.b(context, imageView);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private static void a(@NonNull TextView textView, String[] strArr) {
        int paintFlags = textView.getPaintFlags();
        int i = 0;
        for (String str : strArr) {
            byte b2 = -1;
            switch (str.hashCode()) {
                case -1178781136:
                    if (str.equals(TtmlNode.ITALIC)) {
                        b2 = 1;
                    }
                    break;
                case -1026963764:
                    if (str.equals(TtmlNode.UNDERLINE)) {
                        b2 = 3;
                    }
                    break;
                case -891985998:
                    if (str.equals("strike")) {
                        b2 = 2;
                    }
                    break;
                case 3029637:
                    if (str.equals(TtmlNode.BOLD)) {
                        b2 = 0;
                    }
                    break;
            }
            if (b2 == 0) {
                i |= 1;
            } else if (b2 == 1) {
                i |= 2;
            } else if (b2 == 2) {
                paintFlags |= 16;
            } else if (b2 == 3) {
                paintFlags |= 8;
            }
        }
        textView.setTypeface(Typeface.DEFAULT, i);
        textView.setPaintFlags(paintFlags);
    }

    @TargetApi(21)
    static void a(View view, @NonNull al alVar) {
        int color = Color.parseColor("#00000000");
        try {
            color = Color.parseColor(alVar.e());
        } catch (IllegalArgumentException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        view.setBackgroundColor(color);
        if ("line".equals(alVar.a())) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(color);
            if ("curved".equals(alVar.b())) {
                gradientDrawable.setCornerRadius(alVar.c());
            }
            int color2 = Color.parseColor("#ff000000");
            try {
                color2 = Color.parseColor(alVar.d());
            } catch (IllegalArgumentException e3) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
            }
            gradientDrawable.setStroke(1, color2);
            if (Build.VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(gradientDrawable);
            } else {
                view.setBackground(gradientDrawable);
            }
        }
    }

    @SuppressLint({"AppCompatCustomView"})
    private static final class b extends TextView {
        @Override // android.widget.TextView, android.view.View
        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }

        public b(Context context) {
            super(context);
        }

        @Override // android.view.View
        protected final void onSizeChanged(int i, int i2, int i3, int i4) {
            super.onSizeChanged(i, i2, i3, i4);
            int lineHeight = getLineHeight() > 0 ? i2 / getLineHeight() : 0;
            if (lineHeight > 0) {
                setSingleLine(false);
                setLines(lineHeight);
            }
            if (lineHeight == 1) {
                setSingleLine();
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:40:0x008a  */
    @Nullable
    public final View a(@NonNull Context context, @NonNull ak akVar, @NonNull com.inmobi.ads.c cVar) {
        if (akVar instanceof am) {
            am amVar = (am) akVar;
            if ("root".equalsIgnoreCase(amVar.d)) {
            } else if ("card_scrollable".equalsIgnoreCase(amVar.d)) {
            }
        } else {
            switch (akVar.b) {
                case "TEXT":
                    break;
                case "IMAGE":
                case "ICON":
                    break;
                case "VIDEO":
                    break;
                case "CTA":
                    break;
                case "TIMER":
                    break;
                case "WEBVIEW":
                    break;
                case "GIF":
                    break;
                default:
                    break;
            }
        }
        if (-1 == r1) {
            StringBuilder sb = new StringBuilder("Unsupported AssetType:");
            sb.append(akVar.b);
            sb.append(" failed to instantiate view ");
            return null;
        }
        return this.d.get(Integer.valueOf(r1)).a(context, akVar, cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(17)
    public static Button b(@NonNull Button button, @NonNull ak akVar) {
        an.a aVar = (an.a) akVar.c;
        button.setLayoutParams(new ViewGroup.LayoutParams(c(aVar.a.x), c(aVar.a.y)));
        button.setText((CharSequence) akVar.e);
        button.setTextSize(1, c(aVar.h()));
        int color = Color.parseColor("#ff000000");
        try {
            color = Color.parseColor(aVar.i());
        } catch (IllegalArgumentException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        button.setTextColor(color);
        int color2 = Color.parseColor("#00000000");
        try {
            color2 = Color.parseColor(aVar.e());
        } catch (IllegalArgumentException e3) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
        }
        button.setBackgroundColor(color2);
        if (Build.VERSION.SDK_INT >= 17) {
            button.setTextAlignment(4);
        }
        button.setGravity(17);
        a(button, aVar.j());
        a(button, aVar);
        return button;
    }

    public static ViewGroup.LayoutParams a(@NonNull ak akVar, @NonNull ViewGroup viewGroup) {
        Point point = akVar.c.a;
        Point point2 = akVar.c.c;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(c(point.x), c(point.y));
        if (viewGroup instanceof NativeContainerLayout) {
            NativeContainerLayout.a aVar = new NativeContainerLayout.a(c(point.x), c(point.y));
            NativeContainerLayout.a aVar2 = aVar;
            int iC = c(point2.x);
            int iC2 = c(point2.y);
            aVar2.a = iC;
            aVar2.b = iC2;
            return aVar;
        }
        if (viewGroup instanceof LinearLayout) {
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(c(point.x), c(point.y));
            layoutParams2.setMargins(c(point2.x), c(point2.y), 0, 0);
            return layoutParams2;
        }
        if (viewGroup instanceof AbsListView) {
            return new AbsListView.LayoutParams(c(point.x), c(point.y));
        }
        if (!(viewGroup instanceof FrameLayout)) {
            return layoutParams;
        }
        FrameLayout.LayoutParams layoutParams3 = new FrameLayout.LayoutParams(c(point.x), c(point.y));
        layoutParams3.setMargins(c(point2.x), c(point2.y), 0, 0);
        return layoutParams3;
    }

    static /* synthetic */ void b(View view) {
        if (Build.VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(null);
        } else {
            view.setBackground(null);
        }
    }

    static /* synthetic */ void a(ImageView imageView, ak akVar) {
        int i;
        int i2;
        int i3;
        String str = (String) akVar.e;
        if (str != null) {
            int iC = c(akVar.c.a.x);
            int iC2 = c(akVar.c.a.y);
            String strF = akVar.c.f();
            byte b2 = -1;
            int iHashCode = strF.hashCode();
            int i4 = 0;
            if (iHashCode != -1362001767) {
                if (iHashCode == 727618043 && strF.equals("aspectFill")) {
                    b2 = 1;
                }
            } else if (strF.equals("aspectFit")) {
                b2 = 0;
            }
            if (b2 == 0) {
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            } else if (b2 == 1) {
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            Context context = f.get();
            if (context != null && iC > 0 && iC2 > 0 && str.trim().length() != 0) {
                bh.a(context).load(str).into(imageView, new PicassoCallback(context, imageView, akVar));
                if ("cross_button".equalsIgnoreCase(akVar.d) && akVar.r.length() == 0) {
                    new Handler().postDelayed(new a(context, imageView), 2000L);
                }
            }
            ak akVar2 = akVar.t;
            if (akVar2 == null || !"line".equals(akVar2.c.a())) {
                i = 0;
                i2 = 0;
                i3 = 0;
            } else {
                i = akVar2.c.c.x == akVar.c.c.x ? 1 : 0;
                i2 = c(akVar2.c.a.x) == c(akVar.c.a.x) + akVar.c.c.x ? 1 : 0;
                i3 = c(akVar2.c.c.y) == c(akVar.c.c.y) ? 1 : 0;
                i4 = c(akVar2.c.a.y) == c(akVar.c.a.y) + c(akVar.c.c.y) ? 1 : 0;
                if (c(akVar2.c.a.x) == c(akVar.c.a.x)) {
                    i = 1;
                    i2 = 1;
                }
            }
            if (Build.VERSION.SDK_INT < 17) {
                imageView.setPadding(i, i3, i2, i4);
            } else {
                imageView.setPaddingRelative(i, i3, i2, i4);
            }
            a(imageView, akVar.c);
        }
    }

    static /* synthetic */ void a(GifView gifView, ak akVar) {
        gifView.setLayoutParams(new ViewGroup.LayoutParams(c(akVar.c.a.x), c(akVar.c.a.y)));
        gifView.setGif(((aq) akVar).z);
        a(gifView, akVar.c);
    }

    static /* synthetic */ void a(NativeVideoWrapper nativeVideoWrapper, ak akVar) {
        if (Build.VERSION.SDK_INT >= 15) {
            a(nativeVideoWrapper, akVar.c);
            if (akVar.w != null) {
                nativeVideoWrapper.setPosterImage((Bitmap) akVar.w);
            }
            nativeVideoWrapper.getProgressBar().setVisibility(0);
        }
    }

    static /* synthetic */ void a(TextView textView, ak akVar) {
        az.a aVar = (az.a) akVar.c;
        textView.setLayoutParams(new ViewGroup.LayoutParams(c(aVar.a.x), c(aVar.a.y)));
        textView.setText((CharSequence) akVar.e);
        textView.setTypeface(Typeface.DEFAULT);
        int i = aVar.p;
        if (i == 1) {
            textView.setGravity(8388629);
        } else if (i != 2) {
            textView.setGravity(8388627);
        } else {
            textView.setGravity(17);
        }
        textView.setTextSize(1, c(aVar.h()));
        int color = Color.parseColor("#ff000000");
        try {
            color = Color.parseColor(aVar.i());
        } catch (IllegalArgumentException e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
        textView.setTextColor(color);
        int color2 = Color.parseColor("#00000000");
        try {
            color2 = Color.parseColor(aVar.e());
        } catch (IllegalArgumentException e3) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e3));
        }
        textView.setBackgroundColor(color2);
        if (Build.VERSION.SDK_INT >= 17) {
            textView.setTextAlignment(1);
        }
        a(textView, aVar.j());
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setHorizontallyScrolling(true);
        textView.setFocusable(true);
        textView.setFocusableInTouchMode(true);
        a(textView, aVar);
    }

    static /* synthetic */ void a(TextView textView) {
        textView.setTypeface(Typeface.DEFAULT, 0);
        textView.setPaintFlags(textView.getPaintFlags() & (-17));
        textView.setPaintFlags(textView.getPaintFlags() & (-9));
    }

    static /* synthetic */ void a(NativeViewFactory nativeViewFactory, final NativeTimerView nativeTimerView, ak akVar) {
        long jA;
        nativeTimerView.setVisibility(4);
        final bb bbVar = (bb) akVar;
        ba.a aVar = bbVar.A.a;
        ba.a aVar2 = bbVar.A.b;
        if (aVar != null) {
            try {
                jA = aVar.a();
            } catch (Exception e2) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
                return;
            }
        } else {
            jA = 0;
        }
        long jA2 = aVar2 != null ? aVar2.a() : 0L;
        if (jA2 >= 0) {
            nativeTimerView.setTimerValue(jA2);
            new Handler().postDelayed(new Runnable() { // from class: com.inmobi.ads.NativeViewFactory.4
                @Override // java.lang.Runnable
                public final void run() {
                    if (NativeViewFactory.f.get() != null) {
                        if (bbVar.z) {
                            nativeTimerView.setVisibility(0);
                        }
                        nativeTimerView.a();
                    }
                }
            }, jA * 1000);
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0042  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    static /* synthetic */ void a(RenderView renderView, ak akVar, com.inmobi.ads.c cVar) {
        byte b2;
        try {
            bf bfVar = (bf) akVar;
            renderView.a(RenderView.a, cVar);
            renderView.j = true;
            String str = (String) akVar.e;
            String str2 = bfVar.z;
            switch (str2.hashCode()) {
                case -1081286672:
                    if (!str2.equals("REF_IFRAME")) {
                        b2 = -1;
                    } else {
                        b2 = 2;
                    }
                    break;
                case 84303:
                    if (!str2.equals("URL")) {
                        b2 = -1;
                    } else {
                        b2 = 3;
                    }
                    break;
                case 2228139:
                    if (!str2.equals("HTML")) {
                        b2 = -1;
                    } else {
                        b2 = 1;
                    }
                    break;
                case 83774455:
                    if (!str2.equals("REF_HTML")) {
                        b2 = -1;
                    } else {
                        b2 = 0;
                    }
                    break;
                default:
                    b2 = -1;
                    break;
            }
            if (b2 == 0 || b2 == 1) {
                renderView.a(str);
            } else {
                renderView.b(str);
            }
        } catch (Exception e2) {
            com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
        }
    }

    static /* synthetic */ void a(Context context, ImageView imageView, ak akVar) {
        if (context != null && imageView != null) {
            String str = akVar.r;
            if ("cross_button".equalsIgnoreCase(akVar.d) && str.trim().length() == 0) {
                b(context, imageView);
            }
        }
        HashMap map = new HashMap();
        map.put("[ERRORCODE]", "603");
        akVar.a(NativeTracker.TrackerEventType.TRACKER_EVENT_TYPE_ERROR, map);
    }
}
