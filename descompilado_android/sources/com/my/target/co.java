package com.my.target;

import android.net.Uri;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: AdVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public interface co {

    /* JADX INFO: compiled from: AdVideoPlayer.java */
    public interface a {
        void W(String str);

        void b(float f, float f2);

        void bA();

        void bw();

        void bx();

        void by();

        void bz();

        void f(float f);

        void onComplete();
    }

    void a(@NonNull Uri uri, @NonNull TextureView textureView);

    void a(@Nullable a aVar);

    void a(@NonNull VideoData videoData, @NonNull TextureView textureView);

    @Nullable
    VideoData bs();

    void bt();

    void bu();

    void bv();

    void destroy();

    float getDuration();

    long getPosition();

    boolean isMuted();

    boolean isPaused();

    boolean isPlaying();

    boolean isStarted();

    void pause();

    void resume();

    void seekTo(long j);

    void setVolume(float f);

    void stop();
}
