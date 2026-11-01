package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.TextureView;
import com.appsflyer.share.Constants;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzayw extends zzaya implements TextureView.SurfaceTextureListener, zzazu {
    private Surface zzbhq;
    private final zzays zzdwq;
    private final boolean zzdwr;
    private int zzdww;
    private int zzdwx;
    private int zzdwz;
    private int zzdxa;
    private zzayr zzdxb;
    private final boolean zzdxc;
    private zzayb zzdxe;
    private final zzayt zzdxm;
    private String[] zzdxz;
    private final zzayq zzeaw;
    private zzazm zzeax;
    private String zzeay;
    private boolean zzeaz;
    private int zzeba;
    private boolean zzebb;
    private boolean zzebc;
    private float zzebd;

    public zzayw(Context context, zzays zzaysVar, zzayt zzaytVar, boolean z, boolean z2, zzayq zzayqVar) {
        super(context);
        this.zzeba = 1;
        this.zzdwr = z2;
        this.zzdxm = zzaytVar;
        this.zzdwq = zzaysVar;
        this.zzdxc = z;
        this.zzeaw = zzayqVar;
        setSurfaceTextureListener(this);
        this.zzdwq.zzb(this);
    }

    private final zzazm zzxv() {
        return new zzazm(this.zzdxm.getContext(), this.zzeaw);
    }

    private final String zzxw() {
        return com.google.android.gms.ads.internal.zzp.zzjy().zzr(this.zzdxm.getContext(), this.zzdxm.zzxp().zzbnh);
    }

    private final boolean zzxx() {
        return (this.zzeax == null || this.zzeaz) ? false : true;
    }

    private final boolean zzxy() {
        return zzxx() && this.zzeba != 1;
    }

    private final void zzxz() {
        String str;
        if (this.zzeax != null || (str = this.zzeay) == null || this.zzbhq == null) {
            return;
        }
        if (str.startsWith("cache:")) {
            zzbah zzbahVarZzez = this.zzdxm.zzez(this.zzeay);
            if (zzbahVarZzez instanceof zzbas) {
                this.zzeax = ((zzbas) zzbahVarZzez).zzyp();
            } else if (zzbahVarZzez instanceof zzbat) {
                zzbat zzbatVar = (zzbat) zzbahVarZzez;
                String strZzxw = zzxw();
                ByteBuffer byteBuffer = zzbatVar.getByteBuffer();
                boolean zZzyq = zzbatVar.zzyq();
                String url = zzbatVar.getUrl();
                if (url == null) {
                    zzatm.zzeu("Stream cache URL is null.");
                    return;
                } else {
                    this.zzeax = zzxv();
                    this.zzeax.zza(new Uri[]{Uri.parse(url)}, strZzxw, byteBuffer, zZzyq);
                }
            } else {
                String strValueOf = String.valueOf(this.zzeay);
                zzatm.zzeu(strValueOf.length() != 0 ? "Stream cache miss: ".concat(strValueOf) : new String("Stream cache miss: "));
                return;
            }
        } else {
            this.zzeax = zzxv();
            String strZzxw2 = zzxw();
            Uri[] uriArr = new Uri[this.zzdxz.length];
            int i = 0;
            while (true) {
                String[] strArr = this.zzdxz;
                if (i >= strArr.length) {
                    break;
                }
                uriArr[i] = Uri.parse(strArr[i]);
                i++;
            }
            this.zzeax.zza(uriArr, strZzxw2);
        }
        this.zzeax.zza(this);
        zza(this.zzbhq, false);
        this.zzeba = this.zzeax.zzyk().getPlaybackState();
        if (this.zzeba == 3) {
            zzya();
        }
    }

    private final void zza(Surface surface, boolean z) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zza(surface, z);
        } else {
            zzatm.zzeu("Trying to set surface before player is initalized.");
        }
    }

    private final void zza(float f, boolean z) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzb(f, z);
        } else {
            zzatm.zzeu("Trying to set volume before player is initalized.");
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya, com.google.android.gms.internal.ads.zzayx
    public final void zzwq() {
        zza(this.zzdxl.getVolume(), false);
    }

    private final void zzya() {
        if (this.zzebb) {
            return;
        }
        this.zzebb = true;
        zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzayz
            private final zzayw zzebe;

            {
                this.zzebe = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzyj();
            }
        });
        zzwq();
        this.zzdwq.zzei();
        if (this.zzebc) {
            play();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final String zzwm() {
        String str = this.zzdxc ? " spherical" : "";
        return str.length() != 0 ? "ExoPlayer/3".concat(str) : new String("ExoPlayer/3");
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza(zzayb zzaybVar) {
        this.zzdxe = zzaybVar;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void setVideoPath(String str) {
        if (str != null) {
            this.zzeay = str;
            this.zzdxz = new String[]{str};
            zzxz();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzb(String str, String[] strArr) {
        if (str != null) {
            if (strArr == null) {
                setVideoPath(str);
            }
            this.zzeay = str;
            this.zzdxz = (String[]) Arrays.copyOf(strArr, strArr.length);
            zzxz();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void play() {
        if (zzxy()) {
            if (this.zzeaw.zzdyw) {
                zzyc();
            }
            this.zzeax.zzyk().zzf(true);
            this.zzdwq.zzxg();
            this.zzdxl.zzxg();
            this.zzdxk.zzws();
            zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzaza
                private final zzayw zzebe;

                {
                    this.zzebe = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzebe.zzyh();
                }
            });
            return;
        }
        this.zzebc = true;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void stop() {
        if (zzxx()) {
            this.zzeax.zzyk().stop();
            if (this.zzeax != null) {
                zza((Surface) null, true);
                zzazm zzazmVar = this.zzeax;
                if (zzazmVar != null) {
                    zzazmVar.zza((zzazu) null);
                    this.zzeax.release();
                    this.zzeax = null;
                }
                this.zzeba = 1;
                this.zzeaz = false;
                this.zzebb = false;
                this.zzebc = false;
            }
        }
        this.zzdwq.zzxh();
        this.zzdxl.zzxh();
        this.zzdwq.onStop();
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void pause() {
        if (zzxy()) {
            if (this.zzeaw.zzdyw) {
                zzyd();
            }
            this.zzeax.zzyk().zzf(false);
            this.zzdwq.zzxh();
            this.zzdxl.zzxh();
            zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzazd
                private final zzayw zzebe;

                {
                    this.zzebe = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzebe.zzyg();
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void seekTo(int i) {
        if (zzxy()) {
            this.zzeax.zzyk().seekTo(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzct(int i) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzyn().zzda(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzcu(int i) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzyn().zzdb(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzcv(int i) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzyn().zzcv(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzcw(int i) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzyn().zzcw(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zzcx(int i) {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzcx(i);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final void zza(float f, float f2) {
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzb(f, f2);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getCurrentPosition() {
        if (zzxy()) {
            return (int) this.zzeax.zzyk().zzdu();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getDuration() {
        if (zzxy()) {
            return (int) this.zzeax.zzyk().getDuration();
        }
        return 0;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getVideoWidth() {
        return this.zzdww;
    }

    @Override // com.google.android.gms.internal.ads.zzaya
    public final int getVideoHeight() {
        return this.zzdwx;
    }

    @Override // android.view.View
    protected final void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = this.zzebd;
        if (f != 0.0f && this.zzdxb == null) {
            float f2 = measuredWidth;
            float f3 = f2 / measuredHeight;
            if (f > f3) {
                measuredHeight = (int) (f2 / f);
            }
            float f4 = this.zzebd;
            if (f4 < f3) {
                measuredWidth = (int) (measuredHeight * f4);
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzl(measuredWidth, measuredHeight);
        }
        if (Build.VERSION.SDK_INT == 16) {
            int i4 = this.zzdwz;
            if (((i4 > 0 && i4 != measuredWidth) || ((i3 = this.zzdxa) > 0 && i3 != measuredHeight)) && this.zzdwr && zzxx()) {
                zzge zzgeVarZzyk = this.zzeax.zzyk();
                if (zzgeVarZzyk.zzdu() > 0 && !zzgeVarZzyk.zzds()) {
                    zza(0.0f, true);
                    zzgeVarZzyk.zzf(true);
                    long jZzdu = zzgeVarZzyk.zzdu();
                    long jCurrentTimeMillis = com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis();
                    while (zzxx() && zzgeVarZzyk.zzdu() == jZzdu && com.google.android.gms.ads.internal.zzp.zzkf().currentTimeMillis() - jCurrentTimeMillis <= 250) {
                    }
                    zzgeVarZzyk.zzf(false);
                    zzwq();
                }
            }
            this.zzdwz = measuredWidth;
            this.zzdxa = measuredHeight;
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.zzdxc) {
            this.zzdxb = new zzayr(getContext());
            this.zzdxb.zza(surfaceTexture, i, i2);
            this.zzdxb.start();
            SurfaceTexture surfaceTextureZzxe = this.zzdxb.zzxe();
            if (surfaceTextureZzxe != null) {
                surfaceTexture = surfaceTextureZzxe;
            } else {
                this.zzdxb.zzxd();
                this.zzdxb = null;
            }
        }
        this.zzbhq = new Surface(surfaceTexture);
        if (this.zzeax == null) {
            zzxz();
        } else {
            zza(this.zzbhq, true);
            if (!this.zzeaw.zzdyw) {
                zzyc();
            }
        }
        if (this.zzdww == 0 || this.zzdwx == 0) {
            zzn(i, i2);
        } else {
            zzyb();
        }
        zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzazc
            private final zzayw zzebe;

            {
                this.zzebe = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzyf();
            }
        });
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, final int i, final int i2) {
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzl(i, i2);
        }
        zzatv.zzdsk.post(new Runnable(this, i, i2) { // from class: com.google.android.gms.internal.ads.zzazf
            private final int zzdsy;
            private final int zzdsz;
            private final zzayw zzebe;

            {
                this.zzebe = this;
                this.zzdsy = i;
                this.zzdsz = i2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzo(this.zzdsy, this.zzdsz);
            }
        });
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        this.zzdwq.zzc(this);
        this.zzdxk.zza(surfaceTexture, this.zzdxe);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        pause();
        zzayr zzayrVar = this.zzdxb;
        if (zzayrVar != null) {
            zzayrVar.zzxd();
            this.zzdxb = null;
        }
        if (this.zzeax != null) {
            zzyd();
            Surface surface = this.zzbhq;
            if (surface != null) {
                surface.release();
            }
            this.zzbhq = null;
            zza((Surface) null, true);
        }
        zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzaze
            private final zzayw zzebe;

            {
                this.zzebe = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzye();
            }
        });
        return true;
    }

    @Override // android.view.View
    protected final void onWindowVisibilityChanged(final int i) {
        StringBuilder sb = new StringBuilder(57);
        sb.append("AdExoPlayerView3 window visibility changed to ");
        sb.append(i);
        zzatm.zzdy(sb.toString());
        zzatv.zzdsk.post(new Runnable(this, i) { // from class: com.google.android.gms.internal.ads.zzazh
            private final int zzdsy;
            private final zzayw zzebe;

            {
                this.zzebe = this;
                this.zzdsy = i;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzcz(this.zzdsy);
            }
        });
        super.onWindowVisibilityChanged(i);
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzb(final boolean z, final long j) {
        if (this.zzdxm != null) {
            zzawx.zzdwa.execute(new Runnable(this, z, j) { // from class: com.google.android.gms.internal.ads.zzazg
                private final boolean zzdye;
                private final zzayw zzebe;
                private final long zzebf;

                {
                    this.zzebe = this;
                    this.zzdye = z;
                    this.zzebf = j;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzebe.zzc(this.zzdye, this.zzebf);
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzcy(int i) {
        if (this.zzeba != i) {
            this.zzeba = i;
            if (i == 3) {
                zzya();
                return;
            }
            if (i != 4) {
                return;
            }
            if (this.zzeaw.zzdyw) {
                zzyd();
            }
            this.zzdwq.zzxh();
            this.zzdxl.zzxh();
            zzatv.zzdsk.post(new Runnable(this) { // from class: com.google.android.gms.internal.ads.zzayy
                private final zzayw zzebe;

                {
                    this.zzebe = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzebe.zzyi();
                }
            });
        }
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zzm(int i, int i2) {
        this.zzdww = i;
        this.zzdwx = i2;
        zzyb();
    }

    @Override // com.google.android.gms.internal.ads.zzazu
    public final void zza(String str, Exception exc) {
        String canonicalName = exc.getClass().getCanonicalName();
        String message = exc.getMessage();
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(canonicalName).length() + String.valueOf(message).length());
        sb.append(str);
        sb.append(Constants.URL_PATH_DELIMITER);
        sb.append(canonicalName);
        sb.append(":");
        sb.append(message);
        final String string = sb.toString();
        String strValueOf = String.valueOf(string);
        zzatm.zzeu(strValueOf.length() != 0 ? "ExoPlayerAdapter error: ".concat(strValueOf) : new String("ExoPlayerAdapter error: "));
        this.zzeaz = true;
        if (this.zzeaw.zzdyw) {
            zzyd();
        }
        zzatv.zzdsk.post(new Runnable(this, string) { // from class: com.google.android.gms.internal.ads.zzazb
            private final String zzcyl;
            private final zzayw zzebe;

            {
                this.zzebe = this;
                this.zzcyl = string;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzebe.zzfa(this.zzcyl);
            }
        });
    }

    private final void zzyb() {
        zzn(this.zzdww, this.zzdwx);
    }

    private final void zzn(int i, int i2) {
        float f = i2 > 0 ? i / i2 : 1.0f;
        if (this.zzebd != f) {
            this.zzebd = f;
            requestLayout();
        }
    }

    private final void zzyc() {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzap(true);
        }
    }

    private final void zzyd() {
        zzazm zzazmVar = this.zzeax;
        if (zzazmVar != null) {
            zzazmVar.zzap(false);
        }
    }

    final /* synthetic */ void zzc(boolean z, long j) {
        this.zzdxm.zza(z, j);
    }

    final /* synthetic */ void zzcz(int i) {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.onWindowVisibilityChanged(i);
        }
    }

    final /* synthetic */ void zzye() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzwu();
        }
    }

    final /* synthetic */ void zzo(int i, int i2) {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzj(i, i2);
        }
    }

    final /* synthetic */ void zzyf() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzwr();
        }
    }

    final /* synthetic */ void zzyg() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.onPaused();
        }
    }

    final /* synthetic */ void zzyh() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzws();
        }
    }

    final /* synthetic */ void zzfa(String str) {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzn("ExoPlayerAdapter error", str);
        }
    }

    final /* synthetic */ void zzyi() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzwt();
        }
    }

    final /* synthetic */ void zzyj() {
        zzayb zzaybVar = this.zzdxe;
        if (zzaybVar != null) {
            zzaybVar.zzei();
        }
    }
}
