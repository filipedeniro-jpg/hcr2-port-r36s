package com.inmobi.ads;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/* JADX INFO: compiled from: NativeDataSource.java */
/* JADX INFO: loaded from: classes2.dex */
final class ap extends PagerAdapter implements ax {
    private static final String a = "ap";
    private static Handler e = new Handler(Looper.getMainLooper());
    private boolean b;

    @NonNull
    private final ao c;
    private au d;

    @NonNull
    private SparseArray<Runnable> f = new SparseArray<>();

    ap(@NonNull ao aoVar, @NonNull au auVar) {
        this.c = aoVar;
        this.d = auVar;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getItemPosition(Object obj) {
        Object tag = obj == null ? null : ((View) obj).getTag();
        if (tag == null || !(tag instanceof Integer)) {
            return -2;
        }
        return ((Integer) tag).intValue();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final int getCount() {
        return this.c.b();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view.equals(obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    @TargetApi(21)
    public final Object instantiateItem(@NonNull final ViewGroup viewGroup, final int i) {
        final am amVarA = this.c.a(i);
        if (amVarA == null) {
            return null;
        }
        final ViewGroup viewGroupA = this.d.a(viewGroup, amVarA);
        int iAbs = Math.abs(this.d.b - i);
        Runnable runnable = new Runnable() { // from class: com.inmobi.ads.ap.2
            @Override // java.lang.Runnable
            public final void run() {
                if (ap.this.b) {
                    return;
                }
                ap.this.f.remove(i);
                ap.this.d.b(viewGroupA, amVarA);
            }
        };
        this.f.put(i, runnable);
        e.postDelayed(runnable, iAbs * 50);
        viewGroupA.setLayoutParams(NativeViewFactory.a(amVarA, viewGroup));
        viewGroupA.setTag(Integer.valueOf(i));
        viewGroup.addView(viewGroupA);
        return viewGroupA;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public final void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull final Object obj) {
        viewGroup.removeView((View) obj);
        Runnable runnable = this.f.get(i);
        if (runnable != null) {
            e.removeCallbacks(runnable);
            NativeViewFactory.class.getSimpleName();
        }
        e.post(new Runnable() { // from class: com.inmobi.ads.ap.1
            @Override // java.lang.Runnable
            public final void run() {
                au auVar = ap.this.d;
                auVar.d.a((View) obj);
            }
        });
    }

    @Override // com.inmobi.ads.ax
    public final void destroy() {
        this.b = true;
        int size = this.f.size();
        for (int i = 0; i < size; i++) {
            e.removeCallbacks(this.f.get(this.f.keyAt(i)));
        }
        this.f.clear();
    }
}
