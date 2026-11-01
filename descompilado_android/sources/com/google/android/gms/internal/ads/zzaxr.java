package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import com.fingersoft.billing.util.IabHelper;
import com.tonyodev.fetch.FetchConst;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(14)
public final class zzaxr extends zzaya implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnVideoSizeChangedListener, TextureView.SurfaceTextureListener {
    private static final Map<Integer, String> zzdwp = new HashMap();
    private final zzays zzdwq;
    private final boolean zzdwr;
    private int zzdws;
    private int zzdwt;
    private MediaPlayer zzdwu;
    private Uri zzdwv;
    private int zzdww;
    private int zzdwx;
    private int zzdwy;
    private int zzdwz;
    private int zzdxa;
    private zzayr zzdxb;
    private boolean zzdxc;
    private int zzdxd;
    private zzayb zzdxe;

    public zzaxr(Context context, boolean z, boolean z2, zzayq zzayqVar, zzays zzaysVar) {
        super(context);
        this.zzdws = 0;
        this.zzdwt = 0;
        setSurfaceTextureListener(this);
        this.zzdwq = zzaysVar;
        this.zzdxc = z;
        this.zzdwr = z2;
        this.zzdwq.zzb(this);
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final String zzwm() {
        String str = this.zzdxc ? " spherical" : "";
        return str.length() != 0 ? "MediaPlayer".concat(str) : new String("MediaPlayer");
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza(zzayb zzaybVar) {
        this.zzdxe = zzaybVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void setVideoPath(String str) {
        Uri uri = Uri.parse(str);
        zzrl zzrlVarZze = zzrl.zze(uri);
        if (zzrlVarZze == null || zzrlVarZze.url != null) {
            if (zzrlVarZze != null) {
                uri = Uri.parse(zzrlVarZze.url);
            }
            this.zzdwv = uri;
            this.zzdxd = 0;
            zzwn();
            requestLayout();
            invalidate();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void stop() {
        zzatm.zzdy("AdMediaPlayerView stop");
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.zzdwu.release();
            this.zzdwu = null;
            zzcr(0);
            this.zzdwt = 0;
        }
        this.zzdwq.onStop();
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdMediaPlayerView size changed: ");
        sb.append(i);
        sb.append(" x ");
        sb.append(i2);
        zzatm.zzdy(sb.toString());
        this.zzdww = mediaPlayer.getVideoWidth();
        this.zzdwx = mediaPlayer.getVideoHeight();
        if (this.zzdww == 0 || this.zzdwx == 0) {
            return;
        }
        requestLayout();
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public final void onPrepared(MediaPlayer mediaPlayer) {
        zzatm.zzdy("AdMediaPlayerView prepared");
        zzcr(2);
        this.zzdwq.zzei();
        zzatv.zzdsk.post(new zzaxt(this));
        this.zzdww = mediaPlayer.getVideoWidth();
        this.zzdwx = mediaPlayer.getVideoHeight();
        int i = this.zzdxd;
        if (i != 0) {
            seekTo(i);
        }
        zzwo();
        int i2 = this.zzdww;
        int i3 = this.zzdwx;
        StringBuilder sb = new StringBuilder(62);
        sb.append("AdMediaPlayerView stream dimensions: ");
        sb.append(i2);
        sb.append(" x ");
        sb.append(i3);
        zzatm.zzet(sb.toString());
        if (this.zzdwt == 3) {
            play();
        }
        zzwq();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public final void onCompletion(MediaPlayer mediaPlayer) {
        zzatm.zzdy("AdMediaPlayerView completion");
        zzcr(5);
        this.zzdwt = 5;
        zzatv.zzdsk.post(new zzaxs(this));
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public final boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzdwp.get(Integer.valueOf(i));
        String str2 = zzdwp.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 37 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer info: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzatm.zzdy(sb.toString());
        return true;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public final boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        String str = zzdwp.get(Integer.valueOf(i));
        String str2 = zzdwp.get(Integer.valueOf(i2));
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 38 + String.valueOf(str2).length());
        sb.append("AdMediaPlayerView MediaPlayer error: ");
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        zzatm.zzeu(sb.toString());
        zzcr(-1);
        this.zzdwt = -1;
        zzatv.zzdsk.post(new zzaxv(this, str, str2));
        return true;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        this.zzdwy = i;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        zzatm.zzdy("AdMediaPlayerView surface created");
        zzwn();
        zzatv.zzdsk.post(new zzaxu(this));
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        zzatm.zzdy("AdMediaPlayerView surface changed");
        boolean z = this.zzdwt == 3;
        boolean z2 = this.zzdww == i && this.zzdwx == i2;
        if (this.zzdwu != null && z && z2) {
            int i3 = this.zzdxd;
            if (i3 != 0) {
                seekTo(i3);
            }
            play();
        }
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzl(i, i2);
        }
        zzatv.zzdsk.post(new zzaxx(this, i, i2));
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        zzatm.zzdy("AdMediaPlayerView surface destroyed");
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null && this.zzdxd == 0) {
            this.zzdxd = mediaPlayer.getCurrentPosition();
        }
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzxd();
        }
        zzatv.zzdsk.post(new zzaxw(this));
        zzam(true);
        return true;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzdwq.zzc(this);
        this.zzdxk.zza(surfaceTexture, this.zzdxe);
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(final int i) {
        StringBuilder sb = new StringBuilder(58);
        sb.append("AdMediaPlayerView window visibility changed to ");
        sb.append(i);
        zzatm.zzdy(sb.toString());
        zzatv.zzdsk.post(new Runnable(this, i) { // from class: com.google.android.gms.internal.ads.zzaxq
            private final int zzdsy;
            private final zzaxr zzdwo;

            {
                this.zzdwo = this;
                this.zzdsy = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzdwo.zzcs(this.zzdsy);
            }
        });
        super.onWindowVisibilityChanged(i);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0069 A[PHI: r6
  0x0069: PHI (r6v6 int) = (r6v5 int), (r6v3 int), (r6v3 int), (r6v3 int) binds: [B:29:0x0068, B:27:0x0065, B:21:0x0055, B:15:0x0041] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int size;
        int i3;
        int defaultSize = getDefaultSize(this.zzdww, i);
        int defaultSize2 = getDefaultSize(this.zzdwx, i2);
        if (this.zzdww <= 0 || this.zzdwx <= 0 || this.zzdxb != null) {
            size = defaultSize;
        } else {
            int mode = View.MeasureSpec.getMode(i);
            size = View.MeasureSpec.getSize(i);
            int mode2 = View.MeasureSpec.getMode(i2);
            int size2 = View.MeasureSpec.getSize(i2);
            if (mode == 1073741824 && mode2 == 1073741824) {
                int i4 = this.zzdww;
                int i5 = i4 * size2;
                int i6 = this.zzdwx;
                if (i5 < size * i6) {
                    defaultSize = (i4 * size2) / i6;
                    defaultSize2 = size2;
                    size = defaultSize;
                } else if (i4 * size2 > size * i6) {
                    defaultSize2 = (i6 * size) / i4;
                } else {
                    defaultSize2 = size2;
                }
            } else if (mode == 1073741824) {
                int i7 = (this.zzdwx * size) / this.zzdww;
                if (mode2 != Integer.MIN_VALUE || i7 <= size2) {
                    defaultSize2 = i7;
                } else {
                    defaultSize2 = size2;
                }
            } else if (mode2 == 1073741824) {
                int i8 = (this.zzdww * size2) / this.zzdwx;
                if (mode != Integer.MIN_VALUE || i8 <= size) {
                    size = i8;
                }
                defaultSize2 = size2;
            } else {
                int i9 = this.zzdww;
                int i10 = this.zzdwx;
                if (mode2 != Integer.MIN_VALUE || i10 <= size2) {
                    defaultSize2 = i10;
                } else {
                    i9 = (i9 * size2) / i10;
                    defaultSize2 = size2;
                }
                if (mode != Integer.MIN_VALUE || i9 <= size) {
                    size = i9;
                } else {
                    defaultSize2 = (this.zzdwx * size) / this.zzdww;
                }
            }
        }
        setMeasuredDimension(size, defaultSize2);
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzl(size, defaultSize2);
        }
        if (Build.VERSION.SDK_INT == 16) {
            int i11 = this.zzdwz;
            if ((i11 > 0 && i11 != size) || ((i3 = this.zzdxa) > 0 && i3 != defaultSize2)) {
                zzwo();
            }
            this.zzdwz = size;
            this.zzdxa = defaultSize2;
        }
    }

    @Override // android.view.View
    public final String toString() {
        String name = getClass().getName();
        String hexString = Integer.toHexString(hashCode());
        StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length());
        sb.append(name);
        sb.append("@");
        sb.append(hexString);
        return sb.toString();
    }

    private final void zzwn() {
        zzatm.zzdy("AdMediaPlayerView init MediaPlayer");
        SurfaceTexture surfaceTexture = getSurfaceTexture();
        if (this.zzdwv == null || surfaceTexture == null) {
            return;
        }
        zzam(false);
        try {
            com.google.android.gms.ads.internal.zzp.zzko();
            this.zzdwu = new MediaPlayer();
            this.zzdwu.setOnBufferingUpdateListener(this);
            this.zzdwu.setOnCompletionListener(this);
            this.zzdwu.setOnErrorListener(this);
            this.zzdwu.setOnInfoListener(this);
            this.zzdwu.setOnPreparedListener(this);
            this.zzdwu.setOnVideoSizeChangedListener(this);
            this.zzdwy = 0;
            if (this.zzdxc) {
                this.zzdxb = new zzayr(getContext());
                this.zzdxb.zza(surfaceTexture, getWidth(), getHeight());
                this.zzdxb.start();
                SurfaceTexture surfaceTextureZzxe = this.zzdxb.zzxe();
                if (surfaceTextureZzxe != null) {
                    surfaceTexture = surfaceTextureZzxe;
                } else {
                    this.zzdxb.zzxd();
                    this.zzdxb = null;
                }
            }
            this.zzdwu.setDataSource(getContext(), this.zzdwv);
            com.google.android.gms.ads.internal.zzp.zzkp();
            this.zzdwu.setSurface(new Surface(surfaceTexture));
            this.zzdwu.setAudioStreamType(3);
            this.zzdwu.setScreenOnWhilePlaying(true);
            this.zzdwu.prepareAsync();
            zzcr(1);
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            String strValueOf = String.valueOf(this.zzdwv);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 36);
            sb.append("Failed to initialize MediaPlayer at ");
            sb.append(strValueOf);
            zzatm.zzd(sb.toString(), e);
            onError(this.zzdwu, 1, 0);
        }
    }

    private final void zzwo() {
        if (this.zzdwr && zzwp() && this.zzdwu.getCurrentPosition() > 0 && this.zzdwt != 3) {
            zzatm.zzdy("AdMediaPlayerView nudging MediaPlayer");
            zzd(0.0f);
            this.zzdwu.start();
            int currentPosition = this.zzdwu.getCurrentPosition();
            long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
            while (zzwp() && this.zzdwu.getCurrentPosition() == currentPosition && com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis() - jCurrentTimeMillis <= 250) {
            }
            this.zzdwu.pause();
            zzwq();
        }
    }

    private final void zzam(boolean z) {
        zzatm.zzdy("AdMediaPlayerView release");
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzxd();
            this.zzdxb = null;
        }
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null) {
            mediaPlayer.reset();
            this.zzdwu.release();
            this.zzdwu = null;
            zzcr(0);
            if (z) {
                this.zzdwt = 0;
                this.zzdwt = 0;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void play() {
        zzatm.zzdy("AdMediaPlayerView play");
        if (zzwp()) {
            this.zzdwu.start();
            zzcr(3);
            this.zzdxk.zzws();
            zzatv.zzdsk.post(new zzaxz(this));
        }
        this.zzdwt = 3;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void pause() {
        zzatm.zzdy("AdMediaPlayerView pause");
        if (zzwp() && this.zzdwu.isPlaying()) {
            this.zzdwu.pause();
            zzcr(4);
            zzatv.zzdsk.post(new zzaxy(this));
        }
        this.zzdwt = 4;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getDuration() {
        if (zzwp()) {
            return this.zzdwu.getDuration();
        }
        return -1;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getCurrentPosition() {
        if (zzwp()) {
            return this.zzdwu.getCurrentPosition();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void seekTo(int i) {
        StringBuilder sb = new StringBuilder(34);
        sb.append("AdMediaPlayerView seek ");
        sb.append(i);
        zzatm.zzdy(sb.toString());
        if (zzwp()) {
            this.zzdwu.seekTo(i);
            this.zzdxd = 0;
        } else {
            this.zzdxd = i;
        }
    }

    private final boolean zzwp() {
        int i;
        return (this.zzdwu == null || (i = this.zzdws) == -1 || i == 0 || i == 1) ? false : true;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza(float f, float f2) {
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzb(f, f2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getVideoWidth() {
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoWidth();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getVideoHeight() {
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null) {
            return mediaPlayer.getVideoHeight();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaya, com.google.android.gms.internal.ads.zzayx
    public final void zzwq() {
        zzd(this.zzdxl.getVolume());
    }

    private final void zzd(float f) {
        MediaPlayer mediaPlayer = this.zzdwu;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.setVolume(f, f);
            } catch (IllegalStateException unused) {
            }
        } else {
            zzatm.zzeu("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
        }
    }

    private final void zzcr(int i) {
        if (i == 3) {
            this.zzdwq.zzxg();
            this.zzdxl.zzxg();
        } else if (this.zzdws == 3) {
            this.zzdwq.zzxh();
            this.zzdxl.zzxh();
        }
        this.zzdws = i;
    }

    final /* synthetic */ void zzcs(int i) {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.onWindowVisibilityChanged(i);
        }
    }

    static /* synthetic */ zzayb zza(zzaxr zzaxrVar) {
        return zzaxrVar.zzdxe;
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            zzdwp.put(Integer.valueOf(IabHelper.IABHELPER_SEND_INTENT_FAILED), "MEDIA_ERROR_IO");
            zzdwp.put(Integer.valueOf(IabHelper.IABHELPER_MISSING_TOKEN), "MEDIA_ERROR_MALFORMED");
            zzdwp.put(Integer.valueOf(IabHelper.IABHELPER_INVALID_CONSUMPTION), "MEDIA_ERROR_UNSUPPORTED");
            zzdwp.put(Integer.valueOf(FetchConst.ERROR_SERVER_ERROR), "MEDIA_ERROR_TIMED_OUT");
            zzdwp.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzdwp.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzdwp.put(1, "MEDIA_ERROR_UNKNOWN");
        zzdwp.put(1, "MEDIA_INFO_UNKNOWN");
        zzdwp.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzdwp.put(701, "MEDIA_INFO_BUFFERING_START");
        zzdwp.put(702, "MEDIA_INFO_BUFFERING_END");
        zzdwp.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzdwp.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzdwp.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build.VERSION.SDK_INT >= 19) {
            zzdwp.put(Integer.valueOf(FetchConst.STATUS_DOWNLOADING), "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzdwp.put(Integer.valueOf(FetchConst.STATUS_PAUSED), "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
}
