package net.nend.android.internal.ui.a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.WeakHashMap;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeViewHolder;
import net.nend.android.internal.utilities.g;
import net.nend.android.internal.utilities.j;
import net.nend.android.internal.utilities.k;

/* JADX INFO: compiled from: NendAdNativeRenderer.java */
/* JADX INFO: loaded from: classes2.dex */
public class f {
    private static final Object[] a = new Object[0];
    private static WeakHashMap<View, ViewTreeObserver.OnPreDrawListener> b = new WeakHashMap<>();
    private NendAdNative c;
    private View f;
    private View.OnClickListener d = new View.OnClickListener() { // from class: net.nend.android.internal.ui.a.f.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            net.nend.android.internal.utilities.d.a(view.getContext(), f.this.c.getClickUrl());
            f.this.c.onClick();
        }
    };
    private View.OnClickListener e = new View.OnClickListener() { // from class: net.nend.android.internal.ui.a.f.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            f.this.c.onClickInformation(view.getContext());
        }
    };
    private ViewTreeObserver.OnPreDrawListener g = new ViewTreeObserver.OnPreDrawListener() { // from class: net.nend.android.internal.ui.a.f.3
        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (!f.a(f.this.f.getRootView(), f.this.f, 50)) {
                return true;
            }
            f.this.c.onImpression();
            if (!f.this.f.getViewTreeObserver().isAlive()) {
                return true;
            }
            f.this.f.getViewTreeObserver().removeOnPreDrawListener(this);
            f.b.remove(f.this.f);
            return true;
        }
    };

    private g.f<Bitmap> a(final String str) {
        return new g.f<>(new g.b<Bitmap>() { // from class: net.nend.android.internal.ui.a.f.4
            @Override // net.nend.android.internal.utilities.g.b
            public String getRequestUrl() {
                return str;
            }

            @Override // net.nend.android.internal.utilities.g.b
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Bitmap makeResponse(byte[] bArr) {
                Bitmap bitmapDecodeByteArray;
                if (bArr == null) {
                    return null;
                }
                try {
                    synchronized (f.a) {
                        try {
                            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return bitmapDecodeByteArray;
                } catch (IllegalStateException e) {
                    j.a(k.ERR_HTTP_REQUEST, e);
                    return null;
                } catch (OutOfMemoryError e2) {
                    System.gc();
                    j.a(k.ERR_HTTP_REQUEST, e2);
                    return null;
                }
            }
        });
    }

    public void a(NendAdNativeViewHolder nendAdNativeViewHolder, NendAdNative nendAdNative) {
        if (nendAdNative == null) {
            return;
        }
        a(nendAdNativeViewHolder.titleTextView, nendAdNative.getTitleText());
        a(nendAdNativeViewHolder.contentTextView, nendAdNative.getContentText());
        a(nendAdNativeViewHolder.promotionUrlTextView, nendAdNative.getPromotionUrl());
        a(nendAdNativeViewHolder.promotionNameTextView, nendAdNative.getPromotionName());
        a(nendAdNativeViewHolder.prTextView, nendAdNativeViewHolder.prText);
        a(nendAdNativeViewHolder.actionTextView, nendAdNative.getActionText());
        a(nendAdNativeViewHolder.adImageView, nendAdNative.getAdImageUrl(), nendAdNative);
        a(nendAdNativeViewHolder.logoImageView, nendAdNative.getLogoImageUrl(), nendAdNative);
        a(nendAdNativeViewHolder.itemView, nendAdNativeViewHolder.prTextView, nendAdNative);
    }

    private void a(TextView textView, String str) {
        if (textView == null || textView.getVisibility() != 0 || TextUtils.isEmpty(str)) {
            return;
        }
        textView.setText(str);
    }

    private void a(final ImageView imageView, String str, NendAdNative nendAdNative) {
        if (imageView == null || imageView.getVisibility() != 0 || TextUtils.isEmpty(str)) {
            return;
        }
        a(str, nendAdNative, new NendAdNative.Callback() { // from class: net.nend.android.internal.ui.a.f.5
            @Override // net.nend.android.NendAdNative.Callback
            public void onSuccess(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }

            @Override // net.nend.android.NendAdNative.Callback
            public void onFailure(Exception exc) {
                imageView.setImageDrawable(null);
            }
        });
    }

    public void a(final String str, final NendAdNative nendAdNative, final NendAdNative.Callback callback) {
        Bitmap cache = nendAdNative.getCache(str);
        if (cache != null && !cache.isRecycled()) {
            callback.onSuccess(cache);
        } else {
            g.a().a(a(str), new g.a<Bitmap>() { // from class: net.nend.android.internal.ui.a.f.6
                @Override // net.nend.android.internal.utilities.g.a
                public void a(Bitmap bitmap, Exception exc) {
                    if (bitmap != null) {
                        callback.onSuccess(bitmap);
                        nendAdNative.setCache(str, bitmap);
                    } else {
                        callback.onFailure(new net.nend.android.internal.a.b(k.ERR_UNEXPECTED));
                    }
                }
            });
        }
    }

    public void a(View view, TextView textView, NendAdNative nendAdNative) {
        if (view == null || textView == null || nendAdNative == null) {
            j.c("Parameter is invalid for activateAd.");
            return;
        }
        ViewTreeObserver.OnPreDrawListener onPreDrawListener = b.get(view);
        if (onPreDrawListener != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            }
            b.remove(view);
        }
        this.c = nendAdNative;
        this.f = view;
        view.setOnClickListener(this.d);
        textView.setOnClickListener(this.e);
        if (this.c.isSentImpression() || !view.getViewTreeObserver().isAlive()) {
            return;
        }
        view.getViewTreeObserver().addOnPreDrawListener(this.g);
        b.put(view, this.g);
    }

    public static boolean a(View view, View view2, int i) {
        Rect rect = new Rect();
        if (view2 == null || view2.getVisibility() != 0) {
            return false;
        }
        if ((view != null && view.getParent() == null) || !view2.getGlobalVisibleRect(rect)) {
            return false;
        }
        long jHeight = ((long) rect.height()) * ((long) rect.width());
        long height = ((long) view2.getHeight()) * ((long) view2.getWidth());
        return height > 0 && jHeight * 100 >= ((long) i) * height;
    }
}
