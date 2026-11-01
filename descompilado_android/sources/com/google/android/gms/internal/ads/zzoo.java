package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Surface;
import com.google.android.exoplayer2.C;
import com.ironsource.sdk.constants.Constants;
import com.tonyodev.fetch.FetchService;
import java.nio.ByteBuffer;
import net.hockeyapp.android.Strings;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzoo extends zzkn {
    private static final int[] zzbhh = {1920, 1600, 1440, Strings.LOGIN_HEADLINE_TEXT_ID, 960, 854, 640, 540, FetchService.QUERY_SINGLE};
    private int zzagc;
    private boolean zzajn;
    private final zzos zzbhi;
    private final zzox zzbhj;
    private final long zzbhk;
    private final int zzbhl;
    private final boolean zzbhm;
    private final long[] zzbhn;
    private zzgq[] zzbho;
    private zzoq zzbhp;
    private Surface zzbhq;
    private Surface zzbhr;
    private int zzbhs;
    private boolean zzbht;
    private long zzbhu;
    private long zzbhv;
    private int zzbhw;
    private int zzbhx;
    private int zzbhy;
    private float zzbhz;
    private int zzbia;
    private int zzbib;
    private int zzbic;
    private float zzbid;
    private int zzbie;
    private int zzbif;
    private int zzbig;
    private float zzbih;
    zzot zzbii;
    private long zzbij;
    private int zzbik;
    private final Context zzlk;

    public zzoo(Context context, zzkp zzkpVar, long j, Handler handler, zzou zzouVar, int i) {
        this(context, zzkpVar, 0L, null, false, handler, zzouVar, -1);
    }

    private static boolean zzeg(long j) {
        return j < -30000;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private zzoo(Context context, zzkp zzkpVar, long j, zziq<Object> zziqVar, boolean z, Handler handler, zzou zzouVar, int i) {
        super(2, zzkpVar, null, false);
        boolean z2 = false;
        this.zzbhk = 0L;
        this.zzbhl = -1;
        this.zzlk = context.getApplicationContext();
        this.zzbhi = new zzos(context);
        this.zzbhj = new zzox(handler, zzouVar);
        if (zzoh.SDK_INT <= 22 && "foster".equals(zzoh.DEVICE) && "NVIDIA".equals(zzoh.MANUFACTURER)) {
            z2 = true;
        }
        this.zzbhm = z2;
        this.zzbhn = new long[10];
        this.zzbij = C.TIME_UNSET;
        this.zzbhu = C.TIME_UNSET;
        this.zzbia = -1;
        this.zzbib = -1;
        this.zzbid = -1.0f;
        this.zzbhz = -1.0f;
        this.zzbhs = 1;
        zzit();
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final int zza(zzkp zzkpVar, zzgq zzgqVar) throws zzks {
        boolean z;
        String str = zzgqVar.zzafb;
        if (!zznw.zzbd(str)) {
            return 0;
        }
        zzil zzilVar = zzgqVar.zzafe;
        if (zzilVar != null) {
            z = false;
            for (int i = 0; i < zzilVar.zzamg; i++) {
                z |= zzilVar.zzx(i).zzamk;
            }
        } else {
            z = false;
        }
        zzkk zzkkVarZzc = zzkpVar.zzc(str, z);
        if (zzkkVarZzc == null) {
            return 1;
        }
        boolean zZzaz = zzkkVarZzc.zzaz(zzgqVar.zzaey);
        if (zZzaz && zzgqVar.width > 0 && zzgqVar.height > 0) {
            if (zzoh.SDK_INT >= 21) {
                zZzaz = zzkkVarZzc.zza(zzgqVar.width, zzgqVar.height, zzgqVar.zzaff);
            } else {
                zZzaz = zzgqVar.width * zzgqVar.height <= zzkr.zzgt();
                if (!zZzaz) {
                    int i2 = zzgqVar.width;
                    int i3 = zzgqVar.height;
                    String str2 = zzoh.zzbgs;
                    StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 56);
                    sb.append("FalseCheck [legacyFrameSize, ");
                    sb.append(i2);
                    sb.append("x");
                    sb.append(i3);
                    sb.append("] [");
                    sb.append(str2);
                    sb.append(Constants.RequestParameters.RIGHT_BRACKETS);
                    Log.d("MediaCodecVideoRenderer", sb.toString());
                }
            }
        }
        return (zZzaz ? 3 : 2) | (zzkkVarZzc.zzaxj ? 8 : 4) | (zzkkVarZzc.zzajn ? 16 : 0);
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zzd(boolean z) throws zzgb {
        super.zzd(z);
        this.zzagc = zzdp().zzagc;
        this.zzajn = this.zzagc != 0;
        this.zzbhj.zza(this.zzaza);
        this.zzbhi.enable();
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected final void zza(zzgq[] zzgqVarArr, long j) throws zzgb {
        this.zzbho = zzgqVarArr;
        if (this.zzbij == C.TIME_UNSET) {
            this.zzbij = j;
        } else {
            int i = this.zzbik;
            long[] jArr = this.zzbhn;
            if (i == jArr.length) {
                long j2 = jArr[i - 1];
                StringBuilder sb = new StringBuilder(65);
                sb.append("Too many stream changes, so dropping offset: ");
                sb.append(j2);
                Log.w("MediaCodecVideoRenderer", sb.toString());
            } else {
                this.zzbik = i + 1;
            }
            this.zzbhn[this.zzbik - 1] = j;
        }
        super.zza(zzgqVarArr, j);
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zza(long j, boolean z) throws zzgb {
        super.zza(j, z);
        zzir();
        this.zzbhx = 0;
        int i = this.zzbik;
        if (i != 0) {
            this.zzbij = this.zzbhn[i - 1];
            this.zzbik = 0;
        }
        if (z) {
            zziq();
        } else {
            this.zzbhu = C.TIME_UNSET;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzgv
    public final boolean isReady() {
        Surface surface;
        if (super.isReady() && (this.zzbht || (((surface = this.zzbhr) != null && this.zzbhq == surface) || zzgn() == null))) {
            this.zzbhu = C.TIME_UNSET;
            return true;
        }
        if (this.zzbhu == C.TIME_UNSET) {
            return false;
        }
        if (SystemClock.elapsedRealtime() < this.zzbhu) {
            return true;
        }
        this.zzbhu = C.TIME_UNSET;
        return false;
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void onStarted() {
        super.onStarted();
        this.zzbhw = 0;
        this.zzbhv = SystemClock.elapsedRealtime();
        this.zzbhu = C.TIME_UNSET;
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void onStopped() {
        zziw();
        super.onStopped();
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zzdo() {
        this.zzbia = -1;
        this.zzbib = -1;
        this.zzbid = -1.0f;
        this.zzbhz = -1.0f;
        this.zzbij = C.TIME_UNSET;
        this.zzbik = 0;
        zzit();
        zzir();
        this.zzbhi.disable();
        this.zzbii = null;
        this.zzajn = false;
        try {
            super.zzdo();
        } finally {
            this.zzaza.zzfu();
            this.zzbhj.zzb(this.zzaza);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzga, com.google.android.gms.internal.ads.zzgg
    public final void zza(int i, Object obj) throws zzgb {
        if (i != 1) {
            if (i == 4) {
                this.zzbhs = ((Integer) obj).intValue();
                MediaCodec mediaCodecZzgn = zzgn();
                if (mediaCodecZzgn != null) {
                    mediaCodecZzgn.setVideoScalingMode(this.zzbhs);
                    return;
                }
                return;
            }
            super.zza(i, obj);
            return;
        }
        Surface surface = (Surface) obj;
        if (surface == null) {
            Surface surface2 = this.zzbhr;
            if (surface2 != null) {
                surface = surface2;
            } else {
                zzkk zzkkVarZzgo = zzgo();
                if (zzkkVarZzgo != null && zzm(zzkkVarZzgo.zzaxk)) {
                    this.zzbhr = zzok.zzc(this.zzlk, zzkkVarZzgo.zzaxk);
                    surface = this.zzbhr;
                }
            }
        }
        if (this.zzbhq != surface) {
            this.zzbhq = surface;
            int state = getState();
            if (state == 1 || state == 2) {
                MediaCodec mediaCodecZzgn2 = zzgn();
                if (zzoh.SDK_INT >= 23 && mediaCodecZzgn2 != null && surface != null) {
                    mediaCodecZzgn2.setOutputSurface(surface);
                } else {
                    zzgp();
                    zzgm();
                }
            }
            if (surface != null && surface != this.zzbhr) {
                zziv();
                zzir();
                if (state == 2) {
                    zziq();
                    return;
                }
                return;
            }
            zzit();
            zzir();
            return;
        }
        if (surface == null || surface == this.zzbhr) {
            return;
        }
        zziv();
        if (this.zzbht) {
            this.zzbhj.zzb(this.zzbhq);
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final boolean zza(zzkk zzkkVar) {
        return this.zzbhq != null || zzm(zzkkVar.zzaxk);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zza(zzkk zzkkVar, MediaCodec mediaCodec, zzgq zzgqVar, MediaCrypto mediaCrypto) throws zzks {
        zzoq zzoqVar;
        int i;
        Point point;
        zzgq[] zzgqVarArr = this.zzbho;
        int i2 = zzgqVar.width;
        int i3 = zzgqVar.height;
        int iZzi = zzi(zzgqVar);
        if (zzgqVarArr.length == 1) {
            zzoqVar = new zzoq(i2, i3, iZzi);
        } else {
            int iMax = i3;
            int iMax2 = iZzi;
            boolean z = false;
            int iMax3 = i2;
            for (zzgq zzgqVar2 : zzgqVarArr) {
                if (zza(zzkkVar.zzaxj, zzgqVar, zzgqVar2)) {
                    z |= zzgqVar2.width == -1 || zzgqVar2.height == -1;
                    iMax3 = Math.max(iMax3, zzgqVar2.width);
                    int iMax4 = Math.max(iMax, zzgqVar2.height);
                    iMax2 = Math.max(iMax2, zzi(zzgqVar2));
                    iMax = iMax4;
                }
            }
            if (z) {
                StringBuilder sb = new StringBuilder(66);
                sb.append("Resolutions unknown. Codec max resolution: ");
                sb.append(iMax3);
                sb.append("x");
                sb.append(iMax);
                Log.w("MediaCodecVideoRenderer", sb.toString());
                boolean z2 = zzgqVar.height > zzgqVar.width;
                int i4 = z2 ? zzgqVar.height : zzgqVar.width;
                int i5 = z2 ? zzgqVar.width : zzgqVar.height;
                float f = i5 / i4;
                int[] iArr = zzbhh;
                int length = iArr.length;
                int i6 = 0;
                while (true) {
                    if (i6 < length) {
                        int i7 = length;
                        int i8 = iArr[i6];
                        int[] iArr2 = iArr;
                        int i9 = (int) (i8 * f);
                        if (i8 > i4 && i9 > i5) {
                            int i10 = i4;
                            int i11 = i5;
                            if (zzoh.SDK_INT >= 21) {
                                int i12 = z2 ? i9 : i8;
                                if (z2) {
                                    i9 = i8;
                                }
                                Point pointZzc = zzkkVar.zzc(i12, i9);
                                i = iMax2;
                                if (zzkkVar.zza(pointZzc.x, pointZzc.y, zzgqVar.zzaff)) {
                                    point = pointZzc;
                                    break;
                                }
                                i6++;
                                length = i7;
                                iArr = iArr2;
                                i4 = i10;
                                i5 = i11;
                                iMax2 = i;
                                f = f;
                            } else {
                                i = iMax2;
                                int iZze = zzoh.zze(i8, 16) << 4;
                                int iZze2 = zzoh.zze(i9, 16) << 4;
                                if (iZze * iZze2 <= zzkr.zzgt()) {
                                    int i13 = z2 ? iZze2 : iZze;
                                    if (z2) {
                                        iZze2 = iZze;
                                    }
                                    point = new Point(i13, iZze2);
                                    break;
                                }
                                i6++;
                                length = i7;
                                iArr = iArr2;
                                i4 = i10;
                                i5 = i11;
                                iMax2 = i;
                                f = f;
                            }
                        }
                    }
                    i = iMax2;
                    point = null;
                    break;
                }
                if (point != null) {
                    iMax3 = Math.max(iMax3, point.x);
                    iMax = Math.max(iMax, point.y);
                    iMax2 = Math.max(i, zza(zzgqVar.zzafb, iMax3, iMax));
                    StringBuilder sb2 = new StringBuilder(57);
                    sb2.append("Codec max resolution adjusted to: ");
                    sb2.append(iMax3);
                    sb2.append("x");
                    sb2.append(iMax);
                    Log.w("MediaCodecVideoRenderer", sb2.toString());
                } else {
                    iMax2 = i;
                }
            }
            zzoqVar = new zzoq(iMax3, iMax, iMax2);
        }
        this.zzbhp = zzoqVar;
        zzoq zzoqVar2 = this.zzbhp;
        boolean z3 = this.zzbhm;
        int i14 = this.zzagc;
        MediaFormat mediaFormatZzeh = zzgqVar.zzeh();
        mediaFormatZzeh.setInteger("max-width", zzoqVar2.width);
        mediaFormatZzeh.setInteger("max-height", zzoqVar2.height);
        if (zzoqVar2.zzbil != -1) {
            mediaFormatZzeh.setInteger("max-input-size", zzoqVar2.zzbil);
        }
        if (z3) {
            mediaFormatZzeh.setInteger("auto-frc", 0);
        }
        if (i14 != 0) {
            mediaFormatZzeh.setFeatureEnabled("tunneled-playback", true);
            mediaFormatZzeh.setInteger("audio-session-id", i14);
        }
        if (this.zzbhq == null) {
            zznt.checkState(zzm(zzkkVar.zzaxk));
            if (this.zzbhr == null) {
                this.zzbhr = zzok.zzc(this.zzlk, zzkkVar.zzaxk);
            }
            this.zzbhq = this.zzbhr;
        }
        mediaCodec.configure(mediaFormatZzeh, this.zzbhq, (MediaCrypto) null, 0);
        if (zzoh.SDK_INT < 23 || !this.zzajn) {
            return;
        }
        this.zzbii = new zzot(this, mediaCodec);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzgp() {
        try {
            super.zzgp();
        } finally {
            Surface surface = this.zzbhr;
            if (surface != null) {
                if (this.zzbhq == surface) {
                    this.zzbhq = null;
                }
                this.zzbhr.release();
                this.zzbhr = null;
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzc(String str, long j, long j2) {
        this.zzbhj.zza(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzd(zzgq zzgqVar) throws zzgb {
        super.zzd(zzgqVar);
        this.zzbhj.zzb(zzgqVar);
        this.zzbhz = zzgqVar.zzafh == -1.0f ? 1.0f : zzgqVar.zzafh;
        this.zzbhy = zzj(zzgqVar);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zza(zzim zzimVar) {
        if (zzoh.SDK_INT >= 23 || !this.zzajn) {
            return;
        }
        zzis();
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int integer;
        int integer2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            integer = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            integer = mediaFormat.getInteger("width");
        }
        this.zzbia = integer;
        if (z) {
            integer2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            integer2 = mediaFormat.getInteger("height");
        }
        this.zzbib = integer2;
        this.zzbid = this.zzbhz;
        if (zzoh.SDK_INT >= 21) {
            int i = this.zzbhy;
            if (i == 90 || i == 270) {
                int i2 = this.zzbia;
                this.zzbia = this.zzbib;
                this.zzbib = i2;
                this.zzbid = 1.0f / this.zzbid;
            }
        } else {
            this.zzbic = this.zzbhy;
        }
        mediaCodec.setVideoScalingMode(this.zzbhs);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final boolean zza(MediaCodec mediaCodec, boolean z, zzgq zzgqVar, zzgq zzgqVar2) {
        return zza(z, zzgqVar, zzgqVar2) && zzgqVar2.width <= this.zzbhp.width && zzgqVar2.height <= this.zzbhp.height && zzgqVar2.zzafc <= this.zzbhp.zzbil;
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) {
        while (true) {
            int i3 = this.zzbik;
            if (i3 == 0) {
                break;
            }
            long[] jArr = this.zzbhn;
            if (j3 < jArr[0]) {
                break;
            }
            this.zzbij = jArr[0];
            this.zzbik = i3 - 1;
            System.arraycopy(jArr, 1, jArr, 0, this.zzbik);
        }
        long j4 = j3 - this.zzbij;
        if (z) {
            zza(mediaCodec, i, j4);
            return true;
        }
        long j5 = j3 - j;
        if (this.zzbhq == this.zzbhr) {
            if (!zzeg(j5)) {
                return false;
            }
            zza(mediaCodec, i, j4);
            return true;
        }
        if (!this.zzbht) {
            if (zzoh.SDK_INT >= 21) {
                zza(mediaCodec, i, j4, System.nanoTime());
            } else {
                zzb(mediaCodec, i, j4);
            }
            return true;
        }
        if (getState() != 2) {
            return false;
        }
        long jElapsedRealtime = j5 - ((SystemClock.elapsedRealtime() * 1000) - j2);
        long jNanoTime = System.nanoTime();
        long jZzf = this.zzbhi.zzf(j3, (jElapsedRealtime * 1000) + jNanoTime);
        long j6 = (jZzf - jNanoTime) / 1000;
        if (zzeg(j6)) {
            zzoe.beginSection("dropVideoBuffer");
            mediaCodec.releaseOutputBuffer(i, false);
            zzoe.endSection();
            this.zzaza.zzamc++;
            this.zzbhw++;
            this.zzbhx++;
            this.zzaza.zzamd = Math.max(this.zzbhx, this.zzaza.zzamd);
            if (this.zzbhw == this.zzbhl) {
                zziw();
            }
            return true;
        }
        if (zzoh.SDK_INT >= 21) {
            if (j6 < 50000) {
                zza(mediaCodec, i, j4, jZzf);
                return true;
            }
        } else if (j6 < 30000) {
            if (j6 > 11000) {
                try {
                    Thread.sleep((j6 - 10000) / 1000);
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            }
            zzb(mediaCodec, i, j4);
            return true;
        }
        return false;
    }

    private final void zza(MediaCodec mediaCodec, int i, long j) {
        zzoe.beginSection("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        zzoe.endSection();
        this.zzaza.zzamb++;
    }

    private final void zzb(MediaCodec mediaCodec, int i, long j) {
        zziu();
        zzoe.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        zzoe.endSection();
        this.zzaza.zzama++;
        this.zzbhx = 0;
        zzis();
    }

    @TargetApi(21)
    private final void zza(MediaCodec mediaCodec, int i, long j, long j2) {
        zziu();
        zzoe.beginSection("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        zzoe.endSection();
        this.zzaza.zzama++;
        this.zzbhx = 0;
        zzis();
    }

    private final boolean zzm(boolean z) {
        if (zzoh.SDK_INT < 23 || this.zzajn) {
            return false;
        }
        return !z || zzok.zzc(this.zzlk);
    }

    private final void zziq() {
        this.zzbhu = this.zzbhk > 0 ? SystemClock.elapsedRealtime() + this.zzbhk : C.TIME_UNSET;
    }

    private final void zzir() {
        MediaCodec mediaCodecZzgn;
        this.zzbht = false;
        if (zzoh.SDK_INT < 23 || !this.zzajn || (mediaCodecZzgn = zzgn()) == null) {
            return;
        }
        this.zzbii = new zzot(this, mediaCodecZzgn);
    }

    final void zzis() {
        if (this.zzbht) {
            return;
        }
        this.zzbht = true;
        this.zzbhj.zzb(this.zzbhq);
    }

    private final void zzit() {
        this.zzbie = -1;
        this.zzbif = -1;
        this.zzbih = -1.0f;
        this.zzbig = -1;
    }

    private final void zziu() {
        if (this.zzbie == this.zzbia && this.zzbif == this.zzbib && this.zzbig == this.zzbic && this.zzbih == this.zzbid) {
            return;
        }
        this.zzbhj.zzb(this.zzbia, this.zzbib, this.zzbic, this.zzbid);
        this.zzbie = this.zzbia;
        this.zzbif = this.zzbib;
        this.zzbig = this.zzbic;
        this.zzbih = this.zzbid;
    }

    private final void zziv() {
        if (this.zzbie == -1 && this.zzbif == -1) {
            return;
        }
        this.zzbhj.zzb(this.zzbia, this.zzbib, this.zzbic, this.zzbid);
    }

    private final void zziw() {
        if (this.zzbhw > 0) {
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.zzbhj.zzf(this.zzbhw, jElapsedRealtime - this.zzbhv);
            this.zzbhw = 0;
            this.zzbhv = jElapsedRealtime;
        }
    }

    private static int zzi(zzgq zzgqVar) {
        if (zzgqVar.zzafc != -1) {
            return zzgqVar.zzafc;
        }
        return zza(zzgqVar.zzafb, zzgqVar.width, zzgqVar.height);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:27:0x0050  */
    /* JADX WARN: Code duplicated, block: B:40:0x007d  */
    private static int zza(String str, int i, int i2) {
        byte b;
        int iZze;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        int i3 = 4;
        switch (str) {
            case "video/3gpp":
                b = 0;
                break;
            case "video/hevc":
                b = 4;
                break;
            case "video/mp4v-es":
                b = 1;
                break;
            case "video/avc":
                b = 2;
                break;
            case "video/x-vnd.on2.vp8":
                b = 3;
                break;
            case "video/x-vnd.on2.vp9":
                b = 5;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0 || b == 1) {
            iZze = i * i2;
            i3 = 2;
        } else if (b != 2) {
            if (b == 3) {
                iZze = i * i2;
                i3 = 2;
            } else {
                if (b != 4 && b != 5) {
                    return -1;
                }
                iZze = i * i2;
            }
        } else {
            if ("BRAVIA 4K 2015".equals(zzoh.MODEL)) {
                return -1;
            }
            iZze = ((zzoh.zze(i, 16) * zzoh.zze(i2, 16)) << 4) << 4;
            i3 = 2;
        }
        return (iZze * 3) / (i3 * 2);
    }

    private static boolean zza(boolean z, zzgq zzgqVar, zzgq zzgqVar2) {
        if (!zzgqVar.zzafb.equals(zzgqVar2.zzafb) || zzj(zzgqVar) != zzj(zzgqVar2)) {
            return false;
        }
        if (z) {
            return true;
        }
        return zzgqVar.width == zzgqVar2.width && zzgqVar.height == zzgqVar2.height;
    }

    private static int zzj(zzgq zzgqVar) {
        if (zzgqVar.zzafg == -1) {
            return 0;
        }
        return zzgqVar.zzafg;
    }
}
