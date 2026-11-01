package com.inmobi.ads;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.inmobi.rendering.CustomView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(15)
public class NativeVideoController extends FrameLayout {
    private static final String b = "NativeVideoController";
    boolean a;
    private bd c;
    private a d;
    private NativeVideoView e;
    private CustomView f;
    private CustomView g;
    private ProgressBar h;
    private RelativeLayout i;
    private boolean j;
    private float k;
    private final View.OnClickListener l;

    public NativeVideoController(Context context) {
        this(context, null);
    }

    public NativeVideoController(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NativeVideoController(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = false;
        this.l = new View.OnClickListener() { // from class: com.inmobi.ads.NativeVideoController.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NativeVideoController.c(NativeVideoController.this);
            }
        };
        this.i = new RelativeLayout(getContext());
        addView(this.i, new RelativeLayout.LayoutParams(-1, -1));
        this.i.setPadding(0, 0, 0, 0);
        if (this.i != null) {
            this.k = com.inmobi.commons.core.utilities.b.c.a().c;
            this.f = new CustomView(getContext(), this.k, 9);
            this.g = new CustomView(getContext(), this.k, 11);
            this.h = new ProgressBar(getContext(), null, R.attr.progressBarStyleHorizontal);
            this.h.setScaleY(0.8f);
            c();
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(12, -1);
            float f = com.inmobi.commons.core.utilities.b.c.a().c;
            layoutParams.setMargins(0, (int) ((-6.0f) * f), 0, (int) (f * (-8.0f)));
            LayerDrawable layerDrawable = (LayerDrawable) this.h.getProgressDrawable();
            if (layerDrawable != null) {
                layerDrawable.getDrawable(0).setColorFilter(-1, PorterDuff.Mode.SRC_IN);
                layerDrawable.getDrawable(2).setColorFilter(-327674, PorterDuff.Mode.SRC_IN);
            }
            this.i.addView(this.h, layoutParams);
        }
        this.d = new a(this);
    }

    public void setMediaPlayer(@NonNull NativeVideoView nativeVideoView) {
        this.e = nativeVideoView;
        be beVar = (be) this.e.getTag();
        if (beVar == null || !beVar.B || beVar.a()) {
            return;
        }
        this.j = true;
        this.i.removeView(this.g);
        this.i.removeView(this.f);
        d();
    }

    private void c() {
        float f = this.k;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (f * 30.0f), (int) (f * 30.0f));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        this.i.addView(this.f, layoutParams);
        this.f.setOnClickListener(this.l);
    }

    private void d() {
        float f = this.k;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (f * 30.0f), (int) (f * 30.0f));
        layoutParams.addRule(9, -1);
        layoutParams.addRule(12, -1);
        this.i.addView(this.g, layoutParams);
        this.g.setOnClickListener(this.l);
    }

    public final void a() {
        if (!this.a) {
            e();
            this.a = true;
            be beVar = (be) this.e.getTag();
            if (beVar != null) {
                this.f.setVisibility(beVar.B ? 0 : 4);
                this.h.setVisibility(beVar.D ? 0 : 4);
            }
            setVisibility(0);
        }
        this.d.sendEmptyMessage(2);
    }

    public final void b() {
        if (this.a) {
            try {
                this.d.removeMessages(2);
                setVisibility(8);
            } catch (IllegalArgumentException e) {
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
            }
            this.a = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(15)
    public int e() {
        NativeVideoView nativeVideoView = this.e;
        if (nativeVideoView == null) {
            return 0;
        }
        int currentPosition = nativeVideoView.getCurrentPosition();
        int duration = this.e.getDuration();
        ProgressBar progressBar = this.h;
        if (progressBar != null && duration != 0) {
            progressBar.setProgress((currentPosition * 100) / duration);
        }
        return currentPosition;
    }

    @Override // android.view.View
    @TargetApi(15)
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        NativeVideoView nativeVideoView;
        if (Build.VERSION.SDK_INT < 15 || (nativeVideoView = this.e) == null || !nativeVideoView.b()) {
            return false;
        }
        if (this.a) {
            b();
            return false;
        }
        a();
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(15)
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (Build.VERSION.SDK_INT >= 15) {
            if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
                if (z) {
                    if (this.e.isPlaying()) {
                        this.e.pause();
                    } else {
                        this.e.start();
                    }
                    a();
                }
                return true;
            }
            if (keyCode == 126) {
                if (z && !this.e.isPlaying()) {
                    this.e.start();
                    a();
                }
                return true;
            }
            if (keyCode == 86 || keyCode == 127) {
                if (z && this.e.isPlaying()) {
                    this.e.pause();
                    a();
                }
                return true;
            }
            if (keyCode == 25 || keyCode == 24 || keyCode == 164 || keyCode == 27) {
                return super.dispatchKeyEvent(keyEvent);
            }
            a();
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(NativeVideoController.class.getName());
    }

    @Override // android.view.View
    @TargetApi(15)
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (Build.VERSION.SDK_INT >= 15) {
            accessibilityNodeInfo.setClassName(NativeVideoController.class.getName());
        }
    }

    static final class a extends Handler {

        @NonNull
        private final WeakReference<NativeVideoController> a;

        a(@NonNull NativeVideoController nativeVideoController) {
            this.a = new WeakReference<>(nativeVideoController);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 2) {
                super.handleMessage(message);
                return;
            }
            NativeVideoController nativeVideoController = this.a.get();
            if (nativeVideoController != null) {
                nativeVideoController.e();
                if (nativeVideoController.a && nativeVideoController.e.isPlaying()) {
                    sendMessageDelayed(obtainMessage(2), 200L);
                }
            }
        }
    }

    public void setVideoAd(bd bdVar) {
        this.c = bdVar;
    }

    static /* synthetic */ void c(NativeVideoController nativeVideoController) {
        bd bdVar;
        bd bdVar2;
        NativeVideoView nativeVideoView = nativeVideoController.e;
        if (nativeVideoView != null) {
            be beVar = (be) nativeVideoView.getTag();
            if (nativeVideoController.j) {
                nativeVideoController.e.e();
                nativeVideoController.j = false;
                nativeVideoController.i.removeView(nativeVideoController.g);
                nativeVideoController.i.removeView(nativeVideoController.f);
                nativeVideoController.c();
                if (beVar == null || (bdVar2 = nativeVideoController.c) == null) {
                    return;
                }
                try {
                    bdVar2.d(beVar);
                    beVar.A = true;
                    return;
                } catch (Exception e) {
                    new StringBuilder("SDK encountered unexpected error in handling the onVideoUnMuted event; ").append(e.getMessage());
                    com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e));
                    return;
                }
            }
            nativeVideoController.e.d();
            nativeVideoController.j = true;
            nativeVideoController.i.removeView(nativeVideoController.f);
            nativeVideoController.i.removeView(nativeVideoController.g);
            nativeVideoController.d();
            if (beVar == null || (bdVar = nativeVideoController.c) == null) {
                return;
            }
            try {
                bdVar.c(beVar);
                beVar.A = false;
            } catch (Exception e2) {
                new StringBuilder("SDK encountered unexpected error in handling the onVideoMuted event; ").append(e2.getMessage());
                com.inmobi.commons.core.a.a.a().a(new com.inmobi.commons.core.e.a(e2));
            }
        }
    }
}
