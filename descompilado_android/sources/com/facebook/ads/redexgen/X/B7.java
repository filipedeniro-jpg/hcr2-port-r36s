package com.facebook.ads.redexgen.X;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: assets/audience_network.dex */
public final class B7 extends AsyncTask<String, Void, Bitmap[]> {

    @Nullable
    private final WeakReference<BA> B;
    private final int C;
    private final int D;
    private final WeakReference<Context> E;
    private boolean F;
    private int G;

    @Nullable
    private final WeakReference<ImageView> H;
    private C1D I;

    @Nullable
    private final WeakReference<ViewGroup> J;
    private int K;

    public B7(ViewGroup viewGroup) {
        this(viewGroup, 12, 16);
    }

    public B7(ViewGroup viewGroup, int blurRadius, int blurSampling) {
        this.F = false;
        this.G = -1;
        this.K = -1;
        this.E = new WeakReference<>(viewGroup.getContext());
        this.B = null;
        this.H = null;
        this.J = new WeakReference<>(viewGroup);
        this.C = blurRadius;
        this.D = blurSampling;
    }

    public B7(ImageView imageView) {
        this.F = false;
        this.G = -1;
        this.K = -1;
        this.E = new WeakReference<>(imageView.getContext());
        this.B = null;
        this.H = new WeakReference<>(imageView);
        this.J = null;
        this.C = 0;
        this.D = 1;
    }

    public B7(ImageView imageView, int blurRadius, int blurSampling) {
        this.F = false;
        this.G = -1;
        this.K = -1;
        this.E = new WeakReference<>(imageView.getContext());
        this.B = null;
        this.H = new WeakReference<>(imageView);
        this.J = null;
        this.C = blurRadius;
        this.D = blurSampling;
    }

    public B7(BA blurBorderView) {
        this.F = false;
        this.G = -1;
        this.K = -1;
        this.E = new WeakReference<>(blurBorderView.getContext());
        this.B = new WeakReference<>(blurBorderView);
        this.H = null;
        this.J = null;
        this.C = 12;
        this.D = 16;
    }

    public final B7 A(boolean disableImageBlur) {
        this.F = disableImageBlur;
        return this;
    }

    public final B7 B() {
        this.G = -1;
        this.K = -1;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Bitmap[] doInBackground(String... urls) {
        String str = urls[0];
        Bitmap bitmapA = null;
        Bitmap bitmapB = null;
        Context context = this.E.get();
        if (context == null) {
            return new Bitmap[]{null, null};
        }
        try {
            bitmapA = H9.B(context).A(str, this.G, this.K);
            if (bitmapA != null && !this.F) {
                bitmapB = C0608Iv.B(context, bitmapA, this.C, this.D);
            }
        } catch (Throwable th) {
            C0585Hx.D(context, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.MB, new C0583Hv(th));
        }
        return new Bitmap[]{bitmapA, bitmapB};
    }

    public final B7 C(C1D listener) {
        this.I = listener;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final void onPostExecute(Bitmap[] result) {
        BA ba;
        if (this.H != null) {
            ImageView imageView = this.H.get();
            if (result[1] != null && !this.F && this.C != 0 && imageView != null) {
                imageView.setImageBitmap(result[1]);
                return;
            } else if (imageView != null) {
                imageView.setImageBitmap(result[0]);
            }
        }
        if (this.B != null && (ba = this.B.get()) != null) {
            ba.setImage(result[0], result[1]);
        }
        if (this.J != null && this.J.get() != null && result[1] != null) {
            J6.P(this.J.get(), new BitmapDrawable(this.E.get().getResources(), result[1]));
        }
        if (this.I == null) {
            return;
        }
        this.I.FE(result[0] != null);
    }

    public final B7 D(int height, int width) {
        this.G = height;
        this.K = width;
        return this;
    }

    public final void E(String url) {
        if (TextUtils.isEmpty(url)) {
            if (this.I != null) {
                this.I.FE(false);
                return;
            }
            return;
        }
        executeOnExecutor(LD.E, url);
    }
}
