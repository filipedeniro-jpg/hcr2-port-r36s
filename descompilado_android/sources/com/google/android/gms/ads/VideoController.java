package com.google.android.gms.ads;

import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.ads.zzawo;
import com.google.android.gms.internal.ads.zzwk;
import com.google.android.gms.internal.ads.zzxz;
import javax.annotation.concurrent.GuardedBy;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
/* JADX INFO: loaded from: classes.dex */
public final class VideoController {

    @KeepForSdk
    public static final int PLAYBACK_STATE_ENDED = 3;

    @KeepForSdk
    public static final int PLAYBACK_STATE_PAUSED = 2;

    @KeepForSdk
    public static final int PLAYBACK_STATE_PLAYING = 1;

    @KeepForSdk
    public static final int PLAYBACK_STATE_READY = 5;

    @KeepForSdk
    public static final int PLAYBACK_STATE_UNKNOWN = 0;
    private final Object lock = new Object();

    @Nullable
    @GuardedBy("lock")
    private zzwk zzabn;

    @Nullable
    @GuardedBy("lock")
    private VideoLifecycleCallbacks zzabo;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-ads-lite@@18.1.1 */
    public static abstract class VideoLifecycleCallbacks {
        public void onVideoEnd() {
        }

        public void onVideoMute(boolean z) {
        }

        public void onVideoPause() {
        }

        public void onVideoPlay() {
        }

        public void onVideoStart() {
        }
    }

    public final void zza(zzwk zzwkVar) {
        synchronized (this.lock) {
            this.zzabn = zzwkVar;
            if (this.zzabo != null) {
                setVideoLifecycleCallbacks(this.zzabo);
            }
        }
    }

    public final zzwk zzdd() {
        zzwk zzwkVar;
        synchronized (this.lock) {
            zzwkVar = this.zzabn;
        }
        return zzwkVar;
    }

    public final void play() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return;
            }
            try {
                this.zzabn.play();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call play on video controller.", e);
            }
        }
    }

    public final void pause() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return;
            }
            try {
                this.zzabn.pause();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call pause on video controller.", e);
            }
        }
    }

    public final void mute(boolean z) {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return;
            }
            try {
                this.zzabn.mute(z);
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call mute on video controller.", e);
            }
        }
    }

    public final boolean isMuted() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return true;
            }
            try {
                return this.zzabn.isMuted();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call isMuted on video controller.", e);
                return true;
            }
        }
    }

    @KeepForSdk
    public final int getPlaybackState() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return 0;
            }
            try {
                return this.zzabn.getPlaybackState();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call getPlaybackState on video controller.", e);
                return 0;
            }
        }
    }

    public final boolean isCustomControlsEnabled() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return false;
            }
            try {
                return this.zzabn.isCustomControlsEnabled();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call isUsingCustomPlayerControls.", e);
                return false;
            }
        }
    }

    public final boolean isClickToExpandEnabled() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return false;
            }
            try {
                return this.zzabn.isClickToExpandEnabled();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call isClickToExpandEnabled.", e);
                return false;
            }
        }
    }

    public final void setVideoLifecycleCallbacks(VideoLifecycleCallbacks videoLifecycleCallbacks) {
        Preconditions.checkNotNull(videoLifecycleCallbacks, "VideoLifecycleCallbacks may not be null.");
        synchronized (this.lock) {
            this.zzabo = videoLifecycleCallbacks;
            if (this.zzabn == null) {
                return;
            }
            try {
                this.zzabn.zza(new zzxz(videoLifecycleCallbacks));
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call setVideoLifecycleCallbacks on video controller.", e);
            }
        }
    }

    @Nullable
    public final VideoLifecycleCallbacks getVideoLifecycleCallbacks() {
        VideoLifecycleCallbacks videoLifecycleCallbacks;
        synchronized (this.lock) {
            videoLifecycleCallbacks = this.zzabo;
        }
        return videoLifecycleCallbacks;
    }

    public final boolean hasVideoContent() {
        boolean z;
        synchronized (this.lock) {
            z = this.zzabn != null;
        }
        return z;
    }

    public final float getAspectRatio() {
        synchronized (this.lock) {
            if (this.zzabn == null) {
                return 0.0f;
            }
            try {
                return this.zzabn.getAspectRatio();
            } catch (RemoteException e) {
                zzawo.zzc("Unable to call getAspectRatio on video controller.", e);
                return 0.0f;
            }
        }
    }
}
