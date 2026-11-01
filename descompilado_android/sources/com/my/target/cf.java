package com.my.target;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import com.my.target.common.models.ImageData;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: ImageLoader.java */
/* JADX INFO: loaded from: classes2.dex */
public class cf {

    @NonNull
    private static final WeakHashMap<ImageView, ImageData> jU = new WeakHashMap<>();
    private boolean dW;

    @NonNull
    private final List<ImageData> jV;

    @Nullable
    private a jW;

    /* JADX INFO: compiled from: ImageLoader.java */
    public interface a {
        void bk();
    }

    @NonNull
    public static cf a(@NonNull ImageData imageData) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(imageData);
        return new cf(arrayList);
    }

    @NonNull
    public static cf b(@NonNull List<ImageData> list) {
        return new cf(list);
    }

    @UiThread
    public static void a(@NonNull final ImageData imageData, @NonNull ImageView imageView) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            g.b("[ImageLoader] method loadAndDisplay called from worker thread");
            return;
        }
        if (jU.get(imageView) == imageData) {
            return;
        }
        jU.remove(imageView);
        if (imageData.getBitmap() != null) {
            a(imageData.getBitmap(), imageView);
            return;
        }
        jU.put(imageView, imageData);
        final WeakReference weakReference = new WeakReference(imageView);
        a(imageData).a(new a() { // from class: com.my.target.cf.1
            @Override // com.my.target.cf.a
            public void bk() {
                ImageView imageView2 = (ImageView) weakReference.get();
                if (imageView2 != null) {
                    if (imageData == ((ImageData) cf.jU.get(imageView2))) {
                        cf.jU.remove(imageView2);
                        Bitmap bitmap = imageData.getBitmap();
                        if (bitmap != null) {
                            cf.a(bitmap, imageView2);
                        }
                    }
                }
            }
        }).u(imageView.getContext());
    }

    @UiThread
    public static void b(@NonNull ImageData imageData, @NonNull ImageView imageView) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            g.b("[ImageLoader] method cancel called from worker thread");
        } else if (jU.get(imageView) == imageData) {
            jU.remove(imageView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(@NonNull Bitmap bitmap, @NonNull ImageView imageView) {
        if (imageView instanceof by) {
            ((by) imageView).b(bitmap, true);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    @NonNull
    public cf a(@Nullable a aVar) {
        this.jW = aVar;
        return this;
    }

    @NonNull
    public cf k(boolean z) {
        this.dW = z;
        return this;
    }

    private cf(@NonNull List<ImageData> list) {
        this.jV = list;
    }

    public void u(@NonNull Context context) {
        if (this.jV.isEmpty()) {
            finish();
        } else {
            final Context applicationContext = context.getApplicationContext();
            h.a(new Runnable() { // from class: com.my.target.cf.2
                @Override // java.lang.Runnable
                public void run() {
                    cf.this.v(applicationContext);
                    cf.this.finish();
                }
            });
        }
    }

    @WorkerThread
    public void v(@NonNull Context context) {
        Bitmap bitmapF;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            g.b("[ImageLoader] method loadSync called from main thread");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        au auVarAj = this.dW ? au.aj() : au.ai();
        for (ImageData imageData : this.jV) {
            if (imageData.getBitmap() == null && (bitmapF = auVarAj.f(imageData.getUrl(), applicationContext)) != null) {
                imageData.setData(bitmapF);
                if (imageData.getHeight() == 0 || imageData.getWidth() == 0) {
                    imageData.setHeight(bitmapF.getHeight());
                    imageData.setWidth(bitmapF.getWidth());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        if (this.jW == null) {
            return;
        }
        h.c(new Runnable() { // from class: com.my.target.cf.3
            @Override // java.lang.Runnable
            public void run() {
                if (cf.this.jW != null) {
                    cf.this.jW.bk();
                    cf.this.jW = null;
                }
            }
        });
    }
}
