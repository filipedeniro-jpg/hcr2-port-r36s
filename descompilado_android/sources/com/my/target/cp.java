package com.my.target;

import android.content.Context;
import android.net.Uri;
import android.view.TextureView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.my.target.common.models.VideoData;

/* JADX INFO: compiled from: ExoVideoPlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public class cp implements Player.EventListener, co {

    @NonNull
    private final ci kA;

    @NonNull
    private final SimpleExoPlayer kB;

    @NonNull
    private final a kC;

    @Nullable
    private co.a kD;
    private boolean kE;

    @Nullable
    private VideoData kF;

    @Nullable
    private MediaSource source;
    private boolean started;

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity(int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onSeekProcessed() {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onShuffleModeEnabledChanged(boolean z) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public static cp z(@NonNull Context context) {
        return new cp(context);
    }

    @VisibleForTesting
    cp(@NonNull SimpleExoPlayer simpleExoPlayer, @NonNull a aVar) {
        this.kA = ci.i(200);
        this.kB = simpleExoPlayer;
        this.kC = aVar;
        this.kB.addListener(this);
        aVar.a(this.kB);
    }

    private cp(@NonNull Context context) {
        this(ExoPlayerFactory.newSimpleInstance(context, new DefaultTrackSelector()), new a());
    }

    @Override // com.my.target.co
    @Nullable
    public VideoData bs() {
        return this.kF;
    }

    @Override // com.my.target.co
    public boolean isMuted() {
        return this.kB.getVolume() == 0.0f;
    }

    @Override // com.my.target.co
    public long getPosition() {
        return this.kB.getCurrentPosition();
    }

    @Override // com.my.target.co
    public void seekTo(long j) {
        this.kB.seekTo(j);
    }

    @Override // com.my.target.co
    public boolean isStarted() {
        return this.started;
    }

    @VisibleForTesting
    void l(boolean z) {
        this.started = z;
    }

    @VisibleForTesting
    void m(boolean z) {
        this.kE = z;
    }

    @Override // com.my.target.co
    public void a(@Nullable co.a aVar) {
        this.kD = aVar;
        this.kC.a(aVar);
    }

    @Override // com.my.target.co
    public void destroy() {
        this.kF = null;
        this.started = false;
        this.kE = false;
        this.kB.setVideoTextureView(null);
        this.kB.stop();
        this.kB.release();
        this.kB.removeListener(this);
        this.kA.e(this.kC);
    }

    @Override // com.my.target.co
    public boolean isPaused() {
        return this.started && this.kE;
    }

    @Override // com.my.target.co
    public void a(@NonNull VideoData videoData, @NonNull TextureView textureView) {
        g.a("Play video in ExoPlayer");
        this.kF = videoData;
        this.kE = false;
        co.a aVar = this.kD;
        if (aVar != null) {
            aVar.bA();
        }
        this.kB.setVideoTextureView(textureView);
        if (this.kF != videoData || !this.started) {
            this.source = cq.a(videoData, textureView.getContext());
            this.kB.prepare(this.source);
        }
        this.kB.setPlayWhenReady(true);
    }

    @Override // com.my.target.co
    public void a(@NonNull Uri uri, @NonNull TextureView textureView) {
        g.a("Play video in ExoPlayer");
        this.kE = false;
        co.a aVar = this.kD;
        if (aVar != null) {
            aVar.bA();
        }
        this.kB.setVideoTextureView(textureView);
        if (!this.started) {
            this.source = cq.a(uri, textureView.getContext());
            this.kB.prepare(this.source);
        }
        this.kB.setPlayWhenReady(true);
    }

    @Override // com.my.target.co
    public void stop() {
        this.kB.stop();
    }

    @Override // com.my.target.co
    public void bt() {
        this.kB.setVolume(0.2f);
    }

    @Override // com.my.target.co
    public void bu() {
        this.kB.setVolume(0.0f);
        co.a aVar = this.kD;
        if (aVar != null) {
            aVar.f(0.0f);
        }
    }

    @Override // com.my.target.co
    public void setVolume(float f) {
        this.kB.setVolume(f);
        co.a aVar = this.kD;
        if (aVar != null) {
            aVar.f(f);
        }
    }

    @Override // com.my.target.co
    public void bv() {
        this.kB.setVolume(1.0f);
        co.a aVar = this.kD;
        if (aVar != null) {
            aVar.f(1.0f);
        }
    }

    @Override // com.my.target.co
    public void resume() {
        if (this.started) {
            this.kB.setPlayWhenReady(true);
            return;
        }
        MediaSource mediaSource = this.source;
        if (mediaSource != null) {
            this.kB.prepare(mediaSource, true, true);
        }
    }

    @Override // com.my.target.co
    public void pause() {
        if (!this.started || this.kE) {
            return;
        }
        this.kB.setPlayWhenReady(false);
    }

    @Override // com.my.target.co
    public float getDuration() {
        return this.kB.getDuration() / 1000.0f;
    }

    @Override // com.my.target.co
    public boolean isPlaying() {
        return this.started && !this.kE;
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        co.a aVar;
        if (i == 1) {
            if (this.started) {
                this.started = false;
                co.a aVar2 = this.kD;
                if (aVar2 != null) {
                    aVar2.bw();
                }
            }
            this.kA.e(this.kC);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            this.kE = false;
            this.started = false;
            float duration = this.kB.getDuration() / 1000.0f;
            co.a aVar3 = this.kD;
            if (aVar3 != null) {
                aVar3.b(duration, duration);
                this.kD.onComplete();
            }
            this.kA.e(this.kC);
            return;
        }
        if (z) {
            co.a aVar4 = this.kD;
            if (aVar4 != null) {
                aVar4.bx();
            }
            if (!this.started) {
                this.started = true;
            } else if (this.kE) {
                this.kE = false;
                co.a aVar5 = this.kD;
                if (aVar5 != null) {
                    aVar5.bz();
                }
            }
            this.kA.d(this.kC);
            return;
        }
        if (!this.kE && (aVar = this.kD) != null) {
            this.kE = true;
            aVar.by();
        }
        this.kA.e(this.kC);
    }

    @Override // com.google.android.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        this.kE = false;
        this.started = false;
        if (this.kD != null) {
            String message = exoPlaybackException.getMessage();
            if (message == null) {
                message = "Unknown video error";
            }
            this.kD.W(message);
        }
        this.kB.release();
    }

    /* JADX INFO: compiled from: ExoVideoPlayer.java */
    public static class a implements Runnable {

        @Nullable
        private co.a kD;

        @Nullable
        private SimpleExoPlayer kG;

        @Override // java.lang.Runnable
        public void run() {
            SimpleExoPlayer simpleExoPlayer;
            if (this.kD == null || (simpleExoPlayer = this.kG) == null) {
                return;
            }
            this.kD.b(simpleExoPlayer.getCurrentPosition() / 1000.0f, this.kG.getDuration() / 1000.0f);
        }

        void a(@Nullable co.a aVar) {
            this.kD = aVar;
        }

        void a(@Nullable SimpleExoPlayer simpleExoPlayer) {
            this.kG = simpleExoPlayer;
        }
    }
}
