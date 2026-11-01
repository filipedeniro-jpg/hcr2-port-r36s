package net.nend.android.internal.utilities.a;

import android.graphics.Bitmap;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.LruCache;

/* JADX INFO: compiled from: BitmapLruCacheUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    private static final int a = (int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
    private static final int b = a / 8;
    private static LruCache<String, Bitmap> c = new LruCache<String, Bitmap>(b) { // from class: net.nend.android.internal.utilities.a.a.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    public static void a(String str, Bitmap bitmap) {
        Bitmap bitmapA = a(str);
        if (bitmapA == null || bitmapA.isRecycled()) {
            c.put(str, bitmap);
        }
    }

    public static Bitmap a(String str) {
        Bitmap bitmap = c.get(str);
        if (bitmap == null || !bitmap.isRecycled()) {
            return bitmap;
        }
        c.remove(str);
        return null;
    }
}
