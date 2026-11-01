package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes2.dex */
public class NendAdFullBoardLoader {
    private NendAdNativeClient a;
    private Handler b;

    public interface Callback {
        void onFailure(FullBoardAdError fullBoardAdError);

        void onSuccess(NendAdFullBoard nendAdFullBoard);
    }

    public enum FullBoardAdError {
        FAILED_AD_REQUEST,
        FAILED_DOWNLOAD_IMAGE,
        INVALID_AD_SPACES
    }

    public NendAdFullBoardLoader(Context context, int i, String str) {
        this.a = new NendAdNativeClient(context, i, str);
        this.b = new Handler(context.getMainLooper());
    }

    /* JADX INFO: renamed from: net.nend.android.NendAdFullBoardLoader$1, reason: invalid class name */
    class AnonymousClass1 implements NendAdNativeClient.Callback {
        final /* synthetic */ Callback a;

        AnonymousClass1(Callback callback) {
            this.a = callback;
        }

        @Override // net.nend.android.NendAdNativeClient.Callback
        public void onSuccess(final NendAdNative nendAdNative) {
            if (TextUtils.isEmpty(nendAdNative.getLogoImageUrl())) {
                this.a.onFailure(FullBoardAdError.INVALID_AD_SPACES);
                return;
            }
            final a aVar = new a(null);
            final CountDownLatch countDownLatch = new CountDownLatch(2);
            ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor();
            executorServiceNewSingleThreadExecutor.execute(new Runnable() { // from class: net.nend.android.NendAdFullBoardLoader.1.1
                @Override // java.lang.Runnable
                public void run() {
                    nendAdNative.downloadAdImage(new b() { // from class: net.nend.android.NendAdFullBoardLoader.1.1.1
                        @Override // net.nend.android.NendAdFullBoardLoader.b
                        void a(Bitmap bitmap, Exception exc) {
                            aVar.a = bitmap;
                            countDownLatch.countDown();
                        }
                    });
                    nendAdNative.downloadLogoImage(new b() { // from class: net.nend.android.NendAdFullBoardLoader.1.1.2
                        @Override // net.nend.android.NendAdFullBoardLoader.b
                        void a(Bitmap bitmap, Exception exc) {
                            aVar.b = bitmap;
                            countDownLatch.countDown();
                        }
                    });
                    try {
                        countDownLatch.await();
                    } catch (InterruptedException unused) {
                    }
                    if (aVar.a()) {
                        NendAdFullBoardLoader.this.b.post(new Runnable() { // from class: net.nend.android.NendAdFullBoardLoader.1.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1.this.a.onSuccess(new NendAdFullBoard(nendAdNative, aVar.a, aVar.b));
                            }
                        });
                    } else {
                        NendAdFullBoardLoader.this.b.post(new Runnable() { // from class: net.nend.android.NendAdFullBoardLoader.1.1.4
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass1.this.a.onFailure(FullBoardAdError.FAILED_DOWNLOAD_IMAGE);
                            }
                        });
                    }
                }
            });
            executorServiceNewSingleThreadExecutor.shutdown();
        }

        @Override // net.nend.android.NendAdNativeClient.Callback
        public void onFailure(NendAdNativeClient.NendError nendError) {
            this.a.onFailure(FullBoardAdError.FAILED_AD_REQUEST);
        }
    }

    public void loadAd(Callback callback) {
        if (callback == null) {
            return;
        }
        this.a.loadAd(new AnonymousClass1(callback));
    }

    private static class a {
        Bitmap a;
        Bitmap b;

        private a() {
            this.a = null;
            this.b = null;
        }

        /* synthetic */ a(AnonymousClass1 anonymousClass1) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean a() {
            return (this.a == null || this.b == null) ? false : true;
        }
    }

    private static abstract class b implements NendAdNative.Callback {
        abstract void a(Bitmap bitmap, Exception exc);

        private b() {
        }

        /* synthetic */ b(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // net.nend.android.NendAdNative.Callback
        public void onSuccess(Bitmap bitmap) {
            a(bitmap, null);
        }

        @Override // net.nend.android.NendAdNative.Callback
        public void onFailure(Exception exc) {
            a(null, exc);
        }
    }
}
