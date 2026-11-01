package com.my.target.instreamads;

import android.net.Uri;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface InstreamAdPlayer {

    public interface AdPlayerListener {
        void onAdVideoCompleted();

        void onAdVideoError(@NonNull String str);

        void onAdVideoPaused();

        void onAdVideoResumed();

        void onAdVideoStarted();

        void onAdVideoStopped();

        void onVolumeChanged(float f);
    }

    void destroy();

    @Nullable
    AdPlayerListener getAdPlayerListener();

    float getAdVideoDuration();

    float getAdVideoPosition();

    @NonNull
    View getView();

    void pauseAdVideo();

    void playAdVideo(@NonNull Uri uri, int i, int i2);

    void playAdVideo(@NonNull Uri uri, int i, int i2, float f);

    void resumeAdVideo();

    void setAdPlayerListener(@Nullable AdPlayerListener adPlayerListener);

    void setVolume(float f);

    void stopAdVideo();
}
