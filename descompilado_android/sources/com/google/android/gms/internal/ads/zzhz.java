package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.fingersoft.ads_sdk.advertising.providers.AdProviders;
import com.google.android.exoplayer2.util.MimeTypes;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: com.google.android.gms:play-services-ads@@18.1.1 */
/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public final class zzhz extends zzkn implements zznx {
    private int zzafl;
    private int zzafn;
    private final zzhh zzakj;
    private final zzhq zzakk;
    private boolean zzakl;
    private boolean zzakm;
    private MediaFormat zzakn;
    private long zzako;
    private boolean zzakp;

    public zzhz(zzkp zzkpVar) {
        this(zzkpVar, null, true);
    }

    protected static void zzb(int i, long j, long j2) {
    }

    protected static void zzfk() {
    }

    protected static void zzr(int i) {
    }

    @Override // com.google.android.gms.internal.ads.zzga, com.google.android.gms.internal.ads.zzgv
    public final zznx zzdh() {
        return this;
    }

    private zzhz(zzkp zzkpVar, zziq<Object> zziqVar, boolean z) {
        this(zzkpVar, null, true, null, null);
    }

    private zzhz(zzkp zzkpVar, zziq<Object> zziqVar, boolean z, Handler handler, zzhi zzhiVar) {
        this(zzkpVar, null, true, null, null, null, new zzhg[0]);
    }

    private zzhz(zzkp zzkpVar, zziq<Object> zziqVar, boolean z, Handler handler, zzhi zzhiVar, zzhd zzhdVar, zzhg... zzhgVarArr) {
        super(1, zzkpVar, zziqVar, z);
        this.zzakk = new zzhq(null, zzhgVarArr, new zzib(this));
        this.zzakj = new zzhh(null, null);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final int zza(zzkp zzkpVar, zzgq zzgqVar) throws zzks {
        String str = zzgqVar.zzafb;
        if (!zznw.zzbc(str)) {
            return 0;
        }
        int i = zzoh.SDK_INT >= 21 ? 16 : 0;
        if (zzax(str) && zzkpVar.zzgs() != null) {
            return i | 4 | 3;
        }
        zzkk zzkkVarZzc = zzkpVar.zzc(str, false);
        boolean z = true;
        if (zzkkVarZzc == null) {
            return 1;
        }
        if (zzoh.SDK_INT >= 21 && ((zzgqVar.zzafm != -1 && !zzkkVarZzc.zzap(zzgqVar.zzafm)) || (zzgqVar.zzafl != -1 && !zzkkVarZzc.zzaq(zzgqVar.zzafl)))) {
            z = false;
        }
        return i | 4 | (z ? 3 : 2);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final zzkk zza(zzkp zzkpVar, zzgq zzgqVar, boolean z) throws zzks {
        zzkk zzkkVarZzgs;
        if (zzax(zzgqVar.zzafb) && (zzkkVarZzgs = zzkpVar.zzgs()) != null) {
            this.zzakl = true;
            return zzkkVarZzgs;
        }
        this.zzakl = false;
        return super.zza(zzkpVar, zzgqVar, z);
    }

    private final boolean zzax(String str) {
        return this.zzakk.zzav(str);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zza(zzkk zzkkVar, MediaCodec mediaCodec, zzgq zzgqVar, MediaCrypto mediaCrypto) {
        this.zzakm = zzoh.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(zzkkVar.name) && AdProviders.SAMSUNG.equals(zzoh.MANUFACTURER) && (zzoh.DEVICE.startsWith("zeroflte") || zzoh.DEVICE.startsWith("herolte") || zzoh.DEVICE.startsWith("heroqlte"));
        if (this.zzakl) {
            this.zzakn = zzgqVar.zzeh();
            this.zzakn.setString("mime", MimeTypes.AUDIO_RAW);
            mediaCodec.configure(this.zzakn, (Surface) null, (MediaCrypto) null, 0);
            this.zzakn.setString("mime", zzgqVar.zzafb);
            return;
        }
        mediaCodec.configure(zzgqVar.zzeh(), (Surface) null, (MediaCrypto) null, 0);
        this.zzakn = null;
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzc(String str, long j, long j2) {
        this.zzakj.zza(str, j, j2);
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzd(zzgq zzgqVar) throws zzgb {
        super.zzd(zzgqVar);
        this.zzakj.zzb(zzgqVar);
        this.zzafn = MimeTypes.AUDIO_RAW.equals(zzgqVar.zzafb) ? zzgqVar.zzafn : 2;
        this.zzafl = zzgqVar.zzafl;
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) throws zzgb {
        int[] iArr;
        int i;
        boolean z = this.zzakn != null;
        String string = z ? this.zzakn.getString("mime") : MimeTypes.AUDIO_RAW;
        if (z) {
            mediaFormat = this.zzakn;
        }
        int integer = mediaFormat.getInteger("channel-count");
        int integer2 = mediaFormat.getInteger("sample-rate");
        if (this.zzakm && integer == 6 && (i = this.zzafl) < 6) {
            iArr = new int[i];
            for (int i2 = 0; i2 < this.zzafl; i2++) {
                iArr[i2] = i2;
            }
        } else {
            iArr = null;
        }
        try {
            this.zzakk.zza(string, integer, integer2, this.zzafn, 0, iArr);
        } catch (zzhu e) {
            throw zzgb.zza(e, getIndex());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zzd(boolean z) throws zzgb {
        super.zzd(z);
        this.zzakj.zza(this.zzaza);
        int i = zzdp().zzagc;
        if (i != 0) {
            this.zzakk.zzs(i);
        } else {
            this.zzakk.zzfa();
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zza(long j, boolean z) throws zzgb {
        super.zza(j, z);
        this.zzakk.reset();
        this.zzako = j;
        this.zzakp = true;
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void onStarted() {
        super.onStarted();
        this.zzakk.play();
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void onStopped() {
        this.zzakk.pause();
        super.onStopped();
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzga
    protected final void zzdo() {
        try {
            this.zzakk.release();
            try {
                super.zzdo();
            } finally {
                this.zzaza.zzfu();
                this.zzakj.zzb(this.zzaza);
            }
        } catch (Throwable th) {
            try {
                super.zzdo();
                throw th;
            } finally {
                this.zzaza.zzfu();
                this.zzakj.zzb(this.zzaza);
            }
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzgv
    public final boolean zzel() {
        return super.zzel() && this.zzakk.zzel();
    }

    @Override // com.google.android.gms.internal.ads.zzkn, com.google.android.gms.internal.ads.zzgv
    public final boolean isReady() {
        return this.zzakk.zzey() || super.isReady();
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final long zzfj() {
        long jZzi = this.zzakk.zzi(zzel());
        if (jZzi != Long.MIN_VALUE) {
            if (!this.zzakp) {
                jZzi = Math.max(this.zzako, jZzi);
            }
            this.zzako = jZzi;
            this.zzakp = false;
        }
        return this.zzako;
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final zzgw zzb(zzgw zzgwVar) {
        return this.zzakk.zzb(zzgwVar);
    }

    @Override // com.google.android.gms.internal.ads.zznx
    public final zzgw zzez() {
        return this.zzakk.zzez();
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final boolean zza(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z) throws zzgb {
        if (this.zzakl && (i2 & 2) != 0) {
            mediaCodec.releaseOutputBuffer(i, false);
            return true;
        }
        if (z) {
            mediaCodec.releaseOutputBuffer(i, false);
            this.zzaza.zzamb++;
            this.zzakk.zzev();
            return true;
        }
        try {
            if (!this.zzakk.zza(byteBuffer, j3)) {
                return false;
            }
            mediaCodec.releaseOutputBuffer(i, false);
            this.zzaza.zzama++;
            return true;
        } catch (zzht | zzhy e) {
            throw zzgb.zza(e, getIndex());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzkn
    protected final void zzfl() throws zzgb {
        try {
            this.zzakk.zzew();
        } catch (zzhy e) {
            throw zzgb.zza(e, getIndex());
        }
    }

    @Override // com.google.android.gms.internal.ads.zzga, com.google.android.gms.internal.ads.zzgg
    public final void zza(int i, Object obj) throws zzgb {
        if (i == 2) {
            this.zzakk.setVolume(((Float) obj).floatValue());
        } else if (i == 3) {
            this.zzakk.setStreamType(((Integer) obj).intValue());
        } else {
            super.zza(i, obj);
        }
    }

    static /* synthetic */ boolean zza(zzhz zzhzVar, boolean z) {
        zzhzVar.zzakp = true;
        return true;
    }
}
