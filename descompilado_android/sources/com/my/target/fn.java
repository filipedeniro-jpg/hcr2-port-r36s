package com.my.target;

import android.content.Context;
import android.net.Uri;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.my.target.instreamads.InstreamAdPlayer;

/* JADX INFO: compiled from: InstreamAdVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fn extends FrameLayout implements co.a, InstreamAdPlayer {

    @NonNull
    private final TextureView F;

    @Nullable
    private InstreamAdPlayer.AdPlayerListener G;
    private int H;
    private int I;
    private boolean J;
    private boolean K;

    @Nullable
    private cp L;

    @Override // com.my.target.co.a
    public final void b(float f, float f2) {
    }

    @Override // com.my.target.co.a
    public final void bA() {
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    @NonNull
    public final View getView() {
        return this;
    }

    private fn(@NonNull Context context) {
        this(context, new TextureView(context));
    }

    @VisibleForTesting
    private fn(@NonNull Context context, @NonNull TextureView textureView) {
        super(context, null, 0);
        this.F = textureView;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 17;
        addView(textureView, layoutParams);
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    @Nullable
    public final InstreamAdPlayer.AdPlayerListener getAdPlayerListener() {
        return this.G;
    }

    public fn(@NonNull Context context, byte b) {
        this(context, (char) 0);
    }

    private fn(@NonNull Context context, char c) {
        this(context);
    }

    @VisibleForTesting
    final int getPlaceholderWidth() {
        return this.H;
    }

    @VisibleForTesting
    final int getPlaceholderHeight() {
        return this.I;
    }

    @VisibleForTesting
    final void setVideoPlayer(@Nullable cp cpVar) {
        this.L = cpVar;
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final float getAdVideoDuration() {
        cp cpVar = this.L;
        if (cpVar != null) {
            return cpVar.getDuration();
        }
        return 0.0f;
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final float getAdVideoPosition() {
        cp cpVar = this.L;
        if (cpVar != null) {
            return cpVar.getPosition() / 1000.0f;
        }
        return 0.0f;
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void setAdPlayerListener(@Nullable InstreamAdPlayer.AdPlayerListener adPlayerListener) {
        this.G = adPlayerListener;
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void playAdVideo(@NonNull Uri uri, int i, int i2) {
        this.H = i;
        this.I = i2;
        this.J = false;
        if (this.L == null) {
            this.L = cp.z(getContext());
            this.L.a(this);
        }
        this.L.a(uri, this.F);
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void playAdVideo(@NonNull Uri uri, int i, int i2, float f) {
        playAdVideo(uri, i, i2);
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.seekTo((long) (f * 1000.0f));
        }
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void pauseAdVideo() {
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.pause();
        }
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void resumeAdVideo() {
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.resume();
        }
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void stopAdVideo() {
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.stop();
        }
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void destroy() {
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.destroy();
        }
    }

    @Override // com.my.target.instreamads.InstreamAdPlayer
    public final void setVolume(float f) {
        cp cpVar = this.L;
        if (cpVar != null) {
            cpVar.setVolume(f);
        }
    }

    @Override // com.my.target.co.a
    public final void bw() {
        InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
        if (adPlayerListener != null) {
            adPlayerListener.onAdVideoStopped();
        }
    }

    @Override // com.my.target.co.a
    public final void f(float f) {
        InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
        if (adPlayerListener != null) {
            adPlayerListener.onVolumeChanged(f);
        }
    }

    @Override // com.my.target.co.a
    public final void bx() {
        InstreamAdPlayer.AdPlayerListener adPlayerListener;
        if (this.J || (adPlayerListener = this.G) == null) {
            return;
        }
        adPlayerListener.onAdVideoStarted();
        this.J = true;
    }

    @Override // com.my.target.co.a
    public final void bz() {
        if (this.K) {
            InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
            if (adPlayerListener != null) {
                adPlayerListener.onAdVideoResumed();
            }
            this.K = false;
        }
    }

    @Override // com.my.target.co.a
    public final void by() {
        this.K = true;
        InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
        if (adPlayerListener != null) {
            adPlayerListener.onAdVideoPaused();
        }
    }

    @Override // com.my.target.co.a
    public final void W(String str) {
        InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
        if (adPlayerListener != null) {
            adPlayerListener.onAdVideoError(str);
        }
    }

    @Override // com.my.target.co.a
    public final void onComplete() {
        InstreamAdPlayer.AdPlayerListener adPlayerListener = this.G;
        if (adPlayerListener != null) {
            adPlayerListener.onAdVideoCompleted();
        }
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0062 A[PHI: r9
  0x0062: PHI (r9v14 int) = (r9v6 int), (r9v6 int), (r9v19 int), (r9v19 int) binds: [B:42:0x0086, B:43:0x0088, B:28:0x0056, B:29:0x0058] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:38:0x007a A[PHI: r9
  0x007a: PHI (r9v15 int) = (r9v11 int), (r9v11 int), (r9v24 int), (r9v24 int) binds: [B:35:0x006f, B:36:0x0071, B:24:0x0045, B:25:0x0047] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.FrameLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        int iRound;
        int iRound2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode == 0) {
            mode = Integer.MIN_VALUE;
        }
        if (mode2 == 0) {
            mode2 = Integer.MIN_VALUE;
        }
        int i4 = this.I;
        if (i4 == 0 || (i3 = this.H) == 0) {
            super.onMeasure(i, i2);
            return;
        }
        float f = i3 / i4;
        float f2 = size2 != 0 ? size / size2 : 0.0f;
        if (mode != 1073741824 || mode2 != 1073741824) {
            if (mode == Integer.MIN_VALUE && mode2 == Integer.MIN_VALUE) {
                if (f < f2) {
                    iRound2 = Math.round(size2 * f);
                    if (size <= 0 || iRound2 <= size) {
                        size = iRound2;
                    } else {
                        size2 = Math.round(size / f);
                    }
                } else {
                    iRound = Math.round(size / f);
                    if (size2 <= 0 || iRound <= size2) {
                        size2 = iRound;
                    } else {
                        size = Math.round(size2 * f);
                    }
                }
            } else if (mode == Integer.MIN_VALUE && mode2 == 1073741824) {
                iRound2 = Math.round(size2 * f);
                if (size <= 0 || iRound2 <= size) {
                    size = iRound2;
                } else {
                    size2 = Math.round(size / f);
                }
            } else if (mode == 1073741824 && mode2 == Integer.MIN_VALUE) {
                iRound = Math.round(size / f);
                if (size2 <= 0 || iRound <= size2) {
                    size2 = iRound;
                } else {
                    size = Math.round(size2 * f);
                }
            } else {
                size = 0;
                size2 = 0;
            }
        }
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
    }
}
