package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"ViewConstructor"})
public final class MediaRenderView extends VideoView implements Application.ActivityLifecycleCallbacks, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {
    private static final String l = "MediaRenderView";
    public CustomMediaController a;
    public Bitmap b;
    public ViewGroup c;
    public a d;
    int e;
    boolean f;
    public String g;
    public String h;
    boolean i;
    int j;
    int k;
    private boolean m;
    private MediaPlayer n;
    private WeakReference<Activity> o;

    /* JADX INFO: Access modifiers changed from: package-private */
    public interface a {
        void a();

        void a(MediaRenderView mediaRenderView);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
    }

    public MediaRenderView(Activity activity) {
        super(activity);
        this.m = false;
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT < 28) {
            setDrawingCacheEnabled(true);
        }
        this.e = 100;
        this.j = -1;
        this.k = 0;
        this.f = false;
        this.o = new WeakReference<>(activity);
        activity.getApplication().registerActivityLifecycleCallbacks(this);
    }

    @Override // android.view.SurfaceView, android.view.View
    protected final void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        StringBuilder sb = new StringBuilder(">>> onWindowVisibilityChanged (");
        sb.append(i);
        sb.append(")");
    }

    @Override // android.widget.VideoView, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        getHolder().setSizeFromLayout();
    }

    @Override // android.view.View
    @TargetApi(16)
    protected final void onVisibilityChanged(@NonNull View view, int i) {
        super.onVisibilityChanged(view, i);
        StringBuilder sb = new StringBuilder(">>> onVisibilityChanged (");
        sb.append(i);
        sb.append(")");
        if (i == 0) {
            if (Build.VERSION.SDK_INT >= 16) {
                Context contextB = com.inmobi.commons.a.a.b();
                if (contextB != null) {
                    setBackground(new BitmapDrawable(contextB.getResources(), this.b));
                    return;
                }
                return;
            }
            setBackgroundDrawable(new BitmapDrawable(this.b));
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(">>> onError (");
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        sb.append(")");
        a();
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.n = mediaPlayer;
        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.inmobi.rendering.mraid.MediaRenderView.1
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                String unused = MediaRenderView.l;
                if (MediaRenderView.this.a == null) {
                    MediaRenderView mediaRenderView = MediaRenderView.this;
                    mediaRenderView.a = new CustomMediaController(mediaRenderView.getContext());
                    MediaRenderView.this.a.setAnchorView(MediaRenderView.this);
                    MediaRenderView mediaRenderView2 = MediaRenderView.this;
                    mediaRenderView2.setMediaController(mediaRenderView2.a);
                    MediaRenderView.this.requestLayout();
                    MediaRenderView.this.requestFocus();
                }
            }
        });
        int i = this.k;
        if (i < getDuration()) {
            this.k = i;
            seekTo(i);
        }
        this.i = true;
        this.d.a();
        start();
    }

    public final void setPlaybackData(String str) {
        this.h = a(str);
        this.g = "anonymous";
        if (this.b == null) {
            this.b = Bitmap.createBitmap(24, 24, Bitmap.Config.ARGB_8888);
            this.b = b(this.h);
        }
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public final void start() {
        if (this.m) {
            return;
        }
        super.start();
    }

    @Override // android.widget.VideoView, android.widget.MediaController.MediaPlayerControl
    public final void pause() {
        super.pause();
    }

    public final void a() {
        stopPlayback();
        ViewGroup viewGroup = this.c;
        if (viewGroup != null) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.c);
            }
            ViewGroup viewGroup3 = (ViewGroup) getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(this);
            }
            setBackgroundColor(0);
            this.c = null;
        }
        super.setMediaController(null);
        this.a = null;
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public final ViewGroup getViewContainer() {
        return this.c;
    }

    public final void setViewContainer(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public final void setListener(a aVar) {
        this.d = aVar;
    }

    public static String a(String str) {
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            if ((b & 128) > 0) {
                sb.append("%");
                char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                sb.append(new String(new char[]{cArr[(b >> 4) & 15], cArr[b & 15]}));
            } else {
                sb.append((char) b);
            }
        }
        try {
            return new String(sb.toString().getBytes(), "ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return "";
        }
    }

    public static Bitmap b(String str) {
        try {
            return (Bitmap) Class.forName("android.media.ThumbnailUtils").getDeclaredMethod("createVideoThumbnail", String.class, Integer.TYPE).invoke(null, str, 1);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static class CustomMediaController extends MediaController {
        public CustomMediaController(Context context) {
            super(context);
        }

        @Override // android.widget.MediaController
        public final void show(int i) {
            super.show(i);
            if (Build.VERSION.SDK_INT < 19) {
                try {
                    Field declaredField = MediaController.class.getDeclaredField("mAnchor");
                    declaredField.setAccessible(true);
                    View view = (View) declaredField.get(this);
                    Field declaredField2 = MediaController.class.getDeclaredField("mDecor");
                    declaredField2.setAccessible(true);
                    View view2 = (View) declaredField2.get(this);
                    Field declaredField3 = MediaController.class.getDeclaredField("mDecorLayoutParams");
                    declaredField3.setAccessible(true);
                    WindowManager.LayoutParams layoutParams = (WindowManager.LayoutParams) declaredField3.get(this);
                    Field declaredField4 = MediaController.class.getDeclaredField("mWindowManager");
                    declaredField4.setAccessible(true);
                    WindowManager windowManager = (WindowManager) declaredField4.get(this);
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    view2.measure(View.MeasureSpec.makeMeasureSpec(view.getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(view.getHeight(), Integer.MIN_VALUE));
                    view2.setPadding(0, 0, 0, 0);
                    layoutParams.verticalMargin = 0.0f;
                    layoutParams.horizontalMargin = 0.0f;
                    layoutParams.width = view.getWidth();
                    layoutParams.gravity = 8388659;
                    layoutParams.x = iArr[0];
                    layoutParams.y = (iArr[1] + view.getHeight()) - view2.getMeasuredHeight();
                    windowManager.updateViewLayout(view2, layoutParams);
                } catch (Exception unused) {
                }
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.o.get() == null || !this.o.get().equals(activity)) {
            return;
        }
        this.m = false;
        start();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        Activity activity2 = this.o.get();
        if (activity2 == null || !activity2.equals(activity)) {
            return;
        }
        this.m = true;
        if (getCurrentPosition() != 0) {
            this.k = getCurrentPosition();
        }
        pause();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        activity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }
}
