package com.google.ads.mediation.inmobi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
class ImageDownloaderAsyncTask extends AsyncTask<Object, Void, HashMap<String, Drawable>> {
    private static final long DRAWABLE_FUTURE_TIMEOUT_SECONDS = 10;
    static final String KEY_ICON = "icon_key";
    static final String KEY_IMAGE = "image_key";
    private final DrawableDownloadListener mListener;
    private final InMobiMemoryCache mMemoryCache = new InMobiMemoryCache();

    interface DrawableDownloadListener {
        void onDownloadFailure();

        void onDownloadSuccess(HashMap<String, Drawable> map);
    }

    public ImageDownloaderAsyncTask(DrawableDownloadListener drawableDownloadListener) {
        this.mListener = drawableDownloadListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public HashMap<String, Drawable> doInBackground(Object... objArr) {
        Drawable drawable;
        HashMap map = (HashMap) objArr[0];
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        try {
            if (this.mMemoryCache.get(String.valueOf(map.get(KEY_ICON))) != null) {
                drawable = this.mMemoryCache.get(String.valueOf(map.get(KEY_ICON)));
            } else {
                Drawable drawable2 = getDrawableFuture((URL) map.get(KEY_ICON), executorServiceNewCachedThreadPool).get(DRAWABLE_FUTURE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                this.mMemoryCache.put(String.valueOf(map.get(KEY_ICON)), drawable2);
                drawable = drawable2;
            }
            HashMap<String, Drawable> map2 = new HashMap<>();
            map2.put(KEY_ICON, drawable);
            return map2;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Future<Drawable> getDrawableFuture(final URL url, ExecutorService executorService) {
        return executorService.submit(new Callable<Drawable>() { // from class: com.google.ads.mediation.inmobi.ImageDownloaderAsyncTask.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Drawable call() throws Exception {
                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(url.openStream());
                bitmapDecodeStream.setDensity(160);
                return new BitmapDrawable(Resources.getSystem(), bitmapDecodeStream);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(HashMap<String, Drawable> map) {
        super.onPostExecute(map);
        if (map != null) {
            this.mListener.onDownloadSuccess(map);
        } else {
            this.mListener.onDownloadFailure();
        }
    }
}
