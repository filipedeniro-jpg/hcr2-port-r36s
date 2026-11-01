package com.my.target.common.models;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.LruCache;
import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.ah;
import com.my.target.g;

/* JADX INFO: loaded from: classes2.dex */
public final class ImageData extends ah<Bitmap> {
    private static final int MIN_CACHE_SIZE = 5242880;
    private volatile boolean useCache;
    private static final int DEFAULT_CACHE_SIZE = 31457280;

    @NonNull
    private static volatile LruCache<ImageData, Bitmap> memcache = new BitmapCache(DEFAULT_CACHE_SIZE);

    @AnyThread
    public static void setCacheSize(int i) {
        if (i < MIN_CACHE_SIZE) {
            g.a("setting cache size ignored: size should be >=5242880");
        } else if (Build.VERSION.SDK_INT >= 21) {
            memcache.resize(i);
        } else {
            memcache = new BitmapCache(i);
        }
    }

    @NonNull
    public static ImageData newImageData(@NonNull String str) {
        return new ImageData(str);
    }

    @NonNull
    public static ImageData newImageData(@NonNull String str, int i, int i2) {
        return new ImageData(str, i, i2);
    }

    public boolean isUseCache() {
        return this.useCache;
    }

    @Nullable
    public Bitmap getBitmap() {
        return getData();
    }

    public void setBitmap(@Nullable Bitmap bitmap) {
        setData(bitmap);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.my.target.ah
    @Nullable
    public Bitmap getData() {
        if (this.useCache) {
            return memcache.get(this);
        }
        return (Bitmap) super.getData();
    }

    @Override // com.my.target.ah
    public void setData(@Nullable Bitmap bitmap) {
        if (!this.useCache) {
            super.setData(bitmap);
        } else if (bitmap == null) {
            memcache.remove(this);
        } else {
            memcache.put(this, bitmap);
        }
    }

    private ImageData(@NonNull String str) {
        super(str);
    }

    private ImageData(@NonNull String str, int i, int i2) {
        super(str);
        this.width = i;
        this.height = i2;
    }

    public void useCache(boolean z) {
        if (z == this.useCache) {
            return;
        }
        this.useCache = z;
        if (z) {
            Bitmap bitmap = (Bitmap) super.getData();
            if (bitmap != null) {
                super.setData((Object) null);
                memcache.put(this, bitmap);
                return;
            }
            return;
        }
        super.setData(memcache.remove(this));
    }

    @NonNull
    public String toString() {
        return "ImageData{url='" + this.url + "', width=" + this.width + ", height=" + this.height + ", bitmap=" + getData() + '}';
    }

    private static class BitmapCache extends LruCache<ImageData, Bitmap> {
        public BitmapCache(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(ImageData imageData, Bitmap bitmap) {
            if (Build.VERSION.SDK_INT >= 19) {
                return bitmap.getAllocationByteCount();
            }
            return bitmap.getByteCount();
        }
    }
}
