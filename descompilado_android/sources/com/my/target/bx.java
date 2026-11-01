package com.my.target;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

/* JADX INFO: compiled from: MraidWebView.java */
/* JADX INFO: loaded from: classes2.dex */
public class bx extends WebView {

    @Nullable
    private a iQ;
    private boolean iR;
    private boolean iS;
    private int orientation;

    /* JADX INFO: compiled from: MraidWebView.java */
    public interface a {
        void onVisibilityChanged(boolean z);

        void p();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public bx(@NonNull Context context) {
        super(context);
        this.iR = getVisibility() == 0;
        getSettings().setJavaScriptEnabled(true);
        getSettings().setDomStorageEnabled(true);
        getSettings().setAppCacheEnabled(true);
        getSettings().setAppCachePath(getContext().getCacheDir().getAbsolutePath());
        getSettings().setAllowFileAccess(false);
        getSettings().setAllowContentAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            getSettings().setAllowFileAccessFromFileURLs(false);
            getSettings().setAllowUniversalAccessFromFileURLs(false);
        }
        final b bVar = new b(getContext(), this);
        bVar.a(new b.a() { // from class: com.my.target.bx.1
            @Override // com.my.target.bx.b.a
            public void aX() {
                bx.this.iS = true;
            }
        });
        setOnTouchListener(new View.OnTouchListener() { // from class: com.my.target.bx.2
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                bVar.a(motionEvent);
                return false;
            }
        });
    }

    public boolean ba() {
        return this.iS;
    }

    public void j(boolean z) {
        g.a("MraidWebView: pause, finishing " + z);
        if (z) {
            stopLoading();
            loadUrl("");
        }
        onPause();
    }

    public void setVisibilityChangedListener(@Nullable a aVar) {
        this.iQ = aVar;
    }

    public boolean isVisible() {
        return this.iR;
    }

    @VisibleForTesting
    public void setClicked(boolean z) {
        this.iS = z;
    }

    @Override // android.webkit.WebView, android.widget.AbsoluteLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        d(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        super.onMeasure(i, i2);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        boolean z = i == 0;
        if (z != this.iR) {
            this.iR = z;
            a aVar = this.iQ;
            if (aVar != null) {
                aVar.onVisibilityChanged(this.iR);
            }
        }
    }

    private void d(int i, int i2) {
        int i3 = ((float) i) / ((float) i2) > 1.0f ? 2 : 1;
        if (i3 != this.orientation) {
            this.orientation = i3;
            a aVar = this.iQ;
            if (aVar != null) {
                aVar.p();
            }
        }
    }

    /* JADX INFO: compiled from: MraidWebView.java */
    private static class b extends GestureDetector {

        @Nullable
        private a iV;

        @NonNull
        private final View mView;

        /* JADX INFO: compiled from: MraidWebView.java */
        public interface a {
            void aX();
        }

        public b(@NonNull Context context, @NonNull View view) {
            this(context, view, new GestureDetector.SimpleOnGestureListener());
        }

        private b(@NonNull Context context, @NonNull View view, @NonNull GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
            super(context, simpleOnGestureListener);
            this.mView = view;
            setIsLongpressEnabled(false);
        }

        public void a(@NonNull MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                onTouchEvent(motionEvent);
                return;
            }
            if (action == 1) {
                if (this.iV != null) {
                    g.a("Gestures: user clicked");
                    this.iV.aX();
                    return;
                } else {
                    g.a("View's onUserClick() is not registered.");
                    return;
                }
            }
            if (action == 2 && a(motionEvent, this.mView)) {
                onTouchEvent(motionEvent);
            }
        }

        public void a(@Nullable a aVar) {
            this.iV = aVar;
        }

        private boolean a(@Nullable MotionEvent motionEvent, @Nullable View view) {
            if (motionEvent == null || view == null) {
                return false;
            }
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            return x >= 0.0f && x <= ((float) view.getWidth()) && y >= 0.0f && y <= ((float) view.getHeight());
        }
    }
}
