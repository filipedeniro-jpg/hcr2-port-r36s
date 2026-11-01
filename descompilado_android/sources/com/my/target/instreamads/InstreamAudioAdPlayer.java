package com.my.target.instreamads;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface InstreamAudioAdPlayer {

    public interface AdPlayerListener {
        void onAdAudioCompleted();

        void onAdAudioError(@NonNull String str);

        void onAdAudioPaused();

        void onAdAudioResumed();

        void onAdAudioStarted();

        void onAdAudioStopped();

        void onVolumeChanged(float f);
    }

    void destroy();

    float getAdAudioDuration();

    float getAdAudioPosition();

    @Nullable
    AdPlayerListener getAdPlayerListener();

    @NonNull
    Context getCurrentContext();

    void pauseAdAudio();

    void playAdAudio(@NonNull Uri uri);

    void resumeAdAudio();

    void setAdPlayerListener(@Nullable AdPlayerListener adPlayerListener);

    void setVolume(float f);

    void stopAdAudio();
}
