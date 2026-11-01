package com.facebook.ads.redexgen.X;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

/* JADX INFO: renamed from: com.facebook.ads.redexgen.X.5M, reason: invalid class name */
/* JADX INFO: loaded from: assets/audience_network.dex */
public interface C5M {
    void NC();

    boolean OC();

    void destroy();

    int getCurrentPosition();

    int getDuration();

    long getInitialBufferTime();

    C7A getStartReason();

    C5K getState();

    int getVideoHeight();

    int getVideoWidth();

    View getView();

    float getVolume();

    void pause(boolean z);

    void sF();

    void seekTo(int i);

    void setBackgroundPlaybackEnabled(boolean z);

    void setControlsAnchorView(View view);

    void setFullScreen(boolean z);

    void setRequestedVolume(float f);

    void setVideoMPD(@Nullable String str);

    void setVideoStateChangeListener(InterfaceC0450Cr interfaceC0450Cr);

    void setup(Uri uri);

    void stop();

    void tF(C7A c7a);
}
