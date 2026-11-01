package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Looper;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public abstract class zzkn extends zzga {
    private static final byte[] zzaxq = zzoh.zzbj("0000016742C00BDA259000000168CE0F13200000016588840DCE7118A0002FBF1C31C3275D78");
    private zzgq zzafw;
    private ByteBuffer[] zzaje;
    private final zzkp zzaxr;
    private final zziq<Object> zzaxs;
    private final boolean zzaxt;
    private final zzim zzaxu;
    private final zzim zzaxv;
    private final zzgs zzaxw;
    private final List<Long> zzaxx;
    private final MediaCodec.BufferInfo zzaxy;
    private zzio<Object> zzaxz;
    private zzio<Object> zzaya;
    private MediaCodec zzayb;
    private zzkk zzayc;
    private boolean zzayd;
    private boolean zzaye;
    private boolean zzayf;
    private boolean zzayg;
    private boolean zzayh;
    private boolean zzayi;
    private boolean zzayj;
    private boolean zzayk;
    private boolean zzayl;
    private ByteBuffer[] zzaym;
    private long zzayn;
    private int zzayo;
    private int zzayp;
    private boolean zzayq;
    private boolean zzayr;
    private int zzays;
    private int zzayt;
    private boolean zzayu;
    private boolean zzayv;
    private boolean zzayw;
    private boolean zzayx;
    private boolean zzayy;
    private boolean zzayz;
    protected zzij zzaza;

    public zzkn(int i, zzkp zzkpVar, zziq<Object> zziqVar, boolean z) {
        super(i);
        zznt.checkState(zzoh.SDK_INT >= 16);
        this.zzaxr = (zzkp) zznt.checkNotNull(zzkpVar);
        this.zzaxs = zziqVar;
        this.zzaxt = z;
        this.zzaxu = new zzim(0);
        this.zzaxv = new zzim(0);
        this.zzaxw = new zzgs();
        this.zzaxx = new ArrayList();
        this.zzaxy = new MediaCodec.BufferInfo();
        this.zzays = 0;
        this.zzayt = 0;
    }

    protected void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzgb {
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected void onStarted() {
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected void onStopped() {
    }

    protected abstract int zza(zzkp zzkpVar, zzgq zzgqVar) throws zzks;

    protected void zza(zzim zzimVar) {
    }

    protected abstract void zza(zzkk zzkkVar, MediaCodec mediaCodec, zzgq zzgqVar, MediaCrypto mediaCrypto) throws zzks;

    protected abstract boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzgb;

    protected boolean zza(MediaCodec mediaCodec, boolean z, zzgq zzgqVar, zzgq zzgqVar2) {
        return false;
    }

    protected boolean zza(zzkk zzkkVar) {
        return true;
    }

    protected void zzc(String str, long j, long j2) {
    }

    @Override // com.google.android.gms.internal.ads.zzga, com.google.android.gms.internal.ads.zzgy
    public final int zzdn() {
        return 4;
    }

    protected void zzfl() throws zzgb {
    }

    @Override // com.google.android.gms.internal.ads.zzgy
    public final int zza(zzgq zzgqVar) throws zzgb {
        try {
            return zza(this.zzaxr, zzgqVar);
        } catch (zzks e) {
            throw zzgb.zza(e, getIndex());
        }
    }

    protected zzkk zza(zzkp zzkpVar, zzgq zzgqVar, boolean z) throws zzks {
        return zzkpVar.zzc(zzgqVar.zzafb, z);
    }

    protected final void zzgm() throws zzgb {
        zzgq zzgqVar;
        if (this.zzayb != null || (zzgqVar = this.zzafw) == null) {
            return;
        }
        this.zzaxz = this.zzaya;
        String str = zzgqVar.zzafb;
        zzio<Object> zzioVar = this.zzaxz;
        if (zzioVar != null) {
            int state = zzioVar.getState();
            if (state == 0) {
                throw zzgb.zza(this.zzaxz.zzfx(), getIndex());
            }
            if (state == 3 || state == 4) {
                this.zzaxz.zzfw();
                throw new NoSuchMethodError();
            }
            return;
        }
        if (this.zzayc == null) {
            try {
                this.zzayc = zza(this.zzaxr, this.zzafw, false);
                zzkk zzkkVar = this.zzayc;
            } catch (zzks e) {
                zza(new zzkm(this.zzafw, (Throwable) e, false, -49998));
            }
            if (this.zzayc == null) {
                zza(new zzkm(this.zzafw, (Throwable) null, false, -49999));
            }
        }
        if (zza(this.zzayc)) {
            String str2 = this.zzayc.name;
            this.zzayd = zzoh.SDK_INT < 21 && this.zzafw.zzafd.isEmpty() && "OMX.MTK.VIDEO.DECODER.AVC".equals(str2);
            this.zzaye = zzoh.SDK_INT < 18 || (zzoh.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(str2) || "OMX.SEC.avc.dec.secure".equals(str2))) || (zzoh.SDK_INT == 19 && zzoh.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(str2) || "OMX.Exynos.avc.dec.secure".equals(str2)));
            this.zzayf = zzoh.SDK_INT < 24 && ("OMX.Nvidia.h264.decode".equals(str2) || "OMX.Nvidia.h264.decode.secure".equals(str2)) && ("flounder".equals(zzoh.DEVICE) || "flounder_lte".equals(zzoh.DEVICE) || "grouper".equals(zzoh.DEVICE) || "tilapia".equals(zzoh.DEVICE));
            this.zzayg = zzoh.SDK_INT <= 17 && ("OMX.rk.video_decoder.avc".equals(str2) || "OMX.allwinner.video.decoder.avc".equals(str2));
            this.zzayh = (zzoh.SDK_INT <= 23 && "OMX.google.vorbis.decoder".equals(str2)) || (zzoh.SDK_INT <= 19 && "hb2000".equals(zzoh.DEVICE) && ("OMX.amlogic.avc.decoder.awesome".equals(str2) || "OMX.amlogic.avc.decoder.awesome.secure".equals(str2)));
            this.zzayi = zzoh.SDK_INT == 21 && "OMX.google.aac.decoder".equals(str2);
            this.zzayj = zzoh.SDK_INT <= 18 && this.zzafw.zzafl == 1 && "OMX.MTK.AUDIO.DECODER.MP3".equals(str2);
            try {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                String strValueOf = String.valueOf(str2);
                zzoe.beginSection(strValueOf.length() != 0 ? "createCodec:".concat(strValueOf) : new String("createCodec:"));
                this.zzayb = MediaCodec.createByCodecName(str2);
                zzoe.endSection();
                zzoe.beginSection("configureCodec");
                zza(this.zzayc, this.zzayb, this.zzafw, (MediaCrypto) null);
                zzoe.endSection();
                zzoe.beginSection("startCodec");
                this.zzayb.start();
                zzoe.endSection();
                long jElapsedRealtime2 = SystemClock.elapsedRealtime();
                zzc(str2, jElapsedRealtime2, jElapsedRealtime2 - jElapsedRealtime);
                this.zzaym = this.zzayb.getInputBuffers();
                this.zzaje = this.zzayb.getOutputBuffers();
            } catch (Exception e2) {
                zza(new zzkm(this.zzafw, (Throwable) e2, false, str2));
            }
            this.zzayn = getState() == 2 ? SystemClock.elapsedRealtime() + 1000 : C.TIME_UNSET;
            this.zzayo = -1;
            this.zzayp = -1;
            this.zzayz = true;
            this.zzaza.zzalx++;
        }
    }

    private final void zza(zzkm zzkmVar) throws zzgb {
        throw zzgb.zza(zzkmVar, getIndex());
    }

    protected final MediaCodec zzgn() {
        return this.zzayb;
    }

    protected final zzkk zzgo() {
        return this.zzayc;
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected void zzd(boolean z) throws zzgb {
        this.zzaza = new zzij();
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected void zza(long j, boolean z) throws zzgb {
        this.zzayw = false;
        this.zzayx = false;
        if (this.zzayb != null) {
            this.zzayn = C.TIME_UNSET;
            this.zzayo = -1;
            this.zzayp = -1;
            this.zzayz = true;
            this.zzayy = false;
            this.zzayq = false;
            this.zzaxx.clear();
            this.zzayk = false;
            this.zzayl = false;
            if (this.zzaye || ((this.zzayh && this.zzayv) || this.zzayt != 0)) {
                zzgp();
                zzgm();
            } else {
                this.zzayb.flush();
                this.zzayu = false;
            }
            if (!this.zzayr || this.zzafw == null) {
                return;
            }
            this.zzays = 1;
        }
    }

    @Override // com.google.android.gms.internal.ads.zzga
    protected void zzdo() {
        this.zzafw = null;
        try {
            zzgp();
            try {
                if (this.zzaxz != null) {
                    this.zzaxs.zza(this.zzaxz);
                }
                try {
                    if (this.zzaya != null && this.zzaya != this.zzaxz) {
                        this.zzaxs.zza(this.zzaya);
                    }
                } finally {
                    this.zzaxz = null;
                    this.zzaya = null;
                }
            } catch (Throwable th) {
                try {
                    if (this.zzaya != null && this.zzaya != this.zzaxz) {
                        this.zzaxs.zza(this.zzaya);
                    }
                    throw th;
                } finally {
                    this.zzaxz = null;
                    this.zzaya = null;
                }
            }
        } catch (Throwable th2) {
            try {
                if (this.zzaxz != null) {
                    this.zzaxs.zza(this.zzaxz);
                }
                try {
                    if (this.zzaya != null && this.zzaya != this.zzaxz) {
                        this.zzaxs.zza(this.zzaya);
                    }
                    throw th2;
                } finally {
                    this.zzaxz = null;
                    this.zzaya = null;
                }
            } catch (Throwable th3) {
                try {
                    if (this.zzaya != null && this.zzaya != this.zzaxz) {
                        this.zzaxs.zza(this.zzaya);
                    }
                    throw th3;
                } finally {
                    this.zzaxz = null;
                    this.zzaya = null;
                }
            }
        }
    }

    protected void zzgp() {
        this.zzayn = C.TIME_UNSET;
        this.zzayo = -1;
        this.zzayp = -1;
        this.zzayy = false;
        this.zzayq = false;
        this.zzaxx.clear();
        this.zzaym = null;
        this.zzaje = null;
        this.zzayc = null;
        this.zzayr = false;
        this.zzayu = false;
        this.zzayd = false;
        this.zzaye = false;
        this.zzayf = false;
        this.zzayg = false;
        this.zzayh = false;
        this.zzayj = false;
        this.zzayk = false;
        this.zzayl = false;
        this.zzayv = false;
        this.zzays = 0;
        this.zzayt = 0;
        this.zzaxu.zzcq = null;
        if (this.zzayb != null) {
            this.zzaza.zzaly++;
            try {
                this.zzayb.stop();
                try {
                    this.zzayb.release();
                    this.zzayb = null;
                    zzio<Object> zzioVar = this.zzaxz;
                    if (zzioVar == null || this.zzaya == zzioVar) {
                        return;
                    }
                    try {
                        this.zzaxs.zza(zzioVar);
                    } finally {
                        this.zzaxz = null;
                    }
                } catch (Throwable th) {
                    this.zzayb = null;
                    zzio<Object> zzioVar2 = this.zzaxz;
                    if (zzioVar2 != null && this.zzaya != zzioVar2) {
                        try {
                            this.zzaxs.zza(zzioVar2);
                        } finally {
                            this.zzaxz = null;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    this.zzayb.release();
                    this.zzayb = null;
                    zzio<Object> zzioVar3 = this.zzaxz;
                    if (zzioVar3 != null && this.zzaya != zzioVar3) {
                        try {
                            this.zzaxs.zza(zzioVar3);
                        } finally {
                            this.zzaxz = null;
                        }
                    }
                    throw th2;
                } catch (Throwable th3) {
                    this.zzayb = null;
                    zzio<Object> zzioVar4 = this.zzaxz;
                    if (zzioVar4 != null && this.zzaya != zzioVar4) {
                        try {
                            this.zzaxs.zza(zzioVar4);
                        } finally {
                            this.zzaxz = null;
                        }
                    }
                    throw th3;
                }
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public final void zzb(long j, long j2) throws zzgb {
        if (this.zzayx) {
            zzfl();
            return;
        }
        if (this.zzafw == null) {
            this.zzaxv.clear();
            int iZza = zza(this.zzaxw, this.zzaxv, true);
            if (iZza != -5) {
                if (iZza == -4) {
                    zznt.checkState(this.zzaxv.zzfp());
                    this.zzayw = true;
                    zzgr();
                    return;
                }
                return;
            }
            zzd(this.zzaxw.zzafw);
        }
        zzgm();
        if (this.zzayb != null) {
            zzoe.beginSection("drainAndFeed");
            while (zzd(j, j2)) {
            }
            while (zzgq()) {
            }
            zzoe.endSection();
        } else {
            zzdh(j);
            this.zzaxv.clear();
            int iZza2 = zza(this.zzaxw, this.zzaxv, false);
            if (iZza2 == -5) {
                zzd(this.zzaxw.zzafw);
            } else if (iZza2 == -4) {
                zznt.checkState(this.zzaxv.zzfp());
                this.zzayw = true;
                zzgr();
            }
        }
        this.zzaza.zzfu();
    }

    /* JADX WARN: Code duplicated, block: B:83:0x0144  */
    private final boolean zzgq() throws zzgb {
        int iPosition;
        int iZza;
        boolean z;
        MediaCodec mediaCodec = this.zzayb;
        if (mediaCodec == null || this.zzayt == 2 || this.zzayw) {
            return false;
        }
        if (this.zzayo < 0) {
            this.zzayo = mediaCodec.dequeueInputBuffer(0L);
            int i = this.zzayo;
            if (i < 0) {
                return false;
            }
            zzim zzimVar = this.zzaxu;
            zzimVar.zzcq = this.zzaym[i];
            zzimVar.clear();
        }
        if (this.zzayt == 1) {
            if (!this.zzayg) {
                this.zzayv = true;
                this.zzayb.queueInputBuffer(this.zzayo, 0, 0, 0L, 4);
                this.zzayo = -1;
            }
            this.zzayt = 2;
            return false;
        }
        if (this.zzayk) {
            this.zzayk = false;
            this.zzaxu.zzcq.put(zzaxq);
            this.zzayb.queueInputBuffer(this.zzayo, 0, zzaxq.length, 0L, 0);
            this.zzayo = -1;
            this.zzayu = true;
            return true;
        }
        if (this.zzayy) {
            iZza = -4;
            iPosition = 0;
        } else {
            if (this.zzays == 1) {
                for (int i2 = 0; i2 < this.zzafw.zzafd.size(); i2++) {
                    this.zzaxu.zzcq.put(this.zzafw.zzafd.get(i2));
                }
                this.zzays = 2;
            }
            iPosition = this.zzaxu.zzcq.position();
            iZza = zza(this.zzaxw, this.zzaxu, false);
        }
        if (iZza == -3) {
            return false;
        }
        if (iZza == -5) {
            if (this.zzays == 2) {
                this.zzaxu.clear();
                this.zzays = 1;
            }
            zzd(this.zzaxw.zzafw);
            return true;
        }
        if (this.zzaxu.zzfp()) {
            if (this.zzays == 2) {
                this.zzaxu.clear();
                this.zzays = 1;
            }
            this.zzayw = true;
            if (!this.zzayu) {
                zzgr();
                return false;
            }
            try {
                if (!this.zzayg) {
                    this.zzayv = true;
                    this.zzayb.queueInputBuffer(this.zzayo, 0, 0, 0L, 4);
                    this.zzayo = -1;
                }
                return false;
            } catch (MediaCodec.CryptoException e) {
                throw zzgb.zza(e, getIndex());
            }
        }
        if (this.zzayz && !this.zzaxu.zzfq()) {
            this.zzaxu.clear();
            if (this.zzays == 2) {
                this.zzays = 1;
            }
            return true;
        }
        this.zzayz = false;
        boolean zZzfv = this.zzaxu.zzfv();
        zzio<Object> zzioVar = this.zzaxz;
        if (zzioVar == null) {
            z = false;
        } else {
            int state = zzioVar.getState();
            if (state == 0) {
                throw zzgb.zza(this.zzaxz.zzfx(), getIndex());
            }
            if (state == 4 || (!zZzfv && this.zzaxt)) {
                z = false;
            } else {
                z = true;
            }
        }
        this.zzayy = z;
        if (this.zzayy) {
            return false;
        }
        if (this.zzayd && !zZzfv) {
            zznz.zzk(this.zzaxu.zzcq);
            if (this.zzaxu.zzcq.position() == 0) {
                return true;
            }
            this.zzayd = false;
        }
        try {
            long j = this.zzaxu.zzami;
            if (this.zzaxu.zzfo()) {
                this.zzaxx.add(Long.valueOf(j));
            }
            this.zzaxu.zzcq.flip();
            zza(this.zzaxu);
            if (zZzfv) {
                MediaCodec.CryptoInfo cryptoInfoZzft = this.zzaxu.zzamh.zzft();
                if (iPosition != 0) {
                    if (cryptoInfoZzft.numBytesOfClearData == null) {
                        cryptoInfoZzft.numBytesOfClearData = new int[1];
                    }
                    int[] iArr = cryptoInfoZzft.numBytesOfClearData;
                    iArr[0] = iArr[0] + iPosition;
                }
                this.zzayb.queueSecureInputBuffer(this.zzayo, 0, cryptoInfoZzft, j, 0);
            } else {
                this.zzayb.queueInputBuffer(this.zzayo, 0, this.zzaxu.zzcq.limit(), j, 0);
            }
            this.zzayo = -1;
            this.zzayu = true;
            this.zzays = 0;
            this.zzaza.zzalz++;
            return true;
        } catch (MediaCodec.CryptoException e2) {
            throw zzgb.zza(e2, getIndex());
        }
    }

    protected void zzd(zzgq zzgqVar) throws zzgb {
        MediaCodec mediaCodec;
        zzgq zzgqVar2 = this.zzafw;
        this.zzafw = zzgqVar;
        if (!zzoh.zza(this.zzafw.zzafe, zzgqVar2 == null ? null : zzgqVar2.zzafe)) {
            if (this.zzafw.zzafe != null) {
                zziq<Object> zziqVar = this.zzaxs;
                if (zziqVar == null) {
                    throw zzgb.zza(new IllegalStateException("Media requires a DrmSessionManager"), getIndex());
                }
                this.zzaya = zziqVar.zza(Looper.myLooper(), this.zzafw.zzafe);
                zzio<Object> zzioVar = this.zzaya;
                if (zzioVar == this.zzaxz) {
                    this.zzaxs.zza(zzioVar);
                }
            } else {
                this.zzaya = null;
            }
        }
        if (this.zzaya == this.zzaxz && (mediaCodec = this.zzayb) != null && zza(mediaCodec, this.zzayc.zzaxj, zzgqVar2, this.zzafw)) {
            this.zzayr = true;
            this.zzays = 1;
            this.zzayk = this.zzayf && this.zzafw.width == zzgqVar2.width && this.zzafw.height == zzgqVar2.height;
        } else if (this.zzayu) {
            this.zzayt = 1;
        } else {
            zzgp();
            zzgm();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public boolean zzel() {
        return this.zzayx;
    }

    @Override // com.google.android.gms.internal.ads.zzgv
    public boolean isReady() {
        if (this.zzafw == null || this.zzayy) {
            return false;
        }
        if (zzdq() || this.zzayp >= 0) {
            return true;
        }
        return this.zzayn != C.TIME_UNSET && SystemClock.elapsedRealtime() < this.zzayn;
    }

    private final boolean zzd(long j, long j2) throws zzgb {
        boolean zZza;
        boolean z;
        if (this.zzayp < 0) {
            if (this.zzayi && this.zzayv) {
                try {
                    this.zzayp = this.zzayb.dequeueOutputBuffer(this.zzaxy, 0L);
                } catch (IllegalStateException unused) {
                    zzgr();
                    if (this.zzayx) {
                        zzgp();
                    }
                    return false;
                }
            } else {
                this.zzayp = this.zzayb.dequeueOutputBuffer(this.zzaxy, 0L);
            }
            int i = this.zzayp;
            if (i < 0) {
                if (i != -2) {
                    if (i == -3) {
                        this.zzaje = this.zzayb.getOutputBuffers();
                        return true;
                    }
                    if (this.zzayg && (this.zzayw || this.zzayt == 2)) {
                        zzgr();
                    }
                    return false;
                }
                MediaFormat outputFormat = this.zzayb.getOutputFormat();
                if (this.zzayf && outputFormat.getInteger("width") == 32 && outputFormat.getInteger("height") == 32) {
                    this.zzayl = true;
                } else {
                    if (this.zzayj) {
                        outputFormat.setInteger("channel-count", 1);
                    }
                    onOutputFormatChanged(this.zzayb, outputFormat);
                }
                return true;
            }
            if (this.zzayl) {
                this.zzayl = false;
                this.zzayb.releaseOutputBuffer(i, false);
                this.zzayp = -1;
                return true;
            }
            if ((this.zzaxy.flags & 4) != 0) {
                zzgr();
                this.zzayp = -1;
                return false;
            }
            ByteBuffer byteBuffer = this.zzaje[this.zzayp];
            if (byteBuffer != null) {
                byteBuffer.position(this.zzaxy.offset);
                byteBuffer.limit(this.zzaxy.offset + this.zzaxy.size);
            }
            long j3 = this.zzaxy.presentationTimeUs;
            int size = this.zzaxx.size();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    z = false;
                    break;
                }
                if (this.zzaxx.get(i2).longValue() == j3) {
                    this.zzaxx.remove(i2);
                    z = true;
                    break;
                }
                i2++;
            }
            this.zzayq = z;
        }
        if (this.zzayi && this.zzayv) {
            try {
                zZza = zza(j, j2, this.zzayb, this.zzaje[this.zzayp], this.zzayp, this.zzaxy.flags, this.zzaxy.presentationTimeUs, this.zzayq);
            } catch (IllegalStateException unused2) {
                zzgr();
                if (this.zzayx) {
                    zzgp();
                }
                return false;
            }
        } else {
            MediaCodec mediaCodec = this.zzayb;
            ByteBuffer[] byteBufferArr = this.zzaje;
            int i3 = this.zzayp;
            zZza = zza(j, j2, mediaCodec, byteBufferArr[i3], i3, this.zzaxy.flags, this.zzaxy.presentationTimeUs, this.zzayq);
        }
        if (!zZza) {
            return false;
        }
        long j4 = this.zzaxy.presentationTimeUs;
        this.zzayp = -1;
        return true;
    }

    private final void zzgr() throws zzgb {
        if (this.zzayt == 2) {
            zzgp();
            zzgm();
        } else {
            this.zzayx = true;
            zzfl();
        }
    }
}
