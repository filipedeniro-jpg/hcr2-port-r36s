package com.facebook.ads.redexgen.X;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.AsyncTask;
import com.facebook.share.internal.MessengerShareContentUtility;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.Gd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: assets/audience_network.dex */
public class AsyncTaskC0540Gd extends AsyncTask<C0541Ge, Void, Drawable> {
    private final InterfaceC0538Gb B;

    private AsyncTaskC0540Gd(InterfaceC0538Gb downloadNativeBannerImageListener) {
        this.B = downloadNativeBannerImageListener;
    }

    public /* synthetic */ AsyncTaskC0540Gd(InterfaceC0538Gb x0, C0539Gc x1) {
        this(x0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    @SuppressLint({"CatchGeneralException"})
    /* JADX INFO: renamed from: B, reason: merged with bridge method [inline-methods] */
    public final Drawable doInBackground(C0541Ge... params) {
        if (params == null || params.length < 1) {
            return null;
        }
        String str = params[0].C;
        String str2 = params[0].B;
        Context contextB = HM.B();
        if (contextB == null) {
            return null;
        }
        Drawable mediationDrawable = null;
        try {
            Bitmap bitmapA = H9.B(contextB).A(str, -1, -1);
            if (bitmapA != null) {
                mediationDrawable = new BitmapDrawable(contextB.getResources(), bitmapA);
            }
        } catch (Throwable th) {
            C0585Hx.E(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C0584Hw.MB, new C0583Hv(th));
        }
        if (mediationDrawable == null) {
            return null;
        }
        BitmapDrawable bitmapDrawableB = C8O.B(contextB, str2);
        if (bitmapDrawableB == null) {
            return mediationDrawable;
        }
        return new LayerDrawable(new Drawable[]{mediationDrawable, bitmapDrawableB});
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: C, reason: merged with bridge method [inline-methods] */
    public final void onPostExecute(Drawable drawable) {
        this.B.GE(drawable);
    }
}
